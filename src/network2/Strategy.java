package network2;
/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 1.0
 * @since 20/10/2016
 */
public class Strategy {
	protected Message message;
	protected Node source;
	protected Node destination;
	

	/*
	 * Initializes the message,source and destination fields
	 * @param message - a message to be transferred through the network
	 * @param source - a source node from which the message has to be transferred
	 * @param destination - a destination node to which the message has to be transferred
	 */
	public Strategy(Message message,Node source,Node destination){
		this.message=message;
		this.source=source;
		this.destination=destination;
	}
	/*
	 * gets the message to be transferred
	 * @return message - a message
	 */
	public Message getMessage() {
		return message;
	}
	/*
	 * sets the message to be transferred
	 * @param message - a message
	 */
	
	public void setMessage(Message message) {
		this.message = message;
	}
	/*
	 * gets the node source of the method
	 * @return source - a Source node
	 */
	public Node getSource() {
		return source;
	}
	/*
	 * sets the node source of the method
	 * @param source - a Source node
	 */
	public void setSource(Node source) {
		this.source = source;
	}
	/*
	 * gets the node destination of the method
	 * @return destination - a destination node
	 */
	public Node getDestination() {
		return destination;
	}
	/*
	 * sets the node destination of the method
	 * @param destination - a destination node
	 */
	public void setDestination(Node destination) {
		this.destination = destination;
	}
	public void printResult(){
		System.out.println("the message : "+message.getMessage()+" was transferred successfuly from "+source.getName()+" to "+destination.getName());
	}

	

}
