package ElectApp;

import java.util.ArrayList;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;

public class ElectServer {
	
	public static void main(String[] args) {
		try{
			ORB orb = ORB.init(args,null);
			POA rootPoa = (POA)orb.resolve_initial_references("RootPOA");
			rootPoa.the_POAManager().activate();
			
			Elected alist[] = new Elected[4];
			alist[0] = new Elected("A",0);
			alist[1] = new Elected("B",0);
			alist[2] = new Elected("C",0);
			alist[3] = new Elected("D",0);
			ElectedListHolder list = new ElectedListHolder();
			list.value = alist;
			ElectImpl impl = new ElectImpl();
			impl.setAlist(alist);
			impl.setOrb(orb);
			
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(impl);
			Elect eref = ElectHelper.narrow(ref);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "ElectServer";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, eref);
			
			System.out.println("Server ready and waiting...");
			orb.run();
		}catch  (Exception e){
			e.printStackTrace();
			
		}
	}

}
