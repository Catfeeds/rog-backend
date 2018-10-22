package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventory;
import org.apache.ibatis.annotations.Param;

public interface OpeningInventoryMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningInventory record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningInventory record);

    /**
     *
     * @mbg.generated
     */
    OpeningInventory selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningInventory record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningInventory record);

    /**
     * 获取企业期初库存记录
     * @param enterpriseId
     * @return
     */
    Integer getInventoryCount(Long enterpriseId);
    /**
     * 获取企业期初库存记录
     * @param enterpriseId
     * @return
     */

    OpeningInventory getInventoryByEnterpId(@Param("enterpriseId") Long enterpriseId, @Param("parentId")Long parentId);

}