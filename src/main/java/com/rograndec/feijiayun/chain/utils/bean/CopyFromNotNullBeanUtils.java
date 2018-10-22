package com.rograndec.feijiayun.chain.utils.bean;

import org.springframework.beans.*;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by ST on 2017/8/29.
 */
public class CopyFromNotNullBeanUtils  {

    public static String[] getNullPropertyNames ( Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    public static void copyPropertiesIgnoreNull( Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

}