package com.rograndec.feijiayun.chain.business.basic.supplier.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.*;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierGroup;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierTreePOJO;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierGroupService;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierService;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.BusinessScopeComponent;
import com.rograndec.feijiayun.chain.common.component.SupplierQualificationComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "shelf",description = "供货单位分组服务接口")
@RestController
@RequestMapping("basic/supplier/supplierGroup")
public class SupplierGroupController {

	 private static final Logger logger = LoggerFactory.getLogger(SupplierGroupController.class);
	 
	 @Autowired
	 private SupplierGroupService supplierGroupService;
	 
	 @Autowired
	 private SupplierQualificationComponent supplierQualificationComponent;
	 
	 @Autowired
	 private BusinessScopeComponent businessScopeComponent;
	 
	 @Autowired
	 private SupplierService supplierService;

	 @ApiOperation(value = "新增供货单位分组", notes = "新增供货单位分组 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/addSupplierGroup",method= RequestMethod.POST)
	 @ApiImplicitParam(name = "supplierGroupVO", value = "需要保存的供货单位分组实体", required = true, dataType = "SupplierGroupVO")
	 public Result<SupplierGroupVO> addSupplierGroup(HttpSession session,
			 @RequestBody SupplierGroupVO supplierGroupVO){
        Result<SupplierGroupVO> result = new Result<SupplierGroupVO>();
        try{
            UserVO user = (UserVO) session.getAttribute("user");
            supplierGroupService.addSupplierGroup(user,supplierGroupVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("新增供货单位分组:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增供货单位分组:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	 
	 @ApiOperation(value = "供货单位分组下拉框", notes = "供货单位分组下拉框 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/selectSupplierGroupSelector",method= RequestMethod.GET)
	 public Result<List<SupplierGroup>> selectSupplierGroupSelector(HttpSession session,
			 @ApiParam(value = "性质（0-批发企业；1-生产企业）", required = true) @RequestParam Integer type){
		 Result<List<SupplierGroup>> result = new Result<List<SupplierGroup>>();
         try{
             UserVO user = (UserVO) session.getAttribute("user");
             List<SupplierGroup> selectSupplierGroupSelector = supplierGroupService.selectSupplierGroupSelector(user,type);
             
             result.setBizCodeSuccessInfo(SysCode.SUCCESS,selectSupplierGroupSelector);
         }catch(Exception e){
             logger.error("获取供货单位:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "供货单位资质信息添加中资质描述的下拉框", notes = "供货单位资质信息添加中资质描述的下拉框 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/selectSupplierQulificationSelector",method= RequestMethod.POST)
	 public Result<List<SelectBean>> findEnterpriseQualificationSelectorByEnterpriseId(HttpSession session,
                                                                                       @RequestBody RequestSupplierQualification requestSupplierQualification){
		 Result<List<SelectBean>> result = new Result<List<SelectBean>>();
         try{
             UserVO user = (UserVO) session.getAttribute("user");
             Long enterpriseId = 0L;
             if (ChainType.Headquarters.getCode() == user.getChainType()) {
            	 enterpriseId = user.getEnterpriseId();
             }else {
            	 enterpriseId = user.getParentId();
             }
             List<SelectBean> list = new ArrayList<SelectBean>();
             List<EnterpriseQualification> eq = supplierQualificationComponent.findEnterpriseQualificationSelectorByEnterpriseId(enterpriseId,requestSupplierQualification);
             for (EnterpriseQualification e : eq) {
            	 SelectBean bean = new SelectBean();
         		 bean.setId(String.valueOf(e.getId()));
         		 bean.setText(String.valueOf(e.getName()));
         		 list.add(bean);
			 }
             result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
         }catch(Exception e){
             logger.error("获取供货单位:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "获得供货单位分组树形结构", notes = "获得供货单位分组树形结构 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/selectSupplierTree",method= RequestMethod.GET)
	 public Result<List<SupplierTreePOJO>> selectSupplierTree(HttpSession session){
		 Result<List<SupplierTreePOJO>> result = new Result<List<SupplierTreePOJO>>();
         try{
             UserVO user = (UserVO) session.getAttribute("user");
             Long enterpriseId = 0L;
             if (ChainType.Headquarters.getCode() == user.getChainType()) {
            	 enterpriseId = user.getEnterpriseId();
             }else {
            	 enterpriseId = user.getParentId();
             }
             List<SupplierGroupTreeVO> list = supplierGroupService.selectSupplierTree(enterpriseId);
             List<SupplierTreePOJO> tree = supplierGroupService.structTree(list,user);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS,tree);
         }catch(Exception e){
        	 logger.error("获取供货单位分组树形结构:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "获得关联的供货单位", notes = "获得关联的供货单位 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/selectConnectSupplier",method= RequestMethod.GET)
	 public Result<List<SupplierGroupConnectVO>> selectConnectSupplier(HttpSession session,@ApiParam(value = "需要传入当前供货单位分组ID", required = true) @RequestParam(required = true) Long id){
		 Result<List<SupplierGroupConnectVO>> result = new Result<List<SupplierGroupConnectVO>>();
         try{
             UserVO user = (UserVO) session.getAttribute("user");
             Long enterpriseId = 0L;
             if (ChainType.Headquarters.getCode() == user.getChainType()) {
            	 enterpriseId = user.getEnterpriseId();
             }else {
            	 enterpriseId = user.getParentId();
             }
             List<SupplierGroupConnectVO> list = supplierGroupService.selectConnectSupplier(enterpriseId,id);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
         }catch(Exception e){
             logger.error("获取供货单位分组树形结构:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "保存关联的供货单位", notes = "保存关联的供货单位 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/saveConnectSupplier",method= RequestMethod.POST)
     @ApiImplicitParam(name = "supplierGroupIncludeVO", value = "需要关联的供货单位VO对象集合", required = true, dataType = "SupplierGroupIncludeVO")
	 public Result<String> saveConnectSupplier(HttpSession session,
			 @RequestBody SupplierGroupIncludeVO supplierGroupIncludeVO){
		 Result<String> result = new Result<>();
         try{
             UserVO user = (UserVO) session.getAttribute("user");
             Long enterpriseId = 0L;
             if (ChainType.Headquarters.getCode() == user.getChainType()) {
            	 enterpriseId = user.getEnterpriseId();
             }else {
            	 enterpriseId = user.getParentId();
             }
             supplierService.saveConnectSupplier(supplierGroupIncludeVO,enterpriseId);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS);
         }catch(Exception e){
             logger.error("保存关联的供货单位:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "供货单位分组中的修改", notes = "供货单位分组中的修改 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/updateSupplerGroup",method= RequestMethod.POST)
     @ApiImplicitParam(name = "supplierGroupVO", value = "需要修改的的供货单位分组实体", required = true, dataType = "SupplierGroupVO")
     public Result<Object> updateSupplerGroup(HttpSession session,@RequestBody SupplierGroupVO supplierGroupVO){
		 Result<Object> result = new Result<>();
         try{
             UserVO user = (UserVO) session.getAttribute("user");
             Long enterpriseId = 0L;
             if (ChainType.Headquarters.getCode() == user.getChainType()) {
            	 enterpriseId = user.getEnterpriseId();
             }else {
            	 enterpriseId = user.getParentId();
             }
             supplierService.updateSupplerGroup(user,supplierGroupVO);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS);
         }catch(BusinessException e){
             logger.error("供货单位分组修改失败:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
             return result;
         }catch(Exception e){
             logger.error("供货单位分组修改失败:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "供货单位分组中的移除", notes = "供货单位分组中的移除 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/removeSupplierGroup",method= RequestMethod.GET)
	 public Result<String> removeSupplierGroup(HttpSession session,
			 @ApiParam(value = "当前分组ID", required = true) @RequestParam Long id){
		 Result<String> result = new Result<>();
         try{
        	 supplierService.removeSupplierGroup(id);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS);
         }catch(Exception e){
             logger.error("获取供货单位分组树形结构:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "供货单位分组中的删除", notes = "供货单位分组中的删除 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/deleteSupplierGroup",method= RequestMethod.GET)
	 public Result<String> deleteSupplierGroup(@ApiParam(value = "当前分组ID", required = true) @RequestParam Long id){
		 Result<String> result = new Result<>();
         try{
             supplierService.deleteSupplierGroup(id);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS);
         }catch(Exception e){
             logger.error("供货单位分组中的删除:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "供货单位分组中供货单位的移除", notes = "供货单位分组中供货单位的移除 | 开发者 苏磊 | 已联调")
	 @RequestMapping(value="/RemoveSupplier",method= RequestMethod.GET)
	 public Result<String> RemoveSupplier(@ApiParam(value = "当前供货单位Id", required = true) @RequestParam Long id){
		 Result<String> result = new Result<>();
         try{
             supplierService.RemoveSupplier(id);
             result.setBizCodeSuccessInfo(SysCode.SUCCESS);
         }catch(Exception e){
             logger.error("供货单位分组中供货单位的移除:"+e.getMessage(),e);
             result.setBizCodeFallInfo(SysCode.FAIL);
             return result;
         }
         return result;
	 }
	 
	 @ApiOperation(value = "导出供货单位分组信息", notes = "导出供货单位分组信息 | 开发者 苏磊 | 已联调")
	    @RequestMapping(value="/getExportSupplier",method= RequestMethod.GET)
	    public void ExportGroupSupplier(HttpServletRequest request, HttpServletResponse response){
	         OutputStream output = null;
	         try {
	        	output = response.getOutputStream();
	        	//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
	        	String name = "供货单位分组信息表";
	            response.setContentType("application/octet-stream;charset=utf-8");
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	         	HttpSession session = request.getSession(true);
	         	UserVO loginUser = (UserVO) session.getAttribute("user");
	         	Long enterpriseId = 0L;
	         	if (loginUser.getChainType() == ChainType.Headquarters.getCode()) {
	         		enterpriseId = loginUser.getEnterpriseId();
	         	}else {
	         		enterpriseId = loginUser.getParentId();
	         	}
	         	List<SupplierGroupExcelVO> supplierVOList = supplierService
	         			.ExportGroupSupplier(enterpriseId);
	         	supplierService.exporGroupExcel(output,supplierVOList);
	        }catch(Exception e){
	            logger.error("导出供货单位信息错误:"+e.getMessage(),e);
	        }
	    }
	 
}
