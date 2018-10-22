package com.rograndec.feijiayun.chain.inf.pos.set.controller;

import java.util.List;

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

import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayTypeService;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosSetService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSetVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="POSSetController",description = "POS设置关接口")
@RestController
@RequestMapping("/inf/pos/set")
public class POSSetController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSSetController.class);
	
	@Autowired
	private PosSetService posSetService;
	
	@Autowired
	private PosPayTypeService posPayTypeService;
	
	
	@ApiOperation(value = "获取POS设置", notes = "获取POS设置 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getSetData", method = RequestMethod.POST)
    public Result<PosSetVO> getSetData(HttpSession session) {
		Result<PosSetVO> result = new Result<PosSetVO>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            Long enId;
            // 判断门店或者总部
            if(null != userVO.getParentId() && 0 != userVO.getParentId()) {
            	enId = userVO.getParentId();// 门店读取总部数据
            }else {
            	enId = userVO.getEnterpriseId();
            }
            PosSetVO posSetVO = posSetService.getPosSetData(enId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, posSetVO);
        } catch (Exception e) {
            logger.error("查看系统设置数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "POS修改系统设置", notes = "POS修改系统设置 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosSetVO bean) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            posSetService.saveORupdate(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"更新数据成功");
        } catch (Exception e) {
            logger.error("POS修改系统设置失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "POS获取支付方式", notes = "POS获取支付方式 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getPayTypeData", method = RequestMethod.POST)
    public Result<List<PosPayTypeVO>> getPayTypeData(HttpSession session) {
		Result<List<PosPayTypeVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            List<PosPayTypeVO> list = posPayTypeService.getPayTypeData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("POS获取支付方式数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
}
