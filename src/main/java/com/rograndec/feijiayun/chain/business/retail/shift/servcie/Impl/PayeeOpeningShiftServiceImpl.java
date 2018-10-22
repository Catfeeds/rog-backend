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
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.servcie.PayeeOpeningShiftService;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.ResultPageVo;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosPayeeVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosStandVO;
import com.rograndec.feijiayun.chain.business.retail.shift.vo.SelectPosTeamVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/22.
 */
@Service
public class PayeeOpeningShiftServiceImpl implements PayeeOpeningShiftService {

    @Autowired
    PayeeOpeningShiftMapper payeeOpeningShiftMapper;

    @Autowired
    PayeeOpeningShiftDetailMapper payeeOpeningShiftDetailMapper;

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
    public List<SelectPosStandVO> getSelectPosStand(UserVO loginUser) {
        Long enterpriseId = loginUser.getEnterpriseId();
        int chainType = loginUser.getChainType();
        List<SelectPosStandVO> selectPosStandVOs = payeeOpeningShiftMapper.selectPosStand(enterpriseId,chainType);
        return selectPosStandVOs == null ? new ArrayList<>() : selectPosStandVOs;
    }

    @Override
    public List<SelectPosTeamVO> getSelectPosTeam(UserVO loginUser) {
        Long enterpriseId = loginUser.getEnterpriseId();
        int chainType = loginUser.getChainType();
        List<SelectPosTeamVO> selectPosTeamVOs = payeeOpeningShiftMapper.selectPosTeam(enterpriseId,chainType);
        return selectPosTeamVOs == null ? new ArrayList<>() : selectPosTeamVOs;
    }

    @Override
    public List<SelectPosPayeeVO> getSelectPosPayee(UserVO loginUser) {
        Long enterpriseId = loginUser.getEnterpriseId();
        List<SelectPosPayeeVO> selectPosPayeeVOs = payeeOpeningShiftMapper.selectPosPayee(enterpriseId);
        return selectPosPayeeVOs == null ? new ArrayList<>() : selectPosPayeeVOs;
    }

