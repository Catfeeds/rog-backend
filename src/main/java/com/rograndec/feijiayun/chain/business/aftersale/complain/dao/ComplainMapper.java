package com.rograndec.feijiayun.chain.business.aftersale.complain.dao;

import com.rograndec.feijiayun.chain.business.aftersale.complain.entity.Complain;
import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.CheckComplainVO;
import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.ComplainPageVO;
import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.GetGoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo.ComplainReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComplainMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Complain record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Complain record);

    /**
     *
     * @mbg.generated
     */
    Complain selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Complain record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Complain record);


    Long queryCountByComplainPageParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                     @Param("endTimes")String endTimes, @Param("code")String code, @Param("acceptManName")String acceptManName, @Param("name")String name);

    List<ComplainPageVO> selectByComplainPageParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                    @Param("endTimes")String endTimes, @Param("code")String code, @Param("acceptManName")String acceptManName, @Param("name")String name,
                                                    @Param("orderName")String orderName, @Param("orderType")String orderType);

    CheckComplainVO selectByComplainId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);


    List<GetGoodsVO> selectGoodsVoByParams(@Param("enterpriseId") Long enterpriseId, @Param("divisionId")Long divisionId, @Param("param") String param);

    /**
     * 报表分页查询
     * @author dongyang.du
     * @time   2017-10-23 13:54:08
     * @param requestVO
     * @return
     */
    List<ComplainPageVO> selectReportPage(ComplainReqVO requestVO);

    /**
     * 报表分页总数
     * @param requestVO
     * @return
     */
    Integer selectReportPageCount(ComplainReqVO requestVO);
}