package br.com.fiap.postech.goodbuy.security;

import br.com.fiap.postech.goodbuy.security.enums.UserRole;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String login;
    private String password;
    private UserRole role;

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
