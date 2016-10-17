package network2;

public class Message {
	
	private String message;
	private Node destination;
	private Node source;
	private int numHops;
	
	
	public Message(String message,Node destination,Node source){
		this.message=message;
		numHops=0;
		this.destination=destination;
		this.source=source;
		
	}


	public Node getDestination() {
		return destination;
	}


	public void setDestination(Node destination) {
		this.destination = destination;
	}


	public Node getSource() {
		return source;
	}


	public void setSource(Node source) {
		this.source = source;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getNumHops() {
		return numHops;
	}


	public void IncNumHops() {
		this.numHops++;
	}
	
}
