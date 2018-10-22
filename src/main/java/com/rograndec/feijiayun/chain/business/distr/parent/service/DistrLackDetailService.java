package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrLackDetailVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrLackDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: DistrLackDetailService   
 * @Description:  总部-配货出库-缺配单明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:59:05
 */
public interface DistrLackDetailService {
	
	
	List<DistrLackDetailVO> getDistrLackDetailData(UserVO userVO) throws Exception;
	
	int save(DistrLackDetailSaveOrupdateVO distrLackDetail, UserVO userVO) throws Exception;
	
	int update(DistrLackDetailSaveOrupdateVO distrLackDetail, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
