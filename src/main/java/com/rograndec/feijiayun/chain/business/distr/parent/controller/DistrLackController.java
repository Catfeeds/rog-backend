package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLackDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrLackService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.RequestDistrLackPram;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrLackController
 * @Description: 总部-配货出库-缺配单-Rest接口
 * @date 2017-10-07 15:58:51
 */
@Api(value = "DistrLackController", description = "总部-配货出库-缺配单")
@RestController
@RequestMapping("/distr/distrLack")
public class DistrLackController {

	private static final Logger logger = LoggerFactory.getLogger(DistrLackController.class);

	@Autowired
	private DistrLackService distrLackService;


	@ApiOperation(value = "总部-配货出库-缺配单分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getDistrLackByParam", method = RequestMethod.POST)
	public Result<Page<List<DistrLack>>> getDistrLackByParam(HttpSession session,
															 @Valid @RequestBody RequestDistrLackPram requestDistrLackPram) throws Exception {
		Result<Page<List<DistrLack>>> result = new Result<>();
		int pageNo = requestDistrLackPram.getPageNo();
		int pageSize = requestDistrLackPram.getPageSize();
		try {
			if (pageNo <= 0 || pageSize <= 0) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
				return result;
			}
			Page page = new Page(pageNo, pageSize);
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			requestDistrLackPram.setEnterpriseId(userVO.getEnterpriseId());

			if(userVO.getChainType() != ChainType.Headquarters.getCode()){//分店查询自己要货的缺配单
				requestDistrLackPram.setRequestUnitId(userVO.getEnterpriseId());
				requestDistrLackPram.setEnterpriseId(userVO.getParentId());
			}
			distrLackService.getDistrLackData(requestDistrLackPram, page);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		} catch (Exception e) {
			logger.error("获取总部-配货出库-缺配单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查看总部-配货出库-缺配单", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getDistrLack/{id}", method = RequestMethod.GET)
	public Result<DistrLack> getDistrLackData(HttpSession session,
											  @ApiParam(value = "处理单id", required = true) @PathVariable("id") Long id) throws Exception {
		Result<DistrLack> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			DistrLack distrLack=distrLackService.getDistrLackAndDetail(id);
			Integer distrType = distrLack.getDistrType();
			String distrTypeName = "";
			//0-总部配送；3-分店间调剂；4-直调配送
			if(distrType == DistributionType.DISTRIBUTION_HEAD.getCode()){
				distrTypeName = "总部配送";
			}else if(distrType == DistributionType.SWAP_BETWEEN_STORES.getCode()){
				distrTypeName = "分店间调剂";
			}else if(distrType == DistributionType.DIRECT_DISTRIBUTION.getCode()){
				distrTypeName = "直调配送";
			}
			distrLack.setDistrTypeName(distrTypeName);
			List<DistrLackDetail> list = distrLack.getDistrLackDetailList();
			BigDecimal totalRequestQuantity = BigDecimal.ZERO;
			BigDecimal totalSendQuantity = BigDecimal.ZERO;
			BigDecimal totalLackQuantity = BigDecimal.ZERO;
			for(DistrLackDetail temp : list){
				totalRequestQuantity = totalRequestQuantity.add(temp.getRequestQuantity());
				totalSendQuantity = totalSendQuantity.add(temp.getSendQuantity());
				totalLackQuantity = totalLackQuantity.add(temp.getLackQuantity());
			}
			distrLack.setTotalRequestQuantity(totalRequestQuantity);
			distrLack.setTotalSendQuantity(totalSendQuantity);
			distrLack.setTotalLackQuantity(totalLackQuantity);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrLack);
		} catch (Exception e) {
			logger.error("查看总部-配货出库-缺配单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "取消-配货出库-缺配单", notes = "更新数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
	public Result<String> cancel(HttpSession session,
								 @ApiParam(value = "处理单id", required = true) @PathVariable("id") Long id) {
		Result<String> result = new Result<>();
		try {
			// 当前登录用户数据
			UserVO userVO = (UserVO) session.getAttribute("user");
			distrLackService.cancel(id, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		} catch (BusinessException e){
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),e.getMessage());
			return result;
		}catch (Exception e) {
			logger.error("更新总部-配货出库-缺配单数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "导出总部-配货出库-缺配单", notes = "导出数据 | 开发者 zhengbin.jin | 已联调")
	@RequestMapping(value = "/excelExport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void excelExport(HttpServletRequest request, HttpServletResponse response,
							@ApiParam(value = "处理单id", required = true) @PathVariable("id") Long id) throws FileNotFoundException {
		String name = "缺配单";
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = null;

			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");

			output = response.getOutputStream();
			distrLackService.excelExport(output,userVO,id);
		} catch (Exception e) {
			logger.error("导出总部-配货出库-缺配单错误:" + e.getMessage(), e);
			e.printStackTrace();
		}

	}

}
