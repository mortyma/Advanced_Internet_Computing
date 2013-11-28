package simulator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.Task;

public class SimulatorTimerTask extends TimerTask {
	private List<AnalyseRequest> requests;
	private int currentTimestamp;
	private Timer timer;

	public SimulatorTimerTask(List<AnalyseRequest> requests, Timer timer) {
		super();
		this.requests = requests;
		this.currentTimestamp=0;
		this.timer=timer;
	}

	@Override
	public void run() {
		if(this.requests.size()<1)
			stopTimer();
		List<AnalyseRequest> rqs=getRequestByTimestamp(this.currentTimestamp);
		for(AnalyseRequest r:rqs){
			System.out.println("handling request ["+r.getKeyword()+"] at timestamp ["+this.currentTimestamp+"]");
			//test code matches current implementing
			Task task=new Task();
			Date since=null;
			Date until=null;
			try {
				since = new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-19");
				until = new SimpleDateFormat("yyyy-mm-dd").parse("2013-11-28");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("**** analyse result for ["+r.getKeyword()+"] is: "+task.run(r.getKeyword(), since, until));
		}
		if(this.requests.size()<1)
			stopTimer();
		increaseTimestamp();
	}
	
	public List<AnalyseRequest> getRequestByTimestamp(int timestamp){
		List<AnalyseRequest> rqs=new ArrayList<AnalyseRequest>();
		for(AnalyseRequest r:requests){
			if(r.getTimestamp()==timestamp){
				rqs.add(r);
			}
		}
		for(AnalyseRequest r:rqs){
			requests.remove(r);
		}
		
		return rqs;
	}
	
	private void increaseTimestamp(){
		this.currentTimestamp++;
		System.out.println("timestamp increases to ["+this.currentTimestamp+"], still ["+this.requests.size()+"] requests in queue.");
	}
	
	private void stopTimer(){
		System.out.println("close timer at timestamp ["+this.currentTimestamp+"]");
		timer.cancel();
	}

}
