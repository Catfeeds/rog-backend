package com.rograndec.feijiayun.chain.business.retail.pricing.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingParamVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingTotalVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SalePricingViewVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectMemberTypeVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsLotShelfVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.SelectLockGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


 /**
 * 
 * @ClassName: SalePricingService   
 * @Description:  零售管理-划价单-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 15:56:13
 */
public interface SalePricingService {
	
	/**
     * 
     * @Description: 划价单分页数据
     * @author yuting.li
     * @version 1.0 
     * @date 2017年9月22日 下午5:57:48 
     * @param enterpriseId
     * @return 
     * @return List<SalePricingVO>
     */
    Page<SalePricingTotalVO> getSalePricingPage(SalePricingParamVO param) throws Exception;
    
    
    SalePricingViewVO findByIdSalePricing(Long id) throws Exception;
    
    /**
     * 保存数据
     * @return
     * @throws Exception
     */
    int saveORupdate(SalePricingSaveOrupdateVO salePricingVO,UserVO userVO) throws Exception;
    
    /**
     * 根据主表id 取消数据
     * @param id
     * @return
     * @throws Exception
     */
    int cancelSalePricing(Long id) throws Exception;
    
    /**
     * 导出
     * @param output
     * @param id
     */
    void exportExcel(OutputStream output,Long id) throws Exception;
    
    /**
     * 
     * @Description: 选择商品
     * @author yuting.li
     * @version 1.0 
     * @date 2017年9月25日 下午9:03:44 
     * @param enterpriseId
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception 
     * @return Page<SelectPricingGoodsVO>
     */
    List<SelectPricingGoodsVO> selectPricingGoods(UserVO userVO,String param,int pageNo,int pageSize) throws Exception;
    
    /**
     * 
     * @Description: 选择商品的批号、货位
     * @author yuting.li
     * @version 1.0 
     * @date 2017年9月27日 上午11:48:07 
     * @param enterpriseId
     * @param goodsId
     * @return 
     * @return List<SelectPricingGoodsLotShelfVO>
     */
    List<SelectPricingGoodsLotShelfVO> selectGoodsLotShelf(Long enterpriseId,Long goodsId) throws Exception;
    
    /**
     * 
     * @Description: 选择会员
     * @author yuting.li
     * @version 1.0 
     * @date 2017年9月27日 下午4:03:03 
     * @param enterpriseId
     * @param param
     * @return 
     * @return List<SelectMemberTypeVO>
     */
    List<SelectMemberTypeVO> selectMemberType(Long enterpriseId,String param) throws Exception;
    /**
     *
     * <根据条件查询商品信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/27 19:43
     */
     List<SelectPricingGoodsVO> selectGoodsByParam(UserVO userVO,GoodsParamStockVO goodsParamStockVO);

     List<SelectPricingGoodsVO> selectLockGoodsByParam(GoodsParamStockVO goodsParamStockVO);
 }

