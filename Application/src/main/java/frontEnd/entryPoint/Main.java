package frontEnd.entryPoint;

import business.dto.ContinentDTO;
import business.service.ContinentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import persistence.dao.ContinentDAO;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"persistence.dao", "business.service", "frontEnd" })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

