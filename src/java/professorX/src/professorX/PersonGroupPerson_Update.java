package professorX;


//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class PersonGroup_Update 
{
 public static void main(String[] args) 
 {
     HttpClient httpclient = HttpClients.createDefault();

     try
     {
    	 //目前中文還無法設定。
    	 String personId = "ce5b9b0c-ff40-41c3-8f21-b414b06259f9";
    	 String UpdateName = "xu";
    	 String UpdateUserData = "FCU IECS Teacher";
    	 
         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/test_group/persons/"+personId);


         URI uri = builder.build();
         HttpPatch request = new HttpPatch(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", "30d2ac8ee3bb4df0a42e82b24b315599");


         // Request body
         StringEntity reqEntity = new StringEntity(
        		 "{" + 
	       
	         		"    \"name\": \"" + UpdateName + "\", " +
	        
	         		"    \"userData\": \"" + UpdateUserData +"\", " +
	         		
	         		"}"
        		 );
         request.setEntity(reqEntity);

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