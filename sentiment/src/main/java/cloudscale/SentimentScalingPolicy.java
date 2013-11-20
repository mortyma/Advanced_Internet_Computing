package cloudscale;

import at.ac.tuwien.infosys.cloudscale.policy.IScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ClientCloudObject;
import at.ac.tuwien.infosys.cloudscale.vm.IHost;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

public class SentimentScalingPolicy implements IScalingPolicy {

	public boolean scaleDown(IHost arg0, IHostPool arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public IHost selectHost(ClientCloudObject arg0, IHostPool arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
