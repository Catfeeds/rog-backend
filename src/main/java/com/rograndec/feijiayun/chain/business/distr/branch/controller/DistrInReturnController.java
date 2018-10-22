package com.rograndec.feijiayun.chain.business.distr.branch.controller;

import com.rograndec.feijiayun.chain.business.distr.branch.exception.DistrInReturnBizException;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReturnService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.StartAndEndDate;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "distr_in_return",description = "配送管理-分店-配进退货-配进退出单接口服务")
@RestController
@RequestMapping("distr/in/return")
@Validated
public class DistrInReturnController {
	
	private static final Logger logger = LoggerFactory.getLogger(DistrInReturnController.class);
	
	@Autowired
	DistrInReturnService distrInReturnService;
	
	/*@ApiOperation(value = "查询配货单位列表", notes = "查询配货单位列表 | 开发者 李中义 | 开发中")
    @RequestMapping(value="/getDistrUnitList",method=RequestMethod.GET)
    public void getDistrUnitList(){
		
	}*/
	
	@ApiOperation(value = "查询商品库存列表", notes = "查询商品库存列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getGoodsStockList", method=RequestMethod.GET)
    public Result<List<ResponseDistrInDetailVO>> getGoodsStockList(HttpSession session,@ApiIgnore UserVO userVO,
    @ApiParam(name = "param", value = "检索条件", required = false)  @RequestParam(required = false) String param,
    @ApiParam(name = "distrType", value = "配送类型（0-总部配送，4-直调配送）", required = true)  @RequestParam(required = true) Integer distrType,
    @ApiParam(name = "supplierId", value = "供货单位ID", required = false)  @RequestParam(required = false) Long supplierId){
		Result<List<ResponseDistrInDetailVO>> result=new Result<>();
		try{ 
		/* UserVO userVO = (UserVO) session.getAttribute("user");*/
		 if(param!=null&&!"".equals(param.trim()))param=param.trim();
		 result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getGoodsStockList(userVO.getEnterpriseId(),param,userVO,distrType,supplierId));
		 return result;
		}catch (BusinessException e) {

			logger.error("查询商品库存列表异常:"+e.getMessage(),e);
//			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
		}catch(Exception e){
			logger.error("查询商品库存列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "查询配进入库单列表", notes = "查询已入库的配进入库单列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrInOrderList", method=RequestMethod.POST)
    public Result<Page<List<ResponseDistrInVO>>> getDistrReturnInOrderList(HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestBody DistrInReturnParamVO distrInReturnParamVO){
		Result<Page<List<ResponseDistrInVO>>> result=new Result<>();
		try{
			distrInReturnParamVO.setStartDate(StartAndEndDate.getStartTime(distrInReturnParamVO.getStartDate()));
			distrInReturnParamVO.setEndDate(StartAndEndDate.getEndTime(distrInReturnParamVO.getEndDate()));
			Integer pageNo = distrInReturnParamVO.getPageNo();
	        Integer pageSize = distrInReturnParamVO.getPageSize();
			if((pageNo==null|| pageNo<= 0) || pageSize==null || pageSize<= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),"参数错误");
                return result;
            }
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			/*//暂时用于测试
			user.setEnterpriseId(102L);*/
			distrInReturnParamVO.setEnterpriseId(userVO.getEnterpriseId());
			Page<List<ResponseDistrInVO>> page = new Page<>(pageNo,pageSize);
			distrInReturnParamVO.setStart(page.getStart());
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrReturnInOrderList(page,distrInReturnParamVO));
			return result;
		}catch (BusinessException e) {
			// TODO: handle exception
			logger.error("查询配进入库单列表异常:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
			return result;
		} catch(Exception e){
			logger.error("查询配进入库单列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
		
	}
	
	@ApiOperation(value = "查询配进入库单明细列表", notes = "根据配进入库单ID查询配进入库单明细列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrInOrderDtlList", method=RequestMethod.POST)
    public Result<Page<List<ResponseDistrInDetailVO>>> getDistrReturnInOrderDtlList(HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestBody DistrInReturnParamVO distrInReturnParamVO){
		Result<Page<List<ResponseDistrInDetailVO>>> result=new Result<>();
		try{
			distrInReturnParamVO.setStartDate(StartAndEndDate.getStartTime(distrInReturnParamVO.getStartDate()));
			distrInReturnParamVO.setEndDate(StartAndEndDate.getEndTime(distrInReturnParamVO.getEndDate()));
			Integer pageNo = distrInReturnParamVO.getPageNo();
	        Integer pageSize = distrInReturnParamVO.getPageSize();
			if((pageNo==null|| pageNo<= 0) || pageSize==null|| pageSize<= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),"参数错误");
                return result;
            }
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			/*//暂时用于测试
			user.setEnterpriseId(102L);*/
			if(distrInReturnParamVO.getDistrInId()==null) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
			}
			distrInReturnParamVO.setEnterpriseId(userVO.getEnterpriseId());
			Page<List<ResponseDistrInDetailVO>> page = new Page<>(pageNo, pageSize);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrReturnInOrderDtlList(page,distrInReturnParamVO));
			return result;
		}catch(Exception e){
			logger.error("查询配进入库单明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "查询配进退出单列表", notes = "根据状态查询配进退出单列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrReturnOrderList", method=RequestMethod.GET)
    public Result<Page<List<ResponseDistrInReturnVO>>> getDistrReturnOrderList(
    		HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestParam(required = true)
            @ApiParam(name = "pageNo", value = "当前页码", required = true)
                    Integer pageNo,
            @RequestParam(required = true)
            @ApiParam(name = "pageSize", value = "显示条数", required = true)
                    Integer pageSize,
            @RequestParam(required = false)
            @ApiParam(name = "distrUnitCode", value = "配货单位编码", required = false)
                    String distrUnitCode,
            @RequestParam(required = false)
            @ApiParam(name = "distrUnitName", value = "配货单位名称", required = false)
                    String distrUnitName,
            @RequestParam(required = false)
            @ApiParam(name = "code", value = "配进退出单号", required = false)
                    String code,
            @RequestParam(required = false)
            @ApiParam(name = "distrType", value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）", required = false)
    				Integer distrType,
            @RequestParam(required = false)
            @ApiParam(name = "returnManName", value = "退货人员名称", required = false)
                    String returnManName,
            @RequestParam(required = false)
            @ApiParam(name = "startTime", value = "开始时间", required = false)
    				Date startTime,
            @RequestParam(required = false)
            @ApiParam(name = "endTime", value = "结束时间", required = false)
                    Date endTime,
            @RequestParam(required = false)
            @ApiParam(name = "status", value = "审核状态(21-待审核 22-审核通过 23-审核驳回 30-待出库 33-已完成 34-已取消)", required = false)
                    Integer status,
            @RequestParam(required = false,defaultValue="0")
            @ApiParam(name = "dateOrder", value = "日期排序 0/倒序；1/正序;默认0", required = false)
    	     		Integer dateOrder,
    		@RequestParam(required = false,defaultValue="0")
    		@ApiParam(name = "codeOrder", value = "单号排序 0/倒序；1/正序;默认0", required = false)
    	     		Integer codeOrder){
		Result<Page<List<ResponseDistrInReturnVO>>> result=new Result<>();
		try{
			startTime=StartAndEndDate.getStartTime(startTime);
			endTime=StartAndEndDate.getEndTime(endTime);
			if((pageNo!=null&& pageNo<= 0) || (pageSize!=null&& pageSize<= 0)){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
			/*UserVO user=(UserVO) session.getAttribute("user");*/
			/*//暂时用于测试
			user.setEnterpriseId(102L);*/
			Page<List<ResponseDistrInReturnVO>> page = new Page<>(pageNo, pageSize);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrReturnOrderList(userVO.getEnterpriseId(),
					 distrUnitCode, distrUnitName, code, distrType, returnManName,startTime, endTime, status,dateOrder,codeOrder,page));
			return result;
		}catch(Exception e){
			logger.error("查询配进退出单明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}     		
	}
	
	@ApiOperation(value = "查询配进退出单明细列表", notes = "根据配进退出单ID查询配进退出单明细列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrReturnOrderDtlList", method=RequestMethod.GET)
    public Result<ResponseDistrInReturnDetailInfoVO> getDistrReturnOrderDtlList(HttpSession session,@ApiIgnore UserVO userVO,
    	@ApiParam(name = "id", value = "配进退出单id", required = true)  @RequestParam(required = true) Long id){
		Result<ResponseDistrInReturnDetailInfoVO> result=new Result<>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrReturnOrderDtlList(userVO,id));
			return result;
		}catch(Exception e){
			logger.error("查询配进退出单明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
		
	}
	
	@ApiOperation(value = "保存配进退出单", notes = "保存配进退出单 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/saveDistrReturnOrder", method=RequestMethod.POST)
    public Result<String> saveDistrReturnOrder(HttpSession session,@ApiIgnore UserVO userVO,
    		@ApiParam(name="requsetDistrReturnInSaveOrUpdateVO", value="保存配进退出接口实体" , required=true)
    @RequestBody RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO) throws Exception {
		Result<String> result=new Result<>();
		try{
			String msg=distrInReturnService.saveDistrReturnOrder(userVO,requsetDistrReturnInSaveOrUpdateVO);
			if(msg==null){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
				return result;
			}
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),msg);
            return result;
		}catch (DistrInReturnBizException e) {
			// TODO: handle exception
			logger.error("保存配进退出单异常:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),e.getMessage());
			return result;
		} catch (BusinessException e) {
			// TODO: handle exception
			logger.error("保存配进退出单异常:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
			return result;
		} catch(Exception e){
			logger.error("保存配进退出单异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "修改配进退出单", notes = "修改配进退出单 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/updateDistrReturnOrder", method=RequestMethod.POST)
    public Result<String> updateDistrReturnOrder( HttpSession session,@ApiIgnore UserVO userVO,
    		@ApiParam(name="requsetDistrReturnInSaveOrUpdateVO", value="修改配进退出接口实体" , required=true)
            @RequestBody RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO){
		Result<String> result=new Result<>();
		try{
			
			String msg=distrInReturnService.updateDistrReturnOrder(userVO,requsetDistrReturnInSaveOrUpdateVO);
			if(msg==null){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
				return result;
			}
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),msg);
            return result;
		}catch (DistrInReturnBizException e) {
			// TODO: handle exception
			logger.error("修改配进退出单异常:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),e.getMessage());
			return result;
		} catch (BusinessException e) {
			// TODO: handle exception
			logger.error("修改配进退出单异常:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
			return result;
		}  catch(Exception e){
			logger.error("修改配进退出单异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "取消配进退出单", notes = "取消配进退出单 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/cancelDistrReturnOrder/{id}", method=RequestMethod.POST)
	@ApiImplicitParam(name = "id", value = "配进退出单id", required = true, paramType="path")
    public Result<String> cancelDistrReturnOrder( HttpSession session,@ApiIgnore UserVO userVO,@PathVariable Long id){
		Result<String> result=new Result<>();
		try{
			
			String msg=distrInReturnService.cancelDistrReturnOrder(userVO.getEnterpriseId(),id,userVO);
			if(msg==null){
				result.setBizCodeSuccessInfo(SysCode.SUCCESS);
				return result;
			}
			result.setBizCodeFallInfo(SysCode.FAIL.getCode(),msg);
            return result;
		}catch(Exception e){
			logger.error("取消配进退出单异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "导出Excel", notes = "按照模版将配进退出单信息导出至Excel | 开发者 张东东 | 已联调")
    @RequestMapping(value="/exportExcel/{id}", method=RequestMethod.GET)
	@ApiImplicitParam(name = "id", value = "配进退出单id", required = true, paramType="path")
    public void exportExcel(HttpServletResponse response,HttpSession session,@ApiIgnore UserVO userVO,@PathVariable Long id){
		OutputStream output=null;
		try{
			
			output=response.getOutputStream();
			//这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name="配进退出单信息";
			response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");
            distrInReturnService.exportExcel(output,userVO,id);
		}catch(Exception e){
			logger.error("按照模版将配进退出单信息导出至Excel异常:" + e.getMessage(), e);
		}
	}
	@ApiOperation(value = "根据商品数量实时获取商品单价", notes = "根据商品数量实时获取商品单价 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getUnitPriceByQuantity", method=RequestMethod.GET)
	public Result<BigDecimal> getUnitPriceByQuantity(HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestParam(required = true)
    @ApiParam(name = "goodsId", value = "商品id", required = true)
            Long goodsId,
    @RequestParam(required = true)
    @ApiParam(name = "quantityTotal", value = "商品数量", required = true)
			BigDecimal quantityTotal,
	 @RequestParam(required = true)
    @ApiParam(name = "lotId", value = "商品批号id", required = true)
			Long lotId){
		Result<BigDecimal> result=new Result<>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getUnitPriceByQuantity(userVO.getEnterpriseId(),goodsId,lotId,quantityTotal));
			return result;
		}catch(Exception e){
			logger.error("根据商品数量实时获取商品单价异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
		
	}
	
	@ApiOperation(value = "查询要货计划单列表", notes = "查询要货计划单列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrReqPlanOrderList", method=RequestMethod.POST)
    public Result<Page<List<DistrReqPlanVO>>> getDistrReqPlanOrderList(HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestBody DistrInReturnParamVO distrInReturnParamVO){
		Result<Page<List<DistrReqPlanVO>>> result=new Result<>();
		try{
			distrInReturnParamVO.setStartDate(StartAndEndDate.getStartTime(distrInReturnParamVO.getStartDate()));
			distrInReturnParamVO.setEndDate(StartAndEndDate.getEndTime(distrInReturnParamVO.getEndDate()));
			Integer pageNo = distrInReturnParamVO.getPageNo();
	        Integer pageSize = distrInReturnParamVO.getPageSize();
			if((pageNo==null|| pageNo<= 0) || pageSize==null || pageSize<= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),"参数错误");
                return result;
            }
			distrInReturnParamVO.setEnterpriseId(userVO.getEnterpriseId());
			Page<List<DistrReqPlanVO>> page = new Page<>(pageNo,pageSize);
			distrInReturnParamVO.setStart(page.getStart());
			distrInReturnService.getDistrReqPlanOrderList(page,distrInReturnParamVO,userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
			return result;
		}catch (BusinessException e) {
			// TODO: handle exception
			logger.error("查询要货计划单列表异常:"+e.getMessage(),e);
			result.setBizCodeFallInfo(e.getErrorCode(),e.getMessage());
			return result;
		} catch(Exception e){
			logger.error("查询要货计划单列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
		
	}
	
	@ApiOperation(value = "查询要货计划单明细列表", notes = "根据要货计划单ID查询要货计划单明细列表 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrReqPlanOrderDtlList", method=RequestMethod.POST)
    public Result<Page<DistrInRetReqDtlVO>> getDistrReqPlanOrderDtlList(HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestBody DistrInReturnParamVO distrInReturnParamVO){
		Result<Page<DistrInRetReqDtlVO>> result=new Result<>();
		try{
			Integer pageNo = distrInReturnParamVO.getPageNo();
	        Integer pageSize = distrInReturnParamVO.getPageSize();
			if((pageNo==null|| pageNo<= 0) || pageSize==null|| pageSize<= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR.getCode(),"参数错误");
                return result;
            }
			if(distrInReturnParamVO.getDistrInId()==null) {
				result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
			}
			distrInReturnParamVO.setEnterpriseId(userVO.getEnterpriseId());
			Page<DistrInRetReqDtlVO> page = new Page<>(pageNo, pageSize);
			distrInReturnService.getDistrReqPlanOrderDtlList(page,distrInReturnParamVO,userVO);
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
			return result;
		}catch(Exception e){
			logger.error("查询要货计划单明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "根据商品ID获取商品批号列表信息", notes = "根据商品ID获取商品批号列表信息 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getGoodsLotNums", method=RequestMethod.GET)
	public Result<List<DistrInLotNumVO>> getGoodsLotNums(HttpSession session,@ApiIgnore UserVO userVO,
    @RequestParam(required = true)  @ApiParam(name = "goodsId", value = "商品id", required = true) Long goodsId,
    @RequestParam(required = true)  @ApiParam(name = "quantity", value = "要货商品数量", required = true) BigDecimal quantity){
		Result<List<DistrInLotNumVO>> result=new Result<>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getGoodsLotNums(goodsId, userVO.getEnterpriseId(), quantity));
			return result;
		}catch(Exception e){
			logger.error("根据商品ID获取商品批号信息异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	/*@ApiOperation(value = "根据商品数量及批号获取商品单价", notes = "根据商品数量及批号获取商品单价 | 开发者 张东东 | 已完成")
    @RequestMapping(value="/getUnitPriceByQuantityAndLotIds", method=RequestMethod.GET)
	public Result<BigDecimal> getUnitPriceByQuantityAndLotIds(HttpSession session,@ApiIgnore UserVO userVO,
    @RequestParam(required = true) @ApiParam(name = "goodsId", value = "商品id", required = true) Long goodsId,
    @RequestParam(required = true) @ApiParam(name = "quantity", value = "商品数量", required = true)	BigDecimal quantity,
    @RequestParam(value="lotIds[]",required = true) @ApiParam(name = "lotIds[]", value = "商品批号id数组", required = true) List<Long> lotIds){
		Result<BigDecimal> result=new Result<>();
		try{
			
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrPrice(userVO.getParentId(), userVO.getEnterpriseId(), goodsId, lotIds, quantity));
			return result;
		}catch(Exception e){
			logger.error("根据商品数量及批号获取商品单价异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
		
	}*/
	
	@ApiOperation(value = "查询调用要货计划生成的配进退出单明细列表", notes = "根据配进退出单ID查询调用要货计划生成的配进退出单明细列表 | 开发者 张东东 | 已完成")
    @RequestMapping(value="/getDistrReturnReqOrderDtlList", method=RequestMethod.GET)
    public Result<ResponseDistrInReturnDetailInfoVO> getDistrReturnReqOrderDtlList(HttpSession session,@ApiIgnore UserVO userVO,
    	@ApiParam(name = "id", value = "配进退出单id", required = true)  @RequestParam(required = true) Long id){
		Result<ResponseDistrInReturnDetailInfoVO> result=new Result<>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrReturnReqOrderDtlList(userVO,id));
			return result;
		}catch(Exception e){
			logger.error("查询调用要货计划生成的配进退出单明细列表异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "根据商品批号及批号对应数量获取商品平均单价", notes = "根据商品批号及批号对应数量获取商品平均单价 | 开发者 张东东 | 已完成")
    @RequestMapping(value="/getAvgUnitPrice", method=RequestMethod.POST)
	public Result<BigDecimal> getAvgUnitPrice(HttpSession session,@ApiIgnore UserVO userVO,
			@RequestBody List<DistrInLotNumVO> distrInLotNumVOs){
		Result<BigDecimal> result=new Result<>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getAvgUnitPrice(userVO, distrInLotNumVOs));
			return result;
		}catch(Exception e){
			logger.error("根据商品数量及批号获取商品单价异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
	}
	
	@ApiOperation(value = "根据id查询配进退出单", notes = "根据id查询配进退出单 | 开发者 张东东 | 已联调")
    @RequestMapping(value="/getDistrReturnOrderById", method=RequestMethod.GET)
    public Result<ResponseDistrInReturnVO> getDistrReturnOrderById(
    		HttpSession session,@ApiIgnore UserVO userVO,
    		@RequestParam(required = true)
            @ApiParam(name = "id", value = "配进退出单id", required = true)
                    Long id
      ){
		Result<ResponseDistrInReturnVO> result=new Result<>();
		try{
			result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrInReturnService.getDistrReturnOrderById(id));
			return result;
		}catch(Exception e){
			logger.error("根据id查询配进退出单异常:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}     		
	}
}
