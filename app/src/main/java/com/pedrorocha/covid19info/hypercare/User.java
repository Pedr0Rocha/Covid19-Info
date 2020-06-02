package com.pedrorocha.covid19info.hypercare;

public class User {

    User userInstance;

    String username;

    public User getInstance(String username) {
        if (userInstance == null) {
            userInstance = new User(username);
        }
        return userInstance;
    }

    public User(String username) {
        this.username = username;
    }

}
