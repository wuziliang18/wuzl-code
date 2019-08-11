package org.wuzl.test.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 基本的约束条件
 * @author wuzl
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
	public boolean primaryKey() default false;
	public boolean allowNull() default true;
	public boolean unque() default false;
}
