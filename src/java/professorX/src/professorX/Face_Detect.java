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
 public static void main(String[] args) 
 {
     HttpClient httpclient = HttpClients.createDefault();

     try
     {
    	 String url = "https://lh3.googleusercontent.com/b9o8SwfGkEjVcuZyrLJUdTO_CQc5LXjHDIP8M1zVC4IArV2_346p3O_T7CRkpRTyuzwkdyCsgADSjF3DSjLXoDN-XLNL17c8ZztuYS8ZlM7P4T6k60GxoApsTgdI_7yEhLXo39WbgIgGTevjACaKeMgk9Fg-KTR8NhbMzQtFHKG3VVePprSb10FJRgupm-31Owpmnv-hnRhe3oSc4Xb_NI6ZD5ijmUcSpF6I1VLg_xImF4rb7cnECmc-mkAHMQfz5AI-GeTTZEs2XCjDJoyrWRl1rujTkuDemrfgsV00FMLyYLkVhHYvjhv4ch3woyseApD_ZJgCEc4WPNNRiaInJ2gPgJanzlU9E6vkCHOkAtFjBOWKfYorupwL98AGdNyVh3lblRDp6G9trxYloEwoTek-S4w4y8lH2CttSapCOkWAatDk4YZXZH82-zYaDXvny5YkdPegV5WDMBf8pMTnfMT70xF2SphVEipCg_JnzW3bfRBwiHXsf6ZCwZLeWB2LuV4oQhuCFuicttTtJnxCrRNGjD2dh8yRXA8aLxa1093w-O2cKHzniS6V-Roo08GAWAVDHJ_tR16f9eVIlvPQJTWJ2DDNKkxetT0p7vrj2rhquYFPEgPsiPX21s3hRfisUvCUBVcX2EO1VTRJeHBbT5k8H1Vh1QvgxfYXgizrptsD55B0MJiVeQ=w546-h969-no";
    	 
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
             System.out.println(EntityUtils.toString(entity));
         }
     }
     catch (Exception e)
     {
         System.out.println(e.getMessage());
     }
 }
}
