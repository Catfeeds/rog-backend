package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierVarietiesExportVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SupplierVarietiesMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierVarieties record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierVarieties record);

    /**
     *
     * @mbg.generated
     */
    SupplierVarieties selectByPrimaryKey(Long id);

    List<SupplierVarieties> selectByGoodsIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierVarieties record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierVarieties record);

    int batchInsert(List<SupplierVarieties> supplierVarieties);

    List<SupplierVarietiesExportVO> selectExport(Map map);

    List<SupplierVarietiesExportVO> selectByEnterpriseId(Map param);

    Integer selectCountByEnterpriseId(Map param);

    List<SupplierVarieties> getEnterpriseId(Map map);

    List<SupplierVarietiesExportVO> getSupplierVarieBySupplierId(@Param("supplierId") Long supplierId, @Param("enterpriseId")Long enterpriseId, @Param("status")Integer status);

    List<SupplierVarietiesExportVO> getSupplierVarieByParam(@Param("supplierReportVO") RequestParamSupplierReportVO paramOrgReportVO, @Param("enterpriseId")Long enterpriseId, @Param("status")Integer status);

	int updateLastPurData(@Param("detail") PurchaseInStorageDetail dtl, @Param("supplierId")Long supplierId);

	List<SupplierVarieties> selectSupplierVarietiesByParam(@Param("enterpriseId")Long enterpriseId,
			 @Param("goodsId")Long goodsId, @Param("supplierId")Long supplierId);
}