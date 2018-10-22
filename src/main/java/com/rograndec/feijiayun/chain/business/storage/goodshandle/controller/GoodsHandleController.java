package com.rograndec.feijiayun.chain.business.storage.goodshandle.controller;

import com.rograndec.feijiayun.chain.business.retail.pricing.service.SalePricingService;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandle;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsHandleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.RequestGoodsHandleListVo;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.SelectLockGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: GoodsHandleController
 * @Description: 储存管理-商品处理-药品处理-Rest接口
 * @date 2017-09-27 17:27:38
 */
@Api(value = "GoodsHandleController", description = "储存管理-商品处理-药品处理")
@RestController
@RequestMapping("/goodsHandl/goodsLockgoodshandle")
public class GoodsHandleController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsHandleController.class);

    @Autowired
    private GoodsHandleService goodsHandleService;
    @Autowired
    private SalePricingService salePricingService;


    @ApiOperation(value = "储存管理-商品处理-药品处理分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsHandleByParam", method = RequestMethod.POST)
    public Result<Page<List<GoodsHandle>>> getGoodsHandleByParam(HttpSession session,
                                                                 @Valid @RequestBody RequestGoodsHandleListVo requestGoodsHanleListVo) throws Exception {
        Result<Page<List<GoodsHandle>>> result = new Result<>();
        try {
            int pageNo = requestGoodsHanleListVo.getPageNo();
            int pageSize = requestGoodsHanleListVo.getPageSize();
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            requestGoodsHanleListVo.setEnterpriseId(userVO.getEnterpriseId());
            goodsHandleService.getGoodshandleListData(requestGoodsHanleListVo, page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取储存管理-商品处理-药品处理数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看储存管理-商品处理-药品处理", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsHandle/{handleId}", method = RequestMethod.GET)
    public Result<GoodsHandle> getGoodsHandleData(HttpSession session,
                                                  @ApiParam(value = "处理单id", required = true) @PathVariable("handleId") Long handleId) throws Exception {
        Result<GoodsHandle> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            GoodsHandle goodsHandle = goodsHandleService.getGoodsHandleData(handleId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsHandle);
        } catch (Exception e) {
            logger.error("查看储存管理-商品处理-药品处理数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "添加储存管理-商品处理-药品处理", notes = "添加数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(HttpSession session, @Valid @RequestBody GoodsHandleSaveOrupdateVO goodsHandle) throws Exception {
        Result<String> result = new Result<>();
        // 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        goodsHandleService.save(goodsHandle, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加成功");
        return result;
    }

    @ApiOperation(value = "查询药品数据列表-商品处理-药品处理", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo/{param}", method = RequestMethod.GET)
    public Result<List<SelectPricingGoodsVO>> queryGoods(HttpSession session, @ApiParam(value = "查询条件", required = true) @PathVariable("param") String param) {
        Result<List<SelectPricingGoodsVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            GoodsParamStockVO goodsParamStockVO = new GoodsParamStockVO();
            goodsParamStockVO.setEnterpriseId(userVO.getEnterpriseId());
            goodsParamStockVO.setChainType(userVO.getChainType());
            goodsParamStockVO.setParentId(userVO.getParentId());
            goodsParamStockVO.setParam(param);
            goodsParamStockVO.setIsInChargeDrug(0);
            goodsParamStockVO.setLockQuantity(1);
            List<SelectPricingGoodsVO> selectPricingGoodsVOList = salePricingService.selectLockGoodsByParam(goodsParamStockVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, selectPricingGoodsVOList);
        } catch (Exception e) {
            logger.error("查询药品数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出储存管理-商品处理-药品处理", notes = "导出数据 | 开发者 zhengbin.jin | 已联调")
    @RequestMapping(value = "/excelExport/{handleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "处理单id", required = true) @PathVariable("handleId") Long handleId) throws FileNotFoundException {
        String name = "商品处理";
        try {
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = null;

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            output = response.getOutputStream();
            GoodsHandle goodsHandle = goodsHandleService.getGoodsHandleData(handleId);
            if (goodsHandle == null) {
                return;
            }
            goodsHandleService.exportExcel(goodsHandle, userVO, output, name);
        } catch (Exception e) {
            logger.error("导出储存管理-商品处理-药品处理错误:" + e.getMessage(), e);
            e.printStackTrace();
        }

    }

}
