/**
 * 
 */
package com.kubo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

/**
 * @author Omega_skull
 *
 */
public class LeerArchivo {
	
	public LeerArchivo(String path)
	{	
		
		File file = new File(path);
		BufferedReader reader;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				stringBuilder.append(line);
				line = reader.readLine();
				
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JsonParser springParser = JsonParserFactory.getJsonParser();
		
		List<Object> list = springParser.parseList(stringBuilder.toString());
		
		for(Object o : list) {
			if(o instanceof Map) {
				Map<String,Object> map = (Map<String,Object>) o;
				System.out.println("Items found: " + map.size());

				int i = 0;
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					System.out.println(entry.getKey() + " = " + entry.getValue());
					i++;
				}
			}
		}
		
		
		
		/*
		 * String text=""; Scanner scanner = new Scanner(System.in);
		 * while(!text.equals("salir")){ System.out.println("Ingresa comando"); text =
		 * scanner.nextLine(); System.out.println("Se ingreso: "+text.toUpperCase() ); }
		 */
		 
		
	}
	
}
