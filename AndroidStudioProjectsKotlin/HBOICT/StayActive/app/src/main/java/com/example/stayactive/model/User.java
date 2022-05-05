package com.example.stayactive.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/*
 * This is a 'room' annotation that will create an SQL lite table for this object.
 * With 2 unique columns password and username
 */
@Entity(
        tableName = "user",
        indices = {@Index(value = {"username", "password"},
        unique = true)})
public class User {
    @PrimaryKey private int userId;

    private String username;
    private String password;

    @Ignore private static final int DEFAULT_ID = 0;

    /*
     * Default constructor
     */
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.setUsername(username);
        this.setPassword(password);
    }

    /*
     * Constructor used to make a new user
     */
    public User(String username, String password) {
        this(DEFAULT_ID, username, password);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    /*
     * To change username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /*
     * In case you forget your pw and need a new one
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
