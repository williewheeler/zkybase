package org.skydingo.skybase;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class Main {
	enum RelationshipTypes implements RelationshipType { MEMBER };
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		GraphDatabaseService graphDb = new EmbeddedGraphDatabase("/Users/williewheeler/projects/opensource/neo4j");
		Transaction tx = graphDb.beginTx();
		try {
			Node willie = graphDb.createNode();
			willie.setProperty("name", "Willie Wheeler");
			willie.setProperty("awesomeness", 9);
			graphDb.index().forNodes("people").add(willie, "id", 1);
			
			Node skybase = graphDb.createNode();
			skybase.setProperty("name", "Skybase");
			skybase.setProperty("category", "Configuration Management");
			graphDb.index().forNodes("projects").add(skybase, "id", 1);
			
			Node subdingo = graphDb.createNode();
			subdingo.setProperty("name", "Subdingo");
			subdingo.setProperty("category", "Site Builder");
			graphDb.index().forNodes("projects").add(subdingo, "id", 2);
			
			Relationship willieSkybase = willie.createRelationshipTo(skybase, RelationshipTypes.MEMBER);
			willieSkybase.setProperty("role", "Developer");
			
			Relationship willieSubdingo = willie.createRelationshipTo(subdingo, RelationshipTypes.MEMBER);
			willieSubdingo.setProperty("role", "Developer");
			
			tx.success();
			
		} finally {
			tx.finish();
		}
	}
}
