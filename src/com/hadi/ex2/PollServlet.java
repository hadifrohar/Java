package com.hadi.ex2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *A servlet that acts as a dispatcher: it shows the user the appropriate page (vote page, result page or
 * unavailabe page)
 */
@WebServlet(name = "PollServlet", urlPatterns = "", initParams = {
        @WebInitParam(name = "poll_filename", value="poll.txt", description = "poll filename path")
})
public class PollServlet extends HttpServlet {


    /**
     * Inits the servlet, inits the PollData object which contains all data we need about the poll and shares it
     * with other servlets
     * @throws ServletException ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        final String pollFilename = this.getInitParameter("poll_filename");
        final PollData pollData = new PollData(getServletContext().getRealPath(pollFilename));

        this.getServletContext().setAttribute("poll_data", pollData);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    /**
     * Processes both GET and POST requests. It builds the appropraite page for every situation.
     * In case the poll is not good (doesn't contain one question and at least 2 answers) it will build
     * the unavilable page. In case the user didn't vote it will forward the request to another
     * servlet (VoteServlet) which will get poll data and build the appropriate page with the form.
     * In case the user has already voted, it will build the results page which contains how many
     * votes for each option.
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final PollData pollData = (PollData) this.getServletContext().getAttribute("poll_data");

        response.setContentType("text/html");
        request.getRequestDispatcher("header.html").include(request, response);

        if(pollData == null || !pollData.isGood())
            request.getRequestDispatcher("not_available.html").include(request, response);
        else
        {
            request.getRequestDispatcher("results.html").include(request, response);
            if(request.getSession().getAttribute("poll_vote") == null)
                request.getRequestDispatcher("vote.html").include(request, response);
        }

        request.getRequestDispatcher("footer.html").include(request, response);

    }



}
