package com.rograndec.feijiayun.chain.business.basic.store.service;

import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface StoreService {

	List<StoreVO> selectStoreVoPage(int pageNo, int pageSize, Integer chainType,
			String key, Integer groupId, String businessManName,
			Integer distrPriceType, Integer storeLevelId, Integer saleAreaId,
			Integer saleCircleId, Integer paymentProvision, Long enterpriseId,
			Page page, String order, String sort,Integer approveStatus);

	List<SelectBeanWithCode> selectStoreGroupByEnterpriseId(Long enterpriseId, Integer type);

	List<NewSelectBean> selectStoreLevelByEnterpriseId(Long enterpriseId);

	List<NewSelectBean> selectSaleAreaByEnterpriseId(Long enterpriseId);

	List<NewSelectBean> selectSaleCircleByEnterpriseId(Long enterpriseId);

	String updateEnterprise(StoreEnterpriseVO storeEnterpriseVO, UserVO loginUser,
			String reason) throws Exception;

	List<StoreExportVO> selectStoreVoPage(Integer chainType, String key,
			Integer groupId, String businessManName, Integer distrPriceType,
			Integer storeLevelId, Integer saleAreaId, Integer saleCircleId,
			Integer paymentProvisionId, Long enterpriseId, String order, String sort);

	void excelExport(OutputStream output, List<StoreExportVO> storeVoList, UserVO loginUser);

	List<SelectBeanWithCode> getStoreUserSelectBeanByEnterpriseId(Long enterpriseId);

	List<SelectBeanWithCode> selectPriceOrderListByEnterpriseId(Long enterpriseId);

}
