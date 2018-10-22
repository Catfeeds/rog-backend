package com.rograndec.feijiayun.chain.business.system.approval.dao;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowDetail;
import com.rograndec.feijiayun.chain.business.system.approval.vo.ApprovalFlowDetailUdateVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @ClassName: ApprovalFlowDetailMapper  
 * @Description: TODO(审批流明细dao)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月22日 下午2:14:13  
 *
 */
public interface ApprovalFlowDetailMapper {
	
	/**
	 * 
	 * @Title: getListByFlowId  
	 * @Description: TODO(通过审批流ID查询审批流明细)  
	 * @param @param flowId
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowDetail>    返回类型  
	 * @throws
	 */
	List<ApprovalFlowDetail> getListByFlowId(long flowId);
	List<ApprovalFlowDetailUdateVO> getListByReturnFlowId(long flowId);

	List<ApprovalFlowDetail> getListByFlowIds(List<Long> list);

	/**
	 * 
	 * @Title: getByFlowIdLeval  
	 * @Description: TODO(通过flowId和level查询)  
	 * @param @param flowId
	 * @param @param leval
	 * @param @return    设定文件  
	 * @return List<ApprovalFlowDetail>    返回类型  
	 * @throws
	 */
	ApprovalFlowDetail getByFlowIdLeval(@Param("flowId") long flowId, @Param("level") int level);
	
    /**
     * 
     * @Title: deleteByPrimaryKey  
     * @Description: TODO(通过主键id删除)  
     * @param @param id
     * @param @return    设定文件  
     * @return int    返回类型  
     * @throws
     */
	int deleteByPrimaryKey(Long id);
	
	
	/**
	 * 
	 * @Title: deleteByFlowId  
	 * @Description: TODO(通过flowId删除)  
	 * @param @param flowId
	 * @param @return    设定文件  
	 * @return int    返回类型  
	 * @throws
	 */
	int deleteByFlowId(Long flowId);
    

    /**
     *
     * @mbg.generated
     */
    int insert(ApprovalFlowDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ApprovalFlowDetail record);

    /**
     *
     * @mbg.generated
     */
    ApprovalFlowDetail selectByPrimaryKey(Long id);

	List<ApprovalFlowDetail> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApprovalFlowDetail record);

    int updateByApprover(ApprovalFlowDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApprovalFlowDetail record);
}