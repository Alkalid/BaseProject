package professorX;

//確定可以創建
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
     HttpClient httpclient = HttpClients.createDefault();

     try
     {
         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/test_group/persons");


         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", "30d2ac8ee3bb4df0a42e82b24b315599");

         String name = "Kartd";
         String userData = "Kartd who is handsome man";
         // Request body
         StringEntity reqEntity = new StringEntity("{" + 
        //		"    \"name\": \"Person2\", " + 
         		"    \"name\": \"" + name + "\", " +
        // 		"    \"userData\": \"this is Person2.\" " + 
         		"    \"userData\": \"" + userData +"\", " +
         		
         		"}");
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
