package com.rograndec.feijiayun.chain.business.basic.warehouse.dao;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.RequestQueryWarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.ResponseWarehouseAreaVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseAreaMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(WarehouseArea record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(WarehouseArea record);

    /**
     *
     * @mbg.generated
     */
    WarehouseArea selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WarehouseArea record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(WarehouseArea record);

    List<WarehouseArea> getWarehouseAreaByEnterpId(Long enterpriseId);


    /**
     * 根据企业id和企业父id,仓库id 状态查询所有库区
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    List<ResponseWarehouseAreaVO> getWarehouseAreaByParam(@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId, @Param("warehouseId") Long warehouseId, @Param("areaVO")RequestQueryWarehouseAreaVO areaVO);


    WarehouseArea getWarehouseAreaByCodeName(@Param("enterpriseId")Long enterpriseId,@Param("code")String code,@Param("name")String name);


}