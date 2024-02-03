package edu.greenriver.sdev.adviseit.domain;

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
