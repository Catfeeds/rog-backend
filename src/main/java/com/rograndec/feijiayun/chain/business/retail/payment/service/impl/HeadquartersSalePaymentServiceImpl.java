package com.rograndec.feijiayun.chain.business.retail.payment.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.payment.dao.SalePaymentMapper;
import com.rograndec.feijiayun.chain.business.retail.payment.service.HeadquartersSalePaymentService;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersAlreadyPageVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersAlreadySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStayViewSearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class HeadquartersSalePaymentServiceImpl implements HeadquartersSalePaymentService{

	@Autowired
	private PosPayTypeMapper posPayTypeMapper;
	
	@Autowired
	private PosPayeeMapper posPayeeMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private ReceiptDetailMapper receiptDetailMapper;
	
	@Autowired
	private PayeeOpeningShiftMapper payeeOpeningShiftMapper;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private SalePaymentMapper salePaymentMapper;
	
	@Autowired
	private ReceiptMapper receiptMapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getStayPaymentViewPage(
			StoreStayViewSearchConditionVO condition, Page page,
			UserVO loginUser) {
		
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
		
		if (loginUser.getChainType().equals(ChainType.Division.getCode())){
			//加盟门店自读取自己数据
			loginUser.setParentId(-1L);
		}
		
		List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseId(loginUser.getEnterpriseId(), loginUser.getParentId());
		
		if(list != null && list.size() > 0){
			
			Enterprise en = getEnterpriseByShiftId(id);
			
			for (Map map : list) {
				map.put("storeCode", en.getCode());
				map.put("storeName", en.getName());
				String saleType = map.get("sale_type")==null?"":map.get("sale_type").toString();
				if("1".equals(saleType)){
					map.put("realAmountTotal", new BigDecimal(map.get("realAmountTotal").toString()).negate());
				}
				setDynamicColumnValueBySaleId(map.get("id").toString(), map, payTypeList, saleType);
			}
			
		}
		
		page.setResult(list);
		
		page.setTotalRecord(totalRecord.intValue());
		
	}
	
	
	private Enterprise getEnterpriseByShiftId(Long id) {
		
		PayeeOpeningShift shift = payeeOpeningShiftMapper.selectByPrimaryKey(id);
		
		Enterprise en = enterpriseMapper.selectByPrimaryKey(shift.getEnterpriseId());
		
		return en == null ? new Enterprise() : en;
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
						map2.get("amount")==null?"0":new BigDecimal(map.get("amount").toString()).negate());
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


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getAlreadyPaymentPage(
			HeadquartersAlreadySearchConditionVO condition, Page page,
			UserVO loginUser) {
		Integer pageSize = page.getPageSize();
		Date startDate = condition.getStartDate();
		Date endDate = condition.getEndDate();
		Integer chainType = condition.getChainType();
		String storeCode = condition.getStoreCode();
		String storeName = condition.getStoreName();
		String salePaymentCode = condition.getSalePaymentCode();
		String createrName = condition.getCreaterName();
		String paymentManName = condition.getPaymentManName();
		String order = condition.getOrder();
		String sort = condition.getSort();
		
		if("paymentTime".equals(order) || "paymentDate".equals(order)){
			order = "p.payment_date";
		}else if("storeCode".equals(order)){
			order = "e.code";
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
		
		Long totalRecord = salePaymentMapper.queryHeadquartersAlreadyPaymentByParams(startDate, endDate, chainType, storeCode, 
				storeName, salePaymentCode, createrName, paymentManName, loginUser.getEnterpriseId());
		
		List<HeadquartersAlreadyPageVO> list = salePaymentMapper.selectHeadquartersAlreadyPaymentByParams(page.getStart(), pageSize, 
				startDate, endDate, chainType, storeCode, storeName, salePaymentCode, createrName, paymentManName, order, 
				sort, loginUser.getEnterpriseId());
		
		page.setTotalRecord(totalRecord.intValue());
		
		page.setResult(list);
		
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getStayPaymentPage(HeadquartersStaySearchConditionVO condition,
			Page page, UserVO loginUser) {
		if("shiftDate".equals(condition.getOrder())){
			condition.setOrder("s.shift_time");
		}else if("storeCode".equals(condition.getOrder())){
			condition.setOrder("se.code");
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
		
		Long totalRecord = payeeOpeningShiftMapper.queryHeadquartersStayPaymentCountByParams(condition);
		
		List<Map> mapList = payeeOpeningShiftMapper.selectHeadquartersStayPaymentPageByParams(condition);
		
		if (loginUser.getChainType().equals(ChainType.Division.getCode())){
			//加盟门店自读取自己数据
			loginUser.setParentId(-1L);
		}
		List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseId(loginUser.getEnterpriseId(), loginUser.getParentId());
		
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

}
