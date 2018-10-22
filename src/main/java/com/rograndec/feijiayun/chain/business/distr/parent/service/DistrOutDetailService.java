package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutDetailVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: DistrOutDetailService   
 * @Description:  总部-配货出库-配货出库明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:58:09
 */
public interface DistrOutDetailService {
	
	
	List<DistrOutDetailVO> getDistrOutDetailData(UserVO userVO) throws Exception;
	
	int save(DistrOutDetailSaveOrupdateVO distrOutDetail, UserVO userVO) throws Exception;
	
	int update(DistrOutDetailSaveOrupdateVO distrOutDetail, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
