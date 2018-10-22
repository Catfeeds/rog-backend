package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrListTotalVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrListVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrSendReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrSend;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DistrSendMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrSend record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrSend record);

    /**
     *
     * @mbg.generated
     */
    DistrSend selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrSend record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrSend record);

    /**
     * 查询搜索结果的合计金额
     * @param map
     * @return
     */
    DistrListTotalVO getSearchResultTotal(Map<String, Object> map);

    /**
     * 查询配货单列表
     * @param map
     * @return
     */
    List<DistrListVO> getDistrSendPage(Map<String, Object> map);

	int getDistrSendListCount(RequestDistrSend requestDistrSend);

    List<DistrSendReportVo> getDistrSendList(RequestDistrSend requestDistrSend);

    void updateById(@Param("baseOrderId")Long baseOrderId, @Param("enterpriseId")Long enterpriseId,  @Param("status")Integer status);

    List<DistrListVO> getDistrSendOrDistrOutPage(Map map);

	Long getStayDistrPickTotalRecord(DistrSendRequestVO condition);

	List<DistrSendVO> selectStayDistrPickPage(DistrSendRequestVO condition);

    Integer getDistrSendOrDistrOutPageCount(Map map);
}