package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickOrder;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PickOrderMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PickOrder record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PickOrder record);

    /**
     *
     * @mbg.generated
     */
    PickOrder selectByPrimaryKey(Long id);
    PickOrder selectByBaseOrderId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PickOrder record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PickOrder record);

	Long getAlreadyDistrPickTotalRecord(DistrPickRequestVO condition);

	List<DistrPickVO> selectAlreadyDistrPickPage(DistrPickRequestVO condition);

	List<PickOrder> selectByBaseOrderIdAndType(@Param("baseOrderId")Long idbaseOrderId, 
			@Param("baseOrderType")Integer baseOrderType,
			@Param("enterpriseId")Long enterpriseId);
}