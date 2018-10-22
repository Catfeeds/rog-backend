package com.rograndec.feijiayun.chain.business.retail.royalty.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import com.rograndec.feijiayun.chain.business.retail.royalty.dao.SaleRoyaltyDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.royalty.dao.SaleRoyaltyMapper;
import com.rograndec.feijiayun.chain.business.retail.royalty.dao.SaleRoyaltyTotalMapper;
import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyalty;
import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyDetail;
import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyaltyTotal;
import com.rograndec.feijiayun.chain.business.retail.royalty.service.RoyaltyService;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.*;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.SaveOrUpdateDetailVO;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.SaveOrUpdateTotalVO;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.SaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.constant.RoyaltyFlag;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.ResponseSaleMan;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhaiwei on 2017/9/25.
 */
@Service
public class RoyaltyServiceImpl implements RoyaltyService {

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private SaleRoyaltyMapper saleRoyaltyMapper;

    @Autowired
    private SaleRoyaltyTotalMapper saleRoyaltyTotalMapper;

    @Autowired
    private SaleRoyaltyDetailMapper saleRoyaltyDetailMapper;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private CommissionStrategyMapper royaltyStrategyMapper;

    @Autowired
    private CommissionStrategyDetailMapper royaltyStrategyDetailMapper;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Override
    public ResponseSaleRoyaltyVO getHasSaleRoyalty(UserVO userVO, Long royaltyId){

        SaleRoyalty saleRoyalty = saleRoyaltyMapper.selectByPrimaryKey(royaltyId);

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(saleRoyalty.getEnterpriseId());

        ResponseSaleRoyaltyVO responseSaleRoyaltyVO = ResponseSaleRoyaltyVO.getResponseSaleRoyaltyVO(
                enterprise,
                saleRoyalty
        );

        List<SaleRoyaltyTotal> saleRoyaltyTotals = saleRoyaltyTotalMapper.selectByRoyaltyId(saleRoyalty.getId());
        List<ResponseSaleRoyaltyTotalVO> responseSaleRoyaltyTotalVOs = ResponseSaleRoyaltyTotalVO.getResponseSaleRoyaltyTotalVOs(saleRoyaltyTotals);

        responseSaleRoyaltyVO.setResponseSaleRoyaltyTotalVOS(responseSaleRoyaltyTotalVOs);

        /**
         *   List<Sale> sales
         , List<SaleDetail> saleDetails
         , List<SaleRoyaltyDetail> saleRoyaltyDetails
         */
        List<Long> ids = SaleRoyaltyTotal.getIds(saleRoyaltyTotals);
        List<SaleRoyaltyDetail> saleRoyaltyDetails = saleRoyaltyDetailMapper.selectByToatalIds(ids);
        List<Long> saleIds = SaleRoyaltyDetail.getSaleIds(saleRoyaltyDetails);
        List<Long> saleDetailIds = SaleRoyaltyDetail.getSaleDetailIds(saleRoyaltyDetails);
        List<Sale> sales = saleMapper.selectByIds(saleIds);
        List<SaleDetail> saleDetails = saleDetailMapper.selectByIds(saleDetailIds);

        List<ResponseSaleRoyaltyDetailVO> responseSaleRoyaltyDetailVOs = ResponseSaleRoyaltyDetailVO.getResponseSaleRoyaltyDetailVOs(
                sales
                , saleDetails
                , saleRoyaltyDetails
        );


        for(ResponseSaleRoyaltyTotalVO totalVO : responseSaleRoyaltyTotalVOs){
            for(ResponseSaleRoyaltyDetailVO detailVO : responseSaleRoyaltyDetailVOs){
                if(detailVO.getRoyaltyTotalId().equals(totalVO.getId())){
                 totalVO.getResponseSaleRoyaltyDetailVOList().add(detailVO);
                }
            }
        }

        ResponseSaleRoyaltyVO.setTotalAmount(
                responseSaleRoyaltyVO.getResponseSaleRoyaltyTotalVOS()
                ,responseSaleRoyaltyVO
        );
        return responseSaleRoyaltyVO;
    }

    public ResponseSaleTotalVO getHasSaleRoyaltys(UserVO userVO,Long id){
        return getHasSaleRoyaltys(userVO,null,null,null,id);
    }

    @Override
    public ResponseSaleTotalVO getHasSaleRoyaltys(UserVO userVO, Page page, Date startTime, Date endTime){
        return getHasSaleRoyaltys(userVO,page,startTime,endTime,null);
    }

