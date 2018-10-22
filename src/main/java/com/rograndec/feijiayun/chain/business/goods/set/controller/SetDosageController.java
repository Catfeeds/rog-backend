package com.rograndec.feijiayun.chain.business.goods.set.controller;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsDosage;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetDosageService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;
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
 * Created by zeshi.sun on 2017/9/2.
 */

@Api(value = "goods_set", description = "商品设置-剂型", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/set/setDosage")
public class SetDosageController {

    private static final Log logger = LogFactory.getLog(SetDosageController.class);

    @Autowired
    private SetDosageService setDosageService;

    @ApiOperation(value = "获取商品设置-剂型", notes = "获取商品设置-剂型 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getSetDosage", method = RequestMethod.GET)
    public Result<List<GoodsDosageVO>> getSetDosage(HttpSession session) throws Exception {
        Result<List<GoodsDosageVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, setDosageService.getSetDosage(enterpriseId, null));
        return result;
    }

    @ApiOperation(value = "新增商品-剂型", notes = "新增商品-剂型 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertSetDosage", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertSetDosage(HttpServletRequest request,
                                          @ApiParam(value = "新增商品-剂型", required = true) @RequestBody GoodsDosage goodsDosage) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result = setDosageService.insertSetDosage(loginUser, goodsDosage);
        return result;
    }

    @ApiOperation(value = "修改商品-剂型", notes = "修改商品-剂型 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSetDosage", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSetDosage(HttpServletRequest request,
                                          @ApiParam(value = "需要修改的商品-剂型", required = true) @RequestBody GoodsDosage goodsDosage) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        setDosageService.updateSetDosage(loginUser, goodsDosage);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改商品-剂型成功");
        return result;
    }

    @ApiOperation(value = "删除商品-剂型", notes = "删除商品-剂型 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSetDosage", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSetDosage(HttpServletRequest request,
                                          @ApiParam(value = "需要删除的商品-剂型ID", required = true) @RequestParam Long id) throws Exception {
        Result<String> result = new Result<>();
        if (setDosageService.canDelete(id)) {
            setDosageService.deleteSetDosage(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除商品-剂型成功");
        } else {
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "当前商品-剂型存在关联数据,不能直接删除!若要删除,请先删除当前商品剂型有关联的所有数据~");
        }
        return result;
    }

}
