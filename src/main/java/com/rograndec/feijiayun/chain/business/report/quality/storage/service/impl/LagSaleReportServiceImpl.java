package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.dao.LagSaleReportMapper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.LagSaleReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsRequestParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 * 
 * @ClassName: LagSaleReportService   
 * @Description: 滞销商品
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月21日 上午11:22:27
 */
@Service
public class LagSaleReportServiceImpl implements LagSaleReportService{
	
	@Autowired
	private LagSaleReportMapper lagSaleReportMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

	/**
	 * 
	 * @Description: 计算滞销天数
	 * 滞销天数 = 当前时间 - (最后一次销售时间 + 滞销周期)
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月25日 下午3:17:44 
	 * @param lastSaleDate 最后一次销售时间
	 * @param unsalableCycle 滞销周期
	 * @param unsalableCycleUnit 滞销周期单位
	 * @return 
	 * @return int
	 */
	public int lagSaleDay(Date lastSaleDate,Integer unsalableCycle,Integer unsalableCycleUnit) {
		int lagDay = 0;
		if(null != lastSaleDate) {
			if(null != unsalableCycle && null != unsalableCycleUnit) {
				if(unsalableCycle < 0) {
					return lagDay;
				}
				//月
				if(unsalableCycleUnit == 1) {
					unsalableCycle = unsalableCycle * 30;				
				}
				//年
				if(unsalableCycleUnit == 2) {
					unsalableCycle = unsalableCycle * 30 * 365;
				}
				Date addDate = DateUtils.addDay(lastSaleDate, unsalableCycle);
				Date now = new Date();
				lagDay = DateUtils.diffInDays(now, addDate).intValue();
			}
		}
		return lagDay;
	}

	@Override
	public Page<LagSaleGoodsTotalVO> getLagSaleGoods(LagSaleGoodsRequestParamVO paramVO) throws Exception {
		Page<LagSaleGoodsTotalVO> page = new Page<>();
		paramVO.setStart((paramVO.getPageNo() - 1) * paramVO.getPageSize());
		LagSaleGoodsTotalVO total = getLagSaleGoodsFormat(paramVO);
		page.setResult(total);
		// 设置分页的小计
		List<LagSaleGoodsVO> list = page.getResult() == null ? null : page.getResult().getList();
		BigDecimal subtotal = BigDecimal.ZERO;
		if(null != list && list.size() > 0) {
			for(LagSaleGoodsVO ls : list) {
				subtotal = subtotal.add(ls.getUsableQuantity());
			}
			total.setUsableQuantitySubtotal(subtotal);
		}
		page.setResult(total);
		page.setTotalRecord(lagSaleReportMapper.countLagSaleGoods(paramVO));
		return page;
	}
	
	
	private LagSaleGoodsTotalVO getLagSaleGoodsFormat(LagSaleGoodsRequestParamVO paramVO){
		List<LagSaleGoodsVO> list = lagSaleReportMapper.getLagSaleGoods(paramVO);
		/*if(null != list && list.size() > 0) {
			list.forEach(item -> {
				item.setUnsalableDay(lagSaleDay(item.getSaleDate(), item.getUnsalableCycle(),item.getUnsalableCycleUnit()));
			});
		}*/
		LagSaleGoodsTotalVO total = lagSaleReportMapper.sumLagSaleGoods(paramVO);
		if(null != total) {
			total.setList(list);
		}
		return total;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output, LagSaleGoodsRequestParamVO paramVO,UserVO userVO) throws Exception {
		//标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("滞销商品");
        LagSaleGoodsTotalVO total = getLagSaleGoodsFormat(paramVO);
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
        }
        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName", "商品名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("usableQuantity", "数量");
        map.put("saleDate", "最近销售日期");
        map.put("unsalableCycle", "滞销周期");
        map.put("unsalableDay", "滞销天数");
        map.put("businessVarietyName", "品种类型");
        map.put("categoryName", "商品分类");
        map.put("goodsAttributeName", "商品属性");
        map.put("domesticImportDesc", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("specialDrugName", "特殊管理药品");
        map.put("inChargeDrugName", "专管药品");
        map.put("limitQuantity", "限购数量");
        map.put("storageTempDesc", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("qualityPeriodDesc", "保质期");
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("usableQuantity");
        purchaseGeneralComponent.commExcelExport(output, map, total.getList(),
                names, null, total.getUsableQuantityTotal()+"", false, needTotalName);
	}

}
