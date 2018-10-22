package com.rograndec.feijiayun.chain.business.report.finance.account.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.finance.account.dao.AgingYFReportMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.dao.AgingYSReportMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.service.AgingService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingGroupVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 * 
 * @ClassName: AgingServiceImpl   
 * @Description: 财务管理-应付/应收账款-应付/应收账龄分析
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月15日 下午5:21:20
 */
@Service
public class AgingServiceImpl implements AgingService{
	
	@Autowired
	private AgingYFReportMapper agingYFReportMapper;
	
	@Autowired
	private AgingYSReportMapper agingYSReportMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;

	@Override
	public Page<List<AgingVO>> getAging(Map<String, Object> map) throws Exception {
		Integer count = 0;
		String accountType = map.get("accountType")+"";
		if(accountType.equals("0")) {
			count = agingYFReportMapper.countAgingYFDtl(map);
		}
		if(accountType.equals("1")) {
			count = agingYSReportMapper.countAgingYSDtl(map);
		}
		List<AgingVO> list = getAgingFormat(map);
		Page<List<AgingVO>> page = new Page<>();
		page.setResult(list);
		page.setTotalRecord(count);
		return page;
	}
	
	private List<AgingVO> getAgingFormat(Map<String, Object> map){
		List<AgingVO> list = new ArrayList<>();
		String accountType = map.get("accountType")+"";
		if(accountType.equals("0")) {
			list = agingYFReportMapper.getAgingYFDtl(map);
		}
		if(accountType.equals("1")) {
			list = agingYSReportMapper.getAgingYSDtl(map);
		}
		
		BigDecimal accountPeriodA = BigDecimal.ZERO;
		BigDecimal accountPeriodB = BigDecimal.ZERO;
		BigDecimal accountPeriodC = BigDecimal.ZERO;
		BigDecimal accountPeriodD = BigDecimal.ZERO;
		BigDecimal accountPeriodE = BigDecimal.ZERO;
		for(AgingVO l : list) {
			judgePeriod(l);
			accountPeriodA = accountPeriodA.add(l.getAccountPeriodA());
			accountPeriodB = accountPeriodB.add(l.getAccountPeriodB());
			accountPeriodC = accountPeriodC.add(l.getAccountPeriodC());
			accountPeriodD = accountPeriodD.add(l.getAccountPeriodD());
			accountPeriodE = accountPeriodE.add(l.getAccountPeriodE());
			if(StringUtils.isNotBlank(l.getOrderDate()) && l.getOrderDate().contains("合计")) {
				l.setAccountPeriodA(accountPeriodA);
				l.setAccountPeriodB(accountPeriodB);
				l.setAccountPeriodC(accountPeriodC);
				l.setAccountPeriodD(accountPeriodD);
				l.setAccountPeriodE(accountPeriodE);
				accountPeriodA = BigDecimal.ZERO;
				accountPeriodB = BigDecimal.ZERO;
				accountPeriodC = BigDecimal.ZERO;
				accountPeriodD = BigDecimal.ZERO;
				accountPeriodE = BigDecimal.ZERO;
			}
		}
		
		//处理前台是否合并
		List<AgingGroupVO> groupList = groupData(list);
		for (AgingGroupVO ag : groupList) {
			for (AgingVO a : list) {
				if (a.getSupplierCode().equals(ag.getSupplierCode())) {
					a.setMerge(true);
					break;
				}
			}
		}
		return list;
	}
	
