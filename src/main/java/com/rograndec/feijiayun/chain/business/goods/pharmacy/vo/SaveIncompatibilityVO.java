package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SaveIncompatibilityVO implements Serializable {


    SaveIncompatibilityHeadVO incompatibilityJson;

    public SaveIncompatibilityHeadVO getIncompatibilityJson() {
        return incompatibilityJson;
    }

    public void setIncompatibilityJson(SaveIncompatibilityHeadVO incompatibilityJson) {
        this.incompatibilityJson = incompatibilityJson;
    }

    @Override
    public String toString() {
        return "SaveIncompatibilityVO[" +
                "incompatibilityJson=" + incompatibilityJson +
                ']';
    }
}
