package com.hadi.ex2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that handles vote requests
 */
@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    /**
     * Vote request, it checks if the poll data where initialized. If yes, it checks if the user has already voted, if
     * the user didn't it will try to vote (in case the request was manipulated, for example id for a non existing
     * option) if it sucess it sets that the user has voted and returns dispatcher servlet (PollServlet)
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PollData pollData = (PollData) this.getServletContext().getAttribute("poll_data");
        final String optionId = request.getParameter("option");

        //Check if user didn't vote and poll data has already been initialized and if vote is ok
        if(request.getSession().getAttribute("poll_vote") == null &&
                pollData != null && pollData.vote(optionId)) {
            request.getSession().setAttribute("poll_vote", optionId);
        }

        response.sendRedirect("/");

    }

    /**
     * Redirects to main page
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }


}
