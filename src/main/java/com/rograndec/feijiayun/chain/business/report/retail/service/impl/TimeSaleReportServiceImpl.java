package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import com.rograndec.feijiayun.chain.business.report.retail.dao.TimeSaleReportMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.TimeSaleReportService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSalePeriodMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.ExcelPic;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TimeSaleReportServiceImpl implements TimeSaleReportService {

	@Autowired
	private PosSalePeriodMapper posSalePeriodMapper;
	@Autowired
	private TimeSaleReportMapper timeSaleReportMapper;
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;
	@Autowired
	private ManageConfigService manageConfigService;

	@Override
	public RetailTimeSaleVo getRetailTimeSaleVo(UserVO uservo, RequestRetailTimeSale requestRetailTimeSale) {
		List<String> xline=new ArrayList<>();
		List<BigDecimal> yline=new ArrayList<>();
		requestRetailTimeSale.setEnterpriseId(uservo.getEnterpriseId());
		List<PosSalePeriodVO> posSalePeriodVOS= posSalePeriodMapper.findByEnterpriseIdUseable(uservo.getEnterpriseId());
		for (PosSalePeriodVO posSalePeriodVO : posSalePeriodVOS) {
			xline.add(posSalePeriodVO.getName());
			requestRetailTimeSale.setStartDate(posSalePeriodVO.getStartTime());
			requestRetailTimeSale.setEndDate(posSalePeriodVO.getEndTime());
			if(requestRetailTimeSale.getSaleDate()==null||"".equals(requestRetailTimeSale.getSaleDate())){
				requestRetailTimeSale.setSaleDate(DateUtils.DateToString(new Date(),"yyyy-MM-dd"));
			}
			if(requestRetailTimeSale.getSaleType()==1){//按金额
				yline.add(timeSaleReportMapper.getSalePrice(requestRetailTimeSale));
			}else if(requestRetailTimeSale.getSaleType()==2){//按客流
				yline.add(timeSaleReportMapper.getSaleCount(requestRetailTimeSale));
			}
		}
		RetailTimeSaleVo retailTimeSaleVo=new RetailTimeSaleVo();
		retailTimeSaleVo.setxLine(xline);
		retailTimeSaleVo.setyLine(yline);
		return retailTimeSaleVo;
	}

	@Override
	public void exportExcel(UserVO userVO,OutputStream output, RequestRetailTimeSale requestRetailTimeSale) {
		ExcelPic excelPic=new ExcelPic();
		excelPic.setPic(requestRetailTimeSale.getPic());
		excelPic.setDx1(0);
		excelPic.setDy1(0);
		excelPic.setDx2(1023);
		excelPic.setDy2(100);
		excelPic.setCol1((short)1);
		excelPic.setRow1(4);
		excelPic.setCol2((short) 14);
		excelPic.setRow2(23);
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		List<String> names = new ArrayList<>();
		List<String> titleSecond = new ArrayList<>();
		names.add(userVO.getEnterpriseName());
		if(requestRetailTimeSale.getSaleDate()==null||"".equals(requestRetailTimeSale.getSaleDate())){
			names.add("当日销售");
			titleSecond.add("类型:"+(requestRetailTimeSale.getSaleType()==1?"按金额":"按客流"));
		}else{
			names.add("历史销售");
			titleSecond.add("日期"+requestRetailTimeSale.getSaleDate()+"            类型:"+(requestRetailTimeSale.getSaleType()==1?"按金额":"按客流"));
		}
		List list=new ArrayList();
		purchaseGeneralComponent.commExcelExportPic(output, map,
				list, names,
				titleSecond, "",
				false, null,excelPic);
	}

	@Override
	public List<GoodsCategorySaleVo> listGoodsCategorySaleVos(RequestGoodsCategorySaleVo requestGoodsCategorySaleVo) {
		requestGoodsCategorySaleVo.setParentId(0l);
		//获取所有父类
		List<GoodsCategorySaleVo> parentList=timeSaleReportMapper.getParent(0l,0l);
		List<GoodsCategorySaleVo> list=new ArrayList<>();
		list.addAll(parentList);
		this.handleChildren(parentList,requestGoodsCategorySaleVo,list);
		return parentList;
	}

	@Override
	public void exportExcelForGoodsCategorySale(OutputStream output, RequestGoodsCategorySaleVo requestGoodsCategorySaleVo, UserVO userVO) {
		requestGoodsCategorySaleVo.setParentId(0l);
		//获取所有父类
		List<GoodsCategorySaleVo> parentList=timeSaleReportMapper.getParent(0l,0l);
		List<GoodsCategorySaleVo> list=new ArrayList<>();
		list.addAll(parentList);
		this.handleChildren(parentList,requestGoodsCategorySaleVo,list);
		List<String> names = new ArrayList<>();
		names.add(userVO.getEnterpriseName());
		names.add("商品分类销售");
		//内容数据
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("name", "分类");
		map.put("realAmountTotal", "总额");
		map.put("notaxRealAmountTotal", "不含税总额");
		map.put("taxAmountTotal", "税额");
		purchaseGeneralComponent.commExcelExport(output, map, list, names, null, "", false, new ArrayList<>());
	}

	@Override
	public List<QuerySaleYVO> querySaleX(UserVO userVO, Integer saleType, String saleDate) {
		Long enterpriseId = userVO.getEnterpriseId();
		ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
		//0-总部控制  1-自主控制,若是总部控制则选择总部的销售时段否则选自己的
		if (manageConfig.getPosControl() == 0) enterpriseId = userVO.getParentId();
 			
		List<QuerySaleYVO> querySaleYVOS= posSalePeriodMapper.findByEnterpriseIdX(enterpriseId);
		for(QuerySaleYVO querySaleYVO : querySaleYVOS){
			String startTime = querySaleYVO.getStartTime();
			String endTime = querySaleYVO.getEndTime();
			String startTimeNow = "";
			String endTimeNow = "";
			if(saleDate == null){
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String nowDate = dateFormat.format( now );

				startTimeNow = nowDate  + " " +startTime + ":00";
				endTimeNow = nowDate  + " " +endTime + ":59";
			}else{
				startTimeNow = saleDate + " " +startTime + ":00";
				endTimeNow = saleDate + " " +endTime + ":59";
			}

			BigDecimal data = timeSaleReportMapper.selectQuerySaleY(userVO.getEnterpriseId(),saleType,startTimeNow,endTimeNow);
			if(data == null) {
				querySaleYVO.setData(BigDecimal.ZERO);
			} else {
				querySaleYVO.setData(data);
			}
		}

		return querySaleYVOS;
	}


	private void handleChildren(List<GoodsCategorySaleVo> parentList,RequestGoodsCategorySaleVo requestGoodsCategorySaleVo,List<GoodsCategorySaleVo> list) {
		for (GoodsCategorySaleVo goodsCategorySaleVo : parentList) {
			requestGoodsCategorySaleVo.setParentId(goodsCategorySaleVo.getId());
			List<GoodsCategorySaleVo> childrenList=timeSaleReportMapper.getParent(requestGoodsCategorySaleVo.getEnterpriseId(),goodsCategorySaleVo.getId());
			if(childrenList.isEmpty()){
				requestGoodsCategorySaleVo.setId(goodsCategorySaleVo.getId());
				GoodsCategorySaleVo vo=timeSaleReportMapper.getGoodsCategorySaleVos(requestGoodsCategorySaleVo);
				goodsCategorySaleVo.setNotaxRealAmountTotal(vo.getNotaxRealAmountTotal());
				goodsCategorySaleVo.setRealAmountTotal(vo.getRealAmountTotal());
				goodsCategorySaleVo.setTaxAmountTotal(vo.getTaxAmountTotal());
				continue;
			}
			goodsCategorySaleVo.setChildren(childrenList);
			list.addAll(childrenList);
			handleChildren(childrenList,requestGoodsCategorySaleVo,list);
		}

	}
}
