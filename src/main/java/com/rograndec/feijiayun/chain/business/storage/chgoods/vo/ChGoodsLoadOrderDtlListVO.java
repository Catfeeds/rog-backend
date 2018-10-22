package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChGoodsLoadOrderDtlListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "装斗ID")
    private Long id;

    @ApiModelProperty(required = true, value = "装斗日期")
    private String loadDate;

    @ApiModelProperty(required = true, value = "装斗单号")
    private String code;

    @ApiModelProperty(required = true, value = "装斗人员")
    private String loadManName;

    @ApiModelProperty(required = true, value = "复核人员")
    private String auditManName;

    List<ChGoodsLoadOrderDtlListOneVO> ChGoodsLoadOrderDtlListOneVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoadManName() {
        return loadManName;
    }

    public void setLoadManName(String loadManName) {
        this.loadManName = loadManName;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public List<com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsLoadOrderDtlListOneVO> getChGoodsLoadOrderDtlListOneVO() {
        return ChGoodsLoadOrderDtlListOneVO;
    }

    public void setChGoodsLoadOrderDtlListOneVO(List<com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsLoadOrderDtlListOneVO> chGoodsLoadOrderDtlListOneVO) {
        ChGoodsLoadOrderDtlListOneVO = chGoodsLoadOrderDtlListOneVO;
    }

    @Override
    public String toString() {
        return "ChGoodsLoadOrderDtlListVO[" +
                "id=" + id +
                ", loadDate=" + loadDate +
                ", code='" + code + '\'' +
                ", loadManName='" + loadManName + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", ChGoodsLoadOrderDtlListOneVO=" + ChGoodsLoadOrderDtlListOneVO +
                ']';
    }
}
