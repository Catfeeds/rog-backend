package com.rograndec.feijiayun.chain.business.storage.inventory.dao;

import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryDetail;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryDetailForOrderDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryDetailForPostVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryGoodsInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(InventoryDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(InventoryDetail record);

    /**
     *
     * @mbg.generated
     */
    InventoryDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InventoryDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InventoryDetail record);

    /**
     * 查询按商品盘点的明细
     * @param invId
     * @param enterpriseId
     * @return
     */
    List<InventoryDetailForOrderDetailVO> getDetailByInvId(@Param("invId") Long invId, @Param("enterpriseId") Long enterpriseId);

    /**
     * 查询按货位盘点的明细
     * @param invId
     * @param enterpriseId
     * @return
     */
    List<InventoryDetailForOrderDetailVO> getDetailByInvIdAndInvTypeShelf(@Param("invId") Long invId, @Param("enterpriseId") Long enterpriseId,@Param("isDiff")Integer isDiff);

    /**
     * 查询过账明细
     * @param invId
     * @param enterpriseId
     * @return
     */
    List<InventoryDetailForPostVO> getDetailByInvIdAndInvTypeShelfForPost(@Param("invId") Long invId, @Param("enterpriseId") Long enterpriseId);
    /**
     * 根据总单id查询 明细id
     * @param id
     * @return
     */
    List<Long> getGoodsIdByInventoryId(Long id);

    /**
     *
     * @mbg.generated
     */
    int deleteByInvIdAndGoodsId(@Param("invId") Long invId,@Param("goodsId") Long goodsId);

    int deleteByInvId(@Param("invId") Long invId);

    int updateStatusByInvId(@Param("invId")Long invId,@Param("status")Integer status);

    InventoryDetail getInventoryDetailByParam(@Param("enterpriseId")Long enterpriseId,@Param("invId")Long invId,@Param("goodsId")Long goodsId,@Param("goodsCode")String goodsCode);

    int updateDetailByInvId(@Param("invId") Long invId,@Param("status") Integer status,@Param("enterpriseId")Long enterpriseId);
    int updateByInvIdAndGoodsId(@Param("invId") Long invId,@Param("goodsId") Long goodsId);

   List<InventoryGoodsInfoVO> getGoodsInfoInInventory(@Param("invId") Long invId, @Param("param") String param, @Param("enterpriseId")Long enterpriseId);

    List<InventoryGoodsInfoVO> getGoodsInfoInInventory2Shelf(@Param("invId") Long invId, @Param("param") String param, @Param("enterpriseId")Long enterpriseId);

}