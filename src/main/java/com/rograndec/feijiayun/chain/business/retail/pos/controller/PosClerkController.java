package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosClerkMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosClerkService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosClerkController   
 * @Description:  零售管理-POS管理-营业人员-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:52
 */
@Api(value="PosClerkController",description = "零售管理-POS管理-营业人员")
@RestController
@RequestMapping("/retail/pos/clerk")
public class PosClerkController {

	private static final Logger logger = LoggerFactory.getLogger(PosClerkController.class);
	
	@Autowired
	private PosClerkService posClerkService;
	
	@Autowired
	private PosClerkMapper posClerkMapper;
	
	
	@ApiOperation(value = "查看营业人员", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getClerkData", method = RequestMethod.GET)
	public Result<List<PosClerkVO>> getPosClerkData(HttpSession session) throws Exception {
		Result<List<PosClerkVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosClerkVO> list = posClerkService.getPosClerkData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看零售管理-POS管理-营业人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加营业人员", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosClerkSaveOrupdateVO posClerk) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long check = posClerkMapper.checkPosClerk(userVO.getEnterpriseId(), posClerk.getClerkId());
            if(0 != check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "营业人员称已存在！");
    			return result;
            }
            posClerkService.save(posClerk,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        } catch (Exception e) {
            logger.error("添加营业人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新营业人员", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosClerkSaveOrupdateVO posClerk) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = posClerkService.update(posClerk,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,upresult == 1 ? "更新成功" : "更新数据不存在");
        } catch (Exception e) {
            logger.error("更新营业人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据ID删除营业人员", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,@ApiParam(value = "根据id删除", required = true)@RequestParam(name = "id", required = true) String id) {
		Result<Object> result = new Result<>();
		if(StringUtils.isBlank(id)) {
			result.setBizSetCodeFallInfo(SysCode.SYS_PARAM_ERROR, "id不能为空！");
            return result;
		}
        try {
            int delresult = posClerkService.delete(Long.valueOf(id));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,delresult == 1 ? "删除成功" : "删除数据不存在");
        } catch (BusinessException e) {
            logger.error("删除营业人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("删除营业人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
}
