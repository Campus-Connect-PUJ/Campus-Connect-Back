package CampusConnect.CCBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CcBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcBackApplication.class, args);
	}

}
