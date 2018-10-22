package com.rograndec.feijiayun.chain.business.system.opening.service;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventory;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import com.rograndec.feijiayun.chain.business.system.opening.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.TwoTuple;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;

/**
 * 期初库存服务
 * Created by ST on 2017/8/22.
 */
public interface OpeningInventoryService {

    TwoTuple<OpeningInventory,Page<List<OpeningInventoryDetail>>> getInventoryRecord(Long enterpriseId,Long parentId, Page page);

//    OpeningGoodsVO excelImport(HttpServletRequest request) throws Exception;

    void saveGoodsToInventory(OpeningGoodsInfoVO openingGoodsVO, UserVO userVO) throws Exception;

    void batchInsert(List<OpeningInventoryDetail> detailList);

    ResponseOpeningGoodsExcelVO excelOpeningImport(HttpServletRequest request) throws Exception;

    void excelExport(OutputStream output, List<OpeningGoodsExcelVO> list);

    Integer getOpeningInventoryCount(Long enterpriseId);


    List<OpeningGoodsGet> getGoodsListByParam(String param, UserVO enterpriseId);

    void exportOpeningGoods(OutputStream output, String key, Integer type) throws Exception;

    void importSuccessGoods(UserVO userVO, String key, Page2OpeningInventory<List<OpeningGoodsExcelVO>> page) throws Exception;

    void exportGoodsInfo(OutputStream outputStream, UserVO userVO);
}
