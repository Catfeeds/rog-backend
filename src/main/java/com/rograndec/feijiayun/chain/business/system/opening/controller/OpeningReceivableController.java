package com.rograndec.feijiayun.chain.business.system.opening.controller;

import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningReceivableService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableListVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableSaveVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.ResponsePaymentReceivableExcelVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLEncoder;

@Api(value = "opening_receivable", description = "系统管理-期初库存-期初应收服务接口")
@RestController
@RequestMapping(value = "system/opening/receivable")
public class OpeningReceivableController {

    private static final Logger logger = LoggerFactory.getLogger(OpeningReceivableController.class);

    @Autowired
    private OpeningReceivableService openingReceivableService;

    @ApiOperation(value = "获取期初应收数据", notes = "获取全部期初应收数据 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/getOpeningReceivableList", method = RequestMethod.GET)
    public Result<OpenningPaymentReceivableListVO> getOpeningReceivableList(@ApiIgnore UserVO userVO) {
        Result<OpenningPaymentReceivableListVO> result = new Result();
        try {
            if (ChainType.Headquarters.getCode() != userVO.getChainType()) {
                result.setBizCodeSuccessInfo(SysCode.SUCCESS_WITH_WARN);
                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, openingReceivableService.getOpeningReceivableList(userVO));
        } catch (Exception e) {
            logger.error("获取期初应收数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "存草稿", notes = "期初应收存草稿 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/saveDraftCache", method = RequestMethod.POST)
    public Result saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody @Valid OpenningPaymentReceivableListVO openningReceivableListVO) {
        Result result = new Result();
        try {
            openingReceivableService.saveDraftCache(userVO, openningReceivableListVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (Exception e) {
            logger.error("期初应收存草稿：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            e.printStackTrace();
            return result;
        }
        return result;
    }


    @ApiOperation(value = "下载模板", notes = "下载期初应付模板 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/exporTtemplate", method = RequestMethod.GET)
    public void exporTtemplate(@ApiIgnore UserVO userVO, HttpServletResponse response) throws Exception {
        String name = "期初应收.xlsx";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        openingReceivableService.exporTtemplate(outputStream,userVO);
        outputStream.close();
        outputStream.flush();
    }

    @ApiOperation(value = "导入期初应收", notes = "导入期初应收excel并校验格式 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/excelImportReceivable", method = RequestMethod.POST)
    public Result<ResponsePaymentReceivableExcelVO> excelImportReceivable(HttpServletRequest request) {
        Result<ResponsePaymentReceivableExcelVO> result = new Result();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, openingReceivableService.excelImport(request));
        } catch(InvalidFormatException e){
            logger.error("导入期初应收:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL,"请确定文件未损坏，并且文件扩展名与文件的格式匹配");
            return result;
        }catch (Exception e) {
            logger.error("导入期初应收:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出不合格数据/导出合格数据", notes = "导出期初应收不合格（合格）数据 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/exportUnqualified", method = RequestMethod.GET)
    public void exportUnqualified(@ApiParam(value = "获取数据的key值", required = true) @RequestParam String key,
                                  @ApiParam(value = "1--导出成功数据；2--导出失败数据", required = true) @RequestParam Integer type,
                                  @ApiIgnore UserVO userVO, HttpServletResponse response) throws Exception {
        String name = "成功的期初应收信息表单.xlsx";
        if (type == 2) {
            name = "失败的期初应收信息表单.xlsx";
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        openingReceivableService.exportUnqualified(outputStream, key, type, userVO.getEnterpriseId());
        outputStream.close();
        outputStream.flush();
    }

    @ApiOperation(value = "继续导入", notes = "继续导入期初应收合格数据 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/continueToImport/{key}", method = RequestMethod.POST)
    public Result<OpenningPaymentReceivableListVO> continueToImport(@ApiParam(value = "获取数据的key值", required = true) @PathVariable("key") String key,
                                                                    @RequestBody OpenningPaymentReceivableListVO openningReceivableListVO,
                                                                    @ApiIgnore UserVO userVO) {
        Result<OpenningPaymentReceivableListVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, openingReceivableService.continueToImport(openningReceivableListVO, key, userVO));
        } catch (Exception e) {
            logger.error("继续导入期初应收合格数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存", notes = "保存期初应收数据 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@ApiIgnore UserVO userVO, @RequestBody @Valid OpenningPaymentReceivableSaveVO saveVO) {
        Result result = new Result<>();
        try {

            result.setBizCodeSuccessInfo(SysCode.SUCCESS,openingReceivableService.save(saveVO, userVO));
        } catch (BusinessException e) {
            logger.error("保存期初应收数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("保存期初应收数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出期初应收", notes = "保存期初应收数据 | 开发者 张宇 | 已完成")
    @RequestMapping(value = "/exportOpeningReceivable", method = RequestMethod.GET)
    public void exportOpeningReceivable(@ApiIgnore UserVO userVO, HttpServletResponse response) throws Exception{
        String name = "期初应收.xlsx";
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        openingReceivableService.exportOpeningReceivable(outputStream, userVO);
        outputStream.close();
        outputStream.flush();
    }
}
