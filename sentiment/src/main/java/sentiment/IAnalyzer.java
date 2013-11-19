package sentiment;

/**
 *
 * @author martin
 */
public interface IAnalyzer {
    
    /**
     * Perform sentiment analysis for the given text. 
     * @param text
     * @return {0,1} with 0 indicating negative, 1 indicating positive sentiment
     */
    int analyze(String text);
}
