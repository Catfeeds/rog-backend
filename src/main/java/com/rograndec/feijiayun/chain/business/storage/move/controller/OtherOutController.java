package com.rograndec.feijiayun.chain.business.storage.move.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherInService;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherOutService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.*;
import com.rograndec.feijiayun.chain.business.storage.move.vo.transfer.OtherOutListParam;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author: sulei
 *
 */
@Api(value = "storage_move_otherOut",description = "储存管理-货物移动-其它出库")
@RestController
@RequestMapping("storage/move/otherOut")
@Validated
public class OtherOutController {

    @Autowired
    private OtherOutService otherOutService;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private OtherInService otherInService;

        @ApiOperation(value = "查询出库人员/复核人员列表 | 开发者 翟伟 | 已联调", notes = "查询出库人员/复核人员列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/outOrAuditMans", method=RequestMethod.GET)
    public Result<List<SimpleUserVO>>  getAuditManList(@ApiIgnore UserVO userVO){


        Result<List<SimpleUserVO>> result = new Result();
        List<SimpleUserVO> simpleUserVOByEnterprise = userComponent.getSimpleUserVOByEnterpriseId(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,simpleUserVOByEnterprise);
        return result;
    }

    @ApiOperation(value = "根据来源单位查找出对应的来源单位详细名称 | 开发者 翟伟 | 已联调", notes = "根据来源单位查找出对应的来源单位详细名称 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/srcUnit", method=RequestMethod.GET)
    public Result<List<SelectBeanWithCode>> getExcessSaleGoodsList(HttpServletRequest request,
                                                                   @ApiParam(value = "0-部门；1-总部；2-门店；3-供货单位", required = true) @RequestParam Integer srcUnitType){
        Result<List<SelectBeanWithCode>> result = new Result<List<SelectBeanWithCode>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<SelectBeanWithCode> list = otherInService.getSrcUnit(loginUser,srcUnitType);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询商品库存列表 | 开发者 翟伟 | 已联调", notes = "查询商品库存列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/goodsStocks", method=RequestMethod.GET)
    public Result<List<OtherOutGoodsPageVO>> getGoodsStockList(
            @RequestParam(required = false)
            @ApiParam(name = "name", value = "搜索条件", required = false)
                    String name,@ApiIgnore UserVO userVO
    ){

        Result<List<OtherOutGoodsPageVO>> result = new Result();

        List<OtherOutGoodsPageVO> goods = otherOutService.getGoods(userVO, name);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goods);

        return result;

    }
	
	@ApiOperation(value = "保存其它出库单 | 开发者 翟伟 | 已联调", notes = "保存其它出库单 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public Result saveOtherOutOrder(
            @ApiParam(name="otherOutFormVO", value="提成信息实体" , required=true)
            @RequestBody @Valid
            OtherOutFormVO otherOutFormVO,@ApiIgnore UserVO userVO
    ) throws Exception {
        Result result = new Result();

        otherOutService.save(userVO,otherOutFormVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }
	
	@ApiOperation(value = "查询其它出库单列表 | 开发者 翟伟 | 已联调", notes = "查询其它出库单列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/otherOutOrders", method=RequestMethod.GET)
    public Result<Page<OtherOutPageTotalVO>> getOtherOutOrderList(
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
            @ApiParam(name = "code", value = "出库单号", required = false)
                    String code,
            @RequestParam(required = false)
            @ApiParam(name = "outManName", value = "出库人员名称", required = false)
                    String outManName,
            @RequestParam(required = false)
            @ApiParam(name = "outType", value = "出库类型", required = false)
                    Integer outType,
            @ApiParam(value = "按某一列排序(outate或者code)", required = false)
            @RequestParam(required=false) String order,
            @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false)
            @RequestParam(required=false) String sort,@ApiIgnore UserVO userVO
    ){
        Result<Page<OtherOutPageTotalVO>> result = new Result();

        OtherOutListParam param = new OtherOutListParam(
                startTime
                ,endTime
                ,code
                ,outManName
                ,outType
                ,userVO
                ,order
                ,sort
        );

        Page page = new Page(pageNo, pageSize);
        List<OtherOut> otherOuts = otherOutService.getOtherOuts(param, page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;

    }
	
	@ApiOperation(value = "查询其它出库明细列表 | 开发者 翟伟 | 已联调", notes = "根据其它出库单ID查询其它出库明细列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/otherOutOrderDtl/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "其他出库id"
                    , required = true, paramType="path")
    })
    public Result<OtherOutPageVO> getOtherOutOrderDtlList(
            @PathVariable
                    Long id
    ){
        Result<OtherOutPageVO> result = new Result();

        OtherOutPageVO otherOutPageVO = otherOutService.getOtherOutPageVO(id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,otherOutPageVO);
        return result;
    }

    @ApiOperation(value = "查询货位和库存 | 开发者 翟伟 | 已联调", notes = "根据批号id和商品id查询货位和库存 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/stockAndShelfs/{lotId}/{goodsId}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lotId", value = "批号id"
                    , required = true, paramType="path"),
            @ApiImplicitParam(name = "goodsId", value = "商品id"
                    , required = true, paramType="path")
    })
    public Result<List<StockAndShelfPageVO>> stockAndShelfs(
            @PathVariable
                    Long lotId,
            @PathVariable
                    Long goodsId,@ApiIgnore UserVO userVO
    ){
        Result<List<StockAndShelfPageVO>> result = new Result();


        List<StockAndShelfPageVO> stockAndShelfPageVOs = otherOutService.getStockAndShelfPageVOs(userVO, lotId, goodsId);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,stockAndShelfPageVOs);
        return result;
    }
	
	@ApiOperation(value = "导出Excel | 开发者 翟伟 | 已联调", notes = "按照模版将其它出库单导出至Excel | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "其他出库id"
                    , required = true, paramType="path")
    })
    public void exportExcel(
            HttpServletResponse response,
            @PathVariable
                    Long id,@ApiIgnore UserVO userVO
    ) throws IOException {
        String file = "其它出库单";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = response.getOutputStream();

        otherOutService.export(output,id,userVO);

        output.close();
        output.flush();
    }
	
}
