package com.rograndec.feijiayun.chain.business.report.quality.retail.controller;

import com.rograndec.feijiayun.chain.business.report.quality.retail.service.SalePricingReportService;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestPrescriptionVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterForDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.constant.PrescriptionType;
import com.rograndec.feijiayun.chain.common.constant.status.PrescriptionStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_retail_PrescriptionRegister",description = "报表-质量管理-销售-处方登记")
@RestController
@RequestMapping("report/quality/retail/PrescriptionRegister")
@Validated
public class PrescriptionRegisterReportController {
	

	private static final Logger logger = LoggerFactory.getLogger(SpecialRegisterReportController.class);
	
	@Autowired
	private SalePricingReportService salePricingReportService;
	
	@Autowired
	private PrescriptionRegisterService prescriptionRegisterService;
	

    @ApiOperation(value = "处方登记单查询", notes = "处方单查询 | 开发者 张东东| 已联调")
    @RequestMapping(value="/getPrescriptionList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "requestPrescriptionVO", value = "检索条件", required = true, dataType = "RequestPrescriptionVO")
    public Result<Page<PrescriptionReportVO>> PrescriptionList(HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestBody RequestPrescriptionVO requestPrescriptionVO){
    	requestPrescriptionVO.setStartDate(StartAndEndDate.getStartTime(requestPrescriptionVO.getStartDate()));
    	requestPrescriptionVO.setEndDate(StartAndEndDate.getEndTime(requestPrescriptionVO.getEndDate()));
    	/*UserVO user = (UserVO) session.getAttribute("user");*/
        Result<Page<PrescriptionReportVO>> result = new Result<>();
        Integer pageNo=requestPrescriptionVO.getPageNo();
        Integer pageSize=requestPrescriptionVO.getPageSize();
        if(pageNo==null || pageSize==null){
        	result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数必填");
        	return result;
        }
        if(pageNo <= 0 || pageSize <= 0){
        	result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"分页参数不合法");
        	return result;
        }
        
        Page<PrescriptionReportVO> page = new Page<>(pageNo,pageSize);

      /*  Integer pageNo = requestPrescriptionVO.getPageNo();
        Integer pageSize = requestPrescriptionVO.getPageSize();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }*/
        salePricingReportService.getPrescriptionList(page, userVO, requestPrescriptionVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }
    
    @ApiOperation(value = "处方登记单列表导出数据", notes = "处方登记单列表导出数据 | 开发者 张东东 | 已联调")
	@ApiImplicitParam(name = "requestPrescriptionVO", value = "检索条件", required = true, dataType = "RequestPrescriptionVO")
	@RequestMapping(value = "/exportPrescriptionListExcel", method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void exportPrescriptionListExcel(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
			 RequestPrescriptionVO requestPrescriptionVO) throws Exception {
		String name = "处方登记导出";
		OutputStream output =null;
		try {
			requestPrescriptionVO.setStartDate(StartAndEndDate.getStartTime(requestPrescriptionVO.getStartDate()));
	    	requestPrescriptionVO.setEndDate(StartAndEndDate.getEndTime(requestPrescriptionVO.getEndDate()));
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			output = response.getOutputStream();
			/*HttpSession session = request.getSession(true);
			UserVO user = (UserVO) session.getAttribute("user");*/
			salePricingReportService.exportPrescriptionListExcel(output, userVO, requestPrescriptionVO);
		} catch (Exception e) {
			logger.error("处方登记单导出异常:" + e.getMessage(), e);
			e.printStackTrace();
		}finally{
			if(output!=null){
				output.flush();
				output.close();
			}
		}
	}

    @ApiOperation(value = "查询处方登记单详情", notes = "查询处方详情 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getPrescriptionDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "处方登记单id", required = true, paramType = "path")
    public Result<ResponsePrescriptionRegisterForDetailVO> getPrescriptionDetail(HttpSession session,@ApiIgnore UserVO userVO, @PathVariable("id")Long id){
       /* UserVO userVO = (UserVO) session.getAttribute("user");*/
        Result<ResponsePrescriptionRegisterForDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,salePricingReportService.getDetailById(id,userVO.getEnterpriseId()));
        return result;
    }
    
    @ApiOperation(value = "导出处方信息", notes = "导出处方信息 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/exportPrescriptionRecord/{id}",method= RequestMethod.GET)
    public Result exportPrescriptionRecord(HttpSession session,@ApiIgnore UserVO userVO, HttpServletResponse response,@PathVariable Long id) throws Exception {
      /*  UserVO userVO = (UserVO) session.getAttribute("user");*/
        Result result = new Result<>();
        PrescriptionRegister prescriptionRegister = prescriptionRegisterService.selectByPrimaryKey(id);
        		//salePricingReportService.getPrescriptionRegisterById(id);
        if(prescriptionRegister == null){
            result.setBizCodeSuccessInfo(SysCode.FAIL,"导出失败，商品不存在");
            return result;
        }
        Integer status = prescriptionRegister.getStatus();
        Integer prescriptionType = prescriptionRegister.getPrescriptionType();

        String name = "";
        if(status == PrescriptionStatus.PENDING_AUDIT){
            if(prescriptionType == PrescriptionType.TCM.getCode()){
                name = "中药处方待审核.xlsx";
            } else {
                name = "常规处方待审核.xlsx";
            }
        }else if(status == PrescriptionStatus.WAIT_MIX || status == PrescriptionStatus.CANCELED) {
            if(prescriptionType == PrescriptionType.TCM.getCode()){
                name = "中药处方待调配.xlsx";
            } else {
                name = "常规处方待调配.xlsx";
            }
        } else if(status == PrescriptionStatus.MIXED || status == PrescriptionStatus.FINISHED){
            if(prescriptionType == PrescriptionType.TCM.getCode()){
                name = "中药处方已调配.xlsx";
            } else {
                name = "常规处方已调配.xlsx";
            }
        } else {
            result.setBizCodeSuccessInfo(SysCode.FAIL,"导出失败，商品不存在");
            return result;
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        salePricingReportService.exportPrescriptionRecord(output,userVO.getEnterpriseId(),id);
        output.close();
        output.flush();
        return result;
    }
}
