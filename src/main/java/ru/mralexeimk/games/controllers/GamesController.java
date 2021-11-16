package ru.mralexeimk.games.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/games")
public class GamesController {

    @GetMapping("/tictactoe")
    public String getTictactoe(HttpSession session) {
        if(session.getAttribute("player") != null) {
            return "tictactoe";
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/chess")
    public String getChess(HttpSession session) {
        if(session.getAttribute("player") != null) {
            return "chess";
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/mathbattle")
    public String getMathbattle(HttpSession session) {
        if(session.getAttribute("player") != null) {
            return "mathbattle";
        }
        return "redirect:/auth/login";
    }
}
