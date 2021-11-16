package ru.mralexeimk.games.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mralexeimk.games.database.PlayersDB;
import ru.mralexeimk.games.util.PlayerValidator;

@Controller
@RequestMapping("/players")
public class PlayersController {
    private final PlayersDB playersDB;
    private final PlayerValidator playerValidator;

    @Autowired
    public PlayersController(PlayersDB playersDB, PlayerValidator playerValidator) {
        this.playersDB = playersDB;
        this.playerValidator = playerValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("players", playersDB.getPlayers());
        return "players/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("player", playersDB.getPlayerById(id));
        return "players/show";
    }
}
