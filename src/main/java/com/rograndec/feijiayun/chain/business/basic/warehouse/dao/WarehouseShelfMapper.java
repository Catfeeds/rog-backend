package com.rograndec.feijiayun.chain.business.basic.warehouse.dao;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.ResponseWarehouseShelfVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarehouseShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(WarehouseShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(WarehouseShelf record);

    /**
     *
     * @mbg.generated
     */
    WarehouseShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WarehouseShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(WarehouseShelf record);

    /**
     * 根据名称获取货位
     * @param name
     * @param enterpriseId
     * @return
     */
    WarehouseShelf getShelfByName(@Param("name") String name, @Param("enterpriseId") Long enterpriseId);

    WarehouseShelf getWarehouseShelfByEnterpId(Long enterpriseId);

    /**
     * 根据企业id和企业父id 货区id, 状态查询所有货位
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    List<ResponseWarehouseShelfVO> getWarehouseShelfByParam(@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId, @Param("warehouseCargoAreaId")Long warehouseCargoAreaId, @Param("status")Integer status);

    void addBatchWarehouseShelf(List<WarehouseShelf> list);

    /**
	 * @Description: TODO根据ids查询所有WarehouseShelf
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月16日 上午10:47:10 
	 * @param checkLotIdList
	 * @return 
	 * @return List<PurchaseCheckLot>
	 */
	List<WarehouseShelf> selectByIds(List<Long> list);

    Map<String,Object> getShelfInfoById(Long id);

    List<Long> getShelfIdSByWarehouseID(@Param("warehouseId")Long warehouseId,@Param("enterpriseId")Long enterpriseId);
    List<WarehouseShelf> getSysShelfIdSByEnterpriseId(@Param("enterpriseId")Long enterpriseId);

    List<Long> getShelfIdSByWarehouseAreaID(@Param("warehouseAreaId")Long warehouseAreaId,@Param("enterpriseId")Long enterpriseId);

    List<Long> getShelfIdSByWarehouseCargoID(@Param("cargoAreaId")Long warehouseId,@Param("enterpriseId")Long enterpriseId);

}