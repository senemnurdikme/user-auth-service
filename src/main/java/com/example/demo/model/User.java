package com.example.demo.model;

public class User {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String userNo;

    public User(Long id, String email, String firstName, String lastName, String userNo) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userNo = userNo;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserNo() {
        return userNo;
    }
}

