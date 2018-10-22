package com.rograndec.feijiayun.chain.business.storage.storehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.storage.storehouse.entity.TemperatureHumidityDetail;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface TemperatureHumidityDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(TemperatureHumidityDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TemperatureHumidityDetail record);

    /**
     *
     * @mbg.generated
     */
    TemperatureHumidityDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TemperatureHumidityDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TemperatureHumidityDetail record);
    
    /**
     * 获取库区的下拉选
     * @param warehouseId
     * @return
     */
    List<WarehouseAreaVO> getWarehouseAreaList(Long warehouseId);
    
    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    UserVO getUserById(@Param("id") Long id,@Param("enterpriseId") Long enterpriseId);
    
    /**
     * 批量删除
     * @param id
     */
    void deleteInfoById(Long id);
}