import professorX.Face_Identify;
import org.json.*;
import org.apache.http.util.EntityUtils;


import professorX.Face_Detect;

public class RestApiControl 
{
	
	
	
	public static void main(String args[])
	{
		RestApiControl rc = new RestApiControl();
		
		rc.face_identify();
	}
	
	public void face_identify()
	{
		String url = "http://114.35.11.36/IMG_20190701_101113.jpg"; //
		
		
		Face_Detect Detect = new Face_Detect();
		String faceId = Detect.Face_Detect(url);
		faceId = faceId.substring(1, faceId.length() -1 );
		//System.out.println("string faceId: "+ faceId );
		
		JSONObject result = new JSONObject(faceId);
		
		//System.out.println("faceId: "+result.getString("faceId"));
		faceId = result.getString("faceId");
		
		
		
		Face_Identify Identify = new Face_Identify();
		
		
		String personId =Identify.Face_Identify(faceId);
		personId = personId.substring(1, personId.length() -1 );
		//System.out.println("personId : " + personId );
		
		//JSONObject IdentifyResult = new JSONObject(personId);
/////////////////////////////////////////////////////////////////////
		JSONObject j =  new JSONObject(personId);
		Object jsonOb = j.getJSONArray("candidates").getJSONObject(0).getString("personId");
		
		System.out.println( jsonOb );		//這是回傳結果perconid
		
		
		
	}

}
