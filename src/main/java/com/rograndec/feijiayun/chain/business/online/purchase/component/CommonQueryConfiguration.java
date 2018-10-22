package com.rograndec.feijiayun.chain.business.online.purchase.component;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SmartSupplierDisplayDetailMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SmartSupplierDisplayMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SupplierDisplayDetailMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SupplierDisplayMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartSupplierDisplay;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SupplierDisplay;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SupplierDisplayDetail;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;

@Component
public class CommonQueryConfiguration {

	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private SmartSupplierDisplayMapper smartSupplierDisplayMapper;
	
	@Autowired
	private SmartSupplierDisplayDetailMapper smartSupplierDisplayDetailMapper;
	
	@Autowired
	private SupplierDisplayMapper supplierDisplayMapper;
	
	@Autowired
	private SupplierDisplayDetailMapper supplierDisplayDetailMapper;
	
	public List<SupplierDisplayDetail> getSupplierDisplayDetailList(
			Long enterpriseId) {
		
		Enterprise en = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		
		if(en != null){
			SupplierDisplay dis = null;
			
			if(en.getAreaId() != null && en.getAreaId() != 0){
				dis = supplierDisplayMapper.selectByAreaId(en.getAreaId());
			}
			if(dis == null && en.getCityId() != null && en.getCityId() != 0){
				dis = supplierDisplayMapper.selectByCityId(en.getCityId());
			}
			if(dis == null && en.getProvinceId() != null && en.getProvinceId() != 0){
				dis = supplierDisplayMapper.selectByProvinceId(en.getProvinceId());
			}
			if(dis != null){
				List<SupplierDisplayDetail> detailList = supplierDisplayDetailMapper.selectBySupplierDisplayId(dis.getId());
			
				return detailList;
			}
		}
		return null;
	}
	
	
	public String getSmartDisplay(Long enterpriseId) {
		List<SmartSupplierDisplay> disList = smartSupplierDisplayMapper.selectByEnterpriseId(enterpriseId);
		StringBuilder sb = new StringBuilder();
		if(disList != null && !disList.isEmpty()){
			for (SmartSupplierDisplay smartDisplay : disList) {
				sb.append(smartDisplay.getMphSupplierId()).append(",");
			}
		}
		return StringUtils.isNotBlank(sb.toString())?sb.toString().substring(0, sb.toString().length()-1):sb.toString();
	}
	
	public int getOffsetByPageNoAndPageSize(Integer pageNo, Integer pageSize){
		if(pageNo == null || pageNo <= 0 || pageSize == null || pageSize <= 0){
			return 0;
		}
		return (pageNo-1)*pageSize;
	}
	
}
