package ru.mralexeimk.games.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Player {
    private int id;

    @NotEmpty(message = "Введите никнейм")
    @Size(min = 4, max = 30, message = "Длина ника должна быть не меньше 4 и не больше 30")
    private String nick;

    @NotEmpty(message = "Введите пароль")
    @Size(min = 4, max = 50, message = "Длина пароля должна быть не меньше 4 и не больше 50")
    private String password;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Некорректный Email")
    private String email;

    public Player() {

    }

    public Player(int id, String nick, String password, String email) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
