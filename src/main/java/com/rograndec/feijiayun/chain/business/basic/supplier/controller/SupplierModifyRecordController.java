package com.rograndec.feijiayun.chain.business.basic.supplier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierModifyRecordService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierModifyRecordVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "shelf",description = "供货单位修改记录服务接口")
@RestController
@RequestMapping("basic/supplier/supplierModifyRecord")
public class SupplierModifyRecordController {

	private static final Logger logger = LoggerFactory.getLogger(SupplierModifyRecordController.class);
	
	@Autowired
	private SupplierModifyRecordService supplierModifyRecordService;
	
	@ApiOperation(value="供货单位查看修改记录", notes = "供货单位查看修改记录 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "supplierId", value = "供货单位Id", required = true, dataType = "Long",paramType = "path")
	@RequestMapping(value = "/getSupplierRecord/{supplierId}", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "supplierId", value = "供货单位id"
					, required = true, paramType="path"),
			@ApiImplicitParam(name = "pageNo", value = "当前页码"
					, required = false, paramType="query"),
			@ApiImplicitParam(name = "pageSize", value = "显示条数"
					, required = false, paramType="query")
	})
	@ResponseBody
	public Result<Page<List<SupplierModifyRecordVO>>> getSupplierRecord(HttpServletRequest request, @PathVariable Long supplierId,@RequestParam(required = false)
			Integer pageNo,@RequestParam(required = false)Integer pageSize){
		Result<Page<List<SupplierModifyRecordVO>>> result = new Result<Page<List<SupplierModifyRecordVO>>>();
        try{
			Page page = new Page(pageNo, pageSize);
        	HttpSession session = request.getSession();
        	UserVO user = (UserVO) session.getAttribute("user");
    		if (user == null) {
    			user = new UserVO();
    		}
    		page = supplierModifyRecordService.getSupplierRecord(page,user,supplierId);
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("供货单位查看修改记录错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
}
