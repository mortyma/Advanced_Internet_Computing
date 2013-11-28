package cloudscale;

import java.util.logging.Level;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleConfigurationProvider;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfiguration;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfigurationBuilder;
import at.ac.tuwien.infosys.cloudscale.policy.sample.HostPerObjectScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ec2.EC2CloudPlatformConfiguration;

public class Configuration 
{
	@CloudScaleConfigurationProvider
	public static CloudScaleConfiguration getConfiguration()
	{	
		return getLocalConfiguration();
	}
	
	private static CloudScaleConfiguration getLocalConfiguration()
	{
		return CloudScaleConfigurationBuilder
				.createLocalConfigurationBuilder(new TestPolicy(), Level.SEVERE)
				.withMonitoring(true)
				.withUI(true)
				.build();
	}
	
	private static CloudScaleConfiguration getEC2Configuration()
	{
      EC2CloudPlatformConfiguration cloudPlatformConfiguration = new EC2CloudPlatformConfiguration();
      cloudPlatformConfiguration.setAwsConfigFile("ec2.props");
      

      return CloudScaleConfigurationBuilder.createLocalConfigurationBuilder(new TestPolicy(), Level.SEVERE)
              .with(cloudPlatformConfiguration)
              .build();
	}
	
	
}
