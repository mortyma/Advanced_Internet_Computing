/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appengine;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.apphosting.datastore.EntityV4;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sentiment.TwitterSentimentAnalyzer;

/**
 *
 * @author martin
 */
public class SentimentProcessorTask extends HttpServlet implements IResourceLocator {

    private static final Logger log = Logger.getLogger(SentimentRequestServlet.class.getName());

    ServletContext context;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        /*
            we need the context to access files on the web server
         */
        context = req.getSession(true).getServletContext();

        //get the request parameters from the datastore
        Key request_key = KeyFactory.stringToKey(req.getParameter("request_key"));
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Entity request;
        try {
            request = datastore.get(request_key);
        } catch (EntityNotFoundException ex) {
            log.log(Level.SEVERE, null, ex);
            return;
        }

        String key = (String) request.getProperty("key");
        String since = (String) request.getProperty("since");
        String until = (String) request.getProperty("until");

        /*
            Run sentiment analysis
        */
        TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer(this);
        //TODO: using the method with java.util.Date doesn't work
        double value = analyzier.sentimentFor(key, since, until);

        log.log(Level.INFO, "Sentiment for {0}: {1}", new Object[]{key, value});               
        
        //TODO: write to datastore       
        
    }

    @Override
    public InputStream getResource(String file) {

        if (context == null) {
            throw new NullPointerException();
        }
        InputStream in = context.getResourceAsStream(file);
        if (in == null) {
            throw new IllegalArgumentException("can't open file: " + file);
        }
        return in;
    }
}
