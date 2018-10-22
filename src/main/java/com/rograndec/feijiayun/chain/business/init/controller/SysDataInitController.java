package com.rograndec.feijiayun.chain.business.init.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.UserManagerService;
import com.rograndec.feijiayun.chain.business.init.service.SysDataInitService;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(description = "初始化系统默认数据")
@RestController
@RequestMapping("sysDataInit")
@Validated
public class SysDataInitController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SysDataInitService sysDataInitService;

    @ApiOperation(value="初始化系统默认数据 | 开发者 李中义 | 已完成", notes = "初始化系统默认数据 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/initCommonData", method = RequestMethod.GET)
    public Result initCommonData(@ApiParam(name = "enterpriseId",value = "待初始化的企业ID",required = true) Long enterpriseId,
                                 @ApiParam(name = "enterpriseTypeStr",value = "待初始化的企业类型字符串(门店为store)",required = true) String enterpriseTypeStr,
                                 @ApiParam(name = "userId",value = "操作人员ID",required = true) Long userId) throws Exception {
        Result result = new Result<>();
        try {
            Enterprise enterprise = enterpriseService.queryEnterpriseById(enterpriseId, enterpriseTypeStr);
            User user = userManagerService.queryUserByUserId(userId);
            sysDataInitService.initCommonData(enterprise, userToUserVO(user));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (Exception e) {
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    private UserVO userToUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setEnterpriseId(user.getEnterpriseId());
        userVO.setParentId(user.getParentId());
        userVO.setUserCode(user.getCode());
        userVO.setUserName(user.getName());
        return userVO;
    }

}
