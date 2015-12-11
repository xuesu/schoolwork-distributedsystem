import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MeetingServer {
	public static void main(String args[]){
		String registryURL;
		int portNum = 1099;
		String host = "127.0.0.1";
		try {
			MeetingImpl exportedObj = new MeetingImpl();
			startRegistry(portNum);
			registryURL = "rmi://"+ host +":" + portNum + "/meeting";
			Naming.rebind(registryURL, exportedObj);
			System.out.println("Server ready");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method starts a RMI registry on the local host, if it
	// does not already exist at the specified port number.
	private static void startRegistry(int RMIPortNum) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
			registry.list();
			// The above call will throw an exception
			// if the registry does not already exist
		} catch (RemoteException ex) {
			// No valid registry at that port.
			System.out.println("RMI registry cannot be located at port " + RMIPortNum);
			LocateRegistry.createRegistry(RMIPortNum);
			System.out.println("RMI registry created at port " + RMIPortNum);
		}
	} // end startRegistry

}