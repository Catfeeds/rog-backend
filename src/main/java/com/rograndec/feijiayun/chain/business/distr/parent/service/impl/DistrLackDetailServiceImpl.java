package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrLackDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLackDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrLackDetailVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrLackDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrLackDetailMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: DistrLackDetailServiceImpl   
 * @Description:  总部-配货出库-缺配单明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:59:05
 */
@Service
public class DistrLackDetailServiceImpl implements DistrLackDetailService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DistrLackDetailServiceImpl.class);
	
	@Autowired
	private DistrLackDetailMapper distrLackDetailMapper;
	
	@Override
	public List<DistrLackDetailVO> getDistrLackDetailData(UserVO userVO) throws Exception {
		List<DistrLackDetailVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(DistrLackDetailSaveOrupdateVO distrLackDetail, UserVO userVO) throws Exception {
		DistrLackDetail copy = (DistrLackDetail)EntityUtils.reflectAddSetDefaultValue(new DistrLackDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrLackDetail,copy);
		return distrLackDetailMapper.insertSelective(copy);
	}

	@Override
	public int update(DistrLackDetailSaveOrupdateVO distrLackDetail,UserVO userVO) throws Exception {
		DistrLackDetail copy = (DistrLackDetail)EntityUtils.reflectUpdateSetDefaultValue(new DistrLackDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrLackDetail,copy);
		return distrLackDetailMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return distrLackDetailMapper.deleteByPrimaryKey(id);
	}
	
}