	//设置账期
	private void judgePeriod(AgingVO agingVO) {
		String orderDate = agingVO.getOrderDate();
		if(StringUtils.isNotBlank(orderDate) && !orderDate.contains("合计")) {
			Date od = DateUtils.StringToDate(orderDate, DateStyle.YYYY_MM_DD);
			String odstr = DateUtils.DateToString(od, DateStyle.YYYY_MM_DD);
			agingVO.setOrderDate(odstr);
			Long day = DateUtils.diffInDays(od, new Date());
			BigDecimal unclearAmountTotal  = agingVO.getUnclearAmountTotal();
			if(day <= 30) {
				agingVO.setAccountPeriodA(unclearAmountTotal);
			}
			if(day >= 31 && day <= 60) {
				agingVO.setAccountPeriodB(unclearAmountTotal);
			}
			if(day >= 61 && day <= 90) {
				agingVO.setAccountPeriodC(unclearAmountTotal);
			}
			if(day >= 91 && day <= 120) {
				agingVO.setAccountPeriodD(unclearAmountTotal);
			}
			if(day > 120) {
				agingVO.setAccountPeriodE(unclearAmountTotal);
			}
		}
	}
	
	public List<AgingGroupVO> groupData(Map<String, Object> map){
		List<AgingVO> list = getAgingFormat(map);
		List<AgingGroupVO> groupList = groupData(list);
		return groupList;
	}
	
	private List<AgingGroupVO> groupData(List<AgingVO> list){
		Map<String, List<AgingVO>> map = new LinkedHashMap<>();
		for(AgingVO ag : list) {
			String key = ag.getSupplierCode()+"-"+ag.getSupplierName();
			if(map.containsKey(key)) {
				map.get(key).add(ag);
			} else {
				List<AgingVO> tmpList = new ArrayList<>();
				tmpList.add(ag);
				map.put(key, tmpList);
			}
		}
		//设置分组数据
		List<AgingGroupVO> groupList = new ArrayList<>();
		for(Map.Entry<String, List<AgingVO>> m : map.entrySet()) {
			AgingGroupVO ag = new AgingGroupVO();
			ag.setSupplierCode(getCodeOrName(m.getKey(), 0));
			ag.setSupplierName(getCodeOrName(m.getKey(), 1));
			ag.setList(m.getValue());
			groupList.add(ag);
		}
		return groupList;
	}
	
	private String getCodeOrName(String str,int i) {
		String name = "";
		if(StringUtils.isNoneBlank(str) && str.contains("-")) {
			String[] split = str.split("-");
			name = split[i];
		}
		return name;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output, Map<String, Object> map) throws Exception {
		List<AgingVO> list = getAgingFormat(map);
		List<AgingGroupVO> groupList = groupData(list);
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(Long.valueOf(map.get("enterpriseId")+""));//企业
		List<String> headerList = new ArrayList<>(2);
		headerList.add(enterprise != null ? enterprise.getName() : "");
		String supName = "供货";
		if(map.get("accountType").equals(1)) {
			headerList.add("应收账龄分析");
			supName = "购货";
		}else {
			headerList.add("应付账龄分析");
		}
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(8);
		rowHeaderMap.put("orderDate","单据日期");
        rowHeaderMap.put("orderCode","单据编号");
        rowHeaderMap.put("orderTypeName","单据类型");
        rowHeaderMap.put("orderAmountTotal","单据金额");
        rowHeaderMap.put("clearAmountTotal","已清金额");
        rowHeaderMap.put("unclearAmountTotal","未清金额");
        rowHeaderMap.put("accountPeriodA","账期-(0-30)");
        rowHeaderMap.put("accountPeriodB","账期-(31-60)");
        rowHeaderMap.put("accountPeriodC","账期-(61-90)");
        rowHeaderMap.put("accountPeriodD","账期-(91-120)");
        rowHeaderMap.put("accountPeriodE","账期-(120+)");
        List<String> excelTitle = new ArrayList<>();
        List<List<AgingVO>> excelList = new ArrayList<>();
        for(AgingGroupVO group : groupList) {
        	String title = supName+"单位编码："+group.getSupplierCode()+"  "+supName+"单位名称："+group.getSupplierName();
        	excelTitle.add(title);
			excelList.add(group.getList());
        }
        purchaseGeneralComponent.commMultipleExcelExport(output,rowHeaderMap,excelList,headerList,excelTitle,null,false,new ArrayList<>());
	}
	
	
	
}
