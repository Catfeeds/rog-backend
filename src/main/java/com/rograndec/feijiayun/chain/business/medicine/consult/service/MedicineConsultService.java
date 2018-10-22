package com.rograndec.feijiayun.chain.business.medicine.consult.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineConsultVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineGoodsVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineMemberVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * Created by dudy on 2017/10/7.
 */
public interface MedicineConsultService {
	 /**
     * 根据查询条件获取商品列表
     * */
    List<MedicineGoodsVO> getGoodsList(Map map);
    
    /**
     * 获取会员列表
     * */
    List<MedicineMemberVO> getMemberList(Map map);
    
    /**
     * 获取详情
     * */
    MedicineConsultVO getById(Long id);
    
    /**
     * 获取列表
     * */
    void getList(Page page,Map map);
    
	
	public void save(UserVO userVO,MedicineConsultVO medicineConsultVO) throws Exception,BusinessException;
	
	public void update(UserVO userVO,MedicineConsultVO medicineConsultVO) throws Exception,BusinessException;
	
	public void exportExcel(OutputStream output, Long id, UserVO loginUser);
}
