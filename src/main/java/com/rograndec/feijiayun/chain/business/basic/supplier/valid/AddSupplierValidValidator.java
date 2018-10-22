package com.rograndec.feijiayun.chain.business.basic.supplier.valid;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSupplierValidValidator implements ConstraintValidator<AddSupplierValid,SupplierDetailVO> {

    private Logger logger = LoggerFactory.getLogger(AddSupplierValidValidator.class);

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private EnterpriseQualificationMapper enterpriseQualificationMapper;

    @Override
    public void initialize(AddSupplierValid addSupplierValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(SupplierDetailVO supplierDetailVO, ConstraintValidatorContext constraintValidatorContext) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        String must = checkSupplier(userVO.getChainType() == 0 ? userVO.getEnterpriseId() : userVO.getParentId());
        //字段校验(质量控制开启时)
        if ("1".equals(must)) {
            if (supplierDetailVO.getName() == null || "".equals(supplierDetailVO.getName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"名称不能为空!");
            }
            if (supplierDetailVO.getSaleManName() == null || "".equals(supplierDetailVO.getSaleManName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员不能为空!");
            }
            if (supplierDetailVO.getBusinessManName() == null || "".equals(supplierDetailVO.getBusinessManName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"企业负责人不能为空!");
            }
            if (supplierDetailVO.getQualityOfficerName() == null || "".equals(supplierDetailVO.getQualityOfficerName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"质量负责人不能为空!");
            }
            if (supplierDetailVO.getLegalManName() == null || "".equals(supplierDetailVO.getLegalManName())){
                throw new BusinessException(SysCode.FAIL.getCode(),"法定代表人不能为空!");
            }
            if (supplierDetailVO.getBankName() == null || "".equals(supplierDetailVO.getBankName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"开户行不能为空!");
            }
            if (supplierDetailVO.getBankAccount() == null || "".equals(supplierDetailVO.getBankAccount())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"开户账号不能为空!");
            }
            if (supplierDetailVO.getBankAccountName() == null || "".equals(supplierDetailVO.getBankAccountName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"开户户名不能为空!");
            }
            if (supplierDetailVO.getBusinessVariety() == null || "".equals(supplierDetailVO.getBusinessVariety())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"经营品种不能为空!");
            }
            if (supplierDetailVO.getBusinessScopeId() == null || "".equals(supplierDetailVO.getBusinessScopeId())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"经营范围不能为空!");
            }
            //业务信息校验
            if (supplierDetailVO.getSupplierBusinessVO().getPurchaserName() == null || "".equals(supplierDetailVO.getSupplierBusinessVO().getPurchaserName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"采购人员不能为空!");
            }
            if (supplierDetailVO.getSupplierBusinessVO().getFirst() == null || "".equals(supplierDetailVO.getSupplierBusinessVO().getFirst())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"首营企业不能为空!");
            }
            if (supplierDetailVO.getSupplierBusinessVO().getFirstCode() == null || "".equals(supplierDetailVO.getSupplierBusinessVO().getFirstCode())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"首营企业编号不能为空!");
            }
            //applicant_name  application_time
            if (supplierDetailVO.getSupplierBusinessVO().getApplicationTime() == null || "".equals(supplierDetailVO.getSupplierBusinessVO().getApplicationTime())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"申请日期不能为空!");
            }
            if (supplierDetailVO.getSupplierBusinessVO().getApplicantName() == null || "".equals(supplierDetailVO.getSupplierBusinessVO().getApplicantName())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"申请人员不能为空!");
            }
            //quality_manage_sys
            if (supplierDetailVO.getSupplierBusinessVO().getQualityManageSys() == null || "".equals(supplierDetailVO.getSupplierBusinessVO().getQualityManageSys())) {
                throw new BusinessException(SysCode.FAIL.getCode(),"质量管理体系不能为空!");
            }
            //在质量控制开启之时-- 新增得判断为空
            //在质量控制开启之时-- 修改
            if (supplierDetailVO.getId() == null){
                if (supplierDetailVO.getSupplierSalerVO() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),"销售人员不能为空!");
                }
            }
        }
        /**
         * 销售人员内部信息 不论质量控制是否开启 这些字段都是必填项
         */
        if (supplierDetailVO.getSupplierSalerVO() != null){
            if (supplierDetailVO.getSupplierSalerVO().getName() == null || "".equals(supplierDetailVO.getSupplierSalerVO().getName())){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的姓名不能为空!");
            }
            if (supplierDetailVO.getSupplierSalerVO().getIdNum() == null || "".equals(supplierDetailVO.getSupplierSalerVO().getIdNum())){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的身份证号码不能为空!");
            }
            if (supplierDetailVO.getSupplierSalerVO().getIdValidUntil() == null){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的身份证有效期至不能为空!");
            }
            if (supplierDetailVO.getSupplierSalerVO().getCertificateNum() == null || "".equals(supplierDetailVO.getSupplierSalerVO().getCertificateNum())){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的授权书号不能为空!");
            }
            if (supplierDetailVO.getSupplierSalerVO().getCertificateValidUntil() == null){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的授权书有效期至不能为空!");
            }
            if ("".equals(supplierDetailVO.getSupplierSalerVO().getAuthorizedVariety())){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的授权品种不能为空!");
            }
            if ("".equals(supplierDetailVO.getSupplierSalerVO().getAuthorizedVarietyScope())){
                throw new BusinessException(SysCode.FAIL.getCode(),"销售人员的授权品种范围不能为空!");
            }
        }

        //纳税人识别号
        if (!StringUtils.isEmpty(supplierDetailVO.getTaxpayerCode())){
            //如果填写了，必须为数字或者字母 并且数量小于等于20位
            String reg = "^[A-Za-z0-9]+$";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(supplierDetailVO.getTaxpayerCode());
            if(m.matches()){
                if (supplierDetailVO.getTaxpayerCode().length() > 20){
                    throw new BusinessException(SysCode.FAIL.getCode(),"纳税人识别号需要小于等于20位!");
                }
            }else{
                throw new BusinessException(SysCode.FAIL.getCode(),"纳税人识别号必须为数字或者字母!");
            }
        }

        //判断资质必填字段是否填写
        List<SupplierQulificationVO> supplierQulificationVO = supplierDetailVO.getSupplierQulificationVO();
        if (supplierQulificationVO != null && supplierQulificationVO.size() > 0){
            for (SupplierQulificationVO qualification : supplierQulificationVO) {
                Long qualificationId = qualification.getQualificationId();
                EnterpriseQualification q = enterpriseQualificationMapper.selectByPrimaryKey(qualificationId);
                if (q.getCodeMust() == 1 && (qualification.getQualificationCode() == null || "".equals(qualification.getQualificationCode()))){
                    throw new BusinessException(SysCode.FAIL.getCode(),q.getName() + "的资质编号为必填！");
                }
                if (q.getValidUntilMust() == 1 && (qualification.getValidUntil() == null)){
                    throw new BusinessException(SysCode.FAIL.getCode(),q.getName() + "的有效期至为必填！");
                }
                if (q.getFileMust() == 1 && (qualification.getFileId() == null)){
                    throw new BusinessException(SysCode.FAIL.getCode(),q.getName() + "的附件为必填！");
                }
                if (qualification.getStartDate() != null && qualification.getValidUntil() != null){
                    if (qualification.getValidUntil().before(qualification.getStartDate())){
                        throw new BusinessException(SysCode.FAIL.getCode(),q.getName() + "的起始日期应小于有效期至！");
                    }
                }
            }
        }
        return true;
    }

    private String checkSupplier(Long enterpriseId) {
        String type = supplierMapper.checkSupplier(enterpriseId);
        return type;
    }
}
