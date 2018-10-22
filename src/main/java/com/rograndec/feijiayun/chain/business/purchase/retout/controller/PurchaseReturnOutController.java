package com.rograndec.feijiayun.chain.business.purchase.retout.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.RequestPurchaseReturnParamVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.service.PurchaseReturnOutService;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.GoodsComponent;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * 功能描述：购进退出出库
 * Created by ST on 2017/9/14 17:51
 */

@Api(value = "purchase_return_out",description = "购进退出出库服务")
@RestController
@RequestMapping("purchase/returnOut")
@Validated
public class PurchaseReturnOutController {
    /**
     * 1.查看订单明细和配货/结算
     * 2.出库
     * 3.查询货位
     * 4.查询结算单位
     * 5.复核
     * 6.导出
     * 7.打印
     * 8.查询税率
     * 9.供货单位查询
     *
     */

    @Autowired
    private PurchaseReturnOutService purchaseReturnOutService;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private GoodsComponent goodsComponent;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @ApiOperation(value = "查询购进退出出库list", notes = "查询购进退出出库list | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getReturnOutListByParam",method= RequestMethod.GET)
    public Result<Page<List<ResponsePurchaseReturnOutVO>>> getReturnOutListByParam(HttpSession session, RequestPurchaseReturnParamVO paramVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<ResponsePurchaseReturnOutVO>>> result = new Result<>();

        Page<List<ResponsePurchaseReturnOutVO>> page = new Page<>();
        Integer pageNo = paramVO.getPageNo();
        Integer pageSize = paramVO.getPageSize();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Integer type = Optional.ofNullable(paramVO.getType()).orElse(0);
        paramVO.setEnterpriseId(userVO.getEnterpriseId());
        if(type == 1){
        //1/购进退出出库待复核
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getReturnOutListByParam(userVO,paramVO,page));
        } else if(type == 0){
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getReturnOutListForOutBound(paramVO,page));
        } else {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }
        return result;
    }

    @ApiOperation(value = "查询购进退出出库详情", notes = "查询购进退出出库详情  | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPurchaseReturnDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "购进退出单ID", required = true, paramType = "path")})
    public Result<ResponsePurchaseReturnDetailVO> getPurchaseReturnDetail(@PathVariable Long id, @ApiIgnore UserVO userVO){
        Result<ResponsePurchaseReturnDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getPurchaseReturnDetail(id,userVO));
        return result;
    }

    @ApiOperation(value = "查询购进退出出库 待复核、已完成的详情", notes = "查询购进退出出库 待复核、已完成的详情  | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPurchaseReturnOutDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "购进退出出库单ID", required = true, paramType = "path")})
    public Result<ResponsePurchaseReturnOutDetailVO> getPurchaseReturnOutDetail(@PathVariable Long id){
        Result<ResponsePurchaseReturnOutDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getPurchaseReturnOutDetail(id));
        return result;
    }

    @ApiOperation(value = "保存购进退出出库单信息", notes = "保存购进退出出库单信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateOutStock",method= RequestMethod.POST)
    @ApiImplicitParam(name = "purchaseReturnOutInfoVO", value = "出库", required = true, dataType = "RequestPurchaseReturnOutInfoVO")
    public Result updateOutStock(HttpSession session,@RequestBody RequestPurchaseReturnOutInfoVO purchaseReturnOutInfoVO) throws Exception {
        Result result = new Result();
        UserVO userVO = (UserVO) session.getAttribute("user");
        purchaseReturnOutService.updateOutStock(purchaseReturnOutInfoVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "查询出库人员信息", notes = "查询出库人员信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getReturnManInfo",method= RequestMethod.GET)
    public Result<List<SimpleUserVO>> getReturnManInfo(HttpSession session){
        Result<List<SimpleUserVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,userComponent.getSimpleUserVOByEnterpriseId(userVO.getEnterpriseId(),1));
        return result;
    }


    @ApiOperation(value = "查询货位", notes = "查询货位 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getShelf/{goodsId}/{lotNum}",method= RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "goodsId", value = "检索条件", required = true, paramType = "path")
            ,@ApiImplicitParam(name = "lotNum", value = "检索条件", required = true, paramType = "path")})
    public Result<List<StockShelfVO>> getShelf(HttpSession session, @PathVariable Long goodsId, @PathVariable String lotNum){
        Result<List<StockShelfVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getShelfByLotNum(userVO.getEnterpriseId(),goodsId,lotNum));
        return result;
    }

    @ApiOperation(value = "复核", notes = "复核 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateReCheck",method= RequestMethod.POST)
    public Result updateReCheck(HttpSession session,@RequestBody RequestCheckVO requestCheckVO) throws Exception {
        Result result = new Result();
        UserVO userVO = (UserVO) session.getAttribute("user");
        purchaseReturnOutService.updateReCheck(requestCheckVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "导出", notes = "导出 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void exportExcel(HttpSession session, HttpServletResponse response,@PathVariable Long id) throws IOException {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "购进退出出库单";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name,"UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        purchaseReturnOutService.exportExcel(output,id);
        output.close();
        output.flush();
    }

    @ApiOperation(value = "查询税率", notes = "查询税率 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getTax",method= RequestMethod.GET)
    public Result<List<GoodsTaxRateVO>> getTax(HttpSession session){
        Result<List<GoodsTaxRateVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsComponent.getGoodsTaxRateVO(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus()));
        return result;
    }
    @ApiOperation(value = "查看当前企业一些配置控制信息", notes = "查看当前企业一些配置控制信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/checkCode",method= RequestMethod.GET)
    public Result<ManageConfig> checkCode(HttpSession session){
        Result<ManageConfig> result = new Result<ManageConfig>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,manageConfig);
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "新增购进退出出库单", notes = "新增购进退出出库单 | 开发者 马东 | 已完成")
    @RequestMapping(value="/savePurchaseReturnOut",method= RequestMethod.POST)
    public Result<String> savePurchaseReturnOut(HttpSession session,
           @RequestBody PurchaseReturnOutInfoVO purchaseReturnOutInfoVO){
        Result<String> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            purchaseReturnOutService.savePurchaseReturnOut(purchaseReturnOutInfoVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"新增购进退出出库单成功");
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;

    }

    @ApiOperation(value = "获取默认的退货人员", notes = "获取默认的退货人员 | 开发者 马东 | 已完成")
    @RequestMapping(value = "getDefaultReturnMan",method = RequestMethod.GET)
    public Result<ManageConfig> getDefaultReturnMan(HttpSession session){
        Result<ManageConfig> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getDefaultReturnMan(loginUser));
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据入库细单id返回待出库的对象VO", notes = "根据入库细单id返回待出库的对象VO | 开发者 马东 | 已完成")
    @RequestMapping(value = "getPurchaseReturnOutFromInStorage",method = RequestMethod.POST)
    public Result<PurchaseReturnOutInfoVO> getPurchaseReturnOutFromInStorage(HttpSession session,
           @ApiParam(value = "采购入库单ID",required = true) @RequestBody List<Long> inStorageDtlS){
        Result<PurchaseReturnOutInfoVO> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseReturnOutService.getPurchaseReturnOutFromInStorage(loginUser,inStorageDtlS));
        }catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}