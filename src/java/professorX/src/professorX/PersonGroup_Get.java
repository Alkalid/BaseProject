package professorX;


//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

public class PersonGroup_Get 
{
 public static void main(String[] args) 
 {
     HttpClient httpclient = HttpClients.createDefault();

     try
     {
         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/test_group");

         builder.setParameter("returnRecognitionModel", "false");

         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);
         request.setHeader("Ocp-Apim-Subscription-Key", "30d2ac8ee3bb4df0a42e82b24b315599");


         // Request body
         StringEntity reqEntity = new StringEntity("{group1}");
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