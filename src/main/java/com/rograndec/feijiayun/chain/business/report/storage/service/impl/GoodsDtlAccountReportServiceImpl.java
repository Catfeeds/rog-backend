package com.rograndec.feijiayun.chain.business.report.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.report.storage.service.GoodsDtlAccountReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.GoodsDtlReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.InOutRecordDetailReportVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl.RequestParamGoodsDtlReportVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.CostInfoPostVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能描述：
 * Created by ST on 2017/10/26 14:34
 */
@Service
public class GoodsDtlAccountReportServiceImpl implements GoodsDtlAccountReportService {

    @Autowired
    private InOutRecordDetailMapper inOutRecordDetailMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void getGoodsDtlReportList(Page<List<GoodsDtlReportVO>> page, RequestParamGoodsDtlReportVO paramVO, UserVO userVO){
        Long enterpriseId = paramVO.getEnterpriseId();
        if(enterpriseId == null){
            enterpriseId = userVO.getEnterpriseId();
        }
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if(enterprise == null){
            throw new BusinessException("企业不存在");
        }
        String enterpriseName = enterprise.getName();
        String period = StringUtil.transferTrimStr(paramVO.getStartDate()) + "至" + StringUtil.transferTrimStr(paramVO.getEndDate());
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<GoodsDtlReportVO> goodsDtlReportList = getGoodsDtlReportVOS(paramVO, enterpriseId, enterpriseName, period);
        page.setResult(goodsDtlReportList);
        page.setTotalRecord((int) objects.getTotal());
    }

    private List<GoodsDtlReportVO> getGoodsDtlReportVOS(RequestParamGoodsDtlReportVO paramVO, Long enterpriseId, String enterpriseName, String period) {
        List<GoodsDtlReportVO> goodsDtlReportList = inOutRecordDetailMapper.getGoodsDtlReportList(enterpriseId, paramVO);
        goodsDtlReportList.stream().forEach((GoodsDtlReportVO item) ->{
            item.setEnterpriseName(enterpriseName);
            item.setPeriod(period);
            //商品id
//            Long goodsId = item.getGoodsId();
//            List<InOutRecordDetailReportVO> inOutRecordList = inOutRecordDetailMapper.getInOutRecordList(enterpriseId, paramVO, goodsId);
//            if(!CollectionUtils.isEmpty(inOutRecordList)){
//                BigDecimal inQuantityTotal = inOutRecordList.stream().filter(Objects::nonNull)
//                        .filter(c->c.getInQuantity() != null)
//                        .map(InOutRecordDetailReportVO::getInQuantity)
//                        .reduce(BigDecimal.ZERO,BigDecimal::add);
//                item.setInQuantityTotal(inQuantityTotal);
//
//                BigDecimal outQuantityTotal = inOutRecordList.stream().filter(Objects::nonNull)
//                        .filter(c->c.getOutQuantity() != null)
//                        .map(InOutRecordDetailReportVO::getOutQuantity)
//                       .reduce(BigDecimal.ZERO,BigDecimal::add);
//                item.setOutQuantityTotal(outQuantityTotal);
//
//                BigDecimal storageBalance = inOutRecordList.get(inOutRecordList.size() - 1).getStorageBalance();
//                item.setStorageBalance(storageBalance);
//            }
//
//            item.setInOutRecordDetailReportVOS(inOutRecordList);
        });
        return goodsDtlReportList;
    }



