package com.rograndec.feijiayun.chain.business.retail.special.dao;

import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSpecialRegisterVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SpecialRegisterReportVO;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegister;
import com.rograndec.feijiayun.chain.business.retail.special.vo.RequestSpecialRegisterVo;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterVO;
import com.rograndec.feijiayun.chain.inf.pos.special.vo.POSSpecialRegisterVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SpecialRegisterMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(SpecialRegister record);

    /**
     * @mbg.generated
     */
    int insertSelective(SpecialRegister record);

    /**
     * @mbg.generated
     */
    SpecialRegister selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpecialRegister record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(SpecialRegister record);

    /**
     * <根据条件查询专管登记单数据记录数>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/22 19:33
     */
    int countSpecialRegisterData(RequestSpecialRegisterVo requestSpecialRegisterVo);

    /**
     * <根据条件查询专管登记单数据记录集合>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/22 19:33
     */
    List<SpecialRegisterVO> listSpecialRegisterData(RequestSpecialRegisterVo requestSpecialRegisterVo);

    /**
     *
     * <根据销售单id查询登记单信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 20:25
     */
    SpecialRegister getSpecialRegisterBySaleId(Long saleId);
    /**
     *
     * <取消登记单>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/26 17:52
     */

	List<POSSpecialRegisterVO> selectSpecialRegisterDataByEnterpriseId(
			@Param("enterpriseId")Long enterpriseId, @Param("status")Integer status);
	
	
	List<SpecialRegisterReportVO> getSpecialRegisterReportList(RequestSpecialRegisterVO requestSpecialRegisterVO);
	
	Integer getSpecialRegisterReportListTotalNum(RequestSpecialRegisterVO requestSpecialRegisterVO);
	
	PrescriptionReportVO<SpecialRegisterReportVO> getPrescriptionReportVo(RequestSpecialRegisterVO requestSpecialRegisterVO);
}