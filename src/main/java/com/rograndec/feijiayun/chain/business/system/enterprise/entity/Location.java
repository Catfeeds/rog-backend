package com.rograndec.feijiayun.chain.business.system.enterprise.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "location", description = "行政区域属性对象")
public class Location implements Serializable {
    /**
     * ID
     */
	@ApiModelProperty(value = "区域Id")
    private Integer id;

    /**
     * 名称
     */
	@ApiModelProperty(value = "区域名称")
    private String name;

    /**
     * 父级ID
     */
	@ApiModelProperty(value = "区域父级Id")
    private Integer parentPathId;

    /**
     * 父级ID，以逗号分隔
     */
	@ApiModelProperty(value = "区域Id以,分隔")
    private String parentId;

    /**
     * 父级名称，以逗号分隔
     */
	@ApiModelProperty(value = "区域名称以,分隔")
    private String parentName;

    /**
     * saas_location
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
     * 父级ID
     * @return parent_path_id 父级ID
     */
    public Integer getParentPathId() {
        return parentPathId;
    }

    /**
     * 父级ID
     * @param parentPathId 父级ID
     */
    public void setParentPathId(Integer parentPathId) {
        this.parentPathId = parentPathId;
    }

    /**
     * 父级ID，以逗号分隔
     * @return parent_id 父级ID，以逗号分隔
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级ID，以逗号分隔
     * @param parentId 父级ID，以逗号分隔
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 父级名称，以逗号分隔
     * @return parent_name 父级名称，以逗号分隔
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 父级名称，以逗号分隔
     * @param parentName 父级名称，以逗号分隔
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
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
        sb.append(", name=").append(name);
        sb.append(", parentPathId=").append(parentPathId);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}