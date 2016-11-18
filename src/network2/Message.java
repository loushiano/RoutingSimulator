package network2;

public class Message {
	
	private String message;
	private Node destination;
	private Node source;
	private Node previousNode;
	private int numHops;
	private int numPacks;
	
	
	public Message(String message,Node source,Node destination){
		this.message=message;
		numHops=0;
		numPacks=0;
		this.destination=destination;
		this.source=source;
		previousNode=null;
	}

	/*
	 * gets the number of packs 
	 * Returns the number of packs  
	 */
	public int getNumPacks() {
		return numPacks;
	}

	/*
	* incNumPacks() method increments the number of packs 
	*/
	public void incNumPacks() {
		this.numPacks++;
	}

	/*
	 * gets the destination of message 
	 * Returns the message 
	 */
	public Node getDestination() {
		return destination;
	}

	/*
	 * sets the destination node  of the message 
	 * @param destination is where the message is going to .
	 */
	public void setDestination(Node destination) {
		this.destination = destination;
	}


	/*
	 * gets the source of the message 
	 * Returns the the source  
	 */
	public Node getSource() {
		return source;
	}


	/*
	 * sets the source node of the message 
	 * @param source is the node where the message sends from. 
	 */
	public void setSource(Node source) {
		this.source = source;
	}


	/*
	 * gets the message 
	 * Returns the message 
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * @param message the message to be sent
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * gets the number of hops 
	 * Returns the number of hops  
	 */
	public int getNumHops() {
		return numHops;
	}

	/*
	 * incNumHops() increments the number of Hops 
	 */
	public void incNumHops() {
		this.numHops++;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}
	
}
