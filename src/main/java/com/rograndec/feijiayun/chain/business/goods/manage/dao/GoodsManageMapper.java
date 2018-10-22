package com.rograndec.feijiayun.chain.business.goods.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStorePageVO;

public interface GoodsManageMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsManage record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsManage record);

    /**
     *
     * @mbg.generated
     */
    GoodsManage selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsManage record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsManage record);

    /**
     * @Description: TODO根据总部ID、商品ID查询
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月5日 下午5:26:34 
     * @param enterpriseId
     * @param goodsId
     * @param chainType
     * @param key
     * @param order
     * @param sort
     * @return 
     * @return List<GoodsHeadquartersDetailVO>
     */
	List<GoodsHeadquartersDetailVO> selectGoodsManageByEnterpriseIdAndGoodsId(
			@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId, @Param("chainType")Integer chainType, 
			@Param("key")String key, @Param("order")String order, @Param("sort")String sort);

	
	/**
    *
    * @mbg.generated
    */
	List<GoodsManage> selectByIds(List<Long> list);

	/**
	 * @Description: TODO批量保存商品管理表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月6日 下午7:59:50 
	 * @param manageList 
	 * @return void
	 */
	void batchInsert(List<GoodsManage> list);

	/**
	 * @Description: TODO按组织机构按商品区检索
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月7日 上午11:10:29 
	 * @param enterpriseId
	 * @param key
	 * @return 
	 * @return Long
	 */
	Long queryEnterpriseHeadquartersCountByGoodsParams(@Param("enterpriseId")Long enterpriseId,
			@Param("key")String key);

	/**
	 * @Description: TODO按组织机构按商品区检索
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月7日 上午11:46:25 
	 * @param start
	 * @param pageSize
	 * @param enterpriseId
	 * @param key
	 * @param order
	 * @param sort
	 * @return 
	 * @return List<EnterpriseHeadquartersVO>
	 */
	List<EnterpriseHeadquartersVO> selectEnterpriseHeadquartersVOByGoodsParams(
			@Param("start")int start, @Param("pageSize")int pageSize, @Param("enterpriseId")Long enterpriseId, 
			@Param("key")String key, @Param("order")String order, @Param("sort")String sort);

	/**
	 * @Description: TODO按组织机构按组织机构检索
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月7日 上午11:10:29 
	 * @param enterpriseId
	 * @param key
	 * @return 
	 * @return Long
	 */
	Long queryEnterpriseHeadquartersCountByEnterpriseParams(@Param("enterpriseId")Long enterpriseId,
			@Param("key")String key);

	/**
	 * @Description: TODO按组织机构按组织机构检索
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月7日 下午1:29:43 
	 * @param start
	 * @param pageSize
	 * @param enterpriseId
	 * @param key
	 * @param order
	 * @param sort
	 * @return 
	 * @return List<EnterpriseHeadquartersVO>
	 */
	List<EnterpriseHeadquartersVO> selectEnterpriseHeadquartersVOByEnterpriseParams(
			@Param("start")int start, @Param("pageSize")int pageSize, @Param("enterpriseId")Long enterpriseId,
			@Param("key")String key, @Param("order")String order, @Param("sort")String sort);

	/**
	 * @Description: TODO分店分页展示商品管理总数量
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月8日 下午7:41:36 
	 * @param enterpriseId
	 * @param key
	 * @return 
	 * @return Long
	 */
	Long queryStoreCountByGoodsParams(@Param("enterpriseId")Long enterpriseId, @Param("key")String key,
			@Param("type")Integer type);
	
	/**
	 * @Description: TODO分店分页展示商品管理
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月8日 下午7:41:36 
	 * @param enterpriseId
	 * @param key
	 * @return 
	 * @return Long
	 */
	List<GoodsStorePageVO> selectGoodsStoreVOByGoodsParams(@Param("start")int start,
			@Param("pageSize")int pageSize, @Param("enterpriseId")Long enterpriseId,
			@Param("type")Integer type, @Param("key")String key,
			@Param("order")String order, @Param("sort")String sort);

	/**
	 * 获取商品管理列表
	 *
	 * @param paramMap
	 * @return
	 */
	List<GoodsManage> selectByParamMap(Map<String, Object> paramMap);

}