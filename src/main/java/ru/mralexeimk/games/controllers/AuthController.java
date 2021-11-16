package ru.mralexeimk.games.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mralexeimk.games.database.PlayersDB;
import ru.mralexeimk.games.models.Player;
import ru.mralexeimk.games.util.PlayerValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PlayersDB playersDB;
    private final PlayerValidator playerValidator;

    @Autowired
    public AuthController(PlayersDB playersDB, PlayerValidator playerValidator) {
        this.playersDB = playersDB;
        this.playerValidator = playerValidator;
    }

    @GetMapping("/reg")
    public String newPerson(@ModelAttribute("player") Player player) {
        return "auth/reg";
    }

    @GetMapping("/login")
    public String authPerson(@ModelAttribute("player") Player player) {
        return "auth/login";
    }

    @PostMapping("/reg")
    public String registerUser(@ModelAttribute("player") @Valid Player player,
                               BindingResult bindingResult, HttpSession session) {

        playerValidator.validate(player, bindingResult);

        if (bindingResult.hasErrors())
            return "auth/reg";
        playersDB.save(player);
        session.setAttribute("player", player);


        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("player") @Valid Player player,
                         BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors())
            return "auth/reg";
        playersDB.save(player);
        session.setAttribute("player", player);

        return "redirect:/";
    }
}
