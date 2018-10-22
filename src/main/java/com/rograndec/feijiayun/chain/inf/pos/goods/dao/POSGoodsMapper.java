package com.rograndec.feijiayun.chain.inf.pos.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsLotNumVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsVO;

/**
 * 
 * @ClassName: POSGoodsMapper   
 * @Description: POS选择商品
 * @author yuting.li
 * @version 1.0 
 * @date 2017年9月30日 下午2:33:39
 */
public interface POSGoodsMapper {
	
	
	/**
	 * 
	 * @Description: 分页选择商品数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月30日 下午2:36:09 
	 * @param enterpriseId
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 * @return List<SelectPOSGoodsVO>
	 */
	List<SelectPOSGoodsVO> selectGoods(@Param("enterpriseId")Long enterpriseId,@Param("param")String param);
    Integer countSelectGoods(@Param("enterpriseId")Long enterpriseId,@Param("param")String param);
    
    /**
     * 
     * @Description: 选择商品的批号
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月6日 下午3:40:00 
     * @param enterpriseId
     * @param goodsId
     * @return 
     * @return List<SelectPOSGoodsLotNumVO>
     */
    List<SelectPOSGoodsLotNumVO> selectGoodsLotNum(@Param("enterpriseId")Long enterpriseId,@Param("goodsId")Long goodsId,@Param("usableQuantity")int usableQuantity);
    
    /**
     * 
     * @Description: 选择中药接口
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月9日 下午4:23:01 
     * @param enterpriseId
     * @param param
     * @return 
     * @return List<SelectPOSGoodsVO>
     */
    List<SelectPOSGoodsVO> selectGoodsAttribute(@Param("enterpriseId")Long enterpriseId,@Param("param")String param);

}
