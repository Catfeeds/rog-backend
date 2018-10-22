package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/8/30.
 */
public class ResponseBatchCodeVO {

    @ApiModelProperty(value = "起始编码")
    private String startCode;
    @ApiModelProperty(value = "结束编码")
    private String endCode;

    public String getStartCode() {
        return StringUtil.trimStr(startCode);
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public String getEndCode() {
        return StringUtil.trimStr(endCode);
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }
}