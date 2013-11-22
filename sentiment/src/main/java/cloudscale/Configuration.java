package cloudscale;

import java.util.logging.Level;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleConfigurationProvider;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfiguration;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfigurationBuilder;
import at.ac.tuwien.infosys.cloudscale.policy.sample.HostPerObjectScalingPolicy;;

public class Configuration 
{
	@CloudScaleConfigurationProvider
	public static CloudScaleConfiguration getConfiguration()
	{
		return CloudScaleConfigurationBuilder
				.createLocalConfigurationBuilder(new TestPolicy(), Level.SEVERE)
				.withMonitoring(true)
				.withUI(true)
				.build();
		
		
		
		//default policy
		//new HostPerObjectScalingPolicy();
		
		//Default configuration
//		return CloudScaleConfigurationBuilder
//				.createLocalConfigurationBuilder()
//				.build();
	}

	
}
