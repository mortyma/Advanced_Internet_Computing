#Basic operation system

Ubuntu 13.04

#Required software

Recent versions of Maven and Java are required, that are not yet in the apt sources. Installation Note: $sudo apt-get install maven will NOT install maven 3.1; neither will $sudo apt-get install java install the required Java version.

Maven - 3.1 - http://maven.apache.org/download.cgi
Installation: Follow instructions given at the link, or do the following (if wget can't find the URL, a newer version has been released. Download it and adapt all steps to the new name):
$cd /tmp
$wget http://tweedo.com/mirror/apache/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.tar.gz
$tar zxf apache-maven-3.1.1-bin.tar.gz
$sudo cp -R apache-maven-3.1.1 /usr/local/
$sudo ln -s /usr/local/apache-maven-3.1.1/bin/mvn /usr/bin/mvn
Java - 7u45 (or higher) -
Installation:
$sudo add-apt-repository ppa:webupd8team/java
$sudo apt-get update
$sudo apt-get install oracle-java7-installer
#EC2 images
The predefined imges can be found on Amazon EC2. In order to connect to the virtual machines a private key is required. The private key aic13-team3-group6_MQServer.ppk is located in the repository. 
#ActiveMQ Server
AMI Name: CloudScale ActiveMQ Server 
AMI ID: ami-71e3426c
Instance ID: i-ba0deea4
Key pair name: aic13-team3-group6_MQServer
Username:ubuntu
# Run scripts
Sudo service activemq start
#CloudScale Server
AMI Name: CloudScale_v0.2.0
AMI ID: ami-71e3426c
Instance ID: i-4b300854
Key pair name: aic13-team3-group6_MQServer
Username:ubuntu
Project path: ~/sentiment
#CloudScale Command Line Interface
mvn clean compile exec:exec
# CloudScale configuration
The CloudScale components can be configured in the configuration class via the following 
path: ~/sentiment/src/main/java/cloudscale/Configuration.java






