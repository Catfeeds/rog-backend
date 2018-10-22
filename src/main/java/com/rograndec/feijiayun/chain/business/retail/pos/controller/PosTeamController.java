package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosTeamMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosTeamService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSelectVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosTeamController   
 * @Description:  零售管理-POS管理-班组-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:24
 */
@Api(value="PosTeamController",description = "零售管理-POS管理-班组")
@RestController
@RequestMapping("/retail/pos/team")
public class PosTeamController {

	private static final Logger logger = LoggerFactory.getLogger(PosTeamController.class);
	
	@Autowired
	private PosTeamService posTeamService;
	
	@Autowired
	private PosTeamMapper posTeamMapper;
		
	
	@ApiOperation(value = "查看班组", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getTeamData", method = RequestMethod.GET)
	public Result<List<PosTeamVO>> getData(HttpSession session) throws Exception {
		Result<List<PosTeamVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosTeamVO> dataVO = posTeamService.getBankData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, dataVO);
        } catch (Exception e) {
            logger.error("查看班组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "下拉框选择班组", notes = "下拉框数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getSelectTeam", method = RequestMethod.GET)
	public Result<List<PosTeamSelectVO>> getSelectData(HttpSession session, @ApiParam(value = "企业id", required = true)@RequestParam(name = "id", required = false) Long id) throws Exception {
		Result< List<PosTeamSelectVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            //如果是总部则需要传id
            if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            	userVO.setEnterpriseId(id);
            }
            List<PosTeamSelectVO> dataVO = posTeamService.selectPosTeam(userVO.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, dataVO);
        } catch (Exception e) {
            logger.error("查看班组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加班组", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosTeamSaveOrupdateVO bean) {
		Result<String> result = new Result<>();
		ChineseString.checkCode(bean.getCode());
		checkTime(bean.getStartTime(), bean.getEndTime());
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            // 验证编码
            if(StringUtils.isNoneBlank(bean.getCode())) {
            	Result<String> check = checkCode(bean.getCode(),userVO.getEnterpriseId(), result);
            	if(check != null) {
            		return result;
            	}
            }
            Long check = posTeamMapper.findByName(bean.getName(), userVO.getEnterpriseId());
            if(0 != check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), bean.getName()+"名称已存在！");
    			return result;
            }
            posTeamService.save(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        } catch (Exception e) {
            logger.error("添加班组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新班组", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosTeamSaveOrupdateVO posTeam) {
		Result<String> result = new Result<>();
		checkTime(posTeam.getStartTime(), posTeam.getEndTime());
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long check = posTeamMapper.findUpdateByName(userVO.getEnterpriseId(), posTeam.getName(), posTeam.getId());
            if(1 < check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), posTeam.getName()+"名称已存在！");
    			return result;
            }
            posTeamService.update(posTeam, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"更新成功");
        } catch (Exception e) {
            logger.error("更新班组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据ID删除班组", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,@ApiParam(value = "根据id删除", required = true)@RequestParam(name = "id", required = true) String id) {
		Result<Object> result = new Result<>();
		if(StringUtils.isBlank(id)) {
			result.setBizSetCodeFallInfo(SysCode.SYS_PARAM_ERROR, "id不能为空！");
            return result;
		}
        try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			posTeamService.delete(Long.valueOf(id),userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除成功");
        } catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
			return result;
		} catch (Exception e) {
            logger.error("删除班组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}

	// 编码验证
	private Result<String> checkCode(String code,Long enterpriseId, Result<String> result) throws Exception {
		Long check = posTeamService.findByCode(code,enterpriseId);
		if (0 != check) {
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "编码已存在！");
			return result;
		}
		return null;
	}
	
	private void checkTime(String startTime,String endTime) {
        if(StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
			throw new BusinessException("开始时间、结束时间不能为空");
        } else {
        	// 前台时间
 			Date std = DateUtils.StringToDate(startTime, DateStyle.HH_MM);
 			Date etd = DateUtils.StringToDate(endTime, DateStyle.HH_MM);
 			if(null != std && null != etd) {
 				// 前台时间验证
    			if (std.getTime() >= etd.getTime()) {
    				throw new BusinessException("开始时间不能大于或等于结束时间");
    			}
 			}
        }
	}
	
}
