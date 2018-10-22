package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandle;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.RequestGoodsHandleListVo;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: GoodsHandleService
 * @Description: 储存管理-商品处理-药品处理-接口
 * @date 2017-09-27 17:27:38
 */
public interface GoodsHandleService {


    GoodsHandle getGoodsHandleData(Long handleId);

    int save(GoodsHandleSaveOrupdateVO goodsHandle, UserVO userVO) throws Exception;

    int update(GoodsHandleSaveOrupdateVO goodsHandle, UserVO userVO) throws Exception;

    int delete(Long id) throws Exception;

    /**
     * <获取商品处理单列表数据>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 13:36
     */
    void getGoodshandleListData(RequestGoodsHandleListVo requestGoodsHanleListVo, Page page);
    /**
     *
     * <导出商品处理单明细>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 16:44
     */
    void exportExcel(GoodsHandle goodsHandle, UserVO userVO, OutputStream output, String name);
}
