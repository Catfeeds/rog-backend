package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: PosBankModel
 * @Description: POS开户银行初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年10月23日 下午7:02:55
 *
 */
public class PosBankModel {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    public PosBankModel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<PosBankModel> build(){
        List<PosBankModel> posBankList = new ArrayList<PosBankModel>();
        posBankList.add(new PosBankModel("01", "中国人民银行"));
        posBankList.add(new PosBankModel("02", "国家开发银行"));
        posBankList.add(new PosBankModel("03", "中国进出口银行"));
        posBankList.add(new PosBankModel("04", "中国农业发展银行"));
        posBankList.add(new PosBankModel("05", "中国工商银行"));
        posBankList.add(new PosBankModel("06", "中国建设银行"));
        posBankList.add(new PosBankModel("07", "中国银行"));
        posBankList.add(new PosBankModel("08", "中国农业银行"));
        posBankList.add(new PosBankModel("09", "中国交通银行"));
        posBankList.add(new PosBankModel("10", "招商银行"));
        posBankList.add(new PosBankModel("11", "浦发银行"));
        posBankList.add(new PosBankModel("12", "中信银行"));
        posBankList.add(new PosBankModel("13", "中国光大银行"));
        posBankList.add(new PosBankModel("14", "华夏银行"));
        posBankList.add(new PosBankModel("15", "兴业银行"));
        posBankList.add(new PosBankModel("16", "平安银行"));
        posBankList.add(new PosBankModel("17", "民生银行"));
        posBankList.add(new PosBankModel("18", "恒丰银行"));
        posBankList.add(new PosBankModel("19", "广发银行"));
        posBankList.add(new PosBankModel("20", "浙商银行"));
        posBankList.add(new PosBankModel("21", "渤海银行"));
        return posBankList;
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

}