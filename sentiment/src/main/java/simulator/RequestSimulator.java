package simulator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class RequestSimulator {
	public static final String DEFAULT_INPUT_FILE = "files/simulator/input.txt";
	public static final int DEFAULT_TIMESTAMP_STEP = 1000;
	private String inputFile;
	private int timestampStep;
	private List<AnalyseRequest> requests;

	public RequestSimulator() {
		this.inputFile = RequestSimulator.DEFAULT_INPUT_FILE;
		this.timestampStep = RequestSimulator.DEFAULT_TIMESTAMP_STEP;
		this.requests = new ArrayList<AnalyseRequest>();
		init();
	}

	public RequestSimulator(String inputFile, int timestampStep) {
		this.inputFile = inputFile;
		this.timestampStep = timestampStep;
		this.requests = new ArrayList<AnalyseRequest>();
		init();
	}

	public void init() {
		try {
			File dataSource = new File(this.inputFile);
			Scanner sc = new Scanner(dataSource);
			while (sc.hasNext()) {
				AnalyseRequest newRequest = new AnalyseRequest(sc.nextInt(),
						sc.next());
				requests.add(newRequest);
				System.out.println("init " + newRequest.toString());
			}
			sc.close();

		} catch (Exception e) {
			System.out.println("failed to init simulator.");
		}
	}

	public void start() {
		Timer timer = new Timer();
		timer.schedule(new SimulatorTimerTask(this.requests,timer), 0,
				this.timestampStep);
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public int getTimestampStep() {
		return timestampStep;
	}

	public void setTimestampStep(int timestampStep) {
		this.timestampStep = timestampStep;
	}

	public List<AnalyseRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<AnalyseRequest> requests) {
		this.requests = requests;
	}

}
