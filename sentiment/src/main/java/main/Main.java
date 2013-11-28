package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	@CloudScaleShutdown
	public static void main(String[] args) {
		
                final String enterRequest = "enter key, since, until. (e.g. tuw_aic, 2013-11-19, 2013-11-28)";
		Date since, until;
		String key;
                String[] line;
                ExecutorService executor = Executors.newCachedThreadPool();
                int id = 1;

		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			do
			{
				try
				{
                                        System.out.println(enterRequest);
                                        line = bufferedReader.readLine().split(",");
					key = line[0];
					since = new SimpleDateFormat("yyyy-mm-dd").parse(line[1]);
					until = new SimpleDateFormat("yyyy-mm-dd").parse(line[2]);
                                        System.out.println("Request " + id + ": initiated");
                                        executor.execute(new RequestInvoker(id, key, since, until));
                                        id++;
				} catch (Exception e) 
				{
					System.out.println(e.getMessage());
					line = bufferedReader.readLine().split(",");
				}
			} while (line != null);
                        
                        //wait for all threads to 1finish
                        executor.shutdown();
                        executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
