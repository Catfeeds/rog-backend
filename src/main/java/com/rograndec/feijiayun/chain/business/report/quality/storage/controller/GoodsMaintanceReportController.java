package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsMaintanceCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodsMaintanceReportVO;
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
@Api(value = "report_quality_storage_goodsMaintance",description = "报表-质量管理-存储与养护-药品养护")
@RestController
@RequestMapping("report/quality/storage/goodsMaintance")
@Validated
public class GoodsMaintanceReportController {

    private static final Log logger = LogFactory.getLog(GoodsMaintanceReportController.class);
    @Autowired
    private GoodsStorageReportService goodsStorageReportService;
    @ApiOperation(value="按条件搜索药品养护报表信息", notes = "按条件搜索药品养护报表信息 | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getgoodsMaintance", method = RequestMethod.POST)
    public Result<Page<GoodsMaintanceCountReportVO>> getgoodsMaintance(HttpServletRequest request,@RequestBody RequestGoodsMaintanceReportVO requestGoodsMaintanceReportVO){
        Result<Page<GoodsMaintanceCountReportVO>> result = new Result<Page<GoodsMaintanceCountReportVO>>();
        try{
        	Page<GoodsMaintanceCountReportVO> page = new Page<GoodsMaintanceCountReportVO>(requestGoodsMaintanceReportVO.getPageNo(), requestGoodsMaintanceReportVO.getPageSize());
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
         	if(requestGoodsMaintanceReportVO.getSortField()!=null){
          	  if(requestGoodsMaintanceReportVO.getSortField().equals("maintanceDate")){
          		requestGoodsMaintanceReportVO.setSortField("maintance_date");
          	  }
          	}
           	if(requestGoodsMaintanceReportVO.getPageNo()==1){
           		requestGoodsMaintanceReportVO.setPageNo(0);
          	}else{
          		requestGoodsMaintanceReportVO.setPageNo(requestGoodsMaintanceReportVO.getPageNo()-1);
          		requestGoodsMaintanceReportVO.setPageNo(requestGoodsMaintanceReportVO.getPageNo()*requestGoodsMaintanceReportVO.getPageSize());
          	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("pageNo", requestGoodsMaintanceReportVO.getPageNo());
        	map.put("pageSize", requestGoodsMaintanceReportVO.getPageSize());
        	map.put("startDate", requestGoodsMaintanceReportVO.getStartDate());
        	map.put("endDate", requestGoodsMaintanceReportVO.getEndDate());
        	map.put("code", requestGoodsMaintanceReportVO.getCode());
        	map.put("param", requestGoodsMaintanceReportVO.getParam());
        	map.put("chainType", requestGoodsMaintanceReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsMaintanceReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsMaintanceReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsMaintanceReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsMaintanceReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsMaintanceReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsMaintanceReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsMaintanceReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsMaintanceReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsMaintanceReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsMaintanceReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsMaintanceReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsMaintanceReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsMaintanceReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsMaintanceReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsMaintanceReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestGoodsMaintanceReportVO.getStatus());
        	map.put("maintanceManName", requestGoodsMaintanceReportVO.getMaintanceManName());
        	map.put("goodsType", requestGoodsMaintanceReportVO.getGoodsType());
        	map.put("sortField", requestGoodsMaintanceReportVO.getSortField());
        	map.put("sort", requestGoodsMaintanceReportVO.getSort());
        	map.put("brand", requestGoodsMaintanceReportVO.getBrand());
        	goodsStorageReportService.getGoodsMaintanceList(page, map, loginUser);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索药品养护报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value="导出Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调", produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,RequestGoodsMaintanceReportVO requestGoodsMaintanceReportVO)throws Exception{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestGoodsMaintanceReportVO.getSortField()!=null){
            	  if(requestGoodsMaintanceReportVO.getSortField().equals("maintanceDate")){
            		requestGoodsMaintanceReportVO.setSortField("maintance_date");
            	  }
            	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("startDate", requestGoodsMaintanceReportVO.getStartDate());
        	map.put("endDate", requestGoodsMaintanceReportVO.getEndDate());
        	map.put("code", requestGoodsMaintanceReportVO.getCode());
        	map.put("param", requestGoodsMaintanceReportVO.getParam());
        	map.put("chainType", requestGoodsMaintanceReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsMaintanceReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsMaintanceReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsMaintanceReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsMaintanceReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsMaintanceReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsMaintanceReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsMaintanceReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsMaintanceReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsMaintanceReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsMaintanceReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsMaintanceReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsMaintanceReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsMaintanceReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsMaintanceReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsMaintanceReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestGoodsMaintanceReportVO.getStatus());
        	map.put("maintanceManName", requestGoodsMaintanceReportVO.getMaintanceManName());
        	map.put("goodsType", requestGoodsMaintanceReportVO.getGoodsType());
        	map.put("sortField", requestGoodsMaintanceReportVO.getSortField());
        	map.put("sort", requestGoodsMaintanceReportVO.getSort());
        	map.put("brand", requestGoodsMaintanceReportVO.getBrand());
        	String name = "药品养护报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsMaintanceExcelExport(output, loginUser, map, loginUser);
    }
    @ApiOperation(value="导出Excel分店", notes = "导出Excel分店 | 开发者 孙帮祥 | 已联调", produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcelBranch", method = RequestMethod.POST)
    public void exportExcelBranch(HttpServletRequest request,HttpServletResponse response,RequestGoodsMaintanceReportVO requestGoodsMaintanceReportVO)throws Exception{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestGoodsMaintanceReportVO.getSortField()!=null){
            	  if(requestGoodsMaintanceReportVO.getSortField().equals("maintanceDate")){
            		requestGoodsMaintanceReportVO.setSortField("maintance_date");
            	  }
            	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("startDate", requestGoodsMaintanceReportVO.getStartDate());
        	map.put("endDate", requestGoodsMaintanceReportVO.getEndDate());
        	map.put("code", requestGoodsMaintanceReportVO.getCode());
        	map.put("param", requestGoodsMaintanceReportVO.getParam());
        	map.put("categoryId", requestGoodsMaintanceReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsMaintanceReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsMaintanceReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsMaintanceReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsMaintanceReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsMaintanceReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsMaintanceReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsMaintanceReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsMaintanceReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsMaintanceReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsMaintanceReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsMaintanceReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsMaintanceReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestGoodsMaintanceReportVO.getStatus());
        	map.put("maintanceManName", requestGoodsMaintanceReportVO.getMaintanceManName());
        	map.put("goodsType", requestGoodsMaintanceReportVO.getGoodsType());
        	map.put("sortField", requestGoodsMaintanceReportVO.getSortField());
        	map.put("sort", requestGoodsMaintanceReportVO.getSort());
        	map.put("brand", requestGoodsMaintanceReportVO.getBrand());
        	String name = "药品养护报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsMaintanceExcelExportBranch(output, loginUser, map, loginUser);
    }
}
