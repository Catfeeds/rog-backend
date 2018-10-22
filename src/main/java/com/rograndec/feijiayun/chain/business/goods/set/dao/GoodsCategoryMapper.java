package com.rograndec.feijiayun.chain.business.goods.set.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;

public interface GoodsCategoryMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(GoodsCategory record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(GoodsCategory record);

	/**
	 *
	 * @mbg.generated
	 */
	GoodsCategory selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(GoodsCategory record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(GoodsCategory record);

	List<Goods> selectGoodsByClassifyId(@Param("id") Long categoryId,@Param("enterpriseId") Long enterpriseId);

	/**
	 *
	 * @param enterpriseId
	 * @param status
	 * @return
	 */
	List<GoodsCategoryVO> selectByEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("status") Integer status);

	List<GoodsCategoryVO> selectClassifyOByCategoryId(Long categoryId);

	List<GoodsCategory> getCategory(@Param("enterpriseId") Long enterpriseId);

	/**
	 * 查询所有的只作为孩子级别的商品分类
	 * 
	 * @param enterpriseId
	 * @param status
	 * @return
	 */
	List<GoodsCategory> getAllChildrenCategory(@Param("enterpriseId") Long enterpriseId, @Param("status") Integer status,
			@Param("type") Integer type);

	/**
	 * 检查code 和 name 不能重复
	 * 
	 * @return
	 */
	List<GoodsCategory> selectByCodeOrName(@Param("enterpriseId") Long enterpriseId, @Param("code") String code, @Param("name") String name, @Param("id") Long id);

}