package com.mastertheboss.springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<App> applicationClass = App.class;
}

@RestController
class HelloController {

	@RequestMapping("/hello/{name}")
	String hello(@PathVariable String name) {

		String status = "DBConnection Status:";
				
				try{  
					//step1 load the driver class  
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					  
					//step2 create  the connection object  
					Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@st1aparac-scan.staging.cox.net:1521/DVTOSS","portout","portout");  
					  
					//close connection
					con.close();  
					
					status = "DBConnection Status: SUCCESS";
					  
					}catch(Exception e){ 
						//System.out.println(e);
						status = "DBConnection Status: FAILED";
					}  
						
				 		
		
		            return "Hi " + name +" ===> " +status + " !";
		
		

	}
}
