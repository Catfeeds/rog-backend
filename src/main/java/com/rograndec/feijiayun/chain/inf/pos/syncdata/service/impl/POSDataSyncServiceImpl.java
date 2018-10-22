package com.rograndec.feijiayun.chain.inf.pos.syncdata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosStandMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosStand;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.inf.pos.syncdata.dao.POSDataSyncMapper;
import com.rograndec.feijiayun.chain.inf.pos.syncdata.service.POSDataSyncService;

/**
 * 
 * @ClassName: POSDataSyncService   
 * @Description: POS同步接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月11日 下午3:13:54
 */
@Service("pOSDataSyncService")
public class POSDataSyncServiceImpl implements POSDataSyncService{
	
	@Autowired
	private POSDataSyncMapper pOSDataSyncMapper;
	
	@Autowired
	private PosStandMapper posStandMapper;

	@Override
	public List<Map<String, Object>> getEnterprise(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getEnterprise(param));
	}
	
	@Override
	public List<Map<String, Object>> getEnterpriseBusiness(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getEnterpriseBusiness(param));
	}

	@Override
	public List<Map<String, Object>> getGoods(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getGoods(param));
	}

	@Override
	public List<Map<String, Object>> getPriceOrderDetail(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPriceOrderDetail(param));
	}

	@Override
	public List<Map<String, Object>> getIncompatibilityGoodsOne(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getIncompatibilityGoodsOne(param));
	}

	@Override
	public List<Map<String, Object>> getSpecialGoods(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSpecialGoods(param));
	}

	@Override
	public List<Map<String, Object>> getSpecialPriceStrategy(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSpecialPriceStrategy(param));
	}

	@Override
	public List<Map<String, Object>> getSpecialPriceGoods(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSpecialPriceGoods(param));
	}

	@Override
	public List<Map<String, Object>> getLotNumber(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getLotNumber(param));
	}

	@Override
	public List<Map<String, Object>> getStock(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getStock(param));
	}

	@Override
	public List<Map<String, Object>> getMemberCardType(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getMemberCardType(param));
	}

	@Override
	public List<Map<String, Object>> getMemberInfo(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getMemberInfo(param));
	}

	@Override
	public List<Map<String, Object>> getPrescriptionRegister(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPrescriptionRegister(param));
	}

	@Override
	public List<Map<String, Object>> getPrescriptionRegisterDetail(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPrescriptionRegisterDetail(param));
	}

	@Override
	public List<Map<String, Object>> getPrescriptionRegisterShelf(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPrescriptionRegisterShelf(param));
	}

	@Override
	public List<Map<String, Object>> getSpecialRegister(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSpecialRegister(param));
	}

	@Override
	public List<Map<String, Object>> getSpecialRegisterDetail(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSpecialRegisterDetail(param));
	}

	@Override
	public List<Map<String, Object>> getSpecialRegisterShelf(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSpecialRegisterShelf(param));
	}

	@Override
	public List<Map<String, Object>> getUser(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getUser(param));
	}

	@Override
	public List<Map<String, Object>> getUserAdministration(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getUserAdministration(param));
	}

	@Override
	public List<Map<String, Object>> getUserPosition(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getUserPosition(param));
	}

	@Override
	public List<Map<String, Object>> getPosition(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosition(param));
	}

	@Override
	public List<Map<String, Object>> getPosSet(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosSet(param));
	}

	@Override
	public List<Map<String, Object>> getPosPayType(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosPayType(param));
	}

	@Override
	public List<Map<String, Object>> getPosBank(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosBank(param));
	}

	@Override
	public List<Map<String, Object>> getPosTeam(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosTeam(param));
	}

	@Override
	public List<Map<String, Object>> getPosClerk(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosClerk(param));
	}

	@Override
	public List<Map<String, Object>> getPosPayee(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosPayee(param));
	}

	@Override
	public List<Map<String, Object>> getPosPayeeAuth(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPosPayeeAuth(param));
	}
	

	@Override
	public List<Map<String, Object>> getSysRole(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getSysRole(param));
	}

	@Override
	public List<Map<String, Object>> getUserRole(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getUserRole(param));
	}
	
	@Override
	public Integer countGoods(Map<String, Object> param) throws Exception {
		return pOSDataSyncMapper.countGoods(param);
	}

	@Override
	public Integer countPriceOrderDetail(Map<String, Object> param) throws Exception {
		return pOSDataSyncMapper.countPriceOrderDetail(param);
	}

	@Override
	public Integer countStock(Map<String, Object> param) throws Exception {
		return pOSDataSyncMapper.countStock(param);
	}
	
	@Override
	public List<Map<String, Object>> getGoodsBusiness(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getGoodsBusiness(param));
	}

	@Override
	public List<Map<String, Object>> getQualitySet(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getQualitySet(param));
	}
	
	@Override
	public List<Map<String, Object>> getPharmacySet(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPharmacySet(param));
	}
	
	
	/**
	 * 
	 * @Description: 处理字段中的下划线
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午6:03:44 
	 * @param list
	 * @return 
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> fromatData(List<Map<String, Object>> list){
		List<Map<String, Object>> array = new ArrayList<>();
		Map<String, Object> data = new HashMap<>(list.size());
		for (Map<String, Object> object : list) {
			Map<String, Object> maptable = new HashMap<>();
			for (Map.Entry<String, Object> entry : object.entrySet()) {
				if (!data.containsKey(entry.getKey())) {
					data.put(entry.getKey(), com.rograndec.feijiayun.chain.utils.string.StringUtil.underlineToCamel(entry.getKey()));
				}
				maptable.put(data.get(entry.getKey())+"", entry.getValue());
			}
			array.add(maptable);
		}
		return array;
	}

	@Override
	public Map<String,String> savePosStand(PosStand posStand) throws Exception {
		Map<String,String> check = posStandMapper.checkMac(posStand.getEnterpriseId(), posStand.getMac());
		if(null == check || StringUtils.isBlank(check.get("code"))) {
			Map<String,String> mapCode = posStandMapper.getStandCode(posStand.getEnterpriseId());
			if(null != mapCode && mapCode.size() > 0) {
				posStand.setCode(mapCode.get("code"));
				posStand.setStatus(EnableStatus.ENABLE.getStatus());
				posStandMapper.insertSelective(posStand);
				return mapCode;
			}
			return null;
		} else {
			return check;
		}
	}

	@Override
	public List<Map<String, Object>> getWarehouseShelf(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getWarehouseShelf(param));
	}

	@Override
	public List<Map<String, Object>> getWarehouseCargoArea(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getWarehouseCargoArea(param));
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long countByTableName(Map<String, Object> param) throws Exception {
		return pOSDataSyncMapper.countByTableName(param);
	}

	@Override
	public List<Map<String, Object>> getPrescriptionSignature(Map<String, Object> param) throws Exception {
		return fromatData(pOSDataSyncMapper.getPrescriptionSignature(param));
	}
	

}
