/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Martin
 */
public abstract class ResultPrinter {

    protected final static String sentimentFor = "Sentiment for ";
    protected final static String noTweets = "Error: No tweets found for ";
    protected final static String requestID = "Request ";
    protected DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public abstract void printResult(int id, String key, Date since, Date until, double result);

    public abstract void printInitiated(int id, String key, Date since, Date until);

    String getResultString(int id, String key, Date since, Date until, double result) {
        return sentimentFor + key + " from " + dateFormat.format(since) + " to " + dateFormat.format(until) + ": " + result;
    }

    String getInitatedString(int id, String key, Date since, Date until) {
        return "Request " + id + " initiated: " + key + ", " + dateFormat.format(since) + ", " + dateFormat.format(since);
    }
}
