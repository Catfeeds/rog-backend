package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Manufacturer;
import com.rograndec.feijiayun.chain.business.goods.info.vo.ResponseManufacturerVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ManufacturerMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Manufacturer record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Manufacturer record);

    /**
     *
     * @mbg.generated
     */
    Manufacturer selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Manufacturer record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Manufacturer record);

    List<ResponseManufacturerVO> getManufacturer(Long enterpriseId);
    
    /**
     * 
     * @Description: 根据名称获取主键id
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月28日 下午5:17:28 
     * @param name
     * @return 
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> findByName(@Param("nameList") List<String> nameList);

    Manufacturer selectByName(@Param("enterpriseId") Long enterpriseId, @Param("manufactureName") String manufactureName);
}