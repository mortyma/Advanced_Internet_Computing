package at.ac.tuwien;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sentiment.TwitterSentimentAnalyzer;

public class HelloWorldServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	  
	  TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer();
      double value = 0;
      value = analyzier.sentimentFor("obama", new Date(2013, 12, 01), new Date(2013, 12, 25));
      
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world "+value);
	}
}
