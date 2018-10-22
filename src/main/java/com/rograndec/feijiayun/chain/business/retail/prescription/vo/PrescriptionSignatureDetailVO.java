package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * saas_prescription_signature_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-11-29
 */
public class PrescriptionSignatureDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 签章ID
     */
    @ApiModelProperty(value = "签章ID")
    private Long signatureId;

    /**
     * 图片附件ID
     */
    @ApiModelProperty(value = "图片附件ID")
    private Long pictureId;

    /**
     * base64的url
     */
    @ApiModelProperty(value = "base64的url")
    private String url;

    /**
     * 指纹数据
     */
    @ApiModelProperty(value = "指纹数据")
    private String fingerData;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_prescription_signature_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 签章ID
     * @return signature_id 签章ID
     */
    public Long getSignatureId() {
        return signatureId;
    }

    /**
     * 签章ID
     * @param signatureId 签章ID
     */
    public void setSignatureId(Long signatureId) {
        this.signatureId = signatureId;
    }

    /**
     * 图片附件ID
     * @return picture_id 图片附件ID
     */
    public Long getPictureId() {
        return pictureId;
    }

    /**
     * 图片附件ID
     * @param pictureId 图片附件ID
     */
    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * base64的url
     * @return url base64的url
     */
    public String getUrl() {
        return url;
    }

    /**
     * base64的url
     * @param url base64的url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 指纹数据
     * @return finger_data 指纹数据
     */
    public String getFingerData() {
        return fingerData;
    }

    /**
     * 指纹数据
     * @param fingerData 指纹数据
     */
    public void setFingerData(String fingerData) {
        this.fingerData = fingerData == null ? null : fingerData.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", id=").append(id);
        sb.append(", signatureId=").append(signatureId);
        sb.append(", pictureId=").append(pictureId);
        sb.append(", url=").append(url);
        sb.append(", fingerData=").append(fingerData);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}