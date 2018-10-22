package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckLot;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.ClickCheckDetailOneVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnCheckDetailOneVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.ExportDistrReturnCheckDtlVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrReturnCheckLotMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnCheckLot record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnCheckLot record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnCheckLot selectByPrimaryKey(Long id);

    List<DistrReturnCheckLot> selectByCheckId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnCheckLot record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnCheckLot record);

    List<ClickCheckDetailOneVO> selectByBranchDistrInReturnOutShelf(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderDtlId")Long baseOrderDtlId);

    List<DistrReturnCheckDetailOneVO> selectByCheckIdAndEnterpriseId(@Param("id")Long id, @Param("dtlId")Long dtlId, @Param("enterpriseId")Long enterpriseId);

    List<ExportDistrReturnCheckDtlVO> selectByCheckIdAndEnterpriseIdList(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    DistrReturnCheckLot getUpdateReturnCheckLot(@Param("baseOrderDtlId")Long baseOrderDtlId, @Param("goodsId")Long goodsId, @Param("lotNumber")String lotNumber);
}