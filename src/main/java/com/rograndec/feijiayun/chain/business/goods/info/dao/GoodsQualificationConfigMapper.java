package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsQualificationConfig;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsQualificationConfigVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsQualificationConfigMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    GoodsQualificationConfig selectByPrimaryKey(Long id);

    List<GoodsQualificationConfig> selectByIds(@Param("enterpriseId") Long enterpriseId , @Param("list") List<Long> list);
    List<GoodsQualificationConfig> selectCurrentEnterpriseByIds(@Param("enterpriseId") Long enterpriseId,@Param("ownerId") Long ownerId , @Param("list") List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsQualificationConfig record);


    /**
     * 根据商品id 查询商品资质配置
     * @param goodsId
     * @return
     */
    List<GoodsQualificationConfigVO> getQualificationConfigList(Long goodsId);

    /**
     * 根据商品id 和 资质id 查询商品资质配置信息
     * @param goodsId
     * @param qualificationId
     * @return
     */
    GoodsQualificationConfigVO getGoodsQualificationConfigByGoodsIdQUID(@Param("goodsId") Long goodsId, @Param("qualificationId") Long qualificationId,@Param("enterpriseId")Long enterpriseId);

    int  updateByGoodsIdQUID(GoodsQualificationConfig record);


}