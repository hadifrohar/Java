package com.hadi.ex3.beans;

import com.hadi.ex3.validators.LoginValidator;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

/**This component contains data for the session (client) */
@Component
@SessionScope
public class SessionData implements Serializable {

    private static final long serialVersionUID = -6280514415419500036L;

    /**
     * This class contains the search data that the user entered (this class is used to allow for post/redirect/get
     * pattern)
     */
    private static class SearchData implements Serializable {

        /** searched username */
        private final String searchUser;

        /** search result (number of followers / doesn't have followers / not found ....)*/
        private final String result;

        /**
         * Creates search data object
         * @param searchUser searched username
         * @param result search result
         */
        public SearchData(String searchUser, String result) {
            this.searchUser = searchUser;
            this.result = result;
        }

    }

    /** Username (in case he is logged)*/
    private String username;

    /** Password (in case he is logged)*/
    private String password;

    /** If the client is logged in or not*/
    private boolean isLogged;

    /**Object contains searched user and result*/
    private SearchData searchData;


    /**
     * Returns if the user is logged in or not
     * @return is logged in
     */
    public boolean isLogged() {
        return isLogged;
    }

    /**
     * Returns the username (in case the user is logged in)
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password (in case the user is logged in)
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Updates the data (username, password) of the user
     * @param loginValidator object contains username and password
     */
    public void setLoginInfo(final LoginValidator loginValidator) {
        username = loginValidator.getUsername();
        password = loginValidator.getPassword();
        isLogged = true;
    }

    /**
     * Saves search data in session (searched username and result)
     * This is used to allow for post/redirect/get pattern
     * @param searchUser searched username
     * @param result search result
     */
    public void setSearch(final String searchUser, final String result) {
        searchData = new SearchData(searchUser, result);
    }

    /**
     * Updates the form with the searched data. (Adds to the model the searched user and result)
     * This is used to allow for post/redirect/get pattern
     * @param model model to add data to it
     */
    public void updateSearchForm(Model model) {
        try {
            model.addAttribute("username", searchData.searchUser);
            model.addAttribute("msg", searchData.result);
            searchData = null;
        }
        catch(Exception ignored) { }
    }

}
