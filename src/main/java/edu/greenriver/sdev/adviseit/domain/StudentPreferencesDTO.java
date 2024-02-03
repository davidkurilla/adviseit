package edu.greenriver.sdev.adviseit.domain;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class StudentPreferencesDTO {

    private int id;

    @NonNull
    private Integer coursesPerQuarter;

    @NonNull
    private Boolean summerQuarter;

}
