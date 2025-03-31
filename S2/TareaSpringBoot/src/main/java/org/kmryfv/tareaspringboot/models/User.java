package org.kmryfv.tareaspringboot.models;

public class User {
    public String name;
    public String password;
    public String email;
    public Role role;

    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
