package com.hadi.ex3.validators;

import javax.validation.constraints.Size;

/**This class validates the input of the login form */
public class LoginValidator extends UsernameValidator{

    /**Password*/
    @Size(min = 4, message = "Password cannot be less than 4 characters")
    private String password;

    /** @return password*/
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
