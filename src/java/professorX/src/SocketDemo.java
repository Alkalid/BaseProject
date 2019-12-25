import java.net.*;
import java.io.*;

public class SocketDemo
{
	private ServerSocket server;
		
	Chat_Thread ct;		
	CoreSystem cs;
	
	public SocketDemo()
	{
		
	} 	
	
	void startServer()		// ClientThread
	{
		try					// Java 的例外處理
		{
			server = new ServerSocket(5251); 	// 設定Server port
			System.out.println("Network Deploy Service Server�Ұ�...");
			while(true)
			{
				Socket socket = server.accept();	// 連線
				ct = new Chat_Thread(socket); 		// SocketThread宣告
				ct.start();							// 連線啟動
				
				cs = new CoreSystem();				// 核心運算模組宣告
				ct.getCore(cs);						// 為SocketThread添加核心運算模組
			}
		}
		catch(Exception e)	// 例外的事件處理區域
		{
			System.out.println(e);
		}
	}

	public static void main(String[] args) 		// 主程式
	{
		SocketDemo server = new SocketDemo();	// 宣告SocketDemo 名稱為server
		server.startServer();					// 啟動SocketDemo
	}
		
	class Chat_Thread extends Thread  	// 執行緒的編輯
	{
		Socket socket; 					// 宣告Socket
		private BufferedReader reader;	// 宣告資料緩衝讀取區
		private PrintWriter writer;		// 宣告資料寫出區塊
		CoreSystem cs;					// 宣告核心運算模組物件
		
		private boolean isTerminated = false;	// 為Thread 設定一個開關
		
		Chat_Thread(Socket socket)
		{
			this.socket = socket;		// 將Socket置入執行緒
		}
		
		public void getCore(CoreSystem cs)	// 將核心運算模組物件置入執行緒
		{
			this.cs = cs;
		}
		
		public boolean isTerminated() 	// 開關開啟，將執行緒關閉
		{
			cs.close();					// 將核心運算模組物件關閉
			isTerminated = true;
			System.out.println("isTerminated is true...");
        	return isTerminated;
    	}
                   
		public void run()
		{
			System.out.println(socket.getInetAddress());	// 取得連線方的IP來源
			System.out.println("The connect form : "+socket.getInetAddress());
			
			try
			{
				int che5 = 5;
				int che6 = 6;
				int che7 = 7;
				int che8 = 8;
				int che9 = 9;
				int che10 = 10;
				int che15 = 15;
				
				// 宣告資料緩衝讀取區
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf8"));
				// 宣告資料寫出區塊
				writer = new PrintWriter(socket.getOutputStream(),true);
								
				while(!isTerminated)
				{
					Thread.sleep(1000);	// 設定每一秒輪詢連線一次
					
					if (reader.ready())	// 如果連線有資料
					{						
						String message = reader.readLine();
						String word = "";
						String instruct = "";	
						String code = "";		
						System.out.println("the message is "+message);
						
						// 以下for迴圈是將傳入server的字串做一解析，將指令跟內容做一分類區隔
						for (int x = 0; x<message.length() ; x++)
						{
							if (!message.substring(x,x+1).equals(" "))
							{
								word+=message.substring(x,x+1);
							}							
							else
							{
								instruct = word;
								code = message.substring(x+1,message.length());
								
								System.out.println("the instruct_"+instruct);
								System.out.println("the code"+code);
								break;
							}
						}
						
						
						if (instruct.equals("CheckIn"))	
						{
							
							System.out.println("CheckIn...1");
							cs.connect();
							String[] data = code.split(String.valueOf((char)(che9)));
							String[] LoginData =cs.checkLoginData(data[0],data[1]).split(" ");
							if ( LoginData[0].equals("true")   )
							{
								String userInfo=cs.IdInfo(LoginData[1]);
								sendToTarget(String.valueOf((char)(che9))+"Check"+String.valueOf((char)(che6))+"Pass"+String.valueOf((char)(che6))+LoginData[1]+String.valueOf((char)(che6))+userInfo+String.valueOf((char)(che6)));
								
								//sendToTarget(String.valueOf((char)(che9))+"Check"+String.valueOf((char)(che6))+"Pass"+String.valueOf((char)(che6))+LoginData[1]+String.valueOf((char)(che6))  );
								System.out.println("CheckIn...2");
							}
							else
							{
								sendToTarget(String.valueOf((char)(che9))+"Check"+String.valueOf((char)(che6))+"Fail"+String.valueOf((char)(che6)));
							}

							isTerminated();
							
						}
						
						if (instruct.equals("Identify"))	
						{
							
							String[] data = code.split(String.valueOf((char)(che9))); // uid  URL
							cs.connect();
																		
							if ( cs.getIdentify( data[1] ).equals("success") )
							{
								sendToTarget( String.valueOf((char)(che9)) +"Identify_start" + String.valueOf((char)(che7))   );
								if(cs.SendLock)
								{
									for (int i = 0; i< cs.SendPackage.size(); i++)
									{
										Thread.sleep(100);
										sendToTarget(String.valueOf((char)(che9))+"Identify"+String.valueOf((char)(che7))+cs.SendPackage.get(i).toString()+String.valueOf((char)(che7)));
			
										System.out.println(cs.SendPackage.get(i).toString());
									}	
								}
								Thread.sleep(100);
								sendToTarget( String.valueOf((char)(che9)) +"Identify_close" + String.valueOf((char)(che7))   );
								
							}
							else
							{
								sendToTarget( String.valueOf((char)(che9)) +"Identify_fail" + String.valueOf((char)(che7))+cs.SendPackage.get(0).toString() + String.valueOf((char)(che7)) );
							}

							isTerminated();
							
						}
						
						
						if (instruct.equals("CreatePerson"))	
						{
							String[] data = code.split(String.valueOf((char)(che9))); // String URL,String person_name,String person_fb,String person_ig ,String person_info ,String uid
							cs.connect();
							cs.CreatePerson(data[0],data[1],data[2],data[3],data[4],data[5]);
							sendToTarget( String.valueOf((char)(che9)) +"CreatePerson_success" + String.valueOf((char)(che7))   );
						}
						
						
						
						
						
						
						
						
						
					}
				}
			}
			catch(Exception e)
			{
				
			}
		}
		
		// 資料傳送的方法
		synchronized void sendToTarget(String msg)
		{
			PrintWriter writer = null;			
			try
			{        
				writer = new PrintWriter(socket.getOutputStream(),true);
			}
			catch(Exception ie){}
			if(writer != null) 
			{
				writer.println(msg);
			}
		}
	}
}
