package com.rograndec.feijiayun.chain.business.index.warning.service;

import com.rograndec.feijiayun.chain.business.index.warning.vo.EnterpriseWarnReportVO;
import com.rograndec.feijiayun.chain.business.index.warning.vo.WarningIndexVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserWarningExcelVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface WarningService {
    List<WarningIndexVO> getWarningIndexVOs(
            List<EnterpriseWarnReportVO> enterpriseQualificationWarnReportList
            , List<SupplierQualificationReportVO> supplierQualificationList
            , List<UserWarningExcelVO> userWarningPage
            , List<GoodsLicenseWarnVO> goodsLicenseWarnNotPageNotPage
            , StockWarnExpireGoodsTotalVO stockWarnExpireGoodsTotalVO
            , StockWarnNearPeriodGoodsTotalVO stockWarnNearPeriodGoodsTotalVO
            , StockWarnStoreGoodsTotalVO stockWarnStoreGoodsTotalVO
            , StockWarnLackGoodsTotalVO stockWarnLackGoodsTotalVO
            , StockWarnLagSaleGoodsTotalVO stockWarnLagSaleGoodsTotalVO
            , List<StockWarnGoodsMaintanceVO> stockWarnGoodsMaintanceVOS
    );

    Map<Integer, List<WarnSet>>getWarnSetTypeQualificationIdMaping(UserVO userVO);
}
