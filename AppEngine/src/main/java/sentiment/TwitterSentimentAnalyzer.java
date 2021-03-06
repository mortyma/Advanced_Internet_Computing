package sentiment;

import appengine.IResourceLocator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Perform sentiment analysis on tweets
 * @author martin
 */
public class TwitterSentimentAnalyzer {
    
    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    IAnalyzer analyzer;
    IResourceLocator locator;
    
    
    public TwitterSentimentAnalyzer(IResourceLocator locator) {
        this.locator = locator;
        this.analyzer = new Analyzer(locator);
    }
    
    /**
     * Get sentiment value for the given key within the date range 'since' to 'until'
     * @param key
     * @param since
     * @param until
     * @return [0,1] with 0 indicating worst, 1 indicating best mood
     */
    public double sentimentFor(String key, Date since, Date until) {
        
        //make sure non of the arguments are null
        if(key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be null or empty");
        }
        if(since == null) {
            throw new IllegalArgumentException("since must not be null");
        }
        if(until == null) {
            throw new IllegalArgumentException("until must not be null");
        }
        return sentimentFor(key, formatter.format(since), formatter.format(until));
    }
    
    /**
     * Get sentiment value for the given key within the date range 'since' to 'until'
     * @param key
     * @param since format: YYYY-MM-DD
     * @param until format: YYYY-MM-DD
     * @return {-2, -1, [0,1]} with 0 indicating worst, 1 indicating best mood. 
     * -1 if no tweets for key were found
     * -2 if an error occured while accessing tweets
     */
    public double sentimentFor(String key, String since, String until) {
        Twitter twitter = new TwitterFactory().getInstance();
        int sentiment = 0, cnt = 0;
        try {
            Query query = new Query(key);
            query.setSince(since);
            query.setUntil(until);
            query.setCount(100);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    cnt++;
                    sentiment += analyzer.analyze(tweet.getText());                    
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            return -2;
        }
        
        if(cnt == 0)
            return -1;
        return (double) sentiment/(double) cnt;
    }
    
}
