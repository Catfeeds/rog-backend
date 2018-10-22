package com.rograndec.feijiayun.chain.business.report.finance.stock.service.impl;

import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.report.finance.stock.service.StockBalanceService;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class StockBalanceServiceImpl implements StockBalanceService {

    @Autowired
    private CostMapper costMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Override
    public Page<StockBalanceTotalVO> getBalances(UserVO userVO, StockBalanceReqVO requestVO) {



        Page<StockBalanceTotalVO> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());
        requestVO.setPageStart(page.getStart());

        //构建参数
        buildParam(userVO,requestVO);

        requestVO.setPageStart(page.getStart());


        List<StockBalanceResultVO> dataList = costMapper.getBalancesPage(requestVO);
        Integer count = costMapper.selectBalancesPageCount(requestVO);

        StockBalanceTotalVO stockBalanceTotalVO = costMapper.selectTotalQuantityAndAmout(requestVO);

        if(stockBalanceTotalVO != null){
            stockBalanceTotalVO.setStockBalanceResultVOList(dataList);
        } else {
            stockBalanceTotalVO = new StockBalanceTotalVO();
            stockBalanceTotalVO.setQuantityTotal(BigDecimal.ZERO);
            stockBalanceTotalVO.setRealAmountTotal(BigDecimal.ZERO);
            stockBalanceTotalVO.setStockBalanceResultVOList(new ArrayList<>());
        }


        page.setResult(stockBalanceTotalVO);
        page.setTotalRecord(count);

        return page;
    }

    /** 
    * @Description: 构建参数
    * @return:  
    * @Author: dongyang.du
    * @Date: 11/01/2018 
    */ 
    private void buildParam(UserVO userVO, StockBalanceReqVO requestVO) {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer type = 0;// 默认查询自己企业(总部，自营店，加盟店)的
        if(requestVO.getChainType() != null){
            if(userVO.getChainType().equals(ChainType.Headquarters.getCode())
                    && 0 != requestVO.getChainType()){// 当前用户为总部，查询其他 加盟店或者自营店的数据时候
                type = 1; // 总部查询分店的
            }
        } else if(StringUtils.isNotEmpty(requestVO.getEnterpriseCode()) ||
                StringUtils.isNotEmpty(requestVO.getEnterpriseName())) {
            type = 2;// 查询
        }

        requestVO.setType(type);
        requestVO.setEnterpriseId(enterpriseId);
    }

    @Override
    public void export(OutputStream output, StockBalanceReqVO requestVO, UserVO userVO) throws Exception {

        requestVO.setPageSize(null);
        requestVO.setPageStart(null);

        //构建参数
        buildParam(userVO,requestVO);

        List<StockBalanceResultVO> dataList = costMapper.getBalancesPage(requestVO);
        StockBalanceTotalVO stockBalanceTotalVO = costMapper.selectTotalQuantityAndAmout(requestVO);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("enterpriseCode","组织机构编码");
        map.put("enterpriseName","组织机构名称");
        map.put("goodsCode", "商品编码");
        map.put("barcode","条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName","商品名称");
        map.put("dosageName","剂型");
        map.put("specification", "规格");
        map.put("unitName","单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace","产地");
        map.put("approvalNumber","批准文号");
        map.put("quantity", "数量");
        map.put("realAmount", "金额");

        Map<String, String> totalMap = new HashMap<>();
        if(stockBalanceTotalVO != null){
            totalMap.put("quantity",stockBalanceTotalVO.getQuantityTotal()+"");
            totalMap.put("realAmount",stockBalanceTotalVO.getRealAmountTotal() + "");
        }

        excelComponent.exportExcel(output, userVO.getEnterpriseName(), "库存余额", new ArrayList<>(), map, dataList
                ,totalMap);


    }

    @Override
    public StockBalanceTotalVO print(StockBalanceReqVO requestVO, UserVO userVO) {

        requestVO.setPageSize(null);
        requestVO.setPageStart(null);

        //构建参数
        buildParam(userVO,requestVO);

        List<StockBalanceResultVO> dataList = costMapper.getBalancesPage(requestVO);
        StockBalanceTotalVO stockBalanceTotalVO = costMapper.selectTotalQuantityAndAmout(requestVO);

        stockBalanceTotalVO.setStockBalanceResultVOList(dataList);
        return stockBalanceTotalVO;
    }


}
