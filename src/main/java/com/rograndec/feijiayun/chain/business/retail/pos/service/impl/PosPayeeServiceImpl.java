package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosTeamMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayee;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosTeam;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayeeService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeVO;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @ClassName: PosPayeeServiceImpl   
 * @Description:  零售管理-POS管理-收款人员-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-21 17:33:09
 */
@Service
public class PosPayeeServiceImpl implements PosPayeeService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosPayeeServiceImpl.class);
	
	@Autowired
	private PosPayeeMapper posPayeeMapper;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private PosTeamMapper posTeamMapper;
	@Autowired
	private SaleMapper saleMapper;
	@Autowired
	private PayeeOpeningShiftMapper payeeOpeningShiftMapper;
	
	
	@Override
	public List<PosPayeeVO> getPosPayeeData(UserVO userVO) throws Exception {
		List<PosPayeeVO> list = posPayeeMapper.findByEnterpriseId(userVO.getEnterpriseId());
		for(PosPayeeVO vo : list) {
			vo.setShowStatus(vo.getStatus()==null?"":EnableStatus.getName(vo.getStatus()));
			if(!isDelete(userVO.getEnterpriseId(),vo.getId())){
				vo.setDeleteFlag(false);
			}
		}
		return list;
	}

	 private boolean isDelete(Long enterpriseId, Long id) throws Exception{
		PosPayee posPayee = posPayeeMapper.selectByPrimaryKey(id);
		List<Sale> sales = saleMapper.selectByEnterpriseIdByPayeeId(enterpriseId,posPayee.getPayeeId());
		List<PayeeOpeningShift> payeeOpeningShifts = payeeOpeningShiftMapper.selectByEnterpriseIdByPayeeId(enterpriseId,posPayee.getPayeeId());
		return sales.isEmpty() || payeeOpeningShifts.isEmpty();
	 }
	 
	 @Override
	 public boolean getDeleteFlag(Long enterpriseId, Long id) {
		 try {
			return isDelete(enterpriseId, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return false;
	 }

	 @Override
	public int save(PosPayeeSaveOrupdateVO posPayee, UserVO userVO) throws Exception {
		PosPayee copy = (PosPayee)EntityUtils.reflectAddSetDefaultValue(new PosPayee().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posPayee,copy);
		User user = userComponent.findUserByUserId(posPayee.getPayeeId());
		copy.setPayeeCode(user.getCode());
		copy.setPayeeName(user.getName());
		setTeam(posPayee.getTeamId(), copy);
		return posPayeeMapper.insertSelective(copy);
	}

	@Override
	public int update(PosPayeeSaveOrupdateVO posPayee,UserVO userVO) throws Exception {
		PosPayee copy = (PosPayee)EntityUtils.reflectUpdateSetDefaultValue(new PosPayee().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posPayee,copy);
		setTeam(posPayee.getTeamId(), copy);
		return posPayeeMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(Long id,UserVO userVO) throws Exception {
		if(!isDelete(userVO.getEnterpriseId(),id)){
			throw new BusinessException("该收款员被销售单和款员交班单引用,不能删除");
		}
		return posPayeeMapper.deleteByPrimaryKey(id);
	}
	
	private void setTeam(Long id,PosPayee copy) {
		if(null != id) {
			PosTeam tm = posTeamMapper.selectByPrimaryKey(id);
			if(null == tm) {
				throw new BusinessException("无班组数据");
			}
			copy.setTeamCode(tm.getCode());
			copy.setTeamName(tm.getName());
		}
	}
	
}
