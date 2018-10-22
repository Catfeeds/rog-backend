package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class TestReportIdsVO implements Serializable {

    @ApiModelProperty(value = "附件ID", required = true)
    private Long fileId;

    @ApiModelProperty(value = "附件名称", required = true)
    private String fileName;

    @ApiModelProperty(value = "检验项目ID", required = true)
    private Long checkProjectId;

    @ApiModelProperty(value = "检验项目名称", required = true)
    private String checkProjectName;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getCheckProjectId() {
        return checkProjectId;
    }

    public void setCheckProjectId(Long checkProjectId) {
        this.checkProjectId = checkProjectId;
    }

    public String getCheckProjectName() {
        return checkProjectName;
    }

    public void setCheckProjectName(String checkProjectName) {
        this.checkProjectName = checkProjectName;
    }

    @Override
    public String toString() {
        return "TestReportIdsVO[" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", checkProjectId=" + checkProjectId +
                ", checkProjectName='" + checkProjectName + '\'' +
                ']';
    }
}
