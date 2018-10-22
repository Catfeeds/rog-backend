package com.rograndec.feijiayun.chain.business.goods.pharmacy.service.impl;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.PharmacySetMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.PharmacySetService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * Created by zeshi.sun on 2017/9/9.
 */
@Service
public class PharmacySetServiceImpl implements PharmacySetService {

    @Autowired
    private PharmacySetMapper pharmacySetMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PharmacySetVO> getPharmacySet(Long enterpriseId,Long type) {
        List<PharmacySetVO> pharmacySet = pharmacySetMapper.selectAll(enterpriseId,type);
        for(PharmacySetVO pharmacySetVO : pharmacySet){
            int status = pharmacySetVO.getStatus();
            pharmacySetVO.setStatusName(EnableStatus.getName(status));

            //先检查是否删除期初数据
            Map<String,Long> param = new HashMap();
            param.put("id",pharmacySetVO.getId());
            param.put("enterpriseId",enterpriseId);
            param.put("type",type);
            List<PrescriptionRegister> prescriptionRegister = pharmacySetMapper.selectByIdType(param);
            if(pharmacySetVO.getSysType() == 0){
                if(prescriptionRegister != null && prescriptionRegister.size() > 0 && pharmacySetVO.getSysType() == 0){
                    pharmacySetVO.setDeleteFlag(false);
                } else {
                    pharmacySetVO.setDeleteFlag(true);
                }
            } else {
                pharmacySetVO.setDeleteFlag(false);
            }


        }
        return pharmacySet == null ? new ArrayList<>() : pharmacySet;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int insertPharmacySet(UserVO loginUser, PharmacySet pharmacySet) throws Exception ,BusinessException {

        if(ChineseString.isChinese(pharmacySet.getCode())){
            throw new BusinessException("编码中存在中文,无法保存");
        }
        if(pharmacySet.getCode().trim().equals("") || pharmacySet.getCode() == null || pharmacySet.getName().trim().equals("") || pharmacySet.getName() == null){
            throw new BusinessException("编码与名称不能为空,请重新填写");
        }
        UserEnterpriseUtils.setUserCreateOrModify(pharmacySet, loginUser, true);
        pharmacySet.setCreateTime(new Date());
        pharmacySet.setEnterpriseId(loginUser.getEnterpriseId());
        pharmacySet.setSysType(0);//新增数据一律为0  0-自定义
        pharmacySet.setId(null);
        return pharmacySetMapper.insertSelective(pharmacySet);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePharmacySet(UserVO loginUser, PharmacySet pharmacySetS) throws Exception,BusinessException {

        Map params = new HashMap();
        params.put("names",pharmacySetS.getName());
        params.put("setTypes",pharmacySetS.getSetType());
        params.put("enterpriseId",loginUser.getEnterpriseId());
        List<PharmacySet> pharmacys = pharmacySetMapper.selectByCodes(params);
        if(!pharmacys.isEmpty()){
            for(PharmacySet pharmacy : pharmacys){
                if(pharmacySetS.getName().equals(pharmacy.getName())){
                    throw new BusinessException("名称重复,无法保存");
                }
            }
        }


            List<PrescriptionRegister> prescriptionRegisters = pharmacySetMapper.getByIdType(pharmacySetS.getId(),loginUser.getEnterpriseId(), Long.valueOf(pharmacySetS.getSetType()));
            Iterator<PrescriptionRegister> it = prescriptionRegisters.iterator();
            while ((it.hasNext())){
                if(it.next().getId().equals(pharmacySetS.getId())){
                    it.remove();
                }
            }
            if (!prescriptionRegisters.isEmpty()){
                throw new BusinessException("已被处方单据引用,无法修改");
            }





        UserEnterpriseUtils.setUserCreateOrModify(pharmacySetS, loginUser, false);
        pharmacySetS.setUpdateTime(new Date());
        return pharmacySetMapper.updateByPrimaryKeySelective(pharmacySetS);
    }

    @Override
    public void canDelete(Long id, Long enterpriseId, Long type) throws Exception{
        //先检查是否删除期初数据
        Map<String,Long> param = new HashMap();
        param.put("id",id);
        param.put("enterpriseId",enterpriseId);
        param.put("type",type);
        List<PharmacySet> pharmacySet = pharmacySetMapper.selectByTypeId(param);
        if(!pharmacySet.isEmpty()) {
            throw new BusinessException("系统默认数据,无法删除");
        }else {
            List<PrescriptionRegister> prescriptionRegister = pharmacySetMapper.selectByIdType(param);
            if (!prescriptionRegister.isEmpty())
                throw new BusinessException("编码已使用,无法删除");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePharmacySet(Long id) throws Exception {
        return pharmacySetMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void canSave(PharmacySet pharmacySet, UserVO loginUser) throws Exception ,BusinessException{

        Long enterpriseId = loginUser.getEnterpriseId();
        Long setType = Long.valueOf(pharmacySet.getSetType());
        String code = pharmacySet.getCode();
        String name = pharmacySet.getName();
        Map param = new HashMap();
        param.put("codes",code);
        param.put("names",name);
        param.put("setTypes",setType);
        param.put("enterpriseId",enterpriseId);
        List<PharmacySet> pharmacy = pharmacySetMapper.selectByCode(param);
        if(!pharmacy.isEmpty())
            throw new BusinessException("编码重复,无法保存");
    }



}
