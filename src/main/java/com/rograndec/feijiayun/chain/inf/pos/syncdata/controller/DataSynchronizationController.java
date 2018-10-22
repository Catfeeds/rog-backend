package com.rograndec.feijiayun.chain.inf.pos.syncdata.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosStand;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.context.SpringUtils;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.constant.POSDataSyncPosSetTableEnum;
import com.rograndec.feijiayun.chain.inf.pos.constant.POSDataSyncTableEnum;
import com.rograndec.feijiayun.chain.inf.pos.log.service.AddPOSLogServiceImpl;
import com.rograndec.feijiayun.chain.inf.pos.syncdata.service.impl.POSDataSyncServiceImpl;
import com.rograndec.feijiayun.chain.inf.pos.syncdata.vo.RequestDataSyncVO;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: DataSynchronizationController   
 * @Description: POS数据同步
 * @author yuting.li
 * @version 1.0 
 * @date 2017年9月28日 下午5:54:48
 */
@Api(value="DataSynchronizationController",description = "POS数据同步")
@RestController
@RequestMapping("/inf/pos/sync")
public class DataSynchronizationController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSynchronizationController.class);
	
	@Autowired
	private SpringUtils springUtils;
	
	@Autowired
	private POSDataSyncServiceImpl pOSDataSyncService;
	
	@Autowired
	private EnterpriseBusinessService enterpriseBusinessService;
	
	@Autowired
	private AddPOSLogServiceImpl addPOSLogServiceImpl;
	
	
	@ApiOperation(value = "POS数据同步", notes = "POS数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/dataSynchronization", method = RequestMethod.POST)
    public Result<List<Map<String, Object>>> dataSynchronization(HttpServletResponse response,HttpSession session,@Valid @RequestBody RequestDataSyncVO dataVO) {
		Long start = System.currentTimeMillis();
		Result<List<Map<String, Object>>> result = new Result<>();
        try {
        	if(null != dataVO.getStartRow() && null != dataVO.getEndRow()) {
        		if(dataVO.getStartRow() <= 0 || dataVO.getEndRow() <= 0){
            		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR,"startRow >= 1,endRow>0");
        			return result;
            	}
        	}
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            List<Map<String, Object>> list = getData(userVO, dataVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
            logger.debug("POS数据同步"+dataVO.getKey()+"接口耗时："+(System.currentTimeMillis() - start)+"ms");
		} catch (Exception e) {
			logger.error("POS数据同步失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value = "POS数据同步总数", notes = "POS数据同步总数 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/countData", method = RequestMethod.POST)
    public Result<Map<String, Object>> countData(HttpSession session,@Valid @RequestBody RequestDataSyncVO dataVO) {
		Long start = System.currentTimeMillis();
		Result<Map<String, Object>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            Map<String, Object> countData = getCountData(userVO, dataVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, countData);
            logger.debug("POS数据同步"+dataVO.getKey()+"总数接口耗时："+(System.currentTimeMillis() - start)+"ms");
		} catch (Exception e) {
			logger.error("POS数据同步失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "POS上传款台数据", notes = "POS上传款台数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/savePosStand", method = RequestMethod.POST)
    public Result savePosStand(HttpSession session,@Valid @RequestBody PosStand dataVO) {
		Result result = new Result<>();
        try {
        	Map<String,String> save = pOSDataSyncService.savePosStand(dataVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, save != null ? save : "无款台数据同步");
		} catch (Exception e) {
			logger.error("POS上传款台失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	/**
	 * 
	 * @Description: 获取统计的总数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月12日 下午3:37:37 
	 * @param userVO
	 * @param dataVO
	 * @return
	 * @throws Exception 
	 * @return Map<String,Object>
	 */
	private Map<String, Object> getCountData(UserVO userVO, RequestDataSyncVO dataVO) throws Exception {
		Map<String, Object> map = new HashMap<>(1);
		Long count = countData(userVO, dataVO);
		if(null != count) {
			map.put("count", count);
		} else {
			map.put("count", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Description: 判断同步数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月11日 下午7:24:23 
	 * @param dataVO
	 * @return 
	 * @return List<Map<String,Object>>
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> getData(UserVO userVO,RequestDataSyncVO dataVO) throws Exception{
		List<Map<String, Object>> list = null;
		String key = dataVO.getKey();//表名
		Object invokeclass = springUtils.getBean("pOSDataSyncService"); // 获取同步接口
		// 全部同步，分页条件，最后同步时间
		if(0 == dataVO.getKind()) {
			for(POSDataSyncTableEnum tenum : POSDataSyncTableEnum.values()) {
				if(key.equals(tenum.getTableCode())) {
					String method = tenum.getTableCode();
					method = "get" + method.replaceAll("saas", "");//去除前缀
					// 利用反射获取要执行的方法
					Method invokeMethod = invokeclass.getClass().getDeclaredMethod(method, Map.class);
					list = (List<Map<String, Object>>) invokeMethod.invoke(invokeclass, getParam(userVO, dataVO,tenum.getTableCode()));
					break;
				}
			}
			// 若定义了无法利用反射方法的，直接调用接口即可
			
		}
		// 根据特殊条件同步
		if(1 == dataVO.getKind()) {
			
		}
		return list;
	}
    

	private Long countData(UserVO userVO,RequestDataSyncVO dataVO) throws Exception{
		Long count = null;
		String key = dataVO.getKey();//表名
		Object invokeclass = springUtils.getBean("pOSCountDataSyncService"); // 获取同步接口
		// 全部同步，分页条件，最后同步时间
		if(0 == dataVO.getKind()) {
			for(POSDataSyncTableEnum tenum : POSDataSyncTableEnum.values()) {
				if(key.equals(tenum.getTableCode())) {
					Map<String, Object> param = getCountParam(userVO, dataVO,tenum.getTableName());
					param.put("tableName", tenum.getTableName());
					String method = tenum.getTableCode();
					method = "count" + method.replaceAll("saas", "");//去除前缀
					// 利用反射获取要执行的方法
					Method invokeMethod = invokeclass.getClass().getDeclaredMethod(method, Map.class);
					count = (Long) invokeMethod.invoke(invokeclass, param);
					//记录日志
					addPOSLogServiceImpl.saveCountDataLog(param, userVO, count);
					break;
				}
			}
		}
		// 根据特殊条件同步
		if(1 == dataVO.getKind()) {
			
		}
		return count;
	}
	
    /**
     * 
     * @Description: 处理参数
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月11日 下午7:15:38 
     * @param user
     * @param dataVO
     * @return 
     * @return Map<String,Object>
     */
	private Map<String, Object> getParam(UserVO user,RequestDataSyncVO dataVO,String method){
		Map<String, Object> param = new HashMap<>(6);
		if(user != null && dataVO != null) {
			param.put("chainType", user.getChainType());
			if(null != user.getEnterpriseId()) {
				param.put("enterpriseId", user.getEnterpriseId());
			}
			if(null != user.getParentId() && 0 != user.getParentId()) {
				param.put("parentId", user.getParentId());
			}
			if(null != dataVO.getEndRow() && null != dataVO.getStartRow()) {
				int start = (dataVO.getStartRow() - 1) * dataVO.getEndRow();
				param.put("startRow", start);
				param.put("endRow", dataVO.getEndRow());
			}
			if(null != dataVO.getLastSyncTime()) {
				param.put("lastSyncTime", DateUtils.StringToString(dataVO.getLastSyncTime(), DateStyle.YYYY_MM_DD_HH_MM));
			}
			//检查是否自主控制
			checkPosSet(user, dataVO, param);
		}
		return param;
	}
	
	
	private Map<String, Object> getCountParam(UserVO user,RequestDataSyncVO dataVO,String tableName){
		Map<String, Object> param = new HashMap<>(5);
		if(user != null && dataVO != null) {
			param.put("chainType", user.getChainType());
			if(null != user.getEnterpriseId()) {
				param.put("enterpriseId", user.getEnterpriseId());
			}
			if(null != user.getParentId() && 0 != user.getParentId()) {
				param.put("parentId", user.getParentId());
			}
			if(null != dataVO.getLastSyncTime()) {
				param.put("lastSyncTime", DateUtils.StringToString(dataVO.getLastSyncTime(), DateStyle.YYYY_MM_DD_HH_MM));
			}
			/*// 按不同的表添加不同的条件
			Map<String, Integer> tableMap = countTableSycData();
			for(Map.Entry<String, Integer> m : tableMap.entrySet()) {
				if(tableName.equals(m.getKey())) {
					param.put("countParam", m.getValue());
					break;
				}
			}*/
			//检查是否自主控制
			checkPosSet(user, dataVO, param);
		}
		return param;
	}
	
	/**
	 * 
	 * @Description: 根据开关来选择同步数据范围
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月4日 下午4:07:37 
	 * @param user
	 * @param map 
	 * @return void
	 */
	private void checkPosSet(UserVO user,RequestDataSyncVO dataVO,Map<String, Object> param) {
		EnterpriseBusiness enterpriseBusiness = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(user.getEnterpriseId());
		String key = dataVO.getKey();//表名
		// 门店自主控制
		if (null != enterpriseBusiness) {
			Integer member_info = enterpriseBusiness.getMemberInfo();//会员开关
			Integer special_price_manage = enterpriseBusiness.getSpecialPriceManage();//特价开关
			Integer promotion_rule = enterpriseBusiness.getPromotionRule();//促销后期
			
			if (key.equals("saasMemberCardType") || key.equals("saasMemberInfo")) {
				if (1 == member_info) {
					// 将总部id重置为不存在，则只同步自己门店的数据
					param.put("parentId", -1);
				} else {
					// 只同步总部的数据
					param.put("enterpriseId", -1);
				}
			}

			if (key.equals("saasSpecialPriceStrategy") || key.equals("saasSpecialPriceGoods")) {
				if (1 == special_price_manage) {
					// 将总部id重置为不存在，则只同步自己门店的数据
					param.put("parentId", -1);
				} else {
					// 只同步总部的数据
					param.put("enterpriseId", -1);
				}
			}
			
			// 后期
			if (1 == promotion_rule) {

			}
			
			//支付方式、开户银行、系统设置
			for (POSDataSyncPosSetTableEnum tenum : POSDataSyncPosSetTableEnum.values()) {
				if (key.equals(tenum.getTableCode())) {
					// POS相关
					if (1 == enterpriseBusiness.getPosSet()) {
						// POS相关,加盟店只同步自己的数据
						if (user.getChainType().equals(ChainType.Division.getCode())){
							// 将总部id重置为不存在，则只同步自己门店的数据
							param.put("parentId", -1);
						}
						//自营店读自己
						if (user.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
							param.put("parentId", -1);
						}
					} else {
						//自营店、加盟店读取总部
						if (user.getChainType().equals(ChainType.Division.getCode())){
							param.put("enterpriseId", -1);
						}
						if (user.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
							param.put("enterpriseId", -1);
						}
					}
				}
			}
			
		}
	}
	
	/**
	 * 
	 * @Description: 特殊表总数条件区分
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年2月2日 下午2:16:17 
	 * @return 
	 * @return Map<String,Integer>
	 */
	@SuppressWarnings("unused")
	private Map<String, Integer> countTableSycData(){
		Map<String, Integer> mapTable = new HashMap<>(16);
		//同步总部和门店自己的数据 标示为0
		mapTable.put("saas_incompatibility_goods_one", 0);
		mapTable.put("saas_special_goods", 0);
		mapTable.put("saas_special_price_strategy", 0);
		mapTable.put("saas_special_price_goods", 0);
		mapTable.put("saas_member_info", 0);
		mapTable.put("saas_user_role", 0);
		mapTable.put("saas_pos_set", 0);
		mapTable.put("saas_pos_pay_type", 0);
		mapTable.put("saas_pos_bank", 0);
		mapTable.put("saas_pos_team", 0);
		mapTable.put("saas_goods_business", 0);
		mapTable.put("saas_quality_set", 0);
		//同步总部和门店、系统默认的数据 标示为1
		mapTable.put("saas_member_card_type", 1);
		mapTable.put("saas_position", 1);
		mapTable.put("saas_sys_role", 1);
		mapTable.put("saas_pharmacy_set", 1);
		return mapTable;
	}
	
}
