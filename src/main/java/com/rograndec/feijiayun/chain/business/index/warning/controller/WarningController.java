package com.rograndec.feijiayun.chain.business.index.warning.controller;

import com.rograndec.feijiayun.chain.business.index.warning.service.WarningService;
import com.rograndec.feijiayun.chain.business.index.warning.vo.EnterpriseWarnReportVO;
import com.rograndec.feijiayun.chain.business.index.warning.vo.GoodsWarnReportVO;
import com.rograndec.feijiayun.chain.business.index.warning.vo.SupplierWarnReportVO;
import com.rograndec.feijiayun.chain.business.index.warning.vo.WarningIndexVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.service.OrgQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgWarnReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.GoodsLicenseWarnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.SupplierQualificationReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.service.UserReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserWarningExcelVO;
import com.rograndec.feijiayun.chain.business.report.storage.service.StockWarnReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Api(value = "warning_report",description = "首页-预警")
@RestController
@RequestMapping("index/warning")
@Validated
public class WarningController {

    private static final Log logger = LogFactory.getLog(WarningController.class);

    @Autowired
    private OrgQualificationReportService orgWarnReportService;

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private SupplierQualificationReportService supplierQualificationReportService;

    @Autowired
    private StockWarnReportService stockWarnReportService;

    @Autowired
    private GoodsLicenseWarnReportService goodsLicenseWarnReportService;

    @Autowired
    private WarningService warningService;


    @ApiOperation(value = "按条件搜索员工资质预警", notes = "按条件搜索员工资质预警信息 | 开发者 苏磊 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getUserWarningPage", method = RequestMethod.GET)
    public Result<List<UserWarningExcelVO>> getUserWarningPage(HttpServletRequest request,
                                                               @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort,
                                                               @ApiIgnore UserVO userVO
    ) {
        Result<List<UserWarningExcelVO>> result = new Result<>();
        try {
            String order  = "code";

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> userWarnSets = warnSetTypeQualificationIdMap.get(1);

            List<UserWarningExcelVO> userWarningPages = userReportService.getUserWarning2WarinSet(userVO,userWarnSets);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, userWarningPages);
        } catch (Exception e) {
            logger.error("按条件搜索员工资质预警page信息:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "员工预警报表导出", notes = "员工预警报表导出 | 开发者 苏磊 | 已完成")
    @RequestMapping(value="/exportUserWarning",method= RequestMethod.GET)
    public void exportUserWarning(HttpServletResponse response, HttpServletRequest request,  @ApiIgnore UserVO userVO,
                                  @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "员工资质预警";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            String order  = "code";
            List<UserWarningExcelVO> userWarningExcelVO = userReportService.getUserWarningExcelForm(null, null, null, null, null, null, null, order, sort, userVO);
            userReportService.exportWarning(output,userWarningExcelVO,userVO);
        }catch(Exception e){
            logger.error("员工预警报表导出:"+e.getMessage(),e);
        }

    }

    @ApiOperation(value = "组织机构与质量管理职责--组织机构资质预警查询", notes = "组织机构与质量管理职责--组织机构资质预警查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getEnterpriseOrgQualificationReport",method= RequestMethod.GET)
    public Result<List<EnterpriseWarnReportVO>> getEnterpriseOrgQualificationReport(
            @ApiIgnore UserVO userVO,
            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort) throws ParseException {

        RequestParamOrgWarnReportVO requestParamOrgWarnReportVO = new RequestParamOrgWarnReportVO();

        requestParamOrgWarnReportVO.setChainTypeOrder(null);
        requestParamOrgWarnReportVO.setGroupCodeOrder(null);
        if("desc".equals(sort)){
            requestParamOrgWarnReportVO.setEnterpriseCodeOrder(0);
        }
        Result<List<EnterpriseWarnReportVO>> result = new Result<>();

        /**
         * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
         */
        Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


        /**
         * 企业资质预警统计条目
         */
        List<WarnSet> enterpriseWarnSets = warnSetTypeQualificationIdMap.get(0);

        List<EnterpriseWarnReportVO> enterpriseQualification2WarnSet = orgWarnReportService.getEnterpriseQualification2WarnSet(userVO, enterpriseWarnSets);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,enterpriseQualification2WarnSet);
        return result;
    }


