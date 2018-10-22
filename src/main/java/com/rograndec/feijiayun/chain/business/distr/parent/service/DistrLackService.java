package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrLackSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.RequestDistrLackPram;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrLackReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrLack;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrLackService
 * @Description: 总部-配货出库-缺配单-接口
 * @date 2017-10-07 15:58:51
 */
public interface DistrLackService {


	void getDistrLackData(RequestDistrLackPram requestDistrLackPram, Page page) throws Exception;


	int update(DistrLackSaveOrupdateVO distrLack, UserVO userVO) throws Exception;

	/**
	 * <根据id获取缺配单数据和明细数据>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/12 11:45
	 */
	DistrLack getDistrLackAndDetail(Long id);

	/**
	 * <取消缺配单>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/12 11:55
	 */
	void cancel(Long id, UserVO userVO);

	/**
	 * <导出>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/12 14:13
	 */
	void excelExport(OutputStream output, UserVO userVO, Long id);

	void getDistrlackList(Page<OrderReportVo<DistrLackReportVo>> page, RequestDistrLack requestDistrLack);

	void excelExportReport(OutputStream output, List<DistrLackReportVo> distrLackReportVos, UserVO userVO);
}
