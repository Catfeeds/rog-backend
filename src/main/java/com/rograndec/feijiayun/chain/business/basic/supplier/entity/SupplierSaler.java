package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.AddSupplierSalerVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplierSaler implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 供货单位ID
     */
    private Long supplierId;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;



    /**
     * 编码
     */
    private String code;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 身份证有效期至
     */
    private Date idValidUntil;

    /**
     * 身份证附件ID
     */
    private Long idFileId;

    /**
     * 授权书号
     */
    private String certificateNum;

    /**
     * 授权书有效期至
     */
    private Date certificateValidUntil;

    /**
     * 授权品种ID（多个用逗号分隔）
     */
    private String authorizedVariety;

    /**
     * 授权品种范围ID（多个用逗号分隔）
     */
    private String authorizedVarietyScope;

    /**
     * 授权地域
     */
    private String authorizedRegion;

    /**
     * 授权书附件ID
     */
    private Long certificateFileId;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 手机
     */
    private String mobilePhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String wechatNum;

    /**
     * QQ
     */
    private String qqNum;

    /**
     * 状态（0-离职；1-在职）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_supplier_saler
     */
    private static final long serialVersionUID = 1L;


    public static List<Long> getSupplierIds(List<SupplierSaler> supplierSalers){
        List<Long> ids = new ArrayList<>();
        for (SupplierSaler supplierSaler : supplierSalers){
            ids.add(supplierSaler.getSupplierId());
        }

        return ids;
    }

    public static List<Long> getCertificateFileIds(List<SupplierSaler> supplierSalers){
        List<Long> ids = new ArrayList<>();

        for(SupplierSaler supplierSaler : supplierSalers){
            ids.add(supplierSaler.getCertificateFileId());
        }

        return ids;
    }

    public static List<Long> getIdFiles(List<SupplierSaler> supplierSalers){
        List<Long> ids = new ArrayList<>();

        for(SupplierSaler supplierSaler : supplierSalers){
            ids.add(supplierSaler.getIdFileId());
        }

        return ids;
    }


    public static SupplierSaler getSupplierSaler4VO(UserVO userVO ,AddSupplierSalerVO addSupplierSalerVO,boolean isAdd){
        SupplierSaler supplierSaler = new SupplierSaler();

        supplierSaler.setId(addSupplierSalerVO.getId());
        supplierSaler.setSupplierId(addSupplierSalerVO.getSupplierId());
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
        supplierSaler.setEnterpriseId(headEnterpriseId);
        supplierSaler.setName(addSupplierSalerVO.getName());
        supplierSaler.setIdNum(addSupplierSalerVO.getIdNum());
        supplierSaler.setIdValidUntil(addSupplierSalerVO.getIdValidUntil());
        supplierSaler.setIdFileId(addSupplierSalerVO.getIdFileId());
        supplierSaler.setCertificateNum(addSupplierSalerVO.getCertificateNum());

        supplierSaler.setCertificateValidUntil(addSupplierSalerVO.getCertificateValidUntil());
        supplierSaler.setAuthorizedVariety(addSupplierSalerVO.getAuthorizedVariety());
        supplierSaler.setAuthorizedVarietyScope(addSupplierSalerVO.getAuthorizedVarietyScope());
        supplierSaler.setCertificateFileId(addSupplierSalerVO.getCertificateFileId());
        supplierSaler.setAuthorizedRegion(addSupplierSalerVO.getAuthorizedRegion());
        supplierSaler.setTelephone(addSupplierSalerVO.getTelephone());
        supplierSaler.setFax(addSupplierSalerVO.getFax());
        supplierSaler.setMobilePhone(addSupplierSalerVO.getMobilePhone());
        supplierSaler.setEmail(addSupplierSalerVO.getEmail());
        supplierSaler.setWechatNum(addSupplierSalerVO.getWechatNum());
        supplierSaler.setQqNum(addSupplierSalerVO.getQqNum());
        supplierSaler.setStatus(addSupplierSalerVO.getStatus());
        supplierSaler.setRemark(addSupplierSalerVO.getRemark());

        if(isAdd){
            supplierSaler.setCreaterId(userVO.getUserId());
            supplierSaler.setCreaterCode(userVO.getUserCode());
            supplierSaler.setCreaterName(userVO.getUserName());
            supplierSaler.setCreateTime(new Date());
            supplierSaler.setModifierId(userVO.getUserId());
            supplierSaler.setModifierCode(userVO.getUserCode());
            supplierSaler.setModifierName(userVO.getUserName());
            supplierSaler.setUpdateTime(new Date());
        }else {
            supplierSaler.setModifierId(userVO.getUserId());
            supplierSaler.setModifierCode(userVO.getUserCode());
            supplierSaler.setModifierName(userVO.getUserName());
            supplierSaler.setUpdateTime(new Date());
        }

        return supplierSaler;
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

    /**
     * 授权品种ID（多个用逗号分隔）
     * @return authorized_variety 授权品种ID（多个用逗号分隔）
     */
    public String getAuthorizedVariety() {
        return authorizedVariety;
    }

    /**
     * 授权品种ID（多个用逗号分隔）
     * @param authorizedVariety 授权品种ID（多个用逗号分隔）
     */
    public void setAuthorizedVariety(String authorizedVariety) {
        this.authorizedVariety = authorizedVariety == null ? null : authorizedVariety.trim();
    }

    /**
     * 授权品种范围ID（多个用逗号分隔）
     * @return authorized_variety_scope 授权品种范围ID（多个用逗号分隔）
     */
    public String getAuthorizedVarietyScope() {
        return authorizedVarietyScope;
    }

    /**
     * 授权品种范围ID（多个用逗号分隔）
     * @param authorizedVarietyScope 授权品种范围ID（多个用逗号分隔）
     */
    public void setAuthorizedVarietyScope(String authorizedVarietyScope) {
        this.authorizedVarietyScope = authorizedVarietyScope == null ? null : authorizedVarietyScope.trim();
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

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}