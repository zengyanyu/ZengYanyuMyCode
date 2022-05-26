package com.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性描述器,用在字段上
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月1日 下午6:37:57
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyDesc {

	String desc() default "";

	String classCode() default "";

}
