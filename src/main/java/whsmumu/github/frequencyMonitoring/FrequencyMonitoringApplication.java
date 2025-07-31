package whsmumu.github.frequencyMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FrequencyMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrequencyMonitoringApplication.class, args);
	}

}
