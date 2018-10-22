package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.ShelfMoveVO;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveDetailVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ShelfMoveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ShelfMove record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ShelfMove record);

    /**
     *
     * @mbg.generated
     */
    ShelfMove selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShelfMove record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShelfMove record);

    List<ShelfMove> selectShelfMovePage(Map<String, Object> map);

    List<ShelfMoveDetailVO> selectGoodsByAdd(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

    List<ShelfMoveDetailVO> selectGoodsByDown(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

    List<ShelfMoveDetailVO> selectGoodsByLock(@Param("enterpriseId") Long enterpriseId, @Param("param") String param, @Param("type") Integer type);

    List<ShelfMoveDetailVO> selectGoodsByOver(@Param("enterpriseId") Long enterpriseId, @Param("param") String param, @Param("zero") Date zero);

    List<ShelfMoveVO> selectFatherMoveReport(Map<String, Object> map);

    List<ShelfMoveVO> selectSonMoveReport(Map<String, Object> map);

    BigDecimal selectFatherQuantity(Map<String, Object> map);

    BigDecimal selectSonQuantity(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);

    Integer selectFatherMoveTotalRecord(Map<String, Object> map);

    Integer selectSonMoveTotalRecord(Map<String, Object> map);
}