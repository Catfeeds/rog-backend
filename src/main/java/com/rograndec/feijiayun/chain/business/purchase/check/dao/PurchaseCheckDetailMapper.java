package com.rograndec.feijiayun.chain.business.purchase.check.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckDetail;

public interface PurchaseCheckDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    PurchaseCheckDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseCheckDetail record);

    /**
     * @Description: TODO根据验收单ID查询验收品种
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月14日 上午11:50:52 
     * @param id
     * @return 
     * @return List<PurchaseCheckDetail>
     */
	List<PurchaseCheckDetail> selectByCheckId(Long id);

	void updateStatusFinishedByCheckId(@Param("status")Integer status, @Param("id")Long id);

    int batchInsert(List<PurchaseCheckDetail> purchaseCheckDetails);

    PurchaseCheckDetail selectByGoodsid(@Param("enterpriseId")Long enterpriseId,@Param("goodsId")Long goodsid, @Param("id")Long id);
}