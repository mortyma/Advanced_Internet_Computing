/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;

/**
 *
 * @author Martin
 */
public class RequestInvokerTest extends TestCase {
    
    public RequestInvokerTest(String testName) {
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
     * Test of run method, of class RequestInvoker.
     */
    public void testInvokeMultipleRequests() throws InterruptedException {
        final int nr_requests = 10;
        final String key = "obama";
        Date since=null;
        Date until=null;
        try {
                since = new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-27");
                until = new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-28");
        } catch (ParseException e) {
                e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(nr_requests);
        int i;
        ResultPrinter printer = new StdOutPrinter();
        for(i = 0; i<nr_requests;i++) {
            printer.printInitiated(i);
            executor.execute(new RequestInvoker(printer, i, key, since, until));
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
    }
}
