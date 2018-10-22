package com.rograndec.feijiayun.chain.business.basic.store.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreGroupService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreGroupTreeVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreGroupVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SimpleEnterpriseVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


@Api(value = "storeGroup", description = "门店管理-门店分组", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/basic/store/storeGroup")
public class StoreGroupController {
	
	private static final Log logger = LogFactory.getLog(StoreGroupController.class);
	
	@Autowired
	StoreGroupService storeGroupService; 
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="获取门店分组页面信息", notes = "获取门店分组页面信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStoreGroup", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<StoreGroupTreeVO>> getStoreGroup(HttpServletRequest request){
		Result<List<StoreGroupTreeVO>> result = new Result<List<StoreGroupTreeVO>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<StoreGroupTreeVO> list = storeGroupService.selectStroeGroupTreeByEnterpriseId(loginUser.getEnterpriseId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
        	logger.error("获取企业资质详细信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="新增分组类型下拉", notes = "新增分组类型下拉 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStoreGroupType", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getStoreGroupType(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<SelectBean> list = storeGroupService.selectStoreGroupType(loginUser.getEnterpriseId());
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取经营品种信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="保存门店分组信息 ", notes = "保存门店分组信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveStoreGroup", method = RequestMethod.POST)
	public Result<EnterpriseGroup> saveStoreGroup(HttpServletRequest request,
			@ApiParam(value = "门店分组信息", required = true) @RequestBody StoreGroupVO vo) {
		Result<EnterpriseGroup> result = new Result<EnterpriseGroup>();
		try{
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	EnterpriseGroup group = storeGroupService.saveOrUpdate(vo, loginUser);
			if(group == null || group.getId() == 0){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, group);
			}else{
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, group);
			}
		}catch(Exception e){
			logger.error("保存门店分组信息错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="获取关联门店列表信息 ", notes = "获取关联门店列表信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getRelationStore", method = RequestMethod.POST)
	public Result<List<SimpleEnterpriseVO>> getRelationStore(HttpServletRequest request,
			@ApiParam(value = "药店类型 1-自营店；2-加盟店", required = true) @RequestParam(required=true) Integer chainType) {
		Result<List<SimpleEnterpriseVO>> result = new Result<List<SimpleEnterpriseVO>>();
		try{
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<SimpleEnterpriseVO> groupList = storeGroupService.selectRelationStore(loginUser.getEnterpriseId(), chainType);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, groupList);
		}catch(Exception e){
			logger.error("保存门店分组信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="保存关联门店列表信息", notes = "保存关联门店列表信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveRelationStore", method = RequestMethod.POST)
	public Result<String> saveRelationStore(HttpServletRequest request,
			@ApiParam(value = "选择的门店分组ID", required = true) @RequestParam Long id,
			@ApiParam(value = "关联的门店ID，多个以,分隔", required = true) @RequestParam String storeIds) {
		Result<String> result = new Result<String>();
		try{
			
			if(id == null || id == 0){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
				return result;
			}
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
			
			int count = storeGroupService.saveRelationStore(id, storeIds, loginUser);
			if(count == 1){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
			}else {
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
			}
		}catch(Exception e){
			logger.error("保存关联门店列表信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="移除门店分组", notes = "移除门店分组 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/removeRelationStore", method = RequestMethod.POST)
	public Result<String> removeRelationStore(HttpServletRequest request,
			@ApiParam(value = "选择的门店分组ID", required = true) @RequestParam Long id,
			@ApiParam(value = "选择的门店分组parentID", required = true) @RequestParam Long parentId,
			@ApiParam(value = "级别,目前2,3级都支持移除", required = true) @RequestParam Long level) {
		Result<String> result = new Result<String>();
		try{
			
			if(id == null || id == 0 || parentId == null || parentId == 0
					|| level == null || (level != 2 && level != 3)){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
				return result;
			}
			
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
			
			int count = storeGroupService.removeRelationStore(id, parentId, level, loginUser.getEnterpriseId());
			if(count == 1){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
			}else if(count == 2){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
			}else {
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
			}
		}catch(Exception e){
			logger.error("保存关联门店列表信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="删除门店分组", notes = "删除门店分组 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/deleteRelationStore", method = RequestMethod.POST)
	public Result<String> deleteRelationStore(HttpServletRequest request,
			@ApiParam(value = "选择的门店分组ID", required = true) @RequestParam Long id,
			@ApiParam(value = "选择的门店分组parentID", required = true) @RequestParam Long parentId,
			@ApiParam(value = "级别,目前2,3级都支持移除", required = true) @RequestParam Long level) {
		Result<String> result = new Result<String>();
		try{
			
			if(id == null || id == 0 || parentId == null || parentId == 0
					|| level == null || (level != 2 && level != 3)){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
				return result;
			}
			
			HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
			
			int count = storeGroupService.deleteRelationStore(id, parentId, level, loginUser.getEnterpriseId());
			if(count == 1){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
			}else if(count == 2){
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
			}else if(count == 3) {
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS,"当前分组包含子节点不可删除！");
			}else if(count == 0){
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS,"系统默认分组不允许删除");
			}else if(count == 5){
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS,"被门店引用不允许删除");
			}else {
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"");
			}
		}catch(Exception e){
			logger.error("保存关联门店列表信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value = "导出门店分组信息", notes = "导出门店分组信息 | 开发者:刘群 | 已联调")
    @RequestMapping(value = "/excelExportStoreGroup", method = RequestMethod.GET)
    public void excelExportStoreGroup(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        String name = "门店分组信息导出";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        try {
        	response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = null;
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<EnterpriseGroup> enterpriseGroupList = storeGroupService.selectExportEnterpriseGroup(loginUser.getEnterpriseId());
        	
            output = response.getOutputStream();
            storeGroupService.excelExport(output, enterpriseGroupList, loginUser);
        } catch (Exception e) {
        	logger.error("导出门店分组错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
        
    }

}
