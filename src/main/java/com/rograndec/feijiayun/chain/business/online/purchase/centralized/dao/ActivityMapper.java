package com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity.Activity;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.GetActivityEntrepriseVO;

import java.util.List;

public interface ActivityMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Activity record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Activity record);

    /**
     *
     * @mbg.generated
     */
    Activity selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Activity record);

    List<GetActivityEntrepriseVO> selectByEnterpriseId(Long enterpriseId);
}