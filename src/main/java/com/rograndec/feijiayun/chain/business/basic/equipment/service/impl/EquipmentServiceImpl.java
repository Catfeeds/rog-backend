package com.rograndec.feijiayun.chain.business.basic.equipment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMaintanceMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentTypeMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentVerifyMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentMaintance;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.DepartmentVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EnterprisePageVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.*;


/**
 * 
 * @ClassName: EquipmentServiceImpl   
 * @Description:  设施设备-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-13 13:26:02
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	@Autowired
	private EquipmentMaintanceMapper equipmentMaintanceMapper;
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;
	@Autowired
	private EquipmentVerifyMapper equipmentVerifyMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public Equipment getEquipmentData(UserVO userVO,Long id) throws Exception {
		Equipment equipment=equipmentMapper.selectByPrimaryKey(id);

		return equipment;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int save(Equipment equipment, UserVO userVO) throws Exception {

		checkRepeat(equipment);

//		if(userVO.getParentId()==null ||userVO.getParentId()==0){
//			equipment.setEnterpriseCode(userVO.getEnterpriseCode());
//			equipment.setEnterpriseName(userVO.getEnterpriseName());
//			equipment.setEnterpriseId(userVO.getEnterpriseId());
//		}else {
		Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(equipment.getEnterpriseId());
		equipment.setParentId(enterprise.getParentId());
		if(enterprise==null){
			throw new BusinessException("无法找到该企业,请检查企业id="+equipment.getEnterpriseId());
		}
		equipment.setEnterpriseCode(enterprise.getCode());
		equipment.setEnterpriseName(enterprise.getName());
//		}
		EquipmentType equipmentType=equipmentTypeMapper.selectByPrimaryKey(equipment.getTypeId());
		if(equipmentType==null){
			throw new BusinessException("无法找到该类型,请检查类型id="+equipment.getTypeId());
		}

		User user = userMapper.selectByPrimaryKey(equipment.getChargeManId());
		if(user != null){
			equipment.setChargeManCode(user.getCode());
			equipment.setChargeManName(user.getName());
		}


		equipment.setTypeCode(equipmentType.getCode());
		equipment.setTypeName(equipmentType.getName());
		equipment.setStatus(0);
		UserEnterpriseUtils.setUserCreateOrModify(equipment,userVO,true);
		return equipmentMapper.insertSelective(equipment);
	}

	 /**
	  * 检查编码和名称重复
	  * @param equipment
	  */
	 private void checkRepeat(Equipment equipment) {

		 ChineseString.checkCode(equipment.getCode());
		 equipment.setCode(StringUtil.trimStr(equipment.getCode()));
		 equipment.setName(StringUtil.trimStr(equipment.getName()));

		List<Equipment> resultList =  equipmentMapper.checkRepeat(equipment.getEnterpriseId(),equipment.getCode(),equipment.getName(),equipment.getId());

		if(resultList.size() > 0){
			throw  new BusinessException(SysCode.FAIL.getCode(),"编码不能重复");
		}

	 }

	 @Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int update(Equipment equipment,UserVO userVO) throws Exception {
		if(equipment.getId()==null){
			throw new BusinessException("数据不存在");
		}
		Equipment e=equipmentMapper.selectByPrimaryKey(equipment.getId());
		if(e==null){
			throw new BusinessException("数据不存在");
		}

		checkRepeat(equipment);

		 User user = userMapper.selectByPrimaryKey(equipment.getChargeManId());
		if(user != null){
			equipment.setChargeManCode(user.getCode());
			equipment.setChargeManName(user.getName());
		}

		equipment.setModifierCode(userVO.getUserCode());
		equipment.setModifierId(userVO.getUserId());
		equipment.setModifierName(userVO.getUserName());
		equipment.setUpdateTime(new Date());
		equipment.setTypeId(null);
		equipment.setTypeCode(null);
		equipment.setTypeName(null);
		equipment.setCode(null);
		equipment.setEnterpriseName(null);
		equipment.setEnterpriseCode(null);
		equipment.setEnterpriseId(null);
		return equipmentMapper.updateByPrimaryKeySelective(equipment);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int delete(Long id) throws Exception {
		List<EquipmentMaintance> equipmentMaintanceList=equipmentMaintanceMapper.getMaintanceByEquipment(id);
		if(!equipmentMaintanceList.isEmpty()){
			throw new BusinessException("该设备已进行维护,无法删除");
		}
		return equipmentMapper.deleteByPrimaryKey(id);
	}

	 @Override
	 public void listEquipmentData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page) {
		 requestEquipmentListVO.setPageNo(page.getStart());
		 requestEquipmentListVO.setEnterpriseId(userVO.getEnterpriseId());
		 
		 Integer sortCode=requestEquipmentListVO.getSortCode();
		 Integer sortECode=requestEquipmentListVO.getSortECode();
		 String sort="";
		 if(sortCode==null){
			 sort="";
		 }
		 if(sortCode!=null&&sortCode==0){
			 sort+="enterprise_code,";
		 }
		 if(sortCode!=null&&sortCode==1){
			 sort+="enterprise_code desc,";
		 }
		 if(sortECode!=null&&sortECode==0){
			 sort+="code,";
		 }
		 if(sortECode!=null&&sortECode==1){
			 sort+="code desc,";
		 }
		 if(!"".equals(sort)){
			 sort=sort.substring(0,sort.length()-1);
		 }
		 requestEquipmentListVO.setSort(sort);
		 if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
			 List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
			 if(list == null) list = new ArrayList<>();
			 list.add(userVO.getEnterpriseId());
			 requestEquipmentListVO.setList(list);
		 }
		 int count=equipmentMapper.countListEquipmentData(requestEquipmentListVO);
		 List<Equipment> equipmentList=equipmentMapper.listEquipmentData(requestEquipmentListVO);
		 page.setResult(equipmentList);
		 page.setTotalRecord(count);
	 }
	 @Override
	 public void listEquipmentReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page) {
		 requestEquipmentListVO.setPageNo(page.getStart());
		 requestEquipmentListVO.setEnterpriseId(userVO.getEnterpriseId());
		 int count=equipmentMapper.countListEquipmentReportData(requestEquipmentListVO);
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
		 List<Equipment> equipmentList=equipmentMapper.listEquipmentReportData(requestEquipmentListVO);
		 page.setResult(equipmentList);
		 page.setTotalRecord(count);
	 }
	 @Override
	 public List<Equipment> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO) {
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
		 List<Equipment> equipmentList=equipmentMapper.listEquipmentPrintData(requestEquipmentListEVO);
		 return equipmentList;
	 }

	 @Override
	 public void excelExport(OutputStream output, List<Equipment> equipmentList, UserVO userVO) {
		 //标题数据
		 List<String> names = new ArrayList<>();
		 names.add(userVO.getEnterpriseName());
		 names.add("设施设备台账");
		 //内容数据
		 LinkedHashMap<String, String> map = new LinkedHashMap<>();
		 map.put("enterpriseCode", "组织机构编码");
		 map.put("enterpriseName", "组织机构名称");
		 map.put("typeName", "类型");
		 map.put("code", "编码");
		 map.put("name", "名称");
		 map.put("specificationModel", "规格型号");
		 map.put("manufacturer", "生产厂商");
		 map.put("factoryNumber", "出厂编号");
		 map.put("classifyNumber", "分类编码");
		 map.put("measureRange", "测量范围");
		 map.put("accuracyClass", "精度等级");
		 map.put("productDateStr", "生产日期");
		 map.put("purchaseDateStr", "购置日期");
		 map.put("purchaseQuantity", "购置数量");
		 map.put("purchasePrice", "购置单价");
		 map.put("purchaseAmount", "购置金额");
		 map.put("yearLimitStr", "使用年限");
		 map.put("purpose", "用途");
		 map.put("configSite", "配置场所");
		 map.put("deptName", "所属部门");
		 map.put("checkPeriodStr", "检查周期");
		 map.put("cleanPeriodStr", "清洁周期");
		 map.put("calibrationPeriodStr", "校准周期");
		 map.put("docimasyPeriodStr", "检定周期");
		 map.put("validatePeriodStr", "验证周期");
		 map.put("chargeManName", "负责人");
		 map.put("remark", "备注");
		 purchaseGeneralComponent.commExcelExport(output, map, equipmentList, names, null, "", true, new ArrayList<>());
	 }

	 @Override
	 public List<TreePOJO<DepartmentVO>> getDepartment(UserVO userVO) {

		 List<TreePOJO<DepartmentVO>> listParent = new ArrayList<>();

		 Long eId = userVO.getEnterpriseId();
		 if (ChainType.Headquarters.getCode() != userVO.getChainType()) {
			 eId = userVO.getParentId();
		 }

		 List<DepartmentVO> departmentVOList= equipmentMapper.getDepartment(eId);

		 for (DepartmentVO dptVO:departmentVOList) {//查找所有父级
			 if(dptVO.getParentDeptId() == null ||  dptVO.getParentDeptId() == 0){

				 TreePOJO<DepartmentVO> t = new TreePOJO<>();
				 t.setData(dptVO);
				 t.setId(dptVO.getId());
				 t.setParentId(null);
				 t.setLabel(dptVO.getCode() + "-" + dptVO.getName());
				 listParent.add(t);
			 }
		 }


		 // 递归设置子级
		 for (TreePOJO<DepartmentVO> tree : listParent) {
			 DepartmentVO dptVO = tree.getData();
			 List<TreePOJO<DepartmentVO>> children = getChildDepartment(dptVO.getId(), departmentVOList);
			 tree.setChildren(children == null ? new ArrayList<>() : children);
		 }

		 return getListDepartment(listParent);
	 }



	 /**
	  * 全部为false
	  *
	  * @param listParent
	  * @return
	  */
	 private List<TreePOJO<DepartmentVO>> getListDepartment(List<TreePOJO<DepartmentVO>> listParent) {
		 for (TreePOJO<DepartmentVO> tree : listParent) {
			 tree.setSupplierShow(false);
			 if (tree.getChildren() == null) {
				 tree.setLeaf(true);
			 } else if (tree.getChildren().isEmpty() || tree.getChildren().size() == 0) {
				 tree.setLeaf(true);
			 } else {
				 getListDepartment(tree.getChildren());
			 }
		 }
		 return listParent;
	 }

	 private  List<TreePOJO<DepartmentVO>> getChildDepartment(Long id, List<DepartmentVO> departmentVOList) {


		List<TreePOJO<DepartmentVO>> childList = new ArrayList<>();

		 // 遍历第一层的 子级
		 for (DepartmentVO dptVO : departmentVOList) {
			 if (dptVO.getParentDeptId().equals(id)) {
				 TreePOJO<DepartmentVO> tree = new TreePOJO<>();
				 tree.setData(dptVO);
				 tree.setId(dptVO.getId());
				 tree.setParentId(id);
				 tree.setLabel(dptVO.getCode() + "-" + dptVO.getName());
				 childList.add(tree);
			 }
		 }
		 for (TreePOJO<DepartmentVO> tree : childList) {
			 for (DepartmentVO departmentVO : departmentVOList) {
				 DepartmentVO dptVO = (DepartmentVO) tree.getData();
				 if (dptVO.getId().equals(departmentVO.getParentDeptId())) {
					 tree.setChildren(getChildDepartment(dptVO.getId(),departmentVOList ));
				 }
			 }
		 }

		return childList;
	}




	 @Override
	 public List<Equipment> listEquipmentDataByEnterpeise(UserVO userVO, Long id,Long typeId) {
		 RequestEquipmentListVO requestEquipmentListVO=new RequestEquipmentListVO();
		 requestEquipmentListVO.setTypeId(typeId);
		 if(userVO.getParentId()!=null && userVO.getParentId()!=0){
			id=userVO.getEnterpriseId();
		 }
		 requestEquipmentListVO.setEnterpriseId(userVO.getEnterpriseId());
		 List<Equipment> equipmentList=equipmentMapper.listEquipmentData(requestEquipmentListVO);
		 return  equipmentList;
	 }



	 @Override
	 public List<EnterprisePageVO> getEqptMngHeadCtlEnt(UserVO userVO) {

		 Map<String,Object> param = new HashMap<>();
		 param.put("enterpriseId",userVO.getEnterpriseId());
		 param.put("eqptManage",0);// 设备设施 总部控制
		 List<EnterprisePageVO> entList = enterpriseMapper.getEqptMngHeadCtlEnt(param);
		 return entList;
	 }
 }
