package com.rograndec.feijiayun.chain.inf.pos.special.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.pricing.controller.POSPricingController;
import com.rograndec.feijiayun.chain.inf.pos.pricing.service.POSSalePricingService;
import com.rograndec.feijiayun.chain.inf.pos.pricing.vo.POSSalePricingVO;
import com.rograndec.feijiayun.chain.inf.pos.special.service.POSSpecialRegisterService;
import com.rograndec.feijiayun.chain.inf.pos.special.vo.POSSpecialRegisterVO;

@Api(value="POSSpecialRegisterController",description = "POS专管相关接口")
@RestController
@RequestMapping("/inf/pos/special")
public class POSSpecialRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(POSSpecialRegisterController.class);
	
	@Autowired
	private POSSpecialRegisterService pOSSpecialRegisterService;
	
	@ApiOperation(value = "获取专管登记", notes = "获取专管登记 | 开发者 刘群 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getPricingData", method = RequestMethod.POST)
    public Result<List<POSSpecialRegisterVO>> getSalePricingData(HttpSession session) {
		Result<List<POSSpecialRegisterVO>> result = new Result<>();
        try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			List<POSSpecialRegisterVO> preList = pOSSpecialRegisterService.selectSpecialRegisterDataByEnterpriseId(userVO.getEnterpriseId(), userVO.getParentId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, preList);
        } catch (Exception e) {
            logger.error("POS获取专管登记失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
