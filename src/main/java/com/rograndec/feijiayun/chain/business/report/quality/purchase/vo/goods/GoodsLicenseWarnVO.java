package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsQualificationConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsLicenseWarnVO implements Serializable{

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
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 预警内容
     */
    @ApiModelProperty(value = "预警内容")
    private String content;

    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private String warnInfo;

    /**
     * 预警天数
     */
    @ApiModelProperty(value = "预警天数")
    private Integer warnDay;

    /**
     * 类型必填
     */
    @ApiModelProperty(value = "类型必填")
    private Integer typeMust;

    /**
     * 资质编码必填
     */
    @ApiModelProperty(value = "资质编码必填")
    private Integer codeMust;

    /**
     * 失效期必填
     */
    @ApiModelProperty(value = "失效期必填")
    private Integer validUntilMust;

    /**
     * 附件必填
     */
    @ApiModelProperty(value = "附件必填")
    private Integer fileMust;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private Long fileId;

    /**
     * 失效期
     */
    @ApiModelProperty(value = "失效期")
    private Date validUntil;

    /**
     * 资质编码
     */
    @ApiModelProperty(value = "资质编码")
    private String qualificationCode;

    public static List<GoodsLicenseWarnVO> getGoodsLicenseWarnVO(List<GoodsQualification> goodsQualifications,List<GoodsQualificationConfig> goodsQualificationConfigs, List<Goods> goodsList, List<WarnSet> warnSets){

        List<GoodsLicenseWarnVO> goodsLicenseWarnVOS = new ArrayList<>();

        for(GoodsQualificationConfig goodsQualificationConfig : goodsQualificationConfigs){

            for(Goods goods : goodsList){

                if(goodsQualificationConfig.getGoodsId().equals(goods.getId())){

                    GoodsLicenseWarnVO goodsLicenseWarnVO = new GoodsLicenseWarnVO();
                    /**
                     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
                     */
                    goodsLicenseWarnVO.setBusinessVariety(goods.getBusinessVariety());

                    /**
                     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
                     */

                    String name = BusinessVariety.getName(goodsLicenseWarnVO.getBusinessVariety());
                    goodsLicenseWarnVO.setBusinessVarietyName(name);

                    /**
                     * 商品编码
                     */
                    goodsLicenseWarnVO.setGoodsCode(goods.getCode());

                    /**
                     * 商品名称
                     */
                    goodsLicenseWarnVO.setGoodsName(goods.getName());

                    /**
                     * 商品ID
                     */
                    goodsLicenseWarnVO.setGoodsId(goods.getId());

                    /**
                     * 预警内容
                     */
                    WarnSet warnSet = warnSets.stream().filter(ws -> ws.getQualificationId().equals(goodsQualificationConfig.getQualificationId())).findFirst().orElse(null);
                    goodsLicenseWarnVO.setContent(warnSet.getContent());


                    /**
                     * 预警天数
                     */
                    goodsLicenseWarnVO.setWarnDay(warnSet.getWarnDays());

                    GoodsQualification goodsQualification = goodsQualifications.stream().filter(gq -> gq.getId().equals(goodsQualificationConfig.getQualificationId())).findFirst().orElse(null);
                    /**
                     * 类型必填
                     */
                    goodsLicenseWarnVO.setTypeMust(goodsQualification.getTypeMust());

                    /**
                     * 资质编码必填
                     */
                    goodsLicenseWarnVO.setCodeMust(goodsQualification.getCodeMust());

                    /**
                     * 失效期必填
                     */
                    goodsLicenseWarnVO.setValidUntilMust(goodsQualification.getValidUntilMust());

                    /**
                     * 附件必填
                     */
                    goodsLicenseWarnVO.setFileMust(goodsQualification.getFileMust());

                    /**
                     * 附件
                     */
                    goodsLicenseWarnVO.setFileId(goodsQualificationConfig.getFileId());

                    /**
                     * 失效期
                     */
                    goodsLicenseWarnVO.setValidUntil(goodsQualificationConfig.getValidUntil());

                    /**
                     * 资质编码
                     */
                    goodsLicenseWarnVO.setQualificationCode(goodsQualificationConfig.getQualificationCode());

                    goodsLicenseWarnVOS.add(goodsLicenseWarnVO);
                }

            }

        }

        return goodsLicenseWarnVOS;

    }


    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }

    public Integer getWarnDay() {
        return warnDay;
    }

    public void setWarnDay(Integer warnDay) {
        this.warnDay = warnDay;
    }

    public Integer getTypeMust() {
        return typeMust;
    }

    public void setTypeMust(Integer typeMust) {
        this.typeMust = typeMust;
    }

    public Integer getCodeMust() {
        return codeMust;
    }

    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    public Integer getValidUntilMust() {
        return validUntilMust;
    }

    public void setValidUntilMust(Integer validUntilMust) {
        this.validUntilMust = validUntilMust;
    }

    public Integer getFileMust() {
        return fileMust;
    }

    public void setFileMust(Integer fileMust) {
        this.fileMust = fileMust;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    public String getBusinessVarietyName() {
        return businessVarietyName;
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    @Override
    public String toString() {
        return "GoodsLicenseWarnVO{" +
                "businessVariety=" + businessVariety +
                "businessVarietyName=" + businessVarietyName +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsId=" + goodsId +
                ", content='" + content + '\'' +
                ", warnInfo='" + warnInfo + '\'' +
                ", warnDay=" + warnDay +
                ", typeMust=" + typeMust +
                ", codeMust=" + codeMust +
                ", validUntilMust=" + validUntilMust +
                ", fileMust=" + fileMust +
                ", fileId=" + fileId +
                ", validUntil=" + validUntil +
                ", qualificationCode='" + qualificationCode + '\'' +
                '}';
    }
}
