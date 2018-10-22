package com.rograndec.feijiayun.chain.business.basic.user.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.vo.param.UsersParam;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaiwei on 2017/8/28.
 */
public interface UserManagerService {

//    UserInitVO initUser(UserVO userVO);

    void saveUser(AddUserVO addUserVO, UserVO userVO) throws Exception;

    void updateUser(AddUserVO addUserVO, UserVO userVO) throws Exception;


    void resetPassword(UserVO userVO, Long userId);

    List<UserQualificationConfigReturnVO> initUserQualificationConfig(UserVO user,List<Long> positionIds);

    Page users(UserVO userVO, UsersParam usersParam, Page page);

    Page findUserModifyRecordVOs(Long userId,Page page);

    UserReturnVO findUser(Long userId);

    List<User> findUserByEId(Long id);

    List<User> findUserById(Map<String, Object> map);

    List<User> findUserByIdAndRole(UserVO userVO, String roleCode);

    Map<String,List<User>> findUserByIdAndRoles(UserVO userVO);

    List<UserReturnVO> findUser(Set<Long> userIds);

    UserAdministrationReturnVO findUserAdministration(Long userId,UserVO userVO);

    List<UserAdministrationReturnVO> findUserAdministration(Set<Long> userId);

    UserPersonalReturnVO findUserPersonalReturnVO(Long userId);

    List<UserPersonalReturnVO> findUserPersonalReturnVO(Set<Long> userIds);

    List<UserQualificationConfigReturnVO> findUserQualificationConfigReturnVOs(UserVO userVO,Long userId);

    int updatePwd(UserPwdVO userPwdVO,UserVO userVO);

    Integer getUserCountByUIDPwd(UserPwdVO userPwdVO);


    List<UserQualificationConfigReturnVO> initUserQualificationConfig(UserVO user);

    void excelExport4User(OutputStream output, UserVO userVO, Long enterprise,
                          Long dept,
                          Long position,
                          Long role,
                          Long education,
                          Long major,
                          Long useNature,
                          Long status,
                          String queryStr, Integer approveStatus);


    List<Tree> getRole4User(Long userId);

	User queryUserByUserId(Long userId);
	
	void addOrUpdateWithApprovalControl(Long id);

    void saveDraftCache(UserVO userVO, DraftCacheVO<UserReturnVO> draftCache);

    void removeDraftCach(Long enterpriseId, String type, String redisKeyValue);

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO);

}
