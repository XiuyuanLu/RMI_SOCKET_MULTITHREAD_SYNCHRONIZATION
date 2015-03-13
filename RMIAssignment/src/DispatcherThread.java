package version1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class DispatcherThread extends Thread{
	private Socket socket;
	private BufferedReader bReader = null;// 读取信息
    private PrintStream ps = null;
    private RmiInterface rmi1;
    private RmiInterface rmi2;
	public DispatcherThread(int id, Socket s, RmiInterface rmi1, RmiInterface rmi2){
		this.socket = s;
		this.rmi1 = rmi1;
		this.rmi2 = rmi2;
		start();
	}

	@Override
	public void run() {
		 try {
	    		bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));// 读信息
	            ps = new PrintStream(socket.getOutputStream());// 向客户端输出信息
	    		while(true){
	    			String line = bReader.readLine();
	    			String subLine[] = line.split(" ");
	    			
	    			int key = Integer.parseInt(subLine[1]);
	    			
	    			if(key == -1)break;
	    			
	    			if(subLine[0].equals("read"))
	    			{
		    			if(key>=0 && key<=10){
		    				ps.println(rmi1.read(key));
		    			}
		    			else{ 
		    				ps.println(rmi2.read(key));
		    			}
	    			}else if(subLine[0].equals("write"))
	    			{
	    				int value = Integer.parseInt(subLine[2]);
	    				System.out.println(value);
	    				if(key>=0 && key<=10){
	    					rmi1.write(key,value);
		    			}
		    			else{ 
		    				rmi2.write(key,value);
		    			}
	    				ps.println("Write index "+key+" "+value);
	    			}
	    			ps.flush();
	    		}
	    		socket.close();
		 }
		 catch (IOException ie){
		 	ie.printStackTrace();
		 }
		 finally {
			 try {
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         ps.close();
	     }
	}
	
}
