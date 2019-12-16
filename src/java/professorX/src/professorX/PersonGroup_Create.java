package professorX;


//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PersonGroup_Create 
{
	 public static void main(String[] args) 
	 {
	     
	 }
	 public String PersonGroup_Create(String name )
	 {
		 HttpClient httpclient = HttpClients.createDefault();
		 String rsult = "";
	     try
	     {
	         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/test_group");
	         URI uri = builder.build();
	         HttpPut request = new HttpPut(uri);
	         request.setHeader("Content-Type", "application/json");
	         request.setHeader("Ocp-Apim-Subscription-Key", "30d2ac8ee3bb4df0a42e82b24b315599");

	         // Request body
	         StringEntity reqEntity = new StringEntity(
	        		 "{" + 
	        			       
		         		"    \"name\": \"" + name + "\" " +
		         		
		         		
		         		"}"
	        		 );
	         request.setEntity(reqEntity);
	         HttpResponse response = httpclient.execute(request);
	         HttpEntity entity = response.getEntity();

	         if (entity != null) 
	         {
	        	 rsult = EntityUtils.toString(entity);
	             System.out.println(rsult);
	         }
	     }
	     catch (Exception e)
	     {
	         System.out.println(e.getMessage());
	     }
	     return rsult;
	 }
}