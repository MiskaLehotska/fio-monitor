package sk.learning.fio.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// bude tu cela logika odosielania notifikacii pri zmene stavu uctu]

@Component
public class ScheduledMonitor {
	
//	@Scheduled(initialDelay = 10000, fixedRate = 1_200_000)
	@Scheduled(initialDelay = 2000, fixedRate = 2000)
	public void scheduledJob() {
		System.out.println("job is running");
	}

}
