package com.rograndec.feijiayun.chain.business.basic.equipment.controller;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.DepartmentVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EnterprisePageVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.status.DistrOutStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: EquipmentController
 * @Description: 设施设备-Rest接口
 * @date 2017-10-13 13:26:02
 */
@Api(value = "EquipmentController", description = "设施设备")
@RestController
@RequestMapping("/basic/equipment")
public class EquipmentController {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

	@Autowired
	private EquipmentService equipmentService;



	@ApiOperation(value = "查询设备管理 选择总部控制的门店", notes = "查询设备管理 选择总部控制的门店 | 开发者 杜东阳 | 开发完成")
	@RequestMapping(value = "/getEqptMngHeadCtlEnt", method = RequestMethod.GET)
	public Result<List<EnterprisePageVO>> getChildEnterprise(HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute("user");
		Result<List<EnterprisePageVO>> result = new Result<>();
		List<EnterprisePageVO> enterpriseList = equipmentService.getEqptMngHeadCtlEnt(userVO);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseList);
		return result;
	}



	@ApiOperation(value = "获取部门信息", notes = "获取数据 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDepartment", method = RequestMethod.GET)
	public Result<List<TreePOJO<DepartmentVO>>> getDepartment(@ApiIgnore UserVO userVO){

		Result<List<TreePOJO<DepartmentVO>>> result = new Result<>();

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,equipmentService.getDepartment(userVO));

		return result;

	}




	@ApiOperation(value = "设施设备分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentByParam", method = RequestMethod.POST)
	public Result<Page<List<Equipment>>> getEquipmentByParam(HttpSession session,
															 @Valid @RequestBody RequestEquipmentListVO requestEquipmentListVO) throws Exception {
		Result<Page<List<Equipment>>> result = new Result<>();
		int pageNo = requestEquipmentListVO.getPageNo();
		int pageSize = requestEquipmentListVO.getPageSize();
		try {
			if (pageNo <= 0 || pageSize <= 0) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			Page page = new Page(pageNo, pageSize);
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			equipmentService.listEquipmentData(userVO, requestEquipmentListVO, page);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			logger.error("获取设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "设施设备列表(无分页)", notes = "获取数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentByEnterpeise/{id}/{typeId}", method = RequestMethod.GET)
	public Result<List<Equipment>> getEquipmentByParam(HttpSession session,
													   @ApiParam(value = "设施设备id", required = true) @PathVariable("id") Long id,
													   @ApiParam(value = "设施设备类型id", required = true) @PathVariable("typeId") Long typeId) throws Exception {
		Result<List<Equipment>> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			List<Equipment> equipmentList = equipmentService.listEquipmentDataByEnterpeise(userVO, id,typeId);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, equipmentList);
		} catch (Exception e) {
			logger.error("获取设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查看设施设备", notes = "查看数据 | 开发者 yuting.li | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipment/{id}", method = RequestMethod.GET)
	public Result<Equipment> getEquipmentData(HttpSession session,
											  @ApiParam(value = "设施设备id", required = true) @PathVariable("id") Long id) throws Exception {
		Result<Equipment> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			Equipment equipment = equipmentService.getEquipmentData(userVO, id);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, equipment);
		} catch (Exception e) {
			logger.error("查看设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "添加设施设备", notes = "添加数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session, @Valid @RequestBody Equipment equipment) throws Exception {
		Result<String> result = new Result<>();
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			equipmentService.save(equipment, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加成功");
		return result;
	}

	@ApiOperation(value = "更新设施设备", notes = "更新数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session, @Valid @RequestBody Equipment equipment) {
		Result<String> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			int upresult = equipmentService.update(equipment, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, upresult == 1 ? "更新成功" : "更新数据不存在");
		} catch (BusinessException e) {
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("更新设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "根据ID删除设施设备", notes = "删除数据 | 开发者 zhengbin.jin | 联调完", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,
								 @ApiParam(value = "设施设备id", required = true) @PathVariable("id") String id) {
		Result<Object> result = new Result<>();
		if (StringUtils.isBlank(id)) {
			result.setBizSetCodeFallInfo(SysCode.SYS_PARAM_ERROR, "id不能为空！");
			return result;
		}
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			int delresult = equipmentService.delete(Long.valueOf(id));
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, delresult == 1 ? "删除成功" : "删除数据不存在");
		} catch (BusinessException e) {
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("删除设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

}
