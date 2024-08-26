package se.systementor.javasecstart.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class editAdminDogController {

    @GetMapping("/edit")
    public String showLoginForm(){
        return "editdog";
    }
}
