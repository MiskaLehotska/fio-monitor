package sk.learning.fio.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.learning.fio.config.AppConfig;

// vytvorenie aplikacneho kontextu a pusti sa monitor

public class AppRunner {
	
	public static void main(String [] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		System.out.println("EOL");
		
		
//		System.out.println(System.getenv("FIO_TOKEN"));
	}

}
