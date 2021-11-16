package ru.mralexeimk.games.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mralexeimk.games.database.PlayersDB;
import ru.mralexeimk.games.models.Player;

@Component
public class PlayerValidator implements Validator {

    private final PlayersDB playersDB;
    private final PasswordEncoder encoder;

    @Autowired
    public PlayerValidator(PlayersDB playersDB, PasswordEncoder encoder) {
        this.playersDB = playersDB;
        this.encoder = encoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Player.class.equals(aClass);
    }

    public void regValidator(Player player, Errors errors) {
        if (playersDB.getPlayerByEmail(player.getEmail()) != null) {
            errors.rejectValue("email", "", "Email уже используется!");
        } else if (playersDB.getPlayerByNick(player.getNick()) != null) {
            errors.rejectValue("nick", "", "Никнейм уже используется!");
        }
    }

    public void loginValidator(Player player, Errors errors) {
        boolean error = false;
        if(player.getNick() == null || player.getNick().isEmpty()) {
            errors.rejectValue("nick", "", "Введите ник");
            error = true;
        }
        if(player.getPassword() == null || player.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Введите пароль");
            error = true;
        }

        if(!error) {
            if(playersDB.getPlayerByNick(player.getNick()) == null) {
                errors.rejectValue("nick", "", "Пользователь не найден");
            }
            else {
                Player db_player = playersDB.getPlayerByNick(player.getNick());
                if (!encoder.matches(player.getPassword(), db_player.getPassword())) {
                    errors.rejectValue("password", "", "Неверный пароль");
                }
            }
        }
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o instanceof Player) {
            Player player = (Player) o;

            if(player.getArgs().contains("onReg")) {
                regValidator(player, errors);
            }
            else if(player.getArgs().contains("onLogin")) {
                loginValidator(player, errors);
            }
        }
    }
}
