package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrListTotalVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrListVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.RequestDistrOutPram;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrOutReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DistrOutMapper {
	/**
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int insert(DistrOut record);

	/**
	 * @mbg.generated
	 */
	int insertSelective(DistrOut record);

	/**
	 * @mbg.generated
	 */
	DistrOut selectByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(DistrOut record);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKey(DistrOut record);

	/**
	 * <描述>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/8 9:51
	 */
	int countDistrOutDataList(RequestDistrOutPram requestDistrOutPram);

	/**
	 * <描述>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/8 9:51
	 */
	List<DistrListVO> getDistrOutDataList(RequestDistrOutPram requestDistrOutPram);

	/**
	 * <根据查询参数获取合计金额,不含税合计金额,>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/10 13:19
	 */
	DistrListTotalVO getSumPrice(RequestDistrOutPram requestDistrOutPram);

	int getDistrOutListCount(RequestDistrOut requestDistrOut);

	List<DistrOutReportVo> getDistrOutList(RequestDistrOut requestDistrOut);

    void deleteByOutId(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

	DistrOut selectByBaseOrderId(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId);

    DistrListTotalVO getSearchResultTotalBack(Map map);
	/**
	 * 查询总部应收加盟店账款未开票数据记录数
	 * @param map
	 * @return
	 */
    Integer getReceivablePendingInvoicingCount(Map<String, Object> map);
	/**
	 * 查询总部应收加盟店账款未开票分页数据
	 * @param map
	 * @return
	 */
	List<PendingInvoicingVO> getReceivablePendingInvoicingList(Map<String, Object> map);
}