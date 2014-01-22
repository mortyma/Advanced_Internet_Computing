Advanced_Internet_Computing
===========================

Advanced Internet Computing WS2013. Repository for group 6/3
Task: Cloud Computing

# Contents
* /AppEngine		Twitter-based sentiment analysis using Google AppEngine
* /sentiment		Twitter-based sentiment analysis using CloudScale on AWS
* /paper			Contains the seminar-style overview of current elastic computing research


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
	
#### check installation:

`$mvn --version` should then print:
	Apache Maven 3.1.1 (...)
	Maven home: /usr/local/apache-maven-3.1.1
	Java version: 1.7.0_51, vendor: Oracle Corporation
	Java home: /usr/lib/jvm/java-7-oracle/jre
