package com.rograndec.feijiayun.chain.business.report.finance.account.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.finance.account.dao.AccountsDtlReportMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.service.AccountsDtlService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AccountsDtlGroupCodeVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AccountsDtlVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;

/**
 * 
 * @ClassName: AccountsDtlServiceImpl   
 * @Description: 应付/应收账款明细账分组数据
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月12日 下午2:02:43
 */
@Service
public class AccountsDtlServiceImpl implements AccountsDtlService{
	
	@Autowired
	private AccountsDtlReportMapper accountsDtlReportMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;

	
	@Override
	public Page<List<AccountsDtlVO>> getAccountsDtl(Map<String, Object> map) throws Exception {
		Page<List<AccountsDtlVO>> page = new Page<>();
		List<AccountsDtlVO> list = getAccountsDtlFormat(map);
		page.setResult(list);
		page.setTotalRecord(accountsDtlReportMapper.countAccountsDtl(map));
		return page;
	}
	
	public List<AccountsDtlGroupCodeVO> groupData(Map<String,Object> map)throws Exception {
		List<AccountsDtlVO> list = getAccountsDtlFormat(map);
		List<AccountsDtlGroupCodeVO> groupList = groupData(list,map);
		return groupList;
	}
	
	private List<AccountsDtlVO> getAccountsDtlFormat(Map<String, Object> map){
		List<AccountsDtlVO> list = accountsDtlReportMapper.getAccountsDtl(map);
		if(list != null && list.size() > 0) {
			list.forEach(a->{
				if(StringUtils.isNoneBlank(a.getOrderDate())) {
					Date orderDate = DateUtils.StringToDate(a.getOrderDate(), DateStyle.YYYY_MM_DD);
					String od = DateUtils.DateToString(orderDate, DateStyle.YYYY_MM_DD);
					a.setOrderDate(od);
				}
				//设置期初、本月合计，本年累计
				if(StringUtils.isNotBlank(a.getRemark())) {
					if(a.getRemark().contains("期初余额")) {
						a.setDebitAmount(null);
						a.setCreditAmount(null);
						a.setMerge(true);
					}
					if(a.getRemark().contains("本")) {
						a.setBalance(null);
					}
				}
				
				//设置余额为绝对值
				a.setDirection(DirectionUtils.getDirection(a.getBalance()));
				a.setBalance(DirectionUtils.setPositiveNumber(a.getBalance()));
				//设置年月日
				a.setYear(getNyr(a.getOrderDate(), 0));
				a.setMonth(getNyr(a.getOrderDate(), 1));
				a.setDay(getNyr(a.getOrderDate(), 2));
			});
		}
		return list;
	}
	
	private List<AccountsDtlGroupCodeVO> groupData(List<AccountsDtlVO> list,Map<String, Object> mapParam){
		List<AccountsDtlGroupCodeVO> groupList = new ArrayList<>();
		if(list != null) {
			Map<String,List<AccountsDtlVO>> map = new LinkedHashMap<>();
			//按供应商合并数据
			for (AccountsDtlVO dtl : list) {
				String codeAndName = dtl.getSubAccountCode() + "-" + dtl.getSubAccountName();
				if (!dtl.isMerge() &&map.containsKey(codeAndName)) {
					map.get(codeAndName).add(dtl);
				} else {
					// 不包含放入临时变量
					List<AccountsDtlVO> tmpList = new ArrayList<>();
					tmpList.add(dtl);
					map.put(codeAndName, tmpList);
				}
			}
			//设置分组数据
			for(Map.Entry<String, List<AccountsDtlVO>> m : map.entrySet()) {
				AccountsDtlGroupCodeVO gdVO = new AccountsDtlGroupCodeVO();
				gdVO.setSubAccountCode(getCodeOrName(m.getKey(), 0));
				gdVO.setSubAccountName(getCodeOrName(m.getKey(), 1));
				gdVO.setList(m.getValue());
				groupList.add(gdVO);
			}
			
		}
		return groupList;
	} 
	
	private String getNyr(String date,int i) {
		String m = "";
		if(StringUtils.isNoneBlank(date) && date.contains("-")) {
			String[] split = date.split("-");
			m = split[i];
		}
		return m;
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
		List<AccountsDtlVO> list = getAccountsDtlFormat(map);
		List<AccountsDtlGroupCodeVO> groupList = groupData(list,map);
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(Long.valueOf(map.get("enterpriseId")+""));//企业
		List<String> headerList = new ArrayList<>(2);
		headerList.add(enterprise != null ? enterprise.getName() : "");
		if(map.get("accountType").equals(1)) {
			headerList.add("应收账款明细账");
		}else {
			headerList.add("应付账款明细账");
		}
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(8);
		rowHeaderMap.put("orderDate","日期");
        rowHeaderMap.put("orderCode","凭证号码");
        rowHeaderMap.put("remark","摘要");
        rowHeaderMap.put("debitAmount","借方");
        rowHeaderMap.put("creditAmount","贷方");
        rowHeaderMap.put("direction","方向");
        rowHeaderMap.put("balance","余额");
        List<String> excelTitle = new ArrayList<>();
        List<List<AccountsDtlVO>> excelList = new ArrayList<>();
        for(AccountsDtlGroupCodeVO group : groupList) {
        	String title = "供货单位编码："+group.getSubAccountCode()+"  供货单位名称："+group.getSubAccountName();
        	excelTitle.add(title);
			excelList.add(group.getList());
        }
        purchaseGeneralComponent.commMultipleExcelExport(output,rowHeaderMap,excelList,headerList,excelTitle,null,false,new ArrayList<>());
	}
	
	
	
}
