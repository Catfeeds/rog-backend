package com.rograndec.feijiayun.chain.business.goods.pharmacy.controller;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.SpecialGoods;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.SpecialGoodsService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SpecialGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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

@Api(value = "specialGoods", description = "药学管理-专管药品服务接口")
@RestController
@RequestMapping("goods/pharmacy/specialGoods")
public class SpecialGoodsController {

    private static final Log logger = LogFactory.getLog(SpecialGoodsController.class);

    @Autowired
    SpecialGoodsService specialGoodsService;


    @ApiOperation(value = "分页获取所有药学管理-专管药品信息", notes = "分页获取所有药学管理-专管药品信息  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPharmacyPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<SpecialGoodsVO>>> getPharmacyPage(HttpServletRequest request,
                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                              @ApiParam(value = "输入框的值", required = false) @RequestParam(required = false) String key,
                                                              @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                              @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<SpecialGoodsVO>>> result = new Result<Page<List<SpecialGoodsVO>>>();
        try {

            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<SpecialGoodsVO> specialGoodsVoList = specialGoodsService
                    .specialGoodsVoPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, key, orderName, orderType,loginUser);
            page.setResult(specialGoodsVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取获取所有药学管理-专管药品信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "新增时获取商品信息", notes = "新增时获取商品信息  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectGoodsVO>> getGoods(HttpServletRequest request,
                                                @ApiParam(value = "商品编码", required = false) @RequestParam(required = false) String key,
                                                @ApiParam(value = "专管药品类别ID", required = false) @RequestParam(required = false) String specialGoods,
                                                @ApiParam(value = "专管药品类别级联ID", required = false) @RequestParam(required = false) String formulationType

    ) {
        Result<List<SelectGoodsVO>> result = new Result<List<SelectGoodsVO>>();

            if(StringUtils.isEmpty(specialGoods)){
                throw new BusinessException("专管药品类别不能为空");
            }

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<SelectGoodsVO> storeVoList = specialGoodsService
                    .selectGoodsVoPage(key, loginUser.getEnterpriseId(), specialGoods, formulationType);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,storeVoList);
        return result;
    }

  //专管药品的添加放在商品添加的时候设置，当有专管类型时，添加对应专管药品记录
 /*   @ApiOperation(value = "保存专管商品信息", notes = "保存专管商品信息  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertSpecialGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertSpecialGoods(HttpServletRequest request,
                                             @ApiParam(value = "保存专管商品信息", required = true) @RequestBody SpecialGoods specialGoods) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            specialGoodsService.canSave(specialGoods, loginUser);
            specialGoodsService.insertSpecialGoods(loginUser, specialGoods);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存专管商品信息成功");

        } catch (BusinessException e) {
            logger.error("保存专管商品信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("保存专管商品信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }*/

    @ApiOperation(value = "修改专管类型", notes = "修改专管类型  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateSpecialGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateSpecialGoods(HttpServletRequest request,
                                             @ApiParam(value = "需要修改的专管类型", required = true) @RequestBody SpecialGoods specialGoods) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            specialGoodsService.updateSpecialGoods(loginUser, specialGoods);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改专管类型成功");
        }  catch (BusinessException e) {
            logger.error("需要修改的专管类型错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("需要修改的专管类型错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除专管类型", notes = "删除专管类型  | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteSpecialGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteSpecialGoods(HttpServletRequest request,
                                             @ApiParam(value = "需要删除的专管类型ID", required = true) @RequestParam Long id) {
        Result<String> result = new Result<>();
        try {
        	 HttpSession session = request.getSession(true);
             UserVO loginUser = (UserVO) session.getAttribute("user");
            specialGoodsService.deleteSpecialGoods(id,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除专管类型成功");
        }  catch (BusinessException e) {
            logger.error("删除专管类型错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("删除专管类型错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "专管商品销售上限", notes = "专管商品销售上限  | 开发者 贾瑞丰| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectSpecilGoodsLimit", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> selectSpecilGoodsLimit(HttpServletRequest request,
                                                @ApiParam(value = "商品id", required = true) @RequestParam(required = true) String goodsId

    ) {
        Result<Long> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        Long limit= specialGoodsService.selectSpecilGoodsLimit(loginUser,goodsId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,limit);
        return result;
    }

}
