package com.rograndec.feijiayun.chain.business.storage.storehouse.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.storage.storehouse.entity.TemperatureHumidity;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.ResponseTempHumVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureRecordVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseVO;

public interface TemperatureHumidityMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(TemperatureHumidity record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TemperatureHumidity record);

    /**
     *
     * @mbg.generated
     */
    TemperatureHumidity selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TemperatureHumidity record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TemperatureHumidity record);
    
    /**
     * 后去仓库下拉列表
     * @param enterpriseId
     * @return
     */
    List<WarehouseVO> getWarehouseList(Long enterpriseId);
    
    /**
     * 获取温湿度记录的总条数
     * @return
     */
    Integer getTempHumidityRecordListTotalNum(Map<String,Object> map);
    
    /**
     * 分页获取温湿度列表
     * @param start
     * @param pageSize
     * @param enterpriseId
     * @return
     */
    List<TemperatureRecordVO> getTempHumidityRecordList(Map<String,Object> map);
    
    
    ResponseTempHumVO getTempHumidityRecordDtlList(Long id);
    
    /**
     * 更改温湿度记录的修改人员的信息
     * @param recordId
     * @param modifierId
     * @param modifierCode
     * @param modifierName
     */
    void deleteFileFormTempHumidityRecord(@Param("recordId") Long recordId,@Param("modifierId") Long modifierId,
    		@Param("modifierCode") String modifierCode,@Param("modifierName") String modifierName,@Param("updateTime") Date updateTime);
    
    /**
     * 
     * @param recordId
     * @param modifierId
     * @param modifierCode
     * @param modifierName
     * @param fileId
     */
    void updateFileFormTempHumidityRecord(@Param("recordId") Long recordId,@Param("modifierId") Long modifierId,
    		@Param("modifierCode") String modifierCode,@Param("modifierName") String modifierName,@Param("fileId") Long fileId,@Param("updateTime") Date updateTime);
    
    String getWarehouseAreaNameById(Map<String,Object> map);
	
	String getWarehouseNameById(Map<String,Object> map);
}