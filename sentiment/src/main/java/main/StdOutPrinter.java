/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Date;

/**
 *
 * @author Martin
 */
public class StdOutPrinter extends ResultPrinter {

    @Override
    public synchronized void printResult(int id, String key, Date since, Date until, double result) {
        System.out.print(requestID + id + ": "); 
        if(result == -1) {
            System.out.println(noTweets + key);
        } else {
            System.out.println(getResultString(id, key, since, until, result));
        }
    }

    @Override
    public void printInitiated(int id, String key, Date since, Date until) {
         System.out.println(getInitatedString(id, key, since, until));
    }   
}
