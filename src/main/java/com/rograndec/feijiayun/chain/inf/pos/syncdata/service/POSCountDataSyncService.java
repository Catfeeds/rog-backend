package com.rograndec.feijiayun.chain.inf.pos.syncdata.service;

import java.util.Map;

/**
 * 
 * @ClassName: POSCountDataSyncService   
 * @Description: POS同步总数
 * @author yuting.li
 * @version 1.0 
 * @date 2018年2月26日 下午3:13:55
 */
public interface POSCountDataSyncService {
	
	/**
	 * 
	 * @Description: 获取企业数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:52:58 
	 * @param id
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countEnterprise(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 同步企业业务数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月6日 下午1:43:03 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countEnterpriseBusiness(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取商品
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:54:34 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countGoods(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取商品价格清单
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:55:32 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countPriceOrderDetail(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取配伍禁忌商品
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:56:26 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countIncompatibilityGoodsOne(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取专管药品
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:57:09 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countSpecialGoods(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取特价设置商品
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:57:42 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countSpecialPriceStrategy(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取特价商品
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:58:25 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countSpecialPriceGoods(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取批号
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午4:59:04 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countLotNumber(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取库存
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:00:12 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countStock(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 货位信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月19日 下午8:35:09 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countWarehouseShelf(Map<String, Object> param) throws Exception;
	Long countWarehouseCargoArea(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取会员卡类型
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:00:46 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countMemberCardType(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取会员信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:01:47 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countMemberInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取处方登记
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:02:25 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countPrescriptionRegister(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取处方登记单品种明细
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:03:02 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countPrescriptionRegisterDetail(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取处方登记单货位明细
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:03:43 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countPrescriptionRegisterShelf(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取专管登记
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:11:24 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countSpecialRegister(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取专管登记单品种明细
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:14:47 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countSpecialRegisterDetail(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取专管登记单货位明细
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:16:33 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countSpecialRegisterShelf(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取零售管理-签章
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年2月26日 上午10:27:18 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Long
	 */
	Long countPrescriptionSignature(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取用户
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:16:58 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countUser(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取用户的信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:17:27 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countUserAdministration(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取用户岗位
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:18:02 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countUserPosition(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取系统设置-岗位
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:18:37 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosition(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取pos设置
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:19:06 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosSet(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取零售管理-POS管理-支付方式
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:19:41 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosPayType(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取开户银行
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:20:08 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosBank(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取POS管理-班组
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:20:47 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosTeam(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取POS管理-营业人员
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:21:19 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosClerk(Map<String, Object> param) throws Exception;
	
    /**
     * 
     * @Description: 获取POS管理-收款人员
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月11日 下午5:21:54 
     * 
     * @param param
     * @return
     * @throws Exception 
     * @return List<Map<String,Object>>
     */
	Long countPosPayee(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取POS管理-款员权限
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午5:22:34 
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPosPayeeAuth(Map<String, Object> param) throws Exception;

	/**
	 * 
	 * @Description:获取角色
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月12日 上午11:37:23 
	 * @param param
	 * @return 
	 * @return List<Map<String,Object>>
	 */
	Long countSysRole(Map<String, Object> param);
	
	/**
	 * 
	 * @Description: 获取用户角色
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月12日 上午11:37:37 
	 * @param param
	 * @return 
	 * @return List<Map<String,Object>>
	 */
	Long countUserRole(Map<String, Object> param);
	
	/**
	 * 
	 * @Description: 业务属性
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月14日 上午10:47:18 
	 * @param param
	 * @return 
	 * @return List<Map<String,Object>>
	 */
	Long countGoodsBusiness(Map<String, Object> param);
	Long countQualitySet(Map<String, Object> param);
	
	/**
	 * 
	 * @Description: 药学设置接口
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月14日 下午4:21:34 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	Long countPharmacySet(Map<String, Object> param);
	
	


	
}
