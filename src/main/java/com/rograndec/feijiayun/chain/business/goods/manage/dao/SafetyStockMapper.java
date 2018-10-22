package com.rograndec.feijiayun.chain.business.goods.manage.dao;

import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.storage.split.vo.UpdateSplitSetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SafetyStockMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SafetyStock record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SafetyStock record);

    /**
     *
     * @mbg.generated
     */
    SafetyStock selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SafetyStock record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SafetyStock record);

    /**
     * @Description: TODO根据安全库存ID批量查询
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月6日 上午11:00:39 
     * @param safetyStockId
     * @return 
     * @return List<SafetyStock>
     */
	List<SafetyStock> selectSafetyStockByIds(List<Long> list);

	List<SafetyStock> selectSafetyStockByGoodsIds(List<Long> list);

	/**
	 * @Description: TODO批量修改安全库存
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月6日 下午4:49:08 
	 * @param stockList 
	 * @return void
	 */
	void batchUpdate(List<SafetyStock> list);

	/**
	 * @Description: TODO批量新增安全库存
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月6日 下午7:47:22 
	 * @param stockList 
	 * @return void
	 */
	void batchInsert(List<SafetyStock> list);

	/**
	 * @Description: TODO根据enterpriseId和商品ID批量查询
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月16日 下午2:02:19 
	 * @param goodsIds
	 * @param enterpriseId
	 * @return 
	 * @return List<SafetyStock>
	 */
	List<SafetyStock> selectByGoodsIdsAndEnterpriseId(@Param("list")List<Long> list,
			@Param("enterpriseId")Long enterpriseId);

    /**
     * 根据商品id和企业id查询安全库存
     * @param goodsId 商品idˇ
     * @param enterpriseId 企业id
     * @return
     */
    SafetyStock selectByGoodsIdAndEnterpriseId(@Param("goodsId")Long goodsId,
                                               @Param("enterpriseId")Long enterpriseId);


    /**
     * 拆零设置-修改，更新默认货位
     * @param updateSplitSetVO
     * @return
     */
    int updateDefaultShelfByGoodsId(UpdateSplitSetVO updateSplitSetVO);

}