package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QualitySetMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(QualitySet record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(QualitySet record);

    /**
     *
     * @mbg.generated
     */
    QualitySet selectByPrimaryKey(Long id);
    QualitySet selectByCode(@Param("code") String code,@Param("setType") Integer setType);
    List<QualitySet> selectByIds(List<Long> id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(QualitySet record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(QualitySet record);

	/**  
	 * @Title: getQualitySettingsByUser  
	 * @Description: 获取质量设置所有不合格原因 
	 * @param @param map
	 * @param @return    设定文件  
	 * @return List<QualitySet>    返回类型  
	 * @throws  
	 */
	List<QualitySet> getQualitySettingsByUser(Map<String, Object> map);

	/**  
	 * @Title: addByPrimaryKey  
	 * @Description: 增加拒收原因  
	 * @param @param map    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addQualitySettings(Map<String, Object> map);

	/**  
	 * @Title: getQualitySettingsById  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param map
	 * @param @return    设定文件  
	 * @return List<QualitySet>    返回类型  
	 * @throws  
	 */
	List<QualitySet> getQualitySettingsById(Map<String, Object> map);

	/**  
	 * @Title: getQualitySettingsById  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param map
	 * @param @return    设定文件  
	 * @return List<QualitySet>    返回类型  
	 * @throws  
	 */
	void updateQualitySettings(Map<String, Object> map);

	/**  
	 * @Title: getQualitySettingsById  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param map
	 * @param @return    设定文件  
	 * @return List<QualitySet>    返回类型  
	 * @throws  
	 */
	List<QualitySet> findCheckTypes(Long enterpriseId);


	/**
	 *
	 * @param enterpriseId 企业id
	 * @param setType 设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）
	 * @param type 当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；

	当set_type=4（验收类型）时，type含义为品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）；

	当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-进口麻醉药品、精神药品以及蛋白同化制剂、肽类激素；9-其它）

	当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）

	当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）

	 * @param status
     * @return
     */
	List<QualitySetVO> getQualitySetVOList(@Param("enterpriseId")Long enterpriseId,@Param("setType")Integer setType,@Param("type")Integer type,@Param("status")Integer status);

	List<QualitySet> selectCheckProductSelector(@Param("enterpriseId")Long enterpriseId);

    QualitySet hasQualityCode(@Param("setType") Integer setType, @Param("code") String code, @Param("enterpriseId") Long enterpriseId);

	QualitySet hasQualityDescription(@Param("setType")Integer setType, @Param("description")String description, @Param("enterpriseId")Long enterpriseId);

	
	int selectPurchaseReceive(Long id);
	
	int selectDistrInReceive(Long id);
	
	
	
	int selectPurchaseCheckLotUnquantity(Long id);
	
	int selectDistrInCheckLotUnquantity(Long id);
	
	int selectGoodsMaintanceDetailUnquantity(Long id);
	
	int selectGoodsDisplayCheckDetailUnquantity(Long id);
	
	
	
	int selectPurchaseCheckLotMeasures(Long id);
	
	int selectDistrInCheckLotMeasures(Long id);
	
	int selectGoodsMaintanceDetailMeasures(Long id);
	
	int selectGoodsDisplayCheckDetailMeasures(Long id);


	Integer selectMaintenceMaintenceMesures(Long id);
	int selectDisPlayMaintenceMesures(Long id);
	
	
	int selectPurchaseCheckLotProjects(Long id);
	int selectDistrInCheckLotProjects(Long id);

	int selectPurchaseCheckLotConclusion(Long id);
	int selectDistrInCheckLotConclusion(Long id);
	int selectGoodsMaintanceDetailConclusion(Long id);
	int selectGoodsDisplayCheckDetailConclusion(Long id);
	
	int selectPurchaseReturnDetailReturn(Long id);
	
	List<QualitySet> selectBySetTypeAndType(@Param("type")Long type,@Param("enterpriseId")Long enterpriseId);
	
	int selectGoodsForCheckType(@Param("id")Long id,@Param("enterpriseId")Long enterpriseId);
	
	List<QualitySetVO> selectAllByEnterpriseId(@Param("enterpriseId")Long enterpriseId);

}