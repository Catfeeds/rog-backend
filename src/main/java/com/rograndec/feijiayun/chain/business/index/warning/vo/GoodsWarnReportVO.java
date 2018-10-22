package com.rograndec.feijiayun.chain.business.index.warning.vo;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsWarnReportVO implements Serializable {
    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private String businessVarietyName;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long Id;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private Long fileId;

    /**
     * 预警内容
     */
    @ApiModelProperty(value = "预警内容")
    private String warningContext;

    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private String warningMessage;

    public static List<GoodsWarnReportVO> getGoodsWarnReportVOs(List<GoodsLicenseWarnVO> vos){

        List<GoodsWarnReportVO> all = new ArrayList<>();

        vos.stream().forEach(qr -> {
            GoodsWarnReportVO goodsWarnReportVO = getGoodsWarnReportVO(qr);
            all.add(goodsWarnReportVO);
        });

        return all;
    }


    public static GoodsWarnReportVO getGoodsWarnReportVO(GoodsLicenseWarnVO goodsLicenseWarnVO){

        GoodsWarnReportVO goodsWarnReportVO = new GoodsWarnReportVO();

        /**
         * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
         */
        goodsWarnReportVO.setBusinessVariety(goodsLicenseWarnVO.getBusinessVariety());

        /**
         * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
         */
        goodsWarnReportVO.setBusinessVarietyName(goodsLicenseWarnVO.getBusinessVarietyName());

        /**
         * 商品编码
         */
        goodsWarnReportVO.setCode(goodsLicenseWarnVO.getGoodsCode());

        /**
         * 商品名称
         */
        goodsWarnReportVO.setName(goodsLicenseWarnVO.getGoodsName());

        /**
         * 商品ID
         */
        goodsWarnReportVO.setId(goodsLicenseWarnVO.getGoodsId());

        /**
         * 附件
         */
        goodsWarnReportVO.setFileId(goodsLicenseWarnVO.getFileId());

        /**
         * 预警内容
         */
        goodsWarnReportVO.setWarningContext(goodsLicenseWarnVO.getContent());

        /**
         * 预警信息
         */
        goodsWarnReportVO.setWarningMessage(goodsLicenseWarnVO.getWarnInfo());

        return goodsWarnReportVO;

    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getBusinessVarietyName() {
        return businessVarietyName;
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getWarningContext() {
        return warningContext;
    }

    public void setWarningContext(String warningContext) {
        this.warningContext = warningContext;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }
}