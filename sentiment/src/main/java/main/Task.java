package main;

import java.util.Date;

import at.ac.tuwien.infosys.cloudscale.annotations.ByValueParameter;
import at.ac.tuwien.infosys.cloudscale.annotations.CloudObject;
import twitter.TwitterSentimentAnalyzer;

@CloudObject
public class Task {
	
	public @ByValueParameter double run(@ByValueParameter String key,@ByValueParameter Date since,@ByValueParameter Date until)
	{
		TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer();
                return analyzier.sentimentFor(key, since, until);
	}

}
