package com.rograndec.feijiayun.chain.business.system.set.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface BusinessScopeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(BusinessScope record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(BusinessScope record);

    /**
     *
     * @mbg.generated
     */
    BusinessScope selectByPrimaryKey(Long id);

    List<BusinessScope> selectByDefult();

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BusinessScope record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BusinessScope record);

    /**
     * 根据经营品种查询经营范围
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月23日 上午10:08:17 
     * @param enterpriseId
     * @param businessVariety
     * @return 
     * @return List<BusinessScope>
     */
	List<BusinessScope> getBusinessScopeByBusinessVariety(
			@Param("enterpriseId")Long enterpriseId, @Param("list")List<Long> list);

	/**
     * 根据经营范围ID查询经营范围
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月23日 上午10:08:17 
     * @param enterpriseId
     * @param businessVariety
     * @return 
     * @return List<BusinessScope>
     */
	List<BusinessScope> getBusinessScopeByBusinessScopeId(
			@Param("enterpriseId")Long enterpriseId, @Param("list")List<Long> list);
	/**  
	 * @Title: getScopeQualification  
	 * @Description: 查询总部经营范围
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<BusinessScope>    返回类型  
	 * @throws  
	 */
	List<BusinessScope> getScopeQualification(UserVO user);

	/**  
	 * @Title: getScopeQualificationById  
	 * @Description: 分部的情况下：通过商机企业ID获得经营范围  
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<BusinessScope>    返回类型  
	 * @throws  
	 */
	List<BusinessScope> getScopeQualificationById(UserVO user);

//	List<BusinessScope> getScopeQualificationByIdAndScopes(QueryEntity queryEntity);
	List<BusinessScope> getScopeQualificationByIdAndScopes(Map<String,Object> map);
	List<BusinessScope> getScopeQualificationByDept(@Param("varietys") List<Integer> varietys ,@Param("scopes") List<Long> scopes);

	/**  
	 * @Title: addQualityUnqualified  
	 * @Description: 增加经营范围  
	 * @param @param businessScope    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addQualityUnqualified(Map<String, Object> map);

	/**  
	 * @Title: addQualityUnqualified  
	 * @Description: 修改经营范围  
	 * @param @param businessScope    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateQualityUnqualified(Map<String, Object> map);

	List<BusinessScope> findBusinessScopeSelectorByEnterpriseId(Long enterpriseId);

	List<BusinessScope> getScope(List<String> list);

	List<BusinessScopeVO> getBusinessScopeVOList(@Param("businessVariety") Integer businessVariety, @Param("status") Integer status, @Param("enterpriseId") Long enterpriseId);

    BusinessScope hasBusinessCode(@Param("code")String code, @Param("enterPriseId")Long enterPriseId);

	BusinessScope hasBusinessName(@Param("name") String name,@Param("enterPriseId") Long enterPriseId);

	/**
	 * 根据经营范围查询企业
	 *
	 * @param id
	 * @return
	 */
    List<Enterprise> selectEnterpriseByScopeId(@Param("scopeId") Long id);

	/**
	 * 根据经营范围查询 供货单位
	 *
	 * @param id
	 * @return
	 */

	List<Supplier> selectSupplierByScopeId(@Param("scopeId") Long id);

	/**
	 * 根据经营范围查询 用户
	 * @param id
	 * @return
	 */
	List<UserAdministration> selectUserByScopeId(@Param("scopeId")Long id);

	List<BusinessScope> selectByIds(List<Long> list);
}