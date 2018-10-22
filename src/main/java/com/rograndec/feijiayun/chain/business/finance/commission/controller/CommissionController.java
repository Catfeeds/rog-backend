package com.rograndec.feijiayun.chain.business.finance.commission.controller;

import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommission;
import com.rograndec.feijiayun.chain.business.finance.commission.service.CommissionService;
import com.rograndec.feijiayun.chain.business.finance.commission.vaild.SaleCommissionSaveVaild;
import com.rograndec.feijiayun.chain.business.finance.commission.vaild.SaleCommissionWriteOffVaild;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.*;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.ResponseSaleMan;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提成管理接口
 * Created by zhaiwei on 2017/9/19.
 */
@Api(description = "提成管理接口")
@RestController
@RequestMapping("commission/")
@Validated
public class CommissionController {


    @Autowired
    private CommissionService commissionService;

    @Autowired
    private UserManagerService userManagerService;

    @ApiOperation(value = "待提成列表 | 开发者 翟伟 | 开发中", notes = "待提成列表 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/waits",method= RequestMethod.GET)
    public Result<NoSaleCommissionResponseTotalVO> waits(
            @RequestParam(required = false)
            @ApiParam(name = "branchId", value = " 门店id", required = false)
                    Long branchId,
            @RequestParam(required = true)
            @ApiParam(name = "clerkId", value = " 营业人员", required = true)
            @Valid
            @NotNull(message = "营业人员不能为空")
                    String clerkId,
            @RequestParam(required = true)
            @ApiParam(name = "startTime", value = "开始时间", required = true)
            @Valid
            @NotNull(message = "开始时间不能为空")
                    Date startTime,
            @RequestParam(required = true)
            @ApiParam(name = "endTime", value = "结束时间", required = true)
            @Valid
            @NotNull(message = "结束时间不能为空")
                    Date endTime
            ,@ApiIgnore UserVO userVO
    ) throws Exception {
        Result<NoSaleCommissionResponseTotalVO> result = new Result<>();

        NoSaleCommissionResponseTotalVO commission = commissionService.getCommissions(branchId
                , clerkId
                , startTime
                , endTime
                , userVO
        );



        result.setBizCodeSuccessInfo(SysCode.SUCCESS,commission);
        return result;
    }


