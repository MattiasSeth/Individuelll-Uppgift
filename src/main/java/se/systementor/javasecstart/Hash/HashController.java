package se.systementor.javasecstart.Hash;

import com.google.common.hash.Hashing;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping(path = "/hashed")
    public String processHash(@RequestParam("txt") String text, Model model, HttpServletRequest request) {
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());

        String sha256 = SHA256(text);
        String md5 = md5(text);

        model.addAttribute("sha256", sha256);
        model.addAttribute("md5", md5);
        return "hashed";
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

}

