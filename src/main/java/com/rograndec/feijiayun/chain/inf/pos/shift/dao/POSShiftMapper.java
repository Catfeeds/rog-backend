package com.rograndec.feijiayun.chain.inf.pos.shift.dao;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftVO;

/**
 * 
 * @ClassName: POSShiftMapper   
 * @Description: POS 交接班信息
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月7日 下午3:34:24
 */
public interface POSShiftMapper {
	
	/**
	 * 
	 * @Description: 获取交班数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月7日 下午4:50:43 
	 * @param enterpriseId
	 * @return 
	 * @return POSShfitVO
	 */
	POSShiftVO getShiftByEnterpriseId(Long enterpriseId);
	
	/**
	 * 
	 * @Description: 交班校验是否有重复单号
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月8日 下午1:19:19 
	 * @param enterpriseId
	 * @param baseOrderCode 单号
	 * @param baseOrderId
	 * @return 
	 * @return Long
	 */
	Long checkDetailOrder(@Param("docId")Long docId,@Param("baseOrderCode")String baseOrderCode,@Param("enterpriseId")Long enterpriseId);
	
	/**
	 * 
	 * @Description: 校验是否有交班数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月27日 上午11:29:55 
	 * @param enterpriseId
	 * @param payeeId
	 * @param openingTime
	 * @return 
	 * @return Long
	 */
	Long checkShift(@Param("enterpriseId")Long enterpriseId,@Param("payeeId")Long payeeId,@Param("openingTime")String openingTime);

}
