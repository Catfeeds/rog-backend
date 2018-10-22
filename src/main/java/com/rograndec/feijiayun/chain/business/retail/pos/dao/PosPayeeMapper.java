package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayee;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeVO;

public interface PosPayeeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosPayee record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosPayee record);

    /**
     *
     * @mbg.generated
     */
    PosPayee selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosPayee record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosPayee record);

    /**
     * @Description: 根据收款人员ID获取收款人员表数据
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月20日 下午3:58:00 
     * @param payeeId
     * @return 
     * @return PosPayee
     */
	List<PosPayee> selectPosPayeeByPayeeId(Long payeeId);
	
	List<PosPayeeVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId);
	
	/**
	 * 
	 * @Description: 收款人员id查询数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月13日 上午11:07:41 
	 * @param enterpriseId
	 * @param payeeId
	 * @return 
	 * @return Map<String,Object>
	 */
	Map<String, Object> getPayeeIdAndEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("payeeId")Long payeeId);

    List<PosPayee> selectByEnterpriseIdByTeamId(@Param("enterpriseId")Long enterpriseId, @Param("teamId")Long teamId);
    
    /**
    *
    *根据用户id获取收款员信息
    */
   List<PosPayee> selectByUserId(Long userId);
   
   /**
   *根据用户id删除收款员信息
   */
  int deleteByUserId(@Param("id") Long id);
}