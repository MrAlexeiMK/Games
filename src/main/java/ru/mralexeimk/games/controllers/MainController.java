package ru.mralexeimk.games.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/lk")
    public String lk() { return "lk";}
}
