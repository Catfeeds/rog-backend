package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsHandleDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandleDetail;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleDetailVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsHandleDetailMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: GoodsHandleDetailServiceImpl   
 * @Description:  储存管理-商品处理-药品处理明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:49
 */
@Service
public class GoodsHandleDetailServiceImpl implements GoodsHandleDetailService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GoodsHandleDetailServiceImpl.class);
	
	@Autowired
	private GoodsHandleDetailMapper goodsHandleDetailMapper;
	
	@Override
	public List<GoodsHandleDetailVO> getGoodsHandleDetailData(UserVO userVO) throws Exception {
		List<GoodsHandleDetailVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(GoodsHandleDetailSaveOrupdateVO goodsHandleDetail, UserVO userVO) throws Exception {
		GoodsHandleDetail copy = (GoodsHandleDetail)EntityUtils.reflectAddSetDefaultValue(new GoodsHandleDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleDetail,copy);
		return goodsHandleDetailMapper.insertSelective(copy);
	}

	@Override
	public int update(GoodsHandleDetailSaveOrupdateVO goodsHandleDetail,UserVO userVO) throws Exception {
		GoodsHandleDetail copy = (GoodsHandleDetail)EntityUtils.reflectUpdateSetDefaultValue(new GoodsHandleDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleDetail,copy);
		return goodsHandleDetailMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return goodsHandleDetailMapper.deleteByPrimaryKey(id);
	}
	
}
