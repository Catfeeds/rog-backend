package com.rograndec.feijiayun.chain.business.report.retail.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.report.retail.dao.SaleAnalysisReportGroupMapper;
import com.rograndec.feijiayun.chain.business.report.retail.service.ReportSaleGroupService;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportSaleAnalysisTotalVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportSaleDetailVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.ReportTeamSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestCabinetSaleVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.RequestTeamSaleVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/24 <br/>
 * 描述：销售分析报表-柜组/班组
 */
@Service
public class ReportSaleGroupServiceImpl implements ReportSaleGroupService {

    @Autowired
    private SaleAnalysisReportGroupMapper saleAnalysisReportGroupMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page<ReportSaleAnalysisTotalVO<ReportCabinetSaleVO>> getCabinetSaleList(RequestCabinetSaleVO requestVO, Page page, UserVO userVO) throws Exception {
        if (requestVO.getType() == null) {
            throw new BusinessException("搜索类型不能为空，请检查传参！");
        }

        String sort = "";
        if (requestVO.getType() == 1) {//销售日期搜索
            if (requestVO.getSortSale() != null) {
                if (requestVO.getSortSale() == 0) {
                    sort += "sale.sale_date,";
                } else if (requestVO.getSortSale() == 1) {
                    sort += "sale.sale_date desc,";
                }
            }
        } else {
            if (requestVO.getSortDaily() != null) {
                if (requestVO.getSortDaily() == 0) {
                    sort += "sale.daily_time,";
                } else if (requestVO.getSortDaily() == 1) {
                    sort += "sale.daily_time desc,";
                }
            }
        }

        //门店编码排序
        if (requestVO.getSortCode() != null) {
            if (requestVO.getSortCode() == 0) {
                sort += "se.code,";
            } else if (requestVO.getSortCode() == 0) {
                sort += "se.code desc,";
            }
        }

        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        requestVO.setSort(sort);

        //判断是总部，还是门店
        if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            requestVO.setEpType(1);
            //如果是总部则默认搜索自营店的信息
            if(requestVO.getChainType() == null) requestVO.setChainType(ChainType.Selfoperatedshop.getCode());
        } else {
            requestVO.setEpType(2);
            requestVO.setChainType(null);
        }
        requestVO.setEnterpriseId(userVO.getEnterpriseId());

