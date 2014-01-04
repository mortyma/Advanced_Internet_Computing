package at.ac.tuwien;

import java.io.InputStream;

/**
 *
 * @author Martin Kalany
 */
public interface IResourceLocator {
    
    /**
     * Wraps a call such as this.getClass.getResourceAsStream for the google
     * app engine context
     * @param path the file to open. the root directory is the directory of the 
     * web application: /src/main/webapp
     * @return 
     */
    public InputStream getResource(String file);
    
}
