package com.rograndec.feijiayun.chain.business.report.quality.distr.service.impl;

import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnCheckMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnReceiveMapper;
import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrReturnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsAttributeBuild;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.DistrReturnCheckReportExcelComponent;
import com.rograndec.feijiayun.chain.common.component.DistrReturnInReportExcelComponent;
import com.rograndec.feijiayun.chain.common.component.DistrReturnNoticeReportExcelComponent;
import com.rograndec.feijiayun.chain.common.component.DistrReturnReceiveReportExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
public class DistrReturnReportServiceImpl implements DistrReturnReportService {
	
	@Autowired
	DistrReturnNoticeMapper distrReturnNoticeMapper;
	@Autowired
	DistrReturnReceiveMapper distrReturnReceiveMapper;
	
	@Autowired
	private DistrReturnCheckMapper distrReturnCheckMapper;
	@Autowired
	private DistrReturnInMapper distrReturnInMapper;
	
	@Autowired
	private QualitySetMapper qualitySetMapper;
	
	@Autowired
	DistrReturnNoticeReportExcelComponent<DistrReturnNoticeReportVO> distrReturnNoticeReportExcelComponent;
	@Autowired
	DistrReturnReceiveReportExcelComponent<DistrReturnReceiveReportVO> distrReturnReceiveReportExcelComponent;
	@Autowired
	DistrReturnCheckReportExcelComponent<DistrReturnCheckReportVO> distrReturnCheckReportExcelComponent;
	@Autowired
	DistrReturnInReportExcelComponent<DistrRetuenInReportVO> distrReturnInReportExcelComponent;

	@Override
	public void getDistrReturnNoticeReport(Page<DistrReportVo<DistrReturnNoticeReportVO>> page, UserVO user,
			RequestDistrReturnNoticeVO requestDistrReturnNoticeVO) {
		requestDistrReturnNoticeVO.setEnterpriseId(user.getEnterpriseId());
		requestDistrReturnNoticeVO.setStart(page.getStart());
		Integer sumNum=distrReturnNoticeMapper.getDistrReturnNoticeReportTotalNum(requestDistrReturnNoticeVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			DistrReportVo<DistrReturnNoticeReportVO> distrReportVo=distrReturnNoticeMapper.getDistrReportVo(requestDistrReturnNoticeVO);
			if(distrReportVo!=null){
				List<DistrReturnNoticeReportVO> list=distrReturnNoticeMapper.getDistrReturnNoticeReport(requestDistrReturnNoticeVO);
				reBuildData(list);
				distrReportVo.setDataList(list);
			}
			page.setResult(distrReportVo);
		}else{
			page.setTotalRecord(0);
			DistrReportVo<DistrReturnNoticeReportVO> distrReportVo=new DistrReportVo<DistrReturnNoticeReportVO>();
			distrReportVo.setDataList(new ArrayList<DistrReturnNoticeReportVO>());
			page.setResult(distrReportVo);
		}
	}
	
	//处理返回值显示，商品属性,保质期,状态,品种类型,国产/进口,特殊管理药品,专管药品
	private void  reBuildData(List<DistrReturnNoticeReportVO> list){
		for(DistrReturnNoticeReportVO nf:list){
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
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
			//状态
			nf.setStatusName(DistrReturnStatus.getStatusDesc(nf.getStatus()));
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
			//设置配送类型
			if(nf.getDistrType() != null) {
				nf.setDistrTypeName(DistributionType.getName(nf.getDistrType()));
			}
		}
	}

