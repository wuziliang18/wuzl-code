package org.wuzl.test.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntSqlField {
	public String name() default "";
	public Constraints constraints() default @Constraints;//嵌套使用注解 加入基本限制
}
