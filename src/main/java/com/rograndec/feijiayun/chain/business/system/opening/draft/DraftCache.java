package com.rograndec.feijiayun.chain.business.system.opening.draft;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableListVO;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.YesAndNo;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 期初应付/应收草稿操作
 *
 * @author zhangyu
 * @create 2018-01-04
 */

@Component
public class DraftCache {
    @Autowired
    private RedisComponent redisComponent;


    /**
     * 获取期初应付/应收缓存
     *
     * @param enterpriseId
     * @param codePrefix
     * @param isRemove     是否清空草稿 true:清空，false: 不清空
     * @return
     */
    private DraftCacheVO getDraftCacheVO(Long enterpriseId, String codePrefix, Boolean isRemove) {
        DraftCacheVO draftCacheVO = null;
        List<DraftCacheVO> cacheVOS = redisComponent.getDraftCacheVO(enterpriseId, codePrefix, null);
        if (!CollectionUtils.isEmpty(cacheVOS)) {
            draftCacheVO = cacheVOS.get(0);
            int retainCount = isRemove ? 0 : 1;
            if (cacheVOS.size() > retainCount) {
                for (int i = retainCount; i < cacheVOS.size(); i++) {
                    redisComponent.removeDraftCacheVO(enterpriseId, codePrefix, cacheVOS.get(i).getId());
                }
            }
        }
        return draftCacheVO;
    }

    /**
     * 期初应付，应收存草稿
     *
     * @param userVO
     * @param paymentReceivableListVO
     * @param codePrefix
     */
    public void saveDraftCache(UserVO userVO, OpenningPaymentReceivableListVO paymentReceivableListVO, String codePrefix) {
        DraftCacheVO draftCacheVO = getDraftCacheVO(userVO.getEnterpriseId(), codePrefix, false);
        if (null == draftCacheVO) {
            draftCacheVO = new DraftCacheVO<OpenningPaymentReceivableListVO>();
            draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
            draftCacheVO.setOrderCode(codePrefix);
        }
        draftCacheVO.setOrderStr(JSON.toJSONString(paymentReceivableListVO));
        redisComponent.saveDraftCacheVO(draftCacheVO);
    }

    private OpenningPaymentReceivableListVO getOpenningPaymentReceivableCacheVO(Long enterpriseId, String codePrefix) {
        DraftCacheVO draftCacheVO = getDraftCacheVO(enterpriseId, codePrefix, false);
        if (draftCacheVO == null) {
            return null;
        }
        String orderStr = draftCacheVO.getOrderStr();
        return orderStr == null ? null : JSON.parseObject(orderStr, OpenningPaymentReceivableListVO.class);
    }

    /**
     * 整合缓存和表数据
     *
     * @param userVO
     * @param codePrefix
     * @param listVO
     * @param detailVOS
     */
    public void integrationCacheAndTable(UserVO userVO, String codePrefix, OpenningPaymentReceivableListVO listVO, List<OpeningPaymentReceivableDetailVO> detailVOS) {
        listVO.setIsGenerate(YesAndNo.NO.getCode());
        listVO.setDate(DateUtils.StringToDate(LocalDate.now().toString(), DateUtils.FMT_DATE));
        listVO.setManId(userVO.getUserId());
        listVO.setManName(userVO.getUserName());
        if (!CollectionUtils.isEmpty(detailVOS)) {
            BigDecimal amountTotal = new BigDecimal(0.00);
            OpenningPaymentReceivableListVO cacheVO = getOpenningPaymentReceivableCacheVO(userVO.getEnterpriseId(), codePrefix);
            if (null != cacheVO) {
                listVO.setDate(cacheVO.getDate());
                listVO.setManId(cacheVO.getManId());
                listVO.setManName(cacheVO.getManName());
                listVO.setRemark(cacheVO.getRemark());
                List<OpeningPaymentReceivableDetailVO> detailCacheVOS = cacheVO.getDetailVOS();
                if (!CollectionUtils.isEmpty(detailCacheVOS)) {
                    Map<String, OpeningPaymentReceivableDetailVO> detailVOMap = OpeningPaymentReceivableDetailVO.getMap(detailCacheVOS);
                    for (OpeningPaymentReceivableDetailVO detailVO : detailVOS) {
                        OpeningPaymentReceivableDetailVO detailCacheVO = detailVOMap.get(detailVO.getCode());
                        if (null != detailCacheVO) {
                            detailVO.setAmount(detailCacheVO.getAmount());
                            amountTotal = amountTotal.add(new BigDecimal(detailCacheVO.getAmount()));
                        }
                    }
                }
            }
            listVO.setAmountTotal(amountTotal);
        }
        listVO.setDetailVOS(detailVOS);
    }

    /**
     * 期初税额存草稿
     *
     * @param userVO
     * @param openingTaxVO
     * @param codePrefix
     */
    public void saveDraftCache(UserVO userVO, OpeningTaxVO openingTaxVO, String codePrefix) {
        @SuppressWarnings("unchecked")
        DraftCacheVO<OpeningTaxVO> draftCacheVO = getDraftCacheVO(userVO.getEnterpriseId(), codePrefix, false);
        if (null == draftCacheVO) {
            draftCacheVO = new DraftCacheVO<OpeningTaxVO>();
            draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
            draftCacheVO.setOrderCode(codePrefix);
        }
        draftCacheVO.setOrderStr(JSON.toJSONString(openingTaxVO));
        redisComponent.saveDraftCacheVO(draftCacheVO);
    }

    public OpeningTaxVO getOpeningTaxVOCacheVO(Long enterpriseId, String codePrefix) {
        DraftCacheVO<OpeningTaxVO> draftCacheVO = getDraftCacheVO(enterpriseId, codePrefix, false);
        if (draftCacheVO == null) {
            return null;
        }
        String orderStr = draftCacheVO.getOrderStr();
        return orderStr == null ? null : JSON.parseObject(orderStr, OpeningTaxVO.class);
    }

    /**
     * 清空草稿
     *
     * @param enterpriseId
     * @param codePrefix
     */
    public void removerDraftCache(Long enterpriseId, String codePrefix) {
        getDraftCacheVO(enterpriseId, codePrefix, true);
    }
}
