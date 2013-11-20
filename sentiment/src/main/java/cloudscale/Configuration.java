package cloudscale;

import java.util.logging.Level;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleConfigurationProvider;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfiguration;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfigurationBuilder;

public class Configuration 
{
	@CloudScaleConfigurationProvider
	public static CloudScaleConfiguration getConfiguration()
	{
		return CloudScaleConfigurationBuilder
				.createLocalConfigurationBuilder(new SentimentScalingPolicy(), Level.SEVERE)
				.build();
		
		//Default configuration
//		return CloudScaleConfigurationBuilder
//				.createLocalConfigurationBuilder()
//				.build();
	}

	
}
