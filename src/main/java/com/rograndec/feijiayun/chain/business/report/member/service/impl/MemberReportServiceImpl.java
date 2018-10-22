package com.rograndec.feijiayun.chain.business.report.member.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.member.dao.MemberIntegralDtlReportMapper;
import com.rograndec.feijiayun.chain.business.report.member.dao.MemberReportMapper;
import com.rograndec.feijiayun.chain.business.report.member.dao.StoredAmountRecordReportMapper;
import com.rograndec.feijiayun.chain.business.report.member.service.MemberReportService;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberAmountRecordTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberAmountRecordVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberBirthDateParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberBirthDateVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralDtlVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberSaleTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberSaleVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberStoredAmountDtlVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.SexType;

/**
 * 
 * @ClassName: MemberReportServiceImpl   
 * @Description: 会员报表模块
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月16日 下午1:59:55
 */
@Service
public class MemberReportServiceImpl implements MemberReportService{
	
	@Autowired
	private MemberReportMapper memberReportMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;
	@Autowired
	private MemberIntegralDtlReportMapper memberIntegralDtlReportMapper;
	@Autowired
	private StoredAmountRecordReportMapper storedAmountRecordReportMapper;
		
	@Override
	public Page<MemberIntegralExchangeTotalVO> getIntegralExchange(MemberIntegralExchangeParamVO param) {
		Page<MemberIntegralExchangeTotalVO> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		setOrder(param);
		List<MemberIntegralExchangeVO> list = memberReportMapper.getIntegralExchange(param);
		MemberIntegralExchangeTotalVO sumList = memberReportMapper.sumIntegralExchange(param);
		if(null != sumList) {
			sumList.setList(list);
		}
		page.setResult(sumList);
		page.setTotalRecord(memberReportMapper.countIntegralExchange(param));
		return page;
	}
	
