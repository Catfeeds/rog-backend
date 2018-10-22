package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.constant.Cosmetics;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttribute2DrugsA;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttributeCosmetics;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttributeDrugsOTCType;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttributeFood;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttributeMedicalApparatus;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttributeOthers;
import com.rograndec.feijiayun.chain.common.constant.PrescriptionDrug;

public class GoodsAttributeBuild {

public static String getGoodsAttribute(Integer businessVariiety,Integer goodsAttribute,Integer Prescriptiondrug,Integer otcType,Integer cosmetics){
		
		StringBuffer goodsAttri=new StringBuffer();
		 if (businessVariiety != null && goodsAttribute!=null) {
             switch (businessVariiety) {
                 case 0:
                     //药品
                 if (goodsAttribute == GoodsAttribute2DrugsA.PATENTMEDICINE.getCode()) {
                     //商品属性为成药
                     goodsAttri.append(GoodsAttribute2DrugsA.getName(goodsAttribute));
                     //商品属性为成药
                     if (Prescriptiondrug != null) {
                         //品种类别为0-药品，商品属性为0-成药时，prescription_drug含义为是否为处方药［0：非处方药，1：处方药］
                         goodsAttri.append("-");
                         goodsAttri.append(PrescriptionDrug.getName(Prescriptiondrug));
                         if (otcType != null) {
                             //品种类别为0-药品，商品属性为0-成药，并且为非处方药时，otc_type表示非处方药类别［0-甲类；1-乙类］
                             goodsAttri.append("-");
                             goodsAttri.append(GoodsAttributeDrugsOTCType.getName(otcType));
                         }
                     }
                 } else {
                     goodsAttri.append(GoodsAttribute2DrugsA.getName(goodsAttribute));
                 }
                 break;
             case 1:
                 //医疗器械
                 goodsAttri.append(GoodsAttributeMedicalApparatus.getName(goodsAttribute));
                 break;
             case 2:
                 //食品
                 goodsAttri.append(GoodsAttributeFood.getName(goodsAttribute));
                 break;
             case 3:
                 //化妆品
                 goodsAttri.append(GoodsAttributeCosmetics.getName(goodsAttribute));
                 if (cosmetics!=null) {
                     goodsAttri.append("-");
                     goodsAttri.append(Cosmetics.getName(cosmetics));
                 }
                 break;
             case 4:
                 //其他
                 goodsAttri.append(GoodsAttributeOthers.getName(goodsAttribute));
                 break;
             default:
                 break;
         }
		 }
		 return goodsAttri.toString();
	}
}
