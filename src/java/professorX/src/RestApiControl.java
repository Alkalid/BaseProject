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
		String url = "https://lh3.googleusercontent.com/RP19FiojrnPe5j5SFLMz6c437Jv5MFsEP0ii8-nVhoGGz9269jcahZjkhsL35xOLcLtsgmY0_V73Mi_RsOx5bycjO721-mdoZI_Ux49hXbtlMFbyMDEWdf9nHdvIIRBw4jc077kM3uPx0AZXW83LA8cNgTTjR898aj1qHIG910aoKGrjnOvZISvPdq76JknRs-kgy7KQdTN_UX0czdXlU3QUDnZ9hiakC-emLD_RPtauOs3g2i5T_MQ6J0Ya6IrzqjKDm0Fn1IHsj2C3a4p5Xk7Q169EqKfy1lyW4khoyh5ohmt-uYvTdAjlhJIhwhvFZeIjJvBAx9_uJp3xGffQI9Sirz4r2r8wyK3qMZCW-whrCpY4ts0BaBky2EdU5QPo2T3H06oIBhb6F4CJGJrD24Tja4RmauOmMDtlOL8bs9yrm6qviuUWVRrIk3TOmD1JbszTbfIC40sBR0NfVLBSz5xO346TivOUTCryVQGHOfJ8bcOk4r3MjYgzHe5JKTHKzu4HX-LMYKY6sD1HD7vOSVFTo7wGhLtBIQHgrCSmjzS1pwlrlvRKErYW0D6Fk-RMm9d_QGi4CnelSHfyd3p6FyeRSkZABo0aGyQ6OGAL7JIIoZRPM3PRAwvE8oY6tft0vdr3pyOG8_dAX707uEOXoGiyeeeVAkOxfaA38b8YTLkECP0szy_VHG6TalE=s920-w690-h920-no";
		
		
		Face_Detect Detect = new Face_Detect();
		String faceId = Detect.Face_Detect(url);
		faceId = faceId.substring(1, faceId.length() -1 );
		System.out.println("string faceId: "+ faceId );
		
		JSONObject result = new JSONObject(faceId);
		
		System.out.println("faceId: "+result.getString("faceId"));
		faceId = result.getString("faceId");
		
		
		
		Face_Identify Identify = new Face_Identify();
		
		
		String personId =Identify.Face_Identify(faceId);
		personId = personId.substring(1, personId.length() -1 );
		System.out.println("personId : " + personId );
		
		//JSONObject IdentifyResult = new JSONObject(personId);
/////////////////////////////////////////////////////////////////////
		JSONObject j =  new JSONObject(personId);
		Object jsonOb = j.getJSONArray("candidates").getJSONObject(0).getString("personId");
		
		System.out.println( jsonOb );		//這是回傳結果perconid
		
		
		
	}

}
