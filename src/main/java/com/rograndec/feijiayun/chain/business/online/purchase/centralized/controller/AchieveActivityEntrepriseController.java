package com.rograndec.feijiayun.chain.business.online.purchase.centralized.controller;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.AchieveActivityEntrepriseService;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.CentralizedCartService;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.GetActivityEntrepriseVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.SelectActivityEntrepriseVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
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
import java.math.BigDecimal;
import java.util.List;

@Api(value = "AchieveActivityEntreprise", description = "集采管理-客户端调取运营后台集采活动服务接口")
@RestController
@RequestMapping("online/purchase/centralized/achieveActivityEntreprise")
public class AchieveActivityEntrepriseController {
    private static final Log logger = LogFactory.getLog(AchieveActivityEntrepriseController.class);

    @Autowired
    AchieveActivityEntrepriseService achieveActivityEntrepriseService;
    @Autowired
    CentralizedCartService centralizedCartService;

    @ApiOperation(value = "通过当前企业ID查询可使用的集采活动", notes = "通过当前企业ID查询可使用的集采活动 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getActivityEntreprise", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<GetActivityEntrepriseVO>> getActivityEntreprise(HttpSession session) {
        Result<List<GetActivityEntrepriseVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, achieveActivityEntrepriseService.getActivityEntreprise(userVO));
        } catch (Exception e) {
            logger.error("通过当前企业ID查询可使用的集采活动错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过活动ID展示活动内容信息", notes = "通过活动ID展示活动内容信息 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectActivityEntreprise", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<SelectActivityEntrepriseVO>> selectActivityEntreprise(HttpServletRequest request,
                                                                                   @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                                   @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                                   @ApiParam(value = "活动ID", required = true) @RequestParam Long id,
                                                                                   @ApiParam(value = "搜索栏条件", required = false) @RequestParam(required = false) String searchValues,
                                                                                   @ApiParam(value = "一级分类编号", required = false) @RequestParam(required = false) String gcName1,
                                                                                   @ApiParam(value = "一级分类编号是否展开 0-不展开  1-展开", required = true) @RequestParam Long gcName1Type,
                                                                                   @ApiParam(value = "二级分类编号", required = false) @RequestParam(required = false) String gcName2,
                                                                                   @ApiParam(value = "二级分类编号是否展开 0-不展开  1-展开", required = true) @RequestParam Long gcName2Type,
                                                                                   @ApiParam(value = "生产厂商", required = false) @RequestParam(required = false) String manufacturer,
                                                                                   @ApiParam(value = "生产厂商是否展开 0-不展开  1-展开", required = true) @RequestParam Long manufacturerType
                                                                                   ) {
        Result<Page<SelectActivityEntrepriseVO>> result = new Result<Page<SelectActivityEntrepriseVO>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            SelectActivityEntrepriseVO purchaseCheckVoList = achieveActivityEntrepriseService
                    .selectActivityEntreprise(pageNo, pageSize, page, loginUser.getEnterpriseId(), id, searchValues, gcName1, gcName2, manufacturer, gcName1Type, gcName2Type, manufacturerType);
            page.setResult(purchaseCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("通过活动ID展示活动内容信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "添加货品到购物车", notes = "添加货品到购物车 | 开发者 zeshi.sun | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/setShoppingCart", method = RequestMethod.GET)
    @ResponseBody
    public Result<CentralizedCartVO> setShoppingCart(HttpSession session,
                                                     @ApiParam(value = "活动ID", required = true) @RequestParam Integer activityId,
                                                     @ApiParam(value = "货品ID", required = true) @RequestParam String goodsId,
                                                     @ApiParam(value = "数量", required = true) @RequestParam Integer qty) {
        Result<CentralizedCartVO> result = new Result<>();
        try {
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String key = centralizedCartService.getRedisKey(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, achieveActivityEntrepriseService.setShoppingCart(key,activityId,goodsId,qty,loginUser));
        }  catch (BusinessException e) {
            logger.error("添加货品到购物车错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("添加货品到购物车错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
