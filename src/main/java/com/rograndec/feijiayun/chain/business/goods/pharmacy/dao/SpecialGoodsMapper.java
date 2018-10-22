package com.rograndec.feijiayun.chain.business.goods.pharmacy.dao;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.SpecialGoods;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SpecialGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpecialGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SpecialGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SpecialGoods record);

    /**
     *
     * @mbg.generated
     */
    SpecialGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpecialGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SpecialGoods record);





    Long queryCountByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                            @Param("pageSize")int pageSize);



    List<SelectGoodsVO> selectGoodsVoByspecialParams(@Param("enterpriseId")Long enterpriseId, @Param("key")String key, @Param("specification")String specification, @Param("formulationType")String formulationType);



    Long queryCountBySpecialGoods(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                                  @Param("pageSize")int pageSize, @Param("key")String key,@Param("parentId")Long parentId);




    List<SpecialGoodsVO> selectGoodsVoBySpecialGoods(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                                                     @Param("pageSize")int pageSize, @Param("key")String key, @Param("orderName")String orderName, @Param("orderType")String orderType,@Param("parentId")Long parentId);


    List<SpecialGoods> selectByCode(Map param);

    Long selectSpecilGoodsLimit(Map param);

    SpecialGoods selectByGoodsId(Long goodsId);

}