package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsExpireCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodsExpireReportVO;
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
 * @author 孙帮祥
 *
 */
@Api(value = "report_quality_storage_expireGoods",description = "报表-质量管理-存储与养护-过期商品")
@RestController
@RequestMapping("report/quality/storage/expireGoods")
@Validated
public class ExpireGoodsReportController {


    private static final Log logger = LogFactory.getLog(ExpireGoodsReportController.class);
    @Autowired
    private GoodsStorageReportService goodsStorageReportService;
    @ApiOperation(value="按条件搜索过期商品报表信息", notes = "按条件搜索过期商品报表信息 | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getexpireGoods", method = RequestMethod.POST)
    public Result<Page<GoodsExpireCountReportVO>> getexpireGoods(HttpServletRequest request,@RequestBody RequestGoodsExpireReportVO requestGoodsExpireReportVO){
        Result<Page<GoodsExpireCountReportVO>> result = new Result<Page<GoodsExpireCountReportVO>>();
        try{
        	Page<GoodsExpireCountReportVO> page = new Page<GoodsExpireCountReportVO>(requestGoodsExpireReportVO.getPageNo(), requestGoodsExpireReportVO.getPageSize());
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestGoodsExpireReportVO.getPageNo()==1){
        		requestGoodsExpireReportVO.setPageNo(0);
        	}else{
        		requestGoodsExpireReportVO.setPageNo(requestGoodsExpireReportVO.getPageNo()-1);
        		requestGoodsExpireReportVO.setPageNo(requestGoodsExpireReportVO.getPageNo()*requestGoodsExpireReportVO.getPageSize());
        	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("pageNo", requestGoodsExpireReportVO.getPageNo());
        	map.put("pageSize", requestGoodsExpireReportVO.getPageSize());
        	map.put("param", requestGoodsExpireReportVO.getParam());
        	map.put("chainType", requestGoodsExpireReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsExpireReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsExpireReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsExpireReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsExpireReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsExpireReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsExpireReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsExpireReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsExpireReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsExpireReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsExpireReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsExpireReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsExpireReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsExpireReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsExpireReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsExpireReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("sortField", requestGoodsExpireReportVO.getSortField());
        	map.put("sort", requestGoodsExpireReportVO.getSort());
        	map.put("brand", requestGoodsExpireReportVO.getBrand());
        	goodsStorageReportService.getGoodsExpireList(page, map);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索过期商品报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value="导出Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调",produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,RequestGoodsExpireReportVO requestGoodsExpireReportVO)throws Exception{
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
        	map.put("param", requestGoodsExpireReportVO.getParam());
        	map.put("chainType", requestGoodsExpireReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsExpireReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsExpireReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsExpireReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsExpireReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsExpireReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsExpireReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsExpireReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsExpireReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsExpireReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsExpireReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsExpireReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsExpireReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsExpireReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsExpireReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsExpireReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("sortField", requestGoodsExpireReportVO.getSortField());
        	map.put("sort", requestGoodsExpireReportVO.getSort());
        	map.put("brand", requestGoodsExpireReportVO.getBrand());
        	String name = "过期商品报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsExpireExcelExport(output, loginUser, map);
    }
    @ApiOperation(value="分店导出Excel", notes = "分店导出Excel | 开发者 孙帮祥 | 已联调",produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcelBranch", method = RequestMethod.POST)
    public void exportExcelBranch(HttpServletRequest request,HttpServletResponse response,RequestGoodsExpireReportVO requestGoodsExpireReportVO)throws Exception{
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
        	map.put("param", requestGoodsExpireReportVO.getParam());
        	map.put("categoryId", requestGoodsExpireReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsExpireReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsExpireReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsExpireReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsExpireReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsExpireReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsExpireReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsExpireReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsExpireReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsExpireReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsExpireReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsExpireReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsExpireReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("sortField", requestGoodsExpireReportVO.getSortField());
        	map.put("sort", requestGoodsExpireReportVO.getSort());
        	map.put("brand", requestGoodsExpireReportVO.getBrand());
        	String name = "过期商品报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsExpireExcelExportBranch(output, loginUser, map);
    }

}
