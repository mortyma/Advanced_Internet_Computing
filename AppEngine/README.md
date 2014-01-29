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

#### Google App Engine SDK for Java - 1.8.7 - https://developers.google.com/appengine/downloads#Google_App_Engine_SDK_for_Java

Download and unzip. The run script will assume that the appenige sdk is in `~/appengine-java-sdk/`. Make sure that you rename the unziped directory accordingly.

# Twitter API access

the application needs to be able to use the twitter API, for which credentials are required. In `AppEngine/src/main/webapp/WEB-INF/twitter4j.properties`, set the values for the keys ` oauth.consumerSecret` and `oauth.accessTokenSecret`. The secret tokens are NOT published in the repository, but you may get them from us via e-mail. 

# Run scripts

* deploy.sh

Run the script in the projects root directory to build and deploy. The script will require you to provide login credentials. Again, you can get them via e-mail.

# Available features

Open http://aicsentiment.appspot.com/

Everything is accessible via the web-interace. Use `Analyse` to get twitter sentiment for a given key. `Batch` will send a batch of 5 requests, to simulate a bit more load.