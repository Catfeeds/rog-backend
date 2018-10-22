package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutShelfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: DistrOutShelfService   
 * @Description:  总部-配货出库-配货出库货位明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:58:23
 */
public interface DistrOutShelfService {
	
	
	List<DistrOutShelfVO> getDistrOutShelfData(UserVO userVO) throws Exception;
	
	int save(DistrOutShelfSaveOrupdateVO distrOutShelf, UserVO userVO) throws Exception;
	
	int update(DistrOutShelfSaveOrupdateVO distrOutShelf, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
