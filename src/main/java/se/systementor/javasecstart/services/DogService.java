package se.systementor.javasecstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

import java.util.List;

@Service
public class DogService {
    @Autowired
    DogRepository dogRepository;

    public List<Dog> getPublicDogs() {
        return dogRepository.findAllBySoldToIsNull();
    }

    public List<Dog> searchDogs(String searchTerm) {
        return dogRepository.searchAndFilterDogs(searchTerm);
    }

    public List<Dog> sortDogs(String sortField) {
        return dogRepository.sortDogsByField(sortField);
    }

    public Dog getById(int id){
        Long longId = (long) id;
        return dogRepository.findById(longId).get();
    }
}