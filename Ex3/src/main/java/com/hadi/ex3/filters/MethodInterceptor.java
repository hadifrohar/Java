package com.hadi.ex3.filters;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** This class intercepts all requests checking if the HTTP method is supported
 * (Even without this interceptor, the client will be redirected if the method is not supported (from
 * the authorization interceptor - AuthInterceptor because /error is not supported over there), but with this
 * interceptor, it is more generic (in case we want to handle errors differently)
 * */
@ApplicationScope
public class MethodInterceptor implements HandlerInterceptor {
    /**
     * Checks if the request method is supported (GET OR POST), if supported it will alow the request to
     * pass, otherwise it will redirect and stop the request
     * @param req http request
     * @param res http response
     * @param handler handler
     * @return if to continue with the request or not
     * @throws Exception IOException from sendRedirect method
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if(!req.getMethod().equals(HttpMethod.GET.toString()) && !req.getMethod().equals(HttpMethod.POST.toString()))
            res.sendRedirect("/");

        return res.getStatus() != HttpStatus.FOUND.value();

    }
}
