package com.rograndec.feijiayun.chain.business.retail.prescription.service;

import com.rograndec.feijiayun.chain.business.basic.user.vo.UserForPrescVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberForPrescVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.SaleForPrescrVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Created by ST on 2017/9/22 14:50
 */
public interface PrescriptionRegisterService {

    /**
     * 处方登记列表查询
     * @param page
     * @param userVO
     */
    void getPrescriptionList(Page<List<ResponsePrescriptionRegisterForListVO>> page, RequestParamForListVO paramForListVO, UserVO userVO);

    /**
     * 根据处方登记id 查询详情
     * @param id
     * @return
     */
    ResponsePrescriptionRegisterForDetailVO getDetailById(Long id,Long enterpriseId);

//    /**
//     * 商品查询
//     * @param enterpriseId
//     * @param param
//     * @return
//     */
//    List<GoodsInfoAndStockVO> getGoodsInfoForStock(Long enterpriseId, String param);

//    /**
//     * 查询商品的货位信息
//     * @param enterpriseId
//     * @param goodsId
//     * @return
//     */
//    List<GoodsInfoStockShelfVO> getGoodsStockInfoByGoodsId(Long enterpriseId, Long goodsId);

    /**
     * 处方登记新增
     * @param registerForAddUpdateVO
     */
    void  addPrescription(RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO,UserVO userVO) throws Exception;

    /**
     * 处方登记修改
     * @param registerForAddUpdateVO
     */
    void  updatePrescription(RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO,UserVO userVO) throws Exception;

    /**
     * 查询会员信息
     * @param enterpriseId
     * @param param
     * @return
     */
    List<MemberForPrescVO> getMemberInfoForPresc(Long enterpriseId, String param);

    /**
     * 查询会员信息
     * @param enterpriseId
     * @param setType 设置类型（0-用法；1-用量；2-单次计量；3-注意事项）
     * @return
     */
    List<PharmacySetForPrescVO> getPharmacySetInfo(Long enterpriseId, Integer setType);

    /**
     * 审批
     * @return
     */
    void updateAuditorDrug(UserVO userVO, RequestOperateParamVO operateParamVO);

    /**
     * 调剂
     * @return
     */
    void updateSwapDrug(UserVO userVO, RequestOperateParamVO operateParamVO);

    /**
     * 取消
     * @return
     */
    void updateForCancel(UserVO userVO, Long id);

    /**
     * 查询签章管理列表
     * @param enterpriseId
     * @param page
     */
    void getSignatureList(UserVO userVO, Page<List<ResponsePrescriptionSignatureForListVO>> page);

    /**
     * 签章增加
     * @param signatureVO
     * @param userVO
     * @return
     * @throws Exception
     */
    int addPrescriptionSignature(ResponsePrescriptionSignatureForAddVO signatureVO, UserVO userVO) throws Exception;

    /**
     * 签章修改
     * @param signatureVO
     * @param userVO
     * @return
     * @throws Exception
     */
    int updatePrescriptionSignature(ResponsePrescriptionSignatureForAddVO signatureVO, UserVO userVO) throws Exception;

    /**
     * 签章管理增加中 查询用户
     * @param param
     * @param enterpriseId
     * @return
     */
    List<UserForPrescVO> getUserInfo(String param,Long enterpriseId,Integer prescriptionType);

    /**
     * 删除签章记录
     * @param id
     * @return
     */
    int deletePrescriptionSignature(Long id);

    /**
     * 根据用户id，企业id，处方操作类型查询绑定人数
     * @param userId
     * @param enterpriseId
     * @param prescriptionType
     * @return
     */
    int getCountSignatureByUserId(Long userId, Long enterpriseId,Integer prescriptionType);


    /**
     * 查询调配、核对、发药人员
     * @param userVO
     * @return
     */
    ResponseSwapCheckerSendVO getSwapCheckerSendInfo(UserVO userVO);

    Map getAuditUser(UserVO userVO);

    /**
     * 获取销售单列表
     * @param enterpriseId
     * @return
     */
    void getSaleForPrescrList(Page<List<SaleForPrescrVO>> page, @Param("startDate") String startDate, @Param("endDate") String endDate, Long enterpriseId);

    /**
     * 获取销售单详情
     * @param enterpriseId
     * @param salesId
     * @return
     */
    List<ResponsePrescriptionRegisterDetailForDetailVO> getSaleDetailForPrescrBySaleId(Long enterpriseId, Long salesId);

    PrescriptionRegister selectByPrimaryKey(Long id);

    void exportPrescriptionRecord(OutputStream output, Long enterpriseId, Long id) throws Exception;

    List<SelectPricingGoodsVO> selectGoods(UserVO userVO, String param, Integer prescriptionType);
}
