/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appengine;

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

   
        Queue queue = QueueFactory.getQueue("sentiment-queue");
        queue.add(withUrl("/SentimentProcessorTask").param("key", key).param("since", since).param("until", until));
       
        resp.sendRedirect("/SentimentAnalysis.jsp");
    }
}
