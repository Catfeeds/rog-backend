package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.basic.user.vo.CargoAreaVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.TeamVO;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosClerk;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkVO;

public interface PosClerkMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosClerk record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosClerk record);

    /**
     *
     * @mbg.generated
     */
    PosClerk selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosClerk record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosClerk record);
    
    List<PosClerkVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId);
    
    /**
     * 
     * @Description: 验证营业人员是否存在
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月13日 上午11:42:12 
     * @param enterpriseId
     * @param userId
     * @return 
     * @return Long
     */
    Long checkPosClerk(@Param("enterpriseId")Long enterpriseId,@Param("userId")Long userId);
    /**
     * 根据用户id获取营业人员列表
     * @param userId
     * @return
     */
    List<PosClerk> queryByUserId(Long userId);
    
    /**
     * 根据柜组id获取柜组列表
     * @param list
     * @return
     */
    List<CargoAreaVO> queryByCargoIds(List<Long> list); 
    
    /**
     * 根据班组id获取班组信息
     * @return
     */
    TeamVO getByTeamId(@Param("id") Long id);
    
    /**
     * 通过用户id删除其对应的营业人员
     * @param id
     * @return
     */
    int deleteByUserId(@Param("id") Long id);
    
}