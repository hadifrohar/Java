package com.hadi.ex2;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A servlet that handles poll data requests (poll and results)
 */
@WebServlet(name = "JsonServlet", urlPatterns = "/getPoll")
public class JsonServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes both GET and POST requests. It checks if the user has already voted or not and based of that it
     * responds with the appropriate JSON data (results or poll)
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get poll data
        final PollData pollData = (PollData) this.getServletContext().getAttribute("poll_data");

        //the object is not initialized yet, redirect to the dispatcher servlet (PollServlet, which inits it)
        //this means that the user explicitly entered here (using the url bar). there is no mean in returning
        //data when the poll data is not good
        if(pollData == null || !pollData.isGood())
            response.sendRedirect("/");

        else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            final JsonObject result = pollData.getPollData();

            try (OutputStream out = response.getOutputStream()) {
                JsonWriter jsonWriter = Json.createWriter(out);
                jsonWriter.write(result);
                jsonWriter.close();
            }
        }

    }
}
