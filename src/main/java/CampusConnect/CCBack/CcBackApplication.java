package CampusConnect.CCBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"CampusConnect"})
public class CcBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcBackApplication.class, args);
	}

}
