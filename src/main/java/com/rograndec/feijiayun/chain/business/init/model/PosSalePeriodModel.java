package com.rograndec.feijiayun.chain.business.init.model;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSalePeriod;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: PosSalePeriodModel
 * @Description: 销售时间段初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年10月24日 下午3:02:55
 *
 */
public class PosSalePeriodModel {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public PosSalePeriodModel(String code, String name, String startTime, String endTime) {
        this.code = code;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static List<PosSalePeriodModel> build(){
        List<PosSalePeriodModel> pspList = new ArrayList<PosSalePeriodModel>();
        pspList.add(new PosSalePeriodModel("01", "01", "1:00:00", "1:59:59"));
        pspList.add(new PosSalePeriodModel("02", "02", "2:00:00", "2:59:59"));
        pspList.add(new PosSalePeriodModel("03", "03", "3:00:00", "3:59:59"));
        pspList.add(new PosSalePeriodModel("04", "04", "4:00:00", "4:59:59"));
        pspList.add(new PosSalePeriodModel("05", "05", "5:00:00", "5:59:59"));
        pspList.add(new PosSalePeriodModel("06", "06", "6:00:00", "6:59:59"));
        pspList.add(new PosSalePeriodModel("07", "07", "7:00:00", "7:59:59"));
        pspList.add(new PosSalePeriodModel("08", "08", "8:00:00", "8:59:59"));
        pspList.add(new PosSalePeriodModel("09", "09", "9:00:00", "9:59:59"));
        pspList.add(new PosSalePeriodModel("10", "10", "10:00:00", "10:59:59"));
        pspList.add(new PosSalePeriodModel("11", "11", "11:00:00", "11:59:59"));
        pspList.add(new PosSalePeriodModel("12", "12", "12:00:00", "12:59:59"));
        pspList.add(new PosSalePeriodModel("13", "13", "13:00:00", "13:59:59"));
        pspList.add(new PosSalePeriodModel("14", "14", "14:00:00", "14:59:59"));
        pspList.add(new PosSalePeriodModel("15", "15", "15:00:00", "15:59:59"));
        pspList.add(new PosSalePeriodModel("16", "16", "16:00:00", "16:59:59"));
        pspList.add(new PosSalePeriodModel("17", "17", "17:00:00", "17:59:59"));
        pspList.add(new PosSalePeriodModel("18", "18", "18:00:00", "18:59:59"));
        pspList.add(new PosSalePeriodModel("19", "19", "19:00:00", "19:59:59"));
        pspList.add(new PosSalePeriodModel("20", "20", "20:00:00", "20:59:59"));
        pspList.add(new PosSalePeriodModel("21", "21", "21:00:00", "21:59:59"));
        pspList.add(new PosSalePeriodModel("22", "22", "22:00:00", "22:59:59"));
        pspList.add(new PosSalePeriodModel("23", "23", "23:00:00", "23:59:59"));
        pspList.add(new PosSalePeriodModel("24", "24", "24:00:00", "24:59:59"));
        return pspList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}