package com.rograndec.feijiayun.chain.business.basic.equipment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentTypeMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentTypeService;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 /**
 * 
 * @ClassName: EquipmentTypeServiceImpl   
 * @Description:  设备类型-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-13 11:11:11
 */
@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EquipmentTypeServiceImpl.class);
	
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	
	@Override
	public List<EquipmentType> getEquipmentTypeData(UserVO userVO) throws Exception {
		List<EquipmentType> list = equipmentTypeMapper.getParentEquipmentTypeData();
		for (EquipmentType equipmentType : list) {
			equipmentType.setEquipmentTypeList(equipmentTypeMapper.selectByParent(equipmentType.getCode()));
		}
		return list;
	}

}
