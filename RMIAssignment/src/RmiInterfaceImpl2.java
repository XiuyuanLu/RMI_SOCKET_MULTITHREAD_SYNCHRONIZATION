package version1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiInterfaceImpl2 extends UnicastRemoteObject implements RmiInterface{
	private dataRec datarec2[] = new dataRec[20];
	protected RmiInterfaceImpl2() throws RemoteException {
		super();
		for(int i=0;i<20;i++)
		{
			datarec2[i] = new dataRec(i,i*10+i);
		}
		// TODO A uto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int read(int key) throws RemoteException {
		// TODO Auto-generated method stub
		return this.datarec2[key].op_read();
	}

	@Override
	public void write(int key, int value) throws RemoteException {
		// TODO Auto-generated method stub
		this.datarec2[key].op_write(value);
	}

}
