/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appengine;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import static com.google.appengine.api.taskqueue.TaskOptions.Builder.*;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.*;
import java.util.logging.Level;

public class SentimentRequestServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(SentimentRequestServlet.class.getName());
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String key = req.getParameter("key");
        String since = req.getParameter("since");
        String until = req.getParameter("until");

        //TODO: error handling, validate parameters
        log.log(Level.INFO, "Request initiated: {0}, {1}, {2}", new Object[]{key, since, until});

        //TODO: what is kind and name?
        //Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
        /*
            Create an entry in the task store
        */
        Key db_key = KeyFactory.createKey("Sentiment", "default");
        Entity request = new Entity("request", db_key);
        request.setProperty("key", key);
        request.setProperty("since", since);
        request.setProperty("until", until);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key request_key = datastore.put(request);
        
        //TODO: wrap db entry and task creation in transaction?
        /*
            Create a new task to do the compulationally intensitive work 
            of calculating the sentiment
        */
        Queue queue = QueueFactory.getQueue("sentiment-queue");
        //TODO: pass along request_key
        queue.add(withUrl("/SentimentProcessorTask").param("key", key).param("since", since).param("until", until));
       
        //TODO: give some sort of confirmation to the user
        resp.sendRedirect("/SentimentAnalysis.jsp");
    }
}
