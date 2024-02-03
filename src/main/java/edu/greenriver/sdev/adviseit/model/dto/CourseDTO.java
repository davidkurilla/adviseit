package edu.greenriver.sdev.adviseit.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private int id;

    @NonNull
    private String title;
}
