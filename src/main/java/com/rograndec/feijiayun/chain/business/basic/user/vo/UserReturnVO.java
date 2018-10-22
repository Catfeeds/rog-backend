package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/23.
 */
@ApiModel
public class UserReturnVO implements Serializable {

    /**
     * userId
     */
    @ApiModelProperty(value = "用户id ,修改时需要传入,新增时不需要", required = false)
    private Long id;
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "组织机构id", required = true)
    private Long enterpriseId;

    @ApiModelProperty(value = "组织机构name", required = true)
    private String enterpriseName;

    @ApiModelProperty(value = "企业类型")
    private Integer chainType;

    /**
     * 编码 (根据init接口的初始化业务规则判断是否需要传入)
     */
    @ApiModelProperty(value = "编码", required = false)
    private String code;

    /**
     * 检索码
     */
    @ApiModelProperty(value = "检索码", required = false)
    private String pinyin;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称", required = true)
    private String name;

    /**
     * 行政信息
     */
    @ApiModelProperty(value = "行政信息", required = true)
    private UserAdministrationReturnVO userAdministrationReturnVO;

    /**
     * 个人信息集合
     */
    @ApiModelProperty(value = "行政信息", required = false)
    private UserPersonalReturnVO userPersonalDTOS;

    /**
     * 资质新增关联
     */
    @ApiModelProperty(value = " 资质信息", required = false)
    private List<UserQualificationConfigReturnVO> userQualificationConfigDTOS;


    public static List<Long> getIds(List<User> users ){
        List<Long> ids = new ArrayList<>();

        for(User user : users){
            ids.add(user.getId());
        }

        return ids;
    }

    public static UserReturnVO getUserReturnVO4User(User user , Enterprise enterprise
    ,UserAdministrationReturnVO userAdministrationReturnVO
    ,UserPersonalReturnVO userPersonalDTOS
    ,List<UserQualificationConfigReturnVO> userQualificationConfigDTOS){

        UserReturnVO userReturnVO = new UserReturnVO();
        userReturnVO.setId(user.getId());
        userReturnVO.setEnterpriseId(user.getEnterpriseId());
        userReturnVO.setEnterpriseName(enterprise.getName());
        userReturnVO.setCode(user.getCode());
        userReturnVO.setPinyin(user.getPinyin());
        userReturnVO.setName(user.getName());
        userReturnVO.setUserAdministrationReturnVO(userAdministrationReturnVO);
        userReturnVO.setUserPersonalDTOS(userPersonalDTOS);
        userReturnVO.setUserQualificationConfigDTOS(userQualificationConfigDTOS);
        userReturnVO.setChainType(enterprise.getChainType());
        return userReturnVO;
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public UserAdministrationReturnVO getUserAdministrationReturnVO() {
        return userAdministrationReturnVO;
    }

    public void setUserAdministrationReturnVO(UserAdministrationReturnVO userAdministrationReturnVO) {
        this.userAdministrationReturnVO = userAdministrationReturnVO;
    }

    public UserPersonalReturnVO getUserPersonalDTOS() {
        return userPersonalDTOS;
    }

    public void setUserPersonalDTOS(UserPersonalReturnVO userPersonalDTOS) {
        this.userPersonalDTOS = userPersonalDTOS;
    }

    public List<UserQualificationConfigReturnVO> getUserQualificationConfigDTOS() {
        return userQualificationConfigDTOS;
    }

    public void setUserQualificationConfigDTOS(List<UserQualificationConfigReturnVO> userQualificationConfigDTOS) {
        this.userQualificationConfigDTOS = userQualificationConfigDTOS;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

}
