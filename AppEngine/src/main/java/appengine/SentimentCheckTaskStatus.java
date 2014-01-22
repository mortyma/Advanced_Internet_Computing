package appengine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class SentimentCheckTaskStatus extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(SentimentRequestServlet.class.getName());
	public static final int TIMEOUT=120000;
	public static final int RATE=2000;
	
	 @Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.log(Level.SEVERE, null, e);
			return;
		}
		
		String id=req.getParameter("id");
		System.out.println("*********"+id);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("request");
		Filter idFilter = new FilterPredicate("key",
                FilterOperator.EQUAL,
                id);
		Filter resultFilter = new FilterPredicate("result",
                FilterOperator.EQUAL,
                null);
		Filter filter = CompositeFilterOperator.and(idFilter,resultFilter);
		query.setFilter(filter);

		int counter=0;
		boolean finish=false;
		List<Entity> requests;
		PrintWriter out = resp.getWriter();
		try {
			while ((counter <= TIMEOUT)&&(!finish)) {
				Thread.sleep(RATE);
				counter+=RATE;
				requests = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(20));
				if(requests.size()<1){
					out.println("analyzing for "+id+" is finished!");
					finish=true;
				}
			}
			if(!finish){
				out.println("analyzing for "+id+" is timed out!");
			}
		} catch (InterruptedException e) {
			log.log(Level.SEVERE, null, e);
			return;
		}


	 }

}
