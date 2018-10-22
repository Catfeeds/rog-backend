package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayType;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;

public interface PosPayTypeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosPayType record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosPayType record);

    /**
     *
     * @mbg.generated
     */
    PosPayType selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosPayType record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosPayType record);
    
    List<PosPayTypeVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("parentId")Long parentId);

    List<PosPayTypeVO> findByEnterpriseIdAndParentId(@Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);
    
    Long findByCode(@Param("code")String code,@Param("enterpriseId")Long enterpriseId);
    
    Long findByName(@Param("name")String code,@Param("enterpriseId")Long enterpriseId);
    
    Long findUpdateByName(@Param("enterpriseId")Long enterpriseId,@Param("name")String code,@Param("id")Long id);
    
    Long findShortcutKey(@Param("enterpriseId")Long enterpriseId,@Param("shortcutKey")int shortcutKey);
    
    Long findUpdateShortcutKey(@Param("enterpriseId")Long enterpriseId,@Param("shortcutKey")int shortcutKey,@Param("id")Long id);

    List<PosPayType> selectByEnterpriseId(Long enterpriseId);
    
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