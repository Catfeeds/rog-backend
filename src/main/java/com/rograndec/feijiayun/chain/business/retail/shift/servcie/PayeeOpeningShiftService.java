package com.rograndec.feijiayun.chain.business.retail.shift.servcie;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.PayeeOpeningShiftStoreVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.ResultPageVo;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosPayeeVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosStandVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosTeamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public interface PayeeOpeningShiftService {


    List<SelectPosStandVO> getSelectPosStand(UserVO loginUser);

    List<SelectPosTeamVO> getSelectPosTeam(UserVO loginUser);

    List<SelectPosPayeeVO> getSelectPosPayee(UserVO loginUser);

    List<DynamicColumnVO> selectDynamicColumnBySource(Integer source, UserVO loginUser) throws Exception;

    List<Map> getPayeeOpeningShift(Integer pageNo, Integer pageSize, UserVO loginUser, ResultPageVo page, Date startTime, Date endTime, String orderName, String orderType, String standCode, Long teamId, String payeeName, String storeType, String storeCode, String storeName) throws Exception;

    List<Map> getPayeeOpeningShiftSelect(Integer pageNo, Integer pageSize, UserVO loginUser, Page page, String orderName, String orderType, Long shiftId) throws Exception;

    void exportExcel(OutputStream output, List<DynamicColumnVO> list, UserVO loginUser, List<Map> map, String excelName);

}
