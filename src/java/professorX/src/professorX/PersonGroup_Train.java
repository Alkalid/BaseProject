package professorX;

//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class PersonGroup_Train 
{
	 public static void main(String[] args) 
	 {
		
	 }
	 public void Train() 
	 {
		 HttpClient httpclient = HttpClients.createDefault();

	     try
	     {
	    	 String personGroupId = "test_group";
	         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/"+ personGroupId +"/train");


	         URI uri = builder.build();
	         HttpPost request = new HttpPost(uri);
	         request.setHeader("Ocp-Apim-Subscription-Key", "09be8044d5e44518a9b90a1f4af22f21");


	         // Request body
	         //StringEntity reqEntity = new StringEntity("{body}");
	         //request.setEntity(reqEntity);

	         HttpResponse response = httpclient.execute(request);
	         HttpEntity entity = response.getEntity();

	         if (entity != null) 
	         {
	             System.out.println(EntityUtils.toString(entity));
	         }
	     }
	     catch (Exception e)
	     {
	         System.out.println(e.getMessage());
	     }
	 }
}