package com.rograndec.feijiayun.chain.business.goods.pharmacy.service;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityVO3;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SaveIncompatibilityVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/7.
 */
public interface IncompatibilityService {


    List<IncompatibilityGoodsVO> getIncompatibilityGoodsByGoodsId(UserVO loginUser,String goodsId);

    List<SelectGoodsVO> selectGoodsVoPage(int pageNo, int pageSize, String key, Long enterpriseId, Page page,UserVO userVO);

    int deleteIncompatibility(Long id,UserVO loginUser) throws Exception;

    List<IncompatibilityVO3> incompatibilityVoPage(int pageNo, int pageSize, String key, Long enterpriseId, Page page, Long type, String orderType,UserVO loginUser);

    void saveIncompatibility(UserVO loginUser, SaveIncompatibilityVO saveIncompatibilityVO) throws Exception;

    void updateIncompatibilitys(UserVO loginUser, SaveIncompatibilityVO saveIncompatibilityVO) throws Exception;
}
