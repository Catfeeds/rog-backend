package com.rograndec.feijiayun.chain.common.component;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作为筛选条件的转换工具类
 *dongdong.zhang
 */
public class StartAndEndDate {


    /**
     * 格式： yyyy-MM-dd
     */
    public final static String FMT_DATE = "yyyy-MM-dd";
    /**
     * 格式： yyyy-MM-dd HH:mm:ss
     */
    public final static String FMT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/** 
     * 获取SimpleDateFormat 
     * @param parttern 日期格式 
     * @return SimpleDateFormat对象 
     * @throws RuntimeException 异常：非法日期格式 
     */  
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {  
        return new SimpleDateFormat(parttern);  
    }  
  

    public static Date getStartTime(Date startTime){
    	if(startTime==null) return null;
    	String start=StartAndEndDate.getDateFormat(FMT_DATE).format(startTime)+" 00:00:00";
    	Date date=null;
    	try {
			date= StartAndEndDate.getDateFormat(FMT_DATE_TIME).parse(start);
		} catch (ParseException | RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return date;
    }
    
    public static Date getEndTime(Date endTime){
    	if(endTime==null) return null;
    	String end=StartAndEndDate.getDateFormat(FMT_DATE).format(endTime)+" 23:59:59";
    	Date date=null;
    	try {
			date= StartAndEndDate.getDateFormat(FMT_DATE_TIME).parse(end);
		} catch (ParseException | RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return date;
    }
}
