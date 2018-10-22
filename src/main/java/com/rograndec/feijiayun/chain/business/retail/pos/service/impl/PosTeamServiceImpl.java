package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayee;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosTeamMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosTeam;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosTeamService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSelectVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamVO;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;

 /**
 * 
 * @ClassName: PosTeamServiceImpl   
 * @Description:  零售管理-POS管理-班组-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:24
 */
@Service
public class PosTeamServiceImpl implements PosTeamService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosTeamServiceImpl.class);
	
	@Autowired
	private PosTeamMapper posTeamMapper;
	@Autowired
	private PosPayeeMapper posPayeeMapper;

	@Override
	public List<PosTeamVO> getBankData(UserVO userVO) throws Exception {
		List<PosTeamVO> list = posTeamMapper.findByEnterpriseId(userVO.getEnterpriseId());
		for(PosTeamVO vo : list) {
			vo.setShowStatus(vo.getStatus()==null?"":EnableStatus.getName(vo.getStatus()));
			if(vo.getTeamType() == 1 || !isDelete(userVO.getEnterpriseId(),vo.getId())){
				vo.setDeleteFlag(false);
			}
		}
		return list;
	}

	 private boolean isDelete(Long enterpriseId, Long id) {
		List<PosPayee> posPayees = posPayeeMapper.selectByEnterpriseIdByTeamId(enterpriseId,id);
		return posPayees.isEmpty();
	 }

	 @Override
	public void save(PosTeamSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosTeam copy = (PosTeam)EntityUtils.reflectAddSetDefaultValue(new PosTeam().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		copy.setTeamType(0); //用户自定义
		posTeamMapper.insertSelective(copy);
	}

	@Override
	public void update(PosTeamSaveOrupdateVO bean, UserVO userVO) throws Exception {
		PosTeam copy = (PosTeam)EntityUtils.reflectUpdateSetDefaultValue(new PosTeam().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(bean,copy);
		posTeamMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public void delete(Long id,UserVO userVO) throws Exception {
		PosTeam posTeam = posTeamMapper.selectByPrimaryKey(id);
		if(posTeam.getTeamType() == 1){
			throw new BusinessException("系统默认的班组不能删除!");
		}
		if(!isDelete(userVO.getEnterpriseId(),id)){
			throw new BusinessException("该班组已经有收款人员引用,不能删除!");
		}
		posTeamMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Long findByCode(String code,Long enterpriseId) throws Exception {
		return posTeamMapper.findByCode(code, enterpriseId);
	}

	@Override
	public List<PosTeamSelectVO> selectPosTeam(Long enterpriseId) throws Exception {
		return posTeamMapper.selectPosTeam(enterpriseId);
	}
	
}
