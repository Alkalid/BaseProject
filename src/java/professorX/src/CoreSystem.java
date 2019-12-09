import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class CoreSystem
{
	
	private Connection con_Demo ;					// 資料庫連線物件
	
	private String admin_name = "abc";				// 資料庫管理員名稱
	private String admin_pass = "123456";			// 資料庫管理員的登入密碼
	
	private String Demo_SQLServerIP = "127.0.0.1";	// 資料庫IP位址
	private String Demo_SQLServerName = "Demo";		// 資料庫名稱
		
	public CoreSystem()
	{
		
	}
	
	public static void main(String args[])
	{
		CoreSystem cs = new CoreSystem();
		cs.connect();
		cs.NewData("C002", "James", "0912153130", "火星");
		//cs.Modify("C001","123");
		//cs.Get("C001");
		//cs.Delete("C001");
	}
	
	public void connect()	// 資料庫連線啟動
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
	
	public void close()	// 資料庫連線關閉
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
	
	
	public String Get(String id)		// 取得資料
	{
		String data = "";
		try
		{
			ResultSet rs;
			Statement stm = con_Demo.createStatement();	
			
			rs = stm.executeQuery("select test.* from test where test.id = '"+id+"'");	//�T�ӿﶵ�������j�M
			while(rs.next())
			{ 
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("number"));
				System.out.println(rs.getString("address"));
				
				
				data = rs.getString("name")+" "+rs.getString("number")+" "+rs.getString("address");//跟資料庫連結拿東西
			}
			stm.close();
		}
		catch (Exception exe)
		{
			System.out.println("Get Exception : "+exe.getMessage());
		}
		
		return data;
	}
	
	public void Modify(String id, String name)		// 修改資料
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
	
	public void Delete(String id)		// 移除資料
	{
		try
		{
			// 清除作業開始
			PreparedStatement patmt = con_Demo.prepareStatement("delete test from test where test.id='"+id+"'");
			patmt.executeUpdate();	
			patmt.close();
		}
		catch (Exception exe)
		{
			System.out.println("Delete Exception : "+exe.getMessage());
		}		
	}	
	
	public void NewData(String d1,String d2,String d3,String d4) 	// 新增資料
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
}