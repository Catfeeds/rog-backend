package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnNoticeService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeFormVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticePageVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeStasticVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DistrReturnNoticeServiceImpl implements DistrReturnNoticeService{

    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;

    @Autowired
    private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Override
    public Page getReturnNoticePage(Page page, Date startTime, Date endTime, String requestUnitCode, String requestUnitName, String code, Integer distrType, Integer status, String order, String sort, Long enterpriseId) {
        if("noticeDate".equals(order)){
            order = "notice_date";
        }else if("code".equals(order)){
            order = "code";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("requestUnitCode",requestUnitCode);
        map.put("requestUnitName",requestUnitName);
        map.put("code",code);
        map.put("distrType",distrType);
        map.put("status",status);
        map.put("order",order);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        //已完成的状态同时要查 已完成 + 待开票
        if (status != null){
            if (DistrInStatus.FINISHED == status){
            	List<Integer> statueList = new ArrayList<>();
            	statueList.add(DistrReturnStatus.FINISHED);
            	statueList.add(DistrReturnStatus.WAIT_BILL);
            	statueList.add(DistrReturnStatus.PART_BILL);
            	statueList.add(DistrReturnStatus.WAIT_SEND);
                map.put("statueList",statueList);
            }
        }
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        List<DistrReturnNotice> distrReturnNoticeList = distrReturnNoticeMapper.getReturnNoticePage(map);
        DistrReturnNoticeStasticVO stasticVO = distrReturnNoticeMapper.selectReturnNoticeStastic(map);
        List<DistrReturnNoticePageVO> pageVOList = new ArrayList<DistrReturnNoticePageVO>();
        for (DistrReturnNotice d:distrReturnNoticeList) {
            DistrReturnNoticePageVO vo = new DistrReturnNoticePageVO();
            vo = DistrReturnNoticePageVO.converToVO(d);
            if (stasticVO == null){
                vo.setStasticRealAmountTotal(BigDecimal.ZERO);
                vo.setStasticNotaxRealAmountTotal(BigDecimal.ZERO);
                vo.setStasticTaxAmountTotal(BigDecimal.ZERO);
            }else {
                vo.setStasticRealAmountTotal(stasticVO.getStasticRealAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticRealAmountTotal());
                vo.setStasticNotaxRealAmountTotal(stasticVO.getStasticNotaxRealAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticNotaxRealAmountTotal());
                vo.setStasticTaxAmountTotal(stasticVO.getStasticTaxAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticTaxAmountTotal());
            }
            pageVOList.add(vo);
        }
        page.setResult(pageVOList);
        Integer totalRecord = distrReturnNoticeMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public DistrReturnNoticeFormVO getReturnNoticeDeatil(Long enterpriseId, Long id) {
        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(id);
        DistrReturnNoticeFormVO formVO = new DistrReturnNoticeFormVO();
        formVO = DistrReturnNoticeFormVO.converToVO(distrReturnNotice);
        //查明细单
        List<DistrReturnNoticeDetail> detailList = distrReturnNoticeDetailMapper.selectByEnterpriseIdAndNoticeId(enterpriseId,id);
        List<DistrReturnNoticeDtlVO> returnNoticeDtlVOList = new ArrayList<DistrReturnNoticeDtlVO>();
        for (DistrReturnNoticeDetail d:detailList) {
            DistrReturnNoticeDtlVO vo = new DistrReturnNoticeDtlVO();
            vo = DistrReturnNoticeDtlVO.convertToVO(d);
            returnNoticeDtlVOList.add(vo);
        }
        formVO.setDistrReturnNoticeDtlVOList(returnNoticeDtlVOList);
        return formVO;
    }

    @Override
    public void export(OutputStream output, DistrReturnNoticeFormVO distrReturnNoticeFormVO, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");

        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("lineDiscount","折扣");

        map.put("amount","金额");
        map.put("taxRate","税率");
        map.put("notaxRealPrice","不含税单价");

        map.put("notaxRealAmount","不含税金额");
        map.put("taxAmount","税额");
        map.put("remark","备注");
        List<DistrReturnNoticeDtlVO> distrReturnNoticeDtlVOList = distrReturnNoticeFormVO.getDistrReturnNoticeDtlVOList();

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("要货单位编码:");
        titleSecondRow.append(distrReturnNoticeFormVO.getRequestUnitCode() == null ? "":distrReturnNoticeFormVO.getRequestUnitCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("要货单位名称:");
        titleSecondRow.append(distrReturnNoticeFormVO.getRequestUnitName() == null ? "":distrReturnNoticeFormVO.getRequestUnitName());
        titleSecondRow.append("     ");
        titleSecondRow.append("配后退回单号:");
        titleSecondRow.append(distrReturnNoticeFormVO.getCode() == null ? "":distrReturnNoticeFormVO.getCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("订单日期:");
        titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(distrReturnNoticeFormVO.getNoticeDate()));
        titleSecondRow.append("     ");
        titleSecondRow.append("配货类型:");
        String distrType = "";
        switch (distrReturnNoticeFormVO.getDistrType()){
            case 0:
                distrType = "总部配送";
                break;
            case 1:
                distrType = "分店间调剂";
                break;
            case 2:
                distrType = "直调配送";
                break;
            default:
        }
        titleSecondRow.append(distrType);
        titleSecondRow.append("     ");
        titleSecondRow.append("配进退出单号:");
        titleSecondRow.append(distrReturnNoticeFormVO.getBaseOrderCode() == null ? "":distrReturnNoticeFormVO.getBaseOrderCode());
        titleSecondRow.append("     ");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("配后退回单");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        StringBuilder sb = new StringBuilder();
        sb.append("总商品金额:   ");
        sb.append(distrReturnNoticeFormVO.getAmountTotal());
        sb.append(";");
        sb.append("折扣:   ");
        sb.append(distrReturnNoticeFormVO.getWholeDiscount());
        sb.append("%");
        sb.append("  ");
        sb.append(distrReturnNoticeFormVO.getAmountTotal().subtract(distrReturnNoticeFormVO.getAmountTotal().multiply(distrReturnNoticeFormVO.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
        sb.append(";");
        sb.append("优惠:");
        sb.append("   ");
        sb.append(distrReturnNoticeFormVO.getWholeDiscountAmount());
        sb.append(";");
        sb.append("总额:");
        sb.append("   ");
        sb.append(distrReturnNoticeFormVO.getRealAmountTotal());
        sb.append(";");
        sb.append("不含税总额:");
        sb.append("   ");
        sb.append(distrReturnNoticeFormVO.getNotaxRealAmountTotal());
        sb.append(";");
        sb.append("税额:");
        sb.append("   ");
        sb.append(distrReturnNoticeFormVO.getTaxAmountTotal());

        StringBuilder endTotal = new StringBuilder();
        BigDecimal totalQuantity = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
        BigDecimal totalTaxAmount = BigDecimal.ZERO;
        for (DistrReturnNoticeDtlVO d:distrReturnNoticeDtlVOList) {
            totalQuantity = totalQuantity.add(d.getQuantity());
            totalAmount = totalAmount.add(d.getAmount());
            totalNotaxRealAmount = totalNotaxRealAmount.add(d.getNotaxRealAmount());
            totalTaxAmount = totalTaxAmount.add(d.getTaxAmount());
        }
        endTotal.append(totalQuantity);
        endTotal.append(";");
        endTotal.append(totalAmount);
        endTotal.append(";");
        endTotal.append(totalNotaxRealAmount);
        endTotal.append(";");
        endTotal.append(totalTaxAmount);
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelDistrExport(output,map,distrReturnNoticeDtlVOList,name,secondTitle,sb.toString(),endTotal.toString(),locationList);
    }

    /**
     * 提供给保存的接口
     * @param distrReturnNoticeFormVO
     */
    @Override
    public void insert(DistrReturnNoticeFormVO distrReturnNoticeFormVO, UserVO loginUser) throws Exception{
        //构建主单
        DistrReturnNotice distrReturnNotice = new DistrReturnNotice();
        distrReturnNotice.setEnterpriseId(loginUser.getEnterpriseId());
        distrReturnNotice.setParentId(loginUser.getParentId());
        distrReturnNotice.setOrderType(OrderRule.DISTR_RETURN_NOTICE.getType());
        //生成的移动单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_RETURN_NOTICE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());
        distrReturnNotice.setCode(code);
        distrReturnNotice.setNoticeDate(new Date());
        //需要上游提供的数据以下#################################################
        distrReturnNotice.setBaseOrderId(distrReturnNoticeFormVO.getBaseOrderId());//--基础单据ID
        distrReturnNotice.setBaseOrderType(distrReturnNoticeFormVO.getBaseOrderType());//--基础单据类型
        distrReturnNotice.setBaseOrderCode(distrReturnNoticeFormVO.getBaseOrderCode());//--基础单据编码
        distrReturnNotice.setBaseOrderDate(distrReturnNoticeFormVO.getBaseOrderDate());//--基础单据日期
        distrReturnNotice.setRequestUnitId(distrReturnNoticeFormVO.getRequestUnitId());//--要货单位id
        distrReturnNotice.setRequestUnitCode(distrReturnNoticeFormVO.getRequestUnitCode());//--要货单位编码
        distrReturnNotice.setRequestUnitName(distrReturnNoticeFormVO.getRequestUnitName());//--要货单位名称
        distrReturnNotice.setDistrType(distrReturnNoticeFormVO.getDistrType());//配货类型（0-总部配送；3-分店间调剂；4-直调配送）
        distrReturnNotice.setQuantityTotal(distrReturnNoticeFormVO.getQuantityTotal());//数量合计
        //品种类型先置为0 我查看明细之后计算塞入
        distrReturnNotice.setVarietiesQuantity(0);//此项不需要上游来传
        distrReturnNotice.setAmountTotal(distrReturnNoticeFormVO.getAmountTotal());//金额合计（整单优惠前的金额合计）
        distrReturnNotice.setWholeDiscount(distrReturnNoticeFormVO.getWholeDiscount());//整单折扣（%）
        distrReturnNotice.setWholeDiscountAmount(distrReturnNoticeFormVO.getWholeDiscountAmount());//整单优惠金额
        distrReturnNotice.setRealAmountTotal(distrReturnNoticeFormVO.getRealAmountTotal());//实际金额合计
        distrReturnNotice.setNotaxRealAmountTotal(distrReturnNoticeFormVO.getNotaxRealAmountTotal());//不含税金额合计
        distrReturnNotice.setTaxAmountTotal(distrReturnNoticeFormVO.getTaxAmountTotal());//税额合计
        distrReturnNotice.setStatus(distrReturnNoticeFormVO.getStatus());//状态
        distrReturnNotice.setRemark(distrReturnNoticeFormVO.getRemark());//备注
        //需要上游数据提供以上################################################
        UserEnterpriseUtils.setUserCreateOrModify(distrReturnNotice,loginUser,true);
        distrReturnNoticeMapper.insertSelective(distrReturnNotice);
        //构建明细单插入
        List<DistrReturnNoticeDtlVO> distrReturnNoticeDtlVOList = distrReturnNoticeFormVO.getDistrReturnNoticeDtlVOList();
        Set set = new HashSet();//记录品种数量
        for (DistrReturnNoticeDtlVO d:distrReturnNoticeDtlVOList) {
            DistrReturnNoticeDetail detail = new DistrReturnNoticeDetail();
            detail.setEnterpriseId(loginUser.getEnterpriseId());
            detail.setParentId(loginUser.getParentId());
            detail.setNoticeId(distrReturnNotice.getId());
            detail.setOrderType(OrderRule.DISTR_RETURN_NOTICE.getType());
            detail.setNoticeCode(code);
            detail.setNoticeDate(new Date());
            //由上游提供的数据
            detail.setBaseOrderDtlId(d.getBaseOrderDtlId());//基础单据明细ID
            detail.setBaseOrderId(distrReturnNoticeFormVO.getBaseOrderId());
            detail.setBaseOrderType(distrReturnNoticeFormVO.getBaseOrderType());
            detail.setBaseOrderCode(distrReturnNoticeFormVO.getBaseOrderCode());
            detail.setBaseOrderDate(distrReturnNoticeFormVO.getBaseOrderDate());
            detail.setGoodsId(d.getGoodsId());//商品ID
            set.add(d.getGoodsId());
            Goods goods = goodsMapper.selectByPrimaryKey(d.getGoodsId());
            if (goods != null){
                detail.setGoodsCode(goods.getCode());
                detail.setBarcode(goods.getBarcode());
                detail.setGoodsName(goods.getName());
                detail.setGoodsGenericName(goods.getGenericName());
                detail.setDosageId(goods.getDosageId());
                detail.setDosageName(goods.getDosageName());
                detail.setUnitId(goods.getUnitId());
                detail.setUnitName(goods.getUnitName());
                detail.setGoodsSpecification(goods.getSpecification());
                detail.setManufacturerId(goods.getManufacturerId());
                detail.setManufacturer(goods.getManufacturer());
                detail.setGoodsPlace(goods.getPlace());
                detail.setApprovalNumber(goods.getApprovalNumber());
            }
            detail.setQuantity(d.getQuantity());//数量
            detail.setUnitPrice(d.getUnitPrice());//单价
            detail.setLineDiscount(d.getLineDiscount());//行折扣（%）
            detail.setAmount(d.getAmount());//金额（整单优惠前金额）
            detail.setWholeDiscount(distrReturnNoticeFormVO.getWholeDiscount());
            detail.setLineDiscountAmount(d.getLineDiscountAmount());//行优惠（整单优惠分摊到行的金额）
            detail.setRealPrice(d.getRealPrice());//实际单价（实际金额/数量）
            detail.setRealAmount(d.getRealAmount());//实际金额
            detail.setTaxRateId(d.getTaxRateId());//税率ID
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(d.getTaxRateId());
            detail.setTaxRate(goodsTaxRate.getTaxRate());
            detail.setNotaxRealPrice(d.getNotaxRealPrice());//不含税实际单价
            detail.setNotaxRealAmount(d.getNotaxRealAmount());//不含税实际金额
            detail.setTaxAmount(d.getTaxAmount());//税额
            //先设置未清数量为当前数量--已清数量为0
            detail.setUnclearQuantity(d.getQuantity());
            detail.setClearQuantity(BigDecimal.ZERO);
            detail.setLineNum(d.getLineNum());//行号
            detail.setStatus(distrReturnNoticeFormVO.getStatus());
            detail.setRemark(d.getRemark());//备注
            UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
            distrReturnNoticeDetailMapper.insertSelective(detail);
        }
        //修正总单的品种数量
        DistrReturnNotice newDistrReturnNotice = new DistrReturnNotice();
        newDistrReturnNotice.setId(distrReturnNotice.getId());
        newDistrReturnNotice.setVarietiesQuantity(set.size());
        distrReturnNoticeMapper.updateByPrimaryKeySelective(newDistrReturnNotice);
    }
}