	private void setOrder(MemberIntegralExchangeParamVO param) {
		// 处理排序字段
		String order = param.getOrder();
		String sort = param.getSort();
		if ("cardCode".equals(order)) {
			param.setOrder("cardCode");
		} else if ("saleDate".equals(order)) {
			param.setOrder("saleDate");
		} else {
			param.setOrder("saleCode");
		}
		if(null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				param.setSort("desc");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output, MemberIntegralExchangeParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("积分明细");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("memberName","会员姓名");
        rowHeaderMap.put("mobilePhone","手机");
        rowHeaderMap.put("saleDate","日期");
        rowHeaderMap.put("saleCode","单号");
        rowHeaderMap.put("enterpriseCode","门店编码");
        rowHeaderMap.put("enterpriseName","门店名称");
        rowHeaderMap.put("goodsCode","商品编码");
        rowHeaderMap.put("goodsGenericName","通用名称");
        rowHeaderMap.put("dosageName","剂型");
        rowHeaderMap.put("goodsSpecification","规格");
        rowHeaderMap.put("unitName","单位");
        rowHeaderMap.put("manufacturer","生产厂商");
        rowHeaderMap.put("integralExchange","增减积分");
        rowHeaderMap.put("exchangeQuantity","兑换数量");
        rowHeaderMap.put("deductIntegral","扣除积分");
        List<MemberIntegralExchangeVO> list = memberReportMapper.getIntegralExchange(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}

	@Override
	public Page<MemberSaleTotalVO> getMemberSale(MemberIntegralExchangeParamVO param) throws Exception {
		Page<MemberSaleTotalVO> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		setOrder(param);
		List<MemberSaleVO> list = memberReportMapper.getMemberSale(param);
		MemberSaleTotalVO sumList = memberReportMapper.sumMemberSale(param);
		if(null != sumList) {
			sumList.setList(list);
		}
		page.setResult(sumList);
		page.setTotalRecord(memberReportMapper.countMemberSale(param));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportMemberSaleExcel(OutputStream output, MemberIntegralExchangeParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("会员销售");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("memberName","会员姓名");
        rowHeaderMap.put("mobilePhone","手机");
        rowHeaderMap.put("saleDate","日期");
        rowHeaderMap.put("saleCode","单号");
        rowHeaderMap.put("enterpriseCode","门店编码");
        rowHeaderMap.put("enterpriseName","门店名称");
        rowHeaderMap.put("goodsCode","商品编码");
        rowHeaderMap.put("goodsGenericName","通用名称");
        rowHeaderMap.put("dosageName","剂型");
        rowHeaderMap.put("goodsSpecification","规格");
        rowHeaderMap.put("unitName","单位");
        rowHeaderMap.put("manufacturer","生产厂商");
        rowHeaderMap.put("lotNumber","批号");
        rowHeaderMap.put("productDate","生产日期");
        rowHeaderMap.put("validUntil","有效期");
        rowHeaderMap.put("shelfName","货位名称");
        rowHeaderMap.put("quantity","数量");
        rowHeaderMap.put("unitPrice","单价");
        rowHeaderMap.put("amount","金额");
        rowHeaderMap.put("currentIntegral","积分");
        List<MemberSaleVO> list = memberReportMapper.getMemberSale(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}
	
	/**
	 * 根据类型判断数据为负数
	 */
	private static String[] changeType = {"减少积分","积分清零","销售退回入库","积分兑换"};
	@Override
	public Page<List<MemberIntegralDtlVO>> getIntegralDtl(MemberIntegralExchangeParamVO param) throws Exception {
		Page<List<MemberIntegralDtlVO>> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		List<MemberIntegralDtlVO> list = getIntegralDtlFormat(param);
		page.setResult(list);
		page.setTotalRecord(memberIntegralDtlReportMapper.countIntegralDtl(param));
		return page;
	}
	
	private void setOrderDtl(MemberIntegralExchangeParamVO param) {
		// 处理排序字段
		String order = param.getOrder();
		String sort = param.getSort();
		if ("cardCode".equals(order)) {
			param.setOrder("cardCode");
		} else if ("showDate".equals(order)) {
			param.setOrder("showDate");
		} else if ("showCode".equals(order)){
			param.setOrder("showCode");
		} else {
			param.setOrder(null);
		}
		if(null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				param.setSort("desc");
			}
			if(param.getChangeType() != null && param.getChangeType() == 10){
				param.setChangeType(10);
			}
		}
	}
	
	private List<MemberIntegralDtlVO> getIntegralDtlFormat(MemberIntegralExchangeParamVO param) throws Exception{
		setOrderDtl(param);
		List<MemberIntegralDtlVO> list = memberIntegralDtlReportMapper.getIntegralDtl(param);
		if(null != list && list.size() > 0) {
			list.forEach(item->{
				String ctype = item.getChangeType();
				for(String type : changeType) {
					if(type.equals(ctype) && null != item.getChangeIntegral()) {
						BigDecimal cig = BigDecimal.ZERO.subtract(item.getChangeIntegral());
						item.setChangeIntegral(cig);
					}
				}
			});
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportIntegralDtl(OutputStream output, MemberIntegralExchangeParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("积分明细");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("memberName","会员姓名");
        rowHeaderMap.put("mobilePhone","手机");
        rowHeaderMap.put("showDate","日期");
        rowHeaderMap.put("changeType","操作类型");
        rowHeaderMap.put("showCode","操作单号");
        rowHeaderMap.put("createrName","操作人员");
        rowHeaderMap.put("enterpriseCode","门店编码");
        rowHeaderMap.put("enterpriseName","门店名称");
        rowHeaderMap.put("changeIntegral","增减积分");
        rowHeaderMap.put("currentIntegral","积分余额");
        List<MemberIntegralDtlVO> list = getIntegralDtlFormat(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}

	@Override
	public Page<MemberAmountRecordTotalVO> getStoredAmount(MemberIntegralExchangeParamVO param) throws Exception {
		Page<MemberAmountRecordTotalVO> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		setOrderDtl(param);
		List<MemberAmountRecordVO> list = memberReportMapper.getStoredAmount(param);
		MemberAmountRecordTotalVO sumList = memberReportMapper.sumStoredAmount(param);
		if(null != sumList) {
			sumList.setList(list);;
		}
		page.setResult(sumList);
		page.setTotalRecord(memberReportMapper.countStoredAmount(param));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportMemberAmountRecord(OutputStream output, MemberIntegralExchangeParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("储值付款");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("memberName","会员姓名");
        rowHeaderMap.put("mobilePhone","手机");
        rowHeaderMap.put("showDate","日期");
        rowHeaderMap.put("showCode","操作单号");
        rowHeaderMap.put("enterpriseCode","门店编码");
        rowHeaderMap.put("enterpriseName","门店名称");
        rowHeaderMap.put("amountTotal","销售金额");
        rowHeaderMap.put("changeStoredAmount","支付金额");
        List<MemberAmountRecordVO> list = memberReportMapper.getStoredAmount(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}
	
	private static String[] amountDtlType = {"扣款","转账","销售出库"};
	private List<MemberStoredAmountDtlVO> getStoredAmountDtlFormat(MemberIntegralExchangeParamVO param) throws Exception{
		setOrderDtl(param);
		List<MemberStoredAmountDtlVO> list = storedAmountRecordReportMapper.getStoredAmountDtl(param);
		if(null != list && list.size() > 0) {
			list.forEach(item->{
				String ctype = item.getChangeType();
				for(String type : amountDtlType) {
					if(type.equals(ctype) && null != item.getChangeStoredAmount()) {
						BigDecimal cig = BigDecimal.ZERO.subtract(item.getChangeStoredAmount());
						item.setChangeStoredAmount(cig);
					}
				}
			});
		}
		return list;
	}
	
	@Override
	public Page<List<MemberStoredAmountDtlVO>> getStoredAmountDtl(MemberIntegralExchangeParamVO param)
			throws Exception {
		Page<List<MemberStoredAmountDtlVO>> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		List<MemberStoredAmountDtlVO> list = getStoredAmountDtlFormat(param);
		page.setResult(list);
		page.setTotalRecord(storedAmountRecordReportMapper.countStoredAmountDtl(param));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportStoredAmountDtl(OutputStream output, MemberIntegralExchangeParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("储值明细");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("memberName","会员姓名");
        rowHeaderMap.put("mobilePhone","手机");
        rowHeaderMap.put("showDate","日期");
        rowHeaderMap.put("changeType","操作类型");
        rowHeaderMap.put("showCode","操作单号");
        rowHeaderMap.put("createrName","操作人员");
        rowHeaderMap.put("enterpriseCode","门店编码");
        rowHeaderMap.put("enterpriseName","门店名称");
        rowHeaderMap.put("changeStoredAmount","增减储值");
        rowHeaderMap.put("currentStoredAmount","储值余额");
        List<MemberStoredAmountDtlVO> list = getStoredAmountDtlFormat(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}

	@Override
	public Page<MemberRankingTotalVO> getMemberRanking(MemberRankingParamVO param) throws Exception {
		Page<MemberRankingTotalVO> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		// 处理排序字段
		String order = param.getOrder();
		String sort = param.getSort();
		if ("totalData".equals(order)) {
			param.setOrder("cardCode");
		} else if ("currentData".equals(order)) {
			param.setOrder("showDate");
		} else {
			param.setOrder(null);
		}
		if(null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				param.setSort("desc");
			}
		}
		List<MemberRankingVO> list = memberReportMapper.getMemberRanking(param);
		param.setTop(param.getPageSize().longValue());
		MemberRankingTotalVO sumList = memberReportMapper.sumMemberRanking(param);
		if(null != sumList) {
			sumList.setList(list);
		}
		page.setResult(sumList);
		page.setTotalRecord(memberReportMapper.countMemberRanking(param));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportMemberRanking(OutputStream output, MemberRankingParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
		if(param.getType() == 1) {
			headerList.add("会员排行按销售");
			rowHeaderMap.put("saleAmount","消费金额");
		} else if (param.getType() == 2 || param.getType() == 3) {
			headerList.add("会员排行按积分");
			rowHeaderMap.put("totalData","累计积分");
			rowHeaderMap.put("currentData","当前积分");
		} else if (param.getType() == 4 || param.getType() == 5) {
			headerList.add("会员排行按储值");
			rowHeaderMap.put("totalData","累计储值");
			rowHeaderMap.put("currentData","储值余额");
		}
		if(null != param.getPageSize()) {
			param.setTop(param.getPageSize().longValue());
		}
        List<MemberRankingVO> list = memberReportMapper.getMemberRanking(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}

	@Override
	public Page<MemberLivenessTotalVO> getMemberLiveness(MemberLivenessParamVO param) throws Exception {
		Page<MemberLivenessTotalVO> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		// 处理排序字段
		String sort = param.getSort();
		if(null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				param.setSort("desc");
			}
		}
		List<MemberLivenessVO> list = memberReportMapper.getMemberLiveness(param);
		MemberLivenessTotalVO sumList = memberReportMapper.sumMemberLiveness(param);
		if(null != sumList) {
			sumList.setList(list);
		}
		page.setResult(sumList);
		page.setTotalRecord(memberReportMapper.countMemberLiveness(param));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportMemberLiveness(OutputStream output, MemberLivenessParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("会员活跃度");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("saleCount","消费频次");
        rowHeaderMap.put("saleAmount","消费金额");
        List<MemberLivenessVO> list = memberReportMapper.getMemberLiveness(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}

	@Override
	public Page<List<MemberBirthDateVO>> getMemberBirthDate(MemberBirthDateParamVO param) throws Exception {
		Page<List<MemberBirthDateVO>> page = new Page<>();
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		List<MemberBirthDateVO> list = getMemberBirthDateFormat(param);
		page.setResult(list);
		page.setTotalRecord(memberReportMapper.countMemberBirthDate(param));
		return page;
	}
	
	private List<MemberBirthDateVO> getMemberBirthDateFormat(MemberBirthDateParamVO param){
		String sort = param.getSort();
		if(null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				param.setSort("desc");
			}
		}
		List<MemberBirthDateVO> list = memberReportMapper.getMemberBirthDate(param);
		if(null != list && list.size() > 0) {
			list.forEach(item->{
				if(null != item.getSex()) {
					item.setSexName(SexType.getSexType4Code(item.getSex()).getName());
				}
			});
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportMemberBirthDate(OutputStream output, MemberBirthDateParamVO param) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(param.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("会员生日");
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("cardTypeName","会员卡类型");
        rowHeaderMap.put("levelName","级别");
        rowHeaderMap.put("saleAmount","消费金额");
        rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("memberName","会员姓名");
        rowHeaderMap.put("saleAmount","消费金额");
        rowHeaderMap.put("cardCode","会员卡号");
        rowHeaderMap.put("sexName","性别");
        rowHeaderMap.put("birthDate","生日");
        rowHeaderMap.put("idNum","证件号码");
        rowHeaderMap.put("email","邮箱");
        rowHeaderMap.put("saleAmount","消费金额");
        rowHeaderMap.put("qqNum","QQ");
        rowHeaderMap.put("totalIntegral","累计积分");
        rowHeaderMap.put("currentIntegral","当前积分");
        rowHeaderMap.put("totalStoredAmount","累计储值");
        rowHeaderMap.put("currentStoredAmount","储值余额");
        List<MemberBirthDateVO> list = getMemberBirthDateFormat(param);
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, list, headerList, null, null, false, new ArrayList<>());
	}



}
