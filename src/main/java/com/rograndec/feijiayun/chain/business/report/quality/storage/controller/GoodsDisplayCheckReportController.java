package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsUnqualifiedCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestGoodsDisplayCheckReportVO;
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
@Api(value = "report_quality_storage_goodsDisplayCheck",description = "报表-质量管理-存储与养护-陈列检查")
@RestController
@RequestMapping("report/quality/storage/goodsDisplayCheck")
@Validated
public class GoodsDisplayCheckReportController {
    private static final Log logger = LogFactory.getLog(GoodsDisplayCheckReportController.class);
    @Autowired
    private GoodsStorageReportService goodsStorageReportService;
    @ApiOperation(value="按条件搜索陈列检查报表信息", notes = "按条件搜索陈列检查报表信息 | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getgoodsDisplayCheck", method = RequestMethod.POST)
    public Result<Page<GoodsUnqualifiedCountReportVO>> getgoodsDisplayCheck(HttpServletRequest request,@RequestBody RequestGoodsDisplayCheckReportVO requestGoodsDisplayCheckReportVO){
        Result<Page<GoodsUnqualifiedCountReportVO>> result = new Result<Page<GoodsUnqualifiedCountReportVO>>();
        try{
        	Page<GoodsUnqualifiedCountReportVO> page = new Page<GoodsUnqualifiedCountReportVO>(requestGoodsDisplayCheckReportVO.getPageNo(), requestGoodsDisplayCheckReportVO.getPageSize());
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestGoodsDisplayCheckReportVO.getSortField()!=null){
        	  if(requestGoodsDisplayCheckReportVO.getSortField().equals("checkDate")){
        		  requestGoodsDisplayCheckReportVO.setSortField("check_date");
        	  }
        	}
         	if(requestGoodsDisplayCheckReportVO.getPageNo()== 1){
         		requestGoodsDisplayCheckReportVO.setPageNo(0);
        	}else{
        		requestGoodsDisplayCheckReportVO.setPageNo(requestGoodsDisplayCheckReportVO.getPageNo()-1);
        		requestGoodsDisplayCheckReportVO.setPageNo(requestGoodsDisplayCheckReportVO.getPageNo()*requestGoodsDisplayCheckReportVO.getPageSize());
        	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("pageNo", requestGoodsDisplayCheckReportVO.getPageNo());
        	map.put("pageSize", requestGoodsDisplayCheckReportVO.getPageSize());
        	map.put("startDate", requestGoodsDisplayCheckReportVO.getStartDate());
        	map.put("endDate", requestGoodsDisplayCheckReportVO.getEndDate());
        	map.put("code", requestGoodsDisplayCheckReportVO.getCode());
        	map.put("param", requestGoodsDisplayCheckReportVO.getParam());
        	map.put("chainType", requestGoodsDisplayCheckReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsDisplayCheckReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsDisplayCheckReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsDisplayCheckReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsDisplayCheckReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsDisplayCheckReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsDisplayCheckReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsDisplayCheckReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsDisplayCheckReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsDisplayCheckReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsDisplayCheckReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsDisplayCheckReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsDisplayCheckReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsDisplayCheckReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsDisplayCheckReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsDisplayCheckReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestGoodsDisplayCheckReportVO.getStatus());
        	map.put("checkerName", requestGoodsDisplayCheckReportVO.getCheckerName());
        	map.put("maintanceType", requestGoodsDisplayCheckReportVO.getMaintanceType());
        	map.put("goodsType", requestGoodsDisplayCheckReportVO.getGoodsType());
        	map.put("sortField", requestGoodsDisplayCheckReportVO.getSortField());
        	map.put("sort", requestGoodsDisplayCheckReportVO.getSort());
        	map.put("brand", requestGoodsDisplayCheckReportVO.getBrand());
        	goodsStorageReportService.getGoodsDisplayCheckList(page, map, loginUser);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索陈列检查报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value="导出Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调", produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,RequestGoodsDisplayCheckReportVO requestGoodsDisplayCheckReportVO) throws Exception{
  	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
         	if(requestGoodsDisplayCheckReportVO.getSortField()!=null){
          	  if(requestGoodsDisplayCheckReportVO.getSortField().equals("checkDate")){
          		  requestGoodsDisplayCheckReportVO.setSortField("check_date");
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
        	map.put("startDate", requestGoodsDisplayCheckReportVO.getStartDate());
        	map.put("endDate", requestGoodsDisplayCheckReportVO.getEndDate());
        	map.put("code", requestGoodsDisplayCheckReportVO.getCode());
        	map.put("param", requestGoodsDisplayCheckReportVO.getParam());
        	map.put("chainType", requestGoodsDisplayCheckReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestGoodsDisplayCheckReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestGoodsDisplayCheckReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestGoodsDisplayCheckReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsDisplayCheckReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsDisplayCheckReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsDisplayCheckReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsDisplayCheckReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsDisplayCheckReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsDisplayCheckReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsDisplayCheckReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsDisplayCheckReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsDisplayCheckReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsDisplayCheckReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsDisplayCheckReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsDisplayCheckReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestGoodsDisplayCheckReportVO.getStatus());
        	map.put("checkerName", requestGoodsDisplayCheckReportVO.getCheckerName());
        	map.put("maintanceType", requestGoodsDisplayCheckReportVO.getMaintanceType());
        	map.put("goodsType", requestGoodsDisplayCheckReportVO.getGoodsType());
        	map.put("sortField", requestGoodsDisplayCheckReportVO.getSortField());
        	map.put("sort", requestGoodsDisplayCheckReportVO.getSort());
        	map.put("brand", requestGoodsDisplayCheckReportVO.getBrand());
        	String name = "陈列检查报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsDisplayCheckExcelExport(output, loginUser, map, loginUser);
    }
    @ApiOperation(value="导出Excel分店", notes = "导出Excel分店 | 开发者 孙帮祥 | 已联调", produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcelBranch", method = RequestMethod.POST)
    public void exportExcelBranch(HttpServletRequest request,HttpServletResponse response,RequestGoodsDisplayCheckReportVO requestGoodsDisplayCheckReportVO) throws Exception{
  	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
         	if(requestGoodsDisplayCheckReportVO.getSortField()!=null){
          	  if(requestGoodsDisplayCheckReportVO.getSortField().equals("checkDate")){
          		  requestGoodsDisplayCheckReportVO.setSortField("check_date");
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
        	map.put("startDate", requestGoodsDisplayCheckReportVO.getStartDate());
        	map.put("endDate", requestGoodsDisplayCheckReportVO.getEndDate());
        	map.put("code", requestGoodsDisplayCheckReportVO.getCode());
        	map.put("param", requestGoodsDisplayCheckReportVO.getParam());
        	map.put("categoryId", requestGoodsDisplayCheckReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestGoodsDisplayCheckReportVO.getDomesticImport());//国产/进口
        	map.put("storageTemp", requestGoodsDisplayCheckReportVO.getStorageTemp());//储藏温度
        	map.put("storageConditionName", requestGoodsDisplayCheckReportVO.getStorageConditionName());//储藏条件
        	map.put("registeredTrademark", requestGoodsDisplayCheckReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestGoodsDisplayCheckReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestGoodsDisplayCheckReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestGoodsDisplayCheckReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestGoodsDisplayCheckReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestGoodsDisplayCheckReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestGoodsDisplayCheckReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestGoodsDisplayCheckReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestGoodsDisplayCheckReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestGoodsDisplayCheckReportVO.getStatus());
        	map.put("checkerName", requestGoodsDisplayCheckReportVO.getCheckerName());
        	map.put("maintanceType", requestGoodsDisplayCheckReportVO.getMaintanceType());
        	map.put("goodsType", requestGoodsDisplayCheckReportVO.getGoodsType());
        	map.put("sortField", requestGoodsDisplayCheckReportVO.getSortField());
        	map.put("sort", requestGoodsDisplayCheckReportVO.getSort());
        	map.put("brand", requestGoodsDisplayCheckReportVO.getBrand());
        	String name = "陈列检查报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.goodsDisplayCheckExcelExportBranch(output, loginUser, map, loginUser);
    }
}
