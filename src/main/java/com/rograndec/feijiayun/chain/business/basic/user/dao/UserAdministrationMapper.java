package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserPwdVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserAdministrationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserAdministration record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserAdministration record);

    /**
     *
     * @mbg.generated
     */
    UserAdministration selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserAdministration record);
    /**
     * 使用前请注意查看！！！
     * 毕业时间 graduation_time
     * 参加工作时间 working_hours
     * 入职时间 working_hours
     * 转正时间 full_time
     * 柜组 cargo_area_ids
     * 班组 team_id
     * 可修改为null
     * @mbg.generated
     */
    int updateByPrimaryKeyPartSelective(UserAdministration record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(UserAdministration record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserAdministration record);

    
    /**
     * 根据Userid查询员工-行政-子表
     * @Description: TODO(描述该方法做什么)
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月29日 下午5:36:56 
     * @param adminId
     * @return 
     * @return UserAdministration
     */
    List<UserAdministration> selectUserAdministrationByUserId(Long adminId);
    UserAdministration selectUserAdministrationByAccountAndPwd(@Param("loginAccount") String loginAccount,@Param("password") String password);
    UserAdministration selectUserAdministrationByAccount(@Param("loginAccount") String loginAccount);
    List<UserAdministration> selectUserAdministrationByUserIds(List<Long> list);


    /**
     * 修改密码
     * @param userPwdVO
     * @return
     */
    int updatePwd(UserPwdVO userPwdVO);

    int getUserCountByUIDPwd(UserPwdVO userPwdVO);
    
    /**
     * 
     * @Description: 根据userId获取用户账号
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月13日 上午10:54:59 
     * @param userId
     * @return 
     * @return Map<String,Object>
     */
    Map<String, Object> getUserAccount(@Param("userId")Long userId);

	Long queryCountUserAdministrationByAccount(@Param("loginAccount")String loginAccount);

    int selectUserHasDepartment(Long id);

    int selectUserHasPosition(Long id);
    
    /**
    *根据用户id获取用户的行政信息
    */
   UserAdministration selectByUserId(@Param("id") Long id);
}