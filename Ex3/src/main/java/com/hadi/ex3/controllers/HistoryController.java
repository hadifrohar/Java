package com.hadi.ex3.controllers;

import com.hadi.ex3.repo.GithubUser;
import com.hadi.ex3.repo.GithubUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** (JSON controller) controls the search history*/
@RestController
public class HistoryController {

    /** Search table repo (proxy for the database)*/
    @Autowired
    private GithubUserRepo userRepo;

    /**
     * Handles POST/GET requests for history
     * @return search history (top 10 searches)
     */
    @RequestMapping("/history")
    public List<GithubUser> getHistory() {
        return userRepo.findFirst10ByOrderBySearchCountDesc();
    }

    @PostMapping("/clear")
    public void clearHistory(HttpServletResponse res) throws IOException {
        res.sendRedirect("/search");
        userRepo.deleteAll();
    }
}
