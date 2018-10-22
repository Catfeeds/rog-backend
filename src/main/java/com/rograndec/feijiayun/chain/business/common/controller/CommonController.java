
package com.rograndec.feijiayun.chain.business.common.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.common.vo.AccountingPeriodValidity;
import com.rograndec.feijiayun.chain.business.common.vo.GoodsStorageDataVO;
import com.rograndec.feijiayun.chain.business.common.vo.OrderAccountingPeriodValidity;
import com.rograndec.feijiayun.chain.business.common.vo.StockLockShelfVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PrepayInvoiceGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.business.system.set.vo.DepartmentVO;
import com.rograndec.feijiayun.chain.common.*;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/9/28 21:29
 */
@Api(value = "common_controller", description = "通用控制接口")
@RequestMapping("common/controller/")
@RestController
public class CommonController {

	private static final Log logger = LogFactory.getLog(CommonController.class);

	@Autowired
	private ManageConfigComponent manageConfigComponent;

	@Autowired
	private EnterpriseBusinessService enterpriseBusinessService;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private GoodsComponent goodsComponent;

	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private DepartmentComponent departmentComponent;
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;

	@Autowired
	private CommonComponent commonComponent;


	@ApiOperation(value = "查看当前企业一些配置控制信息", notes = "查看当前企业一些配置控制信息 | 开发者 孙腾 | 已联调")
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public Result<ManageConfig> checkCode(HttpSession session) {
		Result<ManageConfig> result = new Result<ManageConfig>();
		try {
			UserVO loginUser = (UserVO) session.getAttribute("user");

			ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, manageConfig);
		} catch (Exception e) {
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查询总部税率", notes = "查询税率 | 开发者 孙腾 | 已联调")
	@RequestMapping(value = "/getTax", method = RequestMethod.GET)
	public Result<List<GoodsTaxRateVO>> getTax(HttpSession session) {
		Result<List<GoodsTaxRateVO>> result = new Result<>();
		UserVO userVO = (UserVO) session.getAttribute("user");
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsComponent.getGoodsTaxRateVO(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus()));
		return result;
	}

