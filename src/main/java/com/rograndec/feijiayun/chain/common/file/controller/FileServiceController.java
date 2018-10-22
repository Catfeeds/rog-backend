package com.rograndec.feijiayun.chain.common.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.file.model.AccessFile;
import com.rograndec.feijiayun.chain.common.file.model.UploadTokenResult;
import com.rograndec.feijiayun.chain.common.file.service.FileService;



@Api(value = "file", description = "文件上传服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/file")
public class FileServiceController {
	
	private static final Log logger = LogFactory.getLog(FileServiceController.class);
	
	@Autowired
	FileService fileService;

	/**
	 * 获取七牛上传的Upload Token
	 * 
	 * @param uploadType
	 *            上传类型
	 * @param filename
	 *            原始文件名
	 * @return
	 */
	@ApiOperation(value="获取上传凭据", notes = "获取上传凭据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/uploadToken", method = RequestMethod.POST)
	@ResponseBody
	public UploadTokenResult uploadToken(HttpServletRequest request,
			@ApiParam(value = "上传类型1:协议合同,4:商品图片,5:资质", required = true) @RequestParam int uploadType,
			@ApiParam(value = "原始文件名", required = true) @RequestParam String fileName) {

		return fileService.generateUploadToken(uploadType, fileName);
	}

	@ApiOperation(value="获取上传凭据(base64特别版)", notes = "获取上传凭据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/uploadTokenBase64", method = RequestMethod.POST)
	@ResponseBody
	public UploadTokenResult uploadTokenBase64(HttpServletRequest request,
			@ApiParam(value = "上传类型1:协议合同,4:商品图片,5:资质", required = true) @RequestParam int uploadType,
			@ApiParam(value = "原始文件名", required = true) @RequestParam String fileName) {
		return fileService.generateUploadTokenBase64(uploadType,fileName);
	}

	/**
	 * 获取访问文件信息
	 * 
	 * @param ids
	 *            文件ID，多个以逗号分隔
	 * @param fileKeys
	 *            文件Key，多个以逗号分隔
	 * @return
	 */
	@ApiOperation(value="获取访问文件信息", notes = "获取访问文件信息 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/accessFile", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<AccessFile>> accessFilesByIdsOrFileKeys(HttpServletRequest request,
			@ApiParam(value = "文件ID，多个逗号分隔", required = true) @RequestParam String ids) {
		Result<List<AccessFile>> result = new Result<List<AccessFile>>();
		
		List<AccessFile> accessFiles = new ArrayList<>();
		try{
			String fileKeys ="";
			if (StringUtils.isNotBlank(ids) || StringUtils.isNotBlank(fileKeys)) {
				List<Long> idList = StringUtils.isNotBlank(ids) ? Arrays.asList(ids.split(",")).stream().map(m -> {
					return Long.parseLong(m.trim());
				}).filter(f -> {
					return f > 0;
				}).distinct().collect(Collectors.toList()) : new ArrayList<>();
				List<String> fileKeyList = StringUtils.isNotBlank(fileKeys)
						? Arrays.asList(fileKeys.split(",")).stream().filter(f -> {
							return StringUtils.isNotBlank(f);
						}).distinct().collect(Collectors.toList()) : new ArrayList<>();
	
				accessFiles = fileService.accessFilesByIdsOrFileKeys(idList, fileKeyList);
				
				if(accessFiles == null || accessFiles.size() == 0){
	        		
	        		result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, accessFiles);
	        		
	        		return result;
	        	}
				result.setBizCodeSuccessInfo(SysCode.SUCCESS, accessFiles);
			}
		}catch(Exception e){
        	logger.error("获取访问文件信息错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
}
