package cloudscale;

import at.ac.tuwien.infosys.cloudscale.monitoring.CPUUsage;
import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

public class TestPolicy  implements IScalingPolicy
{

	public boolean scaleDown(IHost arg0, IHostPool arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public IHost selectHost(ClientCloudObject newCloudObject, IHostPool hostPool) 
	{

		System.out.println("Starting to select host");
		
		// lock the policy (we do not want to scale up and down at the same time)
//		synchronized (lock) {
			
			System.out.println("-------------------------------------------------------------");

			IHost selected = null;
			
			// for each host, check the current CPU load
			// (unused hosts we are always using)
			for (IHost host : hostPool.getHosts()) {
				CPUUsage cpu = host.getCurrentCPULoad();
				System.out.println(String.format(
					"Host %s (%s) has reported current CPU load %f",
					host.getId().toString(), host.getIpAddress(), cpu.getCpuLoad()
				));
				if((cpu.getCpuLoad() != -1 && cpu.getCpuLoad() < 0.75) || host.getCloudObjectsCount() == 0) {
					selected = host;
				}
			}
			System.out.println("-------------------------------------------------------------");
			if(selected == null)
				System.out.println("Found no suitable host, scaling up");
			else
				System.out.println("Using host "+selected.getId().toString());
			System.out.println("-------------------------------------------------------------");

//		}
			
		// no suitable host found, start a new one (and register monitoring for the host)
		// (and start a reserve instance)
		IHost newHost = hostPool.startNewHost();
		hostPool.startNewHostAsync();
		return newHost;
	}

}
