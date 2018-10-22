package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.vo.QueryUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserDataVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserForPrescVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserLicenseReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserWarningPageVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionUserVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(User record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(User record);

    /**
     *
     * @mbg.generated
     */
    User selectByPrimaryKey(Long id);
	List<User> selectByEnterpriseId(Long id);

	Long selectByParentIdAndUserCode(@Param("enterpriseId") Long enterpriseId , @Param("code") String code);

	List<User> selectById(Map<String,Object> map);
	List<User> selectByIdAndRoleId(@Param("enterpriseId") Long enterpriseId,@Param("code") String code);
	List<User> selectByIdAndRoles(@Param("enterpriseId") Long enterpriseId,@Param("list") List<String> list);

    List<QueryUserVO> selectByQueryParam(Map map);

    Long selectByQueryParamCount(Map map);

    /**
     * in 查询
     * @param list
     * @return
     */
    List<User> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(User record);

	/**  
	 * @Title: findUserByEnterpriseId  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return List<User>    返回类型  
	 * @throws  
	 */
	List<User> findUserByEnterpriseId(Long enterpriseId);
	
	/**  
	 * @Title: findUserByEnterpriseId  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return List<User>    返回类型  
	 * @throws  
	 */
	List<User> findUserByEnterpriseIdRoleId(Long enterpriseId, long roleId);

	/**  
	 * @Title: findUserByEnterpriseId  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return List<User>    返回类型  
	 * @throws  
	 */
	List<User> addUserByEnterpriseId(Long enterpriseId);

	/**
	 * @Title: getSimpleUserVOByEnterpriseId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param enterpriseId
	 * @param @return    设定文件
	 * @return List<User>    返回类型
	 * @throws
	 */
	List<SimpleUserVO> getSimpleUserVOByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("status")Integer status);

	List<UserForPrescVO> getUserInfoForPresc(@Param("param")String param,@Param("enterpriseId")Long enterpriseId);

	UserForPrescVO getUserById(@Param("enterpriseId")Long enterpriseId,@Param("id") Long id);

	User getUserByIdAndEnterpriseId(@Param("receiverId") Long receiverId, @Param("enterpriseId") Long enterpriseId);

	List<PrescriptionUserVO> selectUserByRoleCode(@Param("roList") List<SysRole> roList,
			@Param("enterpriseId") Long enterpriseId);

    List<UserReportPageVO> selectFatherUserReportByMap(Map<String, Object> map);

	List<UserReportPageVO> selectSonUserReportByMap(Map<String, Object> map);

    List<UserLicenseReportPageVO> selectFatherUserLicenseReportByMap(Map<String, Object> map);

	List<UserLicenseReportPageVO> selectSonUserLicenseReportByMap(Map<String, Object> map);

    List<UserWarningPageVO> selectFatherUserWarningByMap(Map<String, Object> map);

	List<UserWarningPageVO> selectSonUserWarningByMap(Map<String, Object> map);

	User selectByCodeAndEnterpriseIdAndParentId(@Param("code") String code, @Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId);

    List<User> selectExisitUser(Long enterpriseId);

	List<UserDataVO> getUserDataByEId(Long enterpriseId);
	List<UserDataVO> selectByPositionIds(@Param("enterpriseId")Long enterpriseId, @Param("ids")List<Long> ids, @Param("chainType")Integer chainType);

    List<User> selectAllUsers();

	List<User> selectByRgtUserId(@Param("enterpriseId")Long enterpriseId, @Param("uId")String uId);

	Integer selectFatherUserReportTotalRecord(Map<String, Object> map);

	Integer selectSonUserReportTotalRecord(Map<String, Object> map);

	Integer selectFatherUserLicenseTotalRecord(Map<String, Object> map);

	Integer selectSonUserLicenseTotalRecord(Map<String, Object> map);

	Integer selectFatherUserWarningTotalRecord(Map<String, Object> map);

	Integer selectSonUserWarningTotalRecord(Map<String, Object> map);

    List<User> getReceivableManNameSelector(@Param("param") String param,@Param("enterpriseId") Long enterpriseId);

	User selectByIdAndEnterpriseIdAndStatusAndApproveStatus(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id,@Param("status")Integer status,@Param("approveStatus")Integer approveStatus);
}