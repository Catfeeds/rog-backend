package com.rograndec.feijiayun.chain.business.auth.login.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.RGTUserComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.LoginSrc;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @ClassName: AuthRealm  
 * @Description: shiro 登录校验
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年8月23日 下午5:13:15  
 *
 */
public class AuthRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private RGTUserComponent rgtUserComponent;
	// 授权
/*	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// 获取Session中的用户
		UserVO user = (UserVO) principal.fromRealm(this.getClass().getName()).iterator().next();
		List<String> permissions = new ArrayList<>();
		Set<SysRoleVO> roles = user.getRoles();
		if(roles.size()>0) {
            for(SysRoleVO role : roles) {
                Set<SysActionVO> actions = role.getActions();
                if(actions.size()>0) {
                    for(SysActionVO action : actions) {
                        permissions.add(action.getActionCode());
                    }
                }
            }
        }
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		// 将权限放入shiro中.
        info.addStringPermissions(permissions);
		return info;
	}*/

	// 认证.登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SaasLoginToken upToken = (SaasLoginToken) token;
		String loginAccount = upToken.getUsername();
		String loginSrc = upToken.getLoginSrc();
		if(!StringUtils.isEmpty(loginAccount)){
			UserVO user = new UserVO();
			if(loginSrc.equals(LoginSrc.CHAIN_CLIENT.getValue())) {
				user = loginService.findUserByLoginAccount(loginAccount);

				if(user == null){
					throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"用户不存在");
				}
				
				// 验证用户
				checkLogin(user);

				if(null != user.getApproveStatus()
						&& (ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue() == user.getApproveStatus()
						|| ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue() == user.getApproveStatus())
						){

					/**
					 * 待审核,审核被驳回,不允许登录
					 */
					throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"用户处于审批被驳回或者待审批,不允许登录");
				}

				rgtUserComponent.loginRgtUser(user.getLoginAccount(),user.getPassword());
			}
			
			if(loginSrc.equals(LoginSrc.POS_CLIENT.getValue())) {
				user = loginService.findUserByPOSLoginAccount(loginAccount);
				if(user == null){
					throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"POS用户不存在");
				}
				// 验证用户
				checkLogin(user);
			}
			// 放入shiro.调用CredentialsMatcher检验密码
			return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
		}

		return new SimpleAuthenticationInfo();
	}
	
	/**
	 * 
	 * @Description: 抽取公共验证
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年2月11日 下午8:24:19 
	 * @param user 
	 * @return void
	 */
	private void checkLogin(UserVO user) {
		Long enterpriseId = user.getEnterpriseId();

		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		if(null == enterprise){
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"用户企业不存在");
		}

		if(enterprise.getStatus().equals(EnableStatus.DISABLE.getStatus())){
			//企业状态为未启用,不允许登录
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"用户企业为禁用状态,不允许登录");
		}

		if(null != enterprise.getApproveStatus()
				&& (ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue() == enterprise.getApproveStatus()
				|| ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue() == enterprise.getApproveStatus())
				){
			//企业状态为未启用,不允许登录
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"用户企业处于审批被驳回或者待审批,不允许登录");
		}


		if(EnableStatus.ENABLE.getStatus() != user.getStatus()){
			/**
			 * 用户状态不等于启用,不允许登录
			 */
			throw new LoginBizException(SysCode.FORBIDDEN.getCode(),"用户状态为禁用状态,不允许登录");
		}
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
}
