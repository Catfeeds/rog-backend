package com.rograndec.feijiayun.chain.business.member.set.controller;

import com.rograndec.feijiayun.chain.business.basic.store.service.StoreService;
import com.rograndec.feijiayun.chain.business.member.set.service.MemberCardTypeService;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardTypeVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.SimpleStoreVO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongyang.du 2017-09-19 15:52:00
 */
@Api(value = "member-set", description = "会员管理-会员设置-会员卡类型", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/set")
public class MemberCardTypeController {

    private static final Log logger = LogFactory.getLog(MemberCardLevelController.class);

    @Autowired
    private MemberCardTypeService cardTypeService;

    @Autowired
    StoreService storeService;

    @ApiOperation(value = "获取会员卡类型", notes = "获取会员卡类型 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardType/get", method = RequestMethod.GET)
    public Result<List<MemberCardTypeVO>> getcardType(
            @RequestParam(required = false) @ApiParam(value = "排序方式（升序：asc(默认),降序：desc）", required = false) String sort,
            @RequestParam(required = false) @ApiParam(name = "status", value = "状态（0-禁用；1-启用）", required = false) Integer status,
            HttpServletRequest request) {
        Result<List<MemberCardTypeVO>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Long eId = loginUser.getEnterpriseId();
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("enterpriseId", eId);
        param.put("sort", sort == null ? "asc" : sort);

        List<MemberCardTypeVO> cardTypeVOs = cardTypeService.selectCardType(param,loginUser);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, cardTypeVOs);

        return result;

    }

    @ApiOperation(value = "保存会员卡类型", notes = "保存会员卡类型 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardType/save", method = RequestMethod.POST)
    public Result<String> save(HttpServletRequest request,
                               @ApiParam(value = "新增会员卡类型", required = true) @RequestBody MemberCardTypeVO cardTypeVO) {

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        return cardTypeService.save(cardTypeVO, loginUser);

    }

    @ApiOperation(value = "更新会员卡类型", notes = "更新会员卡类型 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardType/update", method = RequestMethod.POST)
    public Result<String> update(HttpServletRequest request,
                                 @ApiParam(value = "更新会员卡级别", required = true) @RequestBody MemberCardTypeVO cardTypeVO) {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        cardTypeService.update(cardTypeVO, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "会员卡级别-更新成功");

        return result;
    }

    @ApiOperation(value = "删除会员卡类型", notes = "删除会员卡类型 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/cardType/delete", method = RequestMethod.GET)
    public Result<String> delete(HttpServletRequest request, @ApiParam(value = "需要删除的会员卡类型ID", required = true) @RequestParam Long id) {

        return cardTypeService.delete(id);

    }

    @ApiOperation(value = "门店简单搜索", notes = "门店简单搜索 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/store/simpleSearch", method = RequestMethod.GET)
    public Result<Page<List<SimpleStoreVO>>> getStore(HttpServletRequest request,
                                                      @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                      @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                      @ApiParam(value = "页面输入框，包含编码、检索码、名称", required = false) @RequestParam(required = false) String key,
                                                      @RequestParam(required = false) @ApiParam(value = "排序方式（升序：asc(默认),降序：desc）", required = false) String sort) {
        Result<Page<List<SimpleStoreVO>>> result = new Result<>();
        if (pageNo <= 0 || pageSize <= 0) {
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        Page<List<SimpleStoreVO>> page = new Page<>(pageNo, pageSize);

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Long eId = loginUser.getEnterpriseId();

        if (!"asc".equals(sort) && !"desc".equals(sort)) {
            sort = "asc";
        }
        Map<String, Object> param = new HashMap<>();
        param.put("pageNo", pageNo);
        param.put("pageSize", pageSize);
        param.put("key", key);
        param.put("sort", sort);
        param.put("enterpriseId", eId);

        page = cardTypeService.simpleSelectStoreVOPage(param, page);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        return result;
    }

    @ApiOperation(value = "通过ids获取门店信息", notes = "门店简单搜索 | 开发者:杜东阳 | 已联调")
    @RequestMapping(value = "/store/getByIds", method = RequestMethod.GET)
    public Result<List<SimpleStoreVO>> getSimpleStoreVOsByIds(HttpServletRequest request,
                                                              @RequestParam(required = true) @ApiParam(value = "门店IDS,用逗号隔开", required = true) String ids) {

        Result<List<SimpleStoreVO>> result = new Result<>();
        List<SimpleStoreVO> stores = cardTypeService.getSimpleStoreVOsByIds(ids);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, stores);

        return result;
    }

}
