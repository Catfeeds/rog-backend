package com.rograndec.feijiayun.chain.business.report.quality.equipment.controller;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentVerify;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentVerifyService;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */


@Api(value = "report_quality_equipment_equipmentVerify",description = "报表-质量管理-设施与设备-验证")
@RestController
@RequestMapping("/report/quality/equipment/equipmentVerify")
@Validated
public class EquipmentVerifyReportController {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentVerifyReportController.class);

	@Autowired
	private EquipmentVerifyService equipmentService;

	@ApiOperation(value = "设施与设备-验证报表", notes = "获取数据 | 开发者 zhengbin.jin | 开发完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentReportByParam", method = RequestMethod.POST)
	public Result<Page<List<EquipmentVerify>>> getEquipmentReportByParam(HttpSession session,
																		@Valid @RequestBody RequestEquipmentListVO requestEquipmentListVO) throws Exception {
		Result<Page<List<EquipmentVerify>>> result = new Result<>();
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
			equipmentService.listEquipmentReportData(userVO, requestEquipmentListVO, page);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			logger.error("获取设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "设施与设备-验证-获取打印数据", notes = "获取数据 | 开发者 zhengbin.jin | 开发完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentPrintByParam", method = RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "requestEquipmentListEVO", value = "检索条件", required = true, dataType = "RequestEquipmentListEVO")
	})
	public Result<List<EquipmentVerify>> getEquipmentPrintByParam(HttpSession session,
																  @Valid  RequestEquipmentListEVO requestEquipmentListEVO) throws Exception {
		Result<List<EquipmentVerify>> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			List<EquipmentVerify> equipmentList = equipmentService.listEquipmentPrintData(userVO, requestEquipmentListEVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, equipmentList);
		} catch (Exception e) {
			logger.error("获取设施设备数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "设施与设备-验证-导出数据", notes = "获取数据 | 开发者 zhengbin.jin | 开发完成")
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							@Valid  RequestEquipmentListEVO requestEquipmentListEVO) throws Exception {
		String name = "验证";
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			List<EquipmentVerify> equipmentList = equipmentService.listEquipmentPrintData(userVO, requestEquipmentListEVO);
			equipmentService.excelExport(output, equipmentList, userVO);
		} catch (Exception e) {
			logger.error("导出设施设备台账错误:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
