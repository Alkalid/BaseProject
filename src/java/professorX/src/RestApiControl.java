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
		String url = "https://lh3.googleusercontent.com/HWAQ4ZeECoasI3llQQwjKD8ZortJ9DHU3eO9yMDRxR64DkiB_YVnXsGBockTllbcpOBR7QVXgb4ko6yE5ufJfHqrOT3OyLKcvB7tzM956sx8z8CUSFxOKMmmxfkHPFyJaKfpXTGMzg1THDtYrdD67cgspD8aK_sxp3ms6RfsT0ZydNoXeqZT-73KLlKWTN5J5srZ1bRLpZQ4Cwi0SDhst4RU3vkzp6Nk2uTuPDUIkGM3IrKthI5uBSqyP0ZH0_WaS7BloeoqDGPhEZlURhm3XjJkpYouCAIPc_nNYbeDktsY5ZW80QzbLBW1kE59ny6CPnB6gmlYFVoivorm5vAOS85ClQumLutESUHhG6lJkxc1L8g8ESeHY387SLDVCdwARybJ7Ki4y4AgXOZCK32Q02ouEPmO9DMcSdvI-c9tc2_R8_sXvmSkJRMazQ_OSoCjuCQXxc16UCDRZc1N33X7j6itMaPKuGUyNxmBcMZ7E9iYuJI1P9BskUohbCclJTXHrRaFT4suINvR4ndL2bKw-Kgu_5N_gavnt-ePdetUF8UUwlehGrBPlE6OYewpyOzHOJGlxvB472ttlq3xOtgYfcZ47ubnWL3D23i5xN_UbsqNpr5p-Uo2lC9dKMia_5iiX2x0wY_vk5yP8XXJi3QmI51Ee7EQYG2gW0P44SCeEKUimnM_bIlz9Q=w690-h920-no";
		
		
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
