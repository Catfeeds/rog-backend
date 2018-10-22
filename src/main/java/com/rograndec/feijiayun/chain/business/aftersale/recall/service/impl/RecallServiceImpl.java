package com.rograndec.feijiayun.chain.business.aftersale.recall.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.HandleMeasuresEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.RecallDepositEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.RecallLevelEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.RecallReasonEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.dao.RecallPlanDetailMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recall.dao.RecallPlanMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recall.dao.RecallRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recall.dao.RecallRecordMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallPlan;
import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallPlanDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallRecord;
import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallRecordDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recall.service.RecallService;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.*;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class RecallServiceImpl implements RecallService{

    @Autowired
    RecallPlanMapper recallPlanMapper;
    @Autowired
    RecallPlanDetailMapper recallPlanDetailMapper;
    @Autowired
    RecallRecordMapper recallRecordMapper;
    @Autowired
    RecallRecordDetailMapper recallRecordDetailMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    LotNumberMapper lotNumberMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page<List<RecallPlanVO>> getRecallPlanList(UserVO loginUser, RecallPlanPageParamVO recallPlanPageParamVO) throws Exception {
        Page<List<RecallPlanVO>> page = new Page<>(recallPlanPageParamVO.getPageNo(), recallPlanPageParamVO.getPageSize());
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<RecallPlanVO> recallPlanVOS = recallPlanMapper.selectByEnterpriseId(loginUser.getEnterpriseId(),recallPlanPageParamVO);
        for(RecallPlanVO recallPlanVO : recallPlanVOS){
            if(recallPlanVO.getRecallDeposit() != null)
                recallPlanVO.setRecallDepositName(RecallDepositEnum.getName(recallPlanVO.getRecallDeposit()));
            if(recallPlanVO.getRecallLevel() != null)
                recallPlanVO.setRecallLevelName(RecallLevelEnum.getName(recallPlanVO.getRecallLevel()));
            if(recallPlanVO.getRecallReason() != null)
                recallPlanVO.setRecallReasonName(RecallReasonEnum.getName(recallPlanVO.getRecallReason()));
            recallPlanVO.setModifyFlag(isDeleteRecallPlan(loginUser,recallPlanVO.getId()));
        }
        page.setResult(recallPlanVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        page.setStart((page.getPageNo()-1)*page.getPageSize());
        return page;
    }

    @Override
    public Page<List<RecallRecordVO>> getRecallRecordList(UserVO loginUser, RecallRecordPageParamVO recallRecordPageParamVO) throws Exception {
        Page<List<RecallRecordVO>> page = new Page<>(recallRecordPageParamVO.getPageNo(), recallRecordPageParamVO.getPageSize());
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<RecallRecordVO> recallRecordVOS = recallRecordMapper.selectByEnterpriseId(loginUser.getEnterpriseId(),recallRecordPageParamVO);
        for(RecallRecordVO recallRecordVO : recallRecordVOS){
            if(recallRecordVO.getHandleMeasures() != null)
                recallRecordVO.setHandleMeasuresName(HandleMeasuresEnum.getName(recallRecordVO.getHandleMeasures()));
        }
        page.setResult(recallRecordVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        page.setStart((page.getPageNo()-1)*page.getPageSize());
        return page;
    }

    @Override
    public boolean isDeleteRecallPlan(UserVO loginUser, Long id) throws Exception {
        List<RecallRecord> recallRecords = recallRecordMapper.selectByRecallPlanId(id,loginUser.getEnterpriseId());
        return recallRecords.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRecallPlan(UserVO loginUser, Long id) throws Exception {
        recallPlanDetailMapper.deleteByPlanId(id,loginUser.getEnterpriseId());
        return recallPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRecallRecord(UserVO loginUser, Long id) throws Exception {
        recallRecordDetailMapper.deleteByRecordId(id,loginUser.getEnterpriseId());
        return recallRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RecallPlanVO showRecallPlanDetail(UserVO loginUser, Long id) throws Exception {
        RecallPlan recallPlan = recallPlanMapper.selectByPrimaryKey(id);
        RecallPlanVO recallPlanVO = new RecallPlanVO();
        if(recallPlan.getRecallDeposit() != null)
            recallPlanVO.setRecallDepositName(RecallDepositEnum.getName(recallPlan.getRecallDeposit()));
        if(recallPlan.getRecallLevel() != null)
            recallPlanVO.setRecallLevelName(RecallLevelEnum.getName(recallPlan.getRecallLevel()));
        if(recallPlan.getRecallReason() != null)
            recallPlanVO.setRecallReasonName(RecallReasonEnum.getName(recallPlan.getRecallReason()));
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallPlan,recallPlanVO);
        List<RecallPlanDetailVO> recallPlanDetailVOS = recallPlanDetailMapper.selectByPlanId(id,loginUser.getEnterpriseId());
        recallPlanVO.setRecallPlanDetailVOS(recallPlanDetailVOS);
        return recallPlanVO;
    }

    @Override
    public RecallRecordVO showRecallRecordDetail(UserVO loginUser, Long id) throws Exception {
        RecallRecord recallRecord = recallRecordMapper.selectByPrimaryKey(id);
        RecallRecordVO recallRecordVO = new RecallRecordVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallRecord,recallRecordVO);
        if(recallRecord.getHandleMeasures() != null)
            recallRecordVO.setHandleMeasuresName(HandleMeasuresEnum.getName(recallRecord.getHandleMeasures()));
        List<RecallRecordDetailVO> detailVOS = recallRecordDetailMapper.selectByRecallId(id,loginUser.getEnterpriseId());
        recallRecordVO.setRecallRecordDetailVOList(detailVOS);
        return recallRecordVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRecallPlan(UserVO loginUser, RecallPlanVO recallPlanVO) throws Exception {
        Set<Long> goodsSet = getPlanGoodsSet(recallPlanVO);
        //保存总单
        RecallPlan recallPlan = genPlanDoc(loginUser, recallPlanVO, goodsSet);
        recallPlanMapper.insertSelective(recallPlan);
        //保存细单
        List<RecallPlanDetail> recallPlanDetails = new ArrayList<>();
        for(RecallPlanDetailVO recallPlanDetailVO : recallPlanVO.getRecallPlanDetailVOS()){
            RecallPlanDetail recallPlanDetail = genPlanDtlInfo(loginUser, recallPlan, recallPlanDetailVO);
            recallPlanDetails.add(recallPlanDetail);
        }
        recallPlanDetailMapper.batchInsert(recallPlanDetails);
        return 0;
    }

    private RecallPlanDetail genPlanDtlInfo(UserVO loginUser, RecallPlan recallPlan, RecallPlanDetailVO recallPlanDetailVO) throws Exception {
        RecallPlanDetail recallPlanDetail = new RecallPlanDetail();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallPlanDetailVO,recallPlanDetail);
        //set 企业信息
        recallPlanDetail.setEnterpriseId(loginUser.getEnterpriseId());
        recallPlanDetail.setParentId(loginUser.getParentId());
        //set 创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(recallPlanDetail,loginUser,true);
        //set 总单信息
        recallPlanDetail.setPlanId(recallPlan.getId());
        recallPlanDetail.setOrderType(recallPlan.getOrderType());
        recallPlanDetail.setPlanCode(recallPlan.getCode());
        recallPlanDetail.setPlanDate(recallPlan.getPlanDate());
        //重新查询商品信息并set
        Goods goods = goodsMapper.selectByPrimaryKey(recallPlanDetailVO.getGoodsId());
        recallPlanDetail.setGoodsName(goods.getName());
        recallPlanDetail.setGoodsCode(goods.getCode());
        recallPlanDetail.setBarcode(goods.getBarcode());
        recallPlanDetail.setGoodsGenericName(goods.getGenericName());
        recallPlanDetail.setDosageId(goods.getDosageId());
        recallPlanDetail.setDosageName(goods.getDosageName());
        recallPlanDetail.setUnitId(goods.getUnitId());
        recallPlanDetail.setUnitName(goods.getUnitName());
        recallPlanDetail.setGoodsSpecification(goods.getSpecification());
        recallPlanDetail.setManufacturerId(goods.getManufacturerId());
        recallPlanDetail.setManufacturer(goods.getManufacturer());
        recallPlanDetail.setGoodsPlace(goods.getPlace());
        recallPlanDetail.setApprovalNumber(goods.getApprovalNumber());
        //set 状态 默认为1
        recallPlanDetail.setStatus(1);
        //重新查询批号信息并set
        LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(recallPlanDetailVO.getLotId());
        recallPlanDetail.setLotNumber(lotNumber.getLotNum());
        recallPlanDetail.setProductDate(lotNumber.getProductDate());
        recallPlanDetail.setValidDate(lotNumber.getValidUntil());
        return recallPlanDetail;
    }

    private RecallPlan genPlanDoc(UserVO loginUser, RecallPlanVO recallPlanVO, Set<Long> goodsSet) throws Exception {
        RecallPlan recallPlan = new RecallPlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallPlanVO,recallPlan);
        //set 召回责任人默认值（产品确认可以为空）
        if (null == recallPlanVO.getRecallMan()) recallPlan.setRecallMan("");
        if (null == recallPlanVO.getRecallManPhone()) recallPlan.setRecallManPhone("");
        //set 企业信息
        recallPlan.setEnterpriseId(loginUser.getEnterpriseId());
        recallPlan.setParentId(loginUser.getParentId());
        //set 创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(recallPlan,loginUser,true);
        //set 品种数量
        recallPlan.setVarietiesQuantity(goodsSet.size());
        //set 单据类型
        recallPlan.setOrderType(OrderRule.RECALL_PLAN.getType());
        //set 编号
        recallPlan.setCode(orderCodeComponent.generate(OrderRule.RECALL_PLAN.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        //set 状态 默认为1
        recallPlan.setStatus(1);
        //重新查通知人信息并set
        User user = userMapper.selectByPrimaryKey(recallPlan.getPlanManId());
        recallPlan.setPlanManCode(user.getCode());
        recallPlan.setPlanManName(user.getName());
        return recallPlan;
    }

    private Set<Long> getPlanGoodsSet(RecallPlanVO recallPlanVO) throws Exception{
        Set<Long> goodsSet = new HashSet<>();
        for(RecallPlanDetailVO recallPlanDetailVO : recallPlanVO.getRecallPlanDetailVOS()){
            if(recallPlanDetailVO.getGoodsId() == null) throw new RuntimeException("货品ID不能为空");
            if(recallPlanDetailVO.getLotId() == null) throw new RuntimeException("批号ID不能为空");
            goodsSet.add(recallPlanDetailVO.getGoodsId());
        }
        return goodsSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRecallPlan(UserVO loginUser, RecallPlanVO recallPlanVO) throws Exception {
        Set<Long> goodsSet = getPlanGoodsSet(recallPlanVO);
        RecallPlan recallPlan = genPlanDoc(loginUser, recallPlanVO, goodsSet);
        UserEnterpriseUtils.setUserCreateOrModify(recallPlan,loginUser,false);
        recallPlanMapper.updateByPrimaryKeySelective(recallPlan);
        //细单先删除,在新增
        recallPlanDetailMapper.deleteByPlanId(recallPlan.getId(),loginUser.getEnterpriseId());
        //保存细单
        List<RecallPlanDetail> recallPlanDetails = new ArrayList<>();
        for(RecallPlanDetailVO recallPlanDetailVO : recallPlanVO.getRecallPlanDetailVOS()){
            RecallPlanDetail recallPlanDetail = genPlanDtlInfo(loginUser, recallPlan, recallPlanDetailVO);
            recallPlanDetails.add(recallPlanDetail);
        }
        recallPlanDetailMapper.batchInsert(recallPlanDetails);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRecallRecord(UserVO loginUser, RecallRecordVO recallRecordVO) throws Exception {
        Set<Long> goodsSet = getRecordGoodsSet(recallRecordVO);
        //保存总单
        RecallRecord recallRecord = genRecordDoc(loginUser, recallRecordVO, goodsSet);
        recallRecordMapper.insertSelective(recallRecord);
        //保存细单
        List<RecallRecordDetail> recallRecordDetails = new ArrayList<>();
        for(RecallRecordDetailVO recallRecordDetailVO : recallRecordVO.getRecallRecordDetailVOList()){
            RecallRecordDetail recallRecordDetail = genRecordDtlInfo(loginUser, recallRecord, recallRecordDetailVO);
            recallRecordDetails.add(recallRecordDetail);
        }
        recallRecordDetailMapper.batchInsert(recallRecordDetails);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRecallRecord(UserVO loginUser, RecallRecordVO recallRecordVO) throws Exception {
        Set<Long> goodsSet = getRecordGoodsSet(recallRecordVO);
        RecallRecord recallRecord = genRecordDoc(loginUser, recallRecordVO, goodsSet);
        UserEnterpriseUtils.setUserCreateOrModify(recallRecord,loginUser,false);
        recallRecordMapper.updateByPrimaryKeySelective(recallRecord);
        //细单先删除,在新增
        recallRecordDetailMapper.deleteByRecordId(recallRecordVO.getId(),loginUser.getEnterpriseId());
        //保存细单
        List<RecallRecordDetail> recallRecordDetails = new ArrayList<>();
        for(RecallRecordDetailVO recallRecordDetailVO : recallRecordVO.getRecallRecordDetailVOList()){
            RecallRecordDetail recallRecordDetail = genRecordDtlInfo(loginUser, recallRecord, recallRecordDetailVO);
            recallRecordDetails.add(recallRecordDetail);
        }
        recallRecordDetailMapper.batchInsert(recallRecordDetails);
        return 0;
    }

    @Override
    public List<AfterSaleChooseGoodsVO> getRecordGoodsList(Long id, UserVO loginUser, String param) throws Exception {
        List<RecallPlanDetailVO> recallPlanDetailVOS = recallPlanDetailMapper.selectByPlanIdByParam(id,loginUser.getEnterpriseId(),param);
        if(recallPlanDetailVOS.isEmpty()) return new ArrayList<>();
        List<AfterSaleChooseGoodsVO> saleChooseGoodsVOList = new ArrayList<>();
        for(RecallPlanDetailVO recallPlanDetailVO : recallPlanDetailVOS){
            AfterSaleChooseGoodsVO saleChooseGoodsVO = new AfterSaleChooseGoodsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallPlanDetailVO, saleChooseGoodsVO);
            saleChooseGoodsVO.setBaseOrderDtlId(recallPlanDetailVO.getId());
            saleChooseGoodsVOList.add(saleChooseGoodsVO);
        }
        return saleChooseGoodsVOList;
    }

    @Override
    public void exportExcel(OutputStream output, Long id, UserVO loginUser, Integer type) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        if(type == 1) map.put("quantity","退回数量");
        //标题栏下第一行
        List<String> titleSecond = new ArrayList<>();
        StringBuilder titleSecondRow = new StringBuilder();
        if(type==0){
            RecallPlan recallPlan = recallPlanMapper.selectByPrimaryKey(id);
            titleSecondRow.append("召回单号:");
            titleSecondRow.append(recallPlan.getCode());
            titleSecondRow.append("  通知日期:");
            titleSecondRow.append(recallPlan.getPlanDate());
            titleSecondRow.append("  通知人员:");
            titleSecondRow.append(recallPlan.getPlanManName());
            titleSecondRow.append("  召回单位:");
            titleSecondRow.append(recallPlan.getRecallCompany());
            titleSecond.add(titleSecondRow.toString());

            titleSecondRow = new StringBuilder();
            titleSecondRow.append("召回责任人:");
            titleSecondRow.append(recallPlan.getRecallMan());
            titleSecondRow.append("  联系电话:");
            titleSecondRow.append(recallPlan.getRecallManPhone());
            titleSecondRow.append("  召回级别:");
            titleSecondRow.append(RecallLevelEnum.getName(recallPlan.getRecallLevel()));
            titleSecondRow.append("  召回时限:");
            titleSecondRow.append(recallPlan.getRecallTimeLimit());
            titleSecond.add(titleSecondRow.toString());

            titleSecondRow = new StringBuilder();
            titleSecondRow.append("召回原因:");
            titleSecondRow.append(RecallReasonEnum.getName(recallPlan.getRecallReason()));
            titleSecondRow.append("\t\t\t\t召回存放:");
            titleSecondRow.append(RecallDepositEnum.getName(recallPlan.getRecallDeposit()));
            titleSecond.add(titleSecondRow.toString());
        }else {
            RecallRecord recallRecord = recallRecordMapper.selectByPrimaryKey(id);
            titleSecondRow.append("召回单号:");
            titleSecondRow.append(recallRecord.getCode());
            titleSecondRow.append("  召回日期:");
            titleSecondRow.append(recallRecord.getRecallDate());
            titleSecondRow.append("  召回人员:");
            titleSecondRow.append(recallRecord.getRecallManName());
            titleSecondRow.append("  召回处理:");
            titleSecondRow.append(HandleMeasuresEnum.getName(recallRecord.getHandleMeasures()));
            titleSecond.add(titleSecondRow.toString());
        }
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("药品召回");
        if(type==0){
            List<RecallPlanDetailVO> recallPlanDetailVOS = recallPlanDetailMapper.selectByPlanId(id,loginUser.getEnterpriseId());
            purchaseGeneralComponent.commExcelExport(output,map,recallPlanDetailVOS,name,titleSecond,end.toString(),false,needTotalName);
        }else {
            List<RecallRecordDetailVO> recallRecordDetailVOS = recallRecordDetailMapper.selectByRecallId(id,loginUser.getEnterpriseId());
            purchaseGeneralComponent.commExcelExport(output,map,recallRecordDetailVOS,name,titleSecond,end.toString(),false,needTotalName);
        }

    }

    private RecallRecordDetail genRecordDtlInfo(UserVO loginUser, RecallRecord recallRecord, RecallRecordDetailVO recallRecordDetailVO) throws Exception {
        RecallRecordDetail recallRecordDetail = new RecallRecordDetail();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallRecordDetailVO,recallRecordDetail);
        //set 企业信息
        recallRecordDetail.setEnterpriseId(loginUser.getEnterpriseId());
        recallRecordDetail.setParentId(loginUser.getParentId());
        //set 创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(recallRecordDetail,loginUser,true);
        //set 总单信息
        recallRecordDetail.setRecallId(recallRecord.getId());
        recallRecordDetail.setOrderType(recallRecord.getOrderType());
        recallRecordDetail.setRecallCode(recallRecord.getCode());
        recallRecordDetail.setRecallDate(recallRecord.getRecallDate());
        //set 基础单据信息
        recallRecordDetail.setBaseOrderCode(recallRecord.getBaseOrderCode());
        recallRecordDetail.setBaseOrderDate(recallRecord.getBaseOrderDate());
        recallRecordDetail.setBaseOrderId(recallRecord.getBaseOrderId());
        recallRecordDetail.setBaseOrderType(recallRecord.getBaseOrderType());
        recallRecordDetail.setRecallDate(recallRecord.getRecallDate());
        //重新查询商品信息并set
        Goods goods = goodsMapper.selectByPrimaryKey(recallRecordDetailVO.getGoodsId());
        recallRecordDetail.setGoodsName(goods.getName());
        recallRecordDetail.setGoodsCode(goods.getCode());
        recallRecordDetail.setBarcode(goods.getBarcode());
        recallRecordDetail.setGoodsGenericName(goods.getGenericName());
        recallRecordDetail.setDosageId(goods.getDosageId());
        recallRecordDetail.setDosageName(goods.getDosageName());
        recallRecordDetail.setUnitId(goods.getUnitId());
        recallRecordDetail.setUnitName(goods.getUnitName());
        recallRecordDetail.setGoodsSpecification(goods.getSpecification());
        recallRecordDetail.setManufacturerId(goods.getManufacturerId());
        recallRecordDetail.setManufacturer(goods.getManufacturer());
        recallRecordDetail.setGoodsPlace(goods.getPlace());
        recallRecordDetail.setApprovalNumber(goods.getApprovalNumber());
        //set 状态 默认为1
        recallRecordDetail.setStatus(1);
        //重新查询批号信息并set
        LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(recallRecordDetail.getLotId());
        recallRecordDetail.setLotNumber(lotNumber.getLotNum());
        recallRecordDetail.setProductDate(lotNumber.getProductDate());
        recallRecordDetail.setValidDate(lotNumber.getValidUntil());
        return recallRecordDetail;

    }

    private RecallRecord genRecordDoc(UserVO loginUser, RecallRecordVO recallRecordVO, Set<Long> goodsSet) throws Exception {
        RecallRecord recallRecord = new RecallRecord();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recallRecordVO,recallRecord);
        //set 企业信息
        recallRecord.setEnterpriseId(loginUser.getEnterpriseId());
        recallRecord.setParentId(loginUser.getParentId());
        //set 创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(recallRecord,loginUser,true);
        //set 品种数量
        recallRecord.setVarietiesQuantity(goodsSet.size());
        //set 单据类型
        recallRecord.setOrderType(OrderRule.RECALL.getType());
        //set 编号
        recallRecord.setCode(orderCodeComponent.generate(OrderRule.RECALL.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        //set 状态 默认为1
        recallRecord.setStatus(1);
        //重新查退回单位信息 并set
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(recallRecordVO.getReturnUnitId());
        recallRecord.setReturnUnitCode(enterprise.getCode());
        recallRecord.setReturnUnitName(enterprise.getName());
        //重新查基础单据(计划)信息并set
        RecallPlan recallPlan = recallPlanMapper.selectByPrimaryKey(recallRecordVO.getBaseOrderId());
        recallRecord.setBaseOrderCode(recallPlan.getCode());
        recallRecord.setBaseOrderDate(recallPlan.getPlanDate());
        recallRecord.setBaseOrderType(recallPlan.getOrderType());
        //重新查召回人信息并set
        User user = userMapper.selectByPrimaryKey(recallRecord.getRecallManId());
        recallRecord.setRecallManCode(user.getCode());
        recallRecord.setRecallManName(user.getName());
        return recallRecord;

    }

    private Set<Long> getRecordGoodsSet(RecallRecordVO recallRecordVO) {
        Set<Long> goodsSet = new HashSet<>();
        BigDecimal quantity = BigDecimal.ZERO;
        for(RecallRecordDetailVO recallRecordDetailVO : recallRecordVO.getRecallRecordDetailVOList()){
            if(recallRecordDetailVO.getGoodsId() == null) throw new RuntimeException("货品ID不能为空");
            if(recallRecordDetailVO.getLotId() == null) throw new RuntimeException("批号ID不能为空");
            goodsSet.add(recallRecordDetailVO.getGoodsId());
            quantity = quantity.add(recallRecordDetailVO.getQuantity());
        }
        recallRecordVO.setQuantityTotal(quantity);
        return goodsSet;
    }
}
