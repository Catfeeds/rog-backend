package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.GoodsLicenseReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.GoodsQualificationMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodsLicenseReportServiceImpl implements GoodsLicenseReportService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsQualificationConfigMapper goodsQualificationConfigMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    GoodsQualificationMapper goodsQualificationMapper;
    @Autowired
    FileMapper fileMapper;
    @Override
    public Page<List<FirstGoodsVO>> getQualificationGoodsInfo(UserVO loginUser, Integer businessVariety, String param, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception {
        Page<List<FirstGoodsVO>> page = new Page<>(pageNo, pageSize);
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Goods> goodsList = goodsMapper.getGoodsLicense(loginUser.getEnterpriseId(),businessVariety,param,orderName,orderType);
        List<FirstGoodsVO> goodsVOS = getGoodsVOList(goodsList,loginUser);
        page.setResult(goodsVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, String param, String orderName, String orderType, Integer businessVariety) throws Exception {
        List<Goods> goodsList = goodsMapper.getGoodsLicense(loginUser.getEnterpriseId(),businessVariety,param,orderName,orderType);
        List<FirstGoodsVO> goodsVOS = getGoodsVOList(goodsList,loginUser);
        ExcelWriter writer = new ExcelWriter() {
            @Override
            public void generate() throws Exception {
                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //标题行
                this.insertRow(kk++);
                this.createCell(0, loginUser.getEnterpriseName(),cellStrIndex);
                this.endRow();
                //表名
                this.insertRow(kk++);
                this.createCell(0,"品种资质",cellStrIndex);
                this.endRow();
                //标题行
                this.insertRow(kk++);
                this.createCell(0, "序号",cellStrIndex);
                this.createCell(1, "品种类型",cellStrIndex);
                this.createCell(2,"商品分类",cellStrIndex);
                this.createCell(3,"商品编码",cellStrIndex);
                this.createCell(4,"条形码",cellStrIndex);
                this.createCell(5,"通用名称",cellStrIndex);
                this.createCell(6,"商品名称",cellStrIndex);
                this.createCell(7,"剂型",cellStrIndex);
                this.createCell(8,"规格",cellStrIndex);
                this.createCell(9,"单位",cellStrIndex);
                this.createCell(10,"生产厂商",cellStrIndex);
                this.createCell(11,"产地",cellStrIndex);
                this.createCell(12,"批准文号",cellStrIndex);
                this.createCell(13,"资质名称",cellStrIndex);
                this.createCell(14,"资质编码",cellStrIndex);
                this.createCell(15,"有效期至",cellStrIndex);
                this.endRow();
                int index = 1;
                for(FirstGoodsVO firstGoodsVO : goodsVOS){
                    if(firstGoodsVO.getGoodsQualificationConfigVOList().isEmpty()){
                        continue;
                    }
                    if(firstGoodsVO.getGoodsQualificationConfigVOList().size()==1){
                        this.insertRow(kk++);
                        this.createCell(0, StringUtil.transferTrimStr((index)),cellStrIndex);
                        this.createCell(1, StringUtil.transferTrimStr(firstGoodsVO.getBusinessVarietyName()),cellStrIndex);
                        this.createCell(2, StringUtil.transferTrimStr(firstGoodsVO.getCategoryName()),cellStrIndex);
                        this.createCell(3, StringUtil.transferTrimStr(firstGoodsVO.getCode()),cellStrIndex);
                        this.createCell(4, StringUtil.transferTrimStr(firstGoodsVO.getBarcode()),cellStrIndex);
                        this.createCell(5, StringUtil.transferTrimStr(firstGoodsVO.getGenericName()),cellStrIndex);
                        this.createCell(6, StringUtil.transferTrimStr(firstGoodsVO.getName()),cellStrIndex);
                        this.createCell(7, StringUtil.transferTrimStr(firstGoodsVO.getDosageName()),cellStrIndex);
                        this.createCell(8, StringUtil.transferTrimStr(firstGoodsVO.getSpecification()),cellStrIndex);
                        this.createCell(9, StringUtil.transferTrimStr(firstGoodsVO.getUnitName()),cellStrIndex);
                        this.createCell(10, StringUtil.transferTrimStr(firstGoodsVO.getManufacturer()),cellStrIndex);
                        this.createCell(11, StringUtil.transferTrimStr(firstGoodsVO.getPlace()),cellStrIndex);
                        this.createCell(12, StringUtil.transferTrimStr(firstGoodsVO.getApprovalNumber()),cellStrIndex);
                        this.createCell(13, StringUtil.transferTrimStr(firstGoodsVO.getGoodsQualificationConfigVOList().get(0).getQualificationName()),cellStrIndex);
                        this.createCell(14, StringUtil.transferTrimStr(firstGoodsVO.getGoodsQualificationConfigVOList().get(0).getQualificationCode()),cellStrIndex);
                        this.createCell(15, StringUtil.transferTrimStr(firstGoodsVO.getGoodsQualificationConfigVOList().get(0).getValidUntil()),cellStrIndex);
                        this.endRow();
                    }else {
                        boolean isSameGoods = false;
                        for (GoodsQualificationConfigVO goodsQualificationConfigVO : firstGoodsVO.getGoodsQualificationConfigVOList()){
                            this.insertRow(kk++);
                            if(!isSameGoods){
                                this.createCell(0, StringUtil.transferTrimStr((index)),cellStrIndex);
                                this.createCell(1, StringUtil.transferTrimStr(firstGoodsVO.getBusinessVarietyName()),cellStrIndex);
                                this.createCell(2, StringUtil.transferTrimStr(firstGoodsVO.getCategoryName()),cellStrIndex);
                                this.createCell(3, StringUtil.transferTrimStr(firstGoodsVO.getCode()),cellStrIndex);
                                this.createCell(4, StringUtil.transferTrimStr(firstGoodsVO.getBarcode()),cellStrIndex);
                                this.createCell(5, StringUtil.transferTrimStr(firstGoodsVO.getGenericName()),cellStrIndex);
                                this.createCell(6, StringUtil.transferTrimStr(firstGoodsVO.getName()),cellStrIndex);
                                this.createCell(7, StringUtil.transferTrimStr(firstGoodsVO.getDosageName()),cellStrIndex);
                                this.createCell(8, StringUtil.transferTrimStr(firstGoodsVO.getSpecification()),cellStrIndex);
                                this.createCell(9, StringUtil.transferTrimStr(firstGoodsVO.getUnitName()),cellStrIndex);
                                this.createCell(10, StringUtil.transferTrimStr(firstGoodsVO.getManufacturer()),cellStrIndex);
                                this.createCell(11, StringUtil.transferTrimStr(firstGoodsVO.getPlace()),cellStrIndex);
                                this.createCell(12, StringUtil.transferTrimStr(firstGoodsVO.getApprovalNumber()),cellStrIndex);
                                isSameGoods = true;
                            }else {
                                this.createCell(0, "", cellStrIndex);
                                this.createCell(1, "", cellStrIndex);
                                this.createCell(2, "", cellStrIndex);
                                this.createCell(3, "", cellStrIndex);
                                this.createCell(4, "", cellStrIndex);
                                this.createCell(5, "", cellStrIndex);
                                this.createCell(6, "", cellStrIndex);
                                this.createCell(7, "", cellStrIndex);
                                this.createCell(8, "", cellStrIndex);
                                this.createCell(9, "", cellStrIndex);
                                this.createCell(10, "", cellStrIndex);
                                this.createCell(11, "", cellStrIndex);
                                this.createCell(12, "", cellStrIndex);
                            }
                            this.createCell(13, StringUtil.transferTrimStr(goodsQualificationConfigVO.getQualificationName()),cellStrIndex);
                            this.createCell(14, StringUtil.transferTrimStr(goodsQualificationConfigVO.getQualificationCode()),cellStrIndex);
                            this.createCell(15, StringUtil.transferTrimStr(goodsQualificationConfigVO.getValidUntil()),cellStrIndex);
                            this.endRow();
                        }
                        //合并单元格
                        this.beginMergerCell();
                        this.setMergeCell(kk-firstGoodsVO.getGoodsQualificationConfigVOList().size(),0,kk,12);
                        this.endMergerCell();
                    }
                    index++;
                }
                this.endSheet();
                this.beginMergerCell();
                //标题列合并
                this.setMergeCell(0,0,0,15);
                //名字合并
                this.setMergeCell(1,0,1,15);
                this.endMergerCell();

                this.endWorkSheet();
            }
        };
        writer.process(output);
    }

    @Override
    public GoodsVO getQualificationGoodsDtlInfo(UserVO loginUser, Long goodsId) throws Exception {
        GoodsVO goodsVO = goodsMapper.getGoodsListByGoodsID(loginUser.getEnterpriseId(),goodsId);
        List<GoodsQualificationConfigVO> qualificationRelateGoods = goodsMapper.getGoodsQualificationConfig(goodsVO.getId(),loginUser.getEnterpriseId());
        List<GoodsQualificationConfigVO>  qualificationNotRelateGoods = goodsQualificationMapper.getQualificationNotRelateGoods(loginUser.getEnterpriseId(),goodsVO.getId(),1,1,goodsVO.getCheckTypeId());
        qualificationNotRelateGoods.addAll(qualificationRelateGoods);
        goodsVO.setGoodsQualificationConfigVOList(qualificationNotRelateGoods);
        return goodsVO;
    }

    private List<FirstGoodsVO> getGoodsVOList(List<Goods> goodsList,UserVO loginUser) {
        List<FirstGoodsVO> goodsVOS = new ArrayList<>();
        for(Goods goods : goodsList) {
            FirstGoodsVO goodsVO = new FirstGoodsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goods, goodsVO);
            goodsVO.setBusinessVarietyName(BusinessVariety.getName(goodsVO.getBusinessVariety()));
            //商品分类
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goods.getCategoryId());
            goodsVO.setCategoryName(goodsCategory == null ? null : goodsCategory.getName());
            //商品属性
            goodsVO.setGoodsAttributeName(goodsVO.getGoodsAttribute() == null ? null : GoodsAttribute2DrugsA.getName(goodsVO.getGoodsAttribute()));
            //资质
            List<GoodsQualificationConfigVO> qualificationRelateGoods = goodsMapper.getGoodsQualificationConfig(goodsVO.getId(), loginUser.getEnterpriseId());
            List<GoodsQualificationConfigVO> qualificationNotRelateGoods = goodsQualificationMapper.getQualificationNotRelateGoods(loginUser.getEnterpriseId(), goodsVO.getId(), 1, 1, goodsVO.getCheckTypeId());
            if (qualificationRelateGoods != null && qualificationNotRelateGoods != null) {
                qualificationNotRelateGoods.addAll(qualificationRelateGoods);
                for(GoodsQualificationConfigVO goodsQualificationConfigVO : qualificationNotRelateGoods){
                    if(goodsQualificationConfigVO.getFileId() != null){
                        File file = fileMapper.selectByPrimaryKey(goodsQualificationConfigVO.getFileId());
                        goodsQualificationConfigVO.setFileName(file==null?null:file.getFileName());
                    }
                }
                goodsVO.setGoodsQualificationConfigVOList(qualificationNotRelateGoods);
                }else {
                goodsVO.setGoodsQualificationConfigVOList(new ArrayList<>());
            }
            goodsVOS.add(goodsVO);
            }
            return goodsVOS;
        }
}
