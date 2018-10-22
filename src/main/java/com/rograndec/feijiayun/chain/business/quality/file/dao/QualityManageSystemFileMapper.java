package com.rograndec.feijiayun.chain.business.quality.file.dao;

import com.rograndec.feijiayun.chain.business.quality.file.entity.QualityManageSystemFile;
import com.rograndec.feijiayun.chain.business.quality.file.vo.GetQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.QualityManageSystemFilePageVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface QualityManageSystemFileMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(QualityManageSystemFile record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(QualityManageSystemFile record);

    /**
     *
     * @mbg.generated
     */
    QualityManageSystemFile selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(QualityManageSystemFile record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(QualityManageSystemFile record);

    Long queryCountByStatusAndTypePage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("type")String type,
                                       @Param("status")String status, @Param("key")String key, @Param("types")String[] types, @Param("statuss")String[] statuss);

    List<QualityManageSystemFilePageVO> selectByStatusAndType(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("type")String type,
                                                              @Param("status")String status, @Param("key")String key, @Param("types")String[] types,
                                                              @Param("statuss")String[] statuss, @Param("orderName")String orderName, @Param("orderType")String orderType);

    QualityManageSystemFilePageVO selectByDeptIds(@Param("deptId")String[] deptId);

    GetQualityManageSystemFileVO selectByIdAndEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    void updateData(@Param("status")Integer status, @Param("id")Long id, @Param("operateDate")Date operateDate, @Param("operator")String operator,
                    @Param("operateRemark")String operateRemark, @Param("enterpriseId")Long enterpriseId);
}