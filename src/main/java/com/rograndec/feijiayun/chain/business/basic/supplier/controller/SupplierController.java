package com.rograndec.feijiayun.chain.business.basic.supplier.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierQualificationService;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierSaleService;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierService;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierVarietiesService;
import com.rograndec.feijiayun.chain.business.basic.supplier.valid.AddSupplierValid;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierBasicExcelVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.*;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.PlaceOfService;
import com.rograndec.feijiayun.chain.common.constant.TransportStyle;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ST on 2017/8/21.
 */
@Api(value = "shelf",description = "供货单位服务接口")
@RestController
@RequestMapping("basic/supplier")
@Validated
public class SupplierController {
    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private ManageConfigService manageConfigService;
    
    @Autowired
    private CodeComponent codeComponent;
    
    @Autowired
    private SupplierQualificationService supplierQualificationService;
    @Autowired
    private SupplierSaleService supplierSaleService;
    
    @Autowired
    SupplierVarietiesService supplierVarietiesService;
    
    @Autowired
    private ExcelComponent excelComponent;


    @ApiOperation(value="根据登录用户企业id获取供应单位", notes = "获取供应单位 | 开发者:翟伟", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSupplier", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Supplier>> getSupplier(@ApiIgnore UserVO userVO){
        Result<List<Supplier>> result = new Result<>();
        try{
            List<Supplier> suppliers = supplierService.getSupplier(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, suppliers);
        }catch(Exception e){
            logger.error("获取供应单位错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="根据登录用户企业id获取供应单位", notes = "获取供应单位 | 开发者:翟伟", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/add/supplier", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Supplier>> getSupplier4Add(@ApiIgnore UserVO userVO){
        Result<List<Supplier>> result = new Result<>();
        try{
            List<Supplier> suppliers = supplierService.getSupplier4Add(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, suppliers);
        }catch(Exception e){
            logger.error("获取供应单位错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

/*    @ApiOperation(value = "获取供货单位", notes = "获取供货单位信息 | 开发者 孙腾 | 已联调 ")
    @RequestMapping(value="/getSupplier/{param}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "param", value = "检索条件", required = true, dataType = "String",paramType = "path")
    public Result<List<Supplier>> getWarehouseLocation(HttpSession session,@PathVariable String param,@ApiIgnore UserVO userVO){
        Result<List<Supplier>> result = new Result<>();

        try{
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,supplierService.getSupplierByParam(param,enterpriseId));
        }catch(Exception e){
            logger.error("获取供货单位:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }*/

    
    @ApiOperation(value = "根据条件获取供货单位基本信息", notes = "根据查询条件获取供货单位基本信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getBasicSupplier",method= RequestMethod.GET)
    public Result<Page> getBasicSupplier(HttpSession session,
    		@ApiParam(value = "页码", required = true) @RequestParam Integer pageNo,
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam Integer pageSize, 
			@ApiParam(value = "检索条件（编码/检索码/名称）", required = false) @RequestParam(required=false) String param,
			@ApiParam(value = "企业类型（0-批发企业；1-生产企业）", required = false) @RequestParam(required=false) Integer nature,
			@ApiParam(value = "分组ID", required = false) @RequestParam(required=false) Integer groupId,
			@ApiParam(value = "企业负责人" ,required = false) @RequestParam(required=false) String businessName, 
			@ApiParam(value = "采购人员", required = false) @RequestParam(required=false) String purchaseName,
			@ApiParam(value = "所属机构类型" ,required = false) @RequestParam(required=false) Integer ownerChainType, 
			@ApiParam(value = "所属机构编码", required = false) @RequestParam(required=false) String ownerCode,
			@ApiParam(value = "所属机构名称", required = false) @RequestParam(required=false) String ownerName,
             @RequestParam(required = false)
                 @ApiParam(name = "approveStatus", value = "审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）", required = false)
                         Integer approveStatus,
            @ApiIgnore UserVO userVO){
    	Result<Page> result = new Result<Page>();
        try{
        	Page page = new Page(pageNo, pageSize);
            page = supplierService.getBasicSupplier(pageNo,pageSize,page,param,userVO,nature,groupId,businessName,purchaseName,approveStatus,ownerChainType,ownerCode,ownerName);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(Exception e){
            logger.error("获取供货单位:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "查看供货单位详细信息", notes = "查看供货单位详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getDetailSupplier",method= RequestMethod.GET)
    
    public Result<SupplierDetailVO> getDetailSupplier(HttpSession session,
    		@ApiParam(value = "供货单位ID", required = true) @RequestParam(required=true) Long id,@ApiIgnore UserVO userVO){
        Result<SupplierDetailVO> result = new Result<SupplierDetailVO>();
        try{
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,supplierService.getDetailSupplier(userVO,id));
        }catch(Exception e){
            logger.error("获取供货单位:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "保存供货单位详细信息", notes = "保存供货单位详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/saveDetailSupplier",method= RequestMethod.POST)
    @ResponseBody
	@ApiImplicitParam(name = "supplierDetailVO", value = "需要保存的供货单位详细信息实体", required = true, dataType = "SupplierDetailVO")
    public Result<SupplierDetailVO> saveDetailSupplier(HttpSession session, @AddSupplierValid @RequestBody SupplierDetailVO supplierDetailVO, @ApiIgnore UserVO userVO){
        Result<SupplierDetailVO> result = new Result<SupplierDetailVO>();
        try{
			supplierService.saveDetailSupplier(userVO,supplierDetailVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("获取供货单位:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("获取供货单位:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    /**
     * @Title: getDetailSupplier
     * @param @param request
     * @param @param response
     * @param @return    设定文件
     * @return Result<SupplierDetailVO>    返回类型
     * @throws
     */
    @ApiOperation(value = "导出供货单位信息", notes = "导出供货单位信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getExportSupplier",method= RequestMethod.GET)
    public void ExportDetailSupplier(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "检索条件（编码/检索码/名称）", required = false) @RequestParam(required=false) String param,
			@ApiParam(value = "企业类型（0-批发企业；1-生产企业）", required = false) @RequestParam(required=false) Integer nature,
			@ApiParam(value = "分组ID", required = false) @RequestParam(required=false) Integer groupId,
			@ApiParam(value = "企业负责人", required = false) @RequestParam(required=false) String businessName, 
			@ApiParam(value = "采购人员", required = false) @RequestParam(required=false) String purchaseName,
            @RequestParam(required = false)
                 @ApiParam(name = "approveStatus", value = "审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）", required = false)
                         Integer approveStatus){
         OutputStream output = null;
         try {
        	output = response.getOutputStream();
        	//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        	String name = "供货单位信息表";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
         	HttpSession session = request.getSession(true);
         	UserVO loginUser = (UserVO) session.getAttribute("user");
         	List<SupplierBasicExcelVO> supplierVOList = supplierService.selectSupplierVoPage(param,nature,groupId,businessName,purchaseName,loginUser,approveStatus);
         	supplierService.exportExcel(output,supplierVOList);
        }catch(Exception e){
            logger.error("导出供货单位信息错误:"+e.getMessage(),e);
        }
    }
    
    @ApiOperation(value="送达地点下拉框", notes = "送达地点下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPlaceOfService", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPlaceOfService(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (PlaceOfService s : PlaceOfService.values()){
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("送达地点下拉框信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
    
    @ApiOperation(value="运输方式下拉框", notes = "运输方式下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getTransport", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SelectBean>> getTransport(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (TransportStyle s : TransportStyle.values()){
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("送达地点下拉框信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
    
    @ApiOperation(value="新增供货单位中默认出现的资质信息(新需求:根据经营品种Id集合)", notes = "新增供货单位中默认出现的资质信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDefaultQualification", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SupplierQulificationVO>> getDefaultQualification(HttpServletRequest request,@RequestBody List<Long> idList){
		Result<List<SupplierQulificationVO>> result = new Result<List<SupplierQulificationVO>>();
        try{
        	HttpSession session = request.getSession();
            UserVO user = (UserVO) session.getAttribute("user");
            if (idList == null){
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, new ArrayList<>());
            }
            List<SupplierQulificationVO> list = supplierQualificationService.getDefaultQualification(user,idList);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("新增供货单位中默认出现的资质信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
    
    @ApiOperation(value = "修改供货单位详细信息", notes = "修改供货单位详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/updateDetailSupplier",method= RequestMethod.POST)
    @ApiImplicitParam(name = "supplierDetailVO", value = "需要保存的供货单位详细信息实体", required = true, dataType = "SupplierDetailVO")
    public Result<SupplierDetailVO> updateDetailSupplier(HttpSession session,@AddSupplierValid @RequestBody SupplierDetailVO supplierDetailVO) throws Exception{
        Result<SupplierDetailVO> result = new Result<SupplierDetailVO>();
        try{
            UserVO user = (UserVO) session.getAttribute("user");
			supplierService.updateDetailSupplier(user,supplierDetailVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("修改供货单位详细信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("修改供货单位详细信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "查看当前供货单位新增时双星字段是否必填", notes = "查看当前供货单位新增时双星字段是否必填 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/checkSupplier",method= RequestMethod.GET)
    public Result<String> checkSupplier(HttpSession session){
        Result<String> result = new Result<String>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long enterpriseId = 0L;
         	if (loginUser.getChainType() == ChainType.Headquarters.getCode()) {
         		enterpriseId = loginUser.getEnterpriseId();
         	}else {
         		enterpriseId = loginUser.getParentId();
         	}
            String type = supplierService.checkSupplier(enterpriseId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,type);
        }catch(Exception e){
            logger.error("修改供货单位详细信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value = "查看当前供货单位一些配置控制信息", notes = "查看当前供货单位一些配置控制信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/checkCode",method= RequestMethod.GET)
    public Result<ManageConfig> checkCode(HttpSession session){
        Result<ManageConfig> result = new Result<ManageConfig>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long enterpriseId = 0L;
         	if (loginUser.getChainType() == ChainType.Headquarters.getCode()) {
         		enterpriseId = loginUser.getEnterpriseId();
         	}else {
         		enterpriseId = loginUser.getParentId();
         	}
         	ManageConfig manageConfig = manageConfigService.getManageConfig(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,manageConfig);
        }catch(Exception e){
            logger.error("修改供货单位详细信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    
    @ApiOperation(value="当前供货单位的销售人员下拉框", notes = "当前供货单位的销售人员下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getSale", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<NewSelectBean>> getSale(HttpServletRequest request, @ApiParam(value = "供货单位ID", required = true) @RequestParam(required=true) Long id){
		Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
        	List<NewSelectBean> list = new ArrayList<NewSelectBean>();
        	HttpSession session = request.getSession();
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	List<SupplierSaler> supplierSaler = supplierSaleService.getTransport(id);
        	for (SupplierSaler s : supplierSaler) {
				NewSelectBean bean = new NewSelectBean();
        		bean.setId(s.getId());
        		bean.setText(String.valueOf(s.getName()));
        		list.add(bean);
			}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("送达地点下拉框信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
    
}

