package com.rograndec.feijiayun.chain.business.retail.special.controller;

import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.business.retail.pricing.service.SalePricingService;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegister;
import com.rograndec.feijiayun.chain.business.retail.special.service.SpecialRegisterService;
import com.rograndec.feijiayun.chain.business.retail.special.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: SpecialRegisterController
 * @Description: 零售管理-专管登记-Rest接口
 * @date 2017-09-22 16:25:37
 */
@Api(value = "SpecialRegisterController", description = "零售管理-专管登记")
@RestController
@RequestMapping("/retail/specialregister")
public class SpecialRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(SpecialRegisterController.class);

    @Autowired
    private SpecialRegisterService specialRegisterService;
    @Autowired
    private PrescriptionRegisterService prescriptionRegisterService;
    @Autowired
    private SalePricingService salePricingService;


    @ApiOperation(value = "零售管理-专管登记分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSpecialRegisterByParam", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestSpecialRegisterVo", value = "检索条件", required = true, dataType = "RequestSpecialRegisterVo")
    public Result<Page<List<SpecialRegisterVO>>> getSpecialRegisterByParam(HttpSession session, @Valid @RequestBody RequestSpecialRegisterVo requestSpecialRegisterVo) throws Exception {
        Result<Page<List<SpecialRegisterVO>>> result = new Result<>();
        Integer pageNo = requestSpecialRegisterVo.getPageNo();
        Integer pageSize = requestSpecialRegisterVo.getPageSize();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page<List<SpecialRegisterVO>> page = new Page(pageNo, pageSize);
            //根据条件查询数据集合及分页信息
            requestSpecialRegisterVo.setStart(page.getStart());
            UserVO userVO = (UserVO) session.getAttribute("user");
            requestSpecialRegisterVo.setEnterpriseId(userVO.getEnterpriseId());
            specialRegisterService.listRegisterData(requestSpecialRegisterVo, page);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取零售管理-专管登记数据列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "添加零售管理-专管登记", notes = "添加数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(HttpSession session, @Valid @RequestBody SpecialRegisterSaveOrupdateVO specialRegister) {
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            specialRegisterService.save(specialRegister, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "添加成功");
        } catch (BusinessException e) {
            logger.error("添加零售管理-专管登记数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("添加零售管理-专管登记数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "更新零售管理-专管登记", notes = "更新数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<String> update(HttpSession session, @Valid @RequestBody SpecialRegisterSaveOrupdateVO specialRegister) {
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = specialRegisterService.update(specialRegister, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "更新成功");
        } catch (BusinessException e) {
            logger.error("更新零售管理-专管登记数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("更新零售管理-专管登记数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "计算商品相关金额-专管登记", notes = "更新数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public Result<SpecialRegister> calculate(HttpSession session, @Valid @RequestBody SpecialRegisterSaveOrupdateVO specialRegister) {
        Result<SpecialRegister> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            SpecialRegister sr = specialRegisterService.calculate(specialRegister, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, sr);
        } catch (BusinessException e) {
            logger.error("计算商品相关金额-专管登记数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("计算商品相关金额-专管登记数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "订单数据回传-专管登记", notes = "更新数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/fillByOrder/{saleId}/{rId}", method = RequestMethod.GET)
    public Result<SpecialRegister> fillByOrder(HttpSession session,
                                               @ApiParam(value = "销售单id", required = true) @PathVariable("saleId") Long saleId,
                                               @ApiParam(value = "登记单id", required = true) @PathVariable("rId") Long rId) {
        Result<SpecialRegister> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            specialRegisterService.fillByOrder(saleId, rId, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (BusinessException e) {
            logger.error("订单数据回传-专管登记数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("订单数据回传-专管登记数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "查询专管药品数据-专管登记", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo/{param}", method = RequestMethod.GET)
    public Result<List<SelectPricingGoodsVO>> queryGoods(HttpSession session, @ApiParam(value = "查询条件", required = true) @PathVariable("param") String param) {
        Result<List<SelectPricingGoodsVO>> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            GoodsParamStockVO goodsParamStockVO = new GoodsParamStockVO();
            goodsParamStockVO.setEnterpriseId(userVO.getEnterpriseId());
            goodsParamStockVO.setParam(param);
            goodsParamStockVO.setIsInChargeDrug(1);
            List<SelectPricingGoodsVO> selectPricingGoodsVOList = salePricingService.selectGoodsByParam(userVO,goodsParamStockVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, selectPricingGoodsVOList);
        } catch (Exception e) {
            logger.error("查询专管药品数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

//    @ApiOperation(value = "查询药品货位信息-专管登记", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @RequestMapping(value = "/queryGoodsShelf/{goodsId}", method = RequestMethod.GET)
//    public Result<List<GoodsInfoStockShelfVO>> queryGoodsShelf(HttpSession session, @ApiParam(value = "商品id", required = true) @PathVariable("goodsId") Long goodsId) {
//        Result<List<GoodsInfoStockShelfVO>> result = new Result<>();
//        try {
//            // 当前登录用户数据
//            UserVO userVO = (UserVO) session.getAttribute("user");
//            List<GoodsInfoStockShelfVO> goodsInfoStockShelfVOS = prescriptionRegisterService.getGoodsStockInfoByGoodsId(userVO.getEnterpriseId(), goodsId);
//            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsInfoStockShelfVOS);
//        } catch (Exception e) {
//            logger.error("查询药品货位信息数据失败:", e);
//            result.setBizCodeFallInfo(SysCode.FAIL);
//            return result;
//        }
//        return result;
//    }
    @ApiOperation(value = "取消登记单-专管登记", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/cancelRegister/{rid}", method = RequestMethod.GET)
    public Result<String> cancelRegister(HttpSession session, @ApiParam(value = "登记单id", required = true) @PathVariable("rid") Long rid) {
        Result<String> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int i=specialRegisterService.cancelRegister(userVO,rid);
            if(i==1){
                result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            }else{
                result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR,"此登记单无法取消,请刷新后重试");
            }
        }catch (Exception e) {
            logger.error("取消登记单失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看零售管理-专管登记", notes = "查看数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSpecialRegister/{id}", method = RequestMethod.GET)
    public Result<SpecialRegister> getSpecialRegisterData(HttpSession session, @ApiParam(value = "登记单id", required = true) @PathVariable("id") Long id) {
        Result<SpecialRegister> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            SpecialRegister specialRegister = specialRegisterService.getRegisterAndDetailDataById(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, specialRegister);
        } catch (Exception e) {
            logger.error("查看零售管理-专管登记数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "零售管理-待补登销售单分页列表", notes = "获取数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/listUnRegisterSale", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestSaleVo", value = "检索条件", required = true, dataType = "RequestSaleVo")
    public Result<Page<List<Sale>>> getSpecialRegisterByParam(@ApiIgnore UserVO userVO,@Valid @RequestBody RequestSaleVo requestSaleVo) throws Exception {
        Result<Page<List<Sale>>> result = new Result<>();
        int pageNo=requestSaleVo.getPageNo();
        int pageSize=requestSaleVo.getPageSize();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page<List<Sale>> page = new Page(pageNo, pageSize);
            requestSaleVo.setEnterpriseId(userVO.getEnterpriseId());
            //根据条件查询数据集合及分页信息
            specialRegisterService.listUnRegisterSale(page,requestSaleVo);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取零售管理-专管登记数据列表失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "销售单补登-专管登记", notes = "添加数据 | 开发者 zhengbin.jin | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/registeSale", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestSaleRegisteVo", value = "检索条件", required = true, dataType = "RequestSaleRegisteVo")
    public Result<SpecialRegister> registeSale(HttpSession session,
                                      @Valid @RequestBody RequestSaleRegisteVo requestSaleRegisteVo) {
        Result<SpecialRegister> result = new Result<>();
        try {
            // 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            SpecialRegister specialRegister=specialRegisterService.registeSale(requestSaleRegisteVo,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,specialRegister);
        } catch (BusinessException e) {
            logger.error("销售单补登-专管登记数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("销售单补登-专管登记数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "导出零售管理-专管登记", notes = "导出数据 | 开发者 zhengbin.jin | 已联调")
    @RequestMapping(value = "/excelExport/{rid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "登记单id", required = true) @PathVariable("rid") Long rid) throws FileNotFoundException {
        String name = "专管登记";
        try {
            OutputStream output = null;
            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            output = response.getOutputStream();

            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            //查询登记单
            SpecialRegister specialRegister=specialRegisterService.getRegisterAndDetailDataById(rid);
            if(specialRegister==null){
                return;
            }
            specialRegisterService.exportExcel(specialRegister,userVO,output,name,rid);
        } catch (Exception e) {
            logger.error("导出零售管理-专管登记错误:" + e.getMessage(), e);
            e.printStackTrace();
        }

    }

}
