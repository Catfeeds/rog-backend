package com.rograndec.feijiayun.chain.business.goods.pharmacy.controller;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.PharmacySetService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetVO;
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
 * Created by zeshi.sun on 2017/9/9.
 */

@Api(value = "pharmacySet", description = "药学管理-药学设置服务接口")
@RestController
@RequestMapping("goods/pharmacy/pharmacySet")
public class PharmacySetController {

    private static final Log logger = LogFactory.getLog(PharmacySetController.class);

    @Autowired
    private PharmacySetService pharmacySetService;

    @ApiOperation(value = "获取药学管理-药学设置", notes = "获取药学管理-药学设置  | 开发者 zeshi.sun| 已联调")
    @RequestMapping(value = "/getPharmacySet", method = RequestMethod.GET)
    public Result<List<PharmacySetVO>> getSetUnit(HttpSession session,
                                                  @ApiParam(value = "查询类型 0-用法；1-用量；2-单次计量；3-注意事项", required = true) @RequestParam(required = false) Long type
    ) {
        Result<List<PharmacySetVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pharmacySetService.getPharmacySet(enterpriseId, type));
        } catch (Exception e) {
            logger.error("获取药学管理-药学设置:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "新增药学管理-药学设置", notes = "新增药学管理-药学设置  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertPharmacySet", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertPharmacySet(HttpServletRequest request,
                                            @ApiParam(value = "新增药学管理-药学设置", required = true) @RequestBody PharmacySet pharmacySet) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
                pharmacySetService.canSave(pharmacySet, loginUser);
                pharmacySetService.insertPharmacySet(loginUser, pharmacySet);
                result.setBizCodeSuccessInfo(SysCode.SUCCESS, "新增药学管理-药学设置成功");

        }  catch (BusinessException e) {
            logger.error("新增药学管理-药学设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("新增药学管理-药学设置错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改药学管理-药学设置", notes = "修改药学管理-药学设置  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updatePharmacySet", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updatePharmacySet(HttpServletRequest request,
                                            @ApiParam(value = "需要修改的药学管理-药学设置", required = true) @RequestBody PharmacySet pharmacySet) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            pharmacySetService.updatePharmacySet(loginUser, pharmacySet);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改药学管理-药学设置成功");
        }  catch (BusinessException e) {
            logger.error("需要修改的药学管理-药学设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("需要修改的药学管理-药学设置错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除药学管理-药学设置", notes = "删除药学管理-药学设置  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletePharmacySet", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deletePharmacySet(HttpServletRequest request,
                                            @ApiParam(value = "需要删除的药学管理-药学设置ID", required = true) @RequestParam Long id,
                                            @ApiParam(value = "查询类型 0-用法；1-用量；2-单次计量；3-注意事项", required = true) @RequestParam(required = false) Long type
    ) {
        Result<String> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();

            pharmacySetService.canDelete(id, enterpriseId, type);
            pharmacySetService.deletePharmacySet(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除药学管理-药学设置成功");
        }  catch (BusinessException e) {
            logger.error("删除药学管理-药学设置错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("删除药学管理-药学设置错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


}
