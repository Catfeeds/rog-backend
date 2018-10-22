package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpeningInventoryDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningInventoryDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningInventoryDetail record);

    /**
     *
     * @mbg.generated
     */
    OpeningInventoryDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningInventoryDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningInventoryDetail record);


    /**
     * 查训所有的期初库存记录
     * @return
     */
    List<OpeningInventoryDetail> getInventoryRecord(Long orderId);

    /**
     * 批量添加
     * @param list
     */
    void insertBatchOpeningGoods(List<OpeningInventoryDetail> list);

    int countInventoryRecordByParam(Long orderId);

    List<OpeningInventoryDetail> getInventoryRecordPageByParam(@Param("orderId")Long orderId,
                                                               @Param("start")int start,
                                                               @Param("pageSize")int pageSize);

    List<OpeningInventoryDetail> getOpeningDetailGroupByGoodsId(Long orderId);


}