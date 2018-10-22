package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecordDetail;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockInOutVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.GoodsDtlReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.RequestParamGoodsDtlReportVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.CostInfoPostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InOutRecordDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(InOutRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(InOutRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    InOutRecordDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InOutRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InOutRecordDetail record);

    List<GoodsDtlReportVO> getGoodsDtlReportList(@Param("enterpriseId")Long enterpriseId,@Param("paramVO")RequestParamGoodsDtlReportVO paramVO);

    List<InOutRecordDetailReportVO> getInOutRecordList(Map<String,Object> param);



    List<InOutRecordDetail> getInOutDetailByParam(@Param("enterpriseId")Long enterpriseId,@Param("lotId")Long lotId,
                                                  @Param("goodsId")Long goodsId,@Param("shelfId")Long shelfId,
                                                  @Param("direction")Integer direction,
                                                  @Param("sort")String sort);

    CostInfoPostVO getCostTotalQuantityAndAmount(@Param("enterpriseId")Long enterpriseId, @Param("lotId")Long lotId,
                                                 @Param("goodsId")Long goodsId, @Param("shelfId")Long shelfId,
                                                 @Param("direction")Integer direction);

    /**
     * 获取 总数
     * @param param
     * @return
     */
    Integer getInOutDetailCountByParam(Map<String, Object> param);

    /**
     * 查询出入库 总和  方向（0-入库；1-出库）
     * @param param
     * @return
     */
    CostInfoPostVO getInOutDetailTotalByParam(Map<String, Object> param);

    /**
     * 查询最后一条记录，用于计算结余
     * @param param
     * @return
     */
    InOutRecordDetailReportVO selectLast(Map<String, Object> param);

    /**
     * 获取财务明细账报表
     * @param stockInOutReqVO
     * @return
     */
    List<StockInOutVO> getFinanceInOutReport(StockInOutReqVO stockInOutReqVO);

    /**
     * 获取财务明细账报表 总数
     * @param stockInOutReqVO
     * @return
     */
    Integer getFinanceInOutReportCount(StockInOutReqVO stockInOutReqVO);


    /**
    * @Description: 财务库存报表: 根据企业IDS ,获取当前时间范围内有出入库明细的所有商品
    * @return:
    * @Author: dongyang.du
    * @Date: 14/01/2018
    */
    List<Goods> getGoodsListByEnterpriseList(Map<String,Object> param);

    List<InOutRecordDetail> getInOutRecordListByPurchaseIn(@Param("supplierId") Long supplierId, @Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId, @Param("lotId") Long lotNumberId, @Param("type") Integer type);

    /** 
    * @Description: 计算 当前页 之前的该商品的结余
    * @return:  
    * @Author: dongyang.du
    * @Date: 25/01/2018
     * @param param
    */ 
    CostInfoPostVO getBeforePageAmountQuantity(StockInOutReqVO param);
}