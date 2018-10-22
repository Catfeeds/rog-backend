package com.rograndec.feijiayun.chain.utils.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.AccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 *
 * @ClassName: DirectionUtils
 * @Description: 财务模块 - 平,借,贷 方向
 * @author yuting.li
 * @version 1.0
 * @date 2018年1月8日 下午10:01:35
 */
public class DirectionUtils {

    /**
     * 正则验证金额格式的表现模式
     */
    private static Pattern AMOUNT_PATTERN = Pattern.compile("^(\\-)?(0|([1-9][0-9]*))(\\.\\d+)?$");

	/**
	 *
	 * @Description: 获取平,借,贷 方向
	 * @author yuting.li
	 * @version 1.0
	 * @date 2018年1月8日 下午10:05:23
	 * @param balance
	 * @return
	 * @return String
	 */
	public static String getDirection(BigDecimal balance) {
		String direction = null;
		if(balance != null) {
			if(balance.compareTo(BigDecimal.ZERO) == 0) {
				direction = "平";
			}
			if(balance.compareTo(BigDecimal.ZERO) == 1) {
				direction = "借";
			}
			if(balance.compareTo(BigDecimal.ZERO) == -1) {
				direction = "贷";
			}
		}
		return direction;
	}

	/**
	 *
	 * @Description: 绝对值
	 * @author yuting.li
	 * @version 1.0
	 * @date 2018年1月10日 下午1:56:29
	 * @param balance
	 * @return
	 * @return BigDecimal
	 */
	public static BigDecimal setPositiveNumber(BigDecimal balance) {
		BigDecimal positiveNumber = null;
		if(balance != null) {
			positiveNumber = balance.abs();
		}
		return positiveNumber;
	}


	/**
	 *
	 * @Description: 与会计期间开始时间比较
	 * @author yuting.li
	 * @version 1.0
	 * @date 2018年1月10日 下午6:22:35
	 * @param perEndDate
	 * @param voEndDate
	 * @return
	 * @return Date
	 */
	public static Date getStartDate(Date perstartDate, Date voStartDate) {
		if(voStartDate == null || perstartDate.getTime() > voStartDate.getTime()){
			return perstartDate;
		}else{
			return voStartDate;
		}
	}

	/**
	 *
	 * @Description: 与会计期间结束时间比较
	 * @author yuting.li
	 * @version 1.0
	 * @date 2018年1月10日 下午6:23:08
	 * @param perEndDate
	 * @param voEndDate
	 * @return
	 * @return Date
	 */
	public static Date getEndDate(Date perEndDate, Date voEndDate) {
		if(voEndDate == null || perEndDate.getTime() < voEndDate.getTime()){
			return perEndDate;
		}else{
			return voEndDate;
		}
	}

	/**
	 *
	 * @Description: 判断日期属于那个会计期间
	 * @author yuting.li
	 * @version 1.0
	 * @date 2018年1月11日 下午2:09:00
	 * @param apList
	 * @param between
	 * @return
	 * @return String
	 */
	public static String getBetweenDate(List<AccountingPeriodDetailVO> apList,Date between) {
		if(apList != null && apList.size() > 0) {
			for(AccountingPeriodDetailVO ap : apList) {
				Date start = DateUtils.StringToDate(DateUtils.DateToString(ap.getStartDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
			    Date end = DateUtils.StringToDate(DateUtils.DateToString(ap.getEndDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
			    between = DateUtils.StringToDate(DateUtils.DateToString(between, DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
			    //判断日期是在那个会计区间
			    if(between.getTime() >= start.getTime() && between.getTime() <= end.getTime()) {
			    	String month = ap.getMonth() < 10 ? ("0"+ap.getMonth()) : ap.getMonth()+"";
			    	return ap.getYear()+"年"+month+"月";
			    }
			}
		}
	    return null;
	}


	/**
	 *
	 * @Description: 判断日期属于那个会计期间
	 * @author dongyang.du
	 * @version 1.0
	 * @date 2018年1月11日 下午2:09:00
	 * @param apList
	 * @param between
	 * @return
	 * @return String
	 */
	public static AccountingPeriodDetailVO getBetweenPeriodDetail(List<AccountingPeriodDetailVO> apList,Date between) {
		if(apList != null && apList.size() > 0) {
			for(AccountingPeriodDetailVO ap : apList) {
				Date start = DateUtils.StringToDate(DateUtils.DateToString(ap.getStartDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
				Date end = DateUtils.StringToDate(DateUtils.DateToString(ap.getEndDate(), DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
				between = DateUtils.StringToDate(DateUtils.DateToString(between, DateStyle.YYYY_MM_DD), DateStyle.YYYY_MM_DD);
				//判断日期是在那个会计区间
				if(between.getTime() >= start.getTime() && between.getTime() <= end.getTime()) {
					return ap;
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @Description: 判断字符串是否是正确的金额格式
	 * @author zhangyu
	 * @version 1.0
	 * @date 2018年1月17日
	 * @param amount
	 * @return boolean
	 */
	public static boolean validAmount(String amount) {
        boolean flag = AMOUNT_PATTERN.matcher(amount).matches();
        //去除所有零为负的数（'-0'）
        if (flag && amount.startsWith("-") && BigDecimal.ZERO.compareTo(new BigDecimal(amount)) == 0) {
			flag = false;
		}
		return flag;
	}
	public static boolean isInDateScope(Date startDate, Date endDate, String orderDate) {
		Date d2 = DateUtils.StringToDate(orderDate, DateStyle.YYYY_MM_DD);
		if(DateUtils.diffInDays(startDate, d2)>=0 && DateUtils.diffInDays(d2, endDate)>=0){
			return true;
		}
		return false;
	}


}
