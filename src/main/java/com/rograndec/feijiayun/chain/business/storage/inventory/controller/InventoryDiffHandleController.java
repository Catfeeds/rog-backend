package com.rograndec.feijiayun.chain.business.storage.inventory.controller;

import com.rograndec.feijiayun.chain.business.storage.inventory.service.InventoryOrderService;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryForDiffVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryForOrderDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryForDiffDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.RequestParamForDiffListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO2;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.RequestParamForHadRegisterListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author sunteng
 *
 */
@Api(value = "storage_inventory_inventoryDiffHandle",description = "储存管理-盘点管理-差异处理")
@RestController
@RequestMapping("storage/inventory/inventoryDiffHandle")
@Validated
public class InventoryDiffHandleController {

    @Autowired
    private InventoryOrderService inventoryOrderService;

    @ApiOperation(value = "处理", notes = "处理 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getInventoryOrderDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForOrderDetailVO> getInventoryOrderDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForOrderDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getInventoryDiffOrderDetail(userVO,id));
        return result;
    }

    @ApiOperation(value = "查询待处理的差异处理单列表", notes = "查询待处理的差异处理单列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHadRegisterInventoryOrderList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForHadRegisterListVO")})
    public Result<Page<List<InventoryForRegisterVO2>>> getHadRegisterInventoryOrderList(HttpSession session, @RequestParamValid @RequestBody RequestParamForHadRegisterListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<InventoryForRegisterVO2>>> result = new Result<>();
        Page<List<InventoryForRegisterVO2>> page = new Page<>(param.getPageNo(),param.getPageSize());
        param.setDiff(0);
        inventoryOrderService.getHadRegisterInventoryOrderList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }
    @ApiOperation(value = "查询已处理差异处理单列表", notes = "查询待审核,审核通过(已处理),审核拒绝,差异处理单列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHadHandlerInventoryList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForDiffListVO")})
    public Result<Page<List<InventoryForDiffDetailVO>>> getHadHandlerInventoryList(HttpSession session, @RequestParamValid @RequestBody RequestParamForDiffListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<InventoryForDiffDetailVO>>> result = new Result<>();
        Page<List<InventoryForDiffDetailVO>> page = new Page<>(param.getPageNo(),param.getPageSize());
        inventoryOrderService.getHadHandlerInventoryList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }
    @ApiOperation(value = "查看待处理详情", notes = "查看待处理详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getWaitHandleDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForRegisterVO2> getWaitHandleDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForRegisterVO2> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getHadRegisterInventoryOrderDetail(userVO,id));
        return result;
    }

    @ApiOperation(value = "查看已处理详情", notes = "查看已处理详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getHadHandleDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForDiffDetailVO> getHadHandleInventoryOrderDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForDiffDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getHadHandleInventoryOrderDetail(userVO,id));
        return result;
    }


    @ApiOperation(value = "保存、修改盘点差异处理单", notes = "保存、修改盘点差异处理单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/saveInventoryDiffHandleOrder", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "inventoryForDiffVO", value = "请求参数", required = true, dataType="InventoryForDiffVO")})
    public Result saveInventoryDiffHandleOrder(HttpSession session,@RequestBody InventoryForDiffVO inventoryForDiffVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        inventoryOrderService.saveInventoryDiffHandleOrder(userVO,inventoryForDiffVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


	
	@ApiOperation(value = "导出Excel", notes = "按照模版将盘点差异处理单导出至Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "差异处理单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(
            HttpSession session,
            @PathVariable("id")Long id,
            HttpServletResponse response
    ) throws IOException {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "差异处理";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        inventoryOrderService.export4HadHandleInventory(output,id,userVO);

        output.close();
        output.flush();
    }
	
}