    @Override
    public ResponseSaleTotalVO getHasSaleRoyaltys(UserVO userVO, Page page, Date startTime ,Date endTime,Long id){

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) map.put("parentId",userVO.getEnterpriseId());

        if(null != startTime && null != endTime){
            map.put("startTime",startTime);
            map.put("endTime",endTime);
        }
        if(null != id){
            map.put("id",id);
        }
        map.put("start", page.getStart());
        map.put("pageSize", page.getPageSize());
        List<ResponseSaleRoyaltyVO> saleRoyaltyVOS = new ArrayList<>();
        
        Integer totalNum = saleRoyaltyMapper.selectTotalRecordByEnterpriseId(map);

        List<SaleRoyalty> saleRoyaltys = saleRoyaltyMapper.queryByEnterpriseId(map);
        List<Long> enterpriseIds = SaleRoyalty.getEnterpriseIds(saleRoyaltys);
        List<Enterprise> enterprises = new ArrayList<>();
        if(!CollectionUtils.isEmpty(enterpriseIds)){
           enterprises = enterpriseMapper.selectByIds(enterpriseIds);
        }


        for(SaleRoyalty sy : saleRoyaltys){
            for(Enterprise enterprise : enterprises){
                if(sy.getEnterpriseId().equals(enterprise.getId())){
                    ResponseSaleRoyaltyVO responseSaleRoyaltyVO = ResponseSaleRoyaltyVO.getResponseSaleRoyaltyVO(
                            enterprise,
                            sy
                    );
                    saleRoyaltyVOS.add(responseSaleRoyaltyVO);
                }
            }

        }

        List<Long> ids = SaleRoyalty.getIds(saleRoyaltys);
        List<SaleRoyaltyTotal> saleRoyaltyTotals = new ArrayList<>();
        if(!CollectionUtils.isEmpty(ids)){
            saleRoyaltyTotals = saleRoyaltyTotalMapper.selectByRoyaltyIds(ids);
        }


