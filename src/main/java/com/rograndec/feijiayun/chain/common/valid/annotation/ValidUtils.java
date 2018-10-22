package com.rograndec.feijiayun.chain.common.valid.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;


public class ValidUtils {
	public static void validObject(Object object) {
		if (object == null) {
			return;
		}
		checkClass(object.getClass(),object);
	}
	private static <T> void checkClass(Class<T> cls,Object object) {
		if (object == null) {
			return;
		}
		if(cls.getSuperclass()!=null) {
			checkClass(cls.getSuperclass(),object);
		}
		checkClassValid(cls,object); 
	}
	private  static <T> void checkClassValid(Class<T> cls,Object object) {
		Field[] fieldList = cls.getDeclaredFields();
		if (fieldList != null && fieldList.length > 0) {
			for (Field f : fieldList) {
				ValidNotNull validNotNull = f.getAnnotation(ValidNotNull.class);
				ValidMin validMin = f.getAnnotation(ValidMin.class);
				ValidLength validLength = f.getAnnotation(ValidLength.class);
				ValidMax validMax = f.getAnnotation(ValidMax.class);
				ValidNotRepeat validNotRepeat = f.getAnnotation(ValidNotRepeat.class);
				String type = f.getType().toString();// 数据类型判断
				if (type.contains("java.util.List") || type.contains("java.util.Set")) {
					@SuppressWarnings("unchecked")
					Collection<Object> collection = (Collection<Object>) getFieldValue(object, f.getName());
					//非空校验
					validNotNullList(validNotNull, collection, f);
					//最小集合数量校验
					validMinList(validMin, collection, f);
					//最大集合数量校验
					validMaxList(validMax, collection, f);
					//固定集合长度校验
					validLengthList(validLength, collection, f);
					//集合重复 校验
					validNotRepeat(validNotRepeat, collection, f);
					
					if (collection != null && collection.size() > 0) {
						for (Object o : collection) {
							validObject(o);
						}
					}
				} else if (type.contains("String") || type.contains("Integer") || type.contains("Double")
						|| type.contains("Float") || type.contains("Long") || type.contains("Integer")
						|| type.contains("BigDecimal")) {
					Object objectValue = getFieldValue(object, f.getName());
					//非空校验
					validNotNullString(validNotNull, objectValue, f);
					//最小长度校验
					validMinString(validMin, objectValue, f);
					//最大长度校验
					validMaxString(validMax, objectValue, f);
					//固定长度校验
					validLengthString(validLength, objectValue, f);				
				} else {
					Object objectValue = getFieldValue(object, f.getName());
					//非空校验
					validNotNullString(validNotNull, objectValue, f);
					if (objectValue != null) {
						validObject(objectValue);
					}
				}
			}
		}
	}
	
	private static Object getFieldValue(Object object, String fieldName) {
		if (object == null) {
			return null;
		}
		if (!StringUtils.isEmpty(fieldName)) {
			String upperName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Method method = object.getClass().getMethod("get" + upperName);
				Object value = method.invoke(object);
				return value;
			} catch (Exception e) {
				// 该属性没有getter方法

			}
		}
		return null;
	}

	/**
	 * 
	 * @param validNotRepeat
	 * @param values
	 * @return
	 * @throws Exception
	 */
	private static void filterRepeat(ValidNotRepeat validNotRepeat, Collection<Object> values, Field field)
			 {
		// 只去重集合中的对象属性，不含对象属性中的list集合
		Set<Object> set=getSetName(values,validNotRepeat.value());
		if (set.size() < values.size()) {
			throw new BusinessException(field.getName() + " collection contain same object!");
		}
	}

	/**
	 * 校验list长度
	 * 
	 * @param validLength
	 * @param collection
	 */
	private static void validLengthList(ValidLength validLength, Collection<Object> collection, Field field) {
		if (validLength == null) {
			return;
		}
		if (collection == null) {
			return;
		}
		if (validLength.value() != collection.size()) {
			throw new BusinessException(field.getName() + " size should be " + validLength.value());
		}
	}

	private static void validMaxList(ValidMax validMax, Collection<Object> collection, Field field) {
		if (validMax == null) {
			return;
		}
		if (collection == null) {
			return;
		}
		if (validMax.value() < collection.size()) {
			throw new BusinessException(field.getName() + " size should lt " + validMax.value());
		}
	}

	private static void validMinList(ValidMin validMin, Collection<Object> collection, Field field) {
		if (validMin == null) {
			return;
		}
		if (collection == null) {
			return;
		}
		if (validMin.value() > collection.size()) {
			throw new BusinessException(field.getName() + "size should gt " + validMin.value());
		}
	}

	private static void validNotNullList(ValidNotNull validNotNull, Collection<Object> collection, Field field) {
		if (validNotNull == null) {
			return;
		}
		if (collection == null || collection.size() == 0) {
			throw new BusinessException(field.getName() + " size should not null");
		}
	}
	private static void validNotRepeat(ValidNotRepeat validNotRepeat, Collection<Object> collection, Field field) {
		if (validNotRepeat == null) {
			return;
		}
		if (collection == null || collection.size() == 0) {
			return;
		}
		filterRepeat(validNotRepeat, collection, field);
	}

	/**
	 * 校验字符串长度
	 * 
	 * @param validLength
	 * @param Object
	 * @param field
	 */
	private static void validLengthString(ValidLength validLength, Object Object, Field field) {
		if (validLength == null) {
			return;
		}
		if (Object == null) {
			return;
		}
		if (validLength.value() != Object.toString().length()) {
			throw new BusinessException(field.getName() + " length should be " + validLength.value());
		}
	}
	private static void validMaxString(ValidMax validMax, Object Object, Field field) {
		if (validMax == null) {
			return;
		}
		if (Object == null) {
			return;
		}
		if (validMax.value() < Object.toString().length()) {
			throw new BusinessException(field.getName() + " length should lt " + validMax.value());
		}
	}
	private static void validMinString(ValidMin validMin, Object Object, Field field) {
		if (validMin == null) {
			return;
		}
		if (Object == null) {
			return;
		}
		if (validMin.value() > Object.toString().length()) {
			throw new BusinessException(field.getName() + " length should gt " + validMin.value());
		}
	}
	private static void validNotNullString(ValidNotNull validNotNull, Object Object, Field field) {
		if (validNotNull == null) {
			return;
		}
		if (Object == null) {
			throw new BusinessException(field.getName() + " should not null");
		}
	}

	private static Set<Object> getSetName(Collection<Object> collection,String fieldName) {
		Set<Object> set=new HashSet<Object>();
		if(collection!=null&&collection.size()>0) {
			collection.forEach(x->{
				Object o;
				try {
					o = ReflectUtils.getValue(x, fieldName);
				} catch (Exception e) {
					e.printStackTrace();
					o=new Object();
				}
				set.add(o);				
			});
		}
		return set;
	}
}
