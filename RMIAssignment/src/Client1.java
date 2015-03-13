package version1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client1 {
	private Socket s;
	private PrintStream ps = null;
    private BufferedReader br = null;
    private BufferedReader br2 = null;
	public Client1() throws UnknownHostException, IOException{
		try{
			s = new Socket("localhost",10001);
			System.out.println("Client 1 connected!");
			ps= new PrintStream(s.getOutputStream());//流与套接字之间建立联系
			br = new BufferedReader(new InputStreamReader(System.in));// 从控制台输入要发送到服务器端的消息
			br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));//从服务器端接收到的消息
			//new ReadServerMessage(br2);// 从服务器读取消息 
			while (true) {                                      //一直循环，一直到从客户端输入quit为止
	            String string = br.readLine();
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	            ps.println(string);                           // 向服务器中发送消息
	            ps.flush();
	            if (string.trim().equals("quit"))// 退出
	            {
	                break;
	            }
	            // 从服务器端得到消息
	            String serverString = br2.readLine();
	            String line[] = serverString.split(" ");
	            if(line[0].equals("Write"))
	            System.out.println("Client 1 "+string);
	            else
	            System.out.println("Client 1 "+string+": "+serverString);
	            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	        }
		}catch(IOException ie){
			ie.printStackTrace();
		}finally{
			ps.close();
			br.close();
			br2.close();
		}
}

	public static void main(String args[]) throws UnknownHostException, IOException{
		new Client1();
	}
}
