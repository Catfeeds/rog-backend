package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import com.rograndec.feijiayun.chain.business.common.vo.StockLockShelfVO;
import com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor.ReturnInApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.*;
import com.rograndec.feijiayun.chain.business.distr.branch.exception.DistrInReturnBizException;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReturnOutService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer.*;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnsVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.RequestCheckVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.RequestPurchaseReturnOutInfoVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.LockQtyArgVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistrInReturnOutServiceImpl implements DistrInReturnOutService {

    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Autowired
    private DistrInReturnOutDetailMapper distrInReturnOutDetailMapper;

    @Autowired
    private DistrInReturnOutShelfMapper distrInReturnOutShelfMapper;

    @Autowired
    private DistrInReturnMapper distrInReturnMapper;

    @Autowired
    private DistrInReturnDetailMapper distrInReturnDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private DistrComponent distrComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private DistrInShelfMapper distrInShelfMapper;

    @Autowired
    private ManageConfigComponent manageConfigComponent;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ReturnInApprovalProcessor returnInApprovalProcessor;

    @Autowired
    private OrikaMapperFactory orikaMapperFactory;

    @Autowired
    private DistrOutMapper distrOutMapper;
    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;
    @Autowired
    private DistrSendMapper distrSendMapper;

    @Autowired
    private DistrReqPlanMapper distrReqPlanMapper;

    @Autowired
    private DistrInNoticeMapper distrInNoticeMapper;
    @Autowired
    private DistrInNoticeDetailMapper distrInNoticeDetailMapper;
    @Autowired
    private FinanceComponent financeComponent;
    /**
     * 出库
     * @param userVO
     * @param distrInReturnOutFormVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDistrInReturnOut(UserVO userVO, DistrInReturnOutFormVO distrInReturnOutFormVO) throws Exception {


        DistrInReturn distrInReturn = distrInReturnMapper.selectByPrimaryKey(distrInReturnOutFormVO.getReturnId());

        /**
         * 更改配进退出单的状态为出库中
         */
        DistrInReturn newDistrInReturn = new DistrInReturn();
        newDistrInReturn.setId(distrInReturn.getId());
        newDistrInReturn.setStatus(DistrInReturnStatus.OUTTING);
        distrInReturnMapper.updateByPrimaryKeySelective(newDistrInReturn);

        List<DistrInReturnDetail> distrInReturnDetails = distrInReturnDetailMapper.selectByInReturnId(distrInReturnOutFormVO.getReturnId());

        DistrInReturnDetail newDistrInReturnDetail = new DistrInReturnDetail();
        newDistrInReturnDetail.setInReturnId(distrInReturn.getId());
        newDistrInReturnDetail.setStatus(DistrInReturnStatus.OUTTING);
        distrInReturnDetailMapper.updateByInReturnId(newDistrInReturnDetail);

        List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS = distrInReturnOutFormVO.getDistrInReturnOutDetailFormVOS();
        for(DistrInReturnOutDetailFormVO di : distrInReturnOutDetailFormVOS){
            List<Long> deleteShelfIds = di.getDeleteShelfIds();
            for(Long id : deleteShelfIds){
                distrInReturnOutShelfMapper.deleteByPrimaryKey(id);
            }

        }


        generateAndSaveDistrInReturnOuts(userVO,distrInReturnOutFormVO,distrInReturn,distrInReturnDetails);

    }


    /**
     * 新增
     * @param userVO
     * @param distrInReturnOutAddFormVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save4DistrType(UserVO userVO, DistrInReturnOutAddFormVO distrInReturnOutAddFormVO) throws Exception {

        Integer distrType = distrInReturnOutAddFormVO.getDistrType();

        if(DistrType.DIRECT_DISTRIBUTION.getCode() == distrType){

            /**
             * 企业信息的“配货管理”选择“允许直调”显示直调配送选项，否则不显示
             */
            EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
            Integer approvalControl = bus.getApprovalControl();

            /**
             * 直接配送
             */
            if(DistrButionManage.ENABLE.getCode() != approvalControl){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"不允许直接调用!");
            }

        }

        save2Distr(userVO,distrInReturnOutAddFormVO);

    }


    /**
     * 新增
     * 需要逆向生成配进退出单
     * @param userVO
     * @param distrInReturnOutAddFormVO
     * @throws Exception
     */
    private final static String distrReturnOutOrderStr = "已经成功生成%s";

    private final static String distrReturnOutOrderNoStr = ":%s";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String>  save2Distr(UserVO userVO, DistrInReturnOutAddFormVO distrInReturnOutAddFormVO) throws Exception {


        List<DistrInReturnOutAddFormDetailVO> dsf = distrInReturnOutAddFormVO.getDistrInReturnOutAddFormDetailVOS();
        for(DistrInReturnOutAddFormDetailVO distrInReturnOutDetailFormVO : dsf){
            if(distrInReturnOutDetailFormVO.getQuantity().compareTo(BigDecimal.ZERO) <= 0){
                throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"数量不能小于等于0");
            }
        }

        StringBuffer sb = new StringBuffer();
        List<String> orderStrs = new ArrayList<>();
        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        Integer businessControl = manageConfig.getBusinessControl();
        /**
         * 业务流程质量控制（0关闭；1-开启）
         */
        if(1 == businessControl){
            /**
             * 业务流程质量控制开启的时候不允许新增
             */
            throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"业务流程质量控制开启的时候不允许新增和调用");
        }

        /**
         * 配进退出单头单
         */
        RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO = RequsetDistrReturnInSaveOrUpdateVO.getRequsetDistrReturnInSaveOrUpdateVO(distrInReturnOutAddFormVO);

        List<DistrInReturnOutAddFormDetailVO> distrInReturnOutAddFormDetailVOS = distrInReturnOutAddFormVO.getDistrInReturnOutAddFormDetailVOS();

        /**
         * 配进退出出库单明细单
         */
        List<RequsetDistrReturnDtlSaveOrUpdateVO> distrInReturnDetailVOs = RequsetDistrReturnDtlSaveOrUpdateVO.getRequsetDistrReturnDtlSaveOrUpdateVOs(distrInReturnOutAddFormDetailVOS);
        requsetDistrReturnInSaveOrUpdateVO.setRequsetDistrReturnDtlSaveOrUpdateVO(distrInReturnDetailVOs);

        /**
         * 为配进退出单头单 和 配进退出单明细行集合赋值
         */
        DistrReturnVO distrReturnVO = commonComponent.generateAndSaveDistrInReturn(userVO, requsetDistrReturnInSaveOrUpdateVO,true);


        DistrInReturn distrInReturn = distrReturnVO.getDistrInReturn();
        List<DistrInReturnDetail> distrInReturnDetails = distrReturnVO.getParamList();

        String distrInReturnCode = distrInReturn.getCode();
        sb.append(String.format(distrReturnOutOrderStr,"配进退出单")).append(String.format(distrReturnOutOrderNoStr,distrInReturnCode));
        orderStrs.add(sb.toString());
        sb.setLength(0);

        /*returnInApprovalProcessor.afterAudit(distrInReturn.getId(),DistrInReturnStatus.WAIT_OUT, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());*/

        /**
         * 设置配进退出订单状态为出库中
         */
        DistrInReturn newDistrInReturn = new DistrInReturn();
        newDistrInReturn.setStatus(DistrInReturnStatus.OUTTING);
        newDistrInReturn.setId(distrInReturn.getId());
        distrInReturnMapper.updateByPrimaryKeySelective(newDistrInReturn);

        DistrInReturnDetail newDistrInReturnDetail = new DistrInReturnDetail();
        newDistrInReturnDetail.setInReturnId(distrInReturn.getId());
        newDistrInReturnDetail.setStatus(DistrInReturnStatus.OUTTING);
        distrInReturnDetailMapper.updateByInReturnId(newDistrInReturnDetail);


        /**
         * 生成配进退出出库单需要的vo
         */
        DistrInReturnOutFormVO distrInReturnOutFormVO = DistrInReturnOutFormVO.getDistrInReturnOutFormVO(distrInReturnOutAddFormVO,distrInReturn);


        /**
         * 生成配进退出出库明细单需要的vo
         */
        List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOs = DistrInReturnOutDetailFormVO.getDistrInReturnOutDetailFormVOs(distrInReturnOutAddFormDetailVOS);

        distrInReturnOutFormVO.setDistrInReturnOutDetailFormVOS(distrInReturnOutDetailFormVOs);

        distrInReturnOutFormVO.setReturnId(distrInReturn.getId());

        for(DistrInReturnDetail distrInReturnDetail : distrInReturnDetails){
            for(DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO : distrInReturnOutDetailFormVOs){
                if(distrInReturnDetail.getGoodsId().equals(distrInReturnOutDetailFormVO.getGoodsId()) && distrInReturnDetail.getLotId().equals(distrInReturnOutDetailFormVO.getLotId())){
                    distrInReturnOutDetailFormVO.setReturnDetailId(distrInReturnDetail.getId());
                }
            }

        }

        /**
         * 生成和保存配进退出出库单
         */
        DistrInReturnOut distrInReturnOut = generateAndSaveDistrInReturnOuts(userVO, distrInReturnOutFormVO, distrInReturn, distrInReturnDetails);

        String distrInReturnOutCode = distrInReturnOut.getCode();
        sb.append(String.format(distrReturnOutOrderStr,"配进退出出库单")).append(String.format(distrReturnOutOrderNoStr,distrInReturnOutCode));
        orderStrs.add(sb.toString());
        sb.setLength(0);

        return orderStrs;
        /**
         * 将配进退出单放进redis,复核后取出来再使用存进mysql
         */
      /*  redisComponent.set(RedisKeyPrefix.DISTR_IN_RETURN_OUT_TO_RETURN.getName()+distrInReturnOut.getId(),distrInReturn);
        redisComponent.set(RedisKeyPrefix.DISTR_IN_RETURN_OUT_TO_RETURN_DETAIL.getName()+distrInReturnOut.getId(),distrInReturnDetails);*/

    }

    /**
     * 生成和保存购进退出出库单
     * @param userVO
     * @param distrInReturnOutFormVO
     * @param distrInReturn
     * @param distrInReturnDetails
     * @throws Exception
     */
    private DistrInReturnOut generateAndSaveDistrInReturnOuts(UserVO userVO ,DistrInReturnOutFormVO distrInReturnOutFormVO,DistrInReturn distrInReturn,List<DistrInReturnDetail> distrInReturnDetails) throws Exception {
        DistrInReturnOutParam distrInReturnOutParam = new DistrInReturnOutParam();
        User outMan = userMapper.selectByPrimaryKey(distrInReturnOutFormVO.getOutManId());

        distrInReturnOutParam.setUserVO(userVO);
        distrInReturnOutParam.setDistrInReturnOutFormVO(distrInReturnOutFormVO);
        distrInReturnOutParam.setDistrInReturn(distrInReturn);

        distrInReturnOutParam.setOutMan(outMan);

        DistrInReturnOut distrInReturnOut = DistrInReturnOut.getDistrInReturnOut(
                distrInReturnOutParam
        );


        List<DistrInReturnOutDetail> distrInReturnOutDetails = getDistrInReturnOutDetail(userVO, distrInReturnOutFormVO,distrInReturnDetails);

        distrInReturnOut = DistrInReturnOut.afterGenerateDetailSetReturnOut(distrInReturnOut, distrInReturnOutDetails);

        for(DistrInReturnOutDetail distrInReturnOutDetail : distrInReturnOutDetails){

            DistrInReturnOutDetail.afterGenerateDistrInReturnOutSetDetail(distrInReturnOutDetail, distrInReturnOut);

        }

        distrInReturnOut = DistrInReturnOut.afterGenerateDetailSetReturnOutEnd(distrInReturnOut, distrInReturnOutDetails);

        Map<String ,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("returnId",distrInReturnOut.getBaseOrderId());
        List<DistrInReturnOut> distrInReturnOuts = distrInReturnOutMapper.selectByMap(map);

        if(!CollectionUtils.isEmpty(distrInReturnOuts)){

            DistrInReturnOut distr = distrInReturnOuts.get(0);
            if(distr.getBaseOrderId().equals(distrInReturnOut.getBaseOrderId())){

                distrInReturnOut.setId(distr.getId());
                distrInReturnOut.setCode(distr.getCode());

                /**
                 * 删除被驳回的明细和货位行
                 */
                distrInReturnOutDetailMapper.deleteByOutId(distr.getId());
                distrInReturnOutShelfMapper.deleteByOutId(distr.getId());
            }

        }

        if(null == distrInReturnOut.getId()){
            String code = orderCodeComponent.generate(
                    OrderRule.DISTR_IN_RETURN_OUT.getCodePrefix()
                    , userVO.getEnterpriseId()
                    , userVO.getEnterpriseCode()
            );
            distrInReturnOut.setCode(code);
            distrInReturnOutMapper.insertSelective(distrInReturnOut);
        }else {
            distrInReturnOutMapper.updateByPrimaryKeySelective(distrInReturnOut);
        }


        for(DistrInReturnOutDetail distrInReturnOutDetail : distrInReturnOutDetails) {

            distrInReturnOutDetail = DistrInReturnOutDetail.afterSaveDistrInReturnOutSetDetail(distrInReturnOutDetail, distrInReturnOut);

/*
            List<DistrInReturnOutDetail> distrInoutDetail = distrInReturnOutDetailMapper.selectByParam(distrInReturnOut.getId(), distrInReturnOutDetail.getBaseOrderDtlId(), userVO.getEnterpriseId());
*/

            if(null == distrInReturnOutDetail.getId()) {
                distrInReturnOutDetailMapper.insertSelective(distrInReturnOutDetail);
            }else {
                distrInReturnOutDetailMapper.updateByPrimaryKeySelective(distrInReturnOutDetail);
            }
        }


        List<DistrInReturnOutShelf> distrInReturnOutShelves = getDistrInReturnOutShelfs(userVO, distrInReturnDetails, distrInReturnOutDetails, distrInReturnOutFormVO);

        distrInReturnOutShelves.forEach(distrInReturnOutShelf -> {
            if(null == distrInReturnOutShelf.getId()) {
                distrInReturnOutShelfMapper.insertSelective(distrInReturnOutShelf);
            }else {
                distrInReturnOutShelfMapper.updateByPrimaryKeySelective(distrInReturnOutShelf);
            }
        });

        return distrInReturnOut;
    }

    public List<DistrInReturnOutShelf>  getDistrInReturnOutShelfs(UserVO userVO
            , List<DistrInReturnDetail> distrInReturnDetails
            ,List<DistrInReturnOutDetail> distrInReturnOutDetails
            ,DistrInReturnOutFormVO distrInReturnOutFormVO
    ) throws Exception {

        List<DistrInReturnOutShelf> distrInReturnOutShelves = new ArrayList<>();

        List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS = distrInReturnOutFormVO.getDistrInReturnOutDetailFormVOS();

        List<Long> shelfIds = DistrInReturnOutDetailFormVO.getShelfIds(distrInReturnOutDetailFormVOS);
        List<WarehouseShelf> warehouseShelves = warehouseShelfMapper.selectByIds(shelfIds);

        for(DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO : distrInReturnOutDetailFormVOS){

            for(DistrInReturnOutDetail distrInReturnOutDetail : distrInReturnOutDetails){
                if(distrInReturnOutDetail.getBaseOrderDtlId().equals(distrInReturnOutDetailFormVO.getReturnDetailId())){


                    List<DistrInReturnOutShelfFormVO> distrInReturnOutShelfFormVOS = distrInReturnOutDetailFormVO.getDistrInReturnOutShelfFormVOS();

                    for(DistrInReturnOutShelfFormVO distrInReturnOutShelfFormVO : distrInReturnOutShelfFormVOS){

                        DistrInReturnOutShelfParam distrInReturnOutShelfParam = new DistrInReturnOutShelfParam();

                        distrInReturnOutShelfParam.setUserVO(userVO);

                        distrInReturnOutShelfParam.setDistrInReturnOutShelfFormVO(distrInReturnOutShelfFormVO);
                        distrInReturnOutShelfParam.setDistrInReturnDetails(distrInReturnDetails);
                        distrInReturnOutShelfParam.setDistrInReturnOutDetail(distrInReturnOutDetail);
                        for(WarehouseShelf warehouseShelf : warehouseShelves){
                            if(warehouseShelf.getId().equals(distrInReturnOutShelfFormVO.getShelfId())){
                                distrInReturnOutShelfParam.setWarehouseShelf(warehouseShelf);
                            }

                        }
                        DistrInReturnOutShelf distrInReturnOutShelf = DistrInReturnOutShelf.getDistrInReturnOutShelf(distrInReturnOutShelfParam);

                        distrInReturnOutShelves.add(distrInReturnOutShelf);
                    }

                }
            }

        }

        return distrInReturnOutShelves;

    }

    public List<DistrInReturnOutDetail> getDistrInReturnOutDetail(UserVO userVO,DistrInReturnOutFormVO distrInReturnOutFormVO,List<DistrInReturnDetail> distrInReturnDetails) throws Exception {

        List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS = distrInReturnOutFormVO.getDistrInReturnOutDetailFormVOS();



        List<Long> taxRateIds = DistrInReturnOutDetailFormVO.getTaxRateIds(distrInReturnOutDetailFormVOS);

        List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByIds(taxRateIds);

        List<DistrInReturnOutDetail> distrInReturnOutDetails = new ArrayList<>();


        for(DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO : distrInReturnOutDetailFormVOS){

            DistrInReturnOutDetailParam distrInReturnOutDetailParam = new DistrInReturnOutDetailParam();
            distrInReturnOutDetailParam.setUserVO(userVO);
            distrInReturnOutDetailParam.setDistrInReturnOutDetailFormVO(distrInReturnOutDetailFormVO);
            distrInReturnOutDetailParam.setDistrInReturnDetails(distrInReturnDetails);
            distrInReturnOutDetailParam.setGoodsTaxRates(goodsTaxRates);

            DistrInReturnOutDetail distrInReturnOutDetail = DistrInReturnOutDetail.getDistrInReturnOutDetail(
                    distrInReturnOutDetailParam
            );

            distrInReturnOutDetails.add(distrInReturnOutDetail);
        }

        return distrInReturnOutDetails;

    }

    /**
     * 复核
     * @param userVO
     * @param distrInReturnOutAuditFormVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> audit(UserVO userVO, DistrInReturnOutAuditFormVO distrInReturnOutAuditFormVO) throws Exception {

        DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(distrInReturnOutAuditFormVO.getId());

        distrInReturnOut = DistrInReturnOut.setAudit(userVO, distrInReturnOut,distrInReturnOutAuditFormVO);

        distrInReturnOutMapper.updateByAudit(distrInReturnOut);

        if(ChainType.Division.getCode() == userVO.getChainType()) {
        	
        	DistrInReturnOutDetail distrInReturnOutDetail = new DistrInReturnOutDetail();
        	distrInReturnOutDetail.setOutId(distrInReturnOut.getId());
        	distrInReturnOutDetail.setStatus(distrInReturnOut.getStatus());
        	distrInReturnOutDetailMapper.updateByOutId(distrInReturnOutDetail);
        	
        	DistrInReturnOutShelf distrInReturnOutShelf = new DistrInReturnOutShelf();
        	distrInReturnOutShelf.setOutId(distrInReturnOut.getId());
        	distrInReturnOutShelf.setStatus(distrInReturnOut.getStatus());
        	distrInReturnOutShelfMapper.updateByOutId(distrInReturnOutShelf);
        }else if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) {
        	if(distrInReturnOut.getStatus().equals(DistrInReturnStatus.FINISHED)) {
        		List<DistrInReturnOutDetail> distrInReturnOutDetails = distrInReturnOutDetailMapper.selectByOutId(distrInReturnOut.getId());
        		DistrInReturnOutDetail detail = new DistrInReturnOutDetail();
        		for(DistrInReturnOutDetail dtl : distrInReturnOutDetails) {
        			detail.setId(dtl.getId());
        			detail.setStatus(distrInReturnOut.getStatus());
        			detail.setUnclearQuantity(BigDecimal.ZERO);
        			detail.setClearQuantity(dtl.getQuantity());
        			distrInReturnOutDetailMapper.updateByPrimaryKeySelective(detail);
        		}
        		List<DistrInReturnOutShelf> DistrInReturnOutShelfs = distrInReturnOutShelfMapper.selectByOutId(distrInReturnOut.getId());
        		DistrInReturnOutShelf shelf = new DistrInReturnOutShelf();
        		for(DistrInReturnOutShelf slf : DistrInReturnOutShelfs) {
        			shelf.setId(slf.getId());
        			shelf.setStatus(distrInReturnOut.getStatus());
        			shelf.setUnclearQuantity(BigDecimal.ZERO);
        			shelf.setClearQuantity(slf.getQuantity());
        			distrInReturnOutShelfMapper.updateByPrimaryKeySelective(shelf);
        		}
        	}
        }
        

        List<String> strings = new ArrayList<>();
        //待开票或是已完成
        if(distrInReturnOut.getStatus().equals(DistrInReturnStatus.WAIT_BILL) || distrInReturnOut.getStatus().equals(DistrInReturnStatus.FINISHED) ){

            DistrInReturn distrInReturn = new DistrInReturn();
            distrInReturn.setId(distrInReturnOut.getBaseOrderId());
            distrInReturn.setStatus(distrInReturnOut.getStatus());
            distrInReturnMapper.updateByPrimaryKeySelective(distrInReturn);

            DistrInReturnDetail distrInReturnDetail = new DistrInReturnDetail();
            distrInReturnDetail.setInReturnId(distrInReturnOut.getBaseOrderId());
            distrInReturnDetail.setStatus(distrInReturnOut.getStatus());
            distrInReturnDetailMapper.updateByInReturnId(distrInReturnDetail);

            List<DistrInReturnOutDetail> distrInReturnOutDetails = distrInReturnOutDetailMapper.selectByOutId(distrInReturnOut.getId());

            ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);

            /**
             * 业务流程质量控制（0关闭；1-开启）
             */
            if(null != manageConfig && manageConfig.getBusinessControl() == EnableStatus.DISABLE.getStatus()){
                /**
                 * 判断是否质量控制开启,如果关闭需要更新配进购出单的明细行数量,如果开启则在保存接口中注解里校验了数量
                 */
                for(DistrInReturnOutDetail dr : distrInReturnOutDetails){

                    DistrInReturnDetail dir = distrInReturnDetailMapper.selectByPrimaryKey(dr.getBaseOrderDtlId());
                    DistrInReturnDetail newDistrInReturnDetail = new DistrInReturnDetail();
                    newDistrInReturnDetail.setId(dir.getId());
                    newDistrInReturnDetail.setQuantity(dr.getQuantity());
                    distrInReturnDetailMapper.updateByPrimaryKeySelective(newDistrInReturnDetail);
                }

            }

            /**
             * 修改配进退出的已清未清数量
             */
            for(DistrInReturnOutDetail dr : distrInReturnOutDetails){

                DistrInReturnDetail dir = distrInReturnDetailMapper.selectByPrimaryKey(dr.getBaseOrderDtlId());
                DistrInReturnDetail newDistrInReturnDetail = new DistrInReturnDetail();
                newDistrInReturnDetail.setId(dir.getId());
                BigDecimal subtract = dir.getUnclearQuantity().subtract(dr.getQuantity());
                if(subtract.compareTo(BigDecimal.ZERO) < 0){
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"修改未清数量错误,修改完数量为"+subtract);
                }
                newDistrInReturnDetail.setUnclearQuantity(subtract);
                newDistrInReturnDetail.setClearQuantity(dr.getQuantity());
                distrInReturnDetailMapper.updateByPrimaryKeySelective(newDistrInReturnDetail);
            }

            /**
             * 修改配进退出上游的入库单的可退数量
             */
            DistrInReturn dr = distrInReturnMapper.selectByPrimaryKey(distrInReturnOut.getBaseOrderId());

            Long baseOrderId = dr.getBaseOrderId();

            if(null != baseOrderId && 0 != baseOrderId){
                List<DistrInShelf> distrInShelves = distrInShelfMapper.selectByEnterpriseIdAndInId(baseOrderId);
                List<DistrInReturnOutShelf> slf = distrInReturnOutShelfMapper.selectByOutId(distrInReturnOut.getId());

                deductInventory4DistrInShelf(distrInShelves,slf);
            }


            List<DistrInReturnOutShelf> distrInReturnOutShelfs = distrInReturnOutShelfMapper.selectByOutId(distrInReturnOut.getId());





            // 释放配进退出单锁定的库存
            LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
            lockQtyArgVO.setEnterpriseId(distrInReturnOut.getEnterpriseId());
            lockQtyArgVO.setParentId(distrInReturnOut.getParentId());
            lockQtyArgVO.setBaseOrderId(distrInReturnOut.getBaseOrderId());
            lockQtyArgVO.setBaseOrderType(distrInReturnOut.getBaseOrderType());
            lockQtyArgVO.setUser(userVO);
            commonComponent.releaseStockAndCost(lockQtyArgVO);

            /**
             *已完成后续处理
             */
            List<DistrInReturnOutShelf> distrInReturnOutShelves = distrInReturnOutShelfMapper.selectByOutId(distrInReturnOut.getId());
            OrderModelBuilder builder = new OrderModelBuilder(userVO);
            OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_IN_RETURN_OUT, distrInReturnOut, distrInReturnOutShelves);
            inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);

            /**
             * 配送类型是直调配送的，保存后逆向生成“配后退回、配后退回收货、配后退回验收、配后退回入库、购进退出、购进退出出库
             */
            /**
             * 业务流程质量控制（0关闭；1-开启）
             */



            strings = generateAndSaveDistrReturnOrder(
                    userVO,
                    dr,
                    distrInReturnOut,
                    distrInReturnOutDetails,
                    distrInReturnOutShelfs,
                    manageConfig
            );

            /**
             * 财务业务表数据
             */
            financeComponent.distrInReturnOutToBalanceAndVoucher(userVO, distrInReturnOut);

        }

        return strings;
    }


    private final static String distrReturnOrderStr = "已经成功生成%s";

    private final static String distrReturnOrderNoStr = ":%s";



    /**
     * 配送类型是直调配送的，保存后逆向生成“配后退回、配后退回收货、配后退回验收、配后退回入库、购进退出、购进退出出库
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> generateAndSaveDistrReturnOrder(UserVO loginUser ,DistrInReturn dr,DistrInReturnOut distrInReturnOut,List<DistrInReturnOutDetail> distrInReturnOutDetails,List<DistrInReturnOutShelf> distrInReturnOutShelfs,ManageConfig manageConfig) throws Exception {

        List<String> orderStrs = new ArrayList<>();

        UserVO sourceUser = new UserVO();
        sourceUser = orikaMapperFactory.copyBean(sourceUser,loginUser);

        StringBuffer sb = new StringBuffer();

        /**
         * 生成配后退回通知单 ,无论什么类型,复核同意后 ,都要生成配后退回通知单
         */
        DistrReturnNoticeFormVO distrReturnNoticeFormVO = DistrReturnNoticeFormVO.getDistrReturnNoticeFormVO(loginUser,distrInReturnOut);

        List<DistrReturnNoticeDtlVO> distrReturnNoticeDtlVOs = DistrReturnNoticeDtlVO.getDistrReturnNoticeDtlVOs(distrInReturnOutDetails,distrInReturnOut);

        distrReturnNoticeFormVO.setDistrReturnNoticeDtlVOList(distrReturnNoticeDtlVOs);

        DistrReturnNoticeMapVO distrReturnNoticeMapVO = distrComponent.saveInNoticeStatus4Parent(distrReturnNoticeFormVO, loginUser);

        String noticeCode = distrReturnNoticeMapVO.getDistrReturnNotice().getCode();
        sb.append(String.format(distrReturnOrderStr,"配后退回通知")).append(String.format(distrReturnOrderNoStr,noticeCode));
        orderStrs.add(sb.toString());
        sb.setLength(0);

        /**
         *  业务流程质量控制（0关闭；1-开启）
         */
        Integer businessControl = manageConfig.getBusinessControl();
        if(0 == businessControl && (DistrType.DIRECT_DISTRIBUTION.getCode() == dr.getDistrType() || DistrType.SWAP_BETWEEN_STORES.getCode() == dr.getDistrType())){
            /**
             * 配送类型是直调配送的，保存后逆向生成“配后退回收货、配后退回验收、配后退回入库、购进退出、购进退出出库
             */

            /**
             * 门店出库到总部,需要把当前门店的登录人的 parentid赋值到enterpriseId上
             */
            UserVO userVO = new UserVO();
            userVO = orikaMapperFactory.copyBean(userVO,loginUser);
            userVO.setEnterpriseId(loginUser.getParentId());
            userVO.setParentId(0L);
            userVO.setEnterpriseCode(loginUser.getParentCode());
            userVO.setParentCode("");
            userVO.setEnterpriseName(loginUser.getParentName());
            userVO.setParentName("");
            userVO.setChainType(ChainType.Headquarters.getCode());
            /**
             * 此时生成的是总部的流程,所以需要查询总部的manageconfig
             */
            manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);

            if(null == manageConfig){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的系统配置不存在");
            }
            /**
             * 收货人员取manageconfig里面的收货人员 , 配进退出退回总部,所以配后退回的收货默认人员取总部的默认收货人员
             */
            Long receiverId = manageConfig.getReceiverId();
            Long secondReceiverId = manageConfig.getSecondReceiverId();
            distrReturnNoticeMapVO.setReceiverId(receiverId);
            distrReturnNoticeMapVO.setSecondReceiverId(secondReceiverId);


            if(null == receiverId ){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的默认收货人员不存在");
            }
            if(null == secondReceiverId ){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的默认第二收货人员不存在");
            }

            Long checkerId = manageConfig.getCheckerId();
            Long secondCheckerId = manageConfig.getSecondCheckerId();

            if(null == checkerId ){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的默认验收人员不存在");
            }
            if(null == secondCheckerId ){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的默认第二验收人员不存在");
            }

            DistrReturnReceiveSaveVO distrReturnReceiveSaveVO = DistrReturnReceiveSaveVO.getDistrReturnReceiveSaveVO(distrReturnNoticeMapVO);




            /**
             * 生成和保存配后退回收货
             */
            Result<Map<String, Object>> mapResult = commonComponent.generateAndSaveDistrReturnReceiveOrder(userVO, distrReturnReceiveSaveVO);

            Map<String, Object> data = mapResult.getData();
            DistrReturnReceive distrReturnReceive = (DistrReturnReceive) data.get("distrReturnReceive");
            List<DistrReturnReceiveDetail> distrReturnReceiveDetails = (List<DistrReturnReceiveDetail>) data.get("distrReturnReceiveDetails");

            /**
             * 配货退回收货明细行id : 配进退出出库明细行id
             */
            Map<Long,Long> receiveDetailReturnNoticeDtlMap = new HashMap<>();

            List<DistrReturnNoticeDetail> distrReturnNoticeDetails = distrReturnNoticeMapVO.getDistrReturnNoticeDetails();

            for(DistrReturnReceiveDetail dd : distrReturnReceiveDetails){
                Long baseOrderDtlId = dd.getBaseOrderDtlId();

                for(DistrReturnNoticeDetail dn : distrReturnNoticeDetails){

                    if(baseOrderDtlId.equals(dn.getId())){
                        receiveDetailReturnNoticeDtlMap.put(dd.getId(),dn.getBaseOrderDtlId());
                    }
                }

            }

            /**
             *  配货退回收货明细行id : 配进退出出库货位表(验收时只需要批号,配进退出出库货位表中对应的明细行 批号都是一样的 只有货位不一样,所以对应的明细行保存其中任意一个货位实体就可以)
             */
            Map<Long,DistrInReturnOutShelf> receiveDetailDistrInReturnOutShelfMap = new HashMap<>();

            for(DistrReturnReceiveDetail dd : distrReturnReceiveDetails){
                for(DistrInReturnOutShelf ds : distrInReturnOutShelfs){
                    Long baseOrderId = receiveDetailReturnNoticeDtlMap.get(dd.getId());
                    if(baseOrderId.equals(ds.getDtlId())){
                        receiveDetailDistrInReturnOutShelfMap.put(dd.getId(),ds);
                        continue;
                    }
                }

            }

            sb.append(String.format(distrReturnOrderStr,"配后退回收货单")).append(String.format(distrReturnOrderNoStr,distrReturnReceive.getCode()));
            orderStrs.add(sb.toString());
            sb.setLength(0);


            /**
             * 生成和保存赔后退回验收
             */

            List<Long> goodsIds = distrReturnReceiveDetails.stream().map(distrReturnReceiveDetail -> distrReturnReceiveDetail.getGoodsId()).collect(Collectors.toList());

            if(CollectionUtils.isEmpty(goodsIds)){
                throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品未找到");
            }

            List<Goods> goods = goodsMapper.selectByIds(goodsIds, userVO.getEnterpriseId());

            Map<Long, Goods> goodsMap = Goods.getGoodsMap(goods);

            List<SafetyStock> safetyStocks = safetyStockMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, userVO.getEnterpriseId());

            Map<Long, SafetyStock> safetyStockMap = SafetyStock.getSafetyStockMap(safetyStocks);

