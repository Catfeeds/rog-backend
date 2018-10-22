package com.rograndec.feijiayun.chain.business.goods.set.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsTaxRateMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(GoodsTaxRate record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(GoodsTaxRate record);

	/**
	 *
	 * @mbg.generated
	 */
	GoodsTaxRate selectByPrimaryKey(Long id);

	List<GoodsTaxRate> selectByIds(List<Long> list);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(GoodsTaxRate record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(GoodsTaxRate record);

	List<GoodsTaxRate> selectAll(@Param("enterpriseId") Long enterpriseId);

	List<SupplierVarieties> selectSupplierVarietiesByGoodsTaxId(@Param("id") Long id);

	List<GoodsTaxRateVO> selectAllVO(@Param("enterpriseId") Long enterpriseId,@Param("status") Integer status);


	/**
	 * 检查 编码 或 税率是否重复
	 * 
	 *
	 * @param enterpriseId
	 * @param code
	 * @param taxRate
	 * @param id
     * @return
	 */
	List<GoodsTaxRate> selectByCodeOrRate(@Param("enterpriseId") Long enterpriseId, @Param("code") String code, @Param("taxRate") BigDecimal taxRate, @Param("id") Long id);

	List<GoodsTaxRate> selectByCodeOrRates(@Param("enterpriseId") Long enterpriseId, @Param("list") List<BigDecimal> list);

	List<GoodsTaxRate> selectByEnterpriseIdOrRate(@Param("enterpriseId")Long enterpriseId, 
			@Param("taxRate")BigDecimal taxRate);

    Long selectDefaultTaxRate();

	Long getIdByTaxRate(BigDecimal taxRate);

	GoodsTaxRate getTaxByEnterpriseIdAndId(@Param("enterpriseId")Long enterpriseId,@Param("id")Long id);


}