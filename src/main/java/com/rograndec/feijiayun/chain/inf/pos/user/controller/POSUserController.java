package com.rograndec.feijiayun.chain.inf.pos.user.controller;

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
import com.rograndec.feijiayun.chain.inf.pos.user.service.POSUserService;
import com.rograndec.feijiayun.chain.inf.pos.user.vo.SelectPOSPayeeTeamVO;
import com.rograndec.feijiayun.chain.inf.pos.user.vo.SelectPOSclerkVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="POSUserController",description = "POS用户相关接口")
@RestController
@RequestMapping("/inf/pos/user")
public class POSUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSUserController.class);
	
	@Autowired
	private POSUserService pOSUserService;
	
	@ApiOperation(value = "获取营业员", notes = "获取营业员 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getClerkData", method = RequestMethod.POST)
    public Result<List<SelectPOSclerkVO>> getClerkData(HttpSession session) {
		Result<List<SelectPOSclerkVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            List<SelectPOSclerkVO> list = pOSUserService.getPosClerk(userVO.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		} catch (Exception e) {
			logger.error("POS获取获取营业员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "获取款员数据", notes = "获取款员数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getPayeeData", method = RequestMethod.POST)
    public Result<SelectPOSPayeeTeamVO> getPayeeData(HttpSession session) {
		Result<SelectPOSPayeeTeamVO> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            SelectPOSPayeeTeamVO spvo = pOSUserService.findByEnterpriseId(userVO.getEnterpriseId(), userVO.getUserId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, spvo);
		} catch (Exception e) {
			logger.error("POS获取款员开班数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
}
