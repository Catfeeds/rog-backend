package com.rograndec.feijiayun.chain.business.purchase.ret.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSalerSimpleVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierSaleService;
import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageDetailForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderService;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SupplierOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.ret.exception.ReturnBizException;
import com.rograndec.feijiayun.chain.business.purchase.ret.service.PurchaseReturnService;
import com.rograndec.feijiayun.chain.business.purchase.ret.valid.UpdateReturnCheck;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.QualitySettingsService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.GoodsComponent;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 购进退出
 * Created by zhaiwei on 2017/9/16.
 */
@Api(value = "purchase_return",description = "购进退出服务")
@RestController
@RequestMapping("purchase/return/")
@Validated
public class ReturnController {

    @Autowired
    private PurchaseReturnService purchaseReturnService;

    @Autowired
    private GoodsComponent goodsComponent;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private SupplierSaleService supplierSaleService;

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private QualitySettingsService qualitySettingsService;

    @ApiOperation(value="新增购进退出接口 | 开发者 翟伟 | 已联调", notes = "新增购进退出接口 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result save(@ApiIgnore UserVO userVO,
            @ApiParam(name="returnSaveOrUpdateVO", value="新增购进退出接口实体" , required=true)
            @RequestBody @Valid
            PurchaseReturnSaveOrUpdateVO returnSaveOrUpdateVO
    ) throws Exception {
        Result result = new Result<>();

        purchaseReturnService.save(userVO,returnSaveOrUpdateVO,true);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value="修改购进退出接口 | 开发者 翟伟 | 已联调", notes = "修改购进退出接口 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Result modify(@ApiIgnore UserVO userVO,
            @ApiParam(name="returnSaveOrUpdateVO", value="新增购进退出接口实体" , required=true)
            @RequestBody @Valid @UpdateReturnCheck
            PurchaseReturnSaveOrUpdateVO returnSaveOrUpdateVO
    ) throws Exception {
        Result result = new Result<>();

        purchaseReturnService.save(userVO,returnSaveOrUpdateVO,false);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value="查询购进退出审核列表 | 开发者 翟伟 | 已联调", notes = "查询购进退出审核列表  | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/purchaseReturns", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<PurchaseReturnTotalShowVO>> purchaseReturns(
            @ApiIgnore UserVO userVO,
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize,
            @RequestParam(required = false)
            @ApiParam(name = "supplierCode", value = "供货单位编码", required = false)
                    String supplierCode,
            @RequestParam(required = false)
            @ApiParam(name = "supplierName", value = "供货单位名称", required = false)
                    String supplierName,
            @RequestParam(required = false)
            @ApiParam(name = "code", value = "购进退出单号", required = false)
                    String code,
            @RequestParam(required = false)
            @ApiParam(name = "returnManName", value = "退货人员名称", required = false)
                    String returnManName,
            @RequestParam(required = false)
            @ApiParam(name = "startDate", value = "开始时间", required = false)
                    Date startDate,
            @RequestParam(required = false)
            @ApiParam(name = "endDate", value = "结束时间", required = false)
                    Date endDate,
            @RequestParam(required = false)
            @ApiParam(name = "approveStatus", value = "审核状态(全部:不用传;待审核:21;已完成:33;审核被驳回:24;取消:34;待出库:81)", required = false)
                    Integer approveStatus,
            @ApiParam(value = "按某一列排序(returnDate或者code)", required = false) @RequestParam(required = false) String order,
            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort
    ){
        Result<Page<PurchaseReturnTotalShowVO>> result = new Result<>();

        Map<String,Object> map = new HashMap<>();

        if(!StringUtils.isEmpty(order) && (!"returnDate".equals(order) && !"code".equals(order))){
            throw new ReturnBizException(ReturnBizException.VALUE_CHECK,"排序参数不正确");
        } else {
           if("returnDate".equals(order)){
               map.put("order","return_date");
           }
           if("code".equals(order)){
               map.put("order","code");
           }
        }

        if(!StringUtils.isEmpty(order) && (!"asc".equals(sort) && !"desc".equals(sort))){
            throw new ReturnBizException(ReturnBizException.VALUE_CHECK,"排序参数不正确");
        } else {
            map.put("sort",sort);
        }
        startDate=StartAndEndDate.getStartTime(startDate);
        endDate=StartAndEndDate.getEndTime(endDate);


        if(null != approveStatus){
            map.put("status", approveStatus);
        }

        if(!StringUtils.isEmpty(supplierCode)){
            map.put("supplierCode", supplierCode);
        }


        if(!StringUtils.isEmpty(supplierName)){
            map.put("supplierName", supplierName);
        }


        if(!StringUtils.isEmpty(code)){
            map.put("code", code);
        }

        if(!StringUtils.isEmpty(returnManName)){
            map.put("returnManName", returnManName);
        }

        if(null != startDate){
            map.put("startTime", startDate);
        }

        if(null != endDate){
            map.put("endTime", endDate);
        }
        map.put("enterpriseId", userVO.getEnterpriseId());
        map.put("parentId", userVO.getParentId());

        Page<PurchaseReturnTotalShowVO> page = new Page(pageNo, pageSize);
        List<PurchaseReturn> purchaseReturnsByParam = purchaseReturnService.getPurchaseReturnsByParam(userVO, page, map);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }


    @ApiOperation(value="查询单个购进退出详情 | 开发者 翟伟 | 已联调", notes = "查询单个购进退出详情  | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购进退出单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/purchaseReturn/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<PurchaseReturnShowVO> purchaseReturn(
            @ApiIgnore UserVO userVO,
            @PathVariable
                    Long id
    ){
        Result<PurchaseReturnShowVO> result = new Result<>();

        PurchaseReturnShowVO purchaseReturnDetails = purchaseReturnService.getPurchaseReturnDetails(id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnDetails);
        return result;
    }

    @ApiOperation(value="导出 | 开发者 翟伟 | 已联调", notes = "查询单个购进退出详情  | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购进退出单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/exportExcel/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletResponse response,
                            @ApiIgnore UserVO userVO,
                            @PathVariable
                                    Long id
    ) throws IOException {

        String file = "购进退出单";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = response.getOutputStream();


        purchaseReturnService.exportExcel(output,id);

        output.close();
        output.flush();
    }


    @ApiOperation(value = "搜索商品 | 开发者 孙腾 | 已联调", notes = "根据编码/条形码/检索码搜索商品 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsByParam",method= RequestMethod.GET)
    public Result<List<PurchaseReturnGoodsVO>> getGoodsByParam(
            @ApiIgnore UserVO userVO,
            @RequestParam(required = false) @ApiParam(name = "param", value = "检索条件", required = false) String param,
            @RequestParam(required = false) @ApiParam(name = "supplierId", value = "供应商id", required = false) Long supplierId
    ){
        Result<List<PurchaseReturnGoodsVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnService.getGoodsInfoByPurchaseReturn(param,userVO.getEnterpriseId(),supplierId));
        return result;
    }

