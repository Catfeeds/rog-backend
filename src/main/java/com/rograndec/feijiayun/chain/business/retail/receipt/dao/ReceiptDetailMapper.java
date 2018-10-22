package com.rograndec.feijiayun.chain.business.retail.receipt.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.retail.receipt.entity.ReceiptDetail;
import org.apache.ibatis.annotations.Param;

public interface ReceiptDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceiptDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceiptDetail record);

    /**
     *
     * @mbg.generated
     */
    ReceiptDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceiptDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceiptDetail record);

    /**
     * @Description: TODO根据交接班ID查询
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月21日 下午4:23:25 
     * @param string
     * @return 
     * @return List<Map>
     */
	List<Map> selectPayTypeAmountByShiftId(String id);

	/**
     * @Description: TODO根据销售ID查询销售细表，按支付方式分组获取金额
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月21日 下午7:50:00 
     * @param id
     * @return 
     * @return List<Map>
     */
	List<Map> selectPayTypeAmountBySaleId(String id);

    List<Map> selectPayTypeAmountByShiftDtlId(String id);

    List<ReceiptDetail> selectByEnterpriseIdByPayTypeId(@Param("enterpriseId") Long enterpriseId, @Param("payTypeId") Long payTypeId);

    int selectHasBank(Long id);
}