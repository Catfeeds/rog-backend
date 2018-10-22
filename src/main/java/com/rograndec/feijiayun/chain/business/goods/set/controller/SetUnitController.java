package com.rograndec.feijiayun.chain.business.goods.set.controller;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsUnit;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetUnitService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
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

@Api(value = "goods_set", description = "商品设置-单位", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/set/setUnit")
public class SetUnitController {

    private static final Log logger = LogFactory.getLog(SetUnitController.class);

    @Autowired
    private SetUnitService setUnitService;

    @ApiOperation(value = "获取商品设置-单位", notes = "获取商品设置-单位 |开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getSetUnit", method = RequestMethod.GET)
    public Result<List<GoodsUnitVO>> getSetUnit(HttpSession session) throws Exception {
        Result<List<GoodsUnitVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, setUnitService.getSetUnit(enterpriseId, null));
        return result;
    }

    @ApiOperation(value = "新增商品单位", notes = "新增商品单位 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertSetUnit", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertSetUnit(HttpServletRequest request,
                                        @ApiParam(value = "新增商品单位", required = true) @RequestBody GoodsUnit goodsUnit) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result = setUnitService.insertSetUnit(loginUser, goodsUnit);
        return result;
    }

    @ApiOperation(value = "修改商品单位", notes = "修改商品单位 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSetUnit", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSetUnit(HttpServletRequest request,
                                        @ApiParam(value = "需要修改的商品单位", required = true) @RequestBody GoodsUnit goodsUnit) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        setUnitService.updateSetUnit(loginUser, goodsUnit);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改商品单位成功");
        return result;
    }

    @ApiOperation(value = "删除商品单位", notes = "删除商品单位 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSetUnit", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSetUnit(HttpServletRequest request,
                                        @ApiParam(value = "需要删除的商品单位ID", required = true) @RequestParam Long id) throws Exception {
        Result<String> result = new Result<>();
        if (setUnitService.canDelete(id)) {
            setUnitService.deleteSetUnit(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除商品单位成功");
        } else {
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "当前商品单位存在关联数据,不能直接删除!若要删除,请先删除当前商品单位有关联的所有数据~");
        }
        return result;
    }

}
