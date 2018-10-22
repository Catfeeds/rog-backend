package com.rograndec.feijiayun.chain.business.basic.user.entity;

import com.rograndec.feijiayun.chain.business.basic.user.vo.AddUserVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -6188649653528539876L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 编码
     */
    private String code;

    /**
     * 检索码
     */
    private String pinyin;

    /**
     * 员工名称
     */
    private String name;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 融贯通用户ID
     */
    private Integer rgtUserId;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）
     */
    private Integer approveStatus;



    /**
     * 初始化用户
     * @param name
     * @param code
     * @param enterprise
     * @return
     * @throws Exception
     */
    public static User initEnterpriseUser(
            String name
            , String code
            , Enterprise enterprise
    ) throws Exception {

        User user = new User();
        user.setCode(code);
        user.setName(name);
        if(null == enterprise){
            user.setEnterpriseId(0L);
            user.setParentId(0L);
        }else {
            user.setEnterpriseId(enterprise.getId());
            user.setParentId(enterprise.getParentId());
        }

        user.setPinyin(PinYinUtils.getFirstSpell(name));
        user.setStatus(EnableStatus.ENABLE.getStatus());
        user.setCreaterId(0L);
        user.setCreaterCode(name);
        user.setCreaterName(name);
        user.setCreateTime(new Date());
        user.setModifierId(0L);
        user.setModifierCode(name);
        user.setModifierName(name);
        user.setUpdateTime(new Date());

        return user;
    }

    public static List<Long> getIds(List<User> users ){
        List<Long> ids = new ArrayList<>();

        for(User user : users){
            ids.add(user.getId());
        }

        return ids;
    }

    public static List<Long> getentErpriseIds(List<User> users){
        List<Long> ids = new ArrayList<>();

        for(User user : users){
            ids.add(user.getEnterpriseId());
        }

        return ids;
    }

    public static User getUser4AddUserVO(AddUserVO addUserVO, UserVO userVO, Enterprise enterprise ,boolean isAdd){

        User user = new User();
        user.setId(addUserVO.getId());
        user.setEnterpriseId(enterprise.getId());
        user.setParentId(enterprise.getParentId());
        user.setCode(addUserVO.getCode());

        user.setName(addUserVO.getName());
//        String pinyin = PinYinUtils.getFirstSpell(addUserVO.getName());
        user.setPinyin(addUserVO.getPinyin());

        if(isAdd){
            user.setCreaterId(userVO.getUserId());
            user.setCreaterCode(userVO.getUserCode());
            user.setCreaterName(userVO.getUserName());
            user.setCreateTime(new Date());
            user.setModifierId(userVO.getUserId());
            user.setModifierCode(userVO.getUserCode());
            user.setModifierName(userVO.getUserName());
            user.setUpdateTime(new Date());
        }else {
            user.setModifierId(userVO.getUserId());
            user.setModifierCode(userVO.getUserCode());
            user.setModifierName(userVO.getUserName());
            user.setUpdateTime(new Date());
        }
        return user;
    }

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 检索码
     * @return pinyin 检索码
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 检索码
     * @param pinyin 检索码
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * 员工名称
     * @return name 员工名称
     */
    public String getName() {
        return name;
    }

    /**
     * 员工名称
     * @param name 员工名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    public Integer getRgtUserId() {
        return rgtUserId;
    }

    public void setRgtUserId(Integer rgtUserId) {
        this.rgtUserId = rgtUserId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
}