package twitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Get tweets from the twitter data set provided by lecture
 *
 * @author martin
 */
public class OfflineData {

     //TODO: make sure this matches the path to the data file on your machine
    //TODO: need to ensure this works when handing in our solution
    private static final String path = "/media/Data/twitter/tweets.txt";
    private InputStream is;

    public OfflineData() {
        try {
            is = new FileInputStream(path);
            //TODO: read Json data, search for tweets containing a key...
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
}
