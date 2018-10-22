package com.rograndec.feijiayun.chain.business.report.finance.tax.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodDetailMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.FinanceVoucherMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.FinanceVoucher;
import com.rograndec.feijiayun.chain.business.report.finance.tax.dao.FinanceReportMapper;
import com.rograndec.feijiayun.chain.business.report.finance.tax.service.FinanceReportService;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.AccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceBalanceResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailDtlResultPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailDtlResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceDetailQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherDetailExcelVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherDetailResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherQueryVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.FinanceVoucherResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerDeatilResultPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerDeatilResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.GeneralLedgerQueryVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccount;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;

@Service
public class FinanceReportServiceImpl implements FinanceReportService{
	
	@Autowired
	private FinanceVoucherMapper financeVoucherMapper;
	
	@Autowired
	private FinanceReportMapper financeReportMapper;
	
	@Autowired
	private AccountingPeriodDetailMapper accountingPeriodDetailMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectFinanceBalanceList(FinanceBalanceQueryVO vo,
			UserVO loginUser) {
		//22210101进项税额
		//22210105销项税额
		//总部看自已店+自营店，加盟店看自己
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		List<FinanceBalanceResultVO> list = null;
		Long totalRecord = 0L;
		if(loginUser.getParentId()==null||loginUser.getParentId()==0){
			list = financeReportMapper.selectHeadquartersFinanceBalanceList(vo, loginUser.getEnterpriseId(),
					ChainType.Selfoperatedshop.getCode(), getAccountCode(), page.getStart(), page.getPageSize());
			totalRecord = financeReportMapper.queryCountHeadquartersFinanceBalance(vo, loginUser.getEnterpriseId(),
					ChainType.Selfoperatedshop.getCode(), getAccountCode());
		}else if(loginUser.getChainType()==ChainType.Division.getCode()){
			list = financeReportMapper.selectDivisionFinanceBalanceList(vo, loginUser.getEnterpriseId(), 
					getAccountCode(), page.getStart(), page.getPageSize());
			totalRecord = financeReportMapper.queryCountDivisionFinanceBalance(vo, loginUser.getEnterpriseId(),
					getAccountCode());
		}
		
		if(list != null && list.size() > 0){
			for (FinanceBalanceResultVO result : list) {
				
				reSetFinanceBalanceResultVO(result);
			}
		}
		page.setResult(list);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}
	
