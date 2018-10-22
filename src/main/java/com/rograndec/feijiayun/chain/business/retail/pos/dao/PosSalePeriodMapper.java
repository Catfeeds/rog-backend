package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.retail.vo.QuerySaleYVO;
import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSalePeriod;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodVO;

public interface PosSalePeriodMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosSalePeriod record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosSalePeriod record);

    /**
     *
     * @mbg.generated
     */
    PosSalePeriod selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosSalePeriod record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosSalePeriod record);
    
    List<PosSalePeriodVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("parentId")Long parentId);
    
    Long findByCode(@Param("code")String code,@Param("enterpriseId")Long enterpriseId);
    
    Long findByName(@Param("name")String name,@Param("enterpriseId")Long enterpriseId);
    
    Long findUpdateByName(@Param("enterpriseId")Long enterpriseId,@Param("name")String name,@Param("id")Long id);

	List<PosSalePeriodVO> findByEnterpriseIdUseable(Long enterpriseId);
	
	/**
	 * 
	 * @Description: 获取最大时间
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月24日 下午4:02:17 
	 * @param enterpriseId
	 * @return 
	 * @return Map<String,String>
	 */
	Map<String, String> findMaxTime(@Param("enterpriseId")Long enterpriseId);

    List<QuerySaleYVO> findByEnterpriseIdX(@Param("enterpriseId")Long enterpriseId);

    List<PosSalePeriod> selectByEnterpriseId(Long enterpriseId);
}