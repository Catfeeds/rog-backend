package com.rograndec.feijiayun.chain.business.basic.warehouse.dao;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Warehouse record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Warehouse record);

    /**
     *
     * @mbg.generated
     */
    Warehouse selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Warehouse record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Warehouse record);

    List<WarehouseVO> getWarehouseExclusiveNullTreeByParam(@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId,  @Param("status")Integer status);


    List<WarehouseShelfVO> getShelfIdByParam(@Param("enterpriseId")Long enterpriseId, @Param("status")Integer status
            , @Param("warehouseIds") List<Long> warehouseIds, @Param("warehouseAreaIds")List<Long> warehouseAreaIds, @Param("warehouseCargoAreaIds")List<Long> warehouseCargoAreaIds);
    /**
     * 根据企业id和企业父id 状态查询所有仓库
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    List<ResponseWarehouseVO> getWarehouseByParam(@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId, @Param("status")Integer status,@Param("sortOrder")String sortOrder);

    Integer getWhouseCount(Long warehouseId);

    Integer getWhAreaCount(Long warehouseId);

    Integer getWhCargoAreaCount(Long warehouseId);

    Integer getWhShelfCount(Long warehouseId);


    List<WarehouseVO> getWarehouseTreeIncludeNullByParam(@Param("enterpriseId")Long enterpriseId, @Param("parentId") Long parentId, @Param("status")Integer status);

    /**
     * 根据货区id查询货位的数量
     * @param cargoId
     * @return
     */
    Integer getShelfCountByCargoId(Long cargoId);

    /**
     * 根据库区数量查货区数量
     * @param areaId
     * @return
     */
    Integer getCargoCountByAreaId(Long areaId);

    /**
     * 根据仓库id 查询库区数量
     * @param houseId
     * @return
     */
    Integer getAreaCountByHouseId(Long houseId);


    List<WarehouseVO> getWarehouseTreeByEnterpriseIdForSingle(@Param("enterpriseId")Long enterpriseId, 
    		@Param("status")Integer status, @Param("type")Integer type);

    
    /**
	 * @Description: TODO获取合格/不合格货位
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月16日 下午1:40:41 
	 * @param enterpriseId
	 * @param type 0-合格，1不合格
	 * @return 
	 * @return List<TreePOJO<WarehouseVO>>
	 */
	List<WarehouseVO> getWarehouseTreeByEnterpriseIdAndJobArea(
			@Param("enterpriseId")Long enterpriseId, @Param("type")Integer type);


	List<WarehouseVO> getWarehouseByEnterpriseId(Long enterpriseId);

	// 根据企业id和仓库id 查询库区
	List<WarehouseAreaVO> getWarehouseAreaByEnterpriseIdAndHID(@Param("enterpriseId")Long enterpriseId, @Param("warehouseId")Long warehouseId);

    // 根据企业id和库区id查询货区
    List<WarehouseCargoAreaSimpleVO> getWarehouseCargoAreaByEnterpriseIdAndHID(@Param("enterpriseId")Long enterpriseId, @Param("warehouseAreaIds")List<Long> warehouseAreaIds);

    List<WarehouseVO> getWarehouseTreeByEnterpriseIdForDouble(@Param("enterpriseId")Long enterpriseId,@Param("status")int status);

    Warehouse getWarehouseByCode(@Param("enterpriseId")Long enterpriseId,@Param("code")String code);

    Warehouse getWarehouseByName(@Param("enterpriseId")Long enterpriseId,@Param("name")String name);
}