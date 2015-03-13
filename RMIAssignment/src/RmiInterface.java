package version1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote{
	public int read(int key) throws RemoteException;
	public void write(int key,int value) throws RemoteException;
}
