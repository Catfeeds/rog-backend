package com.rograndec.feijiayun.chain.business.retail.pos.service;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSetVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


 /**
 * 
 * @ClassName: PosSetService   
 * @Description:  零售管理-POS管理-系统设置-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-18 17:31:49
 */
public interface PosSetService {
	
	/**
	 * 
	 * @Description: 获取各门店POS使用统一设置
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月18日 下午5:56:33 
	 * @param enterpriseId
	 * @return 
	 * @return PosSetVO
	 */
	PosSetVO getPosSetData(Long enterpriseId) throws Exception;
	
	/**
	 * 
	 * @Description: 更新or修改参数设置
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月18日 下午6:00:26 
	 * @param posSetVO
	 * 
	 */
	void saveORupdate(PosSetVO posSetVO,UserVO loginUser) throws Exception;
	
	
}
