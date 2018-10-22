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

import com.rograndec.feijiayun.chain.business.report.finance.account.dao.LedgerReportMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.service.LedgerService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerGroupDateVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;

/**
 * 
 * @ClassName: LedgerServiceImpl   
 * @Description: 应付/应收总账显示
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月8日 下午5:03:47
 */
@Service
public class LedgerServiceImpl implements LedgerService{
	
	@Autowired
	private LedgerReportMapper ledgerReportMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;

	@Override
	public Page<List<LedgerVO>> getLedger(Map<String, Object> map) throws Exception {
		Page<List<LedgerVO>> page = new Page<>();
		List<LedgerVO> list = getLedgerFormat(map);
		page.setResult(list);
		page.setTotalRecord(list.isEmpty() ? 0 : ledgerReportMapper.countLedger(map));
		return page;
	}
	
	private List<LedgerVO> getLedgerFormat(Map<String, Object> mapParam){
		List<LedgerVO> list = ledgerReportMapper.getLedger(mapParam);
		//按日期进行合并数据
		if(list != null && list.size() > 0) {
			for (LedgerVO leg : list) {
				Date orderDate = DateUtils.StringToDate(leg.getOrderDate(), DateStyle.YYYY_MM_DD);
				String month = leg.getMonth() < 10 ? ("0"+leg.getMonth()) : leg.getMonth()+"";
				leg.setShowOrderDate(leg.getYear()+"年"+month+"月");
				String od = DateUtils.DateToString(orderDate, DateStyle.YYYY_MM_DD);
				leg.setOrderDate(od);
				//期初余额 + 借 - 贷 =  期末余额
				BigDecimal startBalance = null;
				Map<String,BigDecimal> startData = ledgerReportMapper.getMinId(leg.getMinId());//期初数据
				Map<String,BigDecimal> endData = ledgerReportMapper.getMaxId(leg.getMaxId());//期末数据
				if(null != startData && startData.size() > 0) {
					startBalance = startData.get("balance").add(startData.get("creditAmount")).subtract(startData.get("debitAmount"));
				}
				BigDecimal endBalance = null;
				if(null != endData && endData.size() > 0) {
					endBalance = endData.get("balance");
				}
				leg.setStartDirection(DirectionUtils.getDirection(startBalance));
				leg.setStartBalance(DirectionUtils.setPositiveNumber(startBalance));
				leg.setEndDirection(DirectionUtils.getDirection(endBalance));
				//设置余额为绝对值
				leg.setEndBalance(DirectionUtils.setPositiveNumber(endBalance));
			}
			//合并单元格
			List<LedgerGroupDateVO> groupList = groupData(list,mapParam);
			for(LedgerGroupDateVO g : groupList) {
				for(LedgerVO l : list) {
					String od = l.getShowOrderDate();
					if(StringUtils.isNotBlank(od) && g.getOrderDate().equals(od)) {
						l.setMerge(true);
						break;
					}
				}
			}
		}
		return list;
	}
	
	public List<LedgerGroupDateVO> groupData(Map<String,Object> map)throws Exception{
		List<LedgerVO> list = getLedgerFormat(map);
		List<LedgerGroupDateVO> groupList = groupData(list,map);
		return groupList;
	}
	
	/**
	 * 
	 * @Description: 分组数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月9日 下午4:03:37 
	 * @param list
	 * @return 
	 * @return List<LedgerGroupDateVO>
	 */
	private List<LedgerGroupDateVO> groupData(List<LedgerVO> list,Map<String, Object> mapParam){
		List<LedgerGroupDateVO> groupList = new ArrayList<>();
		if(list != null) {
			Map<String,List<LedgerVO>> map = new LinkedHashMap<>();
			for (LedgerVO leg : list) {
				String kjdate = leg.getShowOrderDate();
				if (map.containsKey(kjdate)) {
					map.get(kjdate).add(leg);
				} else {
					// 不包含放入临时变量
					List<LedgerVO> tmpList = new ArrayList<>();
					tmpList.add(leg);
					map.put(kjdate, tmpList);
				}
			}
			//设置分组数据
			for(Map.Entry<String, List<LedgerVO>> m : map.entrySet()) {
				LedgerGroupDateVO groupDateVO = new LedgerGroupDateVO();
				groupDateVO.setOrderDate(m.getKey());
				groupDateVO.setList(m.getValue());
				groupList.add(groupDateVO);
			}
			
		}
		return groupList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output, Map<String, Object> map) throws Exception {
		List<LedgerVO> list = getLedgerFormat(map);
		List<LedgerGroupDateVO> groupList = groupData(list,map);
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(Long.valueOf(map.get("enterpriseId")+""));//企业
		List<String> headerList = new ArrayList<>(2);
		headerList.add(enterprise != null ? enterprise.getName() : "");
		if(map.get("accountType").equals(1)) {
			headerList.add("应收账款总账");
		}else {
			headerList.add("应付账款总账");
		}
		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(8);
		rowHeaderMap.put("subAccountCode","供货单位编码");
        rowHeaderMap.put("subAccountName","供货单位名称");
        rowHeaderMap.put("startDirection","期初余额方向");
        rowHeaderMap.put("startBalance","期初余额贷方");
        rowHeaderMap.put("debitAmount","本期发生额借方");
        rowHeaderMap.put("creditAmount","本期发生额贷方");
        rowHeaderMap.put("endDirection","期末余额方向");
        rowHeaderMap.put("endBalance","期末余额余额");
        List<String> excelTitle = new ArrayList<>();
        List<List<LedgerVO>> excelList = new ArrayList<>();
        for(LedgerGroupDateVO group : groupList) {
        	excelTitle.add(group.getOrderDate());
			excelList.add(group.getList());
        }
        purchaseGeneralComponent.commMultipleExcelExport(output,rowHeaderMap,excelList,headerList,excelTitle,null,false,new ArrayList<>());
	}
	
	

}
