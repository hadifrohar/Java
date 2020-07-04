package com.hadi.ex3.controllers;

import com.hadi.ex3.beans.SessionData;
import com.hadi.ex3.validators.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**This component controls the authentication form (login\logout)*/
@Controller
public class AuthController {
    /** Saved username in application properties */
    @Value("${ex3.username}")
    private String savedUsername;

    /** Saved password in application properties */
    @Value("${ex3.password}")
    private String savedPassword;

    /** Session data object that contains info used for authorization */
    @Autowired
    private SessionData sessionData;

    /**
     * Handles GET: /login requests
     * @param loginValidator login data (username and password) default validations
     * @return login page (template)
     */
    @GetMapping("/login")
    public String login(LoginValidator loginValidator) {
        return "login";
    }

    /**
     * Handles POST: /login requests.
     * In case the username entered valid information it will update {@link #sessionData}.
     * In case the Valid operation on the validator fails it will return to the form with the appropriate error message
     * @param loginValidator login data (username and password) default validations
     * @param bindingResult validation results (loginValidator)
     * @param model model to add data to it (for thymeleaf)
     * @return login page (in case login failed). redirects to home page (in case login succeed)
     */
    @PostMapping("/login")
    public String login(@Valid LoginValidator loginValidator, BindingResult bindingResult, Model model){

        if(!bindingResult.hasErrors()) {
            if (savedUsername.equals(loginValidator.getUsername()) && savedPassword.equals(loginValidator.getPassword())) {
                sessionData.setLoginInfo(loginValidator);
                return "redirect:/";
            }
            else
                model.addAttribute("loginFailed", "Incorrect username or password!");
        }

        return "login";
    }

    /**
     * Handles POST: /logout requests.
     * It will invalidate the session
     * @param request HTTP request
     * @return redirected to the main page (the filter will redirect to the login page)
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
