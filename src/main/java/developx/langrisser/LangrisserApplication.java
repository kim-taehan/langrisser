package developx.langrisser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LangrisserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LangrisserApplication.class, args);
	}

}
