/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appengine;

import java.io.IOException;
import java.io.InputStream;
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
       
        String key = req.getParameter("key");
        String since = req.getParameter("since");
        String until = req.getParameter("until");

        context = req.getSession(true).getServletContext();

       
        TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer(this);
        double value = 0;
        //TODO: using the method with java.util.Date doesn't work
        value = analyzier.sentimentFor(key, since, until);
       
        //TODO: write to datastore       
         log.log(Level.INFO, "Sentiment for {0}: {1}", new Object[]{key, value});
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