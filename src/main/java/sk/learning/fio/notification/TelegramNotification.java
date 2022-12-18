package sk.learning.fio.notification;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TelegramNotification implements Notification {

	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final String TELEGRAM_SEND_MASSAGE_URL = "https://api.telegram.org/bot$1/sendMessage?chat_id=$2&text=$3&parse_mode=html";

	@Value("${BOT_TOKEN:null}")
	private String botToken;

	@Value("${BOT_CHAT:null}")
	private String chatToken;

	@Override
	public void sendNotification(String message, String telegramGroup) {
		String url = TELEGRAM_SEND_MASSAGE_URL
			.replace("$3", URLEncoder.encode(message, StandardCharsets.UTF_8))
			.replace("$2", chatToken)
			.replace("$1", botToken);

		sendTelegramRequest(url);
	}

	private void sendTelegramRequest(String url) {
		LOGGER.info("Sending telegram notification");
		try {
			// log sending message
			new URL(url).openConnection()
				.getInputStream()
				.readAllBytes();
			// log sending message successful
		} catch (IOException e) {
			LOGGER.warning("Unable to send telegram notification - cause: " + e.getMessage());
		}
	}

	@PostConstruct
	private void init() {
		if (botToken.equals("null") || chatToken.equals("null")) {
			LOGGER.severe("Must define telegram tokens");
			System.exit(-53948);
		}
		LOGGER.info("Telegram notification has been initialized as the main notification service");
	}
}
