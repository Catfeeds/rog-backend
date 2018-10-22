package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by dudy on 2017/9/27.
 */
public class StopSaleReturnVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "通知日期")
    private Date stopDate;

    /**
     * 通知单号
     */
    @ApiModelProperty(value = "通知单号")
    private String code;


    /**
     * 通知人员ID
     */
    @ApiModelProperty(value = "通知人员ID")
    private Long stopManId;

    /**
     * 通知人员名称
     */
    @ApiModelProperty(value = "通知人员名称")
    private String stopManName;

    /**
     * 通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）
     */
    @ApiModelProperty(value = "通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）")
    private Integer stopFrom;

    @ApiModelProperty(value = "通知来源名称")
    private String stopFromName;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "来源编号")
    private String baseOrderCode;


    @ApiModelProperty(value = "解停商品明细")
    private List<StopSaleDetailReturnVO> stopSaleDetailReturnVOList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getStopManId() {
        return stopManId;
    }

    public void setStopManId(Long stopManId) {
        this.stopManId = stopManId;
    }

    public String getStopManName() {
        return stopManName;
    }

    public void setStopManName(String stopManName) {
        this.stopManName = stopManName;
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

    public List<StopSaleDetailReturnVO> getStopSaleDetailReturnVOList() {
        return stopSaleDetailReturnVOList;
    }

    public void setStopSaleDetailReturnVOList(List<StopSaleDetailReturnVO> stopSaleDetailReturnVOList) {
        this.stopSaleDetailReturnVOList = stopSaleDetailReturnVOList;
    }

    public String getStopFromName() {
        return stopFromName;
    }

    public void setStopFromName(String stopFromName) {
        this.stopFromName = stopFromName;
    }
}
