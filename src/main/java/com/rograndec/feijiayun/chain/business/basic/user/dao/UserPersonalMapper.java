package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;

import java.util.List;
import java.util.Set;

public interface UserPersonalMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserPersonal record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserPersonal record);

    /**
     *
     * @mbg.generated
     */
    UserPersonal selectByPrimaryKey(Long id);

    UserPersonal selectByEmail(String email);

    UserPersonal selectByMobilePhone(String mobilePhone);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserPersonal record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserPersonal record);

    /**
     * 根据Userid查询个人信息子表
     * @Description: TODO(描述该方法做什么)
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月29日 下午5:36:56 
     * @param adminId
     * @return 
     * @return UserAdministration
     */
	List<UserPersonal> selectUserPersonalByUserId(Long adminId);

	List<UserPersonal> selectUserPersonalByUserIds(List<Long> list);

    /**
     * 使用前请注意查看！！！！
     * 出生日期 birth_dat
     * 照片附件ID photo_id
     * 可修改为null
     * @param userPersonal
     */
    void updateByPrimaryKeyPartSelective(UserPersonal userPersonal);
}