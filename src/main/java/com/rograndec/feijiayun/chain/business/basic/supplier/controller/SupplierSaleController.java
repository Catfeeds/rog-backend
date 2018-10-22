package com.rograndec.feijiayun.chain.business.basic.supplier.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierModifyRecord;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierSaleService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.AddSupplierSalerVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerReturnVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(value = "shelf",description = "供货单位销售人员服务接口")
@RestController
@RequestMapping("basic/supplier/supplierSale")
public class SupplierSaleController {

	private static final Logger logger = LoggerFactory.getLogger(SupplierSaleController.class);
	
	@Autowired
	private SupplierSaleService supplierSaleService;

	@Autowired
	private CodeComponent codeComponent;

	private String codeName = "com.rograndec.feijiayun.chain.business.basic.supplier.saler";
	
	@ApiOperation(value="添加销售人员", notes = "添加供货单位中的销售人员 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result addSaler(HttpServletRequest request,
						   @ApiParam(name="supplierSaler", value="添加销售人员" , required=true)
						  @Valid @RequestBody AddSupplierSalerVO supplierSaler,@ApiIgnore UserVO user) throws Exception {
		Result result = new Result();
		String code = codeComponent.generate(
				codeName
				, 4
				, supplierSaler.getSupplierId()
		);

		SupplierSaler sc = SupplierSaler.getSupplierSaler4VO(user,supplierSaler,true);
		sc.setCode(code);
		supplierSaleService.addSale(sc);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);
		return result;
	}

	@ApiOperation(value="修改销售人员", notes = "修改供货单位中的销售人员 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public Result modifySaler(HttpServletRequest request,
							  @ApiParam(name="supplierSaler", value="修改销售人员对象" , required=true)
							  @Valid @RequestBody AddSupplierSalerVO supplierSaler,@ApiIgnore UserVO user) throws Exception {
		Result result = new Result();
		SupplierSaler sc = SupplierSaler.getSupplierSaler4VO(user,supplierSaler,false);
			supplierSaleService.updateSale(sc,user);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);
		return result;
	}


	@ApiOperation(value="查询修改记录信息", notes = "根据userId查询修改记录信息 | 开发者 翟伟"
			,produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "modifyRecord/{id}", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "销售人员id"
					, required = true, paramType="path"),
			@ApiImplicitParam(name = "pageNo", value = "当前页码"
					, required = false, paramType="query"),
			@ApiImplicitParam(name = "pageSize", value = "显示条数"
					, required = false, paramType="query")
	})
	@ResponseBody
	public Result< Page> modifyRecords(
			@PathVariable
					Long id,
			@RequestParam(required = false)
					Integer pageNo,
			@RequestParam(required = false)
					Integer pageSize
			,@ApiIgnore UserVO userVO
	){
		Result< Page> result = new Result<>();

		Page page = new Page(pageNo,pageSize);
		List<SupplierModifyRecord> supplierModifyRecords = supplierSaleService.findSupplierModifyRecords(id,page);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		return result;
	}

	@ApiOperation(value="删除销售人员", notes = "删除供货单位中的销售人员 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "业务人员id", required = true,paramType = "path")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result removeSaler(@PathVariable Long id, @ApiIgnore UserVO userVO){
		Result result = new Result();
			supplierSaleService.deleteSale(id, userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);
		return result;
	}

	@ApiOperation(value="查询销售人员", notes = "查询销售人员", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "queryStr", value = "查询条件,code name 和检索码"
					, required = false, paramType="query"),
			@ApiImplicitParam(name = "queryType", value = "查询类别,供货单位 : 0 ; 销售人员 : 1"
					, required = true, paramType="path"),
			@ApiImplicitParam(name = "pageNo", value = "当前页码"
					, required = false, paramType="query"),
			@ApiImplicitParam(name = "pageSize", value = "显示条数"
					, required = false, paramType="query")
	})

	@RequestMapping(value = "{queryType}/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<List<SupplierSalerReturnVO>>> salers(
						@RequestParam(required = false)
								 String queryStr,
						@PathVariable
							 Integer queryType,
						@RequestParam(required = false)
						   @ApiParam(name = "pageNo", value = "当前页码", required = false)
								   Integer pageNo,
						@RequestParam(required = false)
						   @ApiParam(name = "pageSize", value = "显示条数", required = false)
													   Integer pageSize,
						@ApiParam(value = "按某一列排序(supplierCode)", required = false) @RequestParam(required = false) String order,
						@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required = false) String sort,
						@ApiIgnore UserVO userVO
	){
		Result<Page<List<SupplierSalerReturnVO>>> result = new Result<>();
		Page page = new Page(pageNo, pageSize);
		if("supplierCode".equals(order)){
			order = "code";
		}
		List<SupplierSalerReturnVO> salers = supplierSaleService.findSalers(userVO, queryType, queryStr, page,order,sort);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

		return result;
	}

	@ApiOperation(value="销售人员导出", notes = "销售人员导出", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "queryStr", value = "查询条件,code name 和检索码"
					, required = false, paramType="query"),
			@ApiImplicitParam(name = "queryType", value = "查询类别,供货单位 : 0 ; 销售人员 : 1"
					, required = true, paramType="path")
	})

	@RequestMapping(value = "/excel/{queryType}/export", method = RequestMethod.GET)
	@ResponseBody
	public void excelExportUsers( @RequestParam(required = false)
															   String queryStr,
													   @PathVariable
															   Integer queryType,HttpServletResponse response
														,@ApiIgnore UserVO userVO) throws IOException {
		//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式

		String file = "菲加云-供货单位销售人员";
////		String utf8File = "feijiayun";
//		String utf8File = new String(file.getBytes("iso-8859-1"),"utf-8");
		String suffix = ".xlsx";
		String fileName = file+suffix;
		response.setContentType("application/octet-stream;charset=utf-8");
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
//		response.addHeader("Content-Disposition", "attachment;filename="+utf8File+suffix);
		response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
		//输出Excel文件
		OutputStream output = null;
		try {

			output = response.getOutputStream();
			List<SupplierSalerReturnVO>  supplierSalers =  supplierSaleService.findSalers(userVO,queryType,queryStr);
			supplierSaleService.excelExport4User(output,supplierSalers);
		}finally {
			output.close();
			output.flush();
		}
	}



}
