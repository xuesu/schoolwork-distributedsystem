import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */

/**
 * @author Iris
 *
 */
public interface MeetingInterface extends Remote {
	boolean addUser(String name,String password)throws RemoteException;
	boolean addMeeting(String usera,String password,String userb,String desc,Date start,Date end)throws RemoteException;
	boolean delMeeting(String user,String password,int id)throws RemoteException;
	boolean clearMeeting(String user,String password)throws RemoteException;
	ArrayList<MeetingMes> listMeeting(String user)throws RemoteException;
	ArrayList<MeetingMes> listMeeting(String user,Date start,Date end)throws RemoteException;
}
