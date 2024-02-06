package edu.greenriver.sdev.adviseit.model.entities;

import edu.greenriver.sdev.adviseit.model.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quarter {

    private Season season;
    private Course[] courseList;
}
