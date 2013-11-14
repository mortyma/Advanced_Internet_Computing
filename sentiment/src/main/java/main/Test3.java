package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;

public class Test3 {

	@CloudScaleShutdown
	public static void main(String[] args)
	{
		Task t = new Task();
		//System.out.println(t.run("obama"));
		String line;
		try 
		{
			System.out.println("enter your company name:");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			line = bufferedReader.readLine();
			
			while(line != null)
			{
				System.out.println(t.run(line));
				System.out.println("enter your company name:");
				line = bufferedReader.readLine();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
