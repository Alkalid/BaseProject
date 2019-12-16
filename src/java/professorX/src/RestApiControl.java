import professorX.Face_Identify;
import org.json.*;
import org.apache.http.util.EntityUtils;
import java.util.LinkedList;

import professorX.Face_Detect;

public class RestApiControl 
{
	
	
	
	public static void main(String args[])
	{
		RestApiControl rc = new RestApiControl();
		
		//rc.face_identify();
	}
	
	public LinkedList face_identify(String url )
	{
		//String url = "http://114.35.11.36/IMG_20190701_101113.jpg"; //
		
		//String url =  "https://upload.wikimedia.org/wikipedia/commons/1/1b/%E8%94%A1%E8%8B%B1%E6%96%87%E5%AE%98%E6%96%B9%E5%85%83%E9%A6%96%E8%82%96%E5%83%8F%E7%85%A7.png";
		//String url = "https://pgw.udn.com.tw/gw/photo.php?u=https://uc.udn.com.tw/photo/2019/07/12/realtime/6556698.jpg&x=0&y=0&sw=0&sh=0&exp=3600";
		LinkedList Identify_result = new LinkedList();
		
		
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
		
		if(j.getJSONArray("candidates").length() == 0 )//if 
		{
			System.out.println( "fail"  );
			Identify_result.add("fail" );
		}
		else
		{
			System.out.println( "success"  );
			
			
			Object jsonOb = j.getJSONArray("candidates").getJSONObject(0).getString("personId");
			Object jsonOb2 = j.getJSONArray("candidates").getJSONObject(0).get("confidence");
			
			Identify_result.add("success" );
			Identify_result.add(jsonOb.toString() );
			Identify_result.add(jsonOb2.toString() );
			System.out.println( "\n"+jsonOb+"\n"+jsonOb2  );		//這是回傳結果perconid
		}
		
		return Identify_result;
	}
	
	
	
	

}
