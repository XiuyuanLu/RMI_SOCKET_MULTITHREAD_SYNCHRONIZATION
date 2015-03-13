package version1;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class MainClass {
	public static void main(String args[]) throws UnknownHostException, IOException, AlreadyBoundException, InterruptedException, NotBoundException{
		RmiServer1.startRegistry();
		RmiServer1.registerObject(RmiInterface.class.getSimpleName(),new RmiInterfaceImpl1());
		//Thread.sleep(1000);
		RmiServer2.startRegistry();
		RmiServer2.registerObject(RmiInterface.class.getSimpleName(),new RmiInterfaceImpl2());
		//Thread.sleep(1000);
		new Dispatcher();
	}
}
