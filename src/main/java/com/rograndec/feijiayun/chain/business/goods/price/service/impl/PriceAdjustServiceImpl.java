package com.rograndec.feijiayun.chain.business.goods.price.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.approvalProcessor.PriceApprovalProcessor;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceAdjustDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceAdjustMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjust;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjustDetail;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.price.service.PriceAdjustService;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdateAdjustDetailVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceAdjustVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustReturnVO;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.GoodsComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.PriceType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by zhaiwei on 2017/9/6.
 */
@Service
public class PriceAdjustServiceImpl implements PriceAdjustService {

    @Autowired
    private PriceAdjustMapper priceAdjustMapper;

    @Autowired
    private PriceAdjustDetailMapper priceAdjustDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsComponent goodsComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ApprovalFlowActionDetailMapper approvalFlowActionDetailMapper;

    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private PriceApprovalProcessor priceApprovalProcessor;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Override
    public  List<PriceAdjustGoodsVO> queryPriceAdjustGoodsVOs4PriceOrder(UserVO userVO,Long priceOrderId, String param, Page page){

        com.github.pagehelper.Page<List<PriceAdjustGoodsVO>> hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        Map<String,Object> map = new HashMap<>();
        map.put("priceOrderId",priceOrderId);
        map.put("param",param);

//        List<PriceAdjustGoodsVO> priceOrderGoodsByParams = new ArrayList<>();
//        if(StringUtils.isEmpty(param)){
//            priceOrderGoodsByParams = goodsMapper.getPriceOrderGoodsByNotParam(map);
//        }else {
        List<PriceAdjustGoodsVO> priceOrderGoodsByParams = goodsMapper.getPriceOrderGoodsByParam(map);
//        }


        page.setResult(priceOrderGoodsByParams);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
        page.setTotalPage(hPage.getPages());
        return priceOrderGoodsByParams;
}

//    @Override
//    public List<PriceAdjustGoodsVO> queryPriceAdjustGoodsVOs4PriceOrder(Long priceOrderId ,UserVO userVO, String param, Page page){
//
//        List<PriceAdjustGoodsVO> priceAdjustGoodsVOS = new ArrayList<>();
//
//        List<Goods> goodsByParams = goodsComponent.getGoodsByParam(userVO, param, page);
//        List<Long> ids = Goods.getGoodsIds(goodsByParams);
//
//        if(!CollectionUtils.isEmpty(ids)) {
//            List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByIdAndGoodsIds(priceOrderId,ids);
//
//            priceAdjustGoodsVOS = PriceAdjustGoodsVO.getPriceAdjustGoods4GoodsAndPriceOrderDetails(goodsByParams, priceOrderDetails);
//        }
//
//        page.setResult(priceAdjustGoodsVOS);
//        return priceAdjustGoodsVOS;
//    }

    @Override
    public List<PriceAdjustGoodsVO>  queryPriceAdjustGoodsVOs4Adjust(Long adjustId,UserVO userVO){

        List<PriceAdjustGoodsVO> priceAdjustGoodsVOS = new ArrayList<>();

        List<PriceAdjustDetail> priceAdjustDetails = priceAdjustDetailMapper.selectByPriceAdjustId(adjustId);
        Set<Long> goodsIds = PriceAdjustDetail.getGoodsIds(priceAdjustDetails);
        if(!CollectionUtils.isEmpty(goodsIds)) {
            List<Long> cs = new ArrayList<>(goodsIds);
            Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
            List<Goods> goodsList = goodsMapper.selectByIds(cs,headEnterpriseId);
            priceAdjustGoodsVOS = PriceAdjustGoodsVO.getPriceAdjustGoods4GoodsAndAdjustDetails(goodsList, priceAdjustDetails);

        }
        return priceAdjustGoodsVOS;
    }

