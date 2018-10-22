package com.rograndec.feijiayun.chain.business.storage.goodshandle.controller;

import com.rograndec.feijiayun.chain.business.retail.pricing.service.SalePricingService;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegister;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLock;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsLockService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.RequestGoodsLockListVo;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * @ClassName: GoodsLockController
 * @Description: 储存管理-商品处理-药品锁定-Rest接口
 * @date 2017-09-27 17:26:40
 */
@Api(value = "GoodsLockController", description = "储存管理-商品处理-药品锁定")
@RestController
@RequestMapping("/goodsHandl/goodsLockgoodslock")
public class GoodsLockController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsLockController.class);

    @Autowired
    private GoodsLockService goodsLockService;
    @Autowired
    private SalePricingService salePricingService;


    @ApiOperation(value = "储存管理-商品处理-药品锁定分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsLockByParam", method = RequestMethod.POST)
    public Result<Page<List<GoodsLock>>> getGoodsLockByParam(HttpSession session,
                                                             @Valid @RequestBody RequestGoodsLockListVo requestGoodsLockListVo) throws Exception {
        Result<Page<List<GoodsLock>>> result = new Result<>();
        Integer pageNo = requestGoodsLockListVo.getPageNo();
        Integer pageSize = requestGoodsLockListVo.getPageSize();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);

            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            requestGoodsLockListVo.setPageNo(page.getStart());
            requestGoodsLockListVo.setEnterpriseId(userVO.getEnterpriseId());
            goodsLockService.getGoodsLockDateByParam(requestGoodsLockListVo, page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取储存管理-商品处理-药品锁定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "添加储存管理-商品处理-药品锁定", notes = "添加数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(HttpSession session, @Valid @RequestBody GoodsLockSaveOrupdateVO goodsLockSaveOrupdateVO) throws Exception {
        Result<String> result = new Result<>();
        // 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        goodsLockService.save(goodsLockSaveOrupdateVO, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加成功");
        return result;
    }


    @ApiOperation(value = "查看储存管理-商品处理-药品锁定", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsLock/{lockId}", method = RequestMethod.GET)
    public Result<GoodsLock> getGoodsLockData(HttpSession session,
                                              @ApiParam(value = "锁定单id", required = true) @PathVariable("lockId") Long lockId) throws Exception {
        Result<GoodsLock> result = new Result<>();
        try {
            GoodsLock goodsLock = goodsLockService.getGoodsLockData(lockId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsLock);
        } catch (Exception e) {
            logger.error("查看储存管理-商品处理-药品锁定数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "取消锁定单-商品处理-药品锁定", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/cancelGoodsLock/{lockId}", method = RequestMethod.GET)
    public Result<String> cancelGoodsLock(HttpSession session, @ApiParam(value = "锁定单id", required = true) @PathVariable("lockId") Long lockId) {
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int i = goodsLockService.cancelGoodsLock(userVO, lockId, true);
            if (i == 1) {
                result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            } else {
                result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, "此锁定单无法取消,请刷新后重试");
            }
        } catch (Exception e) {
            logger.error("取消锁定单失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询药品数据列表-商品处理-药品锁定", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo/{param}", method = RequestMethod.GET)
    public Result<List<SelectPricingGoodsVO>> queryGoods(HttpSession session, @ApiParam(value = "查询条件", required = true) @PathVariable("param") String param) {
        Result<List<SelectPricingGoodsVO>> result = new Result<>();
        // 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        GoodsParamStockVO goodsParamStockVO = new GoodsParamStockVO();
        goodsParamStockVO.setEnterpriseId(userVO.getEnterpriseId());
        goodsParamStockVO.setParam(param);
        goodsParamStockVO.setJobArea(0);// 合格品
        List<SelectPricingGoodsVO> selectPricingGoodsVOList = goodsLockService.selectGoodsByParam(goodsParamStockVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, selectPricingGoodsVOList);
        return result;
    }

    @ApiOperation(value = "导出储存管理-商品处理-药品锁定", notes = "导出数据 | 开发者 zhengbin.jin | 已联调")
    @RequestMapping(value = "/excelExport/{lockId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "锁定单id", required = true) @PathVariable("lockId") Long lockId) throws FileNotFoundException {
        String name = "商品锁定";
        try {
            OutputStream output = null;
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            output = response.getOutputStream();

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            //查询登记单
            GoodsLock goodsLock = goodsLockService.getGoodsLockData(lockId);
            if (goodsLock == null) {
                return;
            }
            goodsLockService.exportExcel(goodsLock, userVO, output, name);
        } catch (Exception e) {
            logger.error("导出储存管理-商品处理-药品锁定错误:" + e.getMessage(), e);
            e.printStackTrace();
        }

    }

}
