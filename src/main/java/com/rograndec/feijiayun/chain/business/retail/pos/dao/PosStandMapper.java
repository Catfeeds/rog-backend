package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosStand;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandVO;
import com.rograndec.feijiayun.chain.common.SelectBean;

public interface PosStandMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosStand record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosStand record);

    /**
     *
     * @mbg.generated
     */
    PosStand selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosStand record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosStand record);
    
    List<PosStandVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId);

    /**
     * @Description: TODO获取该门店下款台
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月21日 上午11:30:33 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<SelectBean> selectStoreStandByEnterpriseId(Long enterpriseId);

	/**
	 * @Description: TODO获取该总部所有门店款台
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月21日 上午11:31:13 
	 * @param enterpriseId
	 * @return 
	 * @return List<SelectBean>
	 */
	List<SelectBean> selectHeadquartersStandByParentId(Long enterpriseId);
	
	/**
	 * 
	 * @Description: 验证mac地址
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月16日 上午11:37:17 
	 * @param enterpriseId
	 * @param mac
	 * @return 
	 * @return String
	 */
	Map<String,String> checkMac(@Param("enterpriseId")Long enterpriseId,@Param("mac")String mac);
	
	/**
	 * 
	 * @Description: 获取款台编码
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月25日 下午4:06:01 
	 * @param enterpriseId
	 * @return 
	 * @return Map<String,String>
	 */
	Map<String,String> getStandCode(@Param("enterpriseId")Long enterpriseId);
}