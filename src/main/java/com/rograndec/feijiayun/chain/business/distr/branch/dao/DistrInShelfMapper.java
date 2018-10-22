package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrInShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInShelf record);

    /**
     *
     * @mbg.generated
     */
    DistrInShelf selectByPrimaryKey(Long id);
    List<DistrInShelf> selectByDtlId(@Param("dtlId") Long dtlId);

    DistrInShelf selectByPrimaryKeyAndEID(@Param("id") Long id,@Param("enterpriseId")Long enterpriseId);


    List<DistrInShelf> selectByLotNumAndGoodsId(@Param("enterpriseId") Long enterpriseId,@Param("goodsId") Long goodsId,@Param("lotNumber") String lotNumber);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInShelf record);

    List<DistrInShelf> selectByEnterpriseIdAndGoodIdAndInIdAndDtlId(@Param("enterpriseId") Long enterpriseId,@Param("goodsId") Long goodsId,@Param("id") Long id,@Param("dtlId") Long dtlId);

    List<DistrInShelf> selectByEnterpriseIdAndInId(@Param("id") Long id);

    List<DistrInShelf> selectByInIds(List<Long> list);

    List<DistrInShelf> selectByIds(List<Long> list);

    List<DistrInShelf> selectByInIdsAndStatus(@Param("idLisLt") List<Long> idLisLt,@Param("statusList") List<Integer> statusList);
    List<DistrInShelf> selectSupplierOrderByInIdsAndStatusAndParam(@Param("outboundUnitId") Long outboundUnitId,
                                                      @Param("statusList") List<Integer> statusList,
                                                      @Param("param") String param,@Param("distrType") Integer distrType);

    List<DistrInShelf> selectEnterpriseOrderByInIdsAndStatusAndParam(@Param("outboundUnitId") Long outboundUnitId,
                                                      @Param("statusList") List<Integer> statusList,
                                                      @Param("param") String param,@Param("distrType") Integer distrType);

}