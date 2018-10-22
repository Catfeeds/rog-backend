package com.rograndec.feijiayun.chain.business.retail.pos.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;



 /**
 * 
 * @ClassName: PosSalePeriodService   
 * @Description:  零售管理-POS管理-销售时段-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-22 13:13:50
 */	
public interface PosSalePeriodService {
	
	
	List<PosSalePeriodVO> getPosSalePeriodData(UserVO userVO) throws Exception;
	
	int save(PosSalePeriodSaveOrupdateVO posSalePeriod,UserVO userVO) throws Exception;
	
	int update(PosSalePeriodSaveOrupdateVO posSalePeriod,UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
    
    Long findByCode(String code,Long enterpriseId) throws Exception;
    
    /**
     * 
     * @Description: 时间验证
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月24日 下午4:37:30 
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @return 
     * @return String
     */
    String checkTime(Long enterpriseId,String startTime,String endTime);
	

}
