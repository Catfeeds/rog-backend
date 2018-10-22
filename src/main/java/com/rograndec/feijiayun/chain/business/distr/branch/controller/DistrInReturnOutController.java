package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReturnOutService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInStorageService;
import com.rograndec.feijiayun.chain.business.distr.branch.valid.DistrInReturnOutCheckUpdatelValid;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer.DistrInReturnOutSearchParam;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer.DistrInReturnSearchParam;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.service.PurchaseReturnOutService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "distr_in_return_out",description = "配送管理-分店-配进退出出库单接口服务")
@RestController
@RequestMapping("distr/in/return/out")
@Validated
public class DistrInReturnOutController {

    @Autowired
    private DistrInReturnOutService distrInReturnOutService;

    @Autowired
    private PurchaseReturnOutService purchaseReturnOutService;

    @Autowired
    private DistrInStorageService distrInStorageService;

	@ApiOperation(value = "查询配进退出单列表", notes = "根据状态查询配进退出单列表 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/getDistrReturnOrderList", method=RequestMethod.POST)
    public Result<Page<List<ResponseDistrInReturnVO>>> getDistrReturnInOrderList(HttpSession session,
                   @ApiParam(value = "查询配进退出单列表条件", required = true)
                   @RequestBody DistrInReturnSearchParam distrInReturnParamVO,@ApiIgnore UserVO userVO){
        Result<Page<List<ResponseDistrInReturnVO>>> result=new Result<>();
            Page page = new Page(distrInReturnParamVO.getPageNo(), distrInReturnParamVO.getPageSize());
            List<ResponseDistrInReturnVO> distrReturnInOrderList = distrInReturnOutService.getDistrReturnInOrderList(distrInReturnParamVO, userVO, page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
            return result;

    }

    @ApiOperation(value = "查询货位", notes = "查询货位 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getShelf/{goodsId}/{lotNum}",method= RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "goodsId", value = "检索条件", required = true, paramType = "path")
            ,@ApiImplicitParam(name = "lotNum", value = "检索条件", required = true, paramType = "path")})
    public Result<List<StockShelfVO>> getShelf(HttpSession session, @PathVariable Long goodsId, @PathVariable String lotNum, @ApiIgnore UserVO userVO){
        Result<List<StockShelfVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInStorageService.getShelfByLotNum(userVO.getEnterpriseId(),goodsId,lotNum));
        return result;
    }

    @ApiOperation(value = "查询货位", notes = "查询货位 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getShelfs/{distrType}/{goodsId}/{lotNum}",method= RequestMethod.GET)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, paramType = "path")
                    ,@ApiImplicitParam(name = "lotNum", value = "批号", required = true, paramType = "path")
                    ,@ApiImplicitParam(name = "distrType", value = "0:总部配送;3:门店间调剂;4:直调配送", required = true, paramType = "path")
            }
            )
    public Result<List<StockLockRecordVO>> getShelfs(@PathVariable
                                                             Long goodsId, @PathVariable String lotNum, @PathVariable Integer distrType, @ApiIgnore UserVO userVO){
        Result<List<StockLockRecordVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInStorageService.getShelfByLotNum(userVO.getEnterpriseId(),goodsId,lotNum,userVO,distrType));
        return result;
    }


    @ApiOperation(value = "查询配进退出出库单列表", notes = "根据状态查询配进退出出库单列表 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/distrReturnOutOrders", method=RequestMethod.POST)
    public Result<Page<DistrInReturnOutPageTotalVO>>  getDistrReturnOutOrderList(
            HttpSession session,
            @ApiParam(value = "查询配进退出出库单列表条件", required = true)
                    @RequestBody(required = false) DistrInReturnOutSearchParam distrInReturnOutSearchParam,
            @ApiIgnore UserVO userVO
    ){
        Result<Page<DistrInReturnOutPageTotalVO>> result = new Result<>();
        Page page = new Page(distrInReturnOutSearchParam.getPageNo(), distrInReturnOutSearchParam.getPageSize());
        List<DistrInReturnOutPageVO> distrInReturnOuts = distrInReturnOutService.getDistrInReturnOuts(distrInReturnOutSearchParam,userVO, page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }
	
	@ApiOperation(value = "查询配进退出出库单明细列表", notes = "根据配进退出出库单ID查询配进退出出库单明细列表 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/distrReturnOutOrderDetails/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配后退回出出库单id"
                    , required = true, paramType="path")
    })
    public Result<DistrInReturnOutPageVO> getDistrReturnOutOrderDtlList(
            @PathVariable("id") Long id,@ApiIgnore UserVO userVO
    ){
        Result<DistrInReturnOutPageVO> result = new Result<>();
        DistrInReturnOutPageVO distrInReturnOutsAndDetails = distrInReturnOutService.getDistrInReturnOutsAndDetails(userVO,id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrInReturnOutsAndDetails);
        return result;
    }

    @ApiOperation(value = "初始化配后退回出库数据", notes = "初始化配后退回出库数据 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/initDistrReturnOutOrderDetais/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配后退回校验单id"
                    , required = true, paramType="path")
    })
    public Result<DistrInReturnOutPageVO> initDistrReturnOutOrderDetais(
            HttpSession session,
            @PathVariable("id") Long id,@ApiIgnore UserVO userVO
    ){
        Result<DistrInReturnOutPageVO> result = new Result<>();
        DistrInReturnOutPageVO distrInReturnOutsAndDetails4Return = distrInReturnOutService.getDistrInReturnOutsAndDetails4Return(userVO, id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrInReturnOutsAndDetails4Return);
        return result;
    }
	
	@ApiOperation(value = "保存和修改配进退出出库单", notes = "保存和修改配进退出出库单 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/saveDistrReturnOutOrder", method=RequestMethod.POST)
    public Result saveDistrReturnOutOrder(
            HttpSession session,
            @ApiParam(value = "配进退出出库单", required = true)
            @RequestBody
            @Valid
            @DistrInReturnOutCheckUpdatelValid
            DistrInReturnOutFormVO distrInReturnOutFormVO,@ApiIgnore UserVO userVO
    ) throws Exception {

        Result result = new Result<>();
        distrInReturnOutService.saveDistrInReturnOut(userVO,distrInReturnOutFormVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "新增配进退出出库单", notes = "新增配进退出出库单 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/addDistrReturnOutOrder", method=RequestMethod.POST)
    public Result<List<String>> addDistrReturnOutOrder(
            @ApiParam(value = "配进退出出库单", required = true)
            @RequestBody
            @Valid
                    DistrInReturnOutAddFormVO distrInReturnOutFormVO,@ApiIgnore UserVO userVO
    ) throws Exception {

        Result<List<String>> result = new Result<>();
        List<String> strings = distrInReturnOutService.save2Distr(userVO, distrInReturnOutFormVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,strings);
        return result;
    }

	@ApiOperation(value = "复核配进退出出库单", notes = "复核配进退出出库单 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/auditReturnOutOrder", method=RequestMethod.POST)
    public Result<List<String>> auditReturnOutOrder(
            HttpSession session,
            @ApiParam(value = "复核实体", required = true) @RequestBody DistrInReturnOutAuditFormVO distrInReturnOutAuditFormVO
            ,@ApiIgnore UserVO userVO
    ) throws Exception {

        Result<List<String>> result = new Result<>();
        List<String> audit = distrInReturnOutService.audit(userVO, distrInReturnOutAuditFormVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,audit);
        return result;
    }
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将配进退出出库单信息导出至Excel | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配后退回入库单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(HttpServletResponse response, @PathVariable("id") Long id,@ApiIgnore UserVO userVO) throws IOException {

        String name = "配进退出出库单";
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        // 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

        distrInReturnOutService.exportDetail(userVO,output, id);
        output.close();
        output.flush();
    }
	
	
	
}
