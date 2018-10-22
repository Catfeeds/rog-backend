package com.rograndec.feijiayun.chain.business.storage.inventory.vo.register;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/29 13:19
 */

public class RequestParamForHadRegisterListVO extends RequestBaseParamVO {

    @ApiModelProperty("单号 0/倒序；1/正序;默认0")
    private Integer codeOrder = 0;

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;

    /**
     * 盘点方法（0-按货位；1-按商品）
     */
    @ApiModelProperty(value = "盘点方法（0-按货位；1-按商品）")
    private Integer invType;

    @ApiModelProperty(value = "登记人员")
    private String registerManName;

    @ApiModelProperty(value = "盘点人员")
    private String invManName;

    @ApiModelProperty(value = "盘点日期")
    private String invDate;

    //0/差异处理查询待处理列表；1/查询已登记列表
    private Integer diff;

    public Integer getCodeOrder() {
        if(codeOrder == null){
          return  0;
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

    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public String getInvManName() {
        return invManName;
    }

    public void setInvManName(String invManName) {
        this.invManName = invManName;
    }

    public String getInvDate() {
        if("".equals(invDate)) return null;
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }
}