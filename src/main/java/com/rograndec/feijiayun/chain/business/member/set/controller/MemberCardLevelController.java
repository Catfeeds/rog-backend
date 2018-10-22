package com.rograndec.feijiayun.chain.business.member.set.controller;

import com.rograndec.feijiayun.chain.business.member.set.service.MemberCardLevelService;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardLevelVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberSimpleCardLevelVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongyang.du 2017-09-19 15:52:00
 */
@Api(value = "member-set", description = "会员管理-会员设置-会员卡管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/set")
public class MemberCardLevelController {

    private static final Log logger = LogFactory.getLog(MemberCardLevelController.class);

    @Autowired
    private MemberCardLevelService cardLevelService;


    @ApiOperation(value = "获取简单会员卡级别", notes = "获取简单会员卡级别 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/simpleCardLevel/get", method = RequestMethod.GET)

    public Result<List<MemberSimpleCardLevelVO>> getSimpleCardLevelVO(HttpServletRequest request) {

        Result<List<MemberSimpleCardLevelVO>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        /*Long eId = loginUser.getEnterpriseId();
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {
            eId = loginUser.getParentId();
        }*/
        /*if (loginUser.getChainType() == ChainType.Headquarters.getCode()){
            eId = loginUser.getEnterpriseId();
        }else if (loginUser.getChainType() == ChainType.Selfoperatedshop.getCode()){
            eId = loginUser.getParentId();
        }else if (loginUser.getChainType() == ChainType.Division.getCode()){
            eId = loginUser.getEnterpriseId();
        }*/


        List<MemberSimpleCardLevelVO> cardLevelVOs = cardLevelService.selectSimpleCardLevel(loginUser);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, cardLevelVOs);

        return result;

    }


    @ApiOperation(value = "获取会员卡级别", notes = "获取会员卡级别 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardLevel/get", method = RequestMethod.GET)
    public Result<List<MemberCardLevelVO>> getCardLevel(
            @RequestParam(required = false) @ApiParam(value = "排序方式（升序：asc(默认),降序：desc）", required = false) String sort,
            @RequestParam(required = false) @ApiParam(name = "status", value = "状态（0-禁用；1-启用）", required = false) Integer status,
            HttpServletRequest request) {
        Result<List<MemberCardLevelVO>> result = new Result<>();


        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Long eId = loginUser.getEnterpriseId();
        if (ChainType.Headquarters.getCode() != loginUser.getChainType()) {
            eId = loginUser.getParentId();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("enterpriseId", eId);
        param.put("sort", sort == null ? "asc" : sort);

        List<MemberCardLevelVO> cardLevelVOs = cardLevelService.selectCardLevel(param, loginUser);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, cardLevelVOs);

        return result;

    }

    @ApiOperation(value = "新增会员卡级别", notes = "新增会员卡级别 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardLevel/save", method = RequestMethod.POST)
    public Result<String> save(HttpServletRequest request,
                               @ApiParam(value = "新增会员卡级别", required = true) @RequestBody MemberCardLevelVO cardLevelVO) {

        Result<String> result = new Result<String>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result = cardLevelService.save(cardLevelVO, loginUser);

        return result;

    }

    @ApiOperation(value = "更新会员卡级别", notes = "更新会员卡级别 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardLevel/update", method = RequestMethod.POST)
    public Result<String> update(HttpServletRequest request,
                                 @ApiParam(value = "更新会员卡级别", required = true) @RequestBody MemberCardLevelVO cardLevelVO) {

        Result<String> result = new Result<String>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        cardLevelService.update(cardLevelVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "会员卡级别-更新成功");

        return result;
    }

    @ApiOperation(value = "删除会员卡级别", notes = "删除会员卡级别 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardLevel/delete", method = RequestMethod.GET)
    public Result<String> delete(HttpServletRequest request, @ApiParam(value = "需要删除的会员卡ID", required = true) @RequestParam Long id) {
        return cardLevelService.delete(id);

    }
}
