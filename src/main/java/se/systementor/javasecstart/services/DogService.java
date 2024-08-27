package se.systementor.javasecstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.DTO.DogDTO;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogService {
    @Autowired
    DogRepository dogRepository;

    public List<Dog> getPublicDogs() {
        return dogRepository.findAllBySoldToIsNull();
    }

    public List<DogDTO> getPublicDogsDTO() {
        List<Dog> dogs = dogRepository.findAllBySoldToIsNull();
        return dogs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DogDTO convertToDTO (Dog dog){
        return DogDTO.builder()
                .id(dog.getId())
                .age(dog.getAge())
                .price(dog.getPrice())
                .size(dog.getSize())
                .name(dog.getName())
                .image(dog.getImage())
                .breed(dog.getBreed())
                .build();
    }

    public List<Dog> searchDogs(String searchTerm) {
        return dogRepository.searchAndFilterDogs(searchTerm);
    }

    public List<Dog> sortDogs(String sortField, String direction) {
        return dogRepository.sortDogsByFieldAndDirection(sortField, direction);
    }
    public Dog getById(int id){
        Long longId = (long) id;
        return dogRepository.findById(longId).get();
    }

    public void updateDog (Dog dog){
        Long dogId = (long) dog.getId();
        Dog currentDog = dogRepository.findById(dogId).get();

        currentDog.setName(dog.getName());
        currentDog.setBreed(dog.getBreed());
        currentDog.setAge(dog.getAge());
        currentDog.setSize(dog.getSize());
        currentDog.setPrice(dog.getPrice());

        dogRepository.save(currentDog);
    }
}