package com.rograndec.feijiayun.chain.business.index.pending.controller;

import com.rograndec.feijiayun.chain.business.index.pending.service.PendingService;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingCountVO;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingVO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "pending_report",description = "首页-待办")
@RestController
@RequestMapping("index/pending")
@Validated
public class PendingController {

    private static final Log logger = LogFactory.getLog(PendingController.class);

    @Autowired
    private PendingService pendingService;

    @ApiOperation(value = "查询审批流待办", notes = "查询审批流待办 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/pendingApprovalFlows", method = RequestMethod.GET)
    public Result<List<ApprovalFlowPendingVO>> pendingApprovalFlows(HttpServletRequest request,
                                           @ApiIgnore UserVO userVO , @ApiParam(value = "显示多少条",name = "limit") @RequestParam(required = false) Integer limit
    ) {
        Result<List<ApprovalFlowPendingVO>> result = new Result<>();
            List<ApprovalFlowPendingVO> pendingApprovalFlow = pendingService.getPendingApprovalFlow(userVO);
        if(null != limit){
            pendingApprovalFlow = pendingApprovalFlow.stream().limit(limit).collect(Collectors.toList());
        }

            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pendingApprovalFlow);
        return result;
    }

    @ApiOperation(value = "查询审批流待办条数", notes = "查询审批流待办条数 | 开发者 翟伟 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/pendingApprovalFlowCount", method = RequestMethod.GET)
    public Result<List<ApprovalFlowPendingCountVO>> pendingApprovalFlowCount(HttpServletRequest request,
                                                                    @ApiIgnore UserVO userVO

    ) {
        Result<List<ApprovalFlowPendingCountVO>> result = new Result<>();
        List<ApprovalFlowPendingCountVO> count = pendingService.getPendingApprovalFlowCount(userVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, count);
        return result;
    }

}
