package com.rograndec.feijiayun.chain.business.storage.inventory.controller;

import com.rograndec.feijiayun.chain.business.storage.inventory.service.InventoryOrderService;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryForPostDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryPostVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.RequestParamForPostListVO;
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


/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "storage_inventory_inventoryPost",description = "储存管理-盘点管理-盘点过账")
@RestController
@RequestMapping("storage/inventory/inventoryPost")
@Validated
public class InventoryPostController {

    @Autowired
    private InventoryOrderService inventoryOrderService;


	@ApiOperation(value = "查询查询待审核(status = 5),审核通过(已过账/已完成 status = 3),审核拒绝(status = 6)盘点已过账单列表", notes = "查询查询待审核(status = 5),审核通过(已过账/已完成 status = 3),审核拒绝(status = 6)盘点已过账单列表 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getInventoryPostOrderList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForPostListVO")})
    public Result<Page<InventoryPostVO>> getInventoryPostOrderList(HttpSession session, @RequestParamValid @RequestBody RequestParamForPostListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<InventoryPostVO>> result = new Result<>();
        Page<InventoryPostVO> page = new Page<>(param.getPageNo(),param.getPageSize());
        inventoryOrderService.getInventoryPostOrderList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "查看盘点过账详情", notes = "查看盘点过账详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPostDetail/{id}", method=RequestMethod.GET)
    public Result<InventoryForPostDetailVO> getPostDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForPostDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getPostDetail(userVO,id));
        return result;
    }



    @ApiOperation(value = "过账", notes = "过账 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPostDetail2Post/{id}", method=RequestMethod.GET)
    public Result<InventoryForPostDetailVO> getPostDetail2Post(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<InventoryForPostDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,inventoryOrderService.getPostDetail2Post(userVO,id));
        return result;
    }

	
	@ApiOperation(value = "保存盘点过账单", notes = "保存盘点过账单 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/saveInventoryPostOrder", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "detailVO", value = "请求参数", required = true, dataType="InventoryForPostDetailVO")})
    public Result saveInventoryPostOrder(HttpSession session,@RequestBody InventoryForPostDetailVO detailVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        inventoryOrderService.saveInventoryPostOrder(userVO,detailVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }
	

    @ApiOperation(value = "导出Excel", notes = "按照模版将盘点过账单导出至Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "盘点过账单id"
                    , required = true, paramType="path")
    })
    public void exportExcel(
            HttpSession session,
            @PathVariable("id")Long id,
            HttpServletResponse response
    ) throws IOException {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "盘点过账";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        inventoryOrderService.export4PostDetail(output,id,userVO);

        output.close();
        output.flush();
    }
	
}
