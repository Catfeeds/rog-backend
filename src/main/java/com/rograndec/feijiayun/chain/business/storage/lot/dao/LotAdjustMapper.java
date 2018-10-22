package com.rograndec.feijiayun.chain.business.storage.lot.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LotAdjustReportVO;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjust;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.GoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotNumberVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotStockVO;

import io.swagger.annotations.ApiModelProperty;

public interface LotAdjustMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(LotAdjust record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(LotAdjust record);

    /**
     *
     * @mbg.generated
     */
    LotAdjust selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotAdjust record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotAdjust record);
    
    /**
     * 获取商品列表包含批号（主要是根据批号表）
     * */
    List<GoodsLotNumberVO> selectGoodsList(Map map);
    /**
     * 获取调整单详情
     * */
    LotAdjustVO selectById(Long id);
    /**
     * 获取分页列表
     * */
    List<LotAdjustVO> selectLotAdjustList(Map map);
    
    Long selectCount(Map map);
    
    List<LotStockVO> selectStockVOByParam(Map map);
    
    /**
     * 报表查询
     * */
    List<LotAdjustReportVO> selectReportList(Map map);
    
    /**
     * 批号列表查询
     * */
    List<LotNumberVO> selectLotNumberList(Map map);
}