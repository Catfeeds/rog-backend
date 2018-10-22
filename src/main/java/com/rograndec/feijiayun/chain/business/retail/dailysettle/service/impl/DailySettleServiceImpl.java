package com.rograndec.feijiayun.chain.business.retail.dailysettle.service.impl;

import com.rograndec.feijiayun.chain.business.retail.dailysettle.dao.DailySettleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.dao.DailySettleMapper;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.entity.DailySettle;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.entity.DailySettleDetail;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.service.DailySettleService;
import com.rograndec.feijiayun.chain.business.retail.dailysettle.vo.*;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShiftDetail;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by madong on 2017/9/19.
 */
@Service
public class DailySettleServiceImpl implements DailySettleService {

    private static Logger logger = LoggerFactory.getLogger(DailySettleServiceImpl.class);
    @Autowired
    DailySettleMapper dailySettleMapper;
    @Autowired
    DailySettleDetailMapper dailySettleDetailMapper;
    @Autowired
    SaleMapper saleMapper;
    @Autowired
    PayeeOpeningShiftMapper payeeOpeningShiftMapper;
    @Autowired
    PayeeOpeningShiftDetailMapper payeeOpeningShiftDetailMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page<List<DailySettleStoreVO>> getDailySettle(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception {
        Page<List<DailySettleStoreVO>> page = new Page(pageNo,pageSize);
        Map param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("start",(pageNo-1)*pageSize);
        param.put("pageSize",pageSize);
        if(orderName == null || "".equals(orderName)){
            param.put("orderName",null);
        }else
            param.put("orderName","settle_date");
        param.put("orderType",orderType);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<DailySettleStoreVO> dailySettleVOS = dailySettleMapper.getDailySettle(param);
        page.setResult(dailySettleVOS);
        param.replace("start",null);
        param.replace("pageSize",null);
        dailySettleVOS = dailySettleMapper.getDailySettle(param);
        page.setTotalRecord(dailySettleVOS.size());
        return page;
    }

    @Override
    public Page<List<DailySettleStoreVO>> getDailySettleStore(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception {
        Page<List<DailySettleStoreVO>> page = new Page(pageNo,pageSize);
        Map param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("start",(pageNo-1)*pageSize);
        param.put("pageSize",pageSize);
        if(orderName == null || "".equals(orderName)){
            param.put("orderName",null);
        }else
            param.put("orderName","settle_date");
        param.put("orderType",orderType);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<DailySettleStoreVO> dailySettleStoreVOS = dailySettleMapper.getDailySettleStore(param);
        page.setResult(dailySettleStoreVOS);
        param.replace("start",null);
        param.replace("pageSize",null);
        dailySettleStoreVOS = dailySettleMapper.getDailySettleStore(param);;
        page.setTotalRecord(dailySettleStoreVOS.size());
        return page;
    }

    @Override
    public Page<List<WillDailySettleVO>> getWillDailySettle(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception {
        Page<List<WillDailySettleVO>> page = new Page(pageNo,pageSize);
        Map param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("start",(pageNo-1)*pageSize);
        param.put("pageSize",pageSize);
        if(orderName == null || "".equals(orderName)){
            param.put("orderName",null);
        }else
            param.put("orderName","shift_time");
        param.put("orderType",orderType);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<WillDailySettleVO> willDailySettleVOS = dailySettleMapper.getWillDailySettle(param);
        page.setResult(willDailySettleVOS);
        param.replace("start",null);
        param.replace("pageSize",null);
        willDailySettleVOS = dailySettleMapper.getWillDailySettle(param);
        page.setTotalRecord(willDailySettleVOS.size());
        return page;
    }

    @Override
    public Page<List<WillDailySettleVO>> getWillDailySettleStore(UserVO loginUser, Integer pageNo, Integer pageSize, String orderName, String orderType, String startTime, String endTime) throws Exception {
        Page<List<WillDailySettleVO>> page = new Page(pageNo,pageSize);
        Map param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("start",(pageNo-1)*pageSize);
        param.put("pageSize",pageSize);
        if(orderName == null || "".equals(orderName)){
            param.put("orderName",null);
        }else
            param.put("orderName","shift_time");
        param.put("orderType",orderType);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<WillDailySettleVO> willDailySettleVOS = dailySettleMapper.getWillDailySettleStore(param);
        logger.info("待日结数据willDailySettleVOS = [" + willDailySettleVOS + "]");
        page.setResult(willDailySettleVOS);
        param.replace("start",null);
        param.replace("pageSize",null);
        willDailySettleVOS = dailySettleMapper.getWillDailySettleStore(param);
        page.setTotalRecord(willDailySettleVOS.size());
        return page;
    }

    @Override
    public List<SaveShowDailyInfo> showDailySettle(UserVO loginUser, String ids, Long settleType, Long enterpriseId, String orderName,String orderType) throws Exception {
        List<String> shiftDetailIds = Arrays.asList(ids.split(","));
        Map param = new HashMap();
        param.put("enterpriseId",enterpriseId);
        param.put("shiftDetailIds",shiftDetailIds);
        if(orderName == null || "".equals(orderName)){
            param.put("orderName",null);
        }else
            param.put("orderName","date");
        param.put("orderType",orderType);
        List<SaveShowDailyInfo> saveShowDailyInfos = new ArrayList<>();//查看列表
        List<WillDailySettleDetailVO> willDailySettleDetailVOS = new ArrayList<>();
        if(settleType==0l)
            willDailySettleDetailVOS = dailySettleMapper.selectwillDailySettleDetail(param);
        else if(settleType==1l)
            willDailySettleDetailVOS = dailySettleMapper.selectDailySettleDetail(param);
        for (WillDailySettleDetailVO willDailySettleDetailVO : willDailySettleDetailVOS){
            SaveShowDailyInfo saveShowDailyInfo = new SaveShowDailyInfo();
            List<Long> saleIds = new ArrayList<>();
            for(String id : willDailySettleDetailVO.getSaleIds().split(",")){
                saleIds.add(Long.valueOf(id.trim()));
            }
            List<Sale> sales = saleMapper.selectByIds(saleIds);
            List<DailySettleSaleVO> dailySettleSaleVOS = new ArrayList<>();
            //为了保存方便,这里将日结细单查出来,将日结细单id放入细单对象中,便于保存
            List<Long> payeeOpeningShiftDetailIds = new ArrayList<>();
            for (String id :willDailySettleDetailVO.getShiftDetailIds().split(",")){
                payeeOpeningShiftDetailIds.add(Long.valueOf(id.trim()));
            }
            //日结细单信息
            List<PayeeOpeningShiftDetail> payeeOpeningShiftDetails = payeeOpeningShiftDetailMapper.selectByIds(payeeOpeningShiftDetailIds);
            for (Sale sale : sales){
                DailySettleSaleVO dailySettleSaleVO = new DailySettleSaleVO();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(sale,dailySettleSaleVO);
                for (PayeeOpeningShiftDetail payeeOpeningShiftDetail : payeeOpeningShiftDetails){
                    if(dailySettleSaleVO.getId().equals(payeeOpeningShiftDetail.getBaseOrderId()))
                        dailySettleSaleVO.setShiftDetailId(payeeOpeningShiftDetail.getId());
                }
                if(dailySettleSaleVO.getSaleMode() != null )
                    dailySettleSaleVO.setSaleModeName(dailySettleSaleVO.getSaleMode()==0?"常规":"中药");
                if(dailySettleSaleVO.getSaleType() != null)
                    dailySettleSaleVO.setSaleTypeName(dailySettleSaleVO.getSaleType()==0?"销售":"销退");
                dailySettleSaleVOS.add(dailySettleSaleVO);
            }
            saveShowDailyInfo.setDailySettleSaleVOS(dailySettleSaleVOS);
            saveShowDailyInfo.setWillDailySettleDetailVO(willDailySettleDetailVO);
            saveShowDailyInfos.add(saveShowDailyInfo);
        }
        return saveShowDailyInfos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveDailySettle(UserVO loginUser, List<SaveShowDailyInfo> saveShowDailyInfos) throws Exception {
        for (SaveShowDailyInfo saveShowDailyInfo : saveShowDailyInfos){
            DailySettle dailySettle = new DailySettle();
            //set日结企业信息
            dailySettle.setEnterpriseId(saveShowDailyInfo.getWillDailySettleDetailVO().getEnterpriseId());
            if(loginUser.getEnterpriseCode().equals(dailySettle.getEnterpriseId()))
                dailySettle.setParentId(loginUser.getParentId());
            else dailySettle.setParentId(loginUser.getEnterpriseId());
            //set日结信息(单号,类型)
            dailySettle.setCode(orderCodeComponent.generate(OrderRule.RETAIL_DAILY_SETTLE.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
            dailySettle.setOrderType(OrderRule.RETAIL_DAILY_SETTLE.getType());
            //set基础信息(交班单信息)
            dailySettle.setBaseOrderId(saveShowDailyInfo.getWillDailySettleDetailVO().getId());
            PayeeOpeningShift payeeOpeningShift = payeeOpeningShiftMapper.selectByPrimaryKey(dailySettle.getBaseOrderId());
            //set日结人员信息
            dailySettle.setSettleManId(loginUser.getUserId());
            dailySettle.setSettleManCode(loginUser.getUserCode());
            dailySettle.setSettleManName(loginUser.getUserName());
            //set金额合计/不含税金额/税额合计 需从后台重新查询
            //根据id获取零售流水,重新计算金额
            List<Long> saleIds = new ArrayList<>();
            for(String id : saveShowDailyInfo.getWillDailySettleDetailVO().getSaleIds().split(",")){
                saleIds.add(Long.valueOf(id));
            }
            BigDecimal amountTotal = BigDecimal.ZERO;
            BigDecimal notaxAmountTotal = BigDecimal.ZERO;
            BigDecimal taxAmountTotal = BigDecimal.ZERO;
            //根据零售流水id集合获取对应的流水集合
            List<Sale> sales = saleMapper.selectByIds(saleIds);
            for(Sale sale : sales){
                Integer saleType = sale.getSaleType();
                if(saleType == 0){
                    //销售
                    amountTotal = amountTotal.add(sale.getAmountTotal());
                    notaxAmountTotal = notaxAmountTotal.add(sale.getNotaxRealAmountTotal());
                    taxAmountTotal = taxAmountTotal.add(sale.getTaxAmountTotal());
                } else {
                    amountTotal = amountTotal.subtract(sale.getAmountTotal());
                    notaxAmountTotal = notaxAmountTotal.subtract(sale.getNotaxRealAmountTotal());
                    taxAmountTotal = taxAmountTotal.subtract(sale.getTaxAmountTotal());
                }

            }
            dailySettle.setAmountTotal(amountTotal);
            dailySettle.setNotaxAmountTotal(notaxAmountTotal);
            dailySettle.setTaxAmountTotal(taxAmountTotal);
            //set状态 0:禁用 1:启用 默认启用
            dailySettle.setStatus(1);
            //set创建人/修改人信息
            UserEnterpriseUtils.setUserCreateOrModify(dailySettle,loginUser,true);
            //保存日结信息
            dailySettleMapper.insertSelective(dailySettle);
            //保存零售日结细单信息
            List<DailySettleDetail> dailySettleDetails = new ArrayList<>();
            for(DailySettleSaleVO dailySettleSaleVO : saveShowDailyInfo.getDailySettleSaleVOS()){
                DailySettleDetail dailySettleDetail = new DailySettleDetail();
                //set总单id
                dailySettleDetail.setSettleId(dailySettle.getId());
                //set企业信息
                dailySettleDetail.setEnterpriseId(loginUser.getEnterpriseId());
                dailySettleDetail.setParentId(loginUser.getParentId());
                //set基础单据信息,根据id查询交接班单据
                dailySettleDetail.setBaseOrderDtlId(dailySettleSaleVO.getShiftDetailId());
                dailySettleDetail.setBaseOrderId(dailySettle.getBaseOrderId());
                //set状态默认为1
                dailySettleDetail.setStatus(1);
                //set创建人/修改人信息
                UserEnterpriseUtils.setUserCreateOrModify(dailySettleDetail,loginUser,true);
                dailySettleDetails.add(dailySettleDetail);
                //更新零售流水表里的日结状态为已日结,直接用上方查询的流水集合,这里不再重新查询
                Sale sale = new Sale();
                for(Sale s : sales){
                    if(s.getId().equals(dailySettleSaleVO.getId()))
                        sale = s;
                }
                sale.setDailySettlementFlag(1);
                sale.setDailyTime(new Date());
                saleMapper.updateByPrimaryKeySelective(sale);
                //set金额/不含税金额/税额 从流水中获取
                dailySettleDetail.setAmount(sale.getAmountTotal());
                dailySettleDetail.setNotaxAmount(sale.getNotaxRealAmountTotal());
                dailySettleDetail.setTaxAmount(sale.getNotaxRealAmountTotal());
            }
            dailySettleDetailMapper.batchInsert(dailySettleDetails);
            //更新交接班表的日结状态为已日结
            payeeOpeningShift .setDailySettleFlag(1);
            payeeOpeningShiftMapper.updateByPrimaryKeySelective(payeeOpeningShift);

        }
        return 0;
    }

    @Override
    public void export(OutputStream output, UserVO loginUser, String ids) {
        //转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        if(loginUser.getParentId()!=0l){
            map.put("enterpriseCode","门店编码");
            map.put("enterpriseName","门店名称");
        }
        map.put("shiftTime","交班时间");
        map.put("standCodes","款台");
        map.put("posTeamName","班组");
        map.put("payeeName","收款人员");
        map.put("openingTime","开班时间");
        map.put("shiftTime","交班时间");
        map.put("amountTotal","应收金额");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        titleSecond.add("销售日结");
        StringBuilder end = new StringBuilder();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        List<String> needTotalName = new ArrayList<>();
        List<String> shiftDetailIds = Arrays.asList(ids.split(","));
        Map param = new HashMap();
        param.put("shiftDetailIds",shiftDetailIds);
        param.put("orderName",null);
        param.put("orderType",null);
        List<WillDailySettleDetailExportVO> willDailySettleDetailExportVOS = new ArrayList<>();
        List<WillDailySettleDetailVO> willDailySettleDetailVOS = dailySettleMapper.selectDailySettleDetail(param);
        for(WillDailySettleDetailVO willDailySettleDetailVO : willDailySettleDetailVOS){
            WillDailySettleDetailExportVO willDailySettleDetailExportVO = new WillDailySettleDetailExportVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(willDailySettleDetailVO,willDailySettleDetailExportVO);
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(willDailySettleDetailVO.getEnterpriseId());
            willDailySettleDetailExportVO.setEnterpriseCode(enterprise.getCode());
            willDailySettleDetailExportVO.setEnterpriseName(enterprise.getName());
            willDailySettleDetailExportVOS.add(willDailySettleDetailExportVO);
        }
        purchaseGeneralComponent.commExcelExport(output,map,willDailySettleDetailExportVOS,name,titleSecond,end.toString(),true,needTotalName);
    }
}
