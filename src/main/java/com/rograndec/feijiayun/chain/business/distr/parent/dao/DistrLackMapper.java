package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.RequestDistrLackPram;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrLackReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrLack;

import java.util.List;
import java.util.Map;

public interface DistrLackMapper {
	/**
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int insertSelective(DistrLack record);

	/**
	 * @mbg.generated
	 */
	DistrLack selectByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(DistrLack record);

	/**
	 * <获取数据数量>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/11 19:36
	 */
	int countDistrLackData(RequestDistrLackPram requestDistrLackPram);

	/**
	 * <获取缺配单列表数据>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/11 19:36
	 */
	List<DistrLack> getDistrLackData(RequestDistrLackPram requestDistrLackPram);

    /**
     * 总部配货单-调用
     * @param map
     * @return
     */
    List<DistrLack> getDistrLackListByEnterpriseId(Map<String, Object> map);

	int getDistrLackListCount(RequestDistrLack requestDistrLack);

	List<DistrLackReportVo> getDistrLackList(RequestDistrLack requestDistrLack);
}