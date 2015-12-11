package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Member {
	private Mes mes;
	private MulticastSocket mcs;
	
	Member() {
		try {
			mes = new Mes();
			mcs = new MulticastSocket(mes.port);
			mcs.joinGroup(mes.group);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MulticastSocket getMcs() {
		return mcs;
	}

	public void sendData(String data){
		System.out.println("sendData:" + data);
		if(data.length()>0){
			byte[] bytes = data.getBytes();
			DatagramPacket dp = new DatagramPacket(bytes,bytes.length,mes.group,mes.port);
			try {
				mcs.send(dp);
			} catch (IOException e) {
				System.out.println("sendData:"+data+" Failed");
			}
		}
		else{
			System.out.println("sendData:Data length is 0");
			return;
		}
	}
	public void close(){
		try {
			mcs.leaveGroup(mes.group);
			mcs.close();
		} catch (IOException e) {
			System.out.println("Member close");
		}
	}
}
