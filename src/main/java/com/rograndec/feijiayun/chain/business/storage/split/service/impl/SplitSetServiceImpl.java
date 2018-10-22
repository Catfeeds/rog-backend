package com.rograndec.feijiayun.chain.business.storage.split.service.impl;

import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.service.impl.GoodsServiceImpl;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsUnitMapper;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitSetMapper;
import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitSet;
import com.rograndec.feijiayun.chain.business.storage.split.service.SplitSetService;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetPageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/27 <br/>
 * 描述：储存管理-拆零设置
 */
@Service
public class SplitSetServiceImpl implements SplitSetService {


    private org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private SplitSetMapper splitSetMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Override
    public List<CanSpitGoodVO> getCanSplitGoods(String param, UserVO userVO) {
        Map<String, Object> map = new HashMap<>();
        Long enteriseId = userVO.getEnterpriseId();
        Long ownerId = userVO.getEnterpriseId();
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            enteriseId = userVO.getParentId();
        }
        map.put("param", param);
        map.put("enterpriseId", enteriseId);
        map.put("ownerId", ownerId);
        List<CanSpitGoodVO> canSpitGoodVOList = splitSetMapper.getCanSplitGoods(map);
        return canSpitGoodVOList;
    }

    @Override
    public Page getMemberInfoPage(Page page, String param, UserVO userVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("param", param);
        map.put("enterpriseId", userVO.getEnterpriseId());
        map.put("start", page.getStart());
        map.put("pageSize", page.getPageSize());
        map.put("chainType", userVO.getChainType());
        map.put("parentId", userVO.getParentId());


//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        /**
         * 获取基本信息，以下信息需展示当前信息，而不是拆零时候的快照：
         * 1、原商品货位；拆零商品货位
         * 2、原商品零售价、会员价；拆零商品零售价、会员价
         * 3、启用状态，拆零商品编码；
         */
        Integer count = splitSetMapper.getSplitSetPageCount(map);
        List<SplitSetPageVO> splitSetPageVOList = splitSetMapper.getSplitSetPage(map);
        page.setTotalRecord(count);
//        page.setTotalPage(hPage.getPages());
//        PageHelper.clearPage();
        for(SplitSetPageVO splitSetPageVO : splitSetPageVOList){

            if(splitSetPageVO.getStatus() == EnableStatus.DISABLE.getStatus()){//禁用
                splitSetPageVO.setUpdateFlag(false);
            }
            if(userVO.getChainType() == ChainType.Division.getCode()){//总部
                if(splitSetPageVO.getParentId() == 0L){
                    splitSetPageVO.setUpdateFlag(false);
                }
            }
            if(userVO.getChainType() == ChainType.Selfoperatedshop.getCode()){//自营店
                splitSetPageVO.setUpdateFlag(false);
            }
            splitSetPageVO.setStatusDes(EnableStatus.getName(splitSetPageVO.getStatus()));
        }

        if (splitSetPageVOList != null && splitSetPageVOList.size() > 0) {
            List<Long> goodsIds = getGoodsIds(splitSetPageVOList, 0);

            //通过商品id列表获取零售价格信息(包含原商品及拆零商品)
            List<PriceOrderDetail> priceOrderDetailList = priceOrderDetailMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, userVO.getEnterpriseId());

            List<SafetyStock> safetyStockList = safetyStockMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, userVO.getEnterpriseId());

            //拆零商品的启用状态、商品编码
            Long enteriseId = userVO.getEnterpriseId();
            Long ownerId = userVO.getEnterpriseId();
            if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())){
                enteriseId = userVO.getParentId();
            }
            List<Goods> goodsInfoList = goodsMapper.getGoodsByIds(getGoodsIds(splitSetPageVOList, 2),enteriseId , ownerId);

            //将价格信息、货位信息、拆零商品编码信息填充到列表中
            setSupplementInfo(splitSetPageVOList, priceOrderDetailList ,safetyStockList,goodsInfoList);
        }

        page.setResult(splitSetPageVOList == null ? new ArrayList<>() : splitSetPageVOList);
        return page;
    }

    @Override
    public SplitSetPageVO getSplitSetById(Long id, Long enterpriseId) {
        SplitSetPageVO splitSetPageVO = splitSetMapper.getSplitSetById(id);

        //拆零零售单价及会员单价
        List<Long> goodsIds = new ArrayList<>();
        goodsIds.add(splitSetPageVO.getGoodsId());
        goodsIds.add(splitSetPageVO.getSplitGoodsId());
        List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, enterpriseId);
        if (priceOrderDetails != null && priceOrderDetails.size() > 0) {
            for (PriceOrderDetail priceOrderDetail : priceOrderDetails) {
                if (priceOrderDetail.getGoodsId().equals( splitSetPageVO.getGoodsId())) {
                    splitSetPageVO.setRetailPrice(priceOrderDetail.getRetailPrice());
                    splitSetPageVO.setMemberPrice(priceOrderDetail.getMemberPrice());
                }

                if (priceOrderDetail.getGoodsId().equals( splitSetPageVO.getSplitGoodsId())) {
                    splitSetPageVO.setSplitRetailPrice(priceOrderDetail.getRetailPrice());
                    splitSetPageVO.setSplitMemberPrice(priceOrderDetail.getMemberPrice());
                }
            }
        }

        //安全库存，获取拆零商品默认货位信息
        SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(splitSetPageVO.getSplitGoodsId(), enterpriseId);
        if (safetyStock != null) {
            splitSetPageVO.setSplitShelfId(safetyStock.getDefaultShelfId());
            splitSetPageVO.setSplitShelfName(safetyStock.getDefaultShelfName());
        }

        //拆零编码、启用信息
        Goods goods = goodsMapper.selectByPrimaryKey(splitSetPageVO.getSplitGoodsId());
        if (goods != null) {
            splitSetPageVO.setStatus(goods.getStatus());
            splitSetPageVO.setSplitGoodsCode(goods.getCode());
        }

        return splitSetPageVO;
    }

    @Override
    public SplitSet selectByPrimaryKey(Long id) {
        return splitSetMapper.selectByPrimaryKey(id);
    }

