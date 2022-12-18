package sk.learning.fio.job;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sk.learning.fio.core.FioAccount;
import sk.learning.fio.notification.Notification;

@Component
public class ScheduledMonitor {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	private double previousBalance;

	@Autowired
	private FioAccount account;

	@Autowired
	private Notification notificationService;

	@Scheduled(initialDelay = 10000, fixedRate = 1_200_000)
	public void scheduledJob() {
		account.update();

		if (previousBalance != account.getBalance()) {
			// sending null as recipient because we are using telegram notification
			// which has the recipient group already defined via system environment
			// variables
			notificationService.sendNotification(constructNotificationMessage(), null);
			previousBalance = account.getBalance();
		}
	}

	private String constructNotificationMessage() {
		double accountBalance = account.getBalance();
		String inOutMsg = accountBalance > previousBalance 
				? "Na ucet pribudli prostriedky v hodnote " 
				: "Z uctu odisli prostriedky v hodnote ";

		double diff = Math.abs(accountBalance - previousBalance);

		return new StringBuilder(inOutMsg)
			.append(diff)
			.append("EUR")
			.append(System.lineSeparator())
			.append("IBAN: ")
			.append(account.getIBAN())
			.append(System.lineSeparator())
			.append("Aktualny stav uctu: ")
			.append(account.getBalance())
			.toString();
	}

	@PostConstruct
	private void init() {
//		previousBalance = account.getBalance();
		LOGGER.info("Previous price has been initialized with the value of " + this.previousBalance);
	}

}
