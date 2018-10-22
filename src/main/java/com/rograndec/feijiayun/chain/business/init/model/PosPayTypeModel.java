package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: PosPayTypeModel
 * @Description: Pos设置初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年9月4日 下午7:40:23
 *
 */
public class PosPayTypeModel {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 快捷键
     */
    private Integer shortcutKey;

    public PosPayTypeModel(String code, String name, Integer shortcutKey) {
        this.code = code;
        this.name = name;
        this.shortcutKey = shortcutKey;
    }

    public static List<PosPayTypeModel> build(){
        List<PosPayTypeModel> pptList = new ArrayList<PosPayTypeModel>();
        pptList.add(new PosPayTypeModel("01","现金",0));
        pptList.add(new PosPayTypeModel("02","银行",1));
        pptList.add(new PosPayTypeModel("03","储值卡",2));
        pptList.add(new PosPayTypeModel("04","购物券",3));
        pptList.add(new PosPayTypeModel("05","医保卡",4));
        pptList.add(new PosPayTypeModel("06","微信",5));
        pptList.add(new PosPayTypeModel("07","支付宝",6));
        return pptList;
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

    public Integer getShortcutKey() {
        return shortcutKey;
    }

    public void setShortcutKey(Integer shortcutKey) {
        this.shortcutKey = shortcutKey;
    }
}