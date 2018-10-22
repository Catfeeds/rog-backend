package com.rograndec.feijiayun.chain.business.goods.sale.service.impl;

import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionDetail;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CheckCodeExistsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionGoods;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import com.rograndec.feijiayun.chain.business.goods.sale.service.CommissionStrategyService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionStrategyDetailVO;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionStrategyInfoVO;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionStrategyVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.constant.CommissionMethod;
import com.rograndec.feijiayun.chain.common.constant.CommissionModeType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by madong on 2017/9/5.
 */
@Service
public class CommissionStrategyServiceImpl implements CommissionStrategyService {
    @Autowired
    private CommissionStrategyMapper strategyMapper;
    @Autowired
    private CommissionStrategyDetailMapper strategyDetailMapper;
    @Autowired
    private CommissionGoodsMapper commissionGoodsMapper;
    @Autowired
    private CheckCodeExistsMapper checkCodeExistsMapper;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Autowired
    private SaleCommissionDetailMapper saleCommissionDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRoyaltyStrategy(CommissionStrategyInfoVO strategyInfoVO, UserVO loginUser) throws Exception{
        CommissionStrategyVO strategyVO = strategyInfoVO.getCommissionStrategyVO();
        if(strategyVO.getMethod().equals(CommissionMethod.SALESQUANTITY.getCode())){
            strategyVO.setMode(CommissionModeType.QUANLITY.getCode());
        }else if(strategyVO.getMethod().equals(CommissionMethod.PROFITDRAWING.getCode())){
            strategyVO.setMode(CommissionModeType.PROFITREAT.getCode());
        }else {
            if(strategyVO.getMode() == null){
                throw new BusinessException("按销售金额/按利润金额提成时,提成方式必选!");
            }
        }
        if(strategyVO.getMethod().equals(CommissionMethod.TOTALPROFIT.getCode()) || strategyVO.getMethod().equals(CommissionMethod.PROFITDRAWING.getCode())){
            strategyVO.setRange(0);
        }else {
            if(strategyVO.getRange() == null){
                throw new BusinessException("按销售金额/按销售数量提成时,提成范围必选!");
            }
        }
        CommissionStrategy strategy = new CommissionStrategy();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(strategyVO,strategy);
        UserEnterpriseUtils.setUserCreateOrModify(strategy,loginUser,true);
        UserEnterpriseUtils.setUserCreateOrModify(strategy,loginUser,false);
        strategy.setEnterpriseId(loginUser.getEnterpriseId());
        strategy.setParentId(loginUser.getParentId());
        //------检查code是否重复
        if(!checkExistsCode(loginUser,strategy.getCode()).equals(0L)){
            throw new BusinessException("该提成编码重复,请重新输入");
        }
        if(!checkExistsName(loginUser,strategy.getName()).equals(0L)){
            throw new BusinessException("该提成名称重复,请重新输入");
        }
        //------检查code是否重复
        strategyMapper.insertSelective(strategy);
        List<CommissionStrategyDetail> strategyDetails = new ArrayList<>();
        //记录上级baseTo
        BigDecimal baseTo = BigDecimal.ZERO;
        for(int i = 0; i < strategyInfoVO.getCommissionStrategyDetailVOs().size(); i++){
            CommissionStrategyDetailVO strategyDetailVO = strategyInfoVO.getCommissionStrategyDetailVOs().get(i);
            CommissionStrategyDetail strategyDetail = new CommissionStrategyDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(strategyDetailVO,strategyDetail);
            if(i!= 0){
                if(baseTo.compareTo(strategyDetail.getBase()) != 0){
                    throw new BusinessException("提成基数必须连贯");
                }
            }
            if(Integer.valueOf(CommissionModeType.RATIO.getCode()).equals(strategy.getMode())){
                if(new BigDecimal(100).compareTo(strategyDetailVO.getRatio()) < 0){
                    throw new BusinessException("提成比例不能大于100！");
                }
            }
            if(BigDecimal.ZERO.compareTo(strategyDetailVO.getRatio()) == 0){
                throw new BusinessException("提成比例/金额必须大于0！");
            }
            if(i != strategyInfoVO.getCommissionStrategyDetailVOs().size()-1){
                if(strategyDetail.getBaseTo() == null){
                    throw new BusinessException("只有最后一行的【提成基数至】可以不填！");
                }
                if(strategyDetailVO.getBase().compareTo(strategyDetailVO.getBaseTo()) > -1){
                    throw new BusinessException("【提成基数至】必须大于【提成基数】");
                }
                baseTo = strategyDetail.getBaseTo();
            }else {
                if(strategyDetail.getBaseTo() != null){
                    throw new BusinessException("最后一行的【提成基数至】必须为空！");
                }
            }
            UserEnterpriseUtils.setUserCreateOrModify(strategyDetail,loginUser,true);
            UserEnterpriseUtils.setUserCreateOrModify(strategyDetail,loginUser,false);
            strategyDetail.setEnterpriseId(loginUser.getEnterpriseId());
            strategyDetail.setParentId(loginUser.getParentId());
            strategyDetail.setSetId(strategy.getId());
            strategyDetails.add(strategyDetail);
        }
        return strategyDetailMapper.batchInsert(strategyDetails);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRoyaltyStrategy(CommissionStrategyInfoVO strategyInfoVO, UserVO loginUser) throws Exception {
        Map<String,Long> param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("setId",strategyInfoVO.getCommissionStrategyVO().getId());
        //先删除再新增
        //因为是先删除的 应该查出是否有关联的商品
        Map<String,Long> paramGoods = new HashMap<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("strategyId",strategyInfoVO.getCommissionStrategyVO().getId());
        List<CommissionGoods> goods = commissionGoodsMapper.selectByStrategyIdByEnterpriseId(paramGoods);

        strategyDetailMapper.deleteByEnterpriseIdBySetId(param);
        strategyMapper.deleteByPrimaryKey(strategyInfoVO.getCommissionStrategyVO().getId());
        CommissionStrategy strategy = new CommissionStrategy();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(strategyInfoVO.getCommissionStrategyVO(),strategy);
        UserEnterpriseUtils.setUserCreateOrModify(strategy,loginUser,true);
        strategy.setId(null);
        strategy.setEnterpriseId(loginUser.getEnterpriseId());
        strategy.setParentId(loginUser.getParentId());
        if(!checkExistsName(loginUser,strategy.getName()).equals(0L)){
            throw new BusinessException("该提成名称重复,请重新输入");
        }
        strategyMapper.insertSelective(strategy);
        //修改关联商品的提成设置id
        for (CommissionGoods commissionGoods : goods) {
            commissionGoods.setStrategyId(strategy.getId());
            commissionGoodsMapper.updateByPrimaryKeySelective(commissionGoods);
        }
        List<CommissionStrategyDetail> strategyDetails = new ArrayList<>();
        //记录上级baseTo
        BigDecimal baseTo = BigDecimal.ZERO;
        for(int i = 0; i < strategyInfoVO.getCommissionStrategyDetailVOs().size(); i++){
            CommissionStrategyDetailVO strategyDetailVO = strategyInfoVO.getCommissionStrategyDetailVOs().get(i);
            if(i!= 0){
                if(baseTo.compareTo(strategyDetailVO.getBase()) != 0){
                    throw new BusinessException("提成基数必须连贯");
                }
            }
            if(Integer.valueOf(CommissionModeType.RATIO.getCode()).equals(strategy.getMode())){
                if(new BigDecimal(100).compareTo(strategyDetailVO.getRatio()) < 0){
                    throw new BusinessException("提成比例不能大于100！");
                }
            }
            if(BigDecimal.ZERO.compareTo(strategyDetailVO.getRatio()) >= 0){
                throw new BusinessException("提成比例/金额必须大于0！");
            }
            if(i != strategyInfoVO.getCommissionStrategyDetailVOs().size()-1){
                if(strategyDetailVO.getBaseTo() == null){
                    throw new BusinessException("只有最后一行的【提成基数至】可以不填！");
                }
                if(strategyDetailVO.getBase().compareTo(strategyDetailVO.getBaseTo()) > -1){
                    throw new BusinessException("【提成基数至】必须大于【提成基数】");
                }
                baseTo = strategyDetailVO.getBaseTo();
            }else {
                if(strategyDetailVO.getBaseTo() != null){
                    throw new BusinessException("最后一行的【提成基数至】必须为空！");
                }
            }
            CommissionStrategyDetail strategyDetail = new CommissionStrategyDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(strategyDetailVO,strategyDetail);
            strategyDetail.setModifierId(loginUser.getUserId());
            strategyDetail.setModifierCode(loginUser.getUserCode());
            strategyDetail.setModifierName(loginUser.getUserName());
            strategyDetail.setCreaterId(loginUser.getUserId());
            strategyDetail.setCreaterCode(loginUser.getUserCode());
            strategyDetail.setCreaterName(loginUser.getUserName());
            strategyDetail.setEnterpriseId(loginUser.getEnterpriseId());
            strategyDetail.setParentId(loginUser.getParentId());
            strategyDetail.setSetId(strategy.getId());
            strategyDetail.setId(null);
            strategyDetails.add(strategyDetail);
        }
        //再新增明细
        return strategyDetailMapper.batchInsert(strategyDetails);
    }

    @Override
    public int deleteRoyaltyStrategy(Long id, UserVO loginUser) throws Exception {
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("setId",id);
        strategyDetailMapper.deleteByEnterpriseIdBySetId(param);
        return strategyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CommissionStrategyInfoVO> getRoyaltyStrategy(String orderName, String orderType, UserVO loginUser) throws Exception {
        Map param = new HashMap();
        //xinhao
        //0-总部；1-自营店；2-加盟
        EnterpriseBusiness enterpriseBusiness=enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if(loginUser.getChainType().equals(0)){//总部
        	param.put("enterpriseId",loginUser.getEnterpriseId());
        }else{//自营店或者是加盟店
        		if(enterpriseBusiness.getRoyaltyRule().equals(1)){//自主控制
        			param.put("enterpriseId",loginUser.getEnterpriseId());
        		}else{
        			param.put("enterpriseId",loginUser.getParentId());
        		}
        }
        param.put("orderName",orderName);
        param.put("orderType",orderType);
        List<CommissionStrategyInfoVO> strategyInfoVOS = new ArrayList<>();
        List<CommissionStrategy> strategies = strategyMapper.selectByEnterpriseIdOrder(param);
        for (CommissionStrategy strategy : strategies){
            CommissionStrategyInfoVO strategyInfoVO = new CommissionStrategyInfoVO();//总单细单集合VO
            CommissionStrategyVO strategyVO = new CommissionStrategyVO();//总单VO
            List<CommissionStrategyDetailVO> strategyDetailVOS = new ArrayList<>();//细单VOlist
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(strategy,strategyVO);
            //公式描述
            StringBuilder formula = new StringBuilder();
            List<CommissionStrategyDetail> strategyDetails = strategyDetailMapper.selectBySetId(strategyVO.getId());
            for(CommissionStrategyDetail strategyDetail : strategyDetails){
                CommissionStrategyDetailVO strategyDetailVO = new CommissionStrategyDetailVO();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(strategyDetail,strategyDetailVO);
                if(strategy.getMethod().equals(CommissionMethod.SALESQUANTITY.getCode())){
                    formula.append(CommissionMethod.SALESQUANTITY.getName())
                            .append(">")
                            .append(strategyDetail.getBase());
                    if(strategyDetail.getBaseTo()!=null) {
                        formula.append("并且<=")
                                .append(strategyDetail.getBaseTo());
                        }
                            formula.append(",")
                            .append("提成金额=")
                            .append(strategyDetail.getRatio().setScale(2, RoundingMode.HALF_UP))
                            .append(";");
                }else if(strategy.getMethod().equals(CommissionMethod.SALEROOM.getCode())){
                    formula.append(CommissionMethod.SALEROOM.getName())
                            .append(">")
                            .append(strategyDetail.getBase());
                    if(strategyDetail.getBaseTo()!=null) {
                        formula.append("并且<=")
                                .append(strategyDetail.getBaseTo());
                        }
                            formula.append(",")
                            .append("提成金额=");
                    if(strategy.getMode().equals(CommissionModeType.AMOUNT.getCode())){
                        formula.append(strategyDetail.getRatio().setScale(2, RoundingMode.HALF_UP));
                    }else {
                        formula.append(CommissionMethod.SALEROOM.getName())
                                .append("*")
                                .append(strategyDetail.getRatio().setScale(2, RoundingMode.HALF_UP))
                                .append("%")
                                .append(";");
                    }
                }else if (strategy.getMethod().equals(CommissionMethod.TOTALPROFIT.getCode())){
                    formula.append(CommissionMethod.TOTALPROFIT.getName())
                            .append(">")
                            .append(strategyDetail.getBase());
                    if(strategyDetail.getBaseTo()!=null) {
                        formula.append("并且<=")
                                .append(strategyDetail.getBaseTo());
                    }
                    formula.append(",")
                            .append("提成金额=");
                    if(strategy.getMode().equals(CommissionModeType.AMOUNT.getCode())){
                        formula.append(strategyDetail.getRatio().setScale(2, RoundingMode.HALF_UP));
                    }else {
                        formula.append(CommissionMethod.TOTALPROFIT.getName())
                                .append("*")
                                .append(strategyDetail.getRatio().setScale(2, RoundingMode.HALF_UP))
                                .append("%");
                    }
                    formula.append(";");

                }else if(strategy.getMethod().equals(CommissionMethod.PROFITDRAWING.getCode())){
                    formula.append(CommissionMethod.PROFITDRAWING.getName())
                            .append(">")
                            .append(strategyDetail.getBase());
                    if(strategyDetail.getBaseTo()!=null) {
                        formula.append("并且<=")
                                .append(strategyDetail.getBaseTo());
                    }
                    formula.append(",")
                            .append("提成金额=")
                            .append(strategyDetail.getRatio().setScale(2, RoundingMode.HALF_UP))
                            .append(";");
                }

                strategyDetailVOS.add(strategyDetailVO);
            }
            strategyInfoVO.setCommissionStrategyDetailVOs(strategyDetailVOS);//添加细单VOList
            strategyVO.setFormula(formula.toString());
            boolean updateFlag = checkUpdate(strategyVO.getId(),loginUser);
            strategyVO.setUpdateFlag(updateFlag);
            strategyInfoVO.setCommissionStrategyVO(strategyVO);
            strategyInfoVOS.add(strategyInfoVO);
        }
        return strategyInfoVOS;
    }

    private boolean checkUpdate(Long id, UserVO loginUser)  throws Exception{
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("strategyId",id);
        List<CommissionGoods> goods = commissionGoodsMapper.selectByStrategyIdByEnterpriseId(param);
        //如果没有绑定商品 可以修改
        if(goods.isEmpty() || goods.size()==0){
            return true;
        }else {
            //如果绑定了商品 但是商品没有进行过提成销售 可以修改
            List<SaleCommissionDetail> saleCommissionDetails = saleCommissionDetailMapper.selectByCommissionStrategyId(id);
            //已经提成的商品列表
            List<Long> commissionedGoodsList = new ArrayList<>();
            for(SaleCommissionDetail commissionDetail : saleCommissionDetails){
                for(CommissionGoods commissionGoods : goods){
                    if(commissionDetail.getGoodsId().equals(commissionGoods.getGoodsId())){
                        commissionedGoodsList.add(commissionDetail.getGoodsId());
                    }
                }
            }
            if(commissionedGoodsList.isEmpty() || commissionedGoodsList.size() == 0){
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public boolean canDeleteRoyaltyStrategy(Long id, UserVO loginUser) throws Exception{
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("strategyId",id);
        List<CommissionGoods> goods = commissionGoodsMapper.selectByStrategyIdByEnterpriseId(param);
        return goods.isEmpty() || goods.size()==0;
    }

    @Override
    public Long checkExistsCode(UserVO loginUser, String code) throws Exception {
        List<String> codeList = new ArrayList<>();

        codeList.add(code);
        Map param = new HashMap();
        param.put("codeList",codeList);
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("tableName","saas_commission_strategy");
        return checkCodeExistsMapper.isExistsCode(param);
    }

    @Override
    public Long checkExistsName(UserVO loginUser, String name) throws Exception {
        List<String> nameList = new ArrayList<>();
        nameList.add(name);
        Map param = new HashMap();
        param.put("nameList",nameList);
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("tableName","saas_commission_strategy");
        return checkCodeExistsMapper.isExistsName(param);

    }
}
