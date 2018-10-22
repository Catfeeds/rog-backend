
package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.controller;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.service.PrepayInvoiceService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid.PrepayinvoiceAccountVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid.PrepayinvoiceAutpAccountVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid.PrepayinvoiceSaveVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid.PrepayinvoiceWriteOffVaild;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 功能描述：
 * Created by zhaiwei on 2019/1/10 21:29
 */
@Api(value = "prepay_invoice_controller", description = "预付发票")
@RequestMapping("prepay/invoice/")
@RestController
@Validated
public class PrepayInvoiceController {

	private static final Log logger = LogFactory.getLog(PrepayInvoiceController.class);

	@Autowired
	private PrepayInvoiceService prepayInvoiceService;

	@ApiOperation(value = "预付发票新增/修改", notes = "预付发票新增/修改 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/save", method= RequestMethod.POST)
	public Result<PrepayInvoice> save(
			@ApiParam(value = "预付发票新增实体", required = true)
			@RequestBody
			@Valid @PrepayinvoiceSaveVaild PrepayInvoiceSaveVO prepayInvoiceSaveVO, @ApiIgnore UserVO userVO
	) throws Exception {

		Result<PrepayInvoice> result = new Result<>();
		if(null == prepayInvoiceSaveVO.getId()) {
			PrepayInvoice save = prepayInvoiceService.save(userVO, prepayInvoiceSaveVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,save);
		}
		else {
			PrepayInvoice update = prepayInvoiceService.update(userVO, prepayInvoiceSaveVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,update);
		}

		return result;
	}


