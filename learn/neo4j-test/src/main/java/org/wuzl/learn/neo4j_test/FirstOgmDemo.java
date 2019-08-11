package org.wuzl.learn.neo4j_test;

import java.util.Collections;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

/**
 * Hello world!
 *
 */
public class FirstOgmDemo {
	public static void main(String[] args) {
		// Driver driver = GraphDatabase.driver("bolt://localhost:7687",
		// System.out.println(driver.session().run("MATCH (n:`洛杉矶湖人`) RETURN n
		// LIMIT 25").next().asMap());

		// System.out.println(Neo4jSessionFactory.getNeo4jSession()
		// .query("MATCH (n:`洛杉矶湖人`) RETURN n ", Collections.emptyMap()).queryResults().iterator().next());
	}
}
