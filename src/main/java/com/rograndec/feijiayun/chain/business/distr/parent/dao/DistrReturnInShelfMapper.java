package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrPurchaseInStorageShelfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.GetDistrReturnInShelfListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrReturnInShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnInShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnInShelf record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnInShelf selectByPrimaryKey(Long id);

    List<DistrReturnInShelf> selectByReturnIds(List<Long> list);

    List<DistrReturnInShelf> selectByReturnId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnInShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnInShelf record);

    List<DistrPurchaseInStorageShelfVO> selectByDistrReturnInDetailId(Long id);

    Long queryCount(@Param("id")Long id, @Param("orderName")String orderName, @Param("orderType")String orderType);

    List<GetDistrReturnInShelfListVO> getDistrReturnInShelfList(@Param("id")Long id, @Param("start")int start, @Param("pageSize")int pageSize, @Param("orderName")String orderName, @Param("orderType")String orderType);

    List<DistrReturnInShelf> getDistrReturnInShelf(Long id);

    List<DistrReturnInShelf> distrReturnInShelfByDistrOutShelfId(Long id);
}