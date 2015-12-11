import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Server {
	public static int port = 9999;
	private ServerSocket soc;
	JList<String> list;
	JTextPane textPane;
	JTextArea textArea;
	DefaultListModel<String> dlm;
	public Map<String,String> strStore;
	public Map<String,Socket> sockStore;
	public Map<String,RecTh> recStore;
	private Thread listenReqTh;
	private ExecutorService recPool;
	
	public Server(JList<String> list, JTextPane textPane, JTextArea textArea, DefaultListModel<String> dlm) {
		super();
		this.list = list;
		this.textPane = textPane;
		this.textArea = textArea;
		this.dlm = dlm;
		strStore = new HashMap<String,String>();
		sockStore = new HashMap<String,Socket>();
		recStore = new HashMap<String,RecTh>();
	}
	
	public Map<String, String> getStrStore() {
		return strStore;
	}

	private void ListenRequest(){
		while(!Thread.interrupted()){
			try {
				Socket socClient = soc.accept();
				String ipaddr = socClient.getRemoteSocketAddress().toString();
				System.out.println("Connected with ipaddr:" + ipaddr);
				synchronized(this){
					dlm.addElement(ipaddr);
					list.setModel(dlm);;
					strStore.put(ipaddr, "");
				}
				sockStore.put(ipaddr,socClient);
				
				RecTh recTh = new RecTh(this,socClient);
				recStore.put(ipaddr, recTh);
				recPool.execute(recTh);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		try {
			recPool = Executors.newFixedThreadPool(5);
			soc = new ServerSocket(port,5);
			listenReqTh = new Thread(	
				new Runnable(){
					public void run(){
						ListenRequest();
					}
				}
			);
			listenReqTh.start();
			//heartBeatsTh = new Thread(
			//		new HeartBeats(this)
			//		);
			//heartBeatsTh.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send(){
		try{
			String ipaddr = list.getSelectedValue();
			Socket socClient = sockStore.get(ipaddr);
			try {
				OutputStreamWriter out = new OutputStreamWriter(socClient.getOutputStream());
				String str = new Date().toString() + " From Server:\n" + textArea.getText() + "\n"; 
				out.write(str);
				strStore.put(ipaddr,strStore.get(ipaddr) + str);
				textPane.setText(strStore.get(ipaddr));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				closeSoc(ipaddr);
			}
		}catch(java.lang.NullPointerException e){
			textPane.setText("Haven't chose Client\n");
		}
	}
	
	public void close(){
		for(String ipaddr:sockStore.keySet()){
			closeSoc(ipaddr);
		}
		try {
			if(soc != null){
				soc.close();
				soc = null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listenReqTh.interrupt();
		recPool.shutdown();
	}
	public void closeSoc(String ipaddr){
		try {
			if(sockStore.containsKey(ipaddr)){
				sockStore.get(ipaddr).close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(recStore.containsKey(ipaddr)){
			recStore.get(ipaddr).stop();
		}
		
		if(dlm.contains(ipaddr)){
			dlm.removeElement(ipaddr);
			list.setModel(dlm);
		}
		
		recStore.remove(ipaddr);
		sockStore.remove(ipaddr);
		strStore.remove(ipaddr);
	}
	public void closeSoc(String ipaddr,boolean fl){
		try {
			if(sockStore.containsKey(ipaddr)){
				sockStore.get(ipaddr).close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fl && recStore.containsKey(ipaddr)){
			recStore.get(ipaddr).stop();
		}
		
		if(dlm.contains(ipaddr)){
			dlm.removeElement(ipaddr);
			list.setModel(dlm);
		}
		
		recStore.remove(ipaddr);
		sockStore.remove(ipaddr);
		strStore.remove(ipaddr);
	}
}


