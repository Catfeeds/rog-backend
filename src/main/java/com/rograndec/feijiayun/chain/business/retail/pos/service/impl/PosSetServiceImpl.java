package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSetMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSet;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosSetService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSetVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

 /**
 * 
 * @ClassName: PosSetServiceImpl   
 * @Description:  零售管理-POS管理-系统设置-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-18 17:31:49
 */
@Service
public class PosSetServiceImpl implements PosSetService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosSetServiceImpl.class);
	
	@Autowired
	private PosSetMapper posSetMapper;

	@Override
	public PosSetVO getPosSetData(Long enterpriseId) throws Exception{
		List<PosSet> posSet = posSetMapper.findByEnterpriseId(enterpriseId);
		if(null != posSet && posSet.size() > 0) {
			PosSetVO posSetVO = new PosSetVO();
			BeanUtils.copyProperties(posSetVO, posSet.get(0));
			return posSetVO;
		}
		return null;
	}

	@Override
	public void saveORupdate(PosSetVO posSetVO,UserVO loginUser) throws Exception {
		PosSet posSet = new PosSet();
		if(null != posSetVO.getId()) {
			PosSet copyPosSet = (PosSet)EntityUtils.reflectUpdateSetDefaultValue(posSet.getClass(),loginUser);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posSetVO,copyPosSet);
			posSetMapper.updateByPrimaryKeySelective(copyPosSet);
		}else {
			PosSet copyPosSet = (PosSet)EntityUtils.reflectAddSetDefaultValue(posSet.getClass(),loginUser);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posSetVO,copyPosSet);
			copyPosSet.setEnterpriseId(loginUser.getEnterpriseId());
			copyPosSet.setStatus(1);
			posSetMapper.insertSelective(copyPosSet);
		}
	}
	
}
