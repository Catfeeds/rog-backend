package com.rograndec.feijiayun.chain.business.basic.equipment.service;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: EquipmentTypeService   
 * @Description:  设备类型-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-13 11:11:11
 */
public interface EquipmentTypeService {
	
	
	List<EquipmentType> getEquipmentTypeData(UserVO userVO) throws Exception;
	
}
