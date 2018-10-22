package com.rograndec.feijiayun.chain.business.goods.pharmacy.service;


import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * Created by xingjian.lan on 2017/9/9.
 */

@Service
public interface PharmacyDictionaryService {

    /**
     * 查询药品（企业下、标准库ID不为null或者不为0）
     * @param page
     * @param param
     * @param sort
     * @param enterpiseId
     * @return
     */
    Page getPharmacyDictionaryPage(Page page, String param, Integer sort, Long enterpiseId);

    /**
     * 导出药品词典
     * @param outPut
     * @param param
     * @param sort
     * @param userVO
     * @throws Exception
     */
    void excelExportDictionary(OutputStream outPut, String param, Integer sort, UserVO userVO) throws Exception;

    /**
     * 导出药品词典
     * @param outPut
     * @param param
     * @param sort
     * @param userVO
     * @throws Exception
     */
    void excelExportSelectGuide(OutputStream outPut, String param, Integer sort, UserVO userVO) throws Exception;
}
