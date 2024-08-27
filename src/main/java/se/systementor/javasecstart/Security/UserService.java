package se.systementor.javasecstart.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public void addUser(String username, String password) {
        // skapar rollen Kund ifall den inte finns
        Role receptionistRole = roleRepository.findByName("Kund");
        if (receptionistRole == null) {
            roleRepository.save(Role.builder().name("Kund").build());
        }

        // användaren får rollen Kund
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("Kund"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPassword = encoder.encode(password);
        User user = User.builder().enabled(true).password(hashPassword).username(username).roles(roles).build();
        userRepository.save(user);
    }
}
