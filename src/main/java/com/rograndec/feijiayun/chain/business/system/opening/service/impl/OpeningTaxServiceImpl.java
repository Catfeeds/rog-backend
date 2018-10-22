package com.rograndec.feijiayun.chain.business.system.opening.service.impl;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.common.OpeningConstant;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningTaxDetailMapper;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningTaxMapper;
import com.rograndec.feijiayun.chain.business.system.opening.draft.DraftCache;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningTax;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningTaxDetail;
import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningTaxRowReader;
import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningTaxService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxDetailVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxExcelVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxVO;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.OpeningInventoryStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.GoodTaxTypeStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.FileUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OpeningTaxServiceImpl implements OpeningTaxService {

    @Autowired
    private OpeningTaxMapper openingTaxMapper;
    @Autowired
    private OpeningTaxDetailMapper openingTaxDetailMapper;
    @Autowired
    private RedisComponent redisComponent;
//    @Autowired
//    private OpeningTaxRowReader iRowReader;
    @Autowired
    private DraftCache draftCache;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
	@Autowired
	private UserComponent userComponent;
    @Autowired
    private OpenTaxExcelComponent<OpeningTaxDetailVO> openTaxExcelComponent;
    @Autowired
    private FinanceComponent financeComponent;
    @Autowired
	private CommonComponent commonComponent;

    @Override
	public OpeningTaxVO getOpeningTaxList(UserVO userVO) {
    	OpeningTaxVO openingTaxVO = openingTaxMapper.getOpeningTaxList(userVO.getEnterpriseId());
    	if(openingTaxVO != null) {
    		openingTaxVO.setIsGenerate(1);
    		List<OpeningTaxDetailVO> list = openingTaxVO.getDetailList();
    		for(OpeningTaxDetailVO vo : list) {
    			vo.setAmount(vo.getAmountSelf()+"");
    			vo.setTaxRate(vo.getTaxRateSelf().setScale(2, BigDecimal.ROUND_HALF_UP)+"");
    		}
    		return openingTaxVO;
    	}else {
    		//获取草稿拼接筛选信息
    		openingTaxVO = buildOpeningTaxVOInfo(userVO);
    	}
		return openingTaxVO;
	}
    
    /**
     * 整合草稿与数据库查询结果
     * @param openingTaxVO
     * @param userVO
     * @return
     */
    private OpeningTaxVO buildOpeningTaxVOInfo(UserVO userVO) {
    	List<OpeningTaxDetailVO> list =  openingTaxMapper.queryGoodsTaxRates(userVO.getEnterpriseId());
		for(OpeningTaxDetailVO vo : list) {
			vo.setTaxRate(vo.getTaxRateSelf().setScale(2, BigDecimal.ROUND_HALF_UP)+"");
		}
    	List<OpeningTaxDetailVO> detailList = OpeningTaxDetailVO.getList(list);
    	//获取草稿
    	OpeningTaxVO openingTaxVO = draftCache.getOpeningTaxVOCacheVO(userVO.getEnterpriseId(), OrderRule.OPENING_TAX.getCodePrefix());
    	//如果不存在草稿
    	if(openingTaxVO == null) {
    		openingTaxVO = new OpeningTaxVO();
    		openingTaxVO.setOperatorId(userVO.getUserId());
    		openingTaxVO.setOperatorName(userVO.getUserName());
    		openingTaxVO.setTaxDate(new Date());
    		openingTaxVO.setDetailList(detailList);
    		openingTaxVO.setAmountTotal(BigDecimal.ZERO);
    	}else {
    		List<OpeningTaxDetailVO> resList = new ArrayList<>();
    		List<OpeningTaxDetailVO> draftList = openingTaxVO.getDetailList();
    		BigDecimal amountTotal = BigDecimal.ZERO;
    		if(!CollectionUtils.isEmpty(draftList)) {
    			boolean a = true;
    			for(OpeningTaxDetailVO dtl:detailList) {
    				a = true;
    				for(OpeningTaxDetailVO draf:draftList) {
    					//税率和税类型相等则已草稿为准
    					if(draf.getTaxRateId().equals(dtl.getTaxRateId()) && draf.getTaxType().equals(dtl.getTaxType())) {
    						a = false;
    						draf.setTaxRate(dtl.getTaxRate());
    						draf.setTaxRateSelf(dtl.getTaxRateSelf());
    						if(draf.getAmount() != null && !"0.00".equals(draf.getAmount())) {
    							draf.setAmountSelf(new BigDecimal(draf.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
    							draf.setAmount(draf.getAmountSelf()+"");
    							amountTotal = amountTotal.add(draf.getAmountSelf());
    						}
    						resList.add(draf);
    						break;
    					}
    				}
    				if(a) {
    					/*amountTotal = amountTotal.add(dtl.getAmountSelf());*/
    					resList.add(dtl);
    				}
    			}
    		}
    		amountTotal = amountTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    		openingTaxVO.setAmountTotal(amountTotal);
    		openingTaxVO.setDetailList(resList);
    	}
    	return openingTaxVO;
    }
    
    @Override
	public void saveDraftCache(OpeningTaxVO openingTaxVO, UserVO userVO) {
    	draftCache.saveDraftCache(userVO, openingTaxVO, OrderRule.OPENING_TAX.getCodePrefix());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String save(OpeningTaxVO openingTaxVO, UserVO userVO) throws Exception {
		OpeningTaxVO validValue = openingTaxMapper.getOpeningTaxList(userVO.getEnterpriseId());
    	if(validValue != null) {
    		throw new BusinessException("期初税率数据已存在，不允许保存！");
    	}
		//验证人员
    	User user = userComponent.validationUser(userVO.getEnterpriseId(), openingTaxVO.getOperatorId());
		//验证日期
		commonComponent.validationAccountingDate(DateUtils.DateToString(openingTaxVO.getTaxDate(),DateUtils.FMT_DATE),userVO);
		
		List<OpeningTaxDetailVO> list =  openingTaxMapper.queryGoodsTaxRates(userVO.getEnterpriseId());
		List<OpeningTaxDetailVO> paramList = openingTaxVO.getDetailList();
		Map<Long,BigDecimal> map = new HashMap<>(list.size());
		for(OpeningTaxDetailVO vo:list) {
			map.put(vo.getTaxRateId(), vo.getTaxRateSelf());
		}
		
		for(OpeningTaxDetailVO vo : paramList) {
			vo.setTaxRateSelf(map.get(vo.getTaxRateId()));
		}
		
		OpeningTax openingTax = (OpeningTax) EntityUtils.reflectAddSetDefaultValue(OpeningTax.class, userVO);
		openingTax.setEnterpriseId(userVO.getEnterpriseId());
		openingTax.setParentId(userVO.getParentId());
		openingTax.setTaxDate(openingTaxVO.getTaxDate());
		openingTax.setOperatorId(user.getId());
		openingTax.setOperatorCode(user.getCode());
		openingTax.setOperatorName(user.getName());
		openingTax.setStatus(OpeningInventoryStatus.ENABLE.getCode());
		BigDecimal clearAmount = new BigDecimal(0.00);
		openingTax.setRemark(openingTaxVO.getRemark());
		String code = orderCodeComponent.generate(OrderRule.OPENING_TAX.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		openingTax.setCode(code);
		openingTax.setOrderType(OrderRule.OPENING_TAX.getType());
		openingTax.setAmountTotal(clearAmount);
		openingTax.setClearAmountTotal(clearAmount);
		openingTax.setUnclearAmountTotal(clearAmount);
		
		openingTaxMapper.insert(openingTax);
		
		BigDecimal amountTotal = new BigDecimal(0.00);
		List<OpeningTaxDetail> openingTaxDetailList = new ArrayList<>(paramList.size());
		for(OpeningTaxDetailVO vo:paramList) {
			OpeningTaxDetail openingTaxDetail =  (OpeningTaxDetail) EntityUtils.reflectAddSetDefaultValue(OpeningTaxDetail.class, userVO);
			openingTaxDetail.setEnterpriseId(userVO.getEnterpriseId());
			openingTaxDetail.setParentId(userVO.getParentId());
			openingTaxDetail.setTaxId(openingTax.getId());
			openingTaxDetail.setFinanceAccountType(5);
			openingTaxDetail.setTaxType(vo.getTaxType());
			openingTaxDetail.setTaxRateId(vo.getTaxRateId());
			openingTaxDetail.setTaxRate(vo.getTaxRateSelf());
			BigDecimal amount = new BigDecimal(vo.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
			openingTaxDetail.setAmount(amount);
			openingTaxDetail.setClearAmount(clearAmount);
			openingTaxDetail.setUnclearAmount(amount);
			openingTaxDetail.setStatus(OpeningInventoryStatus.ENABLE.getCode());
			openingTaxDetail.setRemark(vo.getRemark());
			amountTotal = amountTotal.add(amount);
			openingTaxDetailMapper.insert(openingTaxDetail);
			openingTaxDetailList.add(openingTaxDetail);
		}
		amountTotal = amountTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		OpeningTax openingTax1 = new OpeningTax();
		openingTax1.setId(openingTax.getId());
		openingTax1.setAmountTotal(amountTotal);
		openingTax1.setUnclearAmountTotal(amountTotal);
		openingTaxMapper.updateByPrimaryKeySelective(openingTax1);
		openingTax.setAmountTotal(amountTotal);
		openingTax.setUnclearAmountTotal(amountTotal);
		
		financeComponent.openingTaxToBalanceAndVoucher(userVO, openingTax, openingTaxDetailList);
		//期初税额操作完成后清空草稿
		draftCache.removerDraftCache(userVO.getEnterpriseId(),OrderRule.OPENING_TAX.getCodePrefix());
		return code;
	}

	@Override
	public void exportOpeningTaxModel(UserVO userVO, OutputStream outPut) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("taxTypeName","税类型");
		map.put("taxRate","税率");
		map.put("direction","方向");
		map.put("amount","金额");
		List<OpeningTaxDetailVO> list =  openingTaxMapper.queryGoodsTaxRates(userVO.getEnterpriseId());
		List<OpeningTaxDetailVO> detailList = OpeningTaxDetailVO.getList(list);
    	
		//下载模板默认为借金额为0.00
		for(OpeningTaxDetailVO vo:detailList) {
			if(vo.getTaxType().equals(GoodTaxTypeStatus.TAX_IN.getCode())) {
				vo.setDirection("借");
			}else {
				vo.setDirection("贷");
			}
			vo.setAmount("0.00");
			vo.setTaxRate(OpeningTaxDetailVO.getTaxRateWithOutZero(vo.getTaxRateSelf())+"%");
		}
		openTaxExcelComponent.commExcelExportModel(outPut, map,detailList, userVO);
	}

	@Override
	public void exportOpeningTax(UserVO userVO, OutputStream outPut) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("taxTypeName","税类型");
		map.put("taxRate","税率");
		map.put("direction","方向");
		map.put("amount","金额");
		OpeningTaxVO openingTaxVO = openingTaxMapper.getOpeningTaxList(userVO.getEnterpriseId());
		if(openingTaxVO == null) {
			openingTaxVO = new OpeningTaxVO();
			openingTaxVO.setAmountTotal(BigDecimal.ZERO);
			openingTaxVO.setDetailList(new ArrayList<>());
		}
		List<OpeningTaxDetailVO> detailList = openingTaxVO.getDetailList();
		for(OpeningTaxDetailVO vo: detailList) {
			vo.setAmount(vo.getAmountSelf().setScale(2, BigDecimal.ROUND_HALF_UP).abs()+"");
			vo.setDirection(DirectionUtils.getDirection(vo.getAmountSelf()));
			vo.setTaxRate(OpeningTaxDetailVO.getTaxRateWithOutZero(vo.getTaxRateSelf())+"%");
		}
		openTaxExcelComponent.commExcelExport(outPut, map, openingTaxVO.getDetailList(), userVO, openingTaxVO);
		
	}

	@Override
	public OpeningTaxExcelVO excelImportTax(HttpServletRequest request)  throws Exception{
		OpeningTaxRowReader iRowReader = SpringBeanFactory.getBean(OpeningTaxRowReader.class);
		 HttpSession session = request.getSession(true);
	     UserVO userVO = (UserVO) session.getAttribute("user");
	     Long enterpriseId = userVO.getEnterpriseId();
	     List<OpeningTaxDetailVO> list =  openingTaxMapper.queryGoodsTaxRates(enterpriseId);
	     Map<String, OpeningTaxDetailVO> map = OpeningTaxDetailVO.getMap(list);
         //合格数据
         List<OpeningTaxDetailVO> qualifiedList = new ArrayList<>();
         //不合格数据
         List<OpeningTaxDetailVO> disqualificationList = new ArrayList<>();
         iRowReader.setUnitMap(map);
         iRowReader.setQualifiedList(qualifiedList);
         iRowReader.setDisqualificationList(disqualificationList);
         iRowReader.setUnitError(ExcelErrorCodeEnum.TAXRATE_NOT_EXSIST.getName());

         Part part = request.getPart("file");
         InputStream inputStream = part.getInputStream();
         ExcelReaderUtil.excelToArrayList(iRowReader, FileUtils.getFileName(part), inputStream, 4, 0);
	     
         OpeningTaxExcelVO openingTaxExcelVO = new OpeningTaxExcelVO();
	     
         openingTaxExcelVO.setQualifiedCount(qualifiedList.size());
         openingTaxExcelVO.setDisqualificationCount(disqualificationList.size());

         Long key = System.currentTimeMillis();
         redisComponent.set(OpeningConstant.OPENING_TAX_QUALIFIED + enterpriseId + key, JSON.toJSONString(qualifiedList));
         redisComponent.set(OpeningConstant.OPENING_TAX_DISQUALIFIED + enterpriseId + key, JSON.toJSONString(disqualificationList));
         openingTaxExcelVO.setKey(key.toString());
	     
		return openingTaxExcelVO;
	}

	@Override
	public void exportUnqualified(OutputStream outputStream, String key, Integer type, UserVO userVO) {
		List<OpeningTaxDetailVO> list = new ArrayList<>();
		Long enterpriseId = userVO.getEnterpriseId();
        if (type == 2) {
            list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_TAX_DISQUALIFIED + enterpriseId + key), OpeningTaxDetailVO.class);
        } else {
            list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_TAX_QUALIFIED + enterpriseId + key), OpeningTaxDetailVO.class);
        }
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("taxTypeName","税类型");
		map.put("taxRate","税率");
		map.put("direction","方向");
		map.put("amount","金额");
		openTaxExcelComponent.commExcelExportModel(outputStream, map,list, userVO);
		
	}

	@Override
	public void continueToImport(OpeningTaxVO openingTaxVO, String key, UserVO userVO) {
		List<OpeningTaxDetailVO> excelVOS = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_TAX_QUALIFIED + userVO.getEnterpriseId() + key), OpeningTaxDetailVO.class);
		List<OpeningTaxDetailVO> paramVOs = openingTaxVO.getDetailList();
		
		reBuildContinueToImportInfo(openingTaxVO,excelVOS, paramVOs, userVO);
	}
	
	private OpeningTaxVO reBuildContinueToImportInfo(OpeningTaxVO openingTaxVO,List<OpeningTaxDetailVO> excelVOS, List<OpeningTaxDetailVO> paramVOs, UserVO userVO) {
		List<OpeningTaxDetailVO> resList = new ArrayList<>();
		BigDecimal amountTotal = BigDecimal.ZERO;
		List<OpeningTaxDetailVO> list =  openingTaxMapper.queryGoodsTaxRates(userVO.getEnterpriseId());
		for(OpeningTaxDetailVO vo : list) {
			vo.setTaxRate(vo.getTaxRateSelf().setScale(2, BigDecimal.ROUND_HALF_UP)+"");
		}
    	List<OpeningTaxDetailVO> detailList = OpeningTaxDetailVO.getList(list);
		if(!CollectionUtils.isEmpty(paramVOs)) {
			boolean a = true;
			for(OpeningTaxDetailVO dtl:detailList) {
				a = true;
				for(OpeningTaxDetailVO draf:paramVOs) {
					//税率和税类型相等则已前端为准
					if(draf.getTaxRateId().equals(dtl.getTaxRateId()) && draf.getTaxType().equals(dtl.getTaxType())) {
						a = false;
						draf.setTaxRate(dtl.getTaxRate());
						draf.setTaxRateSelf(dtl.getTaxRateSelf());
						resList.add(draf);
						break;
					}
				}
				if(a) {
					resList.add(dtl);
				}
			}
		}
		List<OpeningTaxDetailVO> resListFinal = new ArrayList<>();
		if(!CollectionUtils.isEmpty(excelVOS)) {
			boolean a = true;
			for(OpeningTaxDetailVO dtl:resList) {
				String dtlTaxRate = OpeningTaxDetailVO.getTaxRateWithOutZero(dtl.getTaxRateSelf())+"%";
				a = true;
				for(OpeningTaxDetailVO excel:excelVOS) {
					//税率和税类型相等则已前端为准
					if(excel.getTaxRate().equals(dtlTaxRate) && excel.getTaxType().equals(dtl.getTaxType())) {
						a = false;
						dtl.setDirection(excel.getDirection());
						dtl.setAmountSelf(new BigDecimal(excel.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
						dtl.setAmount(dtl.getAmountSelf()+"");
						resListFinal.add(dtl);
						break;
					}
				}
				if(a) {
					dtl.setAmountSelf(new BigDecimal(dtl.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
					resListFinal.add(dtl);
				}
			}
		}
		for(OpeningTaxDetailVO vo : resListFinal) {
			amountTotal = amountTotal.add(vo.getAmountSelf());
		}
		openingTaxVO.setAmountTotal(amountTotal);
		openingTaxVO.setDetailList(resListFinal);
		return openingTaxVO;
	}

}
