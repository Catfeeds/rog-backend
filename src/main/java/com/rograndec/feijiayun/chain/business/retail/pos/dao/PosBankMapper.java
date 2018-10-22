package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosBank;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankVO;

public interface PosBankMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosBank record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosBank record);

    /**
     *
     * @mbg.generated
     */
    PosBank selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosBank record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosBank record);
    
    List<PosBankVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("parentId")Long parentId);
    
    Long findByCode(@Param("code")String code,@Param("enterpriseId")Long enterpriseId);
    
    Long findByName(@Param("name")String name,@Param("enterpriseId")Long enterpriseId);
    
    Long findByAccount(@Param("account")String account,@Param("enterpriseId")Long enterpriseId);
    
    Long findUpdateByName(@Param("name")String name,@Param("id")Long id,@Param("enterpriseId")Long enterpriseId);
    Long findUpdateByAccount(@Param("account")String account,@Param("id")Long id,@Param("enterpriseId")Long enterpriseId);

    List<PosBank> selectByEnterpriseId(Long enterpriseId);

    List<PosBank> selectByEnterpriseIdWithDefault(Long enterpriseId);

    /**
     *
     * @Description:验证是否有系统默认数据
     * @author yuting.li
     * @version 1.0
     * @date 2018年1月19日 下午1:59:58
     * @param enterpriseId
     * @return
     * @return Long
     */
    Long findByEidAndType(@Param("enterpriseId")Long enterpriseId);
}