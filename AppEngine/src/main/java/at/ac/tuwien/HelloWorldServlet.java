package at.ac.tuwien;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sentiment.TwitterSentimentAnalyzer;

public class HelloWorldServlet extends HttpServlet implements IResourceLocator {
    ServletContext context;
    
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

      context = req.getSession(true).getServletContext();
      
      TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer(this);
      double value = 0;
      value = analyzier.sentimentFor("obama", new Date(2013, 12, 01), new Date(2013, 12, 25));
      
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world "+value);
	}

    @Override
    public InputStream getResource(String file) {
          
        if(context == null)
            throw new NullPointerException();
        InputStream in = context.getResourceAsStream(file);
        if(in == null) {
            throw new IllegalArgumentException("can't open file: "+ file);
        }  
        return in;
    }
}
