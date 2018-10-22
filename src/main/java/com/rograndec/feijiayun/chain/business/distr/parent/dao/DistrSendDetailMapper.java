package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSendDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrSendDetailMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(DistrSendDetail record);

    /**
     * @mbg.generated
     */
    int insertSelective(DistrSendDetail record);

    /**
     * @mbg.generated
     */
    DistrSendDetail selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrSendDetail record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrSendDetail record);

    /**
     * <根据配送单id获取配送单明细信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/8 19:19
     */
    List<DistrSendDetail> listDistrSendDetailList(Long id);

    /**
     * 获取明细ID列表
     * @param id
     * @return
     */
    List<Long> distrSendDetailIds(Long id);

    void updateBySendId(@Param("baseOrderId")Long baseOrderId, @Param("enterpriseId")Long enterpriseId, @Param("status")Integer status);

    DistrSendDetail selectBySendId(@Param("baseOrderId")Long baseOrderId, @Param("goodsId")Long goodsId);

    Long selectByBaseOrderId(@Param("baseOrderId")Long baseOrderId);
}