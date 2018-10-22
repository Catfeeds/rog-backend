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

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosBankAndPayTypeInitDataService;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayTypeService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosPayTypeController   
 * @Description:  零售管理-POS管理-支付方式-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:19:11
 */
@Api(value="PosPayTypeController",description = "零售管理-POS管理-支付方式")
@RestController
@RequestMapping("/retail/pos/payType")
public class PosPayTypeController {

	private static final Logger logger = LoggerFactory.getLogger(PosPayTypeController.class);
	
	@Autowired
	private PosPayTypeService posPayTypeService;
	
	@Autowired
	PosPayTypeMapper posPayTypeMapper;
	
	@Autowired
	PosBankAndPayTypeInitDataService posBankAndPayTypeInitDataService;
	
	@ApiOperation(value = "加盟店同步支付方式与开户银行", notes = "加盟店同步支付方式与开户银行 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getSyncData", method = RequestMethod.GET)
	public Result<String> getSyncData(HttpSession session) throws Exception {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            String msg = posBankAndPayTypeInitDataService.initBankAndPayTypeDataByDivision(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, msg);
        } catch (Exception e) {
            logger.error("加盟店同步支付方式与开户银行数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	
	@ApiOperation(value = "查看支付方式", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getPayTypeData", method = RequestMethod.GET)
	public Result<List<PosPayTypeVO>> getData(HttpSession session) throws Exception {
		Result<List<PosPayTypeVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosPayTypeVO> list = posPayTypeService.getPayTypeData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看支付方式数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加支付方式", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosPayTypeSaveOrupdateVO bean) {
		Result<String> result = new Result<>();
		ChineseString.checkCode(bean.getCode());
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
            Long check = posPayTypeMapper.findByName(bean.getName(), userVO.getEnterpriseId());
            if(0 != check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), bean.getName()+"支付名称已存在！");
    			return result;
            }
            Long checkKey = posPayTypeMapper.findShortcutKey(userVO.getEnterpriseId(), bean.getShortcutKey());
            if(0 != checkKey.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "快捷键已存在！");
    			return result;
            }
            posPayTypeService.save(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加数据成功");
        } catch (Exception e) {
            logger.error("添加支付方式数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新支付方式", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosPayTypeSaveOrupdateVO bean) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long check = posPayTypeMapper.findUpdateByName(userVO.getEnterpriseId(),bean.getName(),bean.getId());
            if(1 < check.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), bean.getName()+"支付名称已存在！");
    			return result;
            }
            Long checkKey = posPayTypeMapper.findUpdateShortcutKey(userVO.getEnterpriseId(),bean.getShortcutKey(),bean.getId());
            if(1 < checkKey.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "快捷键已存在！");
    			return result;
            }
            posPayTypeService.update(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"修改数据成功");
        } catch (Exception e) {
            logger.error("更新支付方式数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据id删除支付方式", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<String> remove(HttpSession session,@ApiParam(value = "根据id删除", required = true)@RequestParam(name = "id", required = true) String id
			/*,@ApiParam(value = "验证name是否可以删除", required = true)@RequestParam(name = "name", required = true) String name*/) {
		Result<String> result = new Result<>();
		if(StringUtils.isBlank(id)) {
			result.setBizSetCodeFallInfo(SysCode.SYS_PARAM_ERROR, "id不能为空！");
            return result;
		}
        try {
        	/*Result<String> check = checkName(name, result);
        	if(check != null) {
        		return result;
        	}*/
			UserVO loginUser = (UserVO) session.getAttribute("user");
			posPayTypeService.delete(Long.valueOf(id),loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除数据成功");
        } catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
			return result;

		}catch (Exception e) {
            logger.error("删除支付方式数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	// 编码验证
	private Result<String> checkCode(String code,Long enterpriseId,Result<String> result) throws Exception{
		Long check = posPayTypeService.findByCode(code,enterpriseId);
		if(0 != check) {
			result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, "编码已存在！");
			return result;
		}
		return null;
	}
	
	// 系统默认数据不让删除
	@SuppressWarnings("unused")
	private Result<String> checkName(String name, Result<String> result) throws Exception {
		String[] checkName = {"现金","银行","储值卡","购物券","医保卡","微信","支付宝"};
		for(String str : checkName) {
			if(name.trim().equals(str)) {
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, str+",支付方式不可删除！");
				return result;
			}
		}
		return null;
	}
	
}
