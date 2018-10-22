package com.rograndec.feijiayun.chain.inf.pos.shift.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.shift.service.POSShiftService;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSAddShiftVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSCommitShiftVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftAddReturnVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="POSPayeeShiftController",description = "POS开班/交班接口")
@RestController
@RequestMapping("/inf/pos/shift")
public class POSPayeeShiftController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSPayeeShiftController.class);
	
	@Autowired
	private POSShiftService pOSShiftService;
	
	@Autowired
	private PosPayeeMapper posPayeeMapper;
	
	@Autowired
	private PayeeOpeningShiftMapper payeeOpeningShiftMapper;
	
	@Autowired
	private UserAdministrationMapper userAdministrationMapper;

	@ApiOperation(value = "获取开班/交班数据", notes = "获取开班/交班数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getPayeeShiftData", method = RequestMethod.POST)
    public Result<POSShiftVO> getPayeeShiftData(HttpSession session) {
		Result<POSShiftVO> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            POSShiftVO pvo = pOSShiftService.getShiftByEnterpriseId(userVO.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pvo);
		} catch (Exception e) {
			logger.error("POS获取开班/交班数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "验证是否交班", notes = "验证是否交班 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/checkPayeeShift", method = RequestMethod.POST)
    public Result checkPayeeShift(HttpSession session,@ApiParam(required = true, value = "交班Id")@RequestBody Long shiftId) {
		Result result = new Result<>();
        try {
        	PayeeOpeningShift osf = payeeOpeningShiftMapper.selectByPrimaryKey(shiftId);
        	if(null == osf) {
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), "交班数据不存在");
    			return result;
        	} else {
        		POSShiftVO psfVO = new POSShiftVO();
        		if(null != osf.getShiftTime()) {
        			psfVO.setFlagShift(true);//已交班
        		} else {
        			psfVO.setFlagShift(false);
        		}
        		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(osf,psfVO);
        		result.setBizCodeSuccessInfo(SysCode.SUCCESS, psfVO);
        	}
		} catch (Exception e) {
			logger.error("POS验证是否交班失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "添加开班数据", notes = "添加开班数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/setPayeeShiftData", method = RequestMethod.POST)
    public Result<Object> setPayeeShiftData(HttpSession session,@Valid @RequestBody POSAddShiftVO svo) {
		Result<Object> result = new Result<>();
		try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            // 验证用户
            Map<String,Object> payee = posPayeeMapper.getPayeeIdAndEnterpriseId(userVO.getEnterpriseId(), svo.getPayeeId());
    		if(null != payee && payee.size()>0) {
    			Map<String, Object> ua = userAdministrationMapper.getUserAccount(svo.getPayeeId());
    			if(null == ua || null == ua.get("login_account")) {
    				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), "款员对应的账号不存在");
        			return result;
    			}
    			svo.setOpeningAccount(ua.get("login_account")+"");
    			svo.setPayeeCode(payee.get("payeeCode")+"");
    			svo.setPayeeName(payee.get("payeeName")+"");
    		} else {
    			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), "款员id不存在");
    			return result;
    		}
    		POSShiftAddReturnVO psfVO = pOSShiftService.saveReturnShift(svo, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, psfVO != null ? psfVO : "没有生成开班数据");
		} catch (Exception e) {
			logger.error("POS添加开班数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "添加交班数据", notes = "添加交班数据| 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/updatePayeeShiftData", method = RequestMethod.POST)
    public Result<String> updatePayeeShiftData(HttpSession session,@Valid @RequestBody POSCommitShiftVO svo) {
		Result<String> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
			// 验证用户
            Map<String,Object> payee = posPayeeMapper.getPayeeIdAndEnterpriseId(userVO.getEnterpriseId(), svo.getPayeeId());
    		if(null != payee && payee.size()>0) {
    			Map<String, Object> ua = userAdministrationMapper.getUserAccount(svo.getPayeeId());
    			if(null == ua || null == ua.get("login_account")) {
    				result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "款员对应的账号不存在");
        			return result;
    			}
    			svo.setOpeningAccount(ua.get("login_account")+"");
    			svo.setPayeeCode(payee.get("payeeCode")+"");
    			svo.setPayeeName(payee.get("payeeName")+"");
    		} else {
    			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "款员id不存在");
    			return result;
    		}
            int resave = pOSShiftService.updateShift(svo, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, resave > 0 ? resave+"" : "没有生成交班数据");
		} catch (BusinessException e) {
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
		} catch (Exception e) {
			logger.error("POS添加开班数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		} 
        return result;
    }
	
}
