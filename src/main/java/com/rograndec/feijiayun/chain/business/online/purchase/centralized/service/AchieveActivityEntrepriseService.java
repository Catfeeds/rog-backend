package com.rograndec.feijiayun.chain.business.online.purchase.centralized.service;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.GetActivityEntrepriseVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.SelectActivityEntrepriseVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface AchieveActivityEntrepriseService {

    List<GetActivityEntrepriseVO> getActivityEntreprise(UserVO userVO);

    SelectActivityEntrepriseVO selectActivityEntreprise(int pageNo, int pageSize, Page page, Long enterpriseId, Long id, String searchValues, String gcName1, String gcName2, String manufacturer, Long gcName1Type, Long gcName2Type, Long manufacturerType);

    CentralizedCartVO setShoppingCart(String key, Integer activityId, String goodsId, Integer qty, UserVO loginUser) throws Exception;
}
