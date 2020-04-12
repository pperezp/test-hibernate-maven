package cl.prez.hibernate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BandDTO {
    private Integer id;
    private String name;
    private String styles;
    private String country;
    private Integer year;
}