    @Override
    public List<QueryBean> queryPriceOrderIdAndNameList(Long eId,Integer type,Long id){
        List<Integer> types = null;
        if(null != type){
            types = new ArrayList<>();
            types.add(PriceType.BASE_PRICE.getCode());
            types.add(type);
        }

        List<QueryBean> selectBeans = priceOrderMapper.getEnterpriseIdAndParentIdWithControl(eId,types);

        if(null != id){

            Iterator<QueryBean> iterator = selectBeans.iterator();
            while (iterator.hasNext()){
                QueryBean next = iterator.next();

                if(next.getId().equals(id)){
                    iterator.remove();
                }
            }
        }

        return selectBeans;
    }

    @Override
    public PriceAdjustDetail queryAdjust(Long adjustId, Long goodsId, Long eId){
        Map<String,Object> map = new HashMap();
        map.put("priceOrderId",adjustId);
        map.put("goodsId",goodsId);
        map.put("eId",eId);

        PriceAdjustDetail priceAdjustDetail = priceAdjustDetailMapper.selectByPriceAdjustIdAndGoodsId(map);
        return priceAdjustDetail;
    }

    /**
     * 增加价格调整单
     * @param userVO
     * @param addOrUpdatePriceAdjustVO
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePriceAdjusts(UserVO userVO, AddOrUpdatePriceAdjustVO addOrUpdatePriceAdjustVO, boolean isAdd) throws Exception {

        Long adjustManId = addOrUpdatePriceAdjustVO.getAdjustManId();

        Long priceOrderId = addOrUpdatePriceAdjustVO.getPriceOrderId();

        List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByPriceOrderId(priceOrderId);

        User user = userMapper.selectByPrimaryKey(adjustManId);

        savePriceAdjust(userVO,addOrUpdatePriceAdjustVO,user,priceOrderDetails,isAdd);

    }

    @Transactional(rollbackFor = Exception.class)
    public void savePriceAdjust(
            UserVO userVO
            , AddOrUpdatePriceAdjustVO priceAdjustVO
            , User user
            , List<PriceOrderDetail> priceOrderDetails
            , boolean isAdd
    ) throws Exception {

        if(!isAdd){
            /**
             * 修改时,需要删除用户删除的价格清单明细项
             */
            List<Long> deleteAdjustDetailIds = priceAdjustVO.getDeleteAdjustDetailIds();
            for(Long id : deleteAdjustDetailIds){
                priceAdjustMapper.deleteByPrimaryKey(id);
                priceAdjustDetailMapper.deleteByAdjustId(id);
            }
        }

        String orderCode = orderCodeComponent.generate(OrderRule.PRICE_ADJUST.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        PriceAdjust priceAdjust = PriceAdjust.getPriceAdjust4VO(userVO, orderCode, user, priceAdjustVO, isAdd);

        if(isAdd){
            priceAdjustMapper.insertSelective(priceAdjust);
        }else {
            if(priceAdjust.getId() != null ){
                priceAdjustMapper.updateByPrimaryKeySelective(priceAdjust);
            }else {
                priceAdjustMapper.insertSelective(priceAdjust);
            }

        }


        List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS = priceAdjustVO.getAddOrUpdateAdjustDetailVOS();
        saveAdjustDetail(userVO,priceAdjust,priceOrderDetails,addOrUpdateAdjustDetailVOS,isAdd);


        /**
         * 添加/修改完价格调整单后需要申请一条审批工作流
         */
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());

        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(
                userVO.getEnterpriseId(),
                enterprise.getName(),
                userVO.getUserId(),
                userVO.getUserName(),
                userVO.getChainType(),
                userVO.getParentId(),
                userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId(): userVO.getParentId(),
                ApprovalFlowContentModel.getPriceAdjustCode(),
                priceAdjust.getId(),
                priceAdjust.getOrderCode(),
                "价格调整"
        );

        approvalFlowComponent.apply(submitApprovalFlowVO,userVO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAdjustDetail(UserVO userVO
            , PriceAdjust priceAdjust
            , List<PriceOrderDetail> priceOrderDetails
            , List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS
            , boolean isAdd) throws Exception {

        Set<Long> taxRateIds = AddOrUpdateAdjustDetailVO.getTaxRateIds(addOrUpdateAdjustDetailVOS);
        List<GoodsTaxRate> goodsTaxRates = goodsComponent.getGoodsTaxRates(taxRateIds);

        Map<Long,GoodsTaxRate> goodsTaxRateMap = new HashMap<>();
        for(GoodsTaxRate goodsTaxRate : goodsTaxRates){
            goodsTaxRateMap.put(goodsTaxRate.getId(),goodsTaxRate);
        }

        List<PriceAdjustDetail> priceAdjustDetails = PriceAdjustDetail.getAdjustDetail4VO(userVO, priceAdjust, priceOrderDetails, addOrUpdateAdjustDetailVOS,goodsTaxRateMap, isAdd);

        for(PriceAdjustDetail priceAdjustDetail : priceAdjustDetails){

            if(isAdd){
                priceAdjustDetailMapper.insertSelective(priceAdjustDetail);
            }else {
                if(priceAdjustDetail.getId() != null){
                    priceAdjustDetailMapper.updateByPrimaryKey(priceAdjustDetail);
                }else {
                    priceAdjustDetailMapper.insertSelective(priceAdjustDetail);
                }

            }

        }

    }

    /**
     * 取消价格调整单,此操作只能在审批被拒绝时操作
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPriceAdjust(Long id){

        PriceAdjust priceAdjust = priceAdjustMapper.selectByPrimaryKey(id);
        approvalFlowComponent.cancel(ApprovalFlowContentModel.getPriceAdjustCode(),priceAdjust.getId(),priceAdjust.getEnterpriseId(),priceApprovalProcessor);
    }


    @Override
    public List<PriceAdjustReturnVO> queryPriceAdjust4Param(Map<String, Object> map, Page page){

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());

        List<PriceAdjustReturnVO> priceAdjusts = new ArrayList<>();

        /**
         * 状态 （0-待审核；1-已完成；2-审核被驳回 3-取消)
         */

        if(null != map.get("statusRecom")){
            Integer statusRecom = (Integer) map.get("statusRecom");
            if(statusRecom == 0){
                map.put("statusRecom",PurchaseStatus.PENDING_AUDIT.getStatus());
            }else if(statusRecom == 1){
                map.put("statusRecom",PurchaseStatus.FINISHED.getStatus());
            }else if(statusRecom == 2){
                map.put("statusRecom",PurchaseStatus.AUDIT_REJECT.getStatus());
            }else if(statusRecom == 3){
                map.put("statusRecom",PurchaseStatus.CANCELED.getStatus());
            }
        }
        priceAdjusts.addAll(priceAdjustMapper.selectByParamNotApprovalFLow(map));

        /*List<Long> actionflowIds = PriceAdjustReturnVO.getActionflowId(priceAdjusts);
        List<ApprovalFlowActionDetail> approvalFlowActionDetails = new ArrayList<>();*/

       /* if(!CollectionUtils.isEmpty(actionflowIds))
            approvalFlowActionDetails = approvalFlowActionDetailMapper.selectByActionIds(actionflowIds);*/

        List<PriceAdjustReturnVO> priceAdjustReturnVOS = PriceAdjustReturnVO.setStatusDesc(priceAdjusts);

        page.setResult(priceAdjustReturnVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
        page.setTotalPage(hPage.getPages());
        return priceAdjusts;
    }

    @Override
    public PriceAdjustReturnVO getPriceAdjustReturnVOById(Long adjustId) {

        PriceAdjustReturnVO returnVO = new PriceAdjustReturnVO();
        PriceAdjust priceAdjust = priceAdjustMapper.selectByPrimaryKey(adjustId);
        BeanUtils.copyProperties(priceAdjust,returnVO);

        return returnVO;
    }


}
