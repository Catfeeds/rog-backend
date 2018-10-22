package com.rograndec.feijiayun.chain.common.file.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@ApiModel(value = "location", description = "文件对象")
public class AccessFile implements Serializable {
	private static final long serialVersionUID = 6171447527684161476L;

	@ApiModelProperty(value = "文件Id")
	private long id;

	@ApiModelProperty(value = "类型，1：公用，2：私有")
	@JsonProperty("bucket_type")
	private int bucketType;

	@ApiModelProperty(value = "协议，1：http，2：https")
	private int scheme;

	@ApiModelProperty(value = "域名")
	private String domain;

	@ApiModelProperty(value = "文件key")
	@JsonProperty("file_key")
	private String fileKey;

	@ApiModelProperty(value = "文件名")
	private String fileName;

	@ApiModelProperty(value = "文件url")
	private String url;

	@ApiModelProperty(value = "获取失效时间，秒")
	private int expires;

	@ApiModelProperty(value = "当前时间")
	@JsonProperty("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBucketType() {
		return bucketType;
	}

	public void setBucketType(int bucketType) {
		this.bucketType = bucketType;
	}

	public int getScheme() {
		return scheme;
	}

	public void setScheme(int scheme) {
		this.scheme = scheme;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
