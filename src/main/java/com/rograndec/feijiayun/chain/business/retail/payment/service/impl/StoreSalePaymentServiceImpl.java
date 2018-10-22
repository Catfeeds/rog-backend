package com.rograndec.feijiayun.chain.business.retail.payment.service.impl;

import com.rograndec.feijiayun.chain.business.retail.payment.dao.SalePaymentDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.payment.dao.SalePaymentMapper;
import com.rograndec.feijiayun.chain.business.retail.payment.entity.SalePayment;
import com.rograndec.feijiayun.chain.business.retail.payment.entity.SalePaymentDetail;
import com.rograndec.feijiayun.chain.business.retail.payment.service.StoreSalePaymentService;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosStandMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosTeamMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayee;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayTypeService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class StoreSalePaymentServiceImpl implements StoreSalePaymentService{

	@Autowired
	private PosPayTypeMapper posPayTypeMapper;
	
	@Autowired
	private PayeeOpeningShiftMapper payeeOpeningShiftMapper;
	
	@Autowired
	private PosPayeeMapper posPayeeMapper;
	
	@Autowired
	private PosStandMapper posStandMapper;
	
	@Autowired
	private PosTeamMapper posTeamMapper;
	
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	
	@Autowired
	private SalePaymentMapper salePaymentMapper;
	
	@Autowired
	private SalePaymentDetailMapper salePaymentDetailMapper;
	
	@Autowired
	private ReceiptMapper receiptMapper;
	
	@Autowired
	private ReceiptDetailMapper receiptDetailMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private SaleDetailMapper saleDetailMapper;
	
	@Autowired
	private PosPayTypeService posPayTypeService;
	
	@Override
	public List<DynamicColumnVO> selectDynamicColumnBySource(Integer source, UserVO loginUser) throws Exception{
		
		List<DynamicColumnVO> list = getDynamicColumnBySource(source);
		
//		List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseId(enterpriseId,parentId);
		
		List<PosPayTypeVO> payTypeList = posPayTypeService.getPayTypeData(loginUser);
		
		if(payTypeList != null){
			DynamicColumnVO column = null;
			for (PosPayTypeVO posPayTypeVO : payTypeList) {
//				if(posPayTypeVO.getStatus() == 1){
					column = new DynamicColumnVO();
					column.setKey(posPayTypeVO.getCode());
					column.setTitle(posPayTypeVO.getName());
					
					list.add(column);
//				}
			}
		}
		
		return list;
	}

	private List<DynamicColumnVO> getDynamicColumnBySource(Integer source) {
		
		List<DynamicColumnVO> list = new ArrayList<DynamicColumnVO>();
		
		switch(source){
			case 0://门店待缴款分页列表
				list = getStorePage(list);
				break;
			case 1://门店待缴款查看列表
				list = getStoreView(list);
				break;
			case 2://总部待缴款分页列表
				list = getHeadquartersPage(list);
				break;
			case 3://总部待缴款查看列表
				list = getHeadquartersView(list);
				break;
		
		}
		
		return list;
	}

	
	/**
	 * @Description: TODO总部待缴款查看列表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月20日 下午2:07:51 
	 * @param list
	 * @return 
	 * @return List<DynamicColumn>
	 */
	private List<DynamicColumnVO> getHeadquartersView(List<DynamicColumnVO> list) {
		DynamicColumnVO column = new DynamicColumnVO();
		column.setKey("storeCode");
		column.setTitle("门店编码");
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("storeName");
		column.setTitle("门店名称");
		list.add(column);
		
		list = getStoreView(list);
		
		return list;
	}

	/**
	 * @Description: TODO总部待缴款分页列表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月20日 下午2:03:05 
	 * @return 
	 * @return List<DynamicColumn>
	 */
	private List<DynamicColumnVO> getHeadquartersPage(List<DynamicColumnVO> list) {
		DynamicColumnVO column = new DynamicColumnVO();
		column.setKey("storeCode");
		column.setTitle("门店编码");
		column.setSortable(true);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("storeName");
		column.setTitle("门店名称");
		list.add(column);
		
		list = getStorePage(list);
		
		return list;
	}

	/**
	 * @Description: TODO门店待缴款查看列表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月20日 下午1:59:13 
	 * @return 
	 * @return List<DynamicColumn>
	 */
	private List<DynamicColumnVO> getStoreView(List<DynamicColumnVO> list) {
		DynamicColumnVO column = new DynamicColumnVO();
		column.setKey("standCode");
		column.setTitle("款台");
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("teamName");
		column.setTitle("班组");
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("payeeName");
		column.setTitle("收款人员");
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("saleTime");
		column.setTitle("销售时间");
		column.setSortable(true);
		column.setWidth(240);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("saleCode");
		column.setTitle("销售单号");
		column.setWidth(240);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("saleType");
		column.setTitle("销售类型");
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("saleMode");
		column.setTitle("销售模式");
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("realAmountTotal");
		column.setTitle("应收金额");
		list.add(column);
		
		return list;
	}

	/**
	 * @Description: TODO门店待缴款分页列表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月20日 下午1:58:40 
	 * @return 
	 * @return List<DynamicColumn>
	 */
	private List<DynamicColumnVO> getStorePage(List<DynamicColumnVO> list) {
		DynamicColumnVO column = new DynamicColumnVO();
		column.setKey("shiftDate");
		column.setTitle("日期");
		column.setSortable(true);
		column.setWidth(240);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("standCode");
		column.setTitle("款台");
		column.setSortable(true);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("teamName");
		column.setTitle("班组");
		column.setSortable(true);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("payeeName");
		column.setTitle("收款人员");
		column.setSortable(true);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("openingTime");
		column.setTitle("开班时间");
		column.setWidth(240);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("shiftTime");
		column.setTitle("交班时间");
		column.setWidth(240);
		list.add(column);
		
		column = new DynamicColumnVO();
		column.setKey("realAmountTotal");
		column.setTitle("应收金额");
		list.add(column);
		
		return list;
	}

	@Override
	public PaymentVO queryPaymentVOByShiftId(Long shiftId, UserVO loginUser) {
		
		PayeeOpeningShift shift = payeeOpeningShiftMapper.selectByPrimaryKey(shiftId);
		
		if(shift == null){
			return null;
		}
		PaymentVO vo = new PaymentVO();
		
		vo.setId(shiftId);
		vo.setCreateDate(DateUtils.DateToString(new Date(), DateUtils.FMT_DATE));
		vo.setPayeeName(loginUser.getUserName());
		vo.setPaymentManName(shift.getPayeeName());
		
		String teamName = getTeamNameByPayeeId(shift.getPayeeId());
		vo.setTeamName(teamName);
		
		String standCode = getStandCodeByShiftId(shiftId);
		vo.setStandCode(standCode);
		
		vo.setShiftDate(DateUtils.DateToString(shift.getShiftTime(), DateUtils.FMT_DATE));
		vo.setOpeningTime(shift.getOpeningTime());
		vo.setShiftTime(shift.getShiftTime());
		
		List<PaymentListVO> paymentList = getPaymentListByShiftId(shiftId, shift.getEnterpriseId());
		vo.setPaymentList(paymentList);
		
		return vo;
	}

	private List<PaymentListVO> getPaymentListByShiftId(Long shiftId, Long enterpriseId) {
		return payeeOpeningShiftMapper.selectReceiptDetailByShiftId(shiftId, enterpriseId);
	}

	/**
	 * @Description: TODO根据收款人ID获取班组名称
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月22日 下午3:59:40 
	 * @param payeeId
	 * @return 
	 * @return String
	 */
	private String getTeamNameByPayeeId(Long payeeId) {
		List<PosPayee> payee = posPayeeMapper.selectPosPayeeByPayeeId(payeeId);
		if(payee != null && payee.size() > 0){
			return payee.get(0).getTeamName();
		}
		return "";
	}

	/**
	 * @Description: TODO根据交接班ID获取款台
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月22日 下午3:14:39 
	 * @param shiftId
	 * @return 
	 * @return String
	 */
	private String getStandCodeByShiftId(Long shiftId) {
		List<Map<String,String>> map = payeeOpeningShiftMapper.selectStandCodeByShiftId(shiftId);
		StringBuilder sb = new StringBuilder();
		if(map != null && map.size() > 0){
			for (Map<String, String> map2 : map) {
				sb.append(map2.get("stand_code"));
				sb.append(",");
			}
			return sb.substring(0, sb.length()-1);
		}
		return sb.toString();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveSalePayment(PaymentVO paymentVO, UserVO loginUser) throws Exception {
		PayeeOpeningShift shift = payeeOpeningShiftMapper.selectByPrimaryKey(paymentVO.getId());
		if(shift == null){
			return "交接班ID错误！";
		}
		if(shift.getPaymentFlag() == 1){
			return "该交接班已缴款！";
		}
		SalePayment payment = getSalePayment(paymentVO, shift, loginUser);
		salePaymentMapper.insert(payment);
		
		saveSalePaymentDetail(paymentVO, payment, loginUser);
		
		updateUpstreamOrderStatus(paymentVO);
		
		return "";
	}

	/**
	 * @Description: TODO修改交接班、销售单状态
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月23日 下午3:00:46 
	 * @param paymentVO 
	 * @return void
	 */
	private void updateUpstreamOrderStatus(PaymentVO paymentVO) {
		
		payeeOpeningShiftMapper.updatePaymentFlagByShiftId(paymentVO.getId());
		
		saleMapper.updatePaymentFlagByShiftId(paymentVO.getId());
		
	}

	private void saveSalePaymentDetail(PaymentVO paymentVO,
			SalePayment payment, UserVO loginUser) throws Exception {
		List<PaymentListVO> paymentList = paymentVO.getPaymentList();
		if(paymentList == null || paymentList.size() == 0){
			throw new Exception("缴费详情列表对象不能为空！");
		}
		
		BigDecimal payAmountTotal = BigDecimal.ZERO;//应缴金额
		BigDecimal realAmountTotal = BigDecimal.ZERO;//实缴金额
		BigDecimal diffAmountTotal = BigDecimal.ZERO;//缴款差额
		
		SalePaymentDetail detail = null;
		for (PaymentListVO paymentListVO : paymentList) {
			detail = new SalePaymentDetail();
			detail.setEnterpriseId(payment.getEnterpriseId());
			detail.setParentId(payment.getParentId());
			detail.setPaymentId(payment.getId());
			detail.setPayTypeId(paymentListVO.getPayTypeId());
			detail.setPayTypeName(paymentListVO.getPayTypeName());
			detail.setPayAmount(paymentListVO.getPayAmount());
			detail.setRealAmount(paymentListVO.getRealAmount());
			detail.setDiffAmount(paymentListVO.getPayAmount().subtract(paymentListVO.getRealAmount()));
			detail.setStatus(1);
			detail.setCreaterId(loginUser.getUserId());
			detail.setCreaterCode(loginUser.getUserCode());
			detail.setCreaterName(loginUser.getUserName());
			detail.setCreateTime(new Date());
			detail.setModifierId(loginUser.getUserId());
			detail.setModifierCode(loginUser.getUserCode());
			detail.setModifierName(loginUser.getUserName());
			detail.setUpdateTime(new Date());
			
			payAmountTotal = payAmountTotal.add(detail.getPayAmount());
			realAmountTotal = realAmountTotal.add(detail.getRealAmount());
			diffAmountTotal = diffAmountTotal.add(detail.getDiffAmount());
			
			salePaymentDetailMapper.insert(detail);
		}
		payment.setPayAmountTotal(payAmountTotal);
		payment.setRealAmountTotal(realAmountTotal);
		payment.setDiffAmountTotal(diffAmountTotal);
		salePaymentMapper.updateByPrimaryKeySelective(payment);
	}

	private SalePayment getSalePayment(PaymentVO paymentVO, PayeeOpeningShift shift, UserVO loginUser) throws Exception {
		SalePayment payment = new SalePayment();
		payment.setEnterpriseId(shift.getEnterpriseId());
		payment.setParentId(shift.getParentId());
		payment.setOrderType(OrderRule.RETAIL_PAYMENT.getType());
		//缴款时间取数据库时间
//		payment.setPaymentDate(DateUtils.StringToDate(paymentVO.getCreateDate(), DateUtils.FMT_DATE)
//				==null?new Date():DateUtils.StringToDate(paymentVO.getCreateDate(), DateUtils.FMT_DATE));
		payment.setPaymentDate(new Date());
		payment.setCode(orderCodeComponent.generate(OrderRule.RETAIL_PAYMENT.getCodePrefix(),
				loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
		payment.setBaseOrderId(shift.getId());
//		payment.setBaseOrderType(shift.get);
//		payment.setBaseOrderCode(baseOrderCode);
		payment.setBaseOrderDate(shift.getShiftTime());//交班单日期
		payment.setPaymentManId(shift.getPayeeId());
		payment.setPaymentManCode(shift.getPayeeCode());
		payment.setPaymentManName(shift.getPayeeName());
		payment.setPayAmountTotal(BigDecimal.ZERO);
		payment.setRealAmountTotal(BigDecimal.ZERO);
		payment.setDiffAmountTotal(BigDecimal.ZERO);
		payment.setStatus(1);
		payment.setCreaterId(loginUser.getUserId());
		payment.setCreaterCode(loginUser.getUserCode());
		payment.setCreaterName(loginUser.getUserName());
		payment.setCreateTime(new Date());
		payment.setModifierId(loginUser.getUserId());
		payment.setModifierCode(loginUser.getUserCode());
		payment.setModifierName(loginUser.getUserName());
		payment.setUpdateTime(new Date());
		return payment;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<StoreAlreadyPageVO> selectAlreadyPayment(StoreAlreadySearchConditionVO condition, 
			Long enterpriseId, Page page) {
		Integer pageSize = page.getPageSize();
		Date startDate = condition.getStartDate();
		Date endDate = condition.getEndDate();
		String salePaymentCode = condition.getSalePaymentCode();
		String createrName = condition.getCreaterName();
		String paymentManName = condition.getPaymentManName();
		String order = condition.getOrder();
		String sort = condition.getSort();
		
		if("paymentDate".equals(order) || "paymentTime".equals(order)){
			order = "p.payment_date";
		}else if("salePaymentCode".equals(order)){
			order = "p.code";
		}else if("createrName".equals(order)){
			order = "p.creater_name";
		}else if("paymentManName".equals(order)){
			order = "p.payment_man_name";
		}else{
			order = "p.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		Long totalRecord = salePaymentMapper.queryAlreadyPaymentByParams(startDate, endDate, salePaymentCode, 
				createrName, paymentManName, enterpriseId);
		
		List<StoreAlreadyPageVO> list = salePaymentMapper.selectAlreadyPaymentByParams(page.getStart(), pageSize, startDate, endDate,
				salePaymentCode, createrName, paymentManName, order, sort, enterpriseId);
		
		page.setTotalRecord(totalRecord.intValue());
		
		//合计
//		StoreAlreadyPageVO vo = salePaymentMapper.querySumAlreadyPaymentByParams(startDate, endDate, salePaymentCode, 
//				createrName, paymentManName, enterpriseId);
		
		return list;
	}

	@Override
	public PaymentVO queryAlreadyPaymentInfoById(Long id) {
		
		PaymentVO vo = salePaymentDetailMapper.selectPaymentVOByPaymentId(id);
		if(vo == null){
			return null;
		}
		
		PayeeOpeningShift shift = payeeOpeningShiftMapper.selectByPrimaryKey(vo.getId());
		if(shift == null){
			return null;
		}
		
		String teamName = getTeamNameByPayeeId(shift.getPayeeId());
		vo.setTeamName(teamName);
		
		String standCode = getStandCodeByShiftId(shift.getId());
		vo.setStandCode(standCode);
		
		vo.setShiftDate(DateUtils.DateToString(shift.getShiftTime(), DateUtils.FMT_DATE));
		vo.setOpeningTime(shift.getOpeningTime());
		vo.setShiftTime(shift.getShiftTime());
		
		return vo;
	}

	@Override
	public List<SelectBean> selectPosStandSelectBeanByUserVO(UserVO loginUser) {
		
		List<SelectBean> list = null;
		
		if(loginUser.getChainType() != 0 && loginUser.getParentId() != 0){//分店
			list = posStandMapper.selectStoreStandByEnterpriseId(loginUser.getEnterpriseId());
		}else{//总部
			list = posStandMapper.selectHeadquartersStandByParentId(loginUser.getEnterpriseId());
		}
		
		return list;
	}


	@Override
	public List<SelectBean> selectPosTeamSelectBeanByUserVO(UserVO loginUser) {
		List<SelectBean> list = null;
		
		if(loginUser.getChainType() != 0 && loginUser.getParentId() != 0){//分店
			list = posTeamMapper.selectStoreTeamByEnterpriseId(loginUser.getEnterpriseId());
		}else{//总部
			list = posTeamMapper.selectHeadquartersTeamByParentId(loginUser.getEnterpriseId());
		}
		
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getStayPaymentPageByParams(
			StoreStaySearchConditionVO condition, Page page, UserVO loginUser) throws Exception {
		if("shiftDate".equals(condition.getOrder()) || "shiftDate".equals(condition.getOrder())){
			condition.setOrder("s.shift_time");
		}
//		else if("standCode".equals(condition.getOrder())){
//			condition.setOrder("stand_code");
//		}
		else if("teamName".equals(condition.getOrder())){
			condition.setOrder("pe.team_name");
		}else if("payeeName".equals(condition.getOrder())){
			condition.setOrder("s.payee_name");
		}else{
			condition.setOrder("s.id");
		}
		
		if(!"asc".equals(condition.getSort()) && !"desc".equals(condition.getSort())){
			condition.setSort("desc");
		}
		
		condition.setPageNo(page.getStart());
		
		condition.setEnterpriseId(loginUser.getEnterpriseId());
		
		Long totalRecord = payeeOpeningShiftMapper.queryStayPaymentCountByParams(condition);
		
		List<Map> mapList = payeeOpeningShiftMapper.selectStayPaymentPageByParams(condition);
		
//		List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseId((loginUser.getParentId()==null||loginUser.getParentId()==0) 
//				? loginUser.getEnterpriseId():loginUser.getParentId(),loginUser.getParentId());
		
		List<PosPayTypeVO> payTypeList = posPayTypeService.getPayTypeData(loginUser);
		
		if(mapList != null){
			for (Map map : mapList) {
				String standCode = getStandCodeByShiftId(Long.parseLong(map.get("id").toString()));
				map.put("standCode", standCode);
				
				BigDecimal realAmountTotal = receiptMapper.getSumRealAmountTotalByShiftId(Long.parseLong(map.get("id").toString()));
				map.put("realAmountTotal", realAmountTotal);
				
				setDynamicColumnValueByShiftId(map.get("id").toString(), map, payTypeList);
			}
		}

		page.setResult(mapList);
		
		page.setTotalRecord(totalRecord.intValue());
	}

	
	/**
	 * @Description: TODO根据总部支付方式动态加入列
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月21日 下午4:51:27 
	 * @param id 交接班ID
	 * @param map 当前行数据
	 * @param payTypeList 当前支付方式列表
	 * @return void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setDynamicColumnValueByShiftId(String id, Map map, List<PosPayTypeVO> payTypeList) {
		
		Map<String, String> amountMap = new HashMap<String, String>();
		List<Map> list = receiptDetailMapper.selectPayTypeAmountByShiftId(id);
		if(list != null){
			for (Map map2 : list) {
				amountMap.put(map2.get("pay_type_id")==null?"":map2.get("pay_type_id").toString(),
						map2.get("amount")==null?"":map2.get("amount").toString());
			}
		}
		
		if(payTypeList != null){
			for (PosPayTypeVO posPayTypeVO : payTypeList) {
				map.put(posPayTypeVO.getCode(), amountMap.get(posPayTypeVO.getId().toString())==null?0:amountMap.get(posPayTypeVO.getId().toString()));
			}
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getStayPaymentViewPage(
			StoreStayViewSearchConditionVO condition, Page page,
			UserVO loginUser) throws Exception{
		
		Integer pageSize = page.getPageSize();
		String order = condition.getOrder();
		String sort = condition.getSort();
		Long id = condition.getId();
		
		if("saleTime".equals(order)){
			order = "s.sale_date";
		} else{
			order = "s.id";
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		Long totalRecord = saleMapper.querySaleCountByShiftId(id);
		
		List<Map> list = saleMapper.selectSaleDataByShiftId(page.getStart(), pageSize, id, order, sort);
		
//		List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseId((loginUser.getParentId()==null||loginUser.getParentId()==0) 
//				? loginUser.getEnterpriseId():loginUser.getParentId(),loginUser.getParentId());
		
		List<PosPayTypeVO> payTypeList = posPayTypeService.getPayTypeData(loginUser);
		
		if(list != null){
			for (Map map : list) {
				String saleType = map.get("saleType")==null?"":map.get("saleType").toString();
				if("1".equals(saleType)){
					map.put("realAmountTotal", new BigDecimal(map.get("realAmountTotal").toString()).negate());
					map.put("saleType","销退");
				}else {
					map.put("saleType","销售");
				}
				String saleMode = map.get("saleMode")==null?"":map.get("saleMode").toString();
				if("1".equals(saleMode)){
					map.put("saleMode","中药");
				}else {
					map.put("saleMode","常规");
				}
				setDynamicColumnValueBySaleId(map.get("id").toString(), map, payTypeList, saleType);
			}
			
		}
		
		page.setResult(list);
		
		page.setTotalRecord(totalRecord.intValue());
		
	}

	/**
	 * @Description: TODO根据总部支付方式动态加入列
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月21日 下午4:51:27 
	 * @param id saleID
	 * @param map 当前行数据
	 * @param payTypeList 当前支付方式列表
	 * @return void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setDynamicColumnValueBySaleId(String id, Map map,
			List<PosPayTypeVO> payTypeList, String saleType) {
		
		Map<String, Object> amountMap = new HashMap<String, Object>();
		List<Map> list = receiptDetailMapper.selectPayTypeAmountBySaleId(id);
		if(list != null){
			for (Map map2 : list) {
				if("1".equals(saleType)){
					amountMap.put(map2.get("pay_type_id")==null?"":map2.get("pay_type_id").toString(),
						map2.get("amount")==null?"0":new BigDecimal(map2.get("amount").toString()).negate());
				}else{
					amountMap.put(map2.get("pay_type_id")==null?"":map2.get("pay_type_id").toString(),
							map2.get("amount")==null?"0":map2.get("amount").toString());
				}	
			}
		}
		
		if(payTypeList != null){
			for (PosPayTypeVO posPayTypeVO : payTypeList) {
				map.put(posPayTypeVO.getCode(), amountMap.get(posPayTypeVO.getId().toString())==null?0:amountMap.get(posPayTypeVO.getId().toString()));
			}
		}
		
	}
	
	

}
