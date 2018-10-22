package com.rograndec.feijiayun.chain.business.storage.split.controller;

import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetUnitService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitSet;
import com.rograndec.feijiayun.chain.business.storage.split.service.SplitSetService;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetPageVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetSaveVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.QualityCondition;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/26 <br/>
 * 描述：储存管理-拆零管理-拆零设置
 */
@Api(value = "storage_split_splitSet", description = "储存管理-拆零管理-拆零设置")
@RestController
@RequestMapping("storage/split/splitSet")
@Validated
public class SplitSetController {
    private static final Log logger = LogFactory.getLog(SplitSetController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SetUnitService setUnitService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SplitSetService splitSetService;

    @ApiOperation(value = "拆零设置商品列表", notes = "根据商品编码/条形码/检索码/商品名称/通用名称/批准文号搜索商品 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CanSpitGoodVO>> getGoodsList(HttpServletRequest request,
                                                    @ApiParam(value = "商品编码/条形码/检索码/商品名称/通用名称/批准文号", required = false) @RequestParam(required = false) String param) {
        Result<List<CanSpitGoodVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, splitSetService.getCanSplitGoods(param, loginUser));
        } catch (Exception e) {
            logger.error("拆零设置查询可拆零商品列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据商品ID查询拆零商品信息", notes = "根据商品ID查询拆零商品信息 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getSplitGoodInfoById", method = RequestMethod.GET)
    @ResponseBody
    public Result<CanSpitGoodVO> getSplitGoodInfoById(HttpServletRequest request,
                                                      @ApiParam(value = "商品id", required = true) @RequestParam Long id) {
        Result<CanSpitGoodVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            CanSpitGoodVO spitGoodVO = goodsService.getSplitGoodInfoById(id, loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, spitGoodVO);
        } catch (Exception e) {
            logger.error("拆零设置查询可拆零商品列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询商品单位列表", notes = "查询商品单位列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getGoodsUnitList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<GoodsUnitVO>> getGoodsUnitList(HttpSession session) {
        Result<List<GoodsUnitVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, setUnitService.getUsableUnit(enterpriseId));
        } catch (Exception e) {
            logger.error("获取拆零设置-单位列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询货位列表（合格品货位）", notes = "查询货位列表（合格品货位） | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getShelfList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<WarehouseVO>>> getShelfList(HttpServletRequest request) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long enterpriseId = loginUser.getEnterpriseId();

            //获取启用状态的 合格品区货位
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(enterpriseId, 1, QualityCondition.NONCONFORMING_PRODUCT.getCode()));
        } catch (Exception e) {
            logger.error("拆零设置-查询货位列表:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存拆零设置信息", notes = "保存拆零设置信息 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/saveSplitSet", method = RequestMethod.POST)
    public Result saveSplitSet(HttpServletRequest request, @RequestBody SplitSetSaveVO splitSetSaveVO) throws Exception {
        Result result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        //商品id传参判空
        if (splitSetSaveVO.getGoodsId() == 0) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "商品id为空！");
            return result;
        }

        if (checkRequestBody(splitSetSaveVO) != null) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, checkRequestBody(splitSetSaveVO));
            return result;
        }

        //生成新商品及插入拆零设置单据
        goodsService.addSplitGoods(loginUser, splitSetSaveVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }

    @ApiOperation(value = "更新拆零设置信息", notes = "更新拆零设置信息 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/updateSplitSet", method = RequestMethod.POST)
    public Result updateSplitSet(HttpServletRequest request, @RequestBody SplitSetSaveVO splitSetSaveVO) throws Exception {
        Result result = new Result<>();

        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");

        //商品id传参判空
        if (splitSetSaveVO.getSplitSetId() == 0) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "拆零设置ID为空！");
            return result;
        }

        if (checkRequestBody(splitSetSaveVO) != null) {
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, checkRequestBody(splitSetSaveVO));
            return result;
        }

        SplitSet oldSplitSet = splitSetService.selectByPrimaryKey(splitSetSaveVO.getSplitSetId());
        splitSetSaveVO.setGoodsId(oldSplitSet.getGoodsId());
        //判断是否生成新的商品：修改拆零规格、拆零单位、拆零数量 这3项时，要生成新的商品品种
        if (!splitSetSaveVO.getSplitSpecification().equals(oldSplitSet.getSplitSpecification())
                || splitSetSaveVO.getSplitUnitId() != oldSplitSet.getSplitUnitId()
                || splitSetSaveVO.getSplitQuantity().compareTo(oldSplitSet.getSplitQuantity()) != 0) {
            //生成新商品及插入拆零设置单据
            goodsService.addSplitGoods(userVO, splitSetSaveVO);
        } else {
            throw new BusinessException("没有修改任何数据");
//                //是否修改货位
//                boolean updateShelf = false;
//                if (oldSplitSet.getSplitShelfId().compareTo(splitSetSaveVO.getSplitShelfId()) != 0) {
//                    updateShelf = true;
//                }
//
//                //是否修改价格
//                boolean updatePrice = false;
//                if (oldSplitSet.getSplitRetailPrice().compareTo(splitSetSaveVO.getSplitRetailPrice()) != 0
//                        || oldSplitSet.getSplitMemberPrice().compareTo(splitSetSaveVO.getSplitMemberPrice()) != 0) {
//                    updatePrice = true;
//                }
//
//                if (updatePrice || updateShelf) {
//                    splitSetSaveVO.setSplitGoodsId(oldSplitSet.getSplitGoodsId());
//                    splitSetService.updateSplitSet(splitSetSaveVO, userVO, updateShelf, updatePrice);
//                } else {
//                    result.setBizCodeSuccessInfo(SysCode.SUCCESS, "无任何修改！");
//                }
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }

    /**
     * 参数检查
     *
     * @param splitSetSaveVO
     * @return
     */
    private String checkRequestBody(SplitSetSaveVO splitSetSaveVO) {

        //拆零规格判空
        if (StringUtils.isEmpty(splitSetSaveVO.getSplitSpecification())) {
            return "拆零规格不能为空！";
        }

        //拆零单位判空
        if (splitSetSaveVO.getSplitUnitId() == 0) {
            return "请选择拆零单位！";
        }

//        //拆零货位判空
//        if (splitSetSaveVO.getSplitShelfId() == 0) {
//            return "请选择拆零货位！";
//        }

        //拆零数量判空
        if (splitSetSaveVO.getSplitQuantity() == null || splitSetSaveVO.getSplitQuantity().compareTo(BigDecimal.ZERO) < 1) {
            return "请输入正确的拆零数量！";
        }

//        //拆零零售单价
//        if (splitSetSaveVO.getSplitRetailPrice() == null || splitSetSaveVO.getSplitRetailPrice().compareTo(BigDecimal.ZERO) <= 0) {
//            return "请输入正确拆零零售价！";
//        }
//
//        //拆零会员单价
//        if (splitSetSaveVO.getSplitMemberPrice() == null || splitSetSaveVO.getSplitMemberPrice().compareTo(BigDecimal.ZERO) <= 0) {
//            return "请输入正确拆零会员价！";
//        }
        return null;
    }

    @ApiOperation(value = "查询拆零设置列表", notes = "查询拆零设置列表 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getSplitSetList", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<SplitSetPageVO>> getSplitSetList(HttpServletRequest request,
                                                        @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                        @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                        @ApiParam(value = "desc-倒序;asc正序", required = true) @RequestParam String sort,
                                                        @ApiParam(value = "商品编码/条形码/检索码/商品名称/通用名称/批准文号", required = false) @RequestParam(required = false) String param) {
        Result<Page<SplitSetPageVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if (!"desc".equals(sort) && !"asc".equals(sort)) {
                sort = "desc";
            }
            page = splitSetService.getMemberInfoPage(page, param, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("按条件搜索查询会员信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }

    @ApiOperation(value = "根据拆零设置主键查询单个拆零设置信息", notes = "根据拆零设置主键查询拆零设置信息 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/getSplitSetById", method = RequestMethod.GET)
    @ResponseBody
    public Result<SplitSetPageVO> getSplitSetById(HttpServletRequest request, @ApiParam(value = "主键id", required = true) @RequestParam Long id) {
        Result<SplitSetPageVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            SplitSetPageVO splitSetPageVO = splitSetService.getSplitSetById(id, loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, splitSetPageVO);
        } catch (Exception e) {
            logger.error("按条件搜索查询会员信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
        }
        return result;
    }
}
