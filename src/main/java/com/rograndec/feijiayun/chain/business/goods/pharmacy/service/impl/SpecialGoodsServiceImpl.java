package com.rograndec.feijiayun.chain.business.goods.pharmacy.service.impl;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.SpecialGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.SpecialGoods;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.SpecialGoodsService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SpecialGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.InChargeDrug;
import com.rograndec.feijiayun.chain.common.constant.SpecialCompoundPreparationsType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zeshi.sun on 2017/9/7.
 */


@Service
public class SpecialGoodsServiceImpl implements SpecialGoodsService {

    @Autowired
    SpecialGoodsMapper specialGoodsMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<SelectGoodsVO> selectGoodsVoPage(String key, Long enterpriseId, String specification, String formulationType) {

        List<SelectGoodsVO> list = specialGoodsMapper.selectGoodsVoByspecialParams(enterpriseId, key, specification, formulationType);
        return list == null ? new ArrayList<>() : list;
    }


    @Override
    public List<SpecialGoodsVO> specialGoodsVoPage(int pageNo, int pageSize, Long enterpriseId, Page page, String key, String orderName, String orderType, UserVO loginUser) {

        if(orderName != null && orderName.equals("specialGoodsType")){
            orderName = "special_goods";
        }
        Long parentId=null;
        if(loginUser.getChainType() != ChainType.Headquarters.getCode()) parentId=loginUser.getParentId();
        Long totalRecord = specialGoodsMapper.queryCountBySpecialGoods(enterpriseId, page.getStart(), pageSize, key,parentId);
        List<SpecialGoodsVO> list = specialGoodsMapper.selectGoodsVoBySpecialGoods(enterpriseId, page.getStart(), pageSize, key, orderName, orderType,parentId);
        setName(list);
        if(loginUser.getChainType() != ChainType.Selfoperatedshop.getCode())  addUpdateFlag(list, loginUser);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list == null ? new ArrayList<>() : list;
    }

    private void setName(List<SpecialGoodsVO> list) {
        if (list != null) {
            //查询专管类型
            for (SpecialGoodsVO specialGoodsVO : list) {
                //专管药品
                specialGoodsVO.setSpecialGoodsName(specialGoodsVO.getSpecialGoods() == null ? null : InChargeDrug.getName(specialGoodsVO.getSpecialGoods()));
                specialGoodsVO.setFormulationTypeName(specialGoodsVO.getFormulationType() == null ? null : SpecialCompoundPreparationsType.getName(specialGoodsVO.getFormulationType()));
                //拼接专管药品
                specialGoodsVO.setSpecialGoodsType((specialGoodsVO.getSpecialGoodsName() == null ? "" : specialGoodsVO.getSpecialGoodsName()) + (specialGoodsVO.getFormulationTypeName() == null ? "" : "-" + specialGoodsVO.getFormulationTypeName()));
            }
        }
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int insertSpecialGoods(UserVO loginUser, SpecialGoods specialGoods) throws Exception ,BusinessException {
        UserEnterpriseUtils.setUserCreateOrModify(specialGoods, loginUser, true);
        specialGoods.setId(null);
        specialGoods.setEnterpriseId(loginUser.getEnterpriseId());
        specialGoods.setParentId(loginUser.getParentId());
        specialGoods.setCreateTime(new Date());
        //
        Goods goods = goodsMapper.selectByPrimaryKey(specialGoods.getGoodsId());
        if(goods != null){
            specialGoods.setDosageId(goods.getDosageId());
            specialGoods.setDosageName(goods.getDosageName());
            specialGoods.setGoodsCode(goods.getCode());
            specialGoods.setGoodsName(goods.getName());
            specialGoods.setSpecification(goods.getSpecification());
            specialGoods.setManufacturer(goods.getManufacturer());
            specialGoods.setManufacturerId(goods.getManufacturerId());
            specialGoods.setSpecialGoods(goods.getInChargeDrug());
            specialGoods.setFormulationType(goods.getFormulationType());
            specialGoods.setUnitId(goods.getUnitId());
            specialGoods.setUnitName(goods.getUnitName());
        }
        return specialGoodsMapper.insertSelective(specialGoods);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSpecialGoods(UserVO loginUser, SpecialGoods specialGoods) throws Exception {
    	//验证是否有操作权限
        SpecialGoods dbSpecialGoods = validUpdateOrDelete(specialGoods.getId(), loginUser);
        UserEnterpriseUtils.setUserCreateOrModify(specialGoods, loginUser, false);
        Goods goods = goodsMapper.selectByPrimaryKey(specialGoods.getGoodsId());
        if(goods != null){
            specialGoods.setDosageId(goods.getDosageId());
            specialGoods.setDosageName(goods.getDosageName());
            specialGoods.setGoodsCode(goods.getCode());
            specialGoods.setGoodsName(goods.getName());
            specialGoods.setSpecification(goods.getSpecification());
            specialGoods.setManufacturer(goods.getManufacturer());
            specialGoods.setManufacturerId(goods.getManufacturerId());
            specialGoods.setSpecialGoods(goods.getInChargeDrug());
            specialGoods.setFormulationType(goods.getFormulationType());
            specialGoods.setUnitId(goods.getUnitId());
            specialGoods.setUnitName(goods.getUnitName());
            specialGoods.setLimitSaleQuantity(goods.getLimitQuantity());
        }
        //同步修改商品表的限制数量
        Goods goodsInfo = new Goods();
        goodsInfo.setId(dbSpecialGoods.getGoodsId());
        goodsInfo.setLimitQuantity(specialGoods.getLimitSaleQuantity());
        goodsMapper.updateByPrimaryKeySelective(goodsInfo);

        return specialGoodsMapper.updateByPrimaryKeySelective(specialGoods);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSpecialGoods(Long id, UserVO loginUser) throws Exception {
    	//验证是否有操作权限
    	validUpdateOrDelete(id, loginUser);
        return specialGoodsMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void canSave(SpecialGoods specialGoods, UserVO loginUser) throws Exception ,BusinessException{

        Long enterpriseId = loginUser.getEnterpriseId();
        String code = specialGoods.getGoodsCode();
        Map param = new HashMap();
        param.put("codes", code);
        param.put("enterpriseId", enterpriseId);
        List<SpecialGoods> Special = specialGoodsMapper.selectByCode(param);
        if (!Special.isEmpty())
            throw new BusinessException("编码重复,无法保存");
    }
    
    private SpecialGoods validUpdateOrDelete(Long id,UserVO loginUser) {
    	SpecialGoods specialGoods= specialGoodsMapper.selectByPrimaryKey(id);
    	if(specialGoods==null) throw new BusinessException("已删除");
    	if(!specialGoods.getEnterpriseId().equals(loginUser.getEnterpriseId()))  throw new BusinessException("无操作权限");
    	return specialGoods;
    }
    
    
    private void addUpdateFlag(List<SpecialGoodsVO> list ,UserVO loginUser) {
    	if(list!=null && !list.isEmpty()) {
    		for(SpecialGoodsVO vo:list) {
    			//若为加盟店的则可修改
    			vo.setUpdateFlag(vo.getEnterpriseId().equals(loginUser.getEnterpriseId()));
    		}
    	}
    }

    @Override
    public Long selectSpecilGoodsLimit(UserVO loginUser, String goodsId) {
        Map map=new HashMap();
        map.put("goodsId",goodsId);
        map.put("enterpriseId",loginUser.getEnterpriseId());
        map.put("parentId",loginUser.getParentId());
        return specialGoodsMapper.selectSpecilGoodsLimit(map);
    }
}
