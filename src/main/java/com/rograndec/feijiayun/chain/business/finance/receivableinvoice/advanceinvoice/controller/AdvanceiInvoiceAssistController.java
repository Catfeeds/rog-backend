package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.service.IAdvanceinvoiceService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableInvoiceGoodsVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.CodeVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceEnterpriseVo;
import com.rograndec.feijiayun.chain.business.goods.info.vo.JoinGoodsRequestVo;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.AdvanceInvoiceType;
import com.rograndec.feijiayun.chain.common.valid.annotation.ValidUtils;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author czm
 *
 */
@Api(value = "receivable_invoice", description = "预收发票-预收发票接口服务")
@RestController
@RequestMapping("finance/receivableinvoice/advanceinvoice/assist")
public class AdvanceiInvoiceAssistController {
	private static final Log logger = LogFactory.getLog(AdvanceiInvoiceAssistController.class);
	@Autowired
    private IAdvanceinvoiceService iAdvanceinvoiceService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@ApiOperation(value = "获取发票类型", notes = "获取发票类型 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getIncvoiceTypes", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getIncvoiceTypes() {
		Result<Object> result = new Result<>();
		try {
			List<CodeVo> list = new ArrayList<CodeVo>();
			for (AdvanceInvoiceType a : AdvanceInvoiceType.values()) {
				CodeVo c = new CodeVo();
				c.setCode(a.getCode());
				c.setName(a.getName());
				list.add(c);
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		} catch (Exception e) {
			logger.error("获取发票类型错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查询总部下的加盟店", notes = "查询总部下的加盟店 | 开发者 迟宗民 | 开发中")
	@RequestMapping(value = "/getChildEnterprise", method = RequestMethod.GET)
	public Result<List<InvoiceEnterpriseVo>> getChildEnterprise(HttpSession session) {
		Result<List<InvoiceEnterpriseVo>> result = new Result<>();
		try {
			UserVO userVO = (UserVO) session.getAttribute("user");
			List<Enterprise> enterpriseList = enterpriseService.getChildrens(userVO.getEnterpriseId());
			Enterprise primaryKey = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
			// 筛选加盟店
			List<InvoiceEnterpriseVo> returnList=new ArrayList<>();
			/*List<Enterprise> collect = enterpriseList.stream().filter(x -> x.getChainType().equals(2))
					.collect(Collectors.toList());*/
			for(Enterprise e:enterpriseList) {
				if(e.getChainType().equals(2)) {
					InvoiceEnterpriseVo i=new InvoiceEnterpriseVo();
					//加盟店购货商
					i.setId(e.getId());
					i.setCode(e.getCode());
					i.setName(e.getName());
					i.setAccountName(e.getBankAccountName());
					i.setAccount(e.getBankAccount());
					i.setAccountBank(e.getBankName());
					i.setAddress(e.getCompanyAddress());
					i.setTelephone(e.getTel());
					//总部
					i.setCompanyName(userVO.getEnterpriseName());
					i.setCompanyAccount(primaryKey.getBankAccount());
					i.setCompanyAccountBank(primaryKey.getBankName());
					i.setCompanyAccountName(primaryKey.getBankAccountName());
					i.setCompanyTelephone(primaryKey.getTel());
					i.setCompanyAddress(primaryKey.getCompanyAddress());
					returnList.add(i);
				}
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, returnList);
		} catch (Exception e) {
			logger.error("查询总部下的加盟店错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查询加盟店下面可用商品", notes = "查询加盟店下面可用商品 | 开发者 zongmin.chi| 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/selectGoodsList", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> selectReveivableinvoiceList(HttpServletRequest request,
			@ApiParam(value = "查询加盟店下面可用商品参数", required = true) @RequestBody JoinGoodsRequestVo joinGoodsRequestVo) {
		Result<Object> result = new Result<Object>();
		// 参数校验
		ValidUtils.validObject(joinGoodsRequestVo);
		
		// 数据请求
		Page<Object> page = new Page<Object>(joinGoodsRequestVo.getPageNo(), joinGoodsRequestVo.getPageSize());
		try {
			List<AdvanceReveivableInvoiceGoodsVo> selectJoinGoodsList = iAdvanceinvoiceService.selectJoinGoodsList(page, joinGoodsRequestVo);
			page.setResult(selectJoinGoodsList);
			if(joinGoodsRequestVo.getPageSize().equals(0)) {
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, selectJoinGoodsList);	
			}else {
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);				
			}
		} catch (Exception e) {
			logger.error("查询加盟店下面可用商品错误:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL, e.getMessage());
			return result;
		}
		return result;
	}
}
