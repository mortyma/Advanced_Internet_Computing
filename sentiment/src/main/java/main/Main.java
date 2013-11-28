package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;

public class Main {

	@CloudScaleShutdown
	public static void main(String[] args) {
		
                final String enterRequest = "enter key, since, until. (e.g. tuw_aic, 2013-11-19, 2013-11-28)";
                final String sentimentFor = "Sentiment for ";
                final String noTweets = "Error: No tweets found for ";
		Date since, until;
		String key;

		String[] line;
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
                                        double result = new Task().run(key, since, until);
                                        if(result == -1) {
                                            System.out.println(noTweets + key);
                                        } else {
                                            System.out.println(sentimentFor + key + ": " + result);
                                        }					
				} catch (Exception e) 
				{
					System.out.println(e.getMessage());
					line = bufferedReader.readLine().split(",");
				}
			} while (line != null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