        for(ResponseSaleRoyaltyVO saleRoyaltyVO : saleRoyaltyVOS){
            for(SaleRoyaltyTotal saleRoyaltyTotal : saleRoyaltyTotals){
                if(saleRoyaltyVO.getId().equals(saleRoyaltyTotal.getRoyaltyId())){
                    BigDecimal royaltyAmount = saleRoyaltyVO.getRoyaltyAmount().add(saleRoyaltyTotal.getRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    saleRoyaltyVO.setRoyaltyAmount(royaltyAmount);

                    BigDecimal realRoyaltyAmount = saleRoyaltyVO.getRealRoyaltyAmount().add(saleRoyaltyTotal.getRealRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    saleRoyaltyVO.setRealRoyaltyAmount(realRoyaltyAmount);

                    BigDecimal diffRoyaltyAmount = saleRoyaltyVO.getDiffRoyaltyAmount().add(saleRoyaltyTotal.getDiffRoyaltyAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
//                    BigDecimal diffRoyaltyAmount = royaltyAmount.subtract(realRoyaltyAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                    saleRoyaltyVO.setDiffRoyaltyAmount(diffRoyaltyAmount);
                }
            }
        }
        ResponseSaleTotalVO responseSaleTotalVO = new ResponseSaleTotalVO();
        if(!CollectionUtils.isEmpty(ids)){
            responseSaleTotalVO = saleRoyaltyTotalMapper.selectBySumAmount(ids);
            responseSaleTotalVO.setResponseSaleRoyaltyVOS(saleRoyaltyVOS);
        }



        if(null != page) {
            page.setResult(saleRoyaltyVOS);
            page.setTotalRecord(totalNum);
        }
        return responseSaleTotalVO;
    }

    /**
     * 导出
     *
     * @param output
     */
    @Override
    public void exportExcel(OutputStream output, UserVO userVO,Long id) {

        ResponseSaleRoyaltyVO responseSaleRoyaltyVO = getHasSaleRoyalty(userVO, id);


        List<String> headerList = new ArrayList<>();
        headerList.add(responseSaleRoyaltyVO.getEnterpriseName());
        headerList.add("提成报表");

        StringBuilder titleSecondRow = new StringBuilder();

        Date royaltyDate = responseSaleRoyaltyVO.getRoyaltyDate();
        String royaltyDateStr = DateUtils.DateToString(royaltyDate, DateUtils.FMT_DATE_TIME);

        Date saleDateFrom = responseSaleRoyaltyVO.getSaleDateFrom();
        Date saleDateTo = responseSaleRoyaltyVO.getSaleDateTo();
        String saleDateFromStr = DateUtils.DateToString(saleDateFrom, DateUtils.FMT_DATE_TIME);
        String saleDateToStr = DateUtils.DateToString(saleDateTo, DateUtils.FMT_DATE_TIME);

        titleSecondRow.append("提成日期：").append(Optional.ofNullable(royaltyDateStr).orElse(""))
                .append("  提成人员：").append(Optional.ofNullable(responseSaleRoyaltyVO.getRoyaltyManName()).orElse(""))
                .append("  销售日期（从）：").append(Optional.ofNullable(saleDateFromStr).orElse(""))
                .append("  销售日期（至）：").append(Optional.ofNullable(saleDateToStr).orElse(""));
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            titleSecondRow.append("  门店编码：").append(Optional.ofNullable(responseSaleRoyaltyVO.getEnterpriseCode()).orElse(""))
                    .append("  门店名称：").append(Optional.ofNullable(responseSaleRoyaltyVO.getEnterpriseName()).orElse(""));
        }

        List<String> titleSecondRowList = new ArrayList<>();
        titleSecondRowList.add(titleSecondRow.toString());


        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();

        rowHeaderMap.put("clerkName","营业人员");
        rowHeaderMap.put("saleAmount","销售金额");
        rowHeaderMap.put("costAmount","成本金额");
        rowHeaderMap.put("profit","利润金额");
        rowHeaderMap.put("royaltyAmount","应提金额");
        rowHeaderMap.put("realRoyaltyAmount","实提金额");
        rowHeaderMap.put("diffRoyaltyAmount","提成差额");


        purchaseGeneralComponent.commExcelExport(
                output
                ,rowHeaderMap
                ,responseSaleRoyaltyVO.getResponseSaleRoyaltyTotalVOS()
                ,headerList
                ,titleSecondRowList
                ,null
                ,false
                ,new ArrayList<>()
        );



    }

    @Override
    public List<ResponseSaleRoyaltyTotalVO> getHasSaleRoyaltyTotals(SaleRoyalty saleRoyalty){
        List<SaleRoyaltyTotal> saleRoyaltyTotals = saleRoyaltyTotalMapper.selectByRoyaltyId(saleRoyalty.getId());
        List<ResponseSaleRoyaltyTotalVO> responseSaleRoyaltyTotalVOs = ResponseSaleRoyaltyTotalVO.getResponseSaleRoyaltyTotalVOs(saleRoyaltyTotals);
        return responseSaleRoyaltyTotalVOs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoyaltys(
            List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOs
            , UserVO userVO

    ) throws Exception {
    	if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())) {// 门店
    		EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper
    				.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
    		if(enterpriseBusiness.getRoyaltyRule().equals(0)) throw new BusinessException("无操作权限");
		}

        Set<Long> royaltyManIds = SaveOrUpdateSaleRoyaltyVO.getRoyaltyManIds(saveOrUpdateSaleRoyaltyVOs);

        List<User> userByUsers = userComponent.findUserByUserIds(royaltyManIds);

        Set<Long> clerkIds = SaveOrUpdateSaleRoyaltyVO.getClerkIds(saveOrUpdateSaleRoyaltyVOs);

        List<Long> enterpriseIds = SaveOrUpdateSaleRoyaltyVO.getEnterpriseIds(saveOrUpdateSaleRoyaltyVOs);

        List<Enterprise> enterprises = enterpriseMapper.selectByIds(enterpriseIds);

        List<User> clerkUsers = userComponent.findUserByUserIds(clerkIds);

        List<Long> saleIds = SaveOrUpdateSaleRoyaltyVO.getSaleIds(saveOrUpdateSaleRoyaltyVOs);

        List<Sale> sales = saleMapper.selectByIds(saleIds);

//        List<SaveOrUpdateVO> saveOrUpdateVOS = new ArrayList<>();
        for (SaveOrUpdateSaleRoyaltyVO vo : saveOrUpdateSaleRoyaltyVOs){
            SaveOrUpdateVO saveOrUpdateVO = new SaveOrUpdateVO();
            saveOrUpdateVO.setUserVO(userVO);


            for(User user : userByUsers){
                if(vo.getRoyaltyManId().equals(user.getId())){
                    saveOrUpdateVO.setRoyaltyMan(user);
                }
            }

            if(CollectionUtils.isEmpty(enterprises)){
                throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"提成门店找不到");
            }

            //只能提成一个门店的
            Enterprise ent = enterprises.get(0);
            /**
             * 提成管理单号
             */
            String code = orderCodeComponent.generate(
                    OrderRule.COMMISSION.getCodePrefix()
                    , ent.getId()
                    , ent.getCode()
            );
            saveOrUpdateVO.setCode(code);
            saveOrUpdateVO.setEnterprise(ent);
          /*  for(Enterprise enterprise : enterprises){
                if(enterprise.getId().equals(vo.getEnterpriseId())){

                    saveOrUpdateVO.setCode(code);
                }
            }*/


            saveOrUpdateVO.setSaveOrUpdateSaleRoyaltyVO(vo);
//            saveOrUpdateVOS.add(saveOrUpdateVO);

            SaleRoyalty saleRoyalty = SaleRoyalty.getSaleRoyalty(saveOrUpdateVO, true);
            int i = saleRoyaltyMapper.insertSelective(saleRoyalty);

            /**
             * 保存total
             */
            saveRoyaltyTotals(
                    userVO
                    , vo.getSaveOrUpdateSaleRoyaltyTotalVOS()
                    , clerkUsers
                    , saleRoyalty
                    , sales
                    ,true
            );
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRoyaltyTotals(
            UserVO userVO
            , List<SaveOrUpdateSaleRoyaltyTotalVO> totailVOs
            , List<User> clerkUsers
            , SaleRoyalty saleRoyalty
            , List<Sale> sales
            , Boolean isAdd
    ) throws Exception {

        for(SaveOrUpdateSaleRoyaltyTotalVO totalVO : totailVOs){
            SaveOrUpdateTotalVO saveOrUpdateTotalVO = new SaveOrUpdateTotalVO();
            saveOrUpdateTotalVO.setUserVO(userVO);
            for(User user : clerkUsers) {
                if(user.getId().equals(totalVO.getClerkId())){
                    saveOrUpdateTotalVO.setClerk(user);
                }
            }
            saveOrUpdateTotalVO.setSaleRoyalty(saleRoyalty);
            saveOrUpdateTotalVO.setSaveOrUpdateSaleRoyaltyTotalVO(totalVO);
            SaleRoyaltyTotal saleRoyaltyTotal = SaleRoyaltyTotal.getSaleRoyaltyTotal(saveOrUpdateTotalVO, isAdd);
            int i = saleRoyaltyTotalMapper.insertSelective(saleRoyaltyTotal);

            saveRoyaltyDetails(
                    userVO
                    , saveOrUpdateTotalVO.getClerk()
                    , totalVO.getSaveOrUpdateSaleRoyaltyDetailVOS()
                    , saleRoyaltyTotal
                    , sales
                    , isAdd
            );
            List<Long> saleDtlIds = SaveOrUpdateSaleRoyaltyDetailVO.getSaleDtlIds(totalVO.getSaveOrUpdateSaleRoyaltyDetailVOS());
            for(Long id : saleDtlIds){
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setId(id);
                saleDetail.setRoyaltyFlag(RoyaltyFlag.ROYALTY.getCode());
                saleDetailMapper.updateByPrimaryKeySelective(saleDetail);
            }
        }



    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRoyaltyDetails(
            UserVO userVO
            , User clerk
            , List<SaveOrUpdateSaleRoyaltyDetailVO>  detailVOs
            , SaleRoyaltyTotal saleRoyaltyTotal
            , List<Sale> sales
            , Boolean isAdd
    ) throws Exception {
        for(SaveOrUpdateSaleRoyaltyDetailVO detailVO : detailVOs){

            SaveOrUpdateDetailVO saveOrUpdateDetailVO = new SaveOrUpdateDetailVO();
            saveOrUpdateDetailVO.setUserVO(userVO);
            saveOrUpdateDetailVO.setClerk(clerk);
            saveOrUpdateDetailVO.setSaleRoyaltyTotal(saleRoyaltyTotal);
            for(Sale sale : sales){
                if(sale.getId().equals(detailVO.getSaleId())){
                    saveOrUpdateDetailVO.setSale(sale);
                }
            }

            saveOrUpdateDetailVO.setSaveOrUpdateSaleRoyaltyDetailVO(detailVO);

            SaleRoyaltyDetail saleRoyaltyDetail = SaleRoyaltyDetail.getSaleRoyaltyDetail(saveOrUpdateDetailVO, isAdd);

            int i = saleRoyaltyDetailMapper.insertSelective(saleRoyaltyDetail);

        }

    }

    @Override
	public List<ResponseNoSaleRoyaltyTotalVO> getNoRoyaltys(Integer chainType, String code, String name, String clerkId,
			Date startTime, Date endTime, UserVO userVO) {

		Map<String, Object> map = new HashMap();
		
		if (null != chainType) {
			map.put("chainType", chainType);
		}
		if (!StringUtils.isEmpty(code)) {
			map.put("code", code);
		}

		if (!StringUtils.isEmpty(name)) {
			map.put("name", name);
		}

		if (!StringUtils.isEmpty(clerkId)) {
			map.put("clerkId", clerkId);
		}
		if (null != startTime) {
			map.put("startTime", startTime);
		}
		if (null != endTime) {
			map.put("endTime", endTime);
		}
		map.put("enterpriseId", userVO.getEnterpriseId());
		if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {// 总部
			map.put("parentId", userVO.getEnterpriseId());
		} else {// 门店
			EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper
					.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
			if (enterpriseBusiness.getRoyaltyRule().equals(1)) {// 自主控制
				map.put("royaltyRule", "royaltyRule");
			}
		}

        List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOS = saleRoyaltyMapper.selectNoRoyaltyDetailsByParam(map);
		if (!CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOS)) {
			List<Long> strategyIds = ResponseNoSaleRoyaltyDetailVO.getStrategyIds(responseNoSaleRoyaltyDetailVOS);

			List<CommissionStrategy> royaltyStrategies = new ArrayList<>();
			if (!CollectionUtils.isEmpty(strategyIds)) {
				royaltyStrategies = royaltyStrategyMapper.selectByIds(strategyIds, EnableStatus.ENABLE.getStatus());
			}

			List<Long> setIds = new ArrayList<>();

			for (CommissionStrategy ro : royaltyStrategies) {
				setIds.add(ro.getId());
			}
			List<CommissionStrategyDetail> royaltyStrategyDetails = new ArrayList<>();

			if (!CollectionUtils.isEmpty(setIds)) {
				royaltyStrategyDetails = royaltyStrategyDetailMapper.selectBySetIds(setIds,EnableStatus.ENABLE.getStatus());
			}

			ResponseNoSaleRoyaltyDetailVO.setRoyaltyAmount4Calc(royaltyStrategyDetails, royaltyStrategies,
					responseNoSaleRoyaltyDetailVOS);

			LinkedHashMap<Long, List<ResponseNoSaleRoyaltyDetailVO>> eMap = new LinkedHashMap<>();

			for (ResponseNoSaleRoyaltyDetailVO re : responseNoSaleRoyaltyDetailVOS) {

				List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOs = eMap.get(re.getEnterpriseId());
				if (CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOs)) {
					responseNoSaleRoyaltyDetailVOs = new ArrayList<>();
					responseNoSaleRoyaltyDetailVOs.add(re);
					eMap.put(re.getEnterpriseId(), responseNoSaleRoyaltyDetailVOs);
				} else {
					responseNoSaleRoyaltyDetailVOs.add(re);
				}
			}

			List<ResponseNoSaleRoyaltyTotalVO> saleRoyaltyTotalVOS = new ArrayList<>();
			for (Map.Entry<Long, List<ResponseNoSaleRoyaltyDetailVO>> entry : eMap.entrySet()) {

				LinkedHashMap<Long, List<ResponseNoSaleRoyaltyDetailVO>> rMap = new LinkedHashMap<>();

				List<ResponseNoSaleRoyaltyDetailVO> value = entry.getValue();
				for (ResponseNoSaleRoyaltyDetailVO v : value) {
					List<ResponseNoSaleRoyaltyDetailVO> responseNoSaleRoyaltyDetailVOs = rMap.get(v.getClerkId());
					if (CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOs)) {
						responseNoSaleRoyaltyDetailVOs = new ArrayList<>();
						responseNoSaleRoyaltyDetailVOs.add(v);
						rMap.put(v.getClerkId(), responseNoSaleRoyaltyDetailVOs);
					} else {
						responseNoSaleRoyaltyDetailVOs.add(v);
					}
				}

				for (Map.Entry<Long, List<ResponseNoSaleRoyaltyDetailVO>> e : rMap.entrySet()) {

					ResponseNoSaleRoyaltyTotalVO responseNoSaleRoyaltyTotalVO = ResponseNoSaleRoyaltyTotalVO
							.getResponseNoSaleRoyaltyTotalVO(e.getValue());

					saleRoyaltyTotalVOS.add(responseNoSaleRoyaltyTotalVO);
				}
				//

			}
			return saleRoyaltyTotalVOS;
		}

		return null;

	}

    @Override
    public List<ResponseSaleMan> getSaleMans(Map<String ,Object> map){
        List<SaleDetail> saleDetails = saleDetailMapper.selectById(map);
        List<ResponseSaleMan> responseSaleMans = ResponseSaleMan.getResponseSaleMan(saleDetails);
        return responseSaleMans;
    }
    
   /* private void bb() {
    	EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
    }*/
}
