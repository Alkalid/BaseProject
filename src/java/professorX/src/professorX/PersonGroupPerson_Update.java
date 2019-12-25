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

public class PersonGroupPerson_Update 
{
 public static void main(String[] args) 
 {
     HttpClient httpclient = HttpClients.createDefault();

     try
     {
    	 //目前中文還無法設定。
    	 String personId = "fbcaec47-2961-4306-9394-e200e56b8520";
    	 String UpdateName = "katrina";
    	 String UpdateUserData = "dear";
    	 
         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/test_group/persons/"+personId);


         URI uri = builder.build();
         HttpPatch request = new HttpPatch(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", "09be8044d5e44518a9b90a1f4af22f21");


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