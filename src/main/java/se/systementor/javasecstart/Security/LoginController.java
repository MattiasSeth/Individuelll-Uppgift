package se.systementor.javasecstart.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.javasecstart.DTO.UserDTO;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/registreraSida")
    public String showRegistreraForm(){
        return "registreraSida";
    }

    @PostMapping("/registreraUser")
    public String registreraUser(@ModelAttribute("user") UserDTO user) {
        userService.addUser(user.username, user.password);
        return "registreringLyckad";
    }
}