    private void setDynamicColumnValueByShiftId(String id, Map map, List<PosPayTypeVO> payTypeList) {

        Map<String, String> amountMap = new HashMap<String, String>();
        List<Map> list = receiptDetailMapper.selectPayTypeAmountByShiftId(id);
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

    @Override
    public List<DynamicColumnVO> selectDynamicColumnBySource(Integer source, UserVO loginUser) throws Exception{

        List<DynamicColumnVO> list = getDynamicColumnBySource(source);
        Long enterpriseId = null;
        Long parentId = null;
        if(loginUser.getChainType() == ChainType.Headquarters.getCode()){
            enterpriseId = loginUser.getEnterpriseId();
        }else{
            enterpriseId = loginUser.getEnterpriseId();
            parentId = loginUser.getParentId();
        }
        //List<PosPayTypeVO> payTypeList = posPayTypeMapper.findByEnterpriseIdAndParentId(enterpriseId,parentId);
        List<PosPayTypeVO> payTypeList = posPayTypeService.getPayTypeData(loginUser);
        if (payTypeList != null) {
            DynamicColumnVO column = null;
            for (PosPayTypeVO posPayTypeVO : payTypeList) {
                if (posPayTypeVO.getStatus() == 1) {
                    column = new DynamicColumnVO();
                    column.setKey(posPayTypeVO.getCode());
                    column.setTitle(posPayTypeVO.getName());
                    list.add(column);
                }
            }
            if(source == 0 || source == 2){
                list = getOther(list);
            }
        }
        return list;
    }

    private List<DynamicColumnVO> getDynamicColumnBySource(Integer source) {

        List<DynamicColumnVO> list = new ArrayList<DynamicColumnVO>();

        switch (source) {
            case 0://门店交班分页列表
                list = getStoreShiftPage(list);
                break;
            case 1://门店交班查看分页列表
                list = getStoreShiftView(list);
                break;
            case 2://总部交班分页列表
                list = getShiftPage(list);
                break;
            case 3://总部交班查看分页列表
                list = getShiftView(list);
                break;

        }

        return list;
    }

    private List<DynamicColumnVO> getOther(List<DynamicColumnVO> list) {
        DynamicColumnVO column = new DynamicColumnVO();
        column = new DynamicColumnVO();
        column.setKey("acceptSpareMoney");
        column.setTitle("接收备用金");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("sendSpareMoney");
        column.setTitle("下放备用金");
        list.add(column);

        return list;
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

    private List<DynamicColumnVO> getShiftView(List<DynamicColumnVO> list) {

        DynamicColumnVO column = new DynamicColumnVO();
        column.setKey("storeCode");
        column.setTitle("门店编码");
        column.setSortable(true);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("storeName");
        column.setTitle("门店名称");
        list.add(column);

        list = getStoreShiftView(list);

        return list;
    }

    private List<DynamicColumnVO> getShiftPage(List<DynamicColumnVO> list) {

        DynamicColumnVO column = new DynamicColumnVO();
        column.setKey("storeCode");
        column.setTitle("门店编码");
        column.setSortable(true);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("storeName");
        column.setTitle("门店名称");
        list.add(column);

        list = getStoreShiftPage(list);

        return list;
    }

    private List<DynamicColumnVO> getStoreShiftView(List<DynamicColumnVO> list) {

        DynamicColumnVO column = new DynamicColumnVO();

        column = new DynamicColumnVO();
        column.setKey("standCode");
        column.setTitle("款台");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("teamName");
        column.setTitle("班组名称");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("payeeName");
        column.setTitle("收款人员");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("saleTime");
        column.setTitle("销售时间");
        column.setSortable(true);
        column.setWidth(160);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("saleCode");
        column.setTitle("销售单号");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("saleType");
        column.setTitle("销售类型");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("saleMode");
        column.setTitle("销售模式");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("realAmountTotal");
        column.setTitle("应收金额");
        list.add(column);

        return list;
    }

    private List<DynamicColumnVO> getStoreShiftPage(List<DynamicColumnVO> list) {

        DynamicColumnVO column = new DynamicColumnVO();
        column.setKey("createDate");
        column.setTitle("日期");
        column.setSortable(true);
        column.setWidth(160);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("standCode");
        column.setTitle("款台");
        column.setSortable(true);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("teamName");
        column.setTitle("班组名称");
        column.setSortable(true);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("payeeName");
        column.setTitle("收款人员");
        column.setSortable(true);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("openingTime");
        column.setTitle("开班时间");
        column.setWidth(160);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("shiftTime");
        column.setTitle("交班时间");
        column.setWidth(160);
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("salePens");
        column.setTitle("销售笔数");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("saleAmount");
        column.setTitle("销售金额");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("returnPens");
        column.setTitle("退货笔数");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("returnAmount");
        column.setTitle("退货金额");
        list.add(column);

        column = new DynamicColumnVO();
        column.setKey("realAmountTotal");
        column.setTitle("应收金额");
        list.add(column);

        return list;
    }


    @Override
    public List<Map> getPayeeOpeningShift(Integer pageNo, Integer pageSize, UserVO loginUser, ResultPageVo page, Date startTime, Date endTime, String orderName, String orderType,
                                          String standCode, Long teamId, String payeeName, String storeType, String storeCode, String storeName) throws Exception{

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(endTime);
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
        List<Map<String,Object>> totalRecord = payeeOpeningShiftMapper.queryCountByPayeeOpeningShiftParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, standCode, teamId, payeeName, storeType, storeCode, storeName);
        List<Map> list = payeeOpeningShiftMapper.payeeOpeningShiftVoParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, orderName, orderType, standCode, teamId, payeeName, storeType, storeCode, storeName);
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
        BigDecimal realAmountTotalS = BigDecimal.ZERO;
        if (list != null) {
            for (Map map : list) {

                String standCodes = getStandCodeByShiftId(Long.parseLong(map.get("id").toString()));
                map.put("standCode", standCodes);

                BigDecimal realAmountTotal = receiptMapper.getSumRealAmountTotalByShiftId(Long.parseLong(map.get("id").toString()));
                map.put("realAmountTotal", realAmountTotal);

                setDynamicColumnValueByShiftId(map.get("id").toString(), map, payTypeList);
            }
        }
        //计算总价钱
        Map<String, BigDecimal> sumMap = new HashMap<String, BigDecimal>();
        //固定字段汇总
        Map<String, BigDecimal> fieldMap = new HashMap<String, BigDecimal>();
        fieldMap=this.getMapSum(totalRecord);
        if (totalRecord != null && !totalRecord.isEmpty()) {
            for (Map<String,Object> paramMap : totalRecord) {
            	//动态金额汇总
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
    public List<Map> getPayeeOpeningShiftSelect(Integer pageNo, Integer pageSize, UserVO loginUser, Page page, String orderName, String orderType, Long shiftId) throws Exception{

        if (orderName != null && orderName.equals("saleTime")) {
            orderName = "sale_time";
        } else if (orderName != null && orderName.equals("storeCode")) {
            orderName = "store_code";
        } else {
            orderName = null;
            orderType = null;
        }
        Long totalRecord = payeeOpeningShiftMapper.queryCountByPayeeOpeningShiftSelectStoreParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, shiftId);
        List<Map> list = payeeOpeningShiftMapper.payeeOpeningShiftSelectVoParams(loginUser.getEnterpriseId(), page.getStart(), pageSize, orderName, orderType, shiftId);
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
                setDynamicColumnValueByShiftDtlId(map, payTypeList);
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
