package edu.greenriver.sdev.adviseit.model.entities;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String title;

    private List<Course> prerequisites;
}
