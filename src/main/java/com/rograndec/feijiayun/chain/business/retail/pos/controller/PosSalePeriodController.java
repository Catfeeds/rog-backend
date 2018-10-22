package com.rograndec.feijiayun.chain.business.retail.pos.controller;
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

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSalePeriodMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosSalePeriodService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosSalePeriodController   
 * @Description:  零售管理-POS管理-销售时段-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:54
 */
@Api(value="PosSalePeriodController",description = "零售管理-POS管理-销售时段")
@RestController
@RequestMapping("/retail/pos/posSalePeriod")
public class PosSalePeriodController {

	private static final Logger logger = LoggerFactory.getLogger(PosSalePeriodController.class);
	
	@Autowired
	private PosSalePeriodService posSalePeriodService;
	
	@Autowired
	private PosSalePeriodMapper  posSalePeriodMapper;
		
	
	@ApiOperation(value = "查看销售时段", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	public Result<List<PosSalePeriodVO>> getPosSalePeriodData(HttpSession session) throws Exception {
		Result<List<PosSalePeriodVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosSalePeriodVO> list = posSalePeriodService.getPosSalePeriodData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看销售时段数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加销售时段", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosSalePeriodSaveOrupdateVO posSalePeriod) {
		Result<String> result = new Result<>();
		ChineseString.checkCode(posSalePeriod.getCode());
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            // 验证编码
            if(StringUtils.isNoneBlank(posSalePeriod.getCode())) {
            	Result<String> check = checkCode(posSalePeriod.getCode(),userVO.getEnterpriseId(), result);
            	if(check != null) {
            		return result;
            	}
            }
            Long check = posSalePeriodMapper.findByName(posSalePeriod.getName(), userVO.getEnterpriseId());
            if(0 != check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), posSalePeriod.getName()+"名称已存在！");
    			return result;
            }
            // 日期验证
            String timeCheck = posSalePeriodService.checkTime(userVO.getEnterpriseId(), posSalePeriod.getStartTime(), posSalePeriod.getEndTime());
            if(StringUtils.isNotBlank(timeCheck)) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), timeCheck);
    			return result;
            }
            posSalePeriodService.save(posSalePeriod,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        } catch (Exception e) {
            logger.error("添加销售时段数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新销售时段", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosSalePeriodSaveOrupdateVO posSalePeriod) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long check = posSalePeriodMapper.findUpdateByName(userVO.getEnterpriseId(), posSalePeriod.getName(), posSalePeriod.getId());
            if(1 < check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), posSalePeriod.getName()+"名称已存在！");
    			return result;
            }
            // 日期验证
            String timeCheck = posSalePeriodService.checkTime(userVO.getEnterpriseId(), posSalePeriod.getStartTime(), posSalePeriod.getEndTime());
            if(StringUtils.isNotBlank(timeCheck)) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), timeCheck);
    			return result;
            }
            int upresult = posSalePeriodService.update(posSalePeriod,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,upresult == 1 ? "更新成功" : "更新数据不存在");
        } catch (Exception e) {
            logger.error("更新销售时段数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据ID删除销售时段", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,@ApiParam(value = "根据id删除", required = true)@RequestParam(name = "id", required = true) String id) {
		Result<Object> result = new Result<>();
		if(StringUtils.isBlank(id)) {
			result.setBizSetCodeFallInfo(SysCode.SYS_PARAM_ERROR, "id不能为空！");
            return result;
		}
        try {
        	// 当前登录用户数据
            int delresult = posSalePeriodService.delete(Long.valueOf(id));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,delresult == 1 ? "删除成功" : "删除数据不存在");
        } catch (Exception e) {
            logger.error("删除销售时段数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	// 编码验证
	private Result<String> checkCode(String code,Long enterpriseId, Result<String> result) throws Exception {
		Long check = posSalePeriodService.findByCode(code,enterpriseId);
		if (0 != check) {
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "编码已存在！");
			return result;
		}
		return null;
	}
}
