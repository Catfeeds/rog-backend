package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.SupplierVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReport2ExcelVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SupplierMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Supplier record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Supplier record);

    /**
     *
     * @mbg.generated
     */
    Supplier selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Supplier record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Supplier record);

    /**
     * 根据编码查询供应商
     * @param code
     * @return
     */
    Supplier getSupplierByCode(@Param("enterpriseId") Long enterpriseId, @Param("code") String code);

	Supplier getSupplierByCode2(@Param("paramVO") CommonParamSupplierAndGoods paramSupplierAndGoods, @Param("code") String code);

    /**
     * 根据编码模糊查询供应商
     * @param code
     * @return
     */
    List<Supplier> getSupplierByLikeCode(String code);
    /**
     * 根据名称模糊查询供应商
     *
     * @param enterpriseId
     * @param name
     * @return
     */
    List<Supplier> getSupplierByLikeName(@Param("enterpriseId") Long enterpriseId, @Param("name") String name);

    /**
     * 根据检索码模糊查询供应商
     *
     * @param enterpriseId
     * @param pinyin
     * @return
     */
    List<Supplier> getSupplierByLikePinyin(@Param("enterpriseId") Long enterpriseId, @Param("pinyin") String pinyin);


	/**
	 * @Title: getDetailSupplier  
	 * @Description: 查询资质信息 
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return SupplierVo    返回类型  
	 * @throws
	 */
	Supplier getSupplierByEnterpriseId(Long enterpriseId);

	/**
	 * @Title: getDetailSupplier  
	 * @Description: 查询资质信息 
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return SupplierVo    返回类型  
	 * @throws
	 */
	SupplierDetailVO getDetailSupplier(Long id);

	/**
	 * @return 
	 * 
	 * @Title: getScope  
	 * @param @param split    设定文件
	 * @return void    返回类型  
	 * @throws
	 */
	List<BusinessScope> getScope(List list);

	/**
	 * @return 
	 * 
	 * @Title: getScope  
	 * @param @param split    设定文件
	 * @return void    返回类型  
	 * @throws
	 */
	SupplierBusinessVO getBusiness(Long supplierId);

	/**
	 * @return 
	 * 
	 * @Title: getScope  
	 * @param @param split    设定文件
	 * @return void    返回类型  
	 * @throws
	 */
	List<SupplierQulificationVO> getQulification(Long supplierId);

	List<Supplier> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
	List<SupplierVO> selectSupplier(Map<String,Object> map);

	List<Supplier> selectByEnterpriseIdAndOwnerId(@Param("enterpriseId") Long enterpriseId ,@Param("list") List<Long> list , @Param("approveStatus") Integer approveStatus);

	List<Supplier> selectByIds(List<Long> list);

	void saveDetailSupplier(Map<String, Object> map);

	void updateByPrimaryKeySelective(UserVO user);

	List<SupplierBasicVO> selectSupplierVoByParams(Map<String, Object> map);

	List<SupplierBasicVO> getBasicSupplier(Map<String, Object> map);

	List<SupplierBasicExcelVO> selectSupplierExcel(Map<String, Object> map);

	List<SupplierBasicVO> getCurrentGroupSupplier(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

	List<SupplierGroupConnectVO> selectConnectSupplier(@Param("enterpriseId") Long enterpriseId,@Param("id") Long id);

	void updateSupplier(Map<String, Object> map);

	void updateSupplierByGroupId(Long id);

	void RemoveSupplier(Long id);

	List<SupplierGroupExcelVO> ExportGroupSupplier(Long enterpriseId);

	Supplier getSupplierById(Long id);

	List<Map> selectIdNameByEnterpriseId(Long enterpriseId);

	String checkSupplier(Long enterpriseId);

	List<SupplierQualificationReport2ExcelVO> getSupplierQualificationReportExcelList(@Param("supplierReportVO")RequestParamSupplierReportVO supplierReportVO,@Param("status")Integer status);

	List<SupplierQualificationReportVO> getSupplierQualificationReportList(@Param("supplierReportVO")RequestParamSupplierReportVO supplierReportVO, @Param("status")Integer status);
	List<SupplierQualificationReportVO> getSupplierQualificationReportList2WarnSet(@Param("supplierReportVO")RequestParamSupplierReportVO supplierReportVO, @Param("status")Integer status);

    List<Supplier> selectExisitSupplier(Long ownerId);

    Supplier hasSupplierCode(@Param("code") String code, @Param("enterpriseId") Long enterpriseId);

	Supplier hasSupplierName(@Param("name")String name, @Param("enterpriseId")Long enterpriseId);

    void updateSupplierStatus(@Param("id") Long id, @Param("approvalStatus") Integer approvalStatus);
    
    Long queryCountByGroupId(Map map);

	Integer getCountSupplier(Map<String, Object> map);

    List<Supplier> selectByEnterpriseIdWithParam(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

	List<Supplier> selectHeadquartersAndDivisionByHeadquartersIdWithParam(@Param("enterpriseId") Long parentId, @Param("param") String param);

    List<OpeningPaymentReceivableDetailVO> selectByOwnerIdAndStatusAndApproveStatus(@Param("ownerId") Long ownerId,@Param("status") Integer status,@Param("approveStatus") Integer approveStatus);

	List<Supplier> getActualSalesSettlementSuppliers(Long ownerId);

	Supplier getSupplierByIdAndOwnerId(@Param("id") Long id, @Param("ownerId") Long ownerId);
}