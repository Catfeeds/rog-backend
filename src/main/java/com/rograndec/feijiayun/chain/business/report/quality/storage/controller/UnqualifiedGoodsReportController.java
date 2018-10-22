package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsUnqualifiedCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodsUnqualifiedReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_storage_unqualifiedGoods",description = "报表-质量管理-存储与养护-不合格商品")
@RestController
@RequestMapping("report/quality/storage/unqualifiedGoods")
@Validated
public class UnqualifiedGoodsReportController {
    private static final Log logger = LogFactory.getLog(UnqualifiedGoodsReportController.class);
    @Autowired
    private GoodsStorageReportService goodsStorageReportService;
    @ApiOperation(value="按条件搜索不合格商品报表信息", notes = "按条件搜索不合格商品报表信息 | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getunqualifiedGoods", method = RequestMethod.POST)
    public Result<Page<GoodsUnqualifiedCountReportVO>> getunqualifiedGoods(HttpServletRequest request,@RequestBody RequestGoodsUnqualifiedReportVO requestGoodsUnqualifiedReportVO
                                                           ){
        Result<Page<GoodsUnqualifiedCountReportVO>> result = new Result<Page<GoodsUnqualifiedCountReportVO>>();
        try{
        	Page<GoodsUnqualifiedCountReportVO> page = new Page<GoodsUnqualifiedCountReportVO>(requestGoodsUnqualifiedReportVO.getPageNo(), requestGoodsUnqualifiedReportVO.getPageSize());
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestGoodsUnqualifiedReportVO.getPageNo()==1){
        		requestGoodsUnqualifiedReportVO.setPageNo(0);
        	}else{
        		requestGoodsUnqualifiedReportVO.setPageNo(requestGoodsUnqualifiedReportVO.getPageNo()-1);
        		requestGoodsUnqualifiedReportVO.setPageNo(requestGoodsUnqualifiedReportVO.getPageNo()*requestGoodsUnqualifiedReportVO.getPageSize());
        	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("pageNo", requestGoodsUnqualifiedReportVO.getPageNo());
        	map.put("pageSize", requestGoodsUnqualifiedReportVO.getPageSize());
        	map.put("param", requestGoodsUnqualifiedReportVO.getParam());
        	map.put("chainType", requestGoodsUnqualifiedReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsUnqualifiedReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsUnqualifiedReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsUnqualifiedReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsUnqualifiedReportVO.getDomesticImport());//国产/进口
        	map.put("storageConditionName", requestGoodsUnqualifiedReportVO.getStorageConditionName());//储藏条件
        	map.put("storageTemp", requestGoodsUnqualifiedReportVO.getStorageTemp());//储藏温度
        	map.put("registeredTrademark", requestGoodsUnqualifiedReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsUnqualifiedReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsUnqualifiedReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsUnqualifiedReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsUnqualifiedReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsUnqualifiedReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsUnqualifiedReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsUnqualifiedReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsUnqualifiedReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("sortField", requestGoodsUnqualifiedReportVO.getSortField());
        	map.put("sort", requestGoodsUnqualifiedReportVO.getSort());
        	map.put("brand", requestGoodsUnqualifiedReportVO.getBrand());
        	goodsStorageReportService.getGoodsUnqualifiedList(page, map);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索不合格报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value="导出Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调",produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,RequestGoodsUnqualifiedReportVO requestGoodsUnqualifiedReportVO)throws Exception{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("param", requestGoodsUnqualifiedReportVO.getParam());
        	map.put("chainType", requestGoodsUnqualifiedReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsUnqualifiedReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsUnqualifiedReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsUnqualifiedReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsUnqualifiedReportVO.getDomesticImport());//国产/进口
        	map.put("storageConditionName", requestGoodsUnqualifiedReportVO.getStorageConditionName());//储藏条件
        	map.put("storageTemp", requestGoodsUnqualifiedReportVO.getStorageTemp());//储藏温度
        	map.put("registeredTrademark", requestGoodsUnqualifiedReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsUnqualifiedReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsUnqualifiedReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsUnqualifiedReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsUnqualifiedReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsUnqualifiedReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsUnqualifiedReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsUnqualifiedReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsUnqualifiedReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("sortField", requestGoodsUnqualifiedReportVO.getSortField());
        	map.put("sort", requestGoodsUnqualifiedReportVO.getSort());
        	map.put("brand", requestGoodsUnqualifiedReportVO.getBrand());
        	String name = "不合格商品报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsUnqualifiedExcelExport(output, loginUser, map);
    }
    @ApiOperation(value="导出Excel分店", notes = "导出Excel分店 | 开发者 孙帮祥 | 已联调",produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcelBranch", method = RequestMethod.POST)
    public void exportExcelBranch(HttpServletRequest request,HttpServletResponse response,RequestGoodsUnqualifiedReportVO requestGoodsUnqualifiedReportVO)throws Exception{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("param", requestGoodsUnqualifiedReportVO.getParam());
        	map.put("categoryId", requestGoodsUnqualifiedReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsUnqualifiedReportVO.getDomesticImport());//国产/进口
        	map.put("storageConditionName", requestGoodsUnqualifiedReportVO.getStorageConditionName());//储藏条件
        	map.put("storageTemp", requestGoodsUnqualifiedReportVO.getStorageTemp());//储藏温度
        	map.put("registeredTrademark", requestGoodsUnqualifiedReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsUnqualifiedReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsUnqualifiedReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsUnqualifiedReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsUnqualifiedReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsUnqualifiedReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsUnqualifiedReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsUnqualifiedReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsUnqualifiedReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("sortField", requestGoodsUnqualifiedReportVO.getSortField());
        	map.put("sort", requestGoodsUnqualifiedReportVO.getSort());
        	map.put("brand", requestGoodsUnqualifiedReportVO.getBrand());
        	String name = "不合格商品报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsUnqualifiedExcelExportBranch(output, loginUser, map);
    }
}
