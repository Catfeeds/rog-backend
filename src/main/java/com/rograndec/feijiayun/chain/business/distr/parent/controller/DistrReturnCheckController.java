package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnCheckService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.check.service.PurchaseCheckService;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.ConclusionVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author lizhongyi
 */
@Api(value = "distr_return_check", description = "配送管理-总部-配后退货-配进验收单接口服务")
@RestController
@RequestMapping("distr/return/check")
@Validated
public class DistrReturnCheckController {

    private static final Log logger = LogFactory.getLog(DistrReturnCheckController.class);

    @Autowired
    DistrReturnCheckService distrReturnCheckService;
    @Autowired
    PurchaseCheckService purchaseCheckService;

    @ApiOperation(value = "点击验收按钮后的配进验收信息表头数据", notes = "点击验收按钮后的配进验收信息表头数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClickCheckHead", method = RequestMethod.GET)
    @ResponseBody
    public Result<ClickCheckHeadVO> getClickCheckHead(HttpSession session,
                                                      @ApiParam(value = "配进收货单ID", required = true) @RequestParam Long id
    ) {
        Result<ClickCheckHeadVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnCheckService.getClickCheckHead(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("点击验收按钮后的配进验收信息表头数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "点击验收按钮后的配进验收信息配进验收明细数据", notes = "点击验收按钮后的配进验收信息配进验收明细数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/ClickCheckDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ClickCheckDetailVO>> ClickCheckDetail(HttpSession session,
                                                             @ApiParam(value = "配进收货单ID", required = true) @RequestParam Long id
    ) {
        Result<List<ClickCheckDetailVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnCheckService.ClickCheckDetail(enterpriseId, id));
        } catch (Exception e) {
            logger.error("点击验收按钮后的配进验收信息配进验收明细数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过商品编码查询检验项目接口", notes = "通过商品编码查询检验项目接口 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckProject", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CheckProjectVO>> getCheckProject(HttpSession session,
                                                        @ApiParam(value = "商品编码", required = true) @RequestParam String code
    ) {
        Result<List<CheckProjectVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getCheckProject(enterpriseId, code));
        } catch (Exception e) {
            logger.error("通过商品编码查询检验项目接口错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询验收结论接口,查询处置措施接口,查询验收不合格原因接口 ", notes = "查询验收结论接口,查询处置措施接口,查询验收不合格原因接口   | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getConclusion", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ConclusionVO>> getConclusion(HttpServletRequest request,
                                                    @ApiParam(value = "设置类型（1-不合格原因；3-处置措施；6-验收结论）", required = true) @RequestParam Long type
    ) {
        Result<List<ConclusionVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long enterpriseId = loginUser.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getConclusion(enterpriseId, loginUser.getChainType(),type));
        } catch (Exception e) {
            logger.error("查询验收结论接口,查询处置措施接口,查询验收不合格原因接口错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取验收人员", notes = "获取验收人员 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReceiver", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SimpleUserVO>> getReceiver(HttpServletRequest request) {
        Result<List<SimpleUserVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getChecker(loginUser));
        } catch (Exception e) {
            logger.error("获取验收人员错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存配进验收单据", notes = "保存配进验收单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDistrReturnCheck", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveDistrReturnCheck(HttpServletRequest request,
                                               @ApiParam(value = "保存配进验收单据", required = true) @RequestBody SaveDistrReturnCheckVO saveDistrReturnCheckVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            distrReturnCheckService.saveDistrReturnCheck(loginUser, saveDistrReturnCheckVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存配进验收单据成功");

        } catch (BusinessException e) {
            logger.error("保存配进验收单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存配进验收单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页获取配后退回验收已验收信息", notes = "分页获取配后退回验收已验收信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrReturnCheckPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<DistrReturnCheckVO>>> getDistrReturnCheckPage(HttpServletRequest request,
                                                                          @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                          @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                          @ApiParam(value = "适用范围（0-已验收   1-待入库）", required = true) @RequestParam Long type,
                                                                          @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                          @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                          @ApiParam(value = "要货单位编码", required = false) @RequestParam(required = false) String requestUnitCode,
                                                                          @ApiParam(value = "要货单位名称", required = false) @RequestParam(required = false) String requestUnitName,
                                                                          @ApiParam(value = "配后退回验收单号", required = false) @RequestParam(required = false) String code,
                                                                          @ApiParam(value = "配货类型", required = false) @RequestParam(required = false) Integer distrType,
                                                                          @ApiParam(value = "验收人员1", required = false) @RequestParam(required = false) String checkerName,
                                                                          @ApiParam(value = "验收人员2", required = false) @RequestParam(required = false) String secondCheckerName,
                                                                          @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                          @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<DistrReturnCheckVO>>> result = new Result<Page<List<DistrReturnCheckVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<DistrReturnCheckVO> distrReturnCheckVoList = distrReturnCheckService
                    .getDistrReturnCheckPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime, requestUnitCode, requestUnitName, code, distrType,
                            checkerName, secondCheckerName, orderName, orderType, type);
            page.setResult(distrReturnCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取配后退回验收已验收信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }




    @ApiOperation(value = "导出配后退回验收单信息", notes = "导出配后退回验收单信息 | 开发者:zeshi.szeshi.sun | 已联调 ")
    @RequestMapping(value = "/exportDistrReturnCheck/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的已验收单据ID", required = true, dataType = "Long", paramType = "path")
    public void exportDistrReturnCheck(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName() + "配后退回验收单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            ExportDistrReturnCheckVO exportDistrReturnCheckVo = distrReturnCheckService.exportDistrReturnCheck(loginUser,id);
            distrReturnCheckService.exportExcel(output,exportDistrReturnCheckVo,loginUser);
        } catch (Exception e) {
            logger.error("导出配后退回验收单信息错误:" + e.getMessage(), e);
        }
    }

    @ApiOperation(value = "查看已验收配进验收信息表头数据", notes = "查看已验收配进验收信息表头数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrReturnCheckHead", method = RequestMethod.GET)
    @ResponseBody
    public Result<DistrReturnCheckHeadVO> getDistrReturnCheckHead(HttpSession session,
                                                                  @ApiParam(value = "配进验收单ID", required = true) @RequestParam Long id
    ) {
        Result<DistrReturnCheckHeadVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnCheckService.getDistrReturnCheckHead(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("查看已验收配进验收信息表头数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看已验收配进验收明细数据", notes = "查看已验收配进验收明细数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getDistrReturnCheckDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<DistrReturnCheckDetailVO>> getDistrReturnCheckDetail(HttpSession session,
                                                             @ApiParam(value = "配进验收单ID", required = true) @RequestParam Long id
    ) {
        Result<List<DistrReturnCheckDetailVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, distrReturnCheckService.getDistrReturnCheckDetail(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("查看已验收配进验收明细数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

}
