package com.rograndec.feijiayun.chain.business.storage.split.service.impl;

import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.split.service.SplitDetailService;
import com.rograndec.feijiayun.chain.business.storage.split.vo.ResponseSplitDetailVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.ResponseSplitVO;
import com.rograndec.feijiayun.chain.common.component.SplitDetailExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：dongdong.zhang@rograndec.com <br/>
 * 生成日期：2017/9/27 <br/>
 * 描述：储存管理-拆零设置
 */
@Service
public class SplitDetailServiceImpl implements SplitDetailService {
	
	@Autowired
	SplitDetailMapper splitDetailMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	SplitDetailExcelComponent splitDetailExcelComponent;
	@Override
	public ResponseSplitVO getSplitOrderDtlList(Long id) {
		ResponseSplitVO responseSplitVO=splitDetailMapper.getSplitOrderDtlList(id);
		BigDecimal quantityTotal=BigDecimal.ZERO;
		BigDecimal splitQuantityTotal=BigDecimal.ZERO;
		if(responseSplitVO==null) return null;
		List<ResponseSplitDetailVO> list=responseSplitVO.getSplitDetailList();
		if(list!=null&&!list.isEmpty()){
			for(ResponseSplitDetailVO splitDetail:list){
				quantityTotal=quantityTotal.add(splitDetail.getQuantity());
				splitQuantityTotal=splitQuantityTotal.add(splitDetail.getTargetQuantity());
			}
		}
		responseSplitVO.setQuantityTotal(quantityTotal);
		responseSplitVO.setSplitQuantityTotal(splitQuantityTotal);;
		return responseSplitVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output,ResponseSplitVO responseSplitVO,UserVO user) {
		LinkedHashMap<String,String> map=new LinkedHashMap<String,String>();
		map.put("goodsCode","商品编码");
		map.put("goodsName","通用名称");
		map.put("dosageName","剂型");
		map.put("manufacturer","生产厂商");
		map.put("specification","规格");
		map.put("unitName","单位");
		map.put("shelfName","货位");
		map.put("quantity","数量");
		map.put("shelfStatusDesc","质量状况");
		map.put("splitGoodsCode","编码");
		map.put("splitSpecification","规格");
		map.put("splitUnitName","单位");
		map.put("splitShelfName","货位");
		map.put("targetQuantity","数量");
		map.put("splitShelfStatusDesc","质量状况");
		splitDetailExcelComponent.commExcelExport(output, responseSplitVO, map, responseSplitVO.getSplitDetailList(),user);
	}

}
