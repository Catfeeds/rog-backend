package com.rograndec.feijiayun.chain.business.goods.set.controller;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetCategoryService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/2.
 */

@Api(value = "goods_set", description = "商品设置-分类", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/set/setClassify")
public class SetClassifyController {

    private static final Log logger = LogFactory.getLog(SetClassifyController.class);

    @Autowired
    private SetCategoryService setCategoryService;

    @ApiOperation(value = "获取商品设置-分类基本信息(可维护)", notes = "获取商品设置-分类基本信息,维护接口 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClassifyMaintainable", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<GoodsCategoryVO>>> getClassifyMaintainable(HttpServletRequest request) throws Exception {
        Result<List<TreePOJO<GoodsCategoryVO>>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        List<TreePOJO<GoodsCategoryVO>> goodsCategorys = setCategoryService.getClassify(ChainType.getHeadEnterpriseId(loginUser), true);
        if (goodsCategorys == null) {
            result.setBizCodeSuccessInfo(SysCode.FAIL, goodsCategorys);
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsCategorys);
        return result;
    }

    @ApiOperation(value = "获取商品设置-分类基本信息", notes = "获取商品设置-分类基本信息,不可维护 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClassify", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<GoodsCategoryVO>>> getClassify(HttpServletRequest request) throws Exception {
        Result<List<TreePOJO<GoodsCategoryVO>>> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        List<TreePOJO<GoodsCategoryVO>> goodsCategorys = setCategoryService.getClassify(ChainType.getHeadEnterpriseId(loginUser), false);
        if (goodsCategorys == null) {
            result.setBizCodeSuccessInfo(SysCode.FAIL, goodsCategorys);
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsCategorys);
        return result;
    }

    // 下拉也是树形结构
    @ApiOperation(value = "当前商品设置-分类的上级分类下拉框", notes = "当前商品设置-分类的上级分类下拉框 |	 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCategory", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectBean>> getCategory(HttpServletRequest request) {
        Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        List<SelectBean> list = new ArrayList<SelectBean>();
        Long enterpriseId = 0L;
        HttpSession session = request.getSession();
        UserVO loginUser = (UserVO) session.getAttribute("user");
        if (loginUser.getChainType() == ChainType.Headquarters.getCode()) {
            enterpriseId = loginUser.getEnterpriseId();
        } else {
            enterpriseId = loginUser.getParentId();
        }
        List<GoodsCategory> goodsCategory = setCategoryService.getCategory(enterpriseId);
        for (GoodsCategory s : goodsCategory) {
            SelectBean bean = new SelectBean();
            bean.setId(String.valueOf(s.getId()));
            bean.setText(String.valueOf(s.getName()));
            list.add(bean);
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);

        return result;
    }

    @ApiOperation(value = "保存新增的商品设置-分类", notes = "保存新增的商品设置-分类 | 开发者 杜东阳 | 已联调" + "参数说明: " + "  \"parent_category_id\": \"必填\",\n"
            + "  \"name\": \"必填\",\n" + "  \"code\": 必填,\n" + "  \"remark\": \"选填\",\n"
            + "  \"status\": 必填,\n", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertSetClassify", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertSetClassify(HttpServletRequest request,
                                            @ApiParam(value = "商品设置-分类ID", required = true) @RequestBody GoodsCategory goodsCategory) throws Exception {
        Result<String> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result = setCategoryService.insertSetClassify(goodsCategory, loginUser);
        return result;
    }

    @ApiOperation(value = "修改商品设置-分类", notes = "修改商品设置-分类 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSetClassify", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSetClassify(HttpServletRequest request,
                                            @ApiParam(value = "商品设置-分类", required = true) @RequestBody GoodsCategory goodsCategory) throws Exception {
        Result<String> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        setCategoryService.updateSetClassify(goodsCategory, loginUser);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改商品设置-分类成功");
        return result;
    }

    @ApiOperation(value = "删除商品设置-分类", notes = "删除商品设置-分类 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSetClassify", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSetClassify(HttpServletRequest request,
                                            @ApiParam(value = "商品设置-分类ID", required = true) @RequestParam Long id) throws Exception {
        Result<String> result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        if (setCategoryService.canDelete(id,loginUser.getEnterpriseId())) {
            setCategoryService.deleteSetClassify(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除商品设置-分类成功!");
        } else {
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "当前商品设置-分类存在关联数据,不能直接删除!若要删除,请先删除当前商品设置分类有关联的所有数据~");
        }
        return result;
    }

}
