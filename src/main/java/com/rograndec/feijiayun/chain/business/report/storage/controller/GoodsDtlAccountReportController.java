package com.rograndec.feijiayun.chain.business.report.storage.controller;

import com.rograndec.feijiayun.chain.business.report.storage.service.GoodsDtlAccountReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.GoodsDtlReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.RequestParamGoodsDtlReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_storage_goodsDtlAccount",description = "报表-储存管理-商品明细账")
@RestController
@RequestMapping("report/storage/goodsDtlAccount")
@Validated
public class GoodsDtlAccountReportController {

    @Autowired
    private GoodsDtlAccountReportService goodsDtlAccountReportService;


    @ApiOperation(value = "报表-储存管理-商品分页查询", notes = "报表-储存管理-商品分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsDtlReportList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestParamGoodsDtlReportVO")
    public Result<Page<List<GoodsDtlReportVO>>> getGoodsDtlReportList(HttpSession session, @RequestParamValid @RequestBody RequestParamGoodsDtlReportVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<GoodsDtlReportVO>>> result = new Result<>();
        Page<List<GoodsDtlReportVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        goodsDtlAccountReportService.getGoodsDtlReportList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "报表-储存管理-商品明细账分页查询", notes = "报表-储存管理-商品明细账分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getInOutRecordDetailReportVOList",method= RequestMethod.POST)
    public  Result<Page<InOutRecordDetailReportPageVO>> getInOutRecordDetailReportVOList(HttpServletRequest request,
                                                                                         @ApiParam(name = "paramForListVO")@RequestParamValid @RequestBody RequestParamGoodsDtlReportVO reqParam){


        if(reqParam.getGoodsId() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"商品ID不能为空");
        }

        Result<Page<InOutRecordDetailReportPageVO>>  result = new Result<>();
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        Page<InOutRecordDetailReportPageVO> page = new Page<>(reqParam.getPageNo(),reqParam.getPageSize());

        goodsDtlAccountReportService.getInOutRecordDetailReportVOList(userVO,reqParam,page);


        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return  result;

    }


    @ApiOperation(value = "导出商品明细账", notes = "商品明细账 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelGoodsDtlReportList", method = RequestMethod.GET)

    public void excelGoodsDtlReportList(@ApiParam(name = "goodsId",value = "商品ID")@RequestParam(value = "goodsId",required = true)Long goodsId,
                                        @ApiParam(name = "enterpriseId",value = "企业ID")@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                        @ApiParam(name = "dateOrder",value = "日期排序 0/倒序；1/正序;默认0")
                                            @RequestParam(value = "dateOrder",required = false,defaultValue = "0") Integer orderDate,

                                        HttpSession session, HttpServletResponse response) throws Exception {

        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "商品明细账";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = null;
        output = response.getOutputStream();
        RequestParamGoodsDtlReportVO paramForListVO = new RequestParamGoodsDtlReportVO();
        paramForListVO.setEnterpriseId(enterpriseId);
        paramForListVO.setGoodsId(goodsId);
        paramForListVO.setDateOrder(orderDate);
        goodsDtlAccountReportService.exportGoodsDtlReport(output,userVO,paramForListVO);
        output.close();
        output.flush();

    }
	
}
