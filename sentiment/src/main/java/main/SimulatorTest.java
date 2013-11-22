package main;

import simulator.RequestSimulator;

public class SimulatorTest {

	public static void main(String[] args) {

		RequestSimulator sim = new RequestSimulator();
		sim.setTimestampStep(1000);
		sim.start();
	}
}
