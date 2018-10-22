package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import java.io.OutputStream;
import java.util.Date;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsRequestParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @ClassName: LagSaleReportService   
 * @Description: 滞销商品
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月21日 上午11:22:27
 */
public interface LagSaleReportService {
	
	/**
	 * 
	 * @Description: 计算滞销天数
	 * 滞销天数 = 当前时间 - (最后一次销售时间 + 滞销周期)
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月25日 下午3:17:44 
	 * @param lastSaleDate 最后一次销售时间
	 * @param unsalableCycle 滞销周期
	 * @param unsalableCycleUnit 滞销周期单位
	 * @return 
	 * @return int
	 */
	int lagSaleDay(Date lastSaleDate,Integer unsalableCycle,Integer unsalableCycleUnit);
	
	/**
	 * 
	 * @Description: 滞销商品查询
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月21日 下午4:13:52 
	 * @param paramVO
	 * @return
	 * @throws Exception 
	 * @return List<LagSaleGoodsTotalVO>
	 */
	Page<LagSaleGoodsTotalVO> getLagSaleGoods(LagSaleGoodsRequestParamVO paramVO) throws Exception;
	
	/**
	 * 
	 * @Description: 滞销商品导出
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月21日 下午4:16:24 
	 * @param output
	 * @param paramVO
	 * @throws Exception 
	 * @return void
	 */
	void exportExcel(OutputStream output,LagSaleGoodsRequestParamVO paramVO,UserVO userVO) throws Exception;

}
