package com.rograndec.feijiayun.chain.business.member.integralexchange.controller;

import com.rograndec.feijiayun.chain.business.member.info.vo.SimpleMemberInfoVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.service.IntegralExchangeService;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangePageVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author dongyang.du 2017-09-19 15:52:00
 */
@Api(value = "member-integralExchange", description = "会员管理-积分兑换-积分兑换", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/integralExchange")
public class IntegralExchangeController {

    private static final Log logger = LogFactory.getLog(IntegralExchangeController.class);


    @Autowired
    private IntegralExchangeService exchangeService;


    @ApiOperation(value = "按条件搜索查询会员信息", notes = "按条件搜索查询会员信息 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSimpleMemberInfo", method = RequestMethod.GET)
    public Result<List<SimpleMemberInfoVO>> getSimpleMemberInfo(@ApiParam(value = "会员卡号/手机号/姓名", required = false) @RequestParam(required = false) String param,
                                                                HttpServletRequest request) {


        Result<List<SimpleMemberInfoVO>> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, exchangeService.getSimpleMemberInfo(param, loginUser));
        return result;

    }

    @ApiOperation(value = "获取积分兑换列表", notes = "获取积分兑换列表 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Result<Page<List<IntegralExchangePageVO>>> get(HttpServletRequest request, @ApiParam(value = "请求参数", required = true) @RequestBody IntegralExchangeRequestVO exchangeRequestVO) {


        Result<Page<List<IntegralExchangePageVO>>> result = new Result<>();
        if (exchangeRequestVO.getPageNo() <= 0 || exchangeRequestVO.getPageSize() <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Page page = exchangeService.getIntegralExchangePage(exchangeRequestVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;


    }

    @ApiOperation(value = "查看积分兑换明细", notes = "查看积分兑换明细 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public Result<IntegralExchangeVO> getDetail(@ApiParam(value = "积分兑换ID", required = true) @RequestParam("exchangeId") Long exchangeId) {

        Result<IntegralExchangeVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, exchangeService.selectDetailById(exchangeId));

        return result;
    }

    @ApiOperation(value = "新增积分兑换", notes = "新增积分兑换 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> update(@ApiParam(value = "积分兑换", required = true) @RequestBody IntegralExchangeVO integralExchangeVO, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        return exchangeService.save(integralExchangeVO, loginUser);

    }

}
