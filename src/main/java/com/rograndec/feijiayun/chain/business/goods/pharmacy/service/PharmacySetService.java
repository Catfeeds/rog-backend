package com.rograndec.feijiayun.chain.business.goods.pharmacy.service;


import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/9.
 */

public interface PharmacySetService {

    List<PharmacySetVO> getPharmacySet(Long enterpriseId,Long type);

    int insertPharmacySet(UserVO loginUser, PharmacySet pharmacySet) throws Exception;

    int updatePharmacySet(UserVO loginUser, PharmacySet pharmacySet) throws Exception;

    void canDelete(Long id, Long enterpriseId, Long type) throws Exception;

    int deletePharmacySet(Long id) throws Exception;

    void canSave(PharmacySet pharmacySet, UserVO loginUser) throws Exception;
}
