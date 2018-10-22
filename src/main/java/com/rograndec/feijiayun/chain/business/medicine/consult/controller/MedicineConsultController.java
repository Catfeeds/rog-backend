package com.rograndec.feijiayun.chain.business.medicine.consult.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.basic.user.service.HealthCheckService;
import com.rograndec.feijiayun.chain.business.basic.user.service.NationService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckVO;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrReqPlanService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.AnalyseStockVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.AnalysisVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.EnterpriseReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.GoodsDistrReqPlanAnalysisVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.GoodsDistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.service.MedicineConsultService;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineConsultVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineGoodsVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineMemberVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsDestroyService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author 孙帮祥
 *
 */
@Api(value = "medicine_consult",description = "药学服务-用药咨询服务接口")
@RestController
@RequestMapping("medicine/consult")
@Validated
public class MedicineConsultController {
	private static final Logger logger = LoggerFactory.getLogger(MedicineConsultController.class);
	@Autowired
	private MedicineConsultService medicineConsultService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private NationService nationService;
	@Autowired
	private GoodsDestroyService goodsDestroyService;
	@ApiOperation(value = "查询计划人员列表", notes = "获取登记人员列表 | 开发者 孙帮祥 | 已联调")
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public Result<List<UserDestroyVO>> getUserList(HttpSession session) {
		Result<List<UserDestroyVO>> result = new Result<List<UserDestroyVO>>();
	    UserVO userVO = (UserVO) session.getAttribute("user");
		try {
			Map map=new HashMap();
			map.put("enterpriseId", userVO.getEnterpriseId());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsDestroyService.getUserList(map));
		} catch (Exception e) {
			logger.error("获取人员信息列表:" + e.getMessage(), e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}
		return result;
	}
	 @ApiOperation(value = "查询会员信息列表", notes = "查询会员信息列表 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getMemberList/{param}",method= RequestMethod.GET)
	    public Result<List<MedicineMemberVO>> getMemberList(HttpSession session,@PathVariable("param") String param){
	        Result<List<MedicineMemberVO>> result = new Result<List<MedicineMemberVO>>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("enterpriseId", userVO.getEnterpriseId());
			map.put("param", param);
	        List<MedicineMemberVO> memberList=medicineConsultService.getMemberList(map);
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS,memberList);
	        return result;
	    }
	 @ApiOperation(value = "查询民族列表", notes = "查询民族列表 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getNationList",method= RequestMethod.GET)
	    public Result<List<Nation>> getNationList(HttpSession session){
	        Result<List<Nation>> result = new Result<List<Nation>>();
	        List<Nation> nationList= nationService.queryNationsAll();
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS,nationList);
	        return result;
	    }
	 @ApiOperation(value = "查询商品信息", notes = "查询商品信息 | 开发者 孙帮祥 | 已联调")
	 @RequestMapping(value="/getGoodsList/{param}",method= RequestMethod.GET)
	    public Result<List<MedicineGoodsVO>> getGoodsList(HttpSession session,@PathVariable("param") String param){
	        Result<List<MedicineGoodsVO>> result = new Result<List<MedicineGoodsVO>>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<String, Object>();
			if(userVO.getChainType()!=null){
				if(userVO.getChainType().equals(0)){
					map.put("enterpriseId", userVO.getEnterpriseId());
					map.put("param", param);
				}else{
					map.put("enterpriseId", userVO.getParentId());
					map.put("param", param);
				}
			}
	        List<MedicineGoodsVO> memberList=medicineConsultService.getGoodsList(map);
	        result.setBizCodeSuccessInfo(SysCode.SUCCESS,memberList);
	        return result;
	    }
	@ApiOperation(value = "保存用药咨询对象", notes = "保存用药咨询对象 | 开发者孙帮祥 | 已联调")
    @RequestMapping(value="/saveMedicineConsult", method=RequestMethod.POST)
    public Result<Object> saveMedicineConsult(HttpSession session,@ApiParam(value = "用药咨询对象", required = true) @RequestBody MedicineConsultVO medicineConsultVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
			     	medicineConsultService.save(userVO, medicineConsultVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("用药咨询单添加数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("用药咨询单添加数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	@ApiOperation(value = "修改用药咨询对象", notes = "修改用药咨询对象 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/updateMedicineConsult", method=RequestMethod.POST)
	public Result<Object> updateMedicineConsult(HttpSession session,@ApiParam(value = "修改用药咨询对象", required = true) @RequestBody MedicineConsultVO medicineConsultVO){
		 Result<Object> result = new Result<Object>();
	        UserVO userVO = (UserVO) session.getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			try {
				medicineConsultService.update(userVO, medicineConsultVO);
				   result.setBizCodeSuccessInfo(SysCode.SUCCESS);
			}catch (BusinessException e) {
				result.setBizCodeFallInfo("200100",e.getMessage());
				logger.error("用药咨询修改数据错误:"+e.getMessage(),e);
			}catch (Exception e) {
				result.setBizCodeFallInfo(SysCode.FAIL);
				logger.error("用药咨询修改数据错误:"+e.getMessage(),e);
			}
			return result;
	}
	
	@ApiOperation(value="查询用药咨询单列表", notes = "查询用药咨询单列表 | 开发者 孙帮祥|已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getMedicineConsultList", method = RequestMethod.GET)
	@ResponseBody
	public Result<Page<MedicineConsultVO>> getMedicineConsultList(HttpServletRequest request,
				@ApiParam(value = "页码", required = true) @RequestParam int pageNo,
				@ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize, 
				@ApiParam(value = "开始日期", required = false) @RequestParam(required=false) Date startDate,
				@ApiParam(value = "结束日期", required = false) @RequestParam(required=false) Date endDate,
				@ApiParam(value = "咨询单号", required = false) @RequestParam(required=false) String code,
				@ApiParam(value = "登记人员", required = false) @RequestParam(required=false) String registerManName,
				@ApiParam(value = "会员卡号", required = false) @RequestParam(required=false) String memberCardCode,
				@ApiParam(value = "姓名", required = false) @RequestParam(required=false) String name,
				@ApiParam(value = "排序字段", required = false) @RequestParam(required=false) String sortField,
				@ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
	    	Result<Page<MedicineConsultVO>> result = new Result<Page<MedicineConsultVO>>();
	        try{
	        	if(pageNo <= 0 || pageSize <= 0){
	        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
	    			return result;
	        	}
	        	Page<MedicineConsultVO> page = new Page<MedicineConsultVO>(pageNo, pageSize);
	        	HttpSession session = request.getSession(true);
	        	UserVO loginUser = (UserVO) session.getAttribute("user");
	        	Long enterpriseId=loginUser.getEnterpriseId();
	        	if(sortField!=null){
	        		if(sortField.equals("registerDate")){
	        			sortField="register_date";
	        		}
	        	}
	        	if(pageNo==1){
	        		pageNo=0;
	        	}else{
	        		pageNo=pageNo-1;
	        		pageNo=pageNo*pageSize;
	        	}
	        	Map map=new HashMap();
	        	map.put("enterpriseId", enterpriseId);
	        	map.put("pageNo", pageNo);
	        	map.put("pageSize", pageSize);
	        	map.put("code", code);
	        	map.put("registerManName", registerManName);
	        	map.put("memberCardCode", memberCardCode);
	           	map.put("name", name);
	        	map.put("sortField", sortField);
	        	map.put("sort", sort);
	        	map.put("startDate", startDate);
	        	map.put("endDate", endDate);
	        	medicineConsultService.getList(page, map);
	        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
	        }catch(Exception e){
	        	logger.error("用药咨询列表分页查询错误:"+e.getMessage(),e);
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
	        }
			return result;
		}
	@ApiOperation(value = "查询用药咨询单明细", notes = "查询用药咨询单明细 | 开发者 孙帮祥 | 已联调")
    @RequestMapping(value="/getMedicineConsultDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "健康检查单主键", required = true,paramType = "path")
    public Result<MedicineConsultVO> getMedicineConsultDetail(@PathVariable Long id){
        Result<MedicineConsultVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,medicineConsultService.getById(id));
        return result;
    }
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将药咨询单明细导出至Excel | 开发者 孙帮祥 | 开发完")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
    public void exportExcel(HttpSession session,HttpServletResponse response, @PathVariable("id") Long id) throws IOException{
		String name = "用药咨询";
		// 输出Excel文件
		  UserVO userVO = (UserVO) session.getAttribute("user");
		OutputStream output = response.getOutputStream();
		// 这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
		medicineConsultService.exportExcel(output, id, userVO);
	}
}
