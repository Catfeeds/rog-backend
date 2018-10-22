package com.rograndec.feijiayun.chain.business.report.storage.service.impl;

import com.rograndec.feijiayun.chain.business.report.storage.dao.*;
import com.rograndec.feijiayun.chain.business.report.storage.service.StockWarnReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.MaintenanceType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StockWarnReportServiceImpl implements StockWarnReportService{

	@Autowired
	private StockWarnReportMapper stockWarnReportMapper;
	
	@Autowired
	private StockWarnReportNearPeriodMapper stockWarnReportNearPeriodMapper;
	
	@Autowired
	private StockWarnReportLackMapper stockWarnReportLackMapper;
	
	@Autowired
	private StockWarnReportStoreMapper stockWarnReportStoreMapper;
	
	@Autowired
	private StockWarnReportLogSaleMapper stockWarnReportLogSaleMapper;
	
	@Autowired
	private StockWarnReportGoodsMaintanceMapper stockWarnReportGoodsMaintanceMapper;
	
	@Autowired
	private StockWarnReportGoodsDisplayCheckMapper stockWarnReportGoodsDisplayCheckMapper;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page selectExpireGoodsList(StockListQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else if("shelfName".equals(order)){
			order = "s.shelf_name";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnExpireGoodsVO> resultList = null;
		
		Long totalRecord = 0L;
		
		StockWarnExpireGoodsTotalVO resultVO = new StockWarnExpireGoodsTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){
			
			resultList = stockWarnReportMapper.selectExpireGoodsList(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportMapper.queryCountExpireGoodsList(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockWarnReportMapper.queryExpireGoodsList(vo, 
					loginUser.getEnterpriseId(), null);
		}else {
			
			resultList = stockWarnReportMapper.selectExpireGoodsList(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportMapper.queryCountExpireGoodsList(vo, null, loginUser.getEnterpriseId());
			
			resultVO = stockWarnReportMapper.queryExpireGoodsList(vo, 
					null, loginUser.getEnterpriseId());
		}
		
		if(resultVO == null){
			resultVO = new StockWarnExpireGoodsTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public StockWarnExpireGoodsTotalVO selectExpireGoodsList2WarnSet(WarnSet warnSet, UserVO loginUser) {

		if(null == warnSet){
			return new StockWarnExpireGoodsTotalVO();
		}

		StockWarnExpireGoodsTotalVO resultVO = new StockWarnExpireGoodsTotalVO();

		Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);

		List<StockWarnExpireGoodsVO> resultList = stockWarnReportMapper.selectExpireGoods2WarnSet(DateUtils.getDate(Instant.now().toEpochMilli(),warnSet.getWarnDays()),headEnterpriseId, loginUser.getEnterpriseId());

		if(resultList != null){
			for (StockWarnExpireGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}

		if(resultVO == null){
			resultVO = new StockWarnExpireGoodsTotalVO();
		}

		resultVO.setVoList(resultList);


		return resultVO;
	}

	@Override
	public StockWarnExpireGoodsTotalVO selectExpireGoodsListNotPage(StockListQueryVO vo, UserVO loginUser) {

		String order = vo.getOrder();
		String sort = vo.getSort();

		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else if("shelfName".equals(order)){
			order = "s.shelf_name";
		}else{
			order = null;
		}

		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}

		List<StockWarnExpireGoodsVO> resultList = null;

		StockWarnExpireGoodsTotalVO resultVO = new StockWarnExpireGoodsTotalVO();

		if(loginUser.getParentId() != null && loginUser.getParentId() != 0){

			resultList = stockWarnReportMapper.selectExpireGoodsList(vo, loginUser.getEnterpriseId(), null,
					null, null, order, sort);


			resultVO = stockWarnReportMapper.queryExpireGoodsList(vo,
					loginUser.getEnterpriseId(), null);
		}else {

			resultList = stockWarnReportMapper.selectExpireGoodsList(vo, null, loginUser.getEnterpriseId(),
					null, null, order, sort);


			resultVO = stockWarnReportMapper.queryExpireGoodsList(vo,
					null, loginUser.getEnterpriseId());
		}
		
		if(resultList != null){
			for (StockWarnExpireGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}
		
		if(resultVO == null){
			resultVO = new StockWarnExpireGoodsTotalVO();
		}

		resultVO.setVoList(resultList);


		return resultVO;
	}

	@Override
	public StockWarnNearPeriodGoodsTotalVO selectNearPeriodGoodsList2WarnSet(WarnSet warnSet, UserVO loginUser) {

		if(null == warnSet){
			return new StockWarnNearPeriodGoodsTotalVO();
		}

		Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);

		List<StockWarnNearPeriodGoodsVO> resultList = stockWarnReportNearPeriodMapper.selectNearPeriodGoods2WarnSet(headEnterpriseId, loginUser.getEnterpriseId(), warnSet.getWarnDays());

		StockWarnNearPeriodGoodsTotalVO resultVO = new StockWarnNearPeriodGoodsTotalVO();
		resultVO.setVoList(resultList);

		return resultVO;
	}

	@Override
	public StockWarnNearPeriodGoodsTotalVO selectNearPeriodGoodsListNotPage(StockListQueryVO vo, UserVO loginUser) {

		String order = vo.getOrder();
		String sort = vo.getSort();

		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else if("shelfName".equals(order)){
			order = "s.shelf_name";
		}else{
			order = null;
		}

		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}

		List<StockWarnNearPeriodGoodsVO> resultList = null;

		Long totalRecord = 0L;

		StockWarnNearPeriodGoodsTotalVO resultVO = new StockWarnNearPeriodGoodsTotalVO();

		if(loginUser.getParentId() != null && loginUser.getParentId() != 0){

			resultList = stockWarnReportNearPeriodMapper.selectNearPeriodGoodsList(vo, loginUser.getEnterpriseId(), null,
					null, null, order, sort);


			resultVO = stockWarnReportNearPeriodMapper.queryNearPeriodGoodsList(vo,
					loginUser.getEnterpriseId(), null);
		}else {

			resultList = stockWarnReportNearPeriodMapper.selectNearPeriodGoodsList(vo, null, loginUser.getEnterpriseId(),
					null, null, order, sort);

			totalRecord = stockWarnReportNearPeriodMapper.queryCountNearPeriodGoodsList(vo, null, loginUser.getEnterpriseId());

		}
		
		if(resultList != null){
			for (StockWarnNearPeriodGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}

		if(resultVO == null){
			resultVO = new StockWarnNearPeriodGoodsTotalVO();
		}

		resultVO.setVoList(resultList);

		return resultVO;
	}

	@Override
	public Page selectNearPeriodGoodsList(StockListQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());

		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else if("shelfName".equals(order)){
			order = "s.shelf_name";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnNearPeriodGoodsVO> resultList = null;
		
		Long totalRecord = 0L;
		
		StockWarnNearPeriodGoodsTotalVO resultVO = new StockWarnNearPeriodGoodsTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){
			
			resultList = stockWarnReportNearPeriodMapper.selectNearPeriodGoodsList(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportNearPeriodMapper.queryCountNearPeriodGoodsList(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockWarnReportNearPeriodMapper.queryNearPeriodGoodsList(vo, 
					loginUser.getEnterpriseId(), null);
		}else {
			
			resultList = stockWarnReportNearPeriodMapper.selectNearPeriodGoodsList(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportNearPeriodMapper.queryCountNearPeriodGoodsList(vo, null, loginUser.getEnterpriseId());
			
			resultVO = stockWarnReportNearPeriodMapper.queryNearPeriodGoodsList(vo, 
					null, loginUser.getEnterpriseId());
		}
		
		if(resultVO == null){
			resultVO = new StockWarnNearPeriodGoodsTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public StockWarnLackGoodsTotalVO selectLackGoodsListNotPage(StockListQueryVO vo, UserVO loginUser) {

		String order = vo.getOrder();
		String sort = vo.getSort();

		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}

		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}

		List<StockWarnLackGoodsVO> resultList = null;

		Long totalRecord = 0L;

		StockWarnLackGoodsTotalVO resultVO = new StockWarnLackGoodsTotalVO();

		if(loginUser.getParentId() != null && loginUser.getParentId() != 0){

			resultList = stockWarnReportLackMapper.selectLackGoodsList(vo, loginUser.getEnterpriseId(), null,
					null, null, order, sort);


			resultVO = stockWarnReportLackMapper.queryLackGoodsList(vo,
					loginUser.getEnterpriseId(), null);
		}else {

			resultList = stockWarnReportLackMapper.selectLackGoodsList(vo, null, loginUser.getEnterpriseId(),
					null, null, order, sort);


			resultVO = stockWarnReportLackMapper.queryLackGoodsList(vo,
					null, loginUser.getEnterpriseId());
		}

		if(resultList != null){
			for (StockWarnLackGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}
		
		if(resultVO == null){
			resultVO = new StockWarnLackGoodsTotalVO();
		}

		resultVO.setVoList(resultList);

		return resultVO;
	}

	@Override
	public Page selectLackGoodsList(StockListQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnLackGoodsVO> resultList = null;
		
		Long totalRecord = 0L;
		
		StockWarnLackGoodsTotalVO resultVO = new StockWarnLackGoodsTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){
			
			resultList = stockWarnReportLackMapper.selectLackGoodsList(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportLackMapper.queryCountLackGoodsList(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockWarnReportLackMapper.queryLackGoodsList(vo, 
					loginUser.getEnterpriseId(), null);
		}else {
			
			resultList = stockWarnReportLackMapper.selectLackGoodsList(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportLackMapper.queryCountLackGoodsList(vo, null, loginUser.getEnterpriseId());
			
			resultVO = stockWarnReportLackMapper.queryLackGoodsList(vo, 
					null, loginUser.getEnterpriseId());
		}
		
		if(resultVO == null){
			resultVO = new StockWarnLackGoodsTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public StockWarnStoreGoodsTotalVO selectStoreGoodsListNotPage(StockListQueryVO vo, UserVO loginUser) {

		String order = vo.getOrder();
		String sort = vo.getSort();

		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}

		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}

		List<StockWarnStoreGoodsVO> resultList = null;

		StockWarnStoreGoodsTotalVO resultVO = new StockWarnStoreGoodsTotalVO();

		if(loginUser.getParentId() != null && loginUser.getParentId() != 0){

			resultList = stockWarnReportStoreMapper.selectStoreGoodsList(vo, loginUser.getEnterpriseId(), null,
					null, null, order, sort);


			resultVO = stockWarnReportStoreMapper.queryStoreGoodsList(vo,
					loginUser.getEnterpriseId(), null);
		}else {

			resultList = stockWarnReportStoreMapper.selectStoreGoodsList(vo, null, loginUser.getEnterpriseId(),
					null, null, order, sort);


			resultVO = stockWarnReportStoreMapper.queryStoreGoodsList(vo,
					null, loginUser.getEnterpriseId());
		}

		if(resultList != null){
			for (StockWarnStoreGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}
		
		if(resultVO == null){
			resultVO = new StockWarnStoreGoodsTotalVO();
		}

		resultVO.setVoList(resultList);


		return resultVO;
	}

	@Override
	public Page selectStoreGoodsList(StockListQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnStoreGoodsVO> resultList = null;
		
		Long totalRecord = 0L;
		
		StockWarnStoreGoodsTotalVO resultVO = new StockWarnStoreGoodsTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){
			
			resultList = stockWarnReportStoreMapper.selectStoreGoodsList(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportStoreMapper.queryCountStoreGoodsList(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockWarnReportStoreMapper.queryStoreGoodsList(vo, 
					loginUser.getEnterpriseId(), null);
		}else {
			
			resultList = stockWarnReportStoreMapper.selectStoreGoodsList(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			totalRecord = stockWarnReportStoreMapper.queryCountStoreGoodsList(vo, null, loginUser.getEnterpriseId());
			
			resultVO = stockWarnReportStoreMapper.queryStoreGoodsList(vo, 
					null, loginUser.getEnterpriseId());
		}
		
		if(resultVO == null){
			resultVO = new StockWarnStoreGoodsTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

	@Override
	public Page selectLagSaleGoodsList(StockListQueryVO vo, UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());

		String order = vo.getOrder();
		String sort = vo.getSort();

		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}

		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}

		List<StockWarnLagSaleGoodsVO> resultList = null;

		Long totalRecord = 0L;
		StockWarnLagSaleGoodsTotalVO resultVO = new StockWarnLagSaleGoodsTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){

			resultList = stockWarnReportLogSaleMapper.selectLogSaleGoodsList(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);

			totalRecord = stockWarnReportLogSaleMapper.queryCountLogSaleGoodsList(vo, loginUser.getEnterpriseId(), null);

			resultVO = stockWarnReportLogSaleMapper.queryLogSaleGoodsList(vo,
					loginUser.getEnterpriseId(), null);
		}else {

			resultList = stockWarnReportLogSaleMapper.selectLogSaleGoodsList(vo, null, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);

			totalRecord = stockWarnReportLogSaleMapper.queryCountLogSaleGoodsList(vo, null, loginUser.getEnterpriseId());

			resultVO = stockWarnReportLogSaleMapper.queryLogSaleGoodsList(vo,
					null, loginUser.getEnterpriseId());
		}

		if(resultVO == null){
			resultVO = new StockWarnLagSaleGoodsTotalVO();
		}

		resultVO.setVoList(resultList);

		page.setResult(resultVO);

		page.setTotalRecord(totalRecord.intValue());

		return page;
	}

	@Override
	public StockWarnLagSaleGoodsTotalVO selectLagSaleGoodsList2WarnSet(WarnSet warnSet, UserVO loginUser) {

		if(null == warnSet){
			return new StockWarnLagSaleGoodsTotalVO();
		}

		Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);

		List<StockWarnLagSaleGoodsVO> resultList = stockWarnReportLogSaleMapper.selectLogSaleGoods2WarnSet(loginUser.getEnterpriseId(), headEnterpriseId, warnSet.getWarnDays());

		StockWarnLagSaleGoodsTotalVO resultVO = new StockWarnLagSaleGoodsTotalVO();


		if(resultList != null){
			for (StockWarnLagSaleGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}

		if(resultVO == null){
			resultVO = new StockWarnLagSaleGoodsTotalVO();
		}

		resultVO.setVoList(resultList);

		return resultVO;
	}

	@Override
	public StockWarnLagSaleGoodsTotalVO selectLagSaleGoodsListNotPage(StockListQueryVO vo, UserVO loginUser) {

		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnLagSaleGoodsVO> resultList = null;
		
		StockWarnLagSaleGoodsTotalVO resultVO = new StockWarnLagSaleGoodsTotalVO();

		if(loginUser.getParentId() != null && loginUser.getParentId() != 0){
			
			resultList = stockWarnReportLogSaleMapper.selectLogSaleGoodsList(vo, loginUser.getEnterpriseId(), null,
					null, null, order, sort);
			

			resultVO = stockWarnReportLogSaleMapper.queryLogSaleGoodsList(vo, 
					loginUser.getEnterpriseId(), null);
		}else {
			
			resultList = stockWarnReportLogSaleMapper.selectLogSaleGoodsList(vo, null, loginUser.getEnterpriseId(),
					null, null, order, sort);
			
			resultVO = stockWarnReportLogSaleMapper.queryLogSaleGoodsList(vo,
					null, loginUser.getEnterpriseId());
		}
		
		if(resultList != null){
			for (StockWarnLagSaleGoodsVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}
		
		if(resultVO == null){
			resultVO = new StockWarnLagSaleGoodsTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		return resultVO;
	}

	@Override
	public Page selectGoodsMaintanceList(StockListQueryVO vo,
			UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("storeCode".equals(order)){
			order = "e.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnGoodsMaintanceVO> resultList = null;
		
		Long totalRecord = 0L;
		StockWarnGoodsMaintanceTotalVO resultVO = new StockWarnGoodsMaintanceTotalVO();

		if(loginUser.getChainType() != 0 && loginUser.getChainType() != null){

			resultList = stockWarnReportGoodsMaintanceMapper.selectGoodsMaintanceList(vo, loginUser.getEnterpriseId(), null,
					page.getStart(), page.getPageSize(), order, sort);
			
			if(resultList != null){
				for (StockWarnGoodsMaintanceVO v : resultList) {
					v.setPlanMaintanceDate(
							DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));
				
					v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
				}
			}
			
			totalRecord = stockWarnReportGoodsMaintanceMapper.queryCountGoodsMaintanceList(vo, loginUser.getEnterpriseId(), null);
			
			resultVO = stockWarnReportGoodsMaintanceMapper.queryGoodsMaintanceList(vo, 
					loginUser.getEnterpriseId(), null);
		}else {
			
			resultList = stockWarnReportGoodsMaintanceMapper.selectParertGoodsMaintanceList(vo, loginUser.getEnterpriseId(),
					page.getStart(), page.getPageSize(), order, sort);
			
			if(resultList != null){
				for (StockWarnGoodsMaintanceVO v : resultList) {
					if(v.getMaintanceType() != null && v.getMaintanceType() == MaintenanceType.DELIVERYMODE_A.getCode()){
						if(DateUtils.getIntervalDays(new Date(), v.getValidUntil()) < v.getNearPeriodCycle()){
							v.setMaintanceType(MaintenanceType.DELIVERYMODE_B.getCode());
						}
					}
					if(v.getLastMaintanceDate() != null && v.getMaintanceCycle() != null){
						
						v.setPlanMaintanceDate(
								DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));
					}else if(v.getMaintanceCycle() == null){
						v.setPlanMaintanceDate(v.getLastMaintanceDate());
					}
					v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
				}
			}
			
			totalRecord = stockWarnReportGoodsMaintanceMapper.queryCountParertGoodsMaintanceList(vo, loginUser.getEnterpriseId());
			
			resultVO = stockWarnReportGoodsMaintanceMapper.queryParertGoodsMaintanceList(vo, 
					loginUser.getEnterpriseId());
		}
		
		if(resultVO == null){
			resultVO = new StockWarnGoodsMaintanceTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}


	@Override
	public List<StockWarnGoodsMaintanceVO> selectGoodsMaintanceList2WarnSet(WarnSet warnSet, UserVO loginUser) {

		if(null == warnSet){
			return null;
		}
		Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);

		List<StockWarnGoodsMaintanceVO> resultList = stockWarnReportGoodsMaintanceMapper.selectGoodsMaintance2WarSet(headEnterpriseId, loginUser.getEnterpriseId(),warnSet.getWarnDays());

		if(resultList != null){
			if(ChainType.Headquarters.getCode()  == loginUser.getChainType()){

				for (StockWarnGoodsMaintanceVO v : resultList) {
					v.setPlanMaintanceDate(
							DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));

					v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
				}

			}else {
				for (StockWarnGoodsMaintanceVO v : resultList) {
					if(v.getMaintanceType() != null && v.getMaintanceType() == MaintenanceType.DELIVERYMODE_A.getCode()){
						Integer nearPeriodCycle = v.getNearPeriodCycle() == null ? 0 : v.getNearPeriodCycle();
						Date validUntil = v.getValidUntil();
						if(null != validUntil && DateUtils.getIntervalDays(new Date(), validUntil) < nearPeriodCycle){
							v.setMaintanceType(MaintenanceType.DELIVERYMODE_B.getCode());
						}
					}
					if(v.getLastMaintanceDate() != null && v.getMaintanceCycle() != null){

						v.setPlanMaintanceDate(
								DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));
					}else if(v.getMaintanceCycle() == null){
						v.setPlanMaintanceDate(v.getLastMaintanceDate());
					}
					v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
				}
			}

			for (StockWarnGoodsMaintanceVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}

		return resultList;
	}

	@Override
	public StockWarnGoodsMaintanceTotalVO selectGoodsMaintanceListNotPage(StockListQueryVO vo,
										 UserVO loginUser) {
		String order = vo.getOrder();
		String sort = vo.getSort();

		if("storeCode".equals(order)){
			order = "code";
		}else if("code".equals(order)){
//			order = "s.goods_code";
			order = "code";
		}else{
			order = null;
		}

		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}

		List<StockWarnGoodsMaintanceVO> resultList = null;

		Long totalRecord = 0L;
		StockWarnGoodsMaintanceTotalVO resultVO = new StockWarnGoodsMaintanceTotalVO();

		if(loginUser.getParentId() != null && loginUser.getParentId() != 0){

			resultList = stockWarnReportGoodsMaintanceMapper.selectGoodsMaintanceList(vo, loginUser.getEnterpriseId(), null,
					null, null, order, sort);

			if(resultList != null){
				for (StockWarnGoodsMaintanceVO v : resultList) {
					v.setPlanMaintanceDate(
							DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));

					v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
				}
			}

		}else {

			resultList = stockWarnReportGoodsMaintanceMapper.selectParertGoodsMaintanceList(vo, loginUser.getEnterpriseId(),
					null, null, order, sort);

			if(resultList != null){
				for (StockWarnGoodsMaintanceVO v : resultList) {
					if(v.getMaintanceType() != null && v.getMaintanceType() == MaintenanceType.DELIVERYMODE_A.getCode()){
						Integer nearPeriodCycle = v.getNearPeriodCycle() == null ? 0 : v.getNearPeriodCycle();
						Date validUntil = v.getValidUntil();
						if(null != validUntil && DateUtils.getIntervalDays(new Date(), validUntil) < nearPeriodCycle){
							v.setMaintanceType(MaintenanceType.DELIVERYMODE_B.getCode());
						}
					}
					if(v.getLastMaintanceDate() != null && v.getMaintanceCycle() != null){

						v.setPlanMaintanceDate(
								DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));
					}else if(v.getMaintanceCycle() == null){
						v.setPlanMaintanceDate(v.getLastMaintanceDate());
					}
					v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
				}
			}

		}
		
		if(resultList != null){
			for (StockWarnGoodsMaintanceVO warnvo : resultList) {
				warnvo.setBusinessVarietyName(warnvo.getBusinessVariety()==null?"":BusinessVariety.getName(warnvo.getBusinessVariety()));
			}
		}
		

		if(resultVO == null){
			resultVO = new StockWarnGoodsMaintanceTotalVO();
		}

		resultVO.setVoList(resultList);

		return resultVO;
	}

	@Override
	public Page selectGoodsDisplayCheckList(StockListQueryVO vo,
			UserVO loginUser) {
		Page page = new Page(vo.getPageNo(), vo.getPageSize());
		
		String order = vo.getOrder();
		String sort = vo.getSort();
		
		if("shelfName".equals(order)){
			order = "s.code";
		}else if("code".equals(order)){
			order = "s.goods_code";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "desc";
		}
		
		List<StockWarnGoodsMaintanceVO> resultList = null;
		
		Long totalRecord = 0L;
		StockWarnGoodsMaintanceTotalVO resultVO = new StockWarnGoodsMaintanceTotalVO();

		resultList = stockWarnReportGoodsDisplayCheckMapper.selectGoodsDisplayCheckList(vo, loginUser.getEnterpriseId(),
				page.getStart(), page.getPageSize(), order, sort);
		
		if(resultList != null){
			for (StockWarnGoodsMaintanceVO v : resultList) {
				v.setPlanMaintanceDate(
						DateUtils.addInteger(v.getLastMaintanceDate(), Calendar.DATE, v.getMaintanceCycle()));
			
				v.setMaintanceTypeName(MaintenanceType.getName(v.getMaintanceType()));
			}
		}
		
		totalRecord = stockWarnReportGoodsDisplayCheckMapper.queryCountGoodsDisplayCheckList(vo, loginUser.getEnterpriseId());
		
		resultVO = stockWarnReportGoodsDisplayCheckMapper.queryGoodsDisplayCheckList(vo, 
					loginUser.getEnterpriseId());
		
		if(resultVO == null){
			resultVO = new StockWarnGoodsMaintanceTotalVO();
		}
		
		resultVO.setVoList(resultList);
		
		page.setResult(resultVO);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return page;
	}

}
