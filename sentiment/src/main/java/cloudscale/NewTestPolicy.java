package cloudscale;

import java.util.UUID;

import at.ac.tuwien.infosys.cloudscale.monitoring.CPUUsage;
import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

public class NewTestPolicy  implements IScalingPolicy
{
	private Object lock = new Object();
	
	
	//scaling up policy
	// for each host if CPU < 75% or the host is running no object then choose it, otherwise start a new host
	public IHost selectHost(ClientCloudObject newCloudObject, IHostPool hostPool) 
	{
		System.out.println("##################################################################");
		System.out.println("Starting to select host...");
		System.out.println("There are " + hostPool.getHostsCount() + " cloud hosts in the cloud pool now!");
		System.out.println("##################################################################");


		// avoiding scale up and down at the same time by lock!
		synchronized (lock) {
			
			System.out.println("-------------------------------------------------------------");

			IHost selected = null;
			for (IHost host : hostPool.getHosts()) {
				CPUUsage cpu = host.getCurrentCPULoad();
				System.out.println("##################################################################");
				System.out.println(String.format(
					"Host %s (%s) has reported current CPU load %f",
					host.getId().toString(), host.getIpAddress(), cpu.getCpuLoad()
					
				));
				System.out.println("##################################################################");
				if((cpu.getCpuLoad() != -1 && cpu.getCpuLoad() < 0.75) || host.getCloudObjectsCount() == 0) {
					selected = host;
				}
				
			}

			if(selected == null){
				System.out.println("##################################################################");
				System.out.println("Found no suitable host, scaling up");
				System.out.println("##################################################################");

			}
			else
			{
				System.out.println("##################################################################");
				System.out.println("Using host "+selected.getId().toString());
				System.out.println("##################################################################");
			}


			
		// no suitable host found, start a new one (and register monitoring for the host)
		// (and start a reserve instance)
			if (selected == null) { 
				selected = hostPool.startNewHost();
				}
			
		return selected;
		}
	}
	
	
	
	public boolean scaleDown(IHost host, IHostPool hostPool) {

		// Scaling down policy
		// we do scaling down for cloud hosts which are:
		// running and unused, and it is not the last host 
		
		System.out.println("##################################################################");
		System.out.println("Starting to do scaling down policy ...");
		System.out.println("There are " + hostPool.getHostsCount() + " cloud hosts in the cloud pool now!");
		System.out.println("##################################################################");

		boolean result = true;
		
		synchronized (lock) {
			
			System.out.println("##################################################################");
			System.out.println("Checking whether to scale down host "+host.getId().toString());
			System.out.println("##################################################################");
			
			if(!host.isOnline()) {
				result = false;
				System.out.println("##################################################################");
				System.out.println("Not scaling down. Host is offline");
				System.out.println("##################################################################");
			}

			if(host.getCloudObjectsCount() > 0) {
				result = false;
				System.out.println("##################################################################");
				System.out.println("Not scaling down. Host is in use");
				System.out.println("##################################################################");
			}

			if(!otherUnusedHost(hostPool, host.getId())) {
				result = false;
				System.out.println("##################################################################");
				System.out.println("Not scaling down. Host is the last unused host");
				System.out.println("##################################################################");
			}
		}
		if(result) {
			System.out.println("##################################################################");
			System.out.println("Scaling down host "+host.getId().toString());
			System.out.println("##################################################################");
			
		}
	
		return result;
	}
	
	

	private boolean otherUnusedHost(IHostPool hostPool, UUID id) {

		for(IHost host : hostPool.getHosts()) {
			if(!host.getId().equals(id) && host.getCloudObjectsCount() == 0)
				return true;
		}

		return false;

	}

	
}
	
	
	
