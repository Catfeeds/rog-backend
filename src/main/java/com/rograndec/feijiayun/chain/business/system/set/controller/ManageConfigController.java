/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.rograndec.feijiayun.chain.business.system.set.vo.ValidateCodeRuleVO;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.business.system.set.vo.ManageConfigVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**

 * @Description:系统管理-系统设置-管理设置服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午4:22:21

 */

@Api(description="系统管理-系统设置-管理设置服务接口")
@RestController
@RequestMapping("/system/set/manageConfig")
public class ManageConfigController {
	
	private static final Log logger = LogFactory.getLog(ManageConfigController.class);

	@Autowired
	private ManageConfigService manageConfigService;

	@Autowired
	private ManageConfigComponent manageConfigComponent;
	
	@Autowired
	private UserComponent userComponent;
	
	@ApiOperation(value="获取配置信息", notes = "根据当前登录用户对应的企业ID获取配置列表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getManageConfig", method = RequestMethod.GET)
	@ResponseBody
	public Result<ManageConfig> getManageConfig(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<ManageConfig> result = new Result<ManageConfig>();
		
        try{
        	ManageConfig manageConfig = new ManageConfig();
        	manageConfig = manageConfigService.getManageConfig(userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, manageConfig);
        }catch(Exception e){
			logger.error("获取配置信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value="修改配置信息", notes = "根据当前登录用户对应的企业ID修改配置信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "manageConfig", value = "企业配置实体", required = true, dataType = "ManageConfig")
	@RequestMapping(value = "/updateManageConfig", method = RequestMethod.POST)
	@ResponseBody
	public Result<ManageConfig> updateManageConfig(HttpServletRequest request,@RequestBody ManageConfig manageConfig,@ApiIgnore UserVO userVO){
		Result<ManageConfig> result = new Result<ManageConfig>();
        try{
        	manageConfigService.updateManageConfig(manageConfig,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改配置信息:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改配置信息:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	@ApiOperation(value="根据企业id查询管理配置信息", notes = "根据企业id查询管理配置信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getMangeConfigByEPId", method = RequestMethod.GET)
	@ResponseBody
	public Result<ManageConfig> getMangeConfigByEPId(HttpSession session,@ApiIgnore UserVO userVO){
		Result<ManageConfig> result = new Result<ManageConfig>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,manageConfigComponent.getMangeConfigByEPId(userVO));
		}catch(Exception e){
			logger.error("获取配置信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	@ApiOperation(value="根据企业id查询当前所有相关用户", notes = "根据企业id查询当前所有相关用户 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getUserByEnterpriseId", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<User>> getUserByEnterpriseId(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<User>> result = new Result<List<User>>();
		try{
    		List<User> list = userComponent.findUserByEnterpriseId(userVO.getEnterpriseId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
		}catch(Exception e){
			logger.error("根据企业id查询当前所有相关用户错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "(总部情况)判断当前企业的供货单位编码规则 + 员工信息编码规则 + 商品新息编码规则是否可编辑 0-不可编辑 1-可编辑", notes = "(总部情况)判断当前企业的供货单位编码规则 + 员工信息编码规则 + 商品新息编码规则是否可编辑 | 开发者 苏磊 | 已联调")
	@RequestMapping(value="/validateManageConfig",method= RequestMethod.GET)
	public Result<ValidateCodeRuleVO> judgeCodeRule(@ApiParam(value = "当前管理配置ID", required = true) @RequestParam Long id,@ApiIgnore UserVO userVO){
		Result<ValidateCodeRuleVO> result = new Result<ValidateCodeRuleVO>();
		try{
			ValidateCodeRuleVO vo = manageConfigService.judgeCodeRule(id,userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,vo);
		}catch(Exception e){
			logger.error("(总部情况)判断当前企业的供货单位编码规则 + 员工信息编码规则 + 商品新息编码规则是否可编辑:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
}

