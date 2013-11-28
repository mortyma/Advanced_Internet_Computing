package main;

import java.util.Date;

/**
 * Dispatch a sentiment analysis request to CloudScale
 * @author Martin
 */
public class RequestInvoker implements Runnable { 

    private String key;
    private Date since, until;
    private int id;
    final static String sentimentFor = "Sentiment for ";
    final static String noTweets = "Error: No tweets found for ";
    final static String requestID = "Request ";
    
    public RequestInvoker(int id, String key, Date since, Date until) {
        this.key = key;
        this.since = since;
        this.until = until;
        this.id = id;
    }

    @Override
    public void run() {
        double result = new Task().run(key, since, until);
        print(id, key, result);
    }
    
    private static synchronized void print(int id, String key, double result)  {
        System.out.print(requestID + id + ": "); 
        if(result == -1) {
            System.out.println(noTweets + key);
        } else {
            System.out.println(sentimentFor + key + ": " + result);
        }
    }
}
