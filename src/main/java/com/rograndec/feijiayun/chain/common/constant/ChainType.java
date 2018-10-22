/**    
 * @Title: ChainType.java  
 * @Package com.rograndec.feijiayun.chain.common.constant  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author lei.su lei.su@rograndec.com    
 * @date 2017年8月22日 下午8:55:26  
 * @version V1.0    
 */
package com.rograndec.feijiayun.chain.common.constant;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * @ClassName: ChainType  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author lei.su lei.su@rograndec.com  
 * @date 2017年8月22日 下午8:55:26  
 *    
 */
public enum ChainType {

	Headquarters(0,"总部"),
	Selfoperatedshop(1,"自营店"),
	Division(2,"加盟店");
	
	private int code;
    private String name;
    
    private ChainType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setType(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(Integer code) {
		if(code == null){
			return null;
		}
        for (ChainType c : ChainType.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }


	public static Long getHeadEnterpriseId(UserVO userVO) {
    	if(ChainType.Headquarters.getCode() == userVO.getChainType()){
    		return userVO.getEnterpriseId();
		} else {
    		return userVO.getParentId();
		}
	}

	public static Long getHeadEnterpriseIdAndDivisionEID(UserVO userVO) {
		if(ChainType.Headquarters.getCode() == userVO.getChainType()){
			return userVO.getEnterpriseId();
		} else if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){
			return userVO.getParentId();
		} else if(ChainType.Division.getCode() == userVO.getChainType()){
			return userVO.getEnterpriseId();
		} else {
			throw new BusinessException("会话失效，请重新登录！");
		}

	}

	public static Integer getRgtCode(Integer code) {

		if(code == ChainType.Headquarters.getCode()){
			return 2;//对应融贯通-连锁药店总店
		}
		if(code == ChainType.Selfoperatedshop.getCode()){
			return 3;//对应融贯通-自营店
		}
		if(code == ChainType.Division.getCode()){
			return 4;//对应融贯通-连锁加盟店
		}
		return null;
	}

	public static Integer getSaasCode(Integer code) {

		if(code == 2){
			return ChainType.Headquarters.getCode();//对应融贯通-连锁药店总店
		}
		if(code == 3){
			return ChainType.Selfoperatedshop.getCode();//对应融贯通-自营店
		}
		if(code == 4){
			return ChainType.Selfoperatedshop.getCode();//对应融贯通-连锁加盟店
		}
		return null;
	}

}
