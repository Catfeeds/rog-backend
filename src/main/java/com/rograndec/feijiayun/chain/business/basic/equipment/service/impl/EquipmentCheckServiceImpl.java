package com.rograndec.feijiayun.chain.business.basic.equipment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentCheckMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentTypeMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentVerifyMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentCheck;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentCheckService;
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
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: EquipmentCheckServiceImpl
 * @Description: 设施设备-校准和检定-实现接口
 * @date 2017-10-16 13:18:06
 */
@Service
public class EquipmentCheckServiceImpl implements EquipmentCheckService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EquipmentCheckServiceImpl.class);

	@Autowired
	private EquipmentCheckMapper equipmentCheckMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;
	@Autowired
	private EquipmentVerifyMapper equipmentVerifyMapper;

	@Override
	public EquipmentCheck getEquipmentCheckData(Long id) throws Exception {
		return equipmentCheckMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int save(EquipmentCheck equipmentCheck, UserVO userVO) throws Exception {
		EquipmentCheck copy = (EquipmentCheck) EntityUtils.reflectAddSetDefaultValue(new EquipmentCheck().getClass(), userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(copy, equipmentCheck);
		if (userVO.getParentId() != null && userVO.getParentId() != 0) {
			equipmentCheck.setEnterpriseCode(userVO.getUserCode());
			equipmentCheck.setEnterpriseId(userVO.getEnterpriseId());
			equipmentCheck.setEnterpriseName(userVO.getEnterpriseName());
		}else{
			Long enterPriseId=equipmentCheck.getEnterpriseId();
			Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(enterPriseId);
			if(enterprise==null){
				throw new BusinessException("企业id错误,请检查数据");
			}
			equipmentCheck.setEnterpriseCode(enterprise.getCode());
			equipmentCheck.setEnterpriseId(enterprise.getId());
			equipmentCheck.setEnterpriseName(enterprise.getName());
			equipmentCheck.setParentId(enterprise.getParentId());
		}
		Long equipmentTypeId=equipmentCheck.getTypeId();
		EquipmentType equipmentType=equipmentTypeMapper.selectByPrimaryKey(equipmentTypeId);
		if(equipmentType==null){
			throw new BusinessException("类型ID错误,请检查数据");
		}
		equipmentCheck.setTypeCode(equipmentType.getCode());
		equipmentCheck.setTypeName(equipmentType.getName());
		Long equipmentId=equipmentCheck.getEquipmentId();
		Equipment equipment=equipmentMapper.selectByPrimaryKey(equipmentId);
		if(equipment==null){
			throw new BusinessException("设备id错误,请检查数据");
		}
		equipmentCheck.setEquipmentCode(equipment.getCode());
		equipmentCheck.setEquipmentName(equipment.getName());
		equipmentCheck.setStatus(0);
		return equipmentCheckMapper.insertSelective(equipmentCheck);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int update(EquipmentCheck equipmentCheck, UserVO userVO) throws Exception {
		if(equipmentCheck.getId()==null){
			return 0;
		}
		EquipmentCheck e=equipmentCheckMapper.selectByPrimaryKey(equipmentCheck.getId());
		if(e==null){
			return 0;
		}
		if (userVO.getParentId() != null && userVO.getParentId() != 0) {
			equipmentCheck.setEnterpriseCode(userVO.getUserCode());
			equipmentCheck.setEnterpriseId(userVO.getEnterpriseId());
			equipmentCheck.setEnterpriseName(userVO.getEnterpriseName());
		}else{
			Long enterPriseId=equipmentCheck.getEnterpriseId();
			Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(enterPriseId);
			if(enterprise==null){
				throw new BusinessException("企业id错误,请检查数据");
			}
			equipmentCheck.setEnterpriseCode(enterprise.getCode());
			equipmentCheck.setEnterpriseId(enterprise.getId());
			equipmentCheck.setEnterpriseName(enterprise.getName());
			equipmentCheck.setParentId(enterprise.getParentId());
		}
		Long equipmentTypeId=equipmentCheck.getTypeId();
		EquipmentType equipmentType=equipmentTypeMapper.selectByPrimaryKey(equipmentTypeId);
		if(equipmentType==null){
			throw new BusinessException("类型ID错误,请检查数据");
		}
		equipmentCheck.setTypeCode(equipmentType.getCode());
		equipmentCheck.setTypeName(equipmentType.getName());
		Long equipmentId=equipmentCheck.getEquipmentId();
		Equipment equipment=equipmentMapper.selectByPrimaryKey(equipmentId);
		if(equipment==null){
			throw new BusinessException("设备id错误,请检查数据");
		}
		equipmentCheck.setEquipmentCode(equipment.getCode());
		equipmentCheck.setEquipmentName(equipment.getName());
		equipmentCheck.setStatus(0);
		equipmentCheck.setUpdateTime(new Date());
		equipmentCheck.setModifierCode(userVO.getUserCode());
		equipmentCheck.setModifierId(userVO.getUserId());
		equipmentCheck.setModifierName(userVO.getUserName());
		return equipmentCheckMapper.updateByPrimaryKeySelective(equipmentCheck);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int delete(Long id) throws Exception {
		return equipmentCheckMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void listEquipmentCheckData(UserVO userVO, Page page, RequestEquipmentListVO requestEquipmentListVO) {
		requestEquipmentListVO.setPageNo(page.getStart());
		int count = equipmentCheckMapper.countEquipmentCheckData(requestEquipmentListVO);
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
		requestEquipmentListVO.setSort(sort);
		if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
			List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
			if(list == null) list = new ArrayList<>();
			list.add(userVO.getEnterpriseId());
			requestEquipmentListVO.setList(list);
		}
		List<EquipmentCheck> equipmentCheckList = equipmentCheckMapper.listEquipmentCheckData(requestEquipmentListVO);
		page.setTotalRecord(count);
		page.setResult(equipmentCheckList);
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
		/**
		 1. 自营店和加盟店“企业信息-业务信息-设备管理”，选择总部控制，读取总部新增的且只属于本门店的设
		 施设备
		 2. 自营店和加盟店“企业信息-业务信息-设备管理”，选择自主控制，读取本门店新增的且仅属于本门店的
		 设施设备
		 3. 总部不允许查看自主控制的门店增加的设施设备
		 */
		if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
			//如果没有选加盟店,则总部默认选择自己的
			if(requestEquipmentListVO.getChainType() == null || requestEquipmentListVO.getChainType().equals(ChainType.Headquarters.getCode())){
				requestEquipmentListVO.setList(null);
			}else {
				List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
				if (list == null) list = new ArrayList<>();
//				list.add(userVO.getEnterpriseId());
				requestEquipmentListVO.setList(list);
			}
		}
		List<EquipmentCheck> equipmentList=equipmentCheckMapper.listEquipmentReportData(requestEquipmentListVO);
		page.setResult(equipmentList);
		page.setTotalRecord(count);
	}
	@Override
	public List<EquipmentCheck> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO) {
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
			//如果没有选加盟店,则总部默认选择自己的
			if(requestEquipmentListEVO.getChainType() == null || requestEquipmentListEVO.getChainType().equals(ChainType.Headquarters.getCode())){
				requestEquipmentListEVO.setList(null);
			}else {
				List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
				if(list == null) list = new ArrayList<>();
//				list.add(userVO.getEnterpriseId());
				requestEquipmentListEVO.setList(list);
			}
		}
		List<EquipmentCheck> equipmentList=equipmentCheckMapper.listEquipmentPrintData(requestEquipmentListEVO);
		return equipmentList;
	}

	@Override
	public void excelExport(OutputStream output, List<EquipmentCheck> equipmentList, UserVO userVO) {
		//标题数据
		List<String> names = new ArrayList<>();
		names.add(userVO.getEnterpriseName());
		names.add("校准或检定");
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
		map.put("operateTypeStr", "操作类型");
		map.put("operateOrg", "操作机构");
		map.put("operator", "操作人员");
		map.put("operatePoject", "操作项目");
		map.put("technologyMan", "技术要求");
		map.put("conclusion", "操作结论");
		map.put("measures", "处理措施");
		map.put("remark", "备注");
		purchaseGeneralComponent.commExcelExport(output, map, equipmentList, names, null, "", true, new ArrayList<>());
	}


}