	@Override
	public void exportDistrReturnNoticeReportListExcel(OutputStream output, UserVO user,
			RequestDistrReturnNoticeVO requestDistrReturnNoticeVO) {
		requestDistrReturnNoticeVO.setEnterpriseId(user.getEnterpriseId());
		List<DistrReturnNoticeReportVO> list=distrReturnNoticeMapper.getDistrReturnNoticeReport(requestDistrReturnNoticeVO);
		reBuildData(list);
		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal quantity=BigDecimal.ZERO;
		BigDecimal amount=BigDecimal.ZERO;
		BigDecimal realAmount=BigDecimal.ZERO;
		BigDecimal notaxRealAmount=BigDecimal.ZERO;
		BigDecimal taxAmount=BigDecimal.ZERO;
		for(DistrReturnNoticeReportVO d: list){
			quantity=d.getQuantity().add(quantity);
			amount=d.getAmount().add(amount);
			realAmount=d.getRealAmount().add(realAmount);
			notaxRealAmount=d.getNotaxRealAmount().add(notaxRealAmount);
			taxAmount=d.getTaxAmount().add(taxAmount);
		}
		map.put("quantity",quantity.setScale(6));
		map.put("amount",amount.setScale(2));
		map.put("realAmount",realAmount.setScale(2));
		map.put("notaxRealAmount",notaxRealAmount.setScale(2));
		map.put("taxAmount",taxAmount.setScale(2));
		
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		rowHeaderList.put("noticeDate", "日期");
		rowHeaderList.put("code", "单号");
		rowHeaderList.put("requestUnitCode", "要货单位编码");
		rowHeaderList.put("requestUnitName", "要货单位名称");
		rowHeaderList.put("distrTypeName", "配货类型");
		
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
		
		rowHeaderList.put("quantity", "数量");
		rowHeaderList.put("unitPrice", "单价");
		rowHeaderList.put("lineDiscount", "折扣");
		rowHeaderList.put("amount", "金额");
		rowHeaderList.put("wholeDiscount", "整单折扣");
		rowHeaderList.put("lineDiscountAmount", "优惠分摊");
		rowHeaderList.put("realPrice", "实际单价");
		rowHeaderList.put("realAmount", "实际金额");
		rowHeaderList.put("taxRate", "税率");
		rowHeaderList.put("notaxRealPrice", "不含税单价");
		rowHeaderList.put("notaxRealAmount", "不含税金额");
		rowHeaderList.put("taxAmount", "税额");
	
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
		distrReturnNoticeReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map);
	}

	@Override
	public void getDistrReturnReceiveReport(Page<DistrReportVo<DistrReturnReceiveReportVO>> page, UserVO user,
			RequestDistrReturnReceiveVO requestDistrReturnReceiveVO) {
		requestDistrReturnReceiveVO.setStart(page.getStart());
		requestDistrReturnReceiveVO.setEnterpriseId(user.getEnterpriseId());
		Integer sumNum=distrReturnReceiveMapper.getDistrReturnReceiveReportTotalNum(requestDistrReturnReceiveVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			DistrReportVo<DistrReturnReceiveReportVO> DistrReportVo=distrReturnReceiveMapper.getDistrReportVo(requestDistrReturnReceiveVO);
			if(DistrReportVo!=null){
				List<DistrReturnReceiveReportVO>  list=distrReturnReceiveMapper.getDistrReturnReceiveReport(requestDistrReturnReceiveVO);
				if(!list.isEmpty()){
					reBuildData1(list);
					List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(user.getEnterpriseId(), 0, null, EnableStatus.ENABLE.getStatus());
					setRefuseReason(list, qualitySetVOList);
				}
				DistrReportVo.setDataList(list);
			}
			page.setResult(DistrReportVo);
		}else{
			page.setTotalRecord(0);
			DistrReportVo<DistrReturnReceiveReportVO> DistrReportVo=new DistrReportVo<DistrReturnReceiveReportVO>();
			DistrReportVo.setDataList(new ArrayList<DistrReturnReceiveReportVO>());
			page.setResult(DistrReportVo);
		}
	}

	@Override
	public void exportDistrReturnReceiveReportListExcel(OutputStream output, UserVO user,
			RequestDistrReturnReceiveVO requestDistrReturnReceiveVO) {
		requestDistrReturnReceiveVO.setEnterpriseId(user.getEnterpriseId());
		List<DistrReturnReceiveReportVO>  list=distrReturnReceiveMapper.getDistrReturnReceiveReport(requestDistrReturnReceiveVO);
		if(!list.isEmpty()){
			reBuildData1(list);
			List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(user.getEnterpriseId(), 0, null, EnableStatus.ENABLE.getStatus());
			setRefuseReason(list, qualitySetVOList);
		}

		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal quantity=BigDecimal.ZERO;
		BigDecimal arrivalQuantity=BigDecimal.ZERO;
		BigDecimal receiveQuantity=BigDecimal.ZERO;
		BigDecimal refuseQuantity=BigDecimal.ZERO;
		for(DistrReturnReceiveReportVO d: list){
			quantity=d.getQuantity().add(quantity);
			arrivalQuantity=d.getArrivalQuantity().add(arrivalQuantity);
			receiveQuantity=d.getReceiveQuantity().add(receiveQuantity);
			refuseQuantity=d.getRefuseQuantity().add(refuseQuantity);
		}
		map.put("quantity", quantity.setScale(6));
		map.put("arrivalQuantity",arrivalQuantity.setScale(6));
		map.put("receiveQuantity",receiveQuantity.setScale(6));
		map.put("refuseQuantity",refuseQuantity.setScale(6));
		
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		rowHeaderList.put("receiveDate", "日期");
		rowHeaderList.put("code", "单号");
		rowHeaderList.put("requestUnitCode", "要货单位编码");
		rowHeaderList.put("requestUnitName", "要货单位名称");
		rowHeaderList.put("receiverName", "收货人员");
		rowHeaderList.put("secondReceiverName", "收货人员2");
		rowHeaderList.put("baseOrderCode", "配后退回单号");
		
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
		
		rowHeaderList.put("quantity", "配后退回数量");
		rowHeaderList.put("arrivalQuantity", "到货数量");
		rowHeaderList.put("receiveQuantity", "收货数量");
		rowHeaderList.put("refuseQuantity", "拒收数量");
		rowHeaderList.put("refuseReason", "拒收原因");
	
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
		distrReturnReceiveReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map);
	}
	
	//设置拒收原因
	private void setRefuseReason(List<DistrReturnReceiveReportVO>  list,List<QualitySetVO> qualitySetVOList ){
		for(DistrReturnReceiveReportVO rc:list){
			String refuseReasonIds=rc.getRefuseReasonIds();
			String refuseReasons = "";
	        List<QualitySetVO> qualitySetVOS = qualitySetVOList;
	        if (refuseReasonIds == null || "".equals(refuseReasonIds))
	            refuseReasons =  null;
	        else if (!refuseReasonIds.contains(",")) {
	            for (QualitySetVO qualitySetVO : qualitySetVOS) {
	                if (qualitySetVO.getId().toString().equals(refuseReasonIds))
	                    refuseReasons = qualitySetVO.getDescription();
	            }
	        } else {
	            String[] ids = refuseReasonIds.split(",");
	            StringBuilder result = new StringBuilder();
	            for (int i = 0; i < ids.length; i++) {
	                for (QualitySetVO qualitySetVO : qualitySetVOS) {
	                    if (qualitySetVO.getId().toString().equals(ids[i])) {
	                        result.append(qualitySetVO.getDescription()).append(",");
	                    }
	                }
	            }
	            refuseReasons = result.substring(0, result.length()-1).toString();
	        }
	        rc.setRefuseReason(refuseReasons);
		}
	}
	
	//处理返回值显示，商品属性,保质期,状态,品种类型,国产/进口,特殊管理药品,专管药品
	@SuppressWarnings("static-access")
	private void  reBuildData1(List<DistrReturnReceiveReportVO> list){
		for(DistrReturnReceiveReportVO nf:list){
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
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
			//状态
			if(nf.getStatus()!=null){
				nf.setStatusName(PubStatus.distrReturnNoticeStatus.getStatusDesc(nf.getStatus()));
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
	public void getDistrReturnCheckReport(Page<DistrReportVo<DistrReturnCheckReportVO>> page, UserVO user,
			RequestDistrReturnCheckVO requestDistrReturnCheckVO) {
		requestDistrReturnCheckVO.setEnterpriseId(user.getEnterpriseId());
		requestDistrReturnCheckVO.setStart(page.getStart());
		Integer sumNum=distrReturnCheckMapper.getDistrReturnCheckReportTotalNum(requestDistrReturnCheckVO);
		if(sumNum!=null){
			page.setTotalRecord(sumNum);
		}
		DistrReportVo<DistrReturnCheckReportVO> distrReportVo=distrReturnCheckMapper.getDistrReportVo(requestDistrReturnCheckVO);
		if(distrReportVo!=null){
			List<DistrReturnCheckReportVO> list=distrReturnCheckMapper.getDistrReturnCheckReport(requestDistrReturnCheckVO);
			List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(user.getEnterpriseId(), null, null, EnableStatus.ENABLE.getStatus());
			reBuildCheckData(list, qualitySetVOList);
			reBuildCheckData1(list);
			distrReportVo.setDataList(list);
	    }else{
	    	distrReportVo=new DistrReportVo<DistrReturnCheckReportVO>();
	    	distrReportVo.setDataList(new ArrayList<DistrReturnCheckReportVO>());
	    }
		page.setResult(distrReportVo);
	}
	private void reBuildCheckData(List<DistrReturnCheckReportVO> list,List<QualitySetVO> qualitySetVOList){
		list.stream().forEach(item->{
            String checkProjectIds = item.getCheckProjectIds();//检验项目集合
            if(checkProjectIds != null){
                String[] checkProjectIdArr = checkProjectIds.split(",");

                Stream.of(checkProjectIdArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getCheckProjectNames();
                    if(name != null){
                        item.setCheckProjectNames(names == null ? name : names + "," + name);
                    }

                });
            }


            String conclusionIds = item.getConclusionIds();//验收结论集合
            if(conclusionIds != null){
                String[] conclusionIdsArr = conclusionIds.split(",");
                Stream.of(conclusionIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getConclusionNames();
                    if(name != null){
                        item.setConclusionNames(names == null ? name : names + "," + name);
                    }
                });
            }


            String unqualifiedReasonIds = item.getUnqualifiedReasonIds();//不合格原因集合
            if(unqualifiedReasonIds != null){
                String[] unqualifiedReasonIdsArr = unqualifiedReasonIds.split(",");
                Stream.of(unqualifiedReasonIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getUnqualifiedReasonNames();
                    if(name != null){
                        item.setUnqualifiedReasonNames(names == null ? name : names + "," + name);
                    }

                });
            }

            String measuresIds = item.getMeasuresIds();
            if(measuresIds != null){
                String[] measuresIdsArr = measuresIds.split(",");
                Stream.of(measuresIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getMeasuresNames();
                    if(name != null){
                        item.setMeasuresNames(names == null ? name : names + "," + name);
                    }
                });
            }
        });
	}
	//处理返回值显示，商品属性,保质期,状态,品种类型,国产/进口,特殊管理药品,专管药品
	@SuppressWarnings("static-access")
	private void  reBuildCheckData1(List<DistrReturnCheckReportVO> list){
		for(DistrReturnCheckReportVO nf:list){
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
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
			//状态
			if(nf.getStatus()!=null){
				nf.setStatusName(PubStatus.distrReturnNoticeStatus.getStatusDesc(nf.getStatus()));
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
	public void exportDistrReturnCheckReportListExcel(OutputStream output, UserVO user,
			RequestDistrReturnCheckVO requestDistrReturnCheckVO) {
		requestDistrReturnCheckVO.setEnterpriseId(user.getEnterpriseId());
		List<DistrReturnCheckReportVO> list=distrReturnCheckMapper.getDistrReturnCheckReport(requestDistrReturnCheckVO);
		List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(user.getEnterpriseId(), null, null, EnableStatus.ENABLE.getStatus());
		reBuildCheckData(list, qualitySetVOList);
		reBuildCheckData1(list);
		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal receiveQuantity=BigDecimal.ZERO;
		BigDecimal samplingQuantity=BigDecimal.ZERO;
		BigDecimal qualifiedQuantity=BigDecimal.ZERO;
		BigDecimal unqualifiedQuantity=BigDecimal.ZERO;
		for(DistrReturnCheckReportVO d: list){
			receiveQuantity=d.getReceiveQuantity().add(receiveQuantity);
			samplingQuantity=d.getSamplingQuantity().add(samplingQuantity);
			qualifiedQuantity=d.getQualifiedQuantity().add(qualifiedQuantity);
			unqualifiedQuantity=d.getUnqualifiedQuantity().add(unqualifiedQuantity);
		}
		map.put("receiveQuantity",receiveQuantity.setScale(6));
		map.put("samplingQuantity",samplingQuantity.setScale(6));
		map.put("qualifiedQuantity",qualifiedQuantity.setScale(6));
		map.put("unqualifiedQuantity",unqualifiedQuantity.setScale(6));
		
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		rowHeaderList.put("checkDate", "日期");
		rowHeaderList.put("checkCode", "单号");
		rowHeaderList.put("requestUnitCode", "要货单位编码");
		rowHeaderList.put("requestUnitName", "要货单位名称");
		rowHeaderList.put("checkerName", "验收人员");
		rowHeaderList.put("secondCheckerName", "验收人员2");
		rowHeaderList.put("baseOrderCode", "配后退回收货单号");
		
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
		
		rowHeaderList.put("receiveQuantity", "收货数量");
		rowHeaderList.put("checkProjectNames", "检验项目");
		rowHeaderList.put("samplingQuantity", "抽检数量");
		rowHeaderList.put("qualifiedQuantity", "验收合格数量");
		rowHeaderList.put("conclusionNames", "验收结论");
		rowHeaderList.put("unqualifiedQuantity", "验收不合格数量");
		rowHeaderList.put("unqualifiedReasonNames", "不合格原因");
		rowHeaderList.put("measuresNames", "处置措施");
	
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
		distrReturnCheckReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map);
	}

	@Override
	public void getDistrReturnInReport(Page<DistrReportVo<DistrRetuenInReportVO>> page, UserVO user,
			RequestDistrReturnInVO requestDistrReturnInVO) {
		requestDistrReturnInVO.setStart(page.getStart());
		requestDistrReturnInVO.setEnterpriseId(user.getEnterpriseId());
		Integer sumNum=distrReturnInMapper.getDistrReturnInReportTotalNum(requestDistrReturnInVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			DistrReportVo<DistrRetuenInReportVO> distrReportVo=distrReturnInMapper.getDistrReportVo(requestDistrReturnInVO);
			if(distrReportVo!=null){
				List<DistrRetuenInReportVO> list=distrReturnInMapper.getDistrReturnInReport(requestDistrReturnInVO);
				reBuildInData1(list);
				distrReportVo.setDataList(list);
			}
			page.setResult(distrReportVo);
		}else{
			page.setTotalRecord(0);
			DistrReportVo<DistrRetuenInReportVO> distrReportVo=new DistrReportVo<DistrRetuenInReportVO>();
			distrReportVo.setDataList(new ArrayList<DistrRetuenInReportVO>());
			page.setResult(distrReportVo);
		}
	}
	
	//处理返回值显示，商品属性,保质期,状态,品种类型,国产/进口,特殊管理药品,专管药品
	@SuppressWarnings("static-access")
	private void  reBuildInData1(List<DistrRetuenInReportVO> list){
		for(DistrRetuenInReportVO nf:list){
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
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
			//状态
			if(nf.getStatus()!=null){
				nf.setStatusName(PubStatus.distrReturnNoticeStatus.getStatusDesc(nf.getStatus()));
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
			//0-总部配送；3-分店间调剂；4-直调配送
			String distrTypeName="";
			if(nf.getDistrType()!=null){
				switch(nf.getDistrType()){
				case 0:distrTypeName="总部配送";break;
				case 3:distrTypeName="分店间调剂";break;
				case 4:distrTypeName="直调配送";break;
				}
			}
			nf.setDistrTypeName(distrTypeName);
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
	public void exportDistrReturnInReportListExcel(OutputStream output, UserVO user,
			RequestDistrReturnInVO requestDistrReturnInVO) {
		
		requestDistrReturnInVO.setEnterpriseId(user.getEnterpriseId());
		
		List<DistrRetuenInReportVO> list=distrReturnInMapper.getDistrReturnInReport(requestDistrReturnInVO);
		reBuildInData1(list);
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		rowHeaderList.put("returnInDate", "日期");
		rowHeaderList.put("returnInCode", "单号");
		rowHeaderList.put("requestUnitCode", "要货单位编码");
		rowHeaderList.put("requestUnitName", "要货单位名称");
		rowHeaderList.put("storageManName", "入库人员");
		rowHeaderList.put("distrTypeName", "配货类型");
		rowHeaderList.put("flowCode", "流通监管码");
		rowHeaderList.put("baseOrderCode", "配后退回验收单号");
		
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
		rowHeaderList.put("lineDiscount", "折扣");
		rowHeaderList.put("amount", "金额");
		rowHeaderList.put("wholeDiscount", "整单折扣");
		rowHeaderList.put("lineDiscountAmount", "优惠分摊");
		rowHeaderList.put("realPrice", "实际单价");
		rowHeaderList.put("realAmount", "实际金额");
		rowHeaderList.put("taxRate", "税率");
		rowHeaderList.put("notaxRealPrice", "不含税单价");
		rowHeaderList.put("notaxRealAmount", "不含税金额");
		rowHeaderList.put("taxAmount", "税额");
	
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
		BigDecimal taxAmount=BigDecimal.ZERO;
		for(DistrRetuenInReportVO d: list){
			quantity=d.getQuantity().add(quantity);
			amount=d.getAmount().add(amount);
			realAmount=d.getRealAmount().add(realAmount);
			notaxRealAmount=d.getNotaxRealAmount().add(notaxRealAmount);
			taxAmount=d.getTaxAmount().add(taxAmount);
		}
		map.put("quantity",quantity.setScale(6));
		map.put("amount",amount.setScale(2));
		map.put("realAmount",realAmount.setScale(2));
		map.put("notaxRealAmount",notaxRealAmount.setScale(2));
		map.put("taxAmount",taxAmount.setScale(2));
		
		distrReturnInReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map);
		
	}
	
}
