package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.constant.UserStatusEum;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserTypeEum;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel
public class QueryUserVO implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;


    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 检索码(拼音)
     */
    @ApiModelProperty(value = "检索码(拼音)")
    private String pinyin;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称")
    private String name;

    /**
     * 用户类型集合（0-系统管理员；1-云系统用户；2-POS用户；3-云系统用户和POS用户）
     */
    @ApiModelProperty(value = "用户类型集合")
    private String userType;

    @ApiModelProperty(value = "用户类型集合描述")
    private String userTypeDesc;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;


    /**
     * 部门集合 逗号分隔
     */
    @ApiModelProperty(value = "部门集合 逗号分隔")
    private String deptIds="";

    /**
     * 部门集合id 逗号分隔
     */
    private List<Long> depts;
    /**
     * 岗位集合
     */
    @ApiModelProperty(value = "岗位集合 逗号分隔")
    private String positionIds="";

    /**
     * 岗位集合 逗号分隔
     */
    private List<Long> positions;

    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色集合 逗号分隔")
    private String roleIds="";

    /**
     * 角色集合 逗号分隔
     */
    private List<Long> roles;
    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = "状态（0-离职；1-在职)")
    private Integer status;

    /**
     * 审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）
     */
    @ApiModelProperty(value = "审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）")
    private Integer approveStatus;

    @ApiModelProperty(value = "审核状态描述")
    private String approveStatusDesc;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    public static Set<Long> getIds(List<QueryUserVO> queryUserVOS){

        Set<Long> ids = new HashSet<>();
        for(QueryUserVO queryUserVO : queryUserVOS){
            ids.add(queryUserVO.getId());
        }
        return ids;
    }

    public static List<Long> getEnterpriseIds(List<QueryUserVO> queryUserVOS){

        return queryUserVOS.stream().map(queryUserVO -> queryUserVO.getEnterpriseId()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
    }

    public static List<QueryUserVO> setDesc4QueryUserVOs(List<QueryUserVO> queryUserVOS,
                                                                    List<Enterprise>  enterprises){

        for(QueryUserVO queryUserVO : queryUserVOS){
            for(Enterprise enterprise : enterprises){
                if(queryUserVO.getEnterpriseId().equals(enterprise.getId())){
                    queryUserVO.setEnterpriseName(enterprise.getName());
                }
            }
            String userType = queryUserVO.getUserType();
            List<Long> userTypes = StringSplit.strSplit(userType);
            for(Long ut : userTypes){
                UserTypeEum userTypeEum = UserTypeEum.getUserTypeEum4Code(Integer.parseInt(ut.toString()));
                    queryUserVO.setUserTypeDesc((StringUtils.isEmpty(queryUserVO.getUserTypeDesc()) ? "" : queryUserVO.getUserTypeDesc()+",") + userTypeEum.getValue());

            }


            UserStatusEum userStatusEum = UserStatusEum.getUserStatusEum4Code(queryUserVO.getStatus());
            queryUserVO.setStatusDesc(userStatusEum.getValue());
        }

        return queryUserVOS;
    }

    public static QueryUserVO setDeptDesc(List<Department> departments,QueryUserVO queryUserVO){

        StringBuffer stringBuffer = new StringBuffer();
        List<Long> depts = queryUserVO.getDepts();
        for(Long dId : depts) {
            for (Department department : departments) {
                if (dId.equals(department.getId())) {
                    stringBuffer.append(department.getName()).append(",");
                }

            }
        }
        queryUserVO.setDeptIds(stringBuffer.toString());
        return queryUserVO;
    }

    public static QueryUserVO setPositionsDesc(List<Position> positions, QueryUserVO queryUserVO){

        StringBuffer stringBuffer = new StringBuffer();
        List<Long> ps = queryUserVO.getPositions();
        for(Long dId : ps) {
            for (Position position : positions) {
                if (dId.equals(position.getId())) {
                    stringBuffer.append(position.getName()).append(",");
                }

            }
        }
        queryUserVO.setPositionIds(stringBuffer.toString());
        return queryUserVO;
    }

    public static QueryUserVO setRolesDesc(List<SysRole> roles, QueryUserVO queryUserVO){
        StringBuffer stringBuffer = new StringBuffer();
        List<Long> ps = queryUserVO.getRoles();
        for(Long pId : ps){
            for(SysRole sysRole : roles){
                if(pId.equals(sysRole.getId())){
                    stringBuffer.append(sysRole.getName()).append(",");
                }
            }
        }

        queryUserVO.setRoleIds(stringBuffer.toString());
        return queryUserVO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", code=").append(code);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", name=").append(name);
        return sb.toString();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getUserTypeDesc() {
        return userTypeDesc;
    }

    public void setUserTypeDesc(String userTypeDesc) {
        this.userTypeDesc = userTypeDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<Long> getDepts() {
        return depts;
    }

    public void setDepts(List<Long> depts) {
        this.depts = depts;
    }

    public List<Long> getPositions() {
        return positions;
    }

    public void setPositions(List<Long> positions) {
        this.positions = positions;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatusDesc() {
        return approveStatusDesc;
    }

    public void setApproveStatusDesc(String approveStatusDesc) {
        this.approveStatusDesc = approveStatusDesc;
    }

}