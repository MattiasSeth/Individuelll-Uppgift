package se.systementor.javasecstart.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogDTO {

    private int id;
    private String age;
    private String name;
    private String size;
    private String breed;
    private int price;
    private String image;
}