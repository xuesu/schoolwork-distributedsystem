package multicast;

import java.util.EventObject;

class ReceivedEvent extends EventObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object source;
	public ReceivedEvent(Object source) {
		super(source);
		this.source = source;
	}
	public Object getSource() {
		return source;
	}
	public void setSource(Object source) {
		this.source = source;
	}	
}