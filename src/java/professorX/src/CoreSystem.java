
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.security.Security;
import java.sql.*;    
import java.io.Console;

import java.net.URL;
import java.net.URLConnection;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class CoreSystem
{
	
	
	
	private Connection con_Demo ;					// ��嚙踐��蕭嚙質謍莎��蕭
	
	private String admin_name = "abc";				// ��嚙踐����蕭��嚙踝蕭����
	private String admin_pass = "123456";			// ��嚙踐����蕭��嚙踝蕭��貝嚙踝���
	
	private String Demo_SQLServerIP = "127.0.0.1";	// ��嚙踐��P���蕭
	private String Demo_SQLServerName = "professorx";		// ��嚙踐��蕭嚙踝���
	
	
	LinkedList SendPackage;
	boolean SendLock;
	public CoreSystem()
	{
		
	}
	
	public static void main(String args[])
	{
		CoreSystem cs = new CoreSystem();
		cs.connect();
		//cs.NewAccount("kartd80165", "ddaa3732", "kartd80165@gmail.com");
		//cs.checkLoginData("kartd80165", "ddaa3732");
		//cs.CreatePerson("https://images.chinatimes.com/newsphoto/2019-05-17/900/20190517003816.jpg" , "馬英九" ,"https://www.facebook.com/MaYingjeou","https://www.instagram.com/ma_yingjeou/?hl=zh-tw","台灣前總統","uml1uYPeVTUJU" );
		//cs.getIdentify("https://storage.googleapis.com/www-cw-com-tw/article/201812/article-5c29b03176521.jpg");
		//cs.Person_AddFace("d2caae92-782a-45d4-b1d9-e0d919ef1bf7","https://storage.googleapis.com/www-cw-com-tw/article/201812/article-5c29b03176521.jpg","uml1uYPeVTUJU" );
		//cs.getImg("https://www.facebook.com/CubeSat.TW/posts/2234087903287367/");
		cs.getIdentify("https://www.thinkingtaiwan.com/sites/default/files/styles/author-photo-normal/public/images/photo/writer/11146191_10152629609406065_6142867647903751859_n.jpg?itok=NLMFkvAQ");	
	}
	
	public void connect()	// ��嚙踐��蕭嚙質謍堆蕭賹蕭嚙�
	{
		try
		{
			System.out.println("connect...0");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("connect...1");
			//the target server's address / DBname / userName / passWord /
			con_Demo = DriverManager.getConnection("jdbc:mysql://"+Demo_SQLServerIP+"/"+Demo_SQLServerName+"?user="+admin_name+"&password="+admin_pass+"&useUnicode=true&characterEncoding=UTF-8");
			System.out.println("Demo - Network Deploy Server("+Demo_SQLServerIP+") - MySQL is connect...");
		}
		catch (Exception ee)
		{
			System.out.println("connect's Exception message is "+ee.getMessage());		
		}
	}
	
	public void close()	// ��嚙踐��蕭嚙質謍湛蕭謚殷蕭嚙�
	{
		try
		{
			con_Demo.close();
			System.out.println("MySQL is disconnect...");
		}
		catch (Exception ee)
		{
			System.out.println("close's message is "+ee.getMessage());		
		}
	}
	
	
	public void NewAccount(String d1,String d2,String d3	) 	// 嚙踐��筆嚙踝嚙踝蕭
	{		
		try
		{							
			PreparedStatement patmt_NDS = con_Demo.prepareStatement("insert into account values(?,?,?,?,?)");
			patmt_NDS.setString(1,d1);
			patmt_NDS.setString(2,d2);
			patmt_NDS.setString(3,d3);
			patmt_NDS.setString(4, getSerialnNumber_UID() );
			patmt_NDS.setString(5,"-");
			patmt_NDS.executeUpdate();
			patmt_NDS.close();		
		}
		catch (Exception exe)
		{
			System.out.println("NewAccount Exception : "+exe.getMessage());
		}
	}
	
	public void CreatePerson(String URL,String person_name,String person_fb,String person_ig ,String person_info ,String uid) 	// 嚙踐��筆嚙踝嚙踝蕭
	{		
		String person_id;
		String face_id;
		try
		{							
			RestApiControl AC = new RestApiControl() ;
			if(1 == 1) // URL is a image
			{
				person_id = AC.CreatePerson(person_name, person_info);
				
				face_id   = AC.Person_Add(person_id, URL);
				NewPerson(person_id , person_name ,person_fb , person_ig , person_info , uid );
				NewPersonFace(person_id , face_id , URL);
			}
			
			
			
			
		}
		catch (Exception exe)
		{
			System.out.println("NewPerson Exception : "+exe.getMessage());
		}
	}
	public void Person_AddFace(String person_id ,String URL , String uid) 	// 嚙踐��筆嚙踝嚙踝蕭
	{
		String face_id;
		try
		{
			RestApiControl AC = new RestApiControl() ;
			if(1 == 1) // URL is a image
			{
				face_id   = AC.Person_Add(person_id, URL);
				
				NewPersonFace(person_id , face_id , URL);
			}
		}
		catch (Exception exe)
		{
			System.out.println("NewPerson Exception : "+exe.getMessage());
		}
		
	}
	
	public void NewPerson(String person_id,String person_name,String person_fb,String person_ig ,String person_info ,String uid) 	// 嚙踐��筆嚙踝嚙踝蕭
	{		
		try
		{							
			PreparedStatement patmt_NDS = con_Demo.prepareStatement("insert into person values(?,?,?,?,?,?)");
			
			patmt_NDS.setString(1,person_id);
			patmt_NDS.setString(2,person_name);
			patmt_NDS.setString(3,person_fb);
			patmt_NDS.setString(4,person_ig);
			patmt_NDS.setString(5,person_info);
			patmt_NDS.setString(6,uid);
			patmt_NDS.executeUpdate();
			patmt_NDS.close();		
		}
		catch (Exception exe)
		{
			System.out.println("NewPerson Exception : "+exe.getMessage());
		}
	}
	
	public void NewPersonFace(String person_id,String face_id, String url) 	// 嚙踐��筆嚙踝嚙踝蕭
	{		
		try
		{							
			PreparedStatement patmt_NDS = con_Demo.prepareStatement("insert into person_face values(?,?,?)");
			
			patmt_NDS.setString(1,person_id);
			patmt_NDS.setString(2,face_id);
			patmt_NDS.setString(3,url);
			patmt_NDS.executeUpdate();
			patmt_NDS.close();		
		}
		catch (Exception exe)
		{
			System.out.println("NewPerson Exception : "+exe.getMessage());
		}
	}
	
	public String checkLoginData(String Account, String pw)
	{
		String lock = "false";
		String uid="";
		try
		{
			ResultSet rs;
			Statement stm = con_Demo.createStatement();	
			
			rs = stm.executeQuery("select account.* from account where account.account = '"+Account+"' and account.password = '"+pw+"'");
			while(rs.next())
			{
				uid=rs.getString("uid");
				lock = "true";
				System.out.println("check success");
				break;
			}
			stm.close();
		}
		catch (Exception exe)
		{
			System.out.println("checkLoginData Exception " +exe.getMessage());
		}		
		return lock+" "+uid;
	}
	
	public String IdInfo(String uid)		 //嚙踝蕭謕蕭豲��嚙踝����蕭��嚙踝蕭謕蕭豲��嚙踐�蕭��蕭謕蕭豲��嚙踝����蕭��嚙踝蕭謕蕭豲��嚙踝�揭
	{
		String Info = "";
		
		try
		{
			ResultSet rs;
			Statement stm = con_Demo.createStatement();	
			
			rs = stm.executeQuery("select account.* from account where account.uid = '"+uid+"' ");
			while(rs.next())
			{ 
				String name = rs.getString("name")+String.valueOf((char)(8));
				Info = name;
			}
			stm.close();
		}
		catch (Exception exe)
		{
			System.out.println("getInfo Exception " +exe.getMessage());
		}		
		return Info;
	}
	
	public String getIdentify(String URL)		
	{
		SendPackage = new LinkedList();
		SendLock = false;
		String State = "";
		String DataSuite = "";
		String PersonData = "";
		String PersonFace = "";
		String ImageData[] ;
		Boolean sizecorrect = false;
		LinkedList Identify_result = new LinkedList();
		int che6 = 6;
		try
		{
			ImageData = this.getImg(URL).split(";");
			if( Integer.valueOf(ImageData[0]) > 400 && Integer.valueOf(ImageData[1]) > 400 )
			{
				sizecorrect = true;
				RestApiControl AC = new RestApiControl();
				Identify_result = AC.face_identify( URL ); // 1.fail/succ  2.personId   3.confidence
			}
	///////////////////////////////////////////////////////////////////////////////////////////////////////// Step1   ImageSize		 
			if( sizecorrect == false ) 
			{
				SendLock = true;
				DataSuite="fail_size";
				State="fail";
			}
			else if(Identify_result.get(0).equals("fail") || sizecorrect == false) 
			{
				SendLock = true;
				DataSuite="fail";
				State="fail";
			}
			else if(Identify_result.get(0).equals("success")) 
			{
				State="success";
				PersonData = getPersonIdData( Identify_result.get(1).toString() );	
				PersonFace = getPersonIdFace( Identify_result.get(1).toString() );	
				
				
				DataSuite="success"+String.valueOf((char)(che6))+PersonData+String.valueOf((char)(che6))+PersonFace;
				//person_name 6 ....  person_info
				//person_face 6 .... 
				SendLock = true;
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////// Step2   IdentifyImage		
			
			while (true)//	 Total data
			{
				int Max = 500;
				String Data = "";
				while(true)	// Package
				{
					String reg = HalfSort(DataSuite,Max);
					Max -= reg.getBytes().length;
					DataSuite = DataSuite.substring(reg.length(), DataSuite.length());
					Data += reg;
					if (Max < 10 || reg.getBytes().length == 0)
					{
						SendPackage.add(Data);
						break;
					}
				}
				
				if (DataSuite.getBytes().length == 0)
				{
					break;
				}
			}
		}
		catch (Exception exe)
		{
			System.out.println("getIdentify Exception : "+exe.getMessage());
		}
		return State;
		
	}
	
	public String getPersonIdData(String personId )		
	{
		String data = "";
		int che6 = 6;
		try
		{
			ResultSet rs;
			Statement stm = con_Demo.createStatement();	
			rs = stm.executeQuery("select person.* from person where person.person_id = '"+personId+"'");	
			while(rs.next())
			{ 
				//System.out.println(rs.getString("person_name"));
				//System.out.println(rs.getString("person_fb"));
				//System.out.println(rs.getString("person_ig"));
				
				data = rs.getString("person_name")+String.valueOf((char)(che6))+rs.getString("person_fb")+String.valueOf((char)(che6))+rs.getString("person_ig")+String.valueOf((char)(che6))+rs.getString("person_info");
			}
			stm.close();
			
			
			
		}
		catch (Exception exe)
		{
			System.out.println("getPersonIdData Exception : "+exe.getMessage());
		}
		
		return data;
	}
	
	public String getPersonIdFace(String personId )		
	{
		String data = "";
		int che8 = 8;
		int endPart = 3;
		int FaceNum = 0;
		try
		{
			ResultSet rs;
			Statement stm = con_Demo.createStatement();	
			rs = stm.executeQuery("select person_face.* from person_face where person_face.person_id = '"+personId+"'");	
			while(rs.next())
			{ 
				FaceNum++;
				data += rs.getString("url")+String.valueOf((char)(che8));
				if(FaceNum == endPart)
				{
					break;
				}
				
			}
			stm.close();
			
		}
		catch (Exception exe)
		{
			System.out.println("getPersonIdFace Exception : "+exe.getMessage());
		}
		
		return data;
	}
	
	public String Get(String id)		// 嚙踝蕭謘潘蕭謅蕭�嚙踝蕭
	{
		String data = "";
		try
		{
			ResultSet rs;
			Statement stm = con_Demo.createStatement();	
			
			rs = stm.executeQuery("select test.* from test where test.id = '"+id+"'");	//��Ⅱ�������������������
			while(rs.next())
			{ 
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("number"));
				System.out.println(rs.getString("address"));
				
				
				data = rs.getString("name")+" "+rs.getString("number")+" "+rs.getString("address");//���蕭�嚙踐��蕭嚙質��嚙踐�嚙�
			}
			stm.close();
		}
		catch (Exception exe)
		{
			System.out.println("Get Exception : "+exe.getMessage());
		}
		
		return data;
	}
	
	public void Modify(String id, String name)		// �����嚙踝蕭
	{
		try
		{
			PreparedStatement patmt1 = con_Demo.prepareStatement("update test set test.name ='"+name+"' where test.id ='"+id+"'");
			patmt1.executeUpdate();
			patmt1.close();
		}
		catch (Exception exe)
		{
			System.out.println("Modify Exception : "+exe.getMessage());
		}
		
	}
	
	public void Delete(String id)		// �����嚙踝蕭
	{
		try
		{
			// ��謒謚怠像嚙踝蕭��蕭嚙�
			PreparedStatement patmt = con_Demo.prepareStatement("delete test from test where test.id='"+id+"'");
			patmt.executeUpdate();	
			patmt.close();
		}
		catch (Exception exe)
		{
			System.out.println("Delete Exception : "+exe.getMessage());
		}		
	}	
	
	public void NewData(String d1,String d2,String d3,String d4) 	// 嚙踐��筆嚙踝嚙踝蕭
	{		
		try
		{							
			PreparedStatement patmt_NDS = con_Demo.prepareStatement("insert into test values(?,?,?,?)");
			
			patmt_NDS.setString(1,d1);
			patmt_NDS.setString(2,d2);
			patmt_NDS.setString(3,d3);
			patmt_NDS.setString(4,d4);
			patmt_NDS.executeUpdate();
			patmt_NDS.close();		
		}
		catch (Exception exe)
		{
			System.out.println("NewData Exception : "+exe.getMessage());
		}
	}
	
	public String getSerialnNumber_UID()
	{
		Calendar ca = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("MMmmssyyyyHHdd");
		
		Date dt = ca.getTime();
		String date = df.format(dt);
		
		int g = (int)(Math.random()*60);
		int y = (int)(Math.random()*60);
		int r = (int)(Math.random()*60);
		int p = (int)(Math.random()*60);
		int e = (int)(Math.random()*60);
		
		String gg = String.valueOf(g);
		String yy = String.valueOf(y);
		String rr = String.valueOf(r);
		String pp = String.valueOf(p);
		String ee = String.valueOf(e);
		
		if (gg.length()%2 != 0)
		{
			gg+="0";
		}
		
		if (yy.length()%2 != 0)
		{
			yy+="0";
		}
		
		if (rr.length()%2 != 0)
		{
			rr+="0";
		}
		
		if (pp.length()%2 != 0)
		{
			pp+="0";
		}
		
		if (ee.length()%2 != 0)
		{
			ee+="0";
		}
		
		String s1 = date.substring(0,8);
		String s2 = date.substring(8,date.length());
		
		s1+=rr+gg;
		s2=ee+yy+s2+pp;
				
		return "u"+decode_jr(s1)+decode_ke(s2);
	}
	
	public String decode_jr(String str)
	{
		String code="";
		for (int i = 0; i<str.length(); i+=2)
		{
			String tar = str.substring(i,i+2);
			int che = Integer.parseInt(tar);
			
			if (che <= 25)					// a-z    = 00 - 25		
			{
				code+=((char)(che+97));
			}
			
			if (che > 25 && che <= 35)		// 50-75  = 26 - 51
			{
				code+=((char)(che+22));
			}
			
			if (che > 35 && che <= 60)		// A-Z    = 51 - 60
			{
				code+=((char)(che+29));
			}
		}		
		return code;
	}
	
	
	public String decode_ke(String str)
	{
		String code="";
		for (int i = 0; i<str.length(); i+=2)
		{
			String tar = str.substring(i,i+2);
			int che = Integer.parseInt(tar);
			
			if (che <= 25)					// A-Z    = 00 - 25		
			{
				code+=((char)(che+65));
			}
			
			if (che > 25 && che <= 51)		// a-z  = 26 - 51
			{
				code+=((char)(che+71));
			}
			
			if (che > 51 && che <= 60)		// 0-9    = 51 - 60
			{
				code+=((char)(che-3));
			}
		}
		return code;
	}
	
	public String HalfSort(String Sequence_, int Max)
	{
		String Sequence = Sequence_;
		
		if (Sequence.getBytes().length > Max)
		{
			while(true)
			{
				String Half = Sequence.substring(0,Sequence.length()/2);
				
				if (Half.getBytes().length > Max)
				{
					Sequence = Half.substring(0,Half.length()/2);
				}
				else
				{
					Sequence = Half;
					break;
				}
			}
		}		
			
		return Sequence;
	}
	
	public String getImg(String imageURL) 
	{
		String result = "";
		try
		{
			
			URL url = new URL(imageURL);
	        URLConnection connection = url.openConnection();
	        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        connection.setDoOutput(true);
	        BufferedImage image = ImageIO.read(connection.getInputStream());  
	        int srcWidth = image .getWidth();      // 源图宽度
	        int srcHeight = image .getHeight();    // 源图高度
	        
	        System.out.println("srcWidth = " + srcWidth);
	        System.out.println("srcHeight = " + srcHeight);
	        result = srcWidth + ";" + srcHeight ;
	        
		}
		catch (Exception exe)
		{
			System.out.println("getImg Exception : "+exe.getMessage());
		}
		return result;
	 }
	
}