    @ApiOperation(value = "获取营业人员列表 | 开发者 翟伟 | 已联调", notes = "根据登录人当前的企业id获取营业人员列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/sale/mans", method = RequestMethod.GET)
    public Result<List<ResponseSaleMan>> saleMans(
            @ApiParam(name = "param",value = "供货单位编码名称检索码")@RequestParam(required = false)String param,
            @RequestParam(required = false)
            @ApiParam(name = "branchId", value = " 门店id", required = false)
                    Long branchId,
            @ApiIgnore UserVO userVO
    ) {
        Result<List<ResponseSaleMan>> result = new Result<>();
        Map<String,Object> map = new HashMap<>();
        if(null == branchId){
            map.put("enterpriseId",userVO.getEnterpriseId());
        }else {
            map.put("enterpriseId",branchId);
        }

        if(!StringUtils.isEmpty(param))
            map.put("param",param);

        List<ResponseSaleMan> saleMans = commissionService.getSaleMans(map);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, saleMans);
        return result;
    }

    @ApiOperation(value = "获取提成管理门店列表 | 开发者 翟伟 | 已联调", notes = "获取提成管理门店列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/branchs", method = RequestMethod.GET)
    public Result<List<NewSelectBean>> branchs(
            @ApiParam(name = "param",value = "获取提成管理门店列表")@RequestParam(required = false)String param,
            @ApiIgnore UserVO userVO
    ) {
        Result<List<NewSelectBean>> result = new Result<>();

        List<NewSelectBean> enterprises = commissionService.queryBranch(userVO, param);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterprises);
        return result;
    }

    @ApiOperation(value = "提成新增/修改", notes = "提成新增/修改 | 开发者 翟伟 | 开发中")
    @RequestMapping(value="/save", method= RequestMethod.POST)
    public Result<SaleCommission> save(
            @ApiParam(value = "提成实体", required = true)
            @RequestBody @Valid @SaleCommissionSaveVaild NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO, @ApiIgnore UserVO userVO
    ) throws Exception {

        Result<SaleCommission> result = new Result<>();
        if(null == noSaleCommissionResponseTotalVO.getId()) {
            SaleCommission save = commissionService.save(userVO, noSaleCommissionResponseTotalVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,save);
        }
        else {
            SaleCommission update = commissionService.update(userVO, noSaleCommissionResponseTotalVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,update);
        }

        return result;
    }


    @ApiOperation(value = "提成管理列表", notes = "提成管理列表 | 开发者 翟伟 | 开发中")
    @RequestMapping(value="/list", method= RequestMethod.GET)
    public Result<Page<List<CommissionResponseTotalVO>>> list(
            @ApiParam(value = "提成单号", required = false) @RequestParam(required = false) String code,
            @ApiParam(value = "提成人员名称", required = false) @RequestParam(required = false) String commissionManName,
            @ApiParam(value = "营业人员名称", required = false) @RequestParam(required = false) String clerkName,
            @ApiParam(value = "单据状态(1-已完成；2-已冲销)", required = false) @RequestParam(required = false) Integer status,
            @ApiParam(value = "门店编码", required = false) @RequestParam(required = false) String branchCode,
            @ApiParam(value = "门店名称", required = false) @RequestParam(required = false) String branchName,
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize,

            @RequestParam(required = false)
            @ApiParam(name = "orderName", value = "按某一列排序", required = false)
                    String  orderName,
            @RequestParam(required = false)
            @ApiParam(name = "orderType", value = "排序方式（升序：asc,降序：desc", required = false)
                    String orderType,

            @RequestParam(required = false)
            @ApiParam(name = "startDate", value = "开始时间", required = false)
                    Date  startDate,
            @RequestParam(required = false)
            @ApiParam(name = "endDate", value = "结束时间", required = false)
                    Date endDate,
            @ApiIgnore UserVO userVO
    ) throws Exception {

        Result<Page<List<CommissionResponseTotalVO>>> result = new Result<>();
        CommissionQueryParamVO commissionQueryParamVO = new CommissionQueryParamVO(
                code,
                commissionManName,
                clerkName,
                status,
                branchCode,
                branchName,
                orderName,
                orderType,
                startDate,
                endDate
        );

        Page page = new Page(pageNo,pageSize);

        CommissionResponseTotalVO commissionResponseTotalVO = commissionService.queryCommissions(userVO, commissionQueryParamVO, page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }


    @ApiOperation(value = "提成管理详情", notes = "提成管理详情 | 开发者 翟伟 | 开发中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提成id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value="/commission/{id}", method= RequestMethod.GET)
    public Result<NoSaleCommissionResponseTotalVO> paymentInvoices(
            @PathVariable Long id,
            @ApiIgnore UserVO userVO
    ) throws Exception {

        Result<NoSaleCommissionResponseTotalVO> result = new Result<>();
        NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO = commissionService.queryCommission(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,noSaleCommissionResponseTotalVO);
        return result;
    }


    @ApiOperation(value = "查看草稿", notes = "查看草稿 | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO) {

        Result<List<DraftCacheVO>> result = new Result<>();

        List<DraftCacheVO> draftCacheVO = commissionService.getDraftCacheVO(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存草稿", notes = "保存草稿 | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO draftCacheVO) {
        Result<DraftCacheVO> result = new Result();
        DraftCacheVO distrReqPlanVODraftCacheVO = commissionService.saveDraftCache(userVO, draftCacheVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReqPlanVODraftCacheVO);

        return  result;
    }


    @ApiOperation(value = "删除草稿", notes = "删除草稿 | 开发者 翟伟 | 开发中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.DELETE)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();

        commissionService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.COMMISSION.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }


    @ApiOperation(value = "查看修改记录", notes = "查看修改记录 | 开发者 翟伟 | 开发中")
    @RequestMapping(value = "/modifyRecords/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "提成id", required = true, dataType = "Long", paramType="path")
    public Result<Page<List<SaleCommissionModifyRecordResponseVO>>> modifyRecords(
            @ApiIgnore UserVO userVO,
            @PathVariable Long id,
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize
    ) {

        Result<Page<List<SaleCommissionModifyRecordResponseVO>>> result = new Result<>();

        Page page = new Page(pageNo,pageSize);

        List<SaleCommissionModifyRecordResponseVO> saleCommissionModifyRecordResponseVOS = commissionService.queryModifyRecords(userVO, id, page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);

        return  result;
    }


    @ApiOperation(value = "提成详情信息导出", notes = "提成详情信息导出 | 开发者 翟伟 | 开发中")
    @RequestMapping(value="/exportCommissionDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "提成id", required = true, dataType = "Long", paramType="path")
    public void exportReceiveDetail(HttpServletResponse response,
                                    @PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

        OutputStream output = response.getOutputStream();
        String name = "销售提成";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
        NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO = commissionService.queryCommission(id);

        List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS = noSaleCommissionResponseTotalVO.getResponseNoSaleRoyaltyDetailVOS();

        if(CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOS)){

            commissionService.export(output,userVO, new NoSaleCommissionResponseTotalVO());

        }else {
            for(NoSaleCommissionDetailResponseVO noSaleCommissionDetailResponseVO : responseNoSaleRoyaltyDetailVOS){
                BigDecimal profitRate = noSaleCommissionDetailResponseVO.getProfitRate();
                if(null != profitRate){
                    BigDecimal bigDecimal = profitRate.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP);
                    noSaleCommissionDetailResponseVO.setProfitRate(bigDecimal);
                }
            }
            commissionService.export(output,userVO, noSaleCommissionResponseTotalVO);
        }

    }


    @ApiOperation(value = "修改记录导出", notes = "修改记录导出 | 开发者 翟伟 | 开发中")
    @RequestMapping(value="/exportUpdateRecord/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "提成管理id", required = true, dataType = "Long", paramType="path")
    public void exportUpdateRecord(HttpServletResponse response,
                                   @PathVariable Long id,@ApiIgnore UserVO userVO) throws Exception {

        OutputStream output = response.getOutputStream();
        String name = "提成管理修改信息";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
        List<SaleCommissionModifyRecordResponseVO> saleCommissionModifyRecordResponseVOS = commissionService.queryModifyRecords(userVO, id, null);
        commissionService.exportUpdateRecord(output,saleCommissionModifyRecordResponseVOS,userVO);

    }


    @ApiOperation(value = "冲销", notes = "冲销 | 开发者 翟伟 | 开发中")
    @RequestMapping(value="/wariteOff/{id}",method= RequestMethod.PUT)
    @ApiImplicitParam(name = "id", value = "应付发票id", required = true, dataType = "Long", paramType="path")
    public void wariteOff(@PathVariable @Valid @SaleCommissionWriteOffVaild Long id, @ApiIgnore UserVO userVO) throws Exception {

        Result result = new Result<>();

        commissionService.wariteOff(userVO,id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

    }

}
