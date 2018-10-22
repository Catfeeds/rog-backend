/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CheckTypeComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**

 * @Description:系统管理-系统设置-范围和资质服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午7:42:16

 */

@Api(description="系统管理-系统设置-范围和资质服务接口")
@RestController
@RequestMapping("/system/set/goodsQualification")
public class GoodsQualificationController {

	private static final Log logger = LogFactory.getLog(GoodsQualificationController.class);
	
	@Autowired
	private ScopeQualificationService scopeQualificationService;
	
	@Autowired
	private CheckTypeComponent checkTypeComponent;
	
	@ApiOperation(value="查看商品资质信息", notes = "根据当前用户查看商品资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getGoodsQualification", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<GoodsQualification>> getGoodsQualification(HttpServletRequest request){
		Result<List<GoodsQualification>> result = new Result<List<GoodsQualification>>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
    		List<GoodsQualification> goodsQualification = scopeQualificationService.getGoodsQualification(user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsQualification);
        }catch(Exception e){
        	logger.error("查看商品资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改商品资质信息", notes = "根据当前用户修改商品资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "goodsQualification", value = "修改商品资质", required = true, dataType = "GoodsQualification")
	@RequestMapping(value = "/updateGoodsQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<GoodsQualification> updateGoodsQualification(HttpServletRequest request,@RequestBody GoodsQualification goodsQualification){
		Result<GoodsQualification> result = new Result<GoodsQualification>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
        	scopeQualificationService.updateGoodsQualification(goodsQualification,user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改商品资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改商品资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加商品资质信息", notes = "根据当前用户增加商品资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "goodsQualification", value = "增加商品资质", required = true, dataType = "GoodsQualification")
	@RequestMapping(value = "/addGoodsQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<GoodsQualification> addGoodsQualification(HttpServletRequest request,@RequestBody GoodsQualification goodsQualification){
		Result<GoodsQualification> result = new Result<GoodsQualification>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
        	scopeQualificationService.addGoodsQualification(goodsQualification,user);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsQualification);
        }catch (BusinessException e) {
			logger.error("增加商品资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加商品资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="删除商品资质信息", notes = "根据当前用户删除商品资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "商品资质ID", required = true, dataType = "Long",paramType="path")
	@RequestMapping(value = "/deleteGoodsQualification/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<GoodsQualification> deleteGoodsQualification(HttpServletRequest request,@PathVariable Long id){
		Result<GoodsQualification> result = new Result<GoodsQualification>();
        try{
        	scopeQualificationService.deleteGoodsQualification(id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		}catch (BusinessException e) {
			logger.error("删除企业资质信息错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
        }catch(Exception e){
        	logger.error("删除商品资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="获取当前企业的验收类型信息", notes = "获取当前企业的验收类型信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getCheckBackType", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<QualitySet>> deleteGoodsQualification(HttpServletRequest request){
		Result<List<QualitySet>> result = new Result<List<QualitySet>>();
        try{
        	HttpSession session = request.getSession();
    		UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
    		Long enterpriseId = 0L;
    		if (user.getChainType() == ChainType.Headquarters.getCode()){
    			enterpriseId = user.getEnterpriseId();
    		}else{
    			enterpriseId = user.getParentId();
    		}
        	List<QualitySet> findCheckTypes = checkTypeComponent.findCheckTypes(enterpriseId);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, findCheckTypes);
        }catch(Exception e){
        	logger.error("删除商品资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
}