    @Override
    public void getInOutRecordDetailReportVOList(UserVO userVO, RequestParamGoodsDtlReportVO reqParam, Page<InOutRecordDetailReportPageVO> page) {


        Long enterpriseId = reqParam.getEnterpriseId();
        if(enterpriseId == null){
            enterpriseId = userVO.getEnterpriseId();
        }
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if(enterprise == null){
            throw new BusinessException("企业不存在");
        }

        Map<String,Object> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("goodsId",reqParam.getGoodsId());
        param.put("dateOrder",reqParam.getDateOrder() == 0?"desc":"asc");
        param.put("startDate",reqParam.getStartDate());
        param.put("endDate",reqParam.getEndDate());
        param.put("pageStart",page.getStart());
        param.put("pageSize",page.getPageSize());

        //商品id
        InOutRecordDetailReportPageVO pageVO = new InOutRecordDetailReportPageVO();

        // 查询明细记录
        List<InOutRecordDetailReportVO> inOutRecordList = inOutRecordDetailMapper.getInOutRecordList(param);

        pageVO.setInOutRecordDetailReportVOS(inOutRecordList);
        // 查询数量
        Integer count = inOutRecordDetailMapper.getInOutDetailCountByParam(param);

        // 查询出入 总和

        param.put("direction",0);// 0 入库

        CostInfoPostVO inTotal = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);
        param.put("direction",1);// 1 出库

        CostInfoPostVO outTotal = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);


        pageVO.setInQuantityTotal(inTotal == null? BigDecimal.ZERO : inTotal.getQuantityTotal());
        pageVO.setOutQuantityTotal(outTotal == null?BigDecimal.ZERO : outTotal.getQuantityTotal());

        // 查询结余
        InOutRecordDetailReportVO lastReport =   inOutRecordDetailMapper.selectLast(param);

        if(lastReport != null){
            pageVO.setStorageBalance(lastReport.getStorageBalance());
        }

        page.setResult(pageVO);
        page.setTotalRecord(Integer.parseInt(count + ""));

    }





    @Override
    public void exportGoodsDtlReport(OutputStream output, UserVO userVO, RequestParamGoodsDtlReportVO paramVO) throws Exception {

        Long enterpriseId = paramVO.getEnterpriseId();
        if(enterpriseId == null){
            enterpriseId = userVO.getEnterpriseId();
        }
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if(enterprise == null){
            throw new BusinessException("企业不存在");
        }
        String enterpriseName = enterprise.getName();
        String period = StringUtil.transferTrimStr(paramVO.getStartDate()) + "至" + StringUtil.transferTrimStr(paramVO.getEndDate());


        Map<String,Object> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("goodsId",paramVO.getGoodsId());
        param.put("dateOrder",paramVO.getDateOrder() == 0?"desc":"asc");

        param.put("startDate",paramVO.getStartDate());
        param.put("endDate",paramVO.getEndDate());


        Goods goods = goodsMapper.selectByPrimaryKey(paramVO.getGoodsId());

        List<InOutRecordDetailReportVO> inOutRecordList = inOutRecordDetailMapper.getInOutRecordList(param);

        param.put("direction",0);// 0 入库

        CostInfoPostVO inTotal = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);
        param.put("direction",1);// 1 出库

        CostInfoPostVO outTotal = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        // 查询结余
        InOutRecordDetailReportVO lastReport =   inOutRecordDetailMapper.selectLast(param);


        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {

                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, StringUtil.transferTrimStr(enterpriseName), cellStrIndex);
                this.endRow();
                //第二行
                this.insertRow(kk++);
                this.createCell(0, "库存明细", cellStrIndex);
                this.endRow();
