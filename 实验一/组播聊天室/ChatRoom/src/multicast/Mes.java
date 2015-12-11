package multicast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Mes {
	String host = "224.0.0.1";
	int port = 8888;
	InetAddress group;
	Mes() throws UnknownHostException{
		group = InetAddress.getByName(host);	
	}
	
}
