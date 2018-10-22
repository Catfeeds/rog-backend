package com.rograndec.feijiayun.chain.business.retail.saleflow.dao;

import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.SaleDetailForPrescrVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderParamVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleDetail record);

    /**
     *
     * @mbg.generated
     */
    SaleDetail selectByPrimaryKey(Long id);

    List<SaleDetail> selectByIds(List<Long> list);

    List<SaleDetail> selectById(Map<String,Object> map);

    List<SaleDetail> selectUserByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleDetail record);

    int updateRoyaltyFlag(SaleDetail record);


    /**
     * 查询销售详情
     * @param enterpriseId
     * @param saleId
     * @return
     */
    List<SaleDetailForPrescrVO> getSaleDetailForPrescrBySaleId(@Param("enterpriseId")Long enterpriseId, @Param("saleId")Long saleId);
    /**
     *                       
     * <根据销售单id查询所有专管药品明细>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/26 14:05
     */
    List<SaleDetail> getSaleDetailBySaleId(Long id);

	List<POSOrderDetailVO> selectOrderDetailPageDataByParam(
			@Param("param")POSOrderParamVO param, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, @Param("saleType")Integer saleType);

	Long queryOrderDetailCountByParam(@Param("param")POSOrderParamVO param, @Param("enterpriseId")Long enterpriseId, 
			@Param("saleType")Integer saleType);

    int selectHasClerk(Long id);
    
    int getHasClerk(Long id);
    
    String getHasCargo(@Param("id") Long id, @Param("shelfId")Long shelfId);

    List<SaleDetail> selectByShiftId(Long shiftId);
}