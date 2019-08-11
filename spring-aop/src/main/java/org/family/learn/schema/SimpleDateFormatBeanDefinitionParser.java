package org.family.learn.schema;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;  
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;  
import org.springframework.util.StringUtils;  
import org.w3c.dom.Element;  

public class SimpleDateFormatBeanDefinitionParser extends
		AbstractSingleBeanDefinitionParser {
	protected Class<SimpleDateFormat> getBeanClass(Element element) {  
        return SimpleDateFormat.class;   
    }  
  
    @SuppressWarnings("deprecation")  
    protected void doParse(Element element, BeanDefinitionBuilder bean) {  
        // this will never be null since the schema explicitly requires that a value be supplied  
        String pattern = element.getAttribute("pattern");  
        bean.addConstructorArg(pattern);  
  
        // this however is an optional property  
        String lenient = element.getAttribute("lenient");  
        if (StringUtils.hasText(lenient)) {  
            bean.addPropertyValue("lenient", Boolean.valueOf(lenient));  
        }  
    }  
}
