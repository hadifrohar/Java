package com.hadi.ex2;

import javax.json.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/** A class that contains poll data such as question and options*/
public class PollData {

    /** A class that contains the data of an specfic option*/
    private static class VoteOption {
        /** Vote option text */
        private final String option;

        /** Count of votes for this option*/
        private int votesCount;

        /**
         * VoteOption contains the data of a specific option
         * @param option option text
         */
        VoteOption(final String option) {
            this.option = option;
        }

        /** Increases vote count by one*/
        public synchronized void ok() {
            votesCount++;
        }

        /**
         * Returns option text and votes count in a JSON object
         * @return JSON object contains poll results
         */
        public JsonObject getDetails() {
            return Json.createObjectBuilder().add("option", this.option).add("votes", this.votesCount).build();
        }
    }

    /** Poll question text*/
    private String question;
    /** Poll options (answers)*/
    private List<VoteOption> options;

    /**
     * PollData contains poll data such as question and options.
     * It recieves path of the file that contains the data, it reads it and stores the data
     * @param filePath path of file contains poll data
     */
    public PollData(final String filePath) {

        options = new ArrayList<>();

        if(filePath != null)
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath), StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null)
                    if(question == null)
                        question = line;
                    else
                        options.add(new VoteOption(line));

            } catch (IOException ignored) { }
    }

    /**
     * Votes for a specfic option. In case there is no option with this id or option id is not correct
     * (for example not a number) then it does nothing
     * @param optionId id of the option
     * @return true if vote sucess, otherwise false
     */
    public boolean vote(String optionId) {
        try {
            options.get(Integer.parseInt(optionId)).ok();
            return true;
        } catch(Exception ignored) {}

        return false;
    }

    /**
     * Get poll results. For each option it contains how many votes it has
     * @return JSON objcet that contains poll results (question, options)
     */
    public JsonObject getPollData() {

        final JsonArrayBuilder options = Json.createArrayBuilder();
        for (VoteOption option : this.options)
            options.add(option.getDetails());

        return Json.createObjectBuilder().add("question", this.question).add("options", options).build();
    }

    /**
     * Checks if the the poll is good or not. A good poll contains one question and at least two options (answers)
     * @return true if it's good, otherwise false
     */
    public boolean isGood() {
        return question != null && options.size() >=2;
    }

}
