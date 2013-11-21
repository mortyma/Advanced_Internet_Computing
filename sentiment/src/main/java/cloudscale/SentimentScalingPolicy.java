package cloudscale;

import java.util.UUID;

import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

public class SentimentScalingPolicy implements IScalingPolicy {

	public boolean scaleDown(IHost arg0, IHostPool arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public IHost selectHost(ClientCloudObject newCloudObject, IHostPool hostPool) {
		 if(hostPool.getHostsCount() > 0)
         {
                 IHost selectedHost = hostPool.getHosts().iterator().next();
                 UUID hostId = selectedHost.getId();
                 System.out.println("SCALING: Deploying new object "+ 
                                                         newCloudObject.getCloudObjectClass().getName() +
                                                         " on "+(hostId != null ? hostId: "not started yet host."));
                 
                 return selectedHost;
         }
         else
         {
                 System.out.println("SCALING: Deploying new object "+
                                                                 newCloudObject.getCloudObjectClass().getName() +
                                                                 " on new host.");
                 return hostPool.startNewHostAsync();
         }

	}

}
