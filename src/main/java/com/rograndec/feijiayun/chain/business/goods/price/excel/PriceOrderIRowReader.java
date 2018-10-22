package com.rograndec.feijiayun.chain.business.goods.price.excel;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceOrderExcelVO;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.PriceType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 商品信息导入行解析器
 * Created by ST on 2017/9/7.
 *
 */
@Component
@Scope("prototype")
public class PriceOrderIRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(PriceOrderIRowReader.class);

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;


    private List<PriceOrderExcelVO> qualifiedList = new ArrayList<>();

    private List<PriceOrderExcelVO> disqualificationList = new ArrayList<>();

    private List<String> errors = new ArrayList<>();

    //合格数据的编码set
    private Set<String> codeSet = new HashSet<>();

    private UserVO userVO;

    private PriceOrder priceOrder;

    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowList){

        if (logger.isDebugEnabled()) {
            logger.debug("Sheet={},Row={},Data={}", sheetIndex, curRow, rowList);
        }



        int k = 0;
        if (curRow > 0) {//从第二行开始计算（excel行号从0开始）
            if (!CollectionUtils.isEmpty(rowList)) {

                if(null == priceOrder){
                    throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"未找到价格清单");
                }

                /**
                 *  价格类型（0-基础价格；1-配货价格；2-零售价格）
                 */
                Integer priceType = priceOrder.getPriceType();

                String lineNum = null; //序号
                String code = null; //商品编码
                String oldCode = null;//原商品编码
                String genericName = null;//通用名称*
                String dosageName = null;//剂型*
                String specification = null;//规格*
                String manufacturer = null;//生产厂商*
                String unitId = null;//单位*
                String distrPrice = null;//配货单价*
                String distrTaxRate = null;//配货税率*
                String retailPrice = null;//零售单价*
                String memberPrice = null;//会员单价*
                String saleTaxRate = null;//销项税税率*

                int size = rowList.size();

                if(PriceType.BASE_PRICE.getCode() == priceType){

                    if(size != 13) {
                       throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"对不起，您选择的价格清单模板文件与价格清单类型不一致，请核实后重新导入。");
                    }

                     lineNum = rowList.get(k++); //序号
                     code = rowList.get(k++); //商品编码
                     oldCode = rowList.get(k++); //原商品编码
                     genericName = rowList.get(k++);//通用名称*
                     dosageName = rowList.get(k++);//剂型*
                     specification = rowList.get(k++);//规格*
                     manufacturer = rowList.get(k++);//生产厂商*
                     unitId = rowList.get(k++);//单位*
                     distrPrice = rowList.get(k++);//配货单价*
                     distrTaxRate = rowList.get(k++);//配货税率*
                     retailPrice = rowList.get(k++);//零售单价*
                     memberPrice = rowList.get(k++);//会员单价*
                     saleTaxRate = rowList.get(k++);//销项税税率*

                }else if(PriceType.DIS_PRICE.getCode() == priceType){

                    if(size != 10) {
                        throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"对不起，您选择的价格清单模板文件与价格清单类型不一致，请核实后重新导入。");
                    }

                     lineNum = rowList.get(k++); //序号
                     code = rowList.get(k++);//商品编码
                     oldCode = rowList.get(k++); //原商品编码
                     genericName = rowList.get(k++);//通用名称*
                     dosageName = rowList.get(k++);//剂型*
                     specification = rowList.get(k++);//规格*
                     manufacturer = rowList.get(k++);//生产厂商*
                     unitId = rowList.get(k++);//单位*
                     distrPrice = rowList.get(k++);//配货单价*
                     distrTaxRate = rowList.get(k++);//配货税率*

                }else if(PriceType.RETAIL_PRICE.getCode() == priceType){
                    if(size != 11) {
                        throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"对不起，您选择的价格清单模板文件与价格清单类型不一致，请核实后重新导入。");
                    }

                    lineNum = rowList.get(k++); //序号
                     code = rowList.get(k++);//商品编码
                     oldCode = rowList.get(k++); //原商品编码
                     genericName = rowList.get(k++);//通用名称*
                    dosageName = rowList.get(k++);//剂型*
                     specification = rowList.get(k++);//规格*
                     manufacturer = rowList.get(k++);//生产厂商*
                    unitId = rowList.get(k++);//单位*
                     retailPrice = rowList.get(k++);//零售单价*
                     memberPrice = rowList.get(k++);//会员单价*
                     saleTaxRate = rowList.get(k++);//销项税税率*

                }


                PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
                priceOrderExcelVO.setGoodsCode(code);//商品编码
                priceOrderExcelVO.setGoodsOldCode(oldCode);// 原商品编码
                priceOrderExcelVO.setGoodsGenericName(genericName);//通用名称*
                priceOrderExcelVO.setDosageName(dosageName);//剂型*
                priceOrderExcelVO.setGoodsSpecification(specification);//规格*
                priceOrderExcelVO.setManufacturer(manufacturer);//生产厂商*
                priceOrderExcelVO.setUnitId(unitId);//单位*
                priceOrderExcelVO.setDistrPrice(distrPrice);//配货单价*
                priceOrderExcelVO.setDistrTaxRate(distrTaxRate);//配货税率*
                priceOrderExcelVO.setRetailPrice(retailPrice);//零售单价*
                priceOrderExcelVO.setMemberPrice(memberPrice);//会员单价*
                priceOrderExcelVO.setSaleTaxRate(saleTaxRate);//销项税税率*
                priceOrderExcelVO.setLineNum(lineNum);
                qualifiedList.add(priceOrderExcelVO);

            }

        }
    }


    public GoodsTaxRateMapper getGoodsTaxRateMapper() {
        return goodsTaxRateMapper;
    }

    public void setGoodsTaxRateMapper(GoodsTaxRateMapper goodsTaxRateMapper) {
        this.goodsTaxRateMapper = goodsTaxRateMapper;
    }

    public List<PriceOrderExcelVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<PriceOrderExcelVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<PriceOrderExcelVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<PriceOrderExcelVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }

    public Set<String> getCodeSet() {
        return codeSet;
    }

    public void setCodeSet(Set<String> codeSet) {
        this.codeSet = codeSet;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public PriceOrder getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(PriceOrder priceOrder) {
        this.priceOrder = priceOrder;
    }
}