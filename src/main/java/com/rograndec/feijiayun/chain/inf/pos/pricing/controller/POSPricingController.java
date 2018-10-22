package com.rograndec.feijiayun.chain.inf.pos.pricing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.controller.POSPrescriptionController;
import com.rograndec.feijiayun.chain.inf.pos.pricing.service.POSSalePricingService;
import com.rograndec.feijiayun.chain.inf.pos.pricing.vo.POSSalePricingVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="POSPricingController",description = "POS划价相关接口")
@RestController
@RequestMapping("/inf/pos/pricing")
public class POSPricingController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSPricingController.class);
	
	@Autowired
	private POSSalePricingService pOSSalePricingService;
	
	@ApiOperation(value = "获取划价单", notes = "获取划价单 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getPricingData", method = RequestMethod.POST)
    public Result<List<POSSalePricingVO>> getSalePricingData(HttpSession session) {
		Result<List<POSSalePricingVO>> result = new Result<>();
        try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			List<POSSalePricingVO> preList = pOSSalePricingService.selectSalePricingDataByEnterpriseId(userVO.getEnterpriseId(), userVO.getParentId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, preList);
        } catch (Exception e) {
            logger.error("POS获取划价单失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
}
