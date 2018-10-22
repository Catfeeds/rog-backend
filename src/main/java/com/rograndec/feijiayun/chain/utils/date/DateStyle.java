package com.rograndec.feijiayun.chain.utils.date;

/**
 * 
 * @ClassName: DateStyle  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月24日 下午6:00:12  
 *
 */
public enum DateStyle {  
      
    MM_DD("MM-dd"),  
    YYYY_MM("yyyy-MM"),  
    YYYY_MM_DD("yyyy-MM-dd"),  
    MM_DD_HH_MM("MM-dd HH:mm"),  
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),  
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),  
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_SS_("yyyy-MM-dd HH-mm-ss"),

    MM_DD_EN("MM/dd"),  
    YYYY_MM_EN("yyyy/MM"),  
    YYYY_MM_DD_EN("yyyy/MM/dd"),  
    MM_DD_HH_MM_EN("MM/dd HH:mm"),  
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),  
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),  
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_SS_EN_("yyyy/MM/dd HH/mm/ss"),

    MM_DD_CN("MM月dd日"),  
    YYYY_MM_CN("yyyy年MM月"),  
    YYYY_MM_DD_CN("yyyy年MM月dd日"),  
    MM_DD_HH_MM_CN("MM月dd日 HH:mm"),  
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),  
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),  
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),



    YYYYMMDD("yyyyMMdd"),
    YYYYMMDDHHMMSS("yyyyMMdd HHmmss"),

    YYYYMMDD_("yyyy.MM.dd"),
    YYYYMMDDHHMMSS_("yyyy.MM.dd HH.mm.ss"),

      
    HH_MM("HH:mm"),  
    HH_MM_SS("HH:mm:ss"),

    DD_MM_YY("dd/MM/yy"),
    MM_DD_YY("MM/dd/yy"),
    ;



    private String value;  
      
    DateStyle(String value) {  
       this.value = value;  
    }  
      
    public String getValue() {  
       return value;  
    }  
} 