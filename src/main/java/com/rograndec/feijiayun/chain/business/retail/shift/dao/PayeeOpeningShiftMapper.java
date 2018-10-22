package com.rograndec.feijiayun.chain.business.retail.shift.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.PaymentListVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosPayeeVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosStandVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosTeamVO;

public interface PayeeOpeningShiftMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PayeeOpeningShift record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PayeeOpeningShift record);

    /**
     *
     * @mbg.generated
     */
    PayeeOpeningShift selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PayeeOpeningShift record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PayeeOpeningShift record);

    /**
     * @Description: TODO查找此交接班包含款台
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月20日 下午4:07:48 
     * @param shiftId
     * @return 
     * @return List<Map<String,String>>
     */
	List<Map<String, String>> selectStandCodeByShiftId(Long shiftId);

	/**
     * @Description: TODO查找此交接班包含收款明细
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月20日 下午4:07:48 
     * @param shiftId
     * @return 
     * @return List<Map<String,String>>
     */
	List<PaymentListVO> selectReceiptDetailByShiftId(@Param("shiftId")Long shiftId, @Param("enterpriseId")Long enterpriseId);

	/**
	 * @Description: TODO根据条件查询待缴款数据
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月21日 下午2:59:33 
	 * @param condition
	 * @return 
	 * @return List<Map>
	 */
	List<Map> selectStayPaymentPageByParams(StoreStaySearchConditionVO condition);

	/**
	 * @Description: TODO根据条件查询待缴款数量
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月21日 下午2:59:33 
	 * @param condition
	 * @return 
	 * @return List<Map>
	 */
	Long queryStayPaymentCountByParams(StoreStaySearchConditionVO condition);

	/**
	 * @Description: TODO根据条件查询所有门店待缴款数量
	 * @author liuqun
	 * @version 1.0
	 * @date 2017年9月21日 下午2:59:33
	 * @param condition
	 * @return
	 * @return List<Map>
	 */
	Long queryHeadquartersStayPaymentCountByParams(
			HeadquartersStaySearchConditionVO condition);

	/**
	 * @Description: TODO根据条件查询所有门店待缴款数据
	 * @author liuqun
	 * @version 1.0
	 * @date 2017年9月21日 下午2:59:33
	 * @param condition
	 * @return
	 * @return List<Map>
	 */
	List<Map> selectHeadquartersStayPaymentPageByParams(
			HeadquartersStaySearchConditionVO condition);


    List<SelectPosStandVO> selectPosStand(@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType);

	List<SelectPosTeamVO> selectPosTeam(@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType);

	List<SelectPosPayeeVO> selectPosPayee(@Param("enterpriseId")Long enterpriseId);

	/**
	 * @Description: TODO根据ID修改交接班明细状态
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月23日 下午3:08:04 
	 * @param id 
	 * @return void
	 */
	void updatePaymentFlagByShiftId(Long id);


    List<Map<String,Object>> queryCountByPayeeOpeningShiftStoreParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")Integer pageSize,
											 @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("standCode")String standCode,
											 @Param("teamId")Long teamId, @Param("payeeName")String payeeName);

	List<Map> payeeOpeningShiftStoreVoParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")Integer pageSize,
																  @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("orderName")String orderName,
																  @Param("orderType")String orderType, @Param("standCode")String standCode, @Param("teamId")Long teamId, @Param("payeeName")String payeeName);

    Long queryCountByPayeeOpeningShiftSelectStoreParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")Integer pageSize, @Param("shiftId")Long shiftId);

	List<Map> payeeOpeningShiftStoreSelectVoParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("shiftId")Long shiftId);


	List<Map<String,Object>> queryCountByPayeeOpeningShiftParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")Integer pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,
											 @Param("standCode")String standCode, @Param("teamId")Long teamId, @Param("payeeName")String payeeName, @Param("storeType")String storeType, @Param("storeCode")String storeCode, @Param("storeName")String storeName);

	List<Map> payeeOpeningShiftVoParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")Integer pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,
										@Param("orderName")String orderName, @Param("orderType")String orderType, @Param("standCode")String standCode, @Param("teamId")Long teamId, @Param("payeeName")String payeeName,
										@Param("storeType")String storeType, @Param("storeCode")String storeCode, @Param("storeName")String storeName);

    List<Map> payeeOpeningShiftSelectVoParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")Integer pageSize,
											  @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("shiftId")Long shiftId);

    List<PayeeOpeningShift> selectByEnterpriseIdByPayeeId(@Param("enterpriseId") Long enterpriseId, @Param("payeeId") Long payeeId);

    void updateDailySettleFlagByShiftId(@Param("id") Long shiftId, @Param("dailySettleFlag") Integer dailySettleFlag);

	List<PayeeOpeningShift> selectByEnterpriseId(Long enterpriseId);

    List<PayeeOpeningShift> selectByIds(List<Long> ids);
}