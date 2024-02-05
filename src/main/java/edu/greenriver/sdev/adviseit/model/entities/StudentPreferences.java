package edu.greenriver.sdev.adviseit.model.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPreferences {

    private Integer coursesPerQuarter;

    private Boolean summerQuarter;

}
