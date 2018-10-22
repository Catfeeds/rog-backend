package com.rograndec.feijiayun.chain.business.goods.httpInf.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.goods.httpInf.entity.MphNormRelations;
import com.rograndec.feijiayun.chain.business.goods.httpInf.service.GoodsStandardService;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.*;
import com.rograndec.feijiayun.chain.business.goods.info.dao.ManufacturerMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.GoodsDictionaryVO;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsDosageMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
 * 
 * @ClassName: GoodsStandardServiceImpl   
 * @Description: 根据标准库id获取商品信息
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月17日 下午7:57:07
 */
@Service("goodsStandardService")
public class GoodsStandardServiceImpl implements GoodsStandardService{
	
	private static Logger logger = LoggerFactory.getLogger(GoodsStandardServiceImpl.class);
	
	@Value("${mph.api.url}")
	private String mphApiUrl;
	
	// 请求接口头信息
	private static final String INFHEAD = "head:{\"appKey\":\"dba0de982cee11e5b7a3848f69dd5ff1\",\"version\":1},\"mac\":\"6bf8b4f5cc8c23fde6b4b7c0771b6224\"}";
	
	@Autowired
	private GoodsDosageMapper goodsDosageMapper;
	
	@Autowired
	private ManufacturerMapper manufacturerMapper;
	
