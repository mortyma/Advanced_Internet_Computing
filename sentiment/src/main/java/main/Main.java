package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;

public class Main {

	@CloudScaleShutdown
	public static void main(String[] args) {
		
                final String enterRequest = "enter key, since, until. (e.g. tuw_aic, 2013-11-19, 2013-11-28)";
		Date since, until;
		String key, input = "quit";
                String[] line;
                int id = 1;
                RequestDispatcher dispatcher = new RequestDispatcher();

		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			do
			{
				try
				{
                                        System.out.println(enterRequest);
                                        
                                        input = bufferedReader.readLine();
                                        if(input.equals("quit"))
                                            break;
                                        line = input.split(",");
					key = line[0];
					since = new SimpleDateFormat("yyyy-mm-dd").parse(line[1]);
					until = new SimpleDateFormat("yyyy-mm-dd").parse(line[2]);
                                        System.out.println("Request " + id + ": initiated");
                                       dispatcher.dispatch(id, key, since, until);
                                        id++;
				} catch (Exception e) 
				{
					System.out.println(e.getMessage());
					line = bufferedReader.readLine().split(",");
				}
			} while (!input.equals("quit"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
