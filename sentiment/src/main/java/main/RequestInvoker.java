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
    private RequestDispatcher dispatcher = null;
    
    public RequestInvoker(RequestDispatcher dispatcher, int id, String key, Date since, Date until) {
        this.dispatcher = dispatcher;
        this.key = key;
        this.since = since;
        this.until = until;
        this.id = id;
    }

    @Override
    public void run() {
        double result = new Task().run(key, since, until);
        dispatcher.print(id, key, result);
    }
}
