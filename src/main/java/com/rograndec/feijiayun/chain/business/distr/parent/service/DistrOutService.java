package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrOutReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrOut;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrOutService
 * @Description: 总部-配货出库-配货出库单-接口
 * @date 2017-10-07 15:57:42
 */
public interface DistrOutService {


	DistrOut getDistrOutData(Long id) throws Exception;

	int save(DistrOut distrOut, UserVO userVO) throws Exception;

	int update(DistrOutSaveOrupdateVO distrOut, UserVO userVO) throws Exception;

	int delete(Long id) throws Exception;

	/**
	 * <获取配货出库列表数据>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/7 19:13
	 */
	void getDistrOutDataList(RequestDistrOutPram requestDistrOutPram, Page page, UserVO userVO);

	/**
	 * <根据配送单id封装出库单数据>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/8 18:44
	 */
	DistrOut getDistrOutDataForSave(Long id, UserVO userVO) throws Exception;

	/**
	 * <价格计算接口用于页面展示>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/10 19:56
	 */
	DistrOut calculation(DistrOut distrOut, UserVO userVO) throws Exception;

	/**
	 * <配送出库单复核>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/11 11:06
	 */
	void check(DistrOutCheckVo distrOutCheckVo, UserVO userVO) throws Exception;

	/**
	 * <出库单导出>
	 *
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/11 14:50
	 */
	void excelExport(OutputStream outPut, Long id, Long sta, UserVO userVO);
	/**
	 *
	 * <获取合格品货位信息>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/11 17:26
	 */
	List<StockLockRecordVO> getQualifiedGoodsInfoShelf(String lotNum, Long goodsId, Long baseOrderId, UserVO userVO);

	void getDistrOutList(Page<OrderReportVo<DistrOutReportVo>> page, RequestDistrOut requestDistrOut);

	void excelExportReport(OutputStream output, List<DistrOutReportVo> distrOutReportVos, UserVO userVO);

    void saveDistrOut(UserVO loginUser, SaveDistrOutVO saveDistrOutVO) throws Exception;

    DistrPurchaseInStorageVO getPurchaseInStorageDtlShelfList(UserVO loginUser, Long id);

    List<GoodsLotShelfVO> getGoodsLotShelfList(UserVO loginUser, Long id);

	DistrReturnInStorageVO getDistrReturnInDetailShelfList(UserVO loginUser, Long id);

    void saveRecheck(DistrOut distrOut, UserVO userVO) throws Exception;

    List<GetPurchaseInStorageShelfListVO> getPurchaseInStorageShelfList(UserVO loginUser, int pageNo, int pageSize, Long id, String orderName, String orderType, Page page);

    List<GetDistrReturnInShelfListVO> getDistrReturnInShelfList(int pageNo, int pageSize, Long id, String orderName, String orderType, Page page);

	DistrOut calculationByCall(DistrOut distrOut, UserVO userVO) throws Exception;
}
