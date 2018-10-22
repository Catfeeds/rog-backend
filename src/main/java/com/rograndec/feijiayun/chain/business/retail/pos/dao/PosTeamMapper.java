package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosTeam;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSelectVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamVO;
import com.rograndec.feijiayun.chain.common.SelectBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PosTeamMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosTeam record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosTeam record);

    /**
     *
     * @mbg.generated
     */
    PosTeam selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosTeam record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosTeam record);
    
    List<PosTeamVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId);

   	PosTeamVO findByEnterpriseIdAndDef(@Param("enterpriseId")Long enterpriseId);

    Long findByCode(@Param("code")String code,@Param("enterpriseId")Long enterpriseId);
    
    Long findByName(@Param("name")String code,@Param("enterpriseId")Long enterpriseId);
    Long findUpdateByName(@Param("enterpriseId")Long enterpriseId,@Param("name")String name,@Param("id")Long id);

    /**
     * @Description: TODO获取该门店下班组
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月21日 上午11:42:51 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<SelectBean> selectStoreTeamByEnterpriseId(Long enterpriseId);

	/**
	 * @Description: TODO获取该总部下班组
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月21日 上午11:43:46 
	 * @param enterpriseId
	 * @return 
	 * @return List<SelectBean>
	 */
	List<SelectBean> selectHeadquartersTeamByParentId(Long enterpriseId);
	
	/**
	 * 
	 * @Description: 选择班组下拉框
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月21日 下午5:57:22 
	 * @param enterpriseId
	 * @return 
	 * @return List<PosTeamSelectVO>
	 */
	List<PosTeamSelectVO> selectPosTeam(@Param("enterpriseId")Long enterpriseId);

    String getNameByPrimaryKey(Long id);
}