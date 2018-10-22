package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.RequestPurchaseReturnParamVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述：购进退出公用接口
 * Created by ST on 2017/9/15 15:07
 */
@Component
public class PurchaseReturnComponent {

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    public List<ResponsePurchaseReturnVO> getPurchaseReturnVOByPage(RequestPurchaseReturnParamVO paramVO){
        return purchaseReturnMapper.getPurchaseReturnVOByPage(paramVO);
    }

    /**
     * 分页查询购进退出的总数
     * @param paramVO
     * @return
     */
    public Integer getCountPurchaseReturnVOByPage(RequestPurchaseReturnParamVO paramVO){
        return purchaseReturnMapper.getCountPurchaseReturnVOByPage(paramVO);
    }

    /**
     * 根据id 查询购进出库明细
     * @param id
     * @return
     */
    public ResponsePurchaseReturnDetailVO getPurchaseReturnDetail(Long id){
        return purchaseReturnMapper.getPurchaseReturnDetail(id);
    }

}