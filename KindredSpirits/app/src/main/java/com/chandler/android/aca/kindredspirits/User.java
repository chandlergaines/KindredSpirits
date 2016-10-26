package com.chandler.android.aca.kindredspirits;

public class User {

    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String mobile;
    public String password;

    public User(){}

    public User(String firstName, String lastName, String username,
                String email, String mobile, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    private void writeNewUser() {

    }
}