	private String getAccountCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("'").append(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode()).append("','")
			.append(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode()).append("'");
		return sb.toString();
	}

	private void reSetFinanceBalanceResultVO(FinanceBalanceResultVO result) {
//		FinanceVoucher vend = financeVoucherMapper.selectByPrimaryKey(result.getEndBalanceId());
		
//		result.setBalance(vend!=null?vend.getBalance():BigDecimal.ZERO);
		
		result.setTaxRate(StringUtil.deleteZero(result.getTaxRate()));
		
		result.setDirection(result.getBalance().compareTo(BigDecimal.ZERO)==0?"平":
			(result.getBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
		
		if(result.getBalance().compareTo(BigDecimal.ZERO)<0){
			result.setBalance(result.getBalance().multiply(new BigDecimal(-1)));
		}
		
	}
	
	public static String deleteZero(String tempString) {
		  
        int initlen = tempString.length(); // 串的初始长度
        int finallen = initlen; // 串的最终长度
        int start = 0; // 串的开始位置
        int off = 0; // 串的偏移位置
        char[] val = new char[initlen];
        tempString.getChars(0, finallen, val, 0); // 保存原数据，用于判断字符
 
        while ((start < finallen) && (val[off + finallen - 1] == '0' || val[off + finallen - 1] == '.')) {
            finallen--;
        }
        String re = ((start > 0) || (finallen < initlen)) ? tempString.substring(start, finallen): tempString;
        return StringUtils.isBlank(re)?"0":re;
    }

	private BigDecimal setDecimal(BigDecimal d) {
		if(d != null){
			return d.setScale(2,BigDecimal.ROUND_HALF_DOWN);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelFinanceBalance(OutputStream output, UserVO userVO) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("taxTypeName","税类型");
        map.put("taxRate","税率(%)");
        map.put("debitAmount","借方金额");
        map.put("creditAmount","贷方金额");
        map.put("direction","余额方向");
        map.put("balance","余额");
        
        List<FinanceBalanceResultVO> excelList = null;
		if(userVO.getParentId()==null||userVO.getParentId()==0){
			excelList = financeReportMapper.selectHeadquartersFinanceBalanceList(null, userVO.getEnterpriseId(),
					ChainType.Selfoperatedshop.getCode(), getAccountCode(), null, null);
		}else if(userVO.getChainType()==ChainType.Division.getCode()){
			excelList = financeReportMapper.selectDivisionFinanceBalanceList(null, userVO.getEnterpriseId(), 
					getAccountCode(), null, null);
		}
		if(excelList != null && excelList.size() > 0){
			for (FinanceBalanceResultVO result : excelList) {
				
				reSetFinanceBalanceResultVO(result);
			}
		}
        
        StringBuilder end = new StringBuilder();
        List<String> name = new ArrayList<String>();
//      //第一行的企业名
        name.add(userVO.getEnterpriseName());
        //第二行的
        name.add("税余额");
        List<String> list = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output,map,excelList,name,null,end.toString(),false,list);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectGeneralLedgerList(GeneralLedgerQueryVO vo,
			UserVO loginUser) {
		//22210101进项税额
		//22210105销项税额
		//总部看自已店+自营店，加盟店看自己
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		List<GeneralLedgerDeatilResultVO> list = null;
		
		int start = page.getStart();
        int end = page.getPageNo()*page.getPageSize();
        
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, vo.getStartDate(), vo.getEndDate());
		
		List<GeneralLedgerDeatilResultVO> detailList = new ArrayList<>();
		
		int totalCount = 0;
		
		if(periodDetailList != null && periodDetailList.size() > 0){
			
			list = new ArrayList<GeneralLedgerDeatilResultVO>();
			GeneralLedgerDeatilResultVO dtl = null;
			
			for (AccountingPeriodDetailVO periodDetailVO : periodDetailList) {
				
				dtl = new GeneralLedgerDeatilResultVO();
				if(totalCount >= start && totalCount <= end-1){
					dtl.setTaxType(periodDetailVO.getYear() + "年" + periodDetailVO.getMonth() + "月");
					dtl.setTaxId(-10000L);
					detailList.add(dtl);
				}	
				
				Date startDate = getStartDate(periodDetailVO.getStartDate(), vo.getStartDate());
				Date endDate = getEndDate(periodDetailVO.getEndDate(), vo.getEndDate());
				
				if(loginUser.getParentId()==null || loginUser.getParentId()==0){
					
					list = financeReportMapper.selectHeadquartersGeneralLedgerList(startDate, endDate, loginUser.getEnterpriseId(), 
							ChainType.Selfoperatedshop.getCode(), getAccountCode(), null, null);
					
				}else if(loginUser.getChainType()==ChainType.Division.getCode()){
					
					list = financeReportMapper.selectDivisionGeneralLedgerList(startDate, endDate, loginUser.getEnterpriseId(), 
							getAccountCode(), null, null);
				}
				
				if (list != null && list.size() > 0){
					for (GeneralLedgerDeatilResultVO dtlvo : list) {
						
						if(totalCount >= start && totalCount <= end-1){
							//设置期初方向、期初余额、期末余额
							reSetGeneralLedgerDetail(dtlvo);
							detailList.add(dtlvo);
							
						}
						totalCount ++;
							
					}
				}else{
					
					detailList.remove(dtl);
				}
			}
		}

		page.setTotalRecord(totalCount);
        
		page.setResult(detailList);
        /*if(detailList.size()<start || detailList.size()==0){
            page.setResult(new ArrayList<>());
        }else if(detailList.size()>start && detailList.size()<end){
            page.setResult(detailList.subList(start,detailList.size()));
        }else{
            page.setResult(detailList.subList(start,end));
        } */   

        return page;
	}
	
	
	@Override
	public List<GeneralLedgerDeatilResultPrintVO> selectGeneralLedgerPrintList(
			Date startDt, Date endDt, UserVO loginUser) {
		
		List<GeneralLedgerDeatilResultPrintVO> printList = new ArrayList<>();

        List<GeneralLedgerDeatilResultVO> list = null;
        
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, startDt, endDt);
		
		if(periodDetailList != null && periodDetailList.size() > 0){

			for (AccountingPeriodDetailVO periodDetailVO : periodDetailList) {
				
				Date startDate = getStartDate(periodDetailVO.getStartDate(), startDt);
				Date endDate = getEndDate(periodDetailVO.getEndDate(), endDt);
				
				if(loginUser.getParentId()==null || loginUser.getParentId()==0){
					
					list = financeReportMapper.selectHeadquartersGeneralLedgerList(startDate, endDate, loginUser.getEnterpriseId(), 
							ChainType.Selfoperatedshop.getCode(), getAccountCode(), null, null);
					
				}else if(loginUser.getChainType()==ChainType.Division.getCode()){
					
					list = financeReportMapper.selectDivisionGeneralLedgerList(startDate, endDate, loginUser.getEnterpriseId(), 
							getAccountCode(), null, null);
				}
				
				if(list != null && list.size() > 0){
					
					for (GeneralLedgerDeatilResultVO dtlvo : list) {
						//设置期初方向、期初余额、期末余额
						reSetGeneralLedgerDetail(dtlvo);
						
						dtlvo.setShowOrderDate(periodDetailVO.getYear()+"年"+periodDetailVO.getMonth()+"月");
					}
					
					GeneralLedgerDeatilResultPrintVO print = new GeneralLedgerDeatilResultPrintVO(periodDetailVO.getYear()+"年"+periodDetailVO.getMonth()+"月");
					print.setPrintVOList(list);
					printList.add(print);
				}
						
			}
				
		}
		
		return printList;
	}
	
	
	private void reSetGeneralLedgerDetail(GeneralLedgerDeatilResultVO dtlvo) {
		
		/*FinanceVoucher vbegin = financeVoucherMapper.selectByPrimaryKey(dtlvo.getBeginBalanceId());
		
		dtlvo.setBeginBalance((vbegin!=null?vbegin.getBalance():BigDecimal.ZERO).add(
				vbegin.getCreditAmount()).subtract(vbegin.getDebitAmount()));*/

		dtlvo.setTaxRate(StringUtil.deleteZero(dtlvo.getTaxRate()));
		
		dtlvo.setBeginBalance(getBeginBalanceByBeginBalanceId(dtlvo.getBeginBalanceId()));

		FinanceVoucher vend = financeVoucherMapper.selectByPrimaryKey(dtlvo.getEndBalanceId());
		
		dtlvo.setEndBalance(vend!=null?vend.getBalance():BigDecimal.ZERO);
		
		dtlvo.setBeginDirection(dtlvo.getBeginBalance().compareTo(BigDecimal.ZERO)==0?"平":
			(dtlvo.getBeginBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
		
		dtlvo.setEndDirection(dtlvo.getEndBalance().compareTo(BigDecimal.ZERO)==0?"平":
			(dtlvo.getEndBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
		
		if(dtlvo.getBeginBalance().compareTo(BigDecimal.ZERO)<0){
			dtlvo.setBeginBalance(dtlvo.getBeginBalance().multiply(new BigDecimal(-1)));
		}
		
		if(dtlvo.getEndBalance().compareTo(BigDecimal.ZERO)<0){
			dtlvo.setEndBalance(dtlvo.getEndBalance().multiply(new BigDecimal(-1)));
		}
	}

	private List<AccountingPeriodDetailVO> getPeriodDetail(UserVO loginUser, Date startDate, Date endDate){
		return accountingPeriodDetailMapper.selectByEnterpriseId(loginUser.getEnterpriseId(), startDate, endDate);
	}
	
	private Date getEndDate(Date perEndDate, Date voEndDate) {
		if(voEndDate == null || perEndDate.getTime() < voEndDate.getTime()){
			return perEndDate;
		}else{
			return voEndDate;
		}	
	}

	private Date getStartDate(Date perstartDate, Date voStartDate) {
		if(voStartDate == null || perstartDate.getTime() > voStartDate.getTime()){
			return perstartDate;
		}else{
			return voStartDate;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelGeneralLedger(OutputStream output,
			Date startDt, Date endDt, UserVO loginUser) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("taxTypeName","税类型");
        map.put("taxRate","税率(%)");
        map.put("beginDirection","期初余额方向");
        map.put("beginBalance","期初余额金额");
        map.put("debitAmount","本期发生额借方");
        map.put("creditAmount","本期发生额贷方");
        map.put("endDirection","期末余额方向");
        map.put("endBalance","期末余额金额");
        
        List<String> excelTitle = new ArrayList<>();
        
        List<List<GeneralLedgerDeatilResultVO>> excelList = new ArrayList<>();

        List<GeneralLedgerDeatilResultVO> list = null;
        
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, startDt, endDt);
		
		if(periodDetailList != null && periodDetailList.size() > 0){

			for (AccountingPeriodDetailVO periodDetailVO : periodDetailList) {
				
				Date startDate = getStartDate(periodDetailVO.getStartDate(), startDt);
				Date endDate = getEndDate(periodDetailVO.getEndDate(), endDt);
				
				if(loginUser.getParentId()==null || loginUser.getParentId()==0){
					
					list = financeReportMapper.selectHeadquartersGeneralLedgerList(startDate, endDate, loginUser.getEnterpriseId(), 
							ChainType.Selfoperatedshop.getCode(), getAccountCode(), null, null);
					
				}else if(loginUser.getChainType()==ChainType.Division.getCode()){
					
					list = financeReportMapper.selectDivisionGeneralLedgerList(startDate, endDate, loginUser.getEnterpriseId(), 
							getAccountCode(), null, null);
				}
				
				if(list != null && list.size() > 0){
					
					for (GeneralLedgerDeatilResultVO dtlvo : list) {
						//设置期初方向、期初余额、期末余额
						reSetGeneralLedgerDetail(dtlvo);
							
					}
				 		
					excelTitle.add(periodDetailVO.getYear()+"年"+periodDetailVO.getMonth()+"月");
					excelList.add(list);
				}
						
			}
				
		}
		StringBuilder end = new StringBuilder();
		List<String> name = new ArrayList<String>();
		//第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("税总账");
        List<String> list2 = new ArrayList<>();
        purchaseGeneralComponent.commMultipleExcelExport(output,map,excelList,name,excelTitle,end.toString(),false,list2);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectFinanceDetailList(FinanceDetailQueryVO vo,
			UserVO loginUser) {

		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		int start = page.getStart();
        int end = page.getPageNo()*page.getPageSize();
        
        List<FinanceDetailDtlResultVO> resultList = new ArrayList<>();
        List<FinanceDetailDtlResultVO> list = null;
        
        //获取会计期间
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, vo.getStartDate(), vo.getEndDate());
		
		//获取所有符合条件的数据，按税类型和税率分组
		List<FinanceBalanceResultVO> balanceList = getAccountSubList(loginUser, vo);
		
		if(balanceList != null){
			
			FinanceDetailDtlResultVO dtl = null;
			
			for (FinanceBalanceResultVO fbr : balanceList) {
				
				dtl = new FinanceDetailDtlResultVO();
				if(resultList.size() >= start && resultList.size() <= end){
					dtl.setOrderDate(fbr.getTaxTypeName()+"-"+fbr.getTaxRate()+"%");
					dtl.setBaseOrderType(-10000);
					/*FinanceVoucher vbegin = financeVoucherMapper.selectByPrimaryKey(fbr.getBeginBalanceId());
					dtl.setBalance((vbegin!=null?vbegin.getBalance():BigDecimal.ZERO).add(
							vbegin.getCreditAmount()).subtract(vbegin.getDebitAmount()));*/
					dtl.setBalance(getBeginBalanceByBeginBalanceId(fbr.getBeginBalanceId()));
					
					dtl.setDirection(dtl.getBalance().compareTo(BigDecimal.ZERO)==0?"平":
						(dtl.getBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
					dtl.setBalance(dtl.getBalance().abs());
				}	
				resultList.add(dtl);
				
				list = getFinanceDetailList(vo, loginUser, fbr, list);
				
				boolean balanceHasData = false; //税类型+税率分组是否包含数据
//				boolean yearHasData = false; //是否加本年累计
				BigDecimal debitAmountMonthTotal = BigDecimal.ZERO;//借方金额本年累计
				BigDecimal creditAmountMonthTotal = BigDecimal.ZERO;//贷方金额本年累计
				
				for (int i=0; i<periodDetailList.size(); i++) {
					
					boolean accountingPeriodHasData = false; //当前会计期间是否包含数据
					
					BigDecimal debitAmountMonth = BigDecimal.ZERO;//借方金额本月合计
					BigDecimal creditAmountMonth = BigDecimal.ZERO;//贷方金额本月合计
					
					FinanceDetailDtlResultVO dtl1 = null;
					
					for (FinanceDetailDtlResultVO finDtlRtl : list) {
						
						//数据在会计期间范围内
						if(DirectionUtils.isInDateScope(periodDetailList.get(i).getStartDate(), periodDetailList.get(i).getEndDate(), finDtlRtl.getOrderDate())){
							//设置年月日值
							if(resultList.size() >= start && resultList.size() <= end){
								setFinDtlRtl(finDtlRtl);
								resultList.add(finDtlRtl);
							}else{
								resultList.add(null);
							}	
							
							debitAmountMonth = debitAmountMonth.add(finDtlRtl.getDebitAmount());
							creditAmountMonth = creditAmountMonth.add(finDtlRtl.getCreditAmount());
							debitAmountMonthTotal = debitAmountMonthTotal.add(finDtlRtl.getDebitAmount());
							creditAmountMonthTotal = creditAmountMonthTotal.add(finDtlRtl.getCreditAmount());
							
							balanceHasData = true;
//							yearHasData = true;
							accountingPeriodHasData = true;
						}
					}
					
					if(accountingPeriodHasData){
						//增加本月合计
						if(resultList.size() >= start && resultList.size() <= end){
							dtl1 = setThisMonthData(dtl1, debitAmountMonth, creditAmountMonth);
						}	
						resultList.add(dtl1);
						//增加本年累计						
						if(resultList.size() >= start && resultList.size() <= end){
							dtl1 = setThisYearData(dtl1, debitAmountMonthTotal, creditAmountMonthTotal);
						}	
						resultList.add(dtl1);
					}
					
					if((periodDetailList.get(i).getMonth() == 12 || i == periodDetailList.size()-1 ||
							!periodDetailList.get(i+1).getYear().equals(periodDetailList.get(i).getYear()))){
						debitAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
						creditAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
					}
					
					//增加本年累计
					/*if((periodDetailList.get(i).getMonth() == 12 || i == periodDetailList.size()-1 ||
							!periodDetailList.get(i+1).getYear().equals(periodDetailList.get(i).getYear())) && yearHasData){
						if(resultList.size() >= start && resultList.size() <= end){
							dtl1 = setThisYearData(dtl1, debitAmountMonthTotal, creditAmountMonthTotal);
						}	
						resultList.add(dtl1);
						debitAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
						creditAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
						yearHasData = false;
					}*/
					
				}
				
				if(!balanceHasData){
					resultList.remove(dtl);
				}/*else{
					if(yearHasData){
						FinanceDetailDtlResultVO dtl1 = null;
						if(resultList.size() >= start && resultList.size() <= end){
							dtl1 = setThisYearData(null, debitAmountMonthTotal, creditAmountMonthTotal);
						}
						resultList.add(dtl1);
					}
				}*/
			}
		}
		
		page.setTotalRecord(resultList.size());
        
        if(resultList.size()<start || resultList.size()==0){
            page.setResult(new ArrayList<>());
        }else if(resultList.size()>start && resultList.size()<end){
            page.setResult(resultList.subList(start,resultList.size()));
        }else{
            page.setResult(resultList.subList(start,end));
        }  
		
		return page;
	}
	
	private List<FinanceDetailDtlResultVO> getFinanceDetailList(
			FinanceDetailQueryVO vo, UserVO loginUser,
			FinanceBalanceResultVO fbr, List<FinanceDetailDtlResultVO> list) {
		list = new ArrayList<FinanceDetailDtlResultVO>();
		if(loginUser.getParentId()==null||loginUser.getParentId()==0){
			
			list = financeReportMapper.selectHeadquartersFinanceDetailList(vo, loginUser.getEnterpriseId(), 
					ChainType.Selfoperatedshop.getCode(), fbr.getTaxType(), fbr.getTaxId());
			
		}else if(loginUser.getChainType()==ChainType.Division.getCode()){
			
			list = financeReportMapper.selectDivisionFinanceDetailList(vo, loginUser.getEnterpriseId(), 
					fbr.getTaxType(), fbr.getTaxId());
		
		}
		return list;
	}

	private void setFinDtlRtl(FinanceDetailDtlResultVO finDtlRtl) {
		
		String[] orderDate = StringUtils.isNotBlank(finDtlRtl.getOrderDate())
				?finDtlRtl.getOrderDate().split("-"):null;
		finDtlRtl.setYear(orderDate==null||orderDate.length==0?0:Integer.parseInt(orderDate[0]));
		finDtlRtl.setMonth(orderDate==null||orderDate.length<2?"00":orderDate[1]);
		finDtlRtl.setDay(orderDate==null||orderDate.length<3?"00":orderDate[2]);
		finDtlRtl.setBaseOrderTypeName(OrderRule.getName(finDtlRtl.getBaseOrderType()));
		finDtlRtl.setDirection(finDtlRtl.getBalance().compareTo(BigDecimal.ZERO)==0?"平":
			(finDtlRtl.getBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
		finDtlRtl.setBalance(finDtlRtl.getBalance().abs());
		
	}


	private List<FinanceBalanceResultVO> getAccountSubList(
			UserVO loginUser, FinanceDetailQueryVO vo) {
		List<FinanceBalanceResultVO> balanceList = new ArrayList<>();
		
		if(loginUser.getParentId()==null||loginUser.getParentId()==0){
			balanceList = financeReportMapper.selectHeadquartersAccountSubList(vo, loginUser.getEnterpriseId(),
					ChainType.Selfoperatedshop.getCode(), getAccountCode(), null, null);
		}else if(loginUser.getChainType()==ChainType.Division.getCode()){
			balanceList = financeReportMapper.selectDivisionAccountSubList(vo, loginUser.getEnterpriseId(), 
					getAccountCode(), null, null);
		}
		return balanceList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelFinanceDetail(OutputStream output,
			FinanceDetailQueryVO vo, UserVO loginUser) {
		
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("orderDate","凭证日期");
        map.put("orderCode","凭证号码");
        map.put("remark","摘要");
        map.put("debitAmount","借方金额");
        map.put("creditAmount","贷方金额");
        map.put("direction","余额方向");
        map.put("balance","余额金额");
        
        List<String> excelTitle = new ArrayList<>();
		
		List<List<FinanceDetailDtlResultVO>> excelList = new ArrayList<List<FinanceDetailDtlResultVO>>();
		
		List<FinanceDetailDtlResultVO> resultList = null;
        List<FinanceDetailDtlResultVO> list = null;
        
        //获取会计期间
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, vo.getStartDate(), vo.getEndDate());
		
		//获取所有符合条件的数据，按税类型和税率分组
		List<FinanceBalanceResultVO> balanceList = getAccountSubList(loginUser, vo);
		
		if(balanceList != null){
			
			FinanceDetailDtlResultVO dtl = null;
			
			for (FinanceBalanceResultVO fbr : balanceList) {
				
				resultList = new ArrayList<>();
				
				dtl = new FinanceDetailDtlResultVO();
				dtl.setRemark("期初余额");
				dtl.setBalance(getBeginBalanceByBeginBalanceId(fbr.getBeginBalanceId()));
				dtl.setDirection(dtl.getBalance().compareTo(BigDecimal.ZERO)==0?"平":
					(dtl.getBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
				dtl.setBalance(dtl.getBalance().abs());
				resultList.add(dtl);
				
				list = getFinanceDetailList(vo, loginUser, fbr, list);
				
				boolean balanceHasData = false; //税类型+税率分组是否包含数据
				BigDecimal debitAmountMonthTotal = BigDecimal.ZERO;//借方金额本年累计
				BigDecimal creditAmountMonthTotal = BigDecimal.ZERO;//贷方金额本年累计
				
				for (int i=0; i<periodDetailList.size(); i++) {
					
					boolean accountingPeriodHasData = false; //当前会计期间是否包含数据
					
					BigDecimal debitAmountMonth = BigDecimal.ZERO;//借方金额本月合计
					BigDecimal creditAmountMonth = BigDecimal.ZERO;//贷方金额本月合计
					
					FinanceDetailDtlResultVO dtl1 = null;
					
					for (FinanceDetailDtlResultVO finDtlRtl : list) {
						
						//数据在会计期间范围内
						if(DirectionUtils.isInDateScope(periodDetailList.get(i).getStartDate(), periodDetailList.get(i).getEndDate(), finDtlRtl.getOrderDate())){
							//设置年月日值
							setFinDtlRtl(finDtlRtl);
							resultList.add(finDtlRtl);
							
							debitAmountMonth = debitAmountMonth.add(finDtlRtl.getDebitAmount());
							creditAmountMonth = creditAmountMonth.add(finDtlRtl.getCreditAmount());
							debitAmountMonthTotal = debitAmountMonthTotal.add(finDtlRtl.getDebitAmount());
							creditAmountMonthTotal = creditAmountMonthTotal.add(finDtlRtl.getCreditAmount());
							
							balanceHasData = true;
							accountingPeriodHasData = true;
						}
					}
					
					//增加本月合计
					if(accountingPeriodHasData){
						dtl1 = setThisMonthData(dtl1, debitAmountMonth, creditAmountMonth);
						resultList.add(dtl1);
						
						//增加本年累计						
						dtl1 = setThisYearData(dtl1, debitAmountMonthTotal, creditAmountMonthTotal);
						resultList.add(dtl1);
					}
					
					if((periodDetailList.get(i).getMonth() == 12 || i == periodDetailList.size()-1 ||
							!periodDetailList.get(i+1).getYear().equals(periodDetailList.get(i).getYear()))){
						debitAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
						creditAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
					}
					
					/*//增加本年累计
					if(periodDetailList.get(i).getMonth() == 12 && accountingPeriodHasData){
						dtl1 = setThisYearData(dtl1, debitAmountMonthTotal, creditAmountMonthTotal);
						resultList.add(dtl1);
						debitAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
						creditAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
					}*/
					
				}
				
				
				if(balanceHasData){
					//增加本年累计
					/*if(debitAmountMonthTotal.compareTo(BigDecimal.ZERO)!=0 
							|| creditAmountMonthTotal.compareTo(BigDecimal.ZERO)!=0){
						FinanceDetailDtlResultVO dtl1 = setThisYearData(null, debitAmountMonthTotal, creditAmountMonthTotal);
						resultList.add(dtl1);
					}*/
					
					String title = "税类型："+fbr.getTaxTypeName()+"  税率："+StringUtil.deleteZero(fbr.getTaxRate())+"%";
					excelTitle.add(title);
					excelList.add(resultList);
				}
				
			}
		}
		
		StringBuilder end = new StringBuilder();
		List<String> name = new ArrayList<String>();
		//第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("税明细账");
        List<String> list2 = new ArrayList<>();
        purchaseGeneralComponent.commMultipleExcelExport(output,map,excelList,name,excelTitle,end.toString(),false,list2);
		
	}
	
	
	@Override
	public List<FinanceDetailDtlResultPrintVO> selectFinanceDetailNewPrintList(
			FinanceDetailQueryVO vo, UserVO loginUser) {
		List<FinanceDetailDtlResultPrintVO> excelList = new ArrayList<FinanceDetailDtlResultPrintVO>();
		
		List<FinanceDetailDtlResultVO> resultList = null;
        List<FinanceDetailDtlResultVO> list = null;
        
        //获取会计期间
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, vo.getStartDate(), vo.getEndDate());
		
		//获取所有符合条件的数据，按税类型和税率分组
		List<FinanceBalanceResultVO> balanceList = getAccountSubList(loginUser, vo);
		
		if(balanceList != null){
			
			FinanceDetailDtlResultVO dtl = null;
			
			for (FinanceBalanceResultVO fbr : balanceList) {
				
				resultList = new ArrayList<>();
				
				dtl = new FinanceDetailDtlResultVO();
				dtl.setRemark("期初余额");
				dtl.setBalance(getBeginBalanceByBeginBalanceId(fbr.getBeginBalanceId()));
				dtl.setDirection(dtl.getBalance().compareTo(BigDecimal.ZERO)==0?"平":
					(dtl.getBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
				dtl.setBalance(dtl.getBalance().abs());
				resultList.add(dtl);
				
				list = getFinanceDetailList(vo, loginUser, fbr, list);
				
				boolean balanceHasData = false; //税类型+税率分组是否包含数据
				BigDecimal debitAmountMonthTotal = BigDecimal.ZERO;//借方金额本年累计
				BigDecimal creditAmountMonthTotal = BigDecimal.ZERO;//贷方金额本年累计
				
				for (int i=0; i<periodDetailList.size(); i++) {
					
					boolean accountingPeriodHasData = false; //当前会计期间是否包含数据
					
					BigDecimal debitAmountMonth = BigDecimal.ZERO;//借方金额本月合计
					BigDecimal creditAmountMonth = BigDecimal.ZERO;//贷方金额本月合计
					
					FinanceDetailDtlResultVO dtl1 = null;
					
					for (FinanceDetailDtlResultVO finDtlRtl : list) {
						
						//数据在会计期间范围内
						if(DirectionUtils.isInDateScope(periodDetailList.get(i).getStartDate(), periodDetailList.get(i).getEndDate(), finDtlRtl.getOrderDate())){
							//设置年月日值
							setFinDtlRtl(finDtlRtl);
							resultList.add(finDtlRtl);
							
							debitAmountMonth = debitAmountMonth.add(finDtlRtl.getDebitAmount());
							creditAmountMonth = creditAmountMonth.add(finDtlRtl.getCreditAmount());
							debitAmountMonthTotal = debitAmountMonthTotal.add(finDtlRtl.getDebitAmount());
							creditAmountMonthTotal = creditAmountMonthTotal.add(finDtlRtl.getCreditAmount());
							
							balanceHasData = true;
							accountingPeriodHasData = true;
						}
					}
					
					//增加本月合计
					if(accountingPeriodHasData){
						dtl1 = setThisMonthData(dtl1, debitAmountMonth, creditAmountMonth);
						resultList.add(dtl1);
						
						//增加本年累计						
						dtl1 = setThisYearData(dtl1, debitAmountMonthTotal, creditAmountMonthTotal);
						resultList.add(dtl1);
					}
					
					if((periodDetailList.get(i).getMonth() == 12 || i == periodDetailList.size()-1 ||
							!periodDetailList.get(i+1).getYear().equals(periodDetailList.get(i).getYear()))){
						debitAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
						creditAmountMonthTotal = BigDecimal.ZERO;//重置本年累计
					}
					
				}
				
				
				if(balanceHasData){
					
					String title = "税类型："+fbr.getTaxTypeName()+"  税率："+StringUtil.deleteZero(fbr.getTaxRate())+"%";
					
					FinanceDetailDtlResultPrintVO print = new FinanceDetailDtlResultPrintVO(title);
					
					print.setResultList(resultList);
					
					excelList.add(print);
				}
				
			}
		}
		return excelList;
	}
	

	private FinanceDetailDtlResultVO setThisYearData(FinanceDetailDtlResultVO dtl1,
			BigDecimal debitAmountMonthTotal, BigDecimal creditAmountMonthTotal) {
		dtl1 = new FinanceDetailDtlResultVO();
		dtl1.setRemark("本年累计");
		dtl1.setDebitAmount(debitAmountMonthTotal);
		dtl1.setCreditAmount(creditAmountMonthTotal);
		return dtl1;
	}

	private FinanceDetailDtlResultVO setThisMonthData(FinanceDetailDtlResultVO dtl1, BigDecimal debitAmountMonth, 
			BigDecimal creditAmountMonth) {
		dtl1 = new FinanceDetailDtlResultVO();
		dtl1.setRemark("本月合计");
		dtl1.setDebitAmount(debitAmountMonth);
		dtl1.setCreditAmount(creditAmountMonth);
		return dtl1;
	}

	private BigDecimal getBeginBalanceByBeginBalanceId(Long beginBalanceId) {
		FinanceVoucher vbegin = financeVoucherMapper.selectByPrimaryKey(beginBalanceId);
		return vbegin!=null?vbegin.getBalance().add(
				vbegin.getCreditAmount()).subtract(vbegin.getDebitAmount()):BigDecimal.ZERO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectFinanceVoucherList(FinanceVoucherQueryVO vo,
			UserVO loginUser) {
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		Long totalRecord = 0L;
		
		List<FinanceVoucherResultVO> list = new ArrayList<>();
		
		if(loginUser.getParentId()==null || loginUser.getParentId()==0){
			
			list = financeReportMapper.selectHeadquartersFinanceVoucherList(vo, loginUser.getEnterpriseId(), 
					ChainType.Selfoperatedshop.getCode(), page.getStart(), page.getPageSize());
			totalRecord = financeReportMapper.queryCountHeadquartersFinanceVoucher(vo, loginUser.getEnterpriseId(),
					ChainType.Selfoperatedshop.getCode());
			
		} else if(loginUser.getChainType()==ChainType.Division.getCode()){
			
			list = financeReportMapper.selectDivisionFinanceVoucherList(vo, loginUser.getEnterpriseId(), 
					page.getStart(), page.getPageSize());
			totalRecord = financeReportMapper.queryCountDivisionFinanceVoucher(vo, loginUser.getEnterpriseId());
		}
		
		if(list != null){
			for (FinanceVoucherResultVO financeVoucherResultVO : list) {
				if(financeVoucherResultVO.getDetailList()!=null){
					for (FinanceVoucherDetailResultVO detail : financeVoucherResultVO.getDetailList()) {
						if(detail.getSubAccountType() != null && detail.getSubAccountType() == 5){//税
							detail.setSubAccountName(StringUtil.deleteZero(detail.getSubAccountName())+"%");
						}
					}
				}
				financeVoucherResultVO.setBaseOrderTypeName(OrderRule.getName(financeVoucherResultVO.getBaseOrderType()));
			}
		}
		
		page.setResult(list);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcelFinanceVoucherList(OutputStream output,
			FinanceVoucherQueryVO vo, UserVO loginUser) {
		
		List<FinanceVoucherResultVO> list = new ArrayList<>();
		
		if(loginUser.getParentId()==null || loginUser.getParentId()==0){
			
			list = financeReportMapper.selectHeadquartersFinanceVoucherList(vo, loginUser.getEnterpriseId(), 
					ChainType.Selfoperatedshop.getCode(), null, null);
			
		} else if(loginUser.getChainType()==ChainType.Division.getCode()){
			
			list = financeReportMapper.selectDivisionFinanceVoucherList(vo, loginUser.getEnterpriseId(), 
					null, null);
		}
		
		List<List<FinanceVoucherDetailExcelVO>> excelList = new ArrayList<>();
		List<FinanceVoucherDetailExcelVO> excelDetailList = null;
		List<String> excelTitle = new ArrayList<>();
		StringBuilder sb = null;
		
		if(list != null){
			for (FinanceVoucherResultVO result : list) {
				
				sb = new StringBuilder();
				sb.append(DateUtils.getYear(result.getOrderDate())).append("年")
					.append(DateUtils.getMonth(DateUtils.StringToDate(result.getOrderDate(),DateStyle.YYYY_MM_DD.getValue()))+1).append("月")
						.append(DateUtils.getDay(DateUtils.StringToDate(result.getOrderDate(),DateStyle.YYYY_MM_DD.getValue()))).append("日       ")
							.append(";").append("                               字第  号");
				
				excelTitle.add(sb.toString());
				
				excelDetailList = new ArrayList<>();
				
				setExcelDetailList(result, excelDetailList);
				
				excelList.add(excelDetailList);
			}
		}
		
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("remark","摘要");
        map.put("accountName","总账科目");
        map.put("subAccountName","明细科目");
        map.put("debitAmount","借方金额");
        map.put("creditAmount","贷方金额");
        map.put("symbol","记账符号");
        
        StringBuilder end = new StringBuilder();
        end.append("会计主管：      ").append("记帐：      ").append("出纳：      ")
        	.append("审核：      ").append("制单：      ");
		List<String> name = new ArrayList<String>();
		//第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("记账凭证");
        List<String> list2 = new ArrayList<>();
        purchaseGeneralComponent.commFinanceVoucherListExcelExport(output,map,excelList,name,excelTitle,end.toString(),true,list2);
		
	}

	private void setExcelDetailList(FinanceVoucherResultVO result,
			List<FinanceVoucherDetailExcelVO> excelDetailList) {
		
		List<FinanceVoucherDetailResultVO> detailResultList = result.getDetailList();
		
		if(detailResultList != null){
			
			FinanceVoucherDetailExcelVO excel = null;
			
			BigDecimal debitAmountMonth = BigDecimal.ZERO;//借方金额本月合计
			BigDecimal creditAmountMonth = BigDecimal.ZERO;//贷方金额本月合计
			
			for (FinanceVoucherDetailResultVO financeVoucherDetailExcelVO : detailResultList) {
				excel = new FinanceVoucherDetailExcelVO();
				
				excel.setAccountCode(financeVoucherDetailExcelVO.getAccountCode());
				excel.setAccountName(financeVoucherDetailExcelVO.getAccountName());
				excel.setSubAccountCode(financeVoucherDetailExcelVO.getSubAccountCode());
				
				if(financeVoucherDetailExcelVO.getSubAccountType() != null && financeVoucherDetailExcelVO.getSubAccountType() == 5){//税
					excel.setSubAccountName(StringUtil.deleteZero(financeVoucherDetailExcelVO.getSubAccountName())+"%");
				}else{
					excel.setSubAccountName(financeVoucherDetailExcelVO.getSubAccountName());
				}
				
				excel.setDebitAmount(financeVoucherDetailExcelVO.getDebitAmount());
				excel.setCreditAmount(financeVoucherDetailExcelVO.getCreditAmount());
				excel.setRemark(result.getRemark());
				excel.setSymbol("√");
				
				debitAmountMonth = debitAmountMonth.add(financeVoucherDetailExcelVO.getDebitAmount());
				creditAmountMonth = creditAmountMonth.add(financeVoucherDetailExcelVO.getCreditAmount());
				
				excelDetailList.add(excel);
			}
			//增加合计
			excel = new FinanceVoucherDetailExcelVO();
			excel.setRemark("附件   张");
			excel.setAccountName("合计");
			excel.setDebitAmount(debitAmountMonth);
			excel.setCreditAmount(creditAmountMonth);
			
			excelDetailList.add(excel);
		}
		
	}

	@Override
	public List<SelectBean> getFinanceAccountSelectBean() {
		List<SelectBean> list = new ArrayList<SelectBean>();
		SelectBean bean = new SelectBean(); 
		bean.setId(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode());
		bean.setText(FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountName());
		list.add(bean);
		bean = new SelectBean(); 
		bean.setId(FinanceAccount.SALE_TAX_AMOUNT.getAccountCode());
		bean.setText(FinanceAccount.SALE_TAX_AMOUNT.getAccountName());
		list.add(bean);
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page selectFinanceDetailNewList(FinanceDetailQueryVO vo,
			UserVO loginUser) {
		
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		List<FinanceDetailDtlResultVO> list = null;
		
		//获取会计期间
        List<AccountingPeriodDetailVO> periodDetailList = getPeriodDetail(loginUser, vo.getStartDate(), vo.getEndDate());
//      Map<Integer, AccountingPeriodDetailVO> periodYearMap = new HashMap<>();
        Map<String, AccountingPeriodDetailVO> periodMonthMap = new HashMap<>();
        if(periodDetailList != null){
			periodDetailList.forEach( m->{
				periodMonthMap.put(m.getYear()+"-"+m.getMonth(), m);
				/*if(!periodYearMap.containsKey(m.getYear())){
					periodYearMap.put(m.getYear(), setYearPeriod(m.getYear(), periodDetailList));
				}*/
			});
		}
        
        Map<Integer, AccountingPeriodDetailVO> periodYearMap = setYearPeriod(periodDetailList);
        
		List<FinanceDetailDtlResultVO> newList = new ArrayList<>();
		
		Long totalCount = 0L;
		list = new ArrayList<FinanceDetailDtlResultVO>();
		if(loginUser.getParentId()==null||loginUser.getParentId()==0){
			
			//start取法：当第一页时，start取0，非第一页时start取到上一页最后一条，为判断当前页第一行是否显示：税类型+税率-余额方向
			//pageSize取法：pageSize取到下一页第一条，为判断当前页最后一行是否显示本月合计、本年累计
			
			list = financeReportMapper.selectHeadquartersFinanceDetaiNewlList(vo, loginUser.getEnterpriseId(), 
					ChainType.Headquarters.getCode(), getAccountCode(), page.getStart()==0?page.getStart():page.getStart()-1, page.getStart()==0?page.getPageSize()+1:page.getPageSize()+2);
			
			totalCount = financeReportMapper.queryCountHeadquartersFinanceDetaiNewlList(vo, loginUser.getEnterpriseId(), 
					ChainType.Headquarters.getCode(), getAccountCode());
			
		}else if(loginUser.getChainType()==ChainType.Division.getCode()){
			
			list = financeReportMapper.selectHeadquartersFinanceDetaiNewlList(vo, loginUser.getEnterpriseId(), 
					ChainType.Division.getCode(), getAccountCode(), page.getStart()==0?page.getStart():page.getStart()-1, page.getStart()==0?page.getPageSize()+1:page.getPageSize()+2);
		
			totalCount = financeReportMapper.queryCountHeadquartersFinanceDetaiNewlList(vo, loginUser.getEnterpriseId(), 
					ChainType.Division.getCode(), getAccountCode());
		}
		
		if(list != null && list.size() > 0){
			
			FinanceDetailDtlResultVO dtl = null;
			
			Map<String,String> map = new HashMap<String, String>();
			
			//非第一页时，map中加上上一页最后一条的：税类型+税率
			//为判断当前页第一行是否显示：税类型+税率-余额方向
			if(page.getStart() != 0){
				map.put(list.get(0).getTaxTypeName()+"-"+StringUtil.deleteZero(list.get(0).getTaxRate())+"%", "");
			}
			
			//第一页时，从0开始循环；其他页从第一条开始循环
			//最后一页时，i<list.size()；其他页i<list.size()-1
			for (int i = page.getStart()==0?0:1; i < (page.getStart()+page.getPageSize()>=totalCount?list.size():list.size()-1); i++) {
				dtl = new FinanceDetailDtlResultVO();
				
				FinanceDetailDtlResultVO resultvo = list.get(i);
				
				String key = resultvo.getTaxTypeName()+"-"+StringUtil.deleteZero(resultvo.getTaxRate())+"%";
				
				if(!map.containsKey(key) || (page.getStart() == 0 && i == 0)){
					dtl.setOrderDate(resultvo.getTaxTypeName()+"-"+StringUtil.deleteZero(resultvo.getTaxRate())+"%");
					dtl.setBaseOrderType(-10000);
					dtl.setBalance(resultvo.getBalance().add(resultvo.getCreditAmount()).subtract(resultvo.getDebitAmount()));
					
					dtl.setDirection(dtl.getBalance().compareTo(BigDecimal.ZERO)==0?"平":
						(dtl.getBalance().compareTo(BigDecimal.ZERO)>0?"借":"贷"));//期末方向
					dtl.setBalance(dtl.getBalance().abs());
					newList.add(dtl);
					map.put(key, key);
				}
				
				setFinDtlRtl(resultvo);
				
				newList.add(resultvo);
				
				if((page.getStart() + page.getPageSize() >= totalCount && i == list.size()-1) ||
						!list.get(i+1).getTaxType().equals(resultvo.getTaxType()) ||
						!list.get(i+1).getTaxId().equals(resultvo.getTaxId()) || 
						!list.get(i+1).getPeriodYear().equals(resultvo.getPeriodYear()) ||
						!list.get(i+1).getPeriodMonth().equals(resultvo.getPeriodMonth())){
					dtl = setNewThisMonthData(dtl, periodMonthMap, vo, resultvo, loginUser);
					newList.add(dtl);
					dtl = setNewThisYearData(dtl, periodMonthMap, periodYearMap, vo, resultvo, loginUser);
					newList.add(dtl);
				}
			}
		}
		
		page.setResult(newList);
		
		page.setTotalRecord(totalCount.intValue());
		
		return page;
	}


	private FinanceDetailDtlResultVO setNewThisYearData(
			FinanceDetailDtlResultVO dtl, Map<String, AccountingPeriodDetailVO> periodMonthMap,
			Map<Integer, AccountingPeriodDetailVO> periodYearMap,
			FinanceDetailQueryVO vo, FinanceDetailDtlResultVO resultvo, UserVO loginUser) {
		
		AccountingPeriodDetailVO dtlYearvo = periodYearMap.get(resultvo.getPeriodYear());
		
		AccountingPeriodDetailVO dtlMonthvo = periodMonthMap.get(resultvo.getPeriodYear()+"-"+resultvo.getPeriodMonth());
		
		//开始时间使用年度开始时间与用户输入开始时间对比，取大的
		Date startDate = getStartDate(dtlYearvo.getStartDate(), vo.getStartDate());
		//结束时间使用月度结束时间与用户输入结束时间对比，取小的
		Date endDate = getEndDate(dtlMonthvo.getEndDate(), vo.getEndDate());
		
		FinanceDetailDtlResultVO result = financeReportMapper.getSumAmount(vo, startDate, endDate, getAccountCode(), 
				loginUser.getEnterpriseId(), loginUser.getChainType(), resultvo);
		
		dtl = new FinanceDetailDtlResultVO();
		dtl.setRemark("本年累计");
		dtl.setDebitAmount(result.getDebitAmount());
		dtl.setCreditAmount(result.getCreditAmount());
		
		return dtl;
	}
	
	private FinanceDetailDtlResultVO setNewThisMonthData(
			FinanceDetailDtlResultVO dtl, Map<String, AccountingPeriodDetailVO> periodMonthMap,
			FinanceDetailQueryVO vo, FinanceDetailDtlResultVO resultvo, UserVO loginUser) {
		
		AccountingPeriodDetailVO dtlvo = periodMonthMap.get(resultvo.getPeriodYear()+"-"+resultvo.getPeriodMonth());
		
		Date startDate = getStartDate(dtlvo.getStartDate(), vo.getStartDate());
		Date endDate = getEndDate(dtlvo.getEndDate(), vo.getEndDate());
		
		FinanceDetailDtlResultVO result = financeReportMapper.getSumAmount(vo, startDate, endDate, getAccountCode(), 
				loginUser.getEnterpriseId(), loginUser.getChainType(), resultvo);
		
		dtl = new FinanceDetailDtlResultVO();
		dtl.setRemark("本月合计");
		dtl.setDebitAmount(result.getDebitAmount());
		dtl.setCreditAmount(result.getCreditAmount());
		
		return dtl;
	}

	private Map<Integer, AccountingPeriodDetailVO> setYearPeriod(
			List<AccountingPeriodDetailVO> periodDetailList) {
		Map<Integer, AccountingPeriodDetailVO> periodYearMap = new HashMap<>();
		Map<Integer, List<AccountingPeriodDetailVO>> periodDetail = periodDetailList.stream().collect(Collectors.groupingBy(AccountingPeriodDetailVO::getYear));  
		for (Entry<Integer, List<AccountingPeriodDetailVO>> vo : periodDetail.entrySet()) {
			AccountingPeriodDetailVO min = vo.getValue().stream().collect(Collectors.minBy(Comparator.comparingInt(AccountingPeriodDetailVO::getMonth))).get();
			AccountingPeriodDetailVO max = vo.getValue().stream().collect(Collectors.maxBy(Comparator.comparingInt(AccountingPeriodDetailVO::getMonth))).get();
			AccountingPeriodDetailVO newDetail = new AccountingPeriodDetailVO();
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(min, newDetail);
			newDetail.setEndDate(max.getEndDate());
			periodYearMap.put(vo.getKey(), newDetail);
		}
		return periodYearMap;
	}
	
	private static AccountingPeriodDetailVO setYearPeriod(Integer year,
			List<AccountingPeriodDetailVO> periodDetailList) {
		AccountingPeriodDetailVO min = periodDetailList.stream().filter(m->{
			return m.getYear().equals(year);
		}).collect(Collectors.minBy(Comparator.comparingInt(AccountingPeriodDetailVO::getMonth))).get();
		AccountingPeriodDetailVO max = periodDetailList.stream().filter(m->{
			return m.getYear().equals(year);
		}).collect(Collectors.maxBy(Comparator.comparingInt(AccountingPeriodDetailVO::getMonth))).get();
		AccountingPeriodDetailVO newDetail = new AccountingPeriodDetailVO();
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(min, newDetail);
		newDetail.setEndDate(max.getEndDate());
		return newDetail;
	}
	
	public static void main(String[] args) {
		List<AccountingPeriodDetailVO> periodDetailList = new ArrayList<>();
		AccountingPeriodDetailVO vo = new AccountingPeriodDetailVO();
		vo.setYear(2017);
		vo.setMonth(1);
		vo.setId(1L);
		periodDetailList.add(vo);
		
		vo = new AccountingPeriodDetailVO();
		vo.setYear(2017);
		vo.setMonth(2);
		vo.setId(2L);
		periodDetailList.add(vo);
		
		vo = new AccountingPeriodDetailVO();
		vo.setYear(2017);
		vo.setMonth(3);
		vo.setId(3L);
		periodDetailList.add(vo);
		
		vo = new AccountingPeriodDetailVO();
		vo.setYear(2018);
		vo.setMonth(1);
		vo.setId(11L);
		periodDetailList.add(vo);
		
		vo = new AccountingPeriodDetailVO();
		vo.setYear(2018);
		vo.setMonth(2);
		vo.setId(12L);
		periodDetailList.add(vo);
		
		vo = new AccountingPeriodDetailVO();
		vo.setYear(2018);
		vo.setMonth(3);
		vo.setId(13L);
		periodDetailList.add(vo);
		
		vo = new AccountingPeriodDetailVO();
		vo.setYear(2018);
		vo.setMonth(3);
		vo.setId(14L);
		periodDetailList.add(vo);
		
		/*for (AccountingPeriodDetailVO o : periodDetailList) {
			System.out.println(o.getId());
			System.out.println(o.getYear());
			System.out.println(o.getMonth());
		}*/
		
		AccountingPeriodDetailVO min = periodDetailList.stream().filter(m->{
			return m.getYear() == 2018;
		}).collect(Collectors.minBy(Comparator.comparingInt(AccountingPeriodDetailVO::getMonth))).get();
		AccountingPeriodDetailVO max = periodDetailList.stream().filter(m->{
			return m.getYear() == 2018;
		}).collect(Collectors.maxBy(Comparator.comparingInt(AccountingPeriodDetailVO::getMonth))).get();

		System.out.println("===========================");
		
		System.out.println(min.getId());
		System.out.println(min.getYear());
		System.out.println(min.getMonth());
		
		System.out.println(max.getId());
		System.out.println(max.getYear());
		System.out.println(max.getMonth());
		
		AccountingPeriodDetailVO v = setYearPeriod(2018, periodDetailList);
		
		System.out.println(v.getId());
		System.out.println(v.getYear());
		System.out.println(v.getMonth());
	}

	@Override
	public List<NewSelectBean> getOrderTypeSelectBean(UserVO loginUser) {
		List<NewSelectBean> list = new ArrayList<NewSelectBean>();
		NewSelectBean bean = new NewSelectBean(); 
		bean.setId(null);
		bean.setText("");
		list.add(bean);
		
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.OPENING_INVENTORY.getType().longValue());
		bean.setText(OrderRule.OPENING_INVENTORY.getTypeName());
		list.add(bean);
		
		//期初应收
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.OPENING_RECEIVABLE.getType().longValue());
		bean.setText(OrderRule.OPENING_RECEIVABLE.getTypeName());
		list.add(bean);
		
		//期初税额
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.OPENING_TAX.getType().longValue());
		bean.setText(OrderRule.OPENING_TAX.getTypeName());
		list.add(bean);
		
		//采购入库
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.PURCHASE_IN.getType().longValue());
		bean.setText(OrderRule.PURCHASE_IN.getTypeName());
		list.add(bean);
		
		//购进退出出库
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.PURCHASE_RETURN_OUT.getType().longValue());
		bean.setText(OrderRule.PURCHASE_RETURN_OUT.getTypeName());
		list.add(bean);
		
		//配货出库
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.DISTR_OUT.getType().longValue());
		bean.setText(OrderRule.DISTR_OUT.getTypeName());
		list.add(bean);
		
		//配后退回入库
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.DISTR_RETURN_IN.getType().longValue());
		bean.setText(OrderRule.DISTR_RETURN_IN.getTypeName());
		list.add(bean);
		
		//货位移动
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.SHELF_MOVE.getType().longValue());
		bean.setText(OrderRule.SHELF_MOVE.getTypeName());
		list.add(bean);
		
		//其他入库
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.RECEIVE.getType().longValue());
		bean.setText(OrderRule.RECEIVE.getTypeName());
		list.add(bean);
		
		//其他出库
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.SEND.getType().longValue());
		bean.setText(OrderRule.SEND.getTypeName());
		list.add(bean);
		
		//中药装斗
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.LOAD_BUCKET.getType().longValue());
		bean.setText(OrderRule.LOAD_BUCKET.getTypeName());
		list.add(bean);
		
		//中药清斗
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.CLEAR_BUCKET.getType().longValue());
		bean.setText(OrderRule.CLEAR_BUCKET.getTypeName());
		list.add(bean);
		
		//药品拆零
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.SPLIT.getType().longValue());
		bean.setText(OrderRule.SPLIT.getTypeName());
		list.add(bean);
		
		//药品销毁
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.DESTROY.getType().longValue());
		bean.setText(OrderRule.DESTROY.getTypeName());
		list.add(bean);
		
		//盘点
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.INVENTORY.getType().longValue());
		bean.setText(OrderRule.INVENTORY.getTypeName());
		list.add(bean);
		
		//预付发票
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.PREPAY_INVOICE.getType().longValue());
		bean.setText(OrderRule.PREPAY_INVOICE.getTypeName());
		list.add(bean);
		
		//预付发票
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.PAYMENT_INVOICE.getType().longValue());
		bean.setText(OrderRule.PAYMENT_INVOICE.getTypeName());
		list.add(bean);
		
		//应付贷项凭证
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.PAYMENT_VOUCHER.getType().longValue());
		bean.setText(OrderRule.PAYMENT_VOUCHER.getTypeName());
		list.add(bean);
		
		//预收发票
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getType().longValue());
		bean.setText(OrderRule.ADVANCE_RECEIVABLE_INVOICE.getTypeName());
		list.add(bean);
		
		//应收发票
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.RECEIVABLE_INVOICE.getType().longValue());
		bean.setText(OrderRule.RECEIVABLE_INVOICE.getTypeName());
		list.add(bean);
		
		//应收贷项凭证
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.RECEIVABLE_VOUCHER.getType().longValue());
		bean.setText(OrderRule.RECEIVABLE_VOUCHER.getTypeName());
		list.add(bean);
		
		//收款
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.FINANCE_RECEIVABLE.getType().longValue());
		bean.setText(OrderRule.FINANCE_RECEIVABLE.getTypeName());
		list.add(bean);
		
		//付款
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.FINANCE_PAYMENT.getType().longValue());
		bean.setText(OrderRule.FINANCE_PAYMENT.getTypeName());
		list.add(bean);
		
		//零售日结
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.RETAIL_DAILY_SETTLE.getType().longValue());
		bean.setText(OrderRule.RETAIL_DAILY_SETTLE.getTypeName());
		list.add(bean);
		
		//零售缴款
		bean = new NewSelectBean(); 
		bean.setId(OrderRule.RETAIL_PAYMENT.getType().longValue());
		bean.setText(OrderRule.RETAIL_PAYMENT.getTypeName());
		list.add(bean);
				
		
		return list;
	}


}
