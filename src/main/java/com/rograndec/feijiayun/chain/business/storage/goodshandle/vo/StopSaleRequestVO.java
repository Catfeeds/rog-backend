package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by dudy on 2017/9/28.
 */
public class StopSaleRequestVO  implements Serializable{

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "通知日期")
    private Date stopDate;


    /**
     * 通知人员ID
     */
    @ApiModelProperty(value = "通知人员ID")
    private Long stopManId;

    /**
     * 通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）
     */
    @ApiModelProperty(value = "通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）,前台新增只能传0,其他为其他模块调用自动生成")
    private Integer stopFrom;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "来源编号(前台不需要,后端其他模块调用必传)")
    private String baseOrderCode;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID(前台不需要,后端其他模块调用必传)")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型(前台不需要,后端其他模块调用必传)")
    private Integer baseOrderType;


    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期(前台不需要,后端其他模块调用必传)")
    private Date baseOrderDate;

    @ApiModelProperty(value = "通知明细")
    private List<StopSaleDtlRequestVO> stopSaleDtlRequestVOList;


    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Long getStopManId() {
        return stopManId;
    }

    public void setStopManId(Long stopManId) {
        this.stopManId = stopManId;
    }

    public Integer getStopFrom() {
        return stopFrom;
    }

    public void setStopFrom(Integer stopFrom) {
        this.stopFrom = stopFrom;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public List<StopSaleDtlRequestVO> getStopSaleDtlRequestVOList() {
        return stopSaleDtlRequestVOList;
    }

    public void setStopSaleDtlRequestVOList(List<StopSaleDtlRequestVO> stopSaleDtlRequestVOList) {
        this.stopSaleDtlRequestVOList = stopSaleDtlRequestVOList;
    }
}
