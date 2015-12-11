package multicast;

import java.util.EventListener;

import javax.swing.JTextArea;

public class ReceivedEventListener implements EventListener {
	private JTextArea textPane;
	ReceivedEventListener(JTextArea textPane){
		this.textPane = textPane;
	}
	public void fireReceivedEvent(ReceivedEvent e){
		System.out.println("fireReceivedEvent:");
		EventSourceObject object = (EventSourceObject)(e.getSource());
		String receivedData = object.getReceivedData();
		System.out.println("fireReceivedEvent:receivedData:"+textPane.getText() + receivedData);
		textPane.setText(textPane.getText() + receivedData);
		System.out.println("fireReceivedEvent:end");
	}
}
