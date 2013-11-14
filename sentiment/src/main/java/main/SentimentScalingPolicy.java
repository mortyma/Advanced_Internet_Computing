package main;

import java.util.HashMap;
import java.util.UUID;

import com.espertech.esper.epl.generated.EsperEPL2GrammarParser.concatenationExpr_return;

import at.ac.tuwien.infosys.cloudscale.monitoring.EventCorrelationEngine;
import at.ac.tuwien.infosys.cloudscale.monitoring.MonitoringMetric;
import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;
import at.ac.tuwien.infosys.cloudscale.vm.IVirtualHostPool;

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
