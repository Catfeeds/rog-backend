package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_temperature_humidity_detail
 * 
 * 返回详情页用
 * @author DD
 * 
 * 2017-09-27
 */
public class TemperatureHumidityDetailReportVO implements Serializable {
    @ApiModelProperty(value = "详情记录单主键ID")
    private Long detailId;
    @ApiModelProperty(value = "上午记录时间")
    private String amTime;
    @ApiModelProperty(value = "上午相对温度")
    private BigDecimal amTempA;
    @ApiModelProperty(value = "上午相对湿度")
    private BigDecimal amHumidityA;
    @ApiModelProperty(value = "上午调控措施")
    private String amMeasure;
    @ApiModelProperty(value = "上午调控后相对温度")
    private BigDecimal amTempB;
    @ApiModelProperty(value = "上午调控后相对湿度")
    private BigDecimal amHumidityB;
    @ApiModelProperty(value = "下午记录时间")
    private String pmTime;
    @ApiModelProperty(value = "下午相对温度")
    private BigDecimal pmTempA;
    @ApiModelProperty(value = "下午相对湿度")
    private BigDecimal pmHumidityA;
    @ApiModelProperty(value = "下午调控措施")
    private String pmMeasure;
    @ApiModelProperty(value = "下午调控后相对温度")
    private BigDecimal pmTempB;
    @ApiModelProperty(value = "下午调控后相对湿度")
    private BigDecimal pmHumidityB;
    @ApiModelProperty(value = "行号")
    private Integer lineNum;
    private static final long serialVersionUID = 1L;
    public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/**
     * 上午记录时间
     * @return am_time 上午记录时间
     */
    public String getAmTime() {
        return amTime;
    }

    /**
     * 上午记录时间
     * @param amTime 上午记录时间
     */
    public void setAmTime(String amTime) {
        this.amTime = amTime;
    }

    /**
     * 上午相对温度
     * @return am_temp_a 上午相对温度
     */
    public BigDecimal getAmTempA() {
        return amTempA;
    }

    /**
     * 上午相对温度
     * @param amTempA 上午相对温度
     */
    public void setAmTempA(BigDecimal amTempA) {
        this.amTempA = amTempA;
    }

    /**
     * 上午相对湿度
     * @return am_humidity_a 上午相对湿度
     */
    public BigDecimal getAmHumidityA() {
        return amHumidityA;
    }

    /**
     * 上午相对湿度
     * @param amHumidityA 上午相对湿度
     */
    public void setAmHumidityA(BigDecimal amHumidityA) {
        this.amHumidityA = amHumidityA;
    }

    /**
     * 上午调控措施
     * @return am_measure 上午调控措施
     */
    public String getAmMeasure() {
        return amMeasure;
    }

    /**
     * 上午调控措施
     * @param amMeasure 上午调控措施
     */
    public void setAmMeasure(String amMeasure) {
        this.amMeasure = amMeasure == null ? null : amMeasure.trim();
    }

    /**
     * 上午调控后相对温度
     * @return am_temp_b 上午调控后相对温度
     */
    public BigDecimal getAmTempB() {
        return amTempB;
    }

    /**
     * 上午调控后相对温度
     * @param amTempB 上午调控后相对温度
     */
    public void setAmTempB(BigDecimal amTempB) {
        this.amTempB = amTempB;
    }

    /**
     * 下午调控后相对湿度
     * @return am_humidity_b 下午调控后相对湿度
     */
    public BigDecimal getAmHumidityB() {
        return amHumidityB;
    }

    /**
     * 下午调控后相对湿度
     * @param amHumidityB 下午调控后相对湿度
     */
    public void setAmHumidityB(BigDecimal amHumidityB) {
        this.amHumidityB = amHumidityB;
    }

    /**
     * 下午记录时间
     * @return pm_time 下午记录时间
     */
    public String getPmTime() {
        return pmTime;
    }

    /**
     * 下午记录时间
     * @param pmTime 下午记录时间
     */
    public void setPmTime(String pmTime) {
        this.pmTime = pmTime;
    }

    /**
     * 下午相对温度
     * @return pm_temp_a 下午相对温度
     */
    public BigDecimal getPmTempA() {
        return pmTempA;
    }

    /**
     * 下午相对温度
     * @param pmTempA 下午相对温度
     */
    public void setPmTempA(BigDecimal pmTempA) {
        this.pmTempA = pmTempA;
    }

    /**
     * 下午相对湿度
     * @return pm_humidity_a 下午相对湿度
     */
    public BigDecimal getPmHumidityA() {
        return pmHumidityA;
    }

    /**
     * 下午相对湿度
     * @param pmHumidityA 下午相对湿度
     */
    public void setPmHumidityA(BigDecimal pmHumidityA) {
        this.pmHumidityA = pmHumidityA;
    }

    /**
     * 下午调控措施
     * @return pm_measure 下午调控措施
     */
    public String getPmMeasure() {
        return pmMeasure;
    }

    /**
     * 下午调控措施
     * @param pmMeasure 下午调控措施
     */
    public void setPmMeasure(String pmMeasure) {
        this.pmMeasure = pmMeasure == null ? null : pmMeasure.trim();
    }

    /**
     * 下午调控后相对温度
     * @return pm_temp_b 下午调控后相对温度
     */
    public BigDecimal getPmTempB() {
        return pmTempB;
    }

    /**
     * 下午调控后相对温度
     * @param pmTempB 下午调控后相对温度
     */
    public void setPmTempB(BigDecimal pmTempB) {
        this.pmTempB = pmTempB;
    }

    /**
     * 下午调控后相对湿度
     * @return pm_humidity_b 下午调控后相对湿度
     */
    public BigDecimal getPmHumidityB() {
        return pmHumidityB;
    }

    /**
     * 下午调控后相对湿度
     * @param pmHumidityB 下午调控后相对湿度
     */
    public void setPmHumidityB(BigDecimal pmHumidityB) {
        this.pmHumidityB = pmHumidityB;
    }



    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", amTime=").append(amTime);
        sb.append(", amTempA=").append(amTempA);
        sb.append(", amHumidityA=").append(amHumidityA);
        sb.append(", amMeasure=").append(amMeasure);
        sb.append(", amTempB=").append(amTempB);
        sb.append(", amHumidityB=").append(amHumidityB);
        sb.append(", pmTime=").append(pmTime);
        sb.append(", pmTempA=").append(pmTempA);
        sb.append(", pmHumidityA=").append(pmHumidityA);
        sb.append(", pmMeasure=").append(pmMeasure);
        sb.append(", pmTempB=").append(pmTempB);
        sb.append(", pmHumidityB=").append(pmHumidityB);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}