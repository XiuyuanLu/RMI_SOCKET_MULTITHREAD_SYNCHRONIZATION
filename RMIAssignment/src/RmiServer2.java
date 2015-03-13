package version1;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class RmiServer2 {
	private static final int PORTt = 1100;
	private static Registry registry;

	public static void startRegistry() 
			throws RemoteException{
		registry = java.rmi.registry.LocateRegistry.createRegistry(PORTt);
	}
	
	public static void registerObject(String name, Remote remoteObj) 
			throws RemoteException, AlreadyBoundException{
		registry.bind(name, remoteObj);
		//System.out.println("Registered: "+name+" -> "+remoteObj.getClass().getName()+"["+remoteObj+"]");
		
	}
	public static void main(String args[]) 
			throws Exception{
		
		startRegistry();
		registerObject(RmiInterface.class.getSimpleName(),new RmiInterfaceImpl2());
		Thread.sleep(5*60*1000);
		
	}
}
