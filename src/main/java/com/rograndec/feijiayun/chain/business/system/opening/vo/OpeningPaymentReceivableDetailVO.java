package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.*;

/**
 * 期初应付（期初应收）详情vo
 *
 * @author rograndec
 * <p>
 * 2018-01-03
 */
public class OpeningPaymentReceivableDetailVO implements Serializable {


    /**
     * 单位ID
     */
    @ApiModelProperty(value = "供货(购货)单位ID", required = true)
    @NotNull(message = "供货(购货)单位ID不能为空")
    private Long id;

    /**
     * 单位编码
     */
    @ApiModelProperty(value = "供货(购货)单位编码", required = true)
    @NotBlank(message = "供货(购货)单位编码不能为空")
    private String code;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "供货(购货)单位名称", required = true)
    @NotBlank(message = "供货(购货)单位名称不能为空")
    private String name;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", required = true)
    @NotBlank(message = "金额不能为空")
    @Pattern(regexp = "^(\\-)?(([1-9][0-9]*)|0)(\\.[0-9]{2})$",message = "金额保留两位小数")
    private String amount;

    /**
     * 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
    @ApiModelProperty(hidden = true)
    private Integer financeAccountType;


    /**
     * saas_opening_payment_detail
     */
    private static final long serialVersionUID = 1L;


    /**
     * 单位ID
     *
     * @return id 单位ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 单位ID
     *
     * @param id 单位ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 单位编码
     *
     * @return code 单位编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单位编码
     *
     * @param code 单位编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单位名称
     *
     * @return name 单位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 单位名称
     *
     * @param name 单位名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 金额
     *
     * @return amount 金额
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 金额
     *
     * @param amount 金额
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getFinanceAccountType() {
        return financeAccountType;
    }

    public void setFinanceAccountType(Integer financeAccountType) {
        this.financeAccountType = financeAccountType;
    }

    /**
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
        sb.append(", amount=").append(amount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    /**
     * Map<String,OpeningPaymentReceivableDetailVO> key 为供应商编码code
     *
     * @param openingPaymentDetailCacheVOS
     * @return
     */
    public static Map<String, OpeningPaymentReceivableDetailVO> getMap(List<OpeningPaymentReceivableDetailVO> openingPaymentDetailCacheVOS) {
        Map<String, OpeningPaymentReceivableDetailVO> paymentDetailVOMap = null;
        if (!CollectionUtils.isEmpty(openingPaymentDetailCacheVOS)) {
            paymentDetailVOMap = new HashMap<>();
            for (OpeningPaymentReceivableDetailVO openingPaymentDetailVO : openingPaymentDetailCacheVOS) {
                paymentDetailVOMap.put(openingPaymentDetailVO.getCode(), openingPaymentDetailVO);
            }
        }
        return paymentDetailVOMap;
    }
}