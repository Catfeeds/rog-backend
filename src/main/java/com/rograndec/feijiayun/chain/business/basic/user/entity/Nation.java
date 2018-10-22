package com.rograndec.feijiayun.chain.business.basic.user.entity;

import java.io.Serializable;

public class Nation implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 民族编码
     */
    private String code;

    /**
     * 民族名称
     */
    private String name;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * saas_nation
     */
    private static final long serialVersionUID = 1L;

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
     * 民族编码
     * @return code 民族编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 民族编码
     * @param code 民族编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 民族名称
     * @return name 民族名称
     */
    public String getName() {
        return name;
    }

    /**
     * 民族名称
     * @param name 民族名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}