package com.rograndec.feijiayun.chain.business.retail.payment.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.payment.entity.SalePayment;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersAlreadyPageVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreAlreadyPageVO;

public interface SalePaymentMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SalePayment record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SalePayment record);

    /**
     *
     * @mbg.generated
     */
    SalePayment selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SalePayment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SalePayment record);

    /**
     * @Description: TODO根据条件查询总数量
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月20日 下午7:35:08 
     * @param startDate
     * @param endDate
     * @param salePaymentCode
     * @param createrName
     * @param paymentManName
     * @param enterpriseId
     * @return 
     * @return Long
     */
	Long queryAlreadyPaymentByParams(@Param("startDate")Date startDate, @Param("endDate")Date endDate,
			@Param("salePaymentCode")String salePaymentCode, @Param("createrName")String createrName, 
			@Param("paymentManName")String paymentManName, @Param("enterpriseId")Long enterpriseId);

	/**
	 * @Description: TODO根据条件查询数据
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月20日 下午7:35:39 
	 * @param start
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param salePaymentCode
	 * @param createrName
	 * @param paymentManName
	 * @param order
	 * @param sort
	 * @param enterpriseId
	 * @return 
	 * @return List<StoreAlreadyPageVO>
	 */
	List<StoreAlreadyPageVO> selectAlreadyPaymentByParams(@Param("start")int start,
			@Param("pageSize")Integer pageSize, @Param("startDate")Date startDate, @Param("endDate")Date endDate,
			@Param("salePaymentCode")String salePaymentCode, @Param("createrName")String createrName, 
			@Param("paymentManName")String paymentManName,@Param("order")String order,
			@Param("sort")String sort, @Param("enterpriseId")Long enterpriseId);

	/**
	 * @Description: TODO查询合计
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月20日 下午8:05:26 
	 * @param startDate
	 * @param endDate
	 * @param salePaymentCode
	 * @param createrName
	 * @param paymentManName
	 * @param enterpriseId
	 * @return 
	 * @return StoreAlreadyPageVO
	 */
	StoreAlreadyPageVO querySumAlreadyPaymentByParams(@Param("startDate")Date startDate, @Param("endDate")Date endDate,
			@Param("salePaymentCode")String salePaymentCode, @Param("createrName")String createrName, 
			@Param("paymentManName")String paymentManName, @Param("enterpriseId")Long enterpriseId);

	/**
	 * @Description: TODO根据条件查询总数量
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月22日 下午1:49:40 
	 * @param startDate
	 * @param endDate
	 * @param chainType
	 * @param storeCode
	 * @param storeName
	 * @param salePaymentCode
	 * @param createrName
	 * @param paymentManName
	 * @param enterpriseId
	 * @return 
	 * @return Long
	 */
	Long queryHeadquartersAlreadyPaymentByParams(@Param("startDate")Date startDate, @Param("endDate")Date endDate,
			@Param("chainType")Integer chainType, @Param("storeCode")String storeCode, 
			@Param("storeName")String storeName, @Param("salePaymentCode")String salePaymentCode, 
			@Param("createrName")String createrName, @Param("paymentManName")String paymentManName,
			@Param("enterpriseId")Long enterpriseId);

	/**
	 * @Description: TODO根据条件查询
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月22日 下午1:50:16 
	 * @param start
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param chainType
	 * @param storeCode
	 * @param storeName
	 * @param salePaymentCode
	 * @param createrName
	 * @param paymentManName
	 * @param order
	 * @param sort
	 * @param enterpriseId
	 * @return 
	 * @return List<HeadquartersAlreadyPageVO>
	 */
	List<HeadquartersAlreadyPageVO> selectHeadquartersAlreadyPaymentByParams(
			@Param("start")int start, @Param("pageSize")Integer pageSize, 
			@Param("startDate")Date startDate, @Param("endDate")Date endDate,
			@Param("chainType")Integer chainType, @Param("storeCode")String storeCode, 
			@Param("storeName")String storeName, @Param("salePaymentCode")String salePaymentCode, 
			@Param("createrName")String createrName, @Param("paymentManName")String paymentManName,
			@Param("order")String order, @Param("sort")String sort, 
			@Param("enterpriseId")Long enterpriseId);
}