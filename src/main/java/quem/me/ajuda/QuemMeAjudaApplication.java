package quem.me.ajuda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class QuemMeAjudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuemMeAjudaApplication.class, args);
	}
}
