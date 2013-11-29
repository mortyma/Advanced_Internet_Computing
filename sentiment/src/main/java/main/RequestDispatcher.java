/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Martin
 */
public class RequestDispatcher {
    
    private ExecutorService executor;
    final static String sentimentFor = "Sentiment for ";
    final static String noTweets = "Error: No tweets found for ";
    final static String requestID = "Request ";
    
    public RequestDispatcher() {
         executor = Executors.newCachedThreadPool();
    }
    
    public void dispatch(int id, String key, Date since, Date until) {
         executor.execute(new RequestInvoker(this, id, key, since, until));
    }
    
    public void shutdown() {
        try {
            //wait for all threads to 1finish
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
    
     public static synchronized void print(int id, String key, double result)  {
        System.out.print(requestID + id + ": "); 
        if(result == -1) {
            System.out.println(noTweets + key);
        } else {
            System.out.println(sentimentFor + key + ": " + result);
        }
    }
}
