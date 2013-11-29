/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Martin
 */
public class RequestDispatcher {
    
    private ExecutorService executor;
    private ResultPrinter printer;
    public RequestDispatcher(ResultPrinter printer) {
         executor = Executors.newCachedThreadPool();
         this.printer = printer;
    }
    
    public void dispatch(int id, String key, Date since, Date until) {
         executor.execute(new RequestInvoker(printer, id, key, since, until));
    }
    
    public void shutdown() {
        try {
            //wait for all threads to 1finish
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
}
