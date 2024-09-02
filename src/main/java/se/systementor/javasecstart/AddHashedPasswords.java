package se.systementor.javasecstart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@ComponentScan
public class AddHashedPasswords implements CommandLineRunner {

    public String BCrypt (String string){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(string);
        return hash;
    }
    @Override
    public void run(String... args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/passwords"));
            FileWriter writer = new FileWriter("hashes.txt");

            String text;
            String hash;

            while ((text = reader.readLine()) != null) {
                hash = BCrypt(text);
                String outputText = text + ":" + hash;
                writer.write(outputText + "\n");
            }

            writer.flush();
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
