package com.rograndec.feijiayun.chain.business.retail.saleflow.dao;

import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.SaleForPrescrVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.RequestSaleVo;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderParamVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Sale record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Sale record);

    /**
     *
     * @mbg.generated
     */
    Sale selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Sale record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Sale record);


    /**
     * @Description: TODO根据交接班ID查询总数
     * @author liuqun
     * @version 1.0
     * @date 2017年9月21日 下午7:23:49
     * @param id
     * @return
     * @return Long
     */
    Long querySaleCountByShiftId(Long id);

    /**
     * @Description: TODO根据交接班ID查询销售数据
     * @author liuqun
     * @version 1.0
     * @date 2017年9月21日 下午7:26:32
     * @param start
     * @param pageSize
     * @param id
     * @param order
     * @param sort
     * @return
     * @return List<Map>
     */
    List<Map> selectSaleDataByShiftId(@Param("start")int start, @Param("pageSize")Integer pageSize,
                                      @Param("id")Long id, @Param("order")String order, @Param("sort")String sort);
    List<Sale> selectByIds(List<Long> ids);

    /**
     * @Description: TODO根据交接班ID修改saas_sale状态
     * @author liuqun
     * @version 1.0
     * @date 2017年9月23日 下午3:07:23
     * @param shiftId
     * @return void
     */
    void updatePaymentFlagByShiftId(Long shiftId);

    List<SaleForPrescrVO> getSaleForPrescrList(@Param("enterpriseId")Long enterpriseId, @Param("startDate")String startDate,
                                               @Param("endDate")String endDate,@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    Integer getCountSaleForPrescrList(@Param("enterpriseId")Long enterpriseId,@Param("startDate")String startDate,@Param("endDate")String endDate);

    /**
     * <获取待登记销售单数据>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 17:10
     */
    List<Sale> listUnRegisterSale(RequestSaleVo requestSaleVo);


    /**
     * <获取待登记销售单数量>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 17:09
     */
    int countUnRegisterSale(RequestSaleVo requestSaleVo);

	List<Sale> queryByCodeAndEnterpriseId(
			@Param("saleCode")String saleCode, @Param("enterpriseId")Long enterpriseId);

	List<POSOrderVO> selectOrderPageDataByParam(@Param("param")POSOrderParamVO param, @Param("start")Integer start, 
			@Param("pageSize")Integer pageSize, @Param("enterpriseId")Long enterpriseId, @Param("saleType")Integer saleType);

	Long queryOrderCountByParam(@Param("param")POSOrderParamVO param, @Param("enterpriseId")Long enterpriseId, 
			@Param("saleType")Integer saleType);

	List<Sale> selectExecessSaleDataByEnterpriseId(Long enterpriseId);

    List<Sale> querySaleReturnByCodeAndEnterpriseId(@Param("saleCode")String saleCode, @Param("enterpriseId")Long enterpriseId);

    List<Sale> selectByEnterpriseIdByPayeeId(@Param("enterpriseId") Long enterpriseId, @Param("payeeId") Long payeeId);

    List<Sale> selectByShiftId(@Param("shiftId") Long shiftId);
}