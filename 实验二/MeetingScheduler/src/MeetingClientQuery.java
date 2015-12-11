import java.rmi.Naming;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class MeetingClientQuery {
	public static void main(String args[]) {
		try {
			String host = "127.0.0.1";
			int portNum = 1099;
			// Code for obtaining hostName and RMI Registry port
			// to be supplied.

			// Look up the remote object and cast its reference
			// to the remote interface class -- replace "localhost"
			// with the appropriate host name of the remote object.
			String registryURL = "rmi://" + host + ":" + portNum + "/meeting";
			MeetingInterface h = (MeetingInterface) Naming.lookup(registryURL);
			boolean stop = false;
			String name,password,userb,desc;
			Date start,end;
			do{
				int op = 5;
				switch(op){
				case 1:
					System.out.println("Add user:");
					name = input("name");
					password = input("password");
					if(h.addUser(name, password)){
						System.out.println("Successfully added.");
					}
					else{
						System.out.println("Add user failed!!!");
					}
					break;
				case 2:
					System.out.println("Add meeting:");
					name = input("name");
					password = input("password");
					userb = input("another user's name");
					desc = input("description");
					start = inputDate("start");
					end = inputDate("end");
					if(h.addMeeting(name, password,userb,desc,start,end)){
						System.out.println("Successfully added.");
					}
					else{
						System.out.println("Add meeting failed!!!");
					}
					break;
				case 3:
					System.out.println("Delete Meeting:");
					name = input("name");
					password = input("password");
					int id = inputInt("Id");
					if(h.delMeeting(name, password,id)){
						System.out.println("Successfully deleted.");
					}
					else{
						System.out.println("Delete Meeting failed!!!");
					}
					break;
				case 4:
					System.out.println("Clear Meeting:");
					name = input("name");
					password = input("password");
					if(h.clearMeeting(name, password)){
						System.out.println("Successfully cleared.");
					}
					else{
						System.out.println("Clear Meeting failed!!!");
					}
					break;
				case 5:
					System.out.println("List meeting:");
					name = input("name");
					start = inputDate("start");
					end = inputDate("end");
					ArrayList<MeetingMes> ls = h.listMeeting(name, start, end);
					for(MeetingMes meet:ls){
						System.out.println(meet);
					}
					break;
				default:
					System.out.println("Error Instrcution No!!!");
				}
				String stopstr = input("Stop?(y/n)");
				if(stopstr.equals("y") || stopstr.equals("Y")){
					stop = true;
				}
			}while(!stop);
			
		} // end try
		catch (Exception ex) {
			ex.printStackTrace();
		} // end catch
	} // end main
		// Definition for other methods of the class, if any.

	@SuppressWarnings("unused")
	private static int getInstruction() {
		System.out.println("Please input the instruction number:");
		System.out.println("1.add user");
		System.out.println("2.add meeting");
		System.out.println("3.delete meeting");
		System.out.println("4.clear meeting");
		System.out.println("5.list meeting");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		return i;
	}
	
	private final static Date inputDate(String opt){
		Date date = null;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while (date == null) {
			System.out.println("Please input the "+ opt + "Time:(Format:yyyy/MM/dd HH:mm)");
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String datestr = scan.nextLine();
			try {
				date = fmt.parse(datestr);
			} catch (ParseException e) {
				System.out.println("Error Format!!! Please re-input.");
				date = null;
			}
		}
		return date;
	}
	private final static String input(String opt){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input "+opt);
		String str = scan.nextLine();
		return str;
	}
	private final static int inputInt(String opt){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input "+opt);
		int i = scan.nextInt();
		return i;
	}
}
