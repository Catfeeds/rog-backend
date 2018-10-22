package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;

import java.util.List;
import java.util.Map;

public interface DistrInReturnDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReturnDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReturnDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrInReturnDetail selectByPrimaryKey(Long id);

    List<DistrInReturnDetail> selectByInReturnId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReturnDetail record);

    int updateByInReturnId(DistrInReturnDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReturnDetail record);
    
    void deleteDetailInfo(List<Long> list);
    
    List<Long> selectByReturnInId(Long id);
    
    int deleteDetailInfoByReturnInId(Map<String,Object> param);
}