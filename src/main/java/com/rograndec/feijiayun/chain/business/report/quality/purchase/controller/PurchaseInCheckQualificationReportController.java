package com.rograndec.feijiayun.chain.business.report.quality.purchase.controller;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.RequestGetInCheckQuaParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseInCheckQuaReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.inCheckQua.PurchaseInCheckQuaGoodsReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "report_quality_purchase_purchaseCheckQua",description = "报表-质量管理-收货与验收-药品验收资质")
@RestController
@RequestMapping("report/quality/purchase/purchaseCheckQua")
@Validated
public class PurchaseInCheckQualificationReportController {

    @Autowired
    private PurchaseInCheckQuaReportService purchaseInCheckQuaReportService;

    @ApiOperation(value = "报表-质量管理-收货与验收-药品验收资质分页查询", notes = "报表-质量管理-收货与验收-药品验收资质分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPurchaseInCheckQuaGoodsList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetInCheckQuaParamVO")
    public Result<Page<List<PurchaseInCheckQuaGoodsReportVO>>> getpurchaseInCheckQuaGoodsList(HttpSession session, @RequestParamValid @RequestBody RequestGetInCheckQuaParamVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<PurchaseInCheckQuaGoodsReportVO>>> result = new Result<>();
        Page<List<PurchaseInCheckQuaGoodsReportVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        purchaseInCheckQuaReportService.getInCheckQuaGoodsList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "导出总部药品验收资质单", notes = "导出药品验收资质单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value = "/excelInCheckQuaGoodsList", method = RequestMethod.POST, produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestGetInCheckQuaParamVO")
    })
    public void excelInCheckQuaGoodsList(RequestGetInCheckQuaParamVO paramForListVO, HttpSession session, HttpServletResponse response) throws Exception {

        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "总部药品验收资质单";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = null;
        output = response.getOutputStream();
        purchaseInCheckQuaReportService.exportInCheckQuaGoodsList(output,userVO,paramForListVO);
        output.close();
        output.flush();

    }
	
}
