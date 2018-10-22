package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetCategoryService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LotAdjustReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestLotAdjustReportVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_storage_lotNumberAdjust",description = "报表-质量管理-存储与养护-批号调整")
@RestController
@RequestMapping("report/quality/storage/lotNumberAdjust")
@Validated
public class LotNumberAdjustReportController {

    private static final Log logger = LogFactory.getLog(LotNumberAdjustReportController.class);

    @Autowired
    private GoodsStorageReportService goodsStorageReportService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SetCategoryService setCategoryService;

    @ApiOperation(value="按条件搜索批号调整报表信息", notes = "按条件搜索批号调整报表信息 | 开发者 孙帮祥 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getlotAdjustPage", method = RequestMethod.POST)
    public Result<Page<List<LotAdjustReportVO>>> getlotAdjustPage(HttpServletRequest request,@RequestBody RequestLotAdjustReportVO requestLotAdjustReportVO){
        Result<Page<List<LotAdjustReportVO>>> result = new Result<Page<List<LotAdjustReportVO>>>();
        try{
        	Page<List<LotAdjustReportVO>> page = new Page<List<LotAdjustReportVO>>(requestLotAdjustReportVO.getPageNo(), requestLotAdjustReportVO.getPageSize());
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestLotAdjustReportVO.getSortField()!=null){
        	  if(requestLotAdjustReportVO.getSortField().equals("adjustDate")){
        		  requestLotAdjustReportVO.setSortField("adjust_date");
        	  }
        	}
         	if(requestLotAdjustReportVO.getPageNo()==1){
         		requestLotAdjustReportVO.setPageNo(0);
        	}else{
        		requestLotAdjustReportVO.setPageNo(requestLotAdjustReportVO.getPageNo()-1);
        		requestLotAdjustReportVO.setPageNo(requestLotAdjustReportVO.getPageNo()*requestLotAdjustReportVO.getPageSize());
        	}
        	if(loginUser.getChainType()!=null){
				if(loginUser.getChainType().equals(0)){
					map.put("parentId", loginUser.getEnterpriseId());
				}else{
					map.put("parentId", loginUser.getParentId());
				}
			}
        	map.put("enterpriseId", loginUser.getEnterpriseId());
        	map.put("pageNo", requestLotAdjustReportVO.getPageNo());
        	map.put("pageSize", requestLotAdjustReportVO.getPageSize());
        	map.put("startDate", requestLotAdjustReportVO.getStartDate());
        	map.put("endDate", requestLotAdjustReportVO.getEndDate());
        	map.put("code", requestLotAdjustReportVO.getCode());
        	map.put("param", requestLotAdjustReportVO.getParam());
        	map.put("chainType", requestLotAdjustReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestLotAdjustReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestLotAdjustReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestLotAdjustReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestLotAdjustReportVO.getDomesticImport());//国产/进口
        	map.put("storageConditionName", requestLotAdjustReportVO.getStorageConditionName());//储藏条件
        	map.put("storageTemp", requestLotAdjustReportVO.getStorageTemp());//储藏温度
        	map.put("registeredTrademark", requestLotAdjustReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestLotAdjustReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestLotAdjustReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestLotAdjustReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestLotAdjustReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestLotAdjustReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestLotAdjustReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestLotAdjustReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestLotAdjustReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestLotAdjustReportVO.getStatus());
        	map.put("adjustManName", requestLotAdjustReportVO.getAdjustManName());
        	map.put("adjustReason", requestLotAdjustReportVO.getAdjustReason());
        	map.put("sortField", requestLotAdjustReportVO.getSortField());
        	map.put("sort", requestLotAdjustReportVO.getSort());
        	map.put("brand", requestLotAdjustReportVO.getBrand());
        	goodsStorageReportService.getLotAdjustList(page, map);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索批号调整报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
    @ApiOperation(value="导出Excel", notes = "导出Excel | 开发者 孙帮祥 | 已联调", produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,RequestLotAdjustReportVO requestLotAdjustReportVO)throws IOException{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestLotAdjustReportVO.getSortField()!=null){
          	  if(requestLotAdjustReportVO.getSortField().equals("adjustDate")){
          		  requestLotAdjustReportVO.setSortField("adjust_date");
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
        	map.put("startDate", requestLotAdjustReportVO.getStartDate());
        	map.put("endDate", requestLotAdjustReportVO.getEndDate());
        	map.put("code", requestLotAdjustReportVO.getCode());
        	map.put("param", requestLotAdjustReportVO.getParam());
        	map.put("chainType", requestLotAdjustReportVO.getChainType());//组织机构类型
        	map.put("enterpriseCode", requestLotAdjustReportVO.getEnterpriseCode());//组织机构编码
        	map.put("enterpriseName", requestLotAdjustReportVO.getEnterpriseName());//组织结构名称
        	map.put("categoryId", requestLotAdjustReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestLotAdjustReportVO.getDomesticImport());//国产/进口
        	map.put("storageConditionName", requestLotAdjustReportVO.getStorageConditionName());//储藏条件
        	map.put("storageTemp", requestLotAdjustReportVO.getStorageTemp());//储藏温度
        	map.put("registeredTrademark", requestLotAdjustReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestLotAdjustReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestLotAdjustReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestLotAdjustReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestLotAdjustReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestLotAdjustReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestLotAdjustReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestLotAdjustReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestLotAdjustReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestLotAdjustReportVO.getStatus());
        	map.put("adjustManName", requestLotAdjustReportVO.getAdjustManName());
        	map.put("adjustReason", requestLotAdjustReportVO.getAdjustReason());
        	map.put("sortField", requestLotAdjustReportVO.getSortField());
        	map.put("sort", requestLotAdjustReportVO.getSort());
        	map.put("brand", requestLotAdjustReportVO.getBrand());
        	String name = "批号调整报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.lotAdjustExcelExport(output, loginUser, map);
    }
    @ApiOperation(value="导出Excel分店", notes = "导出Excel分店 | 开发者 孙帮祥 | 已联调", produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @RequestMapping(value = "/exportExcelBranch", method = RequestMethod.POST)
    public void exportExcelBranch(HttpServletRequest request,HttpServletResponse response,RequestLotAdjustReportVO requestLotAdjustReportVO)throws IOException{
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	Long enterpriseId=loginUser.getEnterpriseId();
        	Map map=new HashMap();
        	if(requestLotAdjustReportVO.getSortField()!=null){
          	  if(requestLotAdjustReportVO.getSortField().equals("adjustDate")){
          		  requestLotAdjustReportVO.setSortField("adjust_date");
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
        	map.put("startDate", requestLotAdjustReportVO.getStartDate());
        	map.put("endDate", requestLotAdjustReportVO.getEndDate());
        	map.put("code", requestLotAdjustReportVO.getCode());
        	map.put("param", requestLotAdjustReportVO.getParam());
        	map.put("categoryId", requestLotAdjustReportVO.getCategoryId());//商品分类
        	map.put("domesticImport", requestLotAdjustReportVO.getDomesticImport());//国产/进口
        	map.put("storageConditionName", requestLotAdjustReportVO.getStorageConditionName());//储藏条件
        	map.put("storageTemp", requestLotAdjustReportVO.getStorageTemp());//储藏温度
        	map.put("registeredTrademark", requestLotAdjustReportVO.getRegisteredTrademark());//注册商标
        	map.put("goodsAttribute", requestLotAdjustReportVO.getGoodsAttribute());//商品属性
         	map.put("prescriptionDrug", requestLotAdjustReportVO.getPrescriptionDrug());//商品属性
         	map.put("otcType", requestLotAdjustReportVO.getOtcType());//商品属性
        	map.put("managementScopeId", requestLotAdjustReportVO.getManagementScopeId());//经营范围
        	map.put("specialDrug", requestLotAdjustReportVO.getSpecialDrug());//特殊管理药品
        	map.put("spiritDrugType", requestLotAdjustReportVO.getSpiritDrugType());//精神药品分类
        	map.put("inChargeDrug", requestLotAdjustReportVO.getInChargeDrug());//专管药品
        	map.put("formulationType", requestLotAdjustReportVO.getFormulationType());//含特殊药品复方制剂类型
        	map.put("status", requestLotAdjustReportVO.getStatus());
        	map.put("adjustManName", requestLotAdjustReportVO.getAdjustManName());
        	map.put("adjustReason", requestLotAdjustReportVO.getAdjustReason());
        	map.put("sortField", requestLotAdjustReportVO.getSortField());
        	map.put("sort", requestLotAdjustReportVO.getSort());
        	map.put("brand", requestLotAdjustReportVO.getBrand());
        	String name = "批号调整报表";
    		// 输出Excel文件
    		UserVO userVO = (UserVO) session.getAttribute("user");
    		OutputStream output = response.getOutputStream();
    		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
    		response.setContentType("application/octet-stream;charset=utf-8");
    		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
        	goodsStorageReportService.lotAdjustExcelExportBranch(output, loginUser, map);
    }
    @ApiOperation(value = "根据品种类别查询经营范围---第一个参数不传就是查的所有的经营范围", notes = "根据品种分类查询经营范围 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getBusinessScope/{businessVariety}/{status}",method= RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessVariety", value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）", required = true,paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态（0-禁用；1-启用；2-全部）", required = true,paramType = "path")
    })
    public Result<List<BusinessScopeVO>> getBusinessScope(HttpSession session, @PathVariable Integer businessVariety, @PathVariable Integer status){
        Result<List<BusinessScopeVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getBusinessScopeVOList(businessVariety,status == 2 ? null : status,userVO.getEnterpriseId()));
        return result;
    }

    @ApiOperation(value = "获取商品设置-分类基本信息", notes = "获取商品设置-分类基本信息,不可维护 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClassify", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<GoodsCategoryVO>>> getClassify(HttpServletRequest request) {
        Result<List<TreePOJO<GoodsCategoryVO>>> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<TreePOJO<GoodsCategoryVO>> goodsCategorys = setCategoryService.getClassify(loginUser.getEnterpriseId(), false);
            if (goodsCategorys == null) {
                result.setBizCodeSuccessInfo(SysCode.FAIL, goodsCategorys);
                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsCategorys);
        } catch (Exception e) {
            logger.error("获取商品设置-分类基本信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
