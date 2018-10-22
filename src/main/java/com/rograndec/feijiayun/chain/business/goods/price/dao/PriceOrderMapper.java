package com.rograndec.feijiayun.chain.business.goods.price.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceOrderReturnVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;

public interface PriceOrderMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PriceOrder record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PriceOrder record);

    /**
     *
     * @mbg.generated
     */
    PriceOrder selectByPrimaryKey(Long id);

    List<PriceOrder> selectByParentOrderId(Long parentOrderId);

    List<PriceOrder> selectByEnterpriseAndCode(@Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId, @Param("code") String code);

    List<PriceOrder> selectByEnterpriseAndName(@Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId, @Param("name") String name);

    List<PriceOrder> selectByParentOrder(Long id);

    List<PriceOrder> selectByIds(List<Long> list);

    List<PriceOrder> selectByQueryType(Map map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PriceOrder record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PriceOrder record);

    /**
     * 获取该企业下所有的价格清单
     * @param enterpriseId
     * @return
     */
    PriceOrder getPriceOrderByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId, @Param("status") Integer status);

    /**
     * @Description: TODO查询企业下可用价格清单
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月7日 下午4:36:53 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<SelectBeanWithCode> selectStorePriceOrderSelectBeanByEnterpriseId(
			Long enterpriseId);

	List<QueryBean> selectStorePriceOrderIdAndNames(
			@Param("enterpriseId") Long enterpriseId,@Param("types") List<Integer> types);

    /**
     *
     * 根据编码、企业ID、上级企业ID获取价格清单信息
     * @param enterpriseId
     * @param parentId
     * @return
     */
    PriceOrder selectByCodeAndEnterpriceIdAndParentId(@Param("sysType") Integer sysType,@Param("enterpriseId") Long enterpriseId,@Param("parentId") Long parentId);

    List<PriceOrder> selectByCodeAndEnterpriceIdAndParentId2List(@Param("sysType") Integer sysType,@Param("enterpriseId") Long enterpriseId,@Param("parentId") Long parentId);

    /**
     * 根据价格清单查询企业业务子表
     * @param id
     * @return
     */
    List<EnterpriseBusiness> selectEnterpriseByOrderPriceId(@Param("distrPriceOrderId") Long id);

    /**
     * 根据企业id查询企业业务字表的价格管理信息
     * @param enterpriseId
     * @return
     */
    Integer getEnterpriseBusinessPriceManage(Long enterpriseId);

    List<Long> getPriceDetailByParam(@Param("enterpriseId")Long enterpriseId,@Param("id")Long id,@Param("goodsId")Long goodsId);
    
    /**
     * 根据企业id获取企业价格清单
     * @param id
     * @return
     */
    PriceOrderReturnVO getStorePriceOrderByEnterpriseId(Long id);
    
    /**
     * 根据商品id获取商品的销售定价管理
     * @param list
     * @return
     */
    List<Map<String,Object>> getGoodsBusPriceManage(List<Long> list);
    /**
     * 获取价格管理总部控制的门店的id集合
     * @param id
     * @return
     */
    List<Long> getEnterpriseIdsWithControl(@Param("id") Long id);

    List<QueryBean> getEnterpriseIdAndParentIdWithControl(@Param("id") Long id,@Param("types") List<Integer> types);

    /**
     * 根据价格清单id获取企业价格清单
     * @param id
     * @return
     */
    PriceOrderReturnVO getPriceOrderById(Long id);

    PriceOrder selectByEnterpriseIdAndSysTypeAndPriceType(
			@Param("enterpriseId")Long enterpriseId, @Param("sysType")Integer sysType, @Param("priceType")int priceType);
}