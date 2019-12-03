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

public class Face_Identify 
{
 public static void main(String[] args) 
 {
     HttpClient httpclient = HttpClients.createDefault();

     try
     {
    	 String PersonGroupId="test_group";
    	 String faceIds ="82de1054-4dec-4293-b4ce-82a7691f6af7";
    	 String maxNumOfCandidatesReturned ="1";
    	 String confidenceThreshold ="0.5";
    	
    	 
         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/identify");

         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", "30d2ac8ee3bb4df0a42e82b24b315599");


         // Request body
         StringEntity reqEntity = new StringEntity(
        		 "{" + 
        			         		"    \"PersonGroupId\": \"" + PersonGroupId + "\", " +
        			         		"    \"faceIds\": [ \"" + faceIds +"\" ], " +
        			         		"    \"maxNumOfCandidatesReturned\": \"" + maxNumOfCandidatesReturned +"\", " +
        			         		"    \"confidenceThreshold\": \"" 		 + confidenceThreshold +"\", " +
        			         		
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
