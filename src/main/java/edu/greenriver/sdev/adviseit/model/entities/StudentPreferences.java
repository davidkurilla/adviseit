package edu.greenriver.sdev.adviseit.model.entities;

import edu.greenriver.sdev.adviseit.model.enums.Season;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPreferences {

    private Integer coursesPerQuarter;

    private Boolean summerQuarter;

    private Season startingSeason;

}
