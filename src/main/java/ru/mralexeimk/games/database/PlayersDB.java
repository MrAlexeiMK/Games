package ru.mralexeimk.games.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mralexeimk.games.models.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayersDB {
    private final JdbcTemplate db;
    private PasswordEncoder encoder;

    @Autowired
    public PlayersDB(JdbcTemplate jdbcTemplate, PasswordEncoder encoder) {
        this.db = jdbcTemplate;
        this.encoder = encoder;
    }

    public List<Player> getPlayers() {
        return db.query("SELECT * FROM players", new BeanPropertyRowMapper<>(Player.class));
    }

    public Player getPlayerByEmail(String email) {
        return db.query("SELECT * FROM players WHERE email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(Player.class)).stream().findAny().orElse(null);
    }

    public Player getPlayerByNick(String nick) {
        return db.query("SELECT * FROM players WHERE nick=?", new Object[]{nick},
                new BeanPropertyRowMapper<>(Player.class)).stream().findAny().orElse(null);
    }

    public Player getPlayerById(int id) {
        return db.query("SELECT * FROM players WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Player.class))
                .stream().findAny().orElse(null);
    }

    public void save(Player person) {
        db.update("INSERT INTO players(nick, password, email) VALUES(?, ?, ?)", person.getNick(), encoder.encode(person.getPassword()),
                person.getEmail());
    }

    public void update(int id, Player updatedPerson) {
        db.update("UPDATE players SET nick=?, age=?, email=? WHERE id=?", updatedPerson.getNick(),
                encoder.encode(updatedPerson.getPassword()), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        db.update("DELETE FROM players WHERE id=?", id);
    }
}
