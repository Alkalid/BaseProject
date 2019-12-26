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

public class PersonGroupPerson_AddFace 
{
	 public static void main(String[] args) 
	 {
	     
	 }
	 public String Person_Addface(String personId , String url) 
	 {
		 HttpClient httpclient = HttpClients.createDefault();
		 String result = "";
	     try
	     {
	    	 String personGroupId = "test_group";
	    	 //String personId = "fbcaec47-2961-4306-9394-e200e56b8520";
	    	 
	    	 //String url = "https://lh3.googleusercontent.com/QL2inObnwiaU0Jx5gDlyxiaTmfDnkFv6Ykam_s6NKP5LP0Ob7sXyybYrQmDmK1ywd-LHjeXsj9tCxMi6mOPypzn1TiZnePabBS08DzYfP4BbOvPODpJ4L87ZrOOHr6N1BErR180CA6i2lPc_FksisqL1ayxtknrifrNpu-j1RLq3FnzFKfLhxmiihHDxkzsfC4hpp4qGxUuTf_ASom8vsT_yd1tlRHim2EvEvdFSR7EGrySBd0Ij4jfN-ZOMS6ALjBffFw5Czf84dn0yfoknYXJD0Bq9LsstfTjMlxLuChvVRzRnf2uOhAeSkfGs6-Q0HWwiFmcIjg3_xdT2N2r6_NTw6c6zcvS874YnvZbhK9lxEqwVqk7BIsu1RKMk5E7YhHFeNsn_F618w9VJpWWg2whklACd6AolFSLjbv4MCdYAPnSnNUn9cONThpS9tk3C4isQCDJ6YGnI4JsQiKh6bQx6DxGy_iZnhLTb2rxVHbq57jX4YX1mqIiiCnb914x0lI3feJjddGHYi1Conw92V-8nPAAqWjCnGLls7JOCItyqw7VpfDbNJVB2izutHAcJHVtqyCf7v4uZGGFRU-HOe43StgkPqB2lGaIqfnf1Pe_s9EYOJT2xqhYc6GqdR9bssQ6yK9HiwnauuaU0lCViCEHshhOeKtoivrBhHgHyac6g0AY-hzoW6hn5ynypOQtVafF3RUbSfGfGWI_5S4CJiZbAt1eqBnpgVaILN6_3xqFi2uU=w727-h969-no";
	    			 
	    			 
	    			 
	    			 
	         URIBuilder builder = new URIBuilder("https://eastasia.api.cognitive.microsoft.com/face/v1.0/persongroups/"+ personGroupId +"/persons/" + personId + "/persistedFaces");
	         
	         //builder.setParameter("userData", "{string}");
	         //builder.setParameter("targetFace", "{string}");
	         builder.setParameter("detectionModel", "detection_01");

	         URI uri = builder.build();
	         HttpPost request = new HttpPost(uri);
	         request.setHeader("Content-Type", "application/json");
	         request.setHeader("Ocp-Apim-Subscription-Key", "09be8044d5e44518a9b90a1f4af22f21");


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
