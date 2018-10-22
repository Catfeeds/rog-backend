package com.rograndec.feijiayun.chain.business.system.set.vo;

import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PositionVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value="主键ID",required=true)
    private Long id;

    /**
     * 部门ID
     */
	@ApiModelProperty(value="部门ID",required=true)
    private Long deptId;

    /**
     * 企业ID
     */
	@ApiModelProperty(value="企业ID",required=true)
    private Long enterpriseId;


    /**
     * 编码
     */
	@ApiModelProperty(value="编码",required=true)
    private String code;

    /**
     * 岗位名称
     */
	@ApiModelProperty(value="岗位名称",required=true)
    private String name;

    /**
     * 0：用户自定义岗位；1-系统默认岗位
     */
	@ApiModelProperty(value="0：用户自定义岗位；1-系统默认岗位",required=true)
    private Integer sysType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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
        sb.append(", deptId=").append(deptId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", sysType=").append(sysType);
        sb.append("]");
        return sb.toString();
    }

    public static List<PositionVO> getPositionsVO4Positions(List<Position> positions){
        List<PositionVO> positionVOS = new ArrayList<>();

        for(Position position : positions){
            PositionVO positionVO = new PositionVO();
            positionVO.setCode(position.getCode());
            positionVO.setDeptId(position.getDeptId());
            positionVO.setEnterpriseId(position.getEnterpriseId());
            positionVO.setId(position.getId());
            positionVO.setName(position.getName());
            positionVO.setSysType(position.getSysType());
            positionVOS.add(positionVO);
        }

        return positionVOS;
    }
}