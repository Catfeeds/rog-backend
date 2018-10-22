package com.rograndec.feijiayun.chain.business.goods.httpInf.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.goods.httpInf.service.GoodsStandardService;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoodsExaminationVO;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoodsPharmacyVO;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoosMPHVO;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.SupplierMPHVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.GoodsDictionaryVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @ClassName: GoodsPharmacyHttpController
 * @Description: 药学服务访问标准库接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月18日 上午10:19:53
 */
@Api(value = "GoodsPharmacyHttpController", description = "药学服务访问标准库接口")
@RestController
@RequestMapping("goods/standard")
public class GoodsPharmacyHttpController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsPharmacyHttpController.class);
	
	@Autowired
	private GoodsStandardService goodsStandardService;
	
	@ApiOperation(value = "获取药学说明书/指导单", notes = "获取药学说明书/指导单 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/goodsSpecifications", method = RequestMethod.GET)
	public Result<GoodsPharmacyVO> goodsSpecifications(HttpSession session,
              @ApiParam(name = "nrId", value = "标准库ID", required = true) @RequestParam(required = true)String nrId) throws Exception {
		Result<GoodsPharmacyVO> result = new Result<>();
		try {
			GoodsPharmacyVO gpVO = goodsStandardService.goodsSpecifications(nrId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, gpVO);
        } catch (Exception e) {
            logger.error("获取药学说明书/指导单数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
		return result;
	}
	
	@ApiOperation(value = "用药检查", notes = "用药检查 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/goodsExamination", method = RequestMethod.GET)
	public Result<List<GoodsExaminationVO>> goodsExamination(HttpSession session,
              @ApiParam(name = "nrIds", value = "标准库id,多个用逗号分隔", required = true) @RequestParam(required = true)String nrIds) throws Exception {
		Result<List<GoodsExaminationVO>> result = new Result<>();
		try {
			List<String> standIds = Arrays.asList(nrIds.split(",")).stream().filter(f -> {
				return StringUtils.isNotBlank(f);
			}).distinct().collect(Collectors.toList());
			List<GoodsExaminationVO> list = goodsStandardService.medicationExamination(standIds);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("获取用药检查数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
		return result;
	}
	
	@ApiOperation(value = "按症状搜索商品", notes = "按症状搜索商品 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/searchGoodsBySymptom", method = RequestMethod.GET)
	public Result<Page<List<GoodsDictionaryVO>>> searchGoodsBySymptom(HttpSession session,
              @ApiParam(name = "symptom", value = "按症状搜索", required = true) @RequestParam(required = true)String symptom,
              @ApiParam(name = "pageNo", value = "起始页", required = true) @RequestParam(required = true)Long pageNo) throws Exception {
		Result<Page<List<GoodsDictionaryVO>>> result = new Result<>();
		if(null == pageNo || pageNo == 0) {
			throw new BusinessException("起始页不能为空,必须大于0");
		}
		try {
			if(StringUtils.isBlank(symptom)) {
				result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, "请输入症状！");
				return result;
			}
			Page<List<GoodsDictionaryVO>> list = goodsStandardService.searchGoodsBySymptom(symptom, pageNo);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("获取用药检查数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
		return result;
	}
	
	@ApiOperation(value = "商品从标准库检索", notes = "商品从标准库检索 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/searchGoodsMPH", method = RequestMethod.GET)
	public Result<Page<List<GoosMPHVO>>> searchGoodsMPH(HttpSession session,
              @ApiParam(name = "goodsParam", value = "商品名称|条形码", required = false) @RequestParam(required = false)String goodsParam,
              @ApiParam(name = "approvalNumber", value = "批准文号", required = false) @RequestParam(required = false)String approvalNumber,
              @ApiParam(name = "manufacturer", value = "生产厂家", required = false) @RequestParam(required = false)String manufacturer,
              @ApiParam(name = "pageNo", value = "起始页", required = true) @RequestParam(required = true)Long pageNo) throws BusinessException {
		Result<Page<List<GoosMPHVO>>> result = new Result<>();
		if(null == pageNo || pageNo == 0) {
			throw new BusinessException("起始页不能为空,必须大于0");
		}
		try {
			Map<String, String> param = new HashMap<String, String>(4);
			param.put("goodsParam", goodsParam);
			param.put("approvalNumber", approvalNumber);
			param.put("manufacturer", manufacturer);
			param.put("pageNo", pageNo+"");
			Page<List<GoosMPHVO>> list = goodsStandardService.searchGoodsMPH(param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        } catch (BusinessException e) {
            logger.error("商品从标准库检索数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
		return result;
	}
	
	
	@ApiOperation(value = "供应商从标准库检索", notes = "供应商从标准库检索 | 开发者 yuting.li | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/searchSupplierMPH", method = RequestMethod.GET)
	public Result<Page<List<SupplierMPHVO>>> searchSupplierMPH(HttpSession session,
              @ApiParam(name = "supplierName", value = "商品名称|条形码", required = false) @RequestParam(required = false)String supplierName,
              @ApiParam(name = "businessLicenseCode", value = "批准文号", required = false) @RequestParam(required = false)String businessLicenseCode,
              @ApiParam(name = "pageNo", value = "起始页", required = true) @RequestParam(required = true)Long pageNo) throws BusinessException {
		Result<Page<List<SupplierMPHVO>>> result = new Result<>();
		if(null == pageNo || pageNo == 0) {
			throw new BusinessException("起始页不能为空,必须大于0");
		}
		try {
			Map<String, String> param = new HashMap<String, String>(4);
			param.put("supplierName", supplierName);
			param.put("businessLicenseCode", businessLicenseCode);
			param.put("pageNo", pageNo+"");
			Page<List<SupplierMPHVO>> list = goodsStandardService.searchSupplierMPH(param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        } catch (BusinessException e) {
            logger.error("供应商从标准库检索数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
		return result;
	}

}
