package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.EducationMajor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EducationMajorReturnVO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;

    /**
     * 类型（0-学历；1-专业）
     */
    private Integer type;

    /**
     * 学历、专业名称
     */
    private String name;

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     */
    private Integer sysType;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;



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
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 类型（0-学历；1-专业）
     * @return type 类型（0-学历；1-专业）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型（0-学历；1-专业）
     * @param type 类型（0-学历；1-专业）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 学历、专业名称
     * @return name 学历、专业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 学历、专业名称
     * @param name 学历、专业名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     * @return sys_type 系统默认标志（0-用户自定义；1-系统默认）
     */
    public Integer getSysType() {
        return sysType;
    }

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     * @param sysType 系统默认标志（0-用户自定义；1-系统默认）
     */
    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", sysType=").append(sysType);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    public static List<EducationMajorReturnVO> getEducationMajorReturnVOs4EducationMajors(List<EducationMajor> educationMajors){
        List<EducationMajorReturnVO> vos = new ArrayList<>();
        for (EducationMajor educationMajor : educationMajors){
            EducationMajorReturnVO vo = getEducationMajorReturnVO4EducationMajor(educationMajor);
            vos.add(vo);
        }
        return vos;
    }

    public static EducationMajorReturnVO getEducationMajorReturnVO4EducationMajor(EducationMajor educationMajor){

        EducationMajorReturnVO educationMajorReturnVO = new EducationMajorReturnVO();
        educationMajorReturnVO.setId(educationMajor.getId());
        educationMajorReturnVO.setEnterpriseId(educationMajor.getEnterpriseId());
        educationMajorReturnVO.setEnterpriseId(educationMajor.getEnterpriseId());
        educationMajorReturnVO.setType(educationMajor.getType());
        educationMajorReturnVO.setName(educationMajor.getName());
        educationMajorReturnVO.setStatus(educationMajor.getStatus());
        educationMajorReturnVO.setSysType(educationMajor.getSysType());
        return educationMajorReturnVO;
    }
}