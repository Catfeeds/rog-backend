package com.rograndec.feijiayun.chain.business.retail.royalty.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.retail.royalty.service.RoyaltyService;
import com.rograndec.feijiayun.chain.business.retail.royalty.valid.ExtractCheck;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.*;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.ResponseSaleMan;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
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
@RequestMapping("royalty/")
@Validated
public class RoyaltyController {


    @Autowired
    private RoyaltyService royaltyService;

    @Autowired
    private UserManagerService userManagerService;

    @ApiOperation(value = "待提成列表 | 开发者 翟伟 | 开发中", notes = "待提成列表 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/waits",method= RequestMethod.GET)
    public Result<ResponseNoTotalVO> waits(
            @RequestParam(required = false)
            @ApiParam(name = "chainType", value = " 企业类型：0-总部；1-自营店；2-加盟店", required = false)
                    Integer chainType,
            @RequestParam(required = false)
            @ApiParam(name = "code", value = " 门店编码", required = false)
                    String code,
            @RequestParam(required = false)
            @ApiParam(name = "name", value = " 门店名称", required = false)
                    String name,
            @RequestParam(required = false)
            @ApiParam(name = "clerkId", value = " 营业人员", required = false)
                    String clerkId,
            @RequestParam(required = false)
            @ApiParam(name = "startTime", value = "开始时间", required = false)
                    Date startTime,
            @RequestParam(required = false)
            @ApiParam(name = "endTime", value = "结束时间", required = false)
                    Date endTime
            ,@ApiIgnore UserVO userVO
    ){
        Result<ResponseNoTotalVO> result = new Result<>();

        List<ResponseNoSaleRoyaltyTotalVO> noRoyaltys = royaltyService.getNoRoyaltys(
                chainType
                , code
                , name
                , clerkId
                , startTime
                , endTime
                , userVO
        );

        ResponseNoTotalVO responseNoTotalVO = null;
        if(!CollectionUtils.isEmpty(noRoyaltys)){
            responseNoTotalVO = ResponseNoTotalVO.getResponseNoTotalVO(
                    noRoyaltys
            );

        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,responseNoTotalVO);
        return result;
    }

    @ApiOperation(value = "导出提成详情 | 开发者 翟伟 | 已完成", notes = "导出提成详情 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/exportExcel/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提成id"
                    , required = true, paramType="path")
    })
    @ResponseBody
    public void exportExcel(HttpServletResponse response,
                            @PathVariable
                                    Long id,@ApiIgnore UserVO userVO
    ) throws IOException {

        String chainType = "";
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            chainType = "总部";
        }else {
            chainType = "分部";
        }
        String file = chainType + "提成单";
        String suffix = ".xlsx";
        String fileName = file+suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));

        //输出Excel文件
        OutputStream output = response.getOutputStream();


        royaltyService.exportExcel(output,userVO, id);

        output.close();
        output.flush();
    }

    @ApiOperation(value = "已提成列表 | 开发者 翟伟 | 开发中", notes = "已提成列表 | 开发者 翟伟 | 已完成")
    @RequestMapping(value="/alreadys",method= RequestMethod.GET)
    public Result<Page> alreadys(
            @RequestParam(required = true)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = true)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize,
            @RequestParam(required = false)
            @ApiParam(name = "startTime", value = "开始时间", required = false)
                    Date startTime,
            @RequestParam(required = false)
            @ApiParam(name = "endTime", value = "结束时间", required = false)
                    Date endTime,
            @RequestParam(required = false)
            @ApiParam(name = "clerkId", value = "营业人员ID", required = false)
                    Long clerkId,@ApiIgnore UserVO userVO
    ){
        Result<Page> result = new Result<>();
        Page page = new Page(pageNo, pageSize);
        startTime = StartAndEndDate.getStartTime(startTime);
        endTime = StartAndEndDate.getEndTime(endTime);
        ResponseSaleTotalVO hasSaleRoyaltys = royaltyService.getHasSaleRoyaltys(userVO, page, startTime, endTime,clerkId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "提成明细查看 | 开发者 翟伟 | 开发中", notes = "提成明细查看 | 开发者 翟伟 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提成id"
                    , required = true, paramType="path")
    })
    @RequestMapping(value="/detail/{id}",method= RequestMethod.GET)
    public Result<ResponseSaleRoyaltyVO> detail(
            @PathVariable
                    Long id,@ApiIgnore UserVO userVO
    ){
        Result<ResponseSaleRoyaltyVO> result = new Result<>();


        ResponseSaleRoyaltyVO hasSaleRoyaltys = royaltyService.getHasSaleRoyalty(userVO, id);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,hasSaleRoyaltys);
        return result;
    }

//    @ApiOperation(value = "导出提成详情 | 开发者 翟伟 | 开发中", notes = "导出提成详情 | 开发者 翟伟 | 已完成")
//    @RequestMapping(value="/export",method= RequestMethod.GET)
//    public Result export(
//    ){
//        Result result = new Result<>();
//        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
//        return result;
//    }

    @ApiOperation(value = "提成 | 开发者 翟伟 | 已联调", notes = "提成 | 开发者 翟伟 | 已联调")
    @RequestMapping(value="/extract",method= RequestMethod.POST)
    public Result extract(
            @ApiParam(name="saveOrUpdateSaleRoyaltyVOs", value="提成信息实体" , required=true)
            @RequestBody @Valid @ExtractCheck
            List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOs,@ApiIgnore UserVO userVO
    ){
    	Result result = new Result<>();
    	try {
	        royaltyService.saveRoyaltys(saveOrUpdateSaleRoyaltyVOs,userVO);
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
    	}catch (BusinessException e) {
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
		}
        return  result;
    }

    @ApiOperation(value = "获取提成人员列表 | 开发者 翟伟 | 已联调", notes = "根据登录人当前的企业id获取提成人员列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/royaltyMans", method = RequestMethod.GET)
    public Result<List<SimpleUserVO>> royaltyMans(
            HttpSession session
    ) {
        Result<List<SimpleUserVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("parentId",userVO.getEnterpriseId());
        List<User> users = userManagerService.findUserById(map);
        List<SimpleUserVO> simpleUserVOs = SimpleUserVO.getSimpleUserVOs(users);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, simpleUserVOs);
        return result;
    }

    @ApiOperation(value = "获取营业人员列表 | 开发者 翟伟 | 已联调", notes = "根据登录人当前的企业id获取营业人员列表 | 开发者 翟伟 | 已联调")
    @RequestMapping(value = "/saleMans", method = RequestMethod.GET)
    public Result<List<ResponseSaleMan>> saleMans(
            HttpSession session
    ) {
        Result<List<ResponseSaleMan>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("parentId",userVO.getEnterpriseId());
        List<ResponseSaleMan> saleMans = royaltyService.getSaleMans(map);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, saleMans);
        return result;
    }
    
}
