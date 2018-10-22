package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountParamVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReceivableInvoiceAccountDetailVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableInvoiceGoodsVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableinvoiceSelectVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.AdvanceReveivableinvoiceVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceListrequestVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.InvoiceUpdateVo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo.ReveivableInvoiceListParamVo;
import com.rograndec.feijiayun.chain.business.goods.info.vo.JoinGoodsRequestVo;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface IAdvanceinvoiceService {
	/**
	 * 保存预收发票
	 * @param loginUser
	 * @param advanceReceivableInvoice
	 */
	public String save(UserVO loginUser,AdvanceReveivableinvoiceVo advanceReveivableinvoiceVo) throws Exception;
	/**
	 * 根据发票id获取发票详情
	 * @param invoiceId
	 * @return
	 */
	public AdvanceReceivableInvoice selectReveivableinvoiceAdd(Long invoiceId);
	/**
	 * 根据发票id获取发票详情
	 * @param invoiceId
	 * @return
	 */
	public AdvanceReveivableinvoiceSelectVo selectReveivableinvoice(Long invoiceId);
	/**
	 * 根据发票id获取发票详情对账列表信息，包含出库信息
	 * @param invoiceId
	 * @return
	 */
	public List<AdvanceReceivableInvoiceAccountDetailVo> selectReveivableInvoiceAccountDetail(Long invoiceId);
	/**
	 * 自动对账
	 * @param invoiceId
	 * @return
	 */
	public List<AdvanceReceivableInvoiceAccountDetailVo> autoAccount(Long invoiceId);
	/**
	 * 获取发票列表
	 * @return
	 */
	public ReveivableInvoiceListParamVo  selectReveivableinvoiceList(Page<ReveivableInvoiceListParamVo> page,InvoiceListrequestVo invoiceListrequestVo);
	/**
	 * 保存草稿
	 * @param advanceReveivableinvoiceVo
	 */
	public DraftCacheVO saveDraft(UserVO userVO, DraftCacheVO<AdvanceReveivableinvoiceVo> draftCacheVO) throws Exception;
	
	/**
	 * 删除草稿
	 * @param enterpriseId
	 * @param type
	 * @param redisKeyValue
	 * @throws Exception
	 */
    public void removeDraftCach(Long supplierId,Long enterpriseId, String type, String redisKeyValue) throws Exception ;

    /**
     * 查询草稿
     * @param userVO
     * @return
     * @throws Exception
     */
    public List<DraftCacheVO> getDraftCacheVO(Long supplierId,UserVO userVO) throws Exception ;
    /**
     * 查询加盟店下 可用商品
     * @param joinGoodsRequestVo
     * @return
     */
    List<AdvanceReveivableInvoiceGoodsVo> selectJoinGoodsList(Page<Object> page,JoinGoodsRequestVo joinGoodsRequestVo);
    /**
     * 预收发票对账
     * @param perpayInvoiceAccountParamVO
     */
    void updateAccount(UserVO loginUser,List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParamVO)throws Exception;
    /**
     * 更新 预付发票信息
     * @param loginUser
     * @param invoiceUpdateVo
     * @throws Exception
     */
    String updateInvoice(UserVO loginUser,InvoiceUpdateVo invoiceUpdateVo)throws Exception;
    /**
     * 查询更新记录列表
     * @param page
     * @param userVO
     * @return
     */
    List<AdvanceReceivableInvoiceModifyRecord> selectAdvanceReceivableInvoiceModifyRecordList(Long keyId,Page<List<AdvanceReceivableInvoiceModifyRecord>> page,UserVO userVO);
    /**
     * 查询更新记录列表（不带分页）
     * @param page
     * @param userVO
     * @return
     */
    List<AdvanceReceivableInvoiceModifyRecord> selectAdvanceReceivableInvoiceModifyRecordList(Long keyId,UserVO userVO);
	/**
	 * 导出发票excel
	 * @param selectReveivableinvoice
	 * @param loginUser
	 */
    public void exportExcel(OutputStream output, AdvanceReveivableinvoiceSelectVo selectReveivableinvoice, UserVO loginUser); 
    /**
     * 导出发票excel（更新记录）
     * @param selectReveivableinvoice
     * @param loginUser
     */
    public void exportUpdateExcel(OutputStream output,  List<AdvanceReceivableInvoiceModifyRecord> list, UserVO loginUser); 
    /**
	 * 发票冲销
	 * @param invoiceId
	 * @return
	 */
	public void cancel(UserVO loginUser,Long invoiceId)throws Exception;
}
