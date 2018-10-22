package com.rograndec.feijiayun.chain.business.basic.equipment.controller;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentTypeService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

 /**
 * 
 * @ClassName: EquipmentTypeController   
 * @Description:  设备类型-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-13 11:11:11
 */
@Api(value="EquipmentTypeController",description = "设备类型")
@RestController
@RequestMapping("/basic/equipmentType")
public class EquipmentTypeController {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentTypeController.class);
	
	@Autowired
	private EquipmentTypeService equipmentTypeService;
		

	
	@ApiOperation(value = "设备类型列表", notes = "获取数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentTypeByParam", method = RequestMethod.GET)
	public Result<List<EquipmentType>> getEquipmentTypeByParam(HttpSession session) throws Exception {
		Result<List<EquipmentType>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
			List<EquipmentType> list=equipmentTypeService.getEquipmentTypeData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("获取设备类型数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	

}
