package com.rograndec.feijiayun.chain.business.goods.price.controller;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.service.PriceOrderService;
import com.rograndec.feijiayun.chain.business.goods.price.valid.PriceOrderAddParamCheck;
import com.rograndec.feijiayun.chain.business.goods.price.valid.PriceOrderDeleteCheck;
import com.rograndec.feijiayun.chain.business.goods.price.valid.PriceOrderUpdateParamCheck;
import com.rograndec.feijiayun.chain.business.goods.price.vo.*;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetTaxService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/6.
 */
@Api(description = "价格清单接口")
@RestController
@RequestMapping("price/priceOrder")
@Validated
@Scope(value = "prototype")
public class PriceOrderController {


    @Autowired
    private SetTaxService setTaxService;

    @Autowired
    private PriceOrderService priceOrderService;

    @ApiOperation(value="初始化字段必填和非必填信息 | 开发者 翟伟 | 已完成", notes = "初始化字段必填和非必填信息 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public Result<PriceOrderInitVO> init(@ApiIgnore UserVO userVO){
        Result<PriceOrderInitVO> result = new Result<>();

        PriceOrderInitVO priceOrderInitVO = priceOrderService.init();

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(priceOrderInitVO);
        return result;
    }

    @ApiOperation(value="根据登录用户查询税率信息 | 开发者 翟伟 | 已完成", notes = "根据登录用户查询税率信息 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/taxRate/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<GoodsTaxRateReturnVO>> queryTax4User(@ApiIgnore UserVO userVO) throws Exception {
        Result<List<GoodsTaxRateReturnVO>> result = new Result<>();

        List<GoodsTaxRate> goodsTaxRates = setTaxService.getSetTax(userVO.getEnterpriseId());

        List<GoodsTaxRateReturnVO> rateReturnVOS = GoodsTaxRateReturnVO.getGoodsTaxRateReturnVOs4GoodsTaxRates(goodsTaxRates);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(rateReturnVOS);
        return result;
    }

    @ApiOperation(value="查询价格清单列表 | 开发者 翟伟 | 已完成", notes = "查询价格清单列表 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/priceOrders/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<PriceOrderReturnVO> >> queryPriceOrder(
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize,
            @RequestParam(required = false)
            @ApiParam(name = "queryType", value = "查询类型 2 是全部 0:自定义 1:系统默认", required = false)
                    Integer queryType, @ApiIgnore UserVO userVO,
            @ApiParam(value = "按某一列排序(sys_type或者code)", required = false) @RequestParam(required = false) String order,
            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort
    ) throws Exception {
        Result<Page<List<PriceOrderReturnVO> >> result = new Result<>();

        Page page = new Page(pageNo, pageSize);
        List<PriceOrderReturnVO> priceOrderReturnVOS = priceOrderService.queryPriceOrders(userVO, queryType, page,order,sort);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(page);
        return result;
    }

