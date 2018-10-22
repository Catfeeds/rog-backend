package com.rograndec.feijiayun.chain.business.retail.shift.servcie;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.ResultPageVo;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosPayeeVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosStandVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosTeamVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public interface PayeeOpeningShiftStoreService {


    List<Map> getPayeeOpeningShiftStore(Integer pageNo, Integer pageSize, UserVO loginUser, ResultPageVo page, Date startTime, Date endTime, String orderName, String orderType, String standCode, Long teamId, String payeeName) throws Exception;

    List<Map> getPayeeOpeningShiftStoreSelect(Integer pageNo, Integer pageSize, UserVO loginUser, Page page, String orderName, String orderType, Long shiftId) throws Exception;

    void exportExcel(OutputStream output, List<DynamicColumnVO> list, UserVO loginUser, List<Map> map, String excelName);

}
