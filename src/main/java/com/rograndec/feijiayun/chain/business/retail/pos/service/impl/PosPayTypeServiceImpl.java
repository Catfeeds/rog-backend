package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayType;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosBankAndPayTypeInitDataService;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayTypeService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.receipt.entity.ReceiptDetail;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.ShortcutKey;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

/**
 * 
 * @ClassName: PosPayTypeServiceImpl   
 * @Description:  零售管理-POS管理-支付方式-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:19:11
 */
@Service
public class PosPayTypeServiceImpl implements PosPayTypeService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosPayTypeServiceImpl.class);
	
	@Autowired
	private PosPayTypeMapper posPayTypeMapper;
	@Autowired
	private ReceiptDetailMapper receiptDetailMapper;
	

	@Autowired
	private EnterpriseBusinessService enterpriseBusinessService;
	
	@SuppressWarnings("unused")
	@Autowired
	private PosBankAndPayTypeInitDataService posBankAndPayTypeInitDataService;

	@Override
	public List<PosPayTypeVO> getPayTypeData(UserVO userVO) throws Exception {
		Long enterpriseId = userVO.getEnterpriseId();
		EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
		if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())){
			//门店自主控制读取自己数据
			if(enterpriseBus.getPosSet() == 1) {
				userVO.setParentId(-1L);
				posBankAndPayTypeInitDataService.initBankAndPayTypeData(enterpriseBus.getPosSet(), userVO, enterpriseId);
			} else {
				enterpriseId = userVO.getParentId();
			}
		}

		List<PosPayTypeVO> list = posPayTypeMapper.findByEnterpriseId(enterpriseId,userVO.getParentId());
		for(PosPayTypeVO vo : list) {
			vo.setShowStatus(vo.getStatus()==null?"":EnableStatus.getName(vo.getStatus()));
			vo.setShowShortcutKey(vo.getShortcutKey()==null?"":ShortcutKey.getName(vo.getShortcutKey()));
			if(vo.getPayType() == 1 || !isDelete(enterpriseId,vo.getId())){
				vo.setDeleteFlag(false);
				vo.setUpdateFlag(false);
			}
		}
		return list;
	}

	private boolean isDelete(Long enterpriseId,Long payTypeId) throws Exception{
		List<ReceiptDetail> receiptDetails = receiptDetailMapper.selectByEnterpriseIdByPayTypeId(enterpriseId,payTypeId);
		return receiptDetails.isEmpty();
	}

	@Override
	public void save(PosPayTypeSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosPayType copy = (PosPayType)EntityUtils.reflectAddSetDefaultValue(new PosPayType().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		copy.setEnterpriseId(userVO.getEnterpriseId());
		copy.setPayType(0); // 用户自定义
		posPayTypeMapper.insertSelective(copy);
	}

	@Override
	public void update(PosPayTypeSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosPayType copy = (PosPayType)EntityUtils.reflectUpdateSetDefaultValue(new PosPayType().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		posPayTypeMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id,UserVO loginUser) throws Exception {
		PosPayType posPayType = posPayTypeMapper.selectByPrimaryKey(id);
		if(posPayType.getPayType() == 1){
			throw new BusinessException("系统默认的支付方式不能删除!");
		}
		if(!isDelete(loginUser.getEnterpriseId(),id)){
			throw new BusinessException("该支付方式已经有收款单引用,不能删除!");
		}
		posPayTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Long findByCode(String code,Long enterpriseId) throws Exception {
		return posPayTypeMapper.findByCode(code,enterpriseId);
	}
	
}

