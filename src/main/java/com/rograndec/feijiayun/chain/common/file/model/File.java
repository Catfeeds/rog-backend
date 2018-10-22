package com.rograndec.feijiayun.chain.common.file.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class File implements Serializable {
	private static final long serialVersionUID = 256706267504192852L;

	@JsonProperty("file_key")
	private String fileKey;

	@JsonProperty("url")
	private String url;

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
}
