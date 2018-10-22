package com.rograndec.feijiayun.chain.business.report.storage.controller;

import com.rograndec.feijiayun.chain.business.report.storage.service.StockOccupyService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseAndChildrenVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_storage_stockOccupy",description = "报表-储存管理-库存占用")
@RestController
@RequestMapping("report/storage/stockOccupy")
@Validated
public class StockOccupyReportController {

    private static final Log logger = LogFactory.getLog(OnWayStockReportController.class);

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private StockOccupyService stockOccupyService;

    @ApiOperation(value="根据登录用户的企业id查询该企业底下所有子公司以及本身数据 | 开发者 翟伟 | 已联调", notes = "根据登录用户的企业id查询该企业底下所有子公司以及本身数据 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/children/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<EnterpriseAndChildrenVO>> getEnterpriseChildren(@ApiIgnore UserVO userVO){
        Result<List<EnterpriseAndChildrenVO>> result = new Result<List<EnterpriseAndChildrenVO>>();

        List<EnterpriseAndChildrenVO> enterpriseVOS = new ArrayList<>();
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            List<Enterprise> enterprises = enterpriseService.getChildrens(userVO.getEnterpriseId());

            for(Enterprise enterprise : enterprises){
                EnterpriseAndChildrenVO enterpriseVO = EnterpriseAndChildrenVO.getEnterprise4VO(enterprise);
                enterpriseVOS.add(enterpriseVO);
            }
        }else {
            Enterprise enterprise = enterpriseService.queryEnterpriseById4StatusEnable(userVO.getEnterpriseId(),"enterprise");
            EnterpriseAndChildrenVO enterpriseVO = EnterpriseAndChildrenVO.getEnterprise4VO(enterprise);
            enterpriseVOS.add(enterpriseVO);
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseVOS);
        return result;
    }

    @ApiOperation(value = "查询库存占用列表", notes = "查询库存占用列表 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/queryOnWayStockList", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<StockOccupyVO> queryStockOccupyList(HttpServletRequest request,
                                                    @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                    @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                    @ApiParam(value = "快速搜索的条件（商品编码/条形码/商品名称/通用名称/批准文号）", required = false) @RequestParam(required=false) String param,
                                                    @ApiParam(value = "企业ID(只有在总部的时候)", required = false) @RequestParam(required=false) Long enterpriseId,
                                                    @ApiParam(value = "按某一列排序(enterpriseCode或者goodsCode)", required = false) @RequestParam(required=false) String order,
                                                    @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort, @ApiIgnore UserVO userVO){
        Result<StockOccupyVO> result = new Result<StockOccupyVO>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            StockOccupyVO vo = stockOccupyService.queryStockOccupyList(page,param,enterpriseId,order,sort,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
            logger.error("按条件搜索在库存占用信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将库存占用列表导出至Excel | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, HttpServletRequest request,
                            @ApiParam(value = "快速搜索的条件（商品编码/条形码/商品名称/通用名称/批准文号）", required = false) @RequestParam(required=false) String param,
                            @ApiParam(value = "企业ID(只有在总部的时候)", required = false) @RequestParam(required=false) Long enterpriseId,
                            @ApiParam(value = "按某一列排序(enterpriseCode或者goodsCode)", required = false) @RequestParam(required=false) String order,
                            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort, @ApiIgnore UserVO userVO) throws Exception{

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "库存占用";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            List<StockOccupyExcelPageVO> vo = stockOccupyService.getExcel(param,enterpriseId,order,sort,userVO);
            stockOccupyService.export(output,vo,userVO);

        }catch(Exception e){
            logger.error("导出库存占用信息错误:"+e.getMessage(),e);
        }

    }
	
}
