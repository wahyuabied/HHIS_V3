package com.mrabid.hhis.Modal;

/**
 * Created by Mr Abid on 10/10/2017.
 */

public class User {
    private int id;
    private String username;
    private String token;

    public User() {
    }

    public User(int id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
