package sk.learning.fio.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

@Configuration
@ComponentScan(basePackages = "sk.learning.fio")
@EnableScheduling
public class AppConfig {

	// third-party bean objects like Gson with pretty printing and naming policies...etc.

	@Bean
	public ScheduledExecutorService getScheduler() {
		ThreadPoolExecutorFactoryBean tf = new ThreadPoolExecutorFactoryBean();
		tf.setDaemon(false);
		return Executors.newScheduledThreadPool(1, tf);
	}
}
