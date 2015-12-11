import java.io.Serializable;
import java.util.Date;

/**
 * 
 */

/**
 * @author Iris
 *
 */
public class MeetingMes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5881329457229605032L;
	int id;
	String usera;
	String userb;
	String desc;
	public MeetingMes(int id, String usera, String userb, String desc, Date start, Date end) {
		super();
		this.id = id;
		this.usera = usera;
		this.userb = userb;
		this.desc = desc;
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString() {
		return "MeetingMes [id=" + id + ", usera=" + usera + ", userb=" + userb + ", desc=" + desc + ", start=" + start
				+ ", end=" + end + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsera() {
		return usera;
	}
	public void setUsera(String usera) {
		this.usera = usera;
	}
	public String getUserb() {
		return userb;
	}
	public void setUserb(String userb) {
		this.userb = userb;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	Date start;
	Date end;
	public boolean isCollsion(MeetingMes newMeet) {
		if(isUserCollison(newMeet))return true;
		if(isTimeCollison(newMeet))return true;
		return false;
	}
	public boolean isUserCollison(MeetingMes newMeet){
		if((getUsera().equals(newMeet.getUserb()) ||getUsera().equals(newMeet.getUsera()) )
				|| (getUserb().equals(newMeet.getUserb()) ||getUserb().equals(newMeet.getUsera()) ))return true;
		return false;
	}
	public boolean isTimeCollison(MeetingMes newMeet){
		if((newMeet.getStart().compareTo(getEnd()) <= 0 && newMeet.getStart().compareTo(getStart()) >= 0)
				||(newMeet.getStart().compareTo(getStart()) <= 0 && newMeet.getEnd().compareTo(getStart()) >= 0))return true;
		return false;
	}
	public boolean isInTime(Date s,Date t){
		return s.compareTo(getStart()) <= 0 && t.compareTo(getEnd())>=0;
	}
	
}
