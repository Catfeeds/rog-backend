package com.rograndec.feijiayun.chain.business.init.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysAction;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * 
 * @ClassName: SysDataInitService  
 * @Description: 系统初始化数据服务接口
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月6日 下午1:08:07  
 *
 */
public interface SysDataInitService {

	/**
	 *
	 * 初始化系统默认数据（全局只执行一次）
	 * 默认数据包括：
	 * 菲加云系统管理员；部门；岗位；角色；功能、企业菜单维护；角色菜单、权限设置；质量设置-拒收原因；质量设置-不合格原因；
	 * 质量设置-退货原因；质量设置-处置措施；质量设置-验收类型；质量设置-验收项目；质量设置-验收结论；质量设置-养护措施；
	 * 经营范围；企业资质；员工资质；商品资质；预警设置-企业预警；预警设置-员工预警；预警设置-供货单位预警；预警设置-商品预警；
	 * 预警设置-库存预警；门店分组；供货单位分组；商品设置-分类；商品设置-单位；商品设置-剂型；商品设置-税率；审批内容；民族；药学设置
	 *
	 * @throws Exception
	 */
	@Deprecated
	public void initGlobalDataOnlyOnce() throws Exception;

	/**
	 *
	 * 初始化总部、分店共公共默认数据（总部总部审核通过后、分店新增保存后执行）
	 * 默认数据包括：
	 * 管理设置；仓库、库区、货区、货位；审批流；POS设置；价格清单
	 *
	 * @param enterprise 企业
	 * @param user
	 * @throws Exception
	 */
	public void initCommonData(Enterprise enterprise, UserVO user) throws Exception;


	/**
	 * 初始化权限数据
	 * @throws Exception
	 */
	public void initPermissionData() throws Exception;

}
