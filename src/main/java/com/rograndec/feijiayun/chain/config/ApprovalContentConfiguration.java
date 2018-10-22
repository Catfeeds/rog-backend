package com.rograndec.feijiayun.chain.config;


import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class ApprovalContentConfiguration{


    /**
     * 获取总部,自营店,加盟店的审批内容
     * 审批内容 总部：基础资料(员工信息、供货单位、门店信息)、商品管理(商品信息、价格调整)、采购管理(采购计划、采购订单、购进退出)、储存管理(盘点过账、其它入库、其它出库、商品销毁)、配送管理(要货计划、配货单、配进订单、配进退出)
     * 自营店：基础资料(员工信息、门店信息)、商品管理(价格调整)、储存管理(盘点过账、其它入库、其它出库、商品销毁)、配送管理(要货计划、配进订单、配进退出)
     * 自加盟店：基础资料(员工信息、供货单位、门店信息)、商品管理(商品信息、价格调整)、储存管理(盘点过账、其它入库、其它出库、商品销毁)、配送管理(要货计划、配进订单、配进退出)
     * @return
     */
    public static List<String> getContentCodes(UserVO userVO ) {

        if(null == userVO){
            return null;
        }
        /**
         * 企业类型：0-总部；1-自营店；2-加盟店
         */
        Integer chainType = userVO.getChainType();
        if(ChainType.Headquarters.getCode() == chainType){
            /**
             * 总部：基础资料(员工信息、供货单位、门店信息)、商品管理(商品信息、价格调整)、采购管理(采购计划、采购订单、购进退出)、储存管理(盘点过账、其它入库、其它出库、商品销毁)、配送管理(要货计划、配货单、配进订单、配进退出)
             */
            List<String> headquartersCodes = new ArrayList<>();
            headquartersCodes.add("01");
            headquartersCodes.add("0101");
            headquartersCodes.add("0102");
            headquartersCodes.add("0103");
            headquartersCodes.add("02");
            headquartersCodes.add("0201");
            headquartersCodes.add("0202");
            headquartersCodes.add("03");
            headquartersCodes.add("0301");
            headquartersCodes.add("0302");
            headquartersCodes.add("0303");
            headquartersCodes.add("04");
            headquartersCodes.add("0401");
            headquartersCodes.add("0402");
            headquartersCodes.add("0403");
            headquartersCodes.add("0404");
            headquartersCodes.add("05");
            headquartersCodes.add("0501");
            headquartersCodes.add("0502");
            headquartersCodes.add("0503");
            headquartersCodes.add("0504");
            return headquartersCodes;
        }else if(ChainType.Selfoperatedshop.getCode() == chainType){
            /**
             * 自营店：基础资料(员工信息、--门店信息)、商品管理(价格调整)、储存管理(盘点过账、其它入库、其它出库、商品销毁)、配送管理(要货计划、配进订单、配进退出)
             *
             */
            List<String> selfoperatedShopCodes = new ArrayList<>();
            selfoperatedShopCodes.add("01");
            selfoperatedShopCodes.add("0102");
            //selfoperatedShopCodes.add("0103");
            selfoperatedShopCodes.add("02");
            selfoperatedShopCodes.add("0202");
            selfoperatedShopCodes.add("04");
            selfoperatedShopCodes.add("0401");
            selfoperatedShopCodes.add("0402");
            selfoperatedShopCodes.add("0403");
            selfoperatedShopCodes.add("0404");
            selfoperatedShopCodes.add("05");
            selfoperatedShopCodes.add("0501");
            selfoperatedShopCodes.add("0503");
            selfoperatedShopCodes.add("0504");
            return selfoperatedShopCodes;
        }else if(ChainType.Division.getCode() == chainType){
            /**
             * 自加盟店：基础资料(员工信息、供货单位、门店信息)、商品管理(商品信息、价格调整)、储存管理(盘点过账、其它入库、其它出库、商品销毁)、配送管理(要货计划、配进订单、配进退出)
             */
            List<String> divisionCodes = new ArrayList<>();
            divisionCodes.add("01");
            divisionCodes.add("0101");
            divisionCodes.add("0102");
            //divisionCodes.add("0103");
            divisionCodes.add("02");
            divisionCodes.add("0201");
            divisionCodes.add("0202");
            divisionCodes.add("04");
            divisionCodes.add("0401");
            divisionCodes.add("0402");
            divisionCodes.add("0403");
            divisionCodes.add("0403");
            divisionCodes.add("0404");
            divisionCodes.add("05");
            divisionCodes.add("0501");
            divisionCodes.add("0503");
            divisionCodes.add("0504");
            return divisionCodes;
        }
        return null;
    }


    
}