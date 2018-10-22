package com.rograndec.feijiayun.chain.business.purchase.ret.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PurchaseReturnMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturn record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturn record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturn selectByPrimaryKey(Long id);

    List<PurchaseReturn> selectByParam(Map<String,Object> map);
    
    PurchaseReturnTotalShowVO selectCountByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturn record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturn record);

    /**
     * 分页查询购进退出的主信息
     * @param paramVO
     * @return
     */
    List<ResponsePurchaseReturnVO> getPurchaseReturnVOByPage(RequestPurchaseReturnParamVO paramVO);

    /**
     * 分页查询购进退出的总数
     * @param paramVO
     * @return
     */
    Integer getCountPurchaseReturnVOByPage(RequestPurchaseReturnParamVO paramVO);

    /**
     * 根据id 查询购进出库明细
     * @param id
     * @return
     */
    ResponsePurchaseReturnDetailVO getPurchaseReturnDetail(Long id);

    void updateReturnStatus(@Param("id")Long id,@Param("status") Integer status);

    /**
     * 购进退出查看商品列表
     * @param param
     * @param enterpriseId
     * @return
     */
    List<PurchaseReturnGoodsVO> getGoodsInfoByPurchaseReturn(@Param("param")String param, @Param("enterpriseId")Long enterpriseId, @Param("ownerId")Long ownerId, @Param("managementMode")Integer managementMode);

    Map<String,String> getOrderInfoByReturnId(@Param("enterpriseId")Long enterpriseId,@Param("returnId")Long returnId);

    BigDecimal getQuantityTotal(@Param("enterpriseId")Long enterpriseId, @Param("inStorageId")Long inStorageId);
    
    List<Goods> selectGoodsEnterpriseIds(List<Long> list);

    /**
    *
    * 根据采购入库单id获取采购单的id
    */
   Long getPurchaseOrderId(Long id);

}