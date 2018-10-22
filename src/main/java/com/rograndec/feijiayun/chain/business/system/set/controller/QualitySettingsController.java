/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rograndec.feijiayun.chain.common.NewSelectBean;
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
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.QualitySettingsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**

 * @Description:系统管理-系统设置-质量设置服务接口

 * @author:LeiSu

 * @time:2017年8月21日 下午6:12:59

 */

@Api(description="系统管理-系统设置-质量设置服务接口")
@RestController
@RequestMapping("/system/set/qualitySettings")
public class QualitySettingsController {
	
	private static final Log logger = LogFactory.getLog(QualitySettingsController.class);

	@Autowired
	private QualitySettingsService qualitySettingsService;
	
	@ApiOperation(value="查看质量设置的不合格原因0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施", notes = "根据当前登录用户对应的企业ID以及不合格类型获取不合格原因0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "设置类型", required = true, dataType = "Integer",paramType = "path")
	@RequestMapping(value = "/getQualityUnqualified/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<QualitySet>> getQualityUnqualified(HttpServletRequest request,@PathVariable Integer id,@ApiIgnore UserVO userVO){
		Result<List<QualitySet>> result = new Result<List<QualitySet>>();
        try{
        	List<QualitySet> qualitySet = qualitySettingsService.getQualitySettings(userVO,id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, qualitySet);
        }catch(Exception e){
        	logger.error("获取部门组织机构信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="修改质量设置的不合格原因", notes = "根据当前登录用户对应的企业ID以及不合格类型修改不合格原因 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "qualitySet", value = "不合格信息", required = true, dataType = "QualitySet")
	@RequestMapping(value = "/updateQualityUnqualified", method = RequestMethod.POST)
	@ResponseBody
	public Result<QualitySet> updateQualityUnqualified(HttpServletRequest request,@RequestBody QualitySet qualitySet,@ApiIgnore UserVO userVO){
		Result<QualitySet> result = new Result<QualitySet>();
        try{
        	qualitySettingsService.updateQualitySettings(qualitySet,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("修改质量设置的不合格原因:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("修改质量设置的不合格原因错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="增加质量设置的不合格原因", notes = "根据当前登录用户对应的企业ID以及不合格类型增加不合格原因 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "qualitySet", value = "新增的不合格信息", required = true, dataType = "QualitySet")
	@RequestMapping(value = "/addQualityUnqualified", method = RequestMethod.POST)
	@ResponseBody
	public Result<QualitySet> addQualityUnqualified(HttpServletRequest request,@RequestBody QualitySet qualitySet,@ApiIgnore UserVO userVO){
		Result<QualitySet> result = new Result<QualitySet>();
        try{
        	qualitySettingsService.addQualitySettings(qualitySet,userVO);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("增加质量设置的不合格原因错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("增加质量设置的不合格原因错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	@ApiOperation(value="删除质量设置的不合格原因", notes = "根据当前登录用户对应的企业ID以及不合格类型删除不合格原因 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "质量设置ID", required = true, dataType = "Long", paramType="path")
	@RequestMapping(value = "/deleteQualityUnqualified/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<QualitySet> deleteQualityUnqualified(HttpServletRequest request,@PathVariable Long id){
		Result<QualitySet> result = new Result<QualitySet>();
        try{
        	qualitySettingsService.deleteQualitySettings(id);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
			logger.error("删除质量设置的不合格原因错误:", e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}catch(Exception e){
        	logger.error("删除质量设置的不合格原因错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}

	//验收项目中新增的下拉框-----是从验收类型中取出的
	@ApiOperation(value="验收项目中新增的下拉框", notes = "验收项目中新增的下拉框-----是从验收类型中取出的 | 开发者 苏磊 | 未使用等待后期修改再调用", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/selectCheckProductSelector", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<NewSelectBean>> selectCheckProductSelector(HttpServletRequest request,@ApiIgnore UserVO userVO){
		Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
		try{
			List<QualitySet> list = qualitySettingsService.selectCheckProductSelector(userVO);
			List<NewSelectBean> select = new ArrayList<NewSelectBean>();
			for (QualitySet q:list) {
				NewSelectBean n = new NewSelectBean();
				n.setId(q.getId());
				n.setText(q.getDescription());
				select.add(n);
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,select);
		}catch(Exception e){
			logger.error("增加质量设置的不合格原因错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
}
