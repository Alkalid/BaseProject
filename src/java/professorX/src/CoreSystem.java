
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

class CoreSystem
{
	
	private Connection con_Demo ;					// ��嚙踐��蕭嚙質謍莎��蕭
	
	private String admin_name = "abc";				// ��嚙踐����蕭��嚙踝蕭����
	private String admin_pass = "123456";			// ��嚙踐����蕭��嚙踝蕭��貝嚙踝���
	
	private String Demo_SQLServerIP = "127.0.0.1";	// ��嚙踐��P���蕭
	private String Demo_SQLServerName = "professorx";		// ��嚙踐��蕭嚙踝���
		
	public CoreSystem()
	{
		
	}
	
	public static void main(String args[])
	{
		CoreSystem cs = new CoreSystem();
		cs.connect();
		//cs.NewAccount("kartd80165", "ddaa3732", "kartd80165@gmail.com");
		cs.checkLoginData("kartd80165", "ddaa3732");
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
}