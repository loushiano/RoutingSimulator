package network2;

import java.util.ArrayList;

/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 1.0
 * @since 20/10/2016
 */
public class Strategy {
	protected ArrayList<Message> messages;
	
	

	/*
	 * Initializes the message,source and destination fields
	 * @param message - a message to be transferred through the network
	 * @param source - a source node from which the message has to be transferred
	 * @param destination - a destination node to which the message has to be transferred
	 */
	public Strategy(){
		
	}
	
	
	public void printResult(Message message){
		System.out.println("the message : "+message.getMessage()+" was transferred successfuly from "+message.getSource().getName()+" to "+message.getDestination().getName());
	}

	

}
