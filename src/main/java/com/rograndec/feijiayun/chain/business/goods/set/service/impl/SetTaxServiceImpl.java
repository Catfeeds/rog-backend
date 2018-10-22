package com.rograndec.feijiayun.chain.business.goods.set.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetTaxService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/5.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SetTaxServiceImpl implements SetTaxService {

	private static final Log logger = LogFactory.getLog(SetTaxServiceImpl.class);

	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;

	@Autowired
	private FinanceComponent financeComponent;


	@Override
	public List<GoodsTaxRate> getSetTax(Long enterpriseId) throws Exception {
		List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectAll(enterpriseId);

		return goodsTaxRates == null ? new ArrayList<>() : goodsTaxRates;
	}

	@Override
	public Result<String> insertSetTax(UserVO loginUser, GoodsTaxRate goodsTaxRate) throws Exception {
		// 编码和名称不能重复
		Result<String> result = new Result<>();

		// 汉字检查
		ChineseString.checkCode(goodsTaxRate.getCode());

		checkRepeated(goodsTaxRate,loginUser);


		goodsTaxRate.setTaxRate(goodsTaxRate.getTaxRate().setScale(2, BigDecimal.ROUND_HALF_UP));// 保留2位小数,四舍五入
		goodsTaxRate.setEnterpriseId(loginUser.getEnterpriseId());
		goodsTaxRate.setCreaterId(loginUser.getUserId());
		goodsTaxRate.setCreaterCode(loginUser.getUserCode());
		goodsTaxRate.setCreaterName(loginUser.getUserName());
		goodsTaxRate.setCreateTime(new Date());
		goodsTaxRate.setSysType(SysType.CUSTOMIZE.getCode());
		goodsTaxRate.setId(null);
		goodsTaxRate.setModifierId(loginUser.getUserId());
		goodsTaxRate.setModifierCode(loginUser.getUserCode());
		goodsTaxRate.setModifierName(loginUser.getUserName());
		goodsTaxRate.setUpdateTime(new Date());
		goodsTaxRateMapper.insertSelective(goodsTaxRate);


		financeComponent.initTaxBalance(loginUser, goodsTaxRate);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的商品设置-税率成功");
		return result;
	}

	private void checkRepeated(GoodsTaxRate goodsTaxRate, UserVO userVO) {

		List<GoodsTaxRate> taxTates = goodsTaxRateMapper.selectByCodeOrRate(userVO.getEnterpriseId(),goodsTaxRate.getCode(), null,goodsTaxRate.getId());

		if (taxTates.size() > 0) {
			throw new BusinessException("保存新增的商品设置-税率错误：编码重复");
		}

		List<GoodsTaxRate> taxTateList= goodsTaxRateMapper.selectByCodeOrRate(userVO.getEnterpriseId(),null, goodsTaxRate.getTaxRate(),goodsTaxRate.getId());

		if (taxTateList.size() > 0) {
			throw new BusinessException("保存新增的商品设置-税率错误：税率重复");
		}
	}

	@Override
	public int updateSetTax(UserVO loginUser, GoodsTaxRate goodsTaxRate) throws Exception {
		checkRepeated(goodsTaxRate, loginUser);
		goodsTaxRate.setTaxRate(null);
		goodsTaxRate.setCode(null);
		goodsTaxRate.setSysType(null);
		goodsTaxRate.setModifierId(loginUser.getUserId());
		goodsTaxRate.setModifierCode(loginUser.getUserCode());
		goodsTaxRate.setModifierName(loginUser.getUserName());
		goodsTaxRate.setUpdateTime(new Date());
		return goodsTaxRateMapper.updateByPrimaryKeySelective(goodsTaxRate);
	}

	@Override
	public boolean canDelete(Long id) throws Exception {
		List<SupplierVarieties> supplierVarieties = goodsTaxRateMapper.selectSupplierVarietiesByGoodsTaxId(id);
		return supplierVarieties.size() == 0;
	}

	@Override
	public int deleteSetTax(Long id) throws Exception {

		GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(id);
		if(goodsTaxRate == null){
			return 1;
		}

		if(goodsTaxRate.getSysType().equals(SysType.SYSTEM.getCode())){
			throw  new BusinessException(SysCode.FAIL.getCode(),"系统默认税率，不允许删除");
		}

		return goodsTaxRateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<GoodsTaxRateVO> getSetTaxVO(Long enterpriseId) throws Exception {
		List<GoodsTaxRateVO> goodsTaxRateVOs = goodsTaxRateMapper.selectAllVO(enterpriseId, null);

//		for (GoodsTaxRateVO goodsTaxRateVO : goodsTaxRateVOs) {//是否有关联数据
//
//			if(goodsTaxRateVO.getSysType().equals(SysType.SYSTEM.getCode())){
//				goodsTaxRateVO.setDeleteFlag(Boolean.FALSE);
//				goodsTaxRateVO.setUpdateFlag(Boolean.FALSE);
//			} else if(!canDelete(goodsTaxRateVO.getId())){
//				goodsTaxRateVO.setDeleteFlag(Boolean.FALSE);
//			}
//
//			//goodsTaxRateVO.setUpdateFlag(goodsTaxRateVO.getSysType().equals(SysType.SYSTEM.getCode()));
//
//		}


		return goodsTaxRateVOs == null ? new ArrayList<>() : goodsTaxRateVOs;
	}

}
