package com.kubo.examen;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	
	/*
	 * @Value("${path.file}") String path;
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		LeerArchivo leerArchivo = new LeerArchivo("/Users/Omega_skull/Documents/fuzzy-search.txt");
			
		
	}

}
