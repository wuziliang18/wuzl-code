package com.wuzl.lean.es;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;

import com.alibaba.fastjson.JSON;

public class Test {
	public static void main(String[] args) throws UnknownHostException,
			InterruptedException, ExecutionException {
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name", "dayima-search").build();
		Client client = TransportClient
				.builder()
				.settings(settings)
				.build()
				.addTransportAddress(
						new InetSocketTransportAddress(new InetSocketAddress(
								"192.168.127.222", 9300)));
		System.out.println("启动成功");
		System.out.println(">>>>>>>>>集群信息");
		ClusterHealthResponse healths = client.admin().cluster()
				.prepareHealth().get();
		outJson(healths);
		// Employee employee = new Employee();
		// employee.setFirstName("qian");
		// employee.setLastName("qi");
		// employee.setAge(25);
		// employee.setAbout("wu is good");
		// employee.setInterests(new String[] { "game", "walk" });
		// IndexResponse response = client.prepareIndex("dayima", "employee")
		// .setId("6").setSource(JSON.toJSONString(employee)).get();
		// outJson(response);
		// 查询
		// GetResponse getResponse = client.prepareGet("dayima", "topic_test",
		// "73")
		// .setOperationThreaded(true).get();
		// outJson(getResponse.isExists());
		// outJson(getResponse);
		// client.prepareSearch("","").setTypes("","");//多个索引多个type
		// 根据单个字段查询
		// outJson(client.prepareSearch("dayima")
		// .setQuery(QueryBuilders.termQuery("firstName", "wu")).get());
		// 精确查找 filter没有score 没有排序和相关性 中文词语无法查询到结果 原因：中文词语被分解了
		// 如果英文带分隔符之类的也一样不能查询
		// outJson(client
		// .prepareSearch("dayima")
		// .setTypes("topic")
		// .setPostFilter(
		// QueryBuilders.termQuery("title",
		// "udhshwiidjcjdjfjbdbsv")).get());
		// 多字段查找 最好使用bool
		 outJson(client
		 .prepareSearch("dayima")
		 .setTypes("topic")
		 .setQuery(
		 QueryBuilders.multiMatchQuery("挂电话放开日of", "title",
		 "content")).setExplain(true).get());
		// 批量修改
		// client.prepareUpdate().setIndex("dayima").setType("topic").set
		// 查询
		// outJson(client
		// .prepareSearch("dayima")
		// .setTypes("topic")
		// .setPostFilter(
		// QueryBuilders.termQuery("topicCategoryId",
		// "22")).get());
		// //删除index
		// outJson(client.admin().indices().prepareDelete("dayima").get());
		// 创建index
		// outJson(client
		// .admin()
		// .indices()
		// .prepareCreate("dayima")
		// .setSettings(
		// Settings.builder().put("index.number_of_shards", 3)
		// .put("index.number_of_replicas", 1)).get());
		// mapping 直接创建mapping就可以
		System.out.println(">>>mapping");
		Map<String, Object> mapping = new HashMap<String, Object>();
		Map<String, Object> properties = new HashMap<String, Object>();
		Map<String, Object> fieldMap = null;
		fieldMap = new HashMap<String, Object>();

		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("id", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("topicCategoryId", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("uid", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "string");
		fieldMap.put("analyzer", "ik");
		properties.put("title", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "string");
		fieldMap.put("analyzer", "ik");
		properties.put("content", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("status", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("dateline", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "string");
		fieldMap.put("index", "not_analyzed");
		properties.put("ip", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("replynum", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("lastreplytime", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("settop", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("lastoptime", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("lock", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("setbottom", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("flag", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("digest", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("createtime", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "string");
		fieldMap.put("index", "not_analyzed");
		properties.put("lastpost", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("favnum", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("attachPictures", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("viewnum", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("settoptime", fieldMap);
		fieldMap = new HashMap<String, Object>();
		fieldMap.put("type", "long");
		fieldMap.put("index", "not_analyzed");
		properties.put("hidden", fieldMap);

		mapping.put("properties", properties);
		// // _id
		// Map<String, Object> idMap = new HashMap<String, Object>();
		// idMap.put("path", "id");
		// mapping.put("_id", idMap);
		// outJson(client.admin().indices().preparePutMapping("dayima")
		// .setType("topic").setSource(JSON.toJSONString(mapping)).get());
		// mapping end
		// 没有结果http://192.168.124.54:9200/dayima/_mapping/employee?pretty
		// outJson(client.admin().indices().prepareGetMappings("dayima")
		// .setTypes("employee2").get());
		// 分词器信息
		// System.out.println(">>>分词");
		// outJson(client
		// .admin()
		// .indices()
		// .prepareAnalyze("dayima",
		// "我是中国人")
		// .setAnalyzer("ik").get());
		client.close();
	}

	public static void outJson(Object obj) {
		System.out.println(JSON.toJSON(obj));
	}
}
