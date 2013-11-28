package main;

import java.util.Date;

/**
 * Dispatch a sentiment analysis request to CloudScale
 * @author Martin
 */
public class RequestInvoker implements Runnable { 

    private String key;
    private Date since, until;

    final static String sentimentFor = "Sentiment for ";
    final static String noTweets = "Error: No tweets found for ";
    
    public RequestInvoker(String key, Date since, Date until) {
        this.key = key;
        this.since = since;
        this.until = until;
    }

    @Override
    public void run() {
         double result = new Task().run(key, since, until);
         print(key, result);
    }
    
    private static synchronized void print(String key, double result)  {
         if(result == -1) {
            System.out.println(noTweets + key);
        } else {
            System.out.println(sentimentFor + key + ": " + result);
        }
    }
}
