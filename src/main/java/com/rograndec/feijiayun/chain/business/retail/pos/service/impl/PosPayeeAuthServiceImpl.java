package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeAuthMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayeeAuth;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayeeAuthService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: PosPayeeAuthServiceImpl   
 * @Description:  零售管理-POS管理-款员权限-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-22 10:18:46
 */
@Service
public class PosPayeeAuthServiceImpl implements PosPayeeAuthService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosPayeeAuthServiceImpl.class);
	
	@Autowired
	private PosPayeeAuthMapper posPayeeAuthMapper;
	
	@Override
	public List<PosPayeeAuthVO> getPosPayeeAuthData(UserVO userVO) throws Exception {
		List<PosPayeeAuthVO> list = posPayeeAuthMapper.findByEnterpriseId(userVO.getEnterpriseId());
		return list;
	}

	@Override
	public int save(PosPayeeAuthSaveOrupdateVO posPayeeAuth, UserVO userVO) throws Exception {
		PosPayeeAuth copy = (PosPayeeAuth)EntityUtils.reflectAddSetDefaultValue(new PosPayeeAuth().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posPayeeAuth,copy);
		return posPayeeAuthMapper.insertSelective(copy);
	}

	@Override
	public int update(PosPayeeAuthSaveOrupdateVO posPayeeAuth,UserVO userVO) throws Exception {
		PosPayeeAuth copy = (PosPayeeAuth)EntityUtils.reflectUpdateSetDefaultValue(new PosPayeeAuth().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posPayeeAuth,copy);
		return posPayeeAuthMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return posPayeeAuthMapper.deleteByPrimaryKey(id);
	}
	
}
