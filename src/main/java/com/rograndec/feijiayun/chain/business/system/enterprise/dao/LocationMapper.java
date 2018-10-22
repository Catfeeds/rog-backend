package com.rograndec.feijiayun.chain.business.system.enterprise.dao;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;

import java.util.List;

public interface LocationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Location record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Location record);

    /**
     *
     * @mbg.generated
     */
    Location selectByPrimaryKey(Integer id);

    List<Location> selectByIds(List<Integer> id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Location record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Location record);

    
    List<Location> selectProvinceLocation();

    
    List<Location> selectCityLocationByProvinceId(String provinceId);


	List<Location> selectAreaLocationByCityId(String cityId);
}