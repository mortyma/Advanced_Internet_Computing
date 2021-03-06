/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sentiment;

import sentiment.TwitterSentimentAnalyzer;
import junit.framework.TestCase;

/**
 *
 * @author martin
 */
public class TwitterSentimentAnalyzerTest extends TestCase {
    
    public TwitterSentimentAnalyzerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of sentimentFor method, of class TwitterSentimentAnalyzer.
     */
    public void testSentiment_Sanity() {
       TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer();
        String key = "obama";
        double sentiment = analyzier.sentimentFor(key, "2014-01-01", "2014-01-08");
        assertTrue(sentiment  >= 0 && sentiment <= 1);
        System.out.println("Sentiment value for " + key + ": " + sentiment);
    }

  
    
}
