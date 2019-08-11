package org.wuzl.learn.neo4j_test;

import org.neo4j.driver.v1.StatementResult;

/**
 * Hello world!
 *
 */
public class FirstDemo {
	public static void main(String[] args) {
		Neo4JSessionHelper.getInstance().run(session -> {
			StatementResult result = session.run(
					"MATCH (tom:Person {name: \"Tom Hanks\"})-[:ACTED_IN]->(tomHanksMovies) RETURN tom,tomHanksMovies");
			result.stream().forEach(record -> {
				System.out.println(">>>");
				System.out.println(record.get("tom").asNode());
				System.out.println(record.get("tomHanksMovies").asNode().asMap());
			});
		});

		Neo4JSessionHelper.close();
	}
}
