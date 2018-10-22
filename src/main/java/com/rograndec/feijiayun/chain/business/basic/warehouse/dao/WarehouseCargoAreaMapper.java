package com.rograndec.feijiayun.chain.business.basic.warehouse.dao;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.PosWarehouseCargoAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.RequestQueryWarehouseCargoAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.ResponseWarehouseCargoAreaVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarehouseCargoAreaMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(WarehouseCargoArea record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(WarehouseCargoArea record);

    /**
     *
     * @mbg.generated
     */
    WarehouseCargoArea selectByPrimaryKey(Long id);

    List<WarehouseCargoArea> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WarehouseCargoArea record);

    /**
     *
     * @mbg.generated
     */

    WarehouseCargoArea getWarehouseCargoAreByEnterpId(Long enterpriseId);


    /**
     * 根据企业id和企业父id 库区id, 状态查询所有货区
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    List<ResponseWarehouseCargoAreaVO> getWarehouseCargoAreaByParam(@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId, @Param("warehouseAreaId")Long warehouseId,@Param("commonVO")RequestQueryWarehouseCargoAreaVO commonVO);

    /**
     * 根据货位id 查询对应的货区信息
     * @param shelfId
     * @return
     */
    CargoQualityStateVO getCargoByShelfId(Long shelfId);
    
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
    
   /**
    * 根据柜组id集合获取柜组（基础资料员工信息添加功能使用）
    * @author dongdong.zhang
    * @param list
    * @return
    */
    List<PosWarehouseCargoAreaVO> selectCargoAreaByAargoAreaIds(List<Long> list);

    /**
     * 根据名称或编码货区货区
     * */
    List<WarehouseCargoArea> selectCargoAreaByNameOrCode(Map map);

    WarehouseCargoArea getWarehouseCargoAreaByCodeName(@Param("enterpriseId")Long enterpriseId, @Param("code")String code, @Param("name")String name);
}