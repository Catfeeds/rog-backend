package com.rograndec.feijiayun.chain.business.basic.store.dao;

import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.basic.store.vo.ExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.SelectBean;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StoreLevelMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StoreLevel record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StoreLevel record);

    /**
     *
     * @mbg.generated
     */
    StoreLevel selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StoreLevel record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StoreLevel record);

	List<NewSelectBean> selectStoreLevelByEnterpriseId(Long enterpriseId);

    List<StoreVO> selectByPrimaryKeyNotExistsShopIds(@Param("enterpriseId") Long enterpriseId);

    List<StoreLevel> selectByEnterpriseId(Long enterpriseId);

    List<StoreLevel> selectByEnterpriseIdStatus(@Param("enterpriseId") Long enterpriseId,@Param("status")Integer status);

    List<ExportVO> selectExport(Long enterpriseId);
}