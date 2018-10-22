package com.rograndec.feijiayun.chain.business.basic.store.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreGroupTreeVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreGroupVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SimpleEnterpriseVO;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface StoreGroupService {

	List<StoreGroupTreeVO> selectStroeGroupTreeByEnterpriseId(Long enterpriseId);

	List<SelectBean> selectStoreGroupType(Long enterpriseId);

	EnterpriseGroup saveOrUpdate(StoreGroupVO vo, UserVO loginUser)throws Exception;

	List<SimpleEnterpriseVO> selectRelationStore(Long enterpriseId, Integer chainType);

	int saveRelationStore(Long id, String storeIds, UserVO loginUser);

	int removeRelationStore(Long id, Long parentId, Long level, Long enterpriseId);

	int deleteRelationStore(Long id, Long parentId, Long level,
			Long enterpriseId);

	List<EnterpriseGroup> selectExportEnterpriseGroup(Long enterpriseId);

	void excelExport(OutputStream output,
			List<EnterpriseGroup> enterpriseGroupList, UserVO loginUser);

}
