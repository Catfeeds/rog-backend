package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutDetailService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutDetailVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 /**
 * 
 * @ClassName: DistrOutDetailServiceImpl   
 * @Description:  总部-配货出库-配货出库明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:58:09
 */
@Service
public class DistrOutDetailServiceImpl implements DistrOutDetailService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DistrOutDetailServiceImpl.class);
	
	@Autowired
	private DistrOutDetailMapper distrOutDetailMapper;
	
	@Override
	public List<DistrOutDetailVO> getDistrOutDetailData(UserVO userVO) throws Exception {
		List<DistrOutDetailVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(DistrOutDetailSaveOrupdateVO distrOutDetail, UserVO userVO) throws Exception {
		DistrOutDetail copy = (DistrOutDetail)EntityUtils.reflectAddSetDefaultValue(new DistrOutDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutDetail,copy);
		return distrOutDetailMapper.insertSelective(copy);
	}

	@Override
	public int update(DistrOutDetailSaveOrupdateVO distrOutDetail,UserVO userVO) throws Exception {
		DistrOutDetail copy = (DistrOutDetail)EntityUtils.reflectUpdateSetDefaultValue(new DistrOutDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutDetail,copy);
		return distrOutDetailMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return distrOutDetailMapper.deleteByPrimaryKey(id);
	}
	
}
