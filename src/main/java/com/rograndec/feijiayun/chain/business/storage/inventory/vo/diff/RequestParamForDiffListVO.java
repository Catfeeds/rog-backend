package com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/29 13:19
 */

public class RequestParamForDiffListVO extends RequestBaseParamVO {

    @ApiModelProperty("单号 0/倒序；1/正序;默认0")
    private Integer codeOrder = 0;

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;


    @ApiModelProperty(value = "登记人员")
    private String handleManName;

    //0/查询已差异处理 ；1/查询待过账
//    @ApiModelProperty(value = "5-待审核,6审核拒绝,3-审核通过(已处理/待过账)")
//    private Integer isWaitPosting;

    @ApiModelProperty(value = "2-已处理(待过账和已完成),7-待过账")
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

    public String getHandleManName() {
        return handleManName;
    }

    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}