    @ApiOperation(value="查询价格清单明细 | 开发者 翟伟 | 已完成", notes = "查询价格清单明细 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "priceOrderId", value = "价格清单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/{priceOrderId}/get/{pageNo}/{pageSize}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<PriceOrderReturnVO>> queryPriceOrderDetail(
            @PathVariable Long priceOrderId,@PathVariable Integer pageNo,@PathVariable Integer pageSize, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result<Page<PriceOrderReturnVO>> result = new Result<>();
        if(pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0){
            result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<PriceOrderReturnVO> page = new Page<PriceOrderReturnVO>(pageNo,pageSize);
        priceOrderService.queryPriceOrderDetails(priceOrderId, userVO,page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(page);
        return result;
    }

    @ApiOperation(value="查询定位价格清单明细 | 开发者 翟伟 | 已完成", notes = "查询定位价格清单明细 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/positioning", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<PriceOrderDetailReturnVO>>> queryPriceOrderDetail(
            @RequestParam(required = false)
            @ApiParam(name = "param", value = "检索字段", required = false)
                    String param,
            @RequestParam(required = false)
            @ApiParam(name = "priceOrderId", value = "价格清单id", required = false)
                    Long priceOrderId,
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = true)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = true)
                    Integer pageSize,
            @ApiIgnore UserVO userVO
    ) throws Exception {
        Result<Page<List<PriceOrderDetailReturnVO>>> result = new Result<>();

        Page<List<PriceOrderDetailReturnVO>> page = new Page(pageNo,pageSize);
        priceOrderService.queryPriceOrderDetails4Param(priceOrderId,param,userVO,page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(page);
        return result;
    }

    @ApiOperation(value="合并价格清单的税率和价格 | 开发者 翟伟 | 已完成", notes = "合并价格清单的税率和价格 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newPriceOrderId", value = "选择的价格清单id"
                    , required = true, paramType="path"),
            @ApiImplicitParam(name = "oldPriceOrderId", value = "之前的价格清单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/{newPriceOrderId}/{oldPriceOrderId}/merge/{pageNo}/{pageSize}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<PriceOrderDetailReturnVO>>> MergePriceOrderDetailMerge(
            @PathVariable
                    Long newPriceOrderId,
            @PathVariable
                    Long oldPriceOrderId,
            @PathVariable
                    Integer pageNo,
            @PathVariable
                    Integer pageSize,
            @ApiIgnore UserVO userVO
    ) throws Exception {
        Result<Page<List<PriceOrderDetailReturnVO>>> result = new Result<>();
        if(pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0){
            result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<PriceOrderDetailReturnVO>> page = new Page(pageNo,pageSize);
       priceOrderService.queryPriceOrderDetails2SelectOrderDetails(oldPriceOrderId,newPriceOrderId,userVO,page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(page);
        return result;
    }

    @ApiOperation(value="查询价格清单下拉框 | 开发者 翟伟 | 已完成", notes = "查询价格清单下拉框 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/priceOrder/IdAndNames", method = RequestMethod.GET)
    @ResponseBody
    public Result< List<QueryBean>> queryPriceOrderList(@ApiIgnore UserVO userVO,
                                                        @RequestParam(required = false)
                                                        @ApiParam(name = "type", value = "价格清单类型(0-基础价格；1-配货价格；2-零售价格)", required = false)
                                                                Integer type,
                                                        @RequestParam(required = false)
                                                            @ApiParam(name = "id", value = "价格清单id", required = false)
                                                                    Long id
    ) throws Exception {
        Result< List<QueryBean>> result = new Result<>();

        List<QueryBean> selectBeans = priceOrderService.queryPriceOrderIdAndNameList(userVO.getEnterpriseId(),type,id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(selectBeans);
        return result;
    }



    @ApiOperation(value="添加价格清单 | 开发者 翟伟 | 已完成", notes = "添加价格清单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(
            @ApiParam(name="addUserVO", value="新增员工信息实体" , required=true)
            @RequestBody
            @Valid @PriceOrderAddParamCheck AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result result = new Result<>();

        priceOrderService.savePriceOrder(userVO,addOrUpdatePriceOrderVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value="修改价格清单 | 开发者 翟伟 | 已完成", notes = "修改价格清单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Result modify(
            @ApiParam(name="addUserVO", value="修改价格清单实体" , required=true)
            @RequestBody
            @Valid @PriceOrderUpdateParamCheck AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result result = new Result<>();

        priceOrderService.updatePriceOrder(userVO,addOrUpdatePriceOrderVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value="删除价格清单 | 开发者 翟伟 | 已完成", notes = "删除价格清单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "priceOrderId", value = "价格清单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/{priceOrderId}/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public Result remove(
            @Valid
            @PriceOrderDeleteCheck
            @PathVariable
                    Long priceOrderId, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result result = new Result<>();

        priceOrderService.deletePriceOrder(priceOrderId);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value="查询修改记录信息 | 开发者 翟伟 | 已完成", notes = "根据价格清单id查询修改记录信息 | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "modifyRecords", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页码"
                    , required = false, paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数"
                    , required = false, paramType="query"),
            @ApiImplicitParam(name = "queryStr", value = "查询条件"
                    , required = false, paramType="queryStr"),
            @ApiImplicitParam(name = "priceOrderId", value = "价格清单id"
                    , required = false, paramType="priceOrderId")
    })
    @ResponseBody
    public Result< Page< List<PriceModifyRecordWithBLOBs> >> modifyRecords(
            @RequestParam(required = false)
                    Integer pageNo,
            @RequestParam(required = false)
                    Integer pageSize,
            @RequestParam(required = false)
                    String queryStr,
            @RequestParam(required = false)
                    Long priceOrderId,
            @ApiIgnore UserVO userVO

    ){
        Result< Page< List<PriceModifyRecordWithBLOBs> >> result = new Result<>();


        Page page = new Page(pageNo,pageSize);

        Map<String,Object> map = new HashMap<>();
        map.put("eId",userVO.getEnterpriseId());
        map.put("pId",userVO.getParentId());
        if(!StringUtils.isEmpty(queryStr) ){
            map.put("queryStr",queryStr);
        }
        if(null != priceOrderId){
            map.put("priceOrderId",priceOrderId);
        }


        List<PriceModifyRecordWithBLOBs> priceModifyRecordWithBLOBs = priceOrderService.getPriceModifyRecordWithBLOBs(map, page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }
    
    @ApiOperation(value="门店查询价格清单明细 | 开发者 张东东 | 已联调", notes = "门店查询价格清单明细 | 开发者 张东东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPriceOrderDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<PriceOrderReturnVO>> getPriceOrderDetail(
            @RequestParam(required = true)
                    Integer pageNo,
            @RequestParam(required = true)
                    Integer pageSize,
            @ApiIgnore UserVO userVO) throws Exception {
        Result<Page<PriceOrderReturnVO>> result = new Result<>();
        if(pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0){
            result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<PriceOrderReturnVO> page = new Page(pageNo,pageSize);
        priceOrderService.getPriceOrderDetail(userVO,page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(page);
        return result;
    }

    @ApiOperation(value = "导出列表 导出数据 | 开发者 张东东 | 已完成", notes = "导出数据 | 开发者 张东东 | 已完成")
	@GetMapping(value = "/excelExport/{id}/{sign}", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "价格清单id", required = true, paramType="path"),
    	@ApiImplicitParam(name = "sign", value = "0-导出，1-导入模板", required = true, paramType="path")
    })
    public void excelExport(HttpServletRequest request, HttpServletResponse response,@ApiIgnore UserVO userVO,
    		@PathVariable("id") Long id,@PathVariable("sign") Integer sign
    		) throws Exception {
        try {
        	String nameExcel = "价格清单";
        	PriceOrderReturnVO priceOrderReturnVO = priceOrderService.excelExportDate(userVO, id,sign);
        	if(sign == 1) {
        		//价格类型（0-基础价格；1-配货价格；2-零售价格）
        		/*if(priceOrderReturnVO.getPriceType() == 2) {
        			nameExcel = "零售价格清单导入模板";
        		}else if(priceOrderReturnVO.getPriceType() == 1) {
        			nameExcel = "配货价格清单导入模板";
        		}else {
        			nameExcel = "基础价格清单导入模板";
        		}*/
        		nameExcel = "价格清单导入模板";
        	}
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(nameExcel, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
	        priceOrderService.excelExport(output, userVO, id,priceOrderReturnVO,sign);
	        output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/excelImport")
    @ApiOperation(value = "价格清单导入",notes = "价格清单导入")
    public Result<ResponsePriceOrderExcelVO> excelImport(@RequestParam("id") Long id,
                                               @RequestParam("file") MultipartFile file,
                                               @ApiIgnore UserVO userVO) throws Exception {
        Result<ResponsePriceOrderExcelVO> result = new Result<>();
        ResponsePriceOrderExcelVO responsePriceOrderExcelVO = priceOrderService.excelImport(userVO, id, file);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, responsePriceOrderExcelVO);
        return result;
    }

    @ApiOperation(value = "价格清单导出导入的数据 | 开发者 翟伟 | 已完成", notes = "价格清单导出导入的数据 | 开发者 张东东 | 已完成")
    @GetMapping(value = "/disQualtfied/excelExport", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelDisQualtfiedExport(HttpServletResponse response,
                                        @ApiIgnore UserVO userVO,
                            @RequestParam("id") Long id,
                            @RequestParam("key") String key,
                            @RequestParam("type") Integer type
    ) throws Exception {
        try {

            PriceOrder priceOrder = priceOrderService.queryPriceOrder4Id(id);

            if(null == priceOrder){
                throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"未找到价格清单");
            }

            String nameExcel = "价格清单导入模板";

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(nameExcel, "UTF-8")+".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            priceOrderService.excelDisQualtfiedExport(output,priceOrder, userVO, key,type);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/import/priceOrder")
    @ApiOperation(value = "价格清单继续导入功能",notes = "价格清单实际导入数据到数据库")
    public Result importPriceOrder(
                                    @RequestParam("id") Long id,
                                    @RequestParam("key") String key,
                                    @ApiIgnore UserVO userVO) throws Exception {
        Result result = new Result<>();
        priceOrderService.importPriceOrder(userVO, id, key);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

   /* @PostMapping("/import/test")
    @ApiOperation(value = "test",notes = "test")
    public Result testTh() throws Exception {
        Result result = new Result<>();
        priceOrderService.testTh();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }*/
}
