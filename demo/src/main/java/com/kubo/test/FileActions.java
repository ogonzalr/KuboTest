/**
 * 
 */
package com.kubo.test;

import java.io.StringReader;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.queries.DisjunctionQuery;
import com.couchbase.client.java.search.queries.MatchQuery;
import com.couchbase.client.java.search.result.SearchQueryResult;



/**
 * @author Omega_skull
 *
 */
public class FileActions {

	/**
	 * 
	 */
	public FileActions() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void fileAdd() {
		
	}
	public void fuzzySearch(){
		
		String word = "Book";
		MatchQuery titleFuzzy = SearchQuery.match(word).fuzziness(1).field("title");
		MatchQuery titleSimple = SearchQuery.match(word).field("title");
		DisjunctionQuery ftsQuery = SearchQuery.disjuncts(titleSimple, titleFuzzy);
		 
		/*
		 * SearchQueryResult result =
		 * movieRepository.getCouchbaseOperations().getCouchbaseBucket().query( new
		 * SearchQuery(indexName, ftsQuery).highlight().limit(20));
		 * printResults(result);
		 */
		
	}
	
	public void list() {
		
	
		
	}
	

}
