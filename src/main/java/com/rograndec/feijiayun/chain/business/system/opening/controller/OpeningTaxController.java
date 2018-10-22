package com.rograndec.feijiayun.chain.business.system.opening.controller;

import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningTaxService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxExcelVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
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

@Api(value = "opening_tax", description = "系统管理-期初税额-期初税额服务接口")
@RestController
@RequestMapping(value = "system/opening/tax")
public class OpeningTaxController {

    private static final Logger logger = LoggerFactory.getLogger(OpeningTaxController.class);

    @Autowired
    private OpeningTaxService openingTaxService;

    @ApiOperation(value = "获取期初税额数据", notes = "获取全部期初税额数据 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/getOpeningTaxList", method = RequestMethod.GET)
    public Result<OpeningTaxVO> getOpeningTaxList(@ApiIgnore UserVO userVO) {
        Result<OpeningTaxVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,openingTaxService.getOpeningTaxList(userVO));
        } catch (Exception e) {
            logger.error("获取期初税额数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "存草稿", notes = "期初税额存草稿 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/saveDraftCache", method = RequestMethod.POST)
    public Result<String> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody @Valid OpeningTaxVO openingTaxVO) {
        Result<String> result = new Result<>();
        try {
        	openingTaxService.saveDraftCache(openingTaxVO, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        } catch (Exception e) {
            logger.error("期初税额存草稿：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            e.printStackTrace();
            return result;
        }
        return result;
    }



    @ApiOperation(value = "导入期初税额", notes = "导入期初税额excel并校验格式 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/excelImportTax", method = RequestMethod.POST)
    public Result<OpeningTaxExcelVO> excelImportTax(HttpServletRequest request) {
        Result<OpeningTaxExcelVO> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, openingTaxService.excelImportTax(request));
        } catch(InvalidFormatException e){
            logger.error("导入期初税额:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL,"请确定文件未损坏，并且文件扩展名与文件的格式匹配");
            return result;
        } catch (Exception e) {
            logger.error("导入期初税额:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出不合格数据/导出合格数据", notes = "导出期初税额不合格（合格）数据 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/exportUnqualified", method = RequestMethod.GET)
    public void exportUnqualified(@ApiParam(value = "获取数据的key值", required = true) @RequestParam String key,
                                  @ApiParam(value = "1--导出成功数据；2--导出失败数据", required = true) @RequestParam Integer type,
                                  @ApiIgnore UserVO userVO, HttpServletResponse response) throws Exception {
        String name = "成功的期初税额信息表单.xlsx";
        try {
	        if (type == 2) {
	            name = "失败的期初税额信息表单.xlsx";
	        }
	        response.setContentType("application/octet-stream;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
	        OutputStream outputStream = response.getOutputStream();
	        openingTaxService.exportUnqualified(outputStream, key, type, userVO);
	        outputStream.close();
	        outputStream.flush();
	    }catch(Exception e){
			logger.error("下载期初税额模板异常:" + e.getMessage(), e);
		}
    }

    @ApiOperation(value = "继续导入", notes = "继续导入期初税额合格数据 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/continueToImport/{key}", method = RequestMethod.POST)
    public Result<OpeningTaxVO> continueToImport(@ApiParam(value = "获取数据的key值", required = true) @PathVariable("key") String key,
                                                                    @RequestBody @Valid OpeningTaxVO openingTaxVO,
                                                                    @ApiIgnore UserVO userVO) {
        Result<OpeningTaxVO> result = new Result<>();
        try {
        	openingTaxService.continueToImport(openingTaxVO, key, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,openingTaxVO);
        } catch (Exception e) {
            logger.error("继续导入期初税额合格数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存", notes = "保存期初税额数据 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@ApiIgnore UserVO userVO, @RequestBody @Valid OpeningTaxVO openingTaxVO) {
        Result<String> result = new Result<>();
        try {
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, openingTaxService.save(openingTaxVO, userVO));
        } catch (BusinessException e) {
            logger.error("保存期初税额数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("保存期初税额数据：" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出期初税额", notes = "导出期初税额 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/exportOpeningTax", method = RequestMethod.GET)
    public void exportOpeningTax(@ApiIgnore UserVO userVO, HttpServletResponse response) throws Exception{
    	try{
	        String name = "期初税额.xlsx";
	        response.setContentType("application/octet-stream;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
	        OutputStream outputStream = response.getOutputStream();
	        openingTaxService.exportOpeningTax(userVO, outputStream);
	        outputStream.close();
	        outputStream.flush();
    	}catch(Exception e){
			logger.error("导出期初税额异常:" + e.getMessage(), e);
		}
    }
    
    @ApiOperation(value = "下载期初税额模板", notes = "下载期初税额模板 | 开发者 张东东 | 已联调")
    @RequestMapping(value = "/exportOpeningTaxModel", method = RequestMethod.GET)
    public void exportOpeningTaxModel(@ApiIgnore UserVO userVO, HttpServletResponse response) throws Exception{
        OutputStream output=null;
		try{
			
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="期初税额模板";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            openingTaxService.exportOpeningTaxModel(userVO, output);
            output.flush();
            output.close();
		}catch(Exception e){
			logger.error("下载期初税额模板异常:" + e.getMessage(), e);
		}
    }
}
