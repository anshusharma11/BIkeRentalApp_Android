package com.rent_app.rentb;

/**
 * Created by ccrodrig on 2/22/2016.
 */
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User(){}


    public User(String firstname, String lastname, String email, String password){
        this.firstname =firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
