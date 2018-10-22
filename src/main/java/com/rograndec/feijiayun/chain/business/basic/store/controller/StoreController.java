package com.rograndec.feijiayun.chain.business.basic.store.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.rograndec.feijiayun.chain.business.basic.store.service.StoreService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.AddStoreEnterpriseVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseResponseVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistrPriceType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.MedicalFlag;
import com.rograndec.feijiayun.chain.common.constant.PaymentPeriodUnit;
import com.rograndec.feijiayun.chain.common.constant.PaymentProvision;
import com.rograndec.feijiayun.chain.common.constant.PaymentTime;
import com.rograndec.feijiayun.chain.common.constant.PaymentTimeUnit;
import com.rograndec.feijiayun.chain.common.constant.SettlementMode;
import com.rograndec.feijiayun.chain.common.constant.ValidFlag;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;


@Api(value = "store", description = "门店管理-门店信息-门店基本信息服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/store")
@Validated
public class StoreController {
	
	private static final Log logger = LogFactory.getLog(StoreController.class);
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
	CodeComponent codeComponent;
	
	@ApiOperation(value="分页获取所有门店信息", notes = "分页获取所有门店信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStorePage", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Result<Page> getStorePage(HttpServletRequest request,
			@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
			@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
			@ApiParam(value = "门店类型（0-全部；1-自营店；2-加盟店）", required = false) @RequestParam(required=false) Integer chainType,
			@ApiParam(value = "页面输入框，包含编码、检索码、名称", required = false) @RequestParam(required=false) String key,
			@ApiParam(value = "分组ID", required = false) @RequestParam(required=false) Integer groupId,
			@ApiParam(value = "企业负责人", required = false) @RequestParam(required=false) String businessManName,
			@ApiParam(value = "配货价格类型ID", required = false) @RequestParam(required=false) Integer distrPriceType,
			@ApiParam(value = "门店级别ID", required = false) @RequestParam(required=false) Integer storeLevelId,
			@ApiParam(value = "销售片区ID", required = false) @RequestParam(required=false) Integer saleAreaId,
			@ApiParam(value = "销售商圈ID", required = false) @RequestParam(required=false) Integer saleCircleId,
			@ApiParam(value = "付款条款ID", required = false) @RequestParam(required=false) Integer paymentProvision,
			@ApiParam(value = "按某一列排序", required = false) @RequestParam(required=false) String order,
			 @RequestParam(required = false)
				 @ApiParam(name = "approveStatus", value = "审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）", required = false)
						 Integer approveStatus,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort
			){
		Result<Page> result = new Result<Page>();
        try{
        	if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	
        	Page page = new Page(pageNo, pageSize);
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<StoreVO> storeVoList = storeService
        			.selectStoreVoPage(pageNo, pageSize, chainType, key, groupId, businessManName, distrPriceType, 
        					storeLevelId, saleAreaId, saleCircleId, paymentProvision, loginUser.getEnterpriseId(), page, order, sort,approveStatus);
        	
        	page.setResult(storeVoList);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("获取获取所有门店信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value = "导出门店信息", notes = "导出门店信息 | 开发者:刘群 | 已联调")
    @RequestMapping(value = "/excelExportStore", method = RequestMethod.GET)
    public void excelExportStore(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "门店类型（0-总部；1-自营店；2-加盟店）", required = false) @RequestParam(required=false) Integer chainType,
			@ApiParam(value = "页面输入框，包含编码、检索码、名称", required = false) @RequestParam(required=false) String key,
			@ApiParam(value = "分组ID", required = false) @RequestParam(required=false) Integer groupId,
			@ApiParam(value = "企业负责人", required = false) @RequestParam(required=false) String businessManName,
			@ApiParam(value = "配货价格ID", required = false) @RequestParam(required=false) Integer distrPriceType,
			@ApiParam(value = "门店级别ID", required = false) @RequestParam(required=false) Integer storeLevelId,
			@ApiParam(value = "销售片区ID", required = false) @RequestParam(required=false) Integer saleAreaId,
			@ApiParam(value = "销售商圈ID", required = false) @RequestParam(required=false) Integer saleCircleId,
			@ApiParam(value = "付款条款ID", required = false) @RequestParam(required=false) Integer paymentProvisionId,
			@ApiParam(value = "按某一列排序", required = false) @RequestParam(required=false) String order,
			@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort) throws FileNotFoundException {
        String name = "门店信息导出";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        try {
	        //输出Excel文件
	        OutputStream output = null;
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
        	
        	List<StoreExportVO> storeVoList = storeService
        			.selectStoreVoPage(chainType, key, groupId, businessManName, distrPriceType, 
        					storeLevelId, saleAreaId, saleCircleId, paymentProvisionId, loginUser.getEnterpriseId(), order, sort);
        	
            output = response.getOutputStream();
            storeService.excelExport(output, storeVoList, loginUser);
        } catch (Exception e) {
        	logger.error("导出门店信息错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
        
    }
	
	
	
	@ApiOperation(value="新增门店获取编码信息", notes = "新增门店获取编码信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseCode", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> getEnterpriseCode(HttpServletRequest request){
		Result<String> result = new Result<String>();
        try{
        	
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	String code = codeComponent.generate("Enterprise", 4, loginUser.getEnterpriseId());
        	
        	if(StringUtils.isBlank(code)){
        		
        		result.setBizCodeSuccessInfo(SysCode.FAIL, code);
        		
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, code);
        }catch(Exception e){
        	logger.error("新增门店获取编码信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="根据名称获取检索码信息", notes = "根据名称获取检索码信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterprisePinYin", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> getEnterprisePinYin(HttpServletRequest request,
			@ApiParam(value = "名称", required = false) @RequestParam(required = false) String name){
		Result<String> result = new Result<String>();
        try{
        	String pinyin = ""; 
        	
        	if(StringUtils.isNotBlank(name)){
        		pinyin = PinYinUtils.getFirstSpell(name);
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, pinyin);
        }catch(Exception e){
        	logger.error("根据名称获取检索码信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取门店基本信息", notes = "获取门店基本信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterprise", method = RequestMethod.POST)
	@ResponseBody
	public Result<StoreEnterpriseResponseVO> getEnterprise(HttpServletRequest request,
			@ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId){
		Result<StoreEnterpriseResponseVO> result = new Result<StoreEnterpriseResponseVO>();
        try{
        	
        	Enterprise enterprise = enterpriseService.queryEnterpriseById(enterpriseId, "store");
        	
        	enterprise = enterpriseService.queryEnterpriseAuxiliary(enterprise);//获取辅助信息
        	
        	StoreEnterpriseResponseVO vo = enterpriseService.transfromationResponseEnterprise(enterprise);
        	
        	if(enterprise == null){
        		
        		result.setBizCodeSuccessInfo(SysCode.FAIL, vo);
        		
        		return result;
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取门店基本信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="新增门店-保存门店信息 ", notes = "新增门店-保存门店信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveEnterprise", method = RequestMethod.POST)
	public Result<String> saveEnterprise(HttpServletRequest request,
			@RequestBody AddStoreEnterpriseVO addStoreEnterpriseVO) {
		Result<String> result = new Result<String>();
		try{
			/*HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	String msg = storeService.saveEnterprise(addStoreEnterpriseVO, loginUser);
			
        	if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL_WITH_TIPS, msg);
				return result;
			}*/
        	
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
		}catch(Exception e){
			logger.error("保存门店信息错误:"+e.getMessage(),e);
			result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="修改门店-保存门店信息 ", notes = "修改门店-保存门店信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/updateEnterprise", method = RequestMethod.POST)
	public Result<String> updateEnterprise(@ApiIgnore UserVO loginUser,
			@RequestBody StoreEnterpriseVO storeEnterpriseVO,
			@ApiParam(value = "修改原因-新增时不传", required = false) @RequestParam(required = false) String reason) {
		Result<String> result = new Result<String>();
		try{
        	String msg = storeService.updateEnterprise(storeEnterpriseVO, loginUser, reason);
			
        	if(StringUtils.isNotBlank(msg)){
				result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), msg, "");
				return result;
			}
        	
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
		}catch(Exception e){
			logger.error("保存门店信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	
	
	@ApiOperation(value="获取门店类型信息", notes = "获取门店类型信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getChainType", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getChainType(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (ChainType s : ChainType.values()){ 
        		if(s.getCode() == 0){//排除总部
        			continue;
        		}
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取门店类型信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="根据类型获取门店分组信息", notes = "根据类型获取门店分组信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStoreGroup", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBeanWithCode>> getStoreGroup(HttpServletRequest request,
			@ApiParam(value = "类型（1-自营店；2-加盟店）", required = false) @RequestParam(required = false) Integer type){
		Result<List<SelectBeanWithCode>> result = new Result<List<SelectBeanWithCode>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	List<SelectBeanWithCode> list = storeService.selectStoreGroupByEnterpriseId(loginUser.getEnterpriseId(), type);
        			
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取门店分组信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取配货价格下拉信息", notes = "获取配货价格下拉信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDistrPriceType", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getDistrPriceType(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (DistrPriceType s : DistrPriceType.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取价格类型信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	

	@ApiOperation(value="获取价格清单下拉信息", notes = "获取价格清单下拉信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPriceOrder", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBeanWithCode>> getPriceOrder(HttpServletRequest request){
		Result<List<SelectBeanWithCode>> result = new Result<List<SelectBeanWithCode>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<SelectBeanWithCode> list = storeService.selectPriceOrderListByEnterpriseId((loginUser.getParentId()==null||loginUser.getParentId()==0)
        			?loginUser.getEnterpriseId():loginUser.getParentId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
        	logger.error("获取价格类型信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取门店级别信息", notes = "获取门店级别信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStoreLevel", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<NewSelectBean>> getStoreLevel(HttpServletRequest request){
		Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<NewSelectBean> list = storeService.selectStoreLevelByEnterpriseId(loginUser.getEnterpriseId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取门店级别信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取销售片区信息", notes = "获取销售片区信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getSaleArea", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<NewSelectBean>> getSaleArea(HttpServletRequest request){
		Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<NewSelectBean> list = storeService.selectSaleAreaByEnterpriseId(loginUser.getEnterpriseId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取销售片区信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取销售商圈信息", notes = "获取销售商圈信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getSaleCircle", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<NewSelectBean>> getSaleCircle(HttpServletRequest request){
		Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<NewSelectBean> list = storeService.selectSaleCircleByEnterpriseId(loginUser.getEnterpriseId());
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取销售商圈信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取付款条款信息", notes = "获取付款条款信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPaymentProvision", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPaymentProvision(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (PaymentProvision s : PaymentProvision.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取是否医保药店信息", notes = "获取是否医保药店信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getMedicalFlag", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getMedicalFlag(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (MedicalFlag s : MedicalFlag.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取启用禁用状态信息", notes = "获取启用禁用状态信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnableStatus", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getEnableStatus(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (EnableStatus s : EnableStatus.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getStatus()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取标识状态信息", notes = "获取标识状态信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getValidFlag", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getValidFlag(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (ValidFlag s : ValidFlag.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取结算方式信息", notes = "获取结算方式信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getSettlementMode", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getSettlementMode(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (SettlementMode s : SettlementMode.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取付款账期单位信息", notes = "获取付款账期单位信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPaymentPeriodUnit", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPaymentPeriodUnit(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (PaymentPeriodUnit s : PaymentPeriodUnit.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="获取付款时间单位信息", notes = "获取付款时间单位信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPaymentTimeUnit", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPaymentTimeUnit(HttpServletRequest request){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	for (PaymentTimeUnit s : PaymentTimeUnit.values()){ 
        		SelectBean bean = new SelectBean(); 
        		bean.setId(String.valueOf(s.getCode()));
        		bean.setText(s.getName());
        		list.add(bean);
    		}
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="根据付款时间单位获取付款时间信息", notes = "根据付款时间单位获取付款时间信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPaymentTimeByUnit", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBean>> getPaymentTimeByUnit(HttpServletRequest request,
			@ApiParam(value = "付款时间单位（0-每月；1-每周）", required = true) @RequestParam Integer paymentTimeUnit){
		Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
        	List<SelectBean> list = new ArrayList<SelectBean>();
        	
        	if(paymentTimeUnit == 1){
        		for (PaymentTime s : PaymentTime.values()){
        			if(s.getCode() > 7){
        				break;
        			}
            		SelectBean bean = new SelectBean(); 
            		bean.setId(String.valueOf(s.getCode()));
            		bean.setText(s.getName());
            		list.add(bean);
        		}
        	}else if(paymentTimeUnit == 0){
        		for (PaymentTime s : PaymentTime.values()){ 
            		SelectBean bean = new SelectBean(); 
            		bean.setId(String.valueOf(s.getCode()));
            		bean.setText(String.valueOf(s.getName()));
            		list.add(bean);
        		}
        	}
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="根据门店Id获取门店用户", notes = "根据门店Id获取门店用户 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStoreUserByEnterpriseId", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SelectBeanWithCode>> getStoreUserSelectBeanByEnterpriseId(HttpServletRequest request,
			@ApiParam(value = "门店ID", required = true) @RequestParam Long enterpriseId){
		Result<List<SelectBeanWithCode>> result = new Result<List<SelectBeanWithCode>>();
        try{
        	List<SelectBeanWithCode> list = storeService.getStoreUserSelectBeanByEnterpriseId(enterpriseId);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        	
        }catch(Exception e){
        	logger.error("获取付款条款信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
}
