package com.kubo.examen;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KuboExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(KuboExamenApplication.class, args);
		String path = "/Users/Omega_skull/Documents/fuzzy-search.txt";
		
		FileActions fileActions = new FileActions();
		
		if(fileActions.existFile(path)) 
		{	  
			String text=""; 
			Scanner scanner = new Scanner(System.in);
			while(!text.equals("salir"))
			  { 
				 System.out.println("Ingresa comando");
				 
				 System.out.println("./application add");
				 System.out.println("./application list");
				 System.out.println("./application fuzzy-search");
				 System.out.println("./application salir");
				 text =scanner.nextLine(); 
				 if(text.contains("add")) {
					 fileActions.fileAdd(path, text);
				 }
				 if(text.contains("list")){
					 fileActions.list(path);
				 }
				 if(text.contains("fuzzy-search")) {
					 fileActions.fuzzySearch();
				 } 
			  }
			  scanner.close();
			
		}
	
	}

}
