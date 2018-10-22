package com.rograndec.feijiayun.chain.inf.pos.syncdata.dao;

import java.util.List;
import java.util.Map;

public interface POSDataSyncMapper {
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getEnterprise(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getEnterpriseBusiness(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getGoods(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getPriceOrderDetail(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getIncompatibilityGoodsOne(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getSpecialGoods(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getSpecialPriceStrategy(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getSpecialPriceGoods(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getLotNumber(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getStock(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getWarehouseShelf(Map<String, Object> param) throws Exception;
	List<Map<String, Object>> getWarehouseCargoArea(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getMemberCardType(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getMemberInfo(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getPrescriptionRegister(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getPrescriptionRegisterDetail(Map<String, Object> param) throws Exception;
	
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
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getPrescriptionRegisterShelf(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getSpecialRegister(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getSpecialRegisterDetail(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getSpecialRegisterShelf(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 获取零售管理-签章
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年2月26日 上午9:58:29 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getPrescriptionSignature(Map<String, Object> param) throws Exception;

	
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
	List<Map<String, Object>> getUser(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getUserAdministration(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getUserPosition(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosition(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosSet(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosPayType(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosBank(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosTeam(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosClerk(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosPayee(Map<String, Object> param) throws Exception;
	
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
	List<Map<String, Object>> getPosPayeeAuth(Map<String, Object> param) throws Exception;

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
	List<Map<String, Object>> getSysRole(Map<String, Object> param);
	
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
	List<Map<String, Object>> getUserRole(Map<String, Object> param);
	
	/**
	 * 
	 * @Description: 统计商品、价格清单、库存的总数量
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月12日 下午3:06:47 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Integer
	 */
	Integer countGoods(Map<String, Object> param);
	Integer countPriceOrderDetail(Map<String, Object> param);
	Integer countStock(Map<String, Object> param);
	
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
	List<Map<String, Object>> getGoodsBusiness(Map<String, Object> param);
	List<Map<String, Object>> getQualitySet(Map<String, Object> param);
	
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
	List<Map<String, Object>> getPharmacySet(Map<String, Object> param);
	
	
	/**
	 * 
	 * @Description: 根据表名获取数据的总数
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月24日 下午7:17:50 
	 * @param param
	 * @return 
	 * @return Integer
	 */
	@Deprecated
	Long countByTableName(Map<String, Object> param);

	
}
