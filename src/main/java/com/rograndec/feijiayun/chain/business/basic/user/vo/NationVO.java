package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class NationVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 民族编码
     */
    @ApiModelProperty(value = "民族编码", required = true)
    private String code;

    /**
     * 民族名称
     */
    @ApiModelProperty(value = "民族名称", required = true)
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;


    public static List<NationVO> getNationsVO4Nations(List<Nation> nations){

        List<NationVO> list = new ArrayList<>();

        for(Nation nation : nations){
            NationVO nationVO = getNationVO4Nation(nation);
            list.add(nationVO);
        }


        return list;
    }

    public static NationVO getNationVO4Nation(Nation nation){
        NationVO nationVO = new NationVO();

        nationVO.setId(nation.getId());
        nationVO.setName(nation.getName());
        nationVO.setCode(nation.getCode());
        nationVO.setStatus(nation.getStatus());

        return nationVO;
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
        sb.append("]");
        return sb.toString();
    }
}