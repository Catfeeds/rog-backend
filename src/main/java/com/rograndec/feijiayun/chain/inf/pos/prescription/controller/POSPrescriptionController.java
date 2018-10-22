package com.rograndec.feijiayun.chain.inf.pos.prescription.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.service.POSPrescriptionService;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionParamVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionUserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.UserParamVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="POSPrescriptionController",description = "POS处方接口")
@RestController
@RequestMapping("/inf/pos/pres")
public class POSPrescriptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSPrescriptionController.class);
	
	@Autowired
	private POSPrescriptionService pOSPrescriptionService;
	
	@ApiOperation(value = "获取处方单", notes = "获取处方 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getPrescriptionData", method = RequestMethod.POST)
    public Result<List<PrescriptionVO>> getPrescriptionData(HttpSession session) {
		Result<List<PrescriptionVO>> result = new Result<>();
        try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			List<PrescriptionVO> preList = pOSPrescriptionService.selectPrescriptionDataByEnterpriseId(userVO.getEnterpriseId(), userVO.getParentId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, preList);
        } catch (Exception e) {
            logger.error("POS获取处方单失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "获取用法、用量、注意事项数据", notes = "获取用法、用量、注意事项数据 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getpharmacySetData", method = RequestMethod.POST)
    public Result<List<PharmacySetForPrescVO>> getpharmacySetData(HttpSession session, 
    		@ApiParam(required = true, value = "类型")@RequestBody PrescriptionParamVO param) {
		Result<List<PharmacySetForPrescVO>> result = new Result<>();
        try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			List<PharmacySetForPrescVO> preList = pOSPrescriptionService.selectPharmacySetData(userVO.getEnterpriseId(), userVO.getParentId(), param);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, preList);
        } catch (Exception e) {
            logger.error("POS获取用法、用量、注意事项数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "获取处方人员数据", notes = "获取处方人员数据 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getPrescriptionUserData", method = RequestMethod.POST)
    public Result<List<PrescriptionUserVO>> getPrescriptionUserData(HttpSession session, 
    		@ApiParam(required = true, value = "类型")@RequestBody UserParamVO param) {
		Result<List<PrescriptionUserVO>> result = new Result<>();
        try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			List<PrescriptionUserVO> preList = pOSPrescriptionService.selectPrescriptionUserData(userVO, param);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, preList);
        } catch (Exception e) {
            logger.error("POS获取处方人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	

}
