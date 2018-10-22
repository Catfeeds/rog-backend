package com.rograndec.feijiayun.chain.business.report.quality.distr.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInCheckMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInMapper;
import com.rograndec.feijiayun.chain.business.report.quality.distr.service.DistrInReportService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInCheckVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsAttributeBuild;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.DistrInCheckReportExcelComponent;
import com.rograndec.feijiayun.chain.common.component.DistrInReportExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class DistrInReportServiceImpl implements DistrInReportService {
	@Autowired
	private QualitySetMapper qualitySetMapper;

	@Autowired
	private DistrInCheckMapper distrInCheckMapper;
	@Autowired
	private DistrInCheckReportExcelComponent<DistrInCheckReportVO> distrInCheckReportExcelComponent;
	@Autowired
	private DistrInReportExcelComponent<DistrInReportVO> distrInReportExcelComponent;
	@Autowired
	private DistrInMapper distrInMapper;
	
	@Override
	public void getDistrInCheckReportList(Page<DistrReportVo<DistrInCheckReportVO>> page, UserVO user,
			RequestDistrInCheckVO requestDistrInCheckVO) {
		
		requestDistrInCheckVO.setStart(page.getStart());
		requestDistrInCheckVO.setParentId(user.getEnterpriseId());
		Integer sumNum=distrInCheckMapper.getDistrInCheckReportListTotalNum(requestDistrInCheckVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			DistrReportVo<DistrInCheckReportVO> distrReportVo=distrInCheckMapper.getDistrReportVo(requestDistrInCheckVO);
			if(distrReportVo!=null){
				List<DistrInCheckReportVO> list=distrInCheckMapper.getDistrInCheckReportList(requestDistrInCheckVO);
				Long enterpriseId=user.getEnterpriseId();
				//如果不是总部则用总部的id
				if(user.getChainType()!=0){
					enterpriseId=user.getParentId();
				}
				List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, null, null, EnableStatus.ENABLE.getStatus());
				reBuildData(list, qualitySetVOList);
				reBuildData2(list);
				distrReportVo.setDataList(list);
				page.setResult(distrReportVo);
			}
			
		}else{
			DistrReportVo<DistrInCheckReportVO> distrReportVo=new DistrReportVo<DistrInCheckReportVO>();
			distrReportVo.setDataList(new ArrayList<DistrInCheckReportVO>());
			page.setResult(distrReportVo);
		}
		
	}
	
	@Override
	public void exportDistrInCheckReportListExcel(OutputStream output, UserVO user,
			RequestDistrInCheckVO requestDistrInCheckVO) {
		requestDistrInCheckVO.setParentId(user.getEnterpriseId());
		List<DistrInCheckReportVO> list=distrInCheckMapper.getDistrInCheckReportList(requestDistrInCheckVO);
		List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(user.getEnterpriseId(), null, null, EnableStatus.ENABLE.getStatus());
		reBuildData(list, qualitySetVOList);
		reBuildData2(list);
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}
		rowHeaderList.put("checkDate", "日期");
		rowHeaderList.put("checkCode", "单号");
		rowHeaderList.put("distrUnitCode", "配货单位编码");
		rowHeaderList.put("distrUnitName", "配货单位名称");
		rowHeaderList.put("checkerName", "验收人员");
		rowHeaderList.put("secondCheckerName", "验收人员2");
		rowHeaderList.put("baseOrderCode", "配进收货单号");
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
		
		Map<String,BigDecimal> map=new HashMap<>();
		BigDecimal receiveQuantity=BigDecimal.ZERO;
		BigDecimal samplingQuantity=BigDecimal.ZERO;
		BigDecimal qualifiedQuantity=BigDecimal.ZERO;
		BigDecimal unqualifiedQuantity=BigDecimal.ZERO;
		for(DistrInCheckReportVO d: list){
			receiveQuantity=d.getReceiveQuantity().add(receiveQuantity);
			samplingQuantity=d.getSamplingQuantity().add(samplingQuantity);
			qualifiedQuantity=d.getQualifiedQuantity().add(qualifiedQuantity);
			unqualifiedQuantity=d.getUnqualifiedQuantity().add(unqualifiedQuantity);
		}
		map.put("receiveQuantity",receiveQuantity.setScale(6));
		map.put("samplingQuantity",samplingQuantity.setScale(6));
		map.put("qualifiedQuantity",qualifiedQuantity.setScale(6));
		map.put("unqualifiedQuantity",unqualifiedQuantity.setScale(6));
		distrInCheckReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map);
	}



	private void reBuildData(List<DistrInCheckReportVO> list,List<QualitySetVO> qualitySetVOList){
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
	
	//处理返回值显示，商品属性,保质期
	private void reBuildData2(List<DistrInCheckReportVO> list){
		for(DistrInCheckReportVO nf:list){
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
			}	
			if(nf.getStatus()!=null){
				if(nf.getStatus() == DistrInStatus.WAIT_IN){//状态为待入库，在此处为已验收
					nf.setStatusName("已验收");
				}else{
					nf.setStatusName(PubStatus.distrInStatus.getStatusDesc(nf.getStatus()));
				}
			}else{
				nf.setStatusName("未知类型的状态");
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
	public void getDistrInReportList(Page<DistrReportVo<DistrInReportVO>> page, UserVO user, RequestDistrInVO requestDistrInVO) {
		requestDistrInVO.setStart(page.getStart());
		requestDistrInVO.setParentId(user.getEnterpriseId());
		Integer sumNum=distrInMapper.getDistrInReportListTotalNum(requestDistrInVO);
		if(sumNum!=null&&sumNum!=0){
			page.setTotalRecord(sumNum);
			DistrReportVo<DistrInReportVO> distrReportVo=distrInMapper.getDistrReportVo(requestDistrInVO);
			if(distrReportVo!=null){
				List<DistrInReportVO> list=distrInMapper.getDistrInReportList(requestDistrInVO);
				reBuildDistrInReportVO(list);
				distrReportVo.setDataList(list);
				page.setResult(distrReportVo);
			}
		}else{
			DistrReportVo<DistrInReportVO> distrReportVo=new DistrReportVo<DistrInReportVO>();
			distrReportVo.setDataList(new ArrayList<DistrInReportVO>());
			page.setResult(distrReportVo);
		}
	}

	@Override
	public void exportDistrInReportListExcel(OutputStream output, UserVO user, RequestDistrInVO requestDistrInVO) {
		requestDistrInVO.setParentId(user.getEnterpriseId());
		List<DistrInReportVO> list=distrInMapper.getDistrInReportList(requestDistrInVO);
		reBuildDistrInReportVO(list);
		
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		rowHeaderList.put("enterpriseCode", "组织机构编码");
		rowHeaderList.put("enterpriseName", "组织机构名称");
		rowHeaderList.put("inDate", "日期");
		rowHeaderList.put("inCode", "单号");
		rowHeaderList.put("distrUnitCode", "配货单位编码");
		rowHeaderList.put("distrUnitName", "配货单位名称");
		rowHeaderList.put("storageManName", "入库人员");
		rowHeaderList.put("distrTypeName", "配货类型");
		rowHeaderList.put("baseOrderCode", "配进验收单号");
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
		for(DistrInReportVO d: list){
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
		
		distrInReportExcelComponent.commExcelExport(output, rowHeaderList, list, user, map);
		
	}

	//处理返回值显示，商品属性,保质期
	private void reBuildDistrInReportVO(List<DistrInReportVO> list){
		for(DistrInReportVO nf:list){
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
			}	
			if(nf.getStatus()!=null){
				if(nf.getStatus() == DistrInStatus.WAIT_BILL){
					nf.setStatusName("已入库");
				}else{
					nf.setStatusName(PubStatus.distrInStatus.getStatusDesc(nf.getStatus()));
				}
			}else{
				nf.setStatusName("未知类型的状态");
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
			//设置配送类型
			if(nf.getDistrType() != null) {
				nf.setDistrTypeName(DistributionType.getName(nf.getDistrType()));
			}
		}
	}
	
	
}
