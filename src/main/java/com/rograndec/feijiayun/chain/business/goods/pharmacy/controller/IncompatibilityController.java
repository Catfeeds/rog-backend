package com.rograndec.feijiayun.chain.business.goods.pharmacy.controller;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.IncompatibilityService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityVO3;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SaveIncompatibilityVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
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
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/7.
 */

@Api(value = "incompatibility", description = "药学管理-配伍禁忌服务接口")
@RestController
@RequestMapping("goods/pharmacy/incompatibility")
public class IncompatibilityController {

    private static final Log logger = LogFactory.getLog(IncompatibilityController.class);

    @Autowired
    IncompatibilityService incompatibilityService;

    @ApiOperation(value = "根据药品id查询与其禁忌的药品和结果", notes = "根据药品id查询与其禁忌的药品和结果 | 开发者 贾瑞丰| 未联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getIncompatibilityGoodsByGoodsId", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<IncompatibilityGoodsVO>> getIncompatibilityGoodsByGoodsId(HttpServletRequest request,@ApiParam(value = "商品id", required = false) @RequestParam(required = false) String goodsId
    ) {
        Result<List<IncompatibilityGoodsVO>> result = new Result<List<IncompatibilityGoodsVO>>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<IncompatibilityGoodsVO> incompatibilityVoList = incompatibilityService.getIncompatibilityGoodsByGoodsId(loginUser,goodsId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, incompatibilityVoList);
        }  catch (BusinessException e) {
            logger.error("根据药品id查询与其禁忌的药品和结果错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("根据药品id查询与其禁忌的药品和结果错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "分页获取所有药学管理-配伍禁忌信息", notes = "分页获取所有药学管理-配伍禁忌信息 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getIncompatibilityPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<IncompatibilityVO3>>> getIncompatibilityPage(HttpServletRequest request,
                                               @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                               @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                               @ApiParam(value = "输入参数", required = false) @RequestParam(required = false) String key,
                                               @ApiParam(value = "种类 0-药品  1-种类", required = true) @RequestParam(required = false) Long type,
                                               @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<IncompatibilityVO3>>> result = new Result<Page<List<IncompatibilityVO3>>>();

        try {

            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }

            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<IncompatibilityVO3> incompatibilityVoList = incompatibilityService
                    .incompatibilityVoPage(pageNo, pageSize, key, loginUser.getEnterpriseId(), page, type, orderType,loginUser);

            page.setResult(incompatibilityVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }  catch (BusinessException e) {
            logger.error("获取获取所有药学管理-配伍禁忌信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("获取获取所有药学管理-配伍禁忌信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "新增时获取商品信息", notes = "新增时获取商品信息 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<SelectGoodsVO>>> getGoods(HttpServletRequest request,
                                 @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                 @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                 @ApiParam(value = "页面输入框，包含编码、检索码、名称", required = false) @RequestParam(required = false) String key
    ) {
        Result<Page<List<SelectGoodsVO>>> result = new Result<Page<List<SelectGoodsVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<SelectGoodsVO> storeVoList = incompatibilityService
                    .selectGoodsVoPage(pageNo, pageSize, key, loginUser.getEnterpriseId(), page,loginUser);
            page.setResult(storeVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("新增时获取商品信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "保存药学管理-配伍禁忌", notes = "保存药学管理-配伍禁忌信息 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertIncompatibility", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveIncompatibility(HttpServletRequest request,
                                                @ApiParam(value = "保存药学管理-配伍禁忌信息", required = true) @RequestBody SaveIncompatibilityVO saveIncompatibilityVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            incompatibilityService.saveIncompatibility(loginUser, saveIncompatibilityVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存药学管理-配伍禁忌信息成功");
        }  catch (BusinessException e) {
            logger.error("保存药学管理-配伍禁忌信息错误"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("保存药学管理-配伍禁忌信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改药学管理-配伍禁忌", notes = "修改药学管理-配伍禁忌 | 开发者 zeshi.sun| 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateIncompatibility", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateIncompatibilitys(HttpServletRequest request,
                                                @ApiParam(value = "需要修改的药学管理-配伍禁忌", required = true) @RequestBody SaveIncompatibilityVO saveIncompatibilityVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            incompatibilityService.updateIncompatibilitys(loginUser, saveIncompatibilityVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改药学管理-配伍禁忌成功");
        }  catch (BusinessException e) {
            logger.error("需要修改的药学管理-配伍禁忌错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("需要修改的药学管理-配伍禁忌错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除药学管理-配伍禁忌", notes = "删除药学管理-配伍禁忌 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteIncompatibility", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteIncompatibility(HttpServletRequest request,
                                                @ApiParam(value = "需要删除的药学管理-配伍禁忌ID", required = true) @RequestParam Long id) {
        Result<String> result = new Result<>();
        try {

        	 HttpSession session = request.getSession(true);
             UserVO loginUser = (UserVO) session.getAttribute("user");
            incompatibilityService.deleteIncompatibility(id,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除专管类型成功");
        }  catch (BusinessException e) {
            logger.error("删除药学管理-配伍禁忌错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("删除药学管理-配伍禁忌错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
