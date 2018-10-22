package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosBank;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosBankAndPayTypeInitDataService;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosBankService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankVO;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.deleteValid.DeleteValidSerivce;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

 /**
 * 
 * @ClassName: PosBankServiceImpl   
 * @Description:  零售管理-POS管理-开户银行-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:34:42
 */
@Service
public class PosBankServiceImpl implements PosBankService,DeleteValidSerivce {
	
	@Autowired
	private PosBankMapper posBankMapper;

	@Autowired
	private ReceiptDetailMapper receiptDetailMapper;
	
	@SuppressWarnings("unused")
	@Autowired
	private EnterpriseBusinessService enterpriseBusinessService;
	
	@SuppressWarnings("unused")
	@Autowired
	private PosBankAndPayTypeInitDataService posBankAndPayTypeInitDataService;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosBankServiceImpl.class);

	@Override
	public List<PosBankVO> getBankData(UserVO userVO) throws Exception {
		Long enterpriseId = userVO.getEnterpriseId();
		EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
		if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())){
			//门店自主控制读取自己数据
			if(enterpriseBus.getPosSet() == 1) {
				userVO.setParentId(-1L);
				//posBankAndPayTypeInitDataService.initBankAndPayTypeData(enterpriseBus.getPosSet(), userVO, enterpriseId);
			} else {
				enterpriseId = userVO.getParentId();
			}
		}
		List<PosBankVO> list = posBankMapper.findByEnterpriseId(enterpriseId,userVO.getParentId());
		for(PosBankVO vo : list) {
			vo.setShowStatus(vo.getStatus()==null?"":EnableStatus.getName(vo.getStatus()));
			Boolean valid = valid(vo);
			vo.setDeleteFlag(valid);
			/**
			 * 系统默认无法删除修改
			 */
			if (vo.getBankType() == 1){
				vo.setUpdateFlag(false);
				vo.setDeleteFlag(false);
			}
		}
		return list;
	}

	@Override
	public void save(PosBankSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosBank copy = (PosBank)EntityUtils.reflectAddSetDefaultValue(new PosBank().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		copy.setBankType(0); // 用户自定义
		posBankMapper.insertSelective(copy);
	}

	@Override
	public void update(PosBankSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosBank copy = (PosBank)EntityUtils.reflectUpdateSetDefaultValue(new PosBank().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		posBankMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public void delete(Long id) throws Exception {
		if (receiptDetailMapper.selectHasBank(id) > 0){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前开户行被收款单引用，无法删除!");
		}
		posBankMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Long findByCode(String code,Long enterpriseId) throws Exception {
		return posBankMapper.findByCode(code,enterpriseId);
	}

	 @Override
	 public Boolean valid(Object object) {
		boolean flag = true;
		 PosBankVO vo = (PosBankVO)object;
		//被收款单引用的无法删除
		 if (receiptDetailMapper.selectHasBank(vo.getId()) > 0){
			flag = false;
		 }
		 return flag;
	 }
 }

