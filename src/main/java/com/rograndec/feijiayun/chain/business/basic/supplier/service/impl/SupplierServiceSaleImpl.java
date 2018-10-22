package com.rograndec.feijiayun.chain.business.basic.supplier.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.constant.QuerySupplierSalerType;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierModifyRecord;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.exception.SupplierBizException;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierSaleService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerReturnVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.ModifyRecordCompoent;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;

@Service
public class SupplierServiceSaleImpl implements SupplierSaleService{

	@Autowired
	private SupplierSalerMapper supplierSalerMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private BusinessScopeMapper businessScopeMapper;

	@Autowired
	private SupplierComponent supplierComponent;

	@Autowired
	private FileMapper fileMapper;

	@Autowired
	private ModifyRecordCompoent modifyRecordCompoent;

	@Autowired
	private SupplierModifyRecordMapper supplierModifyRecordMapper;

	@Autowired
	private ExcelComponent<SupplierSalerReturnVO> excelComponent;

	@Override
	public void addSale(SupplierSaler supplierSaler) {
		supplierSalerMapper.insertSelective(supplierSaler);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateSale(SupplierSaler supplierSaler, UserVO userVO) throws Exception {

		Map<String,Object>  newSupplierSalerMap = modifyRecordCompoent.getFieldsMap(supplierSaler);

		SupplierSaler sc = supplierSalerMapper.selectByPrimaryKey(supplierSaler.getId());
		Supplier supplier = supplierMapper.selectByPrimaryKey(sc.getSupplierId());
		//权限校验(加盟店不能更新总部数据)
		if (!supplier.getOwnerId().equals(userVO.getEnterpriseId())){
			throw new BusinessException(SysCode.FAIL.getCode(),"您没有修改权限");
		}

		Map<String,Object>  oldSupplierSalerMap = modifyRecordCompoent.getFieldsMap(sc);

		supplierSalerMapper.updateByPrimaryKeySelective(supplierSaler);

		List<SupplierModifyRecord> supplierModifyRecords = getModifyRecordList(userVO,supplierSaler,"saas_supplier_saler",supplierSaler.getId()
		,supplierSaler.getUpdateTime(),oldSupplierSalerMap,newSupplierSalerMap,fieldMustMap());

		for(SupplierModifyRecord supplierModifyRecord : supplierModifyRecords){
			supplierModifyRecordMapper.insertSelective(supplierModifyRecord);
		}

	}


	private Map<String,String> fieldMustMap(){
		Map<String,String> fieldNames = new HashMap();
		fieldNames.put("supplierId","供货单位ID");
		fieldNames.put("name","姓名");
		fieldNames.put("code","编码");

		fieldNames.put("idNum","身份证号");
		fieldNames.put("idValidUntil","身份证有效期至");
		fieldNames.put("idFileId","身份证附件ID");
		fieldNames.put("certificateNum","授权书号");
		fieldNames.put("certificateValidUntil","授权书有效期至");
		fieldNames.put("authorizedVariety","授权品种ID");
		fieldNames.put("authorizedVarietyScope","授权品种范围ID");
		fieldNames.put("certificateFileId","授权书附件ID");
		fieldNames.put("telephone","电话");
		fieldNames.put("fax","传真");
		fieldNames.put("mobilePhone","手机");
		fieldNames.put("email","邮箱");
		fieldNames.put("wechatNum","微信");
		fieldNames.put("qqNum","QQ");
		fieldNames.put("status","状态");
		fieldNames.put("remark","备注");

		return fieldNames;
	}

	private List<SupplierModifyRecord> getModifyRecordList(
			UserVO userVO ,SupplierSaler supplierSaler, String tableName, Long keyId, Date updateTime
			, Map<String,Object> valMap
			, Map<String,Object> newMap , Map<String,String> fieldMustMap){

		List<SupplierModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

		for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
			Object obj = valMap.get(entry.getKey());
			Object newObj = newMap.get(entry.getKey());
			if(null != obj && null != newObj && !obj.equals(newObj)){
				SupplierModifyRecord userModify = new SupplierModifyRecord();
				userModify.setEnterpriseId(userVO.getEnterpriseId());
				userModify.setTableName(tableName);
				userModify.setKeyId(keyId);
				userModify.setColumnEnName(entry.getKey());
				userModify.setColumnChName(entry.getValue());
				userModify.setUpdateTime(updateTime);
				userModify.setCreaterId(userVO.getUserId());
				userModify.setCreaterCode(userVO.getUserCode());
				userModify.setCreaterName(userVO.getUserName());
				userModify.setCreateTime(new Date());
				userModify.setOldContent(obj.toString());
				userModify.setNewContent(obj.toString());
				userModify.setSupplierId(supplierSaler.getSupplierId());
				userModify.setModifierCode(userVO.getUserCode());
				userModify.setModifierId(userVO.getUserId());
				userModify.setModifierName(userVO.getUserName());

				modifyRecordWithBLOBs.add(userModify);
			}
		}

		return modifyRecordWithBLOBs;
	}

	@Override
	public void deleteSale(Long id, UserVO userVO) {
		SupplierSaler sc = supplierSalerMapper.selectByPrimaryKey(id);
		Supplier supplier = supplierMapper.selectByPrimaryKey(sc.getSupplierId());
		//权限校验(加盟店不能更新总部数据)
		if (!supplier.getOwnerId().equals(userVO.getEnterpriseId())){
			throw new SupplierBizException(SupplierBizException.DELETE_FAIL,"您没有删除权限");
		}
		int deleteCount = supplierSalerMapper.deleteByPrimaryKey(id);
		if(deleteCount <= 0 ){
			throw new SupplierBizException(SupplierBizException.DELETE_FAIL,"删除失败");
		}
	}

