package com.rograndec.feijiayun.chain.business.report.quality.equipment.controller;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentMaintance;
import com.rograndec.feijiayun.chain.business.basic.equipment.service.EquipmentMaintanceService;
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
 * @author lizhongyi
 */


@Api(value = "report_quality_equipment_equipmentMaintance", description = "报表-质量管理-设施与设备-检查、清洁和维护")
@RestController
@RequestMapping("/report/quality/equipment/equipmentMaintance")
@Validated
public class EquipmentMaintanceReportController {

	@Autowired
	private EquipmentMaintanceService maintanceService;

	@ApiOperation(value = "检查、清洁和维护报表", notes = "获取数据 | 开发者 zhengbin.jin | 开发完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentMaintanceReportByParam", method = RequestMethod.POST)
	public Result<Page<List<EquipmentMaintance>>> getEquipmentReportByParam(HttpSession session,
																			@Valid @RequestBody RequestEquipmentListVO requestEquipmentListVO) throws Exception {
		Result<Page<List<EquipmentMaintance>>> result = new Result<>();
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
			maintanceService.listEquipmentMaintanceReportData(userVO, requestEquipmentListVO, page);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "检查、清洁和维护报表-获取打印数据", notes = "获取数据 | 开发者 zhengbin.jin | 开发完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getEquipmentPrintByParam", method = RequestMethod.POST)
	public Result<List<EquipmentMaintance>> getEquipmentPrintByParam(HttpSession session,
																	 @Valid @RequestBody RequestEquipmentListEVO requestEquipmentListEVO) throws Exception {
		Result<List<EquipmentMaintance>> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			List<EquipmentMaintance> equipmentList = maintanceService.listEquipmentPrintData(userVO, requestEquipmentListEVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, equipmentList);
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "检查、清洁和维护报表-导出数据", notes = "获取数据 | 开发者 zhengbin.jin | 开发完成")
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({@ApiImplicitParam(name = "requestEquipmentListEVO", value = "检索条件", required = true, dataType = "RequestEquipmentListEVO")
	})
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, RequestEquipmentListEVO requestEquipmentListEVO) throws Exception {
		String name = "检查、清洁和维护";
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			List<EquipmentMaintance> equipmentList = maintanceService.listEquipmentPrintData(userVO, requestEquipmentListEVO);
			maintanceService.excelExport(output, equipmentList, userVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
