package com.hadi.ex3.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.util.UriUtils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**This class is responsible for fetching data from GitHub and returns it as JSON object*/
@Service
@ApplicationScope
public class SearchService {

    /**GitHub api link*/
    @Value("${ex3.github_api}")
    private String githubApi;


    /**
     * Fetches data from GitHub using their API
     * @param username username to search (fetch data about)
     * @return JSON object that contains all the data returned from GitHub
     * @throws IOException IOException
     */
    public JsonObject search(String username) throws IOException {

        final String url = githubApi+username;
        try(JsonReader reader = Json.createReader(new URL(url).openStream())) {
            return reader.readObject();
        }


    }
}