	@Override
	public List<SupplierModifyRecord> findSupplierModifyRecords(Long id,Page page){
		com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
		List<SupplierModifyRecord> supplierModifyRecords = supplierModifyRecordMapper.selectByKeyId(id);
		page.setResult(supplierModifyRecords);
		page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
		page.setTotalPage(hPage.getPages());
		return supplierModifyRecords;

	}

	@Override
	public void excelExport4User(OutputStream output,List<SupplierSalerReturnVO>  supplierSalers){
		excelComponent.commExcelExport(output,generateRowHeaders(),supplierSalers);
	}

	private LinkedHashMap<String,String> generateRowHeaders(){
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("supplierCode","供货单位编码");
		map.put("supplierName","供货单位名称");
		map.put("name","姓名");
		map.put("idNum","身份证号");
		map.put("idValidUntilDesc","身份证有效期");
		map.put("certificateNum","授权书号");
		map.put("certificateValidUntilDesc","授权书有效期");
		map.put("authorizedVariety","授权品种");
		map.put("authorizedVarietyScope","供货单位编码");
		map.put("telephone","电话");
		map.put("fax","传真");
		map.put("mobilePhone","手机");
		map.put("email","邮箱");
		map.put("wechatNum","微信");
		map.put("qqNum","QQ");

		return map;
	}

	@Override
	public List<SupplierSaler> findSalerBySuppliereId(Long eId){

		List<SupplierSaler> supplierSalers = supplierSalerMapper.selectBySuppliereId(eId);
		return supplierSalers;
	}

	@Override
	public List<SupplierSalerReturnVO> findSalers(UserVO userVO, Integer queryType, String queryStr){

		List<SupplierSalerReturnVO> list = findSalers(userVO,queryType,null,null,null ,null);
		return list;
	}
	@Override
	public List<SupplierSalerReturnVO> findSalers(UserVO userVO, Integer queryType, String queryStr,Page page,String order,String sort){

//		Long enterprise = userComponent.getUserHeadquarters(userVO);

		Long enterprise = userVO.getEnterpriseId();

		List<SupplierSalerReturnVO> list = new ArrayList<>();

		Map<String ,Object> map = new HashMap<>();
		if(!StringUtils.isEmpty(queryStr)){
			map.put("queryStr", queryStr);
		}

		if(!StringUtils.isEmpty(order)){
			map.put("order",order);
		}

		if(!StringUtils.isEmpty(sort)) {
			map.put("sort", sort);
		}else {
			map.put("sort", "desc");
		}

		supplierComponent.setDistinguishDivisionParamMap(userVO,map);

		List<SupplierSaler> supplierSalers = new ArrayList<>();
		com.github.pagehelper.Page hPage = new com.github.pagehelper.Page();
		if(null != page)
			hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());

		if(queryType.equals(QuerySupplierSalerType.SALER.getCode())){
			supplierSalers = supplierSalerMapper.selectByParam(map);
		}else if(queryType.equals(QuerySupplierSalerType.SUPPLIER_SALER.getCode())){
			supplierSalers = supplierSalerMapper.selectBySupplierParam(map);
		}

		if(CollectionUtils.isEmpty(supplierSalers)){
			return list;
		}
		List<Long> certificateFileIds = SupplierSaler.getCertificateFileIds(supplierSalers);

		List<Long> idFiles = SupplierSaler.getIdFiles(supplierSalers);

		List<Long> supplierIds = SupplierSaler.getSupplierIds(supplierSalers);


		certificateFileIds.addAll(idFiles);

		List<File> files = new ArrayList<>();
		if(!CollectionUtils.isEmpty(certificateFileIds)){
			files = fileMapper.selectByIds(certificateFileIds);
		}

		List<Supplier> suppliers = new ArrayList<>();
		if(!CollectionUtils.isEmpty(supplierIds)) {
			suppliers = supplierMapper.selectByIds(supplierIds);
		}

		List<Long> scopes = new ArrayList<>();
		for(SupplierSaler supplierSaler : supplierSalers){
			String authorizedVarietyScope = supplierSaler.getAuthorizedVarietyScope();
			List<Long> authorizedVarietyScopes = StringSplit.strSplit(authorizedVarietyScope);
			scopes.addAll(authorizedVarietyScopes);
		}

		List<BusinessScope> businessScopes = new ArrayList<>();

		if(!CollectionUtils.isEmpty(scopes)){
			businessScopes = businessScopeMapper.getBusinessScopeByBusinessScopeId(enterprise, scopes);
		}


		boolean flag = userVO.getChainType().equals(ChainType.Headquarters.getCode());
		for(SupplierSaler supplierSaler : supplierSalers){

			SupplierSalerReturnVO supplierSalerReturnVO = SupplierSalerReturnVO.getSupplierSalerReturnVO(supplierSaler,suppliers,files,businessScopes);
			supplierSalerReturnVO.setIsOwner(supplierSalerReturnVO.getOwnerId().equals(enterprise)? 1:0);
			if(flag) {
				supplierSalerReturnVO.setFranchisedStoreFlag(supplierSalerReturnVO.geteId().equals(userVO.getEnterpriseId())? 0:1);
			}else {
				supplierSalerReturnVO.setFranchisedStoreFlag(supplierSalerReturnVO.geteId().equals(userVO.getEnterpriseId())? 1:0);
			}
			list.add(supplierSalerReturnVO);
		}

		if(null != page){
			page.setResult(list);
			page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
			page.setTotalPage(hPage.getPages());
		}

		return list;
	}

	@Override
	public List<SupplierSaler> getTransport(Long id) {
		List<SupplierSaler> list = supplierSalerMapper.getTransport(id);
		return list;
	}

}
