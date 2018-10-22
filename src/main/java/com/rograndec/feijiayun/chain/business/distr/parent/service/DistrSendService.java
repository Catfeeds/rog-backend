package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrSendReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrSend;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/7 <br/>
 * 描述：配送管理-总部-配送单
 */
@Service
public interface DistrSendService {

    /**
     * 配货单-要货单位列表
     * @param enterPriseId 门店id
     * @param param
     * @return
     */
    List<DistrEnterpriseVO> getDistrEnterpriseList(Long enterPriseId, String param);

    /**
     * 总部可配送商品列表
     * @param userVO
     * @param param
     * @return
     */
    List<DistrSendGoodsVO> getGoodsStockList(UserVO userVO, Long requestUnitId, String param) throws Exception;

    /**
     * 新增配货单保存
     * @param userVO 用户信息
     * @param requestDistrSendVO 请求信息
     * @return
     */
    DistrSend saveAddDistrSend(UserVO userVO, RequestDistrSendVO requestDistrSendVO, boolean type) throws Exception;

    /**
     * 调用要货计划生成配货单
     * @param userVO 用户信息
     * @param requestVO 请求信息
     * @return
     */
    int saveTransferReqPlan(UserVO userVO, RequestTransferBaseOrderVO requestVO) throws Exception;

    /**
     * 修改配货单保存
     * @param userVO 用户信息
     * @param updateSendVO 请求信息
     * @return
     */
    int updateDistrSend(UserVO userVO, RequestDistrSendVO updateSendVO) throws Exception;

    /**
     * 计算价格接口
     * @param userVO
     * @param requestDistrSendVO
     * @return
     * @throws Exception
     */
    DistrSend calculation(UserVO userVO, RequestDistrSendVO requestDistrSendVO) throws Exception;

    /**
     * 获取配送单列表
     * @param page
     * @param map
     * @return
     */
    Page getDistrSendPage(Page page, Map<String, Object> map);

    /**
     * 获取配送单明细
     * @param id
     * @return
     */
    DistrSend getDistrSendDetail(Long id);

    /**
     * 获取要货计划列表
     */
    List<DistrSendReqPlanVO> getReqPlanList(Long enterpriseId, Date startTime, Date endTime, Integer sortDate, Integer sortCode);

    /**
     * 根据多个要货计划id，获取要货明细
     * @param planIdsStr 要货计划ID，多个用逗号分隔
     * @return
     */
    List<DistrReqPlanGoodsVO> getReqPlanGoodsList(Long enterpriseId, String planIdsStr, Integer distrRule, Integer sortDate);

    /**
     * 采购入库单列表
     * @param page
     * @param enterpiseId
     * @param startTime
     * @param endTime
     * @return
     */
    Page getPurchaseInStorageList(Page page, Long enterpriseId, Date startTime, Date endTime);

    /**
     * 采购入库单明细列表
     * @param inStorageId 采购入库单ID
     * @return
     */
    List<DistrPurchaseInstorageDetailVO> getPurchaseInStorageDtlList(Long inStorageId);

    /**
     * 配后退回入库单列表
     * @param page 分页
     * @param enterpriseId 企业ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Page getDistrReturnInList(Page page, Long enterpriseId, Date startTime, Date endTime) throws Exception;

    /**
     * 配后退回入库单明细列表
     *
     * @param userVO
     * @param returnInId 配后退回入库单ID
     * @return
     */
    List<DistrReturnInDetail> getDistrReturnInDetailList(UserVO userVO, Long returnInId);

    /**
     * 缺配单列表
     * @param page 分页
     * @param enterpriseId 企业ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Page getDistrLackList(Page page, UserVO userVO, Date startTime, Date endTime);

    /**
     * 缺配单明细列表
     * @param lackId 缺配单ID
     * @return
     */
    DistrLack getDistrLackDetailList(Long lackId,UserVO loginUser);

    /**
     * 配货单导出
     */
    void excelExport(OutputStream outPut, Long id, UserVO userVO);

    /**
     * 取消配货单
     * @param id
     * @return
     */
    int cancelOrder(Long id, UserVO userVO);

	void getDistrSendList(Page<OrderReportVo<DistrSendReportVo>> page, RequestDistrSend requestDistrSend);

    void excelExportReport(OutputStream output, List<DistrSendReportVo> distrLackReportVos, UserVO userVO);

    BranchStockVO getBranchStock(Long requestUnitId, Long goodsid);

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception;

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<DistrSendCacheVO> draftCacheVO) throws Exception;

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue) throws Exception;

    Page<List<DistrSendGoodsVO>> getBatchGoodsStockList(UserVO loginUser, Long requestUnitId, String param, Page<List<DistrSendGoodsVO>> page) throws Exception;
}
