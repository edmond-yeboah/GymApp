package com.example.gym;

public class User {
    private String email;
    private String password;
    private String fullname;
    private String age;

    public User() {
    }

    public User(String email, String password, String fullname, String age) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
    }

    public User(String useremail, String userpassword) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
