package com.rograndec.feijiayun.chain.business.goods.pharmacy.dao;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.Incompatibility;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityVO3;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IncompatibilityMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Incompatibility record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Incompatibility record);

    /**
     *
     * @mbg.generated
     */
    Incompatibility selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Incompatibility record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Incompatibility record);




    Long queryCountByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                            @Param("pageSize")int pageSize, @Param("key")String key, @Param("paramVO")CommonParamSupplierAndGoods commParam);




    List<SelectGoodsVO> selectGoodsVoByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                                              @Param("pageSize")int pageSize, @Param("key")String key, @Param("paramVO")CommonParamSupplierAndGoods commParam);


    Long queryCountByParam(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                                     @Param("pageSize")int pageSize, @Param("key")String key, @Param("type")Long type,@Param("parentId")Long parentId);





    List<IncompatibilityVO3> IncompatibilityVo3ByParam(@Param("enterpriseId")Long enterpriseId, @Param("start")int start,
                                                       @Param("pageSize")int pageSize, @Param("key")String key, @Param("orderType")String orderType, @Param("type")Long type,@Param("parentId")Long parentId);

    List<Incompatibility> selectByCode(Map param);

    int deleteFormGoodsOne(Map<String,Object> param);
    
    int deleteFormGoodsTwo(Map<String,Object> param);

    /**
     * 根据药品id查询与其禁忌的药品和结果
     * @param param
     * @return
     */
    List<IncompatibilityGoodsVO> getIncompatibilityGoodsByGoodsId(Map param);
}