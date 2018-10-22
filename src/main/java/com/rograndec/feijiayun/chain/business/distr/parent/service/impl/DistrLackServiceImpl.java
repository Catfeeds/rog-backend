package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrLackDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrLackMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLackDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrLackService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrLackSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.RequestDistrLackPram;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrLackReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrLack;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.status.DistrLackStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrLackServiceImpl
 * @Description: 总部-配货出库-缺配单-实现接口
 * @date 2017-10-07 15:58:51
 */
@Service
public class DistrLackServiceImpl implements DistrLackService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DistrLackServiceImpl.class);

	@Autowired
	private DistrLackMapper distrLackMapper;
	@Autowired
	private DistrLackDetailMapper distrLackDetailMapper;
	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;

	@Override
	public void getDistrLackData(RequestDistrLackPram requestDistrLackPram, Page page) throws Exception {
		int count = distrLackMapper.countDistrLackData(requestDistrLackPram);
		Integer sortDate = requestDistrLackPram.getSortDate();
		Integer sortCode = requestDistrLackPram.getSortCode();
		String sort = "";
		if (sortDate == null && sortCode == null) {
			sort = "";
		}
		if (sortDate != null && sortDate == 0) {
			sort += "a.send_date,";
		}
		if (sortDate != null && sortDate == 1) {
			sort += "a.send_date desc,";
		}
		if (sortCode != null && sortCode == 0) {
			sort += "a.code,";
		}
		if (sortCode != null && sortCode == 1) {
			sort += "a.code desc,";
		}
		if (!"".equals(sort)) {
			sort = sort.substring(0, sort.length() - 1);
		}
		requestDistrLackPram.setSort(sort);
		requestDistrLackPram.setPageNo(page.getStart());

		List<DistrLack> distrLackList = distrLackMapper.getDistrLackData(requestDistrLackPram);
		page.setTotalRecord(count);
		page.setResult(distrLackList);
	}


	@Override
	public int update(DistrLackSaveOrupdateVO distrLack, UserVO userVO) throws Exception {
		DistrLack copy = (DistrLack) EntityUtils.reflectUpdateSetDefaultValue(new DistrLack().getClass(), userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrLack, copy);
		return distrLackMapper.updateByPrimaryKeySelective(copy);
	}

	/**
	 * <根据id获取缺配单数据和明细数据>
	 *
	 * @param id
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/12 11:45
	 */
	@Override
	public DistrLack getDistrLackAndDetail(Long id) {
		DistrLack distrLack = distrLackMapper.selectByPrimaryKey(id);
		if (distrLack == null) return null;
		List<DistrLackDetail> distrLackDetailList = distrLackDetailMapper.getLackDetailByLackId(id);
		distrLack.setDistrLackDetailList(distrLackDetailList);
		return distrLack;
	}

	/**
	 * <取消缺配单>
	 *
	 * @param id
	 * @param userVO
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/12 11:55
	 */
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void cancel(Long id, UserVO userVO) {
		DistrLack distrLack = this.getDistrLackAndDetail(id);
		if (distrLack == null) {
			throw new BusinessException("没有该单据,请检查id=" + id);
		}
		if (distrLack.getStatus() != DistrLackStatus.WAIT_DISTR) {
			throw new BusinessException("该单据无法取消,请刷新后重试");
		}
		boolean flag=true;
		for (DistrLackDetail distrLackDetail : distrLack.getDistrLackDetailList()) {
			if(distrLackDetail.getClearQuantity().compareTo(BigDecimal.ZERO)==1){
				flag=false;
				break;
			}
		}
		if(flag){
			distrLack.setStatus(DistrLackStatus.CANCELED);
		}else{
			distrLack.setStatus(DistrLackStatus.FINISHED);
		}

		distrLackMapper.updateByPrimaryKeySelective(distrLack);
	}

	/**
	 * <导出>
	 *
	 * @param output
	 * @param userVO
	 * @param id
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/10/12 14:13
	 */
	@Override
	public void excelExport(OutputStream output, UserVO userVO, Long id) {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("goodsCode", "商品编码");
		map.put("goodsGenericName", "通用名称");
		map.put("dosageName", "剂型");
		map.put("goodsSpecification", "规格");

		map.put("unitName", "单位");
		map.put("manufacturer", "生产厂商");
		map.put("goodsPlace", "产地");

		map.put("requestQuantity", "要货数量");
		map.put("sendQuantity", "配货数量");
		map.put("lackQuantity", "缺配数量");
		DistrLack distrLack = distrLackMapper.selectByPrimaryKey(id);
		List<DistrLackDetail> distrLackDetailList = distrLackDetailMapper.getLackDetailByLackId(id);

		StringBuilder titleSecondRow = new StringBuilder();
		StringBuilder titleSecondRow1 = new StringBuilder();
		titleSecondRow.append("要货单位编码:");
		titleSecondRow.append(distrLack.getRequestUnitCode() == null ? "" : distrLack.getRequestUnitCode());
		titleSecondRow.append("     ");
		titleSecondRow.append("要货单位名称:");
		titleSecondRow.append(distrLack.getRequestUnitName() == null ? "" : distrLack.getRequestUnitName());
		titleSecondRow.append("     ");
		titleSecondRow.append("配货日期:");
		titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(distrLack.getSendDate()));
		titleSecondRow.append("     ");
		titleSecondRow.append("要货人员:");
		titleSecondRow.append(distrLack.getRequesterName() == null ? "" : distrLack.getRequesterName());
		titleSecondRow.append("     ");
		titleSecondRow1.append("缺配单号:");
		titleSecondRow1.append(distrLack.getCode() == null ? "" : distrLack.getCode());
		titleSecondRow1.append("     ");
		titleSecondRow1.append("配货类型:");
		String distrType = "";
		switch (distrLack.getDistrType()) {
			case 0:
				distrType = "总部配送";
				break;
			case 3:
				distrType = "分店间调剂";
				break;
			case 4:
				distrType = "直调配送";
				break;
		}
		titleSecondRow1.append(distrType);
		titleSecondRow1.append("     ");
		titleSecondRow1.append("要货计划单号:");
		titleSecondRow1.append(distrLack.getRequestPlanCode() == null ? "" : distrLack.getRequestPlanCode());
		titleSecondRow1.append("     ");
		List<String> name = new ArrayList<>();
		name.add(userVO.getEnterpriseName());
		name.add("缺配单");
		List<String> secondTitle = new ArrayList<>();
		secondTitle.add(titleSecondRow.toString());
		secondTitle.add(titleSecondRow1.toString());
		StringBuilder endTotal = new StringBuilder();
		BigDecimal requestQuantity = BigDecimal.ZERO;
		BigDecimal sendQuantity = BigDecimal.ZERO;
		BigDecimal lackQuantity = BigDecimal.ZERO;
		for (DistrLackDetail d : distrLackDetailList) {
			requestQuantity = requestQuantity.add(d.getRequestQuantity());
			sendQuantity = sendQuantity.add(d.getSendQuantity());
			lackQuantity = lackQuantity.add(d.getLackQuantity());
		}
		endTotal.append(requestQuantity);
		endTotal.append(";");
		endTotal.append(sendQuantity);
		endTotal.append(";");
		endTotal.append(lackQuantity);
		List<String> locationList = new ArrayList<String>();
		locationList.add("requestQuantity");
		locationList.add("sendQuantity");
		locationList.add("lackQuantity");
		purchaseGeneralComponent.commExcelDistrExport(output, map, distrLackDetailList, name, secondTitle, "", endTotal.toString(), locationList);
	}


	@Override
	public void getDistrlackList(Page<OrderReportVo<DistrLackReportVo>> page, RequestDistrLack requestDistrLack){
		if(requestDistrLack.getPageNo()!=null &&requestDistrLack.getPageSize()!=null){
			requestDistrLack.setPageNo(page.getStart());
		}
		int count=distrLackMapper.getDistrLackListCount(requestDistrLack);
		Integer sortDate=requestDistrLack.getSortDate();
		Integer sortCode=requestDistrLack.getSortCode();
		String sort="";
		if(sortDate==null&&sortCode==null){
			sort="";
		}
		if(sortDate!=null&&sortDate==0){
			sort+="a.send_date,";
		}
		if(sortDate!=null&&sortDate==1){
			sort+="a.send_date desc,";
		}
		if(sortCode!=null&&sortCode==0){
			sort+="a.code,";
		}
		if(sortCode!=null&&sortCode==1){
			sort+="a.code desc,";
		}
		if(!"".equals(sort)){
			sort=sort.substring(0,sort.length()-1);
		}
		requestDistrLack.setSort(sort);
		List<DistrLackReportVo> distrLackReportVos=distrLackMapper.getDistrLackList(requestDistrLack);
		requestDistrLack.setPageNo(null);
		requestDistrLack.setPageSize(null);
		List<DistrLackReportVo> distrLackReportVos2=distrLackMapper.getDistrLackList(requestDistrLack);
		BigDecimal requestQuantity = BigDecimal.ZERO;
		BigDecimal sendQuantity = BigDecimal.ZERO;
		BigDecimal lackQuantity = BigDecimal.ZERO;
		for (DistrLackReportVo d : distrLackReportVos2) {
			requestQuantity = requestQuantity.add(d.getRequestQuantity());
			sendQuantity = sendQuantity.add(d.getSendQuantity());
			lackQuantity = lackQuantity.add(d.getLackQuantity());
		}
		page.setTotalRecord(count);
		OrderReportVo orderReportVo=new OrderReportVo();
		orderReportVo.setDataList(distrLackReportVos);
		orderReportVo.setRequestQuantity(requestQuantity);
		orderReportVo.setSendQuantity(sendQuantity);
		orderReportVo.setLackQuantity(lackQuantity);
		page.setResult(orderReportVo);
	}

	@Override
	public void excelExportReport(OutputStream output, List<DistrLackReportVo> distrLackReportVos, UserVO userVO){
		//标题数据
		List<String> names = new ArrayList<>();
		names.add(userVO.getEnterpriseName());
		names.add("缺配单");
		//内容数据
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("sendDateStr", "日期");
		map.put("code", "单号");
		map.put("requestUnitCode", "要货单位编码");
		map.put("requestUnitName", "要货单位名称");
		map.put("requesterName", "要货人员");
		map.put("distrTypeStr", "配货类型");
		map.put("requestPlanCode", "要货计划单号");
		map.put("goodsCode", "商品编码");
		map.put("barcode", "条形码");
		map.put("goodsGenericName", "通用名称");
		map.put("goodsName", "商品名称");
		map.put("dosageName", "剂型");
		map.put("goodsSpecification", "规格");
		map.put("unitName", "单位");
		map.put("manufacturer", "生产厂商");
		map.put("goodsPlace", "产地");
		map.put("approvalNumber", "批准文号");
		map.put("requestQuantity", "要货数量");
		map.put("sendQuantity", "配货数量");
		map.put("lackQuantity", "缺配数量");
		map.put("statusStr", "状态");
		map.put("businessVarietyName", "品种类型");
		map.put("categoryName", "商品分类");
		map.put("goodsAttributeName", "商品属性");
		map.put("domesticImportDesc", "国产/进口");
		map.put("managementScopeName", "经营范围");
		map.put("specialDrugName", "特殊管理药品");
		map.put("inChargeDrugName", "专管药品");
		map.put("limitQuantity", "限购数量");
		map.put("storageTempDesc", "贮藏温度");
		map.put("storageConditionName", "贮藏条件");
		map.put("qualityPeriodDesc", "保质期");
		StringBuilder endTotal = new StringBuilder();
		BigDecimal requestQuantity = BigDecimal.ZERO;
		BigDecimal sendQuantity = BigDecimal.ZERO;
		BigDecimal lackQuantity = BigDecimal.ZERO;
		for (DistrLackReportVo d : distrLackReportVos) {
			requestQuantity = requestQuantity.add(d.getRequestQuantity());
			sendQuantity = sendQuantity.add(d.getSendQuantity());
			lackQuantity = lackQuantity.add(d.getLackQuantity());
		}
		endTotal.append(requestQuantity);
		endTotal.append(";");
		endTotal.append(sendQuantity);
		endTotal.append(";");
		endTotal.append(lackQuantity);
		List<String> locationList = new ArrayList<String>();
		locationList.add("requestQuantity");
		locationList.add("sendQuantity");
		locationList.add("lackQuantity");
		purchaseGeneralComponent.commExcelExport(output, map, distrLackReportVos, names, null, endTotal.toString(), false, locationList);
	}



}
