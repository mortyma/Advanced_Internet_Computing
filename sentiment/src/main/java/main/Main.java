package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import twitter.TwitterSentimentAnalyzer;
import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;

public class Main {

	@CloudScaleShutdown
	public static void main(String[] args) {
		 
		Date since, until;
		String key;

		String[] line;
		try {
			System.out.println("enter key, since, until. (e.g. aic, 2013-11-01, 2013-11-19)");
			BufferedReader bufferedReader = new BufferedReader(	new InputStreamReader(System.in));
			line = bufferedReader.readLine().split(",");

			while (line != null) 
			{
				try
				{
					key = line[0];
					since = new SimpleDateFormat("yyyy-mm-dd").parse(line[1]);
					until = new SimpleDateFormat("yyyy-mm-dd").parse(line[2]);
					for(int i = 0; i < 100; i++)
						System.out.println(new Task().run(key, since, until));
					
					System.out.println("enter key, since, until. (e.g. aic, 2013-11-01, 2013-11-19)");
					line = bufferedReader.readLine().split(",");
					
				} catch (Exception e) 
				{
					System.out.println(e.getMessage());
					line = bufferedReader.readLine().split(",");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
