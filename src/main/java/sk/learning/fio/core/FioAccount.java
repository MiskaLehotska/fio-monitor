package sk.learning.fio.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.JsonPath;

// will hold info about account, actualing state of account

@Component
public final class FioAccount {

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	private static final int IMMOVABLE_AMOUNT = 10;
	private static final String FIO_GET_URL = "https://www.fio.cz/ib_api/rest/last/:)/transactions.json";
	private static final String IBAN_PATH = "$.accountStatement.info.iban";
	private static final String BALANCE_PATH = "$.accountStatement.info.closingBalance";

	@Value("${FIO_TOKEN:null}")
	private String apiToken;

	private String IBAN;
	private double balance; 

	public void update() {
		try {
			String response = getAccountInfoJson();
			this.balance = JsonPath.read(response, BALANCE_PATH);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
	}
	
	@PostConstruct
	private void init() {
		validateApiTokenPresence();
		try {
			String response = getAccountInfoJson();
			this.IBAN = JsonPath.read(response, IBAN_PATH);
			this.balance = JsonPath.read(response, BALANCE_PATH);
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
			System.exit(-395);
		}
		LOGGER.info("accout has been successfully initialized");
	}

	private void validateApiTokenPresence() {
		if ("null".equals(apiToken)) {
			LOGGER.severe("Must define token variable to environment variables");
			System.exit(3954);
		}
	}

	private String getAccountInfoJson() throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(FIO_GET_URL.replace(":)", apiToken)).openConnection();
		StringBuilder response = new StringBuilder("");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		return response.toString();

	}

	public String getIBAN() {
		return IBAN;
	}

	public double getBalance() {
		return balance - IMMOVABLE_AMOUNT;
	}

}
