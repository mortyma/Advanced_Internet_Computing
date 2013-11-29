/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Martin
 */
public abstract class ResultPrinter {
    
    protected final static String sentimentFor = "Sentiment for ";
    protected final static String noTweets = "Error: No tweets found for ";
    protected final static String requestID = "Request ";
    
    public abstract void printResult(int id, String key, double result);
    
    public abstract void printInitiated(int id);
    
}
