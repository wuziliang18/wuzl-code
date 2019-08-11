package com.wuzl.lean.spring.data.es;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-es.xml" });
		ElasticsearchTemplate template=context.getBean(ElasticsearchTemplate.class);
		IndexQuery query=new IndexQuery();
		template.index(query);
	}
}
