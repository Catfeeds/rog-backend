package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/29 13:19
 */

public class RequestParamForPostListVO extends RequestBaseParamVO {

    @ApiModelProperty("单号 0/倒序；1/正序;默认0")
    private Integer codeOrder = 0;

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;


    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

    @ApiModelProperty(value = "5-待审核,6审核拒绝,3-已处理(已过账和已完成)")
    private Integer status;

    public Integer getCodeOrder() {
        if(codeOrder == null){
            return 0;
        }
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}