package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLackDetail;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;

import java.util.List;

public interface DistrLackDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrLackDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrLackDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrLackDetail record);


	List<DistrLackDetail> getLackDetailByLackId(Long id);

    /**
     * 查询缺配单明细列表(只返回未清数量大于0的明细)
     * @param lackId
     * @return
     */
    List<DistrLackDetail> getDistrLackDetailByLackId(Long lackId);

    /**
     * 根据缺配单ID批量查询缺配单明细列表,根据商品ID分组,将缺配数量合并
     * @author dongyang.du
     * @remark 采购计划调用缺配单
     * @param ids
     */
    List<PurchasePlanGoodsVO> getByLackIdGroupByGoodsId(String[] ids);
}