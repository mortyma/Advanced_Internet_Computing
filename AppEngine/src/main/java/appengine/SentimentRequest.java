/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appengine;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.InputStream;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import sentiment.TwitterSentimentAnalyzer;

public class SentimentRequest extends HttpServlet implements IResourceLocator {

    private static final Logger log = Logger.getLogger(SentimentRequest.class.getName());

    ServletContext context;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        String key = req.getParameter("key");
        String since = req.getParameter("since");
        String until = req.getParameter("until");

        //TODO: error handling
        log.log(Level.INFO, "Request initiated: {0}, {1}, {2}", new Object[]{key, since, until});

        context = req.getSession(true).getServletContext();

        TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer(this);
        double value = 0;
        //TODO: using the method with java.util.Date doesn't work
        value = analyzier.sentimentFor(key, since, until);

        resp.setContentType("text/plain");
        resp.getWriter().println("Sentiment for " + key + ": " + value);
        //resp.sendRedirect("/SentimentAnalysis.jsp");
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
