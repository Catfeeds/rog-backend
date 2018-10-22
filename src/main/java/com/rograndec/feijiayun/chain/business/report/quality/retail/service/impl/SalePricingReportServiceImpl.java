package com.rograndec.feijiayun.chain.business.report.quality.retail.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.quality.retail.service.SalePricingReportService;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionRegisterReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestPrescriptionVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSalePricingVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSpecialRegisterVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SalePricingShelfVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SpecialRegisterReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsAttributeBuild;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.PrescriptionRegisterMapper;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterDetailForDetail2ExcelVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterForDetailVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingMapper;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.SalePricingReportExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.FeeType;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttribute2DrugsA;
import com.rograndec.feijiayun.chain.common.constant.PrescriptionType;
import com.rograndec.feijiayun.chain.common.constant.SexType;
import com.rograndec.feijiayun.chain.common.constant.status.PrescriptionStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

@Service
public class SalePricingReportServiceImpl implements SalePricingReportService {
	
	@Autowired
	SalePricingMapper salePricingMapper;
	@Autowired
	SalePricingReportExcelComponent salePricingReportExcelComponent;
	@Autowired
	SpecialRegisterMapper specialRegisterMapper;
	@Autowired
	PrescriptionRegisterMapper prescriptionRegisterMapper;
	@Autowired
    private EnterpriseMapper enterpriseMapper;

	@Override
	public void getSalePricingReportList(Page<PrescriptionReportVO<SalePricingShelfVO>> page, UserVO user,
			RequestSalePricingVO requestSalePricingVO) {
		PrescriptionReportVO<SalePricingShelfVO> prescriptionReportVo=new PrescriptionReportVO<>();
		requestSalePricingVO.setEnterpriseId(user.getEnterpriseId());
		requestSalePricingVO.setStart(page.getStart());
		Integer sumNum=salePricingMapper.getSalePricingReportListTotal(requestSalePricingVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			prescriptionReportVo=salePricingMapper.getPrescriptionReportVo(requestSalePricingVO);
			if(prescriptionReportVo!=null){
				List<SalePricingShelfVO> list=salePricingMapper.getSalePricingReportList(requestSalePricingVO);
				reBuildSalePricingData(list);
				prescriptionReportVo.setDataList(list);
				page.setResult(prescriptionReportVo);
			}
		}
	}
	
