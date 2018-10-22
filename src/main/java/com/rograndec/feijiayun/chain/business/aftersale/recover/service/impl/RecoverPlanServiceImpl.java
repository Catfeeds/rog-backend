package com.rograndec.feijiayun.chain.business.aftersale.recover.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallRecord;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverPlanDetailMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverPlanMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.dao.RecoverRecordMapper;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverPlan;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverPlanDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverRecord;
import com.rograndec.feijiayun.chain.business.aftersale.recover.service.RecoverPlanService;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanDetailVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RequestPlanGoodsVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author xingjian.lan
 * @version 1.0
 * @ClassName: RecoverPlanServiceImpl
 * @Description: 售后管理-追回管理-追回计划-实现接口
 * @date 2017-10-16 17:51:12
 */
@Service
public class RecoverPlanServiceImpl implements RecoverPlanService {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RecoverPlanServiceImpl.class);

    @Autowired
    private RecoverPlanMapper recoverPlanMapper;

    @Autowired
    private RecoverPlanDetailMapper recoverPlanDetailMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private RecoverRecordMapper recoverRecordMapper;

    @Override
    public List<RecoverPlanVO> getRecoverPlanData(UserVO userVO) throws Exception {
        List<RecoverPlanVO> list = null; //需修改
        return list;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int save(RecoverPlanSaveOrupdateVO saveRecoverPlan, UserVO userVO) throws Exception {
        //参数验证
        checkSaveVO(saveRecoverPlan);
        //追回计划单
        RecoverPlan recoverPlan = getRecoverPlan(saveRecoverPlan, userVO);
        recoverPlanMapper.insertSelective(recoverPlan);

        //追回计划明细
        List<RecoverPlanDetail> recoverPlanDetails = getRecoverPlanDetailList(saveRecoverPlan, recoverPlan, false);
        for (RecoverPlanDetail recoverPlanDetail : recoverPlanDetails) {
            recoverPlanDetailMapper.insertSelective(recoverPlanDetail);
        }
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int update(RecoverPlanSaveOrupdateVO updateVO, UserVO userVO) throws Exception {
        //追回计划ID判断
        if (updateVO.getPlanId() == null || updateVO.getPlanId() == 0) {
            throw new BusinessException("追回计划ID为空，请检查参数！");
        }

        //参数验证
        checkSaveVO(updateVO);

        //更新操作
        updateRecoverPlan(updateVO, userVO);
        return 1;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int delete(Long id) throws Exception {
        int i = recoverPlanDetailMapper.deleteDetailWithPlanId(id);
        if (i == 0) {
            throw new BusinessException("删除追回计划明细失败！");
        }
        return recoverPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page getRecoverPlanPage(Page page, Map<String, Object> map) {
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
        List<RecoverPlanVO> recoverPlanVOList = recoverPlanMapper.getRecoverPlanPage(map);

        if (recoverPlanVOList != null && recoverPlanVOList.size() > 0) {
            for (RecoverPlanVO recoverPlanVO : recoverPlanVOList) {
                recoverPlanVO.setModifyFlag(isDeleteRecallPlan(recoverPlanVO.getId(), (Long)map.get("enterpriseId")));
            }
        }

        page.setResult(recoverPlanVOList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public RecoverPlanVO getRecoverPlanDetail(Long id) {
        RecoverPlanVO recoverPlanVO = recoverPlanMapper.getRecoverPlanWithId(id);
        if (recoverPlanVO == null) {
            throw new BusinessException("查询追回计划错误！");
        }

        List<RecoverPlanDetailVO> recoverPlanDetailVOList = recoverPlanDetailMapper.getRecoverPlanDetailWithPlanId(id, null);
        if (recoverPlanDetailVOList == null || recoverPlanDetailVOList.size() == 0) {
            throw new BusinessException("查询追回明细错误！");
        }
        recoverPlanVO.setRecoverPlanDetailVOList(recoverPlanDetailVOList);
        return recoverPlanVO;
    }

    @Override
    public List<AfterSaleChooseGoodsVO> getAfterSaleGoodsList(Long enterpriseId, String param) {
        return stockMapper.getAfterSaleGoodsList(enterpriseId, param);
    }

    @Override
    public List<AfterSaleChooseGoodsVO> getRecoverGoodsList(Long id, String param) {
        List<AfterSaleChooseGoodsVO> saleChooseGoodsVOList = new ArrayList<>();
        List<RecoverPlanDetailVO> recoverPlanDetailVOList = recoverPlanDetailMapper.getRecoverPlanDetailWithPlanId(id, param);
        if (recoverPlanDetailVOList == null || recoverPlanDetailVOList.size() == 0) {
            return  saleChooseGoodsVOList;
        }

        AfterSaleChooseGoodsVO saleChooseGoodsVO;
        for (RecoverPlanDetailVO recoverPlanDetail : recoverPlanDetailVOList) {
            saleChooseGoodsVO = new AfterSaleChooseGoodsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(recoverPlanDetail, saleChooseGoodsVO);
            saleChooseGoodsVO.setBaseOrderDtlId(recoverPlanDetail.getId());
            saleChooseGoodsVOList.add(saleChooseGoodsVO);
        }
        return saleChooseGoodsVOList;
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
        map.put("recoverReason", "追回原因");
        RecoverPlan recoverPlan = recoverPlanMapper.selectByPrimaryKey(id);
        if (recoverPlan == null) {
            throw new BusinessException("追回计划单据不存在！");
        }
        List<RecoverPlanDetail> recoverPlanDetailList = recoverPlanDetailMapper.getRecoverPlanDetails(id);
        if (recoverPlanDetailList == null || recoverPlanDetailList.size() == 0) {
            throw new BusinessException("追回计划明细不存在！");
        }

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("追回单号:");
        titleSecondRow.append(recoverPlan.getCode() == null ? "" : recoverPlan.getCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("通知日期:");
        titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recoverPlan.getPlanDate()));
        titleSecondRow.append("     ");
        titleSecondRow.append("通知人员:");
        titleSecondRow.append(recoverPlan.getPlanManName() == null ? "" : recoverPlan.getPlanManName());
        titleSecondRow.append("     ");
        titleSecondRow.append("追回负责人:");
        titleSecondRow.append(recoverPlan.getRecoverMan() == null ? "" : recoverPlan.getRecoverMan());
        titleSecondRow.append("     ");
        titleSecondRow.append("联系电话:");
        titleSecondRow.append(recoverPlan.getRecoverManPhone() == null ? "" : recoverPlan.getRecoverManPhone());
        titleSecondRow.append("     ");
        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("药品追回");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelDistrExport(outPut, map, recoverPlanDetailList, name, secondTitle,"","", new ArrayList<>());
    }

    /**
     * 保存参数验证
     *
     * @param recoverPlan
     * @throws Exception
     */
    private void checkSaveVO(RecoverPlanSaveOrupdateVO recoverPlan) throws Exception {
        if (recoverPlan == null || recoverPlan.getGoodsList() == null || recoverPlan.getGoodsList().size() == 0) {
            throw new BusinessException("添加/修改追回计划错误，请检查参数");
        }

        //通知日期
        if (recoverPlan.getPlanDate() == null) {
            throw new BusinessException("通知日期不能为空！");
        }

        //通知人员
        if (recoverPlan.getPlanManId() == null) {
            throw new BusinessException("通知人员不能为空！");
        }

        //追回负责人、联系电话可以不填写

        //追回原因必填, 商品、批号不能重复
        Set<String> goodsIdAndLotId = new HashSet<>();
        for (RequestPlanGoodsVO goodsVO : recoverPlan.getGoodsList()) {
            if (StringUtils.isEmpty(goodsVO.getRecoverReason())) {
                throw new BusinessException("追回原因不能为空！");
            }
            goodsIdAndLotId.add(goodsVO.getGoodsId() + "," + goodsVO.getLotId());
        }

        if (goodsIdAndLotId.size() < recoverPlan.getGoodsList().size()) {
            throw new BusinessException("商品列表中商品、批次不能重复！");
        }
    }

    /**
     * 封装追回计划单
     *
     * @return
     */
    private RecoverPlan getRecoverPlan(RecoverPlanSaveOrupdateVO saveVO, UserVO userVO) throws Exception {
        RecoverPlan recoverPlan = new RecoverPlan();
        //企业基础信息
        recoverPlan.setEnterpriseId(userVO.getEnterpriseId());
        recoverPlan.setParentId(userVO.getParentId());

        //单据类型、编码
        recoverPlan.setOrderType(OrderRule.RECOVER_PLAN.getType());
        //单号生成规则
        String code = orderCodeComponent.generate(OrderRule.RECOVER_PLAN.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        recoverPlan.setCode(code);

        //通知日期
        recoverPlan.setPlanDate(saveVO.getPlanDate());
        //通知人员信息
        User manInfo = userMapper.selectByPrimaryKey(saveVO.getPlanManId());
        if (manInfo == null) {
            throw new BusinessException("通知人不存在！");
        }
        recoverPlan.setPlanManId(manInfo.getId());
        recoverPlan.setPlanManCode(manInfo.getCode());
        recoverPlan.setPlanManName(manInfo.getName());

        //追回责任人
        recoverPlan.setRecoverMan(saveVO.getRecoverMan());
        //追回责任人联系电话
        recoverPlan.setRecoverManPhone(saveVO.getRecoverManPhone());

        //品种数量
        recoverPlan.setVarietiesQuantity(saveVO.getGoodsList().size());

        //待追回-1
        recoverPlan.setStatus(1);

        //创建人信息
        recoverPlan.setCreaterId(userVO.getUserId());
        recoverPlan.setCreaterCode(userVO.getUserCode());
        recoverPlan.setCreaterName(userVO.getUserName());
        recoverPlan.setCreateTime(new Date());
        return recoverPlan;
    }

    /**
     * 封装追回计划明细信息
     *
     * @param saveRecoverPlanVO
     * @param recoverPlan
     * @return
     */
    private List<RecoverPlanDetail> getRecoverPlanDetailList(RecoverPlanSaveOrupdateVO saveRecoverPlanVO,
                                                             RecoverPlan recoverPlan, boolean update) {
        List<RecoverPlanDetail> recoverPlanDetails = new ArrayList<>();
        RecoverPlanDetail recoverPlanDetail;
        AfterSaleChooseGoodsVO saleChooseGoodsVO;
        for (RequestPlanGoodsVO goodsVO : saveRecoverPlanVO.getGoodsList()) {
            recoverPlanDetail = new RecoverPlanDetail();

            //修改时，判断商品是否已经存在,存在则直接获取
            if (update) {
                RecoverPlanDetail oldDetail = recoverPlanDetailMapper.getRecoverPlanDetailWithPlanIdGoodsIdLotId(recoverPlan.getId(), goodsVO.getGoodsId(), goodsVO.getLotId());
                if (oldDetail != null) {
                    CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(oldDetail, recoverPlanDetail);
                    recoverPlanDetail.setId(null);

                    //追回单日期
                    recoverPlanDetail.setPlanDate(recoverPlan.getPlanDate());
                    //追回原因
                    recoverPlanDetail.setRecoverReason(goodsVO.getRecoverReason());
                    //行号
                    recoverPlanDetail.setLineNum(goodsVO.getLineNum());

                    //更新人信息
                    recoverPlanDetail.setModifierId(recoverPlan.getModifierId());
                    recoverPlanDetail.setModifierCode(recoverPlan.getModifierCode());
                    recoverPlanDetail.setModifierName(recoverPlan.getModifierName());
                    recoverPlanDetail.setUpdateTime(recoverPlan.getUpdateTime());
                    recoverPlanDetails.add(recoverPlanDetail);
                    continue;
                }
            }

            saleChooseGoodsVO = stockMapper.getAfterSaleGoodsWithGoodsIdAndLotId(recoverPlan.getEnterpriseId(), goodsVO.getGoodsId(), goodsVO.getLotId());
            if (saleChooseGoodsVO == null) {
                throw new BusinessException("通过商品ID、批次ID查询商品信息错误！");
            }
            //复制商品信息、批号信息
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saleChooseGoodsVO, recoverPlanDetail);

            //企业信息
            recoverPlanDetail.setEnterpriseId(recoverPlan.getEnterpriseId());
            recoverPlanDetail.setParentId(recoverPlan.getParentId());
            //单据信息
            recoverPlanDetail.setPlanId(recoverPlan.getId());
            recoverPlanDetail.setOrderType(recoverPlan.getOrderType());
            recoverPlanDetail.setPlanCode(recoverPlan.getCode());
            recoverPlanDetail.setPlanDate(recoverPlan.getPlanDate());

            //追回原因
            recoverPlanDetail.setRecoverReason(goodsVO.getRecoverReason());
            //行号
            recoverPlanDetail.setLineNum(goodsVO.getLineNum());
            //状态，默认1
            recoverPlanDetail.setStatus(1);

            //创建人信息
            recoverPlanDetail.setCreaterId(recoverPlan.getCreaterId());
            recoverPlanDetail.setCreaterCode(recoverPlan.getCreaterCode());
            recoverPlanDetail.setCreaterName(recoverPlan.getCreaterName());
            recoverPlanDetail.setCreateTime(recoverPlan.getCreateTime());

            recoverPlanDetails.add(recoverPlanDetail);
        }

        return recoverPlanDetails;
    }

    /**
     * 修改追回计划
     *
     * @param updateVO
     * @param userVO
     * @throws Exception
     */
    private void updateRecoverPlan(RecoverPlanSaveOrupdateVO updateVO, UserVO userVO) throws Exception {
        //追回计划信息
        RecoverPlan recoverPlan = recoverPlanMapper.selectByPrimaryKey(updateVO.getPlanId());
        //通知日期
        recoverPlan.setPlanDate(updateVO.getPlanDate());
        //通知人员信息
        User manInfo = userMapper.selectByPrimaryKey(updateVO.getPlanManId());
        if (manInfo == null) {
            throw new BusinessException("通知人不存在！");
        }
        recoverPlan.setPlanManId(manInfo.getId());
        recoverPlan.setPlanManCode(manInfo.getCode());
        recoverPlan.setPlanManName(manInfo.getName());

        //追回责任人
        recoverPlan.setRecoverMan(updateVO.getRecoverMan());
        //追回责任人联系电话
        recoverPlan.setRecoverManPhone(updateVO.getRecoverManPhone());

        //品种数量
        recoverPlan.setVarietiesQuantity(updateVO.getGoodsList().size());

        //更新人信息
        recoverPlan.setModifierId(userVO.getUserId());
        recoverPlan.setModifierCode(userVO.getUserCode());
        recoverPlan.setModifierName(userVO.getUserName());
        recoverPlan.setUpdateTime(new Date());

        //更新追回计划
        int i = recoverPlanMapper.updateByPrimaryKeySelective(recoverPlan);
        if (i != 1) {
            throw new BusinessException("更新追回计划错误！");
        }

        //更新明细
        updateRecoverPlanDetail(recoverPlan, updateVO, userVO);
    }

    /**
     * 更新追回计划明细
     * @param recoverPlan
     * @param updateVO
     * @param userVO
     */
    private void updateRecoverPlanDetail(RecoverPlan recoverPlan, RecoverPlanSaveOrupdateVO updateVO, UserVO userVO) throws Exception {
        List<RecoverPlanDetail> recoverPlanDetailList = getRecoverPlanDetailList(updateVO, recoverPlan, true);

        //删除追回计划下的明细，重新生成新的明细
        int i = recoverPlanDetailMapper.deleteDetailWithPlanId(recoverPlan.getId());
        if (i== 0) {
            throw new BusinessException("删除追回计划明细错误！");
        }

        for (RecoverPlanDetail detail : recoverPlanDetailList) {
            recoverPlanDetailMapper.insertSelective(detail);
        }
    }

    /**
     * 判断追回计划是否已生成追回记录
     * @param loginUser
     * @param id
     * @return
     * @throws Exception
     */
    private boolean isDeleteRecallPlan(Long id, Long enterpriseId) {
        List<RecoverRecord> recoverRecords = recoverRecordMapper.selectWithRecoverPlanId(id, enterpriseId);
        return recoverRecords.isEmpty();
    }
}
