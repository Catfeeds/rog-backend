package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserStatusEum;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.constant.LimitVarietyType;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel
public class SupplierSalerReturnVO implements Serializable {
	
	 /**
     *   （0 - 否  1- 是）
     */
	@ApiModelProperty(value = "（0 - 否  1- 是）")
    private Integer franchisedStoreFlag;
	 private Long eId;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 供货单位归属企业id
     */
    private Long ownerId;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "销售人员编码")
    private String code;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "销售人员姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 身份证有效期至
     */
    @ApiModelProperty(value = "身份证有效期至")
    private Date idValidUntil;


    @ApiModelProperty(value = "身份证有效期至描述")
    private String idValidUntilDesc;

    /**
     * 身份证附件ID
     */
    @ApiModelProperty(value = "身份证附件ID")
    private Long idFileId;

    @ApiModelProperty(value = "身份证附件名称")
    private String idFileName;

    /**
     * 授权书号
     */
    @ApiModelProperty(value = "授权书号")
    private String certificateNum;

    /**
     * 授权书有效期至
     */
    @ApiModelProperty(value = "授权书有效期至")
    private Date certificateValidUntil;

    @ApiModelProperty(value = "授权书有效期至描述")
    private String certificateValidUntilDesc;

    /**
     * 授权品种ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "授权品种ID集合多个用逗号分隔）")
    private String authorizedVariety;

    @ApiModelProperty(value = "授权品种ID（多个用逗号分隔）")
    private  String authorizedVarietyDesc;

    /**
     * 授权品种范围ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "授权品种范围ID集合多个用逗号分隔）")
    private String authorizedVarietyScope;

    @ApiModelProperty(value = "授权品种范围ID（多个用逗号分隔）")
    private String authorizedVarietyScopeDesc;

    @ApiModelProperty(value = "授权地域")
    private String authorizedRegion;

    /**
     * 授权书附件ID
     */
    @ApiModelProperty(value = "授权书附件ID")
    private Long certificateFileId;

    @ApiModelProperty(value = "授权书附件name")
    private String certificateFileName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String telephone;

    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String fax;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信")
    private String wechatNum;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qqNum;

    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = "状态（0-离职；1-在职）")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否所有者
     */
    @ApiModelProperty(value = "是否所有者，0否1是(控制可编辑)")
    private Integer isOwner;



    public static SupplierSalerReturnVO getSupplierSalerReturnVO(SupplierSaler supplierSaler, List<Supplier> suppliers
    ,List<File> files,List<BusinessScope> businessScopes){

        SupplierSalerReturnVO returnVO = new SupplierSalerReturnVO();



        returnVO.setId(supplierSaler.getId());
        returnVO.setSupplierId(supplierSaler.getSupplierId());
        for(Supplier supplier : suppliers){
            if(supplier.getId().equals(supplierSaler.getSupplierId())){
                returnVO.setSupplierName(supplier.getName());
                returnVO.setSupplierCode(supplier.getCode());
                returnVO.setEnterpriseId(supplier.getEnterpriseId());
                returnVO.setCode(supplierSaler.getCode());
                returnVO.setName(supplierSaler.getName());
                returnVO.setOwnerId(supplier.getOwnerId());
            }
        }

        returnVO.setIdNum(supplierSaler.getIdNum());
        returnVO.setIdValidUntil(supplierSaler.getIdValidUntil());
        returnVO.setIdValidUntilDesc(DateUtils.DateToString(supplierSaler.getIdValidUntil(),DateUtils.FMT_DATE_TIME));
        returnVO.setIdFileId(supplierSaler.getIdFileId());
        if(null != supplierSaler.getIdFileId()){
            for(File idFile : files){
                if(supplierSaler.getIdFileId().equals(idFile.getId())){
                    returnVO.setIdFileName(idFile.getFileName());
                }
            }
        }

        returnVO.setCertificateNum(supplierSaler.getCertificateNum());
        returnVO.setCertificateValidUntil(supplierSaler.getCertificateValidUntil());
        returnVO.setCertificateValidUntilDesc(DateUtils.DateToString(supplierSaler.getCertificateValidUntil(),DateUtils.FMT_DATE_TIME));



        String authorVarietyStrs = supplierSaler.getAuthorizedVariety();
        List<Long> llrs = StringSplit.strSplit(authorVarietyStrs);
        returnVO.setAuthorizedVariety(authorVarietyStrs);
        for(Long ls : llrs){
            LimitVarietyType type = LimitVarietyType.getLimitVarietyType4Code(ls.intValue());
            if(StringUtils.isEmpty(returnVO.getAuthorizedVarietyDesc())){
                returnVO.setAuthorizedVarietyDesc(type.getName());
            }else {
                returnVO.setAuthorizedVarietyDesc(
                        StringUtils.isEmpty(returnVO.getAuthorizedVarietyDesc()) ? "" : returnVO.getAuthorizedVarietyDesc()
                                + "," + type.getName()
                );
            }
        }

        String limitVarietyRangeStrs = supplierSaler.getAuthorizedVarietyScope();
        List<Long> llrss = StringSplit.strSplit(limitVarietyRangeStrs);
        returnVO.setAuthorizedVarietyScope(limitVarietyRangeStrs);
        for(Long ls : llrss){
            for(BusinessScope businessScope : businessScopes){
                if(ls.equals(businessScope.getId())){
                    if(StringUtils.isEmpty(returnVO.getAuthorizedVarietyScopeDesc())){
                        returnVO.setAuthorizedVarietyScopeDesc(businessScope.getName());
                    }else {
                        returnVO.setAuthorizedVarietyScopeDesc(
                                StringUtils.isEmpty(returnVO.getAuthorizedVarietyScopeDesc()) ? "" : returnVO.getAuthorizedVarietyScopeDesc()
                                + "," + businessScope.getName()
                        );
                    }
                }
            }
        }

        returnVO.setAuthorizedRegion(supplierSaler.getAuthorizedRegion());
        returnVO.setCertificateFileId(supplierSaler.getCertificateFileId());

        if(null != supplierSaler.getCertificateFileId()){
            for(File certificateFile : files){
                if(supplierSaler.getCertificateFileId().equals(certificateFile.getId())){
                    returnVO.setCertificateFileName(certificateFile.getFileName());
                }
            }
        }



        returnVO.setTelephone(supplierSaler.getTelephone());
        returnVO.setFax(supplierSaler.getFax());
        returnVO.setMobilePhone(supplierSaler.getMobilePhone());
        returnVO.setEmail(supplierSaler.getEmail());
        returnVO.setWechatNum(supplierSaler.getWechatNum());
        returnVO.setQqNum(supplierSaler.getQqNum());
        returnVO.setStatus(supplierSaler.getStatus());
        returnVO.setStatusDesc(UserStatusEum.getUserStatusEum4Code(supplierSaler.getStatus()).getValue());
        returnVO.setRemark(supplierSaler.getRemark());

        returnVO.seteId(supplierSaler.getEnterpriseId());
        return returnVO;
    }

    /**
     * saas_supplier_saler
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
     * 供货单位ID
     * @return supplier_id 供货单位ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 供货单位ID
     * @param supplierId 供货单位ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 身份证号
     * @return id_num 身份证号
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * 身份证号
     * @param idNum 身份证号
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    /**
     * 身份证有效期至
     * @return id_valid_until 身份证有效期至
     */
    public Date getIdValidUntil() {
        return idValidUntil;
    }

    /**
     * 身份证有效期至
     * @param idValidUntil 身份证有效期至
     */
    public void setIdValidUntil(Date idValidUntil) {
        this.idValidUntil = idValidUntil;
    }

    /**
     * 身份证附件ID
     * @return id_file_id 身份证附件ID
     */
    public Long getIdFileId() {
        return idFileId;
    }

    /**
     * 身份证附件ID
     * @param idFileId 身份证附件ID
     */
    public void setIdFileId(Long idFileId) {
        this.idFileId = idFileId;
    }

    /**
     * 授权书号
     * @return certificate_num 授权书号
     */
    public String getCertificateNum() {
        return certificateNum;
    }

    /**
     * 授权书号
     * @param certificateNum 授权书号
     */
    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    /**
     * 授权书有效期至
     * @return certificate_valid_until 授权书有效期至
     */
    public Date getCertificateValidUntil() {
        return certificateValidUntil;
    }

    /**
     * 授权书有效期至
     * @param certificateValidUntil 授权书有效期至
     */
    public void setCertificateValidUntil(Date certificateValidUntil) {
        this.certificateValidUntil = certificateValidUntil;
    }

    public String getAuthorizedVariety() {
        return authorizedVariety;
    }

    public void setAuthorizedVariety(String authorizedVariety) {
        this.authorizedVariety = authorizedVariety;
    }

    public String getAuthorizedVarietyScope() {
        return authorizedVarietyScope;
    }

    public void setAuthorizedVarietyScope(String authorizedVarietyScope) {
        this.authorizedVarietyScope = authorizedVarietyScope;
    }

    public String getAuthorizedVarietyDesc() {
        return authorizedVarietyDesc;
    }

    public void setAuthorizedVarietyDesc(String authorizedVarietyDesc) {
        this.authorizedVarietyDesc = authorizedVarietyDesc;
    }


    public String getAuthorizedVarietyScopeDesc() {
        return authorizedVarietyScopeDesc;
    }

    public void setAuthorizedVarietyScopeDesc(String authorizedVarietyScopeDesc) {
        this.authorizedVarietyScopeDesc = authorizedVarietyScopeDesc;
    }

    /**
     * 授权书附件ID
     * @return certificate_file_id 授权书附件ID
     */
    public Long getCertificateFileId() {
        return certificateFileId;
    }

    /**
     * 授权书附件ID
     * @param certificateFileId 授权书附件ID
     */
    public void setCertificateFileId(Long certificateFileId) {
        this.certificateFileId = certificateFileId;
    }

    /**
     * 电话
     * @return telephone 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 电话
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 传真
     * @return fax 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 传真
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 手机
     * @return mobile_phone 手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机
     * @param mobilePhone 手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 微信
     * @return wechat_num 微信
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * 微信
     * @param wechatNum 微信
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum == null ? null : wechatNum.trim();
    }

    /**
     * QQ
     * @return qq_num QQ
     */
    public String getQqNum() {
        return qqNum;
    }

    /**
     * QQ
     * @param qqNum QQ
     */
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum == null ? null : qqNum.trim();
    }

    /**
     * 状态（0-离职；1-在职）
     * @return status 状态（0-离职；1-在职）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-离职；1-在职）
     * @param status 状态（0-离职；1-在职）
     */
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getIdValidUntilDesc() {
        return idValidUntilDesc;
    }

    public void setIdValidUntilDesc(String idValidUntilDesc) {
        this.idValidUntilDesc = idValidUntilDesc;
    }

    public String getIdFileName() {
        return idFileName;
    }

    public void setIdFileName(String idFileName) {
        this.idFileName = idFileName;
    }

    public String getCertificateValidUntilDesc() {
        return certificateValidUntilDesc;
    }

    public void setCertificateValidUntilDesc(String certificateValidUntilDesc) {
        this.certificateValidUntilDesc = certificateValidUntilDesc;
    }

    public String getCertificateFileName() {
        return certificateFileName;
    }

    public void setCertificateFileName(String certificateFileName) {
        this.certificateFileName = certificateFileName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Integer isOwner) {
        this.isOwner = isOwner;
    }

    public String getAuthorizedRegion() {
        return authorizedRegion;
    }

    public void setAuthorizedRegion(String authorizedRegion) {
        this.authorizedRegion = authorizedRegion;
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
        sb.append(", supplierId=").append(supplierId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", idNum=").append(idNum);
        sb.append(", idValidUntil=").append(idValidUntil);
        sb.append(", idFileId=").append(idFileId);
        sb.append(", certificateNum=").append(certificateNum);
        sb.append(", certificateValidUntil=").append(certificateValidUntil);
        sb.append(", authorizedVariety=").append(authorizedVariety);
        sb.append(", authorizedVarietyScope=").append(authorizedVarietyScope);
        sb.append(", certificateFileId=").append(certificateFileId);
        sb.append(", telephone=").append(telephone);
        sb.append(", fax=").append(fax);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", email=").append(email);
        sb.append(", wechatNum=").append(wechatNum);
        sb.append(", qqNum=").append(qqNum);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

	public Integer getFranchisedStoreFlag() {
		return franchisedStoreFlag;
	}

	public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
		this.franchisedStoreFlag = franchisedStoreFlag;
	}

	public Long geteId() {
		return eId;
	}

	public void seteId(Long eId) {
		this.eId = eId;
	}
    
}