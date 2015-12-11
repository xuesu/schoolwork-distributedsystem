import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Client {
	private JTextField textField_Ip;
	private JTextField textField_Port;
	private JTextField textField_Name;
	private JTextPane textPane;
	private JTextArea textArea;
	private JLabel lblIfConnected;
	private Socket soc;
	private Thread recvTh;
	public Client(JTextField textField_Ip, JTextField textField_Port, JTextPane textPane, 
			JTextArea textArea,JTextField textField_Name,JLabel lblIfConnected) {
		super();
		this.textField_Ip = textField_Ip;
		this.textField_Port = textField_Port;
		this.textPane = textPane;
		this.textArea = textArea;
		this.textField_Name = textField_Name;
		this.lblIfConnected = lblIfConnected;
	}
	public void connect(){
		try {
			soc = new Socket(InetAddress.getByName(textField_Ip.getText()),Integer.parseInt(textField_Port.getText()));
			recvTh = new Thread(
				new Runnable(){
					public void run(){
						recv();
					}
				}
			);
			recvTh.start();
			lblIfConnected.setText("Connected");
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean ifConnected(){
		if(soc == null){
			lblIfConnected.setText("Disconnected");
		}
		else{
			lblIfConnected.setText("Connected");
		}
		return soc != null;
	}
	public void send(){
		if(soc == null){
			textPane.setText(textPane.getText() + "\nERROR,HAVEN'T CONNECTED YET!\n");
		}
		try {
			OutputStreamWriter out = new OutputStreamWriter(soc.getOutputStream());
			String str = (new Date()).toString() + " From Client:" + textField_Name.getText() + "\n" + textArea.getText() + "\n";
			textPane.setText(textPane.getText() + str);
			out.write(str);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			if(soc!=null){
				if(!soc.isClosed()){
					soc.getOutputStream().flush();
					soc.close();
				}
				soc = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lblIfConnected.setText("Disconnected");
		}
		if(recvTh != null)recvTh.interrupt();;
	}
	public void recv(){
		try {
			BufferedReader scanner = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			while(!Thread.interrupted()){
				String str = scanner.readLine();
				if(str == null) throw new IOException();
				textPane.setText(textPane.getText() + str + "\n");
			}
		} catch (IOException e) {
			System.out.println("Server has disconnected!");
			lblIfConnected.setText("Disconnected");
			e.printStackTrace();
		}
		
	}
}
