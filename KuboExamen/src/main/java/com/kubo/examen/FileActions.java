/**
 * 
 */
package com.kubo.examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	
	public boolean existFile(String path) {
		try 
		{
			File file = new File(path);
			if (!file.exists()) {
			    System.out.println("Se creara archivo vacio");
			    file.createNewFile(); 
			    return true;
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return false;

		}
		return true;
		
	}
	
	public void fileAdd(String path, String registro) {
		
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		File file = new File(path);		
		try {
			fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
			
			if(this.isFileNull(path)) {
				printWriter.println("[");
				printWriter.println(registro);
				printWriter.println("]");
				
			}
			else {
				printWriter.println(registro);
			}
			
			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
			System.out.println("Usuario Agregado");
		} catch (IOException e) {
			System.out.println("No se pudo agregar usuario");
			e.printStackTrace();
		}		
			
			
		
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
		
		System.out.println("Sin coincidencias");
		
	}
	
	public void list(String path) {
		
		if(this.isFileNull(path)) {
			System.out.println("[]");
		}
		else {
		  File file = new File(path); 
		  BufferedReader reader;
		  StringBuilder stringBuilder = new StringBuilder();
		  
		  try { 
			  reader = new BufferedReader(new FileReader(file)); 
			  String line = reader.readLine(); 
			  while (line != null) { 
				  //System.out.println(line);
				  stringBuilder.append(line); 
				  line = reader.readLine();
			  } reader.close();
		  	} 
		  catch (IOException e) { 
			  e.printStackTrace(); 
		  
		  }
		  
		  JsonParser springParser = JsonParserFactory.getJsonParser();
		  
		  List<Object> list = springParser.parseList(stringBuilder.toString());
		  List<String > names = new ArrayList<>();
		  
		  for(Object o : list) { 
			  if(o instanceof Map) { 
				  Map<String,Object> map = (Map<String,Object>) o; 
				  for (Map.Entry<String, Object> entry : map.entrySet()) {
					  names.add(entry.getValue().toString());
				  } 
			  } 
		   }
		  
		  Collections.sort(names);
		  System.out.println("[");
		  int cont = 0;
		  for(String str: names) {
			  if(names.size()-cont==1) {
				  System.out.println("{\"name\":\""+str+"\"}");
			  }else {
				  System.out.println("{\"name\":\""+str+"\"},");
			  }
			  cont++;
			  
		  }
		  System.out.println("]");
		 }
	}
	
	private boolean isFileNull(String path) {
		  
			File file = new File(path); 
			BufferedReader reader;
			
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine(); 
				
				if(line==null) {
					reader.close();
					return true;
				}
				reader.close();
				
			} catch ( IOException e) {
				e.printStackTrace();
			}
			return false; 
		
	}
	

}