    @ApiOperation(value = "搜索采购入库单 | 开发者 孙腾 | 已联调", notes = "搜索采购入库单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHaveBeenInStorage",method= RequestMethod.POST)
    public Result<Page<List<PurchaseInStorageForReturnVO>>> getHaveBeenInStorage(@ApiIgnore UserVO userVO, @RequestBody GetInStorageParamVO getInStorageParamVO){
        Result<Page<List<PurchaseInStorageForReturnVO>>> result = new Result<>();
        Integer pageNo = getInStorageParamVO.getPageNo();
        Integer pageSize = getInStorageParamVO.getPageSize();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page<List<PurchaseInStorageForReturnVO>> page = new Page(pageNo, pageSize);
            getInStorageParamVO.setEnterpriseId(userVO.getEnterpriseId());
            purchaseReturnService.getInStorageForReturn(page,getInStorageParamVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "搜索采购入库单明细 | 开发者 孙腾 | 已联调", notes = "搜索采购入库单明细 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHaveBeenInStorageDetail",method= RequestMethod.POST)
    public Result<Page<List<PurchaseInStorageDetailForReturnVO>>> getHaveBeenInStorageDetail(@ApiIgnore UserVO userVO, @RequestBody GetInStorageParamVO getInStorageParamVO){
        Result<Page<List<PurchaseInStorageDetailForReturnVO>>> result = new Result<>();
        Integer pageNo = getInStorageParamVO.getPageNo();
        Integer pageSize = getInStorageParamVO.getPageSize();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<PurchaseInStorageDetailForReturnVO>> page = new Page(pageNo, pageSize);
        getInStorageParamVO.setEnterpriseId(userVO.getEnterpriseId());
        purchaseReturnService.getInStorageDetailForReturn(page,getInStorageParamVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }

    @ApiOperation(value = "查询税率 | 开发者 翟伟 | 已联调", notes = "查询税率 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/getTax",method= RequestMethod.GET)
    public Result<List<GoodsTaxRateVO>> getTax(@ApiIgnore UserVO userVO){
        Result<List<GoodsTaxRateVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsComponent.getGoodsTaxRateVO(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus()));
        return result;
    }

    @ApiOperation(value="结算单位查询 | 开发者 翟伟 | 已联调", notes = "根据登录用户的结算单位并且附带地址,电话,默认结算方式信息 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/children/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<EnterpriseBzAndChildrenVO>> getEnterpriseChildren(@ApiIgnore UserVO userVO){
        Result<List<EnterpriseBzAndChildrenVO>> result = new Result<List<EnterpriseBzAndChildrenVO>>();


        List<EnterpriseBzAndChildrenVO> enterpriseVOS = new ArrayList<>();
        Enterprise enterprise = enterpriseService.queryEnterpriseById(userVO.getEnterpriseId(),"enterprise");
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterprise.getId());
        EnterpriseBzAndChildrenVO enterpriseVO = EnterpriseBzAndChildrenVO.getEnterprise4VO(enterprise,enterpriseBusiness);
        enterpriseVOS.add(enterpriseVO);


        result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseVOS);
        return result;
    }

    @ApiOperation(value = "查看当前企业一些配置控制信息 | 开发者 翟伟 | 已联调", notes = "查看当前企业一些配置控制信息 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/checkCode",method= RequestMethod.GET)
    public Result<ManageConfig> checkCode(@ApiIgnore UserVO userVO){
        Result<ManageConfig> result = new Result<ManageConfig>();
        try{
            ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,manageConfig);
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="取消购进退出单 | 开发者 翟伟 | 已联调", notes = "取消购进退出单 | 开发者 翟伟 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购进退出单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result cancel(
            @ApiIgnore UserVO userVO,
            @PathVariable
                    Long id, HttpSession session
    ) throws Exception {
        Result result = new Result<>();

        purchaseReturnService.cancel(id, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "获取供货单位列表 | 开发者 翟伟 | 已联调", notes = "获取供货单位列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public Result<List<SupplierOrderVO>> suppliers(@ApiIgnore UserVO userVO) throws Exception {
        Result<List<SupplierOrderVO>> result = new Result<List<SupplierOrderVO>>();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnService.getSupplier(userVO));
        return result;
    }

    @ApiOperation(value = "获取供货单位人员列表 | 开发者 翟伟 | 已联调", notes = "根据供货单位id获取供货单位人员列表 | 开发者 翟伟 | 已联调")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "供货单位id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/supplierUsers/{id}", method = RequestMethod.GET)
    public Result<List<SupplierSalerSimpleVO>> supplierUsers(
            @ApiIgnore UserVO userVO,
             @PathVariable
                    Long id
    ) {
        Result<List<SupplierSalerSimpleVO>> result = new Result<List<SupplierSalerSimpleVO>>();
        List<SupplierSaler> salerBySupplieres = supplierSaleService.findSalerBySuppliereId(id);
        List<SupplierSalerSimpleVO> supplierSalerSimpleVOs = SupplierSalerSimpleVO.getSupplierSalerSimpleVOs(salerBySupplieres);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, supplierSalerSimpleVOs);
        return result;
    }

    @ApiOperation(value = "获取退货人员列表 | 开发者 翟伟 | 已联调", notes = "根据登录人当前的企业id获取退货人员列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/returnMans", method = RequestMethod.GET)
    public Result<List<SimpleUserVO>> returnMans(
            @ApiIgnore UserVO userVO
    ) {
        Result<List<SimpleUserVO>> result = new Result<>();

        List<User> users = userManagerService.findUserByEId(userVO.getEnterpriseId());
        List<SimpleUserVO> simpleUserVOs = SimpleUserVO.getSimpleUserVOs(users);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, simpleUserVOs);
        return result;
    }



    @ApiOperation(value = "获取退货原因列表 | 开发者 翟伟 | 已联调", notes = "获取退货原因列表列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/returnReasons", method = RequestMethod.GET)
    public Result<List<QualitySet>> returnReasons(
            @ApiIgnore UserVO userVO,
            @RequestParam(required = false)
            @ApiParam(name = "type", value = "type含义为退货类型（0-质量问题退货；1-非质量问题退货）", required = false)
                    Integer type
    ) {
        Result<List<QualitySet>> result = new Result();

        List<QualitySet> qualitySettings = qualitySettingsService.getQualitySettings(userVO, 2,type);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, qualitySettings);
        return result;
    }

    @ApiOperation(value="查询修改记录信息 | 开发者 翟伟 | 已联调", notes = "根据购进退出单查询修改记录信息 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "modifyRecord/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"
                    , required = true, paramType="path"),
            @ApiImplicitParam(name = "pageNo", value = "当前页码"
                    , required = false, paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数"
                    , required = false, paramType="query")
    })
    @ResponseBody
    public Result< Page<List<PurchaseReturnModifyRecord>>> modifyRecords(
            @PathVariable
                    Long id,
            @RequestParam(required = false)
                    Integer pageNo,
            @RequestParam(required = false)
                    Integer pageSize,@ApiIgnore UserVO userVO
    ){
        Result< Page<List<PurchaseReturnModifyRecord>>> result = new Result<>();


        Page<List<PurchaseReturnModifyRecord>> page = new Page(pageNo,pageSize);
        page = purchaseReturnService.getPurchaseReturnModifyRecord(userVO,id,page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }



    @ApiOperation(value = "查看购进退出草稿缓存 | 开发者 杜东阳 | 已完成", notes = "查看购进退出草稿缓存 | 开发者 杜东阳 | 已完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {

        Result<List<DraftCacheVO>> result = new Result<>();

        List<DraftCacheVO> draftCacheVO = purchaseReturnService.getDraftCacheVO(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存购进退出草稿缓存 | 开发者 杜东阳 | 已完成", notes = "保存购进退出草稿缓存 | 开发者 杜东阳 | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<PurchaseReturnSaveOrUpdateVO> draftCacheVO) {

        Result result = new Result<>();

        DraftCacheVO saveDraftCache = purchaseReturnService.saveDraftCache(userVO, draftCacheVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,saveDraftCache);

        return  result;
    }

    @ApiOperation(value = "删除购进退出草稿缓存 | 开发者 杜东阳 | 已完成", notes = "删除购进退出草稿缓存 | 开发者 杜东阳 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.POST)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();

        purchaseReturnService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.PURCHASE_RETURN.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }

    @ApiOperation(value = "获取采购订单的配货类型和承运单位 | 开发者 张东东 | 已完成", notes = "获取采购订单的配货类型和承运单位 | 开发者 张东东 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "采购入库单的id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/getDefaultDistrInfo/{id}", method = RequestMethod.GET)
    public Result<Map<String,Object>> getDefaultDistrInfo(@ApiIgnore UserVO userVO, @PathVariable(required = true) Long id) {

        Result<Map<String,Object>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnService.getDefaultDistrInfo(id));
        return  result;
    }

}
