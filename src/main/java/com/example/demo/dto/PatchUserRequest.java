package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String userNo;
    private String password;

    public PatchUserRequest() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserNo() { return userNo; }
    public void setUserNo(String userNo) { this.userNo = userNo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
