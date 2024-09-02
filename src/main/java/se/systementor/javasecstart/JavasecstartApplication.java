package se.systementor.javasecstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.systementor.javasecstart.Security.UserDataSeeder;

import java.util.Objects;

@SpringBootApplication
public class JavasecstartApplication {

    @Autowired
    private UserDataSeeder userDataSeeder;

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(JavasecstartApplication.class, args);
        }else if (Objects.equals(args[0], "Hash")) {
            SpringApplication application = new SpringApplication(AddHashedPasswords.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }

    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userDataSeeder.Seed();
        };
    }
}