//                //第三行
                this.insertRow(kk++);
                this.createCell(0, "组织机构", cellStrIndex);
                this.createCell(1, "期间", cellStrIndex);
                this.createCell(2, "商品编码", cellStrIndex);
                this.createCell(3, "通用名称", cellStrIndex);
                this.createCell(4, "剂型", cellStrIndex);
                this.createCell(5, "规格", cellStrIndex);
                this.createCell(7, "生产厂商", cellStrIndex);
                this.createCell(8, "单位", cellStrIndex);
                this.endRow();


                //
                int tk = 0;
                tk = kk++;
                this.insertRow(tk);
                this.createCell(0, StringUtil.transferTrimStr(enterprise.getName()), cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(period), cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(goods.getCode()), cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(goods.getGenericName()), cellStrIndex);
                this.createCell(4, StringUtil.transferTrimStr(goods.getDosageName()), cellStrIndex);
                this.createCell(5, StringUtil.transferTrimStr(goods.getSpecification()), cellStrIndex);
                this.createCell(7, StringUtil.transferTrimStr(goods.getManufacturer()), cellStrIndex);
                this.createCell(8, StringUtil.transferTrimStr(goods.getUnitName()), cellStrIndex);
                this.endRow();

                int vk = tk + 2;
                this.insertRow(vk);
                this.createCell(0, "日期", cellStrIndex);
                this.createCell(2, "类型", cellStrIndex);
                this.createCell(3, "单号", cellStrIndex);
                this.createCell(4, "批号", cellStrIndex);
                this.createCell(5, "货位", cellStrIndex);
                this.createCell(6, "收入", cellStrIndex);
                this.createCell(7, "发出", cellStrIndex);
                this.createCell(8, "结存", cellStrIndex);
                this.endRow();
                List<InOutRecordDetailReportVO> inOutRecordDetailReportVOS = inOutRecordList;

                int size = inOutRecordDetailReportVOS.size();
                for (int j = 0; j < size; j++) {
                    InOutRecordDetailReportVO inOutRecordDetailReportVO = inOutRecordDetailReportVOS.get(j);
                    this.insertRow(vk + 1 + j);
                    this.createCell(0, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getOrderDate()), cellStrIndex);
                    this.createCell(2, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getOrderTypeName()), cellStrIndex);
                    this.createCell(3, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getOrderCode()), cellStrIndex);
                    this.createCell(4, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getLotNum()), cellStrIndex);
                    this.createCell(5, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getShelfName()), cellStrIndex);
                    this.createCell(6, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getInQuantity()), cellStrIndex);
                    this.createCell(7, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getOutQuantity()), cellStrIndex);
                    this.createCell(8, StringUtil.transferTrimStr(inOutRecordDetailReportVO.getStorageBalance()), cellStrIndex);
                    this.endRow();
                }
                this.insertRow(vk + size + 1);
                this.createCell(2, "本期合计", cellStrIndex);
                this.createCell(6, StringUtil.transferTrimStr(inTotal == null? BigDecimal.ZERO:inTotal.getQuantityTotal()), cellStrIndex);
                this.createCell(7, StringUtil.transferTrimStr(outTotal == null? BigDecimal.ZERO:outTotal.getQuantityTotal()), cellStrIndex);
                this.endRow();
                this.insertRow(vk + size + 2);
                this.createCell(2, "期末结余", cellStrIndex);
                this.createCell(8, StringUtil.transferTrimStr(lastReport.getStorageBalance()), cellStrIndex);
                this.endRow();
                tk += size + 1 + 1 + 1 + 1;


                // sheet表格结束
                this.endSheet();

//                合并单元格
                this.beginMergerCell();
                this.setMergeCell(0, 0, 0, 8);
                this.setMergeCell(1, 0, 1, 8);

                this.setMergeCell(2, 5, 2, 6);
//                int mk = 3;
//                for (int i = 0; i < goodsDtlReportList.size(); i++) {
//                    GoodsDtlReportVO goodsDtlReportVO = goodsDtlReportList.get(i);
//
//                    this.setMergeCell(mk, 5, mk, 6);
//                    List<InOutRecordDetailReportVO> inOutRecordDetailReportVOS = goodsDtlReportVO.getInOutRecordDetailReportVOS();
//                    int size = inOutRecordDetailReportVOS.size();
//                    int nk = mk + 1;
//                    this.setMergeCell(nk, 0, nk, 1);
//                    for (int j = 0; j < size; j++) {
//                        this.setMergeCell(nk + 1 + j, 0, nk + 1 + j, 1);
//                    }
//                    this.setMergeCell(nk + size + 1, 0, nk + size + 1, 1);
//                    this.setMergeCell(nk + size + 2, 0, nk + size + 2, 1);
//                    mk += size + 1 + 1 + 1 + 1;
//                }
                this.endMergerCell();
                this.endWorkSheet();


            }
        };
        writer.process(output);


    }


}
