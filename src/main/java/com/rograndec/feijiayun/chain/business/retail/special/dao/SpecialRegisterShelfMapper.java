package com.rograndec.feijiayun.chain.business.retail.special.dao;

import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterShelf;

import java.util.List;

public interface SpecialRegisterShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SpecialRegisterShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SpecialRegisterShelf record);

    /**
     *
     * @mbg.generated
     */
    SpecialRegisterShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpecialRegisterShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SpecialRegisterShelf record);
    /**
     *
     * <根据登记单主表id删除子表信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 14:17
     */
    void deleteByRegisterId(Long id);
    /**
     *
     * <根据登记单明细获取商品货架信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 16:26
     */
    List<SpecialRegisterShelf> listSpecialRegisterShelfByRegisterDetailId(Long id);
}