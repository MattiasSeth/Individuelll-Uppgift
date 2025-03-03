package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.systementor.javasecstart.DTO.DogDTO;
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
        DogDTO dogDTO = dogService.convertToDTO(dog);
        model.addAttribute("dog", dogDTO);
        return "editdog";
    }

    @PostMapping("/update")
    public String updateDog(Model model, DogDTO dogDTO) {
        Dog dog = dogService.getById(dogDTO.getId());
        dogService.updateDog(dogDTO);
        return "redirect:/admin/dogs";
    }


    @GetMapping(path = "/admin/dogs")
    String list(@RequestParam(value = "q", required = false) String query,
                @RequestParam(value = "sort", required = false) String sortField,
                @RequestParam(value = "dir", required = false) String direction,
                Model model) {
        model.addAttribute("activeFunction", "home");

        List<DogDTO> dogDTOs;
        if (query != null && !query.isEmpty()) {
            dogDTOs = dogService.searchDogs(query).stream()
                    .map(d -> dogService.convertToDTO(d)).toList();

        }
        else if (sortField != null && !sortField.isEmpty()) {
            if (direction == null || direction.isEmpty()) {
                direction = "asc";
            }
            dogDTOs = dogService.sortDogs(sortField, direction).stream()
                    .map(d -> dogService.convertToDTO(d)).toList();

        }
        else {
            dogDTOs = dogService.getPublicDogsDTO();
        }
        model.addAttribute("dogs", dogDTOs);
        return "admin/dogs/list";
    }
}