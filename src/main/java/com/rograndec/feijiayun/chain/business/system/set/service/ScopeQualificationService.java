/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service;

import com.rograndec.feijiayun.chain.business.basic.user.vo.BusinessVarietysVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.QualificationValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.business.system.set.vo.GoodsQualificationVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月21日 下午6:33:09

 */
public interface ScopeQualificationService {

	/**  
	 * @Title: getScopeQualification  
	 * @Description: 获取经营范围
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<BusinessScope>    返回类型  
	 * @throws  
	 */
	List<BusinessScope> getScopeQualification(UserVO user);

    List<BusinessScope> getScopeQualificationByIdAndScopes(UserVO user, BusinessVarietysVO businessVarietysVO);

    /**
	 * @Title: updateQualityUnqualified  
	 * @Description: 修改经营范围  
	 * @param @param businessScope    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateQualityUnqualified(BusinessScope businessScope,UserVO user) throws Exception;

	/**  
	 * @Title: addQualityUnqualified  
	 * @Description: 增加经营范围
	 * @param @param businessScope    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addQualityUnqualified(BusinessScope businessScope,UserVO user) throws Exception;

	/**  
	 * @Title: deleteQualityUnqualified  
	 * @Description: 删除经营范围  
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deleteQualityUnqualified(Long id);

	/**  
	 * @Title: getEnterpriseQualification  
	 * @Description: 查看企业资质
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<EnterpriseQualification>    返回类型  
	 * @throws  
	 */
	List<EnterpriseQualification> getEnterpriseQualification(UserVO user);

	/**  
	 * @Title: updateEnterpriseQualification  
	 * @Description: 修改企业资质  
	 * @param @param enterpriseQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateEnterpriseQualification(EnterpriseQualification enterpriseQualification,UserVO user) throws Exception;

	/**  
	 * @Title: addEnterpriseQualification  
	 * @Description: 增加企业资质
	 * @param @param enterpriseQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addEnterpriseQualification(EnterpriseQualification enterpriseQualification,UserVO user) throws Exception;

	/**  
	 * @Title: deleteEnterpriseQualification  
	 * @Description: 删除企业资质
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deleteEnterpriseQualification(Long id) throws Exception;

	/**  
	 * @Title: getUserQualification  
	 * @Description: 增加员工资质  
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<UserQualification>    返回类型  
	 * @throws  
	 */
	List<UserQualification> getUserQualification(UserVO user);

	List<UserQualification> getUserQualification4Enable(UserVO user);


	/**
	 * @Title: updateUserQualification  
	 * @Description: 修改员工资质 
	 * @param @param userQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateUserQualification(UserQualification userQualification,UserVO user) throws Exception;

	/**  
	 * @Title: addUserQualification  
	 * @Description: 增加员工资质 
	 * @param @param userQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addUserQualification(UserQualification userQualification,UserVO user) throws Exception;

	/**  
	 * @Title: deleteUserQualification  
	 * @Description: 删除员工资质 
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deleteUserQualification(Long id);

	/**  
	 * @Title: getGoodsQualification  
	 * @Description: 增加商品资质 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<GoodsQualification>    返回类型  
	 * @throws  
	 */
	List<GoodsQualification> getGoodsQualification(UserVO user);

	/**  
	 * @Title: updateGoodsQualification  
	 * @Description: 修改商品资质  
	 * @param @param goodsQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateGoodsQualification(GoodsQualification goodsQualification,UserVO user) throws Exception;

	/**  
	 * @Title: addGoodsQualification  
	 * @Description: 增加商品资质 
	 * @param @param goodsQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addGoodsQualification(GoodsQualification goodsQualification,UserVO user) throws Exception;

	/**  
	 * @Title: deleteGoodsQualification  
	 * @Description: 删除商品资质 
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deleteGoodsQualification(Long id) throws Exception;



	/**
	 *  根据条件查询商品的资质
	 * @param enterpriseId
	 * @param checkTypeId
	 * @param type
	 *@param status
	 * @param quId 资质id   @return
	 */
	List<GoodsQualificationVO> getGoodQuaInfoVO(Long enterpriseId, Long checkTypeId, Integer type, Integer status, Long quId);

	/**
	 * 根据主键查询商品资质信息
	 * @param quId
	 * @return
     */
	GoodsQualification getGoodQuaInfoByKey(Long quId);

    QualificationValidateVO queryUserQualificationValidateByEnterpriseIdAndId(Long enterpriseId, String qualificationId);
}
