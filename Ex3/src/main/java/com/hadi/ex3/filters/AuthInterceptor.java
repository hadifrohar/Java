package com.hadi.ex3.filters;

import com.hadi.ex3.beans.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** This class intercepts all requests checking if the user is authorized for the page he is entering*/
@ApplicationScope
public class AuthInterceptor implements HandlerInterceptor {

    /**This object contains info about the user (if logged, username, password)*/
    @Autowired
    private SessionData sessionData;

    /**
     * Checks if the user is authorized to enter the requested page. In case the username is not authorized
     * it will redirect him to the login page. In case the user is authorized and he is trying to a non existing
     * page, it will return him to the main page (search page)
     * @param request http request
     * @param response http response
     * @param handler handler
     * @return if to continue with the request or not
     * @throws Exception IOException from sendRedirect method
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String path = request.getRequestURI();
        if(!sessionData.isLogged() && !path.equals("/login"))
            response.sendRedirect("/login");
        else if(sessionData.isLogged() && (!path.equals("/search") && !path.equals("/history")
                && !path.equals("/logout") && !path.equals("/clear")))
            response.sendRedirect("/search");

        return response.getStatus() != HttpStatus.FOUND.value();
    }
}
