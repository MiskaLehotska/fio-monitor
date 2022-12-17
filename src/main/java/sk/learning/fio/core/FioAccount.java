package sk.learning.fio.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// will hold info about account, actualing state of account

@Component
public final class FioAccount {

	@Value("${FIO_TOKEN}")
	private String apiToken;

	@PostConstruct
	private void init() {
		// po injectnuti premennych, tu mozu byt validacie na ich pritomnost

		// tu moze byt napriklad aj ziskanie prvotneho stavu uctu a zakladnych info o ucte
		// ako je iban a pod...tieto veci staci zavolat iba raz lebo sa nebudu nikdy menit
	}

}
