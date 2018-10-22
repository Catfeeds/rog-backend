package com.rograndec.feijiayun.chain.business.goods.price.controller;

import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.price.service.PriceAdjustService;
import com.rograndec.feijiayun.chain.business.goods.price.service.PriceOrderService;
import com.rograndec.feijiayun.chain.business.goods.price.valid.PriceAdjustApprovalApplyCheck;
import com.rograndec.feijiayun.chain.business.goods.price.valid.PriceAdjustUpdateCheck;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceAdjustVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustReturnVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by zhaiwei on 2017/9/6.
 */
@Api(description = "价格调整接口")
@RestController
@RequestMapping("price/priceAdjust")
@Validated
public class PriceAdjustController {


    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PriceAdjustService priceAdjustService;

    @Autowired
    private PriceOrderService priceOrderService;

//
//    @Autowired
//    private GoodsComponent goodsComponent;

    @ApiOperation(value = "搜索商品 | 开发者 翟伟 | 已完成", notes = "根据编码/条形码/检索码搜索商品 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/getGoodsByParam",method= RequestMethod.GET)
    public Result<Page<List<PriceAdjustGoodsVO>>> getGoodsByParam(HttpSession session,
                     @RequestParam(required = false)
                     @ApiParam(name = "pageNo", value = "当前页码", required = false) Integer pageNo
                    , @RequestParam(required = false)
                       @ApiParam(name = "pageSize", value = "显示条数", required = false) Integer pageSize
                    ,@RequestParam(required = false)
                       @ApiParam(name = "param", value = "检索条件", required = false) String param
                    ,@RequestParam(required = false)
                       @ApiParam(name = "priceOrderId", value = "价格清单id", required = true) Long priceOrderId
                ){
        Result<Page<List<PriceAdjustGoodsVO> >> result = new Result<>();
        Page page = new Page(pageNo, pageSize);
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<PriceAdjustGoodsVO> goodsList = priceAdjustService.queryPriceAdjustGoodsVOs4PriceOrder(userVO,priceOrderId, param, page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value="添加价格调整单 | 开发者 翟伟 | 已完成", notes = "添加价格调整单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(
            @ApiParam(name="addUserVO", value="价格调整单实体" , required=true)
            @RequestBody
            @Valid
                    @PriceAdjustApprovalApplyCheck
                    AddOrUpdatePriceAdjustVO addOrUpdatePriceOrderVO, @ApiIgnore UserVO userVO
            ) throws Exception {
        Result result = new Result<>();


        priceAdjustService.savePriceAdjusts(userVO,addOrUpdatePriceOrderVO,true);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value="修改价格调整单 | 开发者 翟伟 | 已完成", notes = "修改价格调整单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Result modify(
            @ApiParam(name="addUserVO", value="价格调整单实体" , required=true)
            @RequestBody @Valid
                    @PriceAdjustUpdateCheck
                    AddOrUpdatePriceAdjustVO addOrUpdatePriceOrderVO, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result result = new Result<>();

        priceAdjustService.savePriceAdjusts(userVO,addOrUpdatePriceOrderVO,false);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value="查看价格调整单 | 开发者 翟伟 | 已完成", notes = "查看价格调整单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adjustId", value = "价格调整单id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/{adjustId}/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PriceAdjustGoodsVO>> get(
            @PathVariable
                    Long adjustId, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result<List<PriceAdjustGoodsVO> > result = new Result<>();

        List<PriceAdjustGoodsVO> priceAdjustGoodsVOS = priceAdjustService.queryPriceAdjustGoodsVOs4Adjust(adjustId,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,priceAdjustGoodsVOS);
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
        List<QueryBean> selectBeans = new ArrayList<>();
        if(ChainType.Headquarters.getCode() == userVO.getChainType()){
            //总部
            selectBeans = priceAdjustService.queryPriceOrderIdAndNameList(userVO.getEnterpriseId(),type,id);
        } else {
            selectBeans = priceOrderService.queryPriceOrderIdAndNameList(userVO.getEnterpriseId(),type,id);
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        result.setData(selectBeans);
        return result;
    }


    @RequestMapping(value = "/{adjustId}/getPriceAdjustReturnVOById", method = RequestMethod.GET)
    public Result<PriceAdjustReturnVO> getPriceAdjustReturnVOById(@ApiParam(name = "adjustId",value = "价格调整单id",required = true)@PathVariable
                                                                              Long adjustId){

        Result<PriceAdjustReturnVO> result = new Result<>();

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,priceAdjustService.getPriceAdjustReturnVOById(adjustId));
        return result;
    }

    @ApiOperation(value="取消价格调整单 | 开发者 翟伟 | 已完成", notes = "取消价格调整单 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "审批流程流程id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.GET)
    @ResponseBody
    public Result cancel(
            @PathVariable
                    Long id, @ApiIgnore UserVO userVO
    ) throws Exception {
        Result result = new Result<>();

        priceAdjustService.cancelPriceAdjust(id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    /**
     * 查询价格调整单审批列表
     * @param pageNo
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param orderCode
     * @param adjustManId
     * @param priceOrderId
     * @param statusRecom
     * @return
     */
    @ApiOperation(value="查询价格调整单审批列表 | 开发者 翟伟 | 已完成", notes = "查询价格调整单审批列表 | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/approvalFlow/priceAdjust", method = RequestMethod.GET)
    @ResponseBody
    public  Result<Page<List<PriceAdjustReturnVO> >> approvalFlow4PriceAdjust(
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize,
            @RequestParam(required = false)
            @ApiParam(name = "startTime", value = "开始时间", required = false)
                    Date startTime,
            @RequestParam(required = false)
            @ApiParam(name = "endTime", value = "结束时间", required = false)
                    Date endTime,
            @RequestParam(required = false)
            @ApiParam(name = "orderCode", value = "调整单号", required = false)
                    String orderCode,
            @RequestParam(required = false)
            @ApiParam(name = "adjustManId", value = "调整人员", required = false)
                    Long adjustManId,
            @RequestParam(required = false)
            @ApiParam(name = "priceOrderId", value = "价格清单", required = false)
                    Long priceOrderId,
            @RequestParam(required = false)
            @ApiParam(name = "statusRecom", value = "状态 （0-待审核；1-已完成；2-审核被驳回 3-取消)", required = false)
                    Integer statusRecom, @ApiIgnore UserVO userVO
     ){
        Result<Page<List<PriceAdjustReturnVO> >> result = new Result<>();

        Map<String,Object> map = new HashMap<>();
        if(startTime != null){
            map.put("startTime",startTime);
        }

        if(endTime != null){
            map.put("endTime", endTime);
        }

        if(!StringUtils.isEmpty(orderCode)){
            map.put("orderCode", orderCode);
        }

        if(adjustManId != null){
            map.put("adjustManId",adjustManId);
        }

        if(priceOrderId != null){
            map.put("priceOrderId", priceOrderId);
        }

        if(statusRecom != null){
            map.put("statusRecom", statusRecom);
        }

        map.put("enterpriseId",userVO.getEnterpriseId());


        Page page = new Page(pageNo, pageSize);
        List<PriceAdjustReturnVO> priceAdjustReturnVOS = priceAdjustService.queryPriceAdjust4Param(map,page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }
}