	/**
	 * 
	 * @Description: mph接口获取商品说明书，药学指导
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月17日 下午8:15:33 
	 * @param nrId 标准库id
	 * @return 
	 * @return GoodsPharmacyVO
	 */
	public GoodsPharmacyVO goodsSpecifications(String nrId) {
		String sendData = "dataJson={body:[{\"nrIds\": [" + nrId.trim() + "]}],"+INFHEAD;
		String url = mphApiUrl + "/norm/queryNormRelationsByIds?";
		String result = HttpClientUtil.sendPostRequest(url,sendData, true);
		if (result != "" && !result.contains("<html>")) {
			JSONObject jasonObject = new JSONObject();
			jasonObject = JSONObject.parseObject(result);
			JSONArray json_data = jasonObject.getJSONArray("data");
			if (json_data != null) {
				// 获取说明书类型表名
				String nrTable = JSONObject.parseObject(json_data.get(0).toString()).getString("nrTable");
				// 获取商品名
				String nrCommonName = JSONObject.parseObject(json_data.get(0).toString()).getString("nrCommonName");
				GoodsPharmacyVO gpVO = new GoodsPharmacyVO();
				if (nrTable.equals("mph_norm_drug")) {
					String normDrug = JSONObject.parseObject(json_data.get(0).toString()).getString("normDrug");
					NormDrugVO normDrugo = JSONObject.parseObject(normDrug, NormDrugVO.class);
					gpVO.setNormDrug(normDrugo);
					gpVO.setNrCommonName(nrCommonName);
					return gpVO;
				}
				if (nrTable.equals("mph_norm_instrument")) {
					String normInstrument = JSONObject.parseObject(json_data.get(0).toString())
							.getString("normInstrument");
					NormInstrumentVO normInstrumento = JSONObject.parseObject(normInstrument,
							NormInstrumentVO.class);
					gpVO.setNormInstrument(normInstrumento);
					gpVO.setNrCommonName(nrCommonName);
					return gpVO;
				}
				if (nrTable.equals("mph_norm_health_products")) {
					String normHealthProducts = JSONObject.parseObject(json_data.get(0).toString())
							.getString("normHealthProducts");
					NormHealthProductsVO normHealthProductso = JSONObject.parseObject(normHealthProducts,
							NormHealthProductsVO.class);
					gpVO.setNormHealthProducts(normHealthProductso);
					gpVO.setNrCommonName(nrCommonName);
					return gpVO;
				}
				if (nrTable.equals("mph_norm_other")) {
					String normOther = JSONObject.parseObject(json_data.get(0).toString()).getString("normOther");
					NormOtherVO normOthero = JSONObject.parseObject(normOther, NormOtherVO.class);
					gpVO.setNormOther(normOthero);
					gpVO.setNrCommonName(nrCommonName);
					return gpVO;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: 用药检查
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月18日 上午11:09:46 
	 * @param nrIds
	 * @return 
	 * @return List<GoodsExaminationVO>
	 */
	public List<GoodsExaminationVO> medicationExamination(List<String> nrIds) {
		String sendData = "dataJson={body:[{\"nrIds\": " + nrIds + "}],"+INFHEAD;
		String url = mphApiUrl + "/norm/queryNormRelationsByIds?";
		String result = HttpClientUtil.sendPostRequest(url,sendData, true);
		if (result != "" && !result.contains("<html>")) {
			JSONObject jasonObject = new JSONObject();
			List<GoodsExaminationVO> saaslist = new ArrayList<>();
			jasonObject = JSONObject.parseObject(result);
			JSONArray json_data = jasonObject.getJSONArray("data");
			if (json_data != null) {
				//Integer a = json_data.size();
				for (int i = 0; i < json_data.size(); i++) {
					// 获取说明书类型表名
					String nrTable = JSONObject.parseObject(json_data.get(i).toString()).getString("nrTable");
					if (nrTable.equals("mph_norm_drug")) {
						GoodsExaminationVO geVO = new GoodsExaminationVO();
						// 获取商品名
						String nrCommonName = JSONObject.parseObject(json_data.get(i).toString())
								.getString("nrCommonName");
						String normDrug = JSONObject.parseObject(json_data.get(i).toString()).getString("normDrug");
						JSONObject normDrugo = JSONObject.parseObject(normDrug);
						// normDrugo.get("nd_contraindication");//禁忌症
						geVO.setName(nrCommonName);
						geVO.setWarnName("药品与药品冲突");
						if (normDrug != null) {
							geVO.setSimpleDescription(normDrugo.get("nd_contraindication") == null ? ""
									: normDrugo.get("nd_contraindication").toString());
							geVO.setDetailedDescription(normDrugo.get("nd_contraindication") == null ? ""
									: normDrugo.get("nd_contraindication").toString());
						}
						saaslist.add(geVO);
					}
					if (nrTable.equals("mph_norm_instrument")) {
						GoodsExaminationVO geVO = new GoodsExaminationVO();
						String nrCommonName = JSONObject.parseObject(json_data.get(i).toString())
								.getString("nrCommonName");
						String normInstrument = JSONObject.parseObject(json_data.get(i).toString())
								.getString("norm_instrument");
						JSONObject normInstrumento = JSONObject.parseObject(normInstrument);
						// normDrugo.get("nd_contraindication");//禁忌症
						geVO.setName(nrCommonName);
						geVO.setWarnName("药品与药品冲突");
						if (normInstrumento != null) {
							geVO.setSimpleDescription(normInstrumento.get("nd_contraindication") == null ? ""
									: normInstrumento.get("nd_contraindication").toString());
							geVO.setDetailedDescription(normInstrumento.get("nd_contraindication") == null ? ""
									: normInstrumento.get("nd_contraindication").toString());
						}
						saaslist.add(geVO);
					}
					if (nrTable.equals("mph_norm_health_products")) {
						GoodsExaminationVO geVO = new GoodsExaminationVO();
						String nrCommonName = JSONObject.parseObject(json_data.get(i).toString())
								.getString("nrCommonName");
						String normHealthProducts = JSONObject.parseObject(json_data.get(i).toString())
								.getString("normHealthProducts");
						JSONObject normHealthProductso = JSONObject.parseObject(normHealthProducts);
						// normDrugo.get("nhp_no_fit_crowd");//不适宜人群
						geVO.setName(nrCommonName);
						geVO.setWarnName("药品与药品冲突");
						if (normHealthProductso != null) {
							geVO.setSimpleDescription(normHealthProductso.get("nd_contraindication") == null ? ""
									: normHealthProductso.get("nd_contraindication").toString());
							geVO.setDetailedDescription(normHealthProductso.get("nd_contraindication") == null ? ""
									: normHealthProductso.get("nd_contraindication").toString());
						}
						saaslist.add(geVO);
					}
					if (nrTable.equals("mph_norm_other")) {
						GoodsExaminationVO geVO = new GoodsExaminationVO();
						String nrCommonName = JSONObject.parseObject(json_data.get(i).toString())
								.getString("nrCommonName");
						String normOther = JSONObject.parseObject(json_data.get(i).toString()).getString("normOther");
						JSONObject normOthero = JSONObject.parseObject(normOther);
						// normDrugo.get("nd_contraindication");//禁忌症
						geVO.setName(nrCommonName);
						geVO.setWarnName("药品与药品冲突");
						if (normOthero != null) {
							geVO.setSimpleDescription(normOthero.get("nd_contraindication") == null ? ""
									: normOthero.get("nd_contraindication").toString());
							geVO.setDetailedDescription(normOthero.get("nd_contraindication") == null ? ""
									: normOthero.get("nd_contraindication").toString());
						}
						saaslist.add(geVO);

					}
				}
			}
			return saaslist;

		} else {
			return null;
		}

	}

	@Override
	public Page<List<GoodsDictionaryVO>> searchGoodsBySymptom(String symptom, Long pageNo) {
		String sendData = "dataJson={\"body\": [{\"keyword\": \"" + symptom.trim() + "\",\"pageIndex\":\"" + pageNo + "\"}],"+INFHEAD;
		String url = mphApiUrl + "/norm/queryProductFunc?";
		String result = HttpClientUtil.sendPostRequest(url, sendData,true);
		Page<List<GoodsDictionaryVO>> page = new Page<>();
		if (result != "" && !result.contains("<html>")) {
			JSONObject jasonObject = new JSONObject();
			jasonObject = JSONObject.parseObject(result);
			JSONObject json_data = JSONObject.parseObject(jasonObject.getString("data"));
			List<GoodsDictionaryVO> goodslist = new ArrayList<>();

			if (json_data != null) {
				Long taotal = json_data.getString("totalCount") == null ? 0L
						: Long.parseLong(json_data.getString("totalCount"));
				page.setTotalRecord(taotal.intValue());
				String json_array = JSONObject.parseObject(jasonObject.getString("data")).getString("resultList");

				List<MphNormRelations> list = new ArrayList<MphNormRelations>();
				list = JSONObject.parseArray(json_array, MphNormRelations.class);
				List<String> standIds = new ArrayList<String>();

				for (MphNormRelations mphNormRelations : list) {
					standIds.add(mphNormRelations.getNrId().toString());
				}

				String sendData2 = "dataJson={body:[{\"nrIds\": " + standIds + "}],"+INFHEAD;
				String url2 = mphApiUrl + "/norm/queryNormRelationsByIds?";
				String result2 = HttpClientUtil.sendPostRequest(url2,sendData2, true);

				if (!org.apache.commons.lang3.StringUtils.isBlank(result2) && !result2.contains("<html>")) {
					JSONObject jasonObject2 = JSONObject.parseObject(result2);
					JSONArray json_data2 = jasonObject2.getJSONArray("data") == null ? new JSONArray() : jasonObject2.getJSONArray("data");
					for (int i = 0; i < json_data2.size(); i++) {
						GoodsDictionaryVO goods = new GoodsDictionaryVO();
						goods.setStandardLibraryId(JSONObject.parseObject(json_data2.get(i).toString()).getLong("nrId"));
						goods.setName(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrCommonName"));
						goods.setApprovalNumber(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrLicenseNo"));
						goods.setManufacturer(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrProduceUnit"));
						goods.setSpecification(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrSpecifications"));
						goods.setBarcode(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrBarCode"));
						goods.setGenericName(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrCommonName"));
						goods.setDosageName(JSONObject.parseObject(json_data2.get(i).toString()).getString("gdfName"));
						goods.setUnitName(JSONObject.parseObject(json_data2.get(i).toString()).getString("nrUnit"));
						goodslist.add(goods);
					}
					
					page.setResult(goodslist);
					return page;
				} else {
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public Page<List<GoosMPHVO>> searchGoodsMPH(Map<String, String> param) throws BusinessException{
		long stime = System.currentTimeMillis();
		String nrCommonCode = StringUtils.isNotBlank(param.get("goodsParam")) ? param.get("goodsParam").trim() : "";//商品名称|条形码
		String nrLicenseNo = StringUtils.isNotBlank(param.get("approvalNumber")) ? param.get("approvalNumber").trim() : "";//批准文号
		String nrProduceUnit = StringUtils.isNotBlank(param.get("manufacturer")) ? param.get("manufacturer").trim() : "";//生产厂商
		String pageIndex = StringUtils.isNotBlank(param.get("pageNo")) ? param.get("pageNo") : "1";//起始页
		
		String sendData = "dataJson={body:[{\"nrCommonName\":\"" + nrCommonCode + "\",\"nrLicenseNo\":\"" + nrLicenseNo
				+ "\",\"nrProduceUnit\":\"" + nrProduceUnit + "\",\"pageIndex\":\"" + pageIndex
				+ "\"}],"+INFHEAD;
		String url = mphApiUrl + "/norm/fuzzyQuery?";
		String result = HttpClientUtil.sendPostRequest(url, sendData,true);
		
		Page<List<GoosMPHVO>> page = new Page<>();
		if (StringUtils.isNotBlank(result) && !result.contains("<html>")) {
			
			JSONObject jasonObject = new JSONObject();
			jasonObject = JSONObject.parseObject(result);
			JSONObject json_data = JSONObject.parseObject(jasonObject.getString("data"));
			List<GoosMPHVO> goodslist = new ArrayList<>();
			if (json_data != null) {
				Long taotal = json_data.getString("totalCount") == null ? 0L
						: Long.parseLong(json_data.getString("totalCount"));
				page.setTotalRecord(taotal.intValue());
				
				String json_array = JSONObject.parseObject(jasonObject.getString("data")).getString("resultList");

				List<MphNormRelations> list = new ArrayList<MphNormRelations>();
				list = JSONObject.parseArray(json_array, MphNormRelations.class);
				if(!CollectionUtils.isEmpty(list)){
					// 厂商、剂型名称
					List<String> manufacturerList = new ArrayList<>();
					List<String> dosageNameList = new ArrayList<>();
					for (MphNormRelations mphNormRelations : list) {
						GoosMPHVO goods = new GoosMPHVO();
						goods.setStandardLibraryId(mphNormRelations.getNrId().longValue());
						goods.setName(mphNormRelations.getNrCommonName());
						goods.setGenericName(mphNormRelations.getNrCommonName());
						goods.setSpecification(mphNormRelations.getNrSpecifications());
						goods.setApprovalNumber(mphNormRelations.getNrLicenseNo());
						goods.setManufacturer(mphNormRelations.getNrProduceUnit());
						if(StringUtils.isNotBlank(goods.getManufacturer())) {
							manufacturerList.add(goods.getManufacturer());
						}
						goods.setBarcode(mphNormRelations.getNrBarCode());
						goods.setDosageName(mphNormRelations.getGdfName());
						if(StringUtils.isNotBlank(goods.getDosageName())) {
							dosageNameList.add(goods.getDosageName());
						}
						if(mphNormRelations.getNrOtc() != null){
							goods.setPrescriptionDrug(mphNormRelations.getNrOtc() == 1 ? 1 : 0);
						}
						if (mphNormRelations.getNormDrug() != null
								&& mphNormRelations.getNormDrug().getNd_holding_conditions() != null) {
							switch (mphNormRelations.getNormDrug().getNd_holding_conditions()) {
								case 1://常温
									goods.setStorageTemp(0);
									break;
								case 2://阴凉
									goods.setStorageTemp(1);
									break;
								case 3://冷藏
									goods.setStorageTemp(2);
									break;
								default:
									break;
							}
						}
						if (mphNormRelations.getNormDrug() != null && mphNormRelations.getNormDrug().getNd_special_composition() != null
								&& 0 != mphNormRelations.getNormDrug().getNd_special_composition() ) {
							goods.setFormulationType(1);
						}
						goodslist.add(goods);
					}
					//处理厂商、剂型id
					dosageNameList = dosageNameList.stream().filter(f->{return StringUtils.isNotBlank(f);}).distinct().collect(Collectors.toList());

					if(!CollectionUtils.isEmpty(dosageNameList)){
						List<Map<String, Object>> dosaMapList = goodsDosageMapper.findByName(dosageNameList);
						goodslist.forEach(gd -> {
							// 剂型id
							if(null != dosaMapList && dosaMapList.size() > 0) {
								dosaMapList.forEach(dm->{
									if(gd.getDosageName().equals(dm.get("name")+"")) {
										gd.setDosageId(Long.valueOf(dm.get("id")+""));
									}
								});
							}
						});
					}
					manufacturerList = manufacturerList.stream().filter(f->{return StringUtils.isNotBlank(f);}).distinct().collect(Collectors.toList());
					if(!CollectionUtils.isEmpty(manufacturerList)){
						List<Map<String, Object>> manMapList = manufacturerMapper.findByName(manufacturerList);
						goodslist.forEach(gd -> {
							// 厂商id
							if(null != manMapList && manMapList.size() > 0) {
								manMapList.forEach(maf->{
									if(gd.getManufacturer().equals(maf.get("name")+"")) {
										gd.setManufacturerId(Long.valueOf(maf.get("id")+""));
									}
								});
							}

						});
					}
				}

			}
			page.setResult(goodslist);
			logger.debug("从MPH获取商品信息耗时为："+ (System.currentTimeMillis() - stime) +"ms");
			return page;
		} else {
			return null;
		}
	}
	
	
	public Page<List<SupplierMPHVO>> searchSupplierMPH(Map<String,String> param) throws BusinessException {
		Long stime = System.currentTimeMillis();
		String supplierName = StringUtils.isNotBlank(param.get("supplierName")) ? param.get("supplierName").trim() : "";//供货单位名称
		String supplierLicenseNo = StringUtils.isNotBlank(param.get("businessLicenseCode")) ? param.get("businessLicenseCode").trim() : "";//营业执照号
		String pageIndex = StringUtils.isNotBlank(param.get("pageNo")) ? param.get("pageNo") : "1";//起始页
		String sendData = "dataJson={body:[{\"supplierName\":\"" + supplierName + "\",\"supplierLicenseNo\":\"" + supplierLicenseNo + "\",\"pageIndex\":\"" + pageIndex + "\"}],"+INFHEAD;

		String url = mphApiUrl + "/supplier/fuzzyQuery?";
		String result = HttpClientUtil.sendPostRequest(url, sendData, true);
		Page<List<SupplierMPHVO>> page = new Page<>();
		if (result != "") {
			JSONObject jasonObject = new JSONObject();
			jasonObject = JSONObject.parseObject(result);
			JSONObject json_data = JSONObject.parseObject(jasonObject.getString("data"));
			List<SupplierMPHVO> saaslist = new ArrayList<>();
			
			if (json_data != null) {
				Long taotal = json_data.getString("totalCount") == null ? 0L : Long.parseLong(json_data.getString("totalCount"));
				page.setTotalRecord(taotal.intValue());
				String json_array = JSONObject.parseObject(jasonObject.getString("data")).getString("resultList");

				List<MphSupRelations> list = new ArrayList<MphSupRelations>();
				list = JSONObject.parseArray(json_array, MphSupRelations.class);
				for (MphSupRelations mphSupRelations : list) {
					SupplierMPHVO supplier = new SupplierMPHVO();
					supplier.setStandardLibraryId(mphSupRelations.getSupplierId().longValue());
					supplier.setSupplierName(mphSupRelations.getSupplierName());
					supplier.setCompanyAddress(mphSupRelations.getSupplierAddress());
					supplier.setLicenseCode(mphSupRelations.getSupplierAllowCode());
					supplier.setBusinessLicenseCode(mphSupRelations.getSupplierLicenseNo());
					supplier.setLegalManName(mphSupRelations.getSupplierContactor());
					saaslist.add(supplier);
				}
			}
			page.setResult(saaslist);
			logger.debug("从MPH获取供应商信息耗时为："+ (System.currentTimeMillis() - stime) +"ms");
			return page;
		} else {
			return null;
		}
	
	}

}	