    @ApiOperation(value = "导出Excel", notes = "导出组织机构资质预警Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/enterpriseOrgQualificationExportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, @ApiIgnore UserVO userVO,
                            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort
    ) throws Exception {
        String file = "组织机构资质预警";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        RequestParamOrgWarnReportVO paramForListVO = new RequestParamOrgWarnReportVO();
        paramForListVO.setChainTypeOrder(null);
        paramForListVO.setEnterpriseCodeOrder(1);
        paramForListVO.setGroupId(null);
        paramForListVO.setParam(null);
        if("desc".equals(sort)){
            paramForListVO.setEnterpriseCodeOrder(0);
        }
        orgWarnReportService.exportWarn(output,userVO,paramForListVO);

        output.close();
        output.flush();
    }




    @ApiOperation(value = "报表-质量管理-采购-供货单位资质预警查询", notes = "报表-质量管理-采购-供货单位资质查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/supplierQualificationReport",method= RequestMethod.GET)
    public Result<List<SupplierWarnReportVO>> supplierQualificationReport(@ApiIgnore UserVO userVO,
                                                                          @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort
    ) throws ParseException {
        Result<List<SupplierWarnReportVO>> result = new Result<>();
        RequestParamSupplierReportVO requestParamSupplierReportVO = new RequestParamSupplierReportVO();
        requestParamSupplierReportVO.setNatureOrder(null);

        if("desc".equals(sort)){
            requestParamSupplierReportVO.setSupplierCodeOrder(0);
        }

        /**
         * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
         */
        Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


        /**
         * 企业资质预警统计条目
         */
        List<WarnSet> supplierWarnSets = warnSetTypeQualificationIdMap.get(2);

        List<SupplierQualificationReportVO> supplierQualificationList = supplierQualificationReportService.getSupplierQualificationReport2WarinSet(userVO, supplierWarnSets);

        List<SupplierWarnReportVO> enterpriseWarnReportVOs = SupplierWarnReportVO.getEnterpriseWarnReportVOs(supplierQualificationList);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,enterpriseWarnReportVOs);
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "导出供货单位资质预警Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/supplierQualificationExportExcel",method= RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, HttpSession session,
                            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort
    ) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "供货单位资质预警";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        RequestParamSupplierReportVO paramForListVO = new RequestParamSupplierReportVO();
        paramForListVO.setSupplierCodeOrder(1);
        paramForListVO.setNatureOrder(null);
        paramForListVO.setParam(null);
        if("desc".equals(sort)){
            paramForListVO.setSupplierCodeOrder(0);
        }
        supplierQualificationReportService.exportWarn(output,userVO,paramForListVO);

        output.close();
        output.flush();
    }


    @ApiOperation(value = "获取品种资质预警列表", notes = "获取品种资质预警列表 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoodsLicenseWarn", method = RequestMethod.GET)
    @ResponseBody
    public  Result<List<GoodsWarnReportVO>> getGoodsLicenseWarn(HttpServletRequest request,
                            @ApiIgnore UserVO userVO,
                             @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort
    ){
        Result<List<GoodsWarnReportVO>> result = new Result<>();
        try {

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> supplierWarnSets = warnSetTypeQualificationIdMap.get(3);
            List<GoodsLicenseWarnVO> goodsLicenseWarnNotPageNotPage = goodsLicenseWarnReportService.getGoodsLicenseWarnNot2WarnSet(userVO, supplierWarnSets);
            List<GoodsWarnReportVO> goodsWarnReportVOs = GoodsWarnReportVO.getGoodsWarnReportVOs(goodsLicenseWarnNotPageNotPage);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsWarnReportVOs);
        } catch (Exception e) {
            logger.error("获取品种资质列表错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将品种资质预警信息导出至Excel | 开发者:马东 | 已联调")
    @RequestMapping(value="/exportExcel", method=RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ApiIgnore UserVO userVO,
                            @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            String name = "品种资质预警";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            goodsLicenseWarnReportService.exportExcel(output, userVO, null, "goodsCode", sort, null);
        } catch (Exception e) {
            logger.error("导出品种资质预警错误:" + e.getMessage(), e);
        }
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询过期商品列表", notes = "查询过期商品列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryExpireGoodsList", method=RequestMethod.GET)
    public Result<List<StockWarnExpireGoodsVO>> queryExpireGoodsList(HttpServletRequest request
            ,@ApiIgnore UserVO userVO, @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort
    ){
        Result<List<StockWarnExpireGoodsVO>> result = new Result<>();
        try{

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

            WarnSet exprieGoodsWarnSet = null;
            for(WarnSet warnSet : exprieGoodsWarnSets){
                if("过期商品".equals(warnSet.getContent())){
                    exprieGoodsWarnSet = warnSet;
                }

            }


            StockWarnExpireGoodsTotalVO stockWarnExpireGoodsTotalVO = stockWarnReportService.selectExpireGoodsList2WarnSet(exprieGoodsWarnSet, userVO);
            List<StockWarnExpireGoodsVO> voList = stockWarnExpireGoodsTotalVO.getVoList();

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        }catch(Exception e){
            logger.error("查询过期商品列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询近效期商品列表", notes = "查询近效期商品列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryNearPeriodGoodsList", method=RequestMethod.GET)
    public Result<List<StockWarnNearPeriodGoodsVO>> queryNearPeriodGoodsList(HttpServletRequest request
            ,@ApiIgnore UserVO userVO, @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort){
        Result<List<StockWarnNearPeriodGoodsVO>> result = new Result<>();
        try{

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

            WarnSet nearPeriodGoodsWarnSet = null;
            for(WarnSet warnSet : exprieGoodsWarnSets){
                if("近效期商品".equals(warnSet.getContent())){
                    nearPeriodGoodsWarnSet = warnSet;
                }

            }

            StockWarnNearPeriodGoodsTotalVO stockWarnNearPeriodGoodsTotalVO = stockWarnReportService.selectNearPeriodGoodsList2WarnSet(nearPeriodGoodsWarnSet, userVO);

            List<StockWarnNearPeriodGoodsVO> voList = stockWarnNearPeriodGoodsTotalVO.getVoList();

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        }catch(Exception e){
            logger.error("查询近效期商品列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询缺货商品列表", notes = "查询缺货商品列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryLackGoodsList", method=RequestMethod.GET)
    public Result<List<StockWarnLackGoodsVO>> queryLackGoodsList(HttpServletRequest request,
                                                                 @ApiIgnore UserVO userVO, @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort){
        Result<List<StockWarnLackGoodsVO>> result = new Result<>();
        try{
            StockListQueryVO stockListQueryVO = new StockListQueryVO();
            stockListQueryVO.setOrder("code");
            stockListQueryVO.setSort(sort);

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

            WarnSet lackGoodsWarnSet = null;
            for(WarnSet warnSet : exprieGoodsWarnSets){
                if("缺货商品".equals(warnSet.getContent())){
                    lackGoodsWarnSet = warnSet;
                }

            }
            List<StockWarnLackGoodsVO> voList = new ArrayList<>();
            if(null != lackGoodsWarnSet){
                StockWarnLackGoodsTotalVO stockWarnLackGoodsTotalVO = stockWarnReportService.selectLackGoodsListNotPage(stockListQueryVO, userVO);

                voList = stockWarnLackGoodsTotalVO.getVoList();
            }


            result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        }catch(Exception e){
            logger.error("查询缺货商品列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询积货商品列表", notes = "查询积货商品列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryStoreGoodsList", method=RequestMethod.GET)
    public Result<List<StockWarnStoreGoodsVO>> queryStoreGoodsList(HttpServletRequest request,
                                                                   @ApiIgnore UserVO userVO, @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort){
        Result<List<StockWarnStoreGoodsVO>> result = new Result<>();
        try{
            StockListQueryVO vo = new StockListQueryVO();
            vo.setOrder("code");
            vo.setSort(sort);

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

            WarnSet lackGoodsWarnSet = null;
            for(WarnSet warnSet : exprieGoodsWarnSets){
                if("积货商品".equals(warnSet.getContent())){
                    lackGoodsWarnSet = warnSet;
                }

            }

            List<StockWarnStoreGoodsVO> voList = new ArrayList<>();
            if(null != lackGoodsWarnSet){
                StockWarnStoreGoodsTotalVO stockWarnStoreGoodsTotalVO = stockWarnReportService.selectStoreGoodsListNotPage(vo, userVO);

                voList = stockWarnStoreGoodsTotalVO.getVoList();
            }


            result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        }catch(Exception e){
            logger.error("查询积货商品列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询滞销商品列表", notes = "查询滞销商品列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryLagSaleGoodsList", method=RequestMethod.GET)
    public Result<List<StockWarnLagSaleGoodsVO>> queryLagSaleGoodsList(HttpServletRequest request,
                                                                       @ApiIgnore UserVO userVO, @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort){
        Result<List<StockWarnLagSaleGoodsVO>> result = new Result<>();
        try{

            StockListQueryVO vo = new StockListQueryVO();
            vo.setOrder("code");
            vo.setSort(sort);


            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

            WarnSet lackGoodsWarnSet = null;
            for(WarnSet warnSet : exprieGoodsWarnSets){
                if("滞销商品".equals(warnSet.getContent())){
                    lackGoodsWarnSet = warnSet;
                }

            }

            StockWarnLagSaleGoodsTotalVO stockWarnLagSaleGoodsTotalVO = stockWarnReportService.selectLagSaleGoodsList2WarnSet(lackGoodsWarnSet, userVO);

            List<StockWarnLagSaleGoodsVO> voList = stockWarnLagSaleGoodsTotalVO.getVoList();

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        }catch(Exception e){
            logger.error("查询滞销商品列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询药品养护列表", notes = "查询药品养护列表 | 开发者 刘群 | 已完成")
    @RequestMapping(value="/queryGoodsMaintanceList", method=RequestMethod.GET)
    public Result<List<StockWarnGoodsMaintanceVO>> queryGoodsMaintanceList(HttpServletRequest request,
                                                                           @ApiIgnore UserVO userVO, @ApiParam(value = "排序方式ASC/DESC",name = "sort") @RequestParam(required = false) String sort){
        Result<List<StockWarnGoodsMaintanceVO>> result = new Result<>();
        try{

            StockListQueryVO vo = new StockListQueryVO();
            vo.setOrder("code");
            vo.setSort(sort);

            /**
             * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
             */
            Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


            /**
             * 企业资质预警统计条目
             */
            List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

            WarnSet lackGoodsWarnSet = null;
            for(WarnSet warnSet : exprieGoodsWarnSets){
                if("药品养护".equals(warnSet.getContent())){
                    lackGoodsWarnSet = warnSet;
                }

            }
            List<StockWarnGoodsMaintanceVO> stockWarnGoodsMaintanceVOS = stockWarnReportService.selectGoodsMaintanceList2WarnSet(lackGoodsWarnSet, userVO);

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, stockWarnGoodsMaintanceVOS);
        }catch(Exception e){
            logger.error("查询滞销商品列表错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }




    @ApiOperation(value = "预警首页统计条数", notes = "预警首页统计条数 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/indexWarningCount",method= RequestMethod.GET)
    public Result<List<WarningIndexVO>> indexWarningCount(@ApiIgnore UserVO userVO, @ApiParam(value = "显示多少条",name = "limit") @RequestParam(required = false) Integer limit) throws Exception {

        Result<List<WarningIndexVO>> result = new Result<>();


        int threadPoolSize = 8;
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode()) || userVO.getChainType().equals(ChainType.Division.getCode())){
            threadPoolSize = 10;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        /**
         * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）
         */
        Map<Integer, List<WarnSet>> warnSetTypeQualificationIdMap = warningService.getWarnSetTypeQualificationIdMaping(userVO);


        List<EnterpriseWarnReportVO> enterpriseQualificationWarnReportList = new ArrayList<>();
        /**
         * 企业资质预警统计条目
         */
        Runnable syncRunnable = new Runnable() {
            @Override
            public void run() {
                List<WarnSet> enterpriseWarnSets = warnSetTypeQualificationIdMap.get(0);

                try {
                    List<EnterpriseWarnReportVO> enterpriseQualificationWarnRepors = orgWarnReportService.getEnterpriseQualification2WarnSet(userVO, enterpriseWarnSets);
                    if(!CollectionUtils.isEmpty(enterpriseQualificationWarnRepors)){
                        enterpriseQualificationWarnReportList.addAll(enterpriseQualificationWarnRepors);
                    }

                } catch (ParseException e) {
                    logger.error("企业资质预警统计条目报错",e);
                }
            }
        };
        executorService.execute(syncRunnable);


        List<UserWarningExcelVO> userWarningPages = new ArrayList<>();
        /**
         * 员工预警统计条目
         */
        Runnable userSyncRunnable = new Runnable() {
            @Override
            public void run() {
                List<WarnSet> userWarnSets = warnSetTypeQualificationIdMap.get(1);

                List<UserWarningExcelVO> userWarningPageList = userReportService.getUserWarning2WarinSet(userVO, userWarnSets);
                if(!CollectionUtils.isEmpty(userWarningPageList)) {
                    userWarningPages.addAll(userWarningPageList);
                }
            }
        };
        executorService.execute(userSyncRunnable);


        List<GoodsLicenseWarnVO> goodsLicenseWarnNotPageNotPage = new ArrayList<>();
        List<SupplierQualificationReportVO> supplierQualificationList = new ArrayList<>();
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode()) || userVO.getChainType().equals(ChainType.Division.getCode())){
            /**
             * 商品品种资质预警统计条目
             */
            Runnable goodsetsSyncRunnable = new Runnable() {
                @Override
                public void run() {
                    List<WarnSet> supplierWarnSets = warnSetTypeQualificationIdMap.get(3);
                    try {
                        List<GoodsLicenseWarnVO> goodsLicenseWarnNotPageNotPages = goodsLicenseWarnReportService.getGoodsLicenseWarnNot2WarnSet(userVO, supplierWarnSets);
                        if(!CollectionUtils.isEmpty(goodsLicenseWarnNotPageNotPages)) {
                            goodsLicenseWarnNotPageNotPage.addAll(goodsLicenseWarnNotPageNotPages);
                        }
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            };
            executorService.execute(goodsetsSyncRunnable);

            /**
             * 供货单位预警统计条目
             */
            Runnable supplierWarnSetsSyncRunnable = new Runnable() {
                @Override
                public void run() {
                    List<WarnSet> supplierWarnSets = warnSetTypeQualificationIdMap.get(2);
                    try {
                        List<SupplierQualificationReportVO> supplierQualifications = supplierQualificationReportService.getSupplierQualificationReport2WarinSet(userVO, supplierWarnSets);
                        if(!CollectionUtils.isEmpty(supplierQualifications)) {
                            supplierQualificationList.addAll(supplierQualifications);
                        }
                    } catch (ParseException e) {
                        logger.error(e);
                    }
                }
            };
            executorService.execute(supplierWarnSetsSyncRunnable);


        }

        StockListQueryVO stockListQueryVO = new StockListQueryVO();
        /**
         * 库存过期商品预警统计条目
         */
        StockWarnExpireGoodsTotalVO stockWarnExpireGoodsTotalVO = new StockWarnExpireGoodsTotalVO();
        Runnable exprieGoodsSyncRunnable = new Runnable() {
            @Override
            public void run() {

                List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);

                WarnSet exprieGoodsWarnSet = null;
                if (!CollectionUtils.isEmpty(exprieGoodsWarnSets)) {
                    for (WarnSet warnSet : exprieGoodsWarnSets) {
                        if ("过期商品".equals(warnSet.getContent())) {
                            exprieGoodsWarnSet = warnSet;
                        }

                    }
                }

                StockWarnExpireGoodsTotalVO stockWarnExpireGoodsTotal = stockWarnReportService.selectExpireGoodsList2WarnSet(exprieGoodsWarnSet, userVO);
                if(null != stockWarnExpireGoodsTotal) {
                    stockWarnExpireGoodsTotalVO.setVoList(stockWarnExpireGoodsTotal.getVoList());
                }
            }
        };
        executorService.execute(exprieGoodsSyncRunnable);


        StockWarnNearPeriodGoodsTotalVO stockWarnNearPeriodGoodsTotalVO = new StockWarnNearPeriodGoodsTotalVO();
        /**
         * 库存近效期商品预警统计条目
         */
        Runnable nearPeriodSyncRunnable = new Runnable() {
            @Override
            public void run() {
                List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);
                WarnSet nearPeriodGoodsWarnSet = null;
                if (!CollectionUtils.isEmpty(exprieGoodsWarnSets)) {
                    for (WarnSet warnSet : exprieGoodsWarnSets) {
                        if ("近效期商品".equals(warnSet.getContent())) {
                            nearPeriodGoodsWarnSet = warnSet;
                        }

                    }
                }

                StockWarnNearPeriodGoodsTotalVO stockWarnNearPeriodGoodsTotal = stockWarnReportService.selectNearPeriodGoodsList2WarnSet(nearPeriodGoodsWarnSet, userVO);
                if(null != stockWarnNearPeriodGoodsTotal) {
                    stockWarnNearPeriodGoodsTotalVO.setVoList(stockWarnNearPeriodGoodsTotal.getVoList());
                }
            }
        };

        executorService.execute(nearPeriodSyncRunnable);

        /**
         * 库存积货预警统计条目
         */
        StockWarnStoreGoodsTotalVO stockWarnStoreGoodsTotalVO = new StockWarnStoreGoodsTotalVO();
        Runnable storeGoodsSyncRunnable = new Runnable() {
            @Override
            public void run() {

                List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);
                WarnSet lackGoodsWarnSet = null;
                if (!CollectionUtils.isEmpty(exprieGoodsWarnSets)) {
                    for (WarnSet warnSet : exprieGoodsWarnSets) {
                        if ("积货商品".equals(warnSet.getContent())) {
                            lackGoodsWarnSet = warnSet;
                        }

                    }
                }
                if (null != lackGoodsWarnSet) {
                    StockWarnStoreGoodsTotalVO stockWarnStoreGoodsTotal = stockWarnReportService.selectStoreGoodsListNotPage(stockListQueryVO, userVO);
                    if(null != stockWarnStoreGoodsTotal) {
                        stockWarnStoreGoodsTotalVO.setVoList(stockWarnStoreGoodsTotal.getVoList());
                    }
                }
            }
        };
        executorService.execute(storeGoodsSyncRunnable);


        StockWarnLackGoodsTotalVO stockWarnLackGoodsTotalVO = new StockWarnLackGoodsTotalVO();
        /**
         * 库存缺货预警统计条目
         */
        Runnable lackGoodsSyncRunnable = new Runnable() {
            @Override
            public void run() {

                List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);
                WarnSet lackGoodsWarnSet = null;
                if (!CollectionUtils.isEmpty(exprieGoodsWarnSets)) {
                    for (WarnSet warnSet : exprieGoodsWarnSets) {
                        if ("缺货商品".equals(warnSet.getContent())) {
                            lackGoodsWarnSet = warnSet;
                        }

                    }
                }
                if (null != lackGoodsWarnSet) {
                    StockWarnLackGoodsTotalVO stockWarnLackGoodsTotal = stockWarnReportService.selectLackGoodsListNotPage(stockListQueryVO, userVO);
                    if(null != stockWarnLackGoodsTotal) {
                        stockWarnLackGoodsTotalVO.setVoList(stockWarnLackGoodsTotal.getVoList());
                    }
                }

            }
        };
        executorService.execute(lackGoodsSyncRunnable);

        StockWarnLagSaleGoodsTotalVO stockWarnLagSaleGoodsTotalVO = new StockWarnLagSaleGoodsTotalVO();
        /**
         * 库存滞销预警统计条目
         */

        Runnable lagSaleGoodsSyncRunnable = new Runnable() {
            @Override
            public void run() {
                WarnSet lackGoodsWarnSet = null;
                List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);
                if (!CollectionUtils.isEmpty(exprieGoodsWarnSets)) {
                    for (WarnSet warnSet : exprieGoodsWarnSets) {
                        if ("滞销商品".equals(warnSet.getContent())) {
                            lackGoodsWarnSet = warnSet;
                        }

                    }
                }

                StockWarnLagSaleGoodsTotalVO stockWarnLagSaleGoodsTotal = stockWarnReportService.selectLagSaleGoodsList2WarnSet(lackGoodsWarnSet, userVO);
                if(null != stockWarnLagSaleGoodsTotal) {
                    stockWarnLagSaleGoodsTotalVO.setVoList(stockWarnLagSaleGoodsTotal.getVoList());
                }

            }
        };

        executorService.execute(lagSaleGoodsSyncRunnable);

        List<StockWarnGoodsMaintanceVO> stockWarnGoodsMaintanceVOS = new ArrayList<>();
        /**
         * 库存养护预警统计条目
         */
        Runnable stockWarnGoodsMaintanceSyncRunnable = new Runnable() {
            @Override
            public void run() {
                List<WarnSet> exprieGoodsWarnSets = warnSetTypeQualificationIdMap.get(4);
                WarnSet lackGoodsWarnSet = null;
                if (!CollectionUtils.isEmpty(exprieGoodsWarnSets)) {
                    for (WarnSet warnSet : exprieGoodsWarnSets) {
                        if ("药品养护".equals(warnSet.getContent())) {
                            lackGoodsWarnSet = warnSet;
                        }

                    }
                }
                List<StockWarnGoodsMaintanceVO> stockWarnGoodsMaintances = stockWarnReportService.selectGoodsMaintanceList2WarnSet(lackGoodsWarnSet, userVO);
                if(!CollectionUtils.isEmpty(stockWarnGoodsMaintances)) {
                    stockWarnGoodsMaintanceVOS.addAll(stockWarnGoodsMaintances);
                }
            }
        };
        executorService.execute(stockWarnGoodsMaintanceSyncRunnable);

        // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
        executorService.shutdown();
        // awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔1秒循环一次
        while (!executorService.awaitTermination(1, TimeUnit.SECONDS));


        List<WarningIndexVO> warningIndexVOs = warningService.getWarningIndexVOs(
                enterpriseQualificationWarnReportList
                , supplierQualificationList
                , userWarningPages
                , goodsLicenseWarnNotPageNotPage
                , stockWarnExpireGoodsTotalVO
                , stockWarnNearPeriodGoodsTotalVO
                , stockWarnStoreGoodsTotalVO
                , stockWarnLackGoodsTotalVO
                , stockWarnLagSaleGoodsTotalVO
                , stockWarnGoodsMaintanceVOS
        );

        if(null != limit){
            warningIndexVOs = warningIndexVOs.stream().limit(limit).collect(Collectors.toList());
        }

        /**
         * 过滤掉条数为0的预警信息
         */
        List<WarningIndexVO> returnWarningIndexVOList = new ArrayList<>();
        for(WarningIndexVO warningIndexVO:warningIndexVOs){
            if(Integer.valueOf(warningIndexVO.getWarnInfoCount())>0){
                returnWarningIndexVOList.add(warningIndexVO);
            }
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,returnWarningIndexVOList);
        return result;
    }



}