/*
            Map<Long, List<DistrInReturnOutShelf>> shelfsGoodsMap = DistrInReturnOutShelf.getShelfsGoodsMap(distrInReturnOutShelfs);
*/

            List<String> goodsCodes = Goods.getGoodsCodes(goods);

            CheckProjectGoodsVO checkProjects = commonComponent.getCheckProjects(goodsCodes, userVO.getEnterpriseId());

            QualitySet qualitySet = qualitySetMapper.selectByCode("01",6);

            SaveDistrReturnCheckVO saveDistrReturnCheckVO = SaveDistrReturnCheckVO.getSaveDistrReturnCheckVO(
                    distrReturnReceive,
                    distrReturnReceiveDetails,
                    checkerId,
                    secondCheckerId,
                    goodsMap,
                    checkProjects,
                    qualitySet,
                    receiveDetailDistrInReturnOutShelfMap
            );

            Result<Map<String, Object>> distrResultMap = commonComponent.generateAndSaveDistrReturnCheck(userVO, saveDistrReturnCheckVO);

            Map<String, Object> distrResultDate = distrResultMap.getData();

            List<DistrReturnCheckDetail> distrReturnCheckDetailList = (List<DistrReturnCheckDetail>) distrResultDate.get("baseOrderDtlIdList");
            List<DistrReturnCheckLot> distrReturnCheckLots = (List<DistrReturnCheckLot>) distrResultDate.get("distrReturnCheckLots");
            DistrReturnCheck distrReturnCheck = (DistrReturnCheck) distrResultDate.get("distrReturnCheck");

            sb.append(String.format(distrReturnOrderStr,"配后退回验收单")).append(String.format(distrReturnOrderNoStr,distrReturnCheck.getCode()));
            orderStrs.add(sb.toString());
            sb.setLength(0);

            /**
             * 配后退回入库
             */
            Long inOutManId = manageConfig.getInOutManId();
            if(null == inOutManId ){
                throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的默认收货人员不存在");
            }


            DistrReturnInFormVO distrReturnInFormVO = DistrReturnInFormVO.getDistrReturnInFormVO(
                    distrReturnCheck,
                    distrReturnCheckDetailList,
                    distrReturnCheckLots,
                    distrReturnNoticeMapVO.getDistrReturnNotice(),
                    distrReturnNoticeMapVO.getDistrReturnNoticeDetails(),
                    inOutManId,
                    safetyStockMap
            );
            DistrReturnInVO distrReturnInVO = commonComponent.generateAndSaveDistrReturnIn(userVO, distrReturnInFormVO);

            sb.append(String.format(distrReturnOrderStr,"配后退回入库单")).append(String.format(distrReturnOrderNoStr,distrReturnInVO.getDistrReturnIn().getCode()));
            orderStrs.add(sb.toString());
            sb.setLength(0);

            /**
             * 只有直调配送才需要逆向生成购进退出 购进退出出库
             */
            if(DistrType.DIRECT_DISTRIBUTION.getCode() == dr.getDistrType()) {

                /**
                 * 购进退出
                 */
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("user", userVO);
                map.put("setType", 2);
                map.put("type", 1);
                //如果当前用户不是总部的
                List<QualitySet> qualitySets = qualitySetMapper.getQualitySettingsById(map);
                Long qualitySetId = 0L;
                QualitySet q = qualitySets.stream().filter(qs -> "07".equals(qs.getCode())).findFirst().orElse(null);
                if (null != q) {
                    qualitySetId = q.getId();
                }


                EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
                if (bus != null) {
                    bus.setSettlementModeName(SettlementMode.getName(bus.getSettlementMode()));
                    bus.setDistrPriceTypeName(DistrPriceType.getName(bus.getDistrPriceType()));
                    bus.setPaymentProvisionName(PaymentProvision.getName(bus.getPaymentProvision()));
                    bus.setPaymentPeriodUnitName(PaymentPeriodUnit.getName(bus.getPaymentPeriodUnit()));
                    bus.setPaymentTimeUnitName(PaymentTimeUnit.getName(bus.getPaymentTimeUnit()));
                }

            /*distrReturnInVO.setShelfsGoodsMap(shelfsGoodsMap);*/

                Supplier supplier = supplierMapper.selectByPrimaryKey(dr.getOutboundUnitId());

                if (null == supplier) {
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "供货单位不存在");
                }

                if (null == supplier.getSaleManId()) {
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "总部的默认供货单位销售人员不存在");
                }

                distrReturnInVO.setSupplier(supplier);
                distrReturnInVO.setReturnManId(dr.getReturnManId());
                distrReturnInVO.setQualitySetId(qualitySetId);
                distrReturnInVO.setBus(bus);

                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(sourceUser.getEnterpriseId());
                Enterprise parentEnterprise = enterpriseMapper.selectByPrimaryKey(sourceUser.getParentId());
                distrReturnInVO.setEnterprise(enterprise);
                distrReturnInVO.setParentEnterprise(parentEnterprise);
                distrReturnInVO.setSourceUser(sourceUser);

                PurchaseReturnSaveOrUpdateVO purchaseReturnSaveOrUpdateVO = PurchaseReturnSaveOrUpdateVO.getPurchaseReturnSaveOrUpdateVO(
                        distrReturnInVO
                );
                PurchaseReturnsVO purchaseReturnsVO = commonComponent.generateAndSavePurchaseReturn(userVO, purchaseReturnSaveOrUpdateVO, true, true);

                sb.append(String.format(distrReturnOrderStr,"购进退出单")).append(String.format(distrReturnOrderNoStr,purchaseReturnsVO.getPurchaseReturn().getCode()));
                orderStrs.add(sb.toString());
                sb.setLength(0);

                /**
                 * 购进退出出库
                 */

                purchaseReturnsVO.setInOutManId(inOutManId);
                purchaseReturnsVO.setDistrReturnInVO(distrReturnInVO);

                RequestPurchaseReturnOutInfoVO purchaseReturnOutInfoVO = RequestPurchaseReturnOutInfoVO.getRequestPurchaseReturnOutInfoVO(
                        purchaseReturnsVO
                );
                PurchaseReturnOut purchaseReturnOut = commonComponent.generateAndSavePurchaseReturnOut(purchaseReturnOutInfoVO, userVO);
                RequestCheckVO requestCheckVO = RequestCheckVO.getRequestCheckVO(purchaseReturnOut);
                commonComponent.auditPurchaseReturnOut(requestCheckVO,userVO);

                sb.append(String.format(distrReturnOrderStr,"购进退出出库单")).append(String.format(distrReturnOrderNoStr,purchaseReturnOut.getCode()));
                orderStrs.add(sb.toString());
                sb.setLength(0);

            }else if(DistrType.SWAP_BETWEEN_STORES.getCode() == dr.getDistrType()){
                /**
                 * 门店间调剂:总部配货单,总部拣货单,总部配货出库单,调入门店配进订单
                 */

                Long baseOrderId = distrInReturnOut.getBaseOrderId();

                /**
                 * 修改要货计划为已配货
                 */
                DistrInReturn distrInReturn = distrInReturnMapper.selectByPrimaryKey(baseOrderId);
                DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(distrInReturn.getBaseOrderId());

                DistrReqPlan newDistrReqPlan = new DistrReqPlan();
                newDistrReqPlan.setId(distrReqPlan.getId());
                newDistrReqPlan.setStatus(DistrReqPlanStatus.FINISHED);
                distrReqPlanMapper.updateByPrimaryKeySelective(newDistrReqPlan);


                /**
                 * 总部配货出库单 逆向生成总部配货单,总部拣货单
                 */
                Long distrManId = manageConfig.getDistrManId();
                if(null == distrManId ){
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的默认配货人员不存在");
                }

                SaveDistrOutVO saveDistrOutVO = SaveDistrOutVO.getSaveDistrOutVO(distrReturnInVO,distrInReturnOut,distrManId,inOutManId);
                Map<String, Object> dotMap = commonComponent.saveDistrOut(userVO, saveDistrOutVO, true);
                DistrOut distrOut = (DistrOut) dotMap.get("distrOut");
                DistrSend distrSend = (DistrSend) dotMap.get("distrSend");
                PickOrder pickOrder = (PickOrder) dotMap.get("pickOrder");

                sb.append(String.format(distrReturnOrderStr,"配货单")).append(String.format(distrReturnOrderNoStr,distrSend.getCode()));
                orderStrs.add(sb.toString());
                sb.setLength(0);

                sb.append(String.format(distrReturnOrderStr,"拣货单")).append(String.format(distrReturnOrderNoStr,pickOrder.getCode()));
                orderStrs.add(sb.toString());
                sb.setLength(0);


                DistrOut newDistrOut = new DistrOut();
                newDistrOut.setId(distrOut.getId());
                newDistrOut.setBaseOrderId(distrSend.getId());
                newDistrOut.setBaseOrderDate(distrSend.getDistrDate());
                newDistrOut.setBaseOrderCode(distrSend.getCode());
                newDistrOut.setBaseOrderType(distrSend.getOrderType());
                distrOutMapper.updateByPrimaryKeySelective(newDistrOut);
                List<DistrOutDetail> distrOutDetailList = distrOut.getDistrOutDetailList();
                List<DistrSendDetail> distrSendDetailList = distrSend.getDistrSendDetailList();
                for(DistrOutDetail distrOutDetail : distrOutDetailList){
                    for(DistrSendDetail distrSendDetail : distrSendDetailList){
                        if(distrOutDetail.getGoodsId().equals(distrSendDetail.getGoodsId())){
                            DistrOutDetail newDistrOutDetail = new DistrOutDetail();
                            newDistrOutDetail.setId(distrOutDetail.getId());
                            newDistrOutDetail.setBaseOrderId(distrSend.getId());
                            newDistrOutDetail.setBaseOrderDtlId(distrSendDetail.getId());
                            newDistrOutDetail.setBaseOrderDate(distrSend.getDistrDate());
                            newDistrOutDetail.setBaseOrderCode(distrSend.getCode());
                            newDistrOutDetail.setBaseOrderType(distrSend.getOrderType());
                            distrOutDetailMapper.updateByPrimaryKeySelective(newDistrOutDetail);
                        }
                    }
                }

                DistrSend newDistrSend = new DistrSend();
                newDistrSend.setId(distrSend.getId());
                newDistrSend.setStatus(DistrSendStatus.OUTING);
                distrSendMapper.updateByPrimaryKeySelective(newDistrSend);
                DistrOutCheckVo distrOutCheckVo = DistrOutCheckVo.getDistrOutCheckVo(distrOut);
                DistrInNotice distrInNotice = commonComponent.distrOutcheck(distrOutCheckVo, userVO);

                sb.append(String.format(distrReturnOrderStr,"配货出库单")).append(String.format(distrReturnOrderNoStr,distrOut.getCode()));
                orderStrs.add(sb.toString());
                sb.setLength(0);

                /**
                 * 调入门店配进订单
                 */

                UserVO out = new UserVO();
                out = orikaMapperFactory.copyBean(out,loginUser);
                out.setEnterpriseId(distrInReturnOut.getOutboundUnitId());
                out.setEnterpriseCode(distrInReturnOut.getOutboundUnitCode());
                out.setEnterpriseName(distrInReturnOut.getOutboundUnitName());
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrInReturnOut.getOutboundUnitId());
                Enterprise parentEnterprise = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());
                out.setParentId(parentEnterprise.getId());
                out.setParentCode(parentEnterprise.getCode());
                out.setParentName(parentEnterprise.getName());

                /**
                 * 此时生成的是总部的流程,所以需要查询总部的manageconfig
                 */
             /*   manageConfig = manageConfigComponent.getMangeConfigByEPId(out);
                Long requesterId = manageConfig.getRequesterId();
              *//*  Long checkerUserId = manageConfig.getCheckerId();*//*

                if(null == manageConfig){
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"总部的系统配置不存在");
                }

                if (null == requesterId) {
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "默认门店配货人员不存在");
                }
*/
             /*   AddInstorageVO addInstorageVO = AddInstorageVO.getAddInstorageVO(distrOut, distrInReturnOut, enterprise, requesterId);

                Map<String,Object> distrOutMap = new HashMap<>();
                distrOutMap.put("distrOut",distrOut);
                distrOutMap.put("distrOutDetail",distrOut.getDistrOutDetailList());*/
