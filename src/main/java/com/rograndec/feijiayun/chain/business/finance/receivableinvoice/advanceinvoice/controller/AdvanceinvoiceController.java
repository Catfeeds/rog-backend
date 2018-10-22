package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid.PrepayinvoiceAccountVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountParamVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.service.IAdvanceinvoiceService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReceivableInvoiceAccountDetailVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableinvoiceSelectVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableinvoiceVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceListrequestVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceUpdateVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.ReveivableInvoiceListParamVo;
import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.valid.annotation.ValidUtils;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 
 * @author czm
 *
 */
@Api(value = "receivable_invoice", description = "预收发票-预收发票接口服务")
@RestController
@RequestMapping("finance/receivableinvoice/advanceinvoice")
public class AdvanceinvoiceController {
    private static final Log logger = LogFactory.getLog(AdvanceinvoiceController.class);
    @Autowired
    private IAdvanceinvoiceService iAdvanceinvoiceService;

    @ApiOperation(value = "保存应预收发票单据", notes = "保存预收发票单据 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> saveReveivableinvoice(HttpServletRequest request,
                                               @ApiParam(value = "保存预收发票单据", required = true) @RequestBody AdvanceReveivableinvoiceVo advanceReveivableinvoiceVo) {
        Result<Object> result = new Result<>();
        try {
        	// 参数校验
    		ValidUtils.validObject(advanceReveivableinvoiceVo);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String code = iAdvanceinvoiceService.save(loginUser, advanceReveivableinvoiceVo);
            Map<String,String> map=new HashMap<>();
            map.put("code", code);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, map);
        } catch (Exception e) {
            logger.error("保存预收发票单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
    @ApiOperation(value = "查询应预收发票单据", notes = "查询应预收发票单据 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public Result<AdvanceReceivableInvoice> selectReveivableinvoice(HttpServletRequest request,
    		  @ApiParam(value = "发票id", required = true) @RequestParam Long invoiceId) {
        Result<AdvanceReceivableInvoice> result = new Result<>();
        try {
            AdvanceReceivableInvoice reveivableinvoice = iAdvanceinvoiceService.selectReveivableinvoiceAdd(invoiceId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, reveivableinvoice);
        } catch (Exception e) {
            logger.error("查询应预收发票单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
    @ApiOperation(value = "打印应预收发票单据", notes = "打印应预收发票单据 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectForPrint", method = RequestMethod.GET)
    @ResponseBody
    public Result<AdvanceReveivableinvoiceSelectVo> selectReveivableinvoiceForPrint(HttpServletRequest request,
    		@ApiParam(value = "发票id", required = true) @RequestParam Long invoiceId) {
    	Result<AdvanceReveivableinvoiceSelectVo> result = new Result<>();
    	try {
    		AdvanceReveivableinvoiceSelectVo selectReveivableinvoice = iAdvanceinvoiceService.selectReveivableinvoice(invoiceId);
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS, selectReveivableinvoice);
    	} catch (Exception e) {
    		logger.error("打印应预收发票单据错误:" + e.getMessage(), e);
    		result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
    		return result;
    	}
    	return result;
    }
    @ApiOperation(value = "查询应预收发票单据的对账详情", notes = "查询应预收发票单据的对账详情 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectAccountDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<AdvanceReceivableInvoiceAccountDetailVo>> selectReveivableinvoiceAccountDetail(HttpServletRequest request,
    		@ApiParam(value = "发票id", required = true) @RequestParam Long invoiceId) {
    	Result<List<AdvanceReceivableInvoiceAccountDetailVo>> result = new Result<>();
    	try {
    		 List<AdvanceReceivableInvoiceAccountDetailVo> list = iAdvanceinvoiceService.selectReveivableInvoiceAccountDetail(invoiceId);
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
    	} catch (Exception e) {
    		logger.error("查询应预收发票单据的对账详情错误:" + e.getMessage(), e);
    		result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
    		return result;
    	}
    	return result;
    }
    @ApiOperation(value = "自动对账", notes = "自动对账 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/aotoAccount", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<AdvanceReceivableInvoiceAccountDetailVo>> aotoAccount(HttpServletRequest request,
    		@ApiParam(value = "发票id", required = true) @RequestParam Long invoiceId) {
    	Result<List<AdvanceReceivableInvoiceAccountDetailVo>> result = new Result<>();
    	try {
    		List<AdvanceReceivableInvoiceAccountDetailVo> list = iAdvanceinvoiceService.autoAccount(invoiceId);
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
    	} catch (Exception e) {
    		logger.error("查询应预收发票单据的对账详情错误:" + e.getMessage(), e);
    		result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
    		return result;
    	}
    	return result;
    }
    @ApiOperation(value = "查询应预收发票单据", notes = "查询应预收发票单据 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public Result<Page<ReveivableInvoiceListParamVo>> selectReveivableinvoiceList(HttpServletRequest request,
    		  @ApiParam(value = "列表查询参数", required = true)  @RequestBody InvoiceListrequestVo invoiceListrequestVo) {
    	Result<Page<ReveivableInvoiceListParamVo>> result = new Result<Page<ReveivableInvoiceListParamVo>>();
    	//参数校验
    	ValidUtils.validObject(invoiceListrequestVo);
		  HttpSession session = request.getSession(true);
	      UserVO loginUser = (UserVO) session.getAttribute("user");
	      invoiceListrequestVo.setEnterpriseId(loginUser.getEnterpriseId());
	      if("code".equals(invoiceListrequestVo.getOrderName())) {
	          //不做处理
          }else if("billDate".equals(invoiceListrequestVo.getOrderName())){
	          invoiceListrequestVo.setOrderName("bill_date");
	      }else {
	          invoiceListrequestVo.setOrderName(null);
	      }
	      if(StringUtils.isNotEmpty(invoiceListrequestVo.getOrderType())) {
	          if("ASC".equalsIgnoreCase(invoiceListrequestVo.getOrderType())||"DESC".equalsIgnoreCase(invoiceListrequestVo.getOrderType())) {
	              //不做处理
              }else {
	              invoiceListrequestVo.setOrderType(null);
	          }
	      }
    	//数据请求
    	Page<ReveivableInvoiceListParamVo> page = new Page<ReveivableInvoiceListParamVo>(invoiceListrequestVo.getPageNo(), invoiceListrequestVo.getPageSize());
        try {
        	ReveivableInvoiceListParamVo rv = iAdvanceinvoiceService.selectReveivableinvoiceList(page, invoiceListrequestVo);
            page.setResult(rv);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询应预收发票单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
    @ApiOperation(value = "保存预收发票单据到草稿", notes = "保存应预收发票单据到草稿 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save/draft", method = RequestMethod.POST)
    @ResponseBody
    public Result<DraftCacheVO> saveReveivableinvoiceForDraft(HttpServletRequest request,
    		@ApiParam(value = "保存应预收发票单据到草稿", required = true) @RequestBody  DraftCacheVO draftCacheVO) {
    	   Result<DraftCacheVO> result = new Result<>();
           try {
        	   HttpSession session = request.getSession(true);
               UserVO userVO = (UserVO) session.getAttribute("user");
               result.setBizCodeSuccessInfo(SysCode.SUCCESS,iAdvanceinvoiceService.saveDraft(userVO,draftCacheVO));

           } catch (ApprovalFlowBizException e){
               result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
               return result;
           }catch (BusinessException e) {
               result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
           } catch (Exception e) {
               logger.error("保存配货单草稿缓存:" + e.getMessage(), e);
               result.setBizCodeFallInfo(SysCode.FAIL);
           }

           return result;
    }
    @ApiOperation(value = "删除预收发票单据草稿", notes = "删除预收发票单据草稿 | 开发者 zongmin.chi | 开发中")
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{supplierId}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path"),
			@ApiImplicitParam(name = "supplierId", value = "supplierId"
			, required = true, paramType="path")
    })
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue, @PathVariable(required = true) Long supplierId) {

        Result result = new Result<>();
        try {

        	iAdvanceinvoiceService.removeDraftCach(supplierId,userVO.getEnterpriseId(), OrderRule.ADVANCE_RECEIVABLE_INVOICE.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("删除配货单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return  result;
    }
    @ApiOperation(value = "查看配货单草稿缓存", notes = "查看预收发票单据草稿  | 开发者 zongmin.chi | 开发中")
    @RequestMapping(value = "/daftCache/{supplierId}", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@PathVariable Long supplierId ,@ApiIgnore UserVO userVO) {
        Result<List<DraftCacheVO>> result = new Result<>();
        try {
        List<DraftCacheVO> draftCacheVO = iAdvanceinvoiceService.getDraftCacheVO(supplierId,userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看配货单草稿缓存:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return  result;
    }
    @ApiOperation(value = "预收发票单据的对账", notes = "预收发票单据的对账 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> updateAccount(HttpServletRequest request,@RequestBody
			@Valid @PrepayinvoiceAccountVaild List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParamVO) {
    	Result<Object> result = new Result<>();
    	try {
    		 HttpSession session = request.getSession(true);
             UserVO loginUser = (UserVO) session.getAttribute("user");
    		 iAdvanceinvoiceService.updateAccount(loginUser,perpayInvoiceAccountParamVO);
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS, "更新成功");
    	} catch (Exception e) {
    		logger.error("预收发票单据的对账错误:" + e.getMessage(), e);
    		result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
    		return result;
    	}
    	return result;
    }
    @ApiOperation(value = "预收发票单据的更新", notes = "预收发票单据的更新 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateInvoice", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> updateInvoice(HttpServletRequest request,@RequestBody
    		InvoiceUpdateVo invoiceUpdateVo) {
    	Result<Object> result = new Result<>();
    	try {
    		HttpSession session = request.getSession(true);
    		UserVO loginUser = (UserVO) session.getAttribute("user");
    	   	// 参数校验
    		ValidUtils.validObject(invoiceUpdateVo);
    		String code = iAdvanceinvoiceService.updateInvoice(loginUser,invoiceUpdateVo);
    		 Map<String,String> map=new HashMap<>();
             map.put("code", code);
    		result.setBizCodeSuccessInfo(SysCode.SUCCESS, map);
    	} catch (Exception e) {
    		logger.error("预收发票单据的对账错误:" + e.getMessage(), e);
    		result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
    		return result;
    	}
    	return result;
    }
    @ApiOperation(value = "查询应预收发票单据更新记录", notes = "查询应预收发票单据更新记录 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectUpdateList", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<AdvanceReceivableInvoiceModifyRecord>>> selectUpdateList(HttpServletRequest request,
    		@ApiParam(value = "发票id", required = true) @RequestParam Long keyId,
            @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize) {
    	Result<Page<List<AdvanceReceivableInvoiceModifyRecord>>> result = new Result<Page<List<AdvanceReceivableInvoiceModifyRecord>>>();
    	HttpSession session = request.getSession(true);
		UserVO loginUser = (UserVO) session.getAttribute("user");
    	//数据请求
    	Page<List<AdvanceReceivableInvoiceModifyRecord>> page = new Page<List<AdvanceReceivableInvoiceModifyRecord>>(pageNo, pageSize);
        try {
            List<AdvanceReceivableInvoiceModifyRecord> list = iAdvanceinvoiceService.selectAdvanceReceivableInvoiceModifyRecordList(keyId,page, loginUser);
            page.setResult(list);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("查询应预收发票单据更新记录:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
    @ApiOperation(value = "导出预收发票", notes = "导出预收发票 | 开发者 zongmin.chi| 开发中 ")
    @RequestMapping(value="/exportInvoice/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "预收发票ID", required = true, dataType = "Long", paramType="path")
    public void exportReveivableVoucher(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"应收贷项凭证";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
    		AdvanceReveivableinvoiceSelectVo selectReveivableinvoice = iAdvanceinvoiceService.selectReveivableinvoice(id);
    		iAdvanceinvoiceService.exportExcel(output,selectReveivableinvoice,loginUser);
        }catch(Exception e){
            logger.error("导出预收发票信息错误:"+e.getMessage(),e);
        }
    }
    @ApiOperation(value = "导出修改记录", notes = "导出修改记录 | 开发者 zongmin.chi| 开发中 ")
    @RequestMapping(value="/exportUpdate/{keyId}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "keyId", value = "预收发票ID", required = true, dataType = "Long", paramType="path")
    public void exportUpdate(HttpServletRequest request, HttpServletResponse response,@PathVariable Long keyId){
    	OutputStream output = null;
    	try {
    		output = response.getOutputStream();
    		//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		HttpSession session = request.getSession(true);
    		UserVO loginUser = (UserVO) session.getAttribute("user");
    		String name = loginUser.getEnterpriseName()+"应收贷项凭证";
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<AdvanceReceivableInvoiceModifyRecord> list = iAdvanceinvoiceService.selectAdvanceReceivableInvoiceModifyRecordList(keyId,loginUser);
            iAdvanceinvoiceService.exportUpdateExcel(output,list,loginUser);

    	}catch(Exception e){
    		logger.error("导出预收发票信息错误:"+e.getMessage(),e);
    	}
    }
    @ApiOperation(value = "收发票单据冲销", notes = "收发票单据冲销| 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> cancel(HttpServletRequest request,
    		  @ApiParam(value = "发票id", required = true) @RequestParam Long invoiceId) {
        Result<Object> result = new Result<>();
        try {
        	 HttpSession session = request.getSession(true);
             UserVO loginUser = (UserVO) session.getAttribute("user");
           iAdvanceinvoiceService.cancel(loginUser,invoiceId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "冲销成功");
        } catch (Exception e) {
            logger.error("查询应预收发票单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        }
        return result;
    }
}
