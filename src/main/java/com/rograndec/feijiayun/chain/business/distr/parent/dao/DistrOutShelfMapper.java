package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheckLot2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageGoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.GoodsLotShelfVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceDistrOutShelfVo;

public interface DistrOutShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrOutShelf record);

    /**
     *
     * @mbg.generated
     */
    DistrOutShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrOutShelf record);
    /**
     *
     * <根据明细id获取货位信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/8 16:47
     */
    List<DistrOutShelf> getDistrOutShelfList(Long id);

    List<DistrOutShelf> getDistrOutShelfListByOutId(@Param("outId") Long outId,@Param("enterpriseId") Long enterpriseId);

    List<DistrInCheckLot2DetailVO> getDistrOutListByOutIdGID(@Param("outId") Long outId, @Param("goodsId")Long goodsId);

    List<DistrInstorageGoodsLotNumberVO> getGoodsLotInfoByDtlId(@Param("enterpriseId")Long enterpriseId, @Param("outDtlId")Long outDtlId, @Param("goodsId")Long goodsId);

    void deleteByOutId(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

    List<GoodsLotShelfVO> selectByGoodsIdAndEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    void updateByDtlId(@Param("id")Long id, @Param("status")int status, @Param("enterpriseId")Long enterpriseId);
    /**
     * 根据加盟店和商品id查询相应的货位信息
     * @param enterpriseId
     * @param goodsId
     * @return
     */
    List<InvoiceDistrOutShelfVo> getInvoiceDistrOutShelfList(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId);

    List<DistrOutShelf> distrOutShelfToVerificationByMap(Map<String, Object> map);


    List<DistrOutShelf> selectDistrOutShelfToWriteOffByMap(Map<String, Object> map);
}