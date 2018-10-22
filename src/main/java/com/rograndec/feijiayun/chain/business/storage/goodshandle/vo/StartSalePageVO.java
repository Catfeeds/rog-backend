package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import com.rograndec.feijiayun.chain.common.constant.StartSaleFromType;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by dudy on 2017/9/27.
 */
public class StartSalePageVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "通知日期")
    private Date startDate;

    /**
     * 通知单号
     */
    @ApiModelProperty(value = "通知单号")
    private String code;


    /**
     * 通知人员ID
     */
    @ApiModelProperty(value = "通知人员ID")
    private Long startManId;

    /**
     * 通知人员名称
     */
    @ApiModelProperty(value = "通知人员名称")
    private String startManName;

    /**
     * 通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）
     */
    @ApiModelProperty(value = "通知来源（0-主管单位通知；1-药品处理）")
    private Integer startFrom;

    @ApiModelProperty(value = "通知来源名称")
    private String startFromName;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "来源编号")
    private String baseOrderCode;


    public String getStartFromName() {

        if(startFrom == null){
            return "";
        }

        return  StartSaleFromType.getStartSaleFromTypeEnum(startFrom).getValue();

    }

    public void setStartFromName(String startFromName) {
        this.startFromName = startFromName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getStartManId() {
        return startManId;
    }

    public void setStartManId(Long startManId) {
        this.startManId = startManId;
    }

    public String getStartManName() {
        return startManName;
    }

    public void setStartManName(String startManName) {
        this.startManName = startManName;
    }

    public Integer getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(Integer startFrom) {
        this.startFrom = startFrom;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }
}
