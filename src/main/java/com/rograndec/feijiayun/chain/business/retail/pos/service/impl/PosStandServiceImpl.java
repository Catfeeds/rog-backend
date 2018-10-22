package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosStandMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosStand;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosStandService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandVO;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

 /**
 * 
 * @ClassName: PosStandServiceImpl   
 * @Description:  零售管理-POS管理-款台-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:40:37
 */
@Service
public class PosStandServiceImpl implements PosStandService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosStandServiceImpl.class);
	
	@Autowired
	private PosStandMapper posStandMapper;

	@Override
	public List<PosStandVO> getStandData(UserVO userVO) throws Exception {
		return posStandMapper.findByEnterpriseId(userVO.getEnterpriseId());
	}

	@Override
	public void save(PosStandSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosStand copy = (PosStand)EntityUtils.reflectAddSetDefaultValue(new PosStand().getClass(),userVO);
		copy.setStatus(EnableStatus.ENABLE.getStatus());
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		posStandMapper.insertSelective(copy);
	}

	@Override
	public void update(PosStandSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosStand copy = (PosStand)EntityUtils.reflectUpdateSetDefaultValue(new PosStand().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		posStandMapper.updateByPrimaryKeySelective(copy);
	}
	
}
