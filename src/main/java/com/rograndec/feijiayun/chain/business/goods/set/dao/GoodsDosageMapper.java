package com.rograndec.feijiayun.chain.business.goods.set.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsDosage;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;

public interface GoodsDosageMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(GoodsDosage record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(GoodsDosage record);

	/**
	 *
	 * @mbg.generated
	 */
	GoodsDosage selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(GoodsDosage record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(GoodsDosage record);

	List<GoodsDosageVO> selectAll(@Param("enterpriseId") Long enterpriseId, @Param("status") Integer status);

	List<Goods> selectGoodsByDosageId(Long id);

	/**
	 * 检查 编码 或 code是否重复
	 * 
	 *
     * @param enterpriseId
     * @param code
     * @param name
     * @return
	 */
	List<GoodsDosage> selectByCodeOrName(@Param("enterpriseId") Long enterpriseId, @Param("code") String code, @Param("name") String name, @Param("id") Long id);
	
	/**
	 * 
	 * @Description: 根据名称获取数据id
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月28日 下午5:09:22 
	 * @param name
	 * @return 
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> findByName(@Param("nameList") List<String> nameList);
}