package com.rograndec.feijiayun.chain.business.storage.inventory.service;

import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseCargoAreaSimpleVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsStockInfoVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.*;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryForDiffDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.RequestParamForDiffListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterOKVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.ResponseGoodsRegisterExcelVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryForPostDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryPostVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.RequestParamForPostListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO2;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryGoodsInfoVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.RequestParamForHadRegisterListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：盘点单
 * Created by ST on 2017/9/29 13:28
 */
public interface InventoryOrderService {

    /**
     * 盘点单查询
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    void getInventoryList(Page<List<InventoryForOrderListVO>> page, RequestParamForListVO paramForListVO, Long enterpriseId);

    /**
     * 查询当前企业的仓库
     * @param enterpriseId
     * @return
     */
    List<WarehouseVO> getWarehouseVO(Long enterpriseId);

    /**
     * 查询当前企业的库区
     * @param enterpriseId
     * @return
     */
    List<WarehouseAreaVO> getWarehouseAreaByEnterpriseIdAndHID(Long enterpriseId,Long warehouseId);

    /**
     * 查询当前企业的货位
     *
     * @param enterpriseId
     * @return
     */

    List<WarehouseCargoAreaSimpleVO> getWarehouseCargoAreaByEnterpriseIdAndHID(Long enterpriseId, List<Long> warehouseAreaIds);

    List<GoodsStockInfoVO> getGoodsInfoForInventory(Long enterpriseId, RequestGoodsStockVO goodsStockVO);


    DraftCacheVO getDraftCacheVO(UserVO userVO, Long baseOrderId);

    /**
     * 保存盘点单
     * @param userVO
     * @param inventoryForAddVO
     */
    void saveInventoryOrder(UserVO userVO, InventoryForAddVO inventoryForAddVO) throws Exception;

    void updateInventoryOrder(UserVO userVO,InventoryForAddVO inventoryForAddVO) throws Exception;


    /**
     * 查询详情
     * @param userVO
     * @param id
     * @return
     */

    InventoryForOrderDetailVO getInventoryOrderDetail(UserVO userVO, Long id,int type);


    void export(OutputStream output, Long id, UserVO userVO, int type) throws Exception;

    InventoryForOrderDetailVO getInventoryDiffOrderDetail(UserVO userVO, Long id);

    InventoryForRegisterVO2 getHadRegisterInventoryOrderDetail(UserVO userVO, Long id);

    void export4HadRegisterInventory(OutputStream output, Long id, UserVO userVO);

    InventoryForDiffDetailVO getHadHandleInventoryOrderDetail(UserVO userVO, Long id);

    void export4HadHandleInventory(OutputStream output, Long id, UserVO userVO);

    void updateRegisterInventory(UserVO userVO, InventoryForRegisterVO inventoryForRegisterVO) throws Exception;

    /**
     * 保存差异化处理单
     */
    void saveInventoryDiffHandleOrder(UserVO userVO,InventoryForDiffVO inventoryForDiffVO) throws Exception;



    /**
     * 已登记盘点单查询
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    void getHadRegisterInventoryOrderList(Page<List<InventoryForRegisterVO2>> page, RequestParamForHadRegisterListVO paramForListVO, Long enterpriseId);

    /**
     * 已处理的差异单
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    void getHadHandlerInventoryList(Page<List<InventoryForDiffDetailVO>> page, RequestParamForDiffListVO param, Long enterpriseId);

    /**
     * 已过账单查询
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    void getInventoryPostOrderList(Page<InventoryPostVO> page, RequestParamForPostListVO param, Long enterpriseId);

    /**
     * 已过账单详情
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    InventoryForPostDetailVO getPostDetail(UserVO userVO, Long id);

    void export4PostDetail(OutputStream output, Long id, UserVO userVO);

    InventoryForPostDetailVO getPostDetail2Post(UserVO userVO, Long id);

    /**
     * 保存过账单
     * @param userVO
     * @param detailVO
     */
    void saveInventoryPostOrder(UserVO userVO,InventoryForPostDetailVO detailVO) throws Exception;

    void getGoodsInfoForInventoryPage(Page<List<GoodsStockInfoVO>> page, RequestGoodsStockVOPage goodsStockVO, Long enterpriseId);

    ResponseGoodsRegisterExcelVO excelImport(HttpServletRequest request) throws Exception;

    List<GoodsShelfForRegisterOKVO> getSuccessData(String key) throws Exception;

    Integer getInventoryCountData(UserVO userVO, Long invId) throws Exception;

    void exportGoods(OutputStream output, String key, Integer type, Integer invType) throws Exception;

    void cancelInventoryOrder(UserVO userVO, Long id) throws Exception;

    /**
     * 盘点登记按商品登记查询商品
     * @param userVO
     * @param invId
     * @param param
     * @param registerType
     * @return
     */
    List<InventoryGoodsInfoVO> getGoodsInfoInInventory(UserVO userVO, Long invId, String param);


    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<InventoryForRegisterVO2> draftCacheVO);

    void removeDraftCach(Long enterpriseId, String type, String redisKeyValue);

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO);
}
