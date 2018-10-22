package com.rograndec.feijiayun.chain.business.purchase.instorage.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrPurchaseInStorageShelfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.GetPurchaseInStorageShelfListVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageDetailForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInstorageShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.GetInStorageParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseInStorageReportPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseInStorageShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseInStorageShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseInStorageShelf record);

    /**
     *
     * @mbg.generated
     */
    PurchaseInStorageShelf selectByPrimaryKey(Long id);

    List<PurchaseInStorageShelf> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseInStorageShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseInStorageShelf record);

    List<PurchaseInStorageShelf> selectByEnterpriseIdAndId(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);

    /**
     * @Description: TODO批量保存
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月16日 上午11:24:35 
     * @param shelfList 
     * @return void
     */
	void batchSave(List<PurchaseInStorageShelf> list);

    List<PurchaseInStorageDetailForReturnVO> getInStorageDetail(GetInStorageParamVO getInStorageParamVO);

    Integer getCountInStorageDetail(GetInStorageParamVO getInStorageParamVO);

    List<PurchaseInStorageReportPageVO> getPurchaseInStorageReportPageList(@Param("paramForListVO")PurchaseInStorageReportListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);

    List<PurchaseInStorageShelf> getPurchaseInStorageShelf(@Param("enterpriseId") Long enterpriseId, @Param("inStorageId") Long inStorageId,
                                                           @Param("goodsId")Long goodsId,@Param("lotNumber")String lotNumber);

    List<PurchaseInStorageShelf> selectByStorageId(Long id);

    List<PurchaseInStorageShelf> selectByStorageIds(List<Long> list);

    List<PurchaseInStorageShelf> selectByStorageIdsAndStatus(@Param("idList") List<Long> idList,@Param("statusList") List<Integer> statusList);

    List<PurchaseInStorageShelf> selectByStorageIdsAndStatusAndParam(@Param("statusList") List<Integer> statusList,@Param("param") String param,@Param("supplierId") Long supplierId);

    List<PurchaseInStorageShelf> selectByDetailIdAndGoods(@Param("dtlId") Long dtlId,@Param("goodsId") Long goodsId, @Param("lotNumber") String lotNumber);

    List<DistrPurchaseInStorageShelfVO> selectByPurchaseInstorageDetailId(Long id);

    Long queryCount(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id, @Param("orderName")String orderName, @Param("orderType")String orderType);

    List<GetPurchaseInStorageShelfListVO> getPurchaseInStorageShelfList(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id, @Param("start")int start, @Param("pageSize")int pageSize, @Param("orderName")String orderName, @Param("orderType")String orderType);

    Integer getPurchaseInStorageReportPageCount(@Param("paramForListVO")PurchaseInStorageReportListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);
}