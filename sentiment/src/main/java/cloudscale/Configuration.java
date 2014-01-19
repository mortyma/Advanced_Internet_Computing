package cloudscale;

import java.util.logging.Level;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleConfigurationProvider;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfiguration;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfigurationBuilder;
import at.ac.tuwien.infosys.cloudscale.messaging.MessageQueueConfiguration;
import at.ac.tuwien.infosys.cloudscale.policy.sample.HostPerObjectScalingPolicy;
import at.ac.tuwien.infosys.cloudscale.vm.ec2.EC2CloudPlatformConfiguration;

public class Configuration 
{
	@CloudScaleConfigurationProvider
	public static CloudScaleConfiguration getConfiguration()
	{	
		return getEC2Configuration();
	}
	
	private static CloudScaleConfiguration getLocalConfiguration()
	{
		return CloudScaleConfigurationBuilder
				.createLocalConfigurationBuilder(new HostPerObjectScalingPolicy(), Level.INFO)
				.withMonitoring(true)
				.withUI(true)
				.build();
	}
	
	private static CloudScaleConfiguration getEC2Configuration()
	{
      	EC2CloudPlatformConfiguration cloudPlatformConfiguration = new EC2CloudPlatformConfiguration();
		cloudPlatformConfiguration.setAwsConfigFile("ec2.props");
      

	      return CloudScaleConfigurationBuilder
			.createLocalConfigurationBuilder(new HostPerObjectScalingPolicy(), Level.INFO)
	              .with(cloudPlatformConfiguration).withMQServer("ec2-54-207-29-204.sa-east-1.compute.amazonaws.com", 61616)
			.withGlobalLoggingLevel(Level.ALL)
	              .withMonitoring(true)
	              .build();
	}
	
	
	private static CloudScaleConfiguration getEC2ConfigurationTest()
	{
		EC2CloudPlatformConfiguration ec2CloudPlatformConfig = new EC2CloudPlatformConfiguration();
		ec2CloudPlatformConfig.setAwsConfigFile("ec2.props");
		MessageQueueConfiguration messageQueueConfig = new MessageQueueConfiguration();

		messageQueueConfig.setServerAddress("ec2-54-207-40-43.sa-east-1.compute.amazonaws.com");
		//ec2CloudPlatformConfig.setMessageQueueConfiguration(messageQueueConfig);
		
		CloudScaleConfiguration configuration = CloudScaleConfigurationBuilder
		.createLocalConfigurationBuilder().with(ec2CloudPlatformConfig).withMQServer("ec2-54-207-29-204.sa-east-1.compute.amazonaws.com", 61616)
		.withGlobalLoggingLevel(Level.ALL)
		 .with(new HostPerObjectScalingPolicy() )
		.withMonitoring(true)
		.build();
//		configuration.common().clientLogging().setDefaultLoggingLevel(Level.ALL);
//		configuration.common().setCommunicationConfiguration(messageQueueConfig);
		
		return configuration;
	}
	
	
}
