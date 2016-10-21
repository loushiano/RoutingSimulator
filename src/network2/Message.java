package network2;

public class Message {
	
	private String message;
	private Node destination;
	private Node source;
	private int numHops;
	private int numPacks;
	
	
	public Message(String message,Node source,Node destination){
		this.message=message;
		numHops=0;
		numPacks=0;
		this.destination=destination;
		this.source=source;
		
	}


	public int getNumPacks() {
		return numPacks;
	}


	public void incNumPacks() {
		this.numPacks++;
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


	public void incNumHops() {
		this.numHops++;
	}
	
}
