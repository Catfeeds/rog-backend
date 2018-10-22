package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutService;
import com.rograndec.feijiayun.chain.business.distr.parent.valid.DistrOutValid;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.status.DistrOutStatus;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrOutController
 * @Description: 总部-配货出库-配货出库单-Rest接口
 * @date 2017-10-07 15:57:42
 */
@Api(value = "DistrOutController", description = "总部-配货出库-配货出库单")
@RestController
@RequestMapping("/distr/distrOut")
@Validated
public class DistrOutController {

	private static final Logger logger = LoggerFactory.getLogger(DistrOutController.class);

	@Autowired
	private DistrOutService distrOutService;
	@Autowired
	private CommonComponent commonComponent;


	@ApiOperation(value = "总部-配货出库-配货出库单分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getDistrOutByParam", method = RequestMethod.POST)
	public Result<Page<DistrListTotalVO>> getDistrOutByParam(HttpSession session,
															 @Valid @RequestBody RequestDistrOutPram requestDistrOutPram) throws Exception {
		Result<Page<DistrListTotalVO>> result = new Result<>();
		int pageNo = requestDistrOutPram.getPageNo();
		int pageSize = requestDistrOutPram.getPageSize();
		try {
			if (pageNo <= 0 || pageSize <= 0) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			Page page = new Page(pageNo, pageSize);
			UserVO userVO = (UserVO) session.getAttribute("user");
			requestDistrOutPram.setEnterpriseId(userVO.getEnterpriseId());
			distrOutService.getDistrOutDataList(requestDistrOutPram, page, userVO);

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (BusinessException e) {
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("获取总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查看总部-配货出库-配货出库单", notes = "查看数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getDistrOut/{id}", method = RequestMethod.GET)
	public Result<DistrOut> getDistrOutData(HttpSession session,
											@ApiParam(value = "出库单id", required = true) @PathVariable("id") Long id) throws Exception {
		Result<DistrOut> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			DistrOut distrOut = distrOutService.getDistrOutData(id);
			distrOut.setEnterpriseName(userVO.getEnterpriseName());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOut);
		} catch (Exception e) {
			logger.error("查看总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "根据药品id获取货位信息-配货出库-配货出库单", notes = "查看数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getQualifiedGoodsInfoShelf/{goodsId}/{lotNum}/{baseOrderId}", method = RequestMethod.GET)
	public Result<List<StockLockRecordVO>> getQualifiedGoodsInfoShelf( @ApiParam(value = "商品id", required = true)
																		   @PathVariable("goodsId") Long goodsId,
																	   @ApiParam(value = "批号", required = true)
																	  		 @PathVariable("lotNum") String lotNum,
																	   @ApiParam(value = "基础单据ID", required = true)
																		   @PathVariable("baseOrderId") Long baseOrderId,
																	   @ApiIgnore
																	   UserVO userVO

	) throws Exception {
		Result<List<StockLockRecordVO>> result = new Result<>();
		try {
			List<StockLockRecordVO> goodsInfoStockShelfVOList = distrOutService.getQualifiedGoodsInfoShelf(lotNum,goodsId,baseOrderId,userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsInfoStockShelfVOList);
		} catch (Exception e) {
			logger.error("查看总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "获取添加配货单数据-配货出库-配货出库单", notes = "查看数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getDistrOutForSave/{id}", method = RequestMethod.GET)
	public Result<DistrOut> getDistrOutDataForSave(HttpSession session,
												   @ApiParam(value = "出库单id", required = true) @PathVariable("id") Long id) throws Exception {
		Result<DistrOut> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			DistrOut distrOut = distrOutService.getDistrOutDataForSave(id, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOut);
		} catch (BusinessException e) {
			logger.error("查看总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("查看总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "添加总部-配货出库-配货出库单", notes = "添加数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(@ApiIgnore UserVO userVO, @Valid @RequestBody @DistrOutValid DistrOut distrOut) {
		Result<String> result = new Result<>();
		try {
			// 当前登录用户数据
			distrOutService.save(distrOut, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加成功");
		} catch (BusinessException e) {
			logger.error("添加总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("添加总部-配货出库-配货出库单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "保存复核驳回状态的单据", notes = "保存复核驳回状态的单据 | 开发者  zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveRecheck", method = RequestMethod.POST)
	public Result<String> saveRecheck(HttpSession session, @Valid @RequestBody @DistrOutValid DistrOut distrOut) {
		Result<String> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			distrOutService.saveRecheck(distrOut, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加成功");
		} catch (BusinessException e) {
			logger.error("保存复核驳回状态的单据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("保存复核驳回状态的单据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "配货出库-价格计算接口", notes = "价格计算 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/calculation", method = RequestMethod.POST)
	public Result<DistrOut> calculation(HttpSession session, @Valid @RequestBody DistrOut distrOut) {
		Result<DistrOut> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			DistrOut distrOutVo = distrOutService.calculation(distrOut, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOutVo);
		} catch (BusinessException e) {
			logger.error("配货出库-价格计算接口调用失败:", e);
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("配货出库-价格计算接口调用失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "配货出库-复核通过接口", notes = "价格计算 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public Result<Object> check(HttpSession session, @Valid @RequestBody DistrOutCheckVo distrOutCheckVo) {
		Result<Object> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			distrOutService.check(distrOutCheckVo, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);
		} catch (BusinessException e) {
			logger.error("配货出库-复核失败:", e);
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("配货出库-复核失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}


	@ApiOperation(value = "导出总部-配货出库-配货出库单", notes = "导出数据 | 开发者 zhengbin.jin | 已联调")
	@RequestMapping(value = "/excelExport/{id}/{sta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void excelExport(HttpServletRequest request, HttpServletResponse response,
							@ApiParam(value = "单据id", required = true) @PathVariable("id") Long id,
							@ApiParam(value = "状态31-待出库 32-待复核 33-已完成 ", required = true) @PathVariable("sta") Long sta) throws FileNotFoundException {
		String name = "ERROR";
		if (sta == DistrOutStatus.WAIT_OUT) {//配送单
			name = "配货出库单待出库";
		} else if (sta == DistrOutStatus.WAIT_AUDIT) {//出库单
			name = "配货出库单待复核";
		} else if (sta == DistrOutStatus.FINISHED) {
			name = "配货出库单已出库";
		}
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = response.getOutputStream();
			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			distrOutService.excelExport(output, id, sta, userVO);
		} catch (Exception e) {
			logger.error("导出总部-配货出库-配货出库单错误:" + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@ApiOperation(value = "保存配货出库单据", notes = "保存配货出库单据 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveDistrOut", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> saveDistrOut(HttpServletRequest request,
									@ApiParam(value = "保存配货出库单据", required = true) @RequestBody SaveDistrOutVO saveDistrOutVO) {
		Result<String> result = new Result<>();
		try {

			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			distrOutService.saveDistrOut(loginUser, saveDistrOutVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存配货出库单据成功");

		}  catch (BusinessException e) {
			logger.error("保存配货出库单据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("保存配货出库单据错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}


	@ApiOperation(value = "调用采购入库明细插入配货出库", notes = "调用采购入库明细插入配货出库 | 开发者 zeshi.sun | 已完成")
	@ApiImplicitParam(name = "id", value = "采购入库单ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/getPurchaseInStorageDtlShelfList/{id}", method = RequestMethod.GET)
	public Result<DistrPurchaseInStorageVO> getPurchaseInStorageDtlList(HttpServletRequest request,
																					@PathVariable("id") @NotNull Long id) {
		Result<DistrPurchaseInStorageVO> result = new Result<>();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOutService.getPurchaseInStorageDtlShelfList(loginUser, id));
		} catch (Exception e) {
			logger.error("调用采购入库明细插入配货出库错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value = "调用配后退回明细插入配货出库", notes = "调用配后退回明细插入配货出库 | 开发者 zeshi.sun | 已完成")
	@ApiImplicitParam(name = "id", value = "配后退回ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/getDistrReturnInDetailShelfList/{id}", method = RequestMethod.GET)
	public Result<DistrReturnInStorageVO> getDistrReturnInDetailShelfList(HttpServletRequest request,
																						 @PathVariable("id") @NotNull Long id) {
		Result<DistrReturnInStorageVO> result = new Result<>();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOutService.getDistrReturnInDetailShelfList(loginUser, id));
		} catch (Exception e) {
			logger.error("调用配后退回明细插入配货出库错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value = "配货出库通过商品ID选择批号货位信息", notes = "配货出库通过商品ID选择批号货位信息 | 开发者 zeshi.sun | 已完成")
	@ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/getGoodsLotShelfList/{id}", method = RequestMethod.GET)
	public Result<List<GoodsLotShelfVO>> getGoodsLotShelfList(HttpServletRequest request,
																						 @PathVariable("id") @NotNull Long id) {
		Result<List<GoodsLotShelfVO>> result = new Result<>();
		try {
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOutService.getGoodsLotShelfList(loginUser, id));
		} catch (Exception e) {
			logger.error("配货出库通过商品ID选择批号货位信息错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value = "查询采购入库单明细列表 ", notes = "查询采购入库单明细列表  | 开发者 zeshi.sun  | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getPurchaseInStorageShelfList", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<GetPurchaseInStorageShelfListVO>>> getPurchaseInStorageShelfList(HttpServletRequest request,
																					   @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
																					   @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
																					   @ApiParam(value = "采购入库ID", required = true) @RequestParam Long id,
																					   @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
																					   @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType) {
		Result<Page<List<GetPurchaseInStorageShelfListVO>>> result = new Result<>();
		try {
			if (pageNo <= 0 || pageSize <= 0) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			Page page = new Page(pageNo, pageSize);
			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");
			List<GetPurchaseInStorageShelfListVO> getPurchaseInStorageShelfListVOS = distrOutService.getPurchaseInStorageShelfList(loginUser, pageNo, pageSize, id, orderName, orderType, page);
			page.setResult(getPurchaseInStorageShelfListVOS);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			logger.error("查询采购入库单明细列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value = "查询配后退回入库单明细列表 ", notes = "查询配后退回入库单明细列表  | 开发者 zeshi.sun  | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getDistrReturnInShelfList", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<GetDistrReturnInShelfListVO>>> getDistrReturnInShelfList(HttpServletRequest request,
																			   @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
																			   @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
																			   @ApiParam(value = "配后退回入库ID", required = true) @RequestParam Long id,
																			   @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
																			   @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType) {
		Result<Page<List<GetDistrReturnInShelfListVO>>> result = new Result<>();
		try {
			if (pageNo <= 0 || pageSize <= 0) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			Page page = new Page(pageNo, pageSize);
			List<GetDistrReturnInShelfListVO> getDistrReturnInShelfListVOList = distrOutService.getDistrReturnInShelfList(pageNo, pageSize, id, orderName, orderType, page);
			page.setResult(getDistrReturnInShelfListVOList);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			logger.error("查询配后退回入库单明细列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
		return result;
	}

	@ApiOperation(value = "配货出库-价格计算接口(只适用于调用与直接新增时)", notes = "价格计算(只适用于调用与直接新增时) | 开发者 zeshi.sun | 已作废", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/calculationByCall", method = RequestMethod.POST)
	public Result<DistrOut> calculationByCall(HttpSession session, @Valid @RequestBody DistrOut distrOut) {
		Result<DistrOut> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			DistrOut distrOutVo = distrOutService.calculationByCall(distrOut, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrOutVo);
		} catch (BusinessException e) {
			logger.error("配货出库-价格计算接口(只适用于调用时)调用失败:", e);
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR, e.getMessage());
			return result;
		} catch (Exception e) {
			logger.error("配货出库-价格计算接口(只适用于调用时)调用失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

}
