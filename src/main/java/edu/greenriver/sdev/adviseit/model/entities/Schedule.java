package edu.greenriver.sdev.adviseit.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private List<Quarter> quarterList;
}
