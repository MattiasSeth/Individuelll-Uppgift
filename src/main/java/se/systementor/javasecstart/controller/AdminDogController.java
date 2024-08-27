package se.systementor.javasecstart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogService;

import java.util.List;

@Controller
public class AdminDogController {
    @Autowired
    private DogService dogService;


    @RequestMapping(path = "admin/dogs/edit/{id}")
    public String editDog(@PathVariable int id, Model model){
        Dog dog = dogService.getById(id);
        System.out.println(dog.getId());
        model.addAttribute("Dog", dog);
        return "editdog";
    }

    @PostMapping("/update")
    public String updateDog(Model model, Dog dog) {
        dogService.updateDog(dog);
        return "redirect:/admin/dogs";
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