package com.rograndec.feijiayun.chain.common.file.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;
import com.rograndec.feijiayun.chain.common.file.dao.BucketMapper;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.Bucket;
import com.rograndec.feijiayun.chain.common.file.model.AccessFile;
import com.rograndec.feijiayun.chain.common.file.model.File;
import com.rograndec.feijiayun.chain.common.file.model.UploadTokenResult;
import com.rograndec.feijiayun.chain.common.file.service.FileService;

@Service
public class QiNiuFileServiceImpl implements FileService {
	@Autowired
	private BucketMapper bucketMapper;

	@Autowired
	private FileMapper fileMapper;

	@Value("${uploadExpires:1800}")
	private int uploadExpires;

	@Value("${accessExpires:1800}")
	private int accessExpires;

	@Autowired
	private Auth auth;

	@Override
	public UploadTokenResult generateUploadToken(int uploadType, String fileName) {
		UploadTokenResult uploadTokenResult = null;
		Bucket bucket = bucketMapper.selectByPrimaryKey(uploadType);
		String url = null;
		String fileKey = getFileKey(fileName);

		try {
			url = genderateUrl(bucket, fileKey);
		} catch (UnsupportedEncodingException ex) {

		}

		if (null != url) {
			File file = new File();

			file.setFileKey(fileKey);
			file.setUrl(url);

			long id = storeFile(bucket, file, fileName);

			uploadTokenResult = new UploadTokenResult();

			uploadTokenResult.setId(id);
			uploadTokenResult.setUploadToken(auth.uploadToken(bucket.getName(), fileKey, uploadExpires, null));
			uploadTokenResult.setBucketType(bucket.getBucketType());
			uploadTokenResult.setScheme(bucket.getScheme());
			uploadTokenResult.setDomain(bucket.getDomain());
			uploadTokenResult.setFileKey(fileKey);
			uploadTokenResult.setUrl(url);
			uploadTokenResult.setExpires(uploadExpires);
			uploadTokenResult.setCreateTime(new Date());
		}

		return uploadTokenResult;
	}

	public List<AccessFile> accessFilesByIdsOrFileKeys(List<Long> ids, List<String> fileKeys) {
		List<AccessFile> accessFiles = new ArrayList<AccessFile>();

		if ((null != ids && !ids.isEmpty()) || (null != fileKeys && !fileKeys.isEmpty())) {
			if (null != ids && !ids.isEmpty() && (null == fileKeys || fileKeys.isEmpty())) {
				accessFiles = toAccessFiles(
						fileMapper.findByFileIdIn(ids));
			} else if (null != fileKeys && !fileKeys.isEmpty() && (null == ids || ids.isEmpty())) {
				
				accessFiles = toAccessFiles(fileMapper.findByFileKeyIn(fileKeys));
			} else {
				
				accessFiles = toAccessFiles(
						fileMapper.findByIdInOrFileKeyIn(ids, fileKeys));
			}
		}

		return accessFiles;
	}

	@Override
	public UploadTokenResult generateUploadTokenBase64(int uploadType,String fileName) {
		UploadTokenResult uploadTokenResult = null;
		Bucket bucket = bucketMapper.selectByPrimaryKey(uploadType);
		String url = null;
		String fileKey = getFileKey(fileName);

		try {
			url = genderateUrlBase64(bucket, fileKey);
		} catch (UnsupportedEncodingException ex) {

		}

		if (null != url) {
			File file = new File();

			file.setFileKey(fileKey);
			file.setUrl(url);

			long id = storeFile(bucket, file, fileName);

			uploadTokenResult = new UploadTokenResult();

			uploadTokenResult.setId(id);
			uploadTokenResult.setUploadToken(auth.uploadToken(bucket.getName(), fileKey, uploadExpires, null));
			uploadTokenResult.setBucketType(bucket.getBucketType());
			uploadTokenResult.setScheme(bucket.getScheme());
			uploadTokenResult.setDomain(bucket.getDomain());
			uploadTokenResult.setFileKey(fileKey);
			uploadTokenResult.setUrl(url);
			uploadTokenResult.setExpires(uploadExpires);
			uploadTokenResult.setCreateTime(new Date());
		}

		return uploadTokenResult;
	}

	private List<AccessFile> toAccessFiles(List<com.rograndec.feijiayun.chain.common.file.entity.File> fileEntries) {
		List<AccessFile> accessFiles = new ArrayList<>(fileEntries.size());
		if(fileEntries != null && fileEntries.size() > 0){
			
			List<Integer> list = new ArrayList<Integer>();
	
			for (int i=0; i<fileEntries.size(); i++) {
				list.add(fileEntries.get(i).getBucketId());
			}
	
			List<Bucket> listBucket = bucketMapper.findBucketByIds(list);
			Map<Integer, Bucket> bucketMap = new HashMap<>();
			if(listBucket != null){
				for (Bucket bucket : listBucket) {
					bucketMap.put(bucket.getId(), bucket);
				}
			}
	
			for (com.rograndec.feijiayun.chain.common.file.entity.File fileEntity : fileEntries) {
				AccessFile accessFile = new AccessFile();
	
				accessFile.setId(fileEntity.getId());
				accessFile.setFileKey(fileEntity.getFileKey());
				accessFile.setFileName(fileEntity.getFileName());
	
				int bucketId = fileEntity.getBucketId();
	
				if (bucketMap.containsKey(bucketId)) {
					Bucket bucket = bucketMap.get(bucketId);
	
					accessFile.setBucketType(bucket.getBucketType());
					accessFile.setDomain(bucket.getDomain());
					accessFile.setScheme(bucket.getScheme());
	
					try {
						accessFile.setUrl(genderateUrl(bucket, fileEntity.getFileKey()));
					} catch (UnsupportedEncodingException ex) {
	
					}
	
				}
	
				if (StringUtils.isNotBlank(accessFile.getUrl())) {
					accessFile.setExpires(accessExpires);
					accessFile.setCreateTime(new Date());
	
					accessFiles.add(accessFile);
				}
	
			}
		}
		return accessFiles;
	}

	private String getFileKey(String filename) {
		StringBuilder sb = new StringBuilder().append(new ObjectId().toHexString());
		String ext = FilenameUtils.getExtension(filename);

		if (StringUtils.isNotBlank(ext)) {
			sb.append(".").append(ext.toLowerCase());
		}

		return sb.toString();
	}

	private long storeFile(Bucket bucket, File file, String fileName) {
		com.rograndec.feijiayun.chain.common.file.entity.File fileEntity = new com.rograndec.feijiayun.chain.common.file.entity.File();

		fileEntity.setBucketId(bucket.getId());
		fileEntity.setFileKey(file.getFileKey());
		fileEntity.setFileName(fileName);
		fileEntity.setStatus(0);
		fileEntity.setCreateTime(new Date());

		fileMapper.insertSelective(fileEntity);

		return fileEntity.getId();
	}

	private String genderateUrl(Bucket bucket, String fileKey) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder().append(bucket.getScheme() == 2 ? "https" : "http").append("://")
				.append(bucket.getDomain()).append("/").append(URLEncoder.encode(fileKey, "utf-8"));

		return bucket.getBucketType() == 2 ? auth.privateDownloadUrl(sb.toString(), accessExpires) : sb.toString();
	}

	private String genderateUrlBase64(Bucket bucket, String fileKey) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder().append(bucket.getScheme() == 2 ? "https" : "http").append("://")
				.append(bucket.getDomain()).append("/");

		return bucket.getBucketType() == 2 ? auth.privateDownloadUrl(sb.toString(), accessExpires) : sb.toString();
	}

}