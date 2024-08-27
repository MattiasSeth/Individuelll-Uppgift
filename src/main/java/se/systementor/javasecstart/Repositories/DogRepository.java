package se.systementor.javasecstart.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.systementor.javasecstart.model.Dog;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {

    List<Dog> findAllBySoldToIsNull();

    @Query("SELECT d FROM Dog d WHERE (d.soldTo IS NULL) AND " +
            "(LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.breed) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.size) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Dog> searchAndFilterDogs(String searchTerm);

    @Query("SELECT d FROM Dog d WHERE d.soldTo IS NULL ORDER BY " +
            "CASE WHEN :sortField = 'name' AND :direction = 'asc' THEN d.name END ASC, " +
            "CASE WHEN :sortField = 'name' AND :direction = 'desc' THEN d.name END DESC, " +
            "CASE WHEN :sortField = 'breed' AND :direction = 'asc' THEN d.breed END ASC, " +
            "CASE WHEN :sortField = 'breed' AND :direction = 'desc' THEN d.breed END DESC, " +
            "CASE WHEN :sortField = 'age' AND :direction = 'asc' THEN d.age END ASC, " +
            "CASE WHEN :sortField = 'age' AND :direction = 'desc' THEN d.age END DESC, " +
            "CASE WHEN :sortField = 'size' AND :direction = 'asc' THEN d.size END ASC, " +
            "CASE WHEN :sortField = 'size' AND :direction = 'desc' THEN d.size END DESC, " +
            "CASE WHEN :sortField = 'price' AND :direction = 'asc' THEN d.price END ASC, " +
            "CASE WHEN :sortField = 'price' AND :direction = 'desc' THEN d.price END DESC")
    List<Dog> sortDogsByFieldAndDirection(String sortField, String direction);
}
