package com.rograndec.feijiayun.chain.business.basic.supplier.service;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by ST on 2017/8/23.
 */
public interface SupplierService {

    Supplier getSupplierByCode(String code,Long enterpriseId);

	public Supplier getSupplierByCode2(String code,CommonParamSupplierAndGoods paramSupplierAndGoods);

    List<Supplier> getSupplier(UserVO userVO) throws Exception;

    List<Supplier> getSupplier4Add(UserVO userVO) throws Exception;

    List<Supplier> getSupplierByParam(String param, Long enterpriseId);

	Page getBasicSupplier(Integer pageNo, Integer pageSize,Page page, String param, UserVO userVO, Integer nature, Integer groupId, String businessName,
			String purchaseName,Integer approveStatus,Integer ownerChainType,String ownerCode,String ownerName);

	SupplierDetailVO getDetailSupplier(UserVO user, Long id);

	void saveDetailSupplier(UserVO user, SupplierDetailVO supplierDetailVO) throws Exception;

	void updateDetailSupplier(UserVO user, SupplierDetailVO supplierDetailVO) throws Exception;

	List<SupplierBasicExcelVO> selectSupplierVoPage(String param, Integer nature, Integer groupId, String businessName,
                                                    String purchaseName, UserVO user, Integer approveStatus);

	void exportExcel(OutputStream output, List<SupplierBasicExcelVO> supplierVOList);

	void saveConnectSupplier(SupplierGroupIncludeVO supplierGroupIncludeVO,Long enterpriseId);

	void updateSupplerGroup(UserVO user, SupplierGroupVO supplierGroupVO) throws Exception;

	void deleteSupplierGroup(Long id);

	void removeSupplierGroup(Long id);

	void RemoveSupplier(Long id);

	List<SupplierGroupExcelVO> ExportGroupSupplier(Long enterpriseId);

	void exporGroupExcel(OutputStream output, List<SupplierGroupExcelVO> supplierVOList);

	String checkSupplier(Long enterpriseId);

    String getGroupCode(Long groupId);
}
