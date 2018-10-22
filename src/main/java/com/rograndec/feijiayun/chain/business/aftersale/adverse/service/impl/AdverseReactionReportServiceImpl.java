package com.rograndec.feijiayun.chain.business.aftersale.adverse.service.impl;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.constant.FirstReportType;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.constant.ReportType;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.constant.ReportUnitType;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.dao.AdverseReactionDoubtGoodsMapper;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.dao.AdverseReactionReportMapper;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.dao.AdverseReactionTogetherGoodsMapper;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.entity.AdverseReactionDoubtGoods;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.entity.AdverseReactionReport;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.entity.AdverseReactionTogetherGoods;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.service.AdverseReactionReportService;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.dao.NationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SexType;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class AdverseReactionReportServiceImpl implements AdverseReactionReportService {


    @Autowired
    private AdverseReactionReportMapper reportMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private AdverseReactionDoubtGoodsMapper doubtGoodsMapper;

    @Autowired
    private AdverseReactionTogetherGoodsMapper togetherGoodsMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private NationMapper nationMapper;


    @Override
    public Page getPageList(ReportRequestVO requestVO, UserVO loginUser) {

        requestVO.setEnterpriseId(loginUser.getEnterpriseId());

        if ("date".equals(requestVO.getOrder())) {
            requestVO.setOrder("report_time");
        }

        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);

        requestVO.setStart(start);
        requestVO.setEnd(end);

        Page<List<ReportPageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());

        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        requestVO.setPageStart(page.getStart());

        List<ReportPageVO> dataList = reportMapper.selectReportPage(requestVO);
        Integer totalCount =  reportMapper.selectReportPageCount(requestVO);


        page.setResult(dataList);
        page.setTotalRecord(totalCount);

        return page;

    }

    @Override
    public void save(ReportVO reportVO, UserVO loginUser) throws Exception {


        AdverseReactionReport report = (AdverseReactionReport) EntityUtils.reflectAddSetDefaultValue(AdverseReactionReport.class, loginUser);
        BeanUtils.copyProperties(reportVO, report);
        report.setEnterpriseId(loginUser.getEnterpriseId());
        report.setParentId(loginUser.getParentId());
        report.setReportDate(new Date());
        report.setOrderType(OrderRule.ADVERSE_REACTION.getType());//
        String code = orderCodeComponent.generate(OrderRule.ADVERSE_REACTION.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());// 单号
        report.setCode(code);
        report.setStatus(EnableStatus.ENABLE.getStatus());

        reportMapper.insertSelective(report);

        // 怀疑药品列表
        List<ReportGoodsVO> doubtGoodsVOList = reportVO.getDoubtGoodsVOList();
        for (ReportGoodsVO goodsVO : doubtGoodsVOList) {
            AdverseReactionDoubtGoods doubtGoods = (AdverseReactionDoubtGoods) EntityUtils.reflectAddSetDefaultValue(AdverseReactionDoubtGoods.class, loginUser);
            BeanUtils.copyProperties(goodsVO, doubtGoods);

            buildDoubtGoods(doubtGoods);
            doubtGoods.setStatus(EnableStatus.ENABLE.getStatus());

            doubtGoods.setReportId(report.getId());
            doubtGoodsMapper.insertSelective(doubtGoods);
        }


        // 并用药品列表
        List<ReportGoodsVO> togetherGoodsVOList = reportVO.getTogetherGoodsVOList();

        for (ReportGoodsVO goodsVO : togetherGoodsVOList) {
            AdverseReactionTogetherGoods togetherGoods = (AdverseReactionTogetherGoods) EntityUtils.reflectAddSetDefaultValue(AdverseReactionTogetherGoods.class, loginUser);
            BeanUtils.copyProperties(goodsVO, togetherGoods);
            buildTogetherGoods(togetherGoods);
            togetherGoods.setStatus(EnableStatus.ENABLE.getStatus());
            togetherGoods.setReportId(report.getId());
            togetherGoodsMapper.insertSelective(togetherGoods);
        }

    }

    private void buildTogetherGoods(AdverseReactionTogetherGoods togetherGoods) {
        Goods goods = goodsMapper.selectByPrimaryKey(togetherGoods.getGoodsId());
        if (goods == null) {
            throw new BusinessException("当前商品【id=" + togetherGoods.getGoodsId() + "】信息不存在，请检查.");
        }
        togetherGoods.setGoodsCode(goods.getCode());
        togetherGoods.setApprovalNumber(goods.getApprovalNumber());// 批准文号
        togetherGoods.setUnitId(goods.getUnitId());
        togetherGoods.setUnitName(goods.getUnitName());
        togetherGoods.setDosageId(goods.getDosageId());
        togetherGoods.setDosageName(goods.getDosageName());
        togetherGoods.setBarcode(goods.getBarcode());// 条形码
        togetherGoods.setGoodsName(goods.getName());
        togetherGoods.setGoodsGenericName(goods.getGenericName());
        togetherGoods.setGoodsSpecification(goods.getSpecification());
        togetherGoods.setManufacturer(goods.getManufacturer());// 生产厂商
        togetherGoods.setManufacturerId(goods.getManufacturerId());
        togetherGoods.setGoodsPlace(goods.getPlace());// 产地
    }


    private void buildDoubtGoods(AdverseReactionDoubtGoods doubtGoods) {
        Goods goods = goodsMapper.selectByPrimaryKey(doubtGoods.getGoodsId());
        if (goods == null) {
            throw new BusinessException("当前商品【id=" + doubtGoods.getGoodsId() + "】信息不存在，请检查.");
        }
        doubtGoods.setGoodsCode(goods.getCode());
        doubtGoods.setApprovalNumber(goods.getApprovalNumber());// 批准文号
        doubtGoods.setUnitId(goods.getUnitId());
        doubtGoods.setUnitName(goods.getUnitName());
        doubtGoods.setDosageId(goods.getDosageId());
        doubtGoods.setDosageName(goods.getDosageName());
        doubtGoods.setBarcode(goods.getBarcode());// 条形码
        doubtGoods.setGoodsName(goods.getName());
        doubtGoods.setGoodsGenericName(goods.getGenericName());
        doubtGoods.setGoodsSpecification(goods.getSpecification());
        doubtGoods.setManufacturer(goods.getManufacturer());// 生产厂商
        doubtGoods.setManufacturerId(goods.getManufacturerId());
        doubtGoods.setGoodsPlace(goods.getPlace());// 产地
    }

    @Override
    public void delete(Long id) {
        reportMapper.deleteByPrimaryKey(id);
        doubtGoodsMapper.deleteByReportId(id);
        togetherGoodsMapper.deleteByReportId(id);
    }

    @Override
    public void update(ReportVO reportVO, UserVO loginUser) {

        AdverseReactionReport report = new AdverseReactionReport();
        BeanUtils.copyProperties(reportVO, report);
        // 处理商品,修改时可能会删除商品或者新增商品
        // 0.筛选删除的商品
        // 怀疑商品列表
        List<ReportGoodsVO> doubtGoodsList = doubtGoodsMapper.selectByReportId(report.getId());
        // 并用商品列表
        List<ReportGoodsVO> togetherGoodsList = togetherGoodsMapper.selectByReportId(report.getId());

        List<ReportGoodsVO> doubtGoodsVOList = reportVO.getDoubtGoodsVOList();
        List<ReportGoodsVO> togetherGoodsVOList = reportVO.getTogetherGoodsVOList();

        doubtGoodsList.removeAll(doubtGoodsVOList);// 删除的怀疑商品
        togetherGoodsList.removeAll(togetherGoodsVOList);// 删除的并用商品
        // 1.删除商品
        doubtGoodsList.forEach(p -> {
            doubtGoodsMapper.deleteByPrimaryKey(p.getId());
        });

        togetherGoodsList.forEach(p -> {
            togetherGoodsMapper.deleteByPrimaryKey(p.getId());
        });
        // 2.更新或新增商品明细
        doubtGoodsVOList.forEach(p -> {
            if (p.getId() == null) {// 新增
                AdverseReactionDoubtGoods doubtGoods = null;
                try {
                    doubtGoods = (AdverseReactionDoubtGoods) EntityUtils.reflectAddSetDefaultValue(AdverseReactionDoubtGoods.class, loginUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BeanUtils.copyProperties(p, doubtGoods);

                buildDoubtGoods(doubtGoods);
                doubtGoods.setStatus(EnableStatus.ENABLE.getStatus());

                doubtGoods.setReportId(report.getId());
                doubtGoodsMapper.insertSelective(doubtGoods);

            } else { // 修改

                AdverseReactionDoubtGoods doubtGoods = doubtGoodsMapper.selectByPrimaryKey(p.getId());

                if (!p.getGoodsId().equals(doubtGoods.getGoodsId())) {
                    doubtGoods.setGoodsId(p.getGoodsId());
                    buildDoubtGoods(doubtGoods);
                }

                doubtGoods.setUsageDosage(p.getUsageDosage());
                doubtGoods.setLotNumber(p.getLotNumber());
                doubtGoods.setUpdateTime(new Date());
                doubtGoods.setModifierId(loginUser.getUserId());
                doubtGoods.setModifierCode(loginUser.getUserCode());
                doubtGoods.setModifierName(loginUser.getUserName());
                doubtGoodsMapper.updateByPrimaryKeySelective(doubtGoods);


            }
        });

        togetherGoodsVOList.forEach(p -> {
            if (p.getId() == null) {// 新增
                AdverseReactionTogetherGoods togetherGoods = null;
                try {
                    togetherGoods = (AdverseReactionTogetherGoods) EntityUtils.reflectAddSetDefaultValue(AdverseReactionTogetherGoods.class, loginUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                BeanUtils.copyProperties(p, togetherGoods);
                buildTogetherGoods(togetherGoods);
                togetherGoods.setStatus(EnableStatus.ENABLE.getStatus());
                togetherGoods.setReportId(report.getId());
                togetherGoodsMapper.insertSelective(togetherGoods);
            } else { // 修改


                AdverseReactionTogetherGoods togetherGoods = togetherGoodsMapper.selectByPrimaryKey(p.getId());

                if (!p.getGoodsId().equals(togetherGoods.getGoodsId())) {// 商品ID不相等
                    togetherGoods.setGoodsId(p.getGoodsId());
                    buildTogetherGoods(togetherGoods);
                }

                togetherGoods.setLotNumber(p.getLotNumber());
                togetherGoods.setUsageDosage(p.getUsageDosage());

                togetherGoods.setUpdateTime(new Date());
                togetherGoods.setModifierId(loginUser.getUserId());
                togetherGoods.setModifierCode(loginUser.getUserCode());
                togetherGoods.setModifierName(loginUser.getUserName());
                togetherGoodsMapper.updateByPrimaryKeySelective(togetherGoods);
            }
        });

        report.setUpdateTime(new Date());
        report.setModifierCode(loginUser.getUserCode());
        report.setModifierId(loginUser.getUserId());
        report.setModifierName(loginUser.getUserName());
        reportMapper.updateByPrimaryKeySelective(report);


    }

    @Override
    public void export(Long id, OutputStream output, UserVO loginUser) throws Exception {

        ReportVO reportVO = this.get(id);

        ExcelWriter writer = new ExcelWriter() {
            @Override
            public void generate() throws Exception {

                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();

                int num = 0;
                createHeader(num++, this, loginUser.getEnterpriseName());
                createHeader(num++, this, "不良反应报告");
                createHeader(num++, this, "");


                createRow(num++, this, "是否首次报告," + FirstReportType.getValue(reportVO.getFirstReport())
                        + ",报告类型," + ReportType.getValue(reportVO.getReportType())
                        + ",报告单位类别," + ReportUnitType.getValue(reportVO.getReportUnitType())
                        + ",编码," + reportVO.getReportCode()
                );

                createRow(num++, this, "患者姓名," + reportVO.getPatientName()
                        + ",性别," + SexType.getSexType4Code(reportVO.getSex()).getName()
                        + ",出生日期," + DateUtils.DateToString(reportVO.getBirthday(), DateUtils.FMT_DATE)
                        + ",年龄," + reportVO.getAge()
                );


                Nation nation = nationMapper.selectByPrimaryKey(reportVO.getNationId());


                createRow(num++, this, "民族," + nation.getName()
                        + ",体重（KG）," + reportVO.getWeight()
                        + ",联系方式," + reportVO.getTelephone()
                        + ",原患疾病," + reportVO.getPrimaryDisease()
                );
                createRow(num++, this, "医院名称," + reportVO.getHospitalName()
                        + ",病历号/门诊号," + reportVO.getOutpatientCode()
                        + ",既往药品不良反应," + reportVO.getHistoryAdverseReaction()
                        + ",家族药品不良反应," + reportVO.getFamilyAdverseReaction()
                );
                createRow(num++, this, "相关重要信息," + reportVO.getImportantInfo()
                        + ",不良反应名称," + reportVO.getAdverseReactionName()
                        + ",不良反应发生时间," + DateUtils.DateToString(reportVO.getAdverseReactionTime(), DateUtils.FMT_DATE_TIME)
                        + ",不良反应结果," + reportVO.getAdverseReactionResult()
                );
                createRow(num++, this, "报告日期," + DateUtils.DateToString(reportVO.getReportTime(), DateUtils.FMT_DATE)
                        + ",后遗症表现," + reportVO.getSequelaePerformance()
                        + ",直接死因," + reportVO.getDirectDeathReason()
                        + ",死亡时间," + DateUtils.DateToString(reportVO.getDeathTime(), DateUtils.FMT_DATE_TIME)
                );
                createRow(num++, this, "药品,通用名称/剂型/规格/单位,,生产厂商,批准文号,生产批号,用法用量(次剂量、途径、日次数)");
                List<ReportGoodsVO> doubtGoodsVOList = reportVO.getDoubtGoodsVOList();

                for (int i = 0; i < doubtGoodsVOList.size(); i++) {
                    ReportGoodsVO goodsVO = doubtGoodsVOList.get(i);
                    createRow(num++, this, "怀疑药品," + goodsVO.getGoodsGenericName() + "/" + goodsVO.getDosageName() + "/" + goodsVO.getGoodsSpecification() + "/" + goodsVO.getUnitName()
                            + ",," + goodsVO.getManufacturer()
                            + "," + goodsVO.getApprovalNumber()
                            + "," + goodsVO.getLotNumber()
                            + "," + goodsVO.getUsageDosage()
                    );
                }
                List<ReportGoodsVO> togetherGoodsVOList = reportVO.getTogetherGoodsVOList();

                for (int i = 0; i < togetherGoodsVOList.size(); i++) {
                    ReportGoodsVO goodsVO = togetherGoodsVOList.get(i);
                    createRow(num++, this, "并用药品," + goodsVO.getGoodsGenericName() + "/" + goodsVO.getDosageName() + "/" + goodsVO.getGoodsSpecification() + "/" + goodsVO.getUnitName()
                            + ",," + goodsVO.getManufacturer()
                            + "," + goodsVO.getApprovalNumber()
                            + "," + goodsVO.getLotNumber()
                            + "," + goodsVO.getUsageDosage()
                    );
                }

                createRow(num++, this, "用药起止时间," + DateUtils.DateToString(reportVO.getMedicineStartTime(), DateUtils.FMT_DATE)
                        + "," + DateUtils.DateToString(reportVO.getMedicineStopTime(), DateUtils.FMT_DATE)
                        + ",用药原因," + reportVO.getMedicineReason()
                );

                createRow(num++, this, "停药或减量后，反应是否消失或减轻,," + reportVO.getReactionReduce()
                        + ",再次使用可疑药品后是否再次出现同样反应," + reportVO.getAgainSameReaction()
                        + ",,对原还疾病的影响," + reportVO.getEffectPrimaryDisease()
                );

                createRow(num++, this, "报告人评价," + reportVO.getReportManEvaluate()
                );
                createRow(num++, this, "报告人电话," + reportVO.getReportManPhone()
                        + ",报告人执业," + reportVO.getReportManVocation()
                        + ",报告人邮箱," + reportVO.getReportManEmail()
                        + ",报告人姓名," + reportVO.getReportManName()
                );
                createRow(num++, this, "报告单位评价," + reportVO.getReportUnitEvaluate()
                );
                createRow(num++, this, "报告单位名称," + reportVO.getReportUnitName()
                        + ",联系人," + reportVO.getReportUnitMan()
                        + ",电话," + reportVO.getReportUnitPhone()
                );
                createRow(num++, this, "生产企业请填写信息来源,," + reportVO.getInfoSrc()
                        + ",,备注," + reportVO.getRemark()
                );
                createRow(num, this, "不良反应过程描述(包括症状、体征、临床检验等)及处理情况(可附页)," + reportVO.getAdverseReactionDesc());

                // 电子表格结束
                this.endSheet();

                //合并单元格
                this.beginMergerCell();

                // 头三行
                this.setMergeCell(0, 0, 0, 7);
                this.setMergeCell(1, 0, 1, 7);
                this.setMergeCell(2, 0, 2, 7);
                // 药品
                this.setMergeCell(9, 1, 9, 2);
                this.setMergeCell(9, 6, 9, 7);

                // 药品行
                int doubtSize = doubtGoodsVOList.size();
                int togetherSize = togetherGoodsVOList.size();
                for (int i = 0; i < doubtSize + togetherSize; i++) {
                    this.setMergeCell(10 + i, 1, 10 + i, 2);
                    this.setMergeCell(10 + i, 6, 10 + i, 7);
                }

                this.setMergeCell(10, 0, 9 + doubtSize, 0);
                this.setMergeCell(10 + doubtSize, 0, 9 + doubtSize + togetherSize, 0);

                //用药起止时间
                this.setMergeCell(num - 7, 4, num - 7, 7);
                //停药或减量后,反应是否消失或减轻
                this.setMergeCell(num - 6, 0, num - 6, 1);
                this.setMergeCell(num - 6, 4, num - 6, 5);
                // 报告人评价
                this.setMergeCell(num - 5, 1, num - 5, 7);
                //报告单位评价
                this.setMergeCell(num - 3, 1, num - 3, 7);
                //报告单位名称
                this.setMergeCell(num - 2, 1, num - 2, 3);

                this.setMergeCell(num - 1, 0, num - 1, 1);
                this.setMergeCell(num - 1, 2, num - 1, 3);
                this.setMergeCell(num - 1, 5, num - 1, 7);

                this.setMergeCell(num, 0, num, 4);
                this.setMergeCell(num, 5, num, 7);

                this.endMergerCell();
                this.endWorkSheet();
            }


        };

        writer.process(output);


    }

    private void createHeader(int number, ExcelWriter excelWriter, String s) throws IOException {
        excelWriter.insertRow(number);

        Map<String, XSSFCellStyle> styleMap = excelWriter.getCellStyles();

        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            excelWriter.createCell(i, split[i], styleMap.get("cell_string").getIndex());
        }

        excelWriter.endRow();
    }


    private void createRow(int number, ExcelWriter excelWriter, String s) throws IOException {
        excelWriter.insertRow(number);

        Map<String, XSSFCellStyle> styleMap = excelWriter.getCellStyles();
        XSSFCellStyle cellStyle = styleMap.get("cell_string_left");

        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            excelWriter.createCell(i, "null".equals(split[i]) ? "" : split[i], cellStyle.getIndex());
        }

        excelWriter.endRow();
    }

    @Override
    public ReportVO get(Long id) {

        ReportVO reportVO = new ReportVO();
        AdverseReactionReport report = reportMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(report, reportVO);
        if (report.getFileId() != null) {
            File file = fileMapper.selectByPrimaryKey(report.getFileId());
            if (file != null) {
                reportVO.setFileName(file.getFileName());
            }
        }

        if(report.getNationId() != null){
            Nation nation = nationMapper.selectByPrimaryKey(reportVO.getNationId());
            if(nation != null){
                reportVO.setNationName(nation.getName());
            }

        }

        // 怀疑商品列表
        List<ReportGoodsVO> doubtGoodsVOList = doubtGoodsMapper.selectByReportId(id);
        // 并用商品列表
        List<ReportGoodsVO> togetherGoodsVOList = togetherGoodsMapper.selectByReportId(id);

        reportVO.setDoubtGoodsVOList(doubtGoodsVOList);
        reportVO.setTogetherGoodsVOList(togetherGoodsVOList);
        return reportVO;

    }

    @Override
    public List<GoodsReportVO> getGoods(Map<String, Object> map) {
        return reportMapper.selectGoods(map);
    }
}