	private void  reBuildSalePricingData(List<SalePricingShelfVO> list){
		for(SalePricingShelfVO nf:list){
			//设置货架的质量状态
			String shelfStatusDesc = "";
			Integer jobType = nf.getJobType();
			Integer jobArea = nf.getJobArea();
			if(jobType!=null&&jobArea!=null){
				if (0 == jobType && 0 == jobArea) {
					shelfStatusDesc = "合格";
				} else if (0 == jobType && 2 == jobArea) {
					shelfStatusDesc = "不合格";
				} else {
					shelfStatusDesc = "其它";
				}
			}
	        nf.setShelfStatusDesc(shelfStatusDesc);
			//状态
			if(nf.getStatus()!=null){
				nf.setStatusName(PurchaseStatus.getName(nf.getStatus()));
			}
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
			nf.setGoodsAttributeName(goodsAttributeName);
			//保质期
			if(nf.getQualityPeriod()!=null&&nf.getQualityPeriodUnit()!=null){
				nf.setQualityPeriodUnit(nf.getQualityPeriod()+nf.getQualityPeriodUnit());
			}else{
				nf.setQualityPeriodUnit("");
			}
			//品种类型
			if(nf.getBusinessVariety()!=null) nf.setBusinessVarietyName(BusinessVariety.getName(nf.getBusinessVariety()));
			//国产/进口（0-国产；1-进口）
			String domesticImportName="";
			if(nf.getDomesticImport()!=null){
				switch(nf.getDomesticImport()){
				case 0:domesticImportName="国产";break;
				case 1:domesticImportName="进口";break;
				}
			}
			nf.setDomesticImportName(domesticImportName);
			//特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
			String specialDrugName="";
			if(nf.getSpecialDrug()!=null){
				switch(nf.getSpecialDrug()){
				case 0:specialDrugName="精神药品";break;
				case 1:specialDrugName="麻醉药品";break;
				case 2:specialDrugName="医疗用毒性药品";break;
				case 3:specialDrugName="放射性药品";break;
				}
				if(nf.getSpecialDrug()==0&&nf.getSpiritDrugType()!=null){
					switch(nf.getSpiritDrugType()){
					case 0:specialDrugName=specialDrugName+"-一类"; break;
					case 1:specialDrugName=specialDrugName+"-二类";break;
					}
				}
			}
			nf.setSpecialDrugName(specialDrugName);
			//专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
			String inChargeDrugName="";
			if(nf.getInChargeDrug()!=null){
				switch(nf.getInChargeDrug()){
				case 0:inChargeDrugName="含特殊药品复方制剂";break;
				case 1:inChargeDrugName="蛋白同化制剂";break;
				case 2:inChargeDrugName="肽类激素";break;
				}
				if(nf.getInChargeDrug()==0&&nf.getFormulationType()!=null){
					switch(nf.getFormulationType()){
					case 0:inChargeDrugName=inChargeDrugName+"-含可卡因复方口服液"; break;
					case 1:inChargeDrugName=inChargeDrugName+"-含麻黄碱类复方制剂";break;
					case 2:inChargeDrugName=inChargeDrugName+"-复方地芬诺酯片";break;
					case 3:inChargeDrugName=inChargeDrugName+"-复方甘草片";break;
					}
				}
				
			}
			nf.setInChargeDrugName(inChargeDrugName);
			//贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
			String storageTempName="";
			if(nf.getStorageTemp()!=null){
				switch(nf.getStorageTemp()){
				case 0:storageTempName="常温";break;
				case 1:storageTempName="阴凉";break;
				case 2:storageTempName="冷藏";break;
				case 3:storageTempName="冷冻";break;
				}
			}
			nf.setStorageTempName(storageTempName);
			
		}
	}
	@Override
	public void exportSalePricingReportListExcel(OutputStream output, UserVO user,
			RequestSalePricingVO requestSalePricingVO) {
		requestSalePricingVO.setEnterpriseId(user.getEnterpriseId());
		List<SalePricingShelfVO> list=salePricingMapper.getSalePricingReportList(requestSalePricingVO);
		reBuildSalePricingData(list);
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}
		rowHeaderList.put("pricingDate", "日期");
		rowHeaderList.put("pricingCode", "单号");
		rowHeaderList.put("createrName", "划价人员");
		rowHeaderList.put("memberCardCode", "会员卡号");
		rowHeaderList.put("consumerName", "购药者姓名");
		rowHeaderList.put("saleCode", "销售单号");
		
		rowHeaderList.put("goodsCode", "商品编码");
		rowHeaderList.put("barcode", "条形码");
		rowHeaderList.put("goodsGenericName", "通用名称");
		rowHeaderList.put("goodsName", "商品名称");
		rowHeaderList.put("dosageName", "剂型");
		rowHeaderList.put("goodsSpecification", "规格");
		rowHeaderList.put("unitName", "单位");
		rowHeaderList.put("manufacturer", "生产厂商");
		rowHeaderList.put("goodsPlace", "产地");
		rowHeaderList.put("approvalNumber", "批准文号");
		rowHeaderList.put("lotNumber", "批号");
		rowHeaderList.put("productDate", "生产日期");
		rowHeaderList.put("validUntil", "有效期至");
		
		rowHeaderList.put("shelfName", "货位");
		rowHeaderList.put("shelfStatusDesc", "质量状况");
		
		rowHeaderList.put("dose", "剂量");
		rowHeaderList.put("singleDose", "单剂数量");
		rowHeaderList.put("totalQuantity", "总数量");
		
		rowHeaderList.put("unitPrice", "单价");
		rowHeaderList.put("lineDiscount", "行折扣");
		rowHeaderList.put("amount", "金额");
		rowHeaderList.put("wholeDiscount", "整单折扣");
		rowHeaderList.put("lineDiscountAmount", "整单分摊");
		rowHeaderList.put("realPrice", "实际单价");
		rowHeaderList.put("realAmount", "实际金额");
		rowHeaderList.put("taxRate", "税率");
		rowHeaderList.put("notaxRealPrice", "不含税单价");
		rowHeaderList.put("notaxRealAmount", "不含税金额");
		/*rowHeaderList.put("taxAmount", "税额");*/
	
