package com.rograndec.feijiayun.chain.business.retail.special.dao;

import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterDetail;

import java.util.List;

public interface SpecialRegisterDetailMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(SpecialRegisterDetail record);

    /**
     * @mbg.generated
     */
    int insertSelective(SpecialRegisterDetail record);

    /**
     * @mbg.generated
     */
    SpecialRegisterDetail selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpecialRegisterDetail record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(SpecialRegisterDetail record);

    /**
     * <根据登记单主表id删除子表信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 14:17
     */
    void deleteByRegisterId(Long id);

    /**
     * <根据registerId获取商品明细>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 16:18
     */
    List<SpecialRegisterDetail> listSpecialRegisterDetailByRegisterId(Long id);
}