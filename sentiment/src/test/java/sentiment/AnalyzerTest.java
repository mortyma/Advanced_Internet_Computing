package sentiment;

import junit.framework.TestCase;

/**
 *
 * @author martin
 */
public class AnalyzerTest extends TestCase {
    
    public AnalyzerTest(String testName) {
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
     * A basic sanity test
     */
    public void testAnalyze() {
        Analyzer instance = new Analyzer();
        assertEquals(1, instance.analyze("obama rulez"));
        assertEquals(0, instance.analyze("obama sucks"));
    }
    
}
