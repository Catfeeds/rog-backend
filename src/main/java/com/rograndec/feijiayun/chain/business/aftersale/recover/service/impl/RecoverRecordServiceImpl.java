package com.rograndec.feijiayun.chain.business.aftersale.recover.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.constant.RecoverHandleMeasuresEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverPlanDetailMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverPlanMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverRecordMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverPlan;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverPlanDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverRecord;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverRecordDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recover.service.RecoverRecordService;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RequestRecordGoodsVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xingjian.lan
 * @version 1.0
 * @ClassName: RecoverRecordServiceImpl
 * @Description: 售后管理-追回管理-追回记录-实现接口
 * @date 2017-10-16 17:57:12
 */
@Service
public class RecoverRecordServiceImpl implements RecoverRecordService {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RecoverRecordServiceImpl.class);

    @Autowired
    private RecoverRecordMapper recoverRecordMapper;

    @Autowired
    private RecoverRecordDetailMapper recoverRecordDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private RecoverPlanMapper recoverPlanMapper;

    @Autowired
    private RecoverPlanDetailMapper recoverPlanDetailMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public List<RecoverRecordVO> getRecoverRecordData(UserVO userVO) throws Exception {
        List<RecoverRecordVO> list = null; //需修改
        return list;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int save(RecoverRecordSaveOrupdateVO recoverRecordVO, UserVO userVO) throws Exception {
        //请求参数验证
        checkSaveVO(recoverRecordVO);

        //生成追回记录单
        RecoverRecord recoverRecord = getRecoverRecord(recoverRecordVO, userVO);
        recoverRecordMapper.insertSelective(recoverRecord);

        //追回记录明细
        List<RecoverRecordDetail> recoverRecordDetailList = getRecoverRecordDetailList(recoverRecordVO, recoverRecord);
        for (RecoverRecordDetail recoverRecordDetail : recoverRecordDetailList) {
            recoverRecordDetailMapper.insertSelective(recoverRecordDetail);
        }
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int update(RecoverRecordSaveOrupdateVO updateVO, UserVO userVO) throws Exception {

        //判断追回记录ID
        if (updateVO.getRecoverId() == null || updateVO.getRecoverId() == 0) {
            throw new BusinessException("追回记录ID为空，请检查参数！");
        }

        //参数验证
        checkSaveVO(updateVO);

        //更新操作
        updateRecoverRecord(updateVO, userVO);
        return 1;
    }

    @Override
    public int delete(Long id) throws Exception {
        int i = recoverRecordDetailMapper.deleteDetailWithRecoverId(id);
        if (i == 0) {
            throw new BusinessException("删除追回记录明细失败！");
        }
        return recoverRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page getRecoverRecordPage(Page page, Map<String, Object> map) {
        //查询排序
        Integer sortDate = map.get("sortDate") == null ? null : (Integer) map.get("sortDate");
        Integer sortCode = map.get("sortCode") == null ? null : (Integer) map.get("sortCode");
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "plan_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "plan_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        map.put("sort", sort);

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        //查询配后退后入库单列表
        List<RecoverRecordVO> recoverRecordVOList = recoverRecordMapper.getRecoverRecordPage(map);
        page.setResult(recoverRecordVOList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public RecoverRecordVO getRecoverRecordDetail(Long id) {
        RecoverRecordVO recoverRecordVO = recoverRecordMapper.getRecoverPlanWithId(id);
        if (recoverRecordVO == null) {
            throw new BusinessException("查询追回记录不存在！");
        }

        List<RecoverRecordDetail> recoverRecordDetails = recoverRecordDetailMapper.getRecoverRecordDetailWithPlanId(id);
        if (recoverRecordDetails == null || recoverRecordDetails.size() == 0) {
            throw new BusinessException("查询追回记录明细错误！");
        }
        recoverRecordVO.setRecordDetailList(recoverRecordDetails);
        return recoverRecordVO;
    }

    @Override
    public void excelExport(OutputStream outPut, Long id, UserVO userVO) throws Exception {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("recoverQuantity", "追回数量");
        map.put("unrecoverQuantity", "未追回数量");
        map.put("unrecoverReason", "未追回原因");
        map.put("medicalAccident", "是否造成医疗事故");

        RecoverRecord recoverRecord = recoverRecordMapper.selectByPrimaryKey(id);
        if (recoverRecord == null) {
            throw new BusinessException("追回记录单据不存在！");
        }
        List<RecoverRecordDetail> recoverRecordDetailList = recoverRecordDetailMapper.getRecoverRecordDetailWithPlanId(id);
        if (recoverRecordDetailList == null || recoverRecordDetailList.size() == 0) {
            throw new BusinessException("追回记录明细不存在！");
        }

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("追回单号:");
        titleSecondRow.append(recoverRecord.getCode() == null ? "" : recoverRecord.getCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("追回日期:");
        titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recoverRecord.getRecoverDate()));
        titleSecondRow.append("     ");
        titleSecondRow.append("追回人员:");
        titleSecondRow.append(recoverRecord.getRecoverManName() == null ? "" : recoverRecord.getRecoverManName());
        titleSecondRow.append("     ");
        titleSecondRow.append("追回处理:");
        titleSecondRow.append(RecoverHandleMeasuresEnum.getName(recoverRecord.getHandleMeasures()));
        titleSecondRow.append("     ");
        titleSecondRow.append("追回单位编码:");
        titleSecondRow.append(recoverRecord.getRecoverUnitCode() == null ? "" : recoverRecord.getRecoverUnitCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("追回单位名称:");
        titleSecondRow.append(recoverRecord.getRecoverUnitName() == null ? "" : recoverRecord.getRecoverUnitName());
        titleSecondRow.append("     ");
        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("药品追回");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelDistrExport(outPut, map, recoverRecordDetailList, name, secondTitle,"","", new ArrayList<>());
    }

    /**
     * 保存参数验证
     *
     * @param recoverRecordVO
     * @throws Exception
     */
    private void checkSaveVO(RecoverRecordSaveOrupdateVO recoverRecordVO) throws Exception {
        if (recoverRecordVO == null || recoverRecordVO.getRecordGoodsList() == null
                || recoverRecordVO.getRecordGoodsList().size() == 0) {
            throw new BusinessException("添加/修改追回记录错误，请检查参数！");
        }

        //追回日期
        if (recoverRecordVO.getRecoverDate() == null) {
            throw new BusinessException("追回日期不能为空！");
        }

        //追回人员
        if (recoverRecordVO.getRecoverManId() == null) {
            throw new BusinessException("追回人员不能为空！");
        }

        //追回处理
        if (recoverRecordVO.getHandleMeasures() == null) {
            throw new BusinessException("追回处理不能为空！");
        }

        //追回数量、未追回数量不能同时为0；未追回数量为0时，未追回原因、是否造成医疗施工可以为空
        for (RequestRecordGoodsVO goodsVO : recoverRecordVO.getRecordGoodsList()) {
            //追回数量判空
            if (goodsVO.getRecoverQuantity() == null) {
                throw new BusinessException("追回数量不能为空！");
            }
            //未追回数量判空
            if (goodsVO.getUnrecoverQuantity() == null) {
                throw new BusinessException("未追回数量不能为空！");
            }

            //追回数量、未追回数量不能同时为0
            if (goodsVO.getRecoverQuantity().compareTo(BigDecimal.ZERO) == 0
                    && goodsVO.getUnrecoverQuantity().compareTo(BigDecimal.ZERO) == 0) {
                throw new BusinessException("追回数量、未追回数量不能同时未0！");
            }

            //未追回数量大于0时，未追回原因、是否造成医疗事故不能为空
            if (goodsVO.getUnrecoverQuantity().compareTo(BigDecimal.ZERO) == 1) {

                if (StringUtils.isEmpty(goodsVO.getUnrecoverReason())) {
                    throw new BusinessException("未追回原因不能为空！");
                }

                if (StringUtils.isEmpty(goodsVO.getMedicalAccident())) {
                    throw new BusinessException("是否造成医疗事故不能为空！");
                }
            }
        }
    }

    /**
     * 生成追回记录单
     *
     * @param recoverRecordVO
     * @param userVO
     * @return
     */
    private RecoverRecord getRecoverRecord(RecoverRecordSaveOrupdateVO recoverRecordVO, UserVO userVO) throws Exception {
        RecoverRecord recoverRecord = new RecoverRecord();
        //企业信息
        recoverRecord.setEnterpriseId(userVO.getEnterpriseId());
        recoverRecord.setParentId(userVO.getParentId());

        //单据类型
        recoverRecord.setOrderType(OrderRule.RECOVER.getType());
        //单据编码
        String code = orderCodeComponent.generate(OrderRule.RECOVER_PLAN.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        recoverRecord.setCode(code);

        //基础单据ID
        RecoverPlan recoverPlan = recoverPlanMapper.selectByPrimaryKey(recoverRecordVO.getBaseOrderId());
        if (recoverPlan == null) {
            throw new BusinessException("基础单据不存在！");
        }
        recoverRecord.setBaseOrderId(recoverPlan.getId());
        recoverRecord.setBaseOrderCode(recoverPlan.getCode());
        recoverRecord.setBaseOrderType(recoverPlan.getOrderType());
        recoverRecord.setBaseOrderDate(recoverPlan.getPlanDate());

        //追回日期
        recoverRecord.setRecoverDate(recoverRecordVO.getRecoverDate());

        //追回单位信息
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(recoverRecordVO.getRecoverUnitId());
        if (enterprise == null) {
            throw new BusinessException("追回单位信息不存在！");
        }
        recoverRecord.setRecoverUnitId(enterprise.getId());
        recoverRecord.setRecoverUnitCode(enterprise.getCode());
        recoverRecord.setRecoverUnitName(enterprise.getName());

        //追回人员信息
        User manInfo = userMapper.selectByPrimaryKey(recoverRecordVO.getRecoverManId());
        if (manInfo == null) {
            throw new BusinessException("追回人员不存在！");
        }
        recoverRecord.setRecoverManId(manInfo.getId());
        recoverRecord.setRecoverManCode(manInfo.getCode());
        recoverRecord.setRecoverManName(manInfo.getName());

        //追回处理
        recoverRecord.setHandleMeasures(recoverRecordVO.getHandleMeasures());
        //数量合计（追回数量合计）
        BigDecimal quantityTotal = BigDecimal.ZERO;
        for (RequestRecordGoodsVO goodsVO : recoverRecordVO.getRecordGoodsList()) {
            quantityTotal = quantityTotal.add(goodsVO.getRecoverQuantity());
        }
        recoverRecord.setQuantityTotal(quantityTotal);
        //品种合计
        recoverRecord.setVarietiesQuantity(recoverRecordVO.getRecordGoodsList().size());
        //状态（默认1）
        recoverRecord.setStatus(1);

        //创建人信息
        recoverRecord.setCreaterId(userVO.getUserId());
        recoverRecord.setCreaterCode(userVO.getUserCode());
        recoverRecord.setCreaterName(userVO.getUserName());
        recoverRecord.setCreateTime(new Date());
        return recoverRecord;
    }

    /**
     * 封装追回计划明细信息
     *
     * @param saveRecordVO
     * @param recoverRecord
     * @return
     */
    private List<RecoverRecordDetail> getRecoverRecordDetailList(RecoverRecordSaveOrupdateVO saveRecordVO,
                                                             RecoverRecord recoverRecord) {
        List<RecoverRecordDetail> recoverRecordDetailList = new ArrayList<>();
        RecoverRecordDetail recoverRecordDetail;
        RecoverPlanDetail planDetail;
        for (RequestRecordGoodsVO goodsVO : saveRecordVO.getRecordGoodsList()) {
            recoverRecordDetail = new RecoverRecordDetail();

            planDetail = recoverPlanDetailMapper.selectByPrimaryKey(goodsVO.getBaseOrderDtlId());
            if (planDetail == null) {
                throw new BusinessException("查询基础单据明细不存在！");
            }
            //复制商品信息、批号信息
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(planDetail, recoverRecordDetail);

            recoverRecordDetail.setId(null);
            //企业信息
            recoverRecordDetail.setEnterpriseId(recoverRecord.getEnterpriseId());
            recoverRecordDetail.setParentId(recoverRecord.getParentId());
            //单据信息
            recoverRecordDetail.setRecoverId(recoverRecord.getId());
            recoverRecordDetail.setOrderType(recoverRecord.getOrderType());
            recoverRecordDetail.setRecoverCode(recoverRecord.getCode());
            recoverRecordDetail.setRecoverDate(recoverRecord.getRecoverDate());

            //基础单据信息
            recoverRecordDetail.setBaseOrderDtlId(planDetail.getId());
            recoverRecordDetail.setBaseOrderId(recoverRecord.getBaseOrderId());
            recoverRecordDetail.setBaseOrderCode(recoverRecord.getBaseOrderCode());
            recoverRecordDetail.setBaseOrderDate(recoverRecord.getBaseOrderDate());
            recoverRecordDetail.setBaseOrderType(recoverRecord.getBaseOrderType());

            //追回数量
            recoverRecordDetail.setRecoverQuantity(goodsVO.getRecoverQuantity());
            //未追回数量
            recoverRecordDetail.setUnrecoverQuantity(goodsVO.getUnrecoverQuantity());
            //未追回原因
            recoverRecordDetail.setUnrecoverReason(goodsVO.getUnrecoverReason());
            //是否造成医疗事故
            recoverRecordDetail.setMedicalAccident(goodsVO.getMedicalAccident());
            //行号
            recoverRecordDetail.setLineNum(goodsVO.getLineNum());
            //状态(默认1)
            recoverRecordDetail.setStatus(1);

            //创建人信息
            recoverRecordDetail.setCreaterId(recoverRecord.getCreaterId());
            recoverRecordDetail.setCreaterCode(recoverRecord.getCreaterCode());
            recoverRecordDetail.setCreaterName(recoverRecord.getCreaterName());
            recoverRecordDetail.setCreateTime(recoverRecord.getCreateTime());

            recoverRecordDetailList.add(recoverRecordDetail);
        }

        return recoverRecordDetailList;
    }

    /**
     * 修改追回计划
     *
     * @param updateVO
     * @param userVO
     * @throws Exception
     */
    private void updateRecoverRecord(RecoverRecordSaveOrupdateVO updateVO, UserVO userVO) throws Exception {
        //追回计划信息
        RecoverRecord recoverRecord = recoverRecordMapper.selectByPrimaryKey(updateVO.getRecoverId());
        if (recoverRecord == null) {
            throw new BusinessException("追回记录不存在！");
        }

        //更新追回日期
        recoverRecord.setRecoverDate(updateVO.getRecoverDate());

        //更新追回单位
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(updateVO.getRecoverUnitId());
        if (enterprise == null) {
            throw new BusinessException("追回单位信息不存在！");
        }
        recoverRecord.setRecoverUnitId(enterprise.getId());
        recoverRecord.setRecoverUnitCode(enterprise.getCode());
        recoverRecord.setRecoverUnitName(enterprise.getName());

        //更新追回人员信息
        User manInfo = userMapper.selectByPrimaryKey(updateVO.getRecoverManId());
        if (manInfo == null) {
            throw new BusinessException("追回人员不存在！");
        }
        recoverRecord.setRecoverManId(manInfo.getId());
        recoverRecord.setRecoverManCode(manInfo.getCode());
        recoverRecord.setRecoverManName(manInfo.getName());

        //更新退回处理方式
        recoverRecord.setHandleMeasures(updateVO.getHandleMeasures());
        //数量合计（追回数量合计）
        BigDecimal quantityTotal = BigDecimal.ZERO;
        for (RequestRecordGoodsVO goodsVO : updateVO.getRecordGoodsList()) {
            quantityTotal = quantityTotal.add(goodsVO.getRecoverQuantity());
        }
        recoverRecord.setQuantityTotal(quantityTotal);
        //更新品种合计
        recoverRecord.setVarietiesQuantity(updateVO.getRecordGoodsList().size());

        //更新人信息
        recoverRecord.setModifierId(userVO.getUserId());
        recoverRecord.setModifierCode(userVO.getUserCode());
        recoverRecord.setModifierName(userVO.getUserName());
        recoverRecord.setUpdateTime(new Date());

        //更新追回计划
        int i = recoverRecordMapper.updateByPrimaryKeySelective(recoverRecord);
        if (i != 1) {
            throw new BusinessException("更新追回记录错误！");
        }

        //更新明细
        updateRecoverRecordDetail(recoverRecord, updateVO, userVO);
    }

    /**
     * 更新追回计划明细
     * @param recoverRecord
     * @param updateVO
     * @param userVO
     */
    private void updateRecoverRecordDetail(RecoverRecord recoverRecord, RecoverRecordSaveOrupdateVO updateVO, UserVO userVO) throws Exception {
        List<RecoverRecordDetail> recoverRecordDetailList = getRecoverRecordDetailList(updateVO, recoverRecord);

        //删除追回记录下的明细，重新生成新的明细
        int i = recoverRecordDetailMapper.deleteDetailWithRecoverId(recoverRecord.getId());
        if (i== 0) {
            throw new BusinessException("删除追回记录明细错误！");
        }

        //插入明细
        for (RecoverRecordDetail detail : recoverRecordDetailList) {
            recoverRecordDetailMapper.insertSelective(detail);
        }
    }
}
