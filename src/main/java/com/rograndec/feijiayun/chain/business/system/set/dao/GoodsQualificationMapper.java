package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsQualificationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsQualification record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsQualification record);

    /**
     *
     * @mbg.generated
     */
    GoodsQualification selectByPrimaryKey(Long id);

    List<GoodsQualification> selectDefByEnterpriseId(@Param("enterpriseId") Long enterpriseId,@Param("list") List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsQualification record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsQualification record);

	/**  
	 * @Title: getGoodsQualificationById  
	 * @Description: 分部查询加商品资质 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<GoodsQualification>    返回类型  
	 * @throws  
	 */
	List<GoodsQualification> getGoodsQualificationById(UserVO user);

	/**  
	 * @Title: getGoodsQualification  
	 * @Description: 总部查询商品资质 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<GoodsQualification>    返回类型  
	 * @throws  
	 */
	List<GoodsQualification> getGoodsQualification(UserVO user);

	/**  
	 * @Title: addGoodsQualification  
	 * @Description: 增加商品资质 
	 * @param @param goodsQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addGoodsQualification(Map<String, Object> map);

	/**  
	 * @Title: addGoodsQualification  
	 * @Description: 增加商品资质 
	 * @param @param goodsQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateGoodsQualification(Map<String, Object> map);

	/**
	 *  根据条件查询商品的资质
	 * @param enterpriseId
	 * @param checkTypeId
	 * @param type 资质类型是否必选（0-可选；1-必选）
	 *@param status
	 * @param quId 资质id   @return
     */
	List<GoodsQualification> getGoodQuaInfo(@Param("enterpriseId") Long enterpriseId, @Param("checkTypeId") Long checkTypeId,@Param("type") Integer type, @Param("status") Integer status, @Param("quId") Long quId);


	/**
	 * 根据条件查询与商品未关联的商品资质
	 * @param enterpriseId
	 * @param goodsId
	 * @param typeMust
	 * @param status
     * @return
     */
	List<GoodsQualificationConfigVO> getQualificationNotRelateGoods(@Param("enterpriseId")Long enterpriseId,@Param("goodsdId") Long goodsId,@Param("typeMust")Integer typeMust,@Param("status")Integer status,@Param("checkTypeId")Long checkTypeId);

    GoodsQualification hasGoodsCode(@Param("code") String code, @Param("enterpriseId") Long enterpriseId);

	GoodsQualification hasGoodsName(@Param("name") String name, @Param("enterpriseId")Long enterpriseId);

    List<GoodsQualification> queryDefGoodsQualificationList();
}