import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class MeetingImpl implements MeetingInterface,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1477493234051009035L;
	ArrayList<UserMes> users;
	ArrayList<MeetingMes> meetings;
	
	static int futureId = 1;
	
	public MeetingImpl() {
		super();
		users = new ArrayList<UserMes>();
		meetings = new ArrayList<MeetingMes>();
	}

	public boolean validatePass(String name,String password){
		for(UserMes user:users){
			if(user.getName().equals(name) && user.getPassword().equals(password))return true;
		}
		return false;
	}

	private boolean validateMeet(MeetingMes newMeet) {
		for(MeetingMes meet:meetings){
			if(meet.isCollsion(newMeet))return false;
		}
		return true;
	}
	
	private MeetingMes getMeetingbyId(int id){
		for(MeetingMes meet:meetings){
			if(meet.getId() == id)return meet;
		}
		return null;
	}
	@Override
	public boolean addUser(String name, String password) throws RemoteException{
		UserMes newUser = new UserMes(name,password);
		for(UserMes user:users){
			if(newUser.getName().equals(user.getName()))return false;
		}
		users.add(newUser);
		return true;
	}

	@Override
	public boolean addMeeting(String usera, String password, String userb, String desc, Date start, Date end) throws RemoteException{
		if(!validatePass(usera,password))return false;
		MeetingMes newMeet = new MeetingMes(futureId,usera,userb,desc,start,end);
		if(validateMeet(newMeet)){
			meetings.add(newMeet);
			return true;
		}
		else{
			return false;
		}
	}


	@Override
	public boolean delMeeting(String user, String password, int id) throws RemoteException{
		if(!validatePass(user,password))return false;
		MeetingMes meet = getMeetingbyId(id);
		if(meet == null)return false;
		if(!meet.getUsera().equals(user))return false;
		meetings.remove(meet);
		return true;
	}

	@Override
	public boolean clearMeeting(String user, String password) throws RemoteException{
		if(!validatePass(user,password))return false;
		ArrayList<MeetingMes> rm = new ArrayList<MeetingMes>();
		for(MeetingMes meet:meetings){
			if(meet.getUsera().equals(user)){
				rm.add(meet);
			}
		}
		for(MeetingMes meet:rm){
			meetings.remove(meet);
		}
		return true;
	}

	@Override
	public ArrayList<MeetingMes> listMeeting(String user) throws RemoteException{
		ArrayList<MeetingMes> ans = new ArrayList<MeetingMes>();
		for(MeetingMes meet:meetings){
			if(meet.getUsera().equals(user) || meet.getUserb().equals(user)){
				ans.add(meet);
			}
		}
		return ans;
	}

	@Override
	public ArrayList<MeetingMes> listMeeting(String user, Date start, Date end) throws RemoteException{
		ArrayList<MeetingMes> ans = new ArrayList<MeetingMes>();
		for(MeetingMes meet:meetings){
			if((meet.getUsera().equals(user) || meet.getUserb().equals(user)) && meet.isInTime(start,end)){
				ans.add(meet);
			}
		}
		return ans;
	}

}