/*
                Map<String, Object> map = commonComponent.addDistrNotice(out, addInstorageVO, distrOutMap);
*/
              /*  DistrInNotice distrInNotice = (DistrInNotice) map.get("distrInNotice");
                DistrInNotice newDistrInNotice = new DistrInNotice();
                newDistrInNotice.setId(distrInNotice.getId());
                newDistrInNotice.setStatus(DistrInStatus.WAIT_RECEIVE);
                distrInNoticeMapper.updateByPrimaryKeySelective(newDistrInNotice);

                List<DistrInNoticeDetail> distrInNoticeDetails = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(distrInNotice.getId(), distrInNotice.getEnterpriseId());
                for(DistrInNoticeDetail distrInNoticeDetail : distrInNoticeDetails){

                    DistrInNoticeDetail newDistrInNoticeDetail = new DistrInNoticeDetail();
                    newDistrInNoticeDetail.setId(distrInNoticeDetail.getId());
                    newDistrInNoticeDetail.setStatus(DistrInStatus.WAIT_RECEIVE);
                    distrInNoticeDetailMapper.updateByPrimaryKeySelective(newDistrInNoticeDetail);
                }*/

                Enterprise inEnterprise = enterpriseMapper.selectByPrimaryKey(distrInNotice.getEnterpriseId());
                sb.append(String.format(distrReturnOrderStr,inEnterprise.getName()+"配进订单")).append(String.format(distrReturnOrderNoStr,distrInNotice.getCode()));
                orderStrs.add(sb.toString());
                sb.setLength(0);
                /*result.put("distrInNotice",distr);
                result.put("distrInNoticeDetail",distrInNoticeDetailList);*/
            }

        }

        return orderStrs;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deductInventory4DistrInShelf(List<DistrInShelf> distrInShelves, List<DistrInReturnOutShelf> distrInReturnOutShelves){

        //批号:货位实体list
        Map<String,List<DistrInReturnOutShelf>> inReturnOutShelf = new HashMap<>();

        for(DistrInReturnOutShelf distrInReturnOutShelf : distrInReturnOutShelves){

            List<DistrInReturnOutShelf> dfs = inReturnOutShelf.get(distrInReturnOutShelf.getLotNumber());
            if(CollectionUtils.isEmpty(dfs)){
                dfs = new ArrayList<>();
                dfs.add(distrInReturnOutShelf);
                inReturnOutShelf.put(distrInReturnOutShelf.getLotNumber(),dfs);
            }else {
                dfs.add(distrInReturnOutShelf);
            }

        }


        for(Map.Entry<String,List<DistrInReturnOutShelf>> entry : inReturnOutShelf.entrySet()){

            Map<Long,BigDecimal> returnOutGoodsQuantityMap = new HashMap<>();

            List<DistrInReturnOutShelf> distrInReturnOutShelfs = inReturnOutShelf.get(entry.getKey());

            for(DistrInReturnOutShelf dl : distrInReturnOutShelfs){

                BigDecimal quantity = returnOutGoodsQuantityMap.get(dl.getGoodsId());

                if(null == quantity){

                    returnOutGoodsQuantityMap.put(dl.getGoodsId(),dl.getQuantity());

                }else {
                    quantity = quantity.add(dl.getQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    returnOutGoodsQuantityMap.put(dl.getGoodsId(),quantity);
                }

            }

            for(DistrInShelf distrInShelf : distrInShelves){

                if(distrInShelf.getLotNumber().equals(entry.getKey())){

                    Long goodsId = distrInShelf.getGoodsId();
                    //出库单中需要出库的商品数量
                    BigDecimal mustDeductInventory = returnOutGoodsQuantityMap.get(goodsId);

                    if(null != mustDeductInventory && BigDecimal.ZERO.compareTo(mustDeductInventory) < 0){

                        //入库单中货位中的可退数量
                        BigDecimal canReturnQuantity = distrInShelf.getCanReturnQuantity();

                        BigDecimal subtract = mustDeductInventory.subtract(canReturnQuantity);

                        if(BigDecimal.ZERO.compareTo(subtract) == 0){

                            //出库数量减去入库单的可退数量如果大于0 表示该入库单货位的货位中的数量正好扣完
                            distrInShelf.setCanReturnQuantity(BigDecimal.ZERO);

                        }else if(BigDecimal.ZERO.compareTo(subtract) > 0){

                            //出库数量减去入库单的可退数量如果小于0 表示该入库单货位的货位中的数量还有剩余
                            subtract = subtract.abs();
                            distrInShelf.setCanReturnQuantity(subtract.setScale(2,BigDecimal.ROUND_HALF_UP));
                        }

                        returnOutGoodsQuantityMap.put(goodsId,subtract);

                    }

                }
            }


            for( Map.Entry<Long,BigDecimal> en : returnOutGoodsQuantityMap.entrySet()){

                BigDecimal value = en.getValue();

                if(BigDecimal.ZERO.compareTo(value) > 0){
                    throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"扣除入库单数量不正确,商品id"+en.getKey());
                }
            }


        }


        /**
         * 修改可退库存
         */
        distrInShelves.forEach(distrInShelf -> {

            DistrInShelf dsf = new DistrInShelf();
            dsf.setId(distrInShelf.getId());
            dsf.setCanReturnQuantity(distrInShelf.getCanReturnQuantity());

            distrInShelfMapper.updateByPrimaryKeySelective(dsf);

        });


    }

    @Override
    public List<ResponseDistrInReturnVO>  getDistrReturnInOrderList(DistrInReturnSearchParam distrInReturnOutSearchParam, UserVO userVO, Page page){
        Map<String,Object> param = new HashMap<>();

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getDistrUnitCode())){
            param.put("distrUnitCode",distrInReturnOutSearchParam.getDistrUnitCode());
        }
        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getDistrUnitName())) {
            param.put("distrUnitName", distrInReturnOutSearchParam.getDistrUnitName());
        }
        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getCode())) {
            param.put("code", distrInReturnOutSearchParam.getCode());
        }
        if(null != distrInReturnOutSearchParam.getDistrType()) {
            param.put("distrType", distrInReturnOutSearchParam.getDistrType());
        }

        if(null != distrInReturnOutSearchParam.getStartTime()) {
            param.put("startTime", distrInReturnOutSearchParam.getStartTime());
        }
        if(null != distrInReturnOutSearchParam.getEndTime()) {
            param.put("endTime", distrInReturnOutSearchParam.getEndTime());
        }

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getReturnManName())) {
            param.put("returnManName", distrInReturnOutSearchParam.getReturnManName());
        }


        if("inReturnDate".equals(distrInReturnOutSearchParam.getOrderName())){
            param.put("orderName", "in_return_date");
        }
        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getOrderType())){
            param.put("orderType", distrInReturnOutSearchParam.getOrderType());
        }
        param.put("enterpriseId",userVO.getEnterpriseId());
        param.put("waitOut",DistrInReturnStatus.WAIT_OUT);
        param.put("outIng",DistrInReturnStatus.OUTTING);
        param.put("waitAudit",DistrInReturnStatus.WAIT_AUDIT);

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());

        List<ResponseDistrInReturnVO> distrReturnInOrderList = distrInReturnOutMapper.getDistrReturnInOrders(param);

        distrReturnInOrderList.forEach(item -> {

            Map<String ,Object> map = new HashMap<>();
            map.put("enterpriseId",userVO.getEnterpriseId());
            map.put("returnId",item.getId());
            List<DistrInReturnOut> distrInReturnOuts = distrInReturnOutMapper.selectByMap(map);
            if(!CollectionUtils.isEmpty(distrInReturnOuts)){
                DistrInReturnOut distrInReturnOut = distrInReturnOuts.get(0);
                item.setStatus(distrInReturnOut.getStatus());
                item.setDistrTypeName(DistrType.getValue(item.getDistrType()));
                item.setStatusDesc(DistrInReturnStatus.getStatusDesc(distrInReturnOut.getStatus()));
            }else {
                item.setDistrTypeName(DistrType.getValue(item.getDistrType()));
                item.setStatusDesc(DistrInReturnStatus.getStatusDesc(item.getStatus()));
            }


        });



        page.setResult(distrReturnInOrderList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());

        return distrReturnInOrderList;
    }

    /**
     * 查询待复核,已完成列表
     * @param distrInReturnOutSearchParam
     * @param page
     * @return
     */
    @Override
    public List<DistrInReturnOutPageVO> getDistrInReturnOuts(DistrInReturnOutSearchParam distrInReturnOutSearchParam,UserVO userVO, Page page){

        Map<String,Object> param = new HashMap<>();

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getDistrUnitCode())){
            param.put("distrUnitCode",distrInReturnOutSearchParam.getDistrUnitCode());
        }
        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getDistrUnitName())) {
            param.put("distrUnitName", distrInReturnOutSearchParam.getDistrUnitName());
        }
        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getCode())) {
            param.put("code", distrInReturnOutSearchParam.getCode());
        }
        if(null != distrInReturnOutSearchParam.getDistrType()) {
            param.put("distrType", distrInReturnOutSearchParam.getDistrType());
        }


        if(null != distrInReturnOutSearchParam.getStartTime()) {
            param.put("startTime", distrInReturnOutSearchParam.getStartTime());
        }
        if(null != distrInReturnOutSearchParam.getEndTime()) {
            param.put("endTime", distrInReturnOutSearchParam.getEndTime());
        }

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getOutManName())) {
            param.put("outManName", distrInReturnOutSearchParam.getOutManName());
        }

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getAuditManName())) {
            param.put("auditManName", distrInReturnOutSearchParam.getAuditManName());
        }

        if(distrInReturnOutSearchParam.getQueryType() == 0){
            List<Integer> list = new ArrayList<>();
            list.add(DistrInReturnStatus.WAIT_AUDIT);
            list.add(DistrInReturnStatus.OUTTING);
            param.put("list",list);

        }else {
            List<Integer> list = new ArrayList<>();
            list.add(DistrInReturnStatus.FINISHED);
            list.add(DistrInReturnStatus.WAIT_BILL);
            list.add(DistrInReturnStatus.PART_BILL);
            param.put("list",list);

        }

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getOrderName())) {

            if("returnInDate".equals(distrInReturnOutSearchParam.getOrderName())){
                param.put("orderName", "return_in_date");
            }else if("code".equals(distrInReturnOutSearchParam.getOrderName())){
                param.put("orderName", "code");
            }

        }

        if(!StringUtils.isEmpty(distrInReturnOutSearchParam.getOrderType())){
            param.put("orderType", distrInReturnOutSearchParam.getOrderType());
        }

        param.put("enterpriseId",userVO.getEnterpriseId());

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<DistrInReturnOut> distrInReturnOuts = distrInReturnOutMapper.selectByParam(param);
        DistrInReturnOutPageTotalVO distrInReturnOutPageTotalVO = distrInReturnOutMapper.selectByParamCount(param);

        List<DistrInReturnOutPageVO> distrInReturnOutPageVOS = new ArrayList<>();

        for(DistrInReturnOut distrInReturnOut : distrInReturnOuts){

            DistrInReturnOutPageVO distrInReturnOutPageVO = DistrInReturnOutPageVO.getDistrInReturnOutPageVO(distrInReturnOut);

            distrInReturnOutPageVOS.add(distrInReturnOutPageVO);
        }

        if(null != distrInReturnOutPageTotalVO){
            distrInReturnOutPageTotalVO.setDistrInReturnOutPageVOS(distrInReturnOutPageVOS);
        }

        page.setResult(distrInReturnOutPageTotalVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());

        return distrInReturnOutPageVOS;
    }


    /**
     * 查看详情
     *
     * @param userVO
     * @param outId
     * @return
     */
    @Override
    public DistrInReturnOutPageVO getDistrInReturnOutsAndDetails(UserVO userVO, Long outId){

        DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(outId);

        List<DistrInReturnOutDetail> distrInReturnOutDetails = distrInReturnOutDetailMapper.selectByOutId(outId);

        List<DistrInReturnOutShelf> distrInReturnOutShelves = distrInReturnOutShelfMapper.selectByOutId(outId);

        Map<Long,List<DistrInReturnOutShelf>>  firstDistrInReturnOutShelfsMap = DistrInReturnOutShelf.getFirstDistrInReturnOutShelfsMap(distrInReturnOutShelves);

        DistrInReturnOutPageVO distrInReturnOutPageVO = DistrInReturnOutPageVO.getDistrInReturnOutPageVO(distrInReturnOut);

        for(DistrInReturnOutDetail distrInReturnOutDetail : distrInReturnOutDetails){

            if(distrInReturnOutDetail.getOutId().equals(distrInReturnOut.getId())){

                List<DistrInReturnOutShelf> distrInReturnOutShelfs = firstDistrInReturnOutShelfsMap.get(distrInReturnOutDetail.getId());

                if(!CollectionUtils.isEmpty(distrInReturnOutShelfs)){

                    DistrInReturnOutShelf distrInReturnOutShelf = distrInReturnOutShelfs.get(0);

                    DistrInReturnOutDetailPageVO distrInReturnOutDetailPageVO = DistrInReturnOutDetailPageVO.getDistrInReturnOutDetailPageVO(distrInReturnOutDetail, distrInReturnOutShelf);


                    distrInReturnOutPageVO.getDistrInReturnOutDetailFormVOS().add(distrInReturnOutDetailPageVO);

                    for(DistrInReturnOutShelf dr : distrInReturnOutShelfs){
                        DistrInReturnOutShelfPageVO distrInReturnOutShelfPageVO = DistrInReturnOutShelfPageVO.getDistrInReturnOutShelfPageVOs(dr);
                        distrInReturnOutDetailPageVO.getDistrInReturnOutShelfFormVOS().add(distrInReturnOutShelfPageVO);
                    }

                }

            }
        }

        distrInReturnOutPageVO.setEnterpriseName(userVO.getEnterpriseName());
        //增加整单折扣金额字段
        BigDecimal wholeDiscountValue = distrInReturnOutPageVO.getAmountTotal().multiply(distrInReturnOutPageVO.getWholeDiscount());
        distrInReturnOutPageVO.setWholeDiscountValue(wholeDiscountValue);
        return distrInReturnOutPageVO;
    }

    @Override
    /**
     * 出库默认信息展示
     *
     */
    public DistrInReturnOutPageVO getDistrInReturnOutsAndDetails4Return(UserVO userVO, Long returnId){

        DistrInReturn distrInReturn = distrInReturnMapper.selectByPrimaryKey(returnId);

        DistrInReturnOutPageVO distrInReturnOutPageVO4Return = DistrInReturnOutPageVO.getDistrInReturnOutPageVO4Return(userVO,distrInReturn);

        List<DistrInReturnDetail> distrInReturnDetails = distrInReturnDetailMapper.selectByInReturnId(returnId);

        for(DistrInReturnDetail distrInReturnDetail : distrInReturnDetails){

            DistrInReturnOutDetailPageVO distrInReturnOutDetailPageVO4ReturnDetail = DistrInReturnOutDetailPageVO.getDistrInReturnOutDetailPageVO4ReturnDetail(distrInReturnDetail);
            distrInReturnOutPageVO4Return.getDistrInReturnOutDetailFormVOS().add(distrInReturnOutDetailPageVO4ReturnDetail);

            StockLockShelfVO stockLockShelfVO = new StockLockShelfVO();
            stockLockShelfVO.setGoodsId(distrInReturnDetail.getGoodsId());
            stockLockShelfVO.setBaseOrderId(returnId);
            stockLockShelfVO.setLotNum(distrInReturnDetail.getLotNumber());
            List<StockLockRecordVO> stockLockRecord = commonComponent.getStockLockRecord(stockLockShelfVO, userVO.getEnterpriseId());

            List<WarehouseShelfDefVO> warehouseShelfDefVOs4StockLok = WarehouseShelfDefVO.getWarehouseShelfDefVOs4StockLok(stockLockRecord);

            List<DistrInReturnOutShelfPageVO> distrInReturnOutShelfPageVOs4ReturnShelf = DistrInReturnOutShelfPageVO.getDistrInReturnOutShelfPageVOs4ReturnShelf(warehouseShelfDefVOs4StockLok);
            distrInReturnOutDetailPageVO4ReturnDetail.setDistrInReturnOutShelfFormVOS(distrInReturnOutShelfPageVOs4ReturnShelf);
        }



        return distrInReturnOutPageVO4Return;
    }


    @Override
    public void exportDetail(UserVO userVO, OutputStream output, Long orderId) {

        DistrInReturnOutPageVO distrInReturnOutsAndDetails = getDistrInReturnOutsAndDetails(userVO, orderId);

        DistrInReturnOutExcelVO distrInReturnOutPageVO = DistrInReturnOutExcelVO.getDistrInReturnOutPageVO(distrInReturnOutsAndDetails);

        List<DistrInReturnOutDetailPageVO> distrInReturnOutDetailFormVOS = distrInReturnOutsAndDetails.getDistrInReturnOutDetailFormVOS();

        List<DistrInReturnOutDetailExcelVO> distrReturnInDetailExcelVOSAll = new ArrayList<>();

        for(DistrInReturnOutDetailPageVO distrReturnInDetailPageVO : distrInReturnOutDetailFormVOS){

            List<DistrInReturnOutDetailExcelVO> distrInReturnOutDetailPageVO = DistrInReturnOutDetailExcelVO.getDistrInReturnOutDetailPageVO(distrReturnInDetailPageVO);
            distrReturnInDetailExcelVOSAll.addAll(distrInReturnOutDetailPageVO);
        }


        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        // map.put("goodsName", "商品名称");
        map.put("goodsGenericName", "商品通用名称");
        // map.put("dosageId", "剂型ID");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");

        map.put("manufacturer", "产地");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("quantity", "数量");
        map.put("notaxRealPrice", "单价");
        map.put("lineDiscount", "折扣");
        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxRealPrice", "不含税单价");
        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("shelfStatusDesc", "质量状况");
        map.put("remark", "备注");

        List<String> titleSecond = new ArrayList<>();
        // 标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化下日期
        titleSecondRow.append("  配货单位编码：       ");
        titleSecondRow.append(distrInReturnOutPageVO.getDistrUnitCode());
        titleSecondRow.append("  配货单位名称：       ");
        titleSecondRow.append(distrInReturnOutPageVO.getDistrUnitName());
        titleSecondRow.append("  配进退出单号：  ");
        titleSecondRow.append(distrInReturnOutPageVO.getCode());
        titleSecondRow.append("   退货日期：  ");
        titleSecondRow.append(format.format(distrInReturnOutPageVO.getOutDate()));
        titleSecondRow.append("   出库人员：  ");
        titleSecondRow.append(distrInReturnOutPageVO.getOutManName());
        titleSecondRow.append("   复核人员：：  ");
        titleSecondRow.append(distrInReturnOutPageVO.getAuditManName());
        titleSecondRow.append("  配货类型：");
        titleSecondRow.append(DistrType.getValue(distrInReturnOutPageVO.getDistrType()));
        titleSecondRow.append("   备注： ");
        titleSecondRow.append(distrInReturnOutPageVO.getRemark());

        titleSecond.add(titleSecondRow.toString());

        BigDecimal divide = distrInReturnOutPageVO.getWholeDiscount().divide(new BigDecimal(100));
        BigDecimal subtract = new BigDecimal(1).subtract(divide);
        BigDecimal multiply = distrInReturnOutPageVO.getAmountTotal().multiply(subtract);

        StringBuilder end = new StringBuilder();
        end.append("商品  ");
        end.append("总金额为：");
        end.append(distrInReturnOutPageVO.getAmountTotal());
        //每行以分号隔开
        end.append(";");
        end.append("折扣：");
        end.append(distrInReturnOutPageVO.getWholeDiscount());
        end.append("%");
        end.append("   " + multiply);
        end.append(";");
        end.append("优惠：");
        end.append(distrInReturnOutPageVO.getWholeDiscountAmount());
        end.append(";");
        end.append("总额：");
        end.append(distrInReturnOutPageVO.getRealAmountTotal());
        end.append(";");
        end.append("不含税总额：");
        end.append(distrInReturnOutPageVO.getNotaxRealAmountTotal());
        end.append(";");
        end.append("税额：");
        end.append(distrInReturnOutPageVO.getTaxAmountTotal());
        end.append(";");

        List<String> name = new ArrayList<>();
        name.add("配后退回入库单");


        StringBuilder endTotal = new StringBuilder();
        endTotal.append(distrInReturnOutPageVO.getQuantityTotal());
        endTotal.append(";");
        endTotal.append(distrInReturnOutPageVO.getAmountTotal());
        endTotal.append(";");
        endTotal.append(distrInReturnOutPageVO.getNotaxRealAmountTotal());
        endTotal.append(";");
        endTotal.append(distrInReturnOutPageVO.getTaxAmountTotal());
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelDistrExport(output, map, distrReturnInDetailExcelVOSAll, name, titleSecond, end.toString(), endTotal.toString(), locationList);


    }
}
