package com.rograndec.feijiayun.chain.business.report.finance.stock.controller;


import com.rograndec.feijiayun.chain.business.report.finance.stock.service.StockBalanceService;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceTotalVO;
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
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 *
 * @ClassName: StockController
 * @Description:
 * @author dongyang.du
 * @version 1.0
 * @date 2018-01-08 11:21:24
 */
@Api(value = "report_finance_stock_blance",description = "财务管理-存货管理-库存余额")
@RestController
@RequestMapping("/report/finance/stock")
public class StockBalanceController {


    @Autowired
    private StockBalanceService balanceService;

    @ApiOperation(value = "库存余额列表", notes = "库存余额列表 | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/balances")
    public Result<Page<StockBalanceTotalVO>> getBalances(HttpServletRequest request, @RequestBody  StockBalanceReqVO balanceReqVO){

        Result<Page<StockBalanceTotalVO>> result = new Result<>();

        if (balanceReqVO.getPageNo() <= 0 || balanceReqVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        if(balanceReqVO.getOrder() != null){
            if(!balanceReqVO.getOrder().equals("enterpriseCode")
                    && !balanceReqVO.getOrder().equals("goodsCode")){
                throw  new BusinessException("排序 方式不正确");
            }
        }
        if(balanceReqVO.getSort() != null){
            if(!balanceReqVO.getSort().equals("asc") &&
                    !balanceReqVO.getSort().equals("desc")){
                throw  new BusinessException("排序 方式不正确");
            }
        }

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        Page<StockBalanceTotalVO> page = balanceService.getBalances(loginUser, balanceReqVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);

        return result;
    }

    @ApiOperation(value = "库存余额列表导出", notes = "库存余额列表导出 | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/balances/export",method = RequestMethod.POST)
    public void export(HttpServletRequest request,HttpServletResponse response,StockBalanceReqVO balanceReqVO) throws Exception {
        UserVO userVO =  (UserVO)request.getSession().getAttribute("user");
        String name = "库存余额";
        name += ".xlsx";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name,"UTF-8") );
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        balanceService.export(output,balanceReqVO,userVO);
        output.close();
        output.flush();

    }


    @ApiOperation(value = "库存余额列表打印", notes = "库存余额列表打印 | 开发者 dongyang.du | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/balances/print")
    public Result<StockBalanceTotalVO> print(HttpServletRequest request,@RequestBody  StockBalanceReqVO balanceReqVO) throws Exception {
        Result<StockBalanceTotalVO> result = new Result<>();
        UserVO userVO =  (UserVO)request.getSession().getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,balanceService.print(balanceReqVO,userVO));
        return result;
    }

}
