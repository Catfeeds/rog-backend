package com.rograndec.feijiayun.chain.business.retail.shift.servcie.Impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayTypeService;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.servcie.PayeeOpeningShiftStoreService;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.ResultPageVo;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/22.
 */
@Service
public class PayeeOpeningShiftStoreServiceImpl implements PayeeOpeningShiftStoreService {

    @Autowired
    PayeeOpeningShiftMapper payeeOpeningShiftMapper;

    @Autowired
    private PosPayTypeMapper posPayTypeMapper;

    @Autowired
    private ReceiptDetailMapper receiptDetailMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Autowired
    PurchaseGeneralComponent<Object> purchaseGeneralComponent;

    @Autowired
    private PosPayTypeService posPayTypeService;


    @Override
    public List<Map> getPayeeOpeningShiftStore(Integer pageNo, Integer pageSize, UserVO loginUser, ResultPageVo page, Date startTime, Date endTime,
                                               String orderName, String orderType, String standCode, Long teamId, String payeeName) throws Exception{
        String startTimes = null;
        String endTimes = null;

        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        }

        if (orderName != null && orderName.equals("standCode")) {
            orderName = "stand_code";
        } else if (orderName != null && orderName.equals("payeeName")) {
            orderName = "pe.payee_name";
        } else if (orderName != null && orderName.equals("teamName")) {
            orderName = "team_name";
        } else {
            orderName = null;
            orderType = null;
        }

        /*借用符合条件id计算总数量*/
        List<Map<String,Object>> totalRecord = payeeOpeningShiftMapper.queryCountByPayeeOpeningShiftStoreParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, standCode, teamId, payeeName);
        List<Map> list = payeeOpeningShiftMapper.payeeOpeningShiftStoreVoParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, orderName, orderType, standCode, teamId, payeeName);
        Long enterpriseId = null;
        Long parentId = null;
        if(loginUser.getChainType() == ChainType.Headquarters.getCode()){
            enterpriseId = loginUser.getEnterpriseId();
        }else{
            enterpriseId = loginUser.getEnterpriseId();
            parentId = loginUser.getParentId();
        }
