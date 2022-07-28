package com.curdexample.crudexample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.curdexample.crudexample.Controller"})
public class CurdexApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(CurdexApplication.class, args);
	}
}
