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
			ps= new PrintStream(s.getOutputStream());//�����׽���֮�佨����ϵ
			br = new BufferedReader(new InputStreamReader(System.in));// �ӿ���̨����Ҫ���͵��������˵���Ϣ
			br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));//�ӷ������˽��յ�����Ϣ
			//new ReadServerMessage(br2);// �ӷ�������ȡ��Ϣ 
			while (true) {                                      //һֱѭ����һֱ���ӿͻ�������quitΪֹ
	            String string = br.readLine();
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	            System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
	            ps.println(string);                           // ��������з�����Ϣ
	            ps.flush();
	            if (string.trim().equals("quit"))// �˳�
	            {
	                break;
	            }
	            // �ӷ������˵õ���Ϣ
	            String serverString = br2.readLine();
	            String line[] = serverString.split(" ");
	            if(line[0].equals("Write"))
	            System.out.println("Client 1 "+string);
	            else
	            System.out.println("Client 1 "+string+": "+serverString);
	            System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
