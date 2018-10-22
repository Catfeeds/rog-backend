package com.rograndec.feijiayun.chain.business.medicine.consult.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.EnterpriseReqPlanVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.entity.MedicineConsult;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineConsultVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineGoodsVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineMemberVO;

public interface MedicineConsultMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MedicineConsult record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MedicineConsult record);

    /**
     *
     * @mbg.generated
     */
    MedicineConsult selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MedicineConsult record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MedicineConsult record);
    
    
    /**
     * 获取详情
     * */
    MedicineConsultVO selectById(Long id);
    
    /**
     * 获取列表
     * */
    List<MedicineConsultVO> selectList(Map map);
    Long selectCount(Map map);
    
    /**
     * 获取会员列表
     * */
   List<MedicineMemberVO> selectMembers(Map map);
   
   /**
    * 获取会员信息
    * */
   MedicineMemberVO selectMember(Map map);
    /**
     * 获取商品列表
     * */
   List<MedicineGoodsVO> selectGoods(Map map);
}