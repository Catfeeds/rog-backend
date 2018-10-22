package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.business.system.set.vo.WarnSetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarnSetMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(WarnSet record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(WarnSet record);

    /**
     *
     * @mbg.generated
     */
    WarnSet selectByPrimaryKey(Long id);

    Integer checkValid(@Param("setType") Integer setType,
                       @Param("enterpriseId") Long enterpriseId,
                       @Param("parentId") Long parentId,
                       @Param("qualificationId") Long qualificationId,
                       @Param("content") String content);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WarnSet record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(WarnSet record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(WarnSet record);

    /**
     * 根据状态为启用的企业资质(包括自身跟供应商)返回WarnSet列表
     * @return
     */
    List<WarnSet> selectByQualification(Map param);

    List<WarnSetVO> selectByEnterpriseQualification(Map param);

    List<WarnSetVO> selectByUserQualification(Map param);

    List<WarnSetVO> selectBySupplierQualification(Long enterpriseId);

    List<WarnSetVO> selectByGoodsQualification(Long enterpriseId);


    List<WarnSetVO> selectBySetTypeByEnterpriseId(Map existsParam);
    List<WarnSet> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

    List<WarnSet> getWarnSetByParentId(Long enterpriseId);

    List<GoodsLicenseWarnVO> getGoodsLicenseWarn(@Param("param") String param, @Param("orderName") String orderName, @Param("orderType") String orderType, @Param("enterpriseId") Long enterpriseId, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    List<WarnSetVO> selectByStorageQualification(Long enterpriseId);

    Integer getCountGoodsLicenseWarn(@Param("param") String param, @Param("enterpriseId") Long enterpriseId);

    List<WarnSet> selectByEnterPriseId(Long enterpriseId);

    int deleteByQualificationId(@Param("id") Long id);
}