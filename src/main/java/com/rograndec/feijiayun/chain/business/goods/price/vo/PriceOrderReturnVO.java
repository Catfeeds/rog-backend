package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.PriceType;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceOrderReturnVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value=" 价格清单头id")
    private Long id;

    /**
     * 基础价格清单ID（为0代表无）
     */
    @ApiModelProperty(value=" 基础价格清单ID（为0代表无）")
    private Long parentOrderId;

    /**
     * 基础价格名称
     */
    @ApiModelProperty(value=" 基础价格名称")
    private String parentOrderName;

    /**
     * 企业ID
     */
    @ApiModelProperty(value=" 企业ID")
    private Long enterpriseId;
    
    private String enterpriseName;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value=" 上级企业ID")
    private Long parentId;

    /**
     *类型 0-自定义价格清单；1-系统价格清单
     */
    @ApiModelProperty(value=" 类型 0-自定义价格清单；1-系统价格清单")
    private Integer sysType;

    /**
     *类型描述
     */
    @ApiModelProperty(value=" 类型描述")
    private String sysTypeDesc;

    /**
     * 价格类型（0-基础价格；1-配货价格；2-零售价格）
     */
    @ApiModelProperty(value=" 价格类型（0-基础价格；1-配货价格；2-零售价格）")
    private Integer priceType;

    /**
     * 价格类型描述
     */
    @ApiModelProperty(value=" 价格类型描述")
    private String priceTypeDesc;

    /**
     * 清单编码
     */
    @ApiModelProperty(value=" 清单编码")
    private String code;

    /**
     * 清单名称
     */
    @ApiModelProperty(value=" 清单名称")
    private String name;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 状态描述
     */
    @ApiModelProperty(value=" 状态描述")
    private String statusDesc;

    /**
     * 备注
     */
    @ApiModelProperty(value=" 备注")
    private String remark;


    
    /**
     * 门店查看详情时为价格清单详情
     */
    @ApiModelProperty(value="门店查看详情时为价格清单详情")
    List<PriceOrderDetailReturnVO> priceOrderDetailReturnVOS = new ArrayList<>();


    private Boolean deleteFlag = true;

    private Boolean updateFlag = true;

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }
    public List<PriceOrderDetailReturnVO> getPriceOrderDetailReturnVOS() {
		return priceOrderDetailReturnVOS;
	}

	public void setPriceOrderDetailReturnVOS(List<PriceOrderDetailReturnVO> priceOrderDetailReturnVOS) {
		this.priceOrderDetailReturnVOS = priceOrderDetailReturnVOS;
	}

	public static List<PriceOrderReturnVO> getPriceOrders(UserVO userVO,List<PriceOrder> parentPriceOrders,List<PriceOrder> priceOrders) throws Exception {

        List<PriceOrderReturnVO> priceOrderReturnVOS = new ArrayList<>();

        Map<Long,PriceOrder> parentMap = new HashMap<>();


        if(!CollectionUtils.isEmpty(parentPriceOrders)){
            for(PriceOrder priceOrder : priceOrders){
                for(PriceOrder parentPriceOrder : parentPriceOrders){
                    if(parentPriceOrder.getId().equals(priceOrder.getParentOrderId())){

                        parentMap.put(priceOrder.getId(),parentPriceOrder);

                    }
                }

            }

            for(PriceOrder priceOrder : priceOrders){
                PriceOrder parentPriceOrder = parentMap.get(priceOrder.getId());
                PriceOrderReturnVO returnVO = getPriceOrder(userVO,parentPriceOrder,priceOrder);
                priceOrderReturnVOS.add(returnVO);
            }
        }else {
            for(PriceOrder priceOrder : priceOrders){
                PriceOrderReturnVO returnVO = getPriceOrder(userVO,null,priceOrder);
                priceOrderReturnVOS.add(returnVO);
            }
        }


        return priceOrderReturnVOS;
    }


    public static PriceOrderReturnVO getPriceOrder(UserVO userVO,PriceOrder parentPriceOrder,PriceOrder priceOrder) throws Exception {

        PriceOrderReturnVO priceOrderReturnVO = new PriceOrderReturnVO();

        priceOrderReturnVO.setId(priceOrder.getId());

        priceOrderReturnVO.setParentOrderId(priceOrder.getParentOrderId());
        if(null != parentPriceOrder)
            priceOrderReturnVO.setParentOrderName(parentPriceOrder.getName());

        priceOrderReturnVO.setEnterpriseId(priceOrder.getEnterpriseId());
        priceOrderReturnVO.setParentId(priceOrder.getParentId());

        priceOrderReturnVO.setSysType(priceOrder.getSysType());
        priceOrderReturnVO.setSysTypeDesc(SysType.getSysTypeEnum(priceOrder.getSysType()).getValue());

        priceOrderReturnVO.setCode(priceOrder.getCode());

        priceOrderReturnVO.setPriceTypeDesc(PriceType.getPriceTypeEnum(priceOrder.getPriceType()).getValue());
        priceOrderReturnVO.setPriceType(priceOrder.getPriceType());

        priceOrderReturnVO.setName(priceOrder.getName());
        priceOrderReturnVO.setStatus(priceOrder.getStatus());
        priceOrderReturnVO.setStatusDesc(EnableStatus.getName(priceOrder.getStatus()));



        return priceOrderReturnVO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(Long parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public String getParentOrderName() {
        return parentOrderName;
    }

    public void setParentOrderName(String parentOrderName) {
        this.parentOrderName = parentOrderName;
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

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public String getSysTypeDesc() {
        return sysTypeDesc;
    }

    public void setSysTypeDesc(String sysTypeDesc) {
        this.sysTypeDesc = sysTypeDesc;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public String getPriceTypeDesc() {
        return priceTypeDesc;
    }

    public void setPriceTypeDesc(String priceTypeDesc) {
        this.priceTypeDesc = priceTypeDesc;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


}