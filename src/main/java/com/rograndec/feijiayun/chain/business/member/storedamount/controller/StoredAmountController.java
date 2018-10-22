package com.rograndec.feijiayun.chain.business.member.storedamount.controller;

import com.rograndec.feijiayun.chain.business.member.info.service.MemberInfoService;
import com.rograndec.feijiayun.chain.business.member.storedamount.service.StoredAmountService;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.RequestStoredAmountVO;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "memberStored", description = "会员管理-储值管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/storedamount")
public class StoredAmountController {

    private static final Log logger = LogFactory.getLog(StoredAmountController.class);

    @Autowired
    private StoredAmountService storedAmountService;

    @ApiOperation(value = "按条件搜索查询会员信息", notes = "按条件搜索查询会员信息[会员储值状态&&当前页面只显示状态正常的&&只显示类型是储值卡+积分加储卡] | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMemberInfoPage", method = RequestMethod.GET)
    public Result<Page<StoredAmountTotalVO>> getMemberInfoPage(HttpServletRequest request,
                                                               @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                               @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                               @ApiParam(value = "会员卡号/手机号/姓名", required = false) @RequestParam(required = false) String param,
                                                               @ApiParam(value = "会员卡类型", required = false) @RequestParam(required = false) Long cardTypeId,
                                                               @ApiParam(value = "会员卡级别", required = false) @RequestParam(required = false) Long cardLevelId,
                                                               @ApiParam(value = "发卡起始时间", required = false) @RequestParam(required = false) Date startSendCardTime,
                                                               @ApiParam(value = "发卡终止时间", required = false) @RequestParam(required = false) Date endSendCardTime,
                                                               @ApiParam(value = "发卡门店类型(1-自营店 2-加盟店)", required = false) @RequestParam(required = false) Integer chainType,
                                                               @ApiParam(value = "发卡门店编码", required = false) @RequestParam(required = false) String sendCardStorerCode,
                                                               @ApiParam(value = "发卡门店名称", required = false) @RequestParam(required = false) String sendCardStorerName) {
        Result<Page<StoredAmountTotalVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = storedAmountService.getMemberInfoPage(page, param, cardTypeId, cardLevelId, startSendCardTime, endSendCardTime, chainType, sendCardStorerCode, sendCardStorerName, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("按条件搜索查询会员信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "导出储值管理信息", notes = "导出储值管理信息 | 开发者 蓝兴建 | 已联调 ")
    @RequestMapping(value = "/exportStoredAmount", method = RequestMethod.GET)
    public void Export(HttpServletRequest request, HttpServletResponse response,
                       @ApiParam(value = "会员卡号/手机号/姓名", required = false) @RequestParam(required = false) String param,
                       @ApiParam(value = "会员卡类型", required = false) @RequestParam(required = false) Long cardTypeId,
                       @ApiParam(value = "会员卡级别", required = false) @RequestParam(required = false) Long cardLevelId,
                       @ApiParam(value = "发卡起始时间", required = false) @RequestParam(required = false) Date startSendCardTime,
                       @ApiParam(value = "发卡终止时间", required = false) @RequestParam(required = false) Date endSendCardTime,
                       @ApiParam(value = "发卡门店类型(1-自营店 2-加盟店)", required = false) @RequestParam(required = false) Integer chainType,
                       @ApiParam(value = "发卡门店编码", required = false) @RequestParam(required = false) String sendCardStorerCode,
                       @ApiParam(value = "发卡门店名称", required = false) @RequestParam(required = false) String sendCardStorerName) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "会员储值管理单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<StoredAmountPageVO> list = storedAmountService.exportStoredAmount(param, cardTypeId, cardLevelId, startSendCardTime, endSendCardTime, chainType, sendCardStorerCode, sendCardStorerName, loginUser.getEnterpriseId());
            storedAmountService.exportExcel(output, list, loginUser);
        } catch (Exception e) {
            logger.error("导出储值信息错误:" + e.getMessage(), e);
        }
    }

    @ApiOperation(value = "会员储值[充值/扣款/转账/修改密码]操作", notes = "会员储值[充值/扣款/转账/修改密码]操作 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/changeStoredAmount", method = RequestMethod.POST)
    public Result changeStoredAmount(HttpServletRequest request, @RequestBody RequestStoredAmountVO requestStoredAmountVO) {
        Result result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            storedAmountService.changeStoredAmount(loginUser,requestStoredAmountVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch (BusinessException e) {
            logger.error("会员储值[充值/扣款/转账]操作错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch (Exception e) {
            logger.error("会员储值[充值/扣款/转账]操作错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
