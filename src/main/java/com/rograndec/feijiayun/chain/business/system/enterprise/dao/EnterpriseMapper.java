package com.rograndec.feijiayun.chain.business.system.enterprise.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EnterprisePageVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrEnterpriseVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.SimpleStoreVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseQualificationReport2ExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.RequestParamOrgReportVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SimpleEnterpriseVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;

public interface EnterpriseMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Enterprise record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Enterprise record);

    /**
     *
     * @mbg.generated
     */
    Enterprise selectByPrimaryKey(Long id);
    Enterprise selectByPrimaryKeyEnable(Long id);

    /**
     * 根据id查下自己以及自己的子数据
     * @param id
     * @return
     */
    List<Enterprise> selectChildrenByPrimaryKey(Long id);

    List<NewSelectBean> selectHeadControlBranchByParentId(@Param("id") Long id, @Param("param") String param);

    /**
     * in 查询
     * @param list
     * @return
     */
    List<Enterprise> selectByIds(List<Long> list);


    List<StoreVO> selectShopByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Enterprise record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(Enterprise record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Enterprise record);

    /**
     * @Description: 根据条件查询总数量
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午4:42:25 
     * @param enterpriseId
     * @return 
     * @return Long
     */
	Long queryCountByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, 
			@Param("pageSize")int pageSize, @Param("chainType")Integer chainType, 
			@Param("key")String key, @Param("groupId")Integer groupId, 
			@Param("businessManName")String businessManName, @Param("distrPriceType")Integer distrPriceType, 
			@Param("storeLevelId")Integer storeLevelId, @Param("saleAreaId")Integer saleAreaId,
			@Param("saleCircleId")Integer saleCircleId, @Param("paymentProvision")Integer paymentProvision ,
							@Param("approveStatus")Integer approveStatus
	);

	/**
     * @Description: 根据条件查询StoreVo
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午4:42:25 
     * @param enterpriseId
     * @return 
     * @return Long
     */
	List<StoreVO> selectStoreVoByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, 
			@Param("pageSize")int pageSize, @Param("chainType")Integer chainType, 
			@Param("key")String key, @Param("groupId")Integer groupId, 
			@Param("businessManName")String businessManName, @Param("distrPriceType")Integer distrPriceType, 
			@Param("storeLevelId")Integer storeLevelId, @Param("saleAreaId")Integer saleAreaId,
			@Param("saleCircleId")Integer saleCircleId, @Param("paymentProvision")Integer paymentProvision,
			@Param("order")String order, @Param("sort")String sort, @Param("approveStatus")Integer approveStatus
	);

	/**
	 * @Description: 根据ID查询简单门店对象
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月31日 上午11:31:28 
	 * @param list
	 * @return 
	 * @return List<SimpleEnterpriseVO>
	 */
	List<SimpleEnterpriseVO> selectSimpleEnterpriseByIds(List<Long> list);

	/**
	 * @Description: 查询门店list
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月31日 下午8:12:15 
	 * @param enterpriseId
	 * @param chainType
	 * @param key
	 * @param groupId
	 * @param businessManName
	 * @param distrPriceType
	 * @param storeLevelId
	 * @param saleAreaId
	 * @param saleCircleId
	 * @param paymentProvisionId
	 * @return 
	 * @return List<StoreVO>
	 */
	List<StoreExportVO> selectExportStoreVoByParams(@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType, 
			@Param("key")String key, @Param("groupId")Integer groupId, 
			@Param("businessManName")String businessManName, @Param("distrPriceType")Integer distrPriceTypeId, 
			@Param("storeLevelId")Integer storeLevelId, @Param("saleAreaId")Integer saleAreaId,
			@Param("saleCircleId")Integer saleCircleId, @Param("paymentProvision")Integer paymentProvision,
			@Param("order")String order, @Param("sort")String sort);

	List<StoreVO> selectShopByPrimaryKey(Long enterpriseId);

	List<SimpleStoreVO> simpleSelectStoreVOPage(Map<String, Object> param);

	List<SimpleStoreVO> selectSimpleStoreVOsByIds(List<Long> list);

    List<Enterprise> selectUsefulEnterprise();

	List<Enterprise> selectByEnterpriseIdWithChainTypeAndDefut(@Param("enterpriseId")Long enterpriseId);

	List<Enterprise> selectByEnterpriseIdWithChainTypeAndDefutSon(@Param("enterpriseId")Long enterpriseId);
	List<Enterprise> selectByAllParent(@Param("eName") String eName);

    /**
     * 获取企业下的所有门店列表（总店-配送单-要货单位列表）
     * @param id 企业id
     * @param param
     * @return
     */
	List<DistrEnterpriseVO> getEnterpriseByParentId(@Param("id") Long id, @Param("param") String param);

    /**
     * 查门店经营范围
	 * @param enterPriseId 门店id
     * @return
     */
	String  getBusinessScopeId(Long id);

	List<EnterpriseReportVO> getEnterpriseReportList(@Param("paramOrgReportVO")RequestParamOrgReportVO paramOrgReportVO,@Param("parentId")Long parentId,@Param("status")Integer status);

	List<EnterpriseQualificationReport2ExcelVO> getEnterpriseQualificationReportList(@Param("paramOrgReportVO")RequestParamOrgReportVO paramOrgReportVO, @Param("parentId")Long parentId, @Param("status")Integer status);

	List<EnterpriseQualificationReportVO> getEnterprise2Report(@Param("paramOrgReportVO")RequestParamOrgReportVO paramOrgReportVO, @Param("parentId")Long parentId, @Param("status")Integer status);

	List<QualificationConfigVO> getQualificationConfigReport(@Param("enterpriseId") Long enterpriseId,@Param("chainType")Integer chainType);

	List<QualificationConfigVO> getQualificationConfig(@Param("enterpriseId")Long enterpriseId,@Param("list") List<Long> list);

	List<StoreVO> selectShopByBusinessId(@Param("enterpriseId") Long enterpriseId, @Param("businessId") Long businessId, @Param("business") String business);

	int updateBusinessStore(@Param("enterpriseId") Long enterpriseId, @Param("storeId") Long storeId, @Param("business") String business, @Param("businessId") Long businessId);
	int removeBusinessStore(@Param("enterpriseId") Long enterpriseId, @Param("storeId") Long storeId, @Param("business") String business, @Param("businessId") Long businessId);

	List<StoreVO> selectNotChooseShop(@Param("enterpriseId")Long enterpriseId, @Param("business") String business);

    List<Enterprise> selectEnterpriseWithSon(Long enterpriseId);
    Long queryCountByGroupId(Map map);

	List<Enterprise> selectByRgtEnterpriseId(
			String id);

	/**
	 * 获取 设备管理选择 总部控制的门店
	 * @param param
	 * @return
	 */
    List<EnterprisePageVO> getEqptMngHeadCtlEnt(Map<String, Object> param);

	Integer getTotalRecord(@Param("paramOrgReportVO")RequestParamOrgReportVO paramOrgReportVO,@Param("parentId")Long parentId,@Param("status")Integer status);

    List<Enterprise> selectDivisionByHeadquartersIdWithParam(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

	/**
	 * 获取总部下的所有自营店
	 * @param enterpriseId
	 * @return
	 */
	List<DistrEnterpriseVO> getDirectSaleStore(Long enterpriseId);

	/**
	 * 总部底下的门店
	 * @param parentId
	 * @param type
	 * @param status
	 * @param approveStatus
	 * @return
	 */
    List<OpeningPaymentReceivableDetailVO> selectByParentIdAndTypeAndStatusAndApproveStatus(@Param("parentId")Long parentId,
																   @Param("type")Integer type,
																   @Param("status")Integer status,
																   @Param("approveStatus")Integer approveStatus);

    /**
    * @Description: 根据参数获取企业:
    * @return:  
    * @Author: dongyang.du
    * @Date: 14/01/2018 
    */ 
	List<Enterprise> getEnterpriseByParam(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Description: 获取企业类型是加盟店
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年2月10日 下午4:54:33 
	 * @param type
	 * @return 
	 * @return List<Long>
	 */
	List<Long> getEnterpriseByType();
}