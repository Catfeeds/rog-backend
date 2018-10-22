package com.rograndec.feijiayun.chain.common.file.entity;

import java.io.Serializable;

public class Bucket implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 类型，1：公用，2：私有
     */
    private Integer bucketType;

    /**
     * 协议，1：http，2：https
     */
    private Integer scheme;

    /**
     * 名称
     */
    private String name;

    /**
     * 域名
     */
    private String domain;

    /**
     * 描述
     */
    private String description;

    /**
     * saas_bucket
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 类型，1：公用，2：私有
     * @return bucket_type 类型，1：公用，2：私有
     */
    public Integer getBucketType() {
        return bucketType;
    }

    /**
     * 类型，1：公用，2：私有
     * @param bucketType 类型，1：公用，2：私有
     */
    public void setBucketType(Integer bucketType) {
        this.bucketType = bucketType;
    }

    /**
     * 协议，1：http，2：https
     * @return scheme 协议，1：http，2：https
     */
    public Integer getScheme() {
        return scheme;
    }

    /**
     * 协议，1：http，2：https
     * @param scheme 协议，1：http，2：https
     */
    public void setScheme(Integer scheme) {
        this.scheme = scheme;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 域名
     * @return domain 域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 域名
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        sb.append(", bucketType=").append(bucketType);
        sb.append(", scheme=").append(scheme);
        sb.append(", name=").append(name);
        sb.append(", domain=").append(domain);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}