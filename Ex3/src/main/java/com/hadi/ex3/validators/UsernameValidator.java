package com.hadi.ex3.validators;

import javax.validation.constraints.NotBlank;


/** This class validates the form input. It is used in the search and auth controller*/
public class UsernameValidator {

    /**Username*/
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    /**@return username*/
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
