package com.rograndec.feijiayun.chain.business.report.quality.retail.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.rograndec.feijiayun.chain.common.constant.InChargeDrug;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.quality.retail.dao.SaleOutORreturnMapper;
import com.rograndec.feijiayun.chain.business.report.quality.retail.service.SaleOutORreturnService;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


/**
 * 
 * @ClassName: SaleOutORreturnServiceImpl   
 * @Description: 销售/销退
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月23日 下午4:36:42
 */
@Service
public class SaleOutORreturnServiceImpl implements SaleOutORreturnService{
	
//	@Autowired
//	private InOutComponent inOutComponent;
	
	@Autowired
	private SaleOutORreturnMapper saleOutORreturnMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

	
	@Override
	public Page<SaleOutORreturnTotalVO> getSaleOutORreturn(SaleOutORreturnParamVO paramVO) throws Exception {
		Page<SaleOutORreturnTotalVO> page = new Page<>();
		paramVO.setStart((paramVO.getPageNo() - 1) * paramVO.getPageSize());
		SaleOutORreturnTotalVO total = getSaleOutORreturnFormat(paramVO);
		page.setResult(total);
		page.setTotalRecord(saleOutORreturnMapper.countSaleOutORreturn(paramVO));
		return page;
	}
	
	private SaleOutORreturnTotalVO getSaleOutORreturnFormat(SaleOutORreturnParamVO paramVO) {
		//处理排序字段
		String order = paramVO.getOrder();
		String sort = paramVO.getSort();
		if("dailyTime".equals(order)) {
			paramVO.setOrder("dailyTime");
		} else if ("saleDate".equals(order)) {
			paramVO.setOrder("saleDate");
		} else if ("saleCode".equals(order)){
			paramVO.setOrder("saleCode");
		} else {
			paramVO.setOrder(null);
		}
		if(null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				paramVO.setSort("desc");
			}
		}
		List<SaleOutORreturnVO> list = saleOutORreturnMapper.getSaleOutORreturn(paramVO);
		for(SaleOutORreturnVO saleOut : list){
            Integer specialDrug = saleOut.getSpecialDrug();
            Integer inChargeDrug = saleOut.getInChargeDrug();
            if(null != specialDrug && -1 != specialDrug){
                String name = SpecialDrugs.getName(specialDrug);
                saleOut.setSpecialDrugName(name);
            }
            if(null != inChargeDrug && -1 != inChargeDrug){
                String name = InChargeDrug.getName(inChargeDrug);
                saleOut.setInChargeDrugName(name);
            }
        }
		SaleOutORreturnTotalVO total = saleOutORreturnMapper.sumSaleOutORreturn(paramVO);
		if(null != total) {
			total.setList(list);
		}
		return total;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output,SaleOutORreturnParamVO paramVO,UserVO userVO) throws Exception {
		//标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        String saleName = "";
        if(0 == paramVO.getSaleType()) {
        	saleName = "销售";
        	names.add("销售出库单");
        }
        if(1 == paramVO.getSaleType()) {
        	saleName = "销退";
        	names.add("销售退货单");
        }
        SaleOutORreturnTotalVO total = getSaleOutORreturnFormat(paramVO);
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("enterpriseCode", "组织机构编码");
            map.put("enterpriseName", "组织机构名称");
        }
        map.put("dailyTime", "日结日期");
        map.put("saleDate", "销售日期");
        map.put("saleCode", saleName+"单号");
        map.put("saleModeStr", "销售模式");
        map.put("standCode", "款台编码");
        map.put("teamName", "班组");
        map.put("payeeName", "收款人员");
        map.put("cargoAreaName", "柜组");
        map.put("clerkName", "营业人员");
        map.put("memberCardCode", "会员卡号");
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
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效日期");
        map.put("shelfName", "货位");
        map.put("shelfStatusDesc", "质量状况");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("lineDiscount", "行折扣");
        map.put("amount", "金额");
        map.put("wholeDiscount", "整单折扣");
        map.put("lineDiscountAmount", "行优惠");
        map.put("realPrice", "实际单价");
        map.put("realAmount", "实际金额");
        map.put("taxRate", "销项税");
        map.put("notaxRealPrice", "不含税单价");
        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("businessVarietyName", "品种类型");
        map.put("categoryName", "商品分类");
        map.put("goodsAttributeName", "商品属性");
        map.put("domesticImportDesc", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("specialDrugName", "特殊管理药品");
        map.put("inChargeDrugName", "专管药品");
        map.put("storageTempDesc", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("qualityPeriodDesc", "保质期");
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        needTotalName.add("amount");
        needTotalName.add("realAmount");
        needTotalName.add("notaxRealAmount");
        needTotalName.add("taxAmount");
        StringBuilder sb = new StringBuilder();
        sb.append(total.getQuantityTotal()).append(";")
        .append(total.getAmountTotal()).append(";")
        .append(total.getRealAmountTotal()).append(";")
        .append(total.getNotaxRealAmountTotal()).append(";")
        .append(total.getTaxAmountTotal());
        purchaseGeneralComponent.commExcelExport(output, map, total.getList(),
                names, null, sb.toString(), false, needTotalName);
	}

}