//    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
//    public int updateSplitSet(SplitSetSaveVO splitSetSaveVO, UserVO userVO, boolean updateShelf, boolean updatePrice) throws Exception {
//
//        UpdateSplitSetVO updateSplitSetVO = new UpdateSplitSetVO();
//        updateSplitSetVO.setEnterpriseId(userVO.getEnterpriseId());
//        updateSplitSetVO.setParentId(userVO.getParentId());
//        updateSplitSetVO.setModifierId(userVO.getUserId());
//        updateSplitSetVO.setModifierCode(userVO.getUserCode());
//        updateSplitSetVO.setModifierName(userVO.getUserName());
//        updateSplitSetVO.setGoodsId(splitSetSaveVO.getSplitGoodsId());
//
//        //更新默认货位
//        if (updateShelf) {
//            //默认货位信息
//            WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(splitSetSaveVO.getSplitShelfId());
//            if (warehouseShelf == null) {
//                throw new BusinessException("查询货位信息错误！");
//            }
//
//
//            commonComponent.updateGoodsDefShelf(userVO.getEnterpriseId(),userVO.getParentId(),userVO.getChainType(),splitSetSaveVO.getSplitGoodsId(),
//                    warehouseShelf.getId(),warehouseShelf.getName(),userVO);
////            updateSplitSetVO.setDefaultShelfId(warehouseShelf.getId());
////            updateSplitSetVO.setDefaultShelfName(warehouseShelf.getName());
////            splitSetSaveVO.setSplitShelfName(warehouseShelf.getName());
////
////           int i = safetyStockMapper.updateDefaultShelfByGoodsId(updateSplitSetVO);
////           if (i == 0) {
////               throw new BusinessException("拆零设置更新操作-更新安全库存失败！");
////           }
//        }
//
//        //更新价格清单明细
//        if (updatePrice) {
//            updateSplitSetVO.setRetailPrice(splitSetSaveVO.getSplitRetailPrice());
//            updateSplitSetVO.setMemberPrice(splitSetSaveVO.getSplitMemberPrice());
//            int i = priceOrderDetailMapper.updateSplitGoodsPrices(updateSplitSetVO);
//            logger.info("拆零设置更新操作--更新数量:" + i);
//            if (i == 0) {
//                throw new BusinessException("拆零设置更新操作-更新价格清单明细失败！");
//            }
//        }
//
//        SplitSet oldSplitSet = new  SplitSet();
//        oldSplitSet.setId(splitSetSaveVO.getSplitSetId());
//
//        // 老数据 更新为禁用，并生成一条新的数据
//        oldSplitSet.setStatus(EnableStatus.DISABLE.getStatus());
//        splitSetMapper.updateByPrimaryKeySelective(oldSplitSet);
//
//        SplitSet newSplitSet = new SplitSet();
//        SplitSet tepSplitSet = splitSetMapper.selectByPrimaryKey(splitSetSaveVO.getSplitSetId());
//        BeanUtils.copyProperties(tepSplitSet,newSplitSet);
//        //SplitSet splitSet = SplitSetSaveVO.convertToSplitSet(splitSetSaveVO);
//
//        newSplitSet.setGoodsId(splitSetSaveVO.getGoodsId());
//        newSplitSet.setSplitSpecification(splitSetSaveVO.getSplitSpecification());
//        newSplitSet.setSplitUnitId(splitSetSaveVO.getSplitUnitId());
//
//        GoodsUnit goodsUnit = goodsUnitMapper.selectByPrimaryKey(splitSetSaveVO.getSplitUnitId());
//        if(goodsUnit == null){
//            throw  new BusinessException("当前拆零单位不存在，unitId = " + splitSetSaveVO.getSplitUnitId());
//        }
//
//        newSplitSet.setSplitUnitName(goodsUnit.getName());
//        if (updateShelf) {
//            newSplitSet.setSplitShelfId(splitSetSaveVO.getSplitShelfId());
//            newSplitSet.setSplitShelfName(splitSetSaveVO.getSplitShelfName());
//        }
//
//        newSplitSet.setSplitQuantity(splitSetSaveVO.getSplitQuantity());
//        newSplitSet.setSplitRetailPrice(splitSetSaveVO.getSplitRetailPrice());
//        newSplitSet.setSplitMemberPrice(splitSetSaveVO.getSplitMemberPrice());
//        newSplitSet.setStatus(EnableStatus.ENABLE.getStatus());//暂未使用到该状态,默认1
//        newSplitSet.setId(null);
//        splitSetMapper.insertSelective(newSplitSet);
//
//        return 0;
//    }

    /**
     * 获取待查询价格的商品列表
     * @param splitSetPageVOList
     * @param type 0-普通商品及拆零商品；1-普通商品；2-拆零商品
     * @return 待查询商品列表
     */
    private List<Long> getGoodsIds(List<SplitSetPageVO> splitSetPageVOList, int type) {
        List<Long> goodsIds = new ArrayList<Long>();//goodsIds
        if(splitSetPageVOList == null || splitSetPageVOList.size() == 0) {
            return goodsIds;
        }

        for (SplitSetPageVO splitSetPageVO : splitSetPageVOList) {
                goodsIds.add(splitSetPageVO.getGoodsId());
                goodsIds.add(splitSetPageVO.getSplitGoodsId());
        }

        HashSet h = new HashSet(goodsIds);
        goodsIds.clear();
        goodsIds.addAll(h);
        return goodsIds;
    }

    /**
     * 将价格信息、货位信息、拆零商品编码信息填充到列表中
     * @param splitSetPageVOList 拆零设置单据列表
     * @param priceOrderDetailList 零售、会员价格列表
     * @param goodsInfoList 商品信息列表
     */
    private void setSupplementInfo(List<SplitSetPageVO> splitSetPageVOList,
                                   List<PriceOrderDetail> priceOrderDetailList,
                                   List<SafetyStock> safetyStockList,
                                   List<Goods> goodsInfoList) {
        if (splitSetPageVOList == null || splitSetPageVOList.size() == 0) {
            return;
        }

        for (SplitSetPageVO splitSetPageVO : splitSetPageVOList) {

            //设置价格
            if (priceOrderDetailList != null && priceOrderDetailList.size() > 0) {
                for(PriceOrderDetail priceOrderDetail : priceOrderDetailList) {
                    //原商品价格
                    if (splitSetPageVO.getGoodsId().equals(priceOrderDetail.getGoodsId())) {
                        splitSetPageVO.setRetailPrice(priceOrderDetail.getRetailPrice());
                        splitSetPageVO.setMemberPrice(priceOrderDetail.getMemberPrice());
                    }

//                    //拆零商品价格
//                    if (splitSetPageVO.getSplitGoodsId() == priceOrderDetail.getGoodsId()) {
//                        splitSetPageVO.setSplitRetailPrice(priceOrderDetail.getRetailPrice());
//                        splitSetPageVO.setSplitMemberPrice(priceOrderDetail.getMemberPrice());
//                    }
                }
            }

            //设置默认货位信息
            if (safetyStockList != null && safetyStockList.size() > 0) {
                for (SafetyStock safetyStock : safetyStockList) {
                    //原商品货位
                    if (splitSetPageVO.getGoodsId().equals(safetyStock.getGoodsId())) {
                        splitSetPageVO.setShelfName(safetyStock.getDefaultShelfName());
                    }

                    //拆零商品货位
                    if (splitSetPageVO.getSplitGoodsId().equals(safetyStock.getGoodsId())) {
                        splitSetPageVO.setSplitShelfId(safetyStock.getDefaultShelfId());
                        splitSetPageVO.setSplitShelfName(safetyStock.getDefaultShelfName());
                    }
                }
            }

            //设置拆零商品启用状态、拆零编码
            if (goodsInfoList != null && goodsInfoList.size() > 0) {
                for (Goods goods : goodsInfoList) {
                    if (splitSetPageVO.getSplitGoodsId().equals(goods.getId())) {
                        splitSetPageVO.setSplitGoodsCode(goods.getCode());
//                        splitSetPageVO.setStatus(goods.getStatus());
//                        splitSetPageVO.setStatusDes(goods.getStatus() == 1 ? "启用" : "禁用");
                        break;
                    }
                }
            }


            //splitSetPageVO.setStatusDes(EnableStatus.getName(splitSetPageVO.getStatus()));
        }
    }

}
