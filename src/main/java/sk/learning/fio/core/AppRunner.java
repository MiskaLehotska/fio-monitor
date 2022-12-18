package sk.learning.fio.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.learning.fio.config.AppConfig;

public class AppRunner {
	
	public static void main(String [] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();		
	}
}
