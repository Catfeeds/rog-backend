package com.rograndec.feijiayun.chain.utils.finance;

import java.util.Calendar;
import java.util.Date;

import com.rograndec.feijiayun.chain.exception.BusinessException;

public class PeriodDateUtils {
	/**
	 * 验证一月份日期是否合法
	 * @param year
	 * @param startDate
	 */
	public static void validFirstMonthStartDate(int year, Date startDate) {
		Calendar a = Calendar.getInstance();
		a.set(year-1, 11, 2, 0, 0, 0);
		Calendar b = Calendar.getInstance();
		b.set(year, 0, 30, 0, 0, 0);
		if(!betweenDate(a.getTime(), b.getTime(), startDate))  throw new BusinessException("1月开始日期非法");
	}
	
	/**
	 * 验证日期是否在某年某月内
	 * @param date
	 * @param year 2013
	 * @param month 十二月为11
	 */
	public static void validBetweenThisMonth( Date date,int year,int month) {
		Calendar a = Calendar.getInstance();
		a.set(year, month,1);
		int day = a.getActualMaximum(Calendar.DATE);
		Calendar b = Calendar.getInstance();
		b.set(year, month,day);
		if(!betweenDate(a.getTime(), b.getTime(), date))  throw new BusinessException("结束日期不在"+(month+1)+"月内");
	}
	
	/**
	 * 判断是否是某月第一天
	 * @param date
	 * @param year
	 * @param month
	 * @return
	 */
	public static boolean validFirstDayOfMonth(Date date,int year ,int month) {
		Calendar a = Calendar.getInstance();
		a.set(year, month,1,0,0,0);
		a.set(Calendar.MILLISECOND, 0);
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    if(a.getTimeInMillis() == c.getTimeInMillis()) return true;
		return false;
	}
	
	/**
	 * 判断是否是某月最后一天
	 * @param date
	 * @param year
	 * @param month
	 * @return
	 */
	public static boolean validLastDayOfMonth(Date date,int year ,int month) {
		Calendar a = Calendar.getInstance();
		a.set(year, month,1,0,0,0);
		a.set(Calendar.MILLISECOND, 0);
		int day = a.getActualMaximum(Calendar.DATE);
		a.set(year, month, day, 0, 0, 0);
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    if(a.getTimeInMillis() == c.getTimeInMillis()) return true;
		return false;
	}

	
	/**
	 *获取某年月第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(year, month,1,0,0,0);
		return a.getTime();
	}
	
	/**
	 * 获取某年月最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(year, month,1);
		int day = a.getActualMaximum(Calendar.DATE);
		Calendar b = Calendar.getInstance();
		b.set(year, month,day);
		return b.getTime();
	}
	
	/**
	 * 获取指定的日期
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getFixDayOfMonth(int year, int month , int day) {
		Calendar a = Calendar.getInstance();
		a.set(year, month,day,0,0,0);
		return a.getTime();
	}
	
	/**
	 * 获取日期的天
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取下一天
	 * @return
	 */
	public static Date getAfterDay(Date date) {
		 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(Calendar.DAY_OF_MONTH, 1);
         return cal.getTime();
	}
	
	
	/**
	 * 验证时期是否在某个时间范围内
	 * @param start 起始日期
	 * @param end 结束日期
	 * @param date 要验证的日期
	 * @return
	 */
	public static boolean betweenDate(Date start,Date end,Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(start);
		a.set(Calendar.HOUR_OF_DAY, 0);
		a.set(Calendar.MINUTE, 0);
		a.set(Calendar.SECOND, 0);
		a.set(Calendar.MILLISECOND, 0);
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
	    Calendar b = Calendar.getInstance();
	    b.setTime(end);
	    b.set(Calendar.HOUR_OF_DAY, 0);
	    b.set(Calendar.MINUTE, 0);
	    b.set(Calendar.SECOND, 0);
	    b.set(Calendar.MILLISECOND, 0);
	    
	    if(c.getTimeInMillis() >= a.getTimeInMillis() && c.getTimeInMillis() <= b.getTimeInMillis())  	return true;
	    return false;
	}
	
}
