package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import javax.swing.JTextArea;

class ReceivedThread implements Runnable{
	private MulticastSocket mcs;
	private EventSourceObject object;
	ReceivedThread(MulticastSocket mcs,JTextArea textPane){
		this.mcs = mcs;
		this.object = new EventSourceObject();
		object.addReceivedEventListener(new ReceivedEventListener(textPane));
	}
	@Override
	public void run() {
		try {
			byte[] buffer = new byte[1024];
			System.out.println("ReceiveThread:Prepared to receive:");
			while(true){
				DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
				mcs.receive(dp);
				String s = new String(dp.getData(),0,dp.getLength());
				System.out.println("ReceiveThread:Received:"+s);
				object.setReceivedData(s);
				object.clearReceivedData();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
