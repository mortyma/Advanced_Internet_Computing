package cloudscale;

import java.util.UUID;

import at.ac.tuwien.infosys.cloudscale.monitoring.CPUUsage;
import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;
import at.ac.tuwien.infosys.cloudscale.vm.IVirtualHostPool;

public class TestPolicy  implements IScalingPolicy
{

	public boolean scaleDown(IHost arg0, IHostPool arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public IHost selectHost(ClientCloudObject newCloudObject, IHostPool hostPool) 
	{

		System.out.println("-------------------------------------------------------------");
		System.out.println("Start selectHost");
		
		IHost selected = null;

		for (IHost host : hostPool.getHosts()) {
			CPUUsage cpu = host.getCurrentCPULoad();
			System.out.println(String.format(
					"Host %s (%s) has reported current CPU load %f", host
							.getId().toString(), host.getIpAddress(), cpu
							.getCpuLoad()));
			if ((cpu.getCpuLoad() != -1 && cpu.getCpuLoad() < 0.75)
					|| host.getCloudObjectsCount() == 0) {
				selected = host;
			}
		}
		System.out.println("-------------------------------------------------------------");
		
		if (selected == null)
			System.out.println("Found no suitable host, scaling up");
		else
			System.out.println("Using host " + selected.getId().toString());
		System.out.println("-------------------------------------------------------------");

		if (selected == null) 
		{
			System.out.println("Start a new host!");
			selected = hostPool.startNewHost();
		}
		
		return selected;
	}
	
	
	public boolean scaleDown(IHost host, IVirtualHostPool hostPool) {

		// we scale down iff
		// - it is online
		// - the host is currently unused
		// - this is none of our static instances, which we never tear down
		// - there is at least one other unused host

		boolean result = true;
		
//		synchronized (lock) {

			System.out.println("-------------------------------------------------------------");
			System.out.println("Checking whether to scale down host "+host.getId().toString());
			
			if(!host.isOnline()) {
				result = false;
				System.out.println("Not scaling down. Host is offline");
			}

			if(host.getCloudObjectsCount() > 0) {
				result = false;
				System.out.println("Not scaling down. Host is in use");
			}

			if(!otherUnusedHost(hostPool, host.getId())) {
				result = false;
				System.out.println("Not scaling down. Host is the last unused host");
			}
//		}
		if(result) {
			System.out.println("Scaling down host "+host.getId().toString());
		}
		return result;
	}

	private boolean otherUnusedHost(IVirtualHostPool hostPool, UUID id) {

		for(IHost host : hostPool.getHosts()) {
			if(!host.getId().equals(id) && host.getCloudObjectsCount() == 0)
				return true;
		}

		return false;

	}

}



	
	

