package com.rograndec.feijiayun.chain.business.goods.pharmacy.controller;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.PharmacyDictionaryService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.GoodsDictionaryVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 药品词典、选药指导
 * xingjian.lan
 */

@Api(value = "pharmacyDictionary", description = "药学管理-药品词典、选药指导接口")
@RestController
@RequestMapping("goods/pharmacy/pharmacyDictionary")
public class PharmacyDictionaryController {

    private static final Log logger = LogFactory.getLog(PharmacyDictionaryController.class);

    @Autowired
    private PharmacyDictionaryService pharmacyDictionaryService;

    @ApiOperation(value = "药学管理-药品词典/选药指导分页列表", notes = "药学管理-药品词典 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPharmacyDictionaryPage", method = RequestMethod.GET)
    public Result<Page<GoodsDictionaryVO>> getRecoverPlanPage(HttpServletRequest request,
                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                              @ApiParam(value = "商品编码排序：0-升序 1-降序", required = false) @RequestParam(required = false) Integer sort,
                                                              @ApiParam(value = "搜索关键字", required = false) @RequestParam(required = false) String param) {
        Result<Page<GoodsDictionaryVO>> result = new Result<>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, pharmacyDictionaryService.getPharmacyDictionaryPage(page, param, sort, loginUser.getEnterpriseId()));
        } catch (Exception e) {
            logger.error("药学管理-药品词典分页列表错误:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出excel-药学管理-药品词典", notes = "导出数据 | 开发者 蓝兴建 | 已联调")
    @RequestMapping(value = "/excelExport", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExportDictionary(HttpServletRequest request, HttpServletResponse response,
                                      @ApiParam(value = "导出类型：0-药品词典 1-选药指导", required = true) @RequestParam Integer type,
                                      @ApiParam(value = "商品编码排序：0-升序 1-降序", required = false) @RequestParam(required = false) Integer sort,
                                      @ApiParam(value = "搜索关键字", required = false) @RequestParam(required = false) String param) {
        String name = "";
        try {

            if (type == 0) {
                name = "药品词典";
            } else if (type == 1) {
                name = "选药指导";
            }

            response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            if (type == 0) {
                pharmacyDictionaryService.excelExportDictionary(output, param, sort, userVO);
            } else if (type == 1) {
                pharmacyDictionaryService.excelExportSelectGuide(output, param, sort, userVO);
            }
        } catch (Exception e) {
            logger.error("导出excel-药学管理-药品词典:" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

}
