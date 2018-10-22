package com.rograndec.feijiayun.chain.business.purchase.ret.service;

import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageDetailForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SupplierOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.GetInStorageParamVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnShowVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Created by ST on 2017/9/20 13:55
 */
public interface PurchaseReturnService {

    /**
     * 分页查询商品信息
     * @param param
     * @param enterpriseId
     * @return
     */

    List<PurchaseReturnGoodsVO> getGoodsInfoByPurchaseReturn(String param, Long enterpriseId, Long supplierId);

    List<PurchaseReturn> getPurchaseReturnsByParam(UserVO userVO, Page page, Map<String, Object> map);

    void exportExcel(OutputStream output, Long id);

    PurchaseReturnShowVO getPurchaseReturnDetails(Long id);
    void getInStorageForReturn(Page<List<PurchaseInStorageForReturnVO>> page, GetInStorageParamVO getInStorageParamVO);

    void getInStorageDetailForReturn(Page<List<PurchaseInStorageDetailForReturnVO>> page, GetInStorageParamVO getInStorageParamVO);

    void save(UserVO userVO, PurchaseReturnSaveOrUpdateVO returnSaveOrUpdateVO, boolean isAdd) throws Exception;

    void cancel(Long id, UserVO userVO);

    Page<List<PurchaseReturnModifyRecord>> getPurchaseReturnModifyRecord(UserVO use, Long id, Page page);

    List<SupplierOrderVO> getSupplier(UserVO userVO) throws Exception;

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO);

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<PurchaseReturnSaveOrUpdateVO> draftCacheVO);

    void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue);
    
    Map<String,Object> getDefaultDistrInfo(Long id);
}
