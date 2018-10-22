package com.rograndec.feijiayun.chain.business.quality.review.service;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.GoodsReviewVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewRequestVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<GoodsReviewVO> getGoods(Map<String, Object> map);

    void save(ReviewVO reviewVO, UserVO loginUser) throws Exception;

    void update(ReportVO report, UserVO loginUser) throws Exception;

    void delete(Long id);

    ReviewVO get(Long id) throws InvocationTargetException, IllegalAccessException;

    void export(Long id, OutputStream output, UserVO loginUser) throws Exception;

    Page getPageList(ReviewRequestVO requestVO, UserVO loginUser);

    void exportList(ReviewRequestVO requestVO, UserVO loginUser, OutputStream output) throws Exception;


    ReviewVO getStatistics(Long goodsId, String startDate, String endDate, UserVO loginUser);
}
