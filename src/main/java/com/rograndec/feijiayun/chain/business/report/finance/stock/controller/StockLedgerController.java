package com.rograndec.feijiayun.chain.business.report.finance.stock.controller;

import com.rograndec.feijiayun.chain.business.report.finance.stock.service.StockLedgerService;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.BasePageReqParam;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockLedgerEnterpriseByGoodsVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockLedgerMonthVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "report_finance_stock_ledger",description = "财务管理-存货管理-库存总账")
@RestController
@RequestMapping("/report/finance/stock")
public class StockLedgerController {


    @Autowired
    private StockLedgerService ledgerService;


    @ApiOperation(value = "库存余额总账列表(按组织机构)", notes = "库存余额总账列表(按组织机构) | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/ledgers/etps")
    public Result<Page<List<StockLedgerMonthVO>>> getLedgersByEtps(HttpServletRequest request, @RequestBody BasePageReqParam requestVO){
        Result<Page<List<StockLedgerMonthVO>>> result = new Result<>();

        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        if (requestVO.getStartDate() == null || requestVO.getEndDate() == null) {
            throw new BusinessException("请选择起始时间");
        }


        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,ledgerService.getLedgersByEtps(loginUser,requestVO));


        return result;
    }

    @ApiOperation(value = "库存总账列表导出(按组织机构)", notes = "库存总账列表导出(按组织机构) | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/ledgers/etps/export")
    public void exportByEtps(HttpServletRequest request,HttpServletResponse response,BasePageReqParam requestVO) throws IOException {

        if (requestVO.getStartDate() == null || requestVO.getEndDate() == null) {
            throw new BusinessException("请选择起始时间");
        }

        UserVO userVO =  (UserVO)request.getSession().getAttribute("user");
        String name = "库存总账-按组织机构";
        name += ".xlsx";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name,"UTF-8") );
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        ledgerService.exportByEtps(output,requestVO,userVO);
        output.close();
        output.flush();

    }


    @ApiOperation(value = "库存余额总账列表(按商品)", notes = "库存余额总账列表(按商品) | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/ledgers/goods")
    public Result<Page<List<StockLedgerEnterpriseByGoodsVO>>> getLedgersByGoods(HttpServletRequest request, @RequestBody StockInOutReqVO requestVO){
        Result<Page<List<StockLedgerEnterpriseByGoodsVO>>> result = new Result<>();


        if (requestVO.getPageNo() <= 0 || requestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        if (requestVO.getStartDate() == null || requestVO.getEndDate() == null) {
            throw new BusinessException("请选择起始时间");
        }

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,ledgerService.getLedgersByGoods(loginUser,requestVO));

        return result;
    }

    @ApiOperation(value = "库存总账列表导出(按商品)", notes = "库存总账列表导出(按商品) | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/ledgers/goods/export")
    public void exportByGoods(HttpServletRequest request, HttpServletResponse response, StockInOutReqVO requestVO) throws IOException {



        if (requestVO.getStartDate() == null || requestVO.getEndDate() == null) {
            throw new BusinessException("请选择起始时间");
        }

        UserVO userVO =  (UserVO)request.getSession().getAttribute("user");
        String name = "库存总账-按商品";
        name += ".xlsx";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name,"UTF-8") );
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        ledgerService.exportByGoods(output,requestVO,userVO);
        output.close();
        output.flush();

    }

}
