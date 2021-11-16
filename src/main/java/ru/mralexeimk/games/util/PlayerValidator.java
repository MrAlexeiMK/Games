package ru.mralexeimk.games.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mralexeimk.games.database.PlayersDB;
import ru.mralexeimk.games.models.Player;

@Component
public class PlayerValidator implements Validator {

    private final PlayersDB playersDB;

    @Autowired
    public PlayerValidator(PlayersDB playersDB) {
        this.playersDB = playersDB;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Player.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Player person = (Player) o;

        if (playersDB.getPlayerByEmail(person.getEmail()) != null) {
            errors.rejectValue("email", "", "Email уже используется!");
        }

        else if (playersDB.getPlayerByNick(person.getNick()) != null) {
            errors.rejectValue("email", "", "Никнейм уже используется!");
        }
    }
}
