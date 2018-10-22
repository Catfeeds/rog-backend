package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.NearEffectPeriodGoodsReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsAttributeBuild;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectPeriodGoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectExportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.NearEffectGoodsExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class NearEffectPeriodGoodsReportServiceImpl implements NearEffectPeriodGoodsReportService {
	
	@Autowired
	GoodsMapper GoodsMapper;
	
	@Autowired
	NearEffectGoodsExcelComponent<NearEffectPeriodGoodsVO> nearEffectGoodsExcelComponent;
	
	@Override
	public void exportNearEffectPeriodGoodsListExcel(UserVO user, OutputStream output,
			RequestNearEffectExportVO requestNearEffectExportVO) {
		RequestNearEffectVO requestNearEffectVO=new RequestNearEffectVO();
		BeanUtils.copyProperties(requestNearEffectExportVO,requestNearEffectVO);
		requestNearEffectVO.setEnterpriseId(user.getEnterpriseId());
		List<NearEffectPeriodGoodsVO> list=GoodsMapper.getNearEffectPeriodGoodsListNew(requestNearEffectVO);
		
		reBuildNearEffectGoodsData(list);
		BigDecimal totalQuantity=BigDecimal.ZERO;
		for(NearEffectPeriodGoodsVO nf:list){
			totalQuantity=totalQuantity.add(nf.getQuantity());
		}
		totalQuantity=totalQuantity.setScale(6);
		/*reBuildData(list);*/
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}
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
		rowHeaderList.put("validDate", "有效期至");
		rowHeaderList.put("shelfName", "货位");
		rowHeaderList.put("shelfStatusDesc", "质量状况");
		rowHeaderList.put("quantity", "数量");
		rowHeaderList.put("nearEffectPeriodUnit", "近效期周期");
		rowHeaderList.put("validDays", "近效期天数");
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
		nearEffectGoodsExcelComponent.commExcelExport(output, rowHeaderList, list, user, totalQuantity);
		
	}
	

	@Override
	public String getNearEffectPeriodGoodsListNew(UserVO user, Page<NearEffectReportVO<NearEffectPeriodGoodsVO>> page,
			RequestNearEffectVO requestNearEffectVO) {
		NearEffectReportVO<NearEffectPeriodGoodsVO>  nearEffectReportVO=new NearEffectReportVO<>();
		requestNearEffectVO.setEnterpriseId(user.getEnterpriseId());
		requestNearEffectVO.setStart(page.getStart());
		Map<String,Object> resmap=GoodsMapper.getNearEffectPeriodGoodsListTotalData(requestNearEffectVO);
		if(resmap!=null){
			nearEffectReportVO.setQuantity((BigDecimal) resmap.get("quantity"));
			Long totalNum=(Long) resmap.get("totalNum");
			if(totalNum!=null&&totalNum!=0){
				Integer  totalRecord=Integer.parseInt(totalNum+"");
				page.setTotalRecord(totalRecord);
				List<NearEffectPeriodGoodsVO> list=GoodsMapper.getNearEffectPeriodGoodsListNew(requestNearEffectVO);
				reBuildNearEffectGoodsData(list);
				nearEffectReportVO.setDataList(list);
			}
		}
		page.setResult(nearEffectReportVO);
		return null;
	}
	
	//处理返回值显示，商品属性,保质期,近效期,品种类型,国产/进口,特殊管理药品,专管药品
	private void  reBuildNearEffectGoodsData(List<NearEffectPeriodGoodsVO> list){
		for(NearEffectPeriodGoodsVO nf:list){
			//商品属性
			String goodsAttributeName=GoodsAttributeBuild.getGoodsAttribute(nf.getBusinessVariety(),
					nf.getGoodsAttribute(), nf.getPrescriptionDrug(), nf.getOtcType(), nf.getCosmetics());
			nf.setGoodsAttributeName(goodsAttributeName);
			//保质期
			if(nf.getQualityPeriod()!=null&&nf.getQualityPeriodUnit()!=null){
				int quanlityPeriodUnit=Integer.parseInt(nf.getQualityPeriodUnit());
				switch(quanlityPeriodUnit){
				case 0:nf.setQualityPeriodUnit(nf.getQualityPeriod()+"日"); break;
				case 1:nf.setQualityPeriodUnit(nf.getQualityPeriod()+"月"); break;
				case 2:nf.setQualityPeriodUnit(nf.getQualityPeriod()+"年"); break;
				}
			}
			//近效期
			if(nf.getNearEffectPeriod()!=null&&nf.getNearEffectPeriodUnit()!=null){
				int nearEffectPeriodUnit=Integer.parseInt(nf.getNearEffectPeriodUnit());
				switch(nearEffectPeriodUnit){
				case 0:nf.setNearEffectPeriodUnit(nf.getNearEffectPeriod()+"日"); break;
				case 1:nf.setNearEffectPeriodUnit(nf.getNearEffectPeriod()+"月"); break;
				case 2:nf.setNearEffectPeriodUnit(nf.getNearEffectPeriod()+"年"); break;
				}
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
