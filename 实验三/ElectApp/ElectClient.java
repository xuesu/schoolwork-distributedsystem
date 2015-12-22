package ElectApp;

import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ElectClient {
	
	public static void main(String[] args) {
		ORB orb = ORB.init(args, null);
		Elect impl = null;
		try {
			org.omg.CORBA.Object robj = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(robj);
			String name = "ElectServer";
			impl = ElectHelper.narrow(ncRef.resolve_str(name));
			run(impl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(impl!=null)impl.shutdown();
		}
	}
	public static void run(Elect impl) throws noElected{
		int op = printCmd();
		while(op!=0){
			switch(op){
			case 1:
				ElectedListHolder list = new ElectedListHolder();
				impl.getList(list);
				print(list);
				break;
			case 2:
				System.out.println("Please input the elected's name");
				String name = (new Scanner(System.in)).next();
				impl.castVote(name);
				ElectedListHolder list2 = new ElectedListHolder();
				impl.getList(list2);
				print(list2);
				break;
			}
			op = printCmd();
		}
	}
	
	private static void print(ElectedListHolder list) {
		System.out.println("Name,Poll number");
		for(Elected e:list.value){
			System.out.println(e.name + "," + e.poll);
		}
	}
	
	public static int printCmd() throws noElected{
		System.out.println("Obtained a handle on server object\n0 exit\n1 getList\n2 castVote");
		@SuppressWarnings("resource")
		int op = (new Scanner(System.in)).nextInt();
		if(op > 2 || op < 0)throw new noElected();
		return op;
	}
}
