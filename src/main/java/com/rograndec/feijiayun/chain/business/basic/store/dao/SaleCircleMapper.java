package com.rograndec.feijiayun.chain.business.basic.store.dao;

import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleCircle;
import com.rograndec.feijiayun.chain.business.basic.store.vo.ExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.SelectBean;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleCircleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleCircle record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleCircle record);

    /**
     *
     * @mbg.generated
     */
    SaleCircle selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleCircle record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleCircle record);

	List<NewSelectBean> selectSaleCircleByEnterpriseId(Long enterpriseId);

    List<StoreVO> selectByPrimaryKeyNotExistsShopIds(@Param("enterpriseId") Long enterpriseId);

    List<SaleCircle> selectByEnterpriseId(Long enterpriseId);

    List<SaleCircle> selectByEnterpriseIdStatus(@Param("enterpriseId") Long enterpriseId, @Param("status") Integer status);

    List<ExportVO> selectExport(Long enterpriseId);
}