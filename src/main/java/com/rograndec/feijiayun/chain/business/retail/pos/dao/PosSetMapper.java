package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSet;

public interface PosSetMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosSet record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosSet record);

    /**
     *
     * @mbg.generated
     */
    PosSet selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosSet record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosSet record);
    
    /**
     * 
     * @Description: 按企业获取POS设置数据
     * @author yuting.li
     * @version 1.0 
     * @date 2017年9月18日 下午7:59:42 
     * @param enterpriseId
     * @return 
     * @return List<PosSet>
     */
    List<PosSet> findByEnterpriseId(Long enterpriseId);
    
}