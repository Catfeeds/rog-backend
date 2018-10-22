package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/2.
 */
@Component
public class ModifyRecordCompoent {

    public Map<String,Object> getFieldsMap(Object obj) throws Exception {
        Class newUserClazz = obj.getClass();
        Field[] feilds = newUserClazz.getDeclaredFields();
        Map<String,Object> feildMap = new HashMap<>();
        for(Field field : feilds){
            field.setAccessible(true);
            Object valObj = ReflectUtils.getValueOfGet(obj,field.getName());
            feildMap.put(field.getName(),valObj);
        }
        return feildMap;
    }
}
