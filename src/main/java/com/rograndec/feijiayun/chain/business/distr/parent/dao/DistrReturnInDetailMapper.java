package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInDetailShelfVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrReturnInDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnInDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnInDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnInDetail selectByPrimaryKey(Long id);

    List<DistrReturnInDetail> selectByReturnId(Long id);


    List<DistrReturnInDetail> selectByReturnIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnInDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnInDetail record);

    List<DistrReturnInDetailShelfVO> selectByDistrReturnInId(Long id);

    DistrReturnInDetailShelfVO getGoodsIdAndInStorageId(@Param("id")Long id, @Param("goodsId")Long goodsId);

    List<DistrOutDetail> getDistrReturnInDetailList(Long id);
}