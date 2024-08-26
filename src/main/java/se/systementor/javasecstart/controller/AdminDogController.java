package se.systementor.javasecstart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogService;

@Controller
public class AdminDogController {
    @Autowired
    private DogService dogService;

    @GetMapping("/edit")
    public String showEditForm(){
        return "editdog";
    }
    @RequestMapping(path = "/edit/{id}")
    public String editBooking(@PathVariable int id, Model model){
        Dog dog = dogService.getById(id);

        return "editdog";
    }

    @GetMapping(path = "/admin/dogs")
    String list(@RequestParam(value = "q", required = false) String query,
                @RequestParam(value = "sort", required = false) String sortField,
                Model model) {
        model.addAttribute("activeFunction", "home");

        if (query != null && !query.isEmpty()) {
            model.addAttribute("dogs", dogService.searchDogs(query));
        } else if (sortField != null && !sortField.isEmpty()) {
            model.addAttribute("dogs", dogService.sortDogs(sortField));
        } else {
            model.addAttribute("dogs", dogService.getPublicDogs());
        }

        return "admin/dogs/list";
    }
}