package com.rograndec.feijiayun.chain.business.retail.pricing.controller;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.retail.pricing.service.SalePricingService;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingParamVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingTotalVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingViewVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectMemberTypeVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsLotShelfVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: SalePricingController   
 * @Description:  零售管理-划价单-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
@Api(value="SalePricingController",description = "零售管理-划价单")
@RestController
@RequestMapping("/retail/salePricing")
public class SalePricingController {

	private static final Logger logger = LoggerFactory.getLogger(SalePricingController.class);
	
	@Autowired
	private SalePricingService salePricingService;

	 @Autowired
	 private EnterpriseBusinessService enterpriseBusinessService;

	@ApiOperation(value = "划价单选择商品分页列表", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getSalePricingGoodsByParam", method = RequestMethod.GET)
	public Result<List<SelectPricingGoodsVO>> getSalePricingGoodsByParam(HttpSession session,
			  /*@ApiParam(name = "pageNo", value = "当前页码", required = true) @RequestParam(required = true)Integer pageNo,
              @ApiParam(name = "pageSize", value = "显示条数", required = true) @RequestParam(required = true)Integer pageSize,*/
//              @ApiParam(name = "memberId", value = "会员id", required = false) @RequestParam(required = false)Long memberId,
				@ApiParam(name = "param", value = "检索条件:商品编码/条形码/检索码/名称/通用名称/批准文号", required = false) @RequestParam(required = false)String param) throws Exception {
		Result<List<SelectPricingGoodsVO>> result = new Result<>();
        try {
        	/*if(pageNo <= 0 || pageSize <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}*/
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<SelectPricingGoodsVO> page = salePricingService.selectPricingGoods(userVO, param, 0, 0);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取划价单选择商品数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "划价单选择商品批号货位", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getSalePricingGoodsLotShelf", method = RequestMethod.GET)
	public Result<List<SelectPricingGoodsLotShelfVO>> getSalePricingGoodsLotShelf(HttpSession session,
              @ApiParam(name = "goodsId", value = "商品Id", required = true) @RequestParam(required = true)Long goodsId) throws Exception {
		Result<List<SelectPricingGoodsLotShelfVO>> result = new Result<>();
        try {
        	if(null == goodsId || 0 == goodsId){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<SelectPricingGoodsLotShelfVO> page = salePricingService.selectGoodsLotShelf(userVO.getEnterpriseId(), goodsId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("划价单选择商品批号货位数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "划价单选择会员数据", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getMemberTypeByParam", method = RequestMethod.GET)
	public Result<List<SelectMemberTypeVO>> getMemberTypeByParam(HttpSession session,
              @ApiParam(name = "param", value = "检索条件:会员卡号/姓名/手机号", required = false) @RequestParam(required = false)String param) throws Exception {
		Result<List<SelectMemberTypeVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
			Long eId = userVO.getEnterpriseId();
			//门店: 会员管理由总部控制时，则查询总部的会员，否则查询门店的会员
			EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
			if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
					&& 0 == enterpriseBus.getMemberInfo()){
				eId= userVO.getParentId();
			}

            List<SelectMemberTypeVO> page = salePricingService.selectMemberType(eId, param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取划价单选择商品数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	// 排序字段
	private String[] orderString = {"pricing_date","code"};
	@ApiOperation(value = "划价单分页列表", notes = "获取数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getSalePricingByParam", method = RequestMethod.POST)
	public Result<Page<SalePricingTotalVO>> getSalePricingByParam(HttpSession session,@Valid @RequestBody SalePricingParamVO param) throws Exception {
		Result<Page<SalePricingTotalVO>> result = new Result<>();
        try {
        	if(param.getPageNo() <= 0 || param.getPageSize() <= 0){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	if(null != param.getOrder()) {
        		for(String order : orderString) {
        			String od = StringUtil.underlineToCamel(order);
        			if(od.equals(param.getOrder())) {
        				param.setOrder(order);
        			}
        		}
        		if(!param.getSort().equals("desc") && !param.getSort().equals("asc")) {
        			param.setSort("desc");
        		}
        	}
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            param.setEnterpriseId(userVO.getEnterpriseId());
            Page<SalePricingTotalVO> page = salePricingService.getSalePricingPage(param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取划价单数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "按主键id查看划价单", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getSalePricing", method = RequestMethod.GET)
	public Result<SalePricingViewVO> getSalePricing(HttpSession session,@ApiParam(value = "主键id", required = true)@RequestParam(name = "id", required = true)Long id) throws Exception {
		Result<SalePricingViewVO> result = new Result<>();
        try {
        	SalePricingViewVO list = salePricingService.findByIdSalePricing(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看系统设置数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加划价单", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody SalePricingSaveOrupdateVO salePricing) throws Exception {
		Result<String> result = new Result<>();
		// 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        int resave = salePricingService.saveORupdate(salePricing, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,resave == 1 ? "添加成功" : "无数据可添加");
        return result;
	}
	
	@ApiOperation(value = "更新划价单", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody SalePricingSaveOrupdateVO salePricing) throws Exception {
		Result<String> result = new Result<>();
		// 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        int resave = salePricingService.saveORupdate(salePricing, userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,resave == 2 ? "更新成功" : "无数据可更新");
        return result;
	}
	
	@ApiOperation(value = "取消划价单", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,@ApiParam(value = "根据id取消", required = true)@RequestParam(name = "id", required = true)Long id) {
		Result<Object> result = new Result<>();
        try {
        	if(null == id) {
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(), "id不能为空！");
                return result;
    		}
            salePricingService.cancelSalePricing(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"取消操作成功!");
        } catch (Exception e) {
            logger.error("删除划价单数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "导出划价单", notes = "导出数据 | 开发者 yuting.li | 已联调")
    @RequestMapping(value = "/excelExport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void excelExport(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) throws FileNotFoundException {
        String name = "划价单";
        try {
	        response.setContentType("application/msexcel;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
	        //输出Excel文件
	        OutputStream output = response.getOutputStream();
            salePricingService.exportExcel(output, id);
            output.close();
            output.flush();
        } catch (Exception e) {
        	logger.error("导出划价单错误:"+e.getMessage(),e);
            e.printStackTrace();
        }
        
    }
	
}

	


