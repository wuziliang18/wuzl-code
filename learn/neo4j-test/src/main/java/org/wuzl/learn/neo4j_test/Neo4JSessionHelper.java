package org.wuzl.learn.neo4j_test;

import java.util.function.Consumer;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4JSessionHelper {
	private static final Driver driver = GraphDatabase.driver("bolt://88888:7687",
			AuthTokens.basic("neo4j", "888"));
	private static final Neo4JSessionHelper helper = new Neo4JSessionHelper();

	private Neo4JSessionHelper() {

	}

	public static Neo4JSessionHelper getInstance() {
		return helper;
	}

	public void run(Consumer<Session> consumer) {
		Session sesssion = driver.session();
		try {
			consumer.accept(sesssion);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void close() {
		driver.close();
	}
}
