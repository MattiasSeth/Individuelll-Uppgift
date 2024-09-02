package se.systementor.javasecstart.Hash;

import com.google.common.hash.Hashing;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class HashController {

    @RequestMapping(path = "/hash")
    public String showHash (){
        return "hash";
    }
    @RequestMapping(path = "/reversehash")
    public String showReverseHash () {
        return "reversehash";
    }
    @PostMapping(path = "/hashed")
    public String processHash(@RequestParam("txt") String text, Model model, HttpServletRequest request) {
        //System.out.println("Request Method: " + request.getMethod());
        //System.out.println("Request URI: " + request.getRequestURI());

        String sha256 = SHA256(text);
        String md5 = md5(text);

        model.addAttribute("sha256", sha256);
        model.addAttribute("md5", md5);
        return "hashed";
    }

    @PostMapping(path = "reversehashed")
    public String processHashToPassword (@RequestParam("txt") String text, Model model){
        String password = password(text);
        model.addAttribute("password", password);
        return "reversehashed";
    }

    public String SHA256 (String string){
        String sha256hex = Hashing.sha256()
                .hashString(string, StandardCharsets.UTF_8)
                .toString();

        return sha256hex;
    }

    public String md5 (String string){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] messageDigest = md.digest(string.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        System.out.println(hashtext);
        return hashtext;
    }

    public String password (String input){
        String result = "Password not found";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("hashes.txt"));
            String text;

            while ((text = reader.readLine()) != null) {
                String[] parts = text.split(":");
                if (parts[1].equals(input)) {
                    result = parts[0];
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}

