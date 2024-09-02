package se.systementor.javasecstart.Hash;

import com.google.common.hash.Hashing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMethods {

    public String SHA56 (String string){
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

    public String BCrypt (String string){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(string);
        return hash;
    }
}

