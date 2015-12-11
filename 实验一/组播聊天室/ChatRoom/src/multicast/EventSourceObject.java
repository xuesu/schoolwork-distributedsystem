package multicast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class EventSourceObject {
	private String receivedData;
	private Set<ReceivedEventListener> listeners;
	EventSourceObject(){
		listeners = new HashSet<ReceivedEventListener>();
		this.receivedData = "";
	}
	public String getReceivedData() {
		return receivedData;
	}
	public void setReceivedData(String receivedData) {
		if(this.receivedData == ""){
			this.receivedData = receivedData;
			notifies();
		}
	}
	public void addReceivedEventListener(ReceivedEventListener listener){
		this.listeners.add(listener);
	}
	protected void notifies(){
		System.out.println("EventSourceObject:notifies:"+receivedData);
		ReceivedEventListener listener = null;
		Iterator<ReceivedEventListener> ite = this.listeners.iterator();
		while(ite.hasNext()){
			listener = ite.next();
			listener.fireReceivedEvent(new ReceivedEvent(this));
			System.out.println("EventSourceObject:notifies:fireReceiveEventCompleted"+listener);
		}
		System.out.println("EventSourceObject:notifies:end");
	}
	public void clearReceivedData() {
		receivedData = "";
	}
}
