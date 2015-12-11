import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class HeartBeats implements Runnable{
	private Server server;
	public HeartBeats(Server server){
		this.server = server;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			for(Socket socClient:server.sockStore.values()){
				try {
					socClient.sendUrgentData(0xff);
				} catch (SocketException  e) {
					e.printStackTrace();
					String ipaddr = socClient.getRemoteSocketAddress().toString();
					System.out.println("Client:" + ipaddr + " Has left!");
					server.closeSoc(ipaddr);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
