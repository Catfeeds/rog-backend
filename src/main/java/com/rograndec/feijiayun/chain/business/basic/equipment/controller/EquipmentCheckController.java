package com.rograndec.feijiayun.chain.business.basic.equipment.controller;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentCheck;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentCheckService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

 /**
 * 
 * @ClassName: EquipmentCheckController   
 * @Description:  设施设备-校准和检定-Rest接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-16 13:18:06
 */
@Api(value="EquipmentCheckController",description = "设施设备-校准和检定")
@RestController
@RequestMapping("/basic/equipmentcheck")
public class EquipmentCheckController {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentCheckController.class);
	
	@Autowired
	private EquipmentCheckService equipmentCheckService;
		

	
	@ApiOperation(value = "设施设备-校准和检定分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentCheckByParam", method = RequestMethod.POST)
	public Result<Page<List<EquipmentCheck>>> getEquipmentCheckByParam(HttpSession session,
																	   @Valid @RequestBody RequestEquipmentListVO requestEquipmentListVO) throws Exception {
		Result<Page<List<EquipmentCheck>>> result = new Result<>();
		int pageNo=requestEquipmentListVO.getPageNo();
		int pageSize=requestEquipmentListVO.getPageSize();
        try {
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	Page page = new Page(pageNo, pageSize);
        	
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            requestEquipmentListVO.setEnterpriseId(userVO.getEnterpriseId());
            equipmentCheckService.listEquipmentCheckData(userVO,page,requestEquipmentListVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取设施设备-校准和检定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "查看设施设备-校准和检定", notes = "查看数据 | 开发者 yuting.li | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentCheck/{id}", method = RequestMethod.GET)
	public Result<EquipmentCheck> getEquipmentCheckData(HttpSession session,
																@ApiParam(value = "校准id", required = true) @PathVariable("id") Long id) throws Exception {
		Result<EquipmentCheck> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            EquipmentCheck equipmentCheck=equipmentCheckService.getEquipmentCheckData(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, equipmentCheck);
        } catch (Exception e) {
            logger.error("查看设施设备-校准和检定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加设施设备-校准和检定", notes = "添加数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody EquipmentCheck equipmentCheck) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            equipmentCheckService.save(equipmentCheck,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        }catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),e.getMessage());
			return result;
		}catch (Exception e) {
            logger.error("添加设施设备-校准和检定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新设施设备-校准和检定", notes = "更新数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody EquipmentCheck equipmentCheck) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = equipmentCheckService.update(equipmentCheck,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,upresult == 1 ? "更新成功" : "更新数据不存在");
        }catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),e.getMessage());
			return result;
		}catch (Exception e) {
            logger.error("更新设施设备-校准和检定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据ID删除设施设备-校准和检定", notes = "删除数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,
								 @ApiParam(value = "设施设备id", required = true) @PathVariable("id") Long id) {
		Result<Object> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int delresult = equipmentCheckService.delete(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,delresult == 1 ? "删除成功" : "删除数据不存在");
        } catch (Exception e) {
            logger.error("删除设施设备-校准和检定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	

}
