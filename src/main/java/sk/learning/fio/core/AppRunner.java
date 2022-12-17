package sk.learning.fio.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.learning.fio.config.AppConfig;

// vytvorenie aplikacneho kontextu a pusti sa monitor

public class AppRunner {
	
	public static void main(String [] args) {
		
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
			FioAccount account = context.getBean(FioAccount.class);
		}
		
		
//		System.out.println(System.getenv("FIO_TOKEN"));
		
		
		
	}

}
