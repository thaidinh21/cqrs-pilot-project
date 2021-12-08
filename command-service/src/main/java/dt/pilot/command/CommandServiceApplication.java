package dt.pilot.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import dt.pilot.shared.exception.GlobalExceptionHandler;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"dt.pilot.shared.repo"})
@EnableMongoAuditing
@Import(value = {GlobalExceptionHandler.class})
@EnableAsync
@ComponentScan(basePackages = {"dt.pilot"})
public class CommandServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CommandServiceApplication.class, args);
  }
}
