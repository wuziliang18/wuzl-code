package org.wuzl.test.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 表名的注解 放到类上
 * @author wuzl
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
//	public String tableName();
	public String value() default "";//如果用value 且是唯一需要配置项 可以不用配置名称
}
