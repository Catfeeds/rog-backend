package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsHandleShelfService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandleShelf;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleShelfVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsHandleShelfMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: GoodsHandleShelfServiceImpl   
 * @Description:  储存管理-商品处理-药品处理货位明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:28:01
 */
@Service
public class GoodsHandleShelfServiceImpl implements GoodsHandleShelfService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GoodsHandleShelfServiceImpl.class);
	
	@Autowired
	private GoodsHandleShelfMapper goodsHandleShelfMapper;
	
	@Override
	public List<GoodsHandleShelfVO> getGoodsHandleShelfData(UserVO userVO) throws Exception {
		List<GoodsHandleShelfVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(GoodsHandleShelfSaveOrupdateVO goodsHandleShelf, UserVO userVO) throws Exception {
		GoodsHandleShelf copy = (GoodsHandleShelf)EntityUtils.reflectAddSetDefaultValue(new GoodsHandleShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleShelf,copy);
		return goodsHandleShelfMapper.insertSelective(copy);
	}

	@Override
	public int update(GoodsHandleShelfSaveOrupdateVO goodsHandleShelf,UserVO userVO) throws Exception {
		GoodsHandleShelf copy = (GoodsHandleShelf)EntityUtils.reflectUpdateSetDefaultValue(new GoodsHandleShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleShelf,copy);
		return goodsHandleShelfMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return goodsHandleShelfMapper.deleteByPrimaryKey(id);
	}
	
}
