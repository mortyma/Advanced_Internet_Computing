package cloudscale;

import java.util.Date;

import at.ac.tuwien.infosys.cloudscale.annotations.ByValueParameter;
import at.ac.tuwien.infosys.cloudscale.annotations.CloudObject;
import at.ac.tuwien.infosys.cloudscale.annotations.DestructCloudObject;
import sentiment.TwitterSentimentAnalyzer;

@CloudObject
public class Task {
	@DestructCloudObject
	public @ByValueParameter double run(@ByValueParameter String key,@ByValueParameter Date since,@ByValueParameter Date until)
	{
		TwitterSentimentAnalyzer analyzier = new TwitterSentimentAnalyzer();
                return analyzier.sentimentFor(key, since, until);
	}

}