//      List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseIdAndParentId(enterpriseId, parentId);
        List<PosPayTypeVO> payTypeList = posPayTypeService.getPayTypeData(loginUser);
        if (list != null) {
            for (Map map : list) {
                String standCodes = getStandCodeByShiftId(Long.parseLong(map.get("id").toString()));
                map.put("standCode", standCodes);
                BigDecimal realAmountTotal = receiptMapper.getSumRealAmountTotalByShiftId(Long.parseLong(map.get("id").toString()));
                map.put("realAmountTotal", realAmountTotal);
                setDynamicColumnValueByShiftId(map.get("id").toString(), map, payTypeList);
            }
        }
        BigDecimal realAmountTotalS = BigDecimal.ZERO;
        //计算总价钱
        Map<String, BigDecimal> sumMap = new HashMap<String, BigDecimal>();
        //固定字段汇总
        Map<String, BigDecimal> fieldMap = new HashMap<String, BigDecimal>();
        fieldMap=this.getMapSum(totalRecord);
        if (totalRecord != null && !totalRecord.isEmpty()) {
            for (Map<String,Object> paramMap : totalRecord) {
                if (!StringUtils.isEmpty(paramMap.get("id"))) {
                    List<Map> mapList = receiptDetailMapper.selectPayTypeAmountByShiftId(paramMap.get("id").toString());
                    BigDecimal realAmountTotal = receiptMapper.getSumRealAmountTotalByShiftId(Long.parseLong(paramMap.get("id").toString()));
                    if(realAmountTotal != null){
                        realAmountTotalS = realAmountTotalS.add(realAmountTotal);
                    }
                    for (Map map : mapList) {
                        String payType = String.valueOf(map.get("pay_type_code"));
                        String value = String.valueOf(map.get("amount"));
                        if (sumMap.containsKey(payType)) {
                            sumMap.put(payType, sumMap.get(payType).add(new BigDecimal(value)));
                        } else {
                            sumMap.put(payType, new BigDecimal(value));
                        }
                    }
                }
            }
            fieldMap.put("realAmountTotal", realAmountTotalS);
        }
        page.setResult(list);
        page.setSumMap(sumMap);
        page.setFieldMap(fieldMap);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.size());
        return list;
    }

    @Override
    public List<Map> getPayeeOpeningShiftStoreSelect(Integer pageNo, Integer pageSize, UserVO loginUser, Page page, String orderName, String orderType, Long shiftId) throws Exception{

        if (orderName != null && orderName.equals("saleTime")) {
            orderName = "sale_time";
        } else {
            orderName = null;
            orderType = null;
        }

        Long totalRecord = payeeOpeningShiftMapper.queryCountByPayeeOpeningShiftSelectStoreParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, shiftId);
        List<Map> list = payeeOpeningShiftMapper.payeeOpeningShiftStoreSelectVoParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, orderName, orderType, shiftId);
        Long enterpriseId = null;
        Long parentId = null;
        if(loginUser.getChainType() == ChainType.Headquarters.getCode()){
            enterpriseId = loginUser.getEnterpriseId();
        }else{
            enterpriseId = loginUser.getEnterpriseId();
            parentId = loginUser.getParentId();
        }
        //List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseIdAndParentId(enterpriseId, parentId);
        List<PosPayTypeVO> payTypeList = posPayTypeService.getPayTypeData(loginUser);
        if (list != null) {
            for (Map map : list) {
                String standCode = getStandCodeByShiftId(shiftId);
                map.put("standCode", standCode);
                String saleType = map.get("saleType") == null ? "" : map.get("saleType").toString();
                if ("1".equals(saleType)) {
                    map.put("realAmountTotal", new BigDecimal(map.get("realAmountTotal").toString()).negate());
                    map.put("saleType", "销退");
                } else {
                    map.put("saleType", "销售");
                }
                String saleMode = map.get("saleMode") == null ? "" : map.get("saleMode").toString();
                if ("1".equals(saleMode)) {
                    map.put("saleMode", "中药");
                } else {
                    map.put("saleMode", "常规");
                }

                setDynamicColumnValueByShiftDtlId( map, payTypeList);
            }
        }
        page.setResult(list);
        page.setTotalRecord(totalRecord.intValue());
        return list;
    }

    private void setDynamicColumnValueByShiftDtlId(Map map, List<PosPayTypeVO> payTypeList) {

        Map<String, String> amountMap = new HashMap<String, String>();
        List<Map> list = receiptDetailMapper.selectPayTypeAmountByShiftDtlId(map.get("id").toString());
        if (list != null) {
            for (Map map2 : list) {
                amountMap.put(map2.get("pay_type_code") == null ? "" : map2.get("pay_type_code").toString(),
                        map2.get("amount") == null ? "" : map2.get("amount").toString());
            }
        }
        if (payTypeList != null) {
            for (PosPayTypeVO posPayTypeVO : payTypeList) {

                map.put(posPayTypeVO.getCode(), amountMap.get(posPayTypeVO.getCode().toString()) == null ? 0 : amountMap.get(posPayTypeVO.getCode().toString()));
            }
        }
    }

    private void setDynamicColumnValueByShiftId(String id, Map map, List<PosPayTypeVO> payTypeList) {

        Map<String, String> amountMap = new HashMap<String, String>();
        List<Map> list = receiptDetailMapper.selectPayTypeAmountByShiftId(id);
        if (list != null) {
            for (Map map2 : list) {
                amountMap.put(map2.get("pay_type_id") == null ? "" : map2.get("pay_type_id").toString(),
                        map2.get("amount") == null ? "" : map2.get("amount").toString());
            }
        }

        if (payTypeList != null) {
            for (PosPayTypeVO posPayTypeVO : payTypeList) {

                map.put(posPayTypeVO.getCode(), amountMap.get(posPayTypeVO.getId().toString()) == null ? new BigDecimal(0).setScale(2).toString() : amountMap.get(posPayTypeVO.getId().toString()));
            }
        }
    }

    private String getStandCodeByShiftId(Long shiftId) {
        List<Map<String, String>> map = payeeOpeningShiftMapper.selectStandCodeByShiftId(shiftId);
        StringBuilder sb = new StringBuilder();
        if (map != null && map.size() > 0) {
            for (Map<String, String> map2 : map) {
                sb.append(map2.get("stand_code"));
                sb.append(",");
            }
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public void exportExcel(OutputStream output, List<DynamicColumnVO> list, UserVO loginUser, List<Map> maps, String excelName) {
        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getKey(), list.get(i).getTitle());
        }
        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());
        name.add(enterprise.getName());
        name.add(excelName);
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExportSecond(output, map, maps, name, titleSecond, end.toString(), false);
    }
    public Map<String, BigDecimal> getMapSum(List<Map<String, Object>> mapList){
    	if(mapList==null||mapList.size()==0) {
    		return null;
    	}
    	Map<String, BigDecimal> fieldMap = new HashMap<String, BigDecimal>();
    	for(Map<String, Object> map:mapList) {
    		Set<String> keySet = map.keySet();
    		for(String key:keySet) {
    			Object value=map.get(key);
    			if(value!=null) {
    				if(fieldMap.containsKey(key)) {
    					fieldMap.put(key, fieldMap.get(key).add(new BigDecimal(value.toString())));
    				}else {
    					fieldMap.put(key, new BigDecimal(value.toString()));
    					
    				}    				
    			}
    		}
    	}
		return fieldMap;   	
    }
}
