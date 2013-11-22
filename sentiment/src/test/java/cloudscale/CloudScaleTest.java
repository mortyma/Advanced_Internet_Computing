package cloudscale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.Task;
import junit.framework.TestCase;

public class CloudScaleTest extends TestCase 
{
	
	public CloudScaleTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CloudScaleTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
	
    
    public void testCloudScale() throws ParseException 
    {
    	
//    	for(int i = 0; i < 100; i++)
//    		System.out.println(new Task().run("aic", new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-01"), new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-19")));
    	
    	ExecutorService exec = Executors.newCachedThreadPool();
    	
    	try {
    	    for (int i = 0; i < 100; i++)
    	    {
    	        exec.execute(r1);
    	    }
    	} finally {
    	    exec.isShutdown();
    	}
    }
    
   Runnable r1 =  new Runnable() {
        public void run() {
            try {
				System.out.println(new Task().run("aic", new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-01"), new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-19")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    };

}
