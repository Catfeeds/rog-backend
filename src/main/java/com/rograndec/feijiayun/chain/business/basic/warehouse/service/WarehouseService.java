package com.rograndec.feijiayun.chain.business.basic.warehouse.service;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ST on 2017/8/23.
 */
public interface WarehouseService {
    WarehouseShelf getWarehouseByName(String name, Long enterpriseId);


    //仓库操作
    void addWarehouse(Warehouse warehouse);
    void updateWarehouse(Warehouse warehouse);
    void deleteWarehouse(Long id);

    Warehouse getDefaultWarehouse(Long enterpriseId,String enterpriseCode);


    void getWarehouseByParam(Page<List<ResponseWarehouseVO>> page, UserVO userVO, Integer status,  String sortOrder);

    //库区操作
    void addWarehouseArea(WarehouseArea warehouseArea);
    void updateWarehouseArea(WarehouseArea warehouseArea);
    void deleteWarehouseArea(Long id);

    //货区操作
    void addWarehouseCargoArea(WarehouseCargoArea warehouseCargoArea,UserVO userVO) throws Exception ;
    void updateWarehouseCargoArea(WarehouseCargoArea warehouseCargoArea,UserVO userVO);
    void deleteWarehouseCargoArea(Long id);

    //货位操作
    void addWarehouseShelf(WarehouseShelf warehouseShelf);
    void addBatchWarehouseShelf(List<WarehouseShelf> shelfList);
    void updateWarehouseShelf(WarehouseShelf warehouseShelf);
    void deleteWarehouseShelf(Long id);



    Warehouse getWarehouseByKey(Long warehouseId);

    WarehouseArea getWarehouseAreaByKey(Long warehouseAreaId);

    WarehouseCargoArea getWarehouseCargoAreaByKey(Long warehouseCargoAreaId);
    /**
     * 根据企业id,上级企业id,仓库id 查询库区
     * @param enterpriseId
     * @param parentId
     * @param warehouseId
     * @param status
     * @return
     */
    void getWarehouseAreaByParam(Page<List<ResponseWarehouseAreaVO>> page,Long enterpriseId, Long parentId, Long warehouseId, String enterpriseCode,RequestQueryWarehouseAreaVO areaVO);
    /**
     * 根据企业id,上级企业id,库区id 查询货区
     * @param enterpriseId
     * @param parentId
     * @param warehouseAreaId 库区id
     * @param status
     * @return
     */
    void getWarehouseCargoAreaByParam(Page<List<ResponseWarehouseCargoAreaVO>> page,Long enterpriseId, Long parentId, Long warehouseAreaId,String enterpriseCode, RequestQueryWarehouseCargoAreaVO commonVO);

    /**
     * 根据企业id 查询仓库--》库区--》货区--》货位的级联树(不含空树)
     * @param type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    List<TreePOJO<WarehouseVO>> getWarehouseExclusiveNullTreeByParam(Integer type, Long enterpriseId, Long parentId, Integer status);

    /**
     * 根据企业id 查询仓库--》库区--》货区--》货位的级联树(含空树)
     * @param type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    List<TreePOJO<WarehouseVO>> getWarehouseTreeIncludeNullByParam(Integer type, Long enterpriseId, Long parentId, Integer status);

    /**
     * 根据货位查询库存数
     * @param shelfId
     * @return
     */
    Integer getStockCountByShelfId(Long shelfId);

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

    ResponseWarehouseShelfVO getWarehouseShelf(Long id);

	List<TreePOJO<WarehouseVO>> getWarehouseShelfTreeByParam(
			Long enterpriseId, Integer status, Integer type);

	
	/**
     * 
     * @Description: 零售管理>POS管理>柜组
     * @author yuting.li
     * @version 1.0 
     * @date 2017年9月20日 下午2:51:57 
     * @param enterpriseId
     * @return
     * @throws Exception 
     * @return List<PosWarehouseCargoAreaVO>
     */
    List<PosWarehouseCargoAreaVO> findByEnterpriseIdPosgz(@Param("enterpriseId")Long enterpriseId) throws Exception;

    WarehouseShelf selectByKeyId(Long id);

    WarehouseCargoArea selectByCargoId(Long cargoAreaId);

    List<TreePOJO<WarehouseVO>> getWarehouseTreeByEnterpriseIdForDouble(Long enterpriseId, Integer status);

    List<ResponseWarehouseVO> getWarehouseList(Long enterpriseId, Long parentId, Integer status);

    List<ResponseWarehouseAreaVO> getWarehouseAreaList(Long enterpriseId, Long parentId, Long warehouseId, RequestQueryWarehouseAreaVO requestWarehouseCommonVO);

    List<ResponseWarehouseCargoAreaVO> getWarehouseCargoAreaByParam(Long enterpriseId, Long parentId, Long warehouseAreaId, RequestQueryWarehouseCargoAreaVO commonVO);
}
