package com.example.myapplication;

public class helperClass {
    String fullname,password,username,email;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public helperClass(String fullname, String password, String username, String email) {
        this.fullname = fullname;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public helperClass() {
    }
}
