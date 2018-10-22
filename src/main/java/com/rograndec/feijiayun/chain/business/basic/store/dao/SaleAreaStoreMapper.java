package com.rograndec.feijiayun.chain.business.basic.store.dao;

import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleAreaStore;
import com.rograndec.feijiayun.chain.business.basic.store.vo.ExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;

import java.util.List;
import java.util.Map;

public interface SaleAreaStoreMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleAreaStore record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleAreaStore record);

    /**
     *
     * @mbg.generated
     */
    SaleAreaStore selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleAreaStore record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleAreaStore record);

    int batchInsert(List<SaleAreaStore> list);

    int deleteBySaleAreaIdByParentId(Map<String, Long> param);

    List<StoreVO> selectStoreVOBysaleAreaId(Long saleAreaId);

    List<ExportVO> selectExport(Long enterpriseId);

    int deleteBySaleAreaIdByParentIdByShopId(Map<String, Long> param);

    List<Long> selectStoreMapBySaleAreaId(List<Long> saleAreaIds);
}