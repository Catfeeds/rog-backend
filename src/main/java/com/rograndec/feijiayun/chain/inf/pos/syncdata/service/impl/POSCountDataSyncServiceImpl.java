package com.rograndec.feijiayun.chain.inf.pos.syncdata.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.inf.pos.syncdata.dao.POSCountDataSyncMapper;
import com.rograndec.feijiayun.chain.inf.pos.syncdata.service.POSCountDataSyncService;

@Service("pOSCountDataSyncService")
public class POSCountDataSyncServiceImpl implements POSCountDataSyncService{
	
	@Autowired
	POSCountDataSyncMapper pOSCountDataSyncMapper;

	@Override
	public Long countEnterprise(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countEnterprise(param);
	}

	@Override
	public Long countEnterpriseBusiness(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countEnterpriseBusiness(param);
	}

	@Override
	public Long countGoods(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countGoods(param);
	}

	@Override
	public Long countPriceOrderDetail(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPriceOrderDetail(param);
	}

	@Override
	public Long countIncompatibilityGoodsOne(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countIncompatibilityGoodsOne(param);
	}

	@Override
	public Long countSpecialGoods(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countSpecialGoods(param);
	}

	@Override
	public Long countSpecialPriceStrategy(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countSpecialPriceStrategy(param);
	}

	@Override
	public Long countSpecialPriceGoods(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countSpecialPriceGoods(param);
	}

	@Override
	public Long countLotNumber(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countLotNumber(param);
	}

	@Override
	public Long countStock(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countStock(param);
	}

	@Override
	public Long countWarehouseShelf(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countWarehouseShelf(param);
	}

	@Override
	public Long countWarehouseCargoArea(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countWarehouseCargoArea(param);
	}

	@Override
	public Long countMemberCardType(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countMemberCardType(param);
	}

	@Override
	public Long countMemberInfo(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countMemberInfo(param);
	}

	@Override
	public Long countPrescriptionRegister(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPrescriptionRegister(param);
	}

	@Override
	public Long countPrescriptionRegisterDetail(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPrescriptionRegisterDetail(param);
	}

	@Override
	public Long countPrescriptionRegisterShelf(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPrescriptionRegisterShelf(param);
	}

	@Override
	public Long countSpecialRegister(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countSpecialRegister(param);
	}

	@Override
	public Long countSpecialRegisterDetail(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countSpecialRegisterDetail(param);
	}

	@Override
	public Long countSpecialRegisterShelf(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countSpecialRegisterShelf(param);
	}

	@Override
	public Long countPrescriptionSignature(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPrescriptionSignature(param);
	}

	@Override
	public Long countUser(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countUser(param);
	}

	@Override
	public Long countUserAdministration(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countUserAdministration(param);
	}

	@Override
	public Long countUserPosition(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countUserPosition(param);
	}

	@Override
	public Long countPosition(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosition(param);
	}

	@Override
	public Long countPosSet(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosSet(param);
	}

	@Override
	public Long countPosPayType(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosPayType(param);
	}

	@Override
	public Long countPosBank(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosBank(param);
	}

	@Override
	public Long countPosTeam(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosTeam(param);
	}

	@Override
	public Long countPosClerk(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosClerk(param);
	}

	@Override
	public Long countPosPayee(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosPayee(param);
	}

	@Override
	public Long countPosPayeeAuth(Map<String, Object> param) throws Exception {
		return pOSCountDataSyncMapper.countPosPayeeAuth(param);
	}

	@Override
	public Long countSysRole(Map<String, Object> param) {
		return pOSCountDataSyncMapper.countSysRole(param);
	}

	@Override
	public Long countUserRole(Map<String, Object> param) {
		return pOSCountDataSyncMapper.countUserRole(param);
	}

	@Override
	public Long countGoodsBusiness(Map<String, Object> param) {
		return pOSCountDataSyncMapper.countGoodsBusiness(param);
	}

	@Override
	public Long countQualitySet(Map<String, Object> param) {
		return pOSCountDataSyncMapper.countQualitySet(param);
	}

	@Override
	public Long countPharmacySet(Map<String, Object> param) {
		return pOSCountDataSyncMapper.countPharmacySet(param);
	}

}
