# Basic operation system
Ubuntu 13.04

# Required software
Recent versions of Maven and Java required, that are not yet in the apt sources. Installation 
Note: `$sudo apt-get install maven` will NOT install maven 3.1; neither will `$sudo apt-get install java` install the required Java version.

#### Maven - 3.1 - http://maven.apache.org/download.cgi

Installation: Follow instructions given at the link, or do the following (if wget can't find the URL, a newer version has been released. Download it and adapt all steps to the new name):

	$cd /tmp
	$wget http://tweedo.com/mirror/apache/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.tar.gz
	$tar zxf apache-maven-3.1.1-bin.tar.gz
	$sudo cp -R apache-maven-3.1.1 /usr/local/
	$sudo ln -s /usr/local/apache-maven-3.1.1/bin/mvn /usr/bin/mvn

#### Java - 7u45 (or higher) - 

Installation:

	$sudo add-apt-repository ppa:webupd8team/java
	$sudo apt-get update
	$sudo apt-get install oracle-java7-installer

#### Check Maven and Java installation

`$mvn --version` should then print:

	Apache Maven 3.1.1 (...)
	Maven home: /usr/local/apache-maven-3.1.1
	Java version: 1.7.0_51, vendor: Oracle Corporation
	Java home: /usr/lib/jvm/java-7-oracle/jre

# Twitter API access

the application needs to be able to use the twitter API, for which credentials are required. In `sentiment/twitter4j.properties`, set the values for the keys ` oauth.consumerSecret` and `oauth.accessTokenSecret`. The secret tokens are NOT published in the repository, but you may get them from us via e-mail. 

### AWS access

To get access to the required Amazon services, the values for the keys `accessKey` and `secretKey` in `sentiment/ec2.props` need to be set.

	
#### EC2 images
The predefined images can be found on Amazon EC2. In order to connect to the virtual machines a private key is required. The private key aic13-team3-group6_MQServer.ppk need to be in the root of the repository (send us an e-mail to get it)

#### ActiveMQ Server
AMI Name: CloudScale ActiveMQ Server 

AMI ID: ami-71e3426c

Instance ID: i-dda567cc

Key pair name: aic13-team3-group6_MQServer

Username: ubuntu

# Run scripts
sudo service activemq start

# CloudScale Server
AMI Name: CloudScale_v0.2.0

AMI ID: ami-71e3426c

Instance ID: i-4b300854

Key pair name: aic13-team3-group6_MQServer

Username: ubuntu

Project path: ~/sentiment

# CloudScale configuration
The CloudScale components can be configured in the configuration class via the following 
path: ~/sentiment/src/main/java/cloudscale/Configuration.java

Don't forget to set the ActiveMQ DNS correctly!

# CloudScale Command Line Interface
mvn clean compile exec:exec

# Available features
Start sentiment analysis by entering a `key, since, until` triplet.


