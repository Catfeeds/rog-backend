package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor.ReturnInApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlanDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReturnService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.storage.storehouse.dao.TemperatureHumidityDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.LockQtyArgVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DistrInReturnServiceImpl implements DistrInReturnService {

	@Autowired
	private DistrInReturnMapper distrInReturnMapper;
	@Autowired
	private DistrInReturnDetailMapper distrInReturnDetailMapper;
	@Autowired
	private CommonComponent commonComponent;
	@Autowired
	private DistrInMapper distrInMapper;
	@Autowired
	private DistrInDetailMapper distrInDetailMapper;
	@Autowired
	private TemperatureHumidityDetailMapper temperatureHumidityDetailMapper;
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	@SuppressWarnings("rawtypes")
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;
	@Autowired
	ReturnInApprovalProcessor returnInApprovalProcessor;
	@Autowired
	private PriceOrderMapper priceOrderMapper;
	@Autowired
	private PriceOrderDetailMapper priceOrderDetailMapper;
	@Autowired
	private DistrReqPlanMapper distrReqPlanMapper;
	@Autowired
	private ManageConfigService manageConfigService;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private DistrReqPlanDetailMapper distrReqPlanDetailMapper;


	@Override
	public Page<List<ResponseDistrInVO>> getDistrReturnInOrderList(Page<List<ResponseDistrInVO>> page,
			DistrInReturnParamVO distrInReturnParamVO) {
		Integer distributionManage = distrReqPlanMapper.getEnterpriseDistrType(distrInReturnParamVO.getEnterpriseId());
		if(distributionManage == null)  throw new BusinessException("配货管理设置不存在");
		// 0禁止直购，返回值中不能有直接调配类型
		if(distributionManage.equals(0)) distributionManage=null;
		distrInReturnParamVO.setDistributionManage(distributionManage);
		distrInReturnParamVO.setDistributionHead(DistributionType.DISTRIBUTION_HEAD.getCode());
		List<Integer> distrTypes = new ArrayList<>();
		List<Integer> orderStatus = new ArrayList<>();
		distrTypes.add(DistributionType.DISTRIBUTION_HEAD.getCode());
		distrTypes.add(DistributionType.DIRECT_DISTRIBUTION.getCode());
		distrInReturnParamVO.setDistrTypes(distrTypes);
		orderStatus.add(0);
		orderStatus.add(DistrInReturnStatus.PENDING_AUDIT);
		orderStatus.add(DistrInReturnStatus.AUDITED);
		orderStatus.add(DistrInReturnStatus.AUDIT_REJECT);
		orderStatus.add(DistrInReturnStatus.WAIT_OUT);
		orderStatus.add(DistrInReturnStatus.OUTTING);
		orderStatus.add(DistrInReturnStatus.FINISHED);
		orderStatus.add(DistrInReturnStatus.WAIT_BILL);
		orderStatus.add(DistrInReturnStatus.PART_BILL);
		distrInReturnParamVO.setOrderStatus(orderStatus);
		Integer total=distrInReturnMapper.getDistrReturnInOrderListTotalNum(distrInReturnParamVO);
		page.setTotalRecord(total);
		if(total==0) return page;
		List<ResponseDistrInVO> resList=distrInReturnMapper.getDistrReturnInOrderList(distrInReturnParamVO);
		//设置配送类型
		for(ResponseDistrInVO vo:resList) {
			vo.setDistrTypeName(DistributionType.getName(vo.getDistrType()));
		}
		page.setResult(resList);
		return page;
	}

	@Override
	public Page<List<ResponseDistrInDetailVO>> getDistrReturnInOrderDtlList(Page<List<ResponseDistrInDetailVO>> page,DistrInReturnParamVO distrInReturnParamVO) {
		
		List<Integer> orderStatus = new ArrayList<>();
		orderStatus.add(0);
		orderStatus.add(DistrInReturnStatus.PENDING_AUDIT);
		orderStatus.add(DistrInReturnStatus.AUDITED);
		orderStatus.add(DistrInReturnStatus.AUDIT_REJECT);
		orderStatus.add(DistrInReturnStatus.WAIT_OUT);
		orderStatus.add(DistrInReturnStatus.OUTTING);
		orderStatus.add(DistrInReturnStatus.FINISHED);
		orderStatus.add(DistrInReturnStatus.WAIT_BILL);
		orderStatus.add(DistrInReturnStatus.PART_BILL);
		distrInReturnParamVO.setOrderStatus(orderStatus);
		
		Integer total=distrInReturnMapper.getDistrReturnInOrderDtlListTotalNum(distrInReturnParamVO);
		
		List<ResponseDistrInDetailVO> list=distrInReturnMapper.getDistrReturnInOrderDtlList(distrInReturnParamVO);
		Long enterpriseId=distrInReturnParamVO.getEnterpriseId();
		total=total==null?0:total;
		if(total!=0){
			//获取商品及对应批次的库存的信息
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("enterpriseId", enterpriseId);
			map.put("baseOrderType", OrderRule.DISTR_IN.getType());
			List<Integer> status = new ArrayList<>();
			status.add(DistrInReturnStatus.PENDING_AUDIT);
			status.add(DistrInReturnStatus.AUDITED);
			status.add(DistrInReturnStatus.AUDIT_REJECT);
			status.add(DistrInReturnStatus.WAIT_OUT);
			status.add(DistrInReturnStatus.OUTTING);
			map.put("status", status);
			for(ResponseDistrInDetailVO detail:list){
				map.put("goodsId", detail.getGoodsId());
				map.put("lotId", detail.getLotId());
				//获取库存可用数量
				BigDecimal usableQuantity=distrInReturnMapper.getUsableQuantity(map);
				usableQuantity=usableQuantity==null? BigDecimal.ZERO :usableQuantity;//库存没有的设置为0
				
				map.put("detailId", detail.getDetailId());
				//获取待审核，待出库的配进退出单退货数量
				BigDecimal waitRetQua=distrInReturnMapper.getDisInReturnDtlRetQuantity(map);
				//获取可退数量
				BigDecimal canReturnQuantity=detail.getCanReturnQuantity();
				canReturnQuantity=canReturnQuantity==null? BigDecimal.ZERO :canReturnQuantity;
				//减去待审核，待出库的配进退出单退货数量
				canReturnQuantity=canReturnQuantity.subtract(waitRetQua);
				//将较小的值设置为 前端用来判断最大值的数量
				if(usableQuantity.compareTo(canReturnQuantity)>0){
					detail.setUsableQuantity(canReturnQuantity);
				}else{
					detail.setUsableQuantity(usableQuantity);
				}
			}
		}
		page.setTotalRecord(total);
		page.setResult(list);
		return page;
	}

	@Override
	public List<ResponseDistrInDetailVO> getGoodsStockList(Long enterpriseId, String param,UserVO userVO,Integer distrType,Long supplierId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("enterpriseId", enterpriseId);
		map.put("param", param);
		if(distrType == null) throw new BusinessException("缺少配货类型");
		map.put("chainType", userVO.getChainType());
		//若是总部配送
		if(DistributionType.DISTRIBUTION_HEAD.getCode() == distrType) map.put("parentId", userVO.getParentId());
		//若是直调配送
		if(DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType)	map.put("supplierId", supplierId);

		List<ResponseDistrInDetailVO> list=distrInReturnMapper.getGoodsStockList(map);
		//获取成本价
		for(ResponseDistrInDetailVO responseDistrInDetailVO:list){
			BigDecimal unitPrice =commonComponent.getCostPriceWithLotId(enterpriseId, responseDistrInDetailVO.getGoodsId(),responseDistrInDetailVO.getLotId()
					, new BigDecimal(1));//默认一开始的数量为1，成本价是根据数量动态变化的
			responseDistrInDetailVO.setUnitPrice(unitPrice);
			responseDistrInDetailVO.setAmount(unitPrice);
			PriceOrderDetail priceOrderDetail=getGoodsTaxRateId(userVO, responseDistrInDetailVO.getGoodsId());
			//设置最近入库的税率为配进退出税率，若不存在则使用商品业务表默认配送税率
			if(priceOrderDetail==null) continue;
			responseDistrInDetailVO.setTaxRateId(priceOrderDetail.getInTaxRateId() == null?responseDistrInDetailVO.getTaxRateId():priceOrderDetail.getInTaxRateId());
			responseDistrInDetailVO.setTaxRate(priceOrderDetail.getInTaxRate() == null?responseDistrInDetailVO.getTaxRate():priceOrderDetail.getInTaxRate());
		}
		/*page.setResult(list);*/
		return list;
	}
	
	private PriceOrderDetail getGoodsTaxRateId(UserVO userVO,Long goodsId){
		PriceOrder priceOrder = null;
		Long priceOrderId = null;
		PriceOrderDetail priceOrderDetail =null;
		// 分店
		priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), userVO.getEnterpriseId(), userVO.getParentId());
		if(priceOrder != null){
			priceOrderId = priceOrder.getId();
			priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(userVO.getEnterpriseId(), priceOrderId, goodsId);
		}
		
		return priceOrderDetail;
	}

	@Override
	public Page<List<ResponseDistrInReturnVO>> getDistrReturnOrderList(Long enterpriseId,
			String distrUnitCode, String distrUnitName, String code, Integer distrType,
			String returnManName, Date startTime, Date endTime, Integer status,Integer dateOrder,Integer codeOrder,
			Page<List<ResponseDistrInReturnVO>> page) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("enterpriseId", enterpriseId);
		map.put("start", page.getStart());
		map.put("pageSize", page.getPageSize());
		map.put("distrUnitCode", distrUnitCode==null?null:distrUnitCode.trim());
		map.put("distrUnitName", distrUnitName==null?null:distrUnitName.trim());
		map.put("code", code==null?code:code.trim());
		map.put("distrType", distrType);
		map.put("returnManName", returnManName==null?null:returnManName.trim());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<Integer> list = new ArrayList<>();
		if(status != null) {
			list.add(status);
			if(DistrInReturnStatus.WAIT_OUT == status) {
				list.add(DistrInReturnStatus.OUTTING);
			}
			if(DistrInReturnStatus.FINISHED == status) {
				list.add(DistrInReturnStatus.WAIT_BILL);
				list.add(DistrInReturnStatus.PART_BILL);
			}
		}
		map.put("list", list);
		map.put("status", status);
		map.put("dateOrder", dateOrder);
		map.put("codeOrder", codeOrder);
		page.setTotalRecord(distrInReturnMapper.getDistrReturnOrderListTotalNum(map));
		List<ResponseDistrInReturnVO> reslist=distrInReturnMapper.getDistrReturnOrderList(map);
		for(ResponseDistrInReturnVO res:reslist){
			//设置配货类型
			setResponseDistrInReturnVODistrTypeName(res);
			//若该单据被引用则不予许修改
			Integer sign=distrInReturnMapper.getDistrInReturnOut(res.getId());
			//判断是否可显示修改    0隐藏  1 显示
			if(sign!=null && sign!=0){
				res.setIsHidden(0);
			}else{
				res.setIsHidden(1);
			}
			//设置状态
			setResponseDistrInReturnVOStatusName(res);
		}
		page.setResult(reslist);
		return page;
	}
	
	private void setResponseDistrInReturnVODistrTypeName(ResponseDistrInReturnVO res){
		/*配货类型（0-总部配送；3-分店间调剂；4-直调配送）*/
		res.setDistrTypeName(DistributionType.getName(res.getDistrType()));
	}
	
	private void setResponseDistrInReturnVOStatusName(ResponseDistrInReturnVO res){
		res.setStatusName(DistrInReturnStatus.getStatusDesc(res.getStatus()));
	}

	@Override
	public ResponseDistrInReturnDetailInfoVO getDistrReturnOrderDtlList(UserVO userVO, Long recordId) {
		Long enterpriseId = userVO.getEnterpriseId();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("enterpriseId", enterpriseId);
		map.put("recordId",recordId);
		ResponseDistrInReturnDetailInfoVO res=distrInReturnMapper.getDistrReturnOrderDtlList(map);
		if(res==null) return res;
		//若是调用添加
		if(res.getBaseOrderId()!=null){
			//若是调用配筋入库单
			if(res.getBaseOrderCode().equals(OrderRule.DISTR_IN.getType()+"")) {
				List<ResponseDistrInReturnDetailVO> list=res.getList();
				for(ResponseDistrInReturnDetailVO detail:list){
					//获取可退数量
					Map<String,Object> paramap=new HashMap<String,Object>();
					paramap.put("detailId", detail.getBaseOrderDtlId());
					paramap.put("goodsId", detail.getGoodsId());
					paramap.put("lotNumber", detail.getLotNumber());
					paramap.put("enterpriseId", enterpriseId);
					BigDecimal canReturnQuantity=distrInReturnMapper.getDisInDetailCanReturnQuantity(paramap);
					canReturnQuantity=canReturnQuantity==null? BigDecimal.ZERO:canReturnQuantity;
					//减去待审核，待出库的配进退出单退货数量；加上本单自己的数量
					List<Integer> status = new ArrayList<>();
					status.add(DistrInReturnStatus.PENDING_AUDIT);
					status.add(DistrInReturnStatus.AUDITED);
					status.add(DistrInReturnStatus.AUDIT_REJECT);
					status.add(DistrInReturnStatus.WAIT_OUT);
					status.add(DistrInReturnStatus.OUTTING);
					map.put("status", status);
					map.put("baseOrderType", OrderRule.DISTR_IN.getType());
					map.put("goodsId", detail.getGoodsId());
					map.put("lotId", detail.getLotId());
					map.put("detailId", detail.getId());
					BigDecimal waitRetQua=distrInReturnMapper.getDisInReturnDtlRetQuantity(map);
					canReturnQuantity=canReturnQuantity.subtract(waitRetQua).add(detail.getQuantity());

					BigDecimal usableQuantity=detail.getUsableQuantity();
					usableQuantity=usableQuantity==null? BigDecimal.ZERO:usableQuantity;
					//将较小的值设置为 前端用来判断最大值的数量
					if(usableQuantity.compareTo(canReturnQuantity)>0){
						detail.setUsableQuantity(canReturnQuantity);
					}else{
						detail.setUsableQuantity(usableQuantity);
					}
				}
			//若是调用要货计划单
			}else if(res.getBaseOrderCode().equals(OrderRule.REQUIRE_PLAN.getType()+"")) {

			}
		}else{
			//直接添加  将可用数量+退货数量
			List<ResponseDistrInReturnDetailVO> list=res.getList();
			for(ResponseDistrInReturnDetailVO detail:list){
				detail.setUsableQuantity(detail.getUsableQuantity().add(detail.getQuantity()));
			}
		}
		/*配货类型（0-总部配送；3-分店间调剂；4-直调配送）*/
		res.setDistrTypeName(DistributionType.getName(res.getDistrType()));
		res.setEnterpriseName(userVO.getEnterpriseName());
		//增加整单折扣金额字段
		BigDecimal wholeDiscountValue = res.getAmountTotal().multiply(res.getWholeDiscount());
		res.setWholeDiscountValue(wholeDiscountValue);
		return res;
	}

	private String checkReturnDate(Date inDate,Date returnDate){
		if(inDate==null) return null;
		returnDate=returnDate==null?new Date():returnDate;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
		String strInDate=simpleDateFormat.format(inDate);
		String strReturnDate=simpleDateFormat.format(returnDate);
		Integer strIn=Integer.parseInt(strInDate);
		Integer strRe=Integer.parseInt(strReturnDate);
		if((strIn-strRe)>0) return "退货日期不合法";
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveDistrReturnOrder(UserVO user,RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO) throws Exception {
		voildAddOrUpdateParam(requsetDistrReturnInSaveOrUpdateVO);
		commonComponent.generateAndSaveDistrInReturn(user,requsetDistrReturnInSaveOrUpdateVO,false);
		return null;
	}
	
	/**
	 * 验证参数中的单价数量及折扣
	 * @param requsetDistrReturnInSaveOrUpdateVO
	 */
	private void voildAddOrUpdateParam(RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO) {
		BigDecimal max = new BigDecimal(100);
		BigDecimal wholeDiscount = requsetDistrReturnInSaveOrUpdateVO.getWholeDiscount();
		BigDecimal wholeDiscountAmount = requsetDistrReturnInSaveOrUpdateVO.getWholeDiscountAmount();
		if(wholeDiscount.compareTo(BigDecimal.ZERO) < 0 || wholeDiscount.compareTo(max) >0)  throw new BusinessException("整单折扣在0到100之间");
		if(wholeDiscountAmount.compareTo(BigDecimal.ZERO) < 0)  throw new BusinessException("整单优惠不小于0");
		List<RequsetDistrReturnDtlSaveOrUpdateVO> requsetDistrReturnDtlSaveOrUpdateVO = requsetDistrReturnInSaveOrUpdateVO.getRequsetDistrReturnDtlSaveOrUpdateVO();
		BigDecimal unitPrice = null;
		BigDecimal quantity = null;
		BigDecimal lineDiscount = null;
		for(RequsetDistrReturnDtlSaveOrUpdateVO vo:requsetDistrReturnDtlSaveOrUpdateVO) {
			unitPrice = vo.getUnitPrice();
			if(unitPrice.compareTo(BigDecimal.ZERO) < 0) throw new BusinessException("单价不小于0");
			quantity = vo.getQuantity();
			if(quantity.compareTo(BigDecimal.ZERO) < 0) throw new BusinessException("数量不小于0");
			lineDiscount = vo.getLineDiscount();
			if(lineDiscount.compareTo(BigDecimal.ZERO) < 0 || lineDiscount.compareTo(max) >0)  throw new BusinessException("行折扣在0到100之间");
		}
	}

	private void lockStockByDetail(UserVO user, DistrInReturnDetail detail) {
		LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
		lockQtyArgVO.setEnterpriseId(detail.getEnterpriseId());
		lockQtyArgVO.setParentId(detail.getParentId());
		lockQtyArgVO.setBaseOrderId(detail.getInReturnId());
		lockQtyArgVO.setBaseOrderCode(detail.getInReturnCode());
		lockQtyArgVO.setBaseOrderType(detail.getOrderType());
		lockQtyArgVO.setBaseOrderDate(detail.getInReturnDate());
		lockQtyArgVO.setGoodsId(detail.getGoodsId());
		lockQtyArgVO.setLotId(detail.getLotId());
		lockQtyArgVO.setQuantity(detail.getQuantity());
		lockQtyArgVO.setUser(user);
		commonComponent.lockStockAndCost(lockQtyArgVO);
	}
	
	/**
	 * 
	 * @param user
	 * @param distrInReturn
	 * @throws Exception 
	 */

	
	/**
	 * 设置企业基础单据信息
	 * @param distrInReturn
	 */
	private void setDistrInReturnBaseOrderInfo(DistrInReturn distrInReturn,DistrIn distrIn){
		distrInReturn.setBaseOrderId(distrIn.getId());
		distrInReturn.setBaseOrderType(distrIn.getOrderType());
		distrInReturn.setBaseOrderCode(distrIn.getCode());
		distrInReturn.setBaseOrderDate(distrIn.getInDate());

		//设置出库单位的信息
		distrInReturn.setOutboundUnitId(distrIn.getOutboundUnitId());
		distrInReturn.setOutboundUnitCode(distrIn.getOutboundUnitCode());
		distrInReturn.setOutboundUnitName(distrIn.getOutboundUnitName());
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String updateDistrReturnOrder(UserVO user,
			RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO) throws Exception {
		voildAddOrUpdateParam(requsetDistrReturnInSaveOrUpdateVO);
		if(requsetDistrReturnInSaveOrUpdateVO.getId()==null) return "单据id没有传";
		//若该单据被引用则不予许修改
		Integer sign=distrInReturnMapper.getDistrInReturnOut(requsetDistrReturnInSaveOrUpdateVO.getId());
		if(sign!=null && sign!=0) return "该单据被引用，不予许修改";
		
		
		deleteDetailInfo(requsetDistrReturnInSaveOrUpdateVO.getDeleteDetailIds());
		DistrInReturn distrInReturn=new DistrInReturn();
		Long returnManId=requsetDistrReturnInSaveOrUpdateVO.getReturnManId();
		if(returnManId==null) return "缺少退货人员id";
		UserVO returnMan=temperatureHumidityDetailMapper.getUserById(returnManId, user.getEnterpriseId());
		if(returnMan==null) return "该企业员工不存在";
		List<RequsetDistrReturnDtlSaveOrUpdateVO> list=requsetDistrReturnInSaveOrUpdateVO.getRequsetDistrReturnDtlSaveOrUpdateVO();
		if(list.isEmpty()) return "请至少添加一个商品";
		ManageConfig manageConfig = manageConfigService.getManageConfig(user);
		//设置配进退出单据基本信息
		commonComponent.setDistrInReturnBaseInfo(user, distrInReturn, requsetDistrReturnInSaveOrUpdateVO,returnMan,manageConfig);
		Long distrInId=requsetDistrReturnInSaveOrUpdateVO.getBaseOrderId();
		Integer distrType = distrInReturn.getDistrType();
		if(distrInId!=null){//distrInId为null的话，则为普通添加，不需要设置基础单据信息
			if(DistributionType.DISTRIBUTION_HEAD.getCode() == distrType || DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType) {
				DistrIn DistrIn=distrInMapper.selectByPrimaryKey(distrInId);
				if(DistrIn==null) return "不存在的配进入库单据";
				String checkReturnDateMsg=checkReturnDate(DistrIn.getInDate(), requsetDistrReturnInSaveOrUpdateVO.getInReturnDate());
				if(checkReturnDateMsg!=null) return checkReturnDateMsg;
				//设置基础单据信息
				setDistrInReturnBaseOrderInfo(distrInReturn, DistrIn);
			}//要货计划类型的在设置配进退出单据基本信息的时候已经设置
		}
		List<DistrInReturnDetail> paramList=new ArrayList<>(list.size());
		/*//设置状态
		Integer status=distrInReturnMapper.getDistrInReturnStatus(requsetDistrReturnInSaveOrUpdateVO.getId());
		distrInReturn.setStatus(status);*/
		//防止详情id被覆盖
		distrInReturn.setId(null);
		//设置品种总数和总数量
		commonComponent.setDistrInReturnMathInfo(distrInReturn, list);
		//设置行详情内容和计算金额信息
		String amountMsg=commonComponent.setDistrInReturnDetail(list, paramList, user, distrInReturn);
		if(amountMsg!=null) return amountMsg;
		//修改信息
		//设置退出单id
		distrInReturn.setId(requsetDistrReturnInSaveOrUpdateVO.getId());

		distrInReturnMapper.updateByPrimaryKeySelective(distrInReturn);

		// 释放原来配进退出单锁定的库存
		releaseStock(user, distrInReturn);
		//若是配门店间调剂的更改
		if(distrType.equals(DistributionType.SWAP_BETWEEN_STORES.getCode())) {
			//删除原来的详情单信息
			Map<String,Object> delParam = new HashMap<>();
			delParam.put("enterpriseId", user.getEnterpriseId());
			delParam.put("id", distrInReturn.getId());
			distrInReturnDetailMapper.deleteDetailInfoByReturnInId(delParam);
			//添加新的详情单信息
			for(DistrInReturnDetail detail:paramList){
				//设置配进退出单id
				detail.setInReturnId(distrInReturn.getId());
				detail.setId(null);//防止id注入
				distrInReturnDetailMapper.insert(detail);
				// 重新锁定库存
				lockStockByDetail(user, detail);
			}
		}else {
			for(DistrInReturnDetail detail:paramList){
				//设置配进退出单id
				detail.setInReturnId(distrInReturn.getId());
				if(detail.getId()==null){
					//设置创建人的信息
					detail.setCreaterId(user.getUserId());
					detail.setCreaterCode(user.getUserCode());
					detail.setCreaterName(user.getUserName());
					detail.setCreateTime(new Date());
					distrInReturnDetailMapper.insert(detail);
				}else {
					distrInReturnDetailMapper.updateByPrimaryKeySelective(detail);
				}
				// 重新锁定库存
				lockStockByDetail(user, detail);
			}
		}


		  /**
         * 添加/修改完价格调整单后需要申请一条审批工作流
         */
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());

        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(
        		user.getEnterpriseId(),
                enterprise.getName(),
                user.getUserId(),
                user.getUserName(),
                user.getChainType(),
                user.getParentId(),
                user.getChainType().equals(ChainType.Headquarters.getCode()) ? user.getEnterpriseId(): user.getParentId(),
                ApprovalFlowContentModel.getDisInReturnCode(),
                distrInReturn.getId(),
                distrInReturn.getCode(),
				"配进退出审批流"
        );
        approvalFlowComponent.apply(submitApprovalFlowVO,user);
		
		
		return null;
	}
	
	//删除行信息
	private void deleteDetailInfo(List<Long> deleteDetailIds){
		if(deleteDetailIds==null||deleteDetailIds.isEmpty()) return ;
		distrInReturnDetailMapper.deleteDetailInfo(deleteDetailIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String cancelDistrReturnOrder(Long enterpriseId, Long id,UserVO user) {
		//若该单据被引用则不予许取消
		Integer sign=distrInReturnMapper.getDistrInReturnOut(id);
		if(sign!=null && sign!=0) return "该单据被引用，不予许取消";
		Map<String,Object> map=new HashMap<>();
		map.put("enterpriseId", enterpriseId);
		map.put("id", id);
		map.put("status", DistrInReturnStatus.CANCELED);
		distrInReturnMapper.cancelDistrReturnOrder(map);
		distrInReturnMapper.cancelDistrReturnDetail(map);
		
		DistrInReturn distrInReturn= distrInReturnMapper.selectByPrimaryKey(id);
		/*  approvalFlowComponent.cancel(
	                ApprovalFlowContentModel.getDisInReturnCode()
	                ,distrInReturn.getId()
	                ,user.getChainType()==0?user.getEnterpriseId():user.getParentId()//如果是分店则使用分店的总部的id
	                , returnInApprovalProcessor
	        );*/

		// 释放配进退出单锁定的库存
		releaseStock(user, distrInReturn);

		return null;
	}

	private void releaseStock(UserVO user, DistrInReturn distrInReturn) {
		LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
		lockQtyArgVO.setEnterpriseId(distrInReturn.getEnterpriseId());
		lockQtyArgVO.setParentId(distrInReturn.getParentId());
		lockQtyArgVO.setBaseOrderId(distrInReturn.getId());
		lockQtyArgVO.setBaseOrderType(distrInReturn.getOrderType());
		lockQtyArgVO.setUser(user);
		commonComponent.releaseStockAndCost(lockQtyArgVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream outPut,UserVO user, Long id) {

			LinkedHashMap<String,String> map = new LinkedHashMap<>();
			map.put("goodsCode","商品编码");
			map.put("goodsGenericName","通用名称");
			map.put("dosageName","剂型");
			map.put("goodsSpecification","规格");
			map.put("unitName","单位");
			
			map.put("manufacturer","生产厂商");
			map.put("goodsPlace","产地");
			
			map.put("lotNumber", "批号");
			map.put("productDate", "生产日期");
			map.put("validDate", "有效期至");
			
			map.put("quantity","数量");
			map.put("unitPrice","单价");
			map.put("lineDiscount","折扣");

			map.put("amount","金额");
			map.put("taxRate","税率");
			map.put("notaxRealPrice","不含税单价");

			map.put("notaxRealAmount","不含税金额");
			map.put("taxAmount","税额");
			map.put("remark","备注");
			
			Map<String,Object> paramap=new HashMap<String,Object>();
			paramap.put("enterpriseId", user.getEnterpriseId());
			paramap.put("recordId",id);
			ResponseDistrInReturnDetailInfoVO	ResponseDistrInReturnDetailInfoVO=distrInReturnMapper.getDistrReturnOrderDtlList(paramap);	
			List<ResponseDistrInReturnDetailVO> distrInReturnDetailList= ResponseDistrInReturnDetailInfoVO.getList();
			StringBuilder titleSecondRow = new StringBuilder();
			titleSecondRow.append("配货单位编码:");
			titleSecondRow.append(ResponseDistrInReturnDetailInfoVO.getDistrUnitCode() == null ? "":ResponseDistrInReturnDetailInfoVO.getDistrUnitCode());
			titleSecondRow.append("     ");
			titleSecondRow.append("配货单位名称:");
			titleSecondRow.append(ResponseDistrInReturnDetailInfoVO.getDistrUnitName() == null ? "":ResponseDistrInReturnDetailInfoVO.getDistrUnitName());
			titleSecondRow.append("     ");
			titleSecondRow.append("退货日期:");
			titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ResponseDistrInReturnDetailInfoVO.getInReturnDate()));
			titleSecondRow.append("     ");
			titleSecondRow.append("退货人员:");
			titleSecondRow.append(ResponseDistrInReturnDetailInfoVO.getReturnManName()== null ? "":ResponseDistrInReturnDetailInfoVO.getReturnManName());
			titleSecondRow.append("     ");
			titleSecondRow.append("配进退出单号:");
			titleSecondRow.append(ResponseDistrInReturnDetailInfoVO.getCode()== null ? "":ResponseDistrInReturnDetailInfoVO.getCode());
			titleSecondRow.append("     ");
			titleSecondRow.append("配货类型:");
			/*配货类型（0-总部配送；3-分店间调剂；4-直调配送）*/
			ResponseDistrInReturnDetailInfoVO.setDistrTypeName(DistributionType.getName(ResponseDistrInReturnDetailInfoVO.getDistrType()));  
			titleSecondRow.append(ResponseDistrInReturnDetailInfoVO.getDistrTypeName());
			//两个大标题
			List<String> name = new ArrayList<>();
			name.add(user.getEnterpriseName());
			name.add("配进退出单");
			//第三行标题
			List<String> secondTitle = new ArrayList<>();
			secondTitle.add(titleSecondRow.toString());
			//尾部结算
			StringBuilder sb = new StringBuilder();
			sb.append("总商品金额:   ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getAmountTotal());
			sb.append(";");
			sb.append("折扣:   ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getWholeDiscount());
			sb.append("%");
			sb.append("  ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getAmountTotal().subtract(
					ResponseDistrInReturnDetailInfoVO.getAmountTotal().multiply(
							ResponseDistrInReturnDetailInfoVO.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
			sb.append(";");
			sb.append("优惠:");
			sb.append("   ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getWholeDiscountAmount());
			sb.append(";");
			sb.append("总额:");
			sb.append("   ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getRealAmountTotal());
			sb.append(";");
			sb.append("不含税总额:");
			sb.append("   ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getNotaxRealAmountTotal());
			sb.append(";");
			sb.append("税额:");
			sb.append("   ");
			sb.append(ResponseDistrInReturnDetailInfoVO.getTaxAmountTotal());

			StringBuilder endTotal = new StringBuilder();
			BigDecimal totalQuantity = BigDecimal.ZERO;
			BigDecimal totalAmount = BigDecimal.ZERO;
			BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
			BigDecimal totalTaxAmount = BigDecimal.ZERO;
			for (ResponseDistrInReturnDetailVO d:distrInReturnDetailList) {
				totalQuantity=totalQuantity.add(d.getQuantity());
				totalAmount=totalAmount.add(d.getAmount());
				totalNotaxRealAmount=totalNotaxRealAmount.add(d.getNotaxRealAmount());
				totalTaxAmount=totalTaxAmount.add(d.getTaxAmount());
			}
			endTotal.append(totalQuantity);
			endTotal.append(";");
			endTotal.append(totalAmount);
			endTotal.append(";");
			endTotal.append(totalNotaxRealAmount);
			endTotal.append(";");
			endTotal.append(totalTaxAmount);
			List<String> locationList = new ArrayList<String>();
			locationList.add("quantity");
			locationList.add("amount");
			locationList.add("notaxRealAmount");
			locationList.add("taxAmount");
			purchaseGeneralComponent.commExcelDistrExport(outPut,map,distrInReturnDetailList,
					name,secondTitle,sb.toString(),endTotal.toString(),locationList);
	
	}

	@Override
	public BigDecimal getUnitPriceByQuantity(Long enterpriseId, Long goodsId,Long lotId,BigDecimal quantity) {
		return commonComponent.getCostPriceWithLotId(enterpriseId, goodsId, lotId, quantity);
	}

	@Override
	public void getDistrReqPlanOrderList(Page<List<DistrReqPlanVO>> page, DistrInReturnParamVO distrInReturnParamVO, UserVO userVO) {
		if(userVO.getChainType() != 1) throw new BusinessException("只有自营店可用");
		distrInReturnParamVO.setEnterpriseId(userVO.getEnterpriseId());
		page.setResult(new ArrayList<DistrReqPlanVO>(0));
		Integer total=distrInReturnMapper.getDistrReqPlanOrderListTotalNum(distrInReturnParamVO);
		page.setTotalRecord(total);
		if(total != 0) {
			List<DistrReqPlanVO> resList=distrInReturnMapper.getDistrReqPlanOrderList(distrInReturnParamVO);
			//设置配送类型
			for(DistrReqPlanVO vo:resList) {
				vo.setRequestTypeDesc(DistributionType.getName(vo.getRequestType()));
			}
			page.setResult(resList);
		}
	}

	@Override
	public void getDistrReqPlanOrderDtlList(Page<DistrInRetReqDtlVO> page,DistrInReturnParamVO distrInReturnParamVO,UserVO userVO) {

		DistrInRetReqDtlVO distrInRetReqDtlVO = new DistrInRetReqDtlVO();
		Integer total=distrInReturnMapper.getDistrReqPlanOrderDtlListTotalNum(distrInReturnParamVO);
		page.setTotalRecord(total);
		List<DistrInReqPlanDetailVO> resList=distrInReturnMapper.getDistrReqPlanOrderDtlList(distrInReturnParamVO);
		for(DistrInReqPlanDetailVO vo:resList) {
			List<DistrInLotNumVO> lotNums = getGoodsLotNums(vo.getGoodsId(), distrInReturnParamVO.getEnterpriseId(), vo.getQuantity());
			if(!lotNums.isEmpty()) {
				//将出库数量为0 的批号去掉
				Iterator<DistrInLotNumVO> it = lotNums.iterator();
				while(it.hasNext()) {
					if(it.next().getOutQuantity().compareTo(BigDecimal.ZERO) == 0) it.remove();
				}
				
				//List<Long> lotIds = new ArrayList<>(lotNums.size());
				vo.setLotNums(lotNums);
				
				BigDecimal totalNum = BigDecimal.ZERO;
				if(distrInRetReqDtlVO.getFlag() == 0) {
					for(DistrInLotNumVO lotNum:lotNums) {
						//lotIds.add(lotNum.getLotId());
						totalNum = totalNum.add(lotNum.getOutQuantity());
					}
					
					if(totalNum.compareTo(vo.getQuantity()) < 0) distrInRetReqDtlVO.setFlag(1);
				}
				//商品单价计算
				BigDecimal unitPrice = getAvgUnitPrice(userVO, lotNums);
				/*BigDecimal unitPrice = commonComponent.getDistrPrice(userVO.getParentId(), userVO.getEnterpriseId(),
					vo.getGoodsId(), lotIds, vo.getQuantity());*/
				vo.setUnitPrice(unitPrice);
				
				//商品税率设置
				PriceOrderDetail priceOrderDetail=getGoodsTaxRateId(userVO, vo.getGoodsId());
				//设置最近入库的税率为配进退出税率，若不存在则使用商品业务表默认配送税率
				if(priceOrderDetail != null) {
					vo.setTaxRateId(priceOrderDetail.getInTaxRateId());
					vo.setTaxRate(priceOrderDetail.getInTaxRate());
				}
				//若是配进退出出库则查询默认货位
				if(distrInReturnParamVO.getDistrFlag() == 1) {
					for(DistrInLotNumVO numVO:lotNums) {
						List<WarehouseShelfDefVO> shelfs = commonComponent.getDefShef(vo.getGoodsId(), userVO.getEnterpriseId(), numVO.getLotId(), numVO.getOutQuantity());
						numVO.setShelfs(shelfs);
					}
				}
			}else {
				distrInRetReqDtlVO.setFlag(1);
				vo.setLotNums(lotNums);
			}
		}
		distrInRetReqDtlVO.setDistrInReqPlanDetailVOs(resList);
		page.setResult(distrInRetReqDtlVO);
	}

	/**
	 *
	 * @param goodsId			商品id
	 * @param enterpriseId		企业id
	 * @param quantity			要货计划要求数量
	 * @return
	 */
	@Override
	public List<DistrInLotNumVO> getGoodsLotNums(Long goodsId,Long enterpriseId,BigDecimal quantity){
		Map<String,Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("enterpriseId", enterpriseId);
		List<DistrInLotNumVO> list = distrInReturnMapper.getGoodsLotNums(param);
		if(!list.isEmpty()) {
			//按近效期排序
			Collections.sort(list);
			//获取批号库存总量
			BigDecimal stockTatal = BigDecimal.ZERO;
			for(DistrInLotNumVO vo:list) {
				stockTatal = stockTatal.add(vo.getUsableQuantity());
			}
			//若是库存数量小于要求数量
			if(stockTatal.compareTo(quantity) < 0) {
				for(DistrInLotNumVO vo:list) {
					vo.setOutQuantity(vo.getUsableQuantity());
				}
			}else {//库存数量大于或等于要求数量
				BigDecimal sum=BigDecimal.ZERO;
				for(DistrInLotNumVO vo:list) {
					sum = sum.add(vo.getUsableQuantity());
					if(sum.compareTo(quantity) <= 0) {
						vo.setOutQuantity(vo.getUsableQuantity());
					}else {
						sum = sum.subtract(vo.getUsableQuantity());
						vo.setOutQuantity(quantity.subtract(sum));
						break;
					}
				}
			}
			return list;
		}
		return new ArrayList<>(0);
	}

	@Override
	public BigDecimal getDistrPrice(Long parentId, Long enterpriseId, Long goodsId, List<Long> lotIds,
			BigDecimal quantity) {
		BigDecimal unitPrice = commonComponent.getDistrPrice(parentId, enterpriseId,goodsId, lotIds,quantity);
		return unitPrice;
	}

	@Override
	public ResponseDistrInReturnDetailInfoVO getDistrReturnReqOrderDtlList(UserVO userVO, Long recordId) {
		Long enterpriseId = userVO.getEnterpriseId();
		ResponseDistrInReturnDetailInfoVO res = getDistrReturnOrderDtlList(userVO, recordId);
		List<ResponseDistrInReturnDetailVO> list = res.getList();
		List<ResponseDistrInReturnDetailVO> resList = new ArrayList<ResponseDistrInReturnDetailVO>();
		Map<String,Object> reqParam = new HashMap<>();
		reqParam.put("id",res.getBaseOrderId());
		List<DistrReqPlanDetail> reqDtlList = distrInReturnMapper.getReqDtlList(reqParam);
		Set<Long> paramIds = new HashSet<>();
		Set<Long> lotIds = new HashSet<>();
		for(ResponseDistrInReturnDetailVO vo:list) {
			paramIds.add(vo.getGoodsId());
			lotIds.add(vo.getLotId());
		}

		Map<String,Object> param = new HashMap<>();
		param.put("list", paramIds);
		param.put("enterpriseId", enterpriseId);
		param.put("lotIds", lotIds);
		//List<DistrInLotNumVO> stockLotNums = distrInReturnMapper.getGoodsStockLotNums(param);

		List<Long> goodsIds = new ArrayList<>();
		for(ResponseDistrInReturnDetailVO vo:list) {
			if(goodsIds.contains(vo.getGoodsId())) {
				for(ResponseDistrInReturnDetailVO resvo:resList) {
					if(resvo.getGoodsId().equals(vo.getGoodsId())) {
						DistrInLotNumVO lotNum = new DistrInLotNumVO();
						lotNum.setLotId(vo.getLotId());
						lotNum.setLotNum(vo.getLotNumber());
						lotNum.setProductDate(vo.getProductDate());
						lotNum.setValidDate(vo.getValidDate());
						lotNum.setOutQuantity(vo.getQuantity());
						lotNum.setId(vo.getId());
						lotNum.setGoodsId(vo.getGoodsId());
						/*for(DistrInLotNumVO s:stockLotNums) {
							if(vo.getGoodsId().equals(s.getGoodsId()) && vo.getLotId().equals(s.getLotId())) {
								//可用数量为现有库存数量加上本单所锁的库存数量
								lotNum.setUsableQuantity(s.getUsableQuantity().add(vo.getQuantity()));
								break;
							}
						}*/
						//本商品的退货数量累加
						resvo.setQuantity(resvo.getQuantity().add(vo.getQuantity()));
						//设置金额，不含税金额，税额，行优惠
						resvo.setAmount(resvo.getAmount().add(vo.getAmount()));
						resvo.setNotaxRealAmount(resvo.getNotaxRealAmount().add(vo.getNotaxRealAmount()));
						resvo.setTaxAmount(resvo.getTaxAmount().add(vo.getTaxAmount()));
						resvo.setLineDiscountAmount(resvo.getLineDiscountAmount().add(vo.getLineDiscountAmount()));
						resvo.getLotNums().add(lotNum);
						break;
					}
				}
				break;
			}else {
				goodsIds.add(vo.getGoodsId());
				DistrInLotNumVO lotNum = new DistrInLotNumVO();
				lotNum.setLotId(vo.getLotId());
				lotNum.setLotNum(vo.getLotNumber());
				lotNum.setProductDate(vo.getProductDate());
				lotNum.setValidDate(vo.getValidDate());
				lotNum.setOutQuantity(vo.getQuantity());
				lotNum.setId(vo.getId());
				lotNum.setGoodsId(vo.getGoodsId());
				/*for(DistrInLotNumVO s:stockLotNums) {
					if(vo.getGoodsId().equals(s.getGoodsId()) && vo.getLotId().equals(s.getLotId())) {
						//可用数量为现有库存数量加上本单所锁的库存数量
						lotNum.setUsableQuantity(s.getUsableQuantity().add(vo.getQuantity()));
						break;
					}
				}*/
				//设置要货数量
				Long baseOrderDtlId = vo.getBaseOrderDtlId();
				for(DistrReqPlanDetail req:reqDtlList) {
					if(req.getId().equals(baseOrderDtlId)) {
						vo.setUsableQuantity(req.getQuantity());
						break;
					}
				}
				vo.getLotNums().add(lotNum);
				resList.add(vo);
			}
		}
		res.setList(resList);
		return res;
	}

	@Override
	public BigDecimal getAvgUnitPrice(UserVO userVO,List<DistrInLotNumVO> distrInLotNumVOs) {
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalNum = BigDecimal.ZERO;
		BigDecimal avgPrice = BigDecimal.ZERO;
		for (DistrInLotNumVO vo : distrInLotNumVOs) {
			BigDecimal unitPrice =commonComponent.getCostPriceWithLotId(userVO.getEnterpriseId(),vo.getGoodsId(),vo.getLotId()
					,vo.getOutQuantity());
			total = total.add(unitPrice.multiply(vo.getOutQuantity()));
			totalNum = totalNum.add(vo.getOutQuantity());
		}
		avgPrice = total.divide(totalNum, 6, BigDecimal.ROUND_HALF_UP);
		return avgPrice;
	}

	@Override
	public ResponseDistrInReturnVO getDistrReturnOrderById(Long id) {
		ResponseDistrInReturnVO res=distrInReturnMapper.getDistrReturnOrderById(id);
		res.setDistrTypeName(DistributionType.getName(res.getDistrType()));
		return res;
	}
	
}
