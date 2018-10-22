package com.rograndec.feijiayun.chain.business.basic.equipment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMaintanceMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentTypeMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentVerifyMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentMaintance;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentMaintanceService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EquipmentMaintanceServiceImpl implements EquipmentMaintanceService {

    @Autowired
    private EquipmentMaintanceMapper maintanceMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private EquipmentMapper  equipmentMapper;

    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private EquipmentVerifyMapper equipmentVerifyMapper;

    @Override
    public void delete(Long id) {
        maintanceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page getPageList(EquipmentMaintanceRequestVO requestVO, UserVO userVO) {

        // 总部的要查总部及其门店下的
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            requestVO.setParentId(userVO.getEnterpriseId());
        }

        requestVO.setEnterpriseId(userVO.getEnterpriseId());

        if ("enterpriseCode".equals(requestVO.getOrder())) {
            // 企业编码升序
            requestVO.setOrder("enterprise_code");
        }

        if("equipmentCode".equals(requestVO.getOrder())){
            requestVO.setOrder("equipment_code");
        }

        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
            if(list == null) list = new ArrayList<>();
            list.add(userVO.getEnterpriseId());
            requestVO.setList(list);
        }

        Page<List<EquipmentMaintanceVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());
        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        requestVO.setPageStart(page.getStart());

        List<EquipmentMaintanceVO> dataList = maintanceMapper.getMaintancePageList(requestVO);

        Integer totalCount  =    maintanceMapper.getMaintancePageCount(requestVO);

        page.setResult(dataList);
        page.setTotalRecord(totalCount);

        return page;
    }

    @Override
    public void save(EquipmentMaintanceVO maintanceVO, UserVO loginUser) throws Exception {

        EquipmentMaintance maintance =  (EquipmentMaintance)EntityUtils.reflectAddSetDefaultValue(EquipmentMaintance.class,loginUser);
        maintance.setStatus(EnableStatus.ENABLE.getStatus());

        BeanUtils.copyProperties(maintanceVO,maintance);
        // 企业信息
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(maintanceVO.getEnterpriseId());
        if(enterprise == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"无法找到该企业,请检查企业id="+maintanceVO.getEnterpriseId());

        }
        maintance.setEnterpriseCode(enterprise.getCode());
        maintance.setEnterpriseName(enterprise.getName());
        maintance.setParentId(enterprise.getParentId());
        // 设备信息
        Equipment equipment = equipmentMapper.selectByPrimaryKey(maintanceVO.getEquipmentId());
        if(equipment == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"无法找到该设备,请检查设备id="+maintanceVO.getEquipmentId());

        }

        maintance.setEquipmentCode(equipment.getCode());
        maintance.setEquipmentName(equipment.getName());

        // 类型信息
        EquipmentType equipmentType = equipmentTypeMapper.selectByPrimaryKey(maintanceVO.getTypeId());
        if(equipmentType == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"无法找到该设备类型,请检查设备类型id="+maintanceVO.getTypeId());
        }
        maintance.setTypeCode(equipmentType.getCode());
        maintance.setTypeName(equipmentType.getName());

        // 时间校验
        if(maintance.getStartDate().after(maintance.getEndDate())){
            throw  new BusinessException(SysCode.FAIL.getCode(),"开始时间不能大于结束时间");
        }


        User user = userMapper.selectByPrimaryKey(maintanceVO.getOperatorId());
        if(user != null){
            maintance.setOperatorCode(user.getCode());
            maintance.setOperatorName(user.getName());
        }


        maintanceMapper.insertSelective(maintance);

    }

    @Override
    public void update(EquipmentMaintanceVO maintanceVO, UserVO loginUser) throws Exception{
        EquipmentMaintance maintance =  (EquipmentMaintance)EntityUtils.reflectUpdateSetDefaultValue(EquipmentMaintance.class,loginUser);

        BeanUtils.copyProperties(maintanceVO,maintance);


        // 时间校验
        if(maintance.getStartDate().after(maintance.getEndDate())){
            throw  new BusinessException(SysCode.FAIL.getCode(),"开始时间不能大于结束时间");
        }

        User user = userMapper.selectByPrimaryKey(maintanceVO.getOperatorId());
        if(user != null){
            maintance.setOperatorCode(user.getCode());
            maintance.setOperatorName(user.getName());
        }

        maintanceMapper.updateByPrimaryKeySelective(maintance);

    }

    @Override
    public EquipmentMaintanceVO get(Long id) {

        EquipmentMaintanceVO equipmentMaintanceVO = maintanceMapper.get(id);
        //equipmentMaintanceVO.setOperateTypeName(OperationTypeEnum.getName(equipmentMaintanceVO.getOperateType()));
        //equipmentMaintanceVO.setEquipmentStatusName(EquipmentStatusType.getValue(equipmentMaintanceVO.getEquipmentStatus()));

        return equipmentMaintanceVO;
    }

    @Override
    public List<EquipmentSimpleVO> getEquipmentSimpleVOList(UserVO userVO, Long enterpriseId, Long typeId) {
        List<Long> list = null;
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
            if(list == null) list = new ArrayList<>();
            list.add(userVO.getEnterpriseId());
        }
        return maintanceMapper.getEquipmentSimpleVOList(enterpriseId,typeId,list);
    }

    @Override
    public void listEquipmentMaintanceReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page) {
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
            sort+="a.equipment_code,";
        }
        if(sortECode!=null&&sortECode==1){
            sort+="a.equipment_code desc,";
        }
        if(!"".equals(sort)){
            sort=sort.substring(0,sort.length()-1);
        }
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            if(requestEquipmentListVO.getChainType() == null || requestEquipmentListVO.getChainType().equals(ChainType.Headquarters.getCode())){
                requestEquipmentListVO.setList(null);
            }else {
                List<Long> list = equipmentVerifyMapper.listEnterpriseIds(userVO.getEnterpriseId());
                if (list == null) list = new ArrayList<>();
//                list.add(userVO.getEnterpriseId());
                requestEquipmentListVO.setList(list);
            }
        }
        requestEquipmentListVO.setSort(sort);
        List<EquipmentMaintance> equipmentList=maintanceMapper.listEquipmentReportData(requestEquipmentListVO);
        page.setResult(equipmentList);
        page.setTotalRecord(maintanceMapper.countListEquipmentReportData(requestEquipmentListVO));

    }

    @Override
    public List<EquipmentMaintance> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO) {
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
			sort+="a.equipment_code,";
		}
		if(sortECode!=null&&sortECode==1){
			sort+="a.equipment_code desc,";
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
//                list.add(userVO.getEnterpriseId());
                requestEquipmentListEVO.setList(list);
            }
        }
        List<EquipmentMaintance> equipmentList=maintanceMapper.listEquipmentPrintData(requestEquipmentListEVO);
		return equipmentList;
    }

    @Override
    public void excelExport(OutputStream output, List<EquipmentMaintance> equipmentList, UserVO userVO) {
		//标题数据
		List<String> names = new ArrayList<>();
		names.add(userVO.getEnterpriseName());
		names.add("检查、清洁和维护");
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
		map.put("operatorName", "操作人员");
		map.put("operateContent", "操作内容");
		map.put("operateResult", "操作结果");
		map.put("measures", "处理措施");
		map.put("remark", "备注");
		purchaseGeneralComponent.commExcelExport(output, map, equipmentList, names, null, "", true, new ArrayList<>());
    }


}
