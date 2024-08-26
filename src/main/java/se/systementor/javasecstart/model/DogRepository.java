package se.systementor.javasecstart.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {

    List<Dog> findAllBySoldToIsNull();

    // Sök efter hundar
    @Query("SELECT d FROM Dog d WHERE (d.soldTo IS NULL) AND " +
            "(LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.breed) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.size) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Dog> searchAndFilterDogs(String searchTerm);

    // Sortera hundar, Lite osäker men tror det funkar
    @Query("SELECT d FROM Dog d WHERE d.soldTo IS NULL ORDER BY " +
            "CASE WHEN :sortField = 'name' THEN d.name END ASC, " +
            "CASE WHEN :sortField = 'breed' THEN d.breed END ASC, " +
            "CASE WHEN :sortField = 'age' THEN d.age END ASC, " +
            "CASE WHEN :sortField = 'size' THEN d.size END ASC, " +
            "CASE WHEN :sortField = 'price' THEN d.price END ASC")
    List<Dog> sortDogsByField(String sortField);
}