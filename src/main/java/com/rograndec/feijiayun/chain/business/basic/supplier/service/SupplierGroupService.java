package com.rograndec.feijiayun.chain.business.basic.supplier.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierGroup;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierTreePOJO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupConnectVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupTreeVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SupplierGroupService {

	void addSupplierGroup(UserVO user, SupplierGroupVO supplierGroupVO) throws Exception;

	List<SupplierGroup> selectSupplierGroupSelector(UserVO user,int type);

	List<SupplierGroupTreeVO> selectSupplierTree(Long enterpriseId);

	List<SupplierTreePOJO> structTree(List<SupplierGroupTreeVO> list, UserVO userVO);

	List<SupplierGroupConnectVO> selectConnectSupplier(Long enterpriseId,Long id);

}
							