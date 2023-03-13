package se.harbil.policetwitter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import se.harbil.policetwitter.config.InitData;

@SpringBootApplication
@EnableMongoRepositories
public class PoliceTwitterApplication {

  private final InitData initData;

  public PoliceTwitterApplication(InitData initData) {
    this.initData = initData;
  }

  public static void main(String[] args) {
    SpringApplication.run(PoliceTwitterApplication.class, args);
  }

  @Bean
  CommandLineRunner init() {
    return args -> initData.loadDefaultDataToMongo();
  }
}