	@ApiOperation(value = "查询预付发票单据列表", notes = "查询预付发票单据列表 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public Result<Page<List<PrepayInvoiceResponseVO>>> list(
			@ApiParam(value = "供货单位编码", required = false) @RequestParam(required = false) String supplierCode,
			@ApiParam(value = "供货单位名称", required = false) @RequestParam(required = false) String supplierName,
			@ApiParam(value = "单据编码", required = false) @RequestParam(required = false) String code,
			@ApiParam(value = "开票人员名称", required = false) @RequestParam(required = false) String billManName,
			@ApiParam(value = "单据状态(0-待付款；1-部分付款；2-已付款；3-已冲销)", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(value = "对账状态(0-待对账；1-部分对账；2-已对账；3-已冲销)", required = false) @RequestParam(required = false) Integer accountStatus,
			@RequestParam(required = false)
			@ApiParam(name = "pageNo", value = "当前页码", required = false)
					Integer pageNo,
			@RequestParam(required = false)
			@ApiParam(name = "pageSize", value = "显示条数", required = false)
					Integer pageSize,

			@RequestParam(required = false)
			@ApiParam(name = "orderName", value = "按某一列排序", required = false)
					String  orderName,
			@RequestParam(required = false)
			@ApiParam(name = "orderType", value = "排序方式（升序：asc,降序：desc", required = false)
					String orderType,

			@RequestParam(required = false)
			@ApiParam(name = "startDate", value = "开始时间", required = false)
					String  startDate,
			@RequestParam(required = false)
			@ApiParam(name = "endDate", value = "结束时间", required = false)
					String endDate,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<Page<List<PrepayInvoiceResponseVO>>> result = new Result<>();
		PrepayInvoiceQueryParamVO prepayInvoiceQueryParamVO = new PrepayInvoiceQueryParamVO(
				supplierCode,
				supplierName,
				code,
				billManName,
				status,
				accountStatus,orderName,orderType,startDate,endDate);
		Page page = new Page(pageNo,pageSize);
		prepayInvoiceService.queryPrepayInvoiceResponseVOs(userVO,prepayInvoiceQueryParamVO,page);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
		return result;
	}


	@ApiOperation(value = "查询预付发票单据详情", notes = "查询预付发票单据详情 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预付发票id"
					, required = true, paramType="path")
	})
	@RequestMapping(value="/prepayInvoice/{id}", method= RequestMethod.GET)
	public Result<PrepayInvoiceResponseVO> prepayInvoices(
			@PathVariable Long id,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<PrepayInvoiceResponseVO> result = new Result<>();
		PrepayInvoiceResponseVO prepayInvoiceResponseVO = prepayInvoiceService.queryPrepayInvoiceResponseVO(userVO, id);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,prepayInvoiceResponseVO);
		return result;
	}

	@ApiOperation(value = "查询对账-发票清单列表", notes = "查询对账-发票清单列表 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预付发票id"
					, required = true, paramType="path")
	})
	@RequestMapping(value="/prepayInvoice/reconciliation/{id}", method= RequestMethod.GET)
	public Result<PrepayInvoiceResponseVO> prepayInvoice2Reconciliations(
			@PathVariable Long id,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<PrepayInvoiceResponseVO> result = new Result<>();
		PrepayInvoiceResponseVO prepayInvoiceResponseVO = prepayInvoiceService.queryPrepayInvoiceList(userVO, id);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,prepayInvoiceResponseVO);
		return result;
	}

	@ApiOperation(value = "查询对账-入库清单查询", notes = "查询对账-入库清单查询 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/prepayInvoice/in/store", method= RequestMethod.GET)
	@Deprecated
	public Result<PrepayInvoiceInStoreResponseTotalVO> prepayInvoice2InStore(
			@RequestParam(required = false)
			@ApiParam(name = "startDate", value = "开始时间", required = false)
					String  startDate,
			@RequestParam(required = false)
			@ApiParam(name = "endDate", value = "结束时间", required = false)
					String endDate,
			@RequestParam(required = true)
			@ApiParam(name = "goodsId", value = "商品id", required = true)
					Long goodsId,
			@RequestParam(required = true)
			@ApiParam(name = "id", value = "预付发票明细行id", required = true)
					Long id,
			@RequestParam(required = true)
			@ApiParam(name = "financeAccountType", value = "供应商类型 0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税率", required = true)
					Integer financeAccountType,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<PrepayInvoiceInStoreResponseTotalVO> result = new Result<>();

		PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = prepayInvoiceService.queryInStoreList(userVO,id, startDate, endDate, financeAccountType);
		if(null == prepayInvoiceInStoreResponseTotalVO){
			prepayInvoiceInStoreResponseTotalVO = new PrepayInvoiceInStoreResponseTotalVO();
		}
		prepayInvoiceInStoreResponseTotalVO.setId(id);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,prepayInvoiceInStoreResponseTotalVO);
		return result;
	}

	@ApiOperation(value = "对账", notes = "对账 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/reconciliation", method= RequestMethod.POST)
	public Result reconciliation(
			@RequestBody
			@Valid @PrepayinvoiceAccountVaild
			PerpayInvoiceAccountParamListVO sperpayInvoiceAccountParamVO,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result result = new Result<>();

		prepayInvoiceService.reconciliations(userVO,sperpayInvoiceAccountParamVO.getPerpayInvoiceAccountParamVOs());
		result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		return result;
	}

	@ApiOperation(value = "自动对账对账", notes = "自动对账对账 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/reconciliation/auto", method= RequestMethod.POST)
	public Result<PrepayInvoiceResponseVO> autoReconciliation(
			@RequestBody
			@Valid @PrepayinvoiceAutpAccountVaild
			PerpayInvoiceAccountParamVO perpayInvoiceAccountParamVO,
			@ApiIgnore UserVO userVO
	) throws Exception {

		Result<PrepayInvoiceResponseVO> result = new Result<>();

		PrepayInvoiceResponseVO prepayInvoiceResponseVO = prepayInvoiceService.autoReconciliation(userVO, perpayInvoiceAccountParamVO.getId());
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,prepayInvoiceResponseVO);
		return result;
	}

	@ApiOperation(value = "查看预付发票草稿", notes = "查看预付发票草稿 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "supplierId", value = "供货单位id"
					, required = true, paramType="path")
	})
	@RequestMapping(value = "/daftCache/{supplierId}", method = RequestMethod.GET)
	public Result<List<DraftCacheVO>> daftCacheVO(@PathVariable Long supplierId ,@ApiIgnore UserVO userVO) {

		Result<List<DraftCacheVO>> result = new Result<>();

		List<DraftCacheVO> draftCacheVO = prepayInvoiceService.getDraftCacheVO(supplierId,userVO);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

		return  result;
	}

	@ApiOperation(value = "保存预付发票草稿", notes = "保存预付发票草稿 | 开发者 翟伟 | 已完成")
	@RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
	public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO draftCacheVO) {
		Result<DraftCacheVO> result = new Result();
		DraftCacheVO distrReqPlanVODraftCacheVO = prepayInvoiceService.saveDraftCache(userVO, draftCacheVO);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanVODraftCacheVO);

		return  result;
	}


	@ApiOperation(value = "删除预付发票草稿", notes = "删除预付发票草稿 | 开发者 翟伟 | 已完成")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
					, required = true, paramType="path"),
			@ApiImplicitParam(name = "supplierId", value = "supplierId"
					, required = true, paramType="path")
	})

	@RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{supplierId}", method = RequestMethod.DELETE)
	public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue, @PathVariable(required = true) Long supplierId) {

		Result result = new Result<>();

		prepayInvoiceService.removeDraftCach(supplierId,userVO.getEnterpriseId(), OrderRule.PREPAY_INVOICE.getCodePrefix(),redisKeyValue);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS);

		return  result;
	}

	@ApiOperation(value = "查看修改记录", notes = "查看修改记录 | 开发者 翟伟 | 已完成")
	@RequestMapping(value = "/modifyRecords/{id}", method = RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "预付发票id", required = true, dataType = "Long", paramType="path")
	public Result<Page<List<PrepayInvoiceModifyRecordResponseVO>>> modifyRecords(
			@ApiIgnore UserVO userVO,
			@PathVariable Long id,
			@RequestParam(required = false)
			@ApiParam(name = "pageNo", value = "当前页码", required = false)
					Integer pageNo,
			@RequestParam(required = false)
				@ApiParam(name = "pageSize", value = "显示条数", required = false)
					Integer pageSize
	) {

		Result<Page<List<PrepayInvoiceModifyRecordResponseVO>>> result = new Result<>();

		Page page = new Page(pageNo,pageSize);

		prepayInvoiceService.queryModifyRecords(userVO,id,page);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);

		return  result;
	}

	@ApiOperation(value = "预付发票详情信息导出", notes = "预付发票详情信息导出 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/exportReceiveDetail/{id}",method= RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "预付发票id", required = true, dataType = "Long", paramType="path")
	public void exportReceiveDetail(HttpServletResponse response,
									@PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

		OutputStream output = response.getOutputStream();
		String name = "预付发票";
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
		PrepayInvoiceResponseVO prepayInvoiceResponseVO = prepayInvoiceService.queryPrepayInvoiceResponseVO(userVO, id);
		prepayInvoiceService.export(output, prepayInvoiceResponseVO);
	}

	@ApiOperation(value = "修改记录导出", notes = "修改记录导出 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/exportUpdateRecord/{id}",method= RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "预付发票id", required = true, dataType = "Long", paramType="path")
	public void exportUpdateRecord(HttpServletResponse response,
								   @PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

		OutputStream output = response.getOutputStream();
		String name = "预付发票修改信息";
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
		List<PrepayInvoiceModifyRecordResponseVO> prepayInvoiceModifyRecordResponseVOS = prepayInvoiceService.queryModifyRecords(userVO, id, null);
		prepayInvoiceService.exportUpdateRecord(output,prepayInvoiceModifyRecordResponseVOS,userVO);

	}


	@ApiOperation(value = "冲销", notes = "冲销 | 开发者 翟伟 | 已完成")
	@RequestMapping(value="/wariteOff/{id}",method= RequestMethod.PUT)
	@ApiImplicitParam(name = "id", value = "预付发票id", required = true, dataType = "Long", paramType="path")
	public void wariteOff(@PathVariable @PrepayinvoiceWriteOffVaild Long id, @ApiIgnore UserVO userVO) throws Exception {

		Result result = new Result<>();

		prepayInvoiceService.wariteOff(userVO,id);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS);

	}
}