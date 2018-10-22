package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/18.
 */
public class CheckProjectVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 检验项目
     */
    @ApiModelProperty(value = "检验项目", required = true)
    private String checkProjectIds;

    /**
     * 是否有附件（0-无；1-有）
     */
    @ApiModelProperty(value = "是否有附件（0-无；1-有）", required = true)
    private Integer haveFile;

    private String fileId;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public Integer getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(Integer haveFile) {
        this.haveFile = haveFile;
    }

    @Override
    public String toString() {
        return "CheckProjectVO[" +
                "id=" + id +
                ", checkProjectIds='" + checkProjectIds + '\'' +
                ", haveFile=" + haveFile +
                ']';
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
