package com.rograndec.feijiayun.chain.business.goods.set.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsUnit;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsUnitMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(GoodsUnit record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(GoodsUnit record);

	/**
	 *
	 * @mbg.generated
	 */
	GoodsUnit selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(GoodsUnit record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(GoodsUnit record);

	List<GoodsUnitVO> selectAll(@Param("enterpriseId") Long enterpriseId, @Param("status") Integer status);

	List<Goods> selectGoodsByGoodsUnitId(Long id);

	/**
	 * 检查 编码 或 名称是否重复
	 * 
	 * @param code
	 * @param name
	 * @param id
     * @return
	 */
	List<GoodsUnit> selectByCodeOrName(@Param("enterpriseId") Long enterpriseId,@Param("code") String code, @Param("name") String name, @Param("id") Long id);

	//企业下，所有启用状态的单位
    List<GoodsUnitVO> getUsableUnit(Long enterpriseId);
}