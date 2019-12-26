package professorX;

//�T�w�i�H�Ы�
//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

public class PersonGroupPerson_Create 
{
	 public static void main(String[] args) 
	 {
	     
	 }
	 public String Person_Create(String name , String userData) 
	 {
		 HttpClient httpclient = HttpClients.createDefault();
	     String result = "";
	     try
	     {
	         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/test_group/persons");
	         URI uri = builder.build();
	         HttpPost request = new HttpPost(uri);
	         request.setHeader("Content-Type", "application/json");
	         request.setHeader("Ocp-Apim-Subscription-Key", "09be8044d5e44518a9b90a1f4af22f21");
	
	         // Request body
	         StringEntity reqEntity = new StringEntity(
	        		 "{" + 
	       
	         		"    \"name\": \"" + name + "\", " +
	       
	         		"    \"userData\": \"" + userData +"\", " +
	         		
	         		"}"
	         		);
	         request.setEntity(reqEntity);
	
	         HttpResponse response = httpclient.execute(request);
	         HttpEntity entity = response.getEntity();
	
	         if (entity != null) 
	         {
	        	 result = EntityUtils.toString(entity);
	             System.out.println(result);
	         }
	     }
	     catch (Exception e)
	     {
	         System.out.println(e.getMessage());
	     }
	     return result;
	 }
}
