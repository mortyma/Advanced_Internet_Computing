/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Martin
 */
public class StdOutPrinter extends ResultPrinter {

    @Override
    public synchronized void print(int id, String key, double result) {
        System.out.print(requestID + id + ": "); 
        if(result == -1) {
            System.out.println(noTweets + key);
        } else {
            System.out.println(sentimentFor + key + ": " + result);
        }
    }

    @Override
    public void printInitiated(int id) {
         System.out.println("Request " + id + ": initiated");
    }
    
    
    
}
