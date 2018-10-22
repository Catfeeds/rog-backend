package com.rograndec.feijiayun.chain.business.quality.system.dao;

import com.rograndec.feijiayun.chain.business.quality.system.entity.QualityManageSystem;
import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.QualityManageSystemPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QualityManageSystemMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(QualityManageSystem record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(QualityManageSystem record);

    /**
     *
     * @mbg.generated
     */
    QualityManageSystem selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(QualityManageSystem record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(QualityManageSystem record);

    Long queryCountByStatusAndTypePage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize,
                                       @Param("type")String type, @Param("status")String status, @Param("key")String key, @Param("types")String[] types, @Param("statuss")String[] statuss);

    List<QualityManageSystemPageVO> selectByStatusAndType(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize,
                                                          @Param("type")String type, @Param("status")String status, @Param("key")String key, @Param("types")String[] types, @Param("statuss")String[] statuss, @Param("orderName")String orderName, @Param("orderType")String orderType);

    GetQualityManageSystemVO selectByIdAndEnterpriseId(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

    List<QualityManageSystem> selectFileCodeByEnterpriseId(Long enterpriseId);
}