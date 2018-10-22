package com.rograndec.feijiayun.chain.common.file.entity;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 空间ID
     */
    private Integer bucketId;

    /**
     * 文件名
     */
    private String fileKey;
    
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 状态，0：新建，1：上传成功，2：已删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    /**
     * saas_file
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 空间ID
     * @return bucket_id 空间ID
     */
    public Integer getBucketId() {
        return bucketId;
    }

    /**
     * 空间ID
     * @param bucketId 空间ID
     */
    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    /**
     * 文件名
     * @return file_key 文件名
     */
    public String getFileKey() {
        return fileKey;
    }

    /**
     * 文件名
     * @param fileKey 文件名
     */
    public void setFileKey(String fileKey) {
        this.fileKey = fileKey == null ? null : fileKey.trim();
    }

    /**
     * 状态，0：新建，1：上传成功，2：已删除
     * @return status 状态，0：新建，1：上传成功，2：已删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态，0：新建，1：上传成功，2：已删除
     * @param status 状态，0：新建，1：上传成功，2：已删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", bucketId=").append(bucketId);
        sb.append(", fileKey=").append(fileKey);
        sb.append(", fileName=").append(fileName);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}