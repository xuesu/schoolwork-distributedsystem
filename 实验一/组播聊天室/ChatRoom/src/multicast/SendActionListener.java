package multicast;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JTextArea;

public class SendActionListener implements ActionListener{

	private JTextArea textArea;
	private Member mem;
	
	SendActionListener(Member mem,JTextArea textArea){
		this.mem = mem;
		this.textArea = textArea;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("btnSend:actionPerformed");
		String data = new Date() + "\n";
		try {
			data += InetAddress.getLocalHost() + "\n";
		} catch (UnknownHostException e1) {
			System.out.println("send_btn:actionPerformed:"
					+ "InetAddress.getLocalHost,UnknownHostException");
		}
		data += textArea.getText();
		data = data.trim();
		if(!data.endsWith("\n")){
			data += "\n";
		}
		mem.sendData(data);
	}

}
