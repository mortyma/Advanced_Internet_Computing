/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Martin
 */
public class RequestSimulatorTest extends TestCase {
    
    public RequestSimulatorTest(String testName) {
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

    public void testSanity() {
        RequestSimulator sim = new RequestSimulator();
	sim.setTimestampStep(1000);
	sim.start();
    }
}
