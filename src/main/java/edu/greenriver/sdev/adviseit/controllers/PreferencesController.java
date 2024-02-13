package edu.greenriver.sdev.adviseit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PreferencesController {
    @RequestMapping("/schedule-builder")
    public String showView(){
        return "preferences";
    }
}