	@ApiOperation(value = "查询当前企业税率", notes = "查询当前企业税率 | 开发者 孙腾 | 已联调")
	@RequestMapping(value = "/getCurrentEntTax", method = RequestMethod.GET)
	public Result<List<GoodsTaxRateVO>> getCurrentEntTax(HttpSession session) {
		Result<List<GoodsTaxRateVO>> result = new Result<>();
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(ChainType.Division.getCode() == userVO.getChainType()){
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsComponent.getGoodsTaxRateVO(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus()));
		} else {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsComponent.getGoodsTaxRateVO(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus()));
		}

		return result;
	}


	@ApiOperation(value = "查询当前企业下可用用户", notes = "查询当前企业下可用用户（总部不查询其门店用户） | 开发者 孙腾 | 已联调")
	@RequestMapping(value = "/getRegister", method = RequestMethod.GET)
	public Result<List<SimpleUserVO>> getRegister(@ApiIgnore UserVO userVO) {
		Result<List<SimpleUserVO>> result = new Result<>();
		List<SimpleUserVO> simpleUserVOList = userComponent.getSimpleUserVOByEnterpriseId(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, simpleUserVOList);
		return result;
	}


	@ApiOperation(value = "根据企业ID获取企业下可用用户", notes = "根据企业ID获取企业下可用用户（总部不查询其门店用户） | 开发者 杜东阳 | 已联调")
	@RequestMapping(value = "/getUserByEnterpriseId", method = RequestMethod.GET)
	public Result<List<SimpleUserVO>> getUserByEnterpriseId(HttpSession session, @ApiParam(value = "企业ID", required = true) @RequestParam(required = true) Long enterpriseId) {
		Result<List<SimpleUserVO>> result = new Result<>();
		List<SimpleUserVO> simpleUserVOList = userComponent.getSimpleUserVOByEnterpriseId(enterpriseId, EnableStatus.ENABLE.getStatus());
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, simpleUserVOList);
		return result;
	}

	@ApiOperation(value = "待入库-入库-入库明细数据-货位选择树", notes = "仓库-》库区-》货区-》货位--树形展示 开发者:刘群 | 已联调")
	@RequestMapping(value = "/getWarehouseTreeByParam", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TreePOJO<WarehouseVO>>> getWarehouseTreeIncludeNullByParam(HttpServletRequest request,
																				  @ApiParam(value = "质量状况0-合格品，2不合格品", required = true) @RequestParam(required = true) Integer jobArea) {
		Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
		try {

			HttpSession session = request.getSession(true);
			UserVO loginUser = (UserVO) session.getAttribute("user");

			if (jobArea == null || (jobArea != 0 && jobArea != 2)) {
				result.setBizCodeSuccessInfo(SysCode.SYS_PARAM_ERROR, null);
				return result;
			}

			result.setBizCodeSuccessInfo(SysCode.SUCCESS, warehouseService.getWarehouseShelfTreeByParam(loginUser.getEnterpriseId(), EnableStatus.ENABLE.getStatus(), jobArea));
		} catch (Exception e) {
			logger.error("待入库-入库-入库明细数据-货位选择树:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value = "查询总部下的分部", notes = "查询总部下的分部 | 开发者 金正斌 | 开发完成")
	@RequestMapping(value = "/getChildEnterprise", method = RequestMethod.GET)
	public Result<List<Enterprise>> getChildEnterprise(HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute("user");
		Result<List<Enterprise>> result = new Result<>();
		List<Enterprise> enterpriseList = enterpriseService.getChildrens(userVO.getEnterpriseId());
		result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseList);
		return result;
	}

	@ApiOperation(value = "根据企业id获取部门信息", notes = "根据企业id获取部门信息 | 开发者 金正斌 | 开发完成")
	@RequestMapping(value = "/getDepartment/{id}", method = RequestMethod.GET)
	public Result<List<DepartmentVO>> getDepartment(HttpSession session,
													@ApiParam(value = "企业id", required = true) @PathVariable("id") Long id) {
		UserVO userVO = (UserVO) session.getAttribute("user");
		Result<List<DepartmentVO>> result = new Result<>();
		if (userVO.getParentId() != null && userVO.getParentId() != 0) {
			id = userVO.getEnterpriseId();
		}
		List<Department> departments = departmentComponent.findDepartMent4EnterpriseId(id);
		List<DepartmentVO> departmentVOS = DepartmentVO.getDepartmentVOs4Departments(departments);
		result.setData(departmentVOS);
		return result;
	}


	@ApiOperation(value = "导出带图片的excel demo", notes = "导出带图片的excel | 开发者 zhengbin.jin | 已联调")
	@RequestMapping(value = "/excelExport/{pic}", method = RequestMethod.GET, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void excelExport(HttpServletRequest request, HttpServletResponse response,
							@ApiParam(value = "base64加密后的图片流", required = true) @PathVariable("pic") String pic) throws FileNotFoundException {
		String name = "demo";
		try {
			response.setContentType("application/msexcel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
			//输出Excel文件
			OutputStream output = null;

			HttpSession session = request.getSession(true);
			UserVO userVO = (UserVO) session.getAttribute("user");
			output = response.getOutputStream();
			ExcelPic excelPic=new ExcelPic();
			/////////////用于自测//////////////
//			InputStream inputStream = null;
//			byte[] data = null;
//			inputStream = new FileInputStream("D://download/aaa.jpg");
//			data = new byte[inputStream.available()];
//			inputStream.read(data);
//			inputStream.close();
//			// 加密
//			BASE64Encoder encoder = new BASE64Encoder();
//			pic=encoder.encode(data);
			////////////////////////////
			excelPic.setPic(pic);
			excelPic.setDx1(0);
			excelPic.setDy1(0);
			excelPic.setDx2(1023);
			excelPic.setDy2(100);
			excelPic.setCol1((short)2);
			excelPic.setRow1(4);
			excelPic.setCol2((short) 14);
			excelPic.setRow2(23);
			LinkedHashMap<String, String> map = new LinkedHashMap<>();
			List<String> names = new ArrayList<>();
			names.add(userVO.getEnterpriseName());
			names.add("二级头标签");
			List list=new ArrayList();
			purchaseGeneralComponent.commExcelExportPic(output, map,
					 list, names,
					null, "",
					false, null,excelPic);
		} catch (Exception e) {
			logger.error("导出图片demo错误:" + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@ApiOperation(value = "查询货位", notes = "查询货位 | 开发者 翟伟 | 已联调")
	@RequestMapping(value="/getStockLockShelf",method= RequestMethod.POST)
	public Result<List<StockLockRecordVO>> getShelf(HttpSession session,
													@RequestBody(required = false)
													@ApiParam(name = "StockLockShelfVO", value = "查询条件实体", required = true)
															StockLockShelfVO stockLockShelfVO,
													@ApiIgnore UserVO userVO
	){
		Result<List<StockLockRecordVO>> result = new Result<>();
		List<StockLockRecordVO> stockLockRecord = commonComponent.getStockLockRecord(stockLockShelfVO,userVO.getEnterpriseId());

		result.setBizCodeSuccessInfo(SysCode.SUCCESS,stockLockRecord);
		return result;
	}

	@ApiOperation(value = "查询货品的储存和养护信息", notes = "查询货品的储存和养护信息 | 开发者 zeshi.sun | 已联调")
	@RequestMapping(value = "/getGoodsStorageData/{goodsId}", method = RequestMethod.GET)
	public Result<GoodsStorageDataVO> getGoodsStorageData(HttpSession session,
														  @ApiParam(value = "货品id", required = true) @PathVariable("goodsId") Long goodsId) {
		UserVO userVO = (UserVO) session.getAttribute("user");
		Result<GoodsStorageDataVO> result = new Result<>();
		Long enterpriseId = userVO.getEnterpriseId();
		if (userVO.getChainType() != 0) {
			enterpriseId = userVO.getParentId();
		}
		GoodsStorageDataVO goodsStorageDataVO = goodsComponent.getGoodsStorageData(enterpriseId, goodsId);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsStorageDataVO);
		return result;
	}


	@ApiOperation(value="获取企业业务信息", notes = "获取企业业务信息 | 开发者:杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getEnterpriseBusiness", method = RequestMethod.GET)
	public Result<EnterpriseBusiness> getEnterpriseBusiness(HttpServletRequest request){
		Result<EnterpriseBusiness> result = new Result<EnterpriseBusiness>();
		HttpSession session = request.getSession(true);
		UserVO loginUser = (UserVO) session.getAttribute("user");

		EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
		ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);
		enterpriseBus.setManageConfig(manageConfig);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, enterpriseBus);
		return result;
	}

	@ApiOperation(value="获取当前企业的会计期间范围", notes = "获取当前企业的会计期间范围 (当isOnly为1时 查询包含已经结账和未激活的当前月会计期间) | 开发者:翟伟 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/validityAccountingPeriod/{isOnly}", method = RequestMethod.GET)
	public Result<AccountingPeriodValidity> validityAccountingPeriod(@ApiIgnore UserVO userVO,
																	 @ApiParam(value = "是否是单独会计期间 0 : 不是 1 : 是", required = true) @PathVariable("isOnly") Integer isOnly) throws ParseException {
		Result<AccountingPeriodValidity> result = new Result<>();

		AccountingPeriodValidity accountingPeriod = commonComponent.getAccountingPeriod(userVO,isOnly,0,null);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, accountingPeriod);
		return result;
	}

	@ApiOperation(value="获取当前企业的会计期间范围", notes = "获取当前企业的会计期间范围包含已经结账和已激活的会计期间 (当isOnly为1时 查询包含已经结账和已激活的当前月会计期间) | 开发者:翟伟 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/order/validityAccountingPeriod", method = RequestMethod.GET)
	public Result<OrderAccountingPeriodValidity> validityOrderAccountingPeriod(@ApiIgnore UserVO userVO,
																			   @ApiParam(value = "状态（0-已激活；1-已结账）", required = false) @RequestParam(required = false) Integer status,
																			   @ApiParam(value = "状态（0-已激活；1-已结账）", required = false) @RequestParam(required = false) Integer onlyStatus
	) throws ParseException {
		Result<OrderAccountingPeriodValidity> result = new Result<>();

		OrderAccountingPeriodValidity orderAccountingPeriodValidity = new OrderAccountingPeriodValidity();
		AccountingPeriodValidity accountingPeriod = commonComponent.getAccountingPeriod(userVO,0,status,onlyStatus);
		if(null != accountingPeriod){
			orderAccountingPeriodValidity.setBeginTime(accountingPeriod.getBeginTime());
			orderAccountingPeriodValidity.setEndTime(accountingPeriod.getEndTime());
		}
		AccountingPeriodValidity accountingPeriodOrder = commonComponent.getAccountingPeriod(userVO,1,status,onlyStatus);
		if(null != accountingPeriodOrder){
			orderAccountingPeriodValidity.setOnlyBeginTime(accountingPeriodOrder.getBeginTime());
			orderAccountingPeriodValidity.setOnlyEndTime(accountingPeriodOrder.getEndTime());
		}


		result.setBizCodeSuccessInfo(SysCode.SUCCESS, orderAccountingPeriodValidity);
		return result;
	}



	@ApiOperation(value="判断时间范围是否在会计期间范围内", notes = "判断时间范围是否在会计期间范围内,包含已激活或者已结账的 | 开发者:翟伟 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/checkAccountingPeriodRange/{startDateStr}/{endDateStr}", method = RequestMethod.GET)
	public Result<Boolean> checkAccountingPeriodRange(@ApiIgnore UserVO userVO,
																	 @ApiParam(value = "开始日期字符串", required = true) @PathVariable("startDateStr") String startDateStr,
																	   @ApiParam(value = "结束日期字符串", required = true) @PathVariable("endDateStr") String endDateStr){
		Result<Boolean> result = new Result<>();

		boolean b = commonComponent.checkAccountingPeriodRange(userVO, startDateStr, endDateStr);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, b);
		return result;
	}

	@ApiOperation(value="获取当前企业的供货单位-预付发票,应付发票", notes = "获取当前企业的供货单位,总部查询自己企业的供货单位,加盟店查询自己的供货单位+总部企业 | 开发者:翟伟 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/currentSupplier", method = RequestMethod.GET)
	public Result<List<QueryBean>> currentSupplier(@ApiParam(name = "param",value = "供货单位编码名称检索码") @RequestParam(required = false)String param,@ApiIgnore UserVO userVO){
		Result<List<QueryBean>> result = new Result<>();
		List<QueryBean> basicSupplier = commonComponent.getPrepayInvoiceGoodssSupplier(userVO,param);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, basicSupplier);
		return result;
	}

	@ApiOperation(value="获取当前企业的供货单位-应付贷项凭证", notes = "获取当前企业的供货单位,总部查询自己企业的供货单位,加盟店查询自己的供货单位+总部企业 | 开发者:翟伟 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getCurrentSupplierByParam", method = RequestMethod.GET)
	public Result<List<QueryBean>> getCurrentSupplierByParam(@ApiIgnore UserVO userVO,
															 @ApiParam(name = "param",value = "供货单位编码名称检索码")@RequestParam(required = false)String param){
		Result<List<QueryBean>> result = new Result<>();
		List<QueryBean> basicSupplier = commonComponent.getPrepayInvoiceGoodssSupplier(userVO,param);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, basicSupplier);
		return result;
	}

	@ApiOperation(value="根据供货单位id查询商品-预付发票", notes = "根据供货单位id查询商品-预付发票,应付发票 | 开发者:翟伟 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/prepayInvoiceGoodses", method = RequestMethod.GET)
	public Result<Object> prepayInvoiceGoodses(@ApiIgnore UserVO userVO,
														@ApiParam(value = "1:企业,2:供应商", required = true) @RequestParam("type") Integer type,
														@ApiParam(value = "供应商或者总部id", required = true) @RequestParam("supplierId") Long supplierId,
														@ApiParam(value = "查询条件", required = false) @RequestParam(value = "param",required = false) String param,
														@ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo",required = false) Integer pageNo,
														@ApiParam(value = "每页显示的记录数", required = false ) @RequestParam(value = "pageSize",required = false) Integer pageSize
	){
		Result<Object> result = new Result<>();
		Page page = new Page(pageNo, pageSize);
		if(null == pageNo || null == pageSize || (0 == pageNo && 0 == pageSize)){
			page = null;
		}

		List<PrepayInvoiceGoodsVO> prepayInvoiceGoodses = commonComponent.getPrepayInvoiceGoodses(userVO, type, supplierId, param,page);
		if (null == page) {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, prepayInvoiceGoodses);
		}else {
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
		}


		return result;
	}

	@ApiOperation(value = "收款人员模糊搜索下拉框", notes = "收款人员模糊搜索下拉框 | 开发者 苏磊 | 开发中")
	@RequestMapping(value="/receivableManName", method=RequestMethod.GET)
	public Result<List<NewSelectBean>> getReceivableManNameSelector(@ApiIgnore UserVO userVO,
																	@ApiParam(value = "员工编码/员工检索码/员工名称模糊搜索") @RequestParam(required = false) String param){
		Result<List<NewSelectBean>> result = new Result<>();
		try{
			List<User> userList = commonComponent.getReceivableManNameSelector(userVO,param);
			List<NewSelectBean> list = new ArrayList<>();
			for (User s : userList){
				NewSelectBean bean = new NewSelectBean();
				bean.setId(s.getId());
				bean.setText(s.getName());
				bean.setName(s.getName());
				list.add(bean);
			}
			result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		}catch(Exception e){
			logger.error("收款人员模糊搜索下拉框错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}

	@ApiOperation(value="校验所选日期是否在会计期间内", notes = "校验所选日期是否在会计期间内 | 开发者:孙腾 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/validationAccountingDate/{dateStr}", method = RequestMethod.GET)
	public Result validationAccountingDate(@PathVariable("dateStr") String  dateStr, @ApiIgnore UserVO userVO) {
		Result result = new Result();
		commonComponent.validationAccountingDate(dateStr,userVO);
		result.setBizCodeSuccessInfo(SysCode.SUCCESS);
		return result;

	}

}