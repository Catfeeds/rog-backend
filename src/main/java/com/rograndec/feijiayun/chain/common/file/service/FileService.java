package com.rograndec.feijiayun.chain.common.file.service;

import java.util.List;

import com.rograndec.feijiayun.chain.common.file.model.AccessFile;
import com.rograndec.feijiayun.chain.common.file.model.UploadTokenResult;


public interface FileService {
	UploadTokenResult generateUploadToken(int uploadType, String filename);

	List<AccessFile> accessFilesByIdsOrFileKeys(List<Long> ids, List<String> fileKeys);

    UploadTokenResult generateUploadTokenBase64(int uploadType, String filename);
}
