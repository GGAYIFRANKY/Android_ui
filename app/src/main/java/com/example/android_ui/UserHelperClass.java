package com.example.android_ui;

public class UserHelperClass {

    String full_names, username, email, phone_number, password;

    public UserHelperClass() {

    }

    public UserHelperClass(String full_names, String username, String email, String phone_number, String password) {
        this.full_names = full_names;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    public String getFull_names() {
        return full_names;
    }

    public void setFull_names(String full_names) {
        this.full_names = full_names;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
