package version1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Dispatcher {
	private ServerSocket ss;
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 1099;
	private static final int PORTt = 1100;
	private int count = 0;
	public Dispatcher() throws IOException, NotBoundException{
        Registry registry1 = LocateRegistry.getRegistry(HOST,PORT);
		RmiInterface rmi1 = (RmiInterface) registry1.lookup(RmiInterface.class.getSimpleName());
		Registry registry2 = LocateRegistry.getRegistry(HOST,PORTt);
		RmiInterface rmi2 = (RmiInterface) registry2.lookup(RmiInterface.class.getSimpleName());
		ss = new ServerSocket(10001);
		while(true){
			try{
				count++;
				Socket s = ss.accept();
				new DispatcherThread(count,s,rmi1,rmi2);
			}catch(IOException ie){
				ie.printStackTrace();
			}
		}
	}
	public static void main(String args[]) throws IOException, NotBoundException{
		new Dispatcher();
	}
}
