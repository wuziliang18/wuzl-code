package com.yoloho.spring.util;

import com.dayima.core.exception.DayimaException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

public class ServiceThrowsAdvice implements ThrowsAdvice {
	private Log log = LogFactory.getLog(ServiceThrowsAdvice.class);
	public void afterThrowing( Exception ex) {
		if(ex instanceof DayimaException){

		}else{
			log.error(ex.getMessage(), ex);
			throw new RuntimeException(ex.getMessage());
		}
		
	}
}
