package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherInReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.OtherInReportStasticVO;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInDetailVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInStasticVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OtherInMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OtherIn record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OtherIn record);

    /**
     *
     * @mbg.generated
     */
    OtherIn selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OtherIn record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OtherIn record);

    List<OtherIn> getOtherInPage(Map<String, Object> map);

    List<OtherInDetailVO> selectGoodsByAdd(@Param("selfShopId")Long selfShopId, @Param("divisionId") Long divisionId, @Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId, @Param("param") String param);

    List<OtherInReportPageVO> selectFatherOtherInReport(Map<String, Object> map);

    List<OtherInReportPageVO> selectSonOtherInReport(Map<String, Object> map);

    List<OtherInDetailVO> selectGoodsByOver(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

    void updateOtherInStatus(@Param("id") Long id,@Param("approvalStatus") Integer approvalStatus);

    Integer getTotalRecord(Map<String, Object> map);

    OtherInStasticVO selectStastic(Map<String, Object> map);

    Integer selectFatherOtherInTotalRecord(Map<String, Object> map);

    Integer selectSonOtherInTotalRecord(Map<String, Object> map);

    OtherInReportStasticVO selectFatherStastic(Map<String, Object> map);

    OtherInReportStasticVO selectSonStastic(Map<String, Object> map);
}