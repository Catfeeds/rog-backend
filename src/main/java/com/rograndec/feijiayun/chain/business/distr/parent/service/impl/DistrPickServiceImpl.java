package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.PickDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.PickOrderMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.PickShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSendDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickOrder;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrPickService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationSlfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendViewDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendViewVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.PickOrderDtlExcelVO;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.CostLockRecord;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.entity.StockLockRecord;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class DistrPickServiceImpl implements DistrPickService{

	@Autowired
    private DistrSendMapper distrSendMapper;
	
	@Autowired
	private DistrSendDetailMapper distrSendDetailMapper;
	
	@Autowired
	private PickOrderMapper pickOrderMapper;
	
	@Autowired
	private PickDetailMapper pickDetailMapper;
	
	@Autowired
	private PickShelfMapper pickShelfMapper;
	
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private CostMapper costMapper;
	
	@Autowired
	private StockLockRecordMapper stockLockRecordMapper;
	
	@Autowired
	private CostLockRecordMapper costLockRecordMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LotNumberMapper lotNumberMapper;
	
	@Autowired
	private WarehouseShelfMapper warehouseShelfMapper;
	
	@Autowired
	private CommonComponent commonComponent;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@Autowired
	private ManageConfigMapper manageConfigMapper;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getStayDistrPickPage(DistrSendRequestVO condition, Page page,
			UserVO loginUser) {
		if("distrDate".equals(condition.getOrder()) || "distrDate".equals(condition.getOrder())){
			condition.setOrder("distr_date");
		}else if("code".equals(condition.getOrder())){
			condition.setOrder("code");
		}else{
			condition.setOrder("id");
		}
		
		if(!"asc".equals(condition.getSort()) && !"desc".equals(condition.getSort())){
			condition.setSort("desc");
		}
		
		condition.setStart(page.getStart());
		
		condition.setStatus(DistrSendStatus.WAIT_PICK);
		
		condition.setEnterpriseId(loginUser.getEnterpriseId());
		
		Long totalRecord = distrSendMapper.getStayDistrPickTotalRecord(condition);
		
		List<DistrSendVO> sendList = distrSendMapper.selectStayDistrPickPage(condition);
		
		if(sendList != null){
			for (DistrSendVO distrSendVO : sendList) {
				distrSendVO.setDistrTypeName(DistributionType.getName(distrSendVO.getDistrType()));
				distrSendVO.setStatusName(DistrSendStatus.getStatusDesc(distrSendVO.getStatus()));
			}
		}
		
		page.setResult(sendList);
		
		page.setTotalRecord(totalRecord.intValue());
		
	}

	@Override
	public DistrSendViewVO getStayDistrPickView(Long id, UserVO loginUser) {

		DistrSend send = distrSendMapper.selectByPrimaryKey(id);
		if(send == null){
			throw new InventoryBizException("","配货单ID错误，查不到配货单信息！");
		}
		if(send.getEnterpriseId() == null || !send.getEnterpriseId().equals(loginUser.getEnterpriseId())){
			throw new InventoryBizException("","当前用户无操作此配货单权限！");
		}
		DistrSendViewVO vo = new DistrSendViewVO();
		vo.setId(send.getId());
		vo.setDistrDate(send.getDistrDate());
		vo.setCode(send.getCode());
		vo.setRequestUnitCode(send.getRequestUnitCode());
		vo.setRequestUnitName(send.getRequestUnitName());
		vo.setDistrType(send.getDistrType());
		vo.setDistrTypeName(DistributionType.getName(send.getDistrType()));
		vo.setDistrManName(send.getDistrManName());
		
		List<DistrSendDetail> dtlList = distrSendDetailMapper.listDistrSendDetailList(send.getId());
		if(dtlList != null){
			List<DistrSendViewDtlVO> dtlViewList = new ArrayList<DistrSendViewDtlVO>();
			DistrSendViewDtlVO dtlVO = null;
			BigDecimal quantityTotal = BigDecimal.ZERO;
			for (DistrSendDetail dtl : dtlList) {
				dtlVO = new DistrSendViewDtlVO();
				dtlVO.setGoodsCode(dtl.getGoodsCode());
				dtlVO.setGoodsName(dtl.getGoodsName());
				dtlVO.setGoodsGenericName(dtl.getGoodsGenericName());
				dtlVO.setDosageId(dtl.getDosageId());
				dtlVO.setDosageName(dtl.getDosageName());
				dtlVO.setUnitId(dtl.getUnitId());
				dtlVO.setUnitName(dtl.getUnitName());
				dtlVO.setGoodsSpecification(dtl.getGoodsSpecification());
				dtlVO.setManufacturerId(dtl.getManufacturerId());
				dtlVO.setManufacturer(dtl.getManufacturer());
				dtlVO.setGoodsPlace(dtl.getGoodsPlace());
				dtlVO.setQuantity(dtl.getQuantity());
				dtlVO.setRemark(dtl.getRemark());
				
				quantityTotal = quantityTotal.add(dtl.getQuantity());
				dtlViewList.add(dtlVO);
			}
			vo.setDtlList(dtlViewList);
			vo.setQuantityTotal(quantityTotal);
		}
		
		return vo;
	}

	@Override
	public DistrSendOperationVO getStayDistrPickOperation(Long id,
			UserVO loginUser) {
		DistrSend send = distrSendMapper.selectByPrimaryKey(id);
		if(send == null){
			throw new InventoryBizException("","配货单ID错误，查不到配货单信息！");
		}
		if(send.getEnterpriseId() == null || !send.getEnterpriseId().equals(loginUser.getEnterpriseId())){
			throw new InventoryBizException("","当前用户无操作此配货单权限！");
		}
		DistrSendOperationVO vo = new DistrSendOperationVO();
		vo.setId(send.getId());
		vo.setPickDate(new Date());
		vo.setPickManId(loginUser.getUserId());
		vo.setPickManName(loginUser.getUserName());
		vo.setRequestUnitCode(send.getRequestUnitCode());
		vo.setRequestUnitName(send.getRequestUnitName());
		vo.setDistrCode(send.getCode());
		
		List<DistrSendDetail> dtlList = distrSendDetailMapper.listDistrSendDetailList(send.getId());
		if(dtlList != null){
			List<DistrSendOperationDtlVO> dtlViewList = new ArrayList<DistrSendOperationDtlVO>();
			DistrSendOperationDtlVO dtlVO = null;
			BigDecimal quantityTotal = BigDecimal.ZERO;
			for (DistrSendDetail dtl : dtlList) {
				dtlVO = new DistrSendOperationDtlVO();
				dtlVO.setSendDetailId(dtl.getId());
				dtlVO.setGoodsId(dtl.getGoodsId());
				dtlVO.setGoodsCode(dtl.getGoodsCode());
				dtlVO.setGoodsName(dtl.getGoodsName());
				dtlVO.setGoodsGenericName(dtl.getGoodsGenericName());
				dtlVO.setDosageId(dtl.getDosageId());
				dtlVO.setDosageName(dtl.getDosageName());
				dtlVO.setUnitId(dtl.getUnitId());
				dtlVO.setUnitName(dtl.getUnitName());
				dtlVO.setGoodsSpecification(dtl.getGoodsSpecification());
				dtlVO.setManufacturerId(dtl.getManufacturerId());
				dtlVO.setManufacturer(dtl.getManufacturer());
				dtlVO.setGoodsPlace(dtl.getGoodsPlace());
				dtlVO.setQuantity(dtl.getQuantity());
				dtlVO.setRemark(dtl.getRemark());
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("enterpriseId", send.getEnterpriseId());
				paramMap.put("baseOrderId", send.getId());
				paramMap.put("baseOrderType", send.getOrderType());
				paramMap.put("goodsId", dtl.getGoodsId());
				List<StockLockRecordVO> lockRecordList = stockLockRecordMapper.selectLockVOByParamMap(paramMap);
				if(lockRecordList != null){
					List<DistrSendOperationSlfVO> shelfList = new ArrayList<DistrSendOperationSlfVO>();
					DistrSendOperationSlfVO slfVO = null;
					for (StockLockRecordVO lockRecord : lockRecordList) {
						slfVO = new DistrSendOperationSlfVO();
						
						setSlfVOData(slfVO, lockRecord);
						
						shelfList.add(slfVO);
					}
					dtlVO.setShelfList(shelfList);
				}
				
				quantityTotal = quantityTotal.add(dtl.getQuantity());
				dtlViewList.add(dtlVO);
			}
			vo.setDtlList(dtlViewList);
			vo.setQuantityTotal(quantityTotal);
		}
		
		return vo;
	}

	private void setSlfVOData(DistrSendOperationSlfVO slfVO,
			StockLockRecordVO lockRecord) {
		slfVO.setSendSlfId(lockRecord.getStockLockRecordId());
		slfVO.setLotId(lockRecord.getLotId());
		slfVO.setLotNumber(lockRecord.getLotNum());
		slfVO.setProductDate(lockRecord.getProductDate());
		slfVO.setValidDate(lockRecord.getValidDate());
		slfVO.setShelfId(lockRecord.getShelfId());
		slfVO.setShelfName(lockRecord.getShelfName());
		slfVO.setShelfStatusDesc(lockRecord.getShelfStatusDesc());
		slfVO.setQuantity(lockRecord.getUsableQuantity());
		
	}

	private void setSlfVOData(DistrSendOperationSlfVO slfVO, PickShelf slf) {
		slfVO.setSendSlfId(slf.getId());
		slfVO.setLotId(slf.getLotId());
		slfVO.setLotNumber(slf.getLotNumber());
		slfVO.setProductDate(slf.getProductDate());
		slfVO.setValidDate(slf.getValidDate());
		slfVO.setShelfId(slf.getShelfId());
		slfVO.setShelfName(slf.getShelfName());
		slfVO.setShelfStatusDesc(slf.getShelfStatusDesc());
		slfVO.setQuantity(slf.getQuantity());
	}

	@Override
	public List<DistrSendOperationSlfVO> getStayDistrPickShelf(Long sendDetailId,
			UserVO loginUser) {
		DistrSendDetail detail = distrSendDetailMapper.selectByPrimaryKey(sendDetailId);
		if(detail == null || !detail.getEnterpriseId().equals(loginUser.getEnterpriseId())){
			throw new InventoryBizException("","配货单明细ID错误！");
		}
		
		List<DistrSendOperationSlfVO> list = new ArrayList<DistrSendOperationSlfVO>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("enterpriseId", detail.getEnterpriseId());
		paramMap.put("goodsId", detail.getGoodsId());
		List<GoodsInfoStockShelfVO> slfList = stockMapper.getGoodsInfoStockShelfVOList(paramMap);
		if(slfList != null){
			DistrSendOperationSlfVO vo = null;
			for (GoodsInfoStockShelfVO goodsInfoStockShelfVO : slfList) {
				vo = new DistrSendOperationSlfVO();
				setVOData(vo, goodsInfoStockShelfVO);
				list.add(vo);
			}
		}
		
		return list;
	}

	private void setVOData(DistrSendOperationSlfVO slfVO,
			GoodsInfoStockShelfVO vo) {
		slfVO.setSendSlfId(0L);
		slfVO.setLotId(vo.getLotNumberId());
		slfVO.setLotNumber(vo.getLotNum());
		slfVO.setProductDate(vo.getProductDate());
		slfVO.setValidDate(vo.getValidUtil());
		slfVO.setShelfId(vo.getShelfId());
		slfVO.setShelfName(vo.getShelfName());
		slfVO.setShelfStatusDesc(vo.getShelfStatusDesc());
		slfVO.setUsableQuantity(vo.getUsableQuantity());
		slfVO.setQuantity(null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getAlreadyDistrPickPage(DistrPickRequestVO condition, Page page,
			UserVO loginUser) {
		if("pickDate".equals(condition.getOrder()) || "distrDate".equals(condition.getOrder())){
			condition.setOrder("o.pick_date");
		}else if("code".equals(condition.getOrder())){
			condition.setOrder("o.code");
		}else{
			condition.setOrder("o.id");
		}
		
		if(!"asc".equals(condition.getSort()) && !"desc".equals(condition.getSort())){
			condition.setSort("desc");
		}
		
		condition.setStart(page.getStart());
		
		condition.setStatus(DistrSendStatus.WAIT_OUT);
		
//		condition.setOrderType(OrderRule.PICK.getType());
		
		condition.setEnterpriseId(loginUser.getEnterpriseId());
		
		Long totalRecord = pickOrderMapper.getAlreadyDistrPickTotalRecord(condition);
		
		List<DistrPickVO> sendList = pickOrderMapper.selectAlreadyDistrPickPage(condition);
		
		if(sendList != null){
			for (DistrPickVO vo : sendList) {
				vo.setStatusName(DistrSendStatus.getStatusDesc(vo.getStatus()));
			}
		}
		
		page.setResult(sendList);
		
		page.setTotalRecord(totalRecord.intValue());
		
	}

	@Override
	public DistrSendOperationVO getAlreadyDistrPickView(Long id,
			UserVO loginUser) {
		DistrSendOperationVO vo = new DistrSendOperationVO();
		
		PickOrder order = pickOrderMapper.selectByPrimaryKey(id);
		if(order == null || !order.getEnterpriseId().equals(loginUser.getEnterpriseId())){
			throw new InventoryBizException("","拣货单ID错误！");
		}
		DistrSend send = distrSendMapper.selectByPrimaryKey(order.getBaseOrderId());
		if(send == null){
			throw new InventoryBizException("","配货单ID错误，查不到配货单信息！");
		}
		vo.setCode(order.getCode());
		vo.setId(order.getId());
		vo.setPickDate(order.getPickDate());
		vo.setPickManId(order.getPickManId());
		vo.setPickManName(order.getPickManName());
		vo.setRequestUnitCode(order.getRequestUnitCode());
		vo.setRequestUnitName(order.getRequestUnitName());
		vo.setDistrCode(order.getBaseOrderCode());
		vo.setRemark(order.getRemark());
		
		List<PickDetail> pickDtlList = pickDetailMapper.selectByPickId(order.getId());
		
		if(pickDtlList != null){
			List<DistrSendOperationDtlVO> dtlViewList = new ArrayList<DistrSendOperationDtlVO>();
			DistrSendOperationDtlVO dtlVO = null;
			BigDecimal quantityTotal = BigDecimal.ZERO;
			for (PickDetail dtl : pickDtlList) {
				dtlVO = new DistrSendOperationDtlVO();
				dtlVO.setSendDetailId(dtl.getId());
				dtlVO.setGoodsId(dtl.getGoodsId());
				dtlVO.setGoodsCode(dtl.getGoodsCode());
				dtlVO.setGoodsName(dtl.getGoodsName());
				dtlVO.setGoodsGenericName(dtl.getGoodsGenericName());
				dtlVO.setDosageId(dtl.getDosageId());
				dtlVO.setDosageName(dtl.getDosageName());
				dtlVO.setUnitId(dtl.getUnitId());
				dtlVO.setUnitName(dtl.getUnitName());
				dtlVO.setGoodsSpecification(dtl.getGoodsSpecification());
				dtlVO.setManufacturerId(dtl.getManufacturerId());
				dtlVO.setManufacturer(dtl.getManufacturer());
				dtlVO.setGoodsPlace(dtl.getGoodsPlace());
				dtlVO.setQuantity(dtl.getQuantity());
				dtlVO.setRemark(dtl.getRemark());
				
				List<PickShelf> slfList = pickShelfMapper.selectByDtlId(dtl.getId(), order.getId());
				
				if(slfList != null){
					List<DistrSendOperationSlfVO> shelfList = new ArrayList<DistrSendOperationSlfVO>();
					DistrSendOperationSlfVO slfVO = null;
					for (PickShelf slf : slfList) {
						slfVO = new DistrSendOperationSlfVO();
						
						setSlfVOData(slfVO, slf);
						
						shelfList.add(slfVO);
					}
					dtlVO.setShelfList(shelfList);
				}
				
				quantityTotal = quantityTotal.add(dtl.getQuantity());
				dtlViewList.add(dtlVO);
			}
			vo.setDtlList(dtlViewList);
			vo.setQuantityTotal(quantityTotal);
		}
		
		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDistrPick(DistrSendOperationVO vo, UserVO loginUser) throws Exception {

		DistrSend send = validateOperationVO(vo, loginUser);
		PickOrder order = setPickOrder(send, vo, loginUser);
		pickOrderMapper.insertSelective(order);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enterpriseId", send.getEnterpriseId());
		paramMap.put("baseOrderId", send.getId());
		paramMap.put("baseOrderType", send.getOrderType());
		
		List<StockLockRecord> recordList = stockLockRecordMapper.selectByParamMap(paramMap);
		if(recordList != null){
			//根据原来的更改库存可用数量
			for (StockLockRecord stockLockRecord : recordList) {
				updateStockByParam(stockLockRecord.getEnterpriseId(), stockLockRecord.getParentId(),
						stockLockRecord.getGoodsId(), stockLockRecord.getLotId(), stockLockRecord.getShelfId(),
						stockLockRecord.getLockQuantity(), "add");
				stockLockRecordMapper.deleteByPrimaryKey(stockLockRecord.getId());
			}
		}
		
		List<CostLockRecord> costLockList = costLockRecordMapper.selectByParamMap(paramMap);
		if(costLockList != null){
			//根据原来的更改成本可用数量
			for (CostLockRecord lockRecord : costLockList) {
				updateCostByParam(lockRecord.getEnterpriseId(), lockRecord.getParentId(), lockRecord.getGoodsId(),
						lockRecord.getLotId(), lockRecord.getLockQuantity(), "add", send, loginUser, 
						lockRecord.getBatchId(), lockRecord.getBatchNum());
				costLockRecordMapper.deleteByPrimaryKey(lockRecord.getId());
			}
		}
		
		List<DistrSendOperationDtlVO> dtlList = vo.getDtlList();
		for (DistrSendOperationDtlVO dtlvo : dtlList) {
			DistrSendDetail sendDetail = distrSendDetailMapper.selectByPrimaryKey(dtlvo.getSendDetailId());
			PickDetail pDtl = setPickDetail(sendDetail, order, dtlvo);
			pickDetailMapper.insertSelective(pDtl);
			
			if(sendDetail.getQuantity().compareTo(dtlvo.getQuantity()) != 0){
				//与配货单细表数量不一致时更改配货单细表数量
				sendDetail.setQuantity(dtlvo.getQuantity());
				distrSendDetailMapper.updateByPrimaryKeySelective(sendDetail);
			}
			
			List<DistrSendOperationSlfVO> slfList = dtlvo.getShelfList();
			for (DistrSendOperationSlfVO distrSendOperationSlfVO : slfList) {
				PickShelf slf = setPickShelf(distrSendOperationSlfVO, pDtl);
				pickShelfMapper.insertSelective(slf);
				
				//增加库存锁定记录
				StockLockRecord lock = setLockRecord(slf, send);
				stockLockRecordMapper.insertSelective(lock);
				updateStockByParam(lock.getEnterpriseId(), lock.getParentId(),
						lock.getGoodsId(), lock.getLotId(), lock.getShelfId(),
						lock.getLockQuantity(), "subtract");
				
				//增加成本锁定记录
				updateCostByParam(slf.getEnterpriseId(), slf.getParentId(), slf.getGoodsId(),
						slf.getLotId(), slf.getQuantity(), "subtract", send, loginUser, 0L, "");
//				CostLockRecord costLock = setCostLockRecord(slf, send, cost);
//				costLockRecordMapper.insertSelective(costLock);
				
			}
		}
		
		if(send.getQuantityTotal().compareTo(vo.getQuantityTotal()) != 0){
			//与配货单数量不一致时更改配货单数量
			send.setQuantityTotal(vo.getQuantityTotal());
		}
		
		send.setStatus(DistrSendStatus.WAIT_OUT);
		distrSendMapper.updateByPrimaryKeySelective(send);

	}
	
	private StockLockRecord setLockRecord(PickShelf slf, DistrSend send) {
		StockLockRecord stockLockRecord = new StockLockRecord();
		stockLockRecord.setEnterpriseId(send.getEnterpriseId());
		stockLockRecord.setParentId(send.getParentId());
		stockLockRecord.setBaseOrderId(send.getId());
		stockLockRecord.setBaseOrderType(send.getOrderType());
		stockLockRecord.setBaseOrderCode(send.getCode());
		stockLockRecord.setBaseOrderDate(send.getDistrDate());
		stockLockRecord.setGoodsId(slf.getGoodsId());
		stockLockRecord.setGoodsCode(slf.getGoodsCode());
		stockLockRecord.setGoodsName(slf.getGoodsName());
		stockLockRecord.setLotId(slf.getLotId());
		stockLockRecord.setLotNum(slf.getLotNumber());
		stockLockRecord.setShelfId(slf.getShelfId());
		stockLockRecord.setShelfName(slf.getShelfName());
		stockLockRecord.setLockQuantity(slf.getQuantity());
		stockLockRecord.setCreaterId(send.getCreaterId());
		stockLockRecord.setCreaterCode(send.getCreaterCode());
		stockLockRecord.setCreaterName(send.getCreaterName());
		stockLockRecord.setCreateTime(new Date());
		return stockLockRecord;
	}
	
	private PickShelf setPickShelf(
			DistrSendOperationSlfVO distrSendOperationSlfVO, PickDetail pDtl) {
		PickShelf slf = new PickShelf();
		slf.setEnterpriseId(pDtl.getEnterpriseId());
		slf.setParentId(pDtl.getParentId());
		slf.setDtlId(pDtl.getId());
		slf.setPickId(pDtl.getPickId());
		slf.setGoodsId(pDtl.getGoodsId());
		slf.setGoodsCode(pDtl.getGoodsCode());
		slf.setGoodsName(pDtl.getGoodsName());
		slf.setLotId(distrSendOperationSlfVO.getLotId());
		LotNumber num = lotNumberMapper.selectByPrimaryKey(distrSendOperationSlfVO.getLotId());
		if(num == null){
			throw new InventoryBizException("","批号ID错误，查不到数据！");
		}
		slf.setLotNumber(num.getLotNum());
		slf.setProductDate(num.getProductDate());
		slf.setValidDate(num.getValidUntil());
		slf.setShelfId(distrSendOperationSlfVO.getShelfId());
		WarehouseShelf whSlf = warehouseShelfMapper.selectByPrimaryKey(distrSendOperationSlfVO.getShelfId());
		if(whSlf == null){
			throw new InventoryBizException("","货位ID错误，查不到数据！");
		}
		slf.setShelfName(whSlf.getName());
		slf.setShelfStatusDesc(distrSendOperationSlfVO.getShelfStatusDesc());
		slf.setQuantity(distrSendOperationSlfVO.getQuantity());
		slf.setLineNum(distrSendOperationSlfVO.getLineNum());
		slf.setStatus(pDtl.getStatus());
		slf.setRemark(pDtl.getRemark());
		slf.setCreaterId(pDtl.getCreaterId());
		slf.setCreaterCode(pDtl.getCreaterCode());
		slf.setCreaterName(pDtl.getCreaterName());
		slf.setCreateTime(new Date());
		slf.setModifierId(pDtl.getCreaterId());
		slf.setModifierCode(pDtl.getCreaterCode());
		slf.setModifierName(pDtl.getCreaterName());
		slf.setUpdateTime(new Date());
		return slf;
	}
	
	private PickDetail setPickDetail(DistrSendDetail sendDetail, PickOrder order,
			DistrSendOperationDtlVO dtlvo) {
		PickDetail dtl = new PickDetail();
		dtl.setEnterpriseId(order.getEnterpriseId());
		dtl.setParentId(order.getParentId());
		dtl.setPickId(order.getId());
		dtl.setOrderType(order.getOrderType());
		dtl.setPickCode(order.getCode());
		dtl.setPickDate(order.getPickDate());
		dtl.setBaseOrderDtlId(sendDetail.getId());
		dtl.setBaseOrderId(order.getBaseOrderId());
		dtl.setBaseOrderType(order.getBaseOrderType());
		dtl.setBaseOrderCode(order.getBaseOrderCode());
		dtl.setBaseOrderDate(order.getBaseOrderDate());
		dtl.setGoodsId(sendDetail.getGoodsId());
		dtl.setGoodsCode(sendDetail.getGoodsCode());
		dtl.setBarcode(sendDetail.getBarcode());
		dtl.setGoodsName(sendDetail.getGoodsName());
		dtl.setGoodsGenericName(sendDetail.getGoodsGenericName());
		dtl.setDosageId(sendDetail.getDosageId());
		dtl.setDosageName(sendDetail.getDosageName());
		dtl.setUnitId(sendDetail.getUnitId());
		dtl.setUnitName(sendDetail.getUnitName());
		dtl.setGoodsSpecification(sendDetail.getGoodsSpecification());
		dtl.setManufacturerId(sendDetail.getManufacturerId());
		dtl.setManufacturer(sendDetail.getManufacturer());
		dtl.setGoodsPlace(sendDetail.getGoodsPlace());
		dtl.setApprovalNumber(sendDetail.getApprovalNumber());
		dtl.setQuantity(dtlvo.getQuantity());
		dtl.setLineNum(dtlvo.getLineNum());
		dtl.setStatus(order.getStatus());
		dtl.setRemark(dtlvo.getRemark());
		dtl.setCreaterId(order.getCreaterId());
		dtl.setCreaterCode(order.getCreaterCode());
		dtl.setCreaterName(order.getCreaterName());
		dtl.setCreateTime(new Date());
		dtl.setModifierId(order.getCreaterId());
		dtl.setModifierCode(order.getCreaterCode());
		dtl.setModifierName(order.getCreaterName());
		dtl.setUpdateTime(new Date());
		return dtl;
	}
	
	private void updateCostByParam(Long enterpriseId, Long parentId, Long goodsId, Long lotId, 
			BigDecimal lockQuantity, String type, DistrSend send, UserVO user, Long batchId, String batchNum) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("goodsId", goodsId);
		paramMap.put("lotId", lotId);
		List<Cost> costList = costMapper.selectByParamMap(paramMap);
		if(costList == null || costList.size() == 0){
			throw new InventoryBizException("","企业ID"+enterpriseId+"商品ID"+goodsId+"批号ID"+lotId+"查不到SAAS_COST数据！");
		}else if("add".equals(type)){
			for (Cost cost : costList) {
				if(batchId.equals(cost.getBatchId()) && batchNum.equals(cost.getBatchNum())){
					cost.setUsableQuantity(cost.getUsableQuantity().add(lockQuantity).setScale(6, RoundingMode.HALF_UP));
					costMapper.updateByPrimaryKeySelective(cost);
				}	
			}
		}else if("subtract".equals(type)){
//			cost = costList.get(0);
//			cost.setUsableQuantity(cost.getUsableQuantity().subtract(lockQuantity).setScale(6, RoundingMode.HALF_UP));
//			costMapper.updateByPrimaryKey(cost);
			commonComponent.doLockCost(enterpriseId, parentId, send.getId(), send.getOrderType(), 
					send.getCode(), send.getDistrDate(), user, lockQuantity, costList);
		}
	}
	
	private void updateStockByParam(Long enterpriseId, Long parentId,
			Long goodsId, Long lotId, Long shelfId, BigDecimal lockQuantity, String type) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("enterpriseId", enterpriseId);
		paramMap.put("parentId", parentId);
		paramMap.put("goodsId", goodsId);
		paramMap.put("lotId", lotId);
		paramMap.put("shelfId",shelfId);
		List<Stock> stockList = stockMapper.selectByParamMap(paramMap);
		if(stockList == null || stockList.size() == 0){
			throw new InventoryBizException("","企业ID"+enterpriseId+"商品ID"+goodsId+"批号ID"+lotId+"货位ID"+shelfId+"查不到SAAS_STOCK数据！");
		}else if("add".equals(type)){
			
			Stock stock = stockList.get(0);
			stock.setUsableQuantity(stock.getUsableQuantity().add(lockQuantity).setScale(6, RoundingMode.HALF_UP));
			
			if(stock.getLockQuantity().compareTo(lockQuantity) < 0){
				throw new InventoryBizException("","库存原锁定量不足！");
			}
			
			stock.setLockQuantity(stock.getLockQuantity().subtract(lockQuantity).setScale(6, RoundingMode.HALF_UP));
			stockMapper.updateByPrimaryKeySelective(stock);
		
		}else if("subtract".equals(type)){
			
			Stock stock = stockList.get(0);
			
			if(stock.getUsableQuantity().compareTo(lockQuantity) < 0){
				throw new InventoryBizException("","库存可用量不足！");
			}
			
			stock.setUsableQuantity(stock.getUsableQuantity().subtract(lockQuantity).setScale(6, RoundingMode.HALF_UP));
			stock.setLockQuantity(stock.getLockQuantity().add(lockQuantity).setScale(6, RoundingMode.HALF_UP));
			stockMapper.updateByPrimaryKeySelective(stock);
		
		}
	}
	
	
	private PickOrder setPickOrder(DistrSend send, DistrSendOperationVO vo,
			UserVO loginUser) throws Exception {
		PickOrder order = new PickOrder();
		order.setEnterpriseId(send.getEnterpriseId());
		order.setParentId(send.getParentId());
		order.setOrderType(OrderRule.PICK.getType());
		order.setCode(orderCodeComponent.generate(OrderRule.PICK.getCodePrefix(), 
				loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
		order.setPickDate(vo.getPickDate());
		order.setRequestUnitId(send.getRequestUnitId());
		order.setRequestUnitCode(send.getRequestUnitCode());
		order.setRequestUnitName(send.getRequestUnitName());
		order.setBaseOrderId(send.getId());
		order.setBaseOrderType(send.getOrderType());
		order.setBaseOrderCode(send.getCode());
		order.setBaseOrderDate(send.getDistrDate());
		order.setPickManId(vo.getPickManId());
		User user = userMapper.selectByPrimaryKey(vo.getPickManId());
		if(user == null){
			throw new InventoryBizException("","拣货人员ID错误！");
		}
		order.setPickManCode(user.getCode());
		order.setPickManName(user.getName());
		order.setStatus(DistrSendStatus.WAIT_OUT);
		order.setCreaterId(loginUser.getUserId());
		order.setCreaterCode(loginUser.getUserCode());
		order.setCreaterName(loginUser.getUserName());
		order.setCreateTime(new Date());
		order.setModifierId(loginUser.getUserId());
		order.setModifierCode(loginUser.getUserCode());
		order.setModifierName(loginUser.getUserName());
		order.setUpdateTime(new Date());
		order.setRemark(vo.getRemark());
		return order;
	}
	
	
	private DistrSend validateOperationVO(DistrSendOperationVO vo, UserVO loginUser) {
		if(vo == null){
			throw new InventoryBizException("","保存拣货对象不能为空！");
		}
		DistrSend send = distrSendMapper.selectByPrimaryKey(vo.getId());
		if(send == null || !send.getEnterpriseId().equals(loginUser.getEnterpriseId())){
			throw new InventoryBizException("","查询配货单对象为空或配货单企业ID错误！");
		}
		if(vo.getPickDate() == null){
			throw new InventoryBizException("","拣货日期不能为空！");
		}
		if(vo.getPickManId() == null){
			throw new InventoryBizException("","拣货人员ID不能为空！");
		}
		
		if(vo.getDtlList() == null || vo.getDtlList().isEmpty()){
			throw new InventoryBizException("","拣货明细对象不能为空！");
		}
		
		ManageConfig config = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());
		
		BigDecimal dtlQuantity = BigDecimal.ZERO;//商品明细数量
		
		for (DistrSendOperationDtlVO dtl : vo.getDtlList()) {
			if(dtl.getShelfList() == null || dtl.getShelfList().isEmpty()){
				throw new InventoryBizException("","拣货货位明细对象不能为空！");
			}
			if(dtl.getLineNum() == null){
				throw new InventoryBizException("","商品明细对象-行号不能为空！");
			}
			BigDecimal slfQuantity = BigDecimal.ZERO;//货位明细数量
			List<DistrSendOperationSlfVO> slfList = dtl.getShelfList();
			for (DistrSendOperationSlfVO slf : slfList) {
				if(slf.getLineNum() == null){
					throw new InventoryBizException("","货位明细对象-行号不能为空！");
				}
				slfQuantity = slfQuantity.add(slf.getQuantity());
			}
			if(slfQuantity.compareTo(dtl.getQuantity()) != 0){
				throw new InventoryBizException("",dtl.getGoodsGenericName()+"商品数量与货位明细数量之和不一致！");
			}
			dtlQuantity = dtlQuantity.add(dtl.getQuantity());
			
			DistrSendDetail sendDetail = distrSendDetailMapper.selectByPrimaryKey(dtl.getSendDetailId());
			if(sendDetail == null){
				throw new InventoryBizException("","配货单明细ID错误！"+dtl.getSendDetailId());
			}
			
			if(config != null && config.getBusinessControl() != null && config.getBusinessControl() == 1){
				//业务质量流程开启
				if(dtl.getQuantity().compareTo(sendDetail.getQuantity()) != 0){
					throw new InventoryBizException("","当前开启业务流程质量控制，"+dtl.getGoodsGenericName()+"商品重新选择的数量必须与配货单中一致！");
				}
			}
		}
		if(dtlQuantity.compareTo(vo.getQuantityTotal()) != 0){
			throw new InventoryBizException("","总数量与明细数量之和不一致！");
		}
		
		return send;
	}

	private CostLockRecord setCostLockRecord(PickShelf slf, DistrSend send, Cost cost) {
		CostLockRecord costLockRecord = new CostLockRecord();
		costLockRecord.setEnterpriseId(send.getEnterpriseId());
		costLockRecord.setParentId(send.getParentId());
		costLockRecord.setBaseOrderId(send.getId());
		costLockRecord.setBaseOrderType(send.getOrderType());
		costLockRecord.setBaseOrderCode(send.getCode());
		costLockRecord.setBaseOrderDate(send.getDistrDate());
		costLockRecord.setGoodsId(slf.getGoodsId());
		costLockRecord.setGoodsCode(slf.getGoodsCode());
		costLockRecord.setGoodsName(slf.getGoodsName());
		costLockRecord.setLotId(slf.getLotId());
		costLockRecord.setLotNum(slf.getLotNumber());
		costLockRecord.setBatchId(cost.getBatchId());
		costLockRecord.setBatchNum(cost.getBatchNum());
		costLockRecord.setLockQuantity(slf.getQuantity());
		costLockRecord.setCreaterId(send.getCreaterId());
		costLockRecord.setCreaterCode(send.getCreaterCode());
		costLockRecord.setCreaterName(send.getCreaterName());
		costLockRecord.setCreateTime(new Date());
		return costLockRecord;
	}


	@Override
	public void exportExcel(OutputStream output, DistrSendOperationVO vo,
			UserVO loginUser) {
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");
        map.put("quantity","拣货数量");
        List<PickOrderDtlExcelVO> contextList = new ArrayList<PickOrderDtlExcelVO>();
        List<DistrSendOperationDtlVO> dtlList = vo.getDtlList();
        if(dtlList != null){
        	for (DistrSendOperationDtlVO dtlvo : dtlList) {
        		List<DistrSendOperationSlfVO> slfvoList = dtlvo.getShelfList();
        		if(slfvoList != null){
        			for (DistrSendOperationSlfVO slfVO : slfvoList) {
        				PickOrderDtlExcelVO excelvo = setExcelVO(dtlvo, slfVO);
        				contextList.add(excelvo);
					}
        		}
			}
        }
        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("单号:");
        titleSecondRow.append(vo.getCode() == null ? "":vo.getCode());
        titleSecondRow.append("  日期:");
        titleSecondRow.append(vo.getPickDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(vo.getPickDate()));
        titleSecondRow.append("  拣货人员:");
        titleSecondRow.append(vo.getPickManName() == null ? "":vo.getPickManName());
        titleSecondRow.append("  要货单位编码:");
        titleSecondRow.append(vo.getRequestUnitCode() == null ? "":vo.getRequestUnitCode());
        titleSecondRow.append("  要货单位名称:");
        titleSecondRow.append(vo.getRequestUnitName() == null ? "":vo.getRequestUnitName());
        titleSecondRow.append("  配货单号:");
        titleSecondRow.append(vo.getDistrCode() == null ? "":vo.getDistrCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(vo.getRemark() == null ? "":vo.getRemark());
        secondTitle.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        end.append(vo.getQuantityTotal() == null ? 0L : vo.getQuantityTotal());
        List<String> name = new ArrayList<String>();
//      //第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("拣货单");
        List<String> list = new ArrayList<>();
        list.add("quantity");
        purchaseGeneralComponent.commExcelExport(output,map,contextList,name,secondTitle,end.toString(),false,list);
		
	}

	private PickOrderDtlExcelVO setExcelVO(DistrSendOperationDtlVO dtlvo,
			DistrSendOperationSlfVO slfVO) {
		PickOrderDtlExcelVO excelvo = new PickOrderDtlExcelVO();
		excelvo.setGoodsCode(dtlvo.getGoodsCode());
		excelvo.setGoodsGenericName(dtlvo.getGoodsGenericName());
		excelvo.setDosageName(dtlvo.getDosageName());
		excelvo.setUnitName(dtlvo.getUnitName());
		excelvo.setGoodsSpecification(dtlvo.getGoodsSpecification());
		excelvo.setManufacturer(dtlvo.getManufacturer());
		excelvo.setGoodsPlace(dtlvo.getGoodsPlace());
		excelvo.setLotNumber(slfVO.getLotNumber());
		excelvo.setProductDate(slfVO.getProductDate());
		excelvo.setValidDate(slfVO.getValidDate());
		excelvo.setShelfName(slfVO.getShelfName());
		excelvo.setQuantity(slfVO.getQuantity());
		return excelvo;
	}

}
