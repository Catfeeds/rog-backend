package com.rograndec.feijiayun.chain.business.storage.inventory.dao;

import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryShelf;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterOKVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(InventoryShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(InventoryShelf record);

    /**
     *
     * @mbg.generated
     */
    InventoryShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InventoryShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InventoryShelf record);

    int deleteByParam(@Param("invId")Long invId, @Param("goodsId")Long goodsId);

    int deleteShelfByDetailId(@Param("enterpriseId")Long enterpriseId,@Param("invId")Long invId, @Param("dId")Long dId);

    int deleteShelfByInvId(@Param("enterpriseId")Long enterpriseId,@Param("invId")Long invId);

    List<InventoryShelf> getInventoryShelfByParam(@Param("enterpriseId")Long enterpriseId,@Param("goodsId")Long goodsId,@Param("invId")Long invId,@Param("detailId")Long detailId,@Param("sort")String sort);

    int updateShelfByInvId(@Param("invId") Long invId,@Param("status") Integer status,@Param("enterpriseId")Long enterpriseId);

    List<InventoryShelf> getIdInvIdDtlId(@Param("invId")Long invId, @Param("enterpriseId")Long enterpriseId);

    GoodsShelfForRegisterOKVO getStockGoodsForExcel(@Param("enterpriseId")Long enterpriseId,@Param("invId")Long invId,@Param("goodsCode")String goodsCode, @Param("lotNum")String lotNum, @Param("shelfName")String shelfName);

    int updateByInvIdAndGoodsId(@Param("invId") Long invId,@Param("goodsId") Long goodsId);
    int updateByInvIdAnddtlId(@Param("invId") Long invId,@Param("dtlId") Long dtlId);

    int updateStatusByInvId(@Param("invId") Long invId,@Param("status") Integer status);
}