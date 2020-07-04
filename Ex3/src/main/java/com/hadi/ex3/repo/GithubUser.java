package com.hadi.ex3.repo;

import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** Database entity for user*/
@Entity
public class GithubUser {

    /** Primary key*/
    @Id
    @GeneratedValue()
    private long id;

    /** GitHub login username*/
    @NotBlank(message = "Username cannot be empty!")
    @Column(unique = true)
    private String login;

    /** Total search count of this user*/
    private int searchCount;

    /** Link to the GitHub profile*/
    @NotBlank(message = "Username couldn't be found!")
    @Column(unique = true)
    private String htmlUrl;

    /** Number of followers for this user*/
    @NotNull(message = "Username couldn't be found!")
    private Integer followersCount;

    public GithubUser() {}

    /**
     * @param userJson contains all the fetched data from GitHub
     */
    public GithubUser(final JsonObject userJson) {
        this.login = userJson.getString("login");
        this.followersCount = userJson.getInt("followers");
        this.htmlUrl = userJson.getString("html_url");
        this.searchCount = 0;
    }

    /** @return id*/
    public long getId() {
        return id;
    }

    /**@param id object id*/
    public void setId(long id) {
        this.id = id;
    }

    /**@return login (username)*/
    public String getLogin() {
        return login;
    }

    /**
     * sets username
     * @param login (username)
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**@return search count*/
    public int getSearchCount() {
        return searchCount;
    }

    /**
     * sets search count
     * @param searchCount search count
     */
    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    /**@return link to GitHub profile*/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Sets GitHub profile link
     * @param userUrl GitHub profile link
     */
    public void setHtmlUrl(String userUrl) {
        this.htmlUrl = userUrl;
    }

    /**@return followers count*/
    public Integer getFollowersCount() {
        return followersCount;
    }

    /**
     * Sets followers count
     * @param followersCount followers count
     */
    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }


    /**
     * Add one to the search counter
     */
    public void searchOccurred() {
        searchCount++;
    }
}
