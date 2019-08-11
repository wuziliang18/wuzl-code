package com.yoloho.spring.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;


/**
 * 扫描包下注解
 * 
 * @author wuzl
 * 
 */
public class ScanPackageAnnotation {
	private static final String RESOURCE_PATTERN = "/**/*.class";  
	private static Log log = LogFactory.getLog(ScanPackageAnnotation.class);
    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();  
	/**
	 * 扫描包下的注解
	 * @param packageName
	 * @param annotation
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> scanAnnotation(String packageName,
			Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException {
		log.info(String.format("开始扫描【%s】包下的缓存注解", packageName));
		List<T> result=new ArrayList<T>();
		String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +  
                ClassUtils.convertClassNameToResourcePath(packageName) + RESOURCE_PATTERN;  
        Resource[] resources =resourcePatternResolver.getResources(pattern);  
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);  
        for (Resource resource : resources) {  
            if (resource.isReadable()) {  
                MetadataReader reader = readerFactory.getMetadataReader(resource);  
                AnnotationTypeFilter filter= new AnnotationTypeFilter(annotation, false);
                if(filter.match(reader, readerFactory)){
                	String className=reader.getClassMetadata().getClassName();
                	result.add((T) Class.forName(className).getAnnotation(annotation));
                	log.info(String.format("扫描到【%s】类的缓存注解", className));
                }
            }  
        }
        log.info(String.format("扫描【%s】包下的缓存注解成功", packageName));
		return result;
	}
	
	
	
	/**
	 * 扫描包下的注解 成一个map key是classname
	 * @param packageName
	 * @param annotation
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String,T> scanAnnotationForMap(String packageName,
			Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException {
		log.info(String.format("开始扫描【%s】包下的缓存注解", packageName));
		Map<String,T> map=new HashMap<String,T>();
		String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +  
                ClassUtils.convertClassNameToResourcePath(packageName) + RESOURCE_PATTERN;  
        Resource[] resources =resourcePatternResolver.getResources(pattern);  
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);  
        for (Resource resource : resources) {  
            if (resource.isReadable()) {  
                MetadataReader reader = readerFactory.getMetadataReader(resource);  
                AnnotationTypeFilter filter= new AnnotationTypeFilter(annotation, false);
                if(filter.match(reader, readerFactory)){
                	String className=reader.getClassMetadata().getClassName();
                	Class<?> clazz=Class.forName(className);
                	map.put(clazz.getName(), (T) clazz.getAnnotation(annotation));
                	log.info(String.format("扫描到【%s】类的缓存注解", className));
                }
            }  
        }
        log.info(String.format("扫描【%s】包下的缓存注解成功", packageName));
		return map;
	}
}
