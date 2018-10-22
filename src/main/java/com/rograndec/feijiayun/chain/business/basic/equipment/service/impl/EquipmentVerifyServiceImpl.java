package com.rograndec.feijiayun.chain.business.basic.equipment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentTypeMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentVerifyMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentVerify;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentVerifyService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


 /**
 * 
 * @ClassName: EquipmentVerifyServiceImpl   
 * @Description:  设施设备-验证-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-16 13:18:19
 */
@Service
public class EquipmentVerifyServiceImpl implements EquipmentVerifyService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EquipmentVerifyServiceImpl.class);
	
	@Autowired
	private EquipmentVerifyMapper equipmentVerifyMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Override
	public EquipmentVerify getEquipmentVerifyData(Long  id) throws Exception {
		EquipmentVerify equipmentVerify=equipmentVerifyMapper.selectByPrimaryKey(id);
		return equipmentVerify;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int save(EquipmentVerify equipmentVerify, UserVO userVO) throws Exception {
		EquipmentVerify copy = (EquipmentVerify)EntityUtils.reflectAddSetDefaultValue(new EquipmentVerify().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(copy,equipmentVerify);
		if (userVO.getParentId() != null && userVO.getParentId() != 0) {
			equipmentVerify.setEnterpriseCode(userVO.getUserCode());
			equipmentVerify.setEnterpriseId(userVO.getEnterpriseId());
			equipmentVerify.setEnterpriseName(userVO.getEnterpriseName());
		}else{
			Long enterPriseId=equipmentVerify.getEnterpriseId();
			Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(enterPriseId);
			if(enterprise==null){
				throw new BusinessException("企业id错误,请检查数据");
			}
			equipmentVerify.setEnterpriseCode(enterprise.getCode());
			equipmentVerify.setEnterpriseId(enterprise.getId());
			equipmentVerify.setEnterpriseName(enterprise.getName());
			equipmentVerify.setParentId(enterprise.getParentId());
		}
		Long equipmentTypeId=equipmentVerify.getTypeId();
		EquipmentType equipmentType=equipmentTypeMapper.selectByPrimaryKey(equipmentTypeId);
		if(equipmentType==null){
			throw new BusinessException("类型ID错误,请检查数据");
		}
		equipmentVerify.setTypeCode(equipmentType.getCode());
		equipmentVerify.setTypeName(equipmentType.getName());
		Long equipmentId=equipmentVerify.getEquipmentId();
		Equipment equipment=equipmentMapper.selectByPrimaryKey(equipmentId);
		if(equipment==null){
			throw new BusinessException("设备id错误,请检查数据");
		}
		equipmentVerify.setEquipmentCode(equipment.getCode());
		equipmentVerify.setEquipmentName(equipment.getName());
		equipmentVerify.setStatus(0);
		return equipmentVerifyMapper.insertSelective(equipmentVerify);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int update(EquipmentVerify equipmentVerify,UserVO userVO) throws Exception {
		if(equipmentVerify.getId()==null){
			return 0;
		}
		EquipmentVerify e=equipmentVerifyMapper.selectByPrimaryKey(equipmentVerify.getId());
		if(e==null){
			return 0;
		}
		if (userVO.getParentId() != null && userVO.getParentId() != 0) {
			equipmentVerify.setEnterpriseCode(userVO.getUserCode());
			equipmentVerify.setEnterpriseId(userVO.getEnterpriseId());
			equipmentVerify.setEnterpriseName(userVO.getEnterpriseName());
		}else{
			Long enterPriseId=equipmentVerify.getEnterpriseId();
			Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(enterPriseId);
			if(enterprise==null){
				throw new BusinessException("企业id错误,请检查数据");
			}
			equipmentVerify.setEnterpriseCode(enterprise.getCode());
			equipmentVerify.setEnterpriseId(enterprise.getId());
			equipmentVerify.setEnterpriseName(enterprise.getName());
			equipmentVerify.setParentId(enterprise.getParentId());
		}
		Long equipmentTypeId=equipmentVerify.getTypeId();
		EquipmentType equipmentType=equipmentTypeMapper.selectByPrimaryKey(equipmentTypeId);
		if(equipmentType==null){
			throw new BusinessException("类型ID错误,请检查数据");
		}
		equipmentVerify.setTypeCode(equipmentType.getCode());
		equipmentVerify.setTypeName(equipmentType.getName());
		Long equipmentId=equipmentVerify.getEquipmentId();
		Equipment equipment=equipmentMapper.selectByPrimaryKey(equipmentId);
		if(equipment==null){
			throw new BusinessException("设备id错误,请检查数据");
		}
		equipmentVerify.setEquipmentCode(equipment.getCode());
		equipmentVerify.setEquipmentName(equipment.getName());
		equipmentVerify.setStatus(0);
		equipmentVerify.setUpdateTime(new Date());
		equipmentVerify.setModifierCode(userVO.getUserCode());
		equipmentVerify.setModifierId(userVO.getUserId());
		equipmentVerify.setModifierName(userVO.getUserName());
		return equipmentVerifyMapper.updateByPrimaryKeySelective(equipmentVerify);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int delete(Long id) throws Exception {
		return equipmentVerifyMapper.deleteByPrimaryKey(id);
	}

	 @Override
	 public void listEquipmentVerifyData(RequestEquipmentListVO requestEquipmentListVO, Page page,UserVO userVO) {
		 requestEquipmentListVO.setPageNo(page.getStart());
		 Integer sortCode = requestEquipmentListVO.getSortCode();
		 Integer sortECode = requestEquipmentListVO.getSortECode();
		 String sort = "";
		 if (sortCode == null) {
			 sort = "";
		 }
		 if (sortCode != null && sortCode == 0) {
			 sort += "enterprise_code,";
		 }
		 if (sortCode != null && sortCode == 1) {
			 sort += "enterprise_code desc,";
		 }
		 if (sortECode != null && sortECode == 0) {
			 sort += "equipment_code,";
		 }
		 if (sortECode != null && sortECode == 1) {
			 sort += "equipment_code desc,";
		 }
		 if (!"".equals(sort)) {
			 sort = sort.substring(0, sort.length() - 1);
		 }
		 if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
			 List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
			 if(list == null) list = new ArrayList<>();
			 list.add(userVO.getEnterpriseId());
			 requestEquipmentListVO.setList(list);
		 }
		 requestEquipmentListVO.setSort(sort);
		 int count = equipmentVerifyMapper.countEquipmentVerifyData(requestEquipmentListVO);
		 List<EquipmentVerify> equipmentVerifyList = equipmentVerifyMapper.listEquipmentVerifyData(requestEquipmentListVO);
		 page.setTotalRecord(equipmentVerifyMapper.countEquipmentVerifyData(requestEquipmentListVO));
		 page.setResult(equipmentVerifyList);
	 }

	 @Override
	 public void listEquipmentReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page) {
		 requestEquipmentListVO.setPageNo(page.getStart());
		 requestEquipmentListVO.setEnterpriseId(userVO.getEnterpriseId());
		
		 Integer sortCode=requestEquipmentListVO.getSortCode();
		 Integer sortECode=requestEquipmentListVO.getSortECode();
		 String sort="";
		 if(sortCode==null){
			 sort="";
		 }
		 if(sortCode!=null&&sortCode==0){
			 sort+="a.enterprise_code,";
		 }
		 if(sortCode!=null&&sortCode==1){
			 sort+="a.enterprise_code desc,";
		 }
		 if(sortECode!=null&&sortECode==0){
			 sort+="a.code,";
		 }
		 if(sortECode!=null&&sortECode==1){
			 sort+="a.code desc,";
		 }
		 if(!"".equals(sort)){
			 sort=sort.substring(0,sort.length()-1);
		 }
		 requestEquipmentListVO.setSort(sort);
		 
		 if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
			 if(requestEquipmentListVO.getChainType() == null || requestEquipmentListVO.getChainType().equals(ChainType.Headquarters.getCode())){
				 requestEquipmentListVO.setList(null);
			 }else {

				 List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
				 if (list == null) list = new ArrayList<>();
//				 list.add(userVO.getEnterpriseId());
				 requestEquipmentListVO.setList(list);
			 }
		 }
		 int count=equipmentVerifyMapper.countListEquipmentReportData(requestEquipmentListVO);
		 List<EquipmentVerify> equipmentList=equipmentVerifyMapper.listEquipmentReportData(requestEquipmentListVO);
		 page.setResult(equipmentList);
		 page.setTotalRecord(count);
	 }
	 @Override
	 public List<EquipmentVerify> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO) {
		 requestEquipmentListEVO.setEnterpriseId(userVO.getEnterpriseId());
		 Integer sortCode=requestEquipmentListEVO.getSortCode();
		 Integer sortECode=requestEquipmentListEVO.getSortECode();
		 String sort="";
		 if(sortCode==null){
			 sort="";
		 }
		 if(sortCode!=null&&sortCode==0){
			 sort+="a.enterprise_code,";
		 }
		 if(sortCode!=null&&sortCode==1){
			 sort+="a.enterprise_code desc,";
		 }
		 if(sortECode!=null&&sortECode==0){
			 sort+="a.code,";
		 }
		 if(sortECode!=null&&sortECode==1){
			 sort+="a.code desc,";
		 }
		 if(!"".equals(sort)){
			 sort=sort.substring(0,sort.length()-1);
		 }
		 requestEquipmentListEVO.setSort(sort);
		 if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
			 if(requestEquipmentListEVO.getChainType() == null || requestEquipmentListEVO.getChainType().equals(ChainType.Headquarters.getCode())){
				 requestEquipmentListEVO.setList(null);
			 }else {
				 List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
				 if (list == null) list = new ArrayList<>();
//				 list.add(userVO.getEnterpriseId());
				 requestEquipmentListEVO.setList(list);
			 }
		 }

		 List<EquipmentVerify> equipmentList=equipmentVerifyMapper.listEquipmentPrintData(requestEquipmentListEVO);
		 return equipmentList;
	 }

	 @Override
	 public void excelExport(OutputStream output, List<EquipmentVerify> equipmentList, UserVO userVO) {
		 //标题数据
		 List<String> names = new ArrayList<>();
		 names.add(userVO.getEnterpriseName());
		 names.add("验证");
		 //内容数据
		 LinkedHashMap<String, String> map = new LinkedHashMap<>();
		 map.put("enterpriseCode", "组织机构编码");
		 map.put("enterpriseName", "组织机构名称");
		 map.put("typeName", "类型");
		 map.put("equipmentCode", "编码");
		 map.put("equipmentName", "名称");
		 map.put("startDateStr", "开始时间");
		 map.put("endDateStr", "结束时间");
		 map.put("equipmentStatusStr", "设备状况");
		 map.put("actualizeGroup", "验证实施小组");
		 map.put("programNumber", "验证方案编码");
		 map.put("reportNumber", "验证报告编号");
		 map.put("modifierName", "操作人员");
		 map.put("draftMan", "验证报告起草人员");
		 map.put("auditMan", "验证报告审核人员");
		 map.put("approvalDateStr", "验证报告批准日期");
		 map.put("evaluate", "评价");
		 map.put("deviationHandle", "偏差处理");
		 map.put("conclusion", "验证结论");
		 map.put("measures", "预防措施");
		 map.put("remark", "备注");
		 purchaseGeneralComponent.commExcelExport(output, map, equipmentList, names, null, "", true, new ArrayList<>());
	 }


 }
