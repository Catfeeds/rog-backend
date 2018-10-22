package com.rograndec.feijiayun.chain.business.report.finance.stock.controller;


import com.rograndec.feijiayun.chain.business.report.finance.stock.service.StockInOutService;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutEnterpriseVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * @ClassName: StockController
 * @Description:
 * @author dongyang.du
 * @version 1.0
 * @date 2018-01-08 11:21:24
 */
@Api(value = "report_finance_stock_inout",description = "财务管理-存货管理-库存明细账")
@RestController
@RequestMapping("/report/finance/stock")
public class StockInOutDtlController {


    @Autowired
    private StockInOutService inOutService;

    @ApiOperation(value = "库存明细账列表", notes = "库存明细账列表 | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/inouts")
    public Result<Page<List<StockInOutEnterpriseVO>>> getInOuts(HttpServletRequest request, @RequestBody StockInOutReqVO stockInOutReqVO){

        Result<Page<List<StockInOutEnterpriseVO>>> result = new Result<>();
        if (stockInOutReqVO.getPageNo() <= 0 || stockInOutReqVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }


        if (stockInOutReqVO.getStartDate() == null || stockInOutReqVO.getEndDate() == null) {
            throw new BusinessException("请选择起始时间");
        }
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page<List<StockInOutEnterpriseVO>> inouts =  inOutService.getInouts(loginUser,stockInOutReqVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inouts);

        return result;
    }

    @ApiOperation(value = "库存明细账列表导出", notes = "库存明细账列表导出 | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/inouts/export")
    public void export(HttpServletRequest request, HttpServletResponse response ,StockInOutReqVO stockInOutReqVO) throws IOException {


        if (stockInOutReqVO.getStartDate() == null || stockInOutReqVO.getEndDate() == null) {
            throw new BusinessException("请选择起始时间");
        }


        UserVO userVO =  (UserVO)request.getSession().getAttribute("user");
        String name = "库存明细账";
        name += ".xlsx";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name,"UTF-8") );
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        inOutService.export(output,stockInOutReqVO,userVO);
        output.close();
        output.flush();
    }


}
