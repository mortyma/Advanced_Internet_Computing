package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;
import java.text.DateFormat;

public class Main {

	@CloudScaleShutdown
	public static void main(String[] args) {
		
                final String enterRequest = "enter key, since, until. (e.g. obama, 2014-01-01, 2014-01-30)";
		Date since, until;
		String key, input = "quit";
                String[] line;
                int id = 1;
                ResultPrinter printer = new StdOutPrinter();
                RequestDispatcher dispatcher = new RequestDispatcher(printer);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

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
					since = dateFormat.parse(line[1]);
					until = dateFormat.parse(line[2]);
                                        printer.printInitiated(id, key, since, until);
                                        dispatcher.dispatch(id, key, since, until);
                                        id++;
				} catch (Exception e) 
				{
					System.out.println("Invalid request!\n");
					//line = bufferedReader.readLine().split(",");
				}
			} while (!input.equals("quit"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