        com.github.pagehelper.Page hPage = null;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            hPage = PageHelper.startPage(requestVO.getPageNo(), requestVO.getPageSize());
        }
        //获取柜组列表
        List<ReportCabinetSaleVO> cabinetSaleVOList = saleAnalysisReportGroupMapper.getReportCabinetSaleList(requestVO);
        if (cabinetSaleVOList == null) {
            cabinetSaleVOList = new ArrayList<>();
        }

        PageHelper.clearPage();
        //相关金额封装
        getCabinetAmount(cabinetSaleVOList, requestVO.getType());

        ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> cabinetTotalVO = new ReportSaleAnalysisTotalVO<>();
        cabinetTotalVO.setSaleList(cabinetSaleVOList);

        //计算合计金额
        List<ReportCabinetSaleVO> cabinetSaleVOList2;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            cabinetSaleVOList2 = saleAnalysisReportGroupMapper.getReportCabinetSaleList(requestVO);
            //相关金额封装
            getCabinetAmount(cabinetSaleVOList2, requestVO.getType());
        } else {
            cabinetSaleVOList2 = new ArrayList<>();
            cabinetSaleVOList2.addAll(cabinetSaleVOList);
        }
        sumCabinetAmount(cabinetTotalVO, cabinetSaleVOList2);

        page.setResult(cabinetTotalVO);
        if (hPage != null) {
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return page;
    }

    @Override
    public ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> getCabinetSaleDetailList(Long enterpriseId, Long cargoAreaId, Integer type, String dateStr) throws Exception {
        //获取柜组明细列表
        List<ReportCabinetSaleVO> cabinetSaleVOList = saleAnalysisReportGroupMapper.getReportCabinetSaleDetailList(enterpriseId, cargoAreaId, type, dateStr);
        if (cabinetSaleVOList == null || cabinetSaleVOList.size() == 0) {
            throw new BusinessException("查询柜组明细错误，请检查参数！");
        }
        //相关金额封装
        getCabinetAmount(cabinetSaleVOList, type);

        ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> cabinetTotalVO = new ReportSaleAnalysisTotalVO<>();
        cabinetTotalVO.setSaleList(cabinetSaleVOList);

        //计算外层合计
        sumCabinetAmount(cabinetTotalVO, cabinetSaleVOList);
        return cabinetTotalVO;
    }

    @Override
    public void excelExportCabinetSale(OutputStream output, ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> cabinetTotalVO, Integer type, UserVO userVO) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("柜组销售");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("storeCode", "门店编码");
            map.put("storeName", "门店名称");
        }
        if (type == 1) {
            map.put("saleDate", "销售日期");
        } else {
            map.put("dailyTime", "日结日期");
        }
        map.put("cargoAreaCode", "柜组编码");
        map.put("cargoAreaName", "柜组名称");
        map.put("saleQuantity", "销售笔数");
        map.put("realAmount", "销售金额");
        map.put("returnQuantity", "退货笔数");
        map.put("returnRealAmount", "退货金额");
        map.put("realAmountTotal", "总额");
        map.put("notaxRealAmountTotal", "不含税总额");
        map.put("taxAmountTotal", "税额");

        //合计
        StringBuilder endTotal = new StringBuilder();
        endTotal.append(cabinetTotalVO.getSaleQuantity());
        endTotal.append(";");
        endTotal.append(cabinetTotalVO.getRealAmount());
        endTotal.append(";");
        endTotal.append(cabinetTotalVO.getReturnQuantity());
        endTotal.append(";");
        endTotal.append(cabinetTotalVO.getReturnRealAmount());
        endTotal.append(";");
        endTotal.append(cabinetTotalVO.getRealAmountTotal());
        endTotal.append(";");
        endTotal.append(cabinetTotalVO.getNotaxRealAmountTotal());
        endTotal.append(";");
        endTotal.append(cabinetTotalVO.getTaxAmountTotal());
        endTotal.append(";");

        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("saleQuantity");
        needTotalName.add("realAmount");
        needTotalName.add("returnQuantity");
        needTotalName.add("returnRealAmount");
        needTotalName.add("realAmountTotal");
        needTotalName.add("notaxRealAmountTotal");
        needTotalName.add("taxAmountTotal");

        purchaseGeneralComponent.commExcelExport(output, map, cabinetTotalVO.getSaleList(),
                names, null, endTotal.toString(), false, needTotalName);
    }

    @Override
    public Page<ReportSaleAnalysisTotalVO<ReportTeamSaleVO>> getTeamSaleList(RequestTeamSaleVO requestVO, Page page, UserVO userVO) throws Exception {
        if (requestVO.getType() == null) {
            throw new BusinessException("搜索类型不能为空，请检查传参！");
        }

        String sort = "";
        if (requestVO.getType() == 1) {//销售日期搜索
            if (requestVO.getSortSale() != null) {
                if (requestVO.getSortSale() == 0) {
                    sort += "sale.sale_date,";
                } else if (requestVO.getSortSale() == 1) {
                    sort += "sale.sale_date desc,";
                }
            }
        } else {
            if (requestVO.getSortDaily() != null) {
                if (requestVO.getSortDaily() == 0) {
                    sort += "sale.daily_time,";
                } else if (requestVO.getSortDaily() == 1) {
                    sort += "sale.daily_time desc,";
                }
            }
        }

        //门店编码排序
        if (requestVO.getSortCode() != null) {
            if (requestVO.getSortCode() == 0) {
                sort += "se.code,";
            } else if (requestVO.getSortCode() == 0) {
                sort += "se.code desc,";
            }
        }

        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        requestVO.setSort(sort);
        //判断是总部，还是门店
        if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            requestVO.setEpType(1);
            if(requestVO.getChainType() == null) requestVO.setChainType(ChainType.Selfoperatedshop.getCode());
        } else {
            requestVO.setEpType(2);
            requestVO.setChainType(null);
        }
        requestVO.setEnterpriseId(userVO.getEnterpriseId());

        com.github.pagehelper.Page hPage = null;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            hPage = PageHelper.startPage(requestVO.getPageNo(), requestVO.getPageSize());
        }
        //获取柜组列表
        List<ReportTeamSaleVO> teamSaleVOList = saleAnalysisReportGroupMapper.getReportTeamSaleList(requestVO);
        if (teamSaleVOList == null) {
            teamSaleVOList = new ArrayList<>();
        }

        PageHelper.clearPage();
        //相关金额封装
        getTeamAmount(teamSaleVOList, requestVO.getType());

        ReportSaleAnalysisTotalVO<ReportTeamSaleVO> teamTotalVO = new ReportSaleAnalysisTotalVO<>();
        teamTotalVO.setSaleList(teamSaleVOList);

        //计算合计金额
        List<ReportTeamSaleVO> teamSaleVOList2;
        if (requestVO.getPageNo() != null && requestVO.getPageSize() != null) {
            teamSaleVOList2 = saleAnalysisReportGroupMapper.getReportTeamSaleList(requestVO);
            //相关金额封装
            getTeamAmount(teamSaleVOList2, requestVO.getType());
        } else {
            teamSaleVOList2 = new ArrayList<>();
            teamSaleVOList2.addAll(teamSaleVOList);
        }
        sumTeamAmount(teamTotalVO, teamSaleVOList2);

        page.setResult(teamTotalVO);
        if (hPage != null) {
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return page;
    }

    @Override
    public ReportSaleAnalysisTotalVO<ReportTeamSaleVO> getTeamSaleDetailList(Long enterpriseId, Long teamId, Integer type, String dateStr) throws Exception {
        //获取柜组明细列表
        List<ReportTeamSaleVO> teamSaleVOList = saleAnalysisReportGroupMapper.getReportTeamSaleDetailList(enterpriseId, teamId, type, dateStr);
        if (teamSaleVOList == null || teamSaleVOList.size() == 0) {
            throw new BusinessException("查询班组明细错误，请检查参数！");
        }
        //相关金额封装
        getTeamAmount(teamSaleVOList, type);

        ReportSaleAnalysisTotalVO<ReportTeamSaleVO> teamTotalVO = new ReportSaleAnalysisTotalVO<>();
        teamTotalVO.setSaleList(teamSaleVOList);

        //计算外层合计
        sumTeamAmount(teamTotalVO, teamSaleVOList);
        return teamTotalVO;
    }

    @Override
    public void excelExportTeamSale(OutputStream output, ReportSaleAnalysisTotalVO<ReportTeamSaleVO> teamTotalVO, Integer type, UserVO userVO) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("班组销售");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (userVO.getParentId() == 0) {
            map.put("storeCode", "门店编码");
            map.put("storeName", "门店名称");
        }
        if (type == 1) {
            map.put("saleDate", "销售日期");
        } else {
            map.put("dailyTime", "日结日期");
        }
        map.put("teamCode", "班组编码");
        map.put("teamName", "班组名称");
        map.put("saleQuantity", "销售笔数");
        map.put("realAmount", "销售金额");
        map.put("returnQuantity", "退货笔数");
        map.put("returnRealAmount", "退货金额");
        map.put("realAmountTotal", "总额");
        map.put("notaxRealAmountTotal", "不含税总额");
        map.put("taxAmountTotal", "税额");

        //合计
        StringBuilder endTotal = new StringBuilder();
        endTotal.append(teamTotalVO.getSaleQuantity());
        endTotal.append(";");
        endTotal.append(teamTotalVO.getRealAmount());
        endTotal.append(";");
        endTotal.append(teamTotalVO.getReturnQuantity());
        endTotal.append(";");
        endTotal.append(teamTotalVO.getReturnRealAmount());
        endTotal.append(";");
        endTotal.append(teamTotalVO.getRealAmountTotal());
        endTotal.append(";");
        endTotal.append(teamTotalVO.getNotaxRealAmountTotal());
        endTotal.append(";");
        endTotal.append(teamTotalVO.getTaxAmountTotal());
        endTotal.append(";");

        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("saleQuantity");
        needTotalName.add("realAmount");
        needTotalName.add("returnQuantity");
        needTotalName.add("returnRealAmount");
        needTotalName.add("realAmountTotal");
        needTotalName.add("notaxRealAmountTotal");
        needTotalName.add("taxAmountTotal");

        purchaseGeneralComponent.commExcelExport(output, map, teamTotalVO.getSaleList(),
                names, null, endTotal.toString(), false, needTotalName);
    }

    /**
     * 获取列表相关金额
     *
     * @param cabinetSaleVOList
     * @param requestVO
     */
    private void getCabinetAmount(List<ReportCabinetSaleVO> cabinetSaleVOList, Integer type) {
        if (cabinetSaleVOList == null || cabinetSaleVOList.size() == 0) {
            return;
        }

        //获取各个柜组金额
        List<ReportSaleDetailVO> saleDetailList;
        String dateStr;
        //销售笔数
        Set<Long> saleOrderIds;
        //退货笔数
        Set<Long> returnOrderIds;
        //销售金额
        BigDecimal realAmount;
        //退货金额
        BigDecimal returnRealAmount;
        //不含税销售金额
        BigDecimal notaxRealAmount;
        //不含税退货金额
        BigDecimal notaxReturnRealAmount;
        for (ReportCabinetSaleVO cabinetSaleVO : cabinetSaleVOList) {
            saleOrderIds = new HashSet<>();
            returnOrderIds = new HashSet<>();
            realAmount = BigDecimal.ZERO;
            returnRealAmount = BigDecimal.ZERO;
            notaxRealAmount = BigDecimal.ZERO;
            notaxReturnRealAmount = BigDecimal.ZERO;
            if (type == 1) {
                dateStr = cabinetSaleVO.getSaleDate();
            } else {
                dateStr = cabinetSaleVO.getDailyTime();
            }
            saleDetailList = saleAnalysisReportGroupMapper.getReportCabinetSaleDetail(cabinetSaleVO.getEnterpriseId(),
                    cabinetSaleVO.getCargoAreaId(), cabinetSaleVO.getClerkId(), dateStr, type);
            if (saleDetailList != null || saleDetailList.size() > 0) {
                for (ReportSaleDetailVO saleDetailVO : saleDetailList) {
                    if (saleDetailVO.getSaleType() == 0) {//销售
                        saleOrderIds.add(saleDetailVO.getSaleId());
                        realAmount = realAmount.add(saleDetailVO.getRealAmount());
                        notaxRealAmount = notaxRealAmount.add(saleDetailVO.getNotaxRealAmount());
                    } else if (saleDetailVO.getSaleType() == 1) {//退货
                        returnOrderIds.add(saleDetailVO.getSaleId());
                        returnRealAmount = returnRealAmount.add(saleDetailVO.getRealAmount());
                        notaxReturnRealAmount = notaxReturnRealAmount.add(saleDetailVO.getNotaxRealAmount());
                    }
                }
            }

            //销售笔数
            cabinetSaleVO.setSaleQuantity((long) saleOrderIds.size());
            //销售金额
            cabinetSaleVO.setRealAmount(realAmount);
            //退货笔数
            cabinetSaleVO.setReturnQuantity((long) returnOrderIds.size());
            //退货金额
            cabinetSaleVO.setReturnRealAmount(returnRealAmount);
            //总额=销售金额-退货金额
            cabinetSaleVO.setRealAmountTotal(realAmount.subtract(returnRealAmount));
            //不含税总额 = 不含税销售 - 不含税退货
            cabinetSaleVO.setNotaxRealAmountTotal(notaxRealAmount.subtract(notaxReturnRealAmount));
            //税额=总额-不含税总额
            cabinetSaleVO.setTaxAmountTotal(cabinetSaleVO.getRealAmountTotal().subtract(cabinetSaleVO.getNotaxRealAmountTotal()));
        }
    }

    /**
     * 获取列表相关金额
     *
     * @param teamSaleVOList
     * @param requestVO
     */
    private void getTeamAmount(List<ReportTeamSaleVO> teamSaleVOList, Integer type) {
        if (teamSaleVOList == null || teamSaleVOList.size() == 0) {
            return;
        }

        //获取各个柜组金额
        List<ReportSaleDetailVO> saleDetailList;
        String dateStr;
        //销售笔数
        Set<Long> saleOrderIds;
        //退货笔数
        Set<Long> returnOrderIds;
        //销售金额
        BigDecimal realAmount;
        //退货金额
        BigDecimal returnRealAmount;
        //不含税销售金额
        BigDecimal notaxRealAmount;
        //不含税退货金额
        BigDecimal notaxReturnRealAmount;
        for (ReportTeamSaleVO teamSaleVO : teamSaleVOList) {
            saleOrderIds = new HashSet<>();
            returnOrderIds = new HashSet<>();
            realAmount = BigDecimal.ZERO;
            returnRealAmount = BigDecimal.ZERO;
            notaxRealAmount = BigDecimal.ZERO;
            notaxReturnRealAmount = BigDecimal.ZERO;
            if (type == 1) {
                dateStr = teamSaleVO.getSaleDate();
            } else {
                dateStr = teamSaleVO.getDailyTime();
            }
            saleDetailList = saleAnalysisReportGroupMapper.getReportTeamSaleDetail(teamSaleVO.getEnterpriseId(),
                    teamSaleVO.getTeamId(), teamSaleVO.getPayeeId(), dateStr, type);
            if (saleDetailList != null || saleDetailList.size() > 0) {
                for (ReportSaleDetailVO saleDetailVO : saleDetailList) {
                    if (saleDetailVO.getSaleType() == 0) {//销售
                        saleOrderIds.add(saleDetailVO.getSaleId());
                        realAmount = realAmount.add(saleDetailVO.getRealAmount());
                        notaxRealAmount = notaxRealAmount.add(saleDetailVO.getNotaxRealAmount());
                    } else if (saleDetailVO.getSaleType() == 1) {//退货
                        returnOrderIds.add(saleDetailVO.getSaleId());
                        returnRealAmount = returnRealAmount.add(saleDetailVO.getRealAmount());
                        notaxReturnRealAmount = notaxReturnRealAmount.add(saleDetailVO.getNotaxRealAmount());
                    }
                }
            }

            //销售笔数
            teamSaleVO.setSaleQuantity((long) saleOrderIds.size());
            //销售金额
            teamSaleVO.setRealAmount(realAmount);
            //退货笔数
            teamSaleVO.setReturnQuantity((long) returnOrderIds.size());
            //退货金额
            teamSaleVO.setReturnRealAmount(returnRealAmount);
            //总额=销售金额-退货金额
            teamSaleVO.setRealAmountTotal(realAmount.subtract(returnRealAmount));
            //不含税总额 = 不含税销售 - 不含税退货
            teamSaleVO.setNotaxRealAmountTotal(notaxRealAmount.subtract(notaxReturnRealAmount));
            //税额=总额-不含税总额
            teamSaleVO.setTaxAmountTotal(teamSaleVO.getRealAmountTotal().subtract(teamSaleVO.getNotaxRealAmountTotal()));
        }
    }

    /**
     * 计算搜索结果的合计金额-柜组
     */
    private void sumCabinetAmount(ReportSaleAnalysisTotalVO<ReportCabinetSaleVO> cabinetTotalVO, List<ReportCabinetSaleVO> cabinetSaleVOList) {
        if (cabinetSaleVOList == null || cabinetSaleVOList.size() == 0) {
            return;
        }

        //销售笔数
        Long saleQuantity = 0l;
        //退货笔数
        Long returnQuantity = 0l;
        //销售金额
        BigDecimal realAmount = BigDecimal.ZERO;
        //退货金额
        BigDecimal returnRealAmount = BigDecimal.ZERO;

        //总额
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        //不含总额
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        //税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        for (ReportCabinetSaleVO cabinetSaleVO : cabinetSaleVOList) {
            saleQuantity += cabinetSaleVO.getSaleQuantity();
            returnQuantity += cabinetSaleVO.getReturnQuantity();
            realAmount = realAmount.add(cabinetSaleVO.getRealAmount());
            returnRealAmount = returnRealAmount.add(cabinetSaleVO.getReturnRealAmount());
            realAmountTotal = realAmountTotal.add(cabinetSaleVO.getRealAmountTotal());
            notaxRealAmountTotal = notaxRealAmountTotal.add(cabinetSaleVO.getNotaxRealAmountTotal());
            taxAmountTotal = taxAmountTotal.add(cabinetSaleVO.getTaxAmountTotal());
        }

        //合计销售笔数
        cabinetTotalVO.setSaleQuantity(saleQuantity);
        //合计销售金额
        cabinetTotalVO.setRealAmount(realAmount);
        //合计退货笔数
        cabinetTotalVO.setReturnQuantity(returnQuantity);
        //合计退货金额
        cabinetTotalVO.setReturnRealAmount(returnRealAmount);
        //合计总额
        cabinetTotalVO.setRealAmountTotal(realAmountTotal);
        //合计 不含税总额
        cabinetTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        //合计税额
        cabinetTotalVO.setTaxAmountTotal(taxAmountTotal);
    }

    /**
     * 计算搜索结果的合计金额-班组
     */
    private void sumTeamAmount(ReportSaleAnalysisTotalVO<ReportTeamSaleVO> teamTotalVO, List<ReportTeamSaleVO> teamSaleVOList) {
        if (teamSaleVOList == null || teamSaleVOList.size() == 0) {
            return;
        }

        //销售笔数
        Long saleQuantity = 0l;
        //退货笔数
        Long returnQuantity = 0l;
        //销售金额
        BigDecimal realAmount = BigDecimal.ZERO;
        //退货金额
        BigDecimal returnRealAmount = BigDecimal.ZERO;

        //总额
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        //不含总额
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        //税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        for (ReportTeamSaleVO teamSaleVO : teamSaleVOList) {
            saleQuantity += teamSaleVO.getSaleQuantity();
            returnQuantity += teamSaleVO.getReturnQuantity();
            realAmount = realAmount.add(teamSaleVO.getRealAmount());
            returnRealAmount = returnRealAmount.add(teamSaleVO.getReturnRealAmount());
            realAmountTotal = realAmountTotal.add(teamSaleVO.getRealAmountTotal());
            notaxRealAmountTotal = notaxRealAmountTotal.add(teamSaleVO.getNotaxRealAmountTotal());
            taxAmountTotal = taxAmountTotal.add(teamSaleVO.getTaxAmountTotal());
        }

        //合计销售笔数
        teamTotalVO.setSaleQuantity(saleQuantity);
        //合计销售金额
        teamTotalVO.setRealAmount(realAmount);
        //合计退货笔数
        teamTotalVO.setReturnQuantity(returnQuantity);
        //合计退货金额
        teamTotalVO.setReturnRealAmount(returnRealAmount);
        //合计总额
        teamTotalVO.setRealAmountTotal(realAmountTotal);
        //合计 不含税总额
        teamTotalVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        //合计税额
        teamTotalVO.setTaxAmountTotal(taxAmountTotal);
    }
}
