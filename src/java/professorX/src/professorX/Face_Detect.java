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

public class Face_Detect 
{
	 public static void  main(String[] args) 
	 {
	     
	 }
	 
	 public String Face_Detect(String url)
	 {
		 HttpClient httpclient = HttpClients.createDefault();
	     String  result ="";
	 
	     try
	     {
	    	 //String url = "https://images.chinatimes.com/newsphoto/2019-04-16/900/20190416002010.jpg";
	    	 
	    	 
	         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/detect");
	
	         builder.setParameter("returnFaceId", "true");
	         builder.setParameter("returnFaceLandmarks", "false");
	         //builder.setParameter("returnFaceAttributes", "{string}");
	         builder.setParameter("recognitionModel", "recognition_02");
	         builder.setParameter("returnRecognitionModel", "false");
	         builder.setParameter("detectionModel", "detection_01");
	         //detection_01:普通       detection_02最新
	
	         URI uri = builder.build();
	         HttpPost request = new HttpPost(uri);
	         request.setHeader("Content-Type", "application/json");
	         request.setHeader("Ocp-Apim-Subscription-Key", "30d2ac8ee3bb4df0a42e82b24b315599");
	
	
	         // Request body
	         StringEntity reqEntity = new StringEntity(
	        		 
	        		 "{" + 
	        			       
		         		"    \"url\": \"" + url + "\" " +
		         		
		         		"}"
	        		 
	        		 );
	         request.setEntity(reqEntity);
	
	         HttpResponse response = httpclient.execute(request);
	         HttpEntity entity = response.getEntity();
	        
	         
	         if (entity != null) 
	         {
	        	 result = EntityUtils.toString(entity);
	             System.out.println("face:"+result );
	         }
	         
	        
	     }
	     catch (Exception e)
	     {
	         System.out.println("Face_Detect Exception: "+ e.getMessage());
	         
	     }
	    
	     return result;
	 }
}
