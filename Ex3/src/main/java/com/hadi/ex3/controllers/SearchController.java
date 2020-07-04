package com.hadi.ex3.controllers;

import com.hadi.ex3.beans.SearchService;
import com.hadi.ex3.beans.SessionData;
import com.hadi.ex3.repo.GithubUser;
import com.hadi.ex3.repo.GithubUserRepo;
import com.hadi.ex3.validators.UsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.JsonObject;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

/** This component controls the search form\requests, displays search history */
@Controller
@RequestMapping("/search")
public class SearchController {

    /** Search table repo (proxy for the database)*/
    @Autowired
    private GithubUserRepo userRepo;

    /** Search service: fetches data from GitHub server */
    @Autowired
    private SearchService searchService;


    /** Session data object that contains session info (user info, search info) */
    @Autowired
    private SessionData sessionData;

    /**
     * Handles GET: /search requests
     * It returns a page with search form. In case the user has already searched and the results were not displayed
     * it will also show the results (using SessionData.SearchData object)
     * @param usernameValidator username validator object (used in the post method to validate form input)
     * @param model model to add data to it (for thymeleaf)
     * @return search page
     */
    @GetMapping
    public String search(UsernameValidator usernameValidator, Model model) {
        sessionData.updateSearchForm(model);
        return "search";
    }

    /**
     * Handles POST: /search requests
     * Uses {@link com.hadi.ex3.beans.SearchService#search(String)} to get results from GitHub server.
     * It validates that the username is not empty if it is not empty it searches for the user.
     * It adds the user and the result (we are only interested in followers count) to {@link #sessionData}
     * In case the username already exists in history it will increase the search counter for it (in the db).
     * @param usernameValidator username validator object, validates if form input is not empty
     * @param bindingResult validation results
     * @return search page (with the result)
     */
    @PostMapping
    public String search(@Valid UsernameValidator usernameValidator, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "search";
        String result;
        try {
            JsonObject userJson = searchService.search(usernameValidator.getUsername());
            final int followers = userJson.getInt("followers");
            if(followers == 0)
                result = "Username has no followers!";
            else
                result = "Username has " + followers + " followers";

            synchronized (this) {
                GithubUser githubUser = userRepo.findByLogin(userJson.getString("login"));
                if (githubUser == null)
                    githubUser = new GithubUser(userJson);

                githubUser.searchOccurred();
                userRepo.save(githubUser);
            }

        } catch(UnknownHostException e) {
            result = "Couldn't connect to GitHub server!";

        } catch(FileNotFoundException e) {
            result = "Username not found!";
        }
        catch (IOException e) {
            result = "Error occurred! Please try again!";
            e.printStackTrace();
        }
        sessionData.setSearch(usernameValidator.getUsername(), result);

        return "redirect:/search";
    }

}
