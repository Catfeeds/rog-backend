package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.StockLockRecord;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockOccupyPageVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockLockRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StockLockRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StockLockRecord record);

    /**
     *
     * @mbg.generated
     */
    StockLockRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StockLockRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StockLockRecord record);

    /**
     *
     * 获取库存锁定列表
     * @param paramMap
     * @return
     */
    List<StockLockRecord> selectByParamMap(Map<String, Object> paramMap);

    List<StockLockRecordVO> selectStockLok(Map<String, Object> paramMap);

    List<StockOccupyPageVO> selectFatherStockOccupy(Map<String, Object> map);

    List<StockOccupyPageVO> selectSonStockOccupy(Map<String, Object> map);

    List<StockLockRecord> selectAllLockRecord();

    List<StockLockRecordVO> selectStockLockByCode(String code);

    StockLockRecord selectByGoodsIdAndLotIdAndShelfId(@Param("baseOrderId")Long baseOrderId, @Param("goodsId")Long goodsId, @Param("lotId")Long lotId, @Param("shelfId")Long shelfId, @Param("enterpriseId")Long enterpriseId, @Param("baseOrderCode")String baseOrderCode);

    List<StockShelfVO> selectByGoodsIdAndLotId(@Param("baseOrderId")Long baseOrderId, @Param("goodsId")Long goodsId, @Param("lotId")Long lotId, @Param("enterpriseId")Long enterpriseId);

    List<StockLockRecord> selectByLockId(@Param("baseOrderId")Long baseOrderId, @Param("baseOrderCode")String baseOrderCode);

    Integer selectFatherTotalRecord(Map<String, Object> map);

    Integer selectSonTotalRecord(Map<String, Object> map);

	List<StockLockRecordVO> selectLockVOByParamMap(
			Map<String, Object> paramMap);
}