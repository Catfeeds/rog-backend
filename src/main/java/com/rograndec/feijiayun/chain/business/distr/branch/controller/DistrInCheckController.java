package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInCheckService;
import com.rograndec.feijiayun.chain.business.distr.branch.valid.DistrInCheck2DetailValid;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2ListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.RequestParamForListVO;
import com.rograndec.feijiayun.chain.business.purchase.check.service.PurchaseCheckService;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.ConclusionVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @author sunteng
 *
 */
@Api(value = "distr_in_check",description = "配送管理-分店-配进入库-配进验收单接口服务")
@RestController
@RequestMapping("distr/in/check")
@Validated
public class DistrInCheckController {

    @Autowired
    private DistrInCheckService distrInCheckService;

    @Autowired
    PurchaseCheckService purchaseCheckService;

    @Autowired
    private UserComponent userComponent;

	@ApiOperation(value = "查询已验收列表", notes = "查询已验收列表 | 开发者 孙腾 | 已完成")
    @RequestMapping(value="/getDistrInCheckList", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "请求参数", required = true, dataType="RequestParamForListVO")})
    public Result<Page<List<DistrInCheck2ListVO>>> getDistrInCheckList(HttpSession session, @RequestParamValid @RequestBody RequestParamForListVO param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<DistrInCheck2ListVO>>> result = new Result<>();
        Page<List<DistrInCheck2ListVO>> page = new Page<>(param.getPageNo(),param.getPageSize());
        distrInCheckService.getDistrInCheckList(page,param,userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "查看验收详情", notes = "查看验收详情 | 开发者 孙腾 | 已完成")
    @RequestMapping(value="/getInCheckDetail/{id}", method=RequestMethod.GET)
    public Result<DistrInCheck2DetailVO> getInCheckDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<DistrInCheck2DetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInCheckService.getInCheckDetail(userVO,id));
        return result;
    }

    @ApiOperation(value = "验收", notes = "验收 | 开发者 孙腾 | 已完成")
    @RequestMapping(value="/getReceiveDetail2Check/{receiveId}", method=RequestMethod.GET)
    public Result<DistrInCheck2DetailVO> getReceiveDetail2Check(HttpSession session, @PathVariable("receiveId")Long receiveId) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<DistrInCheck2DetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInCheckService.getReceiveDetail2Check(userVO,receiveId));
        return result;
    }
	

	
	@ApiOperation(value = "保存配进验收单", notes = "保存配进验收单 | 开发者 孙腾 | 已完成")
    @RequestMapping(value="/saveDistrInCheck", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "inCheck2DetailVO", value = "请求参数", required = true, dataType="DistrInCheck2DetailVO")})
    public Result saveDistrInCheck(HttpSession session,@DistrInCheck2DetailValid @RequestBody DistrInCheck2DetailVO inCheck2DetailVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        distrInCheckService.saveDistrInCheck(userVO,inCheck2DetailVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "通过商品编码查询检验项目接口", notes = "通过商品编码查询检验项目接口 | 开发者 孙腾  | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckProject", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CheckProjectVO>> getCheckProject(HttpSession session,
                                                        @ApiParam(value = "商品编码", required = true) @RequestParam String code
    ) {
        Result<List<CheckProjectVO>> result = new Result<>();

        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getParentId();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getCheckProject(enterpriseId, code));

        return result;
    }

    @ApiOperation(value = "查询验收结论接口,查询处置措施接口,查询验收不合格原因接口 ", notes = "查询验收结论接口,查询处置措施接口,查询验收不合格原因接口   | 开发者 孙腾  | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getConclusion", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ConclusionVO>> getConclusion(HttpServletRequest request,
                                                    @ApiParam(value = "设置类型（1-不合格原因；3-处置措施；6-验收结论）", required = true) @RequestParam Long type
    ) {
        Result<List<ConclusionVO>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Long enterpriseId = loginUser.getParentId();
        Integer chainType = loginUser.getChainType().equals(ChainType.Headquarters.getCode()) ? 0 : 1;
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getConclusion(enterpriseId,chainType,type));

        return result;
    }



    @ApiOperation(value = "导出Excel", notes = "按照模版将配进验收单已验收单导出至Excel | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "盘点单id", required = true, paramType="path")})
    public void exportExcel(HttpServletResponse response, HttpSession session, @PathVariable("id")Long id) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String file = "配进验收单已验收单";
        String suffix = ".xlsx";
        String fileName = file + suffix;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();

        distrInCheckService.export(output,userVO,id);

        output.close();
        output.flush();
    }

    @ApiOperation(value = "查询当前企业下可用用户", notes = "查询当前企业下可用用户（总部不查询其门店用户） | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getRegister",method= RequestMethod.GET)
    public Result<List<SimpleUserVO>> getRegister(HttpSession session){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<SimpleUserVO>> result = new Result<>();
        List<SimpleUserVO> simpleUserVOList = userComponent.getSimpleUserVOByEnterpriseId(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,simpleUserVOList);
        return result;
    }
	
}
