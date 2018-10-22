package com.rograndec.feijiayun.chain.business.report.finance.account.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.finance.account.dao.FinanceBalanceReportMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.service.FinanceBalanceService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.FinanceBalanceTotalVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.FinanceBalanceVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;

/**
 * 
 * @ClassName: FinanceBalanceServiceImpl   
 * @Description: 应付账款/应收账款接口
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月5日 下午1:33:43
 */
@Service
public class FinanceBalanceServiceImpl implements FinanceBalanceService{
	
	@Autowired
	private FinanceBalanceReportMapper financeBalanceReportMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;
	
	@Override
	public Page<FinanceBalanceTotalVO> getBalanceList(Map<String, Object> map) throws Exception{
		Page<FinanceBalanceTotalVO> page = new Page<>();
		Integer count = financeBalanceReportMapper.countFinanceBalance(map);
		FinanceBalanceTotalVO fbTotal = getFinanceBalanceFormat(map);
		page.setResult(fbTotal==null?new FinanceBalanceTotalVO():fbTotal);
		page.setTotalRecord(count);
		return page;
	}
	
	private FinanceBalanceTotalVO getFinanceBalanceFormat(Map<String, Object> map){
		//处理排序字段
		String order = map.get("order")+"";
		String sort = map.get("sort")+"";
		if ("subAccountCode".equals(order)) {
			map.put("order", "subAccountCode");
		} else if ("subAccountName".equals(order)) {
			map.put("order", "subAccountName");
		} else {
			map.put("order", null);
		}
		if (null != sort) {
			if (!sort.equals("desc") && !sort.equals("asc")) {
				map.put("sort", null);
			}
		}
		List<FinanceBalanceVO> list = financeBalanceReportMapper.getFinanceBalance(map);
		FinanceBalanceTotalVO fbTotal = financeBalanceReportMapper.sumFinanceBalance(map);
		if(fbTotal != null && list != null) {
			fbTotal.setList(list);
			BigDecimal debitAmountSubtotal = BigDecimal.ZERO;
			BigDecimal creditAmountTSubtotal = BigDecimal.ZERO;
			BigDecimal balanceSubtotal = BigDecimal.ZERO;
			for(FinanceBalanceVO l : list) {
				l.setDirection(DirectionUtils.getDirection(l.getBalance()));
				l.setBalance(DirectionUtils.setPositiveNumber(l.getBalance()));
				debitAmountSubtotal = debitAmountSubtotal.add(l.getDebitAmount());
				creditAmountTSubtotal = creditAmountTSubtotal.add(l.getCreditAmount());
				balanceSubtotal = balanceSubtotal.add(l.getBalance());
			}
			//小计
			fbTotal.setDebitAmountSubtotal(debitAmountSubtotal);
			fbTotal.setCreditAmountTSubtotal(creditAmountTSubtotal);
			fbTotal.setBalanceSubtotal(balanceSubtotal);
			//合计添加方向
			fbTotal.setDirection(DirectionUtils.getDirection(fbTotal.getBalanceTotal()));
			fbTotal.setBalanceTotal(DirectionUtils.setPositiveNumber(fbTotal.getBalanceTotal()));
		}
		return fbTotal;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output,Map<String, Object> map) throws Exception{
		FinanceBalanceTotalVO fbTotal = getFinanceBalanceFormat(map);
		/*if(fbTotal == null) {
			throw new BusinessException("无数据可导出!");
		}*/
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(Long.valueOf(map.get("enterpriseId")+""));//企业
		List<String> headerList = new ArrayList<>(2);
		headerList.add(enterprise.getName());
		if(map.get("accountType").equals(1)) {
			headerList.add("应收账款余额");
		}else {
			headerList.add("应付账款余额");
		}
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(6);
		rowHeaderMap.put("subAccountCode","供货单位编码");
        rowHeaderMap.put("subAccountName","供货单位名称");
        rowHeaderMap.put("debitAmount","借方");
        rowHeaderMap.put("creditAmount","贷方");
        rowHeaderMap.put("direction","方向");
        rowHeaderMap.put("balance","余额");
        List<String> needTotalName = new ArrayList<>(3);
        needTotalName.add("debitAmount");
        needTotalName.add("creditAmount");
        needTotalName.add("balance");
        StringBuilder sb = new StringBuilder();
        sb.append(fbTotal.getDebitAmountTotal()).append(";")
        .append(fbTotal.getCreditAmountTotal()).append(";")
        .append(fbTotal.getBalanceTotal()).append(";");
        purchaseGeneralComponent.commExcelExport(output, rowHeaderMap, fbTotal.getList(),
        		headerList, null, sb.toString(), false, needTotalName);
	}

}
