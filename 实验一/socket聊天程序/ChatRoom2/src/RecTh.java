import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecTh implements Runnable{
		private Server server;
		private Socket socClient;
		private String ipaddr;
		private boolean stop;
		RecTh(Server server,Socket socClient){
			this.server = server;
			this.socClient = socClient;
			ipaddr = socClient.getRemoteSocketAddress().toString();
			stop = false;
		}

		@Override
		public void run() {
			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(socClient.getInputStream()));
				while(!stop){
					String str = in.readLine();
					if(str == null) throw new IOException();
					str += "\n";
					server.strStore.put(ipaddr,server.strStore.get(ipaddr) + str);
					if(!server.list.getSelectedValue().isEmpty() && server.list.getSelectedValue().equals(ipaddr)){
						server.textPane.setText(server.strStore.get(ipaddr));
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Client "+ ipaddr + " HAS DISCONNECTED\n");
				server.closeSoc(ipaddr,false);
			}
		}
		public void stop(){
			stop = true;
		}
	}