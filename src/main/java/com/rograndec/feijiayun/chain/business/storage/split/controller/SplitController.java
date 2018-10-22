package com.rograndec.feijiayun.chain.business.storage.split.controller;

import com.rograndec.feijiayun.chain.business.storage.split.service.SplitDetailService;
import com.rograndec.feijiayun.chain.business.storage.split.service.SplitService;
import com.rograndec.feijiayun.chain.business.storage.split.vo.RequestSaveGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.RequestSplitOrderVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.ResponseSplitVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitGoodsStockVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitOrderPageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lizhongyi
 */
@Api(value = "storage_split_split", description = "储存管理-拆零管理-商品拆零")
@RestController
@RequestMapping("storage/split/split")
@Validated
public class SplitController {
    private static final Log logger = LogFactory.getLog(SplitSetController.class);

    @Autowired
    private SplitService splitService;

    @Autowired
    SplitDetailService splitDetailService;

    @ApiOperation(value = "查询可拆零的商品列表", notes = "查询可拆零的商品列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getGoodsStockList", method = RequestMethod.GET)
    public Result<List<SplitGoodsStockVO>> getGoodsStockList(HttpServletRequest request,
                                                             @ApiParam(value = "商品编码/条形码/检索码/商品名称/通用名称/批准文号", required = false) @RequestParam(required = false) String param) {
        Result<List<SplitGoodsStockVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, splitService.getGoodsStockList(param, loginUser));
        } catch (Exception e) {
            logger.error("商品拆零-查询可拆零的商品列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存商品拆零单", notes = "保存商品拆零单 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/saveSplitOrder", method = RequestMethod.POST)
    public Result saveSplitOrder(HttpServletRequest request, @RequestBody RequestSplitOrderVO requestSplitOrderVO) {
        Result result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            if (requestSplitOrderVO == null) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "传参错误");
                return result;
            }

            //分拆人员必填
            if (requestSplitOrderVO.getSplitManId().compareTo(0l) == 0) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请选择分拆人员！");
                return result;
            }

            //复核人员必填
            if (requestSplitOrderVO.getAuditManId().compareTo(0l) == 0) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请选择复核人员！");
                return result;
            }

            //商品判断
            if (requestSplitOrderVO.getSplitGoods() == null || requestSplitOrderVO.getSplitGoods().size() == 0) {
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请选择需要拆零的商品！");
                return result;
            }

            for (RequestSaveGoodsVO requestGoodsVO : requestSplitOrderVO.getSplitGoods()) {
                if (requestGoodsVO.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                    result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请输入正确的拆零数量！");
                    return result;
                }
            }

            splitService.saveSplitOrder(loginUser, requestSplitOrderVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("商品拆零-拆零单据保存错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询商品拆零单列表", notes = "查询商品拆零单列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getSplitOrderList", method = RequestMethod.GET)
    public Result<Page<SplitOrderPageVO>> getSplitOrderList(HttpServletRequest request,
                                                            @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                            @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                            @ApiParam(value = "起始时间", required = false) @RequestParam(required = false) Date startTime,
                                                            @ApiParam(value = "终止时间", required = false) @RequestParam(required = false) Date endTime,
                                                            @ApiParam(value = "拆零单号", required = false) @RequestParam(required = false) String code,
                                                            @ApiParam(value = "拆零人员", required = false) @RequestParam(required = false) String splitManName,
                                                            @ApiParam(value = "复核人员", required = false) @RequestParam(required = false) String auditManName,
                                                            @ApiParam(value = "排序规则(日期-splitDate；单号-code)", required = false) @RequestParam(required=false) String order,
                                                            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort) {

        Result<Page<SplitOrderPageVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Page page = new Page(pageNo, pageSize);

            page = splitService.getSplitOrderList(page, startTime, endTime, code, splitManName, auditManName, order, sort, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("商品拆零-查询单据列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

	@ApiOperation(value = "查询商品拆零明细列表", notes = "根据商品拆零单ID查询商品拆零明细列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getSplitOrderDtlList/{id}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "拆零单id", required = true, paramType = "path")
    public Result<ResponseSplitVO> getSplitOrderDtlList(@PathVariable Long id){
		Result<ResponseSplitVO> result=new Result<ResponseSplitVO>();
		try{
			ResponseSplitVO responseSplitVO=splitDetailService.getSplitOrderDtlList(id);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,responseSplitVO);
			return result;
		}catch(Exception e){
			logger.error("查询商品拆零明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}

	@ApiOperation(value = "导出Excel", notes = "按照模版将商品拆零单导出至Excel | 开发者 张东东 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "拆零单id", required = true, paramType = "path")
    public void exportExcel(HttpServletResponse response,HttpSession session,@ApiIgnore UserVO userVO,@PathVariable Long id){
		ResponseSplitVO responseSplitVO=splitDetailService.getSplitOrderDtlList(id);
		if(responseSplitVO==null){
			responseSplitVO=new ResponseSplitVO();
			responseSplitVO.setSplitDetailList(new ArrayList<>());
		}
		OutputStream output=null;
		try{
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="拆零商品记录";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            splitDetailService.exportExcel(output, responseSplitVO,userVO);
		}catch(Exception e){
			logger.error("拆零商品记录导出表异常:" + e.getMessage(), e);
		}

	}

}
