package com.hadi.ex3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Handles GithubUser entity injection to the database
 */
public interface GithubUserRepo extends JpaRepository<GithubUser, Long> {

    /**
     * Gets GithubUser object by login field. Login field is unique
     * @param login login attribute
     * @return GithubUser object that has login as its field (otherwise null)
     */
    GithubUser findByLogin(String login);

    /**
     * Gets top 10 searches in descending order
     * @return Top 10 (up to 10) searches in descending order
     */
    List<GithubUser> findFirst10ByOrderBySearchCountDesc();
}