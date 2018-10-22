package com.rograndec.feijiayun.chain.utils.bean;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by ST on 2017/8/29.
 */
public class EntityUtils {

    /**
     * 增加的复制对象
     * @param clasz
     * @param userVO
     * @return
     * @throws Exception
     */
    public static Object reflectAddSetDefaultValue(Class clasz,UserVO userVO) throws Exception {
        Object object = clasz.newInstance();
        Method[] methods = clasz.getMethods();

        for(Method method : methods){
            if(method.getName().equals("setEnterpriseId")){
                method.invoke(object,userVO.getEnterpriseId());
            }
            if(method.getName().equals("setParentId")){
                method.invoke(object,userVO.getParentId());
            }
            if(method.getName().equals("setCreaterId")){
                method.invoke(object,userVO.getUserId());
            }
            if(method.getName().equals("setCreaterCode")){
                method.invoke(object,userVO.getUserCode());
            }
            if(method.getName().equals("setCreaterName")){
                method.invoke(object,userVO.getUserName());
            }
            if(method.getName().equals("setCreateTime")){
                method.invoke(object,new Date());
            }
            if(method.getName().equals("setModifierId")){
                method.invoke(object,userVO.getUserId());
            }
            if(method.getName().equals("setModifierCode")){
                method.invoke(object,userVO.getUserCode());
            }
            if(method.getName().equals("setModifierName")){
                method.invoke(object,userVO.getUserName());
            }
            if(method.getName().equals("setUpdateTime")){
                method.invoke(object,new Date());
            }
        }

        return object;
    }

    /**
     * 修改的复制对象
     * @param clasz
     * @param userVO
     * @return
     * @throws Exception
     */
    public static Object reflectUpdateSetDefaultValue(Class clasz,UserVO userVO) throws Exception {
        Object object = clasz.newInstance();
        Method[] methods = clasz.getMethods();

        for(Method method : methods){
//            if(method.getName().equals("setEnterpriseId")){
//                method.invoke(object,userVO.getEnterpriseId());
//            }
//            if(method.getName().equals("setParentId")){
//                method.invoke(object,userVO.getParentId());
//            }
            if(method.getName().equals("setModifierId")){
                method.invoke(object,userVO.getUserId());
            }
            if(method.getName().equals("setModifierCode")){
                method.invoke(object,userVO.getUserCode());
            }
            if(method.getName().equals("setModifierName")){
                method.invoke(object,userVO.getUserName());
            }
            if(method.getName().equals("setUpdateTime")){
                method.invoke(object,new Date());
            }
        }

        return object;
    }

    public static  <T> T setGoodsProperty(T object, Goods goods) throws Exception {
        Class clasz = object.getClass();
        Method[] methods = clasz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("setGoodsId")) {
                method.invoke(object, goods.getId());
            }
            if (method.getName().equals("setGoodsCode")) {
                method.invoke(object, goods.getCode());
            }
            if (method.getName().equals("setBarcode")) {
                method.invoke(object, goods.getBarcode());
            }
            if (method.getName().equals("setGoodsName")) {
                method.invoke(object, goods.getName());
            }
            if (method.getName().equals("setGoodsGenericName")) {
                method.invoke(object, goods.getGenericName());
            }
            if (method.getName().equals("setDosageId")) {
                method.invoke(object, goods.getDosageId());
            }
            if (method.getName().equals("setDosageName")) {
                method.invoke(object, goods.getDosageName());
            }
            if (method.getName().equals("setUnitId")) {
                method.invoke(object, goods.getUnitId());
            }
            if (method.getName().equals("setUnitName")) {
                method.invoke(object, goods.getUnitName());
            }
            if (method.getName().equals("setGoodsSpecification")) {
                method.invoke(object, goods.getSpecification());
            }
            if (method.getName().equals("setManufacturerId")) {
                method.invoke(object, goods.getManufacturerId());
            }
            if (method.getName().equals("setManufacturer")) {
                method.invoke(object, goods.getManufacturer());
            }
            if (method.getName().equals("setGoodsPlace")) {
                method.invoke(object, goods.getPlace());
            }
            if (method.getName().equals("setApprovalNumber")) {
                method.invoke(object, goods.getApprovalNumber());
            }

        }
        return object;

    }
}