package com.rograndec.feijiayun.chain.business.basic.supplier.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierVarietiesService;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.GoodsOrderVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by madong on 2017/8/31.
 */

@Api(value = "supplierVarieties",description = "供货单位经营品种接口")
@RestController
@RequestMapping("basic/supplier/varieties")
public class SupplierVarietiesController {

    private static final Log logger = LogFactory.getLog(SupplierVarietiesController.class);

    @Autowired
    private SupplierVarietiesService supplierVarietiesService;

    /*@ApiOperation(value="获取供应单位", notes = "获取供应单位 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSupplier", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Map>> getSupplier(@ApiIgnore UserVO userVO){
        Result<List<Map>> result = new Result<>();
        try{
            List<Map> suppliers = supplierVarietiesService.getSupplier(userVO.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, suppliers);
        }catch(Exception e){
            logger.error("获取供应单位错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
*/
    @ApiOperation(value="获取供应单位经营品种", notes = "获取供应单位经营品种 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSupplierVarieties", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page> getSupplierVarieties(@ApiIgnore UserVO userVO,
            @ApiParam(value = "供应商搜索条件,没有传-1",required = true) @RequestParam(required = false) String supplierInfo,
            @ApiParam(value = "品种搜索条件,没有传-1",required = true) @RequestParam(required = false) String goodsInfo,
            @ApiParam(value = "排序字段名,没有传-1",required = true) @RequestParam String orderName,
            @ApiParam(value = "排序方式,ASC或者DESC,没有传-1",required = true) @RequestParam String orderType,
            @ApiParam(value = "分页参数",required = true) @RequestParam(required = false) Integer pageNo,
            @ApiParam(value = "分页参数",required = true) @RequestParam(required = false) Integer pageSize){
        Result<Page> result = new Result<>();
        try{
            Page page = supplierVarietiesService.getSupplierVarieties(userVO,supplierInfo,goodsInfo,orderName,orderType,pageNo,pageSize);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("获取供应单位经营品种错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="根据选取供货单位获得商品信息", notes = "根据选取供货单位获得商品信息 | 开发者:马东 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSupplierVarietiesGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<GoodsOrderVO>>> getSupplierVarietiesGoods(@ApiIgnore UserVO userVO,
           @ApiParam(value = "供应商id",required = true) @RequestParam Long supplierId,
           @ApiParam(value = "当前页码", required = true) @RequestParam Integer pageNo,
           @ApiParam(value = "页容量", required = true) @RequestParam Integer pageSize,
           @ApiParam(value = "检索条件") @RequestParam(required = false) String param){
        Result<Page<List<GoodsOrderVO>>> result = new Result<>();
        try{
            Page<List<GoodsOrderVO>> page = supplierVarietiesService.getSupplierVarietiesGoods(userVO,supplierId,pageNo,pageSize,param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("根据选取供货单位获得商品信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="保存供货单位经营品种", notes = "保存供货单位经营品种 | 开发者:马东 | 已联调"
            +" 参数说明  " +
            "agreementPrice: 必传," +
            "    goodsCode: 必传," +
            "    goodsId: 必传," +
            "    goodsName: 必传," +
            "    managementMode: 必传," +
            "    normGoodsId: 必传," +
            "    remark: 有就传," +
            "    soleSupplier: 必传," +
            "    status: 必传," +
            "    supplierGoodsCode: 必传," +
            "    supplierId: 必传," +
            "    taxRate: 必传," +
            "    taxRateId: 必传", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveSupplierVarieties", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveSupplierVarieties(@ApiIgnore UserVO userVO,
           @ApiParam(value = "需要保存的供货单位经营品种集合",required = true) @RequestBody List<SupplierVarieties> supplierVarieties){
        Result<String> result = new Result<>();
        try{
            supplierVarietiesService.saveSupplierVarieties(userVO,supplierVarieties);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存供货单位经营品种成功");
        }catch(Exception e){
            logger.error("保存供货单位经营品种错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="修改供货单位经营品种", notes = "修改供货单位经营品种 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSupplierVarieties", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSupplierVarietie(@ApiIgnore UserVO userVO,
           @ApiParam(value = "需要修改的供货单位经营品种",required = true) @RequestBody SupplierVarieties supplierVarietie){
        Result<String> result = new Result<>();
        try{
            supplierVarietiesService.updateSupplierVarietie(userVO,supplierVarietie);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改供货单位经营品种成功");
        }catch(Exception e){
            logger.error("修改供货单位经营品种错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="删除供货单位经营品种", notes = "删除供货单位经营品种 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSupplierVarietie", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSupplierVarietie(@ApiIgnore UserVO userVO,
           @ApiParam(value = "需要删除的供货单位经营品种ID",required = true) @RequestParam Long id,
           @ApiParam(value = "需要删除的供货单位经营品种货品ID",required = true) @RequestParam Long goodsId,
           @ApiParam(value = "需要删除的供货单位经营品种的供应商id",required = true) @RequestParam Long supplierId){
        Result<String> result = new Result<>();
        try{
            //如果该经营分类里的供应商的品种发生过业务,则不能删除(这个判断接口暂时预留,等到采购模块开始在完善)
            if(supplierVarietiesService.isHappenedBusiness(id,goodsId,supplierId,userVO)){
                int count = supplierVarietiesService.deleteSupplierVarietie(id,userVO);
                if(count == 1)
                    result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除供货单位经营品种成功!");
                else
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该供货单位经营品种已不存在与系统中!");
            }else{
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "该经营品种已经发生过业务,不能删除!");
            }
        }catch(Exception e){
            logger.error("删除供货单位经营品种错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="导出供货单位经营品种信息", notes = "导出供货单位经营品种信息 | 开发者:马东 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportSupplierVarieties", method = RequestMethod.GET)
    @ResponseBody
    public void exportSupplierVarieties(@ApiIgnore UserVO userVO, HttpServletResponse response,
           @ApiParam(value = "供应商搜索条件") @RequestParam(required = false) String supplierInfo,
           @ApiParam(value = "品种搜索条件") @RequestParam(required = false) String goodsInfo){
        try{
            String name = "供货单位经营品种导出结果.xlsx";
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            supplierVarietiesService.excelExport(userVO,output,supplierInfo,goodsInfo);
            output.close();
            output.flush();
        }catch(Exception e){
            logger.error("导出供货单位经营品种错误:"+e.getMessage(),e);
        }
    }

}
