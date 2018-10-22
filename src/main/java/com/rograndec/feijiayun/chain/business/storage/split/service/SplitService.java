package com.rograndec.feijiayun.chain.business.storage.split.service;

import com.rograndec.feijiayun.chain.business.storage.split.vo.RequestSplitOrderVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitGoodsStockVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/29 <br/>
 * 描述：拆零设置-商品拆零
 */
@Service
public interface SplitService {

    /**
     * 商品拆零-获取可拆零商品列表：1、有库存；2-存在启用状态的 拆零商品
     * @param page 分页
     * @param param 搜索关键字
     * @param enterpriseId 门店id
     * @return 商品拆零-获取可拆零商品列表
     */
    List<SplitGoodsStockVO> getGoodsStockList(String param, UserVO userVO);

    /**
     * 商品拆零单据列表
     * @param page 分页信息
     * @param startTime 开始时间
     * @param endTime 截至时间
     * @param code 拆零单号
     * @param splitManName 拆零人员
     * @param auditManName 复核人员
     * @return 商品拆零单据列表
     */
    Page getSplitOrderList(Page page, Date startTime, Date endTime, String code, String splitManName, String auditManName, String order, String sort, UserVO loginUser);

    /**
     * 保存拆零单据
     * @param loginUser 用户信息
     * @param requestSaveSplitVO 拆零请求参数封装
     * @return
     */
    int saveSplitOrder(UserVO loginUser, RequestSplitOrderVO requestSaveSplitVO) throws Exception;
}