		rowHeaderList.put("statusName", "状态");
		rowHeaderList.put("businessVarietyName", "品种类型");
		rowHeaderList.put("categoryName", "商品分类");
		rowHeaderList.put("goodsAttributeName", "商品属性");
		rowHeaderList.put("domesticImportName", "国产/进口");
		rowHeaderList.put("managementScopeName", "经营范围");
		rowHeaderList.put("specialDrugName", "特殊管理药品");
		rowHeaderList.put("inChargeDrugName", "专管药品");
		rowHeaderList.put("limitQuantity", "限购数量");
		rowHeaderList.put("storageTempName", "贮藏温度");
		rowHeaderList.put("storageConditionName", "贮藏条件");
		rowHeaderList.put("qualityPeriodUnit", "保质期");
		
		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal totalQuantity=BigDecimal.ZERO;
		BigDecimal amount=BigDecimal.ZERO;
		BigDecimal realAmount=BigDecimal.ZERO;
		BigDecimal notaxRealAmount=BigDecimal.ZERO;
		for(SalePricingShelfVO d: list){
			totalQuantity=d.getTotalQuantity().add(totalQuantity);
			amount=d.getAmount().add(amount);
			realAmount=d.getRealAmount().add(realAmount);
			notaxRealAmount=d.getNotaxRealAmount().add(notaxRealAmount);
		}
		map.put("totalQuantity",totalQuantity.setScale(6));
		map.put("amount",amount.setScale(2));
		map.put("realAmount",realAmount.setScale(2));
		map.put("notaxRealAmount",notaxRealAmount.setScale(2));
		
		salePricingReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map,"划价单");
	}
	@Override
	public void getSpecialRegisterReportList(Page<PrescriptionReportVO<SpecialRegisterReportVO>> page, UserVO user,
			RequestSpecialRegisterVO requestSpecialRegisterVO) {
		requestSpecialRegisterVO.setStart(page.getStart());
		requestSpecialRegisterVO.setEnterpriseId(user.getEnterpriseId());
		Integer sumNum=specialRegisterMapper.getSpecialRegisterReportListTotalNum(requestSpecialRegisterVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			PrescriptionReportVO<SpecialRegisterReportVO> prescriptionReportVo=specialRegisterMapper.getPrescriptionReportVo(requestSpecialRegisterVO);
			if(prescriptionReportVo!=null){
				List<SpecialRegisterReportVO> list=specialRegisterMapper.getSpecialRegisterReportList(requestSpecialRegisterVO);
				reBuildSpecialRegisterData(list);
				prescriptionReportVo.setDataList(list);
				page.setResult(prescriptionReportVo);
			}
		}
	}
	@Override
	public void exportSpecialRegisterReportListExcel(OutputStream output, UserVO user,
			RequestSpecialRegisterVO requestSpecialRegisterVO) {
		requestSpecialRegisterVO.setEnterpriseId(user.getEnterpriseId());
		List<SpecialRegisterReportVO> list=specialRegisterMapper.getSpecialRegisterReportList(requestSpecialRegisterVO);
		reBuildSpecialRegisterData(list);
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();


		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}
		rowHeaderList.put("registerDate", "日期");
		rowHeaderList.put("registerCode", "单号");
		rowHeaderList.put("registerManName", "登记人员");
		rowHeaderList.put("memberCardCode", "会员卡号");
		rowHeaderList.put("consumerName", "购药者姓名");
		rowHeaderList.put("sexName", "性别");
		rowHeaderList.put("age", "年龄");
		rowHeaderList.put("idNum", "身份证号");
		
		rowHeaderList.put("saleCode", "销售单号");
		
		rowHeaderList.put("goodsCode", "商品编码");
		rowHeaderList.put("barcode", "条形码");
		rowHeaderList.put("goodsGenericName", "通用名称");
		rowHeaderList.put("goodsName", "商品名称");
		rowHeaderList.put("dosageName", "剂型");
		rowHeaderList.put("goodsSpecification", "规格");
		rowHeaderList.put("unitName", "单位");
		rowHeaderList.put("manufacturer", "生产厂商");
		rowHeaderList.put("goodsPlace", "产地");
		rowHeaderList.put("approvalNumber", "批准文号");
		rowHeaderList.put("lotNumber", "批号");
		rowHeaderList.put("productDate", "生产日期");
		rowHeaderList.put("validUntil", "有效期至");
		
		rowHeaderList.put("shelfName", "货位");
		rowHeaderList.put("shelfStatusDesc", "质量状况");
		
		rowHeaderList.put("quantity", "数量");
		
		rowHeaderList.put("unitPrice", "单价");
		rowHeaderList.put("lineDiscount", "行折扣");
		rowHeaderList.put("amount", "金额");
		rowHeaderList.put("wholeDiscount", "整单折扣");
		rowHeaderList.put("lineDiscountAmount", "整单分摊");
		rowHeaderList.put("realPrice", "实际单价");
		rowHeaderList.put("realAmount", "实际金额");
		rowHeaderList.put("taxRate", "税率");
		rowHeaderList.put("notaxRealPrice", "不含税单价");
		rowHeaderList.put("notaxRealAmount", "不含税金额");
		/*rowHeaderList.put("taxAmount", "税额");*/
	
		rowHeaderList.put("statusName", "状态");
		rowHeaderList.put("businessVarietyName", "品种类型");
		rowHeaderList.put("categoryName", "商品分类");
		rowHeaderList.put("goodsAttributeName", "商品属性");
		rowHeaderList.put("domesticImportName", "国产/进口");
		rowHeaderList.put("managementScopeName", "经营范围");
		rowHeaderList.put("specialDrugName", "特殊管理药品");
		rowHeaderList.put("inChargeDrugName", "专管药品");
		rowHeaderList.put("limitQuantity", "限购数量");
		rowHeaderList.put("storageTempName", "贮藏温度");
		rowHeaderList.put("storageConditionName", "贮藏条件");
		rowHeaderList.put("qualityPeriodUnit", "保质期");
		
		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal quantity=BigDecimal.ZERO;
		BigDecimal amount=BigDecimal.ZERO;
		BigDecimal realAmount=BigDecimal.ZERO;
		BigDecimal notaxRealAmount=BigDecimal.ZERO;
		for(SpecialRegisterReportVO d: list){
			quantity=d.getQuantity().add(quantity);
			amount=d.getAmount().add(amount);
			realAmount=d.getRealAmount().add(realAmount);
			notaxRealAmount=d.getNotaxRealAmount().add(notaxRealAmount);
		}
		map.put("quantity",quantity.setScale(6));
		map.put("amount",amount.setScale(2));
		map.put("realAmount",realAmount.setScale(2));
		map.put("notaxRealAmount",notaxRealAmount.setScale(2));
		
		salePricingReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map,"专管登记");
	}
	
	private void  reBuildSpecialRegisterData(List<SpecialRegisterReportVO> list){
		for(SpecialRegisterReportVO nf:list){
			//设置性别
			String sexName="";
			if(nf.getSex()!=null){
				switch(nf.getSex()){
				case 0:sexName="男";break;
				case 1:sexName="女";break;
			}
			nf.setSexName(sexName);
			//设置货架的质量状态
			String shelfStatusDesc = "";
			Integer jobType = nf.getJobType();
			Integer jobArea = nf.getJobArea();
			if(jobType!=null&&jobArea!=null){
				if (0 == jobType && 0 == jobArea) {
					shelfStatusDesc = "合格";
				} else if (0 == jobType && 2 == jobArea) {
					shelfStatusDesc = "不合格";
				} else {
					shelfStatusDesc = "其它";
				}
			}
	        nf.setShelfStatusDesc(shelfStatusDesc);
			//状态
			if(nf.getStatus()!=null){
				nf.setStatusName(PurchaseStatus.getName(nf.getStatus()));
			}
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
			//保质期  保质期单位（0-日；1-月；2-年）
			if(nf.getQualityPeriod() != null && nf.getQualityPeriodUnit() != null){
				switch(nf.getQualityPeriodUnit()){
				case "0":nf.setQualityPeriodUnit(nf.getQualityPeriod()+"日");break;
				case "1":nf.setQualityPeriodUnit(nf.getQualityPeriod()+"月");break;
				case "2":nf.setQualityPeriodUnit(nf.getQualityPeriod()+"年");break;
				default:nf.setQualityPeriodUnit("");
				}
			}else{
				nf.setQualityPeriodUnit("");
			}	
			//品种类型
			if(nf.getBusinessVariety()!=null) nf.setBusinessVarietyName(BusinessVariety.getName(nf.getBusinessVariety()));
			//国产/进口（0-国产；1-进口）
			String domesticImportName="";
			if(nf.getDomesticImport()!=null){
				switch(nf.getDomesticImport()){
				case 0:domesticImportName="国产";break;
				case 1:domesticImportName="进口";break;
				}
			}
			nf.setDomesticImportName(domesticImportName);
			//特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
			String specialDrugName="";
			if(nf.getSpecialDrug()!=null){
				switch(nf.getSpecialDrug()){
				case 0:specialDrugName="精神药品";break;
				case 1:specialDrugName="麻醉药品";break;
				case 2:specialDrugName="医疗用毒性药品";break;
				case 3:specialDrugName="放射性药品";break;
				}
				if(nf.getSpecialDrug()==0&&nf.getSpiritDrugType()!=null){
					switch(nf.getSpiritDrugType()){
					case 0:specialDrugName=specialDrugName+"-一类"; break;
					case 1:specialDrugName=specialDrugName+"-二类";break;
					}
				}
			}
			nf.setSpecialDrugName(specialDrugName);
			//专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
			String inChargeDrugName="";
			if(nf.getInChargeDrug()!=null){
				switch(nf.getInChargeDrug()){
				case 0:inChargeDrugName="含特殊药品复方制剂";break;
				case 1:inChargeDrugName="蛋白同化制剂";break;
				case 2:inChargeDrugName="肽类激素";break;
				}
				if(nf.getInChargeDrug()==0&&nf.getFormulationType()!=null){
					switch(nf.getFormulationType()){
					case 0:inChargeDrugName=inChargeDrugName+"-含可卡因复方口服液"; break;
					case 1:inChargeDrugName=inChargeDrugName+"-含麻黄碱类复方制剂";break;
					case 2:inChargeDrugName=inChargeDrugName+"-复方地芬诺酯片";break;
					case 3:inChargeDrugName=inChargeDrugName+"-复方甘草片";break;
					}
				}
				
			}
			nf.setInChargeDrugName(inChargeDrugName);
			//贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
			String storageTempName="";
			if(nf.getStorageTemp()!=null){
				switch(nf.getStorageTemp()){
				case 0:storageTempName="常温";break;
				case 1:storageTempName="阴凉";break;
				case 2:storageTempName="冷藏";break;
				case 3:storageTempName="冷冻";break;
				}
			}
			nf.setStorageTempName(storageTempName);
			
		}
	  }

	}

	@Override
	public void getPrescriptionList(Page<PrescriptionReportVO> page, UserVO user,
			RequestPrescriptionVO requestPrescriptionVO) {
		PrescriptionReportVO prescriptionReportVo=new PrescriptionReportVO();
		requestPrescriptionVO.setEnterpriseId(user.getEnterpriseId());
		requestPrescriptionVO.setStart(page.getStart());
		Date BillingDate= requestPrescriptionVO.getBillingDate();
		if(BillingDate!=null){
			requestPrescriptionVO.setStartBillingDate(DateUtils.DateToString(BillingDate, DateUtils.FMT_DATE)+" 00:00:00");
			requestPrescriptionVO.setEndBillingDate(DateUtils.DateToString(BillingDate, DateUtils.FMT_DATE)+" 23:59:59");
		}
		Integer sumNum=prescriptionRegisterMapper.getPrescriptionListReportTotalNum(requestPrescriptionVO);
		if(sumNum!=null && sumNum!=0){
			prescriptionReportVo=prescriptionRegisterMapper.getPrescriptionReportVo(requestPrescriptionVO);
			if(prescriptionReportVo!=null){
				List<PrescriptionRegisterReportVO> list=prescriptionRegisterMapper.getPrescriptionListReport(requestPrescriptionVO);
				for(PrescriptionRegisterReportVO v:list){
					if(v.getStatus()!=null){
						v.setStatusName(PrescriptionStatus.getStatusDesc(v.getStatus()));
					}
				}
				prescriptionReportVo.setDataList(list);
				page.setResult(prescriptionReportVo);
			}
		}
		page.setTotalRecord(sumNum);
	}

	@Override
	public void exportPrescriptionListExcel(OutputStream output, UserVO user,
			RequestPrescriptionVO requestPrescriptionVO) {
		requestPrescriptionVO.setEnterpriseId(user.getEnterpriseId());
		Date BillingDate= requestPrescriptionVO.getBillingDate();
		if(BillingDate!=null){
			requestPrescriptionVO.setStartBillingDate(DateUtils.DateToString(BillingDate, DateUtils.FMT_DATE)+" 00:00:00");
			requestPrescriptionVO.setEndBillingDate(DateUtils.DateToString(BillingDate, DateUtils.FMT_DATE)+" 23:59:59");
		}
		List<PrescriptionRegisterReportVO> list=prescriptionRegisterMapper.getPrescriptionListReport(requestPrescriptionVO);
		for(PrescriptionRegisterReportVO v:list){
			if(v.getStatus()!=null){
				v.setStatusName(PrescriptionStatus.getStatusDesc(v.getStatus()));
			}
		}
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}

		rowHeaderList.put("registerDate", "登记日期");
		rowHeaderList.put("code", "登记单号");
		rowHeaderList.put("registerManName", "登记人员");
		rowHeaderList.put("billingDate", "开具日期");
		rowHeaderList.put("prescriptionCode", "处方单号");
		rowHeaderList.put("name", "患者姓名");
		rowHeaderList.put("baseOrderCode", "销售单号");
		rowHeaderList.put("realAmountTotal", "金额");
		rowHeaderList.put("notaxRealAmountTotal", "不含税金额");
		rowHeaderList.put("taxAmountTotal", "税额");
		
		rowHeaderList.put("auditorName", "审核人员");
		rowHeaderList.put("swapManName", "调配人员");
		rowHeaderList.put("checkerName", "核对人员");
		rowHeaderList.put("sendName", "发药人员");
		rowHeaderList.put("statusName", "状态");
		
		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal realAmountTotal=BigDecimal.ZERO;
		BigDecimal notaxRealAmountTotal=BigDecimal.ZERO;
		BigDecimal taxAmountTotal=BigDecimal.ZERO;
		for(PrescriptionRegisterReportVO d: list){
			realAmountTotal=d.getRealAmountTotal().add(realAmountTotal);
			notaxRealAmountTotal=d.getNotaxRealAmountTotal().add(notaxRealAmountTotal);
			taxAmountTotal=d.getTaxAmountTotal().add(taxAmountTotal);
		}
		map.put("realAmountTotal",realAmountTotal.setScale(6));
		map.put("notaxRealAmountTotal",notaxRealAmountTotal.setScale(2));
		map.put("taxAmountTotal",taxAmountTotal.setScale(2));
		
		salePricingReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map,"处方登记");
	}
	
	
	@Override
	public PrescriptionRegister getPrescriptionRegisterById(Long id) {
		return prescriptionRegisterMapper.getPrescriptionRegisterById(id);
	}

	@Override
	public ResponsePrescriptionRegisterForDetailVO getDetailById(Long id, Long enterpriseId) {
		 return prescriptionRegisterMapper.getRegisterDetailReportById(id,enterpriseId);
	}

	/**
     * 导出
     * @param output
     * @param enterpriseId
     * @param id
     * @throws Exception
     */
    @Override
    public void exportPrescriptionRecord(OutputStream output, Long enterpriseId, Long id) throws Exception {

    	PrescriptionRegister registerDetail = prescriptionRegisterMapper.selectByPrimaryKey(id);
    	Long enterpriseIdnew=registerDetail.getEnterpriseId();
    	
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseIdnew);//企业


        //处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
        Integer prescriptionType = registerDetail.getPrescriptionType();
        //（状态（21-待审核 31-待调配 32-待收款 33-已完成 34-已取消）
        Integer status = registerDetail.getStatus();


        List<ResponsePrescriptionRegisterDetailForDetail2ExcelVO> registerDetailForDetailVOList = prescriptionRegisterMapper.getPrescriptionRecordListReportExcel(id, enterpriseId);


        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {
                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, enterprise.getName(),styleMap.get("cell_string").getIndex());
                this.endRow();
                //第二行
                this.insertRow(kk++);
                if(status == PrescriptionStatus.PENDING_AUDIT){
                    this.createCell(0, "处方待审核");
                } else if(status == PrescriptionStatus.WAIT_MIX || status == PrescriptionStatus.CANCELED){
                    this.createCell(0, "处方待调剂");
                } else if(status == PrescriptionStatus.MIXED || status == PrescriptionStatus.FINISHED){
                    this.createCell(0, "处方已调剂");
                }

                this.endRow();
                //第三行
            	this.insertRow(kk++);
            	this.createCell(0, "登记单号：" + Optional.ofNullable(registerDetail.getCode()).orElse(""));
            	this.createCell(2, "登记日期：" + DateUtils.DateToString(registerDetail.getRegisterDate(),DateStyle.YYYY_MM_DD));
            	
            	this.createCell(4, "登记人员：" + Optional.ofNullable(registerDetail.getRegisterManName()).orElse(""));
            	this.createCell(6, "处方类型：" + PrescriptionType.getName(registerDetail.getPrescriptionType()));
            	
            	this.createCell(8, "处方单号：" + registerDetail.getPrescriptionCode());
            	String billingDate=DateUtils.DateToString(registerDetail.getBillingDate(),DateStyle.YYYY_MM_DD);
            	this.createCell(10, "开具日期：" + (billingDate==null?"":billingDate));
            	
            	this.createCell(12, "医疗机构编码：" + Optional.ofNullable(registerDetail.getMedicalOrgCode()).orElse(""));
            	this.createCell(14, "医疗机构名称：" + Optional.ofNullable(registerDetail.getMedicalOrgName()).orElse(""));
            	
            	this.createCell(16, "费别：" + FeeType.getName(registerDetail.getFeeType()));
            	this.createCell(18, "门诊/病历号：" + Optional.ofNullable(registerDetail.getOutpatientCaseNum()).orElse(""));
            	this.createCell(20, "科室：" + Optional.ofNullable(registerDetail.getSectionRoom()).orElse(""));
            	this.endRow();
               
            	//第四行
            	this.insertRow(kk++);
            	this.createCell(0, "病人标识：" + Optional.ofNullable(registerDetail.getPatientId()).orElse(""));
            	
            	this.createCell(2, "姓名：" + Optional.ofNullable(registerDetail.getName()).orElse(""));
            	this.createCell(4, "性别：" + SexType.getSexType4Code(registerDetail.getSex()).getName());
            	
            	this.createCell(6, "年龄：" + "");
            	this.createCell(8, "身份证号：" + Optional.ofNullable(registerDetail.getIdNum()).orElse(""));
            	
            	String height=""+(registerDetail.getHeight()==null?"":registerDetail.getHeight());
            	String weight=""+(registerDetail.getWeight()==null?"":registerDetail.getWeight());
            	this.createCell(10, "身高：" + height);
            	this.createCell(12, "体重：" + weight);
            	
            	this.createCell(14, "过敏试验：" + Optional.ofNullable(registerDetail.getAllergyTest()).orElse(""));
            	this.createCell(16, "医师：" + Optional.ofNullable(registerDetail.getPhysician()).orElse(""));
            	
            	this.createCell(18, "医嘱：" + Optional.ofNullable(registerDetail.getDoctorAdvice()).orElse(""));
            	this.endRow();
            	
                if(prescriptionType == PrescriptionType.TCM.getCode()){
                    //中药
                    this.insertRow(kk++);
                    this.createCell(0, "每日剂量：" + (registerDetail.getDayDose() == null?"": registerDetail.getDayDose()));
                    this.createCell(2, "用法：" + Optional.ofNullable(registerDetail.getUsageName()).orElse(""));

                    this.createCell(4, "用量：" + Optional.ofNullable(registerDetail.getUseQtyName()).orElse(""));
                    this.createCell(6, "注意事项：" + Optional.ofNullable(registerDetail.getAttentionMatterName()).orElse(""));

                    this.createCell(8, "剂量：" + (registerDetail.getDose() == null?"":registerDetail.getDose()));

                    this.endRow();
                }
                if(status == PrescriptionStatus.WAIT_MIX || status == PrescriptionStatus.CANCELED){
                    //待调配
                    this.insertRow(kk++);
                    String audditTime = DateUtils.DateToString( registerDetail.getAuditTime(),DateStyle.YYYY_MM_DD_HH_MM_SS);
                    this.createCell(0, "审核时间：" +(audditTime == null?"":audditTime));
                    this.createCell(2, "审核人员：" + Optional.ofNullable(registerDetail.getAuditorName()).orElse(""));

                    this.createCell(4, "审核意见：" + Optional.ofNullable(registerDetail.getAuditOpinion()).orElse(""));
                    if(status == PrescriptionStatus.WAIT_MIX) {
                	   this.createCell(6, "审核结果：" + "同意");
                    }else {
                    	 this.createCell(6, "审核结果：" + "已取消");
                    }
                    this.endRow();

                } else if(status == PrescriptionStatus.MIXED || status == PrescriptionStatus.FINISHED){
                    //已调配

                    this.insertRow(kk++);
                    String audditTime = DateUtils.DateToString( registerDetail.getAuditTime(),DateStyle.YYYY_MM_DD_HH_MM_SS);
                    this.createCell(0, "审核时间：" + (audditTime == null?"":audditTime));
                    this.createCell(2, "审核人员：" + Optional.ofNullable(registerDetail.getAuditorName()).orElse(""));

                    this.createCell(4, "审核意见：" + Optional.ofNullable(registerDetail.getAuditOpinion()).orElse(""));
                    this.createCell(6, "审核结果：" + "同意");

                    String swapTime =  DateUtils.DateToString(registerDetail.getSwapTime(),DateStyle.YYYY_MM_DD_HH_MM_SS);
                    this.createCell(8, "调配时间：" +(swapTime == null?"":swapTime));
                    this.createCell(10, "调配人员：" + Optional.ofNullable(registerDetail.getSwapManName()).orElse(""));

                    this.createCell(12, "核对人员：" + Optional.ofNullable(registerDetail.getCheckerName()).orElse(""));
                    this.createCell(14, "发药人员：" + Optional.ofNullable(registerDetail.getSendName()).orElse(""));
                    this.endRow();

                }


                createRowHeader(this,kk++,prescriptionType);
                for (int rowNum = 0; rowNum < registerDetailForDetailVOList.size(); rowNum++) {
                    ResponsePrescriptionRegisterDetailForDetail2ExcelVO detailVO = registerDetailForDetailVOList.get(rowNum);
                    // 插入新行
                    this.insertRow(rowNum + kk);
                    // 建立新单元格,索引值从0开始,表示第一列
                    int k = 0;
                    this.createCell(k++, k);
                    this.createCell(k++, detailVO.getGoodsCode());
                    this.createCell(k++, detailVO.getGoodsGenericName());
                    this.createCell(k++, detailVO.getDosageName());
                    this.createCell(k++, detailVO.getGoodsSpecification());
                    this.createCell(k++, detailVO.getManufacturer());
                    this.createCell(k++, detailVO.getGoodsPlace());
                    this.createCell(k++, detailVO.getUnitName());
                    this.createCell(k++, detailVO.getLotNumber());
                    this.createCell(k++, DateUtils.DateToString(detailVO.getProductDate(), DateStyle.YYYY_MM_DD));
                    this.createCell(k++, DateUtils.DateToString(detailVO.getValidDate(), DateStyle.YYYY_MM_DD));
                    this.createCell(k++, detailVO.getShelfName());
                    if(prescriptionType == PrescriptionType.TCM.getCode()){
                        this.createCell(k++, detailVO.getSingleDose() + "");
                    }
                    this.createCell(k++, detailVO.getQuantity() + "");
                    this.createCell(k++, detailVO.getUnitPrice() + "");
                    this.createCell(k++, detailVO.getRealAmount() + "");
                    this.createCell(k++, detailVO.getTaxRate() + "");
                    this.createCell(k++, detailVO.getNotaxRealPrice() + "");
                    this.createCell(k++, detailVO.getNotaxRealAmount() + "");
                    this.createCell(k++, detailVO.getTaxAmount() + "");
                    if(prescriptionType != PrescriptionType.TCM.getCode()){
                        this.createCell(k++, detailVO.getUsageName()==null?"":detailVO.getUsageName());
                        this.createCell(k++, detailVO.getUseQtyName()==null?"":detailVO.getUseQtyName());
                        this.createCell(k++, detailVO.getTimeDoseName()==null?"":detailVO.getTimeDoseName());
                    }

                    this.createCell(k, detailVO.getRemark()==null?"":detailVO.getRemark());

                    // 结束行
                    this.endRow();


                }

                // 电子表格结束
                this.endSheet();

                //合并单元格
                this.beginMergerCell();
                if(prescriptionType == PrescriptionType.TCM.getCode()) {
                	this.setMergeCell(0, 0, 0, 23);
                }else {
                	this.setMergeCell(0, 0, 0, 20);
                }
                if(prescriptionType == PrescriptionType.TCM.getCode()) {
                	this.setMergeCell(1, 0, 1, 23);
                }else {
                	this.setMergeCell(1, 0, 1, 20);
                }
                this.endMergerCell();
                this.endWorkSheet();


            }
        };
        writer.process(output);

    }
    
    private void createRowHeader(ExcelWriter writer,int kk,Integer prescriptionType) throws IOException {
        // 插入新行
        writer.insertRow(kk);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "序号");
        writer.createCell(k++, "商品编码");
        writer.createCell(k++, "通用名称");
        writer.createCell(k++, "剂型");
        writer.createCell(k++, "规格");
        writer.createCell(k++, "生产厂商");
        writer.createCell(k++, "产地");
        writer.createCell(k++, "单位");
        writer.createCell(k++, "批号");
        writer.createCell(k++, "生产日期");
        writer.createCell(k++, "有效期至");
        writer.createCell(k++, "货位");
        if(prescriptionType == PrescriptionType.TCM.getCode()){
        	writer.createCell(k++, "单剂数量");
        }
        writer.createCell(k++, "数量");
        writer.createCell(k++, "单价");
        writer.createCell(k++, "金额");
        writer.createCell(k++, "税率");
        writer.createCell(k++, "不含税单价");
        writer.createCell(k++, "不含税金额");
        writer.createCell(k++, "税额");
        if(prescriptionType != PrescriptionType.TCM.getCode()){
        	writer.createCell(k++, "用法");
        	writer.createCell(k++, "用量");
        	writer.createCell(k++, "单次剂量");
        }
        writer.createCell(k, "备注");

        // 结束行
        writer.endRow();
    